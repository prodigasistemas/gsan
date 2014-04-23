package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 2 de Junho de 2004
 */
public class PesquisarCriterioCobrancaLinhaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String criterioDescricao;

	public String getCriterioDescricao() {
		return criterioDescricao;
	}

	public void setCriterioDescricao(String criterioDescricao) {
		this.criterioDescricao = criterioDescricao;
	}


	
}

