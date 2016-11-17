package gcom.cobranca.bean;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.conta.Conta;

import java.io.Serializable;
import java.math.BigDecimal;

public class ContaValoresHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Conta conta;

	private BigDecimal valorPago;

	private BigDecimal valorMulta;

	private BigDecimal valorJurosMora;

	private BigDecimal valorAtualizacaoMonetaria;

	private Integer indicadorContasDebito;

	private Short existeParcelamento;

	private Integer revisao;

	private BigDecimal valorCreditoConta;

	private BigDecimal valorAtualConta;

	private Short indicadorDebitoPago;

	public ContaValoresHelper() {
		this.revisao = 2;
	}

	public ContaValoresHelper(Conta conta, BigDecimal valorPago, BigDecimal valorMulta, BigDecimal valorJurosMora, BigDecimal valoratualizacaoMonetaria) {
		this.conta = conta;
		this.valorPago = valorPago;
		this.valorMulta = valorMulta;
		this.valorJurosMora = valorJurosMora;
		this.valorAtualizacaoMonetaria = valoratualizacaoMonetaria;
		this.revisao = 2;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public BigDecimal getValorAtualizacaoMonetaria() {
		return valorAtualizacaoMonetaria;
	}

	public void setValorAtualizacaoMonetaria(BigDecimal valoratualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valoratualizacaoMonetaria;
	}

	public BigDecimal getValorJurosMora() {
		return valorJurosMora;
	}

	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Integer getIndicadorContasDebito() {
		return indicadorContasDebito;
	}

	public void setIndicadorContasDebito(Integer indicadorContasDebito) {
		this.indicadorContasDebito = indicadorContasDebito;
	}

	public Short getExisteParcelamento() {
		return existeParcelamento;
	}

	public void setExisteParcelamento(Short existeParcelamento) {
		this.existeParcelamento = existeParcelamento;
	}

	public Integer getRevisao() {
		return revisao;
	}

	public void setRevisao(Integer revisao) {
		this.revisao = revisao;
	}

	public BigDecimal getValorTotalContaValores() {

		BigDecimal retorno = new BigDecimal("0.00");

		if (this.getValorMulta() != null) {
			retorno = retorno.add(this.getValorMulta());
		}

		if (this.getValorJurosMora() != null) {
			retorno = retorno.add(this.getValorJurosMora());
		}

		if (this.getValorAtualizacaoMonetaria() != null) {
			retorno = retorno.add(this.getValorAtualizacaoMonetaria());
		}

		return retorno.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getValorTotalConta() {
		BigDecimal retorno = new BigDecimal("0.00");

		if (this.getConta().getValorAgua() != null) {
			retorno = retorno.add(this.getConta().getValorAgua());
		}

		if (this.getConta().getValorEsgoto() != null) {
			retorno = retorno.add(this.getConta().getValorEsgoto());
		}

		if (this.getConta().getDebitos() != null) {
			retorno = retorno.add(this.getConta().getDebitos());
		}

		if (this.getConta().getValorCreditos() != null) {
			retorno = retorno.subtract(this.getConta().getValorCreditos());
		}

		return retorno.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getValorTotalComValorAtualizacaoMonetaria() {
		BigDecimal valorTotalConta = new BigDecimal("0.00");

		if (this.getConta().getValorAgua() != null) {
			valorTotalConta = valorTotalConta.add(this.getConta().getValorAgua());
		}

		if (this.getConta().getValorEsgoto() != null) {
			valorTotalConta = valorTotalConta.add(this.getConta().getValorEsgoto());
		}

		if (this.getConta().getDebitos() != null) {
			valorTotalConta = valorTotalConta.add(this.getConta().getDebitos());
		}

		if (this.getConta().getValorCreditos() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getConta().getValorCreditos());
		}

		if (this.getValorMulta() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorMulta());
		}

		if (this.getValorJurosMora() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorJurosMora());
		}

		if (this.getValorAtualizacaoMonetaria() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorAtualizacaoMonetaria());
		}

		return valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getFormatarAnoMesParaMesAno() {
		String anoMesRecebido = "" + this.getConta().getReferencia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}

	public BigDecimal getValorTotalContaValoresParcelamento() {
		BigDecimal retorno = new BigDecimal("0.00");

		if (this.getValorMulta() != null) {
			retorno = retorno.add(this.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}

		if (this.getValorJurosMora() != null) {
			retorno = retorno.add(this.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}

		if (this.getValorAtualizacaoMonetaria() != null) {
			retorno = retorno.add(this.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}

		return retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}

		if (!(other instanceof ContaValoresHelper)) {
			return false;
		}

		ContaValoresHelper castOther = (ContaValoresHelper) other;

		return (this.getConta().getId().equals(castOther.getConta().getId()));
	}

	public BigDecimal getValorAtualConta() {
		return valorAtualConta;
	}

	public void setValorAtualConta(BigDecimal valorAtualConta) {
		this.valorAtualConta = valorAtualConta;
	}

	public BigDecimal getValorCreditoConta() {
		return valorCreditoConta;
	}

	public void setValorCreditoConta(BigDecimal valorCreditoConta) {
		this.valorCreditoConta = valorCreditoConta;
	}

	public Short getIndicadorDebitoPago() {
		return indicadorDebitoPago;
	}

	public void setIndicadorDebitoPago(Short indicadorDebitoPago) {
		this.indicadorDebitoPago = indicadorDebitoPago;
	}
	
	public boolean isContaEP() {
		return this.indicadorContasDebito.equals(new Integer("1"));
	}
	
	public boolean isContaNB() {
		return this.indicadorContasDebito.equals(new Integer("2"));
	}
}
