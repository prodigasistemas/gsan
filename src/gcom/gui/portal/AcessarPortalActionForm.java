package gcom.gui.portal;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

import gcom.util.Util;

public class AcessarPortalActionForm extends ActionForm {

	private static final long serialVersionUID = -897961541167999297L;

	private static final String ERR_CPF_CNPJ = "err-cpf-cnpj";
	private static final String ERR_SENHA = "err-senha";

	private String cpfOuCnpj;

	private String senha;

	public String getCpfOuCnpj() {
		return Util.removerSimbolosPontuacao(cpfOuCnpj);
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void reset() {
		this.cpfOuCnpj = null;
		this.senha = null;
	}

	public ActionErrors validate(HttpSession sessao) {
		ActionErrors errors = new ActionErrors();

		if (campoInvalido(cpfOuCnpj)) {
			errors.add(ERR_CPF_CNPJ, new ActionError("errors.portal.obrigatorio", "o CPF/CNPJ"));
		} else if (Util.cpfCnpjInvalido(Util.removerSimbolosPontuacao(cpfOuCnpj))) {
			errors.add(ERR_CPF_CNPJ, new ActionError("errors.portal.invalido", "CPF ou CNPJ"));
		}

		if (campoInvalido(senha)) {
			errors.add(ERR_SENHA, new ActionError("errors.portal.obrigatorio", "a Senha"));
		} else if (senha.length() < 8) {
			errors.add(ERR_SENHA, new ActionError("errors.portal.invalida", "Senha"));
		}

		return errors;
	}

	private boolean campoInvalido(String campo) {
		return campo == null || campo.trim().equals("");
	}
}