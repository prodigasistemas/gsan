package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

public class AdicionarConsumoParametroActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String mesAnoReferencia;

	private String idCategoria;

	private String idSubCategoria;

	private String numeroParametro;

	private String numeroConsumo;

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getNumeroConsumo() {
		return numeroConsumo;
	}

	public void setNumeroConsumo(String numeroConsumo) {
		this.numeroConsumo = numeroConsumo;
	}

	public String getNumeroParametro() {
		return numeroParametro;
	}

	public void setNumeroParametro(String numeroParametro) {
		this.numeroParametro = numeroParametro;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(String idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}


}
