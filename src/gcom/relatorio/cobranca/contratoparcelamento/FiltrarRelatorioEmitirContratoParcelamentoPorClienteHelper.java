package gcom.relatorio.cobranca.contratoparcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoItem;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;

import java.io.Serializable;
import java.util.Collection;

public class FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private ContratoParcelamento contratoParcelamento;
	
	private String usuarioResponsavel;
	
	private Cliente cliente;
	
	private Collection<PrestacaoContratoParcelamento> colecaoParcelas;
	
	private Collection<ContratoParcelamentoItem> colecaoContaItens;
	
	private Collection<ContratoParcelamentoItem> colecaoItensDebitoACobrar;

	public ContratoParcelamento getContratoParcelamento() {
		return contratoParcelamento;
	}

	public void setContratoParcelamento(ContratoParcelamento contratoParcelamento) {
		this.contratoParcelamento = contratoParcelamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(String usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public Collection<PrestacaoContratoParcelamento> getColecaoParcelas() {
		return colecaoParcelas;
	}

	public void setColecaoParcelas(
			Collection<PrestacaoContratoParcelamento> colecaoParcelas) {
		this.colecaoParcelas = colecaoParcelas;
	}

	public Collection<ContratoParcelamentoItem> getColecaoContaItens() {
		return colecaoContaItens;
	}

	public void setColecaoContaItens(
			Collection<ContratoParcelamentoItem> colecaoContaItens) {
		this.colecaoContaItens = colecaoContaItens;
	}

	public Collection<ContratoParcelamentoItem> getColecaoItensDebitoACobrar() {
		return colecaoItensDebitoACobrar;
	}

	public void setColecaoItensDebitoACobrar(
			Collection<ContratoParcelamentoItem> colecaoItensDebitoACobrar) {
		this.colecaoItensDebitoACobrar = colecaoItensDebitoACobrar;
	}
	
}
