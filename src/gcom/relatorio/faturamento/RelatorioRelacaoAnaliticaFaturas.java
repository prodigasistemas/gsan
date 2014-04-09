package gcom.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.FaturaClienteResponsavelHelper;
import gcom.faturamento.bean.FaturaItemClienteResponsavelHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Pinto
 * @created 27/08/2007
 */
public class RelatorioRelacaoAnaliticaFaturas extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioRelacaoAnaliticaFaturas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELACAO_ANALITICA_FATURAS);
	}
	
	@Deprecated
	public RelatorioRelacaoAnaliticaFaturas() {
		super(null, "");
	}
	
	private List<RelatorioRelacaoAnaliticaFaturasBean> montarRelatorioAnalitico(Collection colecaoFaturasHelper){
		
		List<RelatorioRelacaoAnaliticaFaturasBean> retorno = new ArrayList();
		
		Iterator iterator = colecaoFaturasHelper.iterator();
		
		while (iterator.hasNext()) {
			
			FaturaClienteResponsavelHelper fatura = (FaturaClienteResponsavelHelper) iterator.next();
			
			String codigoCliente = fatura.getCodigoCliente();
			String nomeCliente = fatura.getNomeCliente();
			String valorTotalFatura = fatura.getValorTotalAPagar();
			
			Collection colecaoItensHelper = fatura.getColecaoFaturaItemClienteResponsavelHelper();
			
			Iterator iteraItens = colecaoItensHelper.iterator();
			while (iteraItens.hasNext()) {
				
				FaturaItemClienteResponsavelHelper itens = (FaturaItemClienteResponsavelHelper) iteraItens.next();
				
				String matricula = itens.getMatricula();
				String inscricao = itens.getInscricao();
				String categoria = itens.getCategoria();
				String qtdEconomias = itens.getQtdEconomias();
				String leituraAnterior = itens.getLeituraAnterior();
				String leituraAtual = itens.getLeituraAtual();
				String media = itens.getMedia();
				String consumoFaturado = itens.getConsumoFaturado();
				String dataLeitura = itens.getDataLeitura();
				String dataVencimento = itens.getDataVencimento();
				String consumoAgua = itens.getConsumoAgua();
				String rateioAgua = itens.getRateioAgua();
				String valorAgua = itens.getValorAgua();
				String consumoEsgoto = itens.getConsumoEsgoto();
				String rateioEsgoto = itens.getRateioEsgoto();
				String valorEsgoto = itens.getValorEsgoto();
				String debitoCobrado = itens.getDebitoCobrado();
				String creditoRealizado = itens.getCreditoRealizado();
				String totalConta = itens.getValor();
				String endereco = itens.getEndereco();
				String valorImpostos = itens.getValorImpostos();
				
				RelatorioRelacaoAnaliticaFaturasBean relatorio = 
					new RelatorioRelacaoAnaliticaFaturasBean(codigoCliente,
						nomeCliente,
						matricula,
						inscricao,
						categoria,
						qtdEconomias,
						leituraAnterior,
						leituraAtual,
						media,
						consumoFaturado,
						dataLeitura,
						dataVencimento,
						consumoAgua,
						rateioAgua,
						valorAgua,
						consumoEsgoto,
						rateioEsgoto,
						valorEsgoto,
						debitoCobrado,
						creditoRealizado,
						totalConta,
						valorTotalFatura,
						endereco,
						valorImpostos
					);
				
				retorno.add(relatorio);
			}
		}
		
		return retorno;
	}
	
	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		
		Collection colecaoFaturas = (Collection) getParametro("colecaoFaturas");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		FaturaClienteResponsavelHelper fatura = (FaturaClienteResponsavelHelper) Util.retonarObjetoDeColecao(colecaoFaturas);
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("mesAno", fatura.getMesAno());
		
		List relatorioBeans = this.montarRelatorioAnalitico(colecaoFaturas);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RELACAO_ANALITICA_FATURAS,
				parametros, ds, tipoFormatoRelatorio);



		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoAnaliticaFaturas", this);
	}
}
