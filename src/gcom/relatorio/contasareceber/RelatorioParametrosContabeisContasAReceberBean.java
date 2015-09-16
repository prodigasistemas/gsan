package gcom.relatorio.contasareceber;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioParametrosContabeisContasAReceberBean implements RelatorioBean {

	private String tipoLancamento;
	private String itemLancamento;
	private String descricaoCategoria;
	private String numeroConta;
	private BigDecimal valorItemLancamento;
	
	public RelatorioParametrosContabeisContasAReceberBean(String tipoLancamento, String itemLancamento, String descricaoCategoria, String numeroConta, BigDecimal valorItemLancamento) {
		super();
		this.tipoLancamento = tipoLancamento;
		this.itemLancamento = itemLancamento;
		this.descricaoCategoria = descricaoCategoria;
		this.numeroConta = numeroConta;
		this.valorItemLancamento = valorItemLancamento;
	}

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public String getItemLancamento() {
		return itemLancamento;
	}

	public void setItemLancamento(String itemLancamento) {
		this.itemLancamento = itemLancamento;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getValorItemLancamento() {
		return valorItemLancamento;
	}

	public void setValorItemLancamento(BigDecimal valorItemLancamento) {
		this.valorItemLancamento = valorItemLancamento;
	}

}
