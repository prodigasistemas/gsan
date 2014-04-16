package gcom.gui.cadastro.imovel;

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
public class ExibirFiltrarImovelCurvaAbcDebitos extends GcomAction {

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
				.findForward("exibirFiltrarImovelCurvaAbcDebitos");
		
		// obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		sessao.removeAttribute("colecaoGerenciasRegionais");
		sessao.removeAttribute("collectionsNomeConta");
		sessao.removeAttribute("collectionFiltroMedicaoTipo");
		sessao.removeAttribute("collectionsLigacaoAguaSituacao");
		sessao.removeAttribute("collectionLigacaoEsgotoSituacao");
		sessao.removeAttribute("collectionImovelPerfil");
		sessao.removeAttribute("collectionImovelCategoria");
		sessao.removeAttribute("collectionImovelSubcategoria");
		sessao.removeAttribute("collectionAreaConstuidaFaixa");
		sessao.removeAttribute("collectionTipoPoco");
		sessao.removeAttribute("collectionFaturamentoSituacaoTipo");
		sessao.removeAttribute("collectionCobrancaSituacaoTipo");
		sessao.removeAttribute("collectionCobrancaSituacao");
		sessao.removeAttribute("collectionEloAnormalidade");
		sessao.removeAttribute("collectionCadastroOcorrencia");
		sessao.removeAttribute("collectionConsumoTarifa");
		sessao.removeAttribute("colecaoImoveisExcluidosTarifaSocial");
		
		// Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard(
        		"filtrarImovelCurvaAbcDebitosWizardAction",
				"filtrarImovelCurvaAbcDebitos",
				"cancelarImovelOutrosCriteriosAction",
				"",
                "",
                "exibirFiltrarImovelCurvaAbcDebitos.do?menu=sim&limpar=S",
                "");		
		
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "ParametrosPrimeiraAbaA.gif", "ParametrosPrimeiraAbaD.gif",
						"exibirFiltrarImovelCurvaAbcDebitosParametros",
						"validarImovelCurvaAbcDebitos"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "LocalizacaoA.gif", "LocalizacaoD.gif",
						"exibirFiltrarImovelCurvaAbcDebitosLocalizacao",
						"validarImovelCurvaAbcDebitos"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "LigacoesConsumosA.gif", "LigacoesConsumosD.gif",
						"exibirFiltrarImovelCurvaAbcDebitosLigacaoAguaEsgoto",
						"validarImovelCurvaAbcDebitos"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						4, "CaracteristicaA.gif", "CaracteristicaD.gif",
						"exibirFiltrarImovelCurvaAbcDebitosCaracteristicas",
						"validarImovelCurvaAbcDebitos")); 

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						5, "DebitosA.gif", "DebitosD.gif",
						"exibirFiltrarImovelCurvaAbcDebitosDebito",
						"validarImovelCurvaAbcDebitos"));
		
		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);

		return retorno;
	}
}
