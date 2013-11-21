package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Exibe o Filtrar Solicitação Acesso Situação >>
 * 
 * @author: Wallace Thierre
 * @Data: 05/11/2010
 * 
 */
public class ExibirFiltrarSolicitacaoAcessoSituacaoAction extends GcomAction {

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
				.findForward("filtrarSolicitacaoAcessoSituacao");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarSolicitacaoAcessoSituacaoActionForm form = (FiltrarSolicitacaoAcessoSituacaoActionForm) actionForm;

		// Quando for acessado pela primeira vez
		String primeiraVez = httpServletRequest.getParameter("menu");

		if (primeiraVez != null && !primeiraVez.trim().equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
					.toString());
		}

		if (form.getIndicadorAtualizar() == null) {
			form.setIndicadorAtualizar("1");
		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			form.setId("");
			form.setDescricao("");
			form.setIndicadorUso("");
			form.setCodigoSituacao("");

		}
		return retorno;
	}
}
