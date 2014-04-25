package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
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
 * Action de remover um objeto do tipo ParcelamentoDescontoAntiguidade 
 * da collectionParcelamentoDescontoAntiguidade
 * 
 * @author Vivianne Sousa
 * @created 09/05/2006
 */
public class RemoverParcelamentoDescontoAntiguidadeAction extends GcomAction {
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
    	
    	ActionForward retorno = actionMapping.findForward("atualizarPerfilRemoverParcelamentoDescontoAntiguidade");
    	if (sessao.getAttribute("UseCase")!= null &&
    			sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
    		retorno = actionMapping.findForward("inserirPerfilRemoverParcelamentoDescontoAntiguidade");	
    	}
    	
        String quantidadeMinimaMesesDebito = httpServletRequest.getParameter("quantidadeMinimaMesesDeb");
        
        if (quantidadeMinimaMesesDebito != null && !quantidadeMinimaMesesDebito.equalsIgnoreCase("") &&
        	sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null){
        	        	
        	Collection collectionParcelamentoDescontoAntiguidade = (Collection) sessao
            					.getAttribute("collectionParcelamentoDescontoAntiguidade");
        	
        	
        	Collection collectionParcelamentoDescontoAntiguidadeLinhaRemovidas = null;
    		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas") != null
    				&& !sessao
    						.getAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas")
    						.equals("")) {
    			collectionParcelamentoDescontoAntiguidadeLinhaRemovidas = (Collection) sessao
    					.getAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");
    		} else {
    			collectionParcelamentoDescontoAntiguidadeLinhaRemovidas = new ArrayList();
    		}
        	
        	ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = null;
        	ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeExcluir = null;
			Iterator iterator = collectionParcelamentoDescontoAntiguidade.iterator();
						
			while (iterator.hasNext()) {
				parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) iterator.next();
		
				//procura na coleção o parcelamentoDescontoAntiguidade que tem a quantidadeMinimaMesesDebito selecionada
				if (parcelamentoDescontoAntiguidade.getQuantidadeMinimaMesesDebito().equals(new Integer(quantidadeMinimaMesesDebito))){
					parcelamentoDescontoAntiguidadeExcluir =  parcelamentoDescontoAntiguidade;
					collectionParcelamentoDescontoAntiguidadeLinhaRemovidas.add(parcelamentoDescontoAntiguidade);
					
				}
			}
			
			collectionParcelamentoDescontoAntiguidade.remove(parcelamentoDescontoAntiguidadeExcluir);
       	 	sessao.setAttribute("collectionParcelamentoDescontoAntiguidade", 
       	 						collectionParcelamentoDescontoAntiguidade);
       	 	sessao.setAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas",
       	 		collectionParcelamentoDescontoAntiguidadeLinhaRemovidas);
        }
        
       return retorno;
    }
 }
 
