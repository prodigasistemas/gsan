package gcom.gui.seguranca.acesso.transacao;

import org.apache.struts.action.ActionForm;

public class FiltrarTabelaColunaActionForm  extends ActionForm {

	private String nomeFuncionalidade = "";
	private String idFuncionalidade  = "";
	private String funcionalidadeNaoEncontrada = "false";
	private static final long serialVersionUID = 1L;
	private String[] operacoes = new String[0];
	
	private String nomeTabela = "";
	private String idTabela = "";
	private String tabelaNaoEncontrada = "false";
	public String getFuncionalidadeNaoEncontrada() {
		return funcionalidadeNaoEncontrada;
	}
	public void setFuncionalidadeNaoEncontrada(String funcionalidadeNaoEncontrada) {
		this.funcionalidadeNaoEncontrada = funcionalidadeNaoEncontrada;
	}
	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}
	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}
	public String getIdTabela() {
		return idTabela;
	}
	public void setIdTabela(String idTabela) {
		this.idTabela = idTabela;
	}
	public String getNomeFuncionalidade() {
		return nomeFuncionalidade;
	}
	public void setNomeFuncionalidade(String nomeFuncionalidade) {
		this.nomeFuncionalidade = nomeFuncionalidade;
	}
	public String getNomeTabela() {
		return nomeTabela;
	}
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}
	public String[] getOperacoes() {
		return operacoes;
	}
	public void setOperacoes(String[] operacoes) {
		this.operacoes = operacoes;
	}
	public String getTabelaNaoEncontrada() {
		return tabelaNaoEncontrada;
	}
	public void setTabelaNaoEncontrada(String tabelaNaoEncontrada) {
		this.tabelaNaoEncontrada = tabelaNaoEncontrada;
	}	

}
