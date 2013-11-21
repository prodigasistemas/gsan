package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação - Parametros
 * 
 * @author Mariana Victor
 * @date 01/02/2011
 */
public class RelatorioDadosDiariosArrecadacaoParametrosBean implements RelatorioBean {
	
	private String periodoArrecadacaoInicio;
	
	private String periodoArrecadacaoFim;
	
	private String nomeGerenciaRegional;
	
	private String nomeElo;
	
	private String descricaoLocalidade;
	
	private String nomeArrecadador;
	
	private String imovelPerfilDescricao;
	
	private String ligacaoAguaSituacaoDescricao;
	
	private String ligacaoEsgotoSituacaoDescricao;
	
	private String categoriaDescricao;
	
	private String esferaPoderDescricao;
	
	private String documentoTipoDescricao;
	
	
	public RelatorioDadosDiariosArrecadacaoParametrosBean() {
		super();
	}
	
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getNomeArrecadador() {
		return nomeArrecadador;
	}

	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}

	public String getNomeElo() {
		return nomeElo;
	}

	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getPeriodoArrecadacaoFim() {
		return periodoArrecadacaoFim;
	}

	public void setPeriodoArrecadacaoFim(String periodoArrecadacaoFim) {
		this.periodoArrecadacaoFim = periodoArrecadacaoFim;
	}

	public String getPeriodoArrecadacaoInicio() {
		return periodoArrecadacaoInicio;
	}

	public void setPeriodoArrecadacaoInicio(String periodoArrecadacaoInicio) {
		this.periodoArrecadacaoInicio = periodoArrecadacaoInicio;
	}

	public String getCategoriaDescricao() {
		return categoriaDescricao;
	}

	public void setCategoriaDescricao(String categoriaDescricao) {
		this.categoriaDescricao = categoriaDescricao;
	}

	public String getDocumentoTipoDescricao() {
		return documentoTipoDescricao;
	}

	public void setDocumentoTipoDescricao(String documentoTipoDescricao) {
		this.documentoTipoDescricao = documentoTipoDescricao;
	}

	public String getEsferaPoderDescricao() {
		return esferaPoderDescricao;
	}

	public void setEsferaPoderDescricao(String esferaPoderDescricao) {
		this.esferaPoderDescricao = esferaPoderDescricao;
	}

	public String getImovelPerfilDescricao() {
		return imovelPerfilDescricao;
	}

	public void setImovelPerfilDescricao(String imovelPerfilDescricao) {
		this.imovelPerfilDescricao = imovelPerfilDescricao;
	}

	public String getLigacaoAguaSituacaoDescricao() {
		return ligacaoAguaSituacaoDescricao;
	}

	public void setLigacaoAguaSituacaoDescricao(String ligacaoAguaSituacaoDescricao) {
		this.ligacaoAguaSituacaoDescricao = ligacaoAguaSituacaoDescricao;
	}

	public String getLigacaoEsgotoSituacaoDescricao() {
		return ligacaoEsgotoSituacaoDescricao;
	}

	public void setLigacaoEsgotoSituacaoDescricao(
			String ligacaoEsgotoSituacaoDescricao) {
		this.ligacaoEsgotoSituacaoDescricao = ligacaoEsgotoSituacaoDescricao;
	}
	
}
