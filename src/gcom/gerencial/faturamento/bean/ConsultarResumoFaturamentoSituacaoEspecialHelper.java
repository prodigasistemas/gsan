package gcom.gerencial.faturamento.bean;

/**
 * Classe responsável por ajudar o caso de uso [UC0275] Gerar Resumo das Ligacoes/Economias 
 *
 * @author Thiago Toscano
 * @date 20/04/2006
 */
public class ConsultarResumoFaturamentoSituacaoEspecialHelper {

	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idLocalidadeInicial;
	private String codigoSetorComercialInicial;
	private String codigoRotaInicial;
	private String idLocalidadeFinal;
	private String codigoSetorComercialFinal;
	private String codigoRotaFinal;
	private Integer[] situacaoTipo;
	private Integer[] situacaoMotivo;
	
	/**
	 * @return Retorna o campo codigoRotaFinal.
	 */
	public String getCodigoRotaFinal() {
		return codigoRotaFinal;
	}
	/**
	 * @param codigoRotaFinal O codigoRotaFinal a ser setado.
	 */
	public void setCodigoRotaFinal(String codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}
	/**
	 * @return Retorna o campo codigoRotaInicial.
	 */
	public String getCodigoRotaInicial() {
		return codigoRotaInicial;
	}
	/**
	 * @param codigoRotaInicial O codigoRotaInicial a ser setado.
	 */
	public void setCodigoRotaInicial(String codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}
	/**
	 * @return Retorna o campo codigoSetorComercialFinal.
	 */
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	/**
	 * @param codigoSetorComercialFinal O codigoSetorComercialFinal a ser setado.
	 */
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	/**
	 * @return Retorna o campo codigoSetorComercialInicial.
	 */
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	/**
	 * @param codigoSetorComercialInicial O codigoSetorComercialInicial a ser setado.
	 */
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
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
	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	/**
	 * @return Retorna o campo situacaoMotivo.
	 */
	public Integer[] getSituacaoMotivo() {
		return situacaoMotivo;
	}
	/**
	 * @param situacaoMotivo O situacaoMotivo a ser setado.
	 */
	public void setSituacaoMotivo(Integer[] situacaoMotivo) {
		this.situacaoMotivo = situacaoMotivo;
	}
	/**
	 * @return Retorna o campo situacaoTipo.
	 */
	public Integer[] getSituacaoTipo() {
		return situacaoTipo;
	}
	/**
	 * @param situacaoTipo O situacaoTipo a ser setado.
	 */
	public void setSituacaoTipo(Integer[] situacaoTipo) {
		this.situacaoTipo = situacaoTipo;
	}

}
