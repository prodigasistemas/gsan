package gcom.gui.portal;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

public class AcessarPortalActionForm extends ActionForm {

	private static final long serialVersionUID = -897961541167999297L;

	private String matricula;

	private String cpfCnpj;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public ActionErrors validate(HttpSession sessao, boolean validarCpfCnpj) {
		ActionErrors errors = new ActionErrors();

		if (matricula == null || matricula.trim().equals("")) {
			errors.add("matricula", new ActionError("errors.portal.obrigatorio", "a Matrícula do Imóvel"));
		}

		if (validarCpfCnpj && (cpfCnpj == null || cpfCnpj.trim().equals(""))) {
			errors.add("cpfCnpj", new ActionError("errors.portal.obrigatorio", "o CPF/CNPJ"));
		}

		return errors;
	}
}