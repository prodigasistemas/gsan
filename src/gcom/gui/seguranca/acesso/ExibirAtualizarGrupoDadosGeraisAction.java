package gcom.gui.seguranca.acesso;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pré-exibição da primeira página
 * do processo de atualizar grupo.  
 *
 * @author Pedro Alexandre
 * @date 03/07/2006
 */
public class ExibirAtualizarGrupoDadosGeraisAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * [UC0279] - Manter Grupo
	 *
	 * @author Pedro Alexandre
	 * @date 03/07/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento para a tela de informar dados gerais do grupo
        ActionForward retorno = actionMapping.findForward("atualizarGrupoDadosGerais");
        return retorno;
    }
}
