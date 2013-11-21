package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação por Categoria
 * 
 * @author Mariana Victor
 * @date 02/02/2011
 */
public class RelatorioDadosDiariosArrecadacaoCategoriaBean implements RelatorioBean {

	private String tipoProcessamento;
	
	private String processamentoProvisorio;
	
	private String faturamentoCobradoEmConta;
	
	private String mesAno;
	
	private String valor;
	
	private String percentual;

	private String categoria;
	
	private String debitos;
	
	private String descontos;
	
	private String valorArrecadado;
	
	private String devolucao;
	
	private String arrecadacaoLiquida;
	
	private String percentualMes;

	private String valorTotalPeriodo;
	
	
	public RelatorioDadosDiariosArrecadacaoCategoriaBean() {
		super();
	}

	public RelatorioDadosDiariosArrecadacaoCategoriaBean(String tipoProcessamento, String processamentoProvisorio, String faturamentoCobradoEmConta, String mesAno, String valor, String percentual, String categoria, String debitos, String descontos, String valorArrecadado, String devolucao, String arrecadacaoLiquida, String percentualMes, String valorTotalPeriodo) {
		super();
		this.tipoProcessamento = tipoProcessamento;
		this.processamentoProvisorio = processamentoProvisorio;
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
		this.mesAno = mesAno;
		this.valor = valor;
		this.percentual = percentual;
		this.categoria = categoria;
		this.debitos = debitos;
		this.descontos = descontos;
		this.valorArrecadado = valorArrecadado;
		this.devolucao = devolucao;
		this.arrecadacaoLiquida = arrecadacaoLiquida;
		this.percentualMes = percentualMes;
		this.valorTotalPeriodo = valorTotalPeriodo;
	}

	public String getArrecadacaoLiquida() {
		return arrecadacaoLiquida;
	}

	public void setArrecadacaoLiquida(String arrecadacaoLiquida) {
		this.arrecadacaoLiquida = arrecadacaoLiquida;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getPercentual() {
		return percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
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

	public String getTipoProcessamento() {
		return tipoProcessamento;
	}

	public void setTipoProcessamento(String tipoProcessamento) {
		this.tipoProcessamento = tipoProcessamento;
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

	public String getValorTotalPeriodo() {
		return valorTotalPeriodo;
	}

	public void setValorTotalPeriodo(String valorTotalPeriodo) {
		this.valorTotalPeriodo = valorTotalPeriodo;
	}
	
}
