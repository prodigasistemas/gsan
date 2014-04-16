package gcom.arrecadacao.pagamento;

import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;

import java.io.Serializable;
import java.util.Date;

public class PagamentoCartaoDebitoItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private PagamentoCartaoDebito pagamentoCartaoDebito;
	
	private ContaGeral contaGeral;
	
	private GuiaPagamentoGeral guiaPagamentoGeral;
	
	private DebitoACobrarGeral debitoACobrarGeral;
	
	private Date ultimaAlteracao;
	
	public PagamentoCartaoDebitoItem(){}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PagamentoCartaoDebito getPagamentoCartaoDebito() {
		return pagamentoCartaoDebito;
	}

	public void setPagamentoCartaoDebito(PagamentoCartaoDebito pagamentoCartaoDebito) {
		this.pagamentoCartaoDebito = pagamentoCartaoDebito;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
