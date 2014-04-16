package gcom.gui.faturamento;

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
 * @date 04/04/2008
 */

public class ExibirFiltrarFaturamentoGrupoAction extends GcomAction {
	
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
				.findForward("filtrarFaturamentoGrupo");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarFaturamentoGrupoActionForm filtrarFaturamentoGrupoActionForm = (FiltrarFaturamentoGrupoActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarFaturamentoGrupoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarFaturamentoGrupoActionForm.getIndicadorAtualizar()==null){
			filtrarFaturamentoGrupoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarFaturamentoGrupoActionForm.setId("");
        	filtrarFaturamentoGrupoActionForm.setDescricao("");
        	filtrarFaturamentoGrupoActionForm.setDescricaoAbreviada("");
        	filtrarFaturamentoGrupoActionForm.setAnoMesReferencia("");
        	filtrarFaturamentoGrupoActionForm.setDiaVencimento("");
        	filtrarFaturamentoGrupoActionForm.setIndicadorVencimentoMesFatura("");
        	filtrarFaturamentoGrupoActionForm.setIndicadorUso("");
        	
        	
        }
       return retorno;

	}

}
