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
 * @date 11/08/08
 * @author Arthur Carvalho
 */

public class ExibirFiltrarCargoFuncionarioAction extends GcomAction {
	
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
				.findForward("filtrarCargoFuncionario");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarCargoFuncionarioActionForm filtrarCargoFuncionarioActionForm = (FiltrarCargoFuncionarioActionForm) actionForm;
		
		
		String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarCargoFuncionarioActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarCargoFuncionarioActionForm.getIndicadorAtualizar()==null){
			filtrarCargoFuncionarioActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarCargoFuncionarioActionForm.setCodigo("");
        	filtrarCargoFuncionarioActionForm.setDescricao("");
        	filtrarCargoFuncionarioActionForm.setIndicadorUso("");
        	filtrarCargoFuncionarioActionForm.setDescricaoAbreviada("");
        	
        }
       return retorno;

	}

}
