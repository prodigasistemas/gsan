package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0133]FILTRAR BACIA
 * 
 * @author Arthur Carvalho
 * @date 21/05/2008
 */

public class FiltrarBaciaActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String indicadorUso;
	private String tipoPesquisa;
	
	private String sistemaEsgoto;
	private String indicadorAtualizar;
	
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
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
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getSistemaEsgoto() {
		return sistemaEsgoto;
	}
	public void setSistemaEsgoto(String sistemaEsgoto) {
		this.sistemaEsgoto = sistemaEsgoto;
	}
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	

}	
