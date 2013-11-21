package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Exibir Atualizar Solicitação Acesso Situação>>
 * 
 * @author: Wallace Thierre
 * @Data: 08/11/2010
 * 
 */
public class ExibirAtualizarSolicitacaoAcessoSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("SolicitacaoAcessoSituacaoAtualizar");

		AtualizarSolicitacaoAcessoSituacaoActionForm form = (AtualizarSolicitacaoAcessoSituacaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = httpServletRequest.getParameter("idRegistroAtualizacao");

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		if (id != null && !id.trim().equals("")) {

			FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();

			filtroSolicitacaoAcessoSituacao
					.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoAcessoSituacao.ID, id));

			Collection colecaoSolicitacaoAcessoSituacao = fachada.pesquisar(
					filtroSolicitacaoAcessoSituacao,
					SolicitacaoAcessoSituacao.class.getName());

			if (colecaoSolicitacaoAcessoSituacao != null
					&& !colecaoSolicitacaoAcessoSituacao.isEmpty()) {

				SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = (SolicitacaoAcessoSituacao) Util
						.retonarObjetoDeColecao(colecaoSolicitacaoAcessoSituacao);

				form.setDescricao(solicitacaoAcessoSituacao.getDescricao());

				form.setId("" + solicitacaoAcessoSituacao.getId());

				form.setIndicadorUso(""
						+ solicitacaoAcessoSituacao.getIndicadorUso());

				form.setCodigoSituacao(""
						+ solicitacaoAcessoSituacao.getCodigoSituacao());

				sessao.setAttribute("atualizarSolicitacaoAcessoSituacao",
						solicitacaoAcessoSituacao);
			}

			if (sessao.getAttribute("colecaoSolicitacaoAcessoSituacao") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarSolicitacaoAcessoSituacaoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarSolicitacaoAcessoSituacaoAction.do");
			}

		}
		return retorno;
	}
}
