package gcom.gui.faturamento.conta;

import java.util.Collection;
import java.util.Map;

import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.FiltroContaMotivoRetificacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterMotivoRetificacaoAction extends GcomAction {

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
				.findForward("exibirManterMotivoRetificacaoAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroContaMotivoRetificacao filtroContaMotivoRetificacao = (FiltroContaMotivoRetificacao) sessao
				.getAttribute("filtroContaMotivoRetificacao");
		
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroContaMotivoRetificacao, ContaMotivoRetificacao.class.getName());
		Collection colecaoContaMotivoRetificacao = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		sessao.removeAttribute("colecaoContaMotivoRetificacao");
		
		if (colecaoContaMotivoRetificacao != null && !colecaoContaMotivoRetificacao.isEmpty()) {
			if (colecaoContaMotivoRetificacao.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (sessao.getAttribute("atualizar") != null) {
					retorno = actionMapping
							.findForward("atualizarMotivoRetificacao");
					ContaMotivoRetificacao contaMotivoRetificacao = (ContaMotivoRetificacao) colecaoContaMotivoRetificacao
							.iterator().next();
					sessao.setAttribute("objetoContaMotivoRetificacao",
							contaMotivoRetificacao);

				} else {
					httpServletRequest.setAttribute("colecaoContaMotivoRetificacao",
							colecaoContaMotivoRetificacao);
				}
			} else {
				httpServletRequest.setAttribute("colecaoContaMotivoRetificacao",
						colecaoContaMotivoRetificacao);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return retorno;
	}

}
