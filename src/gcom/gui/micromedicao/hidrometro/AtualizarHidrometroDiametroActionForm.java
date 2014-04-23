package gcom.gui.micromedicao.hidrometro;

import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author 	Vinícius Medeiros 
 * @date  	16/05/2008
 */

public class AtualizarHidrometroDiametroActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String numeroOrdem;
	private String dataUltimaAlteracao;
	private String indicadorUso;

	public String getNumeroOrdem() {
		return numeroOrdem;
	}

	public void setNumeroOrdem(String numeroOrdem) {
		this.numeroOrdem = numeroOrdem;
	}

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
