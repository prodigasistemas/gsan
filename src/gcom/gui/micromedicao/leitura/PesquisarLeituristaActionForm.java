package gcom.gui.micromedicao.leitura;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * Form utilizado na pesquisa de contas de um imóvel 
 *
 * @author Francisco Nascimento 
 * @date 03/08/2007
 */
public class PesquisarLeituristaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String empresa;
	private String idFuncionario;
	private String nomeFuncionario;
	private String idCliente;
	private String nomeCliente;
	private String DDDTelefone;
	private String numeroTelefone;
	/**
	 * @return Returns the dDDTelefone.
	 */
	public String getDDDTelefone() {
		return DDDTelefone;
	}
	/**
	 * @param telefone The dDDTelefone to set.
	 */
	public void setDDDTelefone(String telefone) {
		DDDTelefone = telefone;
	}
	/**
	 * @return Returns the empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa The empresa to set.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return Returns the idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}
	/**
	 * @param idCliente The idCliente to set.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	/**
	 * @return Returns the idFuncionario.
	 */
	public String getIdFuncionario() {
		return idFuncionario;
	}
	/**
	 * @param idFuncionario The idFuncionario to set.
	 */
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return Returns the nomeFuncionario.
	 */
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	/**
	 * @param nomeFuncionario The nomeFuncionario to set.
	 */
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	/**
	 * @return Returns the numeroTelefone.
	 */
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	/**
	 * @param numeroTelefone The numeroTelefone to set.
	 */
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}


}
