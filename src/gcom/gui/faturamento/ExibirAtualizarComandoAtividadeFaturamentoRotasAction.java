package gcom.gui.faturamento;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarComandoAtividadeFaturamentoRotasAction extends
        GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("atualizarComandoAtividadeFaturamentoRotas");
        
        return retorno;
    }
}
