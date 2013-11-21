package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarConsumoParametroAction extends GcomAction {

	/**
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

		List colecaoConsumoMinimoParametro = null;
		List colecaoConsumoMinimoParametroBase = null;

		Integer qtd;

		if (sessao.getAttribute("colecaoConsumoMinimoParametro") != null) {

			colecaoConsumoMinimoParametro = (List) sessao
					.getAttribute("colecaoConsumoMinimoParametro");
			
			colecaoConsumoMinimoParametroBase = (List) sessao
					.getAttribute("colecaoConsumoMinimoParametroBase");

			qtd = fachada.informarConsumoMinimoParametro(colecaoConsumoMinimoParametro, colecaoConsumoMinimoParametroBase, usuarioLogado);

			montarPaginaSucesso(httpServletRequest, qtd
					+ " Consumo(s) por Parâmetro informado(s) com sucesso.",
					"Informar outro Consumo por Parâmetros",
					"exibirInformarConsumoParametroAction.do?menu=sim");

			return retorno;

		} else {
			throw new ActionServletException(
					"atencao.informe_consumo_minimo_parametro");

		}

	}

}
