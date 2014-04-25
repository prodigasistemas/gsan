package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 06/08/2008
 */
public class InserirLocalArmazenagemHidrometroActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	Integer id;
	
	String descricao;
	
	String descricaoAbreviada;
	
	Short indicadorOficina;
	
	String indicadorUso;

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

	public Short getIndicadorOficina() {
		return indicadorOficina;
	}

	public void setIndicadorOficina(Short indicadorOficina) {
		this.indicadorOficina = indicadorOficina;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
