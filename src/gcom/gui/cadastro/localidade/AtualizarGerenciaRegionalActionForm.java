package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AtualizarGerenciaRegionalActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String email;

	private String fax;

	// private String gerenciaID;

	private String indicadorUso;

	private String gerenciaRegionalID;

	private String nome;
	
	private String nomeAbreviado;

	private String ramal;

	private String telefone;

	private String[] localidadeSelectID;
	
	private String idCliente;
	
	private String nomeCliente;
	
	private String cnpjGerenciaRegional;

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getGerenciaRegionalID() {
		return gerenciaRegionalID;
	}

	public void setGerenciaRegionalID(String gerenciaRegionalID) {
		this.gerenciaRegionalID = gerenciaRegionalID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}
	
	

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	public String[] getLocalidadeSelectID() {
		return localidadeSelectID;
	}

	public void setLocalidadeSelectID(String[] localidadeSelectID) {
		this.localidadeSelectID = localidadeSelectID;
	}

	public String getCnpjGerenciaRegional() {
		return cnpjGerenciaRegional;
	}

	public void setCnpjGerenciaRegional(String cnpjGerenciaRegional) {
		this.cnpjGerenciaRegional = cnpjGerenciaRegional;
	}

}
