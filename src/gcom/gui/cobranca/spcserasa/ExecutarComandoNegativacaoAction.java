package gcom.gui.cobranca.spcserasa;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de executar o comande de negativacao
 * 
 * @author Thiago Toscano 
 * @created 04/01/2008
 */
public class ExecutarComandoNegativacaoAction extends GcomAction {
	/**
	 * @author Thiago Toscano
	 * @date 04/01/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		//ExecutarComandoNegativacaoActionForm ExecutarComandoNegativacaoActionForm = (ExecutarComandoNegativacaoActionForm) actionForm;

		//HttpSession sessao = httpServletRequest.getSession(false);

		//Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança

		//fachada.executaComandoNegativacao();

		montarPaginaSucesso(httpServletRequest, "Negativações Executadas com Sucesso",
				"","");
		return retorno;
	}
}
 
