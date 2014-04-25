package gcom.relatorio.cadastro;

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

public class RelatorioManterUnidadeOrganizacionalBean implements RelatorioBean {
	
	private String codigo;

	private String descricao;
	
	private String nivel;
	
	private String unidadeTipo;
	
	private String indicadorAberturaRa;
	
	private String indicadorTramite;
	
	private String indicadorUso;
	
	private String empresa;
	
	private String indicadorEsgoto;
	
	private String sigla;
	
	private String gerenciaRegional;
	
	private String localidade;
	
	private String unidadeSuperior;
	
	private String unidadeCentralizadora;
	
	private String unidadeRepavimentadora;
	
	private String meioSolicitacao;

	/**
	 * Construtor da classe RelatorioManterUnidadeOrganizacionalBean
	 * 
	 * @param id
	 *            Descrição do parâmetro
	 * @param descricao
	 *            Descrição do parâmetro
	 * @param sistemaEsgoto
	 *            Descrição do parâmetro
	 */

	public RelatorioManterUnidadeOrganizacionalBean(String codigo,
			String descricao,  String nivel, String unidadeTipo, String indicadorAberturaRa, 
			String indicadorTramite, String indicadorUso, String empresa, String indicadorEsgoto,
			String sigla, String gerenciaRegional, String localidade, String unidadeSuperior,
			String unidadeCentralizadora, String unidadeRepavimentadora, String meioSolicitacao) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.nivel= nivel;
		this.unidadeTipo= unidadeTipo;
		this.indicadorAberturaRa = indicadorAberturaRa;
		this.indicadorTramite = indicadorTramite;
		this.indicadorUso = indicadorUso;
		this.empresa = empresa;
		this.indicadorEsgoto = indicadorEsgoto;
		this.sigla = sigla;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.unidadeCentralizadora = unidadeCentralizadora;
		this.unidadeRepavimentadora = unidadeRepavimentadora;
		this.unidadeSuperior = unidadeSuperior;
		this.meioSolicitacao = meioSolicitacao;
	}

	/**
	 * Retorna o valor do id
	 * 
	 * @return O valor do id
	 */

	public String getCodigo() {
		return codigo;
	}

	/**
	 * Retorna o valor da descricao
	 * 
	 * @return O valor da descricao
	 */

	public String getDescricao() {
		return descricao;
	}

	/**
	 * Seta o valor do id
	 * 
	 * @param id
	 *            O novo valor do id
	 */

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorAberturaRa() {
		return indicadorAberturaRa;
	}

	public void setIndicadorAberturaRa(String indicadorAberturaRa) {
		this.indicadorAberturaRa = indicadorAberturaRa;
	}

	public String getIndicadorTramite() {
		return indicadorTramite;
	}

	public void setIndicadorTramite(String indicadorTramite) {
		this.indicadorTramite = indicadorTramite;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getUnidadeTipo() {
		return unidadeTipo;
	}

	public void setUnidadeTipo(String unidadeTipo) {
		this.unidadeTipo = unidadeTipo;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getIndicadorEsgoto() {
		return indicadorEsgoto;
	}

	public void setIndicadorEsgoto(String indicadorEsgoto) {
		this.indicadorEsgoto = indicadorEsgoto;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMeioSolicitacao() {
		return meioSolicitacao;
	}

	public void setMeioSolicitacao(String meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}

	public String getUnidadeCentralizadora() {
		return unidadeCentralizadora;
	}

	public void setUnidadeCentralizadora(String unidadeCentralizadora) {
		this.unidadeCentralizadora = unidadeCentralizadora;
	}

	public String getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	public void setUnidadeRepavimentadora(String unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public String getUnidadeSuperior() {
		return unidadeSuperior;
	}

	public void setUnidadeSuperior(String unidadeSuperior) {
		this.unidadeSuperior = unidadeSuperior;
	}
	
}
