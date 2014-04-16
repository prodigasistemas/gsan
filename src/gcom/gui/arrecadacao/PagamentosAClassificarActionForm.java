package gcom.gui.arrecadacao;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

public class PagamentosAClassificarActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String situacaoPagamento;
	
	private String idSituacaoPagamento;
	
	private String referenciaArrecadacao = "";
	
	private Collection<Pagamento> colecaoPagamentosAClassificar;

	private String[] idRegistrosClassificacao;
	
	private boolean exibirMotivoCancelamento;
	
	public Collection<Pagamento> getColecaoPagamentosAClassificar() {
		return colecaoPagamentosAClassificar;
	}

	public void setColecaoPagamentosAClassificar(
			Collection<Pagamento> colecaoPagamentosAClassificar) {
		this.colecaoPagamentosAClassificar = colecaoPagamentosAClassificar;
	}

	public String[] getIdRegistrosClassificacao() {
		return idRegistrosClassificacao;
	}

	public void setIdRegistrosClassificacao(String[] idRegistrosClassificacao) {
		this.idRegistrosClassificacao = idRegistrosClassificacao;
	}
	
	public String getSituacaoPagamento() {
		return situacaoPagamento;
	}
	public void setSituacaoPagamento(String situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}
	public String getReferenciaArrecadacao() {
		return referenciaArrecadacao;
	}
	public void setReferenciaArrecadacao(String referenciaArrecadacao) {
		this.referenciaArrecadacao = referenciaArrecadacao;
	}
	public String getIdSituacaoPagamento() {
		return idSituacaoPagamento;
	}
	public void setIdSituacaoPagamento(String idSituacaoPagamento) {
		this.idSituacaoPagamento = idSituacaoPagamento;
	}
	
	public boolean getExibirMotivoCancelamento() {
		if (idSituacaoPagamento.equals("" + PagamentoSituacao.DOCUMENTO_INEXISTENTE_CONTA_CANCELADA))
			return true;
		else 
			return false;
	}
	
	public Integer getQuantidadePagamentos() {
		return colecaoPagamentosAClassificar.size();
	}
}
