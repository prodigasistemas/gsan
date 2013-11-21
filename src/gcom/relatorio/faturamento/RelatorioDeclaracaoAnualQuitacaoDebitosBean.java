package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

public class RelatorioDeclaracaoAnualQuitacaoDebitosBean implements
		RelatorioBean {
	private String anoBase;
	private String anoMes;
	private String descricaoSituacao;
	private String dataSituacao;
	private String valor;

	public RelatorioDeclaracaoAnualQuitacaoDebitosBean(String anoBase,
			String anoMes, String descricaoSituacao, String dataSituacao,
			String valor) {
		this.anoBase = anoBase;
		this.anoMes = anoMes;
		this.descricaoSituacao = descricaoSituacao;
		this.dataSituacao = dataSituacao;
		this.valor = valor;
	}
	public RelatorioDeclaracaoAnualQuitacaoDebitosBean(String anoMes,
			String descricaoSituacao, String dataSituacao, String valor) {
		this.anoMes = anoMes;
		this.descricaoSituacao = descricaoSituacao;
		this.dataSituacao = dataSituacao;
		this.valor = valor;
	}
	public RelatorioDeclaracaoAnualQuitacaoDebitosBean() {
		// TODO Auto-generated constructor stub
	}
	public String getAnoBase() {
		return anoBase;
	}
	public void setAnoBase(String anoBase) {
		this.anoBase = anoBase;
	}
	public String getAnoMes() {
		return anoMes;
	}
	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}
	public String getDescricaoSituacao() {
		return descricaoSituacao;
	}
	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}
	public String getDataSituacao() {
		return dataSituacao;
	}
	public void setDataSituacao(String dataSituacao) {
		this.dataSituacao = dataSituacao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

}
