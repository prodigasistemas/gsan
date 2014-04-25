package gcom.gui.arrecadacao.banco;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rhawi Dantas
 */
public class ExibirInserirAvisoBancarioWizardAction extends WizardAction {

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descsrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    
    public ActionForward validarProcessoUmInserirAvisoBancarioAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        new ValidarProcessoUmInserirAvisoBancarioAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    public ActionForward exibirProcessoUmInserirAvisoBancarioAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	return new ExibirProcessoUmInserirAvisoBancarioAction().execute(actionMapping,
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
    
    public ActionForward validarProcessoDoisInserirAvisoBancarioAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        new ValidarProcessoDoisInserirAvisoBancarioAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    public ActionForward exibirProcessoDoisInserirAvisoBancarioAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	return new ExibirProcessoDoisInserirAvisoBancarioAction().execute(actionMapping,
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
    
    public ActionForward validarProcessoTresInserirAvisoBancarioAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        new ValidarProcessoTresInserirAvisoBancarioAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    public ActionForward exibirProcessoTresInserirAvisoBancarioAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	return new ExibirProcessoTresInserirAvisoBancarioAction().execute(actionMapping,
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
    public ActionForward inserirAvisoBancarioAction(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new InserirAvisoBancarioAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }


}
