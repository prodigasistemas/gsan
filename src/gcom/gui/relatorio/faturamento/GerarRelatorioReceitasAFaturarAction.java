package gcom.gui.relatorio.faturamento;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioReceitasAFaturar;
import gcom.relatorio.faturamento.RelatorioReceitasAFaturarBO;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioReceitasAFaturarAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		try {
			RelatorioReceitasAFaturarBO relatorioBO = new RelatorioReceitasAFaturarBO(actionForm, httpServletRequest);

			RelatorioReceitasAFaturar relatorio = relatorioBO.getRelatorioReceitasAFaturar();

			httpServletRequest.setAttribute("telaSucessoRelatorio",true);
			
			retorno = processarExibicaoRelatorio(relatorio, TarefaRelatorio.TIPO_PDF,
					httpServletRequest, httpServletResponse, actionMapping);
		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");
		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		
		return retorno;
	}
}
