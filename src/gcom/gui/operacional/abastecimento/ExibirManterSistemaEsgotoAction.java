package gcom.gui.operacional.abastecimento;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0525]	MANTER SISTEMA DE ESGOTO
 * 
 * @author Kássia Albuquerque
 * @date 13/03/2007
 */

public class ExibirManterSistemaEsgotoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
			ActionForward retorno = actionMapping.findForward("manterSistemaEsgoto");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Collection colecaoSistemaEsgoto = null;
	
			// Parte da verificação do filtro
	        FiltroSistemaEsgoto filtroSistemaEsgoto = null;
	
			// Verifica se o filtro foi informado pela página de filtragem 
			if (sessao.getAttribute("filtroSistemaEsgotoSessao") != null) {
				filtroSistemaEsgoto = (FiltroSistemaEsgoto) sessao.getAttribute("filtroSistemaEsgotoSessao");
			}
	
			if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterSistemaEsgoto"))) {
				
				filtroSistemaEsgoto.adicionarCaminhoParaCarregamentoEntidade("divisaoEsgoto");
				filtroSistemaEsgoto.adicionarCaminhoParaCarregamentoEntidade("sistemaEsgotoTratamentoTipo");
	
				Map resultado = controlarPaginacao(httpServletRequest, retorno, filtroSistemaEsgoto, SistemaEsgoto.class.getName());			
				colecaoSistemaEsgoto = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
				if (colecaoSistemaEsgoto == null || colecaoSistemaEsgoto.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
				
				String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
				
				if (colecaoSistemaEsgoto.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
					// caso o resultado do filtro só retorne um registro 
					// e o check box Atualizar estiver selecionado
					//o sistema não exibe a tela de manter, exibe a de atualizar 
					retorno = actionMapping.findForward("exibirAtualizarSistemaEsgoto");
					SistemaEsgoto sistemaEsgoto = (SistemaEsgoto) colecaoSistemaEsgoto.iterator().next();
					sessao.setAttribute("idSistemaEsgoto", sistemaEsgoto.getId().toString());
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSistemaEsgotoAction.do");
				}else{
					sessao.setAttribute("colecaoSistemaEsgoto", colecaoSistemaEsgoto);
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterSistemaEsgotoAction.do");
				}
	
			}
			sessao.removeAttribute("tipoPesquisaRetorno");
			
			return retorno;
		
		
	} 
	
}
