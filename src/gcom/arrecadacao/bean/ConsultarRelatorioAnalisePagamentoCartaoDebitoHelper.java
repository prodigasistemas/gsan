package gcom.arrecadacao.bean;

import java.io.Serializable;

public class ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dataConfirmacaoPagamentoInicial;
	private String dataConfirmacaoPagamentoFinal;
	private String idUsuarioConfirmacao;
	private String indicadorConfirmacaoOperadora;
	private String dataConfirmacaoOperadoraInicial;
	private String dataConfirmacaoOperadoraFinal;
	
	
	
	public ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper() {
	}
	
	
	public ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper(
			String dataConfirmacaoPagamentoInicial,
			String dataConfirmacaoPagamentoFinal, String idUsuarioConfirmacao,
			String indicadorConfirmacaoOperadora,
			String dataConfirmacaoOperadoraInicial,
			String dataConfirmacaoOperadoraFinal) {
		this.dataConfirmacaoPagamentoInicial = dataConfirmacaoPagamentoInicial;
		this.dataConfirmacaoPagamentoFinal = dataConfirmacaoPagamentoFinal;
		this.idUsuarioConfirmacao = idUsuarioConfirmacao;
		this.indicadorConfirmacaoOperadora = indicadorConfirmacaoOperadora;
		this.dataConfirmacaoOperadoraInicial = dataConfirmacaoOperadoraInicial;
		this.dataConfirmacaoOperadoraFinal = dataConfirmacaoOperadoraFinal;
	}
	public String getDataConfirmacaoPagamentoInicial() {
		return dataConfirmacaoPagamentoInicial;
	}
	public void setDataConfirmacaoPagamentoInicial(
			String dataConfirmacaoPagamentoInicial) {
		this.dataConfirmacaoPagamentoInicial = dataConfirmacaoPagamentoInicial;
	}
	public String getDataConfirmacaoPagamentoFinal() {
		return dataConfirmacaoPagamentoFinal;
	}
	public void setDataConfirmacaoPagamentoFinal(
			String dataConfirmacaoPagamentoFinal) {
		this.dataConfirmacaoPagamentoFinal = dataConfirmacaoPagamentoFinal;
	}
	public String getIdUsuarioConfirmacao() {
		return idUsuarioConfirmacao;
	}
	public void setIdUsuarioConfirmacao(String idUsuarioConfirmacao) {
		this.idUsuarioConfirmacao = idUsuarioConfirmacao;
	}
	public String getIndicadorConfirmacaoOperadora() {
		return indicadorConfirmacaoOperadora;
	}
	public void setIndicadorConfirmacaoOperadora(
			String indicadorConfirmacaoOperadora) {
		this.indicadorConfirmacaoOperadora = indicadorConfirmacaoOperadora;
	}
	public String getDataConfirmacaoOperadoraInicial() {
		return dataConfirmacaoOperadoraInicial;
	}
	public void setDataConfirmacaoOperadoraInicial(
			String dataConfirmacaoOperadoraInicial) {
		this.dataConfirmacaoOperadoraInicial = dataConfirmacaoOperadoraInicial;
	}
	public String getDataConfirmacaoOperadoraFinal() {
		return dataConfirmacaoOperadoraFinal;
	}
	public void setDataConfirmacaoOperadoraFinal(
			String dataConfirmacaoOperadoraFinal) {
		this.dataConfirmacaoOperadoraFinal = dataConfirmacaoOperadoraFinal;
	}
	
	
	
	
}
