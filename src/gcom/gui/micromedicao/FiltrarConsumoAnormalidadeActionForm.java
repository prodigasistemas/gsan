package gcom.gui.micromedicao;

import gcom.util.ConstantesSistema;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0808]Filtrar Anormalidade de Consumo
 * 
 * @author Vinicius Medeiros
 * @date 03/06/2008
 */

public class FiltrarConsumoAnormalidadeActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String indicadorUso = ConstantesSistema.TODOS.toString();
	private String tipoPesquisa = ConstantesSistema.TIPO_PESQUISA_INICIAL.toString();
	private String indicadorAtualizar;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
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

	

}
