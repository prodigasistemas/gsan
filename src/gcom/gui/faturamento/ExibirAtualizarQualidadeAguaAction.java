package gcom.gui.faturamento;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

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
 * @date 16/09/2008
 */

public class ExibirAtualizarQualidadeAguaAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("atualizarQualidadeAguaActionDadosAction");

		// obtém a instância da sessão
		HttpSession sessao = this.getSessao(httpServletRequest);

		String idRegistroAtualizacao = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			idRegistroAtualizacao = (String) httpServletRequest
					.getParameter("idRegistroAtualizacao");
		} else if (httpServletRequest.getAttribute("idRegistroAtualizacao") != null) {
			idRegistroAtualizacao = (String) httpServletRequest
					.getAttribute("idRegistroAtualizacao");
		}

		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"atualizarQualidadeAguaWizardAction",
				"atualizarQualidadeAguaAction",
				"cancelarAtualizarQualidadeAguaAction",
				"exibirFiltrarQualidadeAguaAction",
				"exibirAtualizarQualidadeAguaAction.do", idRegistroAtualizacao);

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "DadosPrimeiraAbaA.gif", "DadosPrimeiraAbaD.gif",
						"exibirAtualizarQualidadeAguaDadosAction",
						"atualizarQualidadeAguaDadosAction"));

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "AnaliseUltimaAbaA.gif", "AnaliseUltimaAbaD.gif",
						"exibirAtualizarQualidadeAguaAnaliseAction",
						"atualizarQualidadeAguaAnaliseAction"));

		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);

		return retorno;
	}

}
