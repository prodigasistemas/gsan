package gcom.gui.seguranca;


import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**			
 * @date 26/08/08
 * @author Arthur Carvalho
 */

public class ExibirFiltrarUsuarioTipoAction extends GcomAction {
	
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
				.findForward("filtrarUsuarioTipo");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarUsuarioTipoActionForm filtrarUsuarioTipoActionForm = (FiltrarUsuarioTipoActionForm) actionForm;
		
		
		String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarUsuarioTipoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarUsuarioTipoActionForm.getIndicadorAtualizar()==null){
			filtrarUsuarioTipoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarUsuarioTipoActionForm.setId("");
        	filtrarUsuarioTipoActionForm.setIndicadorFuncionario("");
        	filtrarUsuarioTipoActionForm.setDescricao("");
        	filtrarUsuarioTipoActionForm.setIndicadorUso("");
        	
        }
       return retorno;

	}

}
