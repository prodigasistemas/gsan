package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação - Elo
 * 
 * @author Mariana Victor
 * @date 03/02/2011
 */
public class RelatorioDadosDiariosEloBean implements RelatorioBean {

	private String processamentoDefinitivo;
	
	private String mesAno;
	
	private String ultimoProcessamentoAtual;
	
	private String faturamentoCobradoEmConta;
	
	private String nomeGerencia;
	
	private String nomeUnidadeNegocio;
	
	private String valorGerencia;
	
	private String valorUnidadeNegocio;
	
	private String elo;
	
	private String debitos;
	
	private String descontos;
	
	private String valorArrecadado;
	
	private String devolucao;
	
	private String arrecadacaoLiquida;
	
	private String percentualMes;
	
	public RelatorioDadosDiariosEloBean() {
		super();
	}

	public RelatorioDadosDiariosEloBean(String processamentoDefinitivo, String mesAno, String ultimoProcessamentoAtual, String faturamentoCobradoEmConta, String nomeGerencia, String nomeUnidadeNegocio, String valorGerencia, String valorUnidadeNegocio, String elo, String debitos, String descontos, String valorArrecadado, String devolucao, String arrecadacaoLiquida, String percentualMes) {
		super();
		this.processamentoDefinitivo = processamentoDefinitivo;
		this.mesAno = mesAno;
		this.ultimoProcessamentoAtual = ultimoProcessamentoAtual;
		this.faturamentoCobradoEmConta = faturamentoCobradoEmConta;
		this.nomeGerencia = nomeGerencia;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.valorGerencia = valorGerencia;
		this.valorUnidadeNegocio = valorUnidadeNegocio;
		this.elo = elo;
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

	public String getElo() {
		return elo;
	}

	public void setElo(String elo) {
		this.elo = elo;
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

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
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

	public String getValorArrecadado() {
		return valorArrecadado;
	}

	public void setValorArrecadado(String valorArrecadado) {
		this.valorArrecadado = valorArrecadado;
	}

	public String getValorGerencia() {
		return valorGerencia;
	}

	public void setValorGerencia(String valorGerencia) {
		this.valorGerencia = valorGerencia;
	}

	public String getValorUnidadeNegocio() {
		return valorUnidadeNegocio;
	}

	public void setValorUnidadeNegocio(String valorUnidadeNegocio) {
		this.valorUnidadeNegocio = valorUnidadeNegocio;
	}

}
