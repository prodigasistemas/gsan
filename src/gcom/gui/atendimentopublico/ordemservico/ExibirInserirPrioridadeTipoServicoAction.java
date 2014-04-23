package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 10/08/2006
 */
public class ExibirInserirPrioridadeTipoServicoAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclusão de um tipo de serviço de referência.
	 * 
	 * [UC0436] Inserir Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 10/08/2006
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

		ActionForward retorno = actionMapping
				.findForward("inserirPrioridadeTipoServico");

		// HttpSession sessao = httpServletRequest.getSession(false);

		// InserirPrioridadeTipoServicoActionForm
		// inserirPrioridadeTipoServicoActionForm =
		// (InserirPrioridadeTipoServicoActionForm) actionForm;

		return retorno;
	}

}
