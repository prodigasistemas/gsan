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

	private short numeroPrestacoes;

	private short numeroPrestacoesCobradas;

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
		this.valorDescontoFaixa = dados[6] == null ? BigDecimal.ZERO : (BigDecimal) dados[6];
		this.numeroPrestacoes = (Short) dados[7];
		this.numeroPrestacoesCobradas = (Short) dados[8];
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public BigDecimal getSaldoDevedorTotal() {
		return getSaldoDevedorContas().add(getSaldoDevedorAcrescimos()).add(getTotalCancelamentoDescontos());
	}

	public BigDecimal getTotalCancelamentoDescontos() {
		return getParcelaDescontoAcrescimos().multiply(BigDecimal.valueOf(numeroPrestacoesCobradas)).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getSaldoDevedorContas() {
		return getValorContasSemEntrada().subtract(getTotalContasCobradas()).add(valorDescontoFaixa);
	}

	public BigDecimal getSaldoDevedorAcrescimos() {
		return valorAcrescimos.subtract(getTotalAcrescimosCobrados());
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

	public BigDecimal getParcelaAcrescimos() {
		return valorAcrescimos.divide(BigDecimal.valueOf(numeroPrestacoes)).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getTotalAcrescimosCobrados() {
		return getParcelaAcrescimos().multiply(BigDecimal.valueOf(numeroPrestacoesCobradas)).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getParcelaDescontoAcrescimos() {
		return valorDescontoAcrescimos.divide(BigDecimal.valueOf(numeroPrestacoes)).setScale(2, BigDecimal.ROUND_DOWN);
	}
}