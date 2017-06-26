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
		this.valorDescontoFaixa = dados[6] == null ? new BigDecimal(0) : (BigDecimal) dados[6];
		this.numeroPrestacoes = (Short) dados[7];
		this.numeroPrestacoesCobradas = (Short) dados[8];
	}

	public BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}
	
	public short getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public short getNumeroPrestacoesCobradas() {
		return numeroPrestacoesCobradas;
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

	private BigDecimal getParcelaSemJuros() {
		return getValorContasSemEntrada().divide(BigDecimal.valueOf(numeroPrestacoes),2,RoundingMode.HALF_DOWN).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getTotalContasCobradas() {
		return getParcelaSemJuros().multiply(BigDecimal.valueOf(numeroPrestacoesCobradas)).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getParcelaAcrescimos() {
		return valorAcrescimos.divide(BigDecimal.valueOf(numeroPrestacoes),2,RoundingMode.HALF_DOWN).setScale(2, BigDecimal.ROUND_DOWN);
	}
	
	public BigDecimal getTotalAcrescimosCobrados() {
		return getParcelaAcrescimos().multiply(BigDecimal.valueOf(numeroPrestacoesCobradas)).setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getParcelaDescontoAcrescimos() {
		return valorDescontoAcrescimos.divide(BigDecimal.valueOf(numeroPrestacoes),2,RoundingMode.HALF_DOWN).setScale(2, BigDecimal.ROUND_DOWN);
	}
}