package gcom.gui.faturamento.conta;


import java.util.ArrayList;
import java.util.Collection;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0407] Filtrar Imóveis para Inserir ou Manter Conta
 * 
 * @author Ana Maria
 * @created 16/03/2007
 */
public class ManterContaConjuntoImovelAction extends GcomAction {
    
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("manterContaConjuntoImovel");
		
		//Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ManterContaConjuntoImovelActionForm manterContaConjuntoImovelActionForm = (ManterContaConjuntoImovelActionForm) actionForm;

		//Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoImovel = new ArrayList();
		
		if(sessao.getAttribute("colecaoImovel") != null){
			colecaoImovel = (Collection)sessao.getAttribute("colecaoImovel");	
		}
		
		manterContaConjuntoImovelActionForm.setQuatidadeImovel(Integer.toString(colecaoImovel.size()));
		
		return retorno;
	}
}
