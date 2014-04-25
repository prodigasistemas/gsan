package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.BairroArea;
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
 * Action de remover Area de Bairro
 * 
 * @author Vivianne Sousa
 * @created 20/12/2006
 */
public class RemoverAreaBairroAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	ActionForward retorno = null;
    	
    	if (sessao.getAttribute("reloadPage")!= null && 
    			sessao.getAttribute("reloadPage").equals("INSERIRBAIRRO")){
    		retorno = actionMapping.findForward("inserirBairroRemoverAreaBairro");	
    	}else{
    		retorno = actionMapping.findForward("atualizarBairroRemoverAreaBairro");
    	}
    		
        String ultimaAlteracao = httpServletRequest.getParameter("ultimaAlteracao");
        
        Collection colecaoBairroAreaRemover = null ;
        
        if (sessao.getAttribute("colecaoBairroAreaRemover")!= null){
        	colecaoBairroAreaRemover = (Collection) sessao
    		.getAttribute("colecaoBairroAreaRemover");
        }else{
        	colecaoBairroAreaRemover = new ArrayList();
        }
        
        if (ultimaAlteracao != null && !ultimaAlteracao.equalsIgnoreCase("") &&
        	sessao.getAttribute("colecaoBairroArea") != null){
        	        	
        	Collection colecaoBairroArea = (Collection) sessao
            					.getAttribute("colecaoBairroArea");
        	
        	BairroArea bairroArea = null;
        	BairroArea bairroAreaExcluir = null;
			Iterator iterator = colecaoBairroArea.iterator();
						
			while (iterator.hasNext()) {
				bairroArea = (BairroArea) iterator.next();
				
				if (bairroArea.getUltimaAlteracao().getTime() == Long
						.parseLong(ultimaAlteracao)) {

					bairroAreaExcluir =  bairroArea;
					break;
				}
			}
			
			if (bairroArea.getId() != null){
				colecaoBairroAreaRemover.add(bairroAreaExcluir);
			}
			
			colecaoBairroArea.remove(bairroAreaExcluir);
       	 	sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
       	 	sessao.setAttribute("colecaoBairroAreaRemover", colecaoBairroAreaRemover);
        	
        }
        
       return retorno;
    }
    


}
 
