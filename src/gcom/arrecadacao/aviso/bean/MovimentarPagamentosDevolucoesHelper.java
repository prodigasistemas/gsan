package gcom.arrecadacao.aviso.bean;

import gcom.util.Util;

import java.math.BigDecimal;



public class MovimentarPagamentosDevolucoesHelper {
	
	private Integer id;
	private String tipoDocumento;
	private String mesAnoReferencia;
	private String valor;
	private String data;
	private String tipoDebito;
	private BigDecimal valorTotal;
	
	public MovimentarPagamentosDevolucoesHelper() {
	}
	/**
	 * @return Retorna o campo data.
	 */

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	/**
	 * @param mesAnoReferencia O mesAnoReferencia a ser setado.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	/**
	 * @return Retorna o campo tipoDebito.
	 */
	public String getTipoDebito() {
		return tipoDebito;
	}
	/**
	 * @param tipoDebito O tipoDebito a ser setado.
	 */
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	/**
	 * @return Retorna o campo tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento O tipoDocumento a ser setado.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return Retorna o campo data.
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data O data a ser setado.
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return Retorna o campo valor.
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * @param valor O valor a ser setado.
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	/**
	 * @return Retorna o campo idAvisoBancario.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param idAvisoBancario O idAvisoBancario a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	} 	
	
	/**
	 * @return Retorna o campo valorTotal.
	 */
	public String getValorTotal() {
		return Util.formatarMoedaReal(valorTotal);
	}
	/**
	 * @param valorTotal O valorTotal a ser setado.
	 */
	public void setValorTotal(BigDecimal valor) {
		if(valorTotal == null || valorTotal.equals("")){
			valorTotal = new BigDecimal("0.00");	
		}
		this.valorTotal = valorTotal.add(valor);
	}
	
	public boolean equals(Object obj) {

		if (!(obj instanceof MovimentarPagamentosDevolucoesHelper)) {
			return false;
		} else {
			MovimentarPagamentosDevolucoesHelper resumoTemp = (MovimentarPagamentosDevolucoesHelper) obj;

			// Verificamos se todas as propriedades que identificam o objeto sao
			// iguais
			return propriedadesIguais(this.id,resumoTemp.id);

		}
	}
	
	/**
	 * Compara duas properiedades inteiras, comparando seus valores para
	 * descobrirmos se sao iguais
	 * 
	 * @param pro1
	 *            Primeira propriedade
	 * @param pro2
	 *            Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais(Integer pro1, Integer pro2) {
		if (pro2 != null) {
			if (!pro2.equals(pro1)) {
				return false;
			}
		} else if (pro1 != null) {
			return false;
		}

		// Se chegou ate aqui quer dizer que as propriedades sao iguais
		return true;
	}
}
