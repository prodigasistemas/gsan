package gcom.gui.util.tabelaauxiliar.faixa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Rômulo Aurélio
 *
 */
public class TabelaAuxiliarFaixaRealActionForm extends ValidatorActionForm {
	
	private String VolumeMenorFaixa;

	private String VolumeMaiorFaixa;

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
	 * @return Returns the volumeMaiorFaixa.
	 */
	public String getVolumeMaiorFaixa() {
		return VolumeMaiorFaixa;
	}

	/**
	 * @param volumeMaiorFaixa The volumeMaiorFaixa to set.
	 */
	public void setVolumeMaiorFaixa(String volumeMaiorFaixa) {
		VolumeMaiorFaixa = volumeMaiorFaixa;
	}

	/**
	 * @return Returns the volumeMenorFaixa.
	 */
	public String getVolumeMenorFaixa() {
		return VolumeMenorFaixa;
	}

	/**
	 * @param volumeMenorFaixa The volumeMenorFaixa to set.
	 */
	public void setVolumeMenorFaixa(String volumeMenorFaixa) {
		VolumeMenorFaixa = volumeMenorFaixa;
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
