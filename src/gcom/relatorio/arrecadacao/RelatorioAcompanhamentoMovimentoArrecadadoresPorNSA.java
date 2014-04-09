package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.bean.MovimentoArrecadadoresPorNSAHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 04/07/2007
 */
public class RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA extends TarefaRelatorio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES_POR_NSA);
	}
	
	@Deprecated
	public RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA() {
		super(null, "");
	}

	private Collection<RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean> inicializarBeanRelatorio(
			Collection<MovimentoArrecadadoresPorNSAHelper> dadosRelatorio) {

		Collection<RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean> retorno = new ArrayList();
		
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			MovimentoArrecadadoresPorNSAHelper helper = (MovimentoArrecadadoresPorNSAHelper)iterator.next();
			
			String banco = "";
			if (helper.getBanco() != null) {
				banco = helper.getBanco();
			}
			
			String formaArrecadacao = "";
			if (helper.getFormaArrecadacao() != null) {
				formaArrecadacao = helper.getFormaArrecadacao();
			}
			
			String nsa = "";
			if (helper.getNsa() != null) {
				nsa = helper.getNsa().toString();
			}
			
			String dataGeracao = "";
			if (helper.getDataGeracao() != null) {
				dataGeracao = Util.formatarData(helper.getDataGeracao());
			}
			
			Integer qtdeRegistros = 0;
			if (helper.getFormaArrecadacao() != null) {
				qtdeRegistros = helper.getQtdeRegistros();
			}
			
			BigDecimal valor = new BigDecimal("0.00");
			if (helper.getValor() != null && !helper.getValor().equals(0) ) {
				//BigDecimal valor = new BigDecimal("0.00");
                String valorString = "00" +  helper.getValor().toString();
				Integer tamanho = valorString.length();
				int inteiro = tamanho - 2;
				valor = new BigDecimal(valorString.substring(0, inteiro) +"."+ 
						valorString.substring(inteiro, inteiro + 2));
			}
			
			String tarifa = "";
			if (helper.getTarifa() != null) {
				tarifa = Util.formatarMoedaReal(helper.getTarifa());
			}
			
			BigDecimal valorAPagar = new BigDecimal("0.00");
			if (helper.getTarifa() != null) {
				valorAPagar = helper.getTarifa().multiply(new BigDecimal(helper.getQtdeRegistros()));
			}
			
			String mesAnoArrecadacao = "";
			if(helper.getDataGeracao() != null){
				String mes = ""+Util.getMes(helper.getDataGeracao());
				if(mes.length() == 1){
				  mesAnoArrecadacao = "0"+Util.getMes(helper.getDataGeracao())+"/"+Util.getAno(helper.getDataGeracao());
				}else{
				  mesAnoArrecadacao = Util.getMes(helper.getDataGeracao())+"/"+Util.getAno(helper.getDataGeracao());	
				}
			}
			
			RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean bean = new 
			RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean(banco, formaArrecadacao,
			nsa, dataGeracao, qtdeRegistros, valor, tarifa, valorAPagar, mesAnoArrecadacao);
			
			retorno.add(bean);
			
		}
		
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Collection dadosRelatorio = (Collection)getParametro("colecaoMovimentoArrecadadoresPorNSA");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		//String periodo = (String)getParametro("periodo");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			
		//parametros.put("periodo", periodo);
		
		parametros.put("tipoFormatoRelatorio", "R0619");

		Collection<RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES_POR_NSA,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES_POR_NSA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		//retorno = ((Collection) getParametro("idsGuiaDevolucao")).size();

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA", this);
	}
}
