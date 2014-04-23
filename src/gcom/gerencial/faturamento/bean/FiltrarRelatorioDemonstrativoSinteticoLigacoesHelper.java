package gcom.gerencial.faturamento.bean;

import gcom.util.Util;

import java.io.Serializable;


/**
 * 
 * @author Daniel Alves
 * @date 12/04/2007
 */
public class FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer opcaoTotalizacao;
			
	private Integer anoMesReferencia;
	private Integer localidade;
	private Integer unidadeNegocio;
	private Integer gerenciaRegional;
	private Integer municipio;
	private String nomeMunicipio;
	
	
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getAnoMesReferenciaAnterior() {
		return Util.subtrairMesDoAnoMes(this.anoMesReferencia,1);
	}

	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(Integer opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}

	
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	
}
