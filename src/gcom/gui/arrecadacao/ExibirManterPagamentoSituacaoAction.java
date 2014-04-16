package gcom.gui.arrecadacao;

import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Vinícius Medeiros
 * 
 */
public class ExibirManterPagamentoSituacaoAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterPagamentoSituacao");

		// Coleção da Situação de Pagamento
		Collection colecaoPagamentoSituacao = new ArrayList();

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroPagamentoSituacao filtroPagamentoSituacao = (FiltroPagamentoSituacao) sessao
				.getAttribute("filtroPagamentoSituacao");

		filtroPagamentoSituacao.setCampoOrderBy(
				FiltroPagamentoSituacao.CODIGO,
				FiltroPagamentoSituacao.DESCRICAO,
				FiltroPagamentoSituacao.DESCRICAO_ABREVIADA);

		if (filtroPagamentoSituacao != null	&& !filtroPagamentoSituacao.equals("")) {
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroPagamentoSituacao, PagamentoSituacao.class.getName());
			colecaoPagamentoSituacao = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		
		}

		if (colecaoPagamentoSituacao != null&& !colecaoPagamentoSituacao.isEmpty()) {
			
			if (colecaoPagamentoSituacao.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null 
							|| httpServletRequest.getParameter("page.offset").equals("1"))) {
				
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
				
					// Seta o novo retorno, caso o indicador ATUALIZAR esteja marcado
					retorno = actionMapping.findForward("exibirAtualizarPagamentoSituacao");
					
					PagamentoSituacao pagamentoSituacao = (PagamentoSituacao) colecaoPagamentoSituacao
							.iterator().next();
					sessao.setAttribute("pagamentoSituacao", pagamentoSituacao);
				
				} else {
				
					httpServletRequest.setAttribute("colecaoPagamentoSituacao",colecaoPagamentoSituacao);
				}
			
			} else {
			
				httpServletRequest.setAttribute("colecaoPagamentoSituacao",colecaoPagamentoSituacao);
			
			}
		
		} else {
			// Caso a pesquisa não traga nenhum resultado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		
		}

		return retorno;
	}
}
