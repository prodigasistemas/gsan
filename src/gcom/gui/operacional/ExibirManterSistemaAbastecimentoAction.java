package gcom.gui.operacional;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0962] MANTER SISTEMA DE ABASTECIMENTO
 * 
 * @author Fernando Fontelles Filho
 * @date 30/10/2009
 */

public class ExibirManterSistemaAbastecimentoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("exibirManterSistemaAbastecimento");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = null;

		// Parte da verificação do filtro
        FiltroSistemaAbastecimento filtroSistemaAbastecimento = (FiltroSistemaAbastecimento) 
        	sessao.getAttribute("filtroSistemaAbastecimento");
        
		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroSistemaAbastecimento") != null) {
			filtroSistemaAbastecimento = (FiltroSistemaAbastecimento) sessao.getAttribute("filtroSistemaAbastecimento");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("exibirManterSistemaAbastecimento"))) {

			Map resultado = controlarPaginacao(httpServletRequest, retorno,	filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
			colecaoSistemaAbastecimento = (Collection<SistemaAbastecimento>) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0002] Nenhum registro encontrado				
			if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoSistemaAbastecimento != null && !colecaoSistemaAbastecimento.isEmpty() 
					&& identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarSistemaAbastecimento");
				SistemaAbastecimento sistemaAbastecimento = (SistemaAbastecimento) colecaoSistemaAbastecimento.iterator().next();
				sessao.setAttribute("sistemaAbastecimento", sistemaAbastecimento);
				sessao.setAttribute("idRegistroAtualizacao", sistemaAbastecimento);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSistemaAbastecimentoAction.do");
			}else{
				
				sessao.removeAttribute("colecaoSistemaAbastecimento");
				sessao.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterSistemaAbastecimentoAction.do");
			
			}

		}
		
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
	} 
	
}
