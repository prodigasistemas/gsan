package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MovimentarOSSeletivaInspecaoAnormalidadeWizardAction extends WizardAction {

   
    public ActionForward exibirMovimentarOSSeletivaInspecaoAnormalidadeEmitirOSAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirMovimentarOSSeletivaInspecaoAnormalidadeEmitirOSAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    
    
    public ActionForward movimentarOSSeletivaInspecaoAnormalidadeEmitirOSAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new MovimentarOSSeletivaInspecaoAnormalidadeEmitirOSAction().execute(actionMapping, actionForm,
        													httpServletRequest,
        													httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                						httpServletRequest, 
                						httpServletResponse);
    }


    
    public ActionForward exibirMovimentarOSSeletivaInspecaoAnormalidadeGerarOSAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	return new ExibirMovimentarOSSeletivaInspecaoAnormalidadeGerarOSAction().execute(actionMapping,
                												  actionForm, 
                												  httpServletRequest, 
                												  httpServletResponse);
    }


    public ActionForward movimentarOSSeletivaInspecaoAnormalidadeGerarOSAction(
            ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new MovimentarOSSeletivaInspecaoAnormalidadeGerarOSAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, 
        								 actionForm,
        								 httpServletRequest, 
        								 httpServletResponse);
    }


    
    public ActionForward exibirMovimentarOSSeletivaInspecaoAnormalidadeEncerrarOSAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	return new ExibirMovimentarOSSeletivaInspecaoAnormalidadeEncerrarOSAction().execute(actionMapping,
                												  actionForm, 
                												  httpServletRequest, 
                												  httpServletResponse);
    }


    public ActionForward movimentarOSSeletivaInspecaoAnormalidadeEncerrarOSAction(
            ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new MovimentarOSSeletivaInspecaoAnormalidadeEncerrarOSAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, 
        								 actionForm,
        								 httpServletRequest, 
        								 httpServletResponse);
    }

    
    public ActionForward movimentarOSSeletivaInspecaoAnormalidadeAction(ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new MovimentarOSSeletivaInspecaoAnormalidadeAction().execute(actionMapping, 
        										actionForm,
        										httpServletRequest, 
        										httpServletResponse);
    }

}
