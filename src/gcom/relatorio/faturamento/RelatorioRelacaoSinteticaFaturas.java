package gcom.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.FaturaClienteResponsavelHelper;
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
 * @created 19/11/2007
 */
public class RelatorioRelacaoSinteticaFaturas extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioRelacaoSinteticaFaturas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELACAO_SINTETICA_FATURAS);
	}
	
	@Deprecated
	public RelatorioRelacaoSinteticaFaturas() {
		super(null, "");
	}
	
	private List<RelatorioRelacaoSinteticaFaturasBean> montarRelatorioSintetico(Collection colecaoFaturasHelper){
		
		List<RelatorioRelacaoSinteticaFaturasBean> retorno = new ArrayList();
		
		Iterator iterator = colecaoFaturasHelper.iterator();
		
		while (iterator.hasNext()) {
			
			FaturaClienteResponsavelHelper fatura = (FaturaClienteResponsavelHelper) iterator.next();
			
			String codigoCliente = fatura.getCodigoCliente();
			String nomeCliente = fatura.getNomeCliente();
			String valorTotalFatura = fatura.getValorTotalAPagar();
			
			RelatorioRelacaoSinteticaFaturasBean relatorio = 
				new RelatorioRelacaoSinteticaFaturasBean(codigoCliente,
					nomeCliente,
					valorTotalFatura);
				
			retorno.add(relatorio);
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
		
		FaturaClienteResponsavelHelper fatura = 
			(FaturaClienteResponsavelHelper) Util.retonarObjetoDeColecao(colecaoFaturas);
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("mesAno", fatura.getMesAno());
		
		List relatorioBeans = this.montarRelatorioSintetico(colecaoFaturas);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RELACAO_SINTETICA_FATURAS,
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
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoSinteticaFaturas", this);
	}
}
