package gcom.gui.arrecadacao.banco;

import org.apache.struts.action.ActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 2 de Junho de 2004
 */
public class ContaBancariaPesquisarActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idBanco;
	
	private String idAgencia;
	
	private String numeroConta;
	
	private String idBancoRecebido;

	public String getIdBancoRecebido() {
		return idBancoRecebido;
	}

	public void setIdBancoRecebido(String idBancoRecebido) {
		this.idBancoRecebido = idBancoRecebido;
	}

	public String getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(String idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	
}

