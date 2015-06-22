package gcom.gui.relatorio.arrecadacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.RelatorioUtil;
import gcom.relatorio.arrecadacao.dto.ResumoCreditosAvisosBancariosDTO;
import gcom.seguranca.SegurancaParametro;
import gcom.util.IoUtil;
import gcom.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.prodigasistemas.gsan.relatorio.FormatoRelatorio;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;

public class GerarRelatorioResumoCreditosAvisosBancariosAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		GerarRelatorioResumoCreditosAvisosBancariosActionForm form = (GerarRelatorioResumoCreditosAvisosBancariosActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String data = form.getDataConsulta().replace("/", "-");
		String nomeArquivo = "resumo_creditos_avisos_bancarios_" + data + ".pdf";
		
		RelatorioUtil relatorioUtil = new RelatorioUtil(
				"Resumo de Créditos dos Avisos Bancários",
				nomeArquivo,
				ResumoCreditosAvisosBancariosDTO.class, 
				FormatoRelatorio.PDF);

		List<ResumoCreditosAvisosBancariosDTO> resumos = fachada.pesquisarResumoCreditosAvisosBancarios(Util.converteStringParaDate(form.getDataConsulta()));
		
		if (resumos == null || resumos.isEmpty()) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}

		List<ReportItemDTO> itens = new ArrayList<ReportItemDTO>();
		itens.addAll(resumos);

		relatorioUtil.gerarRelatorio(itens);

		String url = fachada.getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_GSAN_RELATORIOS.toString());

		downloadRelatorio(response, url, nomeArquivo);

		return null;
	}

	private void downloadRelatorio(HttpServletResponse response, String url, String nomeArquivo) {
		try {
			response.setContentType(FormatoRelatorio.PDF.getContentType());
			response.addHeader("Content-Disposition", "attachment; filename=" + nomeArquivo);

			File relatorio = Util.salvarArquivoDeURL(url + nomeArquivo, nomeArquivo);

			ServletOutputStream sos = response.getOutputStream();
			sos.write(IoUtil.getBytesFromFile(relatorio));
			sos.flush();
			sos.close();

			relatorio.delete();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_relatorio");
		}
	}
}
