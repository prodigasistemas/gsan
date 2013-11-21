package gcom.relatorio.gerencial.micromedicao;

import java.io.Serializable;

public class FiltrarRelatorioResumoDistritoOperacionalHelper implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private String unidadeNegocio;
	private String descUnidadeNegocio;

	private String gerenciaRegional;
	private String descGerenciaRegional;

	private String distritoOperacional;
	private String descDistritoOperacional;
	
	private Integer localidadeInicial;
	
	private String nomeLocalidadeInicial;

	private Integer setorComercialInicial;
	private Integer codigoSetorComercialInicial;
	private String nomeSetorComercialInicial;

	private Integer localidadeFinal;

	private String nomeLocalidadeFinal;
	
	private Integer setorComercialFinal;
	private Integer codigoSetorComercialFinal;
	private String nomeSetorComercialFinal;

	private String mesAno;

	private String tipo;

	public String getDescDistritoOperacional() {
		return descDistritoOperacional;
	}

	public void setDescDistritoOperacional(String descDistritoOperacional) {
		this.descDistritoOperacional = descDistritoOperacional;
	}

	public String getDescGerenciaRegional() {
		return descGerenciaRegional;
	}

	public void setDescGerenciaRegional(String descGerenciaRegional) {
		this.descGerenciaRegional = descGerenciaRegional;
	}

	public String getDescUnidadeNegocio() {
		return descUnidadeNegocio;
	}

	public void setDescUnidadeNegocio(String descUnidadeNegocio) {
		this.descUnidadeNegocio = descUnidadeNegocio;
	}

	public String getDistritoOperacional() {
		return distritoOperacional;
	}

	public void setDistritoOperacional(String distritoOperacional) {
		this.distritoOperacional = distritoOperacional;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
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

	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	
	
	

}