package gcom.relatorio.financeiro;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC0824] Gerar Relatorio de Parametros Contábeis
 * 
 * @author Bruno Barros
 * @data 08/07/2008
 */
public class RelatorioParametrosContabeisFaturamentoBean implements RelatorioBean {

	private String tipoLancamento;
	private String itemLancamento;
	private String itemLancamentoContabil;
	private String categoria;
	private String contaContabilDebito;
	private String contaContabilCredito;
	private BigDecimal valorContabilizado;

    public RelatorioParametrosContabeisFaturamentoBean(String tipoLancamento, String itemLancamento, String itemLancamentoContabil, String categoria, String contaContabilDebito, String contaContabilCredito, BigDecimal valorContabilizado) {
		super();
		this.tipoLancamento = tipoLancamento;
		this.itemLancamento = itemLancamento;
		this.itemLancamentoContabil = itemLancamentoContabil;
		this.categoria = categoria;
		this.contaContabilDebito = contaContabilDebito;
		this.contaContabilCredito = contaContabilCredito;
		this.valorContabilizado = valorContabilizado;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getContaContabilCredito() {
		return contaContabilCredito;
	}
	public void setContaContabilCredito(String contaContabilCredito) {
		this.contaContabilCredito = contaContabilCredito;
	}
	public String getContaContabilDebito() {
		return contaContabilDebito;
	}
	public void setContaContabilDebito(String contaContabilDebito) {
		this.contaContabilDebito = contaContabilDebito;
	}
	public String getItemLancamento() {
		return itemLancamento;
	}
	public void setItemLancamento(String itemLancamento) {
		this.itemLancamento = itemLancamento;
	}
	public String getItemLancamentoContabil() {
		return itemLancamentoContabil;
	}
	public void setItemLancamentoContabil(String itemLancamentoContabil) {
		this.itemLancamentoContabil = itemLancamentoContabil;
	}
	public String getTipoLancamento() {
		return tipoLancamento;
	}
	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	public BigDecimal getValorContabilizado() {
		return valorContabilizado;
	}
	public void setValorContabilizado(BigDecimal valorContabilizado) {
		this.valorContabilizado = valorContabilizado;
	}   
}
