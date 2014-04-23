package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorForm;

/**
 *  [UC0387] MANTER TIPO PERFIL SERVICO]
 * 
 * @author Kássia Albuquerque
 * @date 27/10/2006
 */


public class ManterTipoPerfilServicoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
private String idServicoPerfilTipo;


public String getIdServicoPerfilTipo() {
	return idServicoPerfilTipo;
}

public void setIdServicoPerfilTipo(String idServicoPerfilTipo) {
	this.idServicoPerfilTipo = idServicoPerfilTipo;
}



}
