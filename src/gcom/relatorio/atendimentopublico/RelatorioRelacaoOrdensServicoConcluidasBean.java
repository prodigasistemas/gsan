package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0732] - Gerar Relatório Acompanhamento de Ordem de Serviço de Hidrometro
 * 			  (RelatorioRelacaoOrdensServicoConcluidas)
 * 
 * @author Ivan Sérgio
 * @date 04/04/2008
 * 
 */
public class RelatorioRelacaoOrdensServicoConcluidasBean implements RelatorioBean {

	private String situacao;
	private String imovel;
	private String tipoServico;
	private String periodoEncerramento;
	private String idLocalidade;
	private String nomeLocalidade;
	private String firma;
	private String nomeFirma;
	private String inscricao;
	private String numeroOrdem;
	private String endereco;
	private String dataGeracao;
	private String dataEncerramento;
	private String motivoEncerramento;
	
	private String idMotivoEncerramento;
	private String descricaoMotivoEncerramento;
	private String idSetorComercial;
	private String codigoSetorComercial;
	private String nomeSetorComercial;
	
	private String idLocalInstalacao;
	private String descricaoLocalInstalacao;
	
	public String getDescricaoLocalInstalacao() {
		return descricaoLocalInstalacao;
	}

	public void setDescricaoLocalInstalacao(String descricaoLocalInstalacao) {
		this.descricaoLocalInstalacao = descricaoLocalInstalacao;
	}

	public String getIdLocalInstalacao() {
		return idLocalInstalacao;
	}

	public void setIdLocalInstalacao(String idLocalInstalacao) {
		this.idLocalInstalacao = idLocalInstalacao;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getDescricaoMotivoEncerramento() {
		return descricaoMotivoEncerramento;
	}

	public void setDescricaoMotivoEncerramento(String descricaoMotivoEncerramento) {
		this.descricaoMotivoEncerramento = descricaoMotivoEncerramento;
	}

	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}

	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}

	public RelatorioRelacaoOrdensServicoConcluidasBean(){
		
	}
	
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getNomeFirma() {
		return nomeFirma;
	}
	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNumeroOrdem() {
		return numeroOrdem;
	}

	public void setNumeroOrdem(String numeroOrdem) {
		this.numeroOrdem = numeroOrdem;
	}

	public String getPeriodoEncerramento() {
		return periodoEncerramento;
	}
	public void setPeriodoEncerramento(String periodoEncerramento) {
		this.periodoEncerramento = periodoEncerramento;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getImovel() {
		return imovel;
	}
	public void setImovel(String imovel) {
		this.imovel = imovel;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
}
