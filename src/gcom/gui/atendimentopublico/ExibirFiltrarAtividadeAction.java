package gcom.gui.atendimentopublico;

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
 * @date 28/04/2008
 */

public class ExibirFiltrarAtividadeAction extends GcomAction {
	
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
				.findForward("filtrarAtividade");
		
		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarAtividadeActionForm filtrarAtividadeActionForm = (FiltrarAtividadeActionForm) actionForm;
		
		// Código para checar ou não o indicador ATUALIZAR
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarAtividadeActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}			
		
		if(filtrarAtividadeActionForm.getIndicadorAtualizar()==null){
			filtrarAtividadeActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	filtrarAtividadeActionForm.setId("");
        	filtrarAtividadeActionForm.setDescricao("");
        	filtrarAtividadeActionForm.setDescricaoAbreviada("");
        	filtrarAtividadeActionForm.setIndicadorAtividadeUnica("");
        	filtrarAtividadeActionForm.setIndicadorUso("");
        	
        }

        return retorno;

	}

}
