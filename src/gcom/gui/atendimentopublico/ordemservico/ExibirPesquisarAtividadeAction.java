package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0438] Pesquisar Atividade
 * 
 * @author Ana Maria
 * @date 01/08/2006
 * 
 */
public class ExibirPesquisarAtividadeAction extends GcomAction {

	/**
	 * 
	 * [UC0438] Este caso de uso efetua pesquisa de Atividade
	 * 
	 * 
	 * @author Ana Maria
	 * @date 03/08/2006
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

		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarAtividadeActionForm pesquisarAtividadeActionForm = (PesquisarAtividadeActionForm) actionForm;
		
		ActionForward retorno = actionMapping.findForward("atividadePesquisar");
		
		// Seta o tipo de pesquisa 
		pesquisarAtividadeActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		pesquisarAtividadeActionForm.setTipoPesquisaAbreviada(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		
        
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaAtividade") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaAtividade",
							httpServletRequest.getParameter("caminhoRetornoTelaPesquisaAtividade"));
		}else if (httpServletRequest.getParameter("caminhoRetornoAtividadeTipoServico") != null) {
			sessao.setAttribute("caminhoRetornoAtividadeTipoServico",
					httpServletRequest.getParameter("caminhoRetornoAtividadeTipoServico"));
		}
		return retorno;
	}
	
}
