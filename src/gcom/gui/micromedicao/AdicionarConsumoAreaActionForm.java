/**
 * 
 */
package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe 
 *
 * @author Rômulo Aurélio
 * @date 14/05/2008
 */
public class AdicionarConsumoAreaActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	
	private String categoria;
	
	private String subCategoria;
	
	private String numeroArea;
	
	private String numeroConsumo;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getNumeroArea() {
		return numeroArea;
	}

	public void setNumeroArea(String numeroArea) {
		this.numeroArea = numeroArea;
	}

	public String getNumeroConsumo() {
		return numeroConsumo;
	}

	public void setNumeroConsumo(String numeroConsumo) {
		this.numeroConsumo = numeroConsumo;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

}
