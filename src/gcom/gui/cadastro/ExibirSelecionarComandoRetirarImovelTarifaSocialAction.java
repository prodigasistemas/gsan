package gcom.gui.cadastro;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirSelecionarComandoRetirarImovelTarifaSocialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("selecionarComandoRetirarImovelTarifaSocial");

		SelecionarComandoRetirarImovelTarifaSocialActionForm form = (SelecionarComandoRetirarImovelTarifaSocialActionForm) actionForm;
		
		if(httpServletRequest.getParameter("menu") != null){
			form.setIndicadorTipoCarta("3");
			form.setIndicadorSituacao(null);
		}

		return retorno;
	}

}
