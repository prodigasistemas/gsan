package gcom.relatorio.faturamento.autoinfracao;

import gcom.relatorio.RelatorioBean;

public class RelatorioManterAutosInfracaoBean implements RelatorioBean {
	
	private String id;

	private String descricaoIrregularidadeConstatada;
	
	private String dataEmissao;
	
	private String dataInicioRecurso;
	
	private String dataFimRecurso;
	
	private String funcionario;
	
	private String situacao;

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDataFimRecurso() {
		return dataFimRecurso;
	}

	public void setDataFimRecurso(String dataFimRecurso) {
		this.dataFimRecurso = dataFimRecurso;
	}

	public String getDataInicioRecurso() {
		return dataInicioRecurso;
	}

	public void setDataInicioRecurso(String dataInicioRecurso) {
		this.dataInicioRecurso = dataInicioRecurso;
	}

	public String getDescricaoIrregularidadeConstatada() {
		return descricaoIrregularidadeConstatada;
	}

	public void setDescricaoIrregularidadeConstatada(
			String descricaoIrregularidadeConstatada) {
		this.descricaoIrregularidadeConstatada = descricaoIrregularidadeConstatada;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
