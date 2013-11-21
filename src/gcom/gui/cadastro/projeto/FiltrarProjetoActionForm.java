package gcom.gui.cadastro.projeto;

import org.apache.struts.action.ActionForm;

public class FiltrarProjetoActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String nome;
	
	private String nomeAbreviado;

	private String idOrgaoFinanciador;
	
	private String nomeOrgaoFinanciador;
	
	
	private String tipoPesquisa;
	
	private String atualizar;
	
	private String situacao;

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	

	

}
