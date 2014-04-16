package gcom.gui.cadastro.cliente;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0000]Filtrar Cliente
 *
 * @author Roberta Costa
 * @date 04/07/2006
 */
public class FiltrarClienteActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String atualizarFiltro;
	private String cpfClienteFiltro;
	private String rgClienteFiltro;
	private String cnpjClienteFiltro;
	private String codigoClienteFiltro;
	private String nomeClienteFiltro;
	private String nomeMaeClienteFiltro;
	private String cepClienteFiltro;
	private String cepDescricaoClienteFiltro;
	private String municipioClienteFiltro;
	private String descricaoMunicipioClienteFiltro;
	private String bairroClienteFiltro;
	private String descricaoBairroClienteFiltro;
	private String logradouroClienteFiltro;
	private String descricaoLogradouroClienteFiltro;
	private String indicadorUsoClienteFiltro;
	private String tipoPesquisa;
	private String tipoPesquisaNomeMae;
	private String idEsferaPoder;
	
	/**
	 * @return Retorna o campo idEsferaPoder.
	 */
	public String getIdEsferaPoder() {
		return idEsferaPoder;
	}
	/**
	 * @param idEsferaPoder O idEsferaPoder a ser setado.
	 */
	public void setIdEsferaPoder(String idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}
	/**
	 * @return Retorna o campo atualizarFiltro.
	 */
	public String getAtualizarFiltro() {
		return atualizarFiltro;
	}
	/**
	 * @param atualizarFiltro O atualizarFiltro a ser setado.
	 */
	public void setAtualizarFiltro(String atualizarFiltro) {
		this.atualizarFiltro = atualizarFiltro;
	}
	/**
	 * @return Retorna o campo bairroClienteFiltro.
	 */
	public String getBairroClienteFiltro() {
		return bairroClienteFiltro;
	}
	/**
	 * @param bairroClienteFiltro O bairroClienteFiltro a ser setado.
	 */
	public void setBairroClienteFiltro(String bairroClienteFiltro) {
		this.bairroClienteFiltro = bairroClienteFiltro;
	}
	/**
	 * @return Retorna o campo cepClienteFiltro.
	 */
	public String getCepClienteFiltro() {
		return cepClienteFiltro;
	}
	/**
	 * @param cepClienteFiltro O cepClienteFiltro a ser setado.
	 */
	public void setCepClienteFiltro(String cepClienteFiltro) {
		this.cepClienteFiltro = cepClienteFiltro;
	}
	/**
	 * @return Retorna o campo cnpjClienteFiltro.
	 */
	public String getCnpjClienteFiltro() {
		return cnpjClienteFiltro;
	}
	/**
	 * @param cnpjClienteFiltro O cnpjClienteFiltro a ser setado.
	 */
	public void setCnpjClienteFiltro(String cnpjClienteFiltro) {
		this.cnpjClienteFiltro = cnpjClienteFiltro;
	}
	/**
	 * @return Retorna o campo codigoClienteFiltro.
	 */
	public String getCodigoClienteFiltro() {
		return codigoClienteFiltro;
	}
	/**
	 * @param codigoClienteFiltro O codigoClienteFiltro a ser setado.
	 */
	public void setCodigoClienteFiltro(String codigoClienteFiltro) {
		this.codigoClienteFiltro = codigoClienteFiltro;
	}
	/**
	 * @return Retorna o campo cpfClienteFiltro.
	 */
	public String getCpfClienteFiltro() {
		return cpfClienteFiltro;
	}
	/**
	 * @param cpfClienteFiltro O cpfClienteFiltro a ser setado.
	 */
	public void setCpfClienteFiltro(String cpfClienteFiltro) {
		this.cpfClienteFiltro = cpfClienteFiltro;
	}
	/**
	 * @return Retorna o campo descricaoBairroClienteFiltro.
	 */
	public String getDescricaoBairroClienteFiltro() {
		return descricaoBairroClienteFiltro;
	}
	/**
	 * @param descricaoBairroClienteFiltro O descricaoBairroClienteFiltro a ser setado.
	 */
	public void setDescricaoBairroClienteFiltro(String descricaoBairroClienteFiltro) {
		this.descricaoBairroClienteFiltro = descricaoBairroClienteFiltro;
	}
	/**
	 * @return Retorna o campo descricaoLogradouroClienteFiltro.
	 */
	public String getDescricaoLogradouroClienteFiltro() {
		return descricaoLogradouroClienteFiltro;
	}
	/**
	 * @param descricaoLogradouroClienteFiltro O descricaoLogradouroClienteFiltro a ser setado.
	 */
	public void setDescricaoLogradouroClienteFiltro(
			String descricaoLogradouroClienteFiltro) {
		this.descricaoLogradouroClienteFiltro = descricaoLogradouroClienteFiltro;
	}
	/**
	 * @return Retorna o campo descricaoMunicipioClienteFiltro.
	 */
	public String getDescricaoMunicipioClienteFiltro() {
		return descricaoMunicipioClienteFiltro;
	}
	/**
	 * @param descricaoMunicipioClienteFiltro O descricaoMunicipioClienteFiltro a ser setado.
	 */
	public void setDescricaoMunicipioClienteFiltro(
			String descricaoMunicipioClienteFiltro) {
		this.descricaoMunicipioClienteFiltro = descricaoMunicipioClienteFiltro;
	}
	/**
	 * @return Retorna o campo indicadorUsoClienteFiltro.
	 */
	public String getIndicadorUsoClienteFiltro() {
		return indicadorUsoClienteFiltro;
	}
	/**
	 * @param indicadorUsoClienteFiltro O indicadorUsoClienteFiltro a ser setado.
	 */
	public void setIndicadorUsoClienteFiltro(String indicadorUsoClienteFiltro) {
		this.indicadorUsoClienteFiltro = indicadorUsoClienteFiltro;
	}
	/**
	 * @return Retorna o campo logradouroClienteFiltro.
	 */
	public String getLogradouroClienteFiltro() {
		return logradouroClienteFiltro;
	}
	/**
	 * @param logradouroClienteFiltro O logradouroClienteFiltro a ser setado.
	 */
	public void setLogradouroClienteFiltro(String logradouroClienteFiltro) {
		this.logradouroClienteFiltro = logradouroClienteFiltro;
	}
	/**
	 * @return Retorna o campo municipioClienteFiltro.
	 */
	public String getMunicipioClienteFiltro() {
		return municipioClienteFiltro;
	}
	/**
	 * @param municipioClienteFiltro O municipioClienteFiltro a ser setado.
	 */
	public void setMunicipioClienteFiltro(String municipioClienteFiltro) {
		this.municipioClienteFiltro = municipioClienteFiltro;
	}
	/**
	 * @return Retorna o campo nomeClienteFiltro.
	 */
	public String getNomeClienteFiltro() {
		return nomeClienteFiltro;
	}
	/**
	 * @param nomeClienteFiltro O nomeClienteFiltro a ser setado.
	 */
	public void setNomeClienteFiltro(String nomeClienteFiltro) {
		this.nomeClienteFiltro = nomeClienteFiltro;
	}
	/**
	 * @return Retorna o campo rgClienteFiltro.
	 */
	public String getRgClienteFiltro() {
		return rgClienteFiltro;
	}
	/**
	 * @param rgClienteFiltro O rgClienteFiltro a ser setado.
	 */
	public void setRgClienteFiltro(String rgClienteFiltro) {
		this.rgClienteFiltro = rgClienteFiltro;
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
	/**
	 * @return Retorna o campo cepDescricaoClienteFiltro.
	 */
	public String getCepDescricaoClienteFiltro() {
		return cepDescricaoClienteFiltro;
	}
	/**
	 * @param cepDescricaoClienteFiltro O cepDescricaoClienteFiltro a ser setado.
	 */
	public void setCepDescricaoClienteFiltro(String cepDescricaoClienteFiltro) {
		this.cepDescricaoClienteFiltro = cepDescricaoClienteFiltro;
	}
	/**
	 * @return Retorna o campo nomeMaeClienteFiltro.
	 */
	public String getNomeMaeClienteFiltro() {
		return nomeMaeClienteFiltro;
	}
	/**
	 * @param nomeMaeClienteFiltro O nomeMaeClienteFiltro a ser setado.
	 */
	public void setNomeMaeClienteFiltro(String nomeMaeClienteFiltro) {
		this.nomeMaeClienteFiltro = nomeMaeClienteFiltro;
	}
	/**
	 * @return Retorna o campo tipoPesquisaNomeMae.
	 */
	public String getTipoPesquisaNomeMae() {
		return tipoPesquisaNomeMae;
	}
	/**
	 * @param tipoPesquisaNomeMae O tipoPesquisaNomeMae a ser setado.
	 */
	public void setTipoPesquisaNomeMae(String tipoPesquisaNomeMae) {
		this.tipoPesquisaNomeMae = tipoPesquisaNomeMae;
	}
	
}
