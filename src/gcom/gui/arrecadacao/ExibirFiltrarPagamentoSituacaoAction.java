package gcom.gui.arrecadacao;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 */

public class ExibirFiltrarPagamentoSituacaoAction extends GcomAction {
	
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
				.findForward("filtrarPagamentoSituacao");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarPagamentoSituacaoActionForm filtrarPagamentoSituacaoActionForm = (FiltrarPagamentoSituacaoActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarPagamentoSituacaoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarPagamentoSituacaoActionForm.getIndicadorAtualizar()==null){
			filtrarPagamentoSituacaoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarPagamentoSituacaoActionForm.setId("");
        	filtrarPagamentoSituacaoActionForm.setDescricao("");
        	filtrarPagamentoSituacaoActionForm.setDescricaoAbreviada("");
        	filtrarPagamentoSituacaoActionForm.setIndicadorUso("");
        	
        	
        }
       return retorno;

	}

}
