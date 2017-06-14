package gcom.cobranca.repositorios.dto;

import java.math.BigDecimal;

public class CancelarParcelamentoDTO {

	private Integer idParcelamento;

	private Integer idImovel;

	private BigDecimal valorOriginal;

	private BigDecimal valorEntrada;

	private BigDecimal valorPrestacao;

	private Integer numeroPrestacoesCobradas;

	public CancelarParcelamentoDTO() {
		super();
	}

	public CancelarParcelamentoDTO(Object[] dados) {
		super();
		this.idParcelamento = (Integer) dados[0];
		this.idImovel = (Integer) dados[1];
		this.valorOriginal = (BigDecimal) dados[2];
		this.valorEntrada = (BigDecimal) dados[3];
		this.valorPrestacao = (BigDecimal) dados[4];
		this.numeroPrestacoesCobradas = (Integer) dados[5];
	}

	public Integer getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(BigDecimal valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorPrestacao() {
		return valorPrestacao;
	}

	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	public Integer getNumeroPrestacoesCobradas() {
		return numeroPrestacoesCobradas;
	}

	public void setNumeroPrestacoesCobradas(Integer numeroPrestacoesCobradas) {
		this.numeroPrestacoesCobradas = numeroPrestacoesCobradas;
	}
}