package gcom.gui.seguranca.acesso;

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
 * @date 14/05/2008
 */

public class ExibirFiltrarAlteracaoTipoAction extends GcomAction {
	
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
				.findForward("filtrarAlteracaoTipo");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarAlteracaoTipoActionForm filtrarAlteracaoTipoActionForm = (FiltrarAlteracaoTipoActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarAlteracaoTipoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarAlteracaoTipoActionForm.getIndicadorAtualizar()==null){
			filtrarAlteracaoTipoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarAlteracaoTipoActionForm.setId("");
        	filtrarAlteracaoTipoActionForm.setDescricao("");
        	filtrarAlteracaoTipoActionForm.setDescricaoAbreviada("");
        	
        	
        }
       return retorno;

	}

}
