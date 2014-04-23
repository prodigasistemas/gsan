package gcom.gui.util.tabelaauxiliar.faixa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author gcom
 *
 */
public class TabelaAuxiliarFaixaInteiroActionForm extends ValidatorActionForm {
	
	private String menorFaixa;

	private String maiorFaixa;

	private String faixaCompleta;
	
	private String indicadorUso;
	
	private String id;

	private static final long serialVersionUID = 1L;

	/**
	 * @return Returns the faixaCompleta.
	 */
	public String getFaixaCompleta() {
		return faixaCompleta;
	}

	/**
	 * @param faixaCompleta The faixaCompleta to set.
	 */
	public void setFaixaCompleta(String faixaCompleta) {
		this.faixaCompleta = faixaCompleta;
	}

	

	
	/**
	 * @return Returns the maiorFaixa.
	 */
	public String getMaiorFaixa() {
		return maiorFaixa;
	}

	/**
	 * @param maiorFaixa The maiorFaixa to set.
	 */
	public void setMaiorFaixa(String maiorFaixa) {
		this.maiorFaixa = maiorFaixa;
	}

	/**
	 * @return Returns the menorFaixa.
	 */
	public String getMenorFaixa() {
		return menorFaixa;
	}

	/**
	 * @param menorFaixa The menorFaixa to set.
	 */
	public void setMenorFaixa(String menorFaixa) {
		this.menorFaixa = menorFaixa;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso The indicadorUso to set.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

}
