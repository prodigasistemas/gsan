package gcom.gui.cadastro.imovel;

import gcom.gui.StatusWizard;
import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rossiter
 */
public class InserirImovelWizardAction extends WizardAction {

    // MÉTODOS DE EXIBIÇÃO
    // =======================================================

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward adicionarInserirImovelClienteAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new AdicionarInserirImovelClienteAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirInserirImovelLocalidadeAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
    	
    	//**********************************************************************
    	// CRC2103
    	// Autor: Ivan Sergio
    	// Data: 13/07/2009
    	// Esta verificacao guarda na sessao o wizard e deve ser feita por conta
    	// do popup de inserir cliente que tambem possui um wizard.
    	//**********************************************************************
    	HttpSession sessao = httpServletRequest.getSession(false);
    	if (sessao.getAttribute("statusWizardAnterior") == null) {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizard");
        	sessao.setAttribute("statusWizardAnterior", statusWizardAnterior);
    	} else {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizardAnterior");
    		sessao.setAttribute("statusWizard", statusWizardAnterior);
    	}
    	//**********************************************************************
    	
        return new ExibirInserirImovelLocalidadeAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirInserirImovelEnderecoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	//**********************************************************************
    	// CRC2103
    	// Autor: Ivan Sergio
    	// Data: 13/07/2009
    	// Esta verificacao guarda na sessao o wizard e deve ser feita por conta
    	// do popup de inserir cliente que tambem possui um wizard.
    	//**********************************************************************
    	HttpSession sessao = httpServletRequest.getSession(false);
    	if (sessao.getAttribute("statusWizardAnterior") == null) {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizard");
        	sessao.setAttribute("statusWizardAnterior", statusWizardAnterior);
    	} else {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizardAnterior");
    		sessao.setAttribute("statusWizard", statusWizardAnterior);
    	}
    	//**********************************************************************

        return new ExibirInserirImovelEnderecoAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    /**
     * Description of the Method
     */
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirInserirImovelPrincipalAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirInserirImovelPrincipalAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirInserirImovelClienteAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	//**********************************************************************
    	// CRC2103
    	// Autor: Ivan Sergio
    	// Data: 13/07/2009
    	// Esta verificacao guarda na sessao o wizard e deve ser feita por conta
    	// do popup de inserir cliente que tambem possui um wizard.
    	//**********************************************************************
    	HttpSession sessao = httpServletRequest.getSession(false);
    	if (sessao.getAttribute("statusWizardAnterior") == null) {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizard");
        	sessao.setAttribute("statusWizardAnterior", statusWizardAnterior);
    	} else {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizardAnterior");
    		sessao.setAttribute("statusWizard", statusWizardAnterior);
    	}
    	//**********************************************************************

        return new ExibirInserirImovelClienteAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirInserirImovelSubCategoriaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirInserirImovelSubCategoriaAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirInserirImovelCaracteristicasAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirInserirImovelCaracteristicasAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirInserirImovelConclusaoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirInserirImovelConclusaoAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    // FIM DOS MÉTODOS DE EXIBIÇÃO =============================================
    //==========================================================================

    // MÉTODOS DE INSERÇÃO =====================================================

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward inserirImovelLocalidadeAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new InserirImovelLocalidadeAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward inserirImovelEnderecoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new InserirImovelEnderecoAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward inserirImovelPrincipalAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new InserirImovelPrincipalAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward inserirImovelClienteAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new InserirImovelClienteAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
        //**********************************************************************
    	// CRC2103
    	// Autor: Ivan Sergio
    	// Data: 13/07/2009
    	// Esta verificacao guarda na sessao o wizard e deve ser feita por conta
    	// do popup de inserir cliente que tambem possui um wizard.
    	//**********************************************************************
    	HttpSession sessao = httpServletRequest.getSession(false);
    	if (sessao.getAttribute("statusWizardAnterior") == null) {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizard");
        	sessao.setAttribute("statusWizardAnterior", statusWizardAnterior);
    	} else {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizardAnterior");
    		sessao.setAttribute("statusWizard", statusWizardAnterior);
    	}
    	//**********************************************************************
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward inserirImovelSubCategoriaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new InserirImovelSubCategoriaAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward inserirImovelCaracteristicasAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new InserirImovelCaracteristicasAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward inserirImovelConclusaoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new InserirImovelConclusaoAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    // FIM DOS MÉTODOS DE EXIBIÇÃO =============================================
    //==========================================================================

    // MÉTODO PARA CONCLUIR O PROCESSO =========================================

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward inserirImovelAction(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new InserirImovelAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

}
