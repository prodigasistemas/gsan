package gcom.gui.relatorio.cadastro.atualizacaocadastral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.big.RelatorioBIG;
import gcom.relatorio.big.RelatorioBIGBO;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioRelacaoImoveisRota;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioRelacaoImoveisRotaBO;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

public class GerarRelatorioRelacaoImoveisRotaAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		try {
			RelatorioRelacaoImoveisRotaBO relatorioRelacaoImoveisRotaBO = new RelatorioRelacaoImoveisRotaBO(actionForm, httpServletRequest);

			RelatorioRelacaoImoveisRota relatorioRelacaoImoveisRota = relatorioRelacaoImoveisRotaBO.getRelatorioRelacaoImoveisRota();

			httpServletRequest.setAttribute("telaSucessoRelatorio",true);
			
			retorno = processarExibicaoRelatorio(relatorioRelacaoImoveisRota, TarefaRelatorio.TIPO_PDF,
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
