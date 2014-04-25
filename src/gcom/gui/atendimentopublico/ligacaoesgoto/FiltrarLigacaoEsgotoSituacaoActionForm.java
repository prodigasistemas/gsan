package gcom.gui.atendimentopublico.ligacaoesgoto;


import gcom.util.ConstantesSistema;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00789] Filtrar Situação de Ligação de Esgoto
 * 
 * @author Bruno Barros
 *
 * @date 14/05/2008
 */
public class FiltrarLigacaoEsgotoSituacaoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descricao;
	private String tipoPesquisa = ConstantesSistema.TIPO_PESQUISA_INICIAL.toString();
	private String descricaoAbreviada;
	private String consumoMinimoFaturamento;
	private String indicadorFaturamento = ConstantesSistema.TODOS.toString();
	private String indicadorExistenciaRede = ConstantesSistema.TODOS.toString();
	private String indicadorExistenciaLigacao = ConstantesSistema.TODOS.toString();
	private String indicadorUso = ConstantesSistema.TODOS.toString();
	private String indicadorAtualizar;
	
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
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
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	public String getIndicadorExistenciaLigacao() {
		return indicadorExistenciaLigacao;
	}
	public void setIndicadorExistenciaLigacao(String indicadorExistenciaLigacao) {
		this.indicadorExistenciaLigacao = indicadorExistenciaLigacao;
	}
	public String getIndicadorExistenciaRede() {
		return indicadorExistenciaRede;
	}
	public void setIndicadorExistenciaRede(String indicadorExistenciaRede) {
		this.indicadorExistenciaRede = indicadorExistenciaRede;
	}
	public String getIndicadorFaturamento() {
		return indicadorFaturamento;
	}
	public void setIndicadorFaturamento(String indicadorFaturamento) {
		this.indicadorFaturamento = indicadorFaturamento;
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
	
	public void reset(){
		this.codigo = "";
		this.descricao = "";
		this.tipoPesquisa = "";
		this.descricaoAbreviada = "";
		this.consumoMinimoFaturamento = "";
		this.tipoPesquisa = ConstantesSistema.TIPO_PESQUISA_INICIAL.toString();
		this.indicadorExistenciaLigacao = ConstantesSistema.TODOS.toString();
		this.indicadorExistenciaRede = ConstantesSistema.TODOS.toString();
		this.indicadorFaturamento = ConstantesSistema.TODOS.toString();
		this.indicadorUso = ConstantesSistema.TODOS.toString();
	}
	public String getConsumoMinimoFaturamento() {
		return consumoMinimoFaturamento;
	}
	public void setConsumoMinimoFaturamento(String consumoMinimoFaturamento) {
		this.consumoMinimoFaturamento = consumoMinimoFaturamento;
	}
}
