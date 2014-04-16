package gcom.gui.batch;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0780] Pesquisar Processo
 * 
 * @date 10/07/2008
 * @author Arthur Carvalho
 */

public class PesquisarProcessoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String descricaoAbreviada;
	
	private String idProcessoTipo;
	
	private String tipoPesquisa;

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

	public String getIdProcessoTipo() {
		return idProcessoTipo;
	}

	public void setIdProcessoTipo(String idProcessoTipo) {
		this.idProcessoTipo = idProcessoTipo;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
}
