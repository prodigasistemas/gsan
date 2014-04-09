package gcom.arrecadacao.aviso.bean;

import java.util.Collection;

public class PagamentosDevolucoesHelper {
	
	private Integer qtdPagamentos;
	private Integer qtdDevolucoes;
	private String valorTotalPagamentos;
	private String valorTotalDevolucoes;
	private Collection colecaoMovimentarPagamentos;
	private Collection colecaoMovimentarDevolucoes;
	/**
	 * @return Retorna o campo colecaoMovimentarDevolucoes.
	 */
	public Collection getColecaoMovimentarDevolucoes() {
		return colecaoMovimentarDevolucoes;
	}
	/**
	 * @param colecaoMovimentarDevolucoes O colecaoMovimentarDevolucoes a ser setado.
	 */
	public void setColecaoMovimentarDevolucoes(
			Collection colecaoMovimentarDevolucoes) {
		this.colecaoMovimentarDevolucoes = colecaoMovimentarDevolucoes;
	}
	/**
	 * @return Retorna o campo colecaoMovimentarPagamentos.
	 */
	public Collection getColecaoMovimentarPagamentos() {
		return colecaoMovimentarPagamentos;
	}
	/**
	 * @param colecaoMovimentarPagamentos O colecaoMovimentarPagamentos a ser setado.
	 */
	public void setColecaoMovimentarPagamentos(
			Collection colecaoMovimentarPagamentos) {
		this.colecaoMovimentarPagamentos = colecaoMovimentarPagamentos;
	}
	/**
	 * @return Retorna o campo valorTotalDevolucoes.
	 */
	public String getValorTotalDevolucoes() {
		return valorTotalDevolucoes;
	}
	/**
	 * @param valorTotalDevolucoes O valorTotalDevolucoes a ser setado.
	 */
	public void setValorTotalDevolucoes(String valorTotalDevolucoes) {
		this.valorTotalDevolucoes = valorTotalDevolucoes;
	}
	/**
	 * @return Retorna o campo valorTotalPagamentos.
	 */
	public String getValorTotalPagamentos() {
		return valorTotalPagamentos;
	}
	/**
	 * @param valorTotalPagamentos O valorTotalPagamentos a ser setado.
	 */
	public void setValorTotalPagamentos(String valorTotalPagamentos) {
		this.valorTotalPagamentos = valorTotalPagamentos;
	}
	/**
	 * @return Retorna o campo qtdDevolucoes.
	 */
	public Integer getQtdDevolucoes() {
		return qtdDevolucoes;
	}
	/**
	 * @param qtdDevolucoes O qtdDevolucoes a ser setado.
	 */
	public void setQtdDevolucoes(Integer qtdDevolucoes) {
		this.qtdDevolucoes = qtdDevolucoes;
	}
	/**
	 * @return Retorna o campo qtdPagamentos.
	 */
	public Integer getQtdPagamentos() {
		return qtdPagamentos;
	}
	/**
	 * @param qtdPagamentos O qtdPagamentos a ser setado.
	 */
	public void setQtdPagamentos(Integer qtdPagamentos) {
		this.qtdPagamentos = qtdPagamentos;
	}
	


}
