package gcom.gui.atendimentopublico.ligacaoagua;

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
 * @date 15/05/2008
 */

public class ExibirFiltrarLigacaoAguaSituacaoAction extends GcomAction {
	
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
				.findForward("filtrarLigacaoAguaSituacao");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarLigacaoAguaSituacaoActionForm filtrarLigacaoAguaSituacaoActionForm = (FiltrarLigacaoAguaSituacaoActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarLigacaoAguaSituacaoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());	
		}			

		
		if(filtrarLigacaoAguaSituacaoActionForm.getIndicadorAtualizar()==null){
			filtrarLigacaoAguaSituacaoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarLigacaoAguaSituacaoActionForm.setId("");
        	filtrarLigacaoAguaSituacaoActionForm.setDescricao("");
        	filtrarLigacaoAguaSituacaoActionForm.setDescricaoAbreviada("");
        	filtrarLigacaoAguaSituacaoActionForm.setConsumoMinimoFaturamento("");
        	filtrarLigacaoAguaSituacaoActionForm.setIndicadorFaturamentoSituacao("");
        	filtrarLigacaoAguaSituacaoActionForm.setIndicadorExistenciaLigacao("");
        	filtrarLigacaoAguaSituacaoActionForm.setIndicadorExistenciaRede("");
        	filtrarLigacaoAguaSituacaoActionForm.setIndicadorUso("");
        	filtrarLigacaoAguaSituacaoActionForm.setIndicadorAbastecimento("");
        	filtrarLigacaoAguaSituacaoActionForm.setIndicadorAguaAtiva("");
        	filtrarLigacaoAguaSituacaoActionForm.setIndicadorAguaCadastrada("");
        	filtrarLigacaoAguaSituacaoActionForm.setIndicadorAguaDesligada("");
        	filtrarLigacaoAguaSituacaoActionForm.setIndicadorAnalizeAgua("");
        	
        	
        }
       return retorno;

	}

}
