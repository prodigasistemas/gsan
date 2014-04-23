package gcom.gui.relatorio;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 29 de Junho de 2004
 */
public class SelecaoRelatorioOLAPAno extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("selecaoOLAP");
		
		
		httpServletRequest.setAttribute("ipServidor", Fachada.getInstancia().pesquisarParametrosDoSistema().getIpServidorModuloGerencial());
		httpServletRequest.setAttribute("tipoRelatorio", httpServletRequest.getParameter("tipoRelatorio"));

		return retorno;
	}
}
