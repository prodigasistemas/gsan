package gcom.gui.micromedicao.hidrometro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0139]	FILTRAR MARCA HIDROMETRO
 * 
 * @author Bruno Barros
 * @date 18/06/2007
 */

public class FiltrarHidrometroMarcaActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricaoMarcaHidrometro;
	private String tipoPesquisa;
	private String descricaoAbreviada;
	private String validadeRevisao;
	private String indicadorUso;	
	
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	public String getDescricaoMarcaHidrometro() {
		return descricaoMarcaHidrometro;
	}
	public void setDescricaoMarcaHidrometro(String descricaoMarcaHidrometro) {
		this.descricaoMarcaHidrometro = descricaoMarcaHidrometro;
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
	public String getValidadeRevisao() {
		return validadeRevisao;
	}
	public void setValidadeRevisao(String validadeRevisao) {
		this.validadeRevisao = validadeRevisao;
	}
	
	
}
