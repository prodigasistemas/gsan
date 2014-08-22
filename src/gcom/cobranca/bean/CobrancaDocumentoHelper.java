package gcom.cobranca.bean;

import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;

import java.io.Serializable;
import java.util.Collection;

public class CobrancaDocumentoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private CobrancaDocumento cobrancaDocumento;
	private Integer quantidadeItensCobrancaDocumento;
	
	private Integer idOrdemServico;
	
	private String situacaoOrdemServico;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItem;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemConta;
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitoACobrar;
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar;
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiaPagamento;
	
	public CobrancaDocumentoHelper() {
		
	}
	
	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItem() {
		return colecaoCobrancaDocumentoItem;
	}

	public void setColecaoCobrancaDocumentoItem(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItem) {
		this.colecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItem;
	}
		
	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}
	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}
	public Integer getQuantidadeItensCobrancaDocumento() {
		return quantidadeItensCobrancaDocumento;
	}
	public void setQuantidadeItensCobrancaDocumento(
			Integer quantidadeItensCobrancaDocumento) {
		this.quantidadeItensCobrancaDocumento = quantidadeItensCobrancaDocumento;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemConta() {
		return colecaoCobrancaDocumentoItemConta;
	}

	public void setColecaoCobrancaDocumentoItemConta(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemConta) {
		this.colecaoCobrancaDocumentoItemConta = colecaoCobrancaDocumentoItemConta;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemDebitoACobrar() {
		return colecaoCobrancaDocumentoItemDebitoACobrar;
	}

	public void setColecaoCobrancaDocumentoItemDebitoACobrar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitoACobrar) {
		this.colecaoCobrancaDocumentoItemDebitoACobrar = colecaoCobrancaDocumentoItemDebitoACobrar;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemCreditoARealizar() {
		return colecaoCobrancaDocumentoItemCreditoARealizar;
	}

	public void setColecaoCobrancaDocumentoItemCreditoARealizar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar) {
		this.colecaoCobrancaDocumentoItemCreditoARealizar = colecaoCobrancaDocumentoItemCreditoARealizar;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemGuiaPagamento() {
		return colecaoCobrancaDocumentoItemGuiaPagamento;
	}

	public void setColecaoCobrancaDocumentoItemGuiaPagamento(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiaPagamento) {
		this.colecaoCobrancaDocumentoItemGuiaPagamento = colecaoCobrancaDocumentoItemGuiaPagamento;
	}

	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}

	public void setSituacaoOrdemServico(String situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}
}
