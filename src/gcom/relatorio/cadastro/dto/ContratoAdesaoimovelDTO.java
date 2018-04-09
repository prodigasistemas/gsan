package gcom.relatorio.cadastro.dto;

import gcom.relatorio.cliente.ReportElementType;
import gcom.relatorio.cliente.ReportItemDTO;

public class ContratoAdesaoimovelDTO implements ReportItemDTO {

	private static final long serialVersionUID = 8611524049087979246L;

	@ReportElementType()
	private String conteudo;
	
	public ContratoAdesaoimovelDTO() {}
	
	public ContratoAdesaoimovelDTO(String conteudo) {
		super();
		
		this.conteudo = conteudo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
