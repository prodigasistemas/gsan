package gcom.relatorio.faturamento.autoinfracao;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.autoinfracao.AutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.autoinfracao.FiltroAutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.FiltroAutosInfracao;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioManterAutoInfracao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioManterAutoInfracao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_AUTOS_INFRACAO_MANTER);
	}

	@Deprecated
	public RelatorioManterAutoInfracao() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao
	 *            pagamento Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		// Integer idFuncionalidadeIniciada =
		// this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroAutosInfracao filtroAutosInfracao = (FiltroAutosInfracao) getParametro("filtroAutosInfracao");

		String idImovel = (String) getParametro("idImovel");

		String descricaoImovel = (String) getParametro("descricaoImovel");

		String idFuncionario = (String) getParametro("idFuncionario");

		String idFiscalizacaoSituacao = (String) getParametro("idFiscalizacaoSituacao");

		String idAutoInfracaoSituacao = (String) getParametro("idAutoInfracaoSituacao");

		String dataEmissaoInicial = (String) getParametro("dataEmissaoInicial");

		String dataEmissaoFinal = (String) getParametro("dataEmissaoFinal");

		String dataInicioRecursoInicial = (String) getParametro("dataInicioRecursoInicial");

		String dataInicioRecursoFinal = (String) getParametro("dataInicioRecursoFinal");

		String dataTerminoRecursoInicial = (String) getParametro("dataTerminoRecursoInicial");

		String dataTerminoRecursoFinal = (String) getParametro("dataTerminoRecursoFinal");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoAutosInfracao = fachada.pesquisar(
				filtroAutosInfracao, AutosInfracao.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoAutosInfracao != null && !colecaoAutosInfracao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator it = colecaoAutosInfracao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (it.hasNext()) {

				AutosInfracao autosInfracao = (AutosInfracao) it.next();

				RelatorioManterAutosInfracaoBean relatorioBean = new RelatorioManterAutosInfracaoBean();

				relatorioBean.setId(autosInfracao.getId().toString());

				relatorioBean.setDataEmissao(Util.formatarData(autosInfracao
						.getDataEmissao()));

				relatorioBean.setDataInicioRecurso(Util
						.formatarData(autosInfracao.getDataInicioRecurso()));

				relatorioBean.setDataFimRecurso(Util.formatarData(autosInfracao
						.getDataTerminoRecurso()));

				relatorioBean.setFuncionario(autosInfracao.getFuncionario()
						.getNome());

				relatorioBean
						.setDescricaoIrregularidadeConstatada(autosInfracao
								.getFiscalizacaoSituacao()
								.getDescricaoFiscalizacaoSituacao());

				relatorioBean.setSituacao(autosInfracao
						.getAutoInfracaoSituacao().getDescricao());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("idImovel", idImovel == null ? "" : "" + idImovel);

		parametros.put("descricaoImovel", descricaoImovel == null ? "" : ""
				+ descricaoImovel);

		parametros.put("idFiscalizacaoSituacao",
				idFiscalizacaoSituacao == null ? "" : ""
						+ idFiscalizacaoSituacao);

		if (idFiscalizacaoSituacao != null
				&& !idFiscalizacaoSituacao.equals("-1")) {

			FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();

			filtroFiscalizacaoSituacao.adicionarParametro(new ParametroSimples(
					FiltroFiscalizacaoSituacao.ID, idFiscalizacaoSituacao));
			// Verifica se os dados foram informados da tabela existem e joga
			// numa
			// colecao

			Collection<FiscalizacaoSituacao> colecaoFiscalizacaoSituacao = fachada
					.pesquisar(filtroFiscalizacaoSituacao,
							FiscalizacaoSituacao.class.getName());

			FiscalizacaoSituacao fiscalizacaoSituacao = (FiscalizacaoSituacao) Util
					.retonarObjetoDeColecao(colecaoFiscalizacaoSituacao);

			parametros.put("descricaoFiscalizacaoSituacao",
					fiscalizacaoSituacao.getDescricaoFiscalizacaoSituacao());

		} else {
			parametros.put("idFiscalizacaoSituacao", "");
			parametros.put("descricaoFiscalizacaoSituacao", "");
		}

		if (idAutoInfracaoSituacao != null
				&& !idAutoInfracaoSituacao.equals("-1")) {
			FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();

			filtroAutoInfracaoSituacao.adicionarParametro(new ParametroSimples(
					FiltroAutoInfracaoSituacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Verifica se os dados foram informados da tabela existem e joga
			// numa
			// colecao

			Collection<AutoInfracaoSituacao> colecaoAutoInfracaoSituacao = fachada
					.pesquisar(filtroAutoInfracaoSituacao,
							AutoInfracaoSituacao.class.getName());

			AutoInfracaoSituacao autoInfracaoSituacao = (AutoInfracaoSituacao) Util
					.retonarObjetoDeColecao(colecaoAutoInfracaoSituacao);

			parametros.put("idAutoInfracaoSituacao", autoInfracaoSituacao
					.getId().toString());
			parametros.put("descricaoAutoInfracaoSituacao",
					autoInfracaoSituacao.getDescricao());
		} else {
			parametros.put("idAutoInfracaoSituacao", "");
			parametros.put("descricaoAutoInfracaoSituacao", "");
		}

		if (idFuncionario != null && !idFuncionario.equals("")) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());
			
			Funcionario funcionario = (Funcionario) Util
					.retonarObjetoDeColecao(colecaoFuncionario);
			
			parametros.put("idFuncionario", funcionario.getId().toString());
			parametros.put("descricaoFuncionario", funcionario.getNome());
		} else {
			parametros.put("idFuncionario", "");
			parametros.put("descricaoFuncionario", "");
		}

		if (dataEmissaoInicial == null || dataEmissaoInicial.equals("")
				|| dataEmissaoFinal == null || dataEmissaoFinal.equals("")) {
			parametros.put("dataEmissao", "");
		} else {
			parametros.put("dataEmissao", dataEmissaoInicial + " à "
					+ dataEmissaoFinal);
		}

		if (dataInicioRecursoInicial == null || dataInicioRecursoFinal == null
				|| dataInicioRecursoInicial.equals("")
				|| dataInicioRecursoFinal.equals("")) {
			parametros.put("dataInicioRecurso", "");
		} else {
			parametros.put("dataInicioRecurso", dataInicioRecursoInicial
					+ " à " + dataInicioRecursoFinal);
		}

		if (dataTerminoRecursoInicial == null
				|| dataTerminoRecursoFinal == null
				|| dataTerminoRecursoInicial.equals("")
				|| dataTerminoRecursoFinal.equals("")) {
			parametros.put("dataTerminoRecurso", "");
		} else {
			parametros.put("dataTerminoRecurso", dataTerminoRecursoFinal
					+ " à " + dataTerminoRecursoFinal);
		}

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_AUTOS_INFRACAO_MANTER,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		// try {
		// persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
		// idFuncionalidadeIniciada);
		// } catch (ControladorException e) {
		// e.printStackTrace();
		// throw new TarefaException("Erro ao gravar relatório no sistema", e);
		// }
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {

		int retorno = 0;

		// retorno = Fachada.getInstancia().totalRegistrosPesquisa(
		// (FiltroLocalidade) getParametro("filtroLocalidade"),
		// Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterAutosInfracao", this);
	}

}
