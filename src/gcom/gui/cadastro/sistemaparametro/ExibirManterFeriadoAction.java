package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.bean.FeriadoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0387] MANTER FERIADO
 * 
 * @author Kássia Albuquerque
 * @date 23/01/2007
 */

public class ExibirManterFeriadoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterFeriado");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoFeriado = null;

		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("colecaoFeriado") != null) {
			colecaoFeriado = (Collection) sessao.getAttribute("colecaoFeriado");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterFeriado"))) {
					
			// [FS0001] Nenhum registro encontrado	
			//			
			//	Caso  nenhum registro seja encontrado o sistema exibe a mensagem
			//	a baixo. " A pesquisa não retornou  nenhum resultado."
			
			if (colecaoFeriado == null || colecaoFeriado.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoFeriado.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarFeriado");
				FeriadoHelper feriadoHelper = (FeriadoHelper) colecaoFeriado.iterator().next();
				sessao.setAttribute("idFeriado", feriadoHelper.getId().toString());
				sessao.setAttribute("tipoFeriado",feriadoHelper.getTipoFeriado().toString());
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarFeriadoAction.do");
			}else{
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterFeriadoAction.do");
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;		
	} 
	
}
