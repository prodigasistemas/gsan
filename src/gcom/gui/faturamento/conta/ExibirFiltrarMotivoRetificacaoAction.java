package gcom.gui.faturamento.conta;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela exibição da pagina de filtrar motivo de retificação
 * [UC1119] Filtrar Motivo Retificação
 * 
 * @author Mariana Victor
 * @date 11/01/2011
 */
public class ExibirFiltrarMotivoRetificacaoAction extends GcomAction {

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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarMotivoRetificacao");
		
		FiltrarMotivoRetificacaoActionForm form = (FiltrarMotivoRetificacaoActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {
			form.setIndicadorAtualizar(ConstantesSistema.SIM.toString());
			sessao.setAttribute("atualizar", "1");
		}
		
		return retorno;
	}

}
