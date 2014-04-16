package gcom.gui.faturamento.conta;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0820] Atualizar Faturamento do Movimento do Celular
 * 
 * @author Bruno Barros
 * @date 10/06/2009
 */

public class EmitirContasImpressaoTermicaActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	
	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	

}
