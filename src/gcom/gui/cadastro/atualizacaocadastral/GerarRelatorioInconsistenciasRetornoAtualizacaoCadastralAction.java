package gcom.gui.cadastro.atualizacaocadastral;

import gcom.gui.cadastro.CarregarDadosAtualizacaoCadastralActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioInconsistenciasRetorno;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioInconsistenciasRetornoBO;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioInconsistenciasRetornoAtualizacaoCadastralAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		CarregarDadosAtualizacaoCadastralActionForm form = (CarregarDadosAtualizacaoCadastralActionForm) actionForm;

		try {
			RelatorioInconsistenciasRetornoBO relatorioInconsistenciasRetornoBO = new RelatorioInconsistenciasRetornoBO(
					httpServletRequest, form);

			RelatorioInconsistenciasRetorno relatorioInconsistenciasRetorno = relatorioInconsistenciasRetornoBO.getRelatorioInconsistenciasRetorno();

			httpServletRequest.setAttribute("telaSucessoRelatorio", true);
			
			retorno = processarExibicaoRelatorio(relatorioInconsistenciasRetorno,
					TarefaRelatorio.TIPO_PDF, httpServletRequest,
					httpServletResponse, actionMapping);
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
