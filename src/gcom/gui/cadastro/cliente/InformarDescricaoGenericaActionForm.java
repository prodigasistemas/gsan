package gcom.gui.cadastro.cliente;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1114] Informar descrição genérica
 *
 * @author Mariana Victor
 * @date 28/12/2010
 * 
 */
public class InformarDescricaoGenericaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String descricao;


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
