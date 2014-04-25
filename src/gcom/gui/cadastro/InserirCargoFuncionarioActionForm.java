package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 11/08/2008
 */
public class InserirCargoFuncionarioActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String codigo;
	
	String descricao;
	
	String descricaoAbreviada;
	
	Short indicadorUso;
	
	public Short gerIndicadorUso(){
		return indicadorUso;
	}
	
	public void setIndicadorUso(Short indicadorUso){
		this.indicadorUso = indicadorUso;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}
	
	}
