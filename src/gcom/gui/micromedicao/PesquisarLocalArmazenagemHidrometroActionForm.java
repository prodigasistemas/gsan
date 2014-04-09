package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0780] Pesquisar Local de Armazenagem do Hidrometro
 * 
 * @date 10/09/2008
 * @author Arthur Carvalho
 */

public class PesquisarLocalArmazenagemHidrometroActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	
	private String descricao;
	
	private String descricaoAbreviada;
	
	private String indicadorOficina;
	
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

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getIndicadorOficina() {
		return indicadorOficina;
	}

	public void setIndicadorOficina(String indicadorOficina) {
		this.indicadorOficina = indicadorOficina;
	}
	
}
