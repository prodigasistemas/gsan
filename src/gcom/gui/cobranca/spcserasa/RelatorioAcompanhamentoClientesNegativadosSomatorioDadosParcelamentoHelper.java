package gcom.gui.cobranca.spcserasa;

import java.math.BigDecimal;

/** 
 * @author Vivianne Sousa
 * @created 29/04/2009
 */

public class RelatorioAcompanhamentoClientesNegativadosSomatorioDadosParcelamentoHelper {
	
	private BigDecimal valorParceladoTotal;
	private BigDecimal valorParceladoEntradaTotal;  
	private BigDecimal valorParceladoEntradaPagoTotal;
	private Short numeroPrestacoesTotal;
	private Short numeroPrestacoesCobradasPagasTotal;
	private BigDecimal valorParceladoCobradoPagoTotal;   
	private Short numeroPrestacoesCobradasNaoPagasTotal;
	private BigDecimal valorParceladoCobradoNaoPagoTotal;
	private Short numeroPrestacoesNaoCobradasTotal;
	private BigDecimal valorParceladoNaoCobradoTotal;
	private BigDecimal valorParceladoCanceladoTotal;
	
	public Short getNumeroPrestacoesCobradasNaoPagasTotal() {
		return numeroPrestacoesCobradasNaoPagasTotal;
	}
	public void setNumeroPrestacoesCobradasNaoPagasTotal(
			Short numeroPrestacoesCobradasNaoPagasTotal) {
		this.numeroPrestacoesCobradasNaoPagasTotal = numeroPrestacoesCobradasNaoPagasTotal;
	}
	public Short getNumeroPrestacoesCobradasPagasTotal() {
		return numeroPrestacoesCobradasPagasTotal;
	}
	public void setNumeroPrestacoesCobradasPagasTotal(
			Short numeroPrestacoesCobradasPagasTotal) {
		this.numeroPrestacoesCobradasPagasTotal = numeroPrestacoesCobradasPagasTotal;
	}
	public Short getNumeroPrestacoesNaoCobradasTotal() {
		return numeroPrestacoesNaoCobradasTotal;
	}
	public void setNumeroPrestacoesNaoCobradasTotal(
			Short numeroPrestacoesNaoCobradasTotal) {
		this.numeroPrestacoesNaoCobradasTotal = numeroPrestacoesNaoCobradasTotal;
	}
	public Short getNumeroPrestacoesTotal() {
		return numeroPrestacoesTotal;
	}
	public void setNumeroPrestacoesTotal(Short numeroPrestacoesTotal) {
		this.numeroPrestacoesTotal = numeroPrestacoesTotal;
	}
	public BigDecimal getValorParceladoCanceladoTotal() {
		return valorParceladoCanceladoTotal;
	}
	public void setValorParceladoCanceladoTotal(
			BigDecimal valorParceladoCanceladoTotal) {
		this.valorParceladoCanceladoTotal = valorParceladoCanceladoTotal;
	}
	public BigDecimal getValorParceladoCobradoNaoPagoTotal() {
		return valorParceladoCobradoNaoPagoTotal;
	}
	public void setValorParceladoCobradoNaoPagoTotal(
			BigDecimal valorParceladoCobradoNaoPagoTotal) {
		this.valorParceladoCobradoNaoPagoTotal = valorParceladoCobradoNaoPagoTotal;
	}
	public BigDecimal getValorParceladoCobradoPagoTotal() {
		return valorParceladoCobradoPagoTotal;
	}
	public void setValorParceladoCobradoPagoTotal(
			BigDecimal valorParceladoCobradoPagoTotal) {
		this.valorParceladoCobradoPagoTotal = valorParceladoCobradoPagoTotal;
	}
	public BigDecimal getValorParceladoEntradaPagoTotal() {
		return valorParceladoEntradaPagoTotal;
	}
	public void setValorParceladoEntradaPagoTotal(
			BigDecimal valorParceladoEntradaPagoTotal) {
		this.valorParceladoEntradaPagoTotal = valorParceladoEntradaPagoTotal;
	}
	public BigDecimal getValorParceladoNaoCobradoTotal() {
		return valorParceladoNaoCobradoTotal;
	}
	public void setValorParceladoNaoCobradoTotal(
			BigDecimal valorParceladoNaoCobradoTotal) {
		this.valorParceladoNaoCobradoTotal = valorParceladoNaoCobradoTotal;
	}
	public BigDecimal getValorParceladoEntradaTotal() {
		return valorParceladoEntradaTotal;
	}
	public void setValorParceladoEntradaTotal(BigDecimal valorParceladoEntradaTotal) {
		this.valorParceladoEntradaTotal = valorParceladoEntradaTotal;
	}
	public BigDecimal getValorParceladoTotal() {
		return valorParceladoTotal;
	}
	public void setValorParceladoTotal(BigDecimal valorParceladoTotal) {
		this.valorParceladoTotal = valorParceladoTotal;
	}
	
	
	
}
