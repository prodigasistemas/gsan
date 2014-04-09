package gcom.gui.cadastro.cliente;

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
 * @author Rodrigo
 */
public class CancelarAtualizarClienteAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("telaPrincipal");

        //obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //limpa a sessão
        sessao.removeAttribute("colecaoClienteFone");
        sessao.removeAttribute("colecaoEnderecos");
        sessao.removeAttribute("foneTipos");
        sessao.removeAttribute("municipios");
        sessao.removeAttribute("colecaoResponsavelSuperiores");
        sessao.removeAttribute("InserirEnderecoActionForm");
        sessao.removeAttribute("ClienteActionForm");
        sessao.removeAttribute("tipoPesquisaRetorno");
        sessao.removeAttribute("statusWizard");

        return retorno;
    }
}
