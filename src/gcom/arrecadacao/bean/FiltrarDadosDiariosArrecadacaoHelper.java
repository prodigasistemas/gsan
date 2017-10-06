package gcom.arrecadacao.bean;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class FiltrarDadosDiariosArrecadacaoHelper {

	private Object itemAgrupado;
	
	private String valorAgrupado;
	
	private Integer quantidadeDocumentos;
	
	private Integer quantidadePagamentos;
	
	private BigDecimal valorDebitos;
	
	private BigDecimal valorDescontos;
	
	private BigDecimal valorArrecadacao;
	
	private BigDecimal valorDevolucoes;
	
	private BigDecimal valorArrecadacaoLiquida;
	
	private BigDecimal percentual;
	
	private String ultimoProcessamentoMesInformado;
	
	private String ultimoProcessamentoAtualSistema;
	
	private String tipoProcessamento;
	
	private String faturamentoCobradoEmConta;
	
	/**
	 * Relatório Analitico dos valores diários da arrecadação
	 * 
	 * Coleção de formas de arrecadação.
	 * 
	 * @author Adriana Muniz
	 * data: 05/09/2012
	 */
	private Collection<FormasArrecadacaoDadosDiariosHelper> colecaoFormasArrecadacao;
	
	private Date dataPagamento;
	
	private String arrecadador;
	
	
	public String getTipoProcessamento() {
		return tipoProcessamento;
	}

	public void setTipoProcessamento(String tipoProcessamento) {
		this.tipoProcessamento = tipoProcessamento;
	}

	/**
	 * @return Retorna o campo percentual.
	 */
	public BigDecimal getPercentual() {
		return percentual;
	}

	/**
	 * @param percentual O percentual a ser setado.
	 */
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	/**
	 * @return Retorna o campo quantidadeDocumentos.
	 */
	public Integer getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	/**
	 * @param quantidadeDocumentos O quantidadeDocumentos a ser setado.
	 */
	public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	/**
	 * @return Retorna o campo quantidadePagamentos.
	 */
	public Integer getQuantidadePagamentos() {
		return quantidadePagamentos;
	}

	/**
	 * @param quantidadePagamentos O quantidadePagamentos a ser setado.
	 */
	public void setQuantidadePagamentos(Integer quantidadePagamentos) {
		this.quantidadePagamentos = quantidadePagamentos;
	}

	/**
	 * @return Retorna o campo valorAgrupado.
	 */
	public String getValorAgrupado() {
		return valorAgrupado;
	}

	/**
	 * @param valorAgrupado O valorAgrupado a ser setado.
	 */
	public void setValorAgrupado(String valorAgrupado) {
		this.valorAgrupado = valorAgrupado;
	}

	/**
	 * @return Retorna o campo valorArrecadacao.
	 */
	public BigDecimal getValorArrecadacao() {
		return valorArrecadacao;
	}

	/**
	 * @param valorArrecadacao O valorArrecadacao a ser setado.
	 */
	public void setValorArrecadacao(BigDecimal valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}

	/**
	 * @return Retorna o campo valorArrecadacaoLiquida.
	 */
	public BigDecimal getValorArrecadacaoLiquida() {
		return valorArrecadacaoLiquida;
	}

	/**
	 * @param valorArrecadacaoLiquida O valorArrecadacaoLiquida a ser setado.
	 */
	public void setValorArrecadacaoLiquida(BigDecimal valorArrecadacaoLiquida) {
		this.valorArrecadacaoLiquida = valorArrecadacaoLiquida;
	}

	/**
	 * @return Retorna o campo valorDebitos.
	 */
	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}

	/**
	 * @param valorDebitos O valorDebitos a ser setado.
	 */
	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	/**
	 * @return Retorna o campo valorDescontos.
	 */
	public BigDecimal getValorDescontos() {
		return valorDescontos;
	}

	/**
	 * @param valorDescontos O valorDescontos a ser setado.
	 */
	public void setValorDescontos(BigDecimal valorDescontos) {
		this.valorDescontos = valorDescontos;
	}

	/**
	 * @return Retorna o campo valorDevolucoes.
	 */
	public BigDecimal getValorDevolucoes() {
		return valorDevolucoes;
	}

	/**
	 * @param valorDevolucoes O valorDevolucoes a ser setado.
	 */
	public void setValorDevolucoes(BigDecimal valorDevolucoes) {
		this.valorDevolucoes = valorDevolucoes;
	}

	/**
	 * @return Retorna o campo itemAgrupado.
	 */
	public Object getItemAgrupado() {
		return itemAgrupado;
	}

	/**
	 * @param itemAgrupado O itemAgrupado a ser setado.
	 */
	public void setItemAgrupado(Object itemAgrupado) {
		this.itemAgrupado = itemAgrupado;
	}

	public String getUltimoProcessamentoMesInformado() {
		return ultimoProcessamentoMesInformado;
	}

	public void setUltimoProcessamentoMesInformado(
			String ultimoProcessamentoMesInformado) {
		this.ultimoProcessamentoMesInformado = ultimoProcessamentoMesInformado;
	}

	public String getUltimoProcessamentoAtualSistema() {
		return ultimoProcessamentoAtualSistema;
	}

	public void setUltimoProcessamentoAtualSistema(
			String ultimoProcessamentoAtualSistema) {
		this.ultimoProcessamentoAtualSistema = ultimoProcessamentoAtualSistema;
	}

	public String getFaturamentoCobradoEmConta() {
		return faturamentoCobradoEmConta;
	}

	public void setFaturamentoCobradoEmConta(String faturamentoCobradoEmConta) {
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
	}
	
	public Collection<FormasArrecadacaoDadosDiariosHelper> getColecaoFormasArrecadacao() {
		return colecaoFormasArrecadacao;
	}

	public void setColecaoFormasArrecadacao(
			Collection<FormasArrecadacaoDadosDiariosHelper> colecaoFormasArrecadacao) {
		this.colecaoFormasArrecadacao = colecaoFormasArrecadacao;
	}
	
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}

}
