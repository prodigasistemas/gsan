package gcom.faturamento.bean;

public class PagadorDTO {

	private Short tipoInscricao;
    private String numeroInscricao;
    private String nome;
    private String endereco;
    private Integer cep;
    private String cidade;
    private String bairro;
    private String uf;
    
    public PagadorDTO() {
		super();
	}
    
	public PagadorDTO(Short tipoInscricao, String numeroInscricao, String nome, String endereco, Integer cep,
			String cidade, String bairro, String uf) {
		super();
       this.tipoInscricao = tipoInscricao;
       this.numeroInscricao = numeroInscricao;
       this.nome = nome;
       this.endereco = endereco;
       this.cep = cep;
       this.cidade = cidade;
       this.bairro = bairro;
       this.uf = uf;
	}
    
    public Short getTipoInscricao() {
		return tipoInscricao;
	}

	public void setTipoInscricao(Short tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}

	public String getNumeroInscricao() {
		return numeroInscricao;
	}

	public void setNumeroInscricao(String numeroInscricao) {
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