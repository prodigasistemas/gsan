package gcom.gui.portal;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirPortalAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		String retorno = "exibir";
		HttpSession sessao = request.getSession(false);

		if (sair(request, sessao))
			return mapping.findForward(retorno);

		return mapping.findForward(retorno);
	}

	private boolean sair(HttpServletRequest request, HttpSession sessao) {
		String sair = (String) request.getParameter("sair");
		if (sair != null && sair.equals("true")) {
			sessao.removeAttribute("matricula");
			sessao.removeAttribute("cpfCnpj");
			return true;
		} else {
			return false;
		}
	}
}
