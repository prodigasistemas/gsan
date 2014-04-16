package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipo;
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
public class RemoverSolicitacaoEspecificacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = null;

		if (httpServletRequest.getParameter("atualizar") != null) {
			
			retorno = actionMapping.findForward("atualizarAdicionarSolicitacaoEspecificacao");
			                                    

		} else {
			retorno = actionMapping.findForward("adicionarSolicitacaoEspecificacao");
		}

		HttpSession sessao = httpServletRequest.getSession(false);

		//AdicionarSolicitacaoEspecificacaoActionForm adicionarSolicitacaoEspecificacaoActionForm = (AdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		Collection colecaoEspecificacaoServicoTipo = (Collection) sessao
				.getAttribute("colecaoEspecificacaoServicoTipo");

		Collection colecaoEspecificacaoServicoTipoRemovidas = null;
		if (sessao.getAttribute("colecaoEspecificacaoServicoTipoRemovidas") != null
				&& !sessao.getAttribute(
						"colecaoEspecificacaoServicoTipoRemovidas").equals("")) {
			colecaoEspecificacaoServicoTipoRemovidas = (Collection) sessao
					.getAttribute("colecaoEspecificacaoServicoTipoRemovidas");
		} else {
			colecaoEspecificacaoServicoTipoRemovidas = new ArrayList();
		}

		Iterator iteratorEspecificacaoTipoSolicitacao = colecaoEspecificacaoServicoTipo
				.iterator();
		String codigoSolicitacaoEspecificacao = httpServletRequest
				.getParameter("codigoSolicitacaoEspecificacao");
		while (iteratorEspecificacaoTipoSolicitacao.hasNext()) {
			EspecificacaoServicoTipo especificacaoServicoTipo = (EspecificacaoServicoTipo) iteratorEspecificacaoTipoSolicitacao
					.next();
			long valorTempo = especificacaoServicoTipo.getUltimaAlteracao()
					.getTime();
			if (valorTempo == Long.parseLong(codigoSolicitacaoEspecificacao)) {
				iteratorEspecificacaoTipoSolicitacao.remove();
				if (especificacaoServicoTipo.getSolicitacaoTipoEspecificacao() != null
						&& !especificacaoServicoTipo
								.getSolicitacaoTipoEspecificacao().equals("")
						&& especificacaoServicoTipo
								.getSolicitacaoTipoEspecificacao().getId() != null
						&& !especificacaoServicoTipo
								.getSolicitacaoTipoEspecificacao().getId()
								.equals("")) {
					colecaoEspecificacaoServicoTipoRemovidas
							.add(especificacaoServicoTipo);
				}

			}
		}

		sessao.setAttribute("colecaoEspecificacaoServicoTipo",
				colecaoEspecificacaoServicoTipo);
		sessao.setAttribute("colecaoEspecificacaoServicoTipoRemovidas",
				colecaoEspecificacaoServicoTipoRemovidas);

		return retorno;
	}
}
