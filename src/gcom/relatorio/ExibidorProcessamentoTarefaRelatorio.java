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

public class ExibidorProcessamentoTarefaRelatorio extends GcomAction {

	public ActionForward processarExibicaoRelatorio(TarefaRelatorio dados, int tipo, HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {
		ActionForward retorno = null;
		HttpSession sessao = request.getSession(false);
		String valorConfirmacao = request.getParameter("confirmado");

		if (valorConfirmacao == null || valorConfirmacao.equals("")) {
			int quantidadeRegistroGerado = dados.calcularTotalRegistrosRelatorio();
			String nomeClasseRelatorio = dados.getClass().getSimpleName();

			int quantidadeMaximaOnLineRelatorio = ConstantesExecucaoRelatorios.get(nomeClasseRelatorio);

			// se a quantidade a ser processada for maior que a permitida
			if (quantidadeMaximaOnLineRelatorio == ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA || quantidadeRegistroGerado > quantidadeMaximaOnLineRelatorio) {
				request.setAttribute("caminhoActionConclusao", request.getContextPath().toString() + request.getServletPath().toString());
				request.setAttribute("tipoRelatorio", "" + tipo);

				return montarPaginaConfirmacao("atencao.numero.registro.excedeu.limite.online", request, mapping);

			} else if (request.getAttribute("telaSucessoRelatorio") != null && quantidadeRegistroGerado <= quantidadeMaximaOnLineRelatorio) {
				sessao.setAttribute("tipoRelatorio", "" + tipo);
				reportarErros(request, "erro.sistema");
				retorno = mapping.findForward("telaErroPopup");

				try {
					RelatorioProcessado relatorioProcessado = GerenciadorExecucaoTarefaRelatorio.analisarExecucao(dados, tipo);
					sessao.setAttribute("relatorioProcessado", relatorioProcessado);
				} catch (ControladorException ex) {
					ActionServletException exception = new ActionServletException(ex.getMessage());
					exception.setUrlBotaoVoltar("telaPrincipal.do");
					throw exception;
				}

				request.setAttribute("telaSucessoRelatorio", true);
				montarPaginaSucesso(request, "Relatório Gerado com Sucesso.", "", "", "", "");
				retorno = mapping.findForward("telaSucesso");
			} else {
				retorno = processarRelatorio(dados, tipo, request, response, mapping, retorno);
			}

		} else {
			retorno = processarRelatorio(dados, tipo, request, response, mapping, retorno);
		}

		return retorno;

	}

	protected ActionForward processarRelatorio(TarefaRelatorio tarefa, int tipo, HttpServletRequest request, HttpServletResponse response, ActionMapping mapping, ActionForward retorno) {
		try {
			RelatorioProcessado relatorioProcessado = GerenciadorExecucaoTarefaRelatorio.analisarExecucao(tarefa, tipo);

			if (relatorioProcessado == null) {
				retorno = mapping.findForward("telaApresentacaoBatch");
			} else {
				OutputStream out = null;
				String mimeType = null;
				
				switch (tipo) {
				case TarefaRelatorio.TIPO_PDF:
					response.addHeader("Content-Disposition", "attachment; filename=relatorio.pdf");
					mimeType = "application/pdf";
					
					break;

				case TarefaRelatorio.TIPO_RTF:
					response.addHeader("Content-Disposition", "attachment; filename=relatorio.rtf");
					mimeType = "application/rtf";
					
					break;
				case TarefaRelatorio.TIPO_XLS:
					response.addHeader("Content-Disposition", "attachment; filename=relatorio.xls");
					mimeType = "application/vnd.ms-excel";
					
					break;
				case TarefaRelatorio.TIPO_HTML:
					response.addHeader("Content-Disposition", "attachment; filename=relatorio.zip");
					mimeType = "application/zip";

					break;
				}

				response.setContentType(mimeType);
				out = response.getOutputStream();
				out.write(relatorioProcessado.getDados());
				out.flush();
				out.close();
			}
		} catch (ControladorException ex) {
			ActionServletException exception = new ActionServletException(ex.getMessage());
			exception.setUrlBotaoVoltar("telaPrincipal.do");
			throw exception;

		} catch (IOException ex) {
			reportarErros(request, "erro.sistema");
			retorno = mapping.findForward("telaErroPopup");
		} catch (SistemaException ex) {
			reportarErros(request, "erro.sistema");
			retorno = mapping.findForward("telaErroPopup");
		} catch (RelatorioVazioException ex1) {
			ActionServletException exception = new ActionServletException(ex1.getMessage());
			throw exception;
		}

		return retorno;
	}

	public ActionForward processarExibicaoRelatorio(TarefaRelatorio dados, String tipo, HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {
		int tipoInt = Integer.parseInt(tipo);

		switch (tipoInt) {
		case TarefaRelatorio.TIPO_HTML:
			break;
		case TarefaRelatorio.TIPO_RTF:
			break;
		case TarefaRelatorio.TIPO_XLS:
			break;
		case TarefaRelatorio.TIPO_PDF:
			break;
		default:
			tipoInt = TarefaRelatorio.TIPO_PDF;
			break;
		}

		return processarExibicaoRelatorio(dados, tipoInt, request, response, mapping);
	}
}
