package gcom.cobranca.bean;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.parcelamento.Parcelamento;

import java.math.BigDecimal;

public class CancelarParcelamentoHelper {

	private Parcelamento parcelamento;

	private Imovel imovel;

	private BigDecimal valorOriginal;

	private BigDecimal valorEntrada;

	private BigDecimal valorPrestacao;

	private Integer numeroPrestacoesCobradas;
	
	public CancelarParcelamentoHelper() {
		super();
	}
	
	public CancelarParcelamentoHelper(Parcelamento parcelamento, Imovel imovel, Object[] dados) {
		super();
		this.parcelamento = parcelamento;
		this.imovel = imovel;
		this.valorOriginal = (BigDecimal) dados[2];
		this.valorEntrada = (BigDecimal) dados[3];
		this.valorPrestacao = (BigDecimal) dados[4];
		this.numeroPrestacoesCobradas = (Integer) dados[5];
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
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
	
	public BigDecimal getParcelaSemJuros() {
		// TODO
		return null;
	}
	
	public BigDecimal totalContasCobradas() {
		// TODO
		return null;
	}
	
	public BigDecimal saldoDevedorContas() {
		// TODO
		return null;
	}
	
	public BigDecimal totalAcrescimosCobrados() {
		// TODO
		return null;
	}
	
	public BigDecimal saldoDevedorAcrescimos() {
		// TODO
		return null;
	}
	
	public BigDecimal saldoDevedorCancelamentoDescontos() {
		// TODO
		return null;
	}
}