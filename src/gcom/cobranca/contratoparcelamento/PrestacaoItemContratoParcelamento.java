package gcom.cobranca.contratoparcelamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PrestacaoItemContratoParcelamento  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;
	
	private PrestacaoContratoParcelamento prestacao;
	
	private ContratoParcelamentoItem item;
	
	private BigDecimal valorPago;
	
	private Date ultimaAlteracao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PrestacaoContratoParcelamento getPrestacao() {
		return prestacao;
	}

	public void setPrestacao(PrestacaoContratoParcelamento prestacao) {
		this.prestacao = prestacao;
	}

	public ContratoParcelamentoItem getItem() {
		return item;
	}

	public void setItem(ContratoParcelamentoItem item) {
		this.item = item;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}
