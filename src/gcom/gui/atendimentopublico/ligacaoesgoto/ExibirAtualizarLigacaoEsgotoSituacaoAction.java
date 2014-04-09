package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * [UC0789] Filtrar Situacao de Ligação de Esgoto
 * 
 * @author Bruno Barros
 *
 * @date 20/05/2008
 */
public class ExibirAtualizarLigacaoEsgotoSituacaoAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
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
				.findForward("ligacaoEsgotoSituacaoAtualizar");

		AtualizarLigacaoEsgotoSituacaoActionForm atualizarLigacaoEsgotoSituacaoActionForm = (AtualizarLigacaoEsgotoSituacaoActionForm) actionForm;

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

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();

/*		String descricao = null;
		String descricaoAbreviada = null;
		String consumoMinimoFaturamento = null;
		String indicadorFaturamentoSituacao = null;
		String indicadorExistenciaRede = null;
		String indicadorExistenciaLigacao = null;*/
		
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoSituacao.ID, id));
			Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(
					filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			if (colecaoLigacaoEsgotoSituacao != null
					&& !colecaoLigacaoEsgotoSituacao.isEmpty()) {
				ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util
						.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
			}

			if (id == null || id.trim().equals("")) {

				atualizarLigacaoEsgotoSituacaoActionForm.setId(ligacaoEsgotoSituacao
						.getId().toString());

				atualizarLigacaoEsgotoSituacaoActionForm
						.setDescricao(ligacaoEsgotoSituacao.getDescricao());

				atualizarLigacaoEsgotoSituacaoActionForm
						.setDescricaoAbreviada(ligacaoEsgotoSituacao
								.getDescricaoAbreviado());

			}

			atualizarLigacaoEsgotoSituacaoActionForm.setId(id);

			atualizarLigacaoEsgotoSituacaoActionForm.setDescricao(ligacaoEsgotoSituacao
					.getDescricao());

			atualizarLigacaoEsgotoSituacaoActionForm
					.setDescricaoAbreviada(ligacaoEsgotoSituacao
							.getDescricaoAbreviado());
			
			atualizarLigacaoEsgotoSituacaoActionForm
			.setConsumoMinimoFaturamento(ligacaoEsgotoSituacao
					.getVolumeMinimoFaturamento().toString());

			atualizarLigacaoEsgotoSituacaoActionForm.setIndicadorUso(""
					+ ligacaoEsgotoSituacao.getIndicadorUso());

			atualizarLigacaoEsgotoSituacaoActionForm
			.setIndicadorExistenciaRede(""
					+ ligacaoEsgotoSituacao
							.getIndicadorExistenciaRede());
			
			atualizarLigacaoEsgotoSituacaoActionForm
			.setIndicadorFaturamentoSituacao(""
					+ ligacaoEsgotoSituacao
							.getIndicadorFaturamentoSituacao());
			
			atualizarLigacaoEsgotoSituacaoActionForm
					.setIndicadorExistenciaLigacao(""
							+ ligacaoEsgotoSituacao
									.getIndicadorExistenciaLigacao());

			sessao.setAttribute("atualizarLigacaoEsgotoSituacao", ligacaoEsgotoSituacao);

			if (sessao.getAttribute("colecaoLigacaoEsgotoSituacao") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarLigacaoEsgotoSituacaoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarLigacaoEsgotoSituacaoAction.do");
			}
		}

		return retorno;
	}
}
