package gcom.cobranca;

import java.io.Serializable;

public class ImoveisComAcordoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idUnidadeNegocio;

	private String nomeUnidadeNegocio;

	private String idLocalidadeInicial;

	private String nomeLocalidadeInicial;

	private String idLocalidadeFinal;

	private String nomeLocalidadeFinal;

	private String idGerenciaRegional;

	private String nomeGerenciaRegional;

	private String dataInicialAcordo;

	private String dataFinalAcordo;

	private String rotaInicial;

	private String rotaFinal;

	private String sequencialRotaInicial;

	private String sequencialRotaFinal;

	private String idSetorComercialInicial;

	private String nomeSetorComercialInicial;

	private String idSetorComercialFinal;

	private String nomeSetorComercialFinal;

	private String inscricao;

	private String nomeCliente;

	private String endereco;

	private String descricaoAcordo;

	private String periodoAcordo;

	private String observacao;

	private String responsavel;

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getDataFinalAcordo() {
		return dataFinalAcordo;
	}

	public void setDataFinalAcordo(String dataFinalAcordo) {
		this.dataFinalAcordo = dataFinalAcordo;
	}

	public String getDataInicialAcordo() {
		return dataInicialAcordo;
	}

	public void setDataInicialAcordo(String dataInicialAcordo) {
		this.dataInicialAcordo = dataInicialAcordo;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}

	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}

	public String getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}

	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDescricaoAcordo() {
		return descricaoAcordo;
	}

	public void setDescricaoAcordo(String descricaoAcordo) {
		this.descricaoAcordo = descricaoAcordo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getPeriodoAcordo() {
		return periodoAcordo;
	}

	public void setPeriodoAcordo(String periodoAcordo) {
		this.periodoAcordo = periodoAcordo;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

}
