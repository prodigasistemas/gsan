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
 * @date 14/08/08
 * @author Arthur Carvalho
 */

public class ExibirFiltrarFonteAbastecimentoAction extends GcomAction {
	
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
				.findForward("filtrarFonteAbastecimento");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarFonteAbastecimentoActionForm filtrarFonteAbastecimentoActionForm = (FiltrarFonteAbastecimentoActionForm) actionForm;
		
		
		String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarFonteAbastecimentoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarFonteAbastecimentoActionForm.getIndicadorAtualizar()==null){
			filtrarFonteAbastecimentoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarFonteAbastecimentoActionForm.setId("");
        	filtrarFonteAbastecimentoActionForm.setIndicadorCalcularVolumeFixo("");
        	filtrarFonteAbastecimentoActionForm.setDescricao("");
        	filtrarFonteAbastecimentoActionForm.setIndicadorUso("");
        	filtrarFonteAbastecimentoActionForm.setDescricaoAbreviada("");
        	
        }
       return retorno;

	}

}
