package gcom.gui.arrecadacao.banco;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FiltrarAgenciaBancariaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String bancoID;
	
	private String codigo;
	
	private String nome;
	
	private String email;

	private String fax;

	private String ramal;

	private String telefone;

	private String[] localidadeSelectID;
	
	private String atualizar;
	
	private String tipoPesquisa;
	
	/**
	 * @return Retorna o campo tipoPesquisa.
	 */
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	/**
	 * @param tipoPesquisa O tipoPesquisa a ser setado.
	 */
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	/**
	 * @return Retorna o campo atualizar.
	 */
	public String getAtualizar() {
		return atualizar;
	}

	/**
	 * @param atualizar O atualizar a ser setado.
	 */
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
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

	public String getBancoID() {
		return bancoID;
	}

	public void setBancoID(String bancoID) {
		this.bancoID = bancoID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
