package gcom.gui.seguranca.acesso;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável por chamar o wizard para definir qual a próxima ação
 *
 * @author Pedro Alexandre
 * @date 15/06/2006
 */
public class InserirGrupoAcessosGrupoAction extends GcomAction {
	
    
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * [UC0278] - Inserir Grupo
     *
     * @author Pedro Alexandre
     * @date 15/06/2006
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

        
        ActionForward retorno = null;
        
     
        return retorno;
    }
}
