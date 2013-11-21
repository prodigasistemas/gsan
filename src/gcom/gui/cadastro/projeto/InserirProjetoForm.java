package gcom.gui.cadastro.projeto;

import org.apache.struts.action.ActionForm;


public class InserirProjetoForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String nome;
	
	private String nomeAbreviado;

	private String idOrgaoFinanciador;
	
	private String nomeOrgaoFinanciador;
	
	private String dataInicio;
	
	private String dataFim;
	
	private String valorFinanciamento;
	
	private String observacao;

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

	public String getIdOrgaoFinanciador() {
		return idOrgaoFinanciador;
	}

	public void setIdOrgaoFinanciador(String idOrgaoFinanciador) {
		this.idOrgaoFinanciador = idOrgaoFinanciador;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getValorFinanciamento() {
		return valorFinanciamento;
	}

	public void setValorFinanciamento(String valorFinanciamento) {
		this.valorFinanciamento = valorFinanciamento;
	}
	
	
	
}
