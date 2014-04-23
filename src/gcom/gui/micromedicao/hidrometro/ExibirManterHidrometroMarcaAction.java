package gcom.gui.micromedicao.hidrometro;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroMarca;

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

public class ExibirManterHidrometroMarcaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
			ActionForward retorno = actionMapping.findForward("exibirManterHidrometroMarca");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Collection colecaoHidrometroMarca = null;
	
			// Parte da verificação do filtro
	        FiltroHidrometroMarca filtroHidrometroMarca = null;
	
			// Verifica se o filtro foi informado pela página de filtragem 
			if (sessao.getAttribute("filtroHidrometroMarcaSessao") != null) {
				filtroHidrometroMarca = (FiltroHidrometroMarca) sessao.getAttribute("filtroHidrometroMarcaSessao");
			}
	
			if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("exibirManterHidrometroMarca"))) {					
				Map resultado = controlarPaginacao(httpServletRequest, retorno, filtroHidrometroMarca, HidrometroMarca.class.getName());			
				colecaoHidrometroMarca = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
				if (colecaoHidrometroMarca == null || colecaoHidrometroMarca.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
				
				String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
				
				if (colecaoHidrometroMarca.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
					// caso o resultado do filtro só retorne um registro 
					// e o check box Atualizar estiver selecionado
					//o sistema não exibe a tela de manter, exibe a de atualizar 
					retorno = actionMapping.findForward("exibirAtualizarHidrometroMarca");
					HidrometroMarca hidrometroMarca = (HidrometroMarca) colecaoHidrometroMarca.iterator().next();
					sessao.setAttribute("idHidrometroMarca", hidrometroMarca.getId().toString());
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarHidrometroMarcaAction.do");
				}else{
					sessao.setAttribute("colecaoHidrometroMarca", colecaoHidrometroMarca);
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterHidrometroMarcaAction.do");
				}
	
			}
			sessao.removeAttribute("tipoPesquisaRetorno");
			
			return retorno;				
	}	
}
