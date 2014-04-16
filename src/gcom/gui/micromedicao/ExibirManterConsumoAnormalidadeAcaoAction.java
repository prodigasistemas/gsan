package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAcao;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1058] MANTER CONSUMO ANORMALIDADE ACAO
 * 
 * @author Rodrigo Cabral
 * @date 04/10/2010
 */

public class ExibirManterConsumoAnormalidadeAcaoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("exibirManterConsumoAnormalidadeAcao");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<ConsumoAnormalidadeAcao> colecaoConsumoAnormalidadeAcao = null;

		// Parte da verificação do filtro
        FiltroConsumoAnormalidadeAcao filtroConsumoAnormalidadeAcao = new FiltroConsumoAnormalidadeAcao();
        
		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroConsumoAnormalidadeAcao") != null) {
			filtroConsumoAnormalidadeAcao = (FiltroConsumoAnormalidadeAcao) sessao.getAttribute("filtroConsumoAnormalidadeAcao");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("exibirManterConsumoAnormalidadeAcao"))) {

			Map resultado = controlarPaginacao(
					httpServletRequest, retorno,	filtroConsumoAnormalidadeAcao, ConsumoAnormalidadeAcao.class.getName());
			colecaoConsumoAnormalidadeAcao = (Collection<ConsumoAnormalidadeAcao>) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// Nenhum registro encontrado				
			if (colecaoConsumoAnormalidadeAcao == null || colecaoConsumoAnormalidadeAcao.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			if (colecaoConsumoAnormalidadeAcao != null
					&& !colecaoConsumoAnormalidadeAcao.isEmpty()) {
				if (colecaoConsumoAnormalidadeAcao.size() == 1
						&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
								.getParameter("page.offset").equals("1"))) {
					if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
						retorno = actionMapping
								.findForward("exibirAtualizarConsumoAnormalidadeAcao");
						ConsumoAnormalidadeAcao consumoAnormalidadeAcao = (ConsumoAnormalidadeAcao) colecaoConsumoAnormalidadeAcao
								.iterator().next();
						sessao.setAttribute("consumoAnormalidadeAcao", consumoAnormalidadeAcao);
					} else {
						httpServletRequest.setAttribute("colecaoConsumoAnormalidadeAcao",
								colecaoConsumoAnormalidadeAcao);
					}
				} else {
					httpServletRequest.setAttribute("colecaoConsumoAnormalidadeAcao",
							colecaoConsumoAnormalidadeAcao);
				}
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
		}
		
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
	} 
	
}
