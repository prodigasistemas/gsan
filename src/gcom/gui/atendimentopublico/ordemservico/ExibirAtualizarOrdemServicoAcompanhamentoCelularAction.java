package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.GcomAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
 * 
 * @author Thúlio Araújo
 * @date 26/09/2011
 */
public class ExibirAtualizarOrdemServicoAcompanhamentoCelularAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("atualizarOrdemServicoAcompanhamentoCelularAction");

        return retorno;
    }
}
