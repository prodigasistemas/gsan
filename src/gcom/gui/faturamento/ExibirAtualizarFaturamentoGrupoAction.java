package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
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
 * 
 * @author Vinicius Medeiros
 * @date 07/04/2008
 */
public class ExibirAtualizarFaturamentoGrupoAction extends GcomAction {

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
				.findForward("atualizarFaturamentoGrupo");

		AtualizarFaturamentoGrupoActionForm atualizarFaturamentoGrupoActionForm = (AtualizarFaturamentoGrupoActionForm) actionForm;

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

		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();

		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, id));
			Collection colecaoFaturamentoGrupo = fachada.pesquisar(
					filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
			if (colecaoFaturamentoGrupo != null
					&& !colecaoFaturamentoGrupo.isEmpty()) {
				faturamentoGrupo = (FaturamentoGrupo) Util
						.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
			}

			if (id == null || id.trim().equals("")) {

				atualizarFaturamentoGrupoActionForm.setId(faturamentoGrupo
						.getId().toString());

				atualizarFaturamentoGrupoActionForm
						.setDescricao(faturamentoGrupo.getDescricao());

				atualizarFaturamentoGrupoActionForm
						.setDescricaoAbreviada(faturamentoGrupo
								.getDescricaoAbreviada());

			}

			atualizarFaturamentoGrupoActionForm.setId(id);

			atualizarFaturamentoGrupoActionForm.setDescricao(faturamentoGrupo
					.getDescricao());

			atualizarFaturamentoGrupoActionForm
					.setDescricaoAbreviada(faturamentoGrupo
							.getDescricaoAbreviada());

			atualizarFaturamentoGrupoActionForm
					.setAnoMesReferencia( Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia()));
			
			atualizarFaturamentoGrupoActionForm
					.setDiaVencimento(faturamentoGrupo.getDiaVencimento()
							.toString());

			atualizarFaturamentoGrupoActionForm.setIndicadorUso(""
					+ faturamentoGrupo.getIndicadorUso());

			atualizarFaturamentoGrupoActionForm
					.setIndicadorVencimentoMesFatura(""
							+ faturamentoGrupo
									.getIndicadorVencimentoMesFatura());

			sessao.setAttribute("faturamentoGrupoAtualizar", faturamentoGrupo);

			if (sessao.getAttribute("colecaoFaturamentoGrupo") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarFaturamentoGrupoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarFaturamentoGrupoAction.do");
			}

		}

		return retorno;
	}
}
