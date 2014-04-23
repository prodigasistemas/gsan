package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0522] MANTER DISTRITO OPERACIONAL
 * 
 * @author Eduardo Bianchi
 * @date 31/01/2007
 */


public class ManterDistritoOperacionalActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
    private String descricao;
    private String descricaoAbreviada;
    private String indicadorUso;
    private String zonaAbastecimento;
    private String setorAbastecimento;
    
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
	public String getZonaAbastecimento() {
		return zonaAbastecimento;
	}
	public void setZonaAbastecimento(String zonaAbastecimento) {
		this.zonaAbastecimento = zonaAbastecimento;
	}



}
