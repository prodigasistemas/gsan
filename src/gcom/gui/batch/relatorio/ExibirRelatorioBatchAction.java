package gcom.gui.batch.relatorio;

import gcom.batch.RelatorioGerado;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.relatorio.FiltroRelatorioGerado;
import gcom.relatorio.RelatorioVazioException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.IoUtil;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe respsável por montar a apresentação dos relatórios armazenados em
 * batch
 * 
 * 
 * @author Rodrigo Silveira
 * @date 26/10/2006
 */
public class ExibirRelatorioBatchAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		int idFuncionalidadeIniciada = converterStringToInt(httpServletRequest
				.getParameter("idFuncionalidadeIniciada"));

		FiltroRelatorioGerado filtroRelatorioGerado = new FiltroRelatorioGerado();
		filtroRelatorioGerado.adicionarParametro(new ParametroSimples(
				FiltroRelatorioGerado.FUNCIONALIDADE_INICIADA_ID,
				idFuncionalidadeIniciada));
		filtroRelatorioGerado
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeIniciada");

		RelatorioGerado relatorioGerado = (RelatorioGerado) Util
				.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(
						filtroRelatorioGerado, RelatorioGerado.class.getName()));

		OutputStream out = null;
		try {

			TarefaRelatorio tarefaRelatorio = (TarefaRelatorio) IoUtil
					.transformarBytesParaObjeto(relatorioGerado
							.getFuncionalidadeIniciada().getTarefaBatch());

			// httpServletResponse.addHeader("Content-Disposition","attachment;
			// filename=relatorio");

			String mimeType = null;
			switch ((Integer) tarefaRelatorio
					.getParametro("tipoFormatoRelatorio")) {
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
			out.write(relatorioGerado.getArquivoRelatorio());
			out.flush();
			out.close();
		} catch (IOException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
		} catch (ClassNotFoundException ex) {
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
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

}
