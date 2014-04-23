package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
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
 * [UC1103] - Manter Tipo de Corte
 * 
 * @author Ivan Sergio
 * @date 06/12/2010
 */

public class ExibirManterTipoCorteAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterTipoCorte");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoCorteTipo = null;
		
		// Parte da verificação do filtro
		FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();

		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroCorteTipo") != null) {
			filtroCorteTipo = (FiltroCorteTipo) sessao.getAttribute("filtroCorteTipo");
		}
		
		if ((retorno != null) && (retorno.getName().equalsIgnoreCase("manterTipoCorte"))) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno, filtroCorteTipo, CorteTipo.class.getName());
			colecaoCorteTipo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			if (colecaoCorteTipo == null || colecaoCorteTipo.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String) sessao.getAttribute("atualizar");
			
			if (colecaoCorteTipo.size() == 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarTipoCorte");
				CorteTipo corteTipo = (CorteTipo) colecaoCorteTipo.iterator().next();
				sessao.setAttribute("tipoCorteAtualizar", corteTipo);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoCorteAction.do?menu=sim");
			}else{
				sessao.setAttribute("colecaoCorteTipo", colecaoCorteTipo);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterTipoCorteAction.do");
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
	} 
}
