package gcom.gui.atendimentopublico;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de melhorias do gcom
 * 
 * @author Sávio Luiz
 * @created 15 de Fevereiro de 2007
 */
public class ExibirInformarMelhoriasGcomAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("informarMelhoriasGcom");

		MelhoriasGcomActionForm melhoriasGcomActionForm = (MelhoriasGcomActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (sessao.getAttribute("indicadorSenhaPendente") != null && sessao.getAttribute("indicadorSenhaPendente").toString().equals("1")){
			melhoriasGcomActionForm.setIndicadorSenhaPendente(sessao.getAttribute("indicadorSenhaPendente").toString());
		}
		
		Usuario usuario = (Usuario)sessao.getAttribute("usuarioLogado");
		
		melhoriasGcomActionForm.setLoginUsuario(usuario.getLogin());
		melhoriasGcomActionForm.setNomeUsuario(usuario.getNomeUsuario());
		
		
		
		return retorno;
	}
}
