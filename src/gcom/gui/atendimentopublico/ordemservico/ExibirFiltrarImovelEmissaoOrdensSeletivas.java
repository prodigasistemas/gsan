package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Ivan Sérgio
 */
public class ExibirFiltrarImovelEmissaoOrdensSeletivas extends GcomAction {

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
	 *            Description of the Parameter*
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarImovelEmissaoOrdensSeletivas");
		
		// obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("colecaoFirma");
		
		// Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard(
        		"filtrarImovelEmissaoOrdensSeletivasWizardAction",
				"filtrarImovelEmissaoOrdensSeletivas",
				"exibirFiltrarImovelEmissaoOrdensSeletivas",
				"",
                "",
                "exibirFiltrarImovelEmissaoOrdensSeletivas.do?menu=sim&limpar=S",
                "");		
		
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "ParametrosPrimeiraAbaA.gif", "ParametrosPrimeiraAbaD.gif",
						"exibirFiltrarImovelEmissaoOrdensSeletivasParametros",
						"validarImovelEmissaoOrdensSeletivas"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "HidrometroIntervaloAbaA.gif", "HidrometroIntervaloAbaD.gif",
						"exibirFiltrarImovelEmissaoOrdensSeletivasHidrometro",
						"validarImovelEmissaoOrdensSeletivas"));

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "CaracteristicaA.gif", "CaracteristicaD.gif",
						"exibirFiltrarImovelEmissaoOrdensSeletivasCaracteristicas",
						"validarImovelEmissaoOrdensSeletivas"));
		
		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);

		return retorno;
	}
}
