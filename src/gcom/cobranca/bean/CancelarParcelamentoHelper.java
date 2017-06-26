package gcom.cobranca.bean;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.parcelamento.Parcelamento;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
		this.valorDescontoFaixa = dados[6] == null ? new BigDecimal(0) : (BigDecimal) dados[6];
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

	public BigDecimal getTotalCancelamentoDescontos() {
		return getParcelaDescontoAcrescimos().multiply(numeroPrestacoesCobradas).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getSaldoDevedorContasCurtoPrazo() {
		int numeroPrestacoesCurtoPrazo = numeroPrestacoesCobradas.intValue() >= 12 ? 12 : numeroPrestacoesCobradas.intValue();
		BigDecimal valorCurtoPrazo = parcelamento.getValorPrestacao().multiply(new BigDecimal(numeroPrestacoesCurtoPrazo));
		return valorCurtoPrazo;
	}
	
	public BigDecimal getSaldoDevedorContasLongoPrazo() {
		BigDecimal valorLongoPrazo = getSaldoDevedorContas().subtract(getSaldoDevedorContasCurtoPrazo());
		return valorLongoPrazo;
	}

	public BigDecimal getSaldoDevedorAcrescimosCurtoPrazo() {
		int numeroPrestacoesCurtoPrazo = numeroPrestacoesCobradas.intValue() >= 12 ? 12 : numeroPrestacoesCobradas.intValue();
		BigDecimal valorAcrescimosCurtoPrazo = getParcelaAcrescimos().multiply(new BigDecimal(numeroPrestacoesCurtoPrazo));
		return valorAcrescimosCurtoPrazo;
	}
	
	public BigDecimal getSaldoDevedorAcrescimosLongoPrazo() {
		BigDecimal valorAcrescimosLongoPrazo = getSaldoDevedorAcrescimos().subtract(getSaldoDevedorAcrescimosCurtoPrazo());
		return valorAcrescimosLongoPrazo;
	}
	
	private BigDecimal getSaldoDevedorContas() {
		return getValorContasSemEntrada().subtract(getTotalContasCobradas()).add(valorDescontoFaixa);
	}
	
	private BigDecimal getSaldoDevedorAcrescimos() {
		return valorAcrescimos.subtract(getTotalAcrescimosCobrados());
	}
	
	private BigDecimal getValorContasSemEntrada() {
		return valorContas.subtract(valorEntrada);
	}

	private BigDecimal getParcelaSemJuros() {
		return getValorContasSemEntrada().divide(numeroPrestacoes,2,RoundingMode.HALF_DOWN).setScale(2, BigDecimal.ROUND_DOWN);
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