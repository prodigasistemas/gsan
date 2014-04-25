package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 16 de Junho de 2004
 */
public class ExibirPesquisarClienteParaEconomiaAction extends GcomAction {
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Prepara o retorno da Ação
        ActionForward retorno = actionMapping
                .findForward("exibirEconomiaParaPesquisaCliente");

        //Obtém o action form
        //EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

        HttpSession sessao = httpServletRequest.getSession(false);

        //nesse caso o parametro ira pelo request para o
        // exibirPesquisarClienteAction
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaCliente") != null) {
            httpServletRequest.setAttribute("caminhoRetornoTelaPesquisaCliente",
                    httpServletRequest
                            .getParameter("caminhoRetornoTelaPesquisaCliente"));
        }else{
        	sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
        }

        return retorno;

    }
}
