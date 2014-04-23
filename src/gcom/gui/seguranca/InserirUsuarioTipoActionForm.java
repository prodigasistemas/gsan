package gcom.gui.seguranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 26/08/2008
 */
public class InserirUsuarioTipoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;

	Short indicadorFuncionario;
	
	Short indicadorUso;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorFuncionario() {
		return indicadorFuncionario;
	}

	public void setIndicadorFuncionario(Short indicadorFuncionario) {
		this.indicadorFuncionario = indicadorFuncionario;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	
	
}
