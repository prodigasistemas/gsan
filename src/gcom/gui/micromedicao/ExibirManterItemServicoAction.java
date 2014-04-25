package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroItemServico;
import gcom.micromedicao.ItemServico;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1065] MANTER ITEM DE SERVICO
 * 
 * @author Rodrigo Cabral
 * @date 04/08/2010
 */

public class ExibirManterItemServicoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("exibirManterItemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<ItemServico> colecaoItemServico = null;

		// Parte da verificação do filtro
        FiltroItemServico filtroItemServico = new FiltroItemServico();
        
		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroItemServico") != null) {
			filtroItemServico = (FiltroItemServico) sessao.getAttribute("filtroItemServico");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("exibirManterItemServico"))) {

			Map resultado = controlarPaginacao(
					httpServletRequest, retorno,	filtroItemServico, ItemServico.class.getName());
			colecaoItemServico = (Collection<ItemServico>) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0002] Nenhum registro encontrado				
			if (colecaoItemServico == null || colecaoItemServico.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			if (colecaoItemServico != null
					&& !colecaoItemServico.isEmpty()) {
				if (colecaoItemServico.size() == 1
						&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
								.getParameter("page.offset").equals("1"))) {
					if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
						retorno = actionMapping
								.findForward("exibirAtualizarItemServico");
						ItemServico itemServico = (ItemServico) colecaoItemServico
								.iterator().next();
						sessao.setAttribute("itemServico", itemServico);
					} else {
						httpServletRequest.setAttribute("colecaoItemServico",
								colecaoItemServico);
					}
				} else {
					httpServletRequest.setAttribute("colecaoItemServico",
							colecaoItemServico);
				}
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
		}
		
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
	} 
	
}
