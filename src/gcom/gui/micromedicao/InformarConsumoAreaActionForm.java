/**
 * 
 */
package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe 
 *
 * @author Rômulo Aurélio
 * @date 12/05/2008
 */
public class InformarConsumoAreaActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	
	private String categoria;
	
	private String subCategoria;

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

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	
	
}