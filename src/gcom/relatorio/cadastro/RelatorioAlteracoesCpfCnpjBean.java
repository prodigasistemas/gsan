package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

public class RelatorioAlteracoesCpfCnpjBean implements RelatorioBean {
	// TIPO USUARIO
	private String nome;
	private String login;
	private String lotacao;

	// TIPO LOCALIDADE
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String localidade;
	
	// TIPO MEIO
	private String meio;

	private Double cpf;
	private Double cnpj;
	private Double total;
	
	public RelatorioAlteracoesCpfCnpjBean() {
		super();
	}

	// Construtor para tipo Usuário
	public RelatorioAlteracoesCpfCnpjBean(String nome, String login, String lotacao,
			String meio, Double cpf, Double cnpj, Double total) {
		super();
		this.nome = nome;
		this.login = login;
		this.lotacao = lotacao;
		this.meio = meio;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.total = total;
	}

	// Construtor para tipo Localidade
	public RelatorioAlteracoesCpfCnpjBean(String gerenciaRegional, String unidadeNegocio,
			String localidade, Double cpf, Double cnpj, Double total) {
		super();
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.localidade = localidade;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.total = total;
	}

	// Construtor para tipo Meio
	public RelatorioAlteracoesCpfCnpjBean(String meio, Double cpf, Double cnpj, Double total) {
		super();
		this.meio = meio;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.total = total;
	}

	public Double getCnpj() {
		return cnpj;
	}

	public void setCnpj(Double cnpj) {
		this.cnpj = cnpj;
	}

	public Double getCpf() {
		return cpf;
	}

	public void setCpf(Double cpf) {
		this.cpf = cpf;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLotacao() {
		return lotacao;
	}

	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	public String getMeio() {
		return meio;
	}

	public void setMeio(String meio) {
		this.meio = meio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	
}
