package gcom.gui.portal;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirCanaisAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse esponse) {
		String retorno = "canais-de-atendimento";

		String method = request.getParameter("pagina");
		if (method.equalsIgnoreCase("callcenter")) {
			retorno = "callcenter";
		}

		if (method.equalsIgnoreCase("ouvidoria")) {
			retorno = "ouvidoria";
		}

		return actionMapping.findForward(retorno);
	}
}
