package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

public class FiltrarLeiturasTelemetriaForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String situacaoLeitura;
	private String periodoEnvioInicial;
	private String periodoEnvioFinal;
	private String periodoLeituraInicial;
	private String periodoLeituraFinal;
	
	public String getSituacaoLeitura() {
		return situacaoLeitura;
	}
	public void setSituacaoLeitura(String situacaoLeitura) {
		this.situacaoLeitura = situacaoLeitura;
	}
	public String getPeriodoEnvioInicial() {
		return periodoEnvioInicial;
	}
	public void setPeriodoEnvioInicial(String periodoEnvioInicial) {
		this.periodoEnvioInicial = periodoEnvioInicial;
	}
	public String getPeriodoEnvioFinal() {
		return periodoEnvioFinal;
	}
	public void setPeriodoEnvioFinal(String periodoEnvioFinal) {
		this.periodoEnvioFinal = periodoEnvioFinal;
	}
	public String getPeriodoLeituraInicial() {
		return periodoLeituraInicial;
	}
	public void setPeriodoLeituraInicial(String periodoLeituraInicial) {
		this.periodoLeituraInicial = periodoLeituraInicial;
	}
	public String getPeriodoLeituraFinal() {
		return periodoLeituraFinal;
	}
	public void setPeriodoLeituraFinal(String periodoLeituraFinal) {
		this.periodoLeituraFinal = periodoLeituraFinal;
	}
	
	
	
}
