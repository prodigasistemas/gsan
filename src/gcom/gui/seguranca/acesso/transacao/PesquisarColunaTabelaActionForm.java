package gcom.gui.seguranca.acesso.transacao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author gcom
 *
 */
public class PesquisarColunaTabelaActionForm  extends ValidatorActionForm{
	private static final long serialVersionUID = 1L;
	private String codigo;
	
	private String nome;
	
	private String idTabela;
	
	private String nomeTabela;
	
	private String tipoPesquisa;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getIdTabela() {
		return idTabela;
	}

	public void setIdTabela(String idTabela) {
		this.idTabela = idTabela;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
