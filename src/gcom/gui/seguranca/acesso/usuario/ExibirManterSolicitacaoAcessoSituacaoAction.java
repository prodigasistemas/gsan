package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Manter Solicitacao Acesso Situacao *
 * 
 * @author Wallace Thierre Date: 08/11/2010
 * 
 */
public class ExibirManterSolicitacaoAcessoSituacaoAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterSolicitacaoAcessoSituacao");

		Collection colecaoSolicitacaoAcessoSituacao = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = (FiltroSolicitacaoAcessoSituacao) sessao
				.getAttribute("filtroSolicitacaoAcessoSituacao");

		if (filtroSolicitacaoAcessoSituacao != null
				&& !filtroSolicitacaoAcessoSituacao.equals("")) {

			filtroSolicitacaoAcessoSituacao
					.setCampoOrderBy(FiltroSolicitacaoAcessoSituacao.ID);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroSolicitacaoAcessoSituacao,
					SolicitacaoAcessoSituacao.class.getName());
			colecaoSolicitacaoAcessoSituacao = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoSolicitacaoAcessoSituacao != null
				&& !colecaoSolicitacaoAcessoSituacao.isEmpty()) {

			if (colecaoSolicitacaoAcessoSituacao.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {

				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarSolicitacaoAcessoSituacao");

					SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = (SolicitacaoAcessoSituacao) colecaoSolicitacaoAcessoSituacao
							.iterator().next();

					sessao.setAttribute("solicitacaoAcessoSituacao",
							solicitacaoAcessoSituacao);

				} else {

					httpServletRequest.setAttribute(
							"colecaoSolicitacaoAcessoSituacao",
							colecaoSolicitacaoAcessoSituacao);
				}

			} else {
				httpServletRequest.setAttribute(
						"colecaoSolicitacaoAcessoSituacao",
						colecaoSolicitacaoAcessoSituacao);
			}
		} else {
			// Caso não haja nenhum resultado da pesquisa
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

		}

		return retorno;
	}

}
