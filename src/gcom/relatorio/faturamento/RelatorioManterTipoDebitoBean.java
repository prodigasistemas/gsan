package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterTipoDebitoBean implements RelatorioBean {
	
	private String codigo;
	
	private String descricao;

	private String descricaoAbreviada;

	private String lancamentoItemContabil;

	private String financiamentoTipo;

	private String indicadorGeracaoDebitoAutomatica;

	private String indicadorGeracaoDebitoConta;

	private String indicadorUso;

	private String valorLimite;

	private String valorSugerido;

	/**
	 * Construtor da classe RelatorioManterBaciaBean
	 * 
	 * @param id
	 *            Descrição do parâmetro
	 * @param descricao
	 *            Descrição do parâmetro
	 * @param sistemaEsgoto
	 *            Descrição do parâmetro
	 */

	public RelatorioManterTipoDebitoBean(String codigo, String descricao, String descricaoAbreviada, 
			String lancamentoItemContabil, String financiamentoTipo, String indicadorGeracaoDebitoAutomatica,
			String indicadorGeracaoDebitoConta, String indicadorUso, String valorLimite, String valorSugerido ){
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.financiamentoTipo = financiamentoTipo;
		this.indicadorGeracaoDebitoAutomatica = indicadorGeracaoDebitoAutomatica;
		this.indicadorGeracaoDebitoConta = indicadorGeracaoDebitoConta;
		this.indicadorUso = indicadorUso;
		this.valorLimite = valorLimite;
		this.valorSugerido = valorSugerido;
	
		
	}
	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getFinanciamentoTipo() {
		return financiamentoTipo;
	}

	public void setFinanciamentoTipo(String financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}

	public String getIndicadorGeracaoDebitoAutomatica() {
		return indicadorGeracaoDebitoAutomatica;
	}

	public void setIndicadorGeracaoDebitoAutomatica(
			String indicadorGeracaoDebitoAutomatica) {
		this.indicadorGeracaoDebitoAutomatica = indicadorGeracaoDebitoAutomatica;
	}

	public String getIndicadorGeracaoDebitoConta() {
		return indicadorGeracaoDebitoConta;
	}

	public void setIndicadorGeracaoDebitoConta(String indicadorGeracaoDebitoConta) {
		this.indicadorGeracaoDebitoConta = indicadorGeracaoDebitoConta;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(String lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}


	public String getValorLimite() {
		return valorLimite;
	}

	public void setValorLimite(String valorLimite) {
		this.valorLimite = valorLimite;
	}

	public String getValorSugerido() {
		return valorSugerido;
	}

	public void setValorSugerido(String valorSugerido) {
		this.valorSugerido = valorSugerido;
	}
}
