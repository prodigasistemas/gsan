package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * Descrição da classe
 * Classe responsável pela descricão dos beans do relatório
 * 
 * @author Anderson Italo
 * @date 09/10/2009
 */
public class RelatorioComandosAcaoCobrancaCronogramaBean implements RelatorioBean {
	
	private String grupo;
	private String referencia;
	private String acao;
	private String atividade;
	private String dataComando;
	private String dataPrevista;
	private String dataRealizacao;
	private String qtdDocs;
	private String valor;
	private String qtdItens;
	
	public RelatorioComandosAcaoCobrancaCronogramaBean(){}
	
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public String getDataComando() {
		return dataComando;
	}
	public void setDataComando(String dataComando) {
		this.dataComando = dataComando;
	}
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public String getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getQtdDocs() {
		return qtdDocs;
	}
	public void setQtdDocs(String qtdDocs) {
		this.qtdDocs = qtdDocs;
	}
	public String getQtdItens() {
		return qtdItens;
	}
	public void setQtdItens(String qtdItens) {
		this.qtdItens = qtdItens;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
