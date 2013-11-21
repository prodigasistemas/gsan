package gcom.relatorio.cadastro.projeto;

import gcom.relatorio.RelatorioBean;

public class RelatorioManterProjetoBean implements RelatorioBean {

	private String id;
	
	private String nome;

	private String nomeAbreviado;

	private String nomeOrgaoFinanciador;

	private String dataInicio;

	private String dataFim;

	private String valorFinanciamento;

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getNomeOrgaoFinanciador() {
		return nomeOrgaoFinanciador;
	}

	public void setNomeOrgaoFinanciador(String nomeOrgaoFinanciador) {
		this.nomeOrgaoFinanciador = nomeOrgaoFinanciador;
	}

	public String getValorFinanciamento() {
		return valorFinanciamento;
	}

	public void setValorFinanciamento(String valorFinanciamento) {
		this.valorFinanciamento = valorFinanciamento;
	}

	public RelatorioManterProjetoBean(String id, String nome, String nomeAbreviado, String nomeOrgaoFinanciador, String dataInicio, String dataFim, String valorFinanciamento) {
		this.id = id;
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.nomeOrgaoFinanciador = nomeOrgaoFinanciador;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.valorFinanciamento = valorFinanciamento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

}
