package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

public class InformarConsumoParametroActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String tipoParametro; 

	private String mesAnoReferencia;

	private String idCategoria;

	private String idSubCategoria;

	private String parametroMaxima;
	
	private String consumoArea;
	
	public String getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
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

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getConsumoArea() {
		return consumoArea;
	}

	public void setConsumoArea(String consumoArea) {
		this.consumoArea = consumoArea;
	}

	public String getParametroMaxima() {
		return parametroMaxima;
	}

	public void setParametroMaxima(String parametroMaxima) {
		this.parametroMaxima = parametroMaxima;
	}


}
