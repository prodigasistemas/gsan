package gcom.gui.atendimentopublico.registroatendimento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;

/**
 * Esta classe tem por finalidade validar as informações da quarta aba do processo de atualização
 * de um registro de atendimento
 *
 * @author Raphael Rossiter
 * @date 27/07/2009
 */
public class AtualizarRegistroAtendimentoAnexosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("");
        
        
        return retorno;
	}
}
