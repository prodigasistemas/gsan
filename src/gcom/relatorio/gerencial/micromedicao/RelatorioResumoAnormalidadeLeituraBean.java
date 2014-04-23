package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoAnormalidadeLeituraBean implements RelatorioBean {

	private String estado;

	private String idGerenciaRegional;

	private String descricaoGerenciaRegional;

	private String idEloPolo;

	private String descricaoEloPolo;

	private String idLocalidade;

	private String descricaoLocalidade;

	private String idSetorComercial;

	private String descricaoSetorComercial;

	private String numeroQuadra;

	private String tipoLigacao;

	private String anormalidade;

	private String ligacoes;

	private String percentual;

	public RelatorioResumoAnormalidadeLeituraBean(String estado,
			String idGerenciaRegional, String descricaoGerenciaRegional,
			String idEloPolo, String descricaoEloPolo, String idLocalidade,
			String descricaoLocalidade, String idSetorComercial,
			String descricaoSetorComercial, String numeroQuadra,
			String tipoLigacao, String anormalidade, String ligacoes,
			String percentual) {

		this.estado = estado;
		this.idGerenciaRegional = idGerenciaRegional;
		this.descricaoGerenciaRegional = descricaoGerenciaRegional;
		this.idEloPolo = idEloPolo;
		this.descricaoEloPolo = descricaoEloPolo;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.descricaoSetorComercial = descricaoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.tipoLigacao = tipoLigacao;
		this.anormalidade = anormalidade;
		this.ligacoes = ligacoes;
		this.percentual = percentual;
		
	}

	public String getAnormalidade() {
		return anormalidade;
	}

	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
	}

	public String getDescricaoEloPolo() {
		return descricaoEloPolo;
	}

	public void setDescricaoEloPolo(String descricaoEloPolo) {
		this.descricaoEloPolo = descricaoEloPolo;
	}

	public String getDescricaoGerenciaRegional() {
		return descricaoGerenciaRegional;
	}

	public void setDescricaoGerenciaRegional(String descricaoGerenciaRegional) {
		this.descricaoGerenciaRegional = descricaoGerenciaRegional;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdEloPolo() {
		return idEloPolo;
	}

	public void setIdEloPolo(String idEloPolo) {
		this.idEloPolo = idEloPolo;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getLigacoes() {
		return ligacoes;
	}

	public void setLigacoes(String ligacoes) {
		this.ligacoes = ligacoes;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getPercentual() {
		return percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}

	public String getTipoLigacao() {
		return tipoLigacao;
	}

	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}

}
