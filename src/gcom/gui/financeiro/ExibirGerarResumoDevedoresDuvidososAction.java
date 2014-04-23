package gcom.gui.financeiro;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Gerar resumo dos devedores duvidosos.
 *
 * @author Pedro Alexandre
 * @date 08/06/2007
 */
public class ExibirGerarResumoDevedoresDuvidososAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//seta o retorno para a página de gerar resumo dos devedores duvidosos
		ActionForward retorno = actionMapping.findForward("exibirGerarResumoDevedoresDuvidosos");

		return retorno;
	}
}
