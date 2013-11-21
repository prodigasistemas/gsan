package gcom.faturamento;

import gcom.faturamento.credito.CreditoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResumoFaturamentoSimulacaoCredito implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ResumoFaturamentoSimulacaoCreditoPK comp_id;
	private CreditoTipo creditoTipo;
	private BigDecimal valor;
	private Date ultimaAlteracao;
	
	public CreditoTipo getCreditoTipo() {
		return creditoTipo;
	}
	public void setCreditoTipo(CreditoTipo creditoTipo) {
		this.creditoTipo = creditoTipo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public ResumoFaturamentoSimulacaoCreditoPK getComp_id() {
		return comp_id;
	}
	public void setComp_id(ResumoFaturamentoSimulacaoCreditoPK comp_id) {
		this.comp_id = comp_id;
	}

}
