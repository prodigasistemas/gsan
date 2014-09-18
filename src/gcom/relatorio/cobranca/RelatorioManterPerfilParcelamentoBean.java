package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
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
 * @author Rafael Corr�a
 * @version 1.0
 */

public class RelatorioManterPerfilParcelamentoBean implements RelatorioBean {
	private String idPerfil;

	private String rd;

	private String tipoSituacaoImovel;

	private String perfilImovel;

	private String subcategoria;

	/*
	 *
	 */
	private String percentualDescontoImpontualidadeMulta;
	
	private String percentualDescontoImpontualidadeJurosMora;
	
	private String percentualDescontoImpontualidadeAtualizacaoMonetaria;
	// fim altera��o

	private String percentualTarifaMinima;

	private String idReparcelamento;

	private String qtdeReparcelamentosConsecutivos;
	
	private String idPrestacao;

	private String qtdePrestacoesParcelamento;

	private String juros;

	private String percentualEntrada;

	private String idDescontoAntiguidade;

	private String qtdeMinimaMesesDebito;

	private String descontoSemRestabelecimentoAntiguidade;

	private String descontoComRestabelecimentoAntiguidade;

	private String descontoAtivo;

	private String idDescontoInatividade;

	private String qtdeMaximaMesesInatividade;

	private String descontoSemRestabelecimentoInatividade;

	private String descontoComRestabelecimentoInatividade;


	/**
	 * Construtor da classe RelatorioManterCronogramaFaturamentoBean
	 * 
	 * @param grupo
	 *            Descri��o do par�metro
	 * @param mesAno
	 *            Descri��o do par�metro
	 * @param atividade
	 *            Descri��o do par�metro
	 * @param predecessora
	 *            Descri��o do par�metro
	 * @param obrigatoria
	 *            Descri��o do par�metro
	 * @param dataPrevista
	 *            Descri��o do par�metro
	 * @param dataRealizacao
	 *            Descri��o do par�metro
	 */

	public RelatorioManterPerfilParcelamentoBean(String idPerfil, String rd,
			String tipoSituacaoImovel, String perfilImovel,
			String subcategoria, String percentualDescontoImpontualidadeMulta,
			String percentualDescontoImpontualidadeJurosMora,
			String percentualDescontoImpontualidadeAtualizacaoMonetaria,
			String percentualTarifaMinima, String idReparcelamento,
			String qtdeReparcelamentosConsecutivos, String idPrestacao,
			String qtdePrestacoesParcelamento, String juros,
			String percentualEntrada, String idDescontoAntiguidade,
			String qtdeMinimaMesesDebito,
			String descontoSemRestabelecimentoAntiguidade,
			String descontoComRestabelecimentoAntiguidade,
			String descontoAtivo, String idDescontoInatividade,
			String qtdeMaximaMesesInatividade,
			String descontoSemRestabelecimentoInatividade,
			String descontoComRestabelecimentoInatividade) {
		this.idPerfil = idPerfil;
		this.rd = rd;
		this.tipoSituacaoImovel = tipoSituacaoImovel;
		this.perfilImovel = perfilImovel;
		this.subcategoria = subcategoria;
		this.percentualDescontoImpontualidadeMulta = percentualDescontoImpontualidadeMulta;
		this.percentualDescontoImpontualidadeJurosMora = percentualDescontoImpontualidadeJurosMora;
		this.percentualDescontoImpontualidadeAtualizacaoMonetaria = percentualDescontoImpontualidadeAtualizacaoMonetaria;
		this.percentualTarifaMinima = percentualTarifaMinima;
		this.idReparcelamento = idReparcelamento;
		this.qtdeReparcelamentosConsecutivos = qtdeReparcelamentosConsecutivos;
		this.idPrestacao = idPrestacao;
		this.qtdePrestacoesParcelamento = qtdePrestacoesParcelamento;
		this.juros = juros;
		this.percentualEntrada = percentualEntrada;
		this.idDescontoAntiguidade = idDescontoAntiguidade;
		this.qtdeMinimaMesesDebito = qtdeMinimaMesesDebito;
		this.descontoSemRestabelecimentoAntiguidade = descontoSemRestabelecimentoAntiguidade;
		this.descontoComRestabelecimentoAntiguidade = descontoComRestabelecimentoAntiguidade;
		this.descontoAtivo = descontoAtivo;
		this.idDescontoInatividade = idDescontoInatividade;
		this.qtdeMaximaMesesInatividade = qtdeMaximaMesesInatividade;
		this.descontoSemRestabelecimentoInatividade = descontoSemRestabelecimentoInatividade;
		this.descontoComRestabelecimentoInatividade = descontoComRestabelecimentoInatividade;
	}

	public String getDescontoAtivo() {
		return descontoAtivo;
	}

	public void setDescontoAtivo(String descontoAtivo) {
		this.descontoAtivo = descontoAtivo;
	}

	public String getDescontoComRestabelecimentoAntiguidade() {
		return descontoComRestabelecimentoAntiguidade;
	}

	public void setDescontoComRestabelecimentoAntiguidade(
			String descontoComRestabelecimentoAntiguidade) {
		this.descontoComRestabelecimentoAntiguidade = descontoComRestabelecimentoAntiguidade;
	}

