package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 21/08/2006
 */
public class ConsultarProgramacaoAbastecimentoManutencaoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String municipio;

	private String nomeMunicipio;

	private String nomeBairro;

	private String bairro;

	private String areaBairro;

	private String mesAnoReferencia;

	/**
	 * @return Retorna o campo areaBairro.
	 */
	public String getAreaBairro() {
		return areaBairro;
	}

	/**
	 * @param areaBairro
	 *            O areaBairro a ser setado.
	 */
	public void setAreaBairro(String areaBairro) {
		this.areaBairro = areaBairro;
	}

	/**
	 * @return Retorna o campo bairro.
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro
	 *            O bairro a ser setado.
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return Retorna o campo mesAnoReferencia.
	 */
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	/**
	 * @param mesAnoReferencia
	 *            O mesAnoReferencia a ser setado.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	/**
	 * @return Retorna o campo municipio.
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            O municipio a ser setado.
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return Retorna o campo nomeBairro.
	 */
	public String getNomeBairro() {
		return nomeBairro;
	}

	/**
	 * @param nomeBairro
	 *            O nomeBairro a ser setado.
	 */
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	/**
	 * @return Retorna o campo nomeMunicipio.
	 */
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	/**
	 * @param nomeMunicipio
	 *            O nomeMunicipio a ser setado.
	 */
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

}
