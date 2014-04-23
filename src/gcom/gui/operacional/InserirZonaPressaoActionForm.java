package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Marcio Roberto
 * @date 26/01/2007
 */
public class InserirZonaPressaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;

	String descricaoAbreviada;
    
	private String distritoOperacionalDescricao;

    private String distritoOperacionalID;

	String IndicadorUso;

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
		return IndicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		IndicadorUso = indicadorUso;
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

	
}
