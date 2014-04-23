package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC]Filtrar Imovel
 * 
 * @author Rafael Santos
 * @created 04/07/2006
 */
public class FiltrarImovelActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String idLocalidadeFiltro;
	private String localidadeDescricaoFiltro;
	private String idSetorComercialFiltro;
	private String idImovelFiltro;
	private String setorComercialDescricaoFiltro;
	private String idQuadraFiltro;
	private String quadraDescricaoFiltro;
	private String loteFiltro;
	private String subLoteFiltro;
	private String idClienteFiltro;
	private String nomeClienteFiltro;
	private String atualizarFiltro;
	private String matriculaFiltro;
	
	private String cepFiltro;
	private String cepDescricaoFiltro;
	private String idBairroFiltro;
	private String bairroFiltro;
	private String idMunicipioFiltro;
	private String municipioFiltro;
	private String idLogradouroFiltro;
	private String logradouroFiltro;
	
	private String numeroImovelInicialFiltro;
	private String numeroImovelFinalFiltro;
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
	 * @return Retorna o campo bairroFiltro.
	 */
	public String getBairroFiltro() {
		return bairroFiltro;
	}
	/**
	 * @param bairroFiltro O bairroFiltro a ser setado.
	 */
	public void setBairroFiltro(String bairroFiltro) {
		this.bairroFiltro = bairroFiltro;
	}
	/**
	 * @return Retorna o campo cepFiltro.
	 */
	public String getCepFiltro() {
		return cepFiltro;
	}
	/**
	 * @param cepFiltro O cepFiltro a ser setado.
	 */
	public void setCepFiltro(String cepFiltro) {
		this.cepFiltro = cepFiltro;
	}
	/**
	 * @return Retorna o campo idBairroFiltro.
	 */
	public String getIdBairroFiltro() {
		return idBairroFiltro;
	}
	/**
	 * @param idBairroFiltro O idBairroFiltro a ser setado.
	 */
	public void setIdBairroFiltro(String idBairroFiltro) {
		this.idBairroFiltro = idBairroFiltro;
	}
	/**
	 * @return Retorna o campo idClienteFiltro.
	 */
	public String getIdClienteFiltro() {
		return idClienteFiltro;
	}
	/**
	 * @param idClienteFiltro O idClienteFiltro a ser setado.
	 */
	public void setIdClienteFiltro(String idClienteFiltro) {
		this.idClienteFiltro = idClienteFiltro;
	}
	/**
	 * @return Retorna o campo idImovelFiltro.
	 */
	public String getIdImovelFiltro() {
		return idImovelFiltro;
	}
	/**
	 * @param idImovelFiltro O idImovelFiltro a ser setado.
	 */
	public void setIdImovelFiltro(String idImovelFiltro) {
		this.idImovelFiltro = idImovelFiltro;
	}
	/**
	 * @return Retorna o campo idLocalidadeFiltro.
	 */
	public String getIdLocalidadeFiltro() {
		return idLocalidadeFiltro;
	}
	/**
	 * @param idLocalidadeFiltro O idLocalidadeFiltro a ser setado.
	 */
	public void setIdLocalidadeFiltro(String idLocalidadeFiltro) {
		this.idLocalidadeFiltro = idLocalidadeFiltro;
	}
	/**
	 * @return Retorna o campo idLogradouroFiltro.
	 */
	public String getIdLogradouroFiltro() {
		return idLogradouroFiltro;
	}
	/**
	 * @param idLogradouroFiltro O idLogradouroFiltro a ser setado.
	 */
	public void setIdLogradouroFiltro(String idLogradouroFiltro) {
		this.idLogradouroFiltro = idLogradouroFiltro;
	}
	/**
	 * @return Retorna o campo idMunicipioFiltro.
	 */
	public String getIdMunicipioFiltro() {
		return idMunicipioFiltro;
	}
	/**
	 * @param idMunicipioFiltro O idMunicipioFiltro a ser setado.
	 */
	public void setIdMunicipioFiltro(String idMunicipioFiltro) {
		this.idMunicipioFiltro = idMunicipioFiltro;
	}
	/**
	 * @return Retorna o campo idQuadraFiltro.
	 */
	public String getIdQuadraFiltro() {
		return idQuadraFiltro;
	}
	/**
	 * @param idQuadraFiltro O idQuadraFiltro a ser setado.
	 */
	public void setIdQuadraFiltro(String idQuadraFiltro) {
		this.idQuadraFiltro = idQuadraFiltro;
	}
	/**
	 * @return Retorna o campo idSetorComercialFiltro.
	 */
	public String getIdSetorComercialFiltro() {
		return idSetorComercialFiltro;
	}
	/**
	 * @param idSetorComercialFiltro O idSetorComercialFiltro a ser setado.
	 */
	public void setIdSetorComercialFiltro(String idSetorComercialFiltro) {
		this.idSetorComercialFiltro = idSetorComercialFiltro;
	}
	/**
	 * @return Retorna o campo localidadeDescricaoFiltro.
	 */
	public String getLocalidadeDescricaoFiltro() {
		return localidadeDescricaoFiltro;
	}
	/**
	 * @param localidadeDescricaoFiltro O localidadeDescricaoFiltro a ser setado.
	 */
	public void setLocalidadeDescricaoFiltro(String localidadeDescricaoFiltro) {
		this.localidadeDescricaoFiltro = localidadeDescricaoFiltro;
	}
	/**
	 * @return Retorna o campo logradouroFiltro.
	 */
	public String getLogradouroFiltro() {
		return logradouroFiltro;
	}
	/**
	 * @param logradouroFiltro O logradouroFiltro a ser setado.
	 */
	public void setLogradouroFiltro(String logradouroFiltro) {
		this.logradouroFiltro = logradouroFiltro;
	}
	/**
	 * @return Retorna o campo loteFiltro.
	 */
	public String getLoteFiltro() {
		return loteFiltro;
	}
	/**
	 * @param loteFiltro O loteFiltro a ser setado.
	 */
	public void setLoteFiltro(String loteFiltro) {
		this.loteFiltro = loteFiltro;
	}
	/**
	 * @return Retorna o campo matriculaFiltro.
	 */
	public String getMatriculaFiltro() {
		return matriculaFiltro;
	}
	/**
	 * @param matriculaFiltro O matriculaFiltro a ser setado.
	 */
	public void setMatriculaFiltro(String matriculaFiltro) {
		this.matriculaFiltro = matriculaFiltro;
	}
	/**
	 * @return Retorna o campo municipioFiltro.
	 */
	public String getMunicipioFiltro() {
		return municipioFiltro;
	}
	/**
	 * @param municipioFiltro O municipioFiltro a ser setado.
	 */
	public void setMunicipioFiltro(String municipioFiltro) {
		this.municipioFiltro = municipioFiltro;
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
	 * @return Retorna o campo quadraDescricaoFiltro.
	 */
	public String getQuadraDescricaoFiltro() {
		return quadraDescricaoFiltro;
	}
	/**
	 * @param quadraDescricaoFiltro O quadraDescricaoFiltro a ser setado.
	 */
	public void setQuadraDescricaoFiltro(String quadraDescricaoFiltro) {
		this.quadraDescricaoFiltro = quadraDescricaoFiltro;
	}
	/**
	 * @return Retorna o campo setorComercialDescricaoFiltro.
	 */
	public String getSetorComercialDescricaoFiltro() {
		return setorComercialDescricaoFiltro;
	}
	/**
	 * @param setorComercialDescricaoFiltro O setorComercialDescricaoFiltro a ser setado.
	 */
	public void setSetorComercialDescricaoFiltro(
			String setorComercialDescricaoFiltro) {
		this.setorComercialDescricaoFiltro = setorComercialDescricaoFiltro;
	}
	/**
	 * @return Retorna o campo subLoteFiltro.
	 */
	public String getSubLoteFiltro() {
		return subLoteFiltro;
	}
	/**
	 * @param subLoteFiltro O subLoteFiltro a ser setado.
	 */
	public void setSubLoteFiltro(String subLoteFiltro) {
		this.subLoteFiltro = subLoteFiltro;
	}
	/**
	 * @return Retorna o campo cepDescricaoFiltro.
	 */
	public String getCepDescricaoFiltro() {
		return cepDescricaoFiltro;
	}
	/**
	 * @param cepDescricaoFiltro O cepDescricaoFiltro a ser setado.
	 */
	public void setCepDescricaoFiltro(String cepDescricaoFiltro) {
		this.cepDescricaoFiltro = cepDescricaoFiltro;
	}
	
	/**
	 * @return Retorna o campo numeroImovelFinal.
	 */
	public String getNumeroImovelFinalFiltro() {
		return numeroImovelFinalFiltro;
	}
	/**
	 * @param numeroImovelFinal O numeroImovelFinal a ser setado.
	 */
	public void setNumeroImovelFinalFiltro(String numeroImovelFinalFiltro) {
		this.numeroImovelFinalFiltro = numeroImovelFinalFiltro;
	}
	/**
	 * @return Retorna o campo numeroImovelInicial.
	 */
	public String getNumeroImovelInicialFiltro() {
		return numeroImovelInicialFiltro;
	}
	/**
	 * @param numeroImovelInicial O numeroImovelInicial a ser setado.
	 */
	public void setNumeroImovelInicialFiltro(String numeroImovelInicialFiltro) {
		this.numeroImovelInicialFiltro = numeroImovelInicialFiltro;
	}
	
}
