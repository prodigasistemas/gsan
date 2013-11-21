package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação - Forma de Arrecadação
 * 
 * @author Mariana Victor
 * @date 04/02/2011
 */
public class RelatorioDadosDiariosArrecadacaoFormaBean implements RelatorioBean {

	private String processamentoDefinitivo;
	
	private String mesAno;
	
	private String ultimoProcessamentoAtual;
	
	private String faturamentoCobradoEmConta;
	
	private String agente;
	
	private String valor;
	
	private String formaArrecadacao;
	
	private String quantidadeDocumentos;
	
	private String quantidadePagamentos;
	
	private String debitos;
	
	private String descontos;
	
	private String valorArrecadado;
	
	private String devolucao;
	
	private String arrecadacaoLiquida;
	
	private String percentualMes;
	
	public RelatorioDadosDiariosArrecadacaoFormaBean() {
		super();
	}

	public RelatorioDadosDiariosArrecadacaoFormaBean(String processamentoDefinitivo, String mesAno, String ultimoProcessamentoAtual, String faturamentoCobradoEmConta, String agente, String valor, String formaArrecadacao, String quantidadeDocumentos, String quantidadePagamentos, String debitos, String descontos, String valorArrecadado, String devolucao, String arrecadacaoLiquida, String percentualMes) {
		super();
		// TODO Auto-generated constructor stub
		this.processamentoDefinitivo = processamentoDefinitivo;
		this.mesAno = mesAno;
		this.ultimoProcessamentoAtual = ultimoProcessamentoAtual;
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
		this.agente = agente;
		this.valor = valor;
		this.formaArrecadacao = formaArrecadacao;
		this.quantidadeDocumentos = quantidadeDocumentos;
		this.quantidadePagamentos = quantidadePagamentos;
		this.debitos = debitos;
		this.descontos = descontos;
		this.valorArrecadado = valorArrecadado;
		this.devolucao = devolucao;
		this.arrecadacaoLiquida = arrecadacaoLiquida;
		this.percentualMes = percentualMes;
	}

	public String getAgente() {
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
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

	public String getFaturamentoCobradoEmConta() {
		return faturamentoCobradoEmConta;
	}

	public void setFaturamentoCobradoEmConta(String faturamentoCobradoEmConta) {
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
	}

	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}

	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
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

	public String getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	public void setQuantidadeDocumentos(String quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	public String getQuantidadePagamentos() {
		return quantidadePagamentos;
	}

	public void setQuantidadePagamentos(String quantidadePagamentos) {
		this.quantidadePagamentos = quantidadePagamentos;
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
