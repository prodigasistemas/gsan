package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

public class RelatorioAnalisePagamentoCartaoDebitoBean implements RelatorioBean {
	
	//Dados Linha
	private String id;
	private String matricula;
	private String numeroCartaoDebito;
	private String titularCartaoDebito;
	private String dataConfirmacaoPagamento;
	private String valorConfirmacaoPagamento;
	private String usuarioConfirmacaoPagamento;
	private String situacaoConfirmacaoOperadora;
	private String dataConfirmacaoOperadora;
	private String valorConfirmacaoOperadora;
	//Dados Itens
	private String tipo;
	private String detalhe;
	private String valor;
	
	public RelatorioAnalisePagamentoCartaoDebitoBean(
			String id,String matricula,
			String numeroCartaoDebito, String titularCartaoDebito,
			String dataConfirmacaoPagamento, String valorConfirmacaoPagamento,
			String usuarioConfirmacaoPagamento,
			String situacaoConfirmacaoOperadora,
			String dataConfirmacaoOperadora, String valorConfirmacaoOperadora,
			String tipo) {
		this.id = id;
		this.matricula = matricula;
		this.numeroCartaoDebito = numeroCartaoDebito;
		this.titularCartaoDebito = titularCartaoDebito;
		this.dataConfirmacaoPagamento = dataConfirmacaoPagamento;
		this.valorConfirmacaoPagamento = valorConfirmacaoPagamento;
		this.usuarioConfirmacaoPagamento = usuarioConfirmacaoPagamento;
		this.situacaoConfirmacaoOperadora = situacaoConfirmacaoOperadora;
		this.dataConfirmacaoOperadora = dataConfirmacaoOperadora;
		this.valorConfirmacaoOperadora = valorConfirmacaoOperadora;
		this.tipo = tipo;
	}
	
	public RelatorioAnalisePagamentoCartaoDebitoBean() {
		
	}
	
	public RelatorioAnalisePagamentoCartaoDebitoBean(
			String detalhe, String valor) {
		this.detalhe = detalhe;
		this.valor = valor;
	}



	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNumeroCartaoDebito() {
		return numeroCartaoDebito;
	}
	public void setNumeroCartaoDebito(String numeroCartaoDebito) {
		this.numeroCartaoDebito = numeroCartaoDebito;
	}
	public String getTitularCartaoDebito() {
		return titularCartaoDebito;
	}
	public void setTitularCartaoDebito(String titularCartaoDebito) {
		this.titularCartaoDebito = titularCartaoDebito;
	}
	public String getDataConfirmacaoPagamento() {
		return dataConfirmacaoPagamento;
	}
	public void setDataConfirmacaoPagamento(String dataConfirmacaoPagamento) {
		this.dataConfirmacaoPagamento = dataConfirmacaoPagamento;
	}
	public String getValorConfirmacaoPagamento() {
		return valorConfirmacaoPagamento;
	}
	public void setValorConfirmacaoPagamento(String valorConfirmacaoPagamento) {
		this.valorConfirmacaoPagamento = valorConfirmacaoPagamento;
	}
	public String getUsuarioConfirmacaoPagamento() {
		return usuarioConfirmacaoPagamento;
	}
	public void setUsuarioConfirmacaoPagamento(String usuarioConfirmacaoPagamento) {
		this.usuarioConfirmacaoPagamento = usuarioConfirmacaoPagamento;
	}
	public String getSituacaoConfirmacaoOperadora() {
		return situacaoConfirmacaoOperadora;
	}
	public void setSituacaoConfirmacaoOperadora(String situacaoConfirmacaoOperadora) {
		this.situacaoConfirmacaoOperadora = situacaoConfirmacaoOperadora;
	}
	public String getDataConfirmacaoOperadora() {
		return dataConfirmacaoOperadora;
	}
	public void setDataConfirmacaoOperadora(String dataConfirmacaoOperadora) {
		this.dataConfirmacaoOperadora = dataConfirmacaoOperadora;
	}
	public String getValorConfirmacaoOperadora() {
		return valorConfirmacaoOperadora;
	}
	public void setValorConfirmacaoOperadora(String valorConfirmacaoOperadora) {
		this.valorConfirmacaoOperadora = valorConfirmacaoOperadora;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
