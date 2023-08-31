package gcom.faturamento.bean;

import java.math.BigDecimal;

public class CalcularValoresAguaEsgotoFaixaHelper {
	
	private Integer idConsumoTarifaFaixa;

	private Integer consumoFaturadoAguaFaixa;
	
	private BigDecimal consumoFaturadoAguaFaixaResto;

	private BigDecimal valorFaturadoAguaFaixa;

	private Integer consumoFaturadoEsgotoFaixa;
	
	private BigDecimal consumoFaturadoEsgotoFaixaResto;

	private BigDecimal valorFaturadoEsgotoFaixa;

	private Integer limiteInicialConsumoFaixa;

	private Integer limiteFinalConsumoFaixa;

	private BigDecimal valorTarifaFaixa;

	public CalcularValoresAguaEsgotoFaixaHelper() {
	}

	public CalcularValoresAguaEsgotoFaixaHelper(
			Integer idConsumoTarifaFaixa,
			Integer consumoFaturadoAguaFaixa,
			BigDecimal valorFaturadoAguaFaixa,
			Integer consumoFaturadoEsgotoFaixa,
			BigDecimal valorFaturadoEsgotoFaixa,
			Integer limiteInicialConsumoFaixa, Integer limiteFinalConsumoFaixa,
			BigDecimal valorTarifaFaixa,
			BigDecimal consumoFaturadoAguaFaixaResto,
			BigDecimal consumoFaturadoEsgotoFaixaResto) {
		this.idConsumoTarifaFaixa = idConsumoTarifaFaixa; 
		this.consumoFaturadoAguaFaixa = consumoFaturadoAguaFaixa;
		this.valorFaturadoAguaFaixa = valorFaturadoAguaFaixa;
		this.consumoFaturadoEsgotoFaixa = consumoFaturadoEsgotoFaixa;
		this.valorFaturadoEsgotoFaixa = valorFaturadoEsgotoFaixa;
		this.limiteInicialConsumoFaixa = limiteInicialConsumoFaixa;
		this.limiteFinalConsumoFaixa = limiteFinalConsumoFaixa;
		this.valorTarifaFaixa = valorTarifaFaixa;
		this.consumoFaturadoAguaFaixaResto = consumoFaturadoAguaFaixaResto;
		this.consumoFaturadoEsgotoFaixaResto = consumoFaturadoEsgotoFaixaResto;
	}

	public Integer getConsumoFaturadoAguaFaixa() {
		return consumoFaturadoAguaFaixa;
	}

	public void setConsumoFaturadoAguaFaixa(Integer consumoFaturadoAguaFaixa) {
		this.consumoFaturadoAguaFaixa = consumoFaturadoAguaFaixa;
	}

	public Integer getConsumoFaturadoEsgotoFaixa() {
		return consumoFaturadoEsgotoFaixa;
	}

	public void setConsumoFaturadoEsgotoFaixa(Integer consumoFaturadoEsgotoFaixa) {
		this.consumoFaturadoEsgotoFaixa = consumoFaturadoEsgotoFaixa;
	}

	public Integer getLimiteFinalConsumoFaixa() {
		return limiteFinalConsumoFaixa;
	}

	public void setLimiteFinalConsumoFaixa(Integer limiteFinalConsumoFaixa) {
		this.limiteFinalConsumoFaixa = limiteFinalConsumoFaixa;
	}

	public Integer getLimiteInicialConsumoFaixa() {
		return limiteInicialConsumoFaixa;
	}

	public void setLimiteInicialConsumoFaixa(Integer limiteInicialConsumoFaixa) {
		this.limiteInicialConsumoFaixa = limiteInicialConsumoFaixa;
	}

	public BigDecimal getValorFaturadoAguaFaixa() {
		return valorFaturadoAguaFaixa;
	}

	public void setValorFaturadoAguaFaixa(BigDecimal valorFaturadoAguaFaixa) {
		this.valorFaturadoAguaFaixa = valorFaturadoAguaFaixa;
	}

	public BigDecimal getValorFaturadoEsgotoFaixa() {
		return valorFaturadoEsgotoFaixa;
	}

	public void setValorFaturadoEsgotoFaixa(BigDecimal valorFaturadoEsgotoFaixa) {
		this.valorFaturadoEsgotoFaixa = valorFaturadoEsgotoFaixa;
	}

	public BigDecimal getValorTarifaFaixa() {
		return valorTarifaFaixa;
	}

	public void setValorTarifaFaixa(BigDecimal valorTarifaFaixa) {
		this.valorTarifaFaixa = valorTarifaFaixa;
	}

	public Integer getIdConsumoTarifaFaixa() {
		return idConsumoTarifaFaixa;
	}

	public void setIdConsumoTarifaFaixa(Integer idConsumoTarifaFaixa) {
		this.idConsumoTarifaFaixa = idConsumoTarifaFaixa;
	}

	public BigDecimal getConsumoFaturadoAguaFaixaResto() {
		return consumoFaturadoAguaFaixaResto;
	}

	public void setConsumoFaturadoAguaFaixaResto(BigDecimal consumoFaturadoAguaFaixaResto) {
		this.consumoFaturadoAguaFaixaResto = consumoFaturadoAguaFaixaResto;
	}

	public BigDecimal getConsumoFaturadoEsgotoFaixaResto() {
		return consumoFaturadoEsgotoFaixaResto;
	}

	public void setConsumoFaturadoEsgotoFaixaResto(BigDecimal consumoFaturadoEsgotoFaixaResto) {
		this.consumoFaturadoEsgotoFaixaResto = consumoFaturadoEsgotoFaixaResto;
	}

}
