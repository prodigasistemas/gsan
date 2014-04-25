package gcom.cobranca.bean;

import java.math.BigDecimal;

/**
 * Classe que irá auxiliar no formato do retorno da pesquisa dos avisos bancários de um
 * determinado movimento do arrecadador 
 *
 * @author Raphael Rossiter
 * @date 08/03/2006
 */
public class RelatorioDebitosResumidoHelper {
	
	private Integer idImovel;
	private BigDecimal valorContas;
	private BigDecimal valorGuiasPagamento;
	private BigDecimal valorAcrescimos;
	private BigDecimal valorDebitosACobrar;
	private BigDecimal valorCreditosARealizar;
	
	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo valorAcrescimos.
	 */
	public BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}
	/**
	 * @param valorAcrescimos O valorAcrescimos a ser setado.
	 */
	public void setValorAcrescimos(BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
	}
	/**
	 * @return Retorna o campo valorContas.
	 */
	public BigDecimal getValorContas() {
		return valorContas;
	}
	/**
	 * @param valorContas O valorContas a ser setado.
	 */
	public void setValorContas(BigDecimal valorContas) {
		this.valorContas = valorContas;
	}
	/**
	 * @return Retorna o campo valorCreditosARealizar.
	 */
	public BigDecimal getValorCreditosARealizar() {
		return valorCreditosARealizar;
	}
	/**
	 * @param valorCreditosARealizar O valorCreditosARealizar a ser setado.
	 */
	public void setValorCreditosARealizar(BigDecimal valorCreditosARealizar) {
		this.valorCreditosARealizar = valorCreditosARealizar;
	}
	/**
	 * @return Retorna o campo valorDebitosACobrar.
	 */
	public BigDecimal getValorDebitosACobrar() {
		return valorDebitosACobrar;
	}
	/**
	 * @param valorDebitosACobrar O valorDebitosACobrar a ser setado.
	 */
	public void setValorDebitosACobrar(BigDecimal valorDebitosACobrar) {
		this.valorDebitosACobrar = valorDebitosACobrar;
	}
	/**
	 * @return Retorna o campo valorGuiasPagamento.
	 */
	public BigDecimal getValorGuiasPagamento() {
		return valorGuiasPagamento;
	}
	/**
	 * @param valorGuiasPagamento O valorGuiasPagamento a ser setado.
	 */
	public void setValorGuiasPagamento(BigDecimal valorGuiasPagamento) {
		this.valorGuiasPagamento = valorGuiasPagamento;
	}
	
	
	
	
	
}
