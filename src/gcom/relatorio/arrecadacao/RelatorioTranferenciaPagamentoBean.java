package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

public class RelatorioTranferenciaPagamentoBean implements RelatorioBean{
	
	private String matricula;
	private String tipoDebito;
	private String tipoDocumento;
	private String mesAno;
	private String presParcela;
	private String dataVencimento;
	private String valor;
	private String nomeCliente;
	private String formaArrecadacao;
	private String dataPagamento;
	private BigDecimal valorPagamento;
	private String situacao;
	private String categoria;
	private String arrecadador;
	
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}
	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getPresParcela() {
		return presParcela;
	}
	public void setPresParcela(String presParcela) {
		this.presParcela = presParcela;
	}
	public String getTipoDebito() {
		return tipoDebito;
	}
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getArrecadador() {
		return arrecadador;
	}
	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}
		
}
