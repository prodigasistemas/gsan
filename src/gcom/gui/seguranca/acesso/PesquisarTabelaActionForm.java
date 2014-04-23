package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Pesquisar Tabela
 * 
 * @author Vinicius Medeiros
 * @date 12/05/2008
 */
public class PesquisarTabelaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String descricao;
	private String nomeTabela;
	private String tipoPesquisa;


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeTabela() {
		return nomeTabela;
	}

	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}
