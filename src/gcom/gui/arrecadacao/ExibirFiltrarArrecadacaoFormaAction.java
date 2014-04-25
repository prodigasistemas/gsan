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
 * @author Vinícius Medeiros
 * @date 09/04/2008
 */

public class ExibirFiltrarArrecadacaoFormaAction extends GcomAction {
	
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
				.findForward("filtrarArrecadacaoForma");

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarArrecadacaoFormaActionForm filtrarArrecadacaoFormaActionForm = (FiltrarArrecadacaoFormaActionForm) actionForm;
		
		// Código para checar ou não o indicador ATUALIZAR
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarArrecadacaoFormaActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarArrecadacaoFormaActionForm.getIndicadorAtualizar()==null){
			filtrarArrecadacaoFormaActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarArrecadacaoFormaActionForm.setDescricao("");
        	filtrarArrecadacaoFormaActionForm.setIdArrecadacaoForma("");
        	filtrarArrecadacaoFormaActionForm.setCodigoArrecadacaoForma("");
        	
        	
        }
       return retorno;

	}

}
