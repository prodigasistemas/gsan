package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 25/04/2006
 */

public class AtualizarAdicionarFuncionalidadeDependenciaActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String funcionalidade_Dependencia;
	
	private String descricaoFuncionalidade;

	private String comp_id;
	
	/**
	 * @return Retorna o campo comp_id.
	 */
	public String getComp_id() {
		return comp_id;
	}

	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * @return Retorna o campo funcionalidade_Dependencia.
	 */
	public String getFuncionalidade_Dependencia() {
		return funcionalidade_Dependencia;
	}

	/**
	 * @param funcionalidade_Dependencia O funcionalidade_Dependencia a ser setado.
	 */
	public void setFuncionalidade_Dependencia(String funcionalidade_Dependencia) {
		this.funcionalidade_Dependencia = funcionalidade_Dependencia;
	}

	public String getDescricaoFuncionalidade() {
		return descricaoFuncionalidade;
	}

	public void setDescricaoFuncionalidade(String descricaoFuncionalidade) {
		this.descricaoFuncionalidade = descricaoFuncionalidade;
	}
	
	
	
}
