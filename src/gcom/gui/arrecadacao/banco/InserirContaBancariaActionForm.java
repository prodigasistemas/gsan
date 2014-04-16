package gcom.gui.arrecadacao.banco;

import org.apache.struts.action.ActionForm;

public class InserirContaBancariaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String banco;

	private String contaBanco;

	private String contaContabil;

	private String agenciaBancaria;

	public String getAgenciaBancaria() {
		return agenciaBancaria;
	}

	public void setAgenciaBancaria(String agenciaBancaria) {
		this.agenciaBancaria = agenciaBancaria;
	}

	public String getContaBanco() {
		return contaBanco;
	}

	public void setContaBanco(String contaBanco) {
		this.contaBanco = contaBanco;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(String contaContabil) {
		this.contaContabil = contaContabil;
	}

}
