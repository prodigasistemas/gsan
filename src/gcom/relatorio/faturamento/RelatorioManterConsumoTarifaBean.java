package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;


public class RelatorioManterConsumoTarifaBean implements RelatorioBean {
	
	private String tarifa;
	private String validade;
	private String categoria;
	private String faixa;
	private String custo;
	private String tarifaMinima;

	public RelatorioManterConsumoTarifaBean(String tarifa, String validade,
			String categoria, String faixa, String custo, String tarifaMinima) {
		this.tarifa = tarifa;
		this.validade = validade;
		this.categoria = categoria;
		this.faixa = faixa;
		this.custo = custo;
		this.tarifaMinima = tarifaMinima;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo custo.
	 */
	public String getCusto() {
		return custo;
	}

	/**
	 * @param custo O custo a ser setado.
	 */
	public void setCusto(String custo) {
		this.custo = custo;
	}

	/**
	 * @return Retorna o campo faixa.
	 */
	public String getFaixa() {
		return faixa;
	}

	/**
	 * @param faixa O faixa a ser setado.
	 */
	public void setFaixa(String faixa) {
		this.faixa = faixa;
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
	 * @return Retorna o campo tarifaMinima.
	 */
	public String getTarifaMinima() {
		return tarifaMinima;
	}

	/**
	 * @param tarifaMinima O tarifaMinima a ser setado.
	 */
	public void setTarifaMinima(String tarifaMinima) {
		this.tarifaMinima = tarifaMinima;
	}

	/**
	 * @return Retorna o campo validade.
	 */
	public String getValidade() {
		return validade;
	}

	/**
	 * @param validade O validade a ser setado.
	 */
	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	

}
