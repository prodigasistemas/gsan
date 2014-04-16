package gcom.gui.batch;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form que manipula os dados do caso de uso Iniciar Processo
 * 
 * @author Rodrigo Silveira
 * @created 10/08/2006
 */
public class InserirProcessoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idProcessoTipo;

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		this.idProcessoTipo = null;

	}

	public String getIdProcessoTipo() {
		return idProcessoTipo;
	}

	public void setIdProcessoTipo(String idProcessoTipo) {
		this.idProcessoTipo = idProcessoTipo;
	}

}
