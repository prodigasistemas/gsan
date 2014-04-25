package gcom.relatorio.cobranca;

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
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterCriterioCobrancaBean implements RelatorioBean {
	private String descricao;

	private String dataInicio;

	private String numeroAnos;

	private String situacaoEspecialCobranca;

	private String situacaoCobranca;

	private String contasRevisao;

	private String imovelDebitoContaMes;

	private String inquilinoDebitoContaMes;

	private String imovelDebitoContasAntigas;
	
	private String indicadorUso;

	private String perfil;

	private String categoria;

	private String intervaloValorDebito;

	private String intervaloQuantidadeContas;

	private String valorMinimoConta;

	private String valorMinimoDebito;

	private String quantidadeMinimaContas;

	/**
	 * Construtor da classe RelatorioManterCronogramaFaturamentoBean
	 * 
	 * @param grupo
	 *            Descrição do parâmetro
	 * @param mesAno
	 *            Descrição do parâmetro
	 * @param atividade
	 *            Descrição do parâmetro
	 * @param predecessora
	 *            Descrição do parâmetro
	 * @param obrigatoria
	 *            Descrição do parâmetro
	 * @param dataPrevista
	 *            Descrição do parâmetro
	 * @param dataRealizacao
	 *            Descrição do parâmetro
	 */

	public RelatorioManterCriterioCobrancaBean(String descricao,
			String dataInicio, String numeroAnos,
			String situacaoEspecialCobranca, String situacaoCobranca,
			String contasRevisao, String imovelDebitoContaMes,
			String inquilinoDebitoContaMes, String imovelDebitoContasAntigas, String indicadorUso,
			String perfil, String categoria, String intervaloValorDebito,
			String intervaloQuantidadeContas, String valorMinimoConta,
			String valorMinimoDebito, String quantidadeMinimaContas) {
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.numeroAnos = numeroAnos;
		this.situacaoEspecialCobranca = situacaoEspecialCobranca;
		this.situacaoCobranca = situacaoCobranca;
		this.contasRevisao = contasRevisao;
		this.imovelDebitoContaMes = imovelDebitoContaMes;
		this.inquilinoDebitoContaMes = inquilinoDebitoContaMes;
		this.imovelDebitoContasAntigas = imovelDebitoContasAntigas;
		this.indicadorUso = indicadorUso;
		this.perfil = perfil;
		this.categoria = categoria;
		this.intervaloValorDebito = intervaloValorDebito;
		this.intervaloQuantidadeContas = intervaloQuantidadeContas;
		this.valorMinimoConta = valorMinimoConta;
		this.valorMinimoDebito = valorMinimoDebito;
		this.quantidadeMinimaContas = quantidadeMinimaContas;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getContasRevisao() {
		return contasRevisao;
	}

	public void setContasRevisao(String contasRevisao) {
		this.contasRevisao = contasRevisao;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImovelDebitoContaMes() {
		return imovelDebitoContaMes;
	}

	public void setImovelDebitoContaMes(String imovelDebitoContaMes) {
		this.imovelDebitoContaMes = imovelDebitoContaMes;
	}

	public String getImovelDebitoContasAntigas() {
		return imovelDebitoContasAntigas;
	}

	public void setImovelDebitoContasAntigas(String imovelDebitoContasAntigas) {
		this.imovelDebitoContasAntigas = imovelDebitoContasAntigas;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getInquilinoDebitoContaMes() {
		return inquilinoDebitoContaMes;
	}

	public void setInquilinoDebitoContaMes(String inquilinoDebitoContaMes) {
		this.inquilinoDebitoContaMes = inquilinoDebitoContaMes;
	}

	public String getIntervaloQuantidadeContas() {
		return intervaloQuantidadeContas;
	}

	public void setIntervaloQuantidadeContas(String intervaloQuantidadeContas) {
		this.intervaloQuantidadeContas = intervaloQuantidadeContas;
	}

	public String getIntervaloValorDebito() {
		return intervaloValorDebito;
	}

	public void setIntervaloValorDebito(String intervaloValorDebito) {
		this.intervaloValorDebito = intervaloValorDebito;
	}

	public String getNumeroAnos() {
		return numeroAnos;
	}

	public void setNumeroAnos(String numeroAnos) {
		this.numeroAnos = numeroAnos;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getQuantidadeMinimaContas() {
		return quantidadeMinimaContas;
	}

	public void setQuantidadeMinimaContas(String quantidadeMinimaContas) {
		this.quantidadeMinimaContas = quantidadeMinimaContas;
	}

	public String getSituacaoCobranca() {
		return situacaoCobranca;
	}

	public void setSituacaoCobranca(String situacaoCobranca) {
		this.situacaoCobranca = situacaoCobranca;
	}

	public String getSituacaoEspecialCobranca() {
		return situacaoEspecialCobranca;
	}

	public void setSituacaoEspecialCobranca(String situacaoEspecialCobranca) {
		this.situacaoEspecialCobranca = situacaoEspecialCobranca;
	}

	public String getValorMinimoConta() {
		return valorMinimoConta;
	}

	public void setValorMinimoConta(String valorMinimoConta) {
		this.valorMinimoConta = valorMinimoConta;
	}

	public String getValorMinimoDebito() {
		return valorMinimoDebito;
	}

	public void setValorMinimoDebito(String valorMinimoDebito) {
		this.valorMinimoDebito = valorMinimoDebito;
	}
	
	
	
}
