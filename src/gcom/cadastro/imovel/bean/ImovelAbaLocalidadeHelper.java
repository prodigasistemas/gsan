package gcom.cadastro.imovel.bean;

import gcom.seguranca.acesso.usuario.Usuario;

public class ImovelAbaLocalidadeHelper {
	
	private Integer idImovel;
	private String idLocalidade;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String idQuadraFace;
	private String lote;
	private String sublote;
	private String testadaLote;
	private String sequencialRota;
	private Usuario usuario;

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Retorna o campo idQuadra.
	 */
	public String getNumeroQuadra() {
		return numeroQuadra;
	}
	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setNumeroQuadra(String idQuadra) {
		this.numeroQuadra = idQuadra;
	}
	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	/**
	 * @return Retorna o campo lote.
	 */
	public String getLote() {
		return lote;
	}
	/**
	 * @param lote O lote a ser setado.
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}
	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public String getSequencialRota() {
		return sequencialRota;
	}
	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	/**
	 * @return Retorna o campo sublote.
	 */
	public String getSublote() {
		return sublote;
	}
	/**
	 * @param sublote O sublote a ser setado.
	 */
	public void setSublote(String sublote) {
		this.sublote = sublote;
	}
	/**
	 * @return Retorna o campo testadaLote.
	 */
	public String getTestadaLote() {
		return testadaLote;
	}
	/**
	 * @param testadaLote O testadaLote a ser setado.
	 */
	public void setTestadaLote(String testadaLote) {
		this.testadaLote = testadaLote;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public String getIdQuadraFace() {
		return idQuadraFace;
	}
	public void setIdQuadraFace(String idQuadraFace) {
		this.idQuadraFace = idQuadraFace;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
