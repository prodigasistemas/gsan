package gcom.gui.relatorio.gerencial.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

public class GerarRelatorioResumoDistritoOperacionalActionForm extends
		ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	private String unidadeNegocio;
	private String gerenciaRegional;
	private String distritoOperacional;

	private String localidadeInicial;
	private String nomeLocalidadeInicial;

	private String setorComercialInicial;
	private String nomeSetorComercialInicial;

	private String localidadeFinal;
	private String nomeLocalidadeFinal;

	private String setorComercialFinal;
	private String nomeSetorComercialFinal;

	private String mesAno;

	private String tipo;

	public void reset() {

		this.localidadeInicial = null;
		this.nomeLocalidadeInicial = null;
		
		this.setorComercialInicial = null;
		this.nomeSetorComercialInicial = null;
		
		this.localidadeFinal = null;
		this.nomeLocalidadeFinal = null;
		
		this.setorComercialFinal = null;
		this.nomeSetorComercialFinal = null;
		
		this.gerenciaRegional = null;
		this.unidadeNegocio = null;
		this.distritoOperacional = null;
		
		this.mesAno = null;
		
		this.tipo = "A";
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

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
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

	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(String setorComercialInicial) {
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
	




}
