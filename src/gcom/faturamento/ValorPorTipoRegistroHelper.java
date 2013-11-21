package gcom.faturamento;

import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoTipo;

import java.math.BigDecimal;

public class ValorPorTipoRegistroHelper {
	private BigDecimal valor;
	private DebitoTipo debitoTipo;
	private CreditoTipo creditoTipo;
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}
	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}
	public CreditoTipo getCreditoTipo() {
		return creditoTipo;
	}
	public void setCreditoTipo(CreditoTipo creditoTipo) {
		this.creditoTipo = creditoTipo;
	}
	
	
}
