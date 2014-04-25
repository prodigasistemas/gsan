package gcom.gui.micromedicao;

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
public class ExibirFiltrarExcecoesLeiturasConsumosAction extends GcomAction {

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
				.findForward("exibirFiltrarExcecoesLeiturasConsumosAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("colecaoIdsImovelTotal");
		sessao.removeAttribute("totalRegistros");
		sessao.removeAttribute("numeroPaginasPesquisa");
		sessao.removeAttribute("filtroMedicaoHistoricoSql");
		sessao.removeAttribute("index");
		sessao.removeAttribute("indiceImovel");
		sessao.removeAttribute("imovelMicromedicaoDadosResumo");
		sessao.removeAttribute("imovelMicromedicaoCarregaMedicaoResumo");
		sessao.removeAttribute("medicaoHistoricoAnoMesAtual");
		sessao.removeAttribute("imovelMicromedicaoConsumo");
		sessao.removeAttribute("medicoesHistoricos");
		sessao.removeAttribute("imoveisMicromedicao");
		
		/*
		 * Colocado por Raphael Rossiter em 04/12/2007 - Analista: Claudio Lira
		 * OBJ: Não perder os registros selecionados na paginação
		 */
		sessao.removeAttribute("idsImoveisJaSelecionados");
		
		//joga caminho do foward na sessao(vem do menu) para saber pra q caso d euso retornar
		if(httpServletRequest.getParameter("nomeCaminhoMapping") != null 
				&& !httpServletRequest.getParameter("nomeCaminhoMapping").trim().equalsIgnoreCase("")){
			sessao.setAttribute("nomeCaminhoMapping", httpServletRequest.getParameter("nomeCaminhoMapping"));
		}
		
		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"filtrarExcecoesLeiturasConsumosWizardAction",
				"filtrarExcecoesLeiturasConsumosAction",
				"cancelarFiltrarExcecoesLeiturasConsumos","",
				"exibirFiltrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos&menu=sim&objetoConsulta=1");

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "LocalizacaoA.gif", "LocalizacaoD.gif",
						"exibirFiltrarExcecoesLeiturasConsumosLocalidade",
						"validarExcecoesLeiturasConsumosLocalizacao"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "CaracteristicaA.gif", "CaracteristicaD.gif",
						"exibirFiltrarExcecoesLeiturasConsumosCaracteristicas",
						"validarExcecoesLeiturasConsumosCaracteristica"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "LigacoesConsumosA.gif", "LigacoesConsumosD.gif",
						"exibirFiltrarExcecoesLeiturasConsumosAnormalidade",
						"validarExcecoesLeiturasConsumosAnormalidade"));
		
		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);

		return retorno;
	}
}
