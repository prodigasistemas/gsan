package gcom.gui.cadastro.geografico;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0005] MANTER MUNICIPIO
 * 
 * @author Kássia Albuquerque
 * @date 04/01/2007
 */


public class ManterMunicipioActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
private String idServicoPerfilTipo;


public String getIdServicoPerfilTipo() {
	return idServicoPerfilTipo;
}

public void setIdServicoPerfilTipo(String idServicoPerfilTipo) {
	this.idServicoPerfilTipo = idServicoPerfilTipo;
}



}
