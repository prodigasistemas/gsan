package gcom.relatorio.arrecadacao.resumocreditosavisosbancarios;

import gcom.fachada.Fachada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.prodigasistemas.gsan.relatorio.FormatoRelatorio;
import br.com.prodigasistemas.gsan.relatorio.ReportDTO;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;
import br.com.prodigasistemas.gsan.relatorio.ReportUtil;

public class ResumoCreditosAvisosBancariosBO {
	private ReportUtil util = new ReportUtil();

	private ReportDTO relatorio = new ReportDTO("Resumo de Creditos Avisos Bancarios", ResumoCreditosAvisosBancariosDTO.class);

	private ArrayList<ReportItemDTO> itens;

	public void gerarRelatorioPDF(Date data) {
		relatorio.setFormato(FormatoRelatorio.PDF);

		itens = new ArrayList<ReportItemDTO>();

		List<ResumoCreditosAvisosBancariosDTO> resumos = Fachada.getInstancia().pesquisarResumoCreditosAvisosBancarios(data);
		for (ResumoCreditosAvisosBancariosDTO resumo : resumos) {
			itens.add(resumo);
		}

		relatorio.addLinhas(itens);

		try {
			util.invokeReport(relatorio);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
