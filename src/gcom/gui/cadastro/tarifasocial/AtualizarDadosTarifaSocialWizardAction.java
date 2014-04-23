package gcom.gui.cadastro.tarifasocial;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Thiago
 */
public class AtualizarDadosTarifaSocialWizardAction extends WizardAction {

	
	public ActionForward exibirPopUpDadosTarifaSocial(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {

		return new AtualizarDadosTarifaSocialAction().exibir(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}


	public ActionForward processarPopUpDadosTarifaSocial(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)  throws Exception {
		new AtualizarDadosTarifaSocialAction().processar(actionMapping, actionForm, httpServletRequest, httpServletResponse);

		return this.redirecionadorWizard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ActionForward exibirPopUpCliente(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)  throws Exception  {


		return new AtualizarDadosTarifaSocialClienteAction().exibir(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward processarPopUpCliente(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)  throws Exception  {

		new AtualizarDadosTarifaSocialClienteAction().processar(actionMapping, actionForm, httpServletRequest, httpServletResponse);

		return this.redirecionadorWizard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

}
