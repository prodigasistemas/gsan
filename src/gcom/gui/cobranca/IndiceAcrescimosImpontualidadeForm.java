package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de informar indices
 * de acrescimos por impontualidade.
 * 
 * @author Sávio Luiz
 * @date 26/09/2006
 */
public class IndiceAcrescimosImpontualidadeForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String mesAnoReferencia;

	private String percentualMulta;

	private String percentualJurosMora;

	private String percentualAtualizacaoMonetaria;

	private String fatorCorrecao;
	
	private String camposDesabilitados;
	
	private String indicadorJurosMensal;
	
	private String indicadorMultaMensal;
	
	private String percentualLimiteJuros;
	
	private String percentualLimiteMulta;

	public String getFatorCorrecao() {
		return fatorCorrecao;
	}

	public void setFatorCorrecao(String fatorCorrecao) {
		this.fatorCorrecao = fatorCorrecao;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getPercentualAtualizacaoMonetaria() {
		return percentualAtualizacaoMonetaria;
	}

	public void setPercentualAtualizacaoMonetaria(
			String percentualAtualizacaoMonetaria) {
		this.percentualAtualizacaoMonetaria = percentualAtualizacaoMonetaria;
	}

	public String getPercentualJurosMora() {
		return percentualJurosMora;
	}

	public void setPercentualJurosMora(String percentualJurosMora) {
		this.percentualJurosMora = percentualJurosMora;
	}

	public String getPercentualMulta() {
		return percentualMulta;
	}

	public void setPercentualMulta(String percentualMulta) {
		this.percentualMulta = percentualMulta;
	}

	public String getCamposDesabilitados() {
		return camposDesabilitados;
	}

	public void setCamposDesabilitados(String camposDesabilitados) {
		this.camposDesabilitados = camposDesabilitados;
	}

	/**
	 * @return Retorna o campo indicadorJurosMensal.
	 */
	public String getIndicadorJurosMensal() {
		return indicadorJurosMensal;
	}

	/**
	 * @param indicadorJurosMensal O indicadorJurosMensal a ser setado.
	 */
	public void setIndicadorJurosMensal(String indicadorJurosMensal) {
		this.indicadorJurosMensal = indicadorJurosMensal;
	}

	/**
	 * @return Retorna o campo indicadorMultaMensal.
	 */
	public String getIndicadorMultaMensal() {
		return indicadorMultaMensal;
	}

	/**
	 * @param indicadorMultaMensal O indicadorMultaMensal a ser setado.
	 */
	public void setIndicadorMultaMensal(String indicadorMultaMensal) {
		this.indicadorMultaMensal = indicadorMultaMensal;
	}

	/**
	 * @return Retorna o campo percentualLimiteJuros.
	 */
	public String getPercentualLimiteJuros() {
		return percentualLimiteJuros;
	}

	/**
	 * @param percentualLimiteJuros O percentualLimiteJuros a ser setado.
	 */
	public void setPercentualLimiteJuros(String percentualLimiteJuros) {
		this.percentualLimiteJuros = percentualLimiteJuros;
	}

	/**
	 * @return Retorna o campo percentualLimiteMulta.
	 */
	public String getPercentualLimiteMulta() {
		return percentualLimiteMulta;
	}

	/**
	 * @param percentualLimiteMulta O percentualLimiteMulta a ser setado.
	 */
	public void setPercentualLimiteMulta(String percentualLimiteMulta) {
		this.percentualLimiteMulta = percentualLimiteMulta;
	}
	
	
}
