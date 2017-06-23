package gcom.cobranca.bean;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.parcelamento.Parcelamento;

import java.math.BigDecimal;

public class CancelarParcelamentoHelper {

	private Parcelamento parcelamento;

	private Imovel imovel;

	private BigDecimal valorContas;

	private BigDecimal valorEntrada;

	private BigDecimal valorAcrescimos;

	private BigDecimal valorDescontoAcrescimos;

	private BigDecimal valorDescontoFaixa;

	private BigDecimal numeroPrestacoes;

	private BigDecimal numeroPrestacoesCobradas;

	public CancelarParcelamentoHelper() {
		super();
	}

	public CancelarParcelamentoHelper(Parcelamento parcelamento, Imovel imovel, Object[] dados) {
		super();
		this.parcelamento = parcelamento;
		this.imovel = imovel;
		this.valorContas = (BigDecimal) dados[2];
		this.valorEntrada = (BigDecimal) dados[3];
		this.valorAcrescimos = (BigDecimal) dados[4];
		this.valorDescontoAcrescimos = (BigDecimal) dados[5];
		this.valorDescontoFaixa = (BigDecimal) dados[6];
		this.numeroPrestacoes = BigDecimal.valueOf((Integer) dados[7]).setScale(2);
		this.numeroPrestacoesCobradas = BigDecimal.valueOf((Integer) dados[8]).setScale(2);
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

	public BigDecimal getSaldoDevedorTotal() {
		return getSaldoDevedorContas().add(getSaldoDevedorAcrescimos()).add(getTotalCancelamentoDescontos());
	}

	public BigDecimal getSaldoDevedorContas() {
		return getValorContasSemEntrada().subtract(getTotalContasCobradas()).add(valorDescontoFaixa);
	}

	public BigDecimal getSaldoDevedorAcrescimos() {
		return valorAcrescimos.subtract(getTotalAcrescimosCobrados());
	}

	public BigDecimal getTotalCancelamentoDescontos() {
		return getParcelaDescontoAcrescimos().multiply(numeroPrestacoesCobradas).setScale(2, BigDecimal.ROUND_DOWN);
	}

	private BigDecimal getValorContasSemEntrada() {
		return valorContas.subtract(valorEntrada);
	}

	private BigDecimal getParcelaSemJuros() {
		return getValorContasSemEntrada().divide(numeroPrestacoes).setScale(2, BigDecimal.ROUND_DOWN);
	}

	private BigDecimal getTotalContasCobradas() {
		return getParcelaSemJuros().multiply(numeroPrestacoesCobradas).setScale(2, BigDecimal.ROUND_DOWN);
	}

	private BigDecimal getParcelaAcrescimos() {
		return valorAcrescimos.divide(numeroPrestacoes).setScale(2, BigDecimal.ROUND_DOWN);
	}

	private BigDecimal getTotalAcrescimosCobrados() {
		return getParcelaAcrescimos().multiply(numeroPrestacoesCobradas).setScale(2, BigDecimal.ROUND_DOWN);
	}

	private BigDecimal getParcelaDescontoAcrescimos() {
		return valorDescontoAcrescimos.divide(numeroPrestacoes).setScale(2, BigDecimal.ROUND_DOWN);
	}
}