package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0837]FILTRAR CARGO DO FUNCIONÁRIO
 * 
 * @author Arthur Carvalho
 * @date 11/08/2008
 */

public class FiltrarCargoFuncionarioActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descricao;
	private String descricaoAbreviada;
	private String indicadorUso;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	

}	
