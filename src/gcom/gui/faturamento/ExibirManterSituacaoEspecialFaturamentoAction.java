package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoSituacaoComando;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 * @date 21/08/08
 */
public class ExibirManterSituacaoEspecialFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterSituacaoEspecialFaturamento");

		HttpSession sessao = httpServletRequest.getSession(false);

		ArrayList<FaturamentoSituacaoComando> colecaoFaturamentoSituacaoComando = 
			(ArrayList<FaturamentoSituacaoComando>) sessao.getAttribute("colecaoFaturamentoSituacaoComando");


		if ( Util.isVazioOrNulo(colecaoFaturamentoSituacaoComando)) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");						
		}

		
		if (colecaoFaturamentoSituacaoComando.size() == 1 
				&& Util.verificarNaoVazio(httpServletRequest.getParameter("indicadorAtualizar"))) {
				
				retorno = actionMapping
						.findForward("exibirAtualizarSituacaoEspecialFaturamento");
				sessao.setAttribute("faturamentoSituacaoComando", colecaoFaturamentoSituacaoComando.iterator().next());
				
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSituacaoEspecialFaturamentoAction.do");

		} else {
			httpServletRequest.setAttribute("colecaoFaturamentoSituacaoComando",
					colecaoFaturamentoSituacaoComando);
			
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterSituacaoEspecialFaturamentoAction.do");

		}

		return retorno;
	}
}
