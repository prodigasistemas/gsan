package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;


/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 04/07/2007
 */
public class RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean implements RelatorioBean {

	private String banco;

	private String formaArrecadacao;

	private String nsa;

	private String dataGeracao;

	private Integer qtdeRegistros;
	
	private BigDecimal valor;
	
	private String tarifa;
	
	private BigDecimal valorAPagar;
	
	private String mesAnoArrecadacao;

	/**
	 * Construtor de RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean 
	 * 
	 * @param banco
	 * @param formaArrecadacao
	 * @param nsa
	 * @param dataGeracao
	 * @param qtdeRegistros
	 * @param valor
	 * @param tarifa
	 * @param valorAPagar
	 */
	public RelatorioAcompanhamentoMovimentoArrecadadoresPorNSABean(String banco, String formaArrecadacao, 
			String nsa, String dataGeracao, Integer qtdeRegistros, BigDecimal valor, String tarifa, 
			BigDecimal valorAPagar, String mesAnoArrecadacao) {
		this.banco = banco;
		this.formaArrecadacao = formaArrecadacao;
		this.nsa = nsa;
		this.dataGeracao = dataGeracao;
		this.qtdeRegistros = qtdeRegistros;
		this.valor = valor;
		this.tarifa = tarifa;
		this.valorAPagar = valorAPagar;
		this.mesAnoArrecadacao = mesAnoArrecadacao;
	}

	
	/**
	 * @return Retorna o campo mesAnoArrecadacao.
	 */
	public String getMesAnoArrecadacao() {
		return mesAnoArrecadacao;
	}

	/**
	 * @param mesAnoArrecadacao O mesAnoArrecadacao a ser setado.
	 */
	public void setMesAnoArrecadacao(String mesAnoArrecadacao) {
		this.mesAnoArrecadacao = mesAnoArrecadacao;
	}

	/**
	 * @return Retorna o campo banco.
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco O banco a ser setado.
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return Retorna o campo dataGeracao.
	 */
	public String getDataGeracao() {
		return dataGeracao;
	}

	/**
	 * @param dataGeracao O dataGeracao a ser setado.
	 */
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	/**
	 * @return Retorna o campo formaArrecadacao.
	 */
	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}

	/**
	 * @param formaArrecadacao O formaArrecadacao a ser setado.
	 */
	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}

	/**
	 * @return Retorna o campo nsa.
	 */
	public String getNsa() {
		return nsa;
	}

	/**
	 * @param nsa O nsa a ser setado.
	 */
	public void setNsa(String nsa) {
		this.nsa = nsa;
	}

	/**
	 * @return Retorna o campo qtdeRegistros.
	 */
	public Integer getQtdeRegistros() {
		return qtdeRegistros;
	}


	/**
	 * @param qtdeRegistros O qtdeRegistros a ser setado.
	 */
	public void setQtdeRegistros(Integer qtdeRegistros) {
		this.qtdeRegistros = qtdeRegistros;
	}


	/**
	 * @return Retorna o campo tarifa.
	 */
	public String getTarifa() {
		return tarifa;
	}

	/**
	 * @param tarifa O tarifa a ser setado.
	 */
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 * @return Retorna o campo valor.
	 */
	public BigDecimal getValor() {
		return valor;
	}


	/**
	 * @param valor O valor a ser setado.
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	/**
	 * @return Retorna o campo valorAPagar.
	 */
	public BigDecimal getValorAPagar() {
		return valorAPagar;
	}


	/**
	 * @param valorAPagar O valorAPagar a ser setado.
	 */
	public void setValorAPagar(BigDecimal valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	
	
}
