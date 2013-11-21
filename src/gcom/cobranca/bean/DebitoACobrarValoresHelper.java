package gcom.cobranca.bean;

import java.io.Serializable;

import gcom.faturamento.debito.DebitoACobrar;

public class DebitoACobrarValoresHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DebitoACobrar debitoACobrar;

	private Short indicadorDebitoPago;

	public DebitoACobrar getDebitoACobrar() {
		return debitoACobrar;
	}

	public void setDebitoACobrar(DebitoACobrar debitoACobrar) {
		this.debitoACobrar = debitoACobrar;
	}

	public Short getIndicadorDebitoPago() {
		return indicadorDebitoPago;
	}

	public void setIndicadorDebitoPago(Short indicadorDebitoPago) {
		this.indicadorDebitoPago = indicadorDebitoPago;
	}

}
