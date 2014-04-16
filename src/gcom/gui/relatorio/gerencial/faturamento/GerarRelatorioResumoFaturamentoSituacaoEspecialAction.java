package gcom.gui.relatorio.gerencial.faturamento;

import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaFinalHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaFinalHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.gerencial.faturamento.RelatorioResumoFaturamentoSituacaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioResumoFaturamentoSituacaoEspecialAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// ResumoAnormalidadeActionForm resumoAnormalidadeActionForm =
		// (ResumoAnormalidadeActionForm) actionForm;

		ResumoFaturamentoSituacaoEspecialConsultaFinalHelper resumoFaturamentoSituacaoEspecialConsultaFinalHelper = (ResumoFaturamentoSituacaoEspecialConsultaFinalHelper) sessao
				.getAttribute("resumoFaturamentoSituacaoEspecialConsultaFinalHelper");
		ResumoCobrancaSituacaoEspecialConsultaFinalHelper resumoCobrancaSituacaoEspecialConsultaFinalHelper = (ResumoCobrancaSituacaoEspecialConsultaFinalHelper) sessao
				.getAttribute("resumoCobrancaSituacaoEspecialConsultaFinalHelper");

		// Inicio da parte que vai mandar os parametros para o relatório

		// Fim da parte que vai mandar os parametros para o relatório

		try {

			RelatorioResumoFaturamentoSituacaoEspecial relatorioResumoFaturamentoSituacaoEspecial = new RelatorioResumoFaturamentoSituacaoEspecial(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorioResumoFaturamentoSituacaoEspecial.addParametro(
					"resumoFaturamentoSituacaoEspecialConsultaFinalHelper",
					resumoFaturamentoSituacaoEspecialConsultaFinalHelper);

			relatorioResumoFaturamentoSituacaoEspecial.addParametro(
					"resumoCobrancaSituacaoEspecialConsultaFinalHelper",
					resumoCobrancaSituacaoEspecialConsultaFinalHelper);

			if (resumoFaturamentoSituacaoEspecialConsultaFinalHelper != null) {
				relatorioResumoFaturamentoSituacaoEspecial.addParametro(
						"nomeRelatorio", "FATURAMENTO");
			} else {
				relatorioResumoFaturamentoSituacaoEspecial.addParametro(
						"nomeRelatorio", "COBRANÇA");
			}
			
			if (resumoFaturamentoSituacaoEspecialConsultaFinalHelper != null) {
				relatorioResumoFaturamentoSituacaoEspecial.addParametro(
						"numeroRelatorio", "R0342");
			} else {
				relatorioResumoFaturamentoSituacaoEspecial.addParametro(
						"numeroRelatorio", "R0347");
			}

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			
			String tipoRelatorio = httpServletRequest
					.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioResumoFaturamentoSituacaoEspecial.addParametro(
					"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			retorno = processarExibicaoRelatorio(
					relatorioResumoFaturamentoSituacaoEspecial, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

//			if (retorno == null) {
//				// prepara a resposta para o popup
//				httpServletResponse.setContentType("application/pdf");
//				out = httpServletResponse.getOutputStream();
//				out.write(relatorioRetorno);
//				out.flush();
//				out.close();
//			}
//		} catch (IOException ex) {
//			// manda o erro para a página no request atual
//			reportarErros(httpServletRequest, "erro.sistema");
//
//			// seta o mapeamento de retorno para a tela de erro de popup
//			retorno = actionMapping.findForward("telaErroPopup");

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
