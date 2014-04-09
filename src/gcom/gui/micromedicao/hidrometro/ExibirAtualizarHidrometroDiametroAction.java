package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
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
 * @date 16/05/2008
 */
public class ExibirAtualizarHidrometroDiametroAction extends GcomAction {

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
				.findForward("HidrometroDiametroAtualizar");

		AtualizarHidrometroDiametroActionForm atualizarHidrometroDiametroActionForm = (AtualizarHidrometroDiametroActionForm) actionForm;

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

		HidrometroDiametro hidrometroDiametro = new HidrometroDiametro();

		if (id != null && !id.trim().equals("")) {

			FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();
			filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
					FiltroHidrometroDiametro.ID, id));
			Collection colecaoHidrometroDiametro = fachada.pesquisar(
					filtroHidrometroDiametro, HidrometroDiametro.class.getName());
			if (colecaoHidrometroDiametro != null
					&& !colecaoHidrometroDiametro.isEmpty()) {
				hidrometroDiametro = (HidrometroDiametro) Util
						.retonarObjetoDeColecao(colecaoHidrometroDiametro);
			}

			if (id == null || id.trim().equals("")) {

				atualizarHidrometroDiametroActionForm.setId(hidrometroDiametro
						.getId().toString());

				atualizarHidrometroDiametroActionForm
						.setDescricao(hidrometroDiametro.getDescricao());

				atualizarHidrometroDiametroActionForm
						.setDescricaoAbreviada(hidrometroDiametro
								.getDescricaoAbreviada());

			}

			atualizarHidrometroDiametroActionForm.setId(id);

			atualizarHidrometroDiametroActionForm.setDescricao(hidrometroDiametro
					.getDescricao());

			atualizarHidrometroDiametroActionForm
					.setDescricaoAbreviada(hidrometroDiametro
							.getDescricaoAbreviada());

			atualizarHidrometroDiametroActionForm.setIndicadorUso(""
					+ hidrometroDiametro.getIndicadorUso());
			
			if (hidrometroDiametro.getNumeroOrdem() != null && !hidrometroDiametro.equals("")){
				atualizarHidrometroDiametroActionForm.setNumeroOrdem(hidrometroDiametro.getNumeroOrdem().toString());
			}

			sessao.setAttribute("atualizarHidrometroDiametro", hidrometroDiametro);

			if (sessao.getAttribute("colecaoHidrometroDiametro") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarHidrometroDiametroAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarHidrometroDiametroAction.do");
			}

		}

		return retorno;
	}
}
