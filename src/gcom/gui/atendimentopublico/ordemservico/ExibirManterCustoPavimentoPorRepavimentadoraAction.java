package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
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
 * [UC1108] Filtrar Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo	
 * @date 23/12/2010
 */
public class ExibirManterCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterCustoPavimentoPorRepavimentadora");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa o atributo se o usuário voltou para o manter
		if (sessao.getAttribute("colecaoCustoPavimentoRua") != null) {
		//	sessao.removeAttribute("colecaoCustoPavimentoRua");
		}
		
		if (sessao.getAttribute("colecaoCustoPavimentoCalcada") != null) {
		//	sessao.removeAttribute("colecaoCustoPavimentoCalcada");
		}

		Collection<UnidadeRepavimentadoraCustoPavimentoRua> colecaoCustoPavimentoRua = (Collection<UnidadeRepavimentadoraCustoPavimentoRua>) httpServletRequest.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
		
		Collection<UnidadeRepavimentadoraCustoPavimentoCalcada> colecaoCustoPavimentoCalcada = (Collection<UnidadeRepavimentadoraCustoPavimentoCalcada>) httpServletRequest.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
		
		// Rua
		if (colecaoCustoPavimentoRua != null && !colecaoCustoPavimentoRua.isEmpty()) {
			
			if (httpServletRequest.getParameter("atualizarRua") != null) {
				
				retorno = actionMapping.findForward("atualizarUnidadeRepavimentadoraCustoPavimentoRua");

				UnidadeRepavimentadoraCustoPavimentoRua unidadeCustoPavimentoRua = (UnidadeRepavimentadoraCustoPavimentoRua) colecaoCustoPavimentoRua.iterator().next();
				sessao.setAttribute("objetoUnidadeCustoPavimentoRua", unidadeCustoPavimentoRua);

			} else {
				
				sessao.removeAttribute("objetoUnidadeCustoPavimentoRua");
				httpServletRequest.setAttribute("colecaoCustoPavimentoRua", colecaoCustoPavimentoRua);
			}
		} else if (colecaoCustoPavimentoCalcada == null || colecaoCustoPavimentoCalcada.isEmpty()){
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		// Calçada
		if (colecaoCustoPavimentoCalcada != null && !colecaoCustoPavimentoCalcada.isEmpty()) {
			
			if (httpServletRequest.getParameter("atualizarCalcada") != null) {
				
				retorno = actionMapping.findForward("atualizarUnidadeRepavimentadoraCustoPavimentoCalcada");

				UnidadeRepavimentadoraCustoPavimentoCalcada unidadeCustoPavimentoCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) colecaoCustoPavimentoCalcada.iterator().next();
				sessao.setAttribute("objetoUnidadeCustoPavimentoCalcada", unidadeCustoPavimentoCalcada);

			} else {
				
				sessao.removeAttribute("objetoUnidadeCustoPavimentoCalcada");
				httpServletRequest.setAttribute("colecaoCustoPavimentoCalcada", colecaoCustoPavimentoCalcada);
			}
		} else if (colecaoCustoPavimentoRua == null || colecaoCustoPavimentoRua.isEmpty()){
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
