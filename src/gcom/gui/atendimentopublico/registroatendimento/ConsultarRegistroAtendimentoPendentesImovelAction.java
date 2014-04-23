package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Thiago Tenório
 */
public class ConsultarRegistroAtendimentoPendentesImovelAction extends GcomAction {

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

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		//Fachada fachada = Fachada.getInstancia();

		//HttpSession sessao = httpServletRequest.getSession(false);

		//ConsultarRegistroAtendimentoPendentesImovelActionForm consultarRegistroAtendimentoPendentesImovelActionForm = (ConsultarRegistroAtendimentoPendentesImovelActionForm) actionForm;


		//String idImovel = (String) sessao
		//.getAttribute("idImovel");
		
		
//		fachada.efetuarRetiradaHidrometro(idOrdemServico, numeroLeitura);

		



		return retorno;
	}
}
