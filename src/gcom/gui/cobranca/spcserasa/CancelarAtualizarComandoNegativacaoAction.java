package gcom.gui.cobranca.spcserasa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;

/**
 * Esta classe tem por finalidade cancelar todas as atividades referentes ao processo de atualização de um 
 * Comando Negativação voltando para o menu principal do sistema
 *
 * @author Ana Maria
 * @date 28/01/2008
 */
public class CancelarAtualizarComandoNegativacaoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaPrincipal");

        HttpSession sessao = httpServletRequest.getSession(false);

        sessao.removeAttribute("statusWizard"); 

        return retorno;
    }

}
