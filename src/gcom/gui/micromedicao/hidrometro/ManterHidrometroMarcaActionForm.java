package gcom.gui.micromedicao.hidrometro;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0081] MANTER HIDROMETRO MARCA
 * 
 * @author Bruno Barros
 * @date 19/06/2007
 */


public class ManterHidrometroMarcaActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String idHidrometroMarca;

	public String getIdHidrometroMarca() {
		return idHidrometroMarca;
	}

	public void setIdHidrometroMarca(String idHidrometroMarca) {
		this.idHidrometroMarca = idHidrometroMarca;
	}

}
