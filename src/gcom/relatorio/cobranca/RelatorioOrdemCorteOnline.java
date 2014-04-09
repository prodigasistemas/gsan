package gcom.relatorio.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC1054] Gerar Ordem de Corte
 * 
 * Este Caso Uso permite realizar a emissão de Documentos de Ordem de Corte
 * de forma individual para um determinado imóvel.
 * 
 * @author Hugo Amorim
 * @data 08/02/2010
 *
 */
public class RelatorioOrdemCorteOnline extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioOrdemCorteOnline(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ORDEM_CORTE_ONLINE);
	}

	@Deprecated
	public RelatorioOrdemCorteOnline() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		System.out.println("********************************************");
		System.out.println("ENTROU NO EXECUTAR RELATORIO ORDEM DE CORTE ");
		System.out.println("********************************************");
		
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String descricaoEmpresa = 
			sistemaParametro.getNomeEmpresa() +" - "
				+sistemaParametro.getNomeAbreviadoEmpresa();
				
		
		String inscricaoEmpresa = 
			"CNPJ:"+Util.formatarCnpj(sistemaParametro.getCnpjEmpresa())+
			" INSC. EST. NR. " + sistemaParametro.getInscricaoEstadual();
		
		// valor de retorno
		byte[] retorno = null;

		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Collection<RelatorioOrdemCorteContasDetailBean> 
			colecaoRelatorioOrdemCorteContasDetailBean = this.pesquisarDebitosImovel((Integer) getParametro("matricula"),fachada);
		
		BigDecimal valorTotalDebitos = BigDecimal.ZERO;
		
		for (RelatorioOrdemCorteContasDetailBean bean : colecaoRelatorioOrdemCorteContasDetailBean) {
			
			BigDecimal valor = Util.formatarMoedaRealparaBigDecimal(bean.getValorFatura());
			
			valorTotalDebitos = Util.somaBigDecimal(
					valorTotalDebitos, valor);

		}
		
		List relatorioBeans = new ArrayList();
		
		RelatorioOrdemCorteOnlineBean relatorioOrdemCorteOnlineBean = 
			new RelatorioOrdemCorteOnlineBean(
					(String)getParametro("qtdeEconResidencial"),
					(String)getParametro("qtdeEconComercial"),
					(String)getParametro("qtdeEconIndustrial"),
					(String)getParametro("qtdeEconPublica"),
					(String)getParametro("qtdeEconTotal"),
					new JRBeanCollectionDataSource(
						colecaoRelatorioOrdemCorteContasDetailBean));

		relatorioBeans.add(relatorioOrdemCorteOnlineBean);
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("descricaoEmpresa", descricaoEmpresa);
		parametros.put("inscricaoEmpresa", inscricaoEmpresa);
		parametros.put("idsOrdemServico", getParametro("idsOrdemServico"));
		parametros.put("endereco", getParametro("endereco"));
		parametros.put("matricula",Util.retornaMatriculaImovelFormatada((Integer) getParametro("matricula")));
		parametros.put("situacaoAgua", getParametro("situacaoAgua"));
		parametros.put("sistuacaoEsgoto", getParametro("sistuacaoEsgoto"));
		parametros.put("inscricao", getParametro("inscricao"));
		parametros.put("grupo", getParametro("grupo"));
		parametros.put("dataGeracaoOs", getParametro("dataGeracaoOs"));
		parametros.put("nomeCliente", getParametro("nomeCliente"));
		parametros.put("numeroHidrometro", getParametro("numeroHidrometro"));
		parametros.put("valorTotalDebitos", Util.formatarMoedaReal(valorTotalDebitos));
		parametros.put("anoMesfaturamentoGrupo", getParametro("anoMesfaturamentoGrupo"));
		parametros.put("perfilImovel", getParametro("perfilImovel"));
		parametros.put("unidadeNegocio", getParametro("unidadeNegocio"));
	
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);


		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ORDEM_CORTE_ONLINE,
				parametros, ds, tipoFormatoRelatorio);

	
		// retorna o relatório gerado
		return retorno;
	}

	private Collection<RelatorioOrdemCorteContasDetailBean> pesquisarDebitosImovel(
			Integer idImovelDebitos,Fachada fachada) {
		
		
		Collection<RelatorioOrdemCorteContasDetailBean> colecaoRelatorioOrdemCorteContasDetailBean = 
			new ArrayList<RelatorioOrdemCorteContasDetailBean>();
		
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		String dataVencimentoInicial = "01/01/0001";
		String dataVencimentoFinal = "31/12/9999";

		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String mesInicial = referenciaInicial.substring(0, 2);
		String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
		String anoMesInicial = anoInicial + mesInicial;
		String mesFinal = referenciaFinal.substring(0, 2);
		String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
		String anoMesFinal = anoFinal + mesFinal;

		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF;

		try {
			dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		try {
			dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
		} catch (ParseException ex) {
			dataVencimentoDebitoF = null;
		}

		// seta valores constantes para chamar o metodo que consulta debitos do imovel
		Integer tipoImovel = new Integer(1);
		Integer indicadorPagamento = new Integer(1);
		Integer indicadorConta = new Integer(1);
		Integer indicadorDebito = new Integer(1);
		Integer indicadorCredito = new Integer(1);
		Integer indicadorNotas = new Integer(1);
		Integer indicadorGuias = new Integer(1);
		Integer indicadorAtualizar = new Integer(1);

		// Obtendo os débitos do imovel
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada
				.obterDebitoImovelOuCliente(tipoImovel.intValue(),
						idImovelDebitos.toString().trim(), null, null,
						anoMesInicial, anoMesFinal,
						dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento
								.intValue(), indicadorConta
								.intValue(), indicadorDebito
								.intValue(), indicadorCredito
								.intValue(), indicadorNotas
								.intValue(), indicadorGuias
								.intValue(), indicadorAtualizar
								.intValue(), null);
		
		Collection<ContaValoresHelper> colecaoContas = null;
		
		if(colecaoDebitoImovel.getColecaoContasValores()!=null 
				&& !colecaoDebitoImovel.getColecaoContasValores().isEmpty()){
			
			colecaoContas = colecaoDebitoImovel.getColecaoContasValores();
			
			if(colecaoContas.size()>11){
				int qtdContasParaSomar = colecaoContas.size() - 9;
				int controle = 1;
				
				String referencia = "SD. ANT.";
				String vencimento = "";
				BigDecimal valor = BigDecimal.ZERO;
				boolean jaSomou = false;
				
				for (ContaValoresHelper contaValoresHelper : colecaoContas) {
					if(controle<qtdContasParaSomar){
						valor = Util.somaBigDecimal(valor, contaValoresHelper.getConta().getValorTotal());
						vencimento = Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta());
						++controle;
					}else if(!jaSomou){			
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									referencia,
									vencimento,
									Util.formatarMoedaReal(valor));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
						
						jaSomou = true;
						
						RelatorioOrdemCorteContasDetailBean beanFluxo = 
							new RelatorioOrdemCorteContasDetailBean(
									contaValoresHelper.getConta().getReferenciaFormatada(),
									Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
									Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(beanFluxo);
						
					}else{
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									contaValoresHelper.getConta().getReferenciaFormatada(),
									Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
									Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
					}
				}
				/*
				qtdContasParaSomar = colecaoContas.size() - 9;
				controle = 0;
				for (ContaValoresHelper contaValoresHelper : colecaoContas) {
					if(colecaoRelatorioOrdemCorteContasDetailBean.size()<11){
						
						RelatorioOrdemCorteContasDetailBean bean = 
							new RelatorioOrdemCorteContasDetailBean(
									contaValoresHelper.getConta().getReferenciaFormatada(),
									Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
									Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
						
						colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
					}
				}
				*/
			}else{
				for (ContaValoresHelper contaValoresHelper : colecaoContas) {
					RelatorioOrdemCorteContasDetailBean bean = 
						new RelatorioOrdemCorteContasDetailBean(
								contaValoresHelper.getConta().getReferenciaFormatada(),
								Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta()),
								Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal()));
					
					colecaoRelatorioOrdemCorteContasDetailBean.add(bean);
				}
			}
		}

		return colecaoRelatorioOrdemCorteContasDetailBean;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioOrdemCorteOnline", this);

	}

}
