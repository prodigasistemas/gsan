package gcom.relatorio.micromedicao;

import java.io.Serializable;


public class GerarDadosLeituraHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idGrupoFaturamento;
	private Integer idRota;
	private Integer idLocalidadeInicial;
	private Integer idLocalidadeFinal;
	private Integer codigoRota;
	
	/**
	 * @return Retorna o campo idGrupoFaturamento.
	 */
	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	/**
	 * @param idGrupoFaturamento O idGrupoFaturamento a ser setado.
	 */
	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	/**
	 * @return Retorna o campo idRota.
	 */
	public Integer getIdRota() {
		return idRota;
	}
	/**
	 * @param idRota O idRota a ser setado.
	 */
	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	/**
	 * @return Retorna o campo idLocalidadeFinal.
	 */
	public Integer getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	/**
	 * @param idLocalidadeFinal O idLocalidadeFinal a ser setado.
	 */
	public void setIdLocalidadeFinal(Integer idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	/**
	 * @return Retorna o campo idLocalidadeInicial.
	 */
	public Integer getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	/**
	 * @param idLocalidadeInicial O idLocalidadeInicial a ser setado.
	 */
	public void setIdLocalidadeInicial(Integer idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	/**
	 * @return Retorna o campo codigoRotaFinal.
	 */
	public Integer getCodigoRota() {
		return codigoRota;
	}
	/**
	 * @param codigoRotaFinal O codigoRotaFinal a ser setado.
	 */
	public void setCodigoRota(Integer codigoRotaFinal) {
		this.codigoRota = codigoRotaFinal;
	}
}
