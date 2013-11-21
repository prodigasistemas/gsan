package gcom.gui.micromedicao;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarMotivoFinalizacaoPopupAction extends GcomAction {
	/**
	 * 
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInformarMotivoFinalizacaoPopupAction");		
		
		ConsultarArquivoTextoLeituraActionForm consultarArquivoTextoLeituraActionForm 
			= (ConsultarArquivoTextoLeituraActionForm) actionForm;
		
		String finalizar = httpServletRequest.getParameter("finalizar");
		
		if ( finalizar != null ){
			consultarArquivoTextoLeituraActionForm.setFinalizar( finalizar );
		}
		
//		consultarArquivoTextoLeituraActionForm.setMotivoFinalizacao("");
		
		return retorno;
	}
}	
