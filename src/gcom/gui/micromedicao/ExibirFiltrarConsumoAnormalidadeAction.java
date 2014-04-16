package gcom.gui.micromedicao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

/**
 * @author Vinícius Medeiros
 * @date 03/06/2008
 */

public class ExibirFiltrarConsumoAnormalidadeAction extends GcomAction {
	
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
				.findForward("filtrarConsumoAnormalidade");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarConsumoAnormalidadeActionForm filtrarConsumoAnormalidadeActionForm = (FiltrarConsumoAnormalidadeActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarConsumoAnormalidadeActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarConsumoAnormalidadeActionForm.getIndicadorAtualizar()==null){
			filtrarConsumoAnormalidadeActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarConsumoAnormalidadeActionForm.setId("");
        	filtrarConsumoAnormalidadeActionForm.setDescricao("");
        	filtrarConsumoAnormalidadeActionForm.setDescricaoAbreviada("");
        	filtrarConsumoAnormalidadeActionForm.setIndicadorUso("");        	        	
        	
        }
       return retorno;

	}

}
