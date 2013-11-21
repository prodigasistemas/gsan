package gcom.gui.arrecadacao;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CancelarInformarAcertoDocumentosNaoAceitosAction extends GcomAction {

   
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * <Breve descrição sobre o subfluxo>
     *
     * <Identificador e nome do subfluxo>	
     *
     * <Breve descrição sobre o fluxo secundário>
     *
     * <Identificador e nome do fluxo secundário> 
     *
     * @author Pedro Alexandre
     * @date 16/02/2006
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

    	//Seta o mapeamento de retorno para a tela principal
        ActionForward retorno = actionMapping.findForward("telaPrincipal");

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        sessao.removeAttribute("colecaoFormaArrecadacao");

        //Retorna o mapeamento contido na variável retorno
        return retorno;
    }

}
