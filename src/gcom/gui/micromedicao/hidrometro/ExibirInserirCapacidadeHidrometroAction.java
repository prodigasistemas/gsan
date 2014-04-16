package gcom.gui.micromedicao.hidrometro;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite Inserir uma Agencia Bancaria
 * 
 * @author Thiago Tenório
 * @date 07/02/2007
 */
public class ExibirInserirCapacidadeHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirCapacidadeHidrometro");

		InserirCapacidadeHidrometroActionForm inserirCapacidadeHidrometroActionForm = (InserirCapacidadeHidrometroActionForm) actionForm;

		
		String limparForm = (String) httpServletRequest
				.getParameter("limparCampos");


		if ((limparForm == null || limparForm.trim().equalsIgnoreCase(""))
				|| (httpServletRequest.getParameter("desfazer") != null && httpServletRequest
						.getParameter("desfazer").equalsIgnoreCase("S"))) {
			// -------------- bt DESFAZER ---------------

			// Limpando o formulario
			inserirCapacidadeHidrometroActionForm.setDescricao("");
			inserirCapacidadeHidrometroActionForm.setAbreviatura("");
			inserirCapacidadeHidrometroActionForm.setNumMinimo("");
			inserirCapacidadeHidrometroActionForm.setNumMaximo("");
			inserirCapacidadeHidrometroActionForm.setNumMinimo("");
			inserirCapacidadeHidrometroActionForm.setNumOrdem("");
			inserirCapacidadeHidrometroActionForm.setCodigo("");

		}

		// devolve o mapeamento de retorno
		return retorno;
	}

}
