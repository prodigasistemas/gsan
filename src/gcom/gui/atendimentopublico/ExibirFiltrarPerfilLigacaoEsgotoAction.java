package gcom.gui.atendimentopublico;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;


/**
 * @author Arthur Carvalho
 */

public class ExibirFiltrarPerfilLigacaoEsgotoAction extends GcomAction {
	
	/*
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarPerfilLigacaoEsgoto");

		

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarPerfilLigacaoEsgotoActionForm filtrarPerfilLigacaoEsgotoActionForm = (FiltrarPerfilLigacaoEsgotoActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarPerfilLigacaoEsgotoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarPerfilLigacaoEsgotoActionForm.getIndicadorAtualizar()==null){
			filtrarPerfilLigacaoEsgotoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarPerfilLigacaoEsgotoActionForm.setId("");
        	filtrarPerfilLigacaoEsgotoActionForm.setDescricao("");
        	filtrarPerfilLigacaoEsgotoActionForm.setPercentualEsgotoConsumidaColetada("");
        	filtrarPerfilLigacaoEsgotoActionForm.setIndicadorUso("");
        	
        	
        }
       return retorno;

	}

}
