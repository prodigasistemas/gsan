package gcom.gui.micromedicao;

import gcom.cobranca.RotaAcaoCriterio;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de remover adicionar Criterio de Cobranca de Rota
 * 
 * @author Vivianne Sousa
 * @created 25/04/2006
 */
public class RemoverAdicionarCriterioCobrancaRotaAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 25/04/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	ActionForward retorno = actionMapping.findForward("atualizarRotaRemoverCriterioCobrancaRota");
    	
    	//InserirRotaActionForm inserirRotaActionForm = (InserirRotaActionForm) actionForm;
    	
    	if (sessao.getAttribute("UseCase")!= null &&
    			sessao.getAttribute("UseCase").equals("INSERIRROTA")){
    		retorno = actionMapping.findForward("inserirRotaRemoverCriterioCobrancaRota");	
    	}
    		
    	                                 
        String idCobrancaAcaoExcluir = httpServletRequest.getParameter("idCobrancaAcaoExcluir");
        
        if (idCobrancaAcaoExcluir != null && !idCobrancaAcaoExcluir.equalsIgnoreCase("") &&
        	sessao.getAttribute("collectionRotaAcaoCriterio") != null){
        	        	
        	Collection collectionRotaAcaoCriterio = (Collection) sessao
            					.getAttribute("collectionRotaAcaoCriterio");
        	
        	RotaAcaoCriterio rotaAcaoCriterio = null;
        	RotaAcaoCriterio rotaAcaoCriterioExcluir = null;
			Iterator iterator = collectionRotaAcaoCriterio.iterator();
						
			while (iterator.hasNext()) {
				rotaAcaoCriterio = (RotaAcaoCriterio) iterator.next();
		
				//procura na coleção a rotaAcaoCriterio que tem o idCobrancaAcao selecionado
				if (rotaAcaoCriterio.getCobrancaAcao().getId().equals(new Integer(idCobrancaAcaoExcluir))){
						rotaAcaoCriterioExcluir =  rotaAcaoCriterio;
//			            //HABILITA o botão adicionar
//			            httpServletRequest.setAttribute("adicionar","habilitado");
				}

			}
			
			collectionRotaAcaoCriterio.remove(rotaAcaoCriterioExcluir);
       	 	sessao.setAttribute("collectionRotaAcaoCriterio", collectionRotaAcaoCriterio);
       	 	
	        Collection collectionAcaoCobranca = (Collection) sessao.getAttribute("collectionAcaoCobranca");
	        if (collectionAcaoCobranca != null && 
	        		collectionRotaAcaoCriterio.size() != collectionAcaoCobranca.size()){
	        	httpServletRequest.setAttribute("adicionar","habilitado");
	        }else{
	        	httpServletRequest.setAttribute("adicionar","desabilitado");
			}
       	 	
        	
        }
        
       return retorno;
    }
    


}
 
