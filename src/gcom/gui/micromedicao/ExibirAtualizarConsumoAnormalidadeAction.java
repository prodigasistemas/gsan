package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
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
 * 
 * @author Vinicius Medeiros
 * @date 203/06/2008
 */
public class ExibirAtualizarConsumoAnormalidadeAction extends GcomAction {

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
				.findForward("consumoAnormalidadeAtualizar");

		AtualizarConsumoAnormalidadeActionForm atualizarConsumoAnormalidadeActionForm = (AtualizarConsumoAnormalidadeActionForm) actionForm;

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

		ConsumoAnormalidade consumoAnormalidade = new ConsumoAnormalidade();

		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
			filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroConsumoAnormalidade.ID, id));
			Collection colecaoConsumoAnormalidade = fachada.pesquisar(
					filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());
			if (colecaoConsumoAnormalidade != null
					&& !colecaoConsumoAnormalidade.isEmpty()) {
				consumoAnormalidade = (ConsumoAnormalidade) Util
						.retonarObjetoDeColecao(colecaoConsumoAnormalidade);
			}

			if (id == null || id.trim().equals("")) {

				atualizarConsumoAnormalidadeActionForm.setId(consumoAnormalidade
						.getId().toString());

				atualizarConsumoAnormalidadeActionForm
						.setDescricao(consumoAnormalidade.getDescricao());

				atualizarConsumoAnormalidadeActionForm
						.setDescricaoAbreviada(consumoAnormalidade
								.getDescricaoAbreviada());
				atualizarConsumoAnormalidadeActionForm
					.setIndicadorRevisaoComPermissaoEspecial(
							consumoAnormalidade.getIndicadorRevisaoPermissaoEspecial().toString());

			}

			atualizarConsumoAnormalidadeActionForm.setId(id);

			atualizarConsumoAnormalidadeActionForm.setDescricao(consumoAnormalidade
					.getDescricao());

			atualizarConsumoAnormalidadeActionForm
					.setDescricaoAbreviada(consumoAnormalidade
							.getDescricaoAbreviada());

			atualizarConsumoAnormalidadeActionForm
			.setMensagemConta(consumoAnormalidade.getMensagemConta());
			
			atualizarConsumoAnormalidadeActionForm.setIndicadorUso(""
					+ consumoAnormalidade.getIndicadorUso());
			
			atualizarConsumoAnormalidadeActionForm
				.setIndicadorRevisaoComPermissaoEspecial(
					consumoAnormalidade.getIndicadorRevisaoPermissaoEspecial().toString());

			sessao.setAttribute("atualizarConsumoAnormalidade", consumoAnormalidade);

			if (sessao.getAttribute("colecaoConsumoAnormalidade") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarConsumoAnormalidadeAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarConsumoAnormalidadeAction.do");
			}

		}

		return retorno;
	}
}
