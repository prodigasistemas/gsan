package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação por Documento Agregador
 * 
 * @author Mariana Victor
 * @date 02/02/2011
 */
public class RelatorioDadosDiariosArrecadacaoDocumentoAgregadorBean implements RelatorioBean {

	private String arrecadacaoLiquida;

	private String debitos;

	private String descontos;

	private String devolucao;

	private String documentoAgregador;

	private String percentualMes;

	private String valorArrecadado;

	private String faturamentoCobradoEmConta;

	private String mesAno;

	private String processamentoProvisorio;

	private String tipoProcessamento;

	private String valorTotalPeriodo;

	private String quantidadeDoc;

	private String quantidadePag;

	public RelatorioDadosDiariosArrecadacaoDocumentoAgregadorBean() {
		super();
	}

	public RelatorioDadosDiariosArrecadacaoDocumentoAgregadorBean(String arrecadacaoLiquida, String debitos, String descontos, String devolucao, String documentoAgregador, String percentualMes, String valorArrecadado, String faturamentoCobradoEmConta, String mesAno, String processamentoProvisorio, String tipoProcessamento, String valorTotalPeriodo, String quantidadeDoc, String quantidadePag) {
		super();
		this.arrecadacaoLiquida = arrecadacaoLiquida;
		this.debitos = debitos;
		this.descontos = descontos;
		this.devolucao = devolucao;
		this.documentoAgregador = documentoAgregador;
		this.percentualMes = percentualMes;
		this.valorArrecadado = valorArrecadado;
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
		this.mesAno = mesAno;
		this.processamentoProvisorio = processamentoProvisorio;
		this.tipoProcessamento = tipoProcessamento;
		this.valorTotalPeriodo = valorTotalPeriodo;
		this.quantidadeDoc = quantidadeDoc;
		this.quantidadePag = quantidadePag;
	}

	public String getArrecadacaoLiquida() {
		return arrecadacaoLiquida;
	}

	public void setArrecadacaoLiquida(String arrecadacaoLiquida) {
		this.arrecadacaoLiquida = arrecadacaoLiquida;
	}

	public String getDebitos() {
		return debitos;
	}

	public void setDebitos(String debitos) {
		this.debitos = debitos;
	}

	public String getDescontos() {
		return descontos;
	}

	public void setDescontos(String descontos) {
		this.descontos = descontos;
	}

	public String getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(String devolucao) {
		this.devolucao = devolucao;
	}

	public String getDocumentoAgregador() {
		return documentoAgregador;
	}

	public void setDocumentoAgregador(String documentoAgregador) {
		this.documentoAgregador = documentoAgregador;
	}

	public String getFaturamentoCobradoEmConta() {
		return faturamentoCobradoEmConta;
	}

	public void setFaturamentoCobradoEmConta(String faturamentoCobradoEmConta) {
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getPercentualMes() {
		return percentualMes;
	}

	public void setPercentualMes(String percentualMes) {
		this.percentualMes = percentualMes;
	}

	public String getProcessamentoProvisorio() {
		return processamentoProvisorio;
	}

	public void setProcessamentoProvisorio(String processamentoProvisorio) {
		this.processamentoProvisorio = processamentoProvisorio;
	}

	public String getQuantidadeDoc() {
		return quantidadeDoc;
	}

	public void setQuantidadeDoc(String quantidadeDoc) {
		this.quantidadeDoc = quantidadeDoc;
	}

	public String getQuantidadePag() {
		return quantidadePag;
	}

	public void setQuantidadePag(String quantidadePag) {
		this.quantidadePag = quantidadePag;
	}

	public String getTipoProcessamento() {
		return tipoProcessamento;
	}

	public void setTipoProcessamento(String tipoProcessamento) {
		this.tipoProcessamento = tipoProcessamento;
	}

	public String getValorArrecadado() {
		return valorArrecadado;
	}

	public void setValorArrecadado(String valorArrecadado) {
		this.valorArrecadado = valorArrecadado;
	}

	public String getValorTotalPeriodo() {
		return valorTotalPeriodo;
	}

	public void setValorTotalPeriodo(String valorTotalPeriodo) {
		this.valorTotalPeriodo = valorTotalPeriodo;
	}

}
