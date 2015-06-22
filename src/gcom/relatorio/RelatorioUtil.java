package gcom.relatorio;

import java.io.IOException;
import java.util.List;

import br.com.prodigasistemas.gsan.relatorio.FormatoRelatorio;
import br.com.prodigasistemas.gsan.relatorio.ReportDTO;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;
import br.com.prodigasistemas.gsan.relatorio.ReportUtil;

public class RelatorioUtil {

	private ReportUtil util = new ReportUtil();
	private ReportDTO relatorio;

	@SuppressWarnings("rawtypes")
	public RelatorioUtil(String titulo, String nome, Class classe, FormatoRelatorio formato) {
		this.relatorio = new ReportDTO(titulo, nome, classe);
		this.relatorio.setFormato(formato);
	}

	public void gerarRelatorio(List<ReportItemDTO> linhas) {
		try {
			relatorio.addLinhas(linhas);

			util.invokeReport(relatorio);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
