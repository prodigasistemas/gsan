package gcom.gui.portal;

import org.apache.struts.validator.ValidatorActionForm;

public class ExibirLojasAtendimentoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String localidade;

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
}