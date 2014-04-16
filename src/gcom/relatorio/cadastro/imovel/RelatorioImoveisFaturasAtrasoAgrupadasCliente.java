package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsável pela lógica de Relatório de Imóveis com Fatura em Atraso
 * Agrupadas, com critétio de filtro por Cliente.
 *
 * @author Marlon Patrick
 * @since 02/09/2009
 */
public class RelatorioImoveisFaturasAtrasoAgrupadasCliente extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisFaturasAtrasoAgrupadasCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADAS_CLIENTE);
	}

	@Deprecated
	public RelatorioImoveisFaturasAtrasoAgrupadasCliente() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro = 
			(FiltrarRelatorioImoveisFaturasAtrasoHelper) getParametro("filtrarRelatorioImoveisFaturasAtrasoHelper");
		
		Map<String, Object> parametros = criarParametrosRelatorio();

		RelatorioDataSource ds = new RelatorioDataSource( executarConsultaECriarColecaoRelatorioBeans(filtro) );

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADAS_CLIENTE,
				parametros, ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.IMOVEIS_FATURAS_ATRASO_AGRUPADAS_CLIENTE,
					this.getIdFuncionalidadeIniciada());
			
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		return retorno;
	}

	/**
	 * Executa a consulta no banco e com o resultado
	 * cria os objetos RelatorioBeans.
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	private List<RelatorioImoveisFaturasAtrasoBean> executarConsultaECriarColecaoRelatorioBeans(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) {

		Collection<RelatorioImoveisFaturasAtrasoHelper> colecao =  
			Fachada.getInstancia().pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(filtro);

		List<RelatorioImoveisFaturasAtrasoBean> relatorioBeans = new ArrayList<RelatorioImoveisFaturasAtrasoBean>();

		if ( !Util.isVazioOrNulo(colecao)) {
			for(RelatorioImoveisFaturasAtrasoHelper helper: colecao){
				relatorioBeans.add(new RelatorioImoveisFaturasAtrasoBean(helper));								
			}
		}

		return relatorioBeans;
	}

	/**
	 * Método configura os parametros que serão exibidos no relatório
	 * como sendo os filtros utilizados pelo usuario para gerá-lo.
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametrosRelatorio() {
		
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();

		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("clienteSuperiorFiltro", getParametro("clienteSuperiorFiltro"));
		parametros.put("clienteFiltro", getParametro("clienteFiltro"));
		parametros.put("tipoRelacaoFiltro", getParametro("tipoRelacaoFiltro"));
		parametros.put("responsavelFiltro", getParametro("responsavelFiltro"));
		parametros.put("hidrometro", getParametro("hidrometro"));
		
		parametros.put("esfPoderFiltro", getParametro("esfPoderFiltro"));
		parametros.put("LigAguaFiltro", getParametro("LigAguaFiltro"));
		parametros.put("categoriaFiltro", getParametro("categoriaFiltro"));
		parametros.put("situacaoCobrancaFiltro", getParametro("situacaoCobrancaFiltro"));
		parametros.put("qtdFaturasFiltro", getParametro("qtdFaturasFiltro"));
		parametros.put("refFaturasFiltro", getParametro("refFaturasFiltro"));
		parametros.put("valorFaturasFiltro",getParametro("valorFaturasFiltro"));
		
		return parametros;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 0;

		retorno = 
			Fachada.getInstancia().pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
				(FiltrarRelatorioImoveisFaturasAtrasoHelper) 
					getParametro("filtrarRelatorioImoveisFaturasAtrasoHelper"));
		
		if(retorno == 0){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Relatório");
		}

		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisFaturasAtraso", this);

	}

}
