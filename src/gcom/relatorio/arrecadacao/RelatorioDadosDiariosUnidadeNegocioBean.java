package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação - Unidade de Negócio
 * 
 * @author Mariana Victor
 * @date 03/02/2011
 */
public class RelatorioDadosDiariosUnidadeNegocioBean implements RelatorioBean {

	private String processamentoDefinitivo;
	
	private String mesAno;
	
	private String ultimoProcessamentoAtual;
	
	private String faturamentoCobradoEmConta;
	
	private String nomeGerencia;
	
	private String valor;
	
	private String unidadeNegocio;
	
	private String debitos;
	
	private String descontos;
	
	private String valorArrecadado;
	
	private String devolucao;
	
	private String arrecadacaoLiquida;
	
	private String percentualMes;
	
	public RelatorioDadosDiariosUnidadeNegocioBean() {
		super();
	}

	public RelatorioDadosDiariosUnidadeNegocioBean(String processamentoDefinitivo, String mesAno,
			String ultimoProcessamentoAtual, String faturamentoCobradoEmConta, String nomeGerencia,
			String valor, String unidadeNegocio, String debitos, String descontos,
			String valorArrecadado, String devolucao, String arrecadacaoLiquida, String percentualMes) {
		super();
		this.processamentoDefinitivo = processamentoDefinitivo;
		this.mesAno = mesAno;
		this.ultimoProcessamentoAtual = ultimoProcessamentoAtual;
		this.nomeGerencia = nomeGerencia;
		this.valor = valor;
		this.unidadeNegocio = unidadeNegocio;
		this.debitos = debitos;
		this.descontos = descontos;
		this.valorArrecadado = valorArrecadado;
		this.devolucao = devolucao;
		this.arrecadacaoLiquida = arrecadacaoLiquida;
		this.percentualMes = percentualMes;
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
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

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
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

	public String getUltimoProcessamentoAtual() {
		return ultimoProcessamentoAtual;
	}

	public void setUltimoProcessamentoAtual(String ultimoProcessamentoAtual) {
		this.ultimoProcessamentoAtual = ultimoProcessamentoAtual;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
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

	public String getFaturamentoCobradoEmConta() {
		return faturamentoCobradoEmConta;
	}

	public void setFaturamentoCobradoEmConta(String faturamentoCobradoEmConta) {
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
	}

}