	public String getDescontoComRestabelecimentoInatividade() {
		return descontoComRestabelecimentoInatividade;
	}

	public void setDescontoComRestabelecimentoInatividade(
			String descontoComRestabelecimentoInatividade) {
		this.descontoComRestabelecimentoInatividade = descontoComRestabelecimentoInatividade;
	}

	public String getDescontoSemRestabelecimentoAntiguidade() {
		return descontoSemRestabelecimentoAntiguidade;
	}

	public void setDescontoSemRestabelecimentoAntiguidade(
			String descontoSemRestabelecimentoAntiguidade) {
		this.descontoSemRestabelecimentoAntiguidade = descontoSemRestabelecimentoAntiguidade;
	}

	public String getDescontoSemRestabelecimentoInatividade() {
		return descontoSemRestabelecimentoInatividade;
	}

	public void setDescontoSemRestabelecimentoInatividade(
			String descontoSemRestabelecimentoInatividade) {
		this.descontoSemRestabelecimentoInatividade = descontoSemRestabelecimentoInatividade;
	}

	public String getJuros() {
		return juros;
	}

	public void setJuros(String juros) {
		this.juros = juros;
	}

	public String getPercentualDescontoImpontualidadeMulta() {
		return percentualDescontoImpontualidadeMulta;
	}

	public void setPercentualDescontoImpontualidadeMulta(
			String percentualDescontoImpontualidadeMulta) {
		this.percentualDescontoImpontualidadeMulta = percentualDescontoImpontualidadeMulta;
	}
	
	public String getPercentualDescontoImpontualidadeJurosMora() {
		return percentualDescontoImpontualidadeJurosMora;
	}

	public void setPercentualDescontoImpontualidadeJurosMora(
			String percentualDescontoImpontualidadeJurosMora) {
		this.percentualDescontoImpontualidadeJurosMora = percentualDescontoImpontualidadeJurosMora;
	}
	
	public String getPercentualDescontoImpontualidadeAtualizacaoMonetaria() {
		return percentualDescontoImpontualidadeAtualizacaoMonetaria;
	}

	public void setPercentualDescontoImpontualidadeAtualizacaoMonetaria(
			String percentualDescontoImpontualidadeAtualizacaoMonetaria) {
		this.percentualDescontoImpontualidadeAtualizacaoMonetaria = percentualDescontoImpontualidadeAtualizacaoMonetaria;
	}

	public String getPercentualEntrada() {
		return percentualEntrada;
	}

	public void setPercentualEntrada(String percentualEntrada) {
		this.percentualEntrada = percentualEntrada;
	}

	public String getPercentualTarifaMinima() {
		return percentualTarifaMinima;
	}

	public void setPercentualTarifaMinima(String percentualTarifaMinima) {
		this.percentualTarifaMinima = percentualTarifaMinima;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getQtdeMaximaMesesInatividade() {
		return qtdeMaximaMesesInatividade;
	}

	public void setQtdeMaximaMesesInatividade(String qtdeMaximaMesesInatividade) {
		this.qtdeMaximaMesesInatividade = qtdeMaximaMesesInatividade;
	}

	public String getQtdeMinimaMesesDebito() {
		return qtdeMinimaMesesDebito;
	}

	public void setQtdeMinimaMesesDebito(String qtdeMinimaMesesDebito) {
		this.qtdeMinimaMesesDebito = qtdeMinimaMesesDebito;
	}

	public String getQtdePrestacoesParcelamento() {
		return qtdePrestacoesParcelamento;
	}

	public void setQtdePrestacoesParcelamento(String qtdePrestacoesParcelamento) {
		this.qtdePrestacoesParcelamento = qtdePrestacoesParcelamento;
	}

	public String getQtdeReparcelamentosConsecutivos() {
		return qtdeReparcelamentosConsecutivos;
	}

	public void setQtdeReparcelamentosConsecutivos(
			String qtdeReparcelamentosConsecutivos) {
		this.qtdeReparcelamentosConsecutivos = qtdeReparcelamentosConsecutivos;
	}

	public String getRd() {
		return rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String getTipoSituacaoImovel() {
		return tipoSituacaoImovel;
	}

	public void setTipoSituacaoImovel(String tipoSituacaoImovel) {
		this.tipoSituacaoImovel = tipoSituacaoImovel;
	}

	public String getIdDescontoAntiguidade() {
		return idDescontoAntiguidade;
	}

	public void setIdDescontoAntiguidade(String idDescontoAntiguidade) {
		this.idDescontoAntiguidade = idDescontoAntiguidade;
	}

	public String getIdDescontoInatividade() {
		return idDescontoInatividade;
	}

	public void setIdDescontoInatividade(String idDescontoInatividade) {
		this.idDescontoInatividade = idDescontoInatividade;
	}

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getIdReparcelamento() {
		return idReparcelamento;
	}

	public void setIdReparcelamento(String idReparcelamento) {
		this.idReparcelamento = idReparcelamento;
	}

	public String getIdPrestacao() {
		return idPrestacao;
	}

	public void setIdPrestacao(String idPrestacao) {
		this.idPrestacao = idPrestacao;
	}

}
