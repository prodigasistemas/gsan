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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de imoveis com faturas em atraso
 * 
 * @author Bruno Barros
 * @created 10/12/2007
 */
public class RelatorioImoveisFaturasAtrasoAgrupadasLocalizacao extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADAS_LOCALIZACAO);
	}

	@Deprecated
	public RelatorioImoveisFaturasAtrasoAgrupadasLocalizacao() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro = 
			(FiltrarRelatorioImoveisFaturasAtrasoHelper) getParametro("filtrarRelatorioImoveisFaturasAtrasoHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		List<RelatorioImoveisFaturasAtrasoBean> relatorioBeans = new ArrayList<RelatorioImoveisFaturasAtrasoBean>();

		Fachada fachada = Fachada.getInstancia();

		RelatorioImoveisFaturasAtrasoBean relatorioImoveisFaturasAtrasoBean = null;

		Collection<RelatorioImoveisFaturasAtrasoHelper> colecao =  
			fachada.pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(filtro);

		if ( !Util.isVazioOrNulo(colecao)){

			Iterator<RelatorioImoveisFaturasAtrasoHelper> colecaoIterator = colecao.iterator();

			while (colecaoIterator.hasNext()) {

				RelatorioImoveisFaturasAtrasoHelper helper = colecaoIterator.next();
				
				relatorioImoveisFaturasAtrasoBean = 
					new RelatorioImoveisFaturasAtrasoBean(helper);

				relatorioBeans.add(relatorioImoveisFaturasAtrasoBean);				
			}
		}

		Map<String,Object> parametros = new HashMap<String,Object>();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("gerRegionalFiltro", getParametro("gerRegionalFiltro"));
		parametros.put("uniNegocioFiltro",getParametro("uniNegocioFiltro"));
		parametros.put("qtdFaturasFiltro", getParametro("qtdFaturasFiltro"));
		parametros.put("localidadeFiltro", getParametro("localidadeFiltro"));
		parametros.put("setorFiltro", getParametro("setorFiltro"));
		parametros.put("rotaFiltro", getParametro("rotaFiltro"));
		parametros.put("refFaturasFiltro", getParametro("refFaturasFiltro"));
		parametros.put("seqRotaFiltro", getParametro("seqRotaFiltro"));
		parametros.put("valorFaturasFiltro",getParametro("valorFaturasFiltro"));
		parametros.put("esfPoderFiltro", getParametro("esfPoderFiltro"));
		parametros.put("LigAguaFiltro", getParametro("LigAguaFiltro"));
		parametros.put("categoriaFiltro", getParametro("categoriaFiltro"));
		parametros.put("hidrometro", getParametro("hidrometro"));

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADAS_LOCALIZACAO,
				parametros, ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.IMOVEIS_FATURAS_ATRASO_AGRUPADAS_LOCALIZACAO,
					this.getIdFuncionalidadeIniciada());
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 0;

		retorno = 
			Fachada.getInstancia().pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
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
