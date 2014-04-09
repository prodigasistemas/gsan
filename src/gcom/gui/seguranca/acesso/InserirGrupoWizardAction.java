package gcom.gui.seguranca.acesso;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirGrupoWizardAction extends WizardAction {

   
    public ActionForward exibirInserirGrupoDadosGeraisAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirInserirGrupoDadosGeraisAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    
    
    public ActionForward inserirGrupoDadosGeraisAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new InserirGrupoDadosGeraisAction().execute(actionMapping, actionForm,
        													httpServletRequest,
        													httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                						httpServletRequest, 
                						httpServletResponse);
    }


    
    public ActionForward exibirInserirGrupoAcessosGrupoAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	return new ExibirInserirGrupoAcessosGrupoAction().execute(actionMapping,
                												  actionForm, 
                												  httpServletRequest, 
                												  httpServletResponse);
    }


    public ActionForward inserirGrupoAcessosGrupoAction(
            ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new InserirGrupoAcessosGrupoAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, 
        								 actionForm,
        								 httpServletRequest, 
        								 httpServletResponse);
    }

    
    public ActionForward inserirGrupoAction(ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new InserirGrupoAction().execute(actionMapping, 
        										actionForm,
        										httpServletRequest, 
        										httpServletResponse);
    }

}
