package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0006]	FILTRAR MUNICIPIO
 * 
 * @author Kássia Albuquerque
 * @date 03/01/2007
 */

public class FiltrarDistritoOperacionalActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String descricao;
    private String descricaoAbreviada;
    private String indicadorUso;
    private String sistemaAbastecimento;
    private String setorAbastecimento;
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
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getSetorAbastecimento() {
		return setorAbastecimento;
	}
	public void setSetorAbastecimento(String setorAbastecimento) {
		this.setorAbastecimento = setorAbastecimento;
	}
	public String getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}
	public void setSistemaAbastecimento(String sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}
    
    

}
