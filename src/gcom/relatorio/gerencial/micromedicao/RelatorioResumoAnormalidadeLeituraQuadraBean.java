package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoAnormalidadeLeituraQuadraBean implements RelatorioBean {

	private String localidade;

	private String setorComercial;

	private String numeroQuadra;

	private String tipoLigacao;

	private String anormalidade;

	private String ligacoes;

	private String percentual;

	public RelatorioResumoAnormalidadeLeituraQuadraBean(String localidade,
			String setorComercial,
			String numeroQuadra,
			String tipoLigacao, String anormalidade, String ligacoes,
			String percentual) {

		this.localidade = localidade;
		this.setorComercial = setorComercial;
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
	
	public String getLigacoes() {
		return ligacoes;
	}

	public void setLigacoes(String ligacoes) {
		this.ligacoes = ligacoes;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
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

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getTipoLigacao() {
		return tipoLigacao;
	}

	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}


}
