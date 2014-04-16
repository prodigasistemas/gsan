package gcom.gui.micromedicao;


import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**			
 * @date 06/08/08
 * @author Arthur Carvalho
 */

public class ExibirFiltrarLocalArmazenagemHidrometroAction extends GcomAction {
	
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
				.findForward("filtrarLocalArmazenagemHidrometro");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarLocalArmazenagemHidrometroActionForm filtrarLocalArmazenagemHidrometroActionForm = (FiltrarLocalArmazenagemHidrometroActionForm) actionForm;
		
		
		String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarLocalArmazenagemHidrometroActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarLocalArmazenagemHidrometroActionForm.getIndicadorAtualizar()==null){
			filtrarLocalArmazenagemHidrometroActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarLocalArmazenagemHidrometroActionForm.setId("");
        	filtrarLocalArmazenagemHidrometroActionForm.setDescricao("");
        	filtrarLocalArmazenagemHidrometroActionForm.setIndicadorUso("");
        	filtrarLocalArmazenagemHidrometroActionForm.setDescricaoAbreviada("");
        	filtrarLocalArmazenagemHidrometroActionForm.setIndicadorOficina("");
        	
        }
       return retorno;

	}

}
