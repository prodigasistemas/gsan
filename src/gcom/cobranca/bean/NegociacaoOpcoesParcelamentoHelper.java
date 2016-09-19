package gcom.cobranca.bean;

import gcom.cobranca.parcelamento.ParcelamentoPerfil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class NegociacaoOpcoesParcelamentoHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Collection<OpcoesParcelamentoHelper> opcoesParcelamento;

	private BigDecimal valorEntradaMinima;

	private BigDecimal valorDescontoFaixaReferenciaConta;

	private BigDecimal valorDescontoAcrecismosImpotualidade;

	private BigDecimal valorDescontoInatividade;

	private BigDecimal valorDescontoAntiguidade;

	private BigDecimal percentualDescontoAcrescimosImpontualidade;

	private BigDecimal percentualDescontoAntiguidadeDebito;

	private BigDecimal percentualDescontoInatividadeLigacaoAgua;

	private ParcelamentoPerfil parcelamentoPerfil;

	private BigDecimal valorDescontoSancoesRDEspecial;

	private BigDecimal valorDescontoTarifaSocialRDEspecial;

	private BigDecimal valorTotalDescontoPagamentoAVista;

	private Collection colecaoContasEmAntiguidade;

	private Collection colecaoContasParaParcelamento;

	private Short indicadorExisteParcelamentoEmAndamento;

	private BigDecimal valorDebitoDesconto;

	private BigDecimal valorMinimoPrestacao;

	private BigDecimal percentualDescontoInatividadeAvistaLigacaoAgua;

	public NegociacaoOpcoesParcelamentoHelper() {
	}

	public BigDecimal getValorEntradaMinima() {
		return valorEntradaMinima;
	}

	public void setValorEntradaMinima(BigDecimal valorEntradaMinima) {
		this.valorEntradaMinima = valorEntradaMinima;
	}

	public BigDecimal getValorDescontoAcrecismosImpotualidade() {
		return valorDescontoAcrecismosImpotualidade;
	}

	public void setValorDescontoAcrecismosImpotualidade(BigDecimal valorDescontoAcrecismosImpotualidade) {
		this.valorDescontoAcrecismosImpotualidade = valorDescontoAcrecismosImpotualidade;
	}

	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	public BigDecimal getValorDescontoInatividade() {
		return valorDescontoInatividade;
	}

	public void setValorDescontoInatividade(BigDecimal valorDescontoInatividade) {
		this.valorDescontoInatividade = valorDescontoInatividade;
	}

	public Collection<OpcoesParcelamentoHelper> getOpcoesParcelamento() {
		return opcoesParcelamento;
	}

	public void setOpcoesParcelamento(Collection<OpcoesParcelamentoHelper> opcoesParcelamento) {
		this.opcoesParcelamento = opcoesParcelamento;
	}

	public ParcelamentoPerfil getParcelamentoPerfil() {
		return parcelamentoPerfil;
	}

	public void setParcelamentoPerfil(ParcelamentoPerfil parcelamentoPerfil) {
		this.parcelamentoPerfil = parcelamentoPerfil;
	}

	public BigDecimal getPercentualDescontoAcrescimosImpontualidade() {
		return percentualDescontoAcrescimosImpontualidade;
	}

	public void setPercentualDescontoAcrescimosImpontualidade(BigDecimal percentualDescontoAcrescimosImpontualidade) {
		this.percentualDescontoAcrescimosImpontualidade = percentualDescontoAcrescimosImpontualidade;
	}

	public BigDecimal getPercentualDescontoAntiguidadeDebito() {
		return percentualDescontoAntiguidadeDebito;
	}

	public void setPercentualDescontoAntiguidadeDebito(BigDecimal percentualDescontoAntiguidadeDebito) {
		this.percentualDescontoAntiguidadeDebito = percentualDescontoAntiguidadeDebito;
	}

	public BigDecimal getPercentualDescontoInatividadeLigacaoAgua() {
		return percentualDescontoInatividadeLigacaoAgua;
	}

	public void setPercentualDescontoInatividadeLigacaoAgua(BigDecimal percentualDescontoInatividadeLigacaoAgua) {
		this.percentualDescontoInatividadeLigacaoAgua = percentualDescontoInatividadeLigacaoAgua;
	}

	public BigDecimal getValorMinimoPrestacao() {
		return valorMinimoPrestacao;
	}

	public void setValorMinimoPrestacao(BigDecimal valorMinimoPrestacao) {
		this.valorMinimoPrestacao = valorMinimoPrestacao;
	}

	public BigDecimal getValorDescontoSancoesRDEspecial() {
		return valorDescontoSancoesRDEspecial;
	}

	public void setValorDescontoSancoesRDEspecial(BigDecimal valorDescontoSancoesRDEspecial) {
		this.valorDescontoSancoesRDEspecial = valorDescontoSancoesRDEspecial;
	}

	public BigDecimal getValorDescontoTarifaSocialRDEspecial() {
		return valorDescontoTarifaSocialRDEspecial;
	}

	public void setValorDescontoTarifaSocialRDEspecial(BigDecimal valorDescontoTarifaSocialRDEspecial) {
		this.valorDescontoTarifaSocialRDEspecial = valorDescontoTarifaSocialRDEspecial;
	}

	public BigDecimal getValorTotalDescontoPagamentoAVista() {
		return valorTotalDescontoPagamentoAVista;
	}

	public void setValorTotalDescontoPagamentoAVista(BigDecimal valorTotalDescontoPagamentoAVista) {
		this.valorTotalDescontoPagamentoAVista = valorTotalDescontoPagamentoAVista;
	}

	public Collection getColecaoContasEmAntiguidade() {
		return colecaoContasEmAntiguidade;
	}

	public void setColecaoContasEmAntiguidade(Collection colecaoContasEmAntiguidade) {
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
	}

	public Collection getColecaoContasParaParcelamento() {
		return colecaoContasParaParcelamento;
	}

	public void setColecaoContasParaParcelamento(Collection colecaoContasParaParcelamento) {
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
	}

	public Short getIndicadorExisteParcelamentoEmAndamento() {
		return indicadorExisteParcelamentoEmAndamento;
	}

	public void setIndicadorExisteParcelamentoEmAndamento(Short indicadorExisteParcelamentoEmAndamento) {
		this.indicadorExisteParcelamentoEmAndamento = indicadorExisteParcelamentoEmAndamento;
	}

	public BigDecimal getPercentualDescontoInatividadeAvistaLigacaoAgua() {
		return percentualDescontoInatividadeAvistaLigacaoAgua;
	}

	public void setPercentualDescontoInatividadeAvistaLigacaoAgua(BigDecimal percentualDescontoInatividadeAvistaLigacaoAgua) {
		this.percentualDescontoInatividadeAvistaLigacaoAgua = percentualDescontoInatividadeAvistaLigacaoAgua;
	}

	public BigDecimal getValorDebitoDesconto() {
		return valorDebitoDesconto;
	}

	public void setValorDebitoDesconto(BigDecimal valorDebitoDesconto) {
		this.valorDebitoDesconto = valorDebitoDesconto;
	}

	public BigDecimal getValorDescontoFaixaReferenciaConta() {
		return valorDescontoFaixaReferenciaConta;
	}

	public void setValorDescontoFaixaReferenciaConta(BigDecimal valorDescontoFaixaReferenciaConta) {
		this.valorDescontoFaixaReferenciaConta = valorDescontoFaixaReferenciaConta;
	}
}
