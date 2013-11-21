/**
 * 
 */
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurelio
 * 
 */
public class InformarConsumoAreaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão ou alteração de um novo consumo pela
	 * área
	 * 
	 * [UC0781] Informar Consumo por Área
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/05/2008
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Collection colecaoConsumoMinimoArea = null;
		Collection colecaoConsumoMinimoAreaBase = null;

		Integer qtd;

		if (sessao.getAttribute("colecaoConsumoMinimoArea") != null) {

			colecaoConsumoMinimoArea = (Collection) sessao
					.getAttribute("colecaoConsumoMinimoArea");
			
			colecaoConsumoMinimoAreaBase = (Collection) sessao
			.getAttribute("colecaoConsumoMinimoAreaBase");

			qtd = fachada.informarConsumoMinimoArea(colecaoConsumoMinimoArea, colecaoConsumoMinimoAreaBase, usuarioLogado);

			montarPaginaSucesso(httpServletRequest, qtd
					+ " Consumo(s) Mínimo(s) informado(s) com sucesso.",
					"Informar outro Consumo Mínimo",
					"exibirInformarConsumoAreaAction.do?menu=sim");

			return retorno;

		} else {
			throw new ActionServletException(
					"atencao.atencao.informe_consumo_minimo");

		}

	}

}
