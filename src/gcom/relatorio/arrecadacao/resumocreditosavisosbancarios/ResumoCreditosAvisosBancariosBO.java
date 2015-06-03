package gcom.relatorio.arrecadacao.resumocreditosavisosbancarios;

import java.util.ArrayList;

import br.com.prodigasistemas.gsan.relatorio.FormatoRelatorio;
import br.com.prodigasistemas.gsan.relatorio.ReportDTO;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;
import br.com.prodigasistemas.gsan.relatorio.ReportUtil;

public class ResumoCreditosAvisosBancariosBO {
	private ReportUtil util = new ReportUtil();
	
	private ReportDTO relatorio = new ReportDTO("Resumo de Creditos Avisos Bancarios", ResumoCreditosAvisosBancariosDTO.class);
	
	private ArrayList<ReportItemDTO> itens;
	
	public void gerarRelatorioPDF(){
		relatorio.setFormato(FormatoRelatorio.PDF);
		
		//add linhas no relatorio
		
		relatorio.addLinhas(itens);
		
		
	}
}
