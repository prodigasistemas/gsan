package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação - Documento Agregador
 * 
 * @author Mariana Victor
 * @date 04/02/2011
 */
public class RelatorioDadosDiariosDocumentoAgregadorBean implements RelatorioBean {

	private String processamentoDefinitivo;
	
	private String mesAno;
	
	private String ultimoProcessamentoAtual;
	
	private String faturamentoCobradoEmConta;
	
	private String documentoAgregador;
	
	private String valor;
	
	private String tipo;
	
	private String quantidadePagamentos;
	
	private String debitos;
	
	private String descontos;
	
	private String valorArrecadado;
	
	private String devolucao;
	
	private String arrecadacaoLiquida;
	
	private String percentualMes;
	
	public RelatorioDadosDiariosDocumentoAgregadorBean() {
		super();
	}

	public RelatorioDadosDiariosDocumentoAgregadorBean(String processamentoDefinitivo, String mesAno, String ultimoProcessamentoAtual, String faturamentoCobradoEmConta, String documentoAgregador, String valor, String tipo, String quantidadePagamentos, String debitos, String descontos, String valorArrecadado, String devolucao, String arrecadacaoLiquida, String percentualMes) {
		super();
		this.processamentoDefinitivo = processamentoDefinitivo;
		this.mesAno = mesAno;
		this.ultimoProcessamentoAtual = ultimoProcessamentoAtual;
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
		this.documentoAgregador = documentoAgregador;
		this.valor = valor;
		this.tipo = tipo;
		this.quantidadePagamentos = quantidadePagamentos;
		this.debitos = debitos;
		this.descontos = descontos;
		this.valorArrecadado = valorArrecadado;
		this.devolucao = devolucao;
		this.arrecadacaoLiquida = arrecadacaoLiquida;
		this.percentualMes = percentualMes;
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

	public String getProcessamentoDefinitivo() {
		return processamentoDefinitivo;
	}

	public void setProcessamentoDefinitivo(String processamentoDefinitivo) {
		this.processamentoDefinitivo = processamentoDefinitivo;
	}

	public String getQuantidadePagamentos() {
		return quantidadePagamentos;
	}

	public void setQuantidadePagamentos(String quantidadePagamentos) {
		this.quantidadePagamentos = quantidadePagamentos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUltimoProcessamentoAtual() {
		return ultimoProcessamentoAtual;
	}

	public void setUltimoProcessamentoAtual(String ultimoProcessamentoAtual) {
		this.ultimoProcessamentoAtual = ultimoProcessamentoAtual;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorArrecadado() {
		return valorArrecadado;
	}

	public void setValorArrecadado(String valorArrecadado) {
		this.valorArrecadado = valorArrecadado;
	}
	
}
