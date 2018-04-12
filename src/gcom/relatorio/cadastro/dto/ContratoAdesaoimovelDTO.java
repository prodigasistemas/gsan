package gcom.relatorio.cadastro.dto;

public class ContratoAdesaoimovelDTO {

	private String conteudo;
	private String nome;
	
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
