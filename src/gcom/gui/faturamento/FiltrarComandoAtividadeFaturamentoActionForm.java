package gcom.gui.faturamento;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FiltrarComandoAtividadeFaturamentoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    private String[] faturamentoAtividadeCronogramaID;

    public String[] getFaturamentoAtividadeCronogramaID() {
        return faturamentoAtividadeCronogramaID;
    }

    public void setFaturamentoAtividadeCronogramaID(
            String[] faturamentoAtividadeCronogramaID) {
        this.faturamentoAtividadeCronogramaID = faturamentoAtividadeCronogramaID;
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
