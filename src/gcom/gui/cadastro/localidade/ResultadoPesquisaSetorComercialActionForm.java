package gcom.gui.cadastro.localidade;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ResultadoPesquisaSetorComercialActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String Button;

    private String auxiliar;

    private String[] setorComercialID;

    public String getButton() {
        return Button;
    }

    public void setButton(String Button) {
        this.Button = Button;
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }

    public String[] getSetorComercialID() {
        return setorComercialID;
    }

    public void setSetorComercialID(String[] setorComercialID) {
        this.setorComercialID = setorComercialID;
    }

    public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }
}
