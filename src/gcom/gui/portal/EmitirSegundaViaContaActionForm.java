package gcom.gui.portal;

import org.apache.struts.validator.ValidatorActionForm;

public class EmitirSegundaViaContaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String cpfCnpjSolicitante;

	private String valorDebito;

	private String data;

	private String valorDebitoCobrado;

	private String matricula;

	private String nomeUsuario;
	
	public String getCpfCnpjSolicitante() {
		return cpfCnpjSolicitante;
	}

	public void setCpfCnpjSolicitante(String cpfCnpjSolicitante) {
		this.cpfCnpjSolicitante = cpfCnpjSolicitante;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

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

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getValorDebitoCobrado() {
		return valorDebitoCobrado;
	}

	public void setValorDebitoCobrado(String valorDebitoCobrado) {
		this.valorDebitoCobrado = valorDebitoCobrado;
	}
}
