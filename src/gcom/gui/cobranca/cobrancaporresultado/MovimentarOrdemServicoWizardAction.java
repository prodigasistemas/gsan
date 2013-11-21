package gcom.gui.cobranca.cobrancaporresultado;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MovimentarOrdemServicoWizardAction extends WizardAction {

   
    public ActionForward exibirMovimentarOrdemServicoEmitirOSAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirMovimentarOrdemServicoEmitirOSAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    
    
    public ActionForward movimentarOrdemServicoEmitirOSAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new MovimentarOrdemServicoEmitirOSAction().execute(actionMapping, actionForm,
        													httpServletRequest,
        													httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                						httpServletRequest, 
                						httpServletResponse);
    }


    
    public ActionForward exibirMovimentarOrdemServicoGerarOSAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	return new ExibirMovimentarOrdemServicoGerarOSAction().execute(actionMapping,
                												  actionForm, 
                												  httpServletRequest, 
                												  httpServletResponse);
    }


    public ActionForward movimentarOrdemServicoGerarOSAction(
            ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new MovimentarOrdemServicoGerarOSAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, 
        								 actionForm,
        								 httpServletRequest, 
        								 httpServletResponse);
    }


    
    public ActionForward exibirMovimentarOrdemServicoEncerrarOSAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	return new ExibirMovimentarOrdemServicoEncerrarOSAction().execute(actionMapping,
                												  actionForm, 
                												  httpServletRequest, 
                												  httpServletResponse);
    }


    public ActionForward movimentarOrdemServicoEncerrarOSAction(
            ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new MovimentarOrdemServicoEncerrarOSAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, 
        								 actionForm,
        								 httpServletRequest, 
        								 httpServletResponse);
    }

    
    public ActionForward movimentarOrdemServicoAction(ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new MovimentarOrdemServicoAction().execute(actionMapping, 
        										actionForm,
        										httpServletRequest, 
        										httpServletResponse);
    }

}
