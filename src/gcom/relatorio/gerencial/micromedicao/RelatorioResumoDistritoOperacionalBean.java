package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoDistritoOperacionalBean implements RelatorioBean {
	private String distritoOperacional;
	private String descDistritoOperacional;
	private Integer comHidrometro;
	private Integer semHidrometro;
	private Integer situacaoLigadas;
	private Integer situacaoCortadas;
	private Integer situacaoTotal;
	private Integer situacaoFactiveis;
	private Integer situacaoPotenciais;
	private Integer economias;
	private Integer volumeReal;
	private Integer faturadosComHidro;
	private Integer faturadosSemHidro;
	private Integer faturadosTotal;
	
	
	public RelatorioResumoDistritoOperacionalBean(RelatorioResumoDistritoOperacionalHelper helper) {
		this.descDistritoOperacional = helper.getDescDistritoOperacional();
		this.distritoOperacional = helper.getDistritoOperacional();
		this.comHidrometro = new Integer(helper.getComHidrometro());
		this.semHidrometro = new Integer(helper.getSemHidrometro());
		this.situacaoLigadas = new Integer(helper.getSituacaoLigadas());
		this.situacaoCortadas = new Integer(helper.getSituacaoCortadas());
		this.situacaoTotal = new Integer(helper.getSituacaoTotal());
		this.situacaoFactiveis = new Integer(helper.getSituacaoFactiveis());
		this.situacaoPotenciais = new Integer(helper.getSituacaoPotenciais());
		this.economias = new Integer(helper.getEconomias());
		this.volumeReal = new Integer(helper.getVolumeReal());
		this.faturadosComHidro = new Integer(helper.getFaturadosComHidro());
		this.faturadosSemHidro = new Integer(helper.getFaturadosSemHidro());
		this.faturadosTotal = new Integer(helper.getFaturadosTotal());
	}


	public Integer getComHidrometro() {
		return comHidrometro;
	}


	public void setComHidrometro(Integer comHidrometro) {
		this.comHidrometro = comHidrometro;
	}


	public String getDescDistritoOperacional() {
		return descDistritoOperacional;
	}


	public void setDescDistritoOperacional(String descDistritoOperacional) {
		this.descDistritoOperacional = descDistritoOperacional;
	}


	public String getDistritoOperacional() {
		return distritoOperacional;
	}


	public void setDistritoOperacional(String distritoOperacional) {
		this.distritoOperacional = distritoOperacional;
	}


	public Integer getEconomias() {
		return economias;
	}


	public void setEconomias(Integer economias) {
		this.economias = economias;
	}


	public Integer getFaturadosComHidro() {
		return faturadosComHidro;
	}


	public void setFaturadosComHidro(Integer faturadosComHidro) {
		this.faturadosComHidro = faturadosComHidro;
	}


	public Integer getFaturadosSemHidro() {
		return faturadosSemHidro;
	}


	public void setFaturadosSemHidro(Integer faturadosSemHidro) {
		this.faturadosSemHidro = faturadosSemHidro;
	}


	public Integer getFaturadosTotal() {
		return faturadosTotal;
	}


	public void setFaturadosTotal(Integer faturadosTotal) {
		this.faturadosTotal = faturadosTotal;
	}


	public Integer getSemHidrometro() {
		return semHidrometro;
	}


	public void setSemHidrometro(Integer semHidrometro) {
		this.semHidrometro = semHidrometro;
	}


	public Integer getSituacaoCortadas() {
		return situacaoCortadas;
	}


	public void setSituacaoCortadas(Integer situacaoCortadas) {
		this.situacaoCortadas = situacaoCortadas;
	}


	public Integer getSituacaoFactiveis() {
		return situacaoFactiveis;
	}


	public void setSituacaoFactiveis(Integer situacaoFactiveis) {
		this.situacaoFactiveis = situacaoFactiveis;
	}


	public Integer getSituacaoLigadas() {
		return situacaoLigadas;
	}


	public void setSituacaoLigadas(Integer situacaoLigadas) {
		this.situacaoLigadas = situacaoLigadas;
	}


	public Integer getSituacaoPotenciais() {
		return situacaoPotenciais;
	}


	public void setSituacaoPotenciais(Integer situacaoPotenciais) {
		this.situacaoPotenciais = situacaoPotenciais;
	}


	public Integer getSituacaoTotal() {
		return situacaoTotal;
	}


	public void setSituacaoTotal(Integer situacaoTotal) {
		this.situacaoTotal = situacaoTotal;
	}


	public Integer getVolumeReal() {
		return volumeReal;
	}


	public void setVolumeReal(Integer volumeReal) {
		this.volumeReal = volumeReal;
	}




	
	
	
	
}
