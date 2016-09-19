package gcom.cobranca.parcelamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ParcelamentoFaixaDesconto implements Serializable {

	private static final long serialVersionUID = 656378329631476730L;

	private Integer id;

	private Integer referenciaMinima;

	private Integer referenciaMaxima;

	private BigDecimal percentualDesconto;

	private Date dataCriacao;

	private Date ultimaAlteracao;

	public ParcelamentoFaixaDesconto() {
		super();
	}

	public ParcelamentoFaixaDesconto(Integer id, Integer referenciaMinima, Integer referenciaMaxima, BigDecimal percentualDesconto, Date dataCriacao, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.referenciaMinima = referenciaMinima;
		this.referenciaMaxima = referenciaMaxima;
		this.percentualDesconto = percentualDesconto;
		this.dataCriacao = dataCriacao;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReferenciaMinima() {
		return referenciaMinima;
	}

	public void setReferenciaMinima(Integer referenciaMinima) {
		this.referenciaMinima = referenciaMinima;
	}

	public Integer getReferenciaMaxima() {
		return referenciaMaxima;
	}

	public void setReferenciaMaxima(Integer referenciaMaxima) {
		this.referenciaMaxima = referenciaMaxima;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
