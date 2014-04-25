package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class CalcularValorDescontoAntiguidadeDebitoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal valorDescontoAntiguidade;
	
	/*
     * TODO - COSANPA - Felipe Santos
     * 
     */
	private BigDecimal valorTotalAcrescimosImpontualidadeMulta;
	
	private BigDecimal valorTotalAcrescimosImpontualidadeJurosMora;
	
	private BigDecimal valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria;
	// fim alteração
	
	private BigDecimal maiorQuantidadeMinimaMesesAntiguidade;
	
	private Collection colecaoContasEmAntiguidade;
	
	private Collection colecaoContasParaParcelamento;
	
	public CalcularValorDescontoAntiguidadeDebitoHelper(BigDecimal valorDescontoAntiguidade, 
			BigDecimal valorTotalAcrescimosImpontualidadeMulta, BigDecimal valorTotalAcrescimosImpontualidadeJurosMora, BigDecimal valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria, 
			BigDecimal maiorQuantidadeMinimaMesesAntiguidade, Collection colecaoContasEmAntiguidade, Collection colecaoContasParaParcelamento) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
		this.valorTotalAcrescimosImpontualidadeMulta = valorTotalAcrescimosImpontualidadeMulta;
		this.valorTotalAcrescimosImpontualidadeJurosMora = valorTotalAcrescimosImpontualidadeJurosMora;
		this.valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria = valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria;
		this.maiorQuantidadeMinimaMesesAntiguidade = maiorQuantidadeMinimaMesesAntiguidade;
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
	}

	public Collection getColecaoContasEmAntiguidade() {
		return colecaoContasEmAntiguidade;
	}

	public void setColecaoContasEmAntiguidade(Collection colecaoContasEmAntiguidade) {
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
	}

	public BigDecimal getMaiorQuantidadeMinimaMesesAntiguidade() {
		return maiorQuantidadeMinimaMesesAntiguidade;
	}

	public void setMaiorQuantidadeMinimaMesesAntiguidade(
			BigDecimal maiorQuantidadeMinimaMesesAntiguidade) {
		this.maiorQuantidadeMinimaMesesAntiguidade = maiorQuantidadeMinimaMesesAntiguidade;
	}

	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	public BigDecimal getValorTotalAcrescimosImpontualidadeMulta() {
		return valorTotalAcrescimosImpontualidadeMulta;
	}

	public void setValorTotalAcrescimosImpontualidadeMulta(
			BigDecimal valorTotalAcrescimosImpontualidadeMulta) {
		this.valorTotalAcrescimosImpontualidadeMulta = valorTotalAcrescimosImpontualidadeMulta;
	}
	
	public BigDecimal getValorTotalAcrescimosImpontualidadeJurosMora() {
		return valorTotalAcrescimosImpontualidadeJurosMora;
	}

	public void setValorTotalAcrescimosImpontualidadeJurosMora(
			BigDecimal valorTotalAcrescimosImpontualidadeJurosMora) {
		this.valorTotalAcrescimosImpontualidadeJurosMora = valorTotalAcrescimosImpontualidadeJurosMora;
	}
	
	public BigDecimal getValorTotalAcrescimosImpontualidadeAtualizacaoMonetaria() {
		return valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria;
	}
	
	public void setValorTotalAcrescimosImpontualidadeAtualizacaoMonetaria(
			BigDecimal valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria) {
		this.valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria = valorTotalAcrescimosImpontualidadeAtualizacaoMonetaria;
	}

	public Collection getColecaoContasParaParcelamento() {
		return colecaoContasParaParcelamento;
	}

	public void setColecaoContasParaParcelamento(
			Collection colecaoContasParaParcelamento) {
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
	}
}
