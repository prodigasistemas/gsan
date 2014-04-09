package gcom.gui.faturamento;

import gcom.faturamento.FiltroQualidadeAgua;
import gcom.faturamento.QualidadeAgua;
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
 * [UC0507] MANTER QUALIDADE DE AGUA
 * 
 * @author Flavio Leonardo	
 * @date 26/09/2007
 */

public class ExibirManterQualidadeAguaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterQualidadeAgua");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<QualidadeAgua> colecaoQualidadeAgua = null;

		// Parte da verificação do filtro
        FiltroQualidadeAgua filtroQualidadeAgua = (FiltroQualidadeAgua) sessao.getAttribute("filtroQualidadeAgua");
        
		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroQualidadeAgua") != null) {
			filtroQualidadeAgua = (FiltroQualidadeAgua) sessao.getAttribute("filtroQualidadeAgua");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterQualidadeAgua"))) {

			Map resultado = controlarPaginacao(httpServletRequest, retorno,	filtroQualidadeAgua, QualidadeAgua.class.getName());
			colecaoQualidadeAgua = (Collection<QualidadeAgua>) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0002] Nenhum registro encontrado				
			if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoQualidadeAgua.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarQualidadeAgua");
				QualidadeAgua qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua.iterator().next();
				sessao.setAttribute("idRegistroAtualizar", qualidadeAgua);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarQualidadeAguaAction.do");
				//chama ExibirAtualizarMunicipioAction
			}else{
				sessao.removeAttribute("colecaoQualidade");
				sessao.setAttribute("colecaoQualidade", colecaoQualidadeAgua);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterQualidadeAguaAction.do");
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
		
	} 
	
}
