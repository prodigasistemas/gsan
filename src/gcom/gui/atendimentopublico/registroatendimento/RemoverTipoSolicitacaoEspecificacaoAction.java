package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * processamento para remover a especificacao tipo solicitacao
 * 
 * @author Sávio Luiz
 * @date 28/07/2006
 */
public class RemoverTipoSolicitacaoEspecificacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("inserirTipoSolicitacaoEspecificacao");

		if (httpServletRequest.getParameter("tipoRetorno") != null
				&& httpServletRequest.getParameter("tipoRetorno").equals(
						"filtrar")) {
			retorno = actionMapping
					.findForward("filtrarTipoSolicitacaoEspecificacao");
		}
		if (httpServletRequest.getParameter("tipoRetorno") != null
				&& httpServletRequest.getParameter("tipoRetorno").equals(
						"atualizar")) {
			retorno = actionMapping
					.findForward("atualizarTipoSolicitacaoEspecificacao");

			if (httpServletRequest.getParameter("idTipoSolicitacao") != null) {
				sessao.setAttribute("idTipoSolicitacao", httpServletRequest
						.getParameter("idTipoSolicitacao"));
			}
		}

		Collection colecaoEspecificacaoTipoSolicitacao = (Collection) sessao
				.getAttribute("colecaoSolicitacaoTipoEspecificacao");

		Collection colecaoEspecificacaoTipoSolicitacaoRemovidas = null;
		if (sessao.getAttribute("colecaoEspecificacaoTipoSolicitacaoRemovidas") != null
				&& !sessao.getAttribute(
						"colecaoEspecificacaoTipoSolicitacaoRemovidas").equals(
						"")) {
			colecaoEspecificacaoTipoSolicitacaoRemovidas = (Collection) sessao
					.getAttribute("colecaoCobrancaCriterioLinhaRemovidas");
		} else {
			colecaoEspecificacaoTipoSolicitacaoRemovidas = new ArrayList();
		}

		Iterator iteratorEspecificacaoTipoSolicitacao = colecaoEspecificacaoTipoSolicitacao
				.iterator();
		String codigoSolicitacaoTipoEspecificacao = httpServletRequest
				.getParameter("codigoSolicitacaoTipoEspecificacao");
		while (iteratorEspecificacaoTipoSolicitacao.hasNext()) {
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) iteratorEspecificacaoTipoSolicitacao
					.next();
			long valorTempo = solicitacaoTipoEspecificacao.getUltimaAlteracao()
					.getTime();
			if (valorTempo == Long
					.parseLong(codigoSolicitacaoTipoEspecificacao)) {
				iteratorEspecificacaoTipoSolicitacao.remove();
				if (solicitacaoTipoEspecificacao.getId() != null
						&& !solicitacaoTipoEspecificacao.getId().equals("")) {
					colecaoEspecificacaoTipoSolicitacaoRemovidas
							.add(solicitacaoTipoEspecificacao);
				}

			}
		}

		sessao.setAttribute("colecaoEspecificacaoTipoSolicitacao",
				colecaoEspecificacaoTipoSolicitacao);
		sessao.setAttribute("colecaoEspecificacaoTipoSolicitacaoRemovidas",
				colecaoEspecificacaoTipoSolicitacaoRemovidas);

		return retorno;
	}
}
