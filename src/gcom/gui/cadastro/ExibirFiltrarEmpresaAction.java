package gcom.gui.cadastro;

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

public class ExibirFiltrarEmpresaAction extends GcomAction {
	
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
				.findForward("filtrarEmpresa");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarEmpresaActionForm filtrarEmpresaActionForm = (FiltrarEmpresaActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarEmpresaActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarEmpresaActionForm.getIndicadorAtualizar()==null){
			filtrarEmpresaActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarEmpresaActionForm.setId("");
        	filtrarEmpresaActionForm.setDescricao("");
        	filtrarEmpresaActionForm.setDescricaoAbreviada("");
        	filtrarEmpresaActionForm.setEmail("");
        	filtrarEmpresaActionForm.setIndicadorEmpresaPrincipal("");
        	filtrarEmpresaActionForm.setIndicadorUso("");
        	
        	
        }
       return retorno;

	}

}
