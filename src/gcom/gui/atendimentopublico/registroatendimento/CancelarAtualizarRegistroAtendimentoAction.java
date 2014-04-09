package gcom.gui.atendimentopublico.registroatendimento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;

/**
 * Esta classe tem por finalidade cancelar todas as atividades referentes ao
 * processo de atualização de um R.A voltando para o menu principal do sistema
 * 
 * @author Sávio Luiz
 * @date 10/08/2006
 */
public class CancelarAtualizarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaPrincipal");

		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.removeAttribute("statusWizard");

		return retorno;
	}

}
