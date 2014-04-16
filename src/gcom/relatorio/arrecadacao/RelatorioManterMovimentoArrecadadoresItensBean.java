package gcom.relatorio.arrecadacao;

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
 * @author Vinicius Medeiros
 * @version 1.0
 */

public class RelatorioManterMovimentoArrecadadoresItensBean implements RelatorioBean {
	private String codigoRegistro;
	private String descricaoIndicadorAceitacao;
	private String descricaoOcorrencia;
	private String identificacao;
	private String ocorrencia;
	private String idImovel;
	private String tipoPagamento;
	private String vlMovimento;
	private String vlPagamento;
	private String indicadorDiferencaValorMovimentoValorPagamento;
	private String matriculaImovel;
	
	/**
	 * Construtor da classe RelatorioManterMotivoCorteBean
	 * 
	 * @param codigo
	 *            Descrição do parâmetro
	 * @param gerenciaRegional
	 *            Descrição do parâmetro
	 * @param nome
	 *            Descrição do parâmetro
	 * @param indicadorUso
	 *            Descrição do parâmetro
	 */

	public RelatorioManterMovimentoArrecadadoresItensBean(String codigoRegistro, 
			String ocorrencia,
			String descricaoIndicadorAceitacao, 
			String identificacao, 
			String tipoPagamento, 
			String vlMovimento, 
			String vlPagamento,
			String matriculaImovel) {
		
		
		this.codigoRegistro = codigoRegistro;
		this.ocorrencia = ocorrencia;
		this.descricaoIndicadorAceitacao = descricaoIndicadorAceitacao;
		this.identificacao = identificacao;
		this.tipoPagamento = tipoPagamento;
		this.vlMovimento = vlMovimento;
		this.vlPagamento = vlPagamento;
		this.matriculaImovel = matriculaImovel;
		
	}

	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public String getDescricaoOcorrencia() {
		return descricaoOcorrencia;
	}

	public void setDescricaoOcorrencia(String descricaoOcorrencia) {
		this.descricaoOcorrencia = descricaoOcorrencia;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	
	public String getDescricaoIndicadorAceitacao() {
		return descricaoIndicadorAceitacao;
	}

	public void setDescricaoIndicadorAceitacao(String descricaoIndicadorAceitacao) {
		this.descricaoIndicadorAceitacao = descricaoIndicadorAceitacao;
	}

	public String getIndicadorDiferencaValorMovimentoValorPagamento() {
		return indicadorDiferencaValorMovimentoValorPagamento;
	}

	public void setIndicadorDiferencaValorMovimentoValorPagamento(
			String indicadorDiferencaValorMovimentoValorPagamento) {
		this.indicadorDiferencaValorMovimentoValorPagamento = indicadorDiferencaValorMovimentoValorPagamento;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getVlMovimento() {
		return vlMovimento;
	}

	public void setVlMovimento(String vlMovimento) {
		this.vlMovimento = vlMovimento;
	}

	public String getVlPagamento() {
		return vlPagamento;
	}

	public void setVlPagamento(String vlPagamento) {
		this.vlPagamento = vlPagamento;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}



}
