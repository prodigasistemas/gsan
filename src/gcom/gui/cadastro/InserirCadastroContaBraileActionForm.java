package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

public class InserirCadastroContaBraileActionForm extends
		ValidatorActionForm {

	/**
	 * 
	 * [UC1128] Solicitar Conta Braile
	 * 
	 * @author Hugo Leonardo
	 * @date 02/03/2011
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String nomeCliente;
	private String cpfCnpjCliente;
	private String email;
	private String nomeSolicitante;
	private String cpfSolicitante;
	private String telefoneContato;
	private String confirmarNomeCliente;
	private String confirmarCpfCnpjCliente;
	private String rg;
	private String orgaoExpeditor;
	private String dataExpedicao;
	private String unidadeFederacao;
	private Integer idCliente;
	
	//Valores criados para comparar se os valores foram alterados pelo usuario
	private String nomeClienteAux;
	private String cpfCnpjClienteAux;
	private String emailAux;
	
	private String desabilitaCampos;
	
	private boolean indicadorCpf = false;
	private boolean indicadorCnpj = false;
	
	public String getConfirmarCpfCnpjCliente() {
		return confirmarCpfCnpjCliente;
	}
	
	public void setConfirmarCpfCnpjCliente(String confirmarCpfCnpjCliente) {
		this.confirmarCpfCnpjCliente = confirmarCpfCnpjCliente;
	}

	public String getConfirmarNomeCliente() {
		return confirmarNomeCliente;
	}

	public void setConfirmarNomeCliente(String confirmarNomeCliente) {
		this.confirmarNomeCliente = confirmarNomeCliente;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getCpfCnpjClienteAux() {
		return cpfCnpjClienteAux;
	}

	public void setCpfCnpjClienteAux(String cpfCnpjClienteAux) {
		this.cpfCnpjClienteAux = cpfCnpjClienteAux;
	}

	public String getCpfSolicitante() {
		return cpfSolicitante;
	}

	public void setCpfSolicitante(String cpfSolicitante) {
		this.cpfSolicitante = cpfSolicitante;
	}

	public String getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(String dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public String getDesabilitaCampos() {
		return desabilitaCampos;
	}

	public void setDesabilitaCampos(String desabilitaCampos) {
		this.desabilitaCampos = desabilitaCampos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailAux() {
		return emailAux;
	}

	public void setEmailAux(String emailAux) {
		this.emailAux = emailAux;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public boolean isIndicadorCnpj() {
		return indicadorCnpj;
	}

	public void setIndicadorCnpj(boolean indicadorCnpj) {
		this.indicadorCnpj = indicadorCnpj;
	}

	public boolean isIndicadorCpf() {
		return indicadorCpf;
	}

	public void setIndicadorCpf(boolean indicadorCpf) {
		this.indicadorCpf = indicadorCpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeClienteAux() {
		return nomeClienteAux;
	}

	public void setNomeClienteAux(String nomeClienteAux) {
		this.nomeClienteAux = nomeClienteAux;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getOrgaoExpeditor() {
		return orgaoExpeditor;
	}

	public void setOrgaoExpeditor(String orgaoExpeditor) {
		this.orgaoExpeditor = orgaoExpeditor;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(String unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}
	
}
