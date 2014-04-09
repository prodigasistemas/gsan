package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
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
 * 
 * @author Arthur Carvalho
 * @date 17/10/2008
 */
public class ExibirAtualizarPerfilLigacaoEsgotoAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("ligacaoEsgotoPerfilAtualizar");

		AtualizarPerfilLigacaoEsgotoActionForm atualizarPerfilLigacaoEsgotoActionForm = (AtualizarPerfilLigacaoEsgotoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		} else {
			id = ((LigacaoEsgotoPerfil) sessao.getAttribute("ligacaoEsgotoPerfil")).getId().toString();
		}

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

		LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();

		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil= new FiltroLigacaoEsgotoPerfil();
			filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.ID, id));
			Collection colecaoLigacaoEsgotoPerfil = fachada.pesquisar(filtroLigacaoEsgotoPerfil,
					LigacaoEsgotoPerfil.class.getName());
			if (colecaoLigacaoEsgotoPerfil != null && !colecaoLigacaoEsgotoPerfil.isEmpty()) {
				ligacaoEsgotoPerfil = (LigacaoEsgotoPerfil) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoPerfil);
			}

			if (id == null || id.trim().equals("")) {

				atualizarPerfilLigacaoEsgotoActionForm.setId(ligacaoEsgotoPerfil.getId().toString());

				atualizarPerfilLigacaoEsgotoActionForm.setDescricao(ligacaoEsgotoPerfil.getDescricao());

				atualizarPerfilLigacaoEsgotoActionForm.setPercentualEsgotoConsumidaColetada(""+ligacaoEsgotoPerfil
						.getPercentualEsgotoConsumidaColetada());

				atualizarPerfilLigacaoEsgotoActionForm.setIndicadorPrincipal(ligacaoEsgotoPerfil.getIndicadorPrincipal());

				atualizarPerfilLigacaoEsgotoActionForm.setIndicadorUso(ligacaoEsgotoPerfil
						.getIndicadorUso());

			}

			atualizarPerfilLigacaoEsgotoActionForm.setId(ligacaoEsgotoPerfil.getId().toString());

			atualizarPerfilLigacaoEsgotoActionForm.setDescricao(ligacaoEsgotoPerfil.getDescricao());

			atualizarPerfilLigacaoEsgotoActionForm.setPercentualEsgotoConsumidaColetada(ligacaoEsgotoPerfil
					.getPercentualEsgotoConsumidaColetada().toString());

			atualizarPerfilLigacaoEsgotoActionForm.setIndicadorPrincipal(ligacaoEsgotoPerfil
					.getIndicadorPrincipal());

			atualizarPerfilLigacaoEsgotoActionForm.setIndicadorUso(ligacaoEsgotoPerfil
					.getIndicadorUso());

			sessao.setAttribute("atualizarPerfilLigacaoEsgoto", ligacaoEsgotoPerfil);

			if (sessao.getAttribute("colecaoLigacaoEsgotoPerfil") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarPerfilLigacaoEsgotoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarLigacaoEsgotoPerfilAction.do");
			}

		}

		return retorno;
	}
}
