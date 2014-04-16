package gcom.gui.faturamento;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0596] Inserir Qualidade de Agua
 * 
 * @author Kássia Albuquerque, Rômulo Aurélio
 * @date 24/07/2007 , 16/09/2008
 */
public class InserirQualidadeAguaAnaliseAction extends GcomAction{

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
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		// Verifica qual é o próximo passo para a execução do processo
		// String destinoPagina = httpServletRequest.getParameter("destino");

		return retorno;

	}

}
