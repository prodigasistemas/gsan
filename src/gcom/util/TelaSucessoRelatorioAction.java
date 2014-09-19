package gcom.util;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioProcessado;
import gcom.tarefa.TarefaRelatorio;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 17/03/2009
 */
public class TelaSucessoRelatorioAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		try {

			String tipoRelatorio = (String) sessao
					.getAttribute("tipoRelatorio");

			RelatorioProcessado relatorioProcessado = (RelatorioProcessado) sessao
					.getAttribute("relatorioProcessado");

			OutputStream out = null;

			String mimeType = null;
			switch (Integer.parseInt(tipoRelatorio)) {
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

			out.write(relatorioProcessado.getDados());
			out.flush();
			out.close();

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return retorno;
	}

}
