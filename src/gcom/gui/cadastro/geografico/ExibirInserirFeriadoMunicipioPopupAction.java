package gcom.gui.cadastro.geografico;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0001] INSERIR FERIADO MUNICIPIO
 * 
 * @author Kassia Albuquerque
 * @created 20/12/2006
 */


public class ExibirInserirFeriadoMunicipioPopupAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirFeriadoMunicipioPopup");
		
		
			
			return retorno;
	}
}
