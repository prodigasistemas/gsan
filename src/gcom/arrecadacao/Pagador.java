package src.gcom.arrecadacao;

public class Pagador {
	
	private Integer id;
	
	private Short tipoInscricao;
	private Integer numeroInscricao;
	private String nome;
	private String endereco;
	private Integer cep;
	private String cidade;
	private String bairro;
	private String uf;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Short getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(Short tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public Integer getNumeroInscricao() {
		return numeroInscricao;
	}
	public void setNumeroInscricao(Integer numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getCep() {
		return cep;
	}
	public void setCep(Integer cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

}
