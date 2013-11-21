package gcom.micromedicao.bean;

import java.io.Serializable;
import java.util.Date;

public class FiltrarLeiturasTelemetriaHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String situacaoLeitura;
	private Date periodoEnvioInicial;
	private Date periodoEnvioFinal;
	private Date periodoLeituraInicial;
	private Date periodoLeituraFinal;
	private Integer numeroPagina;
	public String getSituacaoLeitura() {
		return situacaoLeitura;
	}
	public void setSituacaoLeitura(String situacaoLeitura) {
		this.situacaoLeitura = situacaoLeitura;
	}
	public Date getPeriodoEnvioInicial() {
		return periodoEnvioInicial;
	}
	public void setPeriodoEnvioInicial(Date periodoEnvioInicial) {
		this.periodoEnvioInicial = periodoEnvioInicial;
	}
	public Date getPeriodoEnvioFinal() {
		return periodoEnvioFinal;
	}
	public void setPeriodoEnvioFinal(Date periodoEnvioFinal) {
		this.periodoEnvioFinal = periodoEnvioFinal;
	}
	public Date getPeriodoLeituraInicial() {
		return periodoLeituraInicial;
	}
	public void setPeriodoLeituraInicial(Date periodoLeituraInicial) {
		this.periodoLeituraInicial = periodoLeituraInicial;
	}
	public Date getPeriodoLeituraFinal() {
		return periodoLeituraFinal;
	}
	public void setPeriodoLeituraFinal(Date periodoLeituraFinal) {
		this.periodoLeituraFinal = periodoLeituraFinal;
	}
	public Integer getNumeroPagina() {
		return numeroPagina;
	}
	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	} 
	
	
}
