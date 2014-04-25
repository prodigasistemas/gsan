package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 29/08/2006
 */
public class ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
		extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String municipio;

	private String sistemaAbastecimento;

	private String zonaAbastecimento;

	private String bairro;

	private String areaBairro;

	private String mes;

	private String ano;

	private String codigoMunicipio;

	private String codigoBairro;

	private String codigoSistemaAbastecimento;

	private String codigoZonaAbastecimento;

	private String codigoAreaBairro;

	/**
	 * @return Retorna o campo ano.
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * @param ano
	 *            O ano a ser setado.
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/**
	 * @return Retorna o campo mes.
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes
	 *            O mes a ser setado.
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

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
	 * @return Retorna o campo sistemaAbastecimento.
	 */
	public String getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}

	/**
	 * @param sistemaAbastecimento
	 *            O sistemaAbastecimento a ser setado.
	 */
	public void setSistemaAbastecimento(String sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}

	/**
	 * @return Retorna o campo zonaAbastecimento.
	 */
	public String getZonaAbastecimento() {
		return zonaAbastecimento;
	}

	/**
	 * @param zonaAbastecimento
	 *            O zonaAbastecimento a ser setado.
	 */
	public void setZonaAbastecimento(String zonaAbastecimento) {
		this.zonaAbastecimento = zonaAbastecimento;
	}

	/**
	 * @return Retorna o campo codigoAreaBairro.
	 */
	public String getCodigoAreaBairro() {
		return codigoAreaBairro;
	}

	/**
	 * @param codigoAreaBairro
	 *            O codigoAreaBairro a ser setado.
	 */
	public void setCodigoAreaBairro(String codigoAreaBairro) {
		this.codigoAreaBairro = codigoAreaBairro;
	}

	/**
	 * @return Retorna o campo codigoBairro.
	 */
	public String getCodigoBairro() {
		return codigoBairro;
	}

	/**
	 * @param codigoBairro
	 *            O codigoBairro a ser setado.
	 */
	public void setCodigoBairro(String codigoBairro) {
		this.codigoBairro = codigoBairro;
	}

	/**
	 * @return Retorna o campo codigoMunicipio.
	 */
	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	/**
	 * @param codigoMunicipio
	 *            O codigoMunicipio a ser setado.
	 */
	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	/**
	 * @return Retorna o campo codigoSistemaAbastecimento.
	 */
	public String getCodigoSistemaAbastecimento() {
		return codigoSistemaAbastecimento;
	}

	/**
	 * @param codigoSistemaAbastecimento
	 *            O codigoSistemaAbastecimento a ser setado.
	 */
	public void setCodigoSistemaAbastecimento(String codigoSistemaAbastecimento) {
		this.codigoSistemaAbastecimento = codigoSistemaAbastecimento;
	}

	/**
	 * @return Retorna o campo codigoZonaAbastecimento.
	 */
	public String getCodigoZonaAbastecimento() {
		return codigoZonaAbastecimento;
	}

	/**
	 * @param codigoZonaAbastecimento
	 *            O codigoZonaAbastecimento a ser setado.
	 */
	public void setCodigoZonaAbastecimento(String codigoZonaAbastecimento) {
		this.codigoZonaAbastecimento = codigoZonaAbastecimento;
	}

}
