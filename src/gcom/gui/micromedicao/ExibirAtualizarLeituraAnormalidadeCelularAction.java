package gcom.gui.micromedicao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;

public class ExibirAtualizarLeituraAnormalidadeCelularAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("AtualizarLeituraAnormalidadeCelularAction");
		return retorno;
	}
}
