package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcaoCronograma;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.bean.CobrancaCronogramaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAdicionarAcaoCobrancaCronogramaPopupAction extends
		GcomAction {
	/**
	 * [UC00313] Manter Cronograma Cobrança
	 * 
	 * @author Flávio Cordeiro
	 * 
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("adicionarAcaoCobrancaCronogramaPopup");

		CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("limpar") != null) {
			sessao.removeAttribute("cobrancaAcaoEscolhida");
			sessao.removeAttribute("colecaoCobrancaAtiviade");
			cobrancaActionForm.setIdCobrancaAcao("-1");
		}

		Collection colecaoAcaoCobrancaNovo = null;
		Collection colecaoAcaoCronogramaNaBase = new ArrayList();
		Collection colecaoAcaoCobrancaEscolhida = null;

		CobrancaAcao cobrancaAcaoEscolhida = null;

		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();

		if (sessao.getAttribute("acoesCobranca") != null) {
			colecaoAcaoCronogramaNaBase = (Collection) sessao
					.getAttribute("acoesCobranca");
		} else if (sessao.getAttribute("colecaoCobrancaCronogramaHelper") != null) {
			colecaoAcaoCronogramaNaBase = (Collection) sessao
					.getAttribute("colecaoCobrancaCronogramaHelper");
		} else {
			FiltroCobrancaAcaoCronograma filtroCobrancaAcaoCronograma = new FiltroCobrancaAcaoCronograma();

			filtroCobrancaAcaoCronograma
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao");
			filtroCobrancaAcaoCronograma
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupoCronogramaMes");
			filtroCobrancaAcaoCronograma
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoCronograma.ID_COBRANCA_GRUPO_CRONOGRAMA_MES,
							cobrancaActionForm
									.getIdCobrancaGrupoCronogramaMes()));

			colecaoAcaoCronogramaNaBase = fachada.pesquisar(
					filtroCobrancaAcaoCronograma, CobrancaAcaoCronograma.class
							.getName());
		}

		Iterator iterator = colecaoAcaoCronogramaNaBase.iterator();

		filtroCobrancaAcao
				.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora");

		CobrancaAcaoCronograma cobrancaAcaoCronograma = null;
		CobrancaAcao cobrancaAcaoAux = null;
		while (iterator.hasNext()) {
			if (sessao.getAttribute("colecaoCobrancaCronogramaHelper") == null) {
				cobrancaAcaoCronograma = (CobrancaAcaoCronograma) iterator
						.next();
			} else {
				if (sessao.getAttribute("acoesCobranca") != null) {
					cobrancaAcaoAux = (CobrancaAcao) iterator.next();
				} else {
					cobrancaAcaoCronograma = ((CobrancaCronogramaHelper) iterator
							.next()).getCobrancaAcaoCronograma();
				}
			}

			if (cobrancaAcaoAux != null) {
				filtroCobrancaAcao
				.adicionarParametro(new ParametroSimplesDiferenteDe(
						FiltroCobrancaAcao.ID, cobrancaAcaoAux.getId()));
			} else {
				filtroCobrancaAcao
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroCobrancaAcao.ID, cobrancaAcaoCronograma
										.getCobrancaAcao().getId()));
			}
		}

		if (cobrancaActionForm.getIdCobrancaAcao() != null
				&& !cobrancaActionForm.getIdCobrancaAcao().trim().equals("-1")) {
			filtroCobrancaAcao
					.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroCobrancaAcao.ID, cobrancaActionForm
									.getIdCobrancaAcao()));
		}

		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_CRONOGRAMA,
				CobrancaAcao.INDICADOR_CRONOGRAMA_ATIVO));

		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		colecaoAcaoCobrancaNovo = fachada.pesquisar(filtroCobrancaAcao,
				CobrancaAcao.class.getName());

		if ((colecaoAcaoCobrancaNovo.isEmpty() && httpServletRequest
				.getParameter("reload") == null)
				|| (colecaoAcaoCobrancaNovo.isEmpty() && !httpServletRequest
						.getParameter("reload").equalsIgnoreCase("N"))) {
			throw new ActionServletException(
					"atencao.dependencias.nenhuma_acao_adicionar");
		}

		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAtividade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCobrancaAtividade
				.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
		filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA,
				CobrancaAtividade.ATIVO_CRONOGRAMA));

		Collection colecaoCobrancaAtividade = fachada.pesquisar(
				filtroCobrancaAtividade, CobrancaAtividade.class.getName());
		sessao
				.setAttribute("colecaoCobrancaAtiviade",
						colecaoCobrancaAtividade);

		if (cobrancaActionForm.getIdCobrancaAcao() != null
				&& !cobrancaActionForm.getIdCobrancaAcao().trim().equals("-1")) {
			filtroCobrancaAcao.limparListaParametros();
			filtroCobrancaAcao
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora");
			filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaAcao.ID, cobrancaActionForm
							.getIdCobrancaAcao()));

			colecaoAcaoCobrancaEscolhida = fachada.pesquisar(
					filtroCobrancaAcao, CobrancaAcao.class.getName());

			cobrancaAcaoEscolhida = (CobrancaAcao) colecaoAcaoCobrancaEscolhida
					.iterator().next();

			sessao.setAttribute("cobrancaAcaoEscolhida", cobrancaAcaoEscolhida);
		}

		if (httpServletRequest.getParameter("adicionar") != null) {

			Collection atividadesCobrancaObrigatoriedadeAtivo = (Collection) sessao
					.getAttribute("atividadesCobrancaObrigatoriedadeAtivo");

			httpServletRequest.setAttribute("adicionar", "adicionar");
			CobrancaCronogramaHelper cobrancaCronogramaHelper = new CobrancaCronogramaHelper();

			if (cobrancaAcaoEscolhida == null) {
				cobrancaAcaoEscolhida = (CobrancaAcao) sessao
						.getAttribute("cobrancaAcaoEscolhida");
			}

			if (cobrancaAcaoEscolhida != null) {
				Iterator iteratorColecaoNova = colecaoAcaoCobrancaNovo
						.iterator();
				CobrancaAcao cobrancaNovoTesteRemover = null;
				Collection colecaoCobrancaAcaoReporNova = new ArrayList();
				if (!colecaoAcaoCobrancaNovo.isEmpty()) {
					while (iteratorColecaoNova.hasNext()) {
						cobrancaNovoTesteRemover = (CobrancaAcao) iteratorColecaoNova
								.next();
						if (cobrancaAcaoEscolhida != null
								&& !cobrancaAcaoEscolhida.getId().equals(
										cobrancaNovoTesteRemover.getId())) {
							colecaoCobrancaAcaoReporNova
									.add(cobrancaNovoTesteRemover);
						}
					}
				}
				colecaoAcaoCobrancaNovo = colecaoCobrancaAcaoReporNova;

				CobrancaAtividade cobrancaAtividade = null;
				CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = null;
				Collection cobrancasAtividadesParaInsercao = new ArrayList();

				CobrancaAcaoCronograma cobrancaAcaoCronogramaNovo = new CobrancaAcaoCronograma();

				cobrancaAcaoCronogramaNovo
						.setCobrancaAcao(cobrancaAcaoEscolhida);

				cobrancaCronogramaHelper
						.setCobrancaAcaoCronograma(cobrancaAcaoCronogramaNovo);
				Collection colecaoAtividades = (Collection) sessao
						.getAttribute("colecaoCobrancaAtiviade");
				Collection colecaoCobrancaHelperNaBase = (Collection) sessao
						.getAttribute("colecaoCobrancaCronogramaHelper");

				Iterator iteratorAtividades = colecaoAtividades.iterator();
				Iterator iteratorTesteSucessora = colecaoCobrancaHelperNaBase
						.iterator();

				String idAcaoCobranca = "";
				String dataPrevista = "";
				// String anoMes = "";
				// String mesAno = "";
				int verificaDataPreenchida = 0;

				int contadorAtividades = 0;

				/**
				 * 
				 * 
				 */
				CobrancaCronogramaHelper cobrancaCronogramaHelperTesteSucessora = null;
				boolean predecessoraNaColecao = false;
				if (cobrancaAcaoEscolhida != null
						&& cobrancaAcaoEscolhida.getCobrancaAcaoPredecessora() != null) {
					// Integer idPredecessora = null;
					while (iteratorTesteSucessora.hasNext()) {
						cobrancaCronogramaHelperTesteSucessora = (CobrancaCronogramaHelper) iteratorTesteSucessora
								.next();

						if (cobrancaAcaoEscolhida.getCobrancaAcaoPredecessora()
								.getId().equals(
										cobrancaCronogramaHelperTesteSucessora
												.getCobrancaAcaoCronograma()
												.getCobrancaAcao().getId())) {
							predecessoraNaColecao = true;
						} else {
							// idPredecessora =
							// cobrancaCronogramaHelperTesteSucessora.getCobrancaAcaoCronograma()
							// .getCobrancaAcao().getId();
						}
					}
					if (!predecessoraNaColecao) {
						FiltroCobrancaAcao filtroCobrancaAcaoErro = new FiltroCobrancaAcao();
						filtroCobrancaAcaoErro
								.adicionarParametro(new ParametroSimples(
										FiltroCobrancaAcao.ID,
										cobrancaAcaoEscolhida
												.getCobrancaAcaoPredecessora()
												.getId()));
						Collection colecaoCobrancaAcaroErro = fachada
								.pesquisar(filtroCobrancaAcaoErro,
										CobrancaAcao.class.getName());
						CobrancaAcao cobrancaAcao = null;
						if (!colecaoCobrancaAcaroErro.isEmpty()) {
							cobrancaAcao = (CobrancaAcao) Util
									.retonarObjetoDeColecao(colecaoCobrancaAcaroErro);
							throw new ActionServletException(
									"atencao.dependencias.adionar_predecessora",
									null, cobrancaAcao
											.getDescricaoCobrancaAcao());
						} else {
							throw new ActionServletException(
									"atencao.dependencias.adionar_predecessora");
						}

					}
				}

				if (cobrancaAcaoEscolhida.getIndicadorObrigatoriedade()
						.intValue() == 1) {
					while (iteratorAtividades.hasNext()) {
						contadorAtividades += 1;

						cobrancaAtividade = (CobrancaAtividade) iteratorAtividades
								.next();
						// --------pega o valor de comandar.Ex: comandar2
						idAcaoCobranca = (String) httpServletRequest
								.getParameter("comandar"
										+ cobrancaAcaoEscolhida.getId()
												.toString() + "atividade"
										+ cobrancaAtividade.getId().toString());
						// -------- separa o id da string comandar

						dataPrevista = "";
						dataPrevista = (String) httpServletRequest
								.getParameter("a"
										+ cobrancaAcaoEscolhida.getId()
												.toString() + "n"
										+ cobrancaAtividade.getId().toString());

						if (dataPrevista.trim().equals("")
								&& cobrancaAtividade
										.getIndicadorObrigatoriedade() == 1) {
							throw new ActionServletException(
									"atencao.cobranca.data_prevista_acao_obrigatoria");
						} else {
							// ----seta os valores no objeto
							// CobrancaAcaoAtividadeCronograma
							cobrancaAcaoAtividadeCronograma = new CobrancaAcaoAtividadeCronograma();
							cobrancaAcaoAtividadeCronograma
									.setCobrancaAtividade(cobrancaAtividade);
							cobrancaAcaoAtividadeCronograma
									.setCobrancaAcaoCronograma(cobrancaAcaoCronogramaNovo);

							cobrancaAcaoAtividadeCronograma
									.setDataPrevista(Util
											.converteStringParaDate(dataPrevista));
							if (idAcaoCobranca != null
									&& idAcaoCobranca.trim().equals("1")) {
								cobrancaAcaoAtividadeCronograma
										.setComando(Util
												.converteStringParaDateHora(dataPrevista
														+ " "
														+ Util
																.formatarHoraSemData(new Date())));
							} else {
								cobrancaAcaoAtividadeCronograma
										.setComando(null);
							}

							cobrancasAtividadesParaInsercao
									.add(cobrancaAcaoAtividadeCronograma);

						}
					}
					cobrancaCronogramaHelper
							.setCobrancasAtividadesParaInsercao(cobrancasAtividadesParaInsercao);

					/**
					 * 
					 */
					colecaoCobrancaHelperNaBase.add(cobrancaCronogramaHelper);
				} else {
					verificaDataPreenchida = 0;
					while (iteratorAtividades.hasNext()) {
						contadorAtividades += 1;

						cobrancaAtividade = (CobrancaAtividade) iteratorAtividades
								.next();

						// --------pega o valor de comandar.Ex: comandar2
						idAcaoCobranca = (String) httpServletRequest
								.getParameter("comandar"
										+ cobrancaAcaoEscolhida.getId()
												.toString() + "atividade"
										+ cobrancaAtividade.getId().toString());
						// -------- separa o id da string comandar

						dataPrevista = "";
						dataPrevista = (String) httpServletRequest
								.getParameter("a"
										+ cobrancaAcaoEscolhida.getId()
												.toString() + "n"
										+ cobrancaAtividade.getId().toString());

						cobrancaAcaoAtividadeCronograma = new CobrancaAcaoAtividadeCronograma();
						cobrancaAcaoAtividadeCronograma
								.setCobrancaAtividade(cobrancaAtividade);
						cobrancaAcaoAtividadeCronograma
								.setCobrancaAcaoCronograma(cobrancaAcaoCronogramaNovo);

						if (!dataPrevista.trim().equals("")
								|| cobrancaAtividade
										.getIndicadorObrigatoriedade()
										.equals(
												CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO)) {
							verificaDataPreenchida += 1;
							// ----seta os valores no objeto
							// CobrancaAcaoAtividadeCronograma
							cobrancaAcaoAtividadeCronograma
									.setDataPrevista(Util
											.converteStringParaDate(dataPrevista));
							if (idAcaoCobranca != null
									&& idAcaoCobranca.trim().equals("1")) {
								cobrancaAcaoAtividadeCronograma
										.setComando(Util
												.converteStringParaDateHora(dataPrevista
														+ " "
														+ Util
																.formatarHoraSemData(new Date())));
							} else {
								cobrancaAcaoAtividadeCronograma
										.setComando(null);
							}
						} else {
							cobrancaAcaoAtividadeCronograma
									.setDataPrevista(null);
							cobrancaAcaoAtividadeCronograma.setComando(null);
						}
						cobrancasAtividadesParaInsercao
								.add(cobrancaAcaoAtividadeCronograma);
					}
					/**
					 * Caso o usuario informe a data prevista somente para
					 * algumas atividades da acao, exibir a mensagem "É
					 * necessário informar a data prevista para todas as
					 * atividades da ação."
					 */
					if ((verificaDataPreenchida > 0)
							&& (verificaDataPreenchida < atividadesCobrancaObrigatoriedadeAtivo
									.size())) {
						throw new ActionServletException(
								"atencao.cobranca.data_prevista_algumas_atividades");
					}

					cobrancaCronogramaHelper
							.setCobrancasAtividadesParaInsercao(cobrancasAtividadesParaInsercao);

					/**
					 * 
					 */
					colecaoCobrancaHelperNaBase.add(cobrancaCronogramaHelper);

				}

				sessao.removeAttribute("colecaoCobrancaCronogramaHelper");

				// Organizar a coleção
				Collections.sort((List) colecaoCobrancaHelperNaBase,
						new Comparator() {
							public int compare(Object a, Object b) {
								Short posicao1 = ((CobrancaCronogramaHelper) a)
										.getCobrancaAcaoCronograma()
										.getCobrancaAcao().getOrdemRealizacao();
								Short posicao2 = ((CobrancaCronogramaHelper) b)
										.getCobrancaAcaoCronograma()
										.getCobrancaAcao().getOrdemRealizacao();

								return posicao1.compareTo(posicao2);
							}
						});

				sessao.setAttribute("colecaoCobrancaCronogramaHelper",
						colecaoCobrancaHelperNaBase);
			}
		}

		if (httpServletRequest.getParameter("adicionar") != null) {
			sessao.removeAttribute("cobrancaAcaoEscolhida");
		}
		sessao.setAttribute("colecaoCobrancaAcaoNovo", colecaoAcaoCobrancaNovo);
		return retorno;
	}

}
