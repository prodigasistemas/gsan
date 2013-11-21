package gcom.relatorio.gerencial.micromedicao;

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
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioResumoDistritoOperacional extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioResumoDistritoOperacional(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_RESUMO_DISTRITO_OPERACIONAL);
	}
	
	@Deprecated
	public RelatorioResumoDistritoOperacional() {
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();

		FiltrarRelatorioResumoDistritoOperacionalHelper filtro = (FiltrarRelatorioResumoDistritoOperacionalHelper) getParametro("filtrarRelatorioResumoDistritoOperacionalHelper");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioResumoDistritoOperacionalBean relatorioBean = null;

		Collection<RelatorioResumoDistritoOperacionalHelper> colecao = fachada
				.pesquisarRelatorioResumoDistritoOperacional(filtro);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();
			
			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {
				
				RelatorioResumoDistritoOperacionalHelper helper = (RelatorioResumoDistritoOperacionalHelper) colecaoIterator
						.next();

				relatorioBean = new RelatorioResumoDistritoOperacionalBean(helper);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
				

			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		parametros = this.setarParametros();
		

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_DISTRITO_OPERACIONAL,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_RESUMO_DISTRITO_OPERACIONAL,
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

		retorno = Fachada
				.getInstancia()
				.pesquisarTotalRegistroRelatorioResumoDistritoOperacional(
						(FiltrarRelatorioResumoDistritoOperacionalHelper) getParametro("filtrarRelatorioResumoDistritoOperacionalHelper"));

		if (retorno == 0) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Relatório");
		}

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoDistritoOperacional", this);

	}

	public Map setarParametros() {
		Map parametros = new HashMap();
		FiltrarRelatorioResumoDistritoOperacionalHelper filtro = (FiltrarRelatorioResumoDistritoOperacionalHelper) getParametro("filtrarRelatorioResumoDistritoOperacionalHelper");
		SistemaParametro sistemaParametro = Fachada.getInstancia()
				.pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		String mesAno = gcom.util.Util.formatarAnoMesParaMesAno(filtro
				.getMesAno());

		if (filtro.getMesAno() != null && !filtro.getMesAno().equals("")) {
			parametros.put("mesAno", mesAno);
		}
		if (filtro.getDescDistritoOperacional() != null
				&& !filtro.getDescDistritoOperacional().equals("")) {
			parametros.put("nomeDistritoOperacional", filtro
					.getDescDistritoOperacional());
		}
		if (filtro.getDistritoOperacional() != null
				&& !filtro.getDistritoOperacional().equals("")) {
			parametros.put("idDistritoOperacional", filtro
					.getDistritoOperacional());
		}
		if (filtro.getDescUnidadeNegocio() != null
				&& !filtro.getDescUnidadeNegocio().equals("")) {
			parametros
					.put("nomeUnidadeNegocio", filtro.getDescUnidadeNegocio());
		}
		if (filtro.getUnidadeNegocio() != null
				&& !filtro.getUnidadeNegocio().equals("")) {
			parametros
					.put("idUnidadeNegocio", filtro.getUnidadeNegocio());
		}
		if (filtro.getDescGerenciaRegional() != null
				&& !filtro.getDescGerenciaRegional().equals("")) {
			parametros.put("nomeGerenciaRegional", filtro
					.getDescGerenciaRegional());
		}
		if (filtro.getGerenciaRegional() != null
				&& !filtro.getGerenciaRegional().equals("")) {
			parametros.put("idGerenciaRegional", filtro
					.getGerenciaRegional());
		}
		if (filtro.getLocalidadeInicial() != null
				&& !filtro.getLocalidadeInicial().equals("")) {
			parametros.put("idLocalidadeInicial", filtro.getLocalidadeInicial()
					.toString());
		}
		if (filtro.getNomeLocalidadeInicial() != null
				&& !filtro.getNomeLocalidadeInicial().equals("")) {
			parametros.put("nomeLocalidadeInicial", filtro
					.getNomeLocalidadeInicial().toString());
		}
		if (filtro.getSetorComercialInicial() != null
				&& !filtro.getSetorComercialInicial().equals("")) {
			parametros.put("idSetorComercialInicial", filtro
					.getCodigoSetorComercialInicial());
		}
		if (filtro.getNomeSetorComercialInicial() != null
				&& !filtro.getNomeSetorComercialInicial().equals("")) {
			parametros.put("nomeSetorComercialInicial", filtro
					.getNomeSetorComercialInicial().toString());
		}
		if (filtro.getLocalidadeFinal() != null
				&& !filtro.getLocalidadeFinal().equals("")) {
			parametros.put("idLocalidadeFinal", filtro.getLocalidadeFinal()
					.toString());
		}
		if (filtro.getNomeLocalidadeFinal() != null
				&& !filtro.getNomeLocalidadeFinal().equals("")) {
			parametros.put("nomeLocalidadeFinal", filtro
					.getNomeLocalidadeFinal().toString());
		}
		if (filtro.getSetorComercialFinal() != null
				&& !filtro.getSetorComercialFinal().equals("")) {
			parametros.put("idSetorComercialFinal", filtro
					.getCodigoSetorComercialFinal());
		}
		if (filtro.getNomeSetorComercialFinal() != null
				&& !filtro.getNomeSetorComercialFinal().equals("")) {
			parametros.put("nomeSetorComercialFinal", filtro
					.getNomeSetorComercialInicial().toString());
		}
		return parametros;
	}

}
