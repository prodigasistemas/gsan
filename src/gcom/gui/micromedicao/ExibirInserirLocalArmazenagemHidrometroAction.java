package gcom.gui.micromedicao;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;

/**
 * @author Arthur Carvalho
 * @created 06 de agosto de 2008
 */
public class ExibirInserirLocalArmazenagemHidrometroAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirLocalArmazenagemHidrometro");

		InserirLocalArmazenagemHidrometroActionForm inserirLocalArmazenagemHidrometroActionForm = (InserirLocalArmazenagemHidrometroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			inserirLocalArmazenagemHidrometroActionForm.setIndicadorUso("2");
		}

		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirLocalArmazenagemHidrometroActionForm.setIndicadorOficina(new Short("2"));
		}

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirLocalArmazenagemHidrometroActionForm.setDescricao("");

			if (inserirLocalArmazenagemHidrometroActionForm.getDescricao() == null
					|| inserirLocalArmazenagemHidrometroActionForm
							.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

				filtroHidrometroLocalArmazenagem
						.setCampoOrderBy(FiltroHidrometroLocalArmazenagem.ID);

				colecaoPesquisa = fachada.pesquisar(
						filtroHidrometroLocalArmazenagem,
						HidrometroLocalArmazenagem.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Local de Armazenagem do Hidrômetro");
				} else {
					sessao.setAttribute("colecaoHidrometroLocalArmazenagem",
							colecaoPesquisa);
				}

				// Coleção de local de armazenagem do hidrômetro
				filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();
				filtroHidrometroLocalArmazenagem
						.setCampoOrderBy(FiltroHidrometroLocalArmazenagem.ID);

				Collection colecaoHidrometroLocalArmazenagem = fachada
						.pesquisar(filtroHidrometroLocalArmazenagem,
								HidrometroLocalArmazenagem.class.getName());
				sessao.setAttribute("colecaoHidrometroLocalArmazenagem",
						colecaoHidrometroLocalArmazenagem);

			}

		}
		return retorno;
	}
}
