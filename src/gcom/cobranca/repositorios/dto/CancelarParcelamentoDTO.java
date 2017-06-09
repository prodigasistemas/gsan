package gcom.cobranca.repositorios.dto;

import java.math.BigDecimal;

public class CancelarParcelamentoDTO {

	private Integer idParcelamento;

	private Integer idImovel;

	private Integer numeroPrestacoesCobradas;

	private BigDecimal valorOriginal;

	private Integer numeroPrestacoes;

	private BigDecimal valorJuros;

	private BigDecimal valorEntrada;

	private BigDecimal valorTotalDebitoCobrado;

	public CancelarParcelamentoDTO() {
		super();
	}

	public CancelarParcelamentoDTO(Object[] dados) {
		super();
		this.idParcelamento = (Integer) dados[0];
		this.idImovel = (Integer) dados[1];
		this.valorEntrada = (BigDecimal) dados[2];
		this.valorTotalDebitoCobrado = (BigDecimal) dados[3];
		this.valorOriginal = (BigDecimal) dados[4];
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

	public Integer getNumeroPrestacoesCobradas() {
		return numeroPrestacoesCobradas;
	}

	public void setNumeroPrestacoesCobradas(Integer numeroPrestacoesCobradas) {
		this.numeroPrestacoesCobradas = numeroPrestacoesCobradas;
	}

	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(BigDecimal valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public Integer getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(Integer numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public BigDecimal getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(BigDecimal valorJuros) {
		this.valorJuros = valorJuros;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorTotalDebitoCobrado() {
		return valorTotalDebitoCobrado;
	}

	public void setValorTotalDebitoCobrado(BigDecimal valorTotalDebitoCobrado) {
		this.valorTotalDebitoCobrado = valorTotalDebitoCobrado;
	}
}