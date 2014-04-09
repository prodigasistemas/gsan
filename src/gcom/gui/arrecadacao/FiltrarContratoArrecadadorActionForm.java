package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Marcio Roberto
 * @date 15/03/2007
 */
public class FiltrarContratoArrecadadorActionForm extends ValidatorActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idContrato;
 
	private String idArrecadador;
	
	private String idClienteCombo;

	private String nomeClienteCombo;

	private String numeroContrato;

	private String idContaBancariaArrecadador; 
	
	private String bcoArrecadadorConta;

	private String agArrecadadorConta;

	private String numeroArrecadadorConta;

	private String idContaBancariaTarifa;
	
	private String bcoTarifaConta;

	private String agTarifaConta;

	private String numeroTarifaConta;

	private String idCliente;
	
	private String nomeCliente;

	private String emailCliente;

	private String idConvenio;

	private String indicadorCobranca;

	private String dtInicioContrato;
	
	private String dtFimContrato;
	
	private String descricaoEmail;
	
	private String dataContratoEncerramento;
	
	private String contratoMotivoCancelamento;
	
	public String getContratoMotivoCancelamento() {
		return contratoMotivoCancelamento;
	}

	public void setContratoMotivoCancelamento(String contratoMotivoCancelamento) {
		this.contratoMotivoCancelamento = contratoMotivoCancelamento;
	}

	public String getDataContratoEncerramento() {
		return dataContratoEncerramento;
	}

	public void setDataContratoEncerramento(String dataContratoEncerramento) {
		this.dataContratoEncerramento = dataContratoEncerramento;
	}

	public String getDescricaoEmail() {
		return descricaoEmail;
	}

	public void setDescricaoEmail(String descricaoEmail) {
		this.descricaoEmail = descricaoEmail;
	}

	public String getAgArrecadadorConta() {
		return agArrecadadorConta;
	}

	public void setAgArrecadadorConta(String agArrecadadorConta) {
		this.agArrecadadorConta = agArrecadadorConta;
	}

	public String getAgTarifaConta() {
		return agTarifaConta;
	}

	public void setAgTarifaConta(String agTarifaConta) {
		this.agTarifaConta = agTarifaConta;
	}

	public String getBcoArrecadadorConta() {
		return bcoArrecadadorConta;
	}

	public void setBcoArrecadadorConta(String bcoArrecadadorConta) {
		this.bcoArrecadadorConta = bcoArrecadadorConta;
	}

	public String getBcoTarifaConta() {
		return bcoTarifaConta;
	}

	public void setBcoTarifaConta(String bcoTarifaConta) {
		this.bcoTarifaConta = bcoTarifaConta;
	}

	public String getDtFimContrato() {
		return dtFimContrato;
	}

	public void setDtFimContrato(String dtFimContrato) {
		this.dtFimContrato = dtFimContrato;
	}

	public String getDtInicioContrato() {
		return dtInicioContrato;
	}

	public void setDtInicioContrato(String dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}

	public String getIndicadorCobranca() {
		return indicadorCobranca;
	}

	public void setIndicadorCobranca(String indicadorCobranca) {
		this.indicadorCobranca = indicadorCobranca;
	}

	public String getNumeroArrecadadorConta() {
		return numeroArrecadadorConta;
	}

	public void setNumeroArrecadadorConta(String numeroArrecadadorConta) {
		this.numeroArrecadadorConta = numeroArrecadadorConta;
	}

	public String getNumeroTarifaConta() {
		return numeroTarifaConta;
	}

	public void setNumeroTarifaConta(String numeroTarifaConta) {
		this.numeroTarifaConta = numeroTarifaConta;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeClienteCombo() {
		return nomeClienteCombo;
	}

	public void setNomeClienteCombo(String nomeClienteCombo) {
		this.nomeClienteCombo = nomeClienteCombo;
	}

	public String getIdClienteCombo() {
		return idClienteCombo;
	}

	public void setIdClienteCombo(String idClienteCombo) {
		this.idClienteCombo = idClienteCombo;
	}

	public String getIdContaBancariaArrecadador() {
		return idContaBancariaArrecadador;
	}

	public void setIdContaBancariaArrecadador(String idContaBancariaArrecadador) {
		this.idContaBancariaArrecadador = idContaBancariaArrecadador;
	}

	public String getIdContaBancariaTarifa() {
		return idContaBancariaTarifa;
	}

	public void setIdContaBancariaTarifa(String idContaBancariaTarifa) {
		this.idContaBancariaTarifa = idContaBancariaTarifa;
	}
}
