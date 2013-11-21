package gcom.gui.cadastro.localidade;

import org.apache.struts.validator.ValidatorActionForm;

public class PesquisarGerenciaRegionalActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String nome;
	
	private String nomeAbreviado;
	
	private String tipoPesquisa;

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
	

	
	

}
