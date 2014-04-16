package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcDesctoInativVista;
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
 * Action de remover um objeto do tipo ParcelamentoDescontoInatividadeAVista
 * da collectionParcelamentoDescontoInatividadeAVista
 * 
 * @author Vivianne Sousa
 * @created 15/07/2010
 */
public class RemoverParcelamentoDescontoInatividadeAVistaAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 15/07/2010
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
    	
    	ActionForward retorno = actionMapping.findForward("atualizarPerfilRemoverParcelamentoDescontoInatividadeAVista");
    	if (sessao.getAttribute("UseCase")!= null &&
    			sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
    		retorno = actionMapping.findForward("inserirPerfilRemoverParcelamentoDescontoInatividadeAVista");	
    	}
    	
    	String quantidadeMaximaMesesInatividade = httpServletRequest.getParameter("quantidadeMaximaMesesInat");
    	
        if (quantidadeMaximaMesesInatividade != null && !quantidadeMaximaMesesInatividade.equalsIgnoreCase("") &&
        	sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null){
        	        	
        	Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
            					.getAttribute("collectionParcelamentoDescontoInatividadeAVista");
        	
        	Collection collectionParcelamentoDescontoInatividadeLinhaRemovidas = null;
    		if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas") != null
    			&& !sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas").equals("")) {
    			collectionParcelamentoDescontoInatividadeLinhaRemovidas = (Collection) sessao
    					.getAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");
    		} else {
    			collectionParcelamentoDescontoInatividadeLinhaRemovidas = new ArrayList();
    		}
        	
    		ParcDesctoInativVista parcelamentoDescontoInatividade = null;
    		ParcDesctoInativVista parcelamentoDescontoInatividadeExcluir = null;
			Iterator iterator = collectionParcelamentoDescontoInatividade.iterator();
						
			while (iterator.hasNext()) {
				parcelamentoDescontoInatividade = (ParcDesctoInativVista) iterator.next();
		
				//procura na coleção o parcelamentoDescontoInatividade que tem a quantidadeMaximaMesesInatividade selecionada
				if (parcelamentoDescontoInatividade.getQuantidadeMaximaMesesInatividade().equals(new Integer(quantidadeMaximaMesesInatividade))){
					parcelamentoDescontoInatividadeExcluir =  parcelamentoDescontoInatividade;
					collectionParcelamentoDescontoInatividadeLinhaRemovidas
					.add(parcelamentoDescontoInatividade);
				}
			}
			
			collectionParcelamentoDescontoInatividade.remove(parcelamentoDescontoInatividadeExcluir);
			sessao.setAttribute("collectionParcelamentoDescontoInatividadeAVista",
					collectionParcelamentoDescontoInatividade);
			sessao.setAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas",
					collectionParcelamentoDescontoInatividadeLinhaRemovidas);
        	
        }
     
       return retorno;
    }
    
 }
 
