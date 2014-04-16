package gcom.gui.micromedicao.hidrometro;

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
 * @date 16/05/2008
 */

public class ExibirFiltrarHidrometroDiametroAction extends GcomAction {
	
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarHidrometroDiametro");

		// Mudar isso quando estiver implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarHidrometroDiametroActionForm filtrarHidrometroDiametroActionForm = (FiltrarHidrometroDiametroActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		// Se estiver sendo chamado pela primeira vez
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarHidrometroDiametroActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarHidrometroDiametroActionForm.getIndicadorAtualizar()==null){
			filtrarHidrometroDiametroActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarHidrometroDiametroActionForm.setId("");
        	filtrarHidrometroDiametroActionForm.setDescricao("");
        	filtrarHidrometroDiametroActionForm.setDescricaoAbreviada("");
        	filtrarHidrometroDiametroActionForm.setNumeroOrdem("");
        	filtrarHidrometroDiametroActionForm.setIndicadorUso("");
        	
        	
        }
       return retorno;

	}

}
