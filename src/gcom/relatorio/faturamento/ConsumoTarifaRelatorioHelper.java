package gcom.relatorio.faturamento;

import java.math.BigDecimal;
import java.util.Date;

public class ConsumoTarifaRelatorioHelper {
	
	private Integer idConsumoTarifa;
	private String descricaoConsumoTarifa;
	private Date dataValidadeInicial;
	private String categoria;
	private Integer faixaInicial;
	private Integer faixaFinal;
	private BigDecimal custo;
	private BigDecimal tarifaMinima;
	private Integer consumoMinimo;
	
	/**
	 * @return Retorna o campo consumoMinimo.
	 */
	public Integer getConsumoMinimo() {
		return consumoMinimo;
	}
	/**
	 * @param consumoMinimo O consumoMinimo a ser setado.
	 */
	public void setConsumoMinimo(Integer consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
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
	public BigDecimal getCusto() {
		return custo;
	}
	/**
	 * @param custo O custo a ser setado.
	 */
	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}
	/**
	 * @return Retorna o campo dataValidadeInicial.
	 */
	public Date getDataValidadeInicial() {
		return dataValidadeInicial;
	}
	/**
	 * @param dataValidadeInicial O dataValidadeInicial a ser setado.
	 */
	public void setDataValidadeInicial(Date dataValidadeInicial) {
		this.dataValidadeInicial = dataValidadeInicial;
	}
	/**
	 * @return Retorna o campo descricaoConsumoTarifa.
	 */
	public String getDescricaoConsumoTarifa() {
		return descricaoConsumoTarifa;
	}
	/**
	 * @param descricaoConsumoTarifa O descricaoConsumoTarifa a ser setado.
	 */
	public void setDescricaoConsumoTarifa(String descricaoConsumoTarifa) {
		this.descricaoConsumoTarifa = descricaoConsumoTarifa;
	}
	/**
	 * @return Retorna o campo faixaFinal.
	 */
	public Integer getFaixaFinal() {
		return faixaFinal;
	}
	/**
	 * @param faixaFinal O faixaFinal a ser setado.
	 */
	public void setFaixaFinal(Integer faixaFinal) {
		this.faixaFinal = faixaFinal;
	}
	/**
	 * @return Retorna o campo faixaInicial.
	 */
	public Integer getFaixaInicial() {
		return faixaInicial;
	}
	/**
	 * @param faixaInicial O faixaInicial a ser setado.
	 */
	public void setFaixaInicial(Integer faixaInicial) {
		this.faixaInicial = faixaInicial;
	}
	/**
	 * @return Retorna o campo idConsumoTarifa.
	 */
	public Integer getIdConsumoTarifa() {
		return idConsumoTarifa;
	}
	/**
	 * @param idConsumoTarifa O idConsumoTarifa a ser setado.
	 */
	public void setIdConsumoTarifa(Integer idConsumoTarifa) {
		this.idConsumoTarifa = idConsumoTarifa;
	}
	/**
	 * @return Retorna o campo tarifaMinima.
	 */
	public BigDecimal getTarifaMinima() {
		return tarifaMinima;
	}
	/**
	 * @param tarifaMinima O tarifaMinima a ser setado.
	 */
	public void setTarifaMinima(BigDecimal tarifaMinima) {
		this.tarifaMinima = tarifaMinima;
	}
	
	

}
