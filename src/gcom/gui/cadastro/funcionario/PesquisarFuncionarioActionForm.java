package gcom.gui.cadastro.funcionario;

import org.apache.struts.validator.ValidatorActionForm;

public class PesquisarFuncionarioActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String nome;
	
	private String idUnidadeEmpresa;

	private String descricaoUnidadeEmpresa;
	
	private String tipoPesquisa;

	public String getDescricaoUnidadeEmpresa() {
		return descricaoUnidadeEmpresa;
	}

	public void setDescricaoUnidadeEmpresa(String descricaoUnidadeEmpresa) {
		this.descricaoUnidadeEmpresa = descricaoUnidadeEmpresa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdUnidadeEmpresa() {
		return idUnidadeEmpresa;
	}

	public void setIdUnidadeEmpresa(String idUnidadeEmpresa) {
		this.idUnidadeEmpresa = idUnidadeEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
}

