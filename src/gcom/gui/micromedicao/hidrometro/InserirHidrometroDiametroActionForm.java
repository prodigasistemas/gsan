package gcom.gui.micromedicao.hidrometro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Vinicius Medeiros
 * @date 16/05/2007
 */
public class InserirHidrometroDiametroActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;

	String descricaoAbreviada;

	String numeroOrdem;

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

	public String getNumeroOrdem() {
		return numeroOrdem;
	}

	public void setNumeroOrdem(String numeroOrdem) {
		this.numeroOrdem = numeroOrdem;
	}

	
}
