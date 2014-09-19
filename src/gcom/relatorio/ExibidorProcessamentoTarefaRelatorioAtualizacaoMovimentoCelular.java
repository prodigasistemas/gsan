package gcom.relatorio;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.SistemaException;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe responsável por montar a apresentação do Relatorio Processado
 * para Atualizar Faturamento Movimento Celular. 
 * 
 * @author Felipe Santos
 * @date 25/05/2006
 */
public class ExibidorProcessamentoTarefaRelatorioAtualizacaoMovimentoCelular
		extends GcomAction {

	public ActionForward processarExibicaoRelatorio(
			TarefaRelatorio tarefaRelatorio, int tipoRelatorio,
			boolean indicadorSucessoAtualizacao, String mensagem,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, ActionMapping actionMapping) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		String valorConfirmacao = httpServletRequest.getParameter("confirmado");

		if (valorConfirmacao == null || valorConfirmacao.equals("")) {
			int quantidadeRegistroGerado = tarefaRelatorio
					.calcularTotalRegistrosRelatorio();

			String nomeClasseRelatorio = tarefaRelatorio.getClass()
					.getSimpleName();

			int quantidadeMaximaOnLineRelatorio = ConstantesExecucaoRelatorios
					.get(nomeClasseRelatorio);

			// se a quantidade a ser processada for maior que a permitida
			if (quantidadeMaximaOnLineRelatorio == ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA
					|| quantidadeRegistroGerado > quantidadeMaximaOnLineRelatorio) {

				httpServletRequest.setAttribute("caminhoActionConclusao",
						httpServletRequest.getContextPath().toString()
								+ httpServletRequest.getServletPath()
										.toString());

				httpServletRequest.setAttribute("tipoRelatorio", ""
						+ tipoRelatorio);

				// Fazer lógica de controle
				return montarPaginaConfirmacao(
						"atencao.numero.registro.excedeu.limite.online",
						httpServletRequest, actionMapping);

			} else if (httpServletRequest.getAttribute("telaSucessoRelatorio") != null
					&& quantidadeRegistroGerado <= quantidadeMaximaOnLineRelatorio) {

				sessao.setAttribute("tipoRelatorio", "" + tipoRelatorio);

				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "erro.sistema");

				// seta o mapeamento de retorno para a tela de erro de popup
				retorno = actionMapping.findForward("telaErroPopup");

				RelatorioProcessado relatorioProcessado = null;

				try {
					relatorioProcessado = GerenciadorExecucaoTarefaRelatorio
							.analisarExecucao(tarefaRelatorio, tipoRelatorio);
				} catch (ControladorException ex) {
					ActionServletException exception = new ActionServletException(
							ex.getMessage());
					exception.setUrlBotaoVoltar("telaPrincipal.do");
					throw exception;
				}

				sessao.setAttribute("relatorioProcessado", relatorioProcessado);

				httpServletRequest.setAttribute("telaSucessoRelatorio", true);
				
				montarPaginaSucesso(httpServletRequest, mensagem, "Voltar", "/exibirAtualizarFaturamentoMovimentoCelularAction.do");
				
				/*
				 *
				 * 
				 * Verificação da flag de atualização para montar tela ao
				 * atualizar Faturamento Movimento Celular. Caso seja verdadeiro,
				 * mostra tela de Sucesso com geração do relatório. Caso contrário,
				 * mostra rela de Atenção com geração de relatório.
				 */
				if (indicadorSucessoAtualizacao) {	
					 retorno = actionMapping.findForward("telaSucesso");
				 } else {					 
					 retorno = actionMapping.findForward("telaAtencaoRelatorioAtualizarFaturamentoMovimentoCelular");
				 }
			} else {
				retorno = processarRelatorio(tarefaRelatorio, tipoRelatorio,
						httpServletRequest, httpServletResponse, actionMapping,
						retorno);
			}

		} else {
			retorno = processarRelatorio(tarefaRelatorio, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping,
					retorno);
		}

		return retorno;

	}

	protected ActionForward processarRelatorio(TarefaRelatorio tarefaRelatorio,
			int tipoRelatorio, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			ActionMapping actionMapping, ActionForward retorno) {

		try {
			RelatorioProcessado relatorioProcessado = GerenciadorExecucaoTarefaRelatorio
					.analisarExecucao(tarefaRelatorio, tipoRelatorio);

			if (relatorioProcessado == null) {

				retorno = actionMapping.findForward("telaApresentacaoBatch");

			} else {
				OutputStream out = null;

				// httpServletResponse.addHeader("Content-Disposition","attachment;
				// filename=relatorio");

				String mimeType = null;
				switch (tipoRelatorio) {
				case TarefaRelatorio.TIPO_PDF:
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename=relatorio.pdf");
					mimeType = "application/pdf";
					break;

				case TarefaRelatorio.TIPO_RTF:
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename=relatorio.rtf");

					mimeType = "application/rtf";
					break;
				case TarefaRelatorio.TIPO_XLS:
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename=relatorio.xls");

					mimeType = "application/vnd.ms-excel";
					break;
				case TarefaRelatorio.TIPO_HTML:
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename=relatorio.zip");

					mimeType = "application/zip";
					break;
				}

				httpServletResponse.setContentType(mimeType);
				out = httpServletResponse.getOutputStream();
				// out.write((byte[])
				// Util.retonarObjetoDeColecao(relatorioRetorno.values()));
				out.write(relatorioProcessado.getDados());
				out.flush();
				out.close();

			}

		} catch (ControladorException ex) {
			ActionServletException exception = new ActionServletException(ex
					.getMessage());
			exception.setUrlBotaoVoltar("telaPrincipal.do");
			throw exception;

		} catch (IOException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

	public ActionForward processarExibicaoRelatorio(
			TarefaRelatorio tarefaRelatorio, String tipoRelatorio,
			boolean indicadorSucessoAtualizacao, String mensagem,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, ActionMapping actionMapping) {

		int tipoIntRelatorio = TarefaRelatorio.TIPO_PDF;

		try {
			tipoIntRelatorio = Integer.parseInt(tipoRelatorio);

			switch (tipoIntRelatorio) {
			case TarefaRelatorio.TIPO_HTML:
				break;
			case TarefaRelatorio.TIPO_RTF:
				break;
			case TarefaRelatorio.TIPO_XLS:
				break;
			case TarefaRelatorio.TIPO_PDF:
				break;
			default:
				tipoIntRelatorio = TarefaRelatorio.TIPO_PDF;
				break;
			}

		} catch (Exception e) {
		}

		return processarExibicaoRelatorio(tarefaRelatorio, tipoIntRelatorio,
				indicadorSucessoAtualizacao, mensagem,
				httpServletRequest, httpServletResponse, actionMapping);
	}
}
