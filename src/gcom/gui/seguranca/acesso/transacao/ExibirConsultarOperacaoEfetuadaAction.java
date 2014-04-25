package gcom.gui.seguranca.acesso.transacao;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarOperacaoEfetuadaAction   extends GcomAction {
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
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("consultaOperacaoEfetuada");

        //ExibirConsultarOperacaoEfetuadaActionForm form = (ExibirConsultarOperacaoEfetuadaActionForm) actionForm;  

        //HttpSession sessao = httpServletRequest.getSession(false);
        
        //Collection coll = (Collection) sessao.getAttribute("usuarioAlteracao");

        return retorno;
    }

}
