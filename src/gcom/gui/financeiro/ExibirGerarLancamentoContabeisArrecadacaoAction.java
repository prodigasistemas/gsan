package gcom.gui.financeiro;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Rafael Santos
 * @since 21/12/2005
 * 
 */
public class ExibirGerarLancamentoContabeisArrecadacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarLancamentosContabeisArrecadacao");

		//HttpSession sessao = httpServletRequest.getSession(false);

//		Fachada fachada = Fachada.getInstancia();

		return retorno;
	}
}
