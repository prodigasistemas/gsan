package gcom.gui.atendimentopublico.registroatendimento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;

/**
 * Esta classe tem por finalidade cancelar todas as atividades referentes ao processo de inserção de um R.A voltando
 * para o menu principal do sistema
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class CancelarInserirRegistroAtendimentoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

		
        HttpSession sessao = httpServletRequest.getSession(false);

      
        ActionForward retorno = null;
        
       
        retorno =  actionMapping.findForward("telaPrincipal");
       
        
       
        
        sessao.removeAttribute("statusWizard"); 
        
        sessao.removeAttribute("gis");	
        
        sessao.removeAttribute("origemGIS");
        
        sessao.removeAttribute("gisHelper");

        return retorno;
    }

}
