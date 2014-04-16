package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0438] Pesquisar Atividade
 * 
 * @author Ana Maria
 * @date 03/08/2006
 */
public class PesquisarAtividadeActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idAtividade;

	private String descricaoAtividade;

	private String abreviaturaAtividade;
	
	private String tipoPesquisa;
	
	private String tipoPesquisaAbreviada;

	public String getTipoPesquisaAbreviada() {
		return tipoPesquisaAbreviada;
	}

	public void setTipoPesquisaAbreviada(String tipoPesquisaAbreviada) {
		this.tipoPesquisaAbreviada = tipoPesquisaAbreviada;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getAbreviaturaAtividade() {
		return abreviaturaAtividade;
	}

	public void setAbreviaturaAtividade(String abreviaturaAtividade) {
		this.abreviaturaAtividade = abreviaturaAtividade;
	}

	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}

	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}

	public String getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}


}
