package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0962] MANTER RAMO DE ATIVIDADE
 * 
 * @author Fernando Fontelles Filho
 * @date 02/12/2009
 */

public class ExibirManterRamoAtividadeAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("exibirManterRamoAtividade");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<RamoAtividade> colecaoRamoAtividade = null;

		// Parte da verificação do filtro
        FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();//(FiltroRamoAtividade) 
//        	sessao.getAttribute("filtroRamoAtividade");
        
		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroRamoAtividade") != null) {
			filtroRamoAtividade = (FiltroRamoAtividade) sessao.getAttribute("filtroRamoAtividade");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("exibirManterRamoAtividade"))) {

			Map resultado = controlarPaginacao(
					httpServletRequest, retorno,	filtroRamoAtividade, RamoAtividade.class.getName());
			colecaoRamoAtividade = (Collection<RamoAtividade>) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0002] Nenhum registro encontrado				
			if (colecaoRamoAtividade == null || colecaoRamoAtividade.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			if (colecaoRamoAtividade != null
					&& !colecaoRamoAtividade.isEmpty()) {
				if (colecaoRamoAtividade.size() == 1
						&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
								.getParameter("page.offset").equals("1"))) {
					if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
						retorno = actionMapping
								.findForward("exibirAtualizarRamoAtividade");
						RamoAtividade ramoAtividade = (RamoAtividade) colecaoRamoAtividade
								.iterator().next();
						sessao.setAttribute("ramoAtividade", ramoAtividade);
					} else {
						httpServletRequest.setAttribute("colecaoRamoAtividade",
								colecaoRamoAtividade);
					}
				} else {
					httpServletRequest.setAttribute("colecaoRamoAtividade",
							colecaoRamoAtividade);
				}
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
		}
		
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
	} 
	
}
