package gcom.gui.atendimentopublico;


import org.apache.struts.validator.ValidatorActionForm;

/** * [UC0864] Gerar Certidao Negativa por Cliente *  * @author Rafael Corrêa * * @date 22/09/2008 */

public class GerarCertidaoNegativaClienteActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idCliente;
	private String cpfCnpj;
	private String nomeCliente;		private String responsavel;	public String getResponsavel() {		return responsavel;	}	public void setResponsavel(String responsavel) {		this.responsavel = responsavel;	}	/**	 * @return Retorna o campo cpfCnpj.	 */	public String getCpfCnpj() {		return cpfCnpj;	}	/**	 * @param cpfCnpj O cpfCnpj a ser setado.	 */	public void setCpfCnpj(String cpfCnpj) {		this.cpfCnpj = cpfCnpj;	}	/**	 * @return Retorna o campo idCliente.	 */	public String getIdCliente() {		return idCliente;	}	/**	 * @param idCliente O idCliente a ser setado.	 */	public void setIdCliente(String idCliente) {		this.idCliente = idCliente;	}	/**	 * @return Retorna o campo nomeCliente.	 */	public String getNomeCliente() {		return nomeCliente;	}	/**	 * @param nomeCliente O nomeCliente a ser setado.	 */	public void setNomeCliente(String nomeCliente) {		this.nomeCliente = nomeCliente;	}
}
