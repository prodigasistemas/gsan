package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author 	Vinícius Medeiros 
 * @date  	21/05/2008
 */

public class AtualizarZonaPressaoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String distritoOperacionalID;
	private String distritoOperacionalDescricao;
	private String dataUltimaAlteracao;
	private String indicadorUso;

	public String getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(String dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getDistritoOperacionalDescricao() {
		return distritoOperacionalDescricao;
	}

	public void setDistritoOperacionalDescricao(String distritoOperacionalDescricao) {
		this.distritoOperacionalDescricao = distritoOperacionalDescricao;
	}

	public String getDistritoOperacionalID() {
		return distritoOperacionalID;
	}

	public void setDistritoOperacionalID(String distritoOperacionalID) {
		this.distritoOperacionalID = distritoOperacionalID;
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

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

}
