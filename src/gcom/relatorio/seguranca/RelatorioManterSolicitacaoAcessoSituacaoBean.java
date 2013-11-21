package gcom.relatorio.seguranca;

import gcom.relatorio.RelatorioBean;

public class RelatorioManterSolicitacaoAcessoSituacaoBean implements
		RelatorioBean {

	private String codigo;

	private String descricao;

	private String indicadorUso;

	private String codigoSituacao;

	public RelatorioManterSolicitacaoAcessoSituacaoBean(String codigo,
			String descricao, String indicadorUso, String codigoSituacao) {

		this.codigo = codigo;
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.codigoSituacao = codigoSituacao;

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(String codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

}
