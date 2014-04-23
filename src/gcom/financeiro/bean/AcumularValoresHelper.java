package gcom.financeiro.bean;

import java.math.BigDecimal;

public class AcumularValoresHelper {

	private Integer idLancamentoItemContabil;
	
	private Integer idLancamentoItem;
	
	private Integer idCategoria;
	
	private BigDecimal valorItemFaturamento;
	
	
	public AcumularValoresHelper(Integer idLancamentoItemContabil, Integer idLancamentoItem, 
			Integer idCategoria, BigDecimal valorItemFaturamento){
		this.idLancamentoItemContabil = idLancamentoItemContabil;
		this.idLancamentoItem = idLancamentoItem;
		this.idCategoria = idCategoria;
		this.valorItemFaturamento = valorItemFaturamento;
	}
	
	public AcumularValoresHelper(){}
	

	public Integer getIdLancamentoItemContabil() {
		return idLancamentoItemContabil;
	}

	public void setIdLancamentoItemContabil(Integer idLancamentoItemContabil) {
		this.idLancamentoItemContabil = idLancamentoItemContabil;
	}

	public BigDecimal getValorItemFaturamento() {
		return valorItemFaturamento;
	}

	public void setValorItemFaturamento(BigDecimal valorItemFaturamento) {
		this.valorItemFaturamento = valorItemFaturamento;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdLancamentoItem() {
		return idLancamentoItem;
	}

	public void setIdLancamentoItem(Integer idLancamentoItem) {
		this.idLancamentoItem = idLancamentoItem;
	}

}
