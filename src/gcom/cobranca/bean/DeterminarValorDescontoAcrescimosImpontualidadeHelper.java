package gcom.cobranca.bean;

import gcom.cobranca.parcelamento.ParcelamentoPerfil;

import java.io.Serializable;
import java.math.BigDecimal;

public class DeterminarValorDescontoAcrescimosImpontualidadeHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private ParcelamentoPerfil parcelamentoPerfil;
	
	/*
	 * TODO - COSANPA
	 */
	private BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidadeMulta;
	
	private BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidadeJurosMora;
	
	private BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidadeAtualizacaoMonetaria;
	
	private BigDecimal valorTotalAcrescimosImpontualidadeGuiaPagamentoMulta;
	
	private BigDecimal valorTotalAcrescimosImpontualidadeGuiaPagamentoJurosMora;
	
	private BigDecimal valorTotalAcrescimosImpontualidadeGuiaPagamentoAtualizacaoMonetaria;
	// fim alteração
	
	private BigDecimal valorDescontoAcrescimosImpontualidadeRDEspecial;
	
	public ParcelamentoPerfil getParcelamentoPerfil() {
		return parcelamentoPerfil;
	}

	public void setParcelamentoPerfil(ParcelamentoPerfil parcelamentoPerfil) {
		this.parcelamentoPerfil = parcelamentoPerfil;
	}

	public BigDecimal getValorTotalAcrescimosImpontualidadeGuiaPagamentoMulta() {
		return valorTotalAcrescimosImpontualidadeGuiaPagamentoMulta;
	}

	public void setValorTotalAcrescimosImpontualidadeGuiaPagamentoMulta(
			BigDecimal valorTotalAcrescimosImpontualidadeGuiaPagamentoMulta) {
		this.valorTotalAcrescimosImpontualidadeGuiaPagamentoMulta = valorTotalAcrescimosImpontualidadeGuiaPagamentoMulta;
	}

	public BigDecimal getValorTotalAcrescimosImpontualidadeGuiaPagamentoJurosMora() {
		return valorTotalAcrescimosImpontualidadeGuiaPagamentoJurosMora;
	}

	public void setValorTotalAcrescimosImpontualidadeGuiaPagamentoJurosMora(
			BigDecimal valorTotalAcrescimosImpontualidadeGuiaPagamentoJurosMora) {
		this.valorTotalAcrescimosImpontualidadeGuiaPagamentoJurosMora = valorTotalAcrescimosImpontualidadeGuiaPagamentoJurosMora;
	}

	public BigDecimal getValorTotalAcrescimosImpontualidadeGuiaPagamentoAtualizacaoMonetaria() {
		return valorTotalAcrescimosImpontualidadeGuiaPagamentoAtualizacaoMonetaria;
	}

	public void setValorTotalAcrescimosImpontualidadeGuiaPagamentoAtualizacaoMonetaria(
			BigDecimal valorTotalAcrescimosImpontualidadeGuiaPagamentoAtualizacaoMonetaria) {
		this.valorTotalAcrescimosImpontualidadeGuiaPagamentoAtualizacaoMonetaria = valorTotalAcrescimosImpontualidadeGuiaPagamentoAtualizacaoMonetaria;
	}

	public BigDecimal getValorDescontoAcrescimosImpontualidadeRDEspecial() {
		return valorDescontoAcrescimosImpontualidadeRDEspecial;
	}

	public void setValorDescontoAcrescimosImpontualidadeRDEspecial(
			BigDecimal valorDescontoAcrescimosImpontualidadeRDEspecial) {
		this.valorDescontoAcrescimosImpontualidadeRDEspecial = valorDescontoAcrescimosImpontualidadeRDEspecial;
	}

	public BigDecimal getValorTotalAcrescimosImpontualidadePorAntiguidadeMulta() {
		return valorTotalAcrescimosImpontualidadePorAntiguidadeMulta;
	}

	public void setValorTotalAcrescimosImpontualidadePorAntiguidadeMulta(
			BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidadeMulta) {
		this.valorTotalAcrescimosImpontualidadePorAntiguidadeMulta = valorTotalAcrescimosImpontualidadePorAntiguidadeMulta;
	}
	
	public BigDecimal getValorTotalAcrescimosImpontualidadePorAntiguidadeJurosMora() {
		return valorTotalAcrescimosImpontualidadePorAntiguidadeJurosMora;
	}

	public void setValorTotalAcrescimosImpontualidadePorAntiguidadeJurosMora(
			BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidadeJurosMora) {
		this.valorTotalAcrescimosImpontualidadePorAntiguidadeJurosMora = valorTotalAcrescimosImpontualidadePorAntiguidadeJurosMora;
	}
	
	public BigDecimal getValorTotalAcrescimosImpontualidadePorAntiguidadeAtualizacaoMonetaria() {
		return valorTotalAcrescimosImpontualidadePorAntiguidadeAtualizacaoMonetaria;
	}

	public void setValorTotalAcrescimosImpontualidadePorAntiguidadeAtualizacaoMonetaria(
			BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidadeAtualizacaoMonetaria) {
		this.valorTotalAcrescimosImpontualidadePorAntiguidadeAtualizacaoMonetaria = valorTotalAcrescimosImpontualidadePorAntiguidadeAtualizacaoMonetaria;
	}

	public DeterminarValorDescontoAcrescimosImpontualidadeHelper(ParcelamentoPerfil parcelamentoPerfil, 
			BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidadeMulta, 
			BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidadeJurosMora, 
			BigDecimal valorTotalAcrescimosImpontualidadePorAntiguidadeAtualizacaoMonetaria, 
			BigDecimal valorAcrescimosImpontualidadeGuiaPagamentoMulta, 
			BigDecimal valorAcrescimosImpontualidadeGuiaPagamentoJurosMora, 
			BigDecimal valorAcrescimosImpontualidadeGuiaPagamentoAtualizacaoMonetaria, 
			BigDecimal valorDescontoAcrescimosImpontualidadeRDEspecial) {
		super();
		// TODO Auto-generated constructor stub
		this.parcelamentoPerfil = parcelamentoPerfil;
		this.valorTotalAcrescimosImpontualidadePorAntiguidadeMulta = valorTotalAcrescimosImpontualidadePorAntiguidadeMulta;
		this.valorTotalAcrescimosImpontualidadePorAntiguidadeJurosMora = valorTotalAcrescimosImpontualidadePorAntiguidadeJurosMora;
		this.valorTotalAcrescimosImpontualidadePorAntiguidadeAtualizacaoMonetaria = valorTotalAcrescimosImpontualidadePorAntiguidadeAtualizacaoMonetaria;
		this.valorTotalAcrescimosImpontualidadeGuiaPagamentoMulta = valorAcrescimosImpontualidadeGuiaPagamentoMulta;
		this.valorTotalAcrescimosImpontualidadeGuiaPagamentoJurosMora = valorAcrescimosImpontualidadeGuiaPagamentoJurosMora;
		this.valorTotalAcrescimosImpontualidadeGuiaPagamentoAtualizacaoMonetaria = valorAcrescimosImpontualidadeGuiaPagamentoAtualizacaoMonetaria;
		this.valorDescontoAcrescimosImpontualidadeRDEspecial = valorDescontoAcrescimosImpontualidadeRDEspecial;
	}
	
	
}
