package gcom.arrecadacao.aviso.bean;

import java.math.BigDecimal;



public class ValoresArrecadacaoDevolucaoAvisoBancarioHelper {
	
	private BigDecimal valorArrecadacaoInformado;
	private BigDecimal valorArrecadacaoCalculado;
	private BigDecimal valorDevolucaoInformado;
	private BigDecimal valorDevolucaoCalculado;
	
	
	public ValoresArrecadacaoDevolucaoAvisoBancarioHelper(BigDecimal valorArrecadacaoInformado, 
			BigDecimal valorArrecadacaoCalculado, BigDecimal valorDevolucaoInformado, BigDecimal valorDevolucaoCalculado) {
		this.valorArrecadacaoInformado = valorArrecadacaoInformado;
		this.valorArrecadacaoCalculado = valorArrecadacaoCalculado;
		this.valorDevolucaoInformado = valorDevolucaoInformado;
		this.valorDevolucaoCalculado = valorDevolucaoCalculado;
	}
	/**
	 * @return Retorna o campo valorArrecadacaoCalculado.
	 */
	public BigDecimal getValorArrecadacaoCalculado() {
		return valorArrecadacaoCalculado;
	}
	/**
	 * @param valorArrecadacaoCalculado O valorArrecadacaoCalculado a ser setado.
	 */
	public void setValorArrecadacaoCalculado(BigDecimal valorArrecadacaoCalculado) {
		this.valorArrecadacaoCalculado = valorArrecadacaoCalculado;
	}
	/**
	 * @return Retorna o campo valorArrecadacaoInformado.
	 */
	public BigDecimal getValorArrecadacaoInformado() {
		return valorArrecadacaoInformado;
	}
	/**
	 * @param valorArrecadacaoInformado O valorArrecadacaoInformado a ser setado.
	 */
	public void setValorArrecadacaoInformado(BigDecimal valorArrecadacaoInformado) {
		this.valorArrecadacaoInformado = valorArrecadacaoInformado;
	}
	/**
	 * @return Retorna o campo valorDevolucaoCalculado.
	 */
	public BigDecimal getValorDevolucaoCalculado() {
		return valorDevolucaoCalculado;
	}
	/**
	 * @param valorDevolucaoCalculado O valorDevolucaoCalculado a ser setado.
	 */
	public void setValorDevolucaoCalculado(BigDecimal valorDevolucaoCalculado) {
		this.valorDevolucaoCalculado = valorDevolucaoCalculado;
	}
	/**
	 * @return Retorna o campo valorDevolucaoInformado.
	 */
	public BigDecimal getValorDevolucaoInformado() {
		return valorDevolucaoInformado;
	}
	/**
	 * @param valorDevolucaoInformado O valorDevolucaoInformado a ser setado.
	 */
	public void setValorDevolucaoInformado(BigDecimal valorDevolucaoInformado) {
		this.valorDevolucaoInformado = valorDevolucaoInformado;
	}
	

}
