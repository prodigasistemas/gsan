package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoAnormalidadeLeituraAnormalidadeBean implements
		RelatorioBean {

	private String anormalidade;

	private String ligacoes;

	private String percentual;

	public RelatorioResumoAnormalidadeLeituraAnormalidadeBean(
			String anormalidade, String ligacoes, String percentual) {

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

	public String getPercentual() {
		return percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}

}
