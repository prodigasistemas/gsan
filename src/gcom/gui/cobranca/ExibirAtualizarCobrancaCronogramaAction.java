package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CobrancaGrupoCronogramaMes;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroCobrancaAcaoCronograma;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupoCronogramaMes;
import gcom.cobranca.bean.CobrancaCronogramaHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarCobrancaCronogramaAction extends GcomAction {
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
				.findForward("atualizarCobrancaCronograma");

		CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String voltaFiltro = httpServletRequest.getParameter("filtro");
		if (voltaFiltro != null && !voltaFiltro.trim().equals("")) {
			sessao.setAttribute("voltaFiltro", true);
		}

		if (httpServletRequest.getParameter("reloadPage") == null) {

			String idCobrancaGrupoCronogramaMensal = "";

			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idCobrancaGrupoCronogramaMensal = httpServletRequest
						.getParameter("idRegistroAtualizacao");

				sessao.setAttribute("idCobrancaGrupoCronogramaMensal",
						idCobrancaGrupoCronogramaMensal);
			} else {
				if (sessao.getAttribute("idRegistroAtualizacao") != null) {
					idCobrancaGrupoCronogramaMensal = (String) sessao
							.getAttribute("idRegistroAtualizacao");
				} else {
					idCobrancaGrupoCronogramaMensal = (String) sessao
							.getAttribute("idCobrancaGrupoCronogramaMensal");
				}
			}

			cobrancaActionForm
					.setIdCobrancaGrupoCronogramaMes(idCobrancaGrupoCronogramaMensal);

			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);

			Collection colecaoCobrancaGrupo = fachada.pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			sessao.setAttribute("colecaoCobrancaGrupo", colecaoCobrancaGrupo);

			/**
			 * Para montar o cronograma de exibicao é necessário montar uma
			 * coleção de CobrancaCronogramaHelper. Para montar cada objeto
			 * helper é preciso montar um objeto CobrancaGrupoAcaoCronogramaMes,
			 * um objeto CobrancaAcaoCronograma e uma colecao de
			 * CobrancaAcaoAtividadeCronograma.
			 * 
			 * O código abaixo serve para montar tais objetos apartir do id de
			 * CobrancaGrupoCronogramaMes.
			 */
			FiltroCobrancaGrupoCronogramaMes filtroCobrancaGrupoCronogramaMes = new FiltroCobrancaGrupoCronogramaMes();

			filtroCobrancaGrupoCronogramaMes
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
			filtroCobrancaGrupoCronogramaMes
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaGrupoCronogramaMes.ID,
							idCobrancaGrupoCronogramaMensal));

			Collection colecaoCobrancaGrupoCronogramaMes = fachada.pesquisar(
					filtroCobrancaGrupoCronogramaMes,
					CobrancaGrupoCronogramaMes.class.getName());
			Collection colecaoCobrancaCronogramaHelper = new ArrayList();
			Collection colecaoCobrancaCronogramaHelperErroAtualizacao = new ArrayList();

			CobrancaCronogramaHelper cobrancaCronogramaHelper = null;
			CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes = null;
			CobrancaAcaoCronograma cobrancaAcaoCronograma = null;
			// CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma =
			// null;

			if (!colecaoCobrancaGrupoCronogramaMes.isEmpty()) {
				Iterator iteratorCobrancagrupoCronogramaMes = colecaoCobrancaGrupoCronogramaMes
						.iterator();

				FiltroCobrancaAcaoCronograma filtroCobrancaAcaoCronograma = null;
				Collection colecaoCobrancaAcaoCronograma = null;

				while (iteratorCobrancagrupoCronogramaMes.hasNext()) {
					cobrancaGrupoCronogramaMes = (CobrancaGrupoCronogramaMes) iteratorCobrancagrupoCronogramaMes
							.next();
					sessao.setAttribute("cobrancaGrupoCronogramaMes",
							cobrancaGrupoCronogramaMes);

					filtroCobrancaAcaoCronograma = new FiltroCobrancaAcaoCronograma();
					filtroCobrancaAcaoCronograma
							.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao.cobrancaAcaoPredecessora");
					filtroCobrancaAcaoCronograma
							.adicionarParametro(new ParametroSimples(
									FiltroCobrancaAcaoCronograma.ID_COBRANCA_GRUPO_CRONOGRAMA_MES,
									idCobrancaGrupoCronogramaMensal));
					filtroCobrancaAcaoCronograma
							.setCampoOrderBy(FiltroCobrancaAcaoCronograma.COBRANCA_ACAO_ORDEM_REALIZACAO);

					colecaoCobrancaAcaoCronograma = fachada.pesquisar(
							filtroCobrancaAcaoCronograma,
							CobrancaAcaoCronograma.class.getName());

					if (!colecaoCobrancaAcaoCronograma.isEmpty()) {
						Iterator iteratorCobrancaAcaoCronograma = colecaoCobrancaAcaoCronograma
								.iterator();

						FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = null;
						List colecaoCobrancaAcaoAtividadeCronograma = null;

						while (iteratorCobrancaAcaoCronograma.hasNext()) {
							cobrancaCronogramaHelper = new CobrancaCronogramaHelper();
							cobrancaAcaoCronograma = (CobrancaAcaoCronograma) iteratorCobrancaAcaoCronograma
									.next();

							filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
							filtroCobrancaAcaoAtividadeCronograma
									.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividade");
							filtroCobrancaAcaoAtividadeCronograma
									.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoCronograma.cobrancaAcao.cobrancaAcaoPredecessora");
							filtroCobrancaAcaoAtividadeCronograma
									.adicionarParametro(new ParametroSimples(
											FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ACAO_CRONOGRAMA,
											cobrancaAcaoCronograma.getId()));
							filtroCobrancaAcaoAtividadeCronograma
									.adicionarParametro(new ParametroSimples(
											FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE_INDICADOR_CRONOGRAMA,
											CobrancaAtividade.ATIVO_CRONOGRAMA));
							filtroCobrancaAcaoAtividadeCronograma
									.setCampoOrderBy(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE_ORDEM_REALIZACAO);

							colecaoCobrancaAcaoAtividadeCronograma = (List) fachada
									.pesquisar(
											filtroCobrancaAcaoAtividadeCronograma,
											CobrancaAcaoAtividadeCronograma.class
													.getName());

							
							if (sessao
									.getAttribute("atividadesCobrancaObrigatoriedadeAtivo") == null) {
								FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
								filtroCobrancaAtividade
										.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividadePredecessora");
								filtroCobrancaAtividade
										.adicionarParametro(new ParametroSimples(
												FiltroCobrancaAtividade.INDICADOR_USO,
												ConstantesSistema.INDICADOR_USO_ATIVO));
								filtroCobrancaAtividade
										.adicionarParametro(new ParametroSimples(
												FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA,
												CobrancaAtividade.ATIVO_CRONOGRAMA));
								filtroCobrancaAtividade
										.adicionarParametro(new ParametroSimples(
												FiltroCobrancaAtividade.INDICADOR_OBRIGATORIEDADE,
												CobrancaAtividade.INDICADOR_OBRIGATORIEDADE_ATIVO));
								filtroCobrancaAtividade
										.setCampoOrderBy(FiltroCobrancaAtividade.ORDEM_REALIZACAO);
								Collection atividadesCobrancaObrigatoriedadeAtivo = fachada
										.pesquisar(filtroCobrancaAtividade,
												CobrancaAtividade.class
														.getName());
								sessao
										.setAttribute(
												"atividadesCobrancaObrigatoriedadeAtivo",
												atividadesCobrancaObrigatoriedadeAtivo);
							}

							// ********************************************************************************
							// ************** Colocar as atividades que ainda
							// nao foram cadastradas no inerir
							// ************** Flávio Cordeiro 14/03/2007
							// ********************************************************************************

							if (!colecaoCobrancaAcaoAtividadeCronograma.isEmpty()) {
								FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
								filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(
									FiltroCobrancaAtividade.INDICADOR_CRONOGRAMA, CobrancaAtividade.ATIVO_CRONOGRAMA));
								Collection colecaoAtividadesBanco = fachada.pesquisar(filtroCobrancaAtividade,
												CobrancaAtividade.class.getName());

								if (colecaoCobrancaAcaoAtividadeCronograma.size() != colecaoAtividadesBanco.size()) {
									Iterator iterator = colecaoAtividadesBanco.iterator();
									while (iterator.hasNext()) {
										Iterator iteratorCobrancaAcaoAtividade = colecaoCobrancaAcaoAtividadeCronograma.iterator();
										CobrancaAtividade cobrancaAtividade = (CobrancaAtividade) iterator.next();
										boolean existe = false;
										while (iteratorCobrancaAcaoAtividade.hasNext()) {
											CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronogramaBase = (CobrancaAcaoAtividadeCronograma) iteratorCobrancaAcaoAtividade.next();
											if (cobrancaAtividade.getId().equals(
													cobrancaAcaoAtividadeCronogramaBase.getCobrancaAtividade().getId())) {
												existe = true;
												break;
											}
										}
										if (!existe) {
											CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = new CobrancaAcaoAtividadeCronograma();
											cobrancaAcaoAtividadeCronograma.setCobrancaAcaoCronograma(cobrancaAcaoCronograma);
											cobrancaAcaoAtividadeCronograma.setCobrancaAtividade(cobrancaAtividade);
																					
//											colecaoCobrancaAcaoAtividadeCronograma
//												.add((cobrancaAtividade.getId() - 1), cobrancaAcaoAtividadeCronograma);
											
											colecaoCobrancaAcaoAtividadeCronograma
											.add(colecaoCobrancaAcaoAtividadeCronograma.size(),
											cobrancaAcaoAtividadeCronograma);
											
										}
									}
									
									//ordena a colecao para exibicao na tela.
									Collections.sort((List) colecaoCobrancaAcaoAtividadeCronograma, new Comparator() {
										public int compare(Object a, Object b) {
										Short codigo1 = ((CobrancaAcaoAtividadeCronograma) a).getCobrancaAtividade().getOrdemRealizacao();
	
										Short codigo2 = ((CobrancaAcaoAtividadeCronograma) b).getCobrancaAtividade().getOrdemRealizacao();
	
										if (codigo1 == null || codigo1.equals("")) {
											return -1;
										} else {
											return codigo1.compareTo(codigo2);
										}
										
										}
									});

								}

							}

							// ********************************************************************************

							if (!colecaoCobrancaAcaoAtividadeCronograma
									.isEmpty()) {
								CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronogramaComandar = (CobrancaAcaoAtividadeCronograma) colecaoCobrancaAcaoAtividadeCronograma
										.iterator().next();
								if (cobrancaAcaoAtividadeCronogramaComandar
										.getComando() != null) {
									cobrancaCronogramaHelper.setComandar("S");
								} else {
									cobrancaCronogramaHelper.setComandar("N");
								}
								cobrancaCronogramaHelper
										.setCobrancasAtividadesParaInsercao(colecaoCobrancaAcaoAtividadeCronograma);
							}

							cobrancaCronogramaHelper
									.setCobrancaAcaoCronograma(cobrancaAcaoCronograma);
							cobrancaCronogramaHelper
									.setCobrancaGrupoCronogramaMes(cobrancaGrupoCronogramaMes);
							colecaoCobrancaCronogramaHelper
									.add(cobrancaCronogramaHelper);
						}
					}

				}
			}

			cobrancaActionForm
					.setMesAno(cobrancaGrupoCronogramaMes.getMesAno());
			cobrancaActionForm
					.setDescricaoGrupoCobranca(cobrancaGrupoCronogramaMes
							.getCobrancaGrupo().getDescricao());
			cobrancaActionForm.setIdGrupoCobranca(cobrancaGrupoCronogramaMes
					.getCobrancaGrupo().getId().toString());

			sessao.setAttribute("colecaoCobrancaCronogramaHelper",
					colecaoCobrancaCronogramaHelper);

			// criacao da colecao para insercao no caso de erro de atualizacao
			if (!colecaoCobrancaGrupoCronogramaMes.isEmpty()) {
				Iterator iteratorCobrancagrupoCronogramaMes = colecaoCobrancaGrupoCronogramaMes
						.iterator();

				FiltroCobrancaAcaoCronograma filtroCobrancaAcaoCronograma = null;
				Collection colecaoCobrancaAcaoCronograma = null;

				while (iteratorCobrancagrupoCronogramaMes.hasNext()) {
					cobrancaGrupoCronogramaMes = (CobrancaGrupoCronogramaMes) iteratorCobrancagrupoCronogramaMes
							.next();
					sessao.setAttribute("cobrancaGrupoCronogramaMes",
							cobrancaGrupoCronogramaMes);

					filtroCobrancaAcaoCronograma = new FiltroCobrancaAcaoCronograma();
					filtroCobrancaAcaoCronograma
							.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao.cobrancaAcaoPredecessora");
					filtroCobrancaAcaoCronograma
							.adicionarParametro(new ParametroSimples(
									FiltroCobrancaAcaoCronograma.ID_COBRANCA_GRUPO_CRONOGRAMA_MES,
									idCobrancaGrupoCronogramaMensal));
					filtroCobrancaAcaoCronograma
							.setCampoOrderBy(FiltroCobrancaAcaoCronograma.COBRANCA_ACAO_ORDEM_REALIZACAO);

					colecaoCobrancaAcaoCronograma = fachada.pesquisar(
							filtroCobrancaAcaoCronograma,
							CobrancaAcaoCronograma.class.getName());

					if (!colecaoCobrancaAcaoCronograma.isEmpty()) {
						Iterator iteratorCobrancaAcaoCronograma = colecaoCobrancaAcaoCronograma
								.iterator();

						FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = null;
						Collection colecaoCobrancaAcaoAtividadeCronogramaErro = null;

						while (iteratorCobrancaAcaoCronograma.hasNext()) {
							cobrancaCronogramaHelper = new CobrancaCronogramaHelper();
							cobrancaAcaoCronograma = (CobrancaAcaoCronograma) iteratorCobrancaAcaoCronograma
									.next();

							filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
							filtroCobrancaAcaoAtividadeCronograma
									.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividade");
							filtroCobrancaAcaoAtividadeCronograma
									.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoCronograma.cobrancaAcao.cobrancaAcaoPredecessora");
							filtroCobrancaAcaoAtividadeCronograma
									.adicionarParametro(new ParametroSimples(
											FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ACAO_CRONOGRAMA,
											cobrancaAcaoCronograma.getId()));
							filtroCobrancaAcaoAtividadeCronograma
									.setCampoOrderBy(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE_ORDEM_REALIZACAO);

							colecaoCobrancaAcaoAtividadeCronogramaErro = fachada
									.pesquisar(
											filtroCobrancaAcaoAtividadeCronograma,
											CobrancaAcaoAtividadeCronograma.class
													.getName());

							if (!colecaoCobrancaAcaoAtividadeCronogramaErro
									.isEmpty()) {
								CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronogramaComandar = (CobrancaAcaoAtividadeCronograma) colecaoCobrancaAcaoAtividadeCronogramaErro
										.iterator().next();
								if (cobrancaAcaoAtividadeCronogramaComandar
										.getComando() != null) {
									cobrancaCronogramaHelper.setComandar("S");
								} else {
									cobrancaCronogramaHelper.setComandar("N");
								}
								cobrancaCronogramaHelper
										.setCobrancasAtividadesParaInsercao(colecaoCobrancaAcaoAtividadeCronogramaErro);
							}

							cobrancaCronogramaHelper
									.setCobrancaAcaoCronograma(cobrancaAcaoCronograma);
							cobrancaCronogramaHelper
									.setCobrancaGrupoCronogramaMes(cobrancaGrupoCronogramaMes);
							colecaoCobrancaCronogramaHelperErroAtualizacao
									.add(cobrancaCronogramaHelper);
						}
					}

				}
			}

			sessao.setAttribute(
					"colecaoCobrancaCronogramaHelperErroAtualizacao",
					colecaoCobrancaCronogramaHelperErroAtualizacao);
		}
		
		verificarExecucaoAutomaticaGrupo(cobrancaActionForm.getIdGrupoCobranca(),httpServletRequest);

		return retorno;

	}

	private void verificarExecucaoAutomaticaGrupo(String grupoCobranca,
			HttpServletRequest httpServletRequest){
    	
    	String retorno = "2";
    	
    	if(grupoCobranca != null){
    		
    		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
            filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, new Integer(grupoCobranca)));

     		Collection gruposCobranca = getFachada().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
     		CobrancaGrupo cobrancaGrupo = (CobrancaGrupo)Util.retonarObjetoDeColecao(gruposCobranca);
     		
     		if(cobrancaGrupo.getIndicadorExecucaoAutomatica().equals(ConstantesSistema.SIM)){
     			retorno = "1";
     		}
    	}
    	
    	httpServletRequest.setAttribute("desabilitaAlteracao",retorno);
		 
    }
	
}
