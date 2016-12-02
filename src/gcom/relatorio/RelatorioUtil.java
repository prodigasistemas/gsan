package gcom.relatorio;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import gcom.fachada.Fachada;
import gcom.relatorio.cliente.FormatoRelatorio;
import gcom.relatorio.cliente.ReportDTO;
import gcom.relatorio.cliente.ReportItemDTO;
import gcom.relatorio.cliente.ReportUtil;
import gcom.seguranca.SegurancaParametro;
import gcom.util.Util;

public class RelatorioUtil {

	private ReportUtil util = new ReportUtil();
	private ReportDTO relatorio;
 
	@SuppressWarnings("rawtypes")
	public RelatorioUtil(String titulo, String nome, Class classe, FormatoRelatorio formato) {
		this.relatorio = new ReportDTO(titulo, nome, classe);
		this.relatorio.setFormato(formato);
	}

	public File gerarRelatorio(List<ReportItemDTO> linhas) throws MalformedURLException, IOException {
		String url = Fachada.getInstancia().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_GSAN_RELATORIOS.toString());
		
		relatorio.addLinhas(linhas);

		try {
			util.invokeReport(relatorio, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Util.salvarArquivoDeURL(url, relatorio.getName());
	}
	
	public void setOmitirTotalGeral(boolean omitir) {
		relatorio.setOmitirTotalGeral(omitir);
	}
}
