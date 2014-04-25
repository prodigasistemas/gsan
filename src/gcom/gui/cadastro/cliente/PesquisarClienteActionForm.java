package gcom.gui.cadastro.cliente;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0000]Filtrar Cliente
 *
 * @author Roberta Costa
 * @date 12/07/2006
 */
public class PesquisarClienteActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String idTipoClientePesquisa;
	private String nomeClientePesquisa;
	private String cpfClientePesquisa;
	private String rgClientePesquisa;
	private String cnpjClientePesquisa;
	private String cepClientePesquisa;
	private String municipioClientePesquisa;
	private String descricaoMunicipioClientePesquisa;
	private String bairroClientePesquisa;
	private String descricaoBairroClientePesquisa;
	private String logradouroClientePesquisa;
	private String descricaoLogradouroClientePesquisa;
	private String tipoPesquisa;
	
	/**
	 * @return Retorna o campo bairroClientePesquisa.
	 */
	public String getBairroClientePesquisa() {
		return bairroClientePesquisa;
	}
	/**
	 * @param bairroClientePesquisa O bairroClientePesquisa a ser setado.
	 */
	public void setBairroClientePesquisa(String bairroClientePesquisa) {
		this.bairroClientePesquisa = bairroClientePesquisa;
	}
	/**
	 * @return Retorna o campo cepClientePesquisa.
	 */
	public String getCepClientePesquisa() {
		return cepClientePesquisa;
	}
	/**
	 * @param cepClientePesquisa O cepClientePesquisa a ser setado.
	 */
	public void setCepClientePesquisa(String cepClientePesquisa) {
		this.cepClientePesquisa = cepClientePesquisa;
	}
	/**
	 * @return Retorna o campo cnpjClientePesquisa.
	 */
	public String getCnpjClientePesquisa() {
		return cnpjClientePesquisa;
	}
	/**
	 * @param cnpjClientePesquisa O cnpjClientePesquisa a ser setado.
	 */
	public void setCnpjClientePesquisa(String cnpjClientePesquisa) {
		this.cnpjClientePesquisa = cnpjClientePesquisa;
	}
	/**
	 * @return Retorna o campo cpfClientePesquisa.
	 */
	public String getCpfClientePesquisa() {
		return cpfClientePesquisa;
	}
	/**
	 * @param cpfClientePesquisa O cpfClientePesquisa a ser setado.
	 */
	public void setCpfClientePesquisa(String cpfClientePesquisa) {
		this.cpfClientePesquisa = cpfClientePesquisa;
	}
	/**
	 * @return Retorna o campo descricaoBairroClientePesquisa.
	 */
	public String getDescricaoBairroClientePesquisa() {
		return descricaoBairroClientePesquisa;
	}
	/**
	 * @param descricaoBairroClientePesquisa O descricaoBairroClientePesquisa a ser setado.
	 */
	public void setDescricaoBairroClientePesquisa(
			String descricaoBairroClientePesquisa) {
		this.descricaoBairroClientePesquisa = descricaoBairroClientePesquisa;
	}
	/**
	 * @return Retorna o campo descricaoLogradouroClientePesquisa.
	 */
	public String getDescricaoLogradouroClientePesquisa() {
		return descricaoLogradouroClientePesquisa;
	}
	/**
	 * @param descricaoLogradouroClientePesquisa O descricaoLogradouroClientePesquisa a ser setado.
	 */
	public void setDescricaoLogradouroClientePesquisa(
			String descricaoLogradouroClientePesquisa) {
		this.descricaoLogradouroClientePesquisa = descricaoLogradouroClientePesquisa;
	}
	/**
	 * @return Retorna o campo descricaoMunicipioClientePesquisa.
	 */
	public String getDescricaoMunicipioClientePesquisa() {
		return descricaoMunicipioClientePesquisa;
	}
	/**
	 * @param descricaoMunicipioClientePesquisa O descricaoMunicipioClientePesquisa a ser setado.
	 */
	public void setDescricaoMunicipioClientePesquisa(
			String descricaoMunicipioClientePesquisa) {
		this.descricaoMunicipioClientePesquisa = descricaoMunicipioClientePesquisa;
	}
	/**
	 * @return Retorna o campo idTipoCliente.
	 */
	public String getIdTipoClientePesquisa() {
		return idTipoClientePesquisa;
	}
	/**
	 * @param idTipoCliente O idTipoCliente a ser setado.
	 */
	public void setIdTipoClientePesquisa(String idTipoClientePesquisa) {
		this.idTipoClientePesquisa = idTipoClientePesquisa;
	}
	/**
	 * @return Retorna o campo logradouroClientePesquisa.
	 */
	public String getLogradouroClientePesquisa() {
		return logradouroClientePesquisa;
	}
	/**
	 * @param logradouroClientePesquisa O logradouroClientePesquisa a ser setado.
	 */
	public void setLogradouroClientePesquisa(String logradouroClientePesquisa) {
		this.logradouroClientePesquisa = logradouroClientePesquisa;
	}
	/**
	 * @return Retorna o campo municipioClientePesquisa.
	 */
	public String getMunicipioClientePesquisa() {
		return municipioClientePesquisa;
	}
	/**
	 * @param municipioClientePesquisa O municipioClientePesquisa a ser setado.
	 */
	public void setMunicipioClientePesquisa(String municipioClientePesquisa) {
		this.municipioClientePesquisa = municipioClientePesquisa;
	}
	/**
	 * @return Retorna o campo nomeClientePesquisa.
	 */
	public String getNomeClientePesquisa() {
		return nomeClientePesquisa;
	}
	/**
	 * @param nomeClientePesquisa O nomeClientePesquisa a ser setado.
	 */
	public void setNomeClientePesquisa(String nomeClientePesquisa) {
		this.nomeClientePesquisa = nomeClientePesquisa;
	}
	/**
	 * @return Retorna o campo rgClientePesquisa.
	 */
	public String getRgClientePesquisa() {
		return rgClientePesquisa;
	}
	/**
	 * @param rgClientePesquisa O rgClientePesquisa a ser setado.
	 */
	public void setRgClientePesquisa(String rgClientePesquisa) {
		this.rgClientePesquisa = rgClientePesquisa;
	}
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
}
