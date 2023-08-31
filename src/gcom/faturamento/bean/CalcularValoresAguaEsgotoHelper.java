package gcom.faturamento.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/** @author Hibernate CodeGenerator */
public class CalcularValoresAguaEsgotoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idCategoria;
	
	private String descricaoCategoria;
	
	private Integer quantidadeEconomiasCategoria;
	
	private Integer idConsumoTarifaCategoria;

	private BigDecimal valorFaturadoAguaCategoria;

	private Integer consumoFaturadoAguaCategoria;
	
	private BigDecimal consumoFaturadoAguaCategoriaResto;

	private BigDecimal valorFaturadoEsgotoCategoria;

	private Integer consumoFaturadoEsgotoCategoria;
	
	private BigDecimal consumoFaturadoEsgotoCategoriaResto;

	private BigDecimal valorTarifaMinimaAguaCategoria;

	private Integer consumoMinimoAguaCategoria;

	private BigDecimal valorTarifaMinimaEsgotoCategoria;

	private Integer consumoMinimoEsgotoCategoria;

	private Collection<CalcularValoresAguaEsgotoFaixaHelper> faixaTarifaConsumo;

	public CalcularValoresAguaEsgotoHelper() {
	}
	
	public CalcularValoresAguaEsgotoHelper(Integer idCategoria, Integer idConsumoTarifaCategoria, BigDecimal valorFaturadoAguaCategoria, Integer consumoFaturadoAguaCategoria, BigDecimal valorFaturadoEsgotoCategoria, Integer consumoFaturadoEsgotoCategoria, BigDecimal valorTarifaMinimaAguaCategoria, Integer consumoMinimoAguaCategoria, BigDecimal valorTarifaMinimaEsgotoCategoria, Integer consumoMinimoEsgotoCategoria, Collection<CalcularValoresAguaEsgotoFaixaHelper> faixaTarifaConsumo, BigDecimal consumoFaturadoAguaCategoriaResto, BigDecimal consumoFaturadoEsgotoCategoriaResto) {
		this.idCategoria = idCategoria;
		this.idConsumoTarifaCategoria = idConsumoTarifaCategoria;
		this.valorFaturadoAguaCategoria = valorFaturadoAguaCategoria;
		this.consumoFaturadoAguaCategoria = consumoFaturadoAguaCategoria;
		this.valorFaturadoEsgotoCategoria = valorFaturadoEsgotoCategoria;
		this.consumoFaturadoEsgotoCategoria = consumoFaturadoEsgotoCategoria;
		this.valorTarifaMinimaAguaCategoria = valorTarifaMinimaAguaCategoria;
		this.consumoMinimoAguaCategoria = consumoMinimoAguaCategoria;
		this.valorTarifaMinimaEsgotoCategoria = valorTarifaMinimaEsgotoCategoria;
		this.consumoMinimoEsgotoCategoria = consumoMinimoEsgotoCategoria;
		this.faixaTarifaConsumo = faixaTarifaConsumo;
		this.consumoFaturadoAguaCategoriaResto = consumoFaturadoAguaCategoriaResto;
		this.consumoFaturadoEsgotoCategoriaResto = consumoFaturadoEsgotoCategoriaResto;
	}

	public Integer getConsumoFaturadoAguaCategoria() {
		return consumoFaturadoAguaCategoria;
	}

	public void setConsumoFaturadoAguaCategoria(Integer consumoFaturadoAguaCategoria) {
		this.consumoFaturadoAguaCategoria = consumoFaturadoAguaCategoria;
	}

	public Integer getConsumoFaturadoEsgotoCategoria() {
		return consumoFaturadoEsgotoCategoria;
	}

	public void setConsumoFaturadoEsgotoCategoria(
			Integer consumoFaturadoEsgotoCategoria) {
		this.consumoFaturadoEsgotoCategoria = consumoFaturadoEsgotoCategoria;
	}

	public Integer getConsumoMinimoAguaCategoria() {
		return consumoMinimoAguaCategoria;
	}

	public void setConsumoMinimoAguaCategoria(Integer consumoMinimoAguaCategoria) {
		this.consumoMinimoAguaCategoria = consumoMinimoAguaCategoria;
	}

	public Integer getConsumoMinimoEsgotoCategoria() {
		return consumoMinimoEsgotoCategoria;
	}

	public void setConsumoMinimoEsgotoCategoria(Integer consumoMinimoEsgotoCategoria) {
		this.consumoMinimoEsgotoCategoria = consumoMinimoEsgotoCategoria;
	}

	public Collection<CalcularValoresAguaEsgotoFaixaHelper> getFaixaTarifaConsumo() {
		return faixaTarifaConsumo;
	}

	public void setFaixaTarifaConsumo(
			Collection<CalcularValoresAguaEsgotoFaixaHelper> faixaTarifaConsumo) {
		this.faixaTarifaConsumo = faixaTarifaConsumo;
	}

	public BigDecimal getValorFaturadoAguaCategoria() {
		return valorFaturadoAguaCategoria;
	}

	public void setValorFaturadoAguaCategoria(BigDecimal valorFaturadoAguaCategoria) {
		this.valorFaturadoAguaCategoria = valorFaturadoAguaCategoria;
	}

	public BigDecimal getValorFaturadoEsgotoCategoria() {
		return valorFaturadoEsgotoCategoria;
	}

	public void setValorFaturadoEsgotoCategoria(
			BigDecimal valorFaturadoEsgotoCategoria) {
		this.valorFaturadoEsgotoCategoria = valorFaturadoEsgotoCategoria;
	}

	public BigDecimal getValorTarifaMinimaAguaCategoria() {
		return valorTarifaMinimaAguaCategoria;
	}

	public void setValorTarifaMinimaAguaCategoria(
			BigDecimal valorTarifaMinimaAguaCategoria) {
		this.valorTarifaMinimaAguaCategoria = valorTarifaMinimaAguaCategoria;
	}

	public BigDecimal getValorTarifaMinimaEsgotoCategoria() {
		return valorTarifaMinimaEsgotoCategoria;
	}

	public void setValorTarifaMinimaEsgotoCategoria(
			BigDecimal valorTarifaMinimaEsgotoCategoria) {
		this.valorTarifaMinimaEsgotoCategoria = valorTarifaMinimaEsgotoCategoria;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdConsumoTarifaCategoria() {
		return idConsumoTarifaCategoria;
	}

	public void setIdConsumoTarifaCategoria(Integer idConsumoTarifaCategoria) {
		this.idConsumoTarifaCategoria = idConsumoTarifaCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	
	public BigDecimal getValorTotalCategoria(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		if (this.valorFaturadoAguaCategoria != null){
			retorno = retorno.add(this.valorFaturadoAguaCategoria);
		}

		if (this.valorFaturadoEsgotoCategoria != null){
			retorno = retorno.add(this.valorFaturadoEsgotoCategoria);
		}
		
		retorno.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return retorno;
	}

	public Integer getQuantidadeEconomiasCategoria() {
		return quantidadeEconomiasCategoria;
	}

	public void setQuantidadeEconomiasCategoria(Integer quantidadeEconomiasCategoria) {
		this.quantidadeEconomiasCategoria = quantidadeEconomiasCategoria;
	}

    public void adicionaRateioAgua(BigDecimal valorRateioAgua) {
        if (valorFaturadoAguaCategoria == null){
            valorFaturadoAguaCategoria = BigDecimal.ZERO;
        }
        if (valorRateioAgua != null){
            this.valorFaturadoAguaCategoria = this.valorFaturadoAguaCategoria.add(valorRateioAgua);
        }
    }

    public void adicionaRateioEsgoto(BigDecimal valorRateioEsgoto) {
        if (valorFaturadoEsgotoCategoria == null){
            valorFaturadoEsgotoCategoria = BigDecimal.ZERO;
        }
        if (valorRateioEsgoto != null){
            this.valorFaturadoEsgotoCategoria = this.valorFaturadoEsgotoCategoria.add(valorRateioEsgoto);
        }
    }

	public BigDecimal getConsumoFaturadoAguaCategoriaResto() {
		return consumoFaturadoAguaCategoriaResto;
	}

	public void setConsumoFaturadoAguaCategoriaResto(BigDecimal consumoFaturadoAguaCategoriaResto) {
		this.consumoFaturadoAguaCategoriaResto = consumoFaturadoAguaCategoriaResto;
	}

	public BigDecimal getConsumoFaturadoEsgotoCategoriaResto() {
		return consumoFaturadoEsgotoCategoriaResto;
	}

	public void setConsumoFaturadoEsgotoCategoriaResto(BigDecimal consumoFaturadoEsgotoCategoriaResto) {
		this.consumoFaturadoEsgotoCategoriaResto = consumoFaturadoEsgotoCategoriaResto;
	}
}
