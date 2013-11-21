package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação por Arrecadador
 * 
 * @author Mariana Victor
 * @date 02/02/2011
 */
public class RelatorioDadosDiariosArrecadacaoArrecadadorBean implements RelatorioBean {

	private String ultimoProcessamentoAtual;
	
	private String faturamentoCobradoEmConta;
	
	private String mesAno;
	
	private String debitos;
	
	private String descontos;
	
	private String valorArrecadado;
	
	private String devolucao;
	
	private String arrecadacaoLiquida;
	
	private String evolucaoMesAnterior;

	private String valorTotalPeriodo;
	
	
	public RelatorioDadosDiariosArrecadacaoArrecadadorBean() {
		super();
	}

	public RelatorioDadosDiariosArrecadacaoArrecadadorBean(String ultimoProcessamentoAtual, String faturamentoCobradoEmConta, String mesAno, String debitos, String descontos, String valorArrecadado, String devolucao, String arrecadacaoLiquida, String evolucaoMesAnterior, String valorTotalPeriodo) {
		super();
		this.ultimoProcessamentoAtual = ultimoProcessamentoAtual;
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
		this.mesAno = mesAno;
		this.debitos = debitos;
		this.descontos = descontos;
		this.valorArrecadado = valorArrecadado;
		this.devolucao = devolucao;
		this.arrecadacaoLiquida = arrecadacaoLiquida;
		this.evolucaoMesAnterior = evolucaoMesAnterior;
		this.valorTotalPeriodo = valorTotalPeriodo;
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

	public String getEvolucaoMesAnterior() {
		return evolucaoMesAnterior;
	}

	public void setEvolucaoMesAnterior(String evolucaoMesAnterior) {
		this.evolucaoMesAnterior = evolucaoMesAnterior;
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

	public String getUltimoProcessamentoAtual() {
		return ultimoProcessamentoAtual;
	}

	public void setUltimoProcessamentoAtual(String ultimoProcessamentoAtual) {
		this.ultimoProcessamentoAtual = ultimoProcessamentoAtual;
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
