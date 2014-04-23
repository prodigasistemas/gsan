package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de remover um objeto do tipo ParcelamentoQuantidadeReparcelamentoHelper 
 * da collectionParcelamentoQuantidadeReparcelamentoHelper
 * 
 * @author Vivianne Sousa
 * @created 09/05/2006
 */
public class RemoverParcelamentoQuantidadeReparcelamentoHelperAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 09/05/2006
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
    	
    	ActionForward retorno = null;
    	if (sessao.getAttribute("UseCase")!= null &&
    			sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
    		retorno = actionMapping.findForward("inserirPerfilRemoverParcelamentoQuantidadeReparcelamentoHelper");
    		//ParcelamentoPerfilActionForm parcelamentoPerfilActionForm = (ParcelamentoPerfilActionForm) actionForm;
    	}else if (sessao.getAttribute("UseCase")!= null &&
    			sessao.getAttribute("UseCase").equals("ATUALIZARPERFIL")){
    		retorno = actionMapping.findForward("atualizarPerfilRemoverParcelamentoQuantidadeReparcelamentoHelper");
    		//AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm = (AtualizarParcelamentoPerfilActionForm) actionForm;	
    	}
    	
    	sessao.getAttribute("ParcelamentoPerfilActionForm");
    	sessao.getAttribute("AtualizarParcelamentoPerfilActionForm");
    	
    	String quantidadeMaximaReparcelamento = httpServletRequest.getParameter("qtdeMaxReparcelamento");

        if (quantidadeMaximaReparcelamento != null && !quantidadeMaximaReparcelamento.equalsIgnoreCase("") &&
        	sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null){
        	        	
        	Collection collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
            					.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
        	
        	Collection collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas = null;
    		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas") != null
    				&& !sessao
    						.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas")
    						.equals("")) {
    			collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas = (Collection) sessao
    					.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");
    		} else {
    			collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas = new ArrayList();
    		}
    		
        	ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = null;
        	ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelperExcluir = null;
			Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();
						
			while (iterator.hasNext()) {
				parcelamentoQuantidadeReparcelamentoHelper = (ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();
		
				//procura na coleção a rotaAcaoCriterio que tem o idCobrancaAcao selecionado
				if (parcelamentoQuantidadeReparcelamentoHelper.getQuantidadeMaximaReparcelamento().toString().equals
						(quantidadeMaximaReparcelamento)){
					parcelamentoQuantidadeReparcelamentoHelperExcluir =  parcelamentoQuantidadeReparcelamentoHelper;
					collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas.add(parcelamentoQuantidadeReparcelamentoHelper);
					
				}
			}
			
			collectionParcelamentoQuantidadeReparcelamentoHelper.remove(parcelamentoQuantidadeReparcelamentoHelperExcluir);
       	 	sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper", 
       	 			collectionParcelamentoQuantidadeReparcelamentoHelper);
       	 	sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas",
       	 		collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas);
        }
        
		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") == null || 
			((Collection) sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper")).size() == 0){
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","1");
		}else{
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","2");
		}
        
       return retorno;
    }
    
 }
 
