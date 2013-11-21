package gcom.gui.relatorio.faturamento;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioContasEmRevisaoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idGerenciaRegional;
	
//	private String idElo;
//	
//	private String nomeElo;
	
	private String idLocalidadeInicial;
	
	private String nomeLocalidadeInicial;
	
	private String idLocalidadeFinal;
	
	private String nomeLocalidadeFinal;
	
	private String[] colecaoIdsContaMotivoRevisao;
	
	private String idImovelPerfil;

    private String referenciaInicial;
    
    private String referenciaFinal;
    
    private String idCategoria;
    
    private String idEsferaPoder;
    
    private String idUnidadeNegocio;
    
    private String codigoSetorComercialInicial;
    
    private String codigoSetorComercialFinal;
    
    private String nomeSetorComercialInicial;
    
    private String nomeSetorComercialFinal;

//	/**
//	 * @return Retorna o campo idElo.
//	 */
//	public String getIdElo() {
//		return idElo;
//	}
//
//	/**
//	 * @param idElo O idElo a ser setado.
//	 */
//	public void setIdElo(String idElo) {
//		this.idElo = idElo;
//	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idImovelPerfil.
	 */
	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}

	/**
	 * @param idImovelPerfil O idImovelPerfil a ser setado.
	 */
	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	/**
	 * @return Retorna o campo idLocalidadeFinal.
	 */
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	/**
	 * @param idLocalidadeFinal O idLocalidadeFinal a ser setado.
	 */
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	/**
	 * @return Retorna o campo idLocalidadeInicial.
	 */
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	/**
	 * @param idLocalidadeInicial O idLocalidadeInicial a ser setado.
	 */
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

//	/**
//	 * @return Retorna o campo nomeElo.
//	 */
//	public String getNomeElo() {
//		return nomeElo;
//	}
//
//	/**
//	 * @param nomeElo O nomeElo a ser setado.
//	 */
//	public void setNomeElo(String nomeElo) {
//		this.nomeElo = nomeElo;
//	}

	/**
	 * @return Retorna o campo nomeLocalidadeFinal.
	 */
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	/**
	 * @param nomeLocalidadeFinal O nomeLocalidadeFinal a ser setado.
	 */
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeInicial.
	 */
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	/**
	 * @param nomeLocalidadeInicial O nomeLocalidadeInicial a ser setado.
	 */
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	/**
	 * @return Retorna o campo referenciaFinal.
	 */
	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	/**
	 * @param referenciaFinal O referenciaFinal a ser setado.
	 */
	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	/**
	 * @return Retorna o campo referenciaInicial.
	 */
	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	/**
	 * @param referenciaInicial O referenciaInicial a ser setado.
	 */
	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	/**
	 * @return Retorna o campo idCategoria.
	 */
	public String getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

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
	
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	/**
	 * @return Retorna o campo colecaoIdsContaMotivoRevisao.
	 */
	public String[] getColecaoIdsContaMotivoRevisao() {
		return colecaoIdsContaMotivoRevisao;
	}

	/**
	 * @param colecaoIdsContaMotivoRevisao O colecaoIdsContaMotivoRevisao a ser setado.
	 */
	public void setColecaoIdsContaMotivoRevisao(
			String[] colecaoIdsContaMotivoRevisao) {
		this.colecaoIdsContaMotivoRevisao = colecaoIdsContaMotivoRevisao;
	}

}