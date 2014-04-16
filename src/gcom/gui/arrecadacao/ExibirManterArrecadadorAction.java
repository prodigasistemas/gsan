package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
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
 * [UC0507] MANTER ARRECADADOR
 * 
 * @author Marcio Roberto	
 * @date 08/02/2007
 */

public class ExibirManterArrecadadorAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterArrecadador");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<Arrecadador> colecaoArrecadador = null;

		// Parte da verificação do filtro
        FiltroArrecadador filtroArrecadador = (FiltroArrecadador) sessao.getAttribute("filtroArrecadador");
        
		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroArrecadador") != null) {
			filtroArrecadador = (FiltroArrecadador) sessao.getAttribute("filtroArrecadador");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterArrecadador"))) {

			Map resultado = controlarPaginacao(httpServletRequest, retorno,	filtroArrecadador, Arrecadador.class.getName());
			colecaoArrecadador = (Collection<Arrecadador>) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0002] Nenhum registro encontrado				
			if (colecaoArrecadador == null || colecaoArrecadador.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
		
			if (colecaoArrecadador.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
			
				if (colecaoArrecadador.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
					// caso o resultado do filtro só retorne um registro 
					// e o check box Atualizar estiver selecionado
					//o sistema não exibe a tela de manter, exibe a de atualizar 
					retorno = actionMapping.findForward("exibirAtualizarArrecadador");
					Arrecadador arrecadador = (Arrecadador) colecaoArrecadador.iterator().next();
					sessao.setAttribute("idRegistroAtualizar", arrecadador);
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarArrecadadorAction.do");
					//chama ExibirAtualizarMunicipioAction
				}else{
					sessao.setAttribute("colecaoArrecadador", colecaoArrecadador);
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterArrecadadorAction.do");
					//chama ExibirManterMunicipioAction
				}
			
			} else {
					httpServletRequest.setAttribute("colecaoArrecadador",
							colecaoArrecadador);
				
			}
		}
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
		}
	}


