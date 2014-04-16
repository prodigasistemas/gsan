package gcom.gui.micromedicao.hidrometro;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 18 de junho de 2007
 */
public class ExibirFiltrarCapacidadeHidrometroAction extends GcomAction {
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


		FiltrarCapacidadeHidrometroActionForm filtrarCapacidadeHidrometroActionForm = (FiltrarCapacidadeHidrometroActionForm) actionForm;

		filtrarCapacidadeHidrometroActionForm.setAtualizar("1");
		
		if (httpServletRequest.getParameter("menu") != null) {
			filtrarCapacidadeHidrometroActionForm.setCodigo("");
			filtrarCapacidadeHidrometroActionForm.setIndicadorUso("");
			filtrarCapacidadeHidrometroActionForm.setDescricao("");

		}
			
		filtrarCapacidadeHidrometroActionForm.setIndicadorAtualizar("1");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarCapacidadeHidrometroActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());

		}

		// Se voltou da tela de Atualizar Sistema de Esgoto
		if (sessao.getAttribute("voltar") != null
				&& sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if (sessao.getAttribute("tipoPesquisa") != null) {
				filtrarCapacidadeHidrometroActionForm.setTipoPesquisa(sessao
						.getAttribute("tipoPesquisa").toString());
			}
		}

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarCapacidadeHidrometro");

		return retorno;

	}
}
