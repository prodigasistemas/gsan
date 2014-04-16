package gcom.gui.faturamento;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para realização
 * da execução da atividade do cronograma 
 *
 * @author Raphael Rossiter
 * @date 30/03/2006
 */
public class ExecutarAtividadeFaturamentoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String[] idsFaturamentoAtividadeCronograma;

	
	public String[] getIdsFaturamentoAtividadeCronograma() {
		return idsFaturamentoAtividadeCronograma;
	}

	public void setIdsFaturamentoAtividadeCronograma(
			String[] idsFaturamentoAtividadeCronograma) {
		this.idsFaturamentoAtividadeCronograma = idsFaturamentoAtividadeCronograma;
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
