package gcom.relatorio.cadastro.geografico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioManterBairroBean implements RelatorioBean {

	private String codigo;

	private String nome;

	private String municipio;

	private Integer codPref;

	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioManterBairroBean(String codigo, String nome,
			String municipio, Integer codPref, String indicadorUso) {
		this.codigo = codigo;
		this.nome = nome;
		this.municipio = municipio;
		this.codPref = codPref;
		this.indicadorUso = indicadorUso;

	}

	/**
	 * Gets the codigo attribute of the RelatorioManterBairroBean object
	 * 
	 * @return The codigo value
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo attribute of the RelatorioManterBairroBean object
	 * 
	 * @param codigo
	 *            The new codigo value
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the codPref attribute of the RelatorioManterBairroBean object
	 * 
	 * @return The codPref value
	 */
	public Integer getCodPref() {
		return this.codPref;
	}

	/**
	 * Gets the codPrefRelatorio attribute of the RelatorioManterBairroBean
	 * object
	 * 
	 * @return The codPrefRelatorio value
	 */
	public String getCodPrefRelatorio() {
		return (this.codPref == null) ? "" : "" + this.codPref;
	}

	/**
	 * Sets the codPref attribute of the RelatorioManterBairroBean object
	 * 
	 * @param codPref
	 *            The new codPref value
	 */
	public void setCodPref(Integer codPref) {
		this.codPref = codPref;
	}

	/**
	 * Gets the municicio attribute of the RelatorioManterBairroBean object
	 * 
	 * @return The municicio value
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * Sets the municicio attribute of the RelatorioManterBairroBean object
	 * 
	 * @param municipio
	 *            The new municipio value
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * Gets the nome attribute of the RelatorioManterBairroBean object
	 * 
	 * @return The nome value
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome attribute of the RelatorioManterBairroBean object
	 * 
	 * @param nome
	 *            The new nome value
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the indicadorUso attribute of the RelatorioManterBairroBean object
	 * 
	 * @return The indicadorUso value
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * Sets the indicadorUso attribute of the RelatorioManterBairroBean object
	 * 
	 * @param indicadorUso
	 *            The new indicadorUso value
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

}
