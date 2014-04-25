package gcom.cadastro.imovel.bean;

import java.math.BigDecimal;
import java.util.Collection;


/**
 * Débitos de um imóvel ou de um cliente
 * @author Rafael Santos
 * @since 04/01/2006
 */
public class GerarRelacaoDebitosHelper {
	
	private GerarRelacaoDebitosImovelHelper gerarRelacaoDebitosImovelHelper;
	
	private Collection colecaoDebitosACobrarCreditoARealizar;
	
	private Collection colecaoContas;
	
	private Collection colecaoGuiasPagamento;
	
	private BigDecimal totalContas;
	
	private BigDecimal totalDebitosACobrar;
	
	private BigDecimal totalGuiasPagamento;
	
	private BigDecimal totalMulta;
	
	private BigDecimal totalJuros;
	
	private BigDecimal totalAtualizacaoMonetaria;
	
	private BigDecimal totalGeralAtualizado;

	private BigDecimal totalContaAtualizado;
	
	private BigDecimal totalCreditoARealizar;
	
	/**
	 * @return Retorna o campo colecaoContas.
	 */
	public Collection getColecaoContas() {
		return colecaoContas;
	}

	/**
	 * @param colecaoContas O colecaoContas a ser setado.
	 */
	public void setColecaoContas(Collection colecaoContas) {
		this.colecaoContas = colecaoContas;
	}

	/**
	 * @return Retorna o campo colecaoDebitosACobrarCreditoARealizar.
	 */
	public Collection getColecaoDebitosACobrarCreditoARealizar() {
		return colecaoDebitosACobrarCreditoARealizar;
	}

	/**
	 * @param colecaoDebitosACobrarCreditoARealizar O colecaoDebitosACobrarCreditoARealizar a ser setado.
	 */
	public void setColecaoDebitosACobrarCreditoARealizar(
			Collection colecaoDebitosACobrarCreditoARealizar) {
		this.colecaoDebitosACobrarCreditoARealizar = colecaoDebitosACobrarCreditoARealizar;
	}

	/**
	 * @return Retorna o campo colecaoGuiasPagamento.
	 */
	public Collection getColecaoGuiasPagamento() {
		return colecaoGuiasPagamento;
	}

	/**
	 * @param colecaoGuiasPagamento O colecaoGuiasPagamento a ser setado.
	 */
	public void setColecaoGuiasPagamento(Collection colecaoGuiasPagamento) {
		this.colecaoGuiasPagamento = colecaoGuiasPagamento;
	}

	/**
	 * @return Retorna o campo gerarRelacaoDebitosImovelHelper.
	 */
	public GerarRelacaoDebitosImovelHelper getGerarRelacaoDebitosImovelHelper() {
		return gerarRelacaoDebitosImovelHelper;
	}

	/**
	 * @param gerarRelacaoDebitosImovelHelper O gerarRelacaoDebitosImovelHelper a ser setado.
	 */
	public void setGerarRelacaoDebitosImovelHelper(
			GerarRelacaoDebitosImovelHelper gerarRelacaoDebitosImovelHelper) {
		this.gerarRelacaoDebitosImovelHelper = gerarRelacaoDebitosImovelHelper;
	}

	/**
	 * @return Retorna o campo totalAtualizacaoMonetaria.
	 */
	public BigDecimal getTotalAtualizacaoMonetaria() {
		return totalAtualizacaoMonetaria;
	}

	/**
	 * @param totalAtualizacaoMonetaria O totalAtualizacaoMonetaria a ser setado.
	 */
	public void setTotalAtualizacaoMonetaria(BigDecimal totalAtualizacaoMonetaria) {
		this.totalAtualizacaoMonetaria = totalAtualizacaoMonetaria;
	}

	/**
	 * @return Retorna o campo totalContaAtualizado.
	 */
	public BigDecimal getTotalContaAtualizado() {
		return totalContaAtualizado;
	}

	/**
	 * @param totalContaAtualizado O totalContaAtualizado a ser setado.
	 */
	public void setTotalContaAtualizado(BigDecimal totalContaAtualizado) {
		this.totalContaAtualizado = totalContaAtualizado;
	}

	/**
	 * @return Retorna o campo totalContas.
	 */
	public BigDecimal getTotalContas() {
		return totalContas;
	}

	/**
	 * @param totalContas O totalContas a ser setado.
	 */
	public void setTotalContas(BigDecimal totalContas) {
		this.totalContas = totalContas;
	}

	/**
	 * @return Retorna o campo totalCreditoARealizar.
	 */
	public BigDecimal getTotalCreditoARealizar() {
		return totalCreditoARealizar;
	}

	/**
	 * @param totalCreditoARealizar O totalCreditoARealizar a ser setado.
	 */
	public void setTotalCreditoARealizar(BigDecimal totalCreditoARealizar) {
		this.totalCreditoARealizar = totalCreditoARealizar;
	}

	/**
	 * @return Retorna o campo totalDebitosACobrar.
	 */
	public BigDecimal getTotalDebitosACobrar() {
		return totalDebitosACobrar;
	}

	/**
	 * @param totalDebitosACobrar O totalDebitosACobrar a ser setado.
	 */
	public void setTotalDebitosACobrar(BigDecimal totalDebitosACobrar) {
		this.totalDebitosACobrar = totalDebitosACobrar;
	}

	/**
	 * @return Retorna o campo totalGeralAtualizado.
	 */
	public BigDecimal getTotalGeralAtualizado() {
		return totalGeralAtualizado;
	}

	/**
	 * @param totalGeralAtualizado O totalGeralAtualizado a ser setado.
	 */
	public void setTotalGeralAtualizado(BigDecimal totalGeralAtualizado) {
		this.totalGeralAtualizado = totalGeralAtualizado;
	}

	/**
	 * @return Retorna o campo totalGuiasPagamento.
	 */
	public BigDecimal getTotalGuiasPagamento() {
		return totalGuiasPagamento;
	}

	/**
	 * @param totalGuiasPagamento O totalGuiasPagamento a ser setado.
	 */
	public void setTotalGuiasPagamento(BigDecimal totalGuiasPagamento) {
		this.totalGuiasPagamento = totalGuiasPagamento;
	}

	/**
	 * @return Retorna o campo totalJuros.
	 */
	public BigDecimal getTotalJuros() {
		return totalJuros;
	}

	/**
	 * @param totalJuros O totalJuros a ser setado.
	 */
	public void setTotalJuros(BigDecimal totalJuros) {
		this.totalJuros = totalJuros;
	}

	/**
	 * @return Retorna o campo totalMulta.
	 */
	public BigDecimal getTotalMulta() {
		return totalMulta;
	}

	/**
	 * @param totalMulta O totalMulta a ser setado.
	 */
	public void setTotalMulta(BigDecimal totalMulta) {
		this.totalMulta = totalMulta;
	}
	
}
