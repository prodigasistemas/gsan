package gcom.cobranca.bean;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cobranca.parcelamento.Parcelamento;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Guias de Pagamento com os valores:
 * 
 * @author Rafael Santos
 * @since 04/01/2006
 */
public class GuiaPagamentoValoresHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Guia de Pagamento
	 */
	private GuiaPagamento guiaPagamento;
	
	/**
	 * Valor Pago
	 */
	private BigDecimal valorPago;

	/**
	 * Valor da Multa
	 */
	private BigDecimal valorMulta;
	
	/**
	 * Valor Juros Mora
	 */
	private BigDecimal valorJurosMora;
	
	/**
	 * Valor Atualizacao Monetaria
	 */
	private BigDecimal valorAtualizacaoMonetaria;

	/*
	 * [UC1214] Informar Acerto Documentos Não Aceitos
	 * */
	private Short indicadorDebitoPago;

	/**
	 *
	 */
	public GuiaPagamentoValoresHelper() {
	}
	
	/**
	 * @param guiPagamento
	 * @param valorPago
	 * @param valorMulta
	 * @param valorJurosMora
	 * @param valoratualizacaoMonetaria
	 */
	public GuiaPagamentoValoresHelper(GuiaPagamento guiaPagamento, BigDecimal valorPago, BigDecimal valorMulta, BigDecimal valorJurosMora, BigDecimal valorAtualizacaoMonetaria) {
		this.guiaPagamento = guiaPagamento;
		this.valorPago = valorPago;
		this.valorMulta = valorMulta;
		this.valorJurosMora = valorJurosMora;
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	/**
	 * @return Returns the valorJurosMora.
	 */
	public BigDecimal getValorJurosMora() {
		return valorJurosMora;
	}

	/**
	 * @param valorJurosMora The valorJurosMora to set.
	 */
	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	/**
	 * @return Returns the valorMulta.
	 */
	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	/**
	 * @param valorMulta The valorMulta to set.
	 */
	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	/**
	 * @return Returns the valorPago.
	 */
	public BigDecimal getValorPago() {
		return valorPago;
	}

	/**
	 * @param valorPago The valorPago to set.
	 */
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	
	/**
	 * @return Returns the valorAtualizacaoMonetaria.
	 */
	public BigDecimal getValorAtualizacaoMonetaria() {
		return valorAtualizacaoMonetaria;
	}

	/**
	 * @param valorAtualizacaoMonetaria The valorAtualizacaoMonetaria to set.
	 */
	public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	/**
	 * @return Retorna o campo guiaPagamento.
	 */
	public GuiaPagamento getGuiaPagamento() {
		return guiaPagamento;
	}

	/**
	 * @param guiaPagamento O guiaPagamento a ser setado.
	 */
	public void setGuiaPagamento(GuiaPagamento guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

	public Short getIndicadorDebitoPago() {
		return indicadorDebitoPago;
	}
	

	public void setIndicadorDebitoPago(Short indicadorDebitoPago) {
		this.indicadorDebitoPago = indicadorDebitoPago;
	}
	

	/*
     * [UC0214] - Efetuar Parcelamento de Débitos
     * Cálcula o valor dos acrescimos por impontualidade da conta (multa + juros de mora + atualização monetária )
     * @author Roberta Costa
     * @created 03/03/2006
     */
    public BigDecimal getValorAcrescimosImpontualidade(){
		BigDecimal retorno = new BigDecimal("0.00");
		
		// Valor de Multa
		if (this.getValorMulta() != null) {
			retorno = retorno.add(this.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}
		// Valor de JurosMora
		if (this.getValorJurosMora() != null) {
			retorno = retorno.add(this.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}
		// Valor de AtualizacaoMonetaria
		if (this.getValorAtualizacaoMonetaria() != null) {
			retorno = retorno.add(this.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}
		
		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		return retorno ;
	}
    
    @Override
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof GuiaPagamentoValoresHelper)) {
			return false;
		}
		
		GuiaPagamentoValoresHelper castOther = (GuiaPagamentoValoresHelper) other;

		return (this.getGuiaPagamento().getId().equals(castOther.getGuiaPagamento().getId()));
	}
    
}
