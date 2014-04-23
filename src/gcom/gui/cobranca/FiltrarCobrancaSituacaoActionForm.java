package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0859]FILTRAR Situacao de Cobranca
 * 
 * @author Arthur Carvalho
 * @date 05/09/2008
 */

public class FiltrarCobrancaSituacaoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String contaMotivoRevisao;
	private String indicadorExigenciaAdvogado;
	private String indicadorBloqueioParcelamento;
	private String indicadorUso;
	private String indicadorAtualizar;
	private String tipoPesquisa;
	private String indicadorBloqueioRetirada;
	private String profissao;
	private String ramoAtividade;
	private String indicadorBloqueioInclusao;
	private String indicadorSelecaoApenasComPermissao;
	private String indicadorPrescricaoImoveisParticulares;
	
	public String getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}
	public void setContaMotivoRevisao(String contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
	public String getIndicadorBloqueioParcelamento() {
		return indicadorBloqueioParcelamento;
	}
	public void setIndicadorBloqueioParcelamento(
			String indicadorBloqueioParcelamento) {
		this.indicadorBloqueioParcelamento = indicadorBloqueioParcelamento;
	}
	public String getIndicadorExigenciaAdvogado() {
		return indicadorExigenciaAdvogado;
	}
	public void setIndicadorExigenciaAdvogado(String indicadorExigenciaAdvogado) {
		this.indicadorExigenciaAdvogado = indicadorExigenciaAdvogado;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public String getIndicadorBloqueioRetirada() {
		return indicadorBloqueioRetirada;
	}
	public void setIndicadorBloqueioRetirada(String indicadorBloqueioRetirada) {
		this.indicadorBloqueioRetirada = indicadorBloqueioRetirada;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	public String getIndicadorBloqueioInclusao() {
		return indicadorBloqueioInclusao;
	}
	public void setIndicadorBloqueioInclusao(String indicadorBloqueioInclusao) {
		this.indicadorBloqueioInclusao = indicadorBloqueioInclusao;
	}
	public String getIndicadorSelecaoApenasComPermissao() {
		return indicadorSelecaoApenasComPermissao;
	}
	public void setIndicadorSelecaoApenasComPermissao(
			String indicadorSelecaoApenasComPermissao) {
		this.indicadorSelecaoApenasComPermissao = indicadorSelecaoApenasComPermissao;
	}
	public String getIndicadorPrescricaoImoveisParticulares() {
		return indicadorPrescricaoImoveisParticulares;
	}
	public void setIndicadorPrescricaoImoveisParticulares(
			String indicadorPrescricaoImoveisParticulares) {
		this.indicadorPrescricaoImoveisParticulares = indicadorPrescricaoImoveisParticulares;
	}
}	
