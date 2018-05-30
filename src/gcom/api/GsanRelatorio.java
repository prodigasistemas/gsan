package gcom.api;

import gcom.api.relatorio.ReportDTO;
import gcom.api.relatorio.ReportFormat;
import gcom.api.relatorio.ReportItemDTO;
import gcom.fachada.Fachada;
import gcom.seguranca.SegurancaParametro;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class GsanRelatorio {

	private ReportDTO dto;

	public GsanRelatorio(String titulo, String nome, Class<?> classe, ReportFormat formato) {
		this.dto = new ReportDTO(titulo, nome, classe);
		this.dto.setFormato(formato);
	}

	public void gerar(List<ReportItemDTO> linhas, HttpServletResponse response) throws MalformedURLException, IOException {
		String url = Fachada.getInstancia().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_GSAN_RELATORIOS.toString());

		dto.addLinhas(linhas);

		GsanApi api = new GsanApi(url);
		try {
			api.invoke(dto);
			api.download(dto.getName(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setOmitirTotalGeral(boolean omitir) {
		dto.setOmitirTotalGeral(omitir);
	}
}