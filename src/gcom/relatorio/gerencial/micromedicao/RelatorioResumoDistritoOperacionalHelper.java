package gcom.relatorio.gerencial.micromedicao;

import java.io.Serializable;

public class RelatorioResumoDistritoOperacionalHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String distritoOperacional;
	private String descDistritoOperacional;
	private String comHidrometro;
	private String semHidrometro;
	private String situacaoLigadas;
	private String situacaoCortadas;
	private String situacaoTotal;
	private String situacaoFactiveis;
	private String situacaoPotenciais;
	private String economias;
	private String volumeReal;
	private String faturadosComHidro;
	private String faturadosSemHidro;
	private String faturadosTotal;
	public String getComHidrometro() {
		return comHidrometro;
	}
	public void setComHidrometro(String comHidrometro) {
		this.comHidrometro = comHidrometro;
	}
	public String getDistritoOperacional() {
		return distritoOperacional;
	}
	public void setDistritoOperacional(String distritoOperacional) {
		this.distritoOperacional = distritoOperacional;
	}
	public String getEconomias() {
		return economias;
	}
	public void setEconomias(String economias) {
		this.economias = economias;
	}
	public String getFaturadosComHidro() {
		return faturadosComHidro;
	}
	public void setFaturadosComHidro(String faturadosComHidro) {
		this.faturadosComHidro = faturadosComHidro;
	}
	public String getFaturadosSemHidro() {
		return faturadosSemHidro;
	}
	public void setFaturadosSemHidro(String faturadosSemHidro) {
		this.faturadosSemHidro = faturadosSemHidro;
	}
	public String getFaturadosTotal() {
		return faturadosTotal;
	}
	public void setFaturadosTotal(String faturadosTotal) {
		this.faturadosTotal = faturadosTotal;
	}
	public String getSemHidrometro() {
		return semHidrometro;
	}
	public void setSemHidrometro(String semHidrometro) {
		this.semHidrometro = semHidrometro;
	}
	public String getSituacaoCortadas() {
		return situacaoCortadas;
	}
	public void setSituacaoCortadas(String situacaoCortadas) {
		this.situacaoCortadas = situacaoCortadas;
	}
	public String getSituacaoFactiveis() {
		return situacaoFactiveis;
	}
	public void setSituacaoFactiveis(String situacaoFactiveis) {
		this.situacaoFactiveis = situacaoFactiveis;
	}
	public String getSituacaoLigadas() {
		return situacaoLigadas;
	}
	public void setSituacaoLigadas(String situacaoLigadas) {
		this.situacaoLigadas = situacaoLigadas;
	}
	public String getSituacaoPotenciais() {
		return situacaoPotenciais;
	}
	public void setSituacaoPotenciais(String situacaoPotenciais) {
		this.situacaoPotenciais = situacaoPotenciais;
	}
	public String getSituacaoTotal() {
		return situacaoTotal;
	}
	public void setSituacaoTotal(String situacaoTotal) {
		this.situacaoTotal = situacaoTotal;
	}
	public String getVolumeReal() {
		return volumeReal;
	}
	public void setVolumeReal(String volumeReal) {
		this.volumeReal = volumeReal;
	}
	public String getDescDistritoOperacional() {
		return descDistritoOperacional;
	}
	public void setDescDistritoOperacional(String descDistritoOperacional) {
		this.descDistritoOperacional = descDistritoOperacional;
	}
	


}
