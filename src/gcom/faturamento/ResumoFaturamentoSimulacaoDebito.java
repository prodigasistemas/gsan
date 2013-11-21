package gcom.faturamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResumoFaturamentoSimulacaoDebito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ResumoFaturamentoSimulacaoDebitoPK comp_id;
	private BigDecimal valor;
	private Date ultimaAlteracao;
	
	public ResumoFaturamentoSimulacaoDebitoPK getComp_id() {
		return comp_id;
	}
	public void setComp_id(ResumoFaturamentoSimulacaoDebitoPK comp_id) {
		this.comp_id = comp_id;
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
	
	
}
