package gcom.gui.seguranca;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0837]FILTRAR TIPO DE USUARIO
 * 
 * @author Arthur Carvalho
 * @date 25/08/2008
 */

public class FiltrarUsuarioTipoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String indicadorUso;
	private String indicadorFuncionario;
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
	public String getIndicadorFuncionario() {
		return indicadorFuncionario;
	}
	public void setIndicadorFuncionario(String indicadorFuncionario) {
		this.indicadorFuncionario = indicadorFuncionario;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


}	
