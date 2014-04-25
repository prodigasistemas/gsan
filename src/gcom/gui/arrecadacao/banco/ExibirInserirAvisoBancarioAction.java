package gcom.gui.arrecadacao.banco;

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
 * @author Rodrigo
 */
public class ExibirInserirAvisoBancarioAction extends GcomAction {

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
				.findForward("exibirInserirAvisoBancarioAction");

		// GerenciadorPaginas gerenciadorPaginas = null;

		// obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		/**
		 * sessao.setAttribute("gerarRelatorio", httpServletRequest
		 * .getAttribute("gerarRelatorio"));
		 * sessao.removeAttribute("colecaoGerenciasRegionais");
		 * sessao.removeAttribute("collectionsNomeConta");
		 * sessao.removeAttribute("collectionFiltroMedicaoTipo");
		 * sessao.removeAttribute("collectionsLigacaoAguaSituacao");
		 * sessao.removeAttribute("collectionLigacaoEsgotoSituacao");
		 * sessao.removeAttribute("collectionImovelPerfil");
		 * sessao.removeAttribute("collectionImovelCategoria");
		 * sessao.removeAttribute("collectionImovelSubcategoria");
		 * sessao.removeAttribute("collectionAreaConstuidaFaixa");
		 * sessao.removeAttribute("collectionTipoPoco");
		 * sessao.removeAttribute("collectionFaturamentoSituacaoTipo");
		 * sessao.removeAttribute("collectionCobrancaSituacaoTipo");
		 * sessao.removeAttribute("collectionCobrancaSituacao");
		 * sessao.removeAttribute("collectionEloAnormalidade");
		 * sessao.removeAttribute("collectionCadastroOcorrencia");
		 * sessao.removeAttribute("collectionConsumoTarifa");
		 */

		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"exibirInserirAvisoBancarioWizardAction",
				"inserirAvisoBancarioAction",
				"cancelarInserirAvisoBancarioAction",
				"exibirInserirAvisoBancarioAction.do");

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "ArrecadadorA.gif", "ArrecadadorD.gif",
						"exibirProcessoUmInserirAvisoBancarioAction",
						"validarProcessoUmInserirAvisoBancarioAction"));

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "AvisoBancarioA2.gif", "AvisoBancarioD2.gif",
						"exibirProcessoDoisInserirAvisoBancarioAction",
						"validarProcessoDoisInserirAvisoBancarioAction"));

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "ConclusaoA.gif", "ConclusaoD.gif",
						"exibirProcessoTresInserirAvisoBancarioAction",
						"validarProcessoTresInserirAvisoBancarioAction"));

		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);

		return retorno;
	}
}
