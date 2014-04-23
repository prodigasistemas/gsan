package gcom.gui.seguranca.acesso;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Action responsável pelo cancelamento da atualização de um grupo de usuários,
 * e por remover da sessão todos os objetos inseridos.  
 *
 * @author Pedro Alexandre
 * @date 26/06/2006
 */
public class CancelarAtualizarGrupoAction extends GcomAction {

   
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * [UC0279] - Manter Grupo
     *
     * @author Pedro Alexandre
     * @date 26/06/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a tela principal
        ActionForward retorno = actionMapping.findForward("telaPrincipal");

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Limpa a sessão
        sessao.removeAttribute("grupo");
        sessao.removeAttribute("GrupoActionForm");
        sessao.removeAttribute("grupoFuncionalidades");

        //Retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
