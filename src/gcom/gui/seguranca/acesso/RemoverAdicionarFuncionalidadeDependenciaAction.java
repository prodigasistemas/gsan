package gcom.gui.seguranca.acesso;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 08/05/2006
 */
public class RemoverAdicionarFuncionalidadeDependenciaAction extends GcomAction {

	/**
	 * @author Rômulo Aurélio
	 * @date 08/05/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping
				.findForward("inserirRemoverFuncionalidadeDependencia");

		// Remocao de Objeto da collection
		if (httpServletRequest.getParameter("timestamp") != null
				&& !httpServletRequest.getParameter("timestamp").equals("")) {

			Collection colecaoFuncionalidadeTela = (Collection) sessao
					.getAttribute("colecaoFuncionalidadeTela");
			Iterator iterator = colecaoFuncionalidadeTela.iterator();
			long timestamp = new Long(httpServletRequest
					.getParameter("timestamp")).longValue();

			while (iterator.hasNext()) {
				Funcionalidade funcionalidade = (Funcionalidade) iterator
						.next();
				if (GcomAction.obterTimestampIdObjeto(funcionalidade) == timestamp) {
					iterator.remove();
				}
			}
			sessao.setAttribute("colecaoFuncionalidadeTela",
					colecaoFuncionalidadeTela);
		}

				return retorno;
	}
}
