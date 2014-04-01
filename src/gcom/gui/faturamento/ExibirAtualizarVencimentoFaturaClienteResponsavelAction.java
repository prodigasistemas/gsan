package gcom.gui.faturamento;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import gcom.gui.GcomAction;

public class ExibirAtualizarVencimentoFaturaClienteResponsavelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirAtualizarVencimentoFaturaClienteResponsavel");
		
		return retorno;
	}
}
