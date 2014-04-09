package gcom.arrecadacao.bean;

import java.math.BigDecimal;
import java.util.Date;

import gcom.relatorio.RelatorioBean;


/**
 * Descrição da classe 
 *
 * @author Ana Maria
 * @date 12/07/2007
 */
public class MovimentoArrecadadoresPorNSAHelper implements RelatorioBean {

	private String banco;

	private String formaArrecadacao;

	private Integer nsa;

	private Date dataGeracao;

	private Integer qtdeRegistros;
	
	private Long valor;
	
	private BigDecimal tarifa;
	

	public MovimentoArrecadadoresPorNSAHelper() {
	}

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
	public MovimentoArrecadadoresPorNSAHelper(String banco, String formaArrecadacao, Integer nsa,
			Date dataGeracao, Integer qtdeRegistros, Long valor, BigDecimal tarifa) {
		this.banco = banco;
		this.formaArrecadacao = formaArrecadacao;
		this.nsa = nsa;
		this.dataGeracao = dataGeracao;
		this.qtdeRegistros = qtdeRegistros;
		this.valor = valor;
		this.tarifa = tarifa;
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
	 * @return Retorna o campo dataGeracao.
	 */
	public Date getDataGeracao() {
		return dataGeracao;
	}

	/**
	 * @param dataGeracao O dataGeracao a ser setado.
	 */
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	/**
	 * @return Retorna o campo nsa.
	 */
	public Integer getNsa() {
		return nsa;
	}

	/**
	 * @param nsa O nsa a ser setado.
	 */
	public void setNsa(Integer nsa) {
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
	public BigDecimal getTarifa() {
		return tarifa;
	}

	/**
	 * @param tarifa O tarifa a ser setado.
	 */
	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 * @return Retorna o campo valor.
	 */
	public Long getValor() {
		return valor;
	}

	/**
	 * @param valor O valor a ser setado.
	 */
	public void setValor(Long valor) {
		this.valor = valor;
	}



	
}
