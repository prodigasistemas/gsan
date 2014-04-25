package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.EspecificacaoPavimentacaoServicoTipo;
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
 * Action de remover adicionar Criterio de Cobranca de Rota
 * 
 * @author Vivianne Sousa
 * @created 25/04/2006
 */
public class RemoverDeterminarTipoServicoEspecificacaoAction extends GcomAction {
	/**
	 * @author Rodrigo Cabral
	 * @date 25/04/2011
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
    	
    	ActionForward retorno = actionMapping.findForward("inserirRemoverTipoServicoEspecificacao");
    	                                 
        String ultimaAlteracaoTipoServicoEspecificacaoExcluir = httpServletRequest.getParameter("ultimaAlteracaoTipoServicoEspecificacaoExcluir");
        
        Collection colecaoRemoverEspServTipo = null;
		
        if (ultimaAlteracaoTipoServicoEspecificacaoExcluir != null && !ultimaAlteracaoTipoServicoEspecificacaoExcluir.equalsIgnoreCase("") &&
        	sessao.getAttribute("colecaoEspServTipo") != null){
        	        	
        	Collection collectionServicoTipoEsp = (Collection) sessao
            					.getAttribute("colecaoEspServTipo");
        	
        	if (sessao.getAttribute("colecaoRemoverEspServTipo") != null) {
        		colecaoRemoverEspServTipo = (Collection) sessao
                        .getAttribute("colecaoRemoverEspServTipo");
            } else {
            	colecaoRemoverEspServTipo = new ArrayList();
            }
        	
        	EspecificacaoPavimentacaoServicoTipo tipoServicoEspecificacao = null;
        	EspecificacaoPavimentacaoServicoTipo tipoServicoEspecificacaoExcluir = null;
			Iterator iterator = collectionServicoTipoEsp.iterator();
						
			while (iterator.hasNext()) {
				tipoServicoEspecificacao = (EspecificacaoPavimentacaoServicoTipo) iterator.next();
		
				//procura na coleção
				if (tipoServicoEspecificacao.getUltimaAlteracao().getTime() == new Long(ultimaAlteracaoTipoServicoEspecificacaoExcluir)){
					tipoServicoEspecificacaoExcluir =  tipoServicoEspecificacao;
				}

			}
			
			collectionServicoTipoEsp.remove(tipoServicoEspecificacaoExcluir);
			colecaoRemoverEspServTipo.add(tipoServicoEspecificacaoExcluir);
       	 	sessao.setAttribute("colecaoEspServTipo", collectionServicoTipoEsp);
       	 	sessao.setAttribute("colecaoRemoverEspServTipo", colecaoRemoverEspServTipo);

        }
        
       return retorno;
    }
    


}
 
