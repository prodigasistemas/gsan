package gcom.cobranca.contratoparcelamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class QuantidadePrestacoes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;
	
	private Integer qtdFaturasParceladas;
	
	private BigDecimal taxaJuros;
	
	private Date ultimaAlteracao;
	
	private ContratoParcelamentoRD contratoRD;
	
	/** default constructor */
	public QuantidadePrestacoes() {
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public ContratoParcelamentoRD getContratoRD() {
		return contratoRD;
	}

	public void setContratoRD(ContratoParcelamentoRD contratoRD) {
		this.contratoRD = contratoRD;
	}

	public Integer getQtdFaturasParceladas() {
		return qtdFaturasParceladas;
	}

	public void setQtdFaturasParceladas(Integer qtdFaturasParceladas) {
		this.qtdFaturasParceladas = qtdFaturasParceladas;
	}

	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
