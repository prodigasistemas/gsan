package gcom.gui.operacional;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0522] MANTER DISTRITO OPERACIONAL
 * 
 * @author Eduardo Bianchi
 * @date 31/01/2007
 */

public class ExibirManterDistritoOperacionalAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterDistritoOperacional");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("idDistritoOperacional");
		sessao.removeAttribute("idRegistroAtualizar");
		sessao.removeAttribute("distritoOperacional");
		
		Collection colecaoDistritoOperacional = null;

		// Parte da verificação do filtro
        FiltroDistritoOperacional filtroDistritoOperacional = null;

		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroDistritoOperacional") != null) {
			filtroDistritoOperacional = (FiltroDistritoOperacional) sessao
					.getAttribute("filtroDistritoOperacional");
			filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento.sistemaAbastecimento");
			filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade("zonaAbastecimento");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterDistritoOperacional"))) {

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroDistritoOperacional, DistritoOperacional.class.getName());
			colecaoDistritoOperacional = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			//Nenhum registro encontrado				
			if (colecaoDistritoOperacional == null || colecaoDistritoOperacional.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoDistritoOperacional.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarDistritoOperacional");
				DistritoOperacional distritoOperacional = (DistritoOperacional) colecaoDistritoOperacional.iterator().next();
				sessao.setAttribute("idRegistroAtualizar", distritoOperacional);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarDistritoOperacionalAction.do");
				//chama ExibirAtualizarMunicipioAction
			}else{
				sessao.setAttribute("colecaoDistritoOperacional", colecaoDistritoOperacional);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterDistritoOperacionalAction.do");
				//chama ExibirManterMunicipioAction
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
		
	} 
	
}
