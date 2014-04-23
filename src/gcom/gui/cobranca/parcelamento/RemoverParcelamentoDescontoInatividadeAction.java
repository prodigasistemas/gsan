package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
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
 * Action de remover um objeto do tipo ParcelamentoDescontoInatividade 
 * da collectionParcelamentoDescontoInatividade
 * 
 * @author Vivianne Sousa
 * @created 09/05/2006
 */
public class RemoverParcelamentoDescontoInatividadeAction extends GcomAction {
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
    	
    	ActionForward retorno = actionMapping.findForward("atualizarPerfilRemoverParcelamentoDescontoInatividade");
    	if (sessao.getAttribute("UseCase")!= null &&
    			sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
    		retorno = actionMapping.findForward("inserirPerfilRemoverParcelamentoDescontoInatividade");	
    	}
    	
    	String quantidadeMaximaMesesInatividade = httpServletRequest.getParameter("quantidadeMaximaMesesInat");
    	
        if (quantidadeMaximaMesesInatividade != null && !quantidadeMaximaMesesInatividade.equalsIgnoreCase("") &&
        	sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null){
        	        	
        	Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
            					.getAttribute("collectionParcelamentoDescontoInatividade");
        	
        	Collection collectionParcelamentoDescontoInatividadeLinhaRemovidas = null;
    		if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas") != null
    				&& !sessao
    						.getAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas")
    						.equals("")) {
    			collectionParcelamentoDescontoInatividadeLinhaRemovidas = (Collection) sessao
    					.getAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
    		} else {
    			collectionParcelamentoDescontoInatividadeLinhaRemovidas = new ArrayList();
    		}
        	
        	ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = null;
        	ParcelamentoDescontoInatividade parcelamentoDescontoInatividadeExcluir = null;
			Iterator iterator = collectionParcelamentoDescontoInatividade.iterator();
						
			while (iterator.hasNext()) {
				parcelamentoDescontoInatividade = (ParcelamentoDescontoInatividade) iterator.next();
		
				//procura na coleção o parcelamentoDescontoInatividade que tem a quantidadeMaximaMesesInatividade selecionada
				if (parcelamentoDescontoInatividade.getQuantidadeMaximaMesesInatividade().equals(new Integer(quantidadeMaximaMesesInatividade))){
					parcelamentoDescontoInatividadeExcluir =  parcelamentoDescontoInatividade;
					collectionParcelamentoDescontoInatividadeLinhaRemovidas
					.add(parcelamentoDescontoInatividade);
				}
			}
			
			collectionParcelamentoDescontoInatividade.remove(parcelamentoDescontoInatividadeExcluir);
			sessao.setAttribute("collectionParcelamentoDescontoInatividade",
					collectionParcelamentoDescontoInatividade);
			sessao.setAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas",
					collectionParcelamentoDescontoInatividadeLinhaRemovidas);
        	
        }
     
       return retorno;
    }
    
 }
 
