package gcom.gui.cobranca;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Desfazer Parcelamento Débito
 * 
 * @author Fernanda Karla
 * @since 11/01/2006
 */
public class ExibirDesfazerParcelamentoDebitoAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("telaSucesso");


		String codigo = httpServletRequest.getParameter("codigoParcelamento").trim();
		String motivo = httpServletRequest.getParameter("motivo").trim();
		
		Usuario usuario = this.getUsuarioLogado(httpServletRequest);
		
		this.getFachada().desfazerParcelamentosDebito(motivo, new Integer(codigo),usuario);

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Parcelamento de Débitos desfeito com sucesso.",
			"Realizar outra manutenção de Parcelamento de Débitos",
			"exibirConsultarListaParcelamentoDebitoAction.do?menu=sim");
		
		return retorno;
	}
}
