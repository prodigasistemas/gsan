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
 * @author Rodrigo
 */
public class ExibirFiltrarImovelOutrosCriterios extends GcomAction {

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
				.findForward("exibirFiltrarImovelOutrosCriterios");
		
		String parametroGerarRelatorio = null;
		if(httpServletRequest.getParameter("gerarRelatorio") != null)
			parametroGerarRelatorio = httpServletRequest.getParameter("gerarRelatorio");		
		
		// GerenciadorPaginas gerenciadorPaginas = null;

		// obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		sessao.setAttribute("gerarRelatorio", httpServletRequest
				.getAttribute("gerarRelatorio"));
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
		
		///if(httpServletRequest.getParameter("limpar") != null && httpServletRequest.getParameter("limpar").trim().equalsIgnoreCase("S")){
			//sessao.removeAttribute("ImovelOutrosCriteriosActionForm");
///		}
		
		//HttpSession session = httpServletRequest.getSession();
		String tipoRelatorio = (String) 
			httpServletRequest.getParameter("gerarRelatorio"); 
		StatusWizard statusWizard = null;
		
		if ( tipoRelatorio!= null && tipoRelatorio.equals("RelatorioImoveis")) {
		
			//Monta o Status do Wizard caso seja o Relatorio de Imoveis
			statusWizard = new StatusWizard(
	        		"filtrarImovelOutrosCriteriosWizardAction",
					"filtrarImovelOutrosCriterios",
					"cancelarImovelOutrosCriteriosAction",
					"",
	                "",
	                "exibirFiltrarImovelOutrosCriteriosImoveis.do?menu=sim&gerarRelatorio="+tipoRelatorio+"&limpar=S",
	                "");		
			
			
		} else if (tipoRelatorio!= null && tipoRelatorio.equals("RelatorioImoveisEndereco")){
			
			// Monta o Status do Wizard
	        statusWizard = new StatusWizard(
	        		"filtrarImovelOutrosCriteriosWizardAction",
					"filtrarImovelOutrosCriterios",
					"cancelarImovelOutrosCriteriosAction",
					"",
	                "",
	                "exibirFiltrarImovelOutrosCriteriosEndereco.do?menu=sim&gerarRelatorio="+tipoRelatorio+"&limpar=S",
	                "");		
		
		} else if (tipoRelatorio!= null && tipoRelatorio.equals("RelatorioCadastroConsumidoresInscricao")){
			
			// Monta o Status do Wizard
	        statusWizard = new StatusWizard(
	        		"filtrarImovelOutrosCriteriosWizardAction",
					"filtrarImovelOutrosCriterios",
					"cancelarImovelOutrosCriteriosAction",
					"",
	                "",
	                "exibirFiltrarImovelOutrosCriteriosConsumidoresInscricao.do?menu=sim&gerarRelatorio="+tipoRelatorio+"&limpar=S",
	                "");		
		
		} else {
			
			// Monta o Status do Wizard
	        statusWizard = new StatusWizard(
	        		"filtrarImovelOutrosCriteriosWizardAction",
					"filtrarImovelOutrosCriterios",
					"cancelarImovelOutrosCriteriosAction",
					"",
	                "",
	                "exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio="+tipoRelatorio+"&limpar=S",
	                "");		
		
		}
        
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "LocalizacaoA.gif", "LocalizacaoD.gif",
						"exibirFiltrarImovelOutrosCriteriosLocalizarImoveis",
						"validarImovelOutrosCriterios"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "ClientesImoveisA.gif", "ClientesImoveisD.gif",
						"exibirClientesImoveisRelacionados",
						"validarClientesImoveisRelacionados"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "LigacoesConsumosA.gif", "LigacoesConsumosD.gif",
						"exibirLigacaoAguaEsgoto",
						"validarLigacaoAguaEsgoto"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						4, "CaracteristicaA.gif", "CaracteristicaD.gif",
						"exibirCaracteristicasImovel",
						"validarCaracteristicasImovel")); 

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						5, "FaturamentoCobrancaA.gif", "FaturamentoCobrancaD.gif",
						"exibirImovelDadosFaturamento",
						"validarImovelDadosFaturamento"));
		
		if (tipoRelatorio != null
			&& (tipoRelatorio.trim().equalsIgnoreCase(
					"consultarTarifaExcluida"))
			||	(tipoRelatorio.equalsIgnoreCase("RelatorioTarifaSocial"))) {
			statusWizard
			.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
					6, "TarifaSocial2A.gif", "TarifaSocial2D.gif",
					"exibirFiltrarDadosTarifaSocialAction",
					"filtrarDadosTarifaSocialAction"));
		}else if (tipoRelatorio != null
				&& (tipoRelatorio.trim().equalsIgnoreCase("GerarRelacaoDebito"))) {
				statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						6,"DebitosA.gif", "DebitosD.gif",
						"exibirFiltrarImovelOutrosCriteriosCobrancaAction",
						"filtrarImovelOutrosCriteriosCobrancaAction"));
		}
		
		

		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);
		if(parametroGerarRelatorio != null)
			sessao.setAttribute("parametroGerarRelatorio", parametroGerarRelatorio);
		
		

		return retorno;
	}
}
