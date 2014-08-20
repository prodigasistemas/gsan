package gcom.gui.relatorio.cadastro.atualizacaocadastral;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioImoveisSituacaoPeriodo;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioImoveisSituacaoPeriodoBO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioImoveisSituacaoPeriodoAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		processarRetorno(actionMapping, httpServletRequest, httpServletResponse, actionForm, retorno);
	        
		montarPaginaSucesso(httpServletRequest, "Gerar Relat�rio de Im�veis", "Voltar", "/exibirGerarRelatorioImoveisSituacaoPeriodo.do");
		
		return retorno;
	}

	private void processarRetorno(ActionMapping actionMapping, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, ActionForm actionForm, ActionForward retorno) {
		try{
			RelatorioImoveisSituacaoPeriodoBO relatorioBO = new  RelatorioImoveisSituacaoPeriodoBO(actionForm, httpServletRequest);

			RelatorioImoveisSituacaoPeriodo relatorio = relatorioBO.getRelatorioImoveisSituacaoPeriodo();

			processarExibicaoRelatorio(relatorio, (Integer)relatorio.getParametro("tipoFormatoRelatorio"), 
					httpServletRequest, httpServletResponse, actionMapping);

		} catch (RelatorioVazioException ex) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		} 
	}
	
}
