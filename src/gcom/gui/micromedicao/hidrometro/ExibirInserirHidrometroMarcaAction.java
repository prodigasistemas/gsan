package gcom.gui.micromedicao.hidrometro;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0524]	INSERIR MARCA HIDROMETRO
 * 
 * @author Bruno Leonardo
 * @date 18/06/2007
 */
 


public class ExibirInserirHidrometroMarcaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
			ActionForward retorno = actionMapping.findForward("inserirHidrometroMarca");	

			return retorno;
	}
}
