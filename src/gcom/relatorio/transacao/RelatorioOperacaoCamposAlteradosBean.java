package gcom.relatorio.transacao;

import gcom.relatorio.RelatorioBean;

public class RelatorioOperacaoCamposAlteradosBean implements RelatorioBean {

	private String conteudoAnterior;
	private String conteudoAtual;
	private String dataHora;
	private String coluna;

	public RelatorioOperacaoCamposAlteradosBean (String conteudoAnterior, String conteudoAtual, String dataHora, String coluna) {
		this.conteudoAnterior = conteudoAnterior;
		this.conteudoAtual = conteudoAtual;
		this.dataHora = dataHora;
		this.coluna = coluna;
	}

	public String getColuna() {
		return coluna;
	}
	public void setColuna(String coluna) {
		this.coluna = coluna;
	}
	public String getConteudoAnterior() {
		return conteudoAnterior;
	}
	public void setConteudoAnterior(String conteudoAnterior) {
		this.conteudoAnterior = conteudoAnterior;
	}
	public String getConteudoAtual() {
		return conteudoAtual;
	}
	public void setConteudoAtual(String conteudoAtual) {
		this.conteudoAtual = conteudoAtual;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	
	

}
