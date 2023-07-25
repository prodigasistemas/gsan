package gcom.gui.portal;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

public class EmitirSegundaViaContaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String matricula;

	private String nomeUsuario;

	private String endereco;

	private String quantidadeContas;

	private String valorTotalContas;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(String quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public String getValorTotalContas() {
		return valorTotalContas;
	}

	public void setValorTotalContas(String valorTotalContas) {
		this.valorTotalContas = valorTotalContas;
	}

	public ActionErrors validate() {
		ActionErrors errors = new ActionErrors();

		if (matricula == null || matricula.trim().equals("")) {
			errors.add("erro-segunda-via", new ActionError("errors.portal.obrigatorio", "a Matrcula do Imvel"));
		}

		return errors;
	}
}
