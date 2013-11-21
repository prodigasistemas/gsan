package gcom.gui.relatorio.big;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.big.RelatorioBIG;
import gcom.relatorio.big.RelatorioBIGBO;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioBIGAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		try {
			RelatorioBIGBO relatorioBIGBO = new RelatorioBIGBO(
					actionForm, httpServletRequest);

			RelatorioBIG relatorioBIG = relatorioBIGBO.getRelatorioBIG();

			httpServletRequest.setAttribute("telaSucessoRelatorio",true);
			
			retorno = processarExibicaoRelatorio(relatorioBIG, TarefaRelatorio.TIPO_XLS,
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
