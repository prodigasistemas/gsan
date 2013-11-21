package gcom.arrecadacao;

import java.io.Serializable;

public class PagamentoDocumentosNaoAceitosHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	private String idPagamento;
	
	private String tipoDebito;
	
	private String formaArrecadacao;
	
	private String dataPagamento;
	
	private String valorPagamento;
	
	private String numeroNsa;
	
	private String arrecadador;

	public String getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}

	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}

	public String getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(String idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getNumeroNsa() {
		return numeroNsa;
	}

	public void setNumeroNsa(String numeroNsa) {
		this.numeroNsa = numeroNsa;
	}

	public String getTipoDebito() {
		return tipoDebito;
	}

	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	public String getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	
}
