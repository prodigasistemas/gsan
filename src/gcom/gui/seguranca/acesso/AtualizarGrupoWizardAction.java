package gcom.gui.seguranca.acesso;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define os passos do processo de atualizar grupo
 *
 * @author Pedro Alexandre
 * @date 26/06/2006
 */
public class AtualizarGrupoWizardAction extends WizardAction {

    
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * [UC]
     *
     * @author Pedro Alexandre
     * @date 03/07/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward exibirAtualizarGrupoDadosGeraisAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirAtualizarGrupoDadosGeraisAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    
    
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 03/07/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward atualizarGrupoDadosGeraisAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new AtualizarGrupoDadosGeraisAction().execute(actionMapping, actionForm,
        													httpServletRequest,
        													httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                						httpServletRequest, 
                						httpServletResponse);
    }


   
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 03/07/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward exibirAtualizarGrupoAcessosGrupoAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	return new ExibirAtualizarGrupoAcessosGrupoAction().execute(actionMapping,
                												  actionForm, 
                												  httpServletRequest, 
                												  httpServletResponse);
    }


    
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 03/07/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward atualizarGrupoAcessosGrupoAction(
            ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new AtualizarGrupoAcessosGrupoAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, 
        								 actionForm,
        								 httpServletRequest, 
        								 httpServletResponse);
    }

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 03/07/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward atualizarGrupoAction(ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new AtualizarGrupoAction().execute(actionMapping, 
        										actionForm,
        										httpServletRequest, 
        										httpServletResponse);
    }

}
