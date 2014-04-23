package gcom.relatorio.atendimentopublico;

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
 * @author Hugo Leonardo
 * @version 1.0
 */

public class RelatorioAcompanhamentoRegistroAtendimentoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String idRA;
	private String tipoSolicitacao;
	private String dataAbertura;
	private String dataRecebimento;
	private String dataEncerramento;
	private String motivoEncerramento;
	private String quantidade;
	private String idUnidadeAtendimento;
	private String descricaoUnidadeAtendimento;
	private String idMunicipio;
	private String descricaoMunicipio;
	private Integer quantidadeTotal;
	private String situacaoRA;
	private String totalGeral;
	
	public RelatorioAcompanhamentoRegistroAtendimentoBean(){
		
	}
	
	// Síntetico
	public RelatorioAcompanhamentoRegistroAtendimentoBean(String idRA, String tipoSolicitacao, 
			String dataAbertura, String dataEncerramento,
			String motivoEncerramento, String idUnidadeAtendimento,
			String descricaoUnidadeAtendimento,
			String idMunicipio, String descricaoMunicipio) {
		
		this.idRA = idRA;
		this.tipoSolicitacao = tipoSolicitacao;
		this.dataAbertura = dataAbertura;
		this.dataEncerramento = dataEncerramento;
		this.motivoEncerramento = motivoEncerramento;
		this.idUnidadeAtendimento = idUnidadeAtendimento;
		this.descricaoUnidadeAtendimento = descricaoUnidadeAtendimento;
		this.idMunicipio = idMunicipio;
		this.descricaoMunicipio = descricaoMunicipio;
	}
	
	public RelatorioAcompanhamentoRegistroAtendimentoBean(String idUnidadeAtendimento, 
			String motivoEncerramento, String quantidade, String idMunicipio){
		
		this.idUnidadeAtendimento = idUnidadeAtendimento;
		this.idMunicipio = idMunicipio;
		this.quantidade = quantidade;
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(String dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public String getIdRA() {
		return idRA;
	}

	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}

	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}

	public String getDescricaoUnidadeAtendimento() {
		return descricaoUnidadeAtendimento;
	}

	public void setDescricaoUnidadeAtendimento(String descricaoUnidadeAtendimento) {
		this.descricaoUnidadeAtendimento = descricaoUnidadeAtendimento;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public String getSituacaoRA() {
		return situacaoRA;
	}

	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	public String getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(String totalGeral) {
		this.totalGeral = totalGeral;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}	
}
