package gcom.gui.arrecadacao;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarAcertoDocumentosNaoAceitosWizardAction extends WizardAction {

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
    public ActionForward exibirInformarAcertoDocumentosNaoAceitosPagamentoAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirInformarAcertoDocumentosNaoAceitosPagamentoAction().execute(actionMapping,
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
    public ActionForward informarAcertoDocumentosNaoAceitosPagamentoAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new InformarAcertoDocumentosNaoAceitosPagamentoAction().execute(actionMapping, actionForm,
        													httpServletRequest,
        													httpServletResponse);
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                						httpServletRequest, 
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
    public ActionForward exibirInformarAcertoDocumentosNaoAceitosDebitosAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirInformarAcertoDocumentosNaoAceitosDebitosAction().execute(actionMapping,
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
    public ActionForward informarAcertoDocumentosNaoAceitosDebitosAction(
            ActionMapping actionMapping, 
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        new InformarAcertoDocumentosNaoAceitosDebitosAction().execute(actionMapping, actionForm,
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
    public ActionForward informarAcertoDocumentosNaoAceitosAction(ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new InformarAcertoDocumentosNaoAceitosAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

}
