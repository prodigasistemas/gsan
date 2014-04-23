package gcom.gui.cobranca.spcserasa;

import java.math.BigDecimal;

/** 
 * @author Vivianne Sousa
 * @created 28/04/2009
 */

public class RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper {
	
	private BigDecimal valorParceladoEntrada;
	private BigDecimal valorParceladoEntradaPago;
	private BigDecimal valorParcelado;
	private BigDecimal valorParceladoPago;
	
	public BigDecimal getValorParcelado() {
		return valorParcelado;
	}
	public void setValorParcelado(BigDecimal valorParcelado) {
		this.valorParcelado = valorParcelado;
	}
	public BigDecimal getValorParceladoEntrada() {
		return valorParceladoEntrada;
	}
	public void setValorParceladoEntrada(BigDecimal valorParceladoEntrada) {
		this.valorParceladoEntrada = valorParceladoEntrada;
	}
	public BigDecimal getValorParceladoEntradaPago() {
		return valorParceladoEntradaPago;
	}
	public void setValorParceladoEntradaPago(BigDecimal valorParceladoEntradaPago) {
		this.valorParceladoEntradaPago = valorParceladoEntradaPago;
	}
	public BigDecimal getValorParceladoPago() {
		return valorParceladoPago;
	}
	public void setValorParceladoPago(BigDecimal valorParceladoPago) {
		this.valorParceladoPago = valorParceladoPago;
	}
	
}
