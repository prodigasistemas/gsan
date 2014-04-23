package gcom.gui.portal;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <p>
 * Classe Responsável por exibir o formulário de cadastro para solicitação de
 * contas por email na Loja Virtual da Compesa
 * </p>
 * 
 * @author Magno Gouveia
 * @date 18/05/2011
 */
public class InserirCadastroEmailClientePortalActionForm extends
		ValidatorActionForm {

	/**
	 * 
	 * [UC1036] Inserir Cadastro de Email do CLiente
	 * 
	 * @author Fernando Fontelles
	 * @date 07/07/2010
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
	
	private Integer idCliente;
	
	//Valores criados para comparar se os valores foram alterados pelo usuario
	private String nomeClienteAux;
	private String cpfCnpjClienteAux;
	private String emailAux;
	
	private String desabilitaCampos;
	
	private boolean indicadorCpf = false;
	private boolean indicadorCnpj = false;
	
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
	public String getConfirmarNomeCliente() {
		return confirmarNomeCliente;
	}
	public void setConfirmarNomeCliente(String confirmarNomeCliente) {
		this.confirmarNomeCliente = confirmarNomeCliente;
	}
	public String getCpfSolicitante() {
		return cpfSolicitante;
	}
	public void setCpfSolicitante(String cpfSolicitante) {
		this.cpfSolicitante = cpfSolicitante;
	}
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public String getTelefoneContato() {
		return telefoneContato;
	}
	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}
	public String getConfirmarCpfCnpjCliente() {
		return confirmarCpfCnpjCliente;
	}
	public void setConfirmarCpfCnpjCliente(String confirmarCpfCnpjCliente) {
		this.confirmarCpfCnpjCliente = confirmarCpfCnpjCliente;
	}
	public String getCpfCnpjClienteAux() {
		return cpfCnpjClienteAux;
	}
	public void setCpfCnpjClienteAux(String cpfCnpjClienteAux) {
		this.cpfCnpjClienteAux = cpfCnpjClienteAux;
	}
	public String getEmailAux() {
		return emailAux;
	}
	public void setEmailAux(String emailAux) {
		this.emailAux = emailAux;
	}
	public String getNomeClienteAux() {
		return nomeClienteAux;
	}
	public void setNomeClienteAux(String nomeClienteAux) {
		this.nomeClienteAux = nomeClienteAux;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getDesabilitaCampos() {
		return desabilitaCampos;
	}
	public void setDesabilitaCampos(String desabilitaCampos) {
		this.desabilitaCampos = desabilitaCampos;
	}
}
