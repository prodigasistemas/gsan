package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0835]FILTRAR LOCAL DE ARMAZENAGEM DO HIDROMETRO 
 * 
 * @author Arthur Carvalho
 * @date 07/08/2008
 */

public class FiltrarLocalArmazenagemHidrometroActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String indicadorUso;
	private String indicadorOficina;
	private String indicadorAtualizar;
	private String tipoPesquisa;

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
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
	public String getIndicadorOficina() {
		return indicadorOficina;
	}
	public void setIndicadorOficina(String indicadorOficina) {
		this.indicadorOficina = indicadorOficina;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	

}	
