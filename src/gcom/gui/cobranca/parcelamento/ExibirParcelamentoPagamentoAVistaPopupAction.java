package gcom.gui.cobranca.parcelamento;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição da tela de consultar débito cobrado
 * 
 * @author Fernanda Paiva
 * @created 30 de Março de 2006
 */
public class ExibirParcelamentoPagamentoAVistaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno e seta o mapeamento para a tela de
		// consultar débito cobrado
		ActionForward retorno = actionMapping
				.findForward("exibirParcelamentoPagamentoAVistaPopupAction");

		// retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
