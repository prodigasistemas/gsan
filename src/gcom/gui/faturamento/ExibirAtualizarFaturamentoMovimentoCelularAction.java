package gcom.gui.faturamento;

import gcom.gui.GcomAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0820] Atualizar Faturamento do Movimento do Celular
 * 
 * @author Bruno Barros
 * @date 10/06/2009
 */

public class ExibirAtualizarFaturamentoMovimentoCelularAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("atualizarFaturamentoMovimentoCelular");

        return retorno;
    }
}
