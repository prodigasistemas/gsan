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
	private String matriculaImovel = "";
	private String idMotivoCancelamento;
	private String dataPagamento;
	private String[] idRegistrosClassificacao;

	private Collection<Pagamento> colecaoPagamentosAClassificar;
	
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

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getIdMotivoCancelamento() {
		return idMotivoCancelamento;
	}

	public void setIdMotivoCancelamento(String idMotivoCancelamento) {
		this.idMotivoCancelamento = idMotivoCancelamento;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
}
