package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioDebitoCobradoContaBean implements RelatorioBean {
	

	private String idImovel;
	
	private String situacaoConta;
	
	private String situacaoLigacaoAgua;
	
	private String mesAnoConta;
	
	private String situacaoLigacaoEsgoto;
	
	private String tipoDebito;
	
	private String mesAnoReferencia;
	
	private String mesAnoCobranca;
	
	private String parcela;
	
	private String valorParcela;
	
	

	public RelatorioDebitoCobradoContaBean() {
		
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getMesAnoCobranca() {
		return mesAnoCobranca;
	}

	public void setMesAnoCobranca(String mesAnoCobranca) {
		this.mesAnoCobranca = mesAnoCobranca;
	}

	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public String getSituacaoConta() {
		return situacaoConta;
	}

	public void setSituacaoConta(String situacaoConta) {
		this.situacaoConta = situacaoConta;
	}

	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getTipoDebito() {
		return tipoDebito;
	}

	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	public String getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(String valorParcela) {
		this.valorParcela = valorParcela;
	}
	
	

	
}
