package gcom.gui.cobranca.contratoparcelamento;

import org.apache.struts.validator.ValidatorActionForm;


public class PesquisarContratoParcelamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String numeroContrato;
	private String dataContrato;
	private String indicadorSituacao;
	private String loginUsuario;
	private String nomeUsuario;
	private String autocompleteCliente;
	
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getDataContrato() {
		return dataContrato;
	}
	public void setDataContrato(String dataContrato) {
		this.dataContrato = dataContrato;
	}
	public String getIndicadorSituacao() {
		return indicadorSituacao;
	}
	public void setIndicadorSituacao(String indicadorSituacao) {
		this.indicadorSituacao = indicadorSituacao;
	}
	public String getLoginUsuario() {
		return loginUsuario;
	}
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getAutocompleteCliente() {
		return autocompleteCliente;
	}
	public void setAutocompleteCliente(String autocompleteCliente) {
		this.autocompleteCliente = autocompleteCliente;
	}
	

	

}

