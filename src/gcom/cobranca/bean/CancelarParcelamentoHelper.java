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

	private BigDecimal valordescontoAcrescimos;

	private BigDecimal valorDescontoFaixa;

	private Integer numeroPrestacoes;

	private Integer numeroPrestacoesCobradas;

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
		this.valordescontoAcrescimos = (BigDecimal) dados[5];
		this.valorDescontoFaixa = (BigDecimal) dados[6];
		this.numeroPrestacoes = (Integer) dados[7];
		this.numeroPrestacoesCobradas = (Integer) dados[8];
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

	public BigDecimal getValorContas() {
		return valorContas;
	}

	public void setValorContas(BigDecimal valorContas) {
		this.valorContas = valorContas;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}

	public void setValorAcrescimos(BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
	}

	public BigDecimal getValordescontoAcrescimos() {
		return valordescontoAcrescimos;
	}

	public void setValordescontoAcrescimos(BigDecimal valordescontoAcrescimos) {
		this.valordescontoAcrescimos = valordescontoAcrescimos;
	}

	public BigDecimal getValorDescontoFaixa() {
		return valorDescontoFaixa;
	}

	public void setValorDescontoFaixa(BigDecimal valorDescontoFaixa) {
		this.valorDescontoFaixa = valorDescontoFaixa;
	}

	public Integer getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(Integer numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public Integer getNumeroPrestacoesCobradas() {
		return numeroPrestacoesCobradas;
	}

	public void setNumeroPrestacoesCobradas(Integer numeroPrestacoesCobradas) {
		this.numeroPrestacoesCobradas = numeroPrestacoesCobradas;
	}

	public BigDecimal getValorContasSemEntrada() {
		return valorContas.subtract(valorEntrada);
	}

	public BigDecimal getParcelaSemJuros() {
		return getValorContasSemEntrada().divide(BigDecimal.valueOf(numeroPrestacoes)).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getTotalContasCobradas() {
		return getParcelaSemJuros().multiply(BigDecimal.valueOf(numeroPrestacoesCobradas)).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getSaldoDevedorContas() {
		return getValorContasSemEntrada().subtract(getTotalContasCobradas()).add(valorDescontoFaixa);
	}
	
	public BigDecimal getSaldoDevedorContasCurtoPrazo() {
		int numeroPrestacoesCurtoPrazo = numeroPrestacoesCobradas >= 12 ? 12 : numeroPrestacoesCobradas;
		BigDecimal valorCurtoPrazo = parcelamento.getValorPrestacao().multiply(new BigDecimal(numeroPrestacoesCurtoPrazo));
		return valorCurtoPrazo;
	}
	
	public BigDecimal getSaldoDevedorContasLongoPrazo() {
		BigDecimal valorLongoPrazo = getSaldoDevedorContas().subtract(getSaldoDevedorContasCurtoPrazo());
		return valorLongoPrazo;
	}

	public BigDecimal getParcelaAcrescimos() {
		return valorAcrescimos.divide(BigDecimal.valueOf(numeroPrestacoes)).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getTotalAcrescimosCobrados() {
		return getParcelaAcrescimos().multiply(BigDecimal.valueOf(numeroPrestacoesCobradas)).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getSaldoDevedorAcrescimos() {
		return valorAcrescimos.subtract(getTotalAcrescimosCobrados());
	}
	
	public BigDecimal getSaldoDevedorAcrescimosCurtoPrazo() {
		int numeroPrestacoesCurtoPrazo = numeroPrestacoesCobradas >= 12 ? 12 : numeroPrestacoesCobradas;
		BigDecimal valorAcrescimosCurtoPrazo = getParcelaAcrescimos().multiply(new BigDecimal(numeroPrestacoesCurtoPrazo));
		return valorAcrescimosCurtoPrazo;
	}
	
	public BigDecimal getSaldoDevedorAcrescimosLongoPrazo() {
		BigDecimal valorAcrescimosLongoPrazo = getSaldoDevedorAcrescimos().subtract(getSaldoDevedorAcrescimosCurtoPrazo());
		return valorAcrescimosLongoPrazo;
	}

	public BigDecimal getTotalCancelamentoDescontos() {
		return getParcelaAcrescimos().multiply(BigDecimal.valueOf(numeroPrestacoesCobradas));
	}
	
	public BigDecimal getSaldoDevedorTotal() {
		return getSaldoDevedorContas().add(getSaldoDevedorAcrescimos()).add(getTotalCancelamentoDescontos());
	}
}