package gcom.gui.faturamento.conta;


import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Selecionar as quadras do ímovel
 * 
 * @author Ana Maria
 * @created 21/03/2007
 */
public class SelecionarQuadraImovelInserirManterContaAction extends GcomAction {
	
    
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("selecionarQuadraImovelInserirManterConta");
        
		InserirConjuntoQuadraActionForm inserirConjuntoQuadraActionForm = (InserirConjuntoQuadraActionForm) actionForm;

        // Array de strings com todos os Ids que serão removidos da tela Manter Material
        String[] ids = inserirConjuntoQuadraActionForm.getIdQuadras();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        // mensagem de erro quando o usuário tenta excluir sem ter selecionado nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }
        
        if(sessao.getAttribute("quadraSelecionada") != null){
        	sessao.removeAttribute("quadraSelecionada");
        }
        
        sessao.setAttribute("quadraSelecionada", ids);
		
    	//Fecha popup
    	httpServletRequest.setAttribute("fecharPopup", "OK");
    	
		return retorno;
	}
}
