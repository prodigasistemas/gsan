package gcom.gui.relatorio.faturamento;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.bo.faturamento.RelatorioContasRetidasBO;
import gcom.relatorio.faturamento.RelatorioContasRetidas;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioContasRetidasAction extends ExibidorProcessamentoTarefaRelatorio {

	/**
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
        
		httpServletRequest.getSession(false).removeAttribute("indicadorAtualizar");
        
        processarRetorno(actionMapping, httpServletRequest, httpServletResponse, actionForm, retorno);
        
		montarPaginaSucesso(httpServletRequest, "Gerar Relatório de Contas Retidas", "Voltar", "/exibirRelatorioContasRetidas.do");
		
		return retorno;
	}
	
	private void processarRetorno(ActionMapping actionMapping, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
											ActionForm actionForm, ActionForward retorno) {
		try {
			RelatorioContasRetidasBO relatorioContasRetidasBO = new RelatorioContasRetidasBO(actionForm, httpServletRequest);
			
			RelatorioContasRetidas relatorioContasRetidas = relatorioContasRetidasBO.getRelatorioContasRetidas();

			retorno = processarExibicaoRelatorio(relatorioContasRetidas, (Integer) relatorioContasRetidas.getParametro("tipoFormatoRelatorio"), 
													httpServletRequest, httpServletResponse, actionMapping);

		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");
		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
	}
}
