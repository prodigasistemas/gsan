package gcom.micromedicao.bean;

public class ImovelFaltandoSituacaoLeituraHelper {
	
	private String matricula;	
	private String medido;
	private String endereco;

	public ImovelFaltandoSituacaoLeituraHelper(String matricula, String endereco, String medido) {
		super();

		this.matricula = matricula;
		this.endereco = endereco;
		this.medido = medido;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMedido() {
		return medido;
	}

	public void setMedido(String medido) {
		this.medido = medido;
	}

}
