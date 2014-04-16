package gcom.gui.cobranca;


import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
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
 * @author Arthur Carvalho
 * @date 05/09/08
 */
public class ExibirManterCobrancaSituacaoAction extends GcomAction {
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterCobrancaSituacao");

		Collection colecaoCobrancaSituacao = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroCobrancaSituacao filtroCobrancaSituacao = (FiltroCobrancaSituacao) sessao
				.getAttribute("filtroCobrancaSituacao");

		filtroCobrancaSituacao.setCampoOrderBy(FiltroCobrancaSituacao.ID);	
		
		if (filtroCobrancaSituacao!= null && !filtroCobrancaSituacao.equals("")) {
			Map resultado = 
				controlarPaginacao(httpServletRequest, retorno, filtroCobrancaSituacao, CobrancaSituacao.class.getName());
			
			colecaoCobrancaSituacao = (Collection) resultado.get("colecaoRetorno");
			
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoCobrancaSituacao != null
				&& !colecaoCobrancaSituacao.isEmpty()) {
			if (colecaoCobrancaSituacao.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarCobrancaSituacao");
					CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) colecaoCobrancaSituacao
							.iterator().next();
					sessao.setAttribute("cobrancaSituacao", cobrancaSituacao);
				} else {
					httpServletRequest.setAttribute("colecaoCobrancaSituacao",
							colecaoCobrancaSituacao);
				}
			} else {
				httpServletRequest.setAttribute("colecaoCobrancaSituacao",
						colecaoCobrancaSituacao);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
