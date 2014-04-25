package gcom.gui.cobranca;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**			
 * @date 14/08/09
 * @author Arthur Carvalho
 */

public class ExibirFiltrarCobrancaGrupoAction extends GcomAction {
	
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
				.findForward("filtrarCobrancaGrupo");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarCobrancaGrupoActionForm form = (FiltrarCobrancaGrupoActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}			
		
		if(form.getIndicadorAtualizar()==null){
			form.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null && 
        	httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	form.setId("");
        	form.setDescricao("");
        	form.setDescricaoAbreviada("");
        	form.setAnoMesReferencia("");
        	form.setIndicadorUso("");
        	
        }
       return retorno;

	}

}
