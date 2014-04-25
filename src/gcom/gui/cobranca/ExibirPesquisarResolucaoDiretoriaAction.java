package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite pesquisar resoluções de diretoria 
 * [UC0223] Pesquisar Resolução de Diretoria
 * 
 * @author Vivianne Sousa
 * @since 19/04/2006
 */
public class ExibirPesquisarResolucaoDiretoriaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirPesquisarResolucaoDiretoria");
        HttpSession sessao = httpServletRequest.getSession(false);
        PesquisarResolucaoDiretoriaActionForm pesquisarResolucaoDiretoriaActionForm = (PesquisarResolucaoDiretoriaActionForm)actionForm;
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------
        	pesquisarResolucaoDiretoriaActionForm.setNumeroResolucaoDiretoria("");
        	pesquisarResolucaoDiretoriaActionForm.setDataInicioVigencia("");
        	pesquisarResolucaoDiretoriaActionForm.setDataFimVigencia("");
        	
        }           
        
        
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaResolucaoDiretoria") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaUsuario",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaResolucaoDiretoria"));

		}

		
		
		return retorno;

	}

}
