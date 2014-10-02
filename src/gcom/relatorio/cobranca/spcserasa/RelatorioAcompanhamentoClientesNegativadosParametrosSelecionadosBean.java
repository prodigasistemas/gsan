package gcom.relatorio.cobranca.spcserasa;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Vivianne Sousa
 * @created 11/05/2009
 * @version 1.0
 */

public class RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean implements RelatorioBean {
	  
	private String negativador;
	private String periodoEnvioNegativacao;
	private String tituloComando;
	private String eloPolo;
	private String localidade;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String grupoCobranca;
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String imovelPerfil;
	private String categoria;
	private String tipoCliente;
	private String esferaPoder;
	
	//*************************************************************
	// RM3755
	// Autor: Ivan Sergio
	// Data: 10/01/2011
	//*************************************************************
	private String ligacaoAguaSituacao;
	private String ligacaoEsgotoSituacao;
	//*************************************************************

	public RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean(
			String negativador,
			String periodoEnvioNegativacao,
			String tituloComando,
			String eloPolo,
			String localidade,
			String codigoSetorComercial,
			String numeroQuadra,
			String grupoCobranca,
			String gerenciaRegional,
			String unidadeNegocio,
			String imovelPerfil,
			String categoria,
			String tipoCliente,
			String esferaPoder

			) {
		super();
		

		this.negativador = negativador;
		this.periodoEnvioNegativacao = periodoEnvioNegativacao;
		this.tituloComando = tituloComando;
		this.eloPolo = eloPolo;
		this.localidade = localidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.grupoCobranca = grupoCobranca;
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.imovelPerfil = imovelPerfil;
		this.categoria = categoria;
		this.tipoCliente = tipoCliente;
		this.esferaPoder = esferaPoder; 
	}

	public RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean(){
		super();
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getEloPolo() {
		return eloPolo;
	}

	public void setEloPolo(String eloPolo) {
		this.eloPolo = eloPolo;
	}

	public String getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNegativador() {
		return negativador;
	}

	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getPeriodoEnvioNegativacao() {
		return periodoEnvioNegativacao;
	}

	public void setPeriodoEnvioNegativacao(String periodoEnvioNegativacao) {
		this.periodoEnvioNegativacao = periodoEnvioNegativacao;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getTituloComando() {
		return tituloComando;
	}

	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	
	
}
