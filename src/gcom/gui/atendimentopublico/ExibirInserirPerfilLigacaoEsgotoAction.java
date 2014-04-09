package gcom.gui.atendimentopublico;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

/**
 * @author Arthur Carvalho
 * @created 16 de outubro de 2008
 */
public class ExibirInserirPerfilLigacaoEsgotoAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("inserirPerfilLigacaoEsgoto");

		InserirPerfilLigacaoEsgotoActionForm inserirPerfilLigacaoEsgotoActionForm = (InserirPerfilLigacaoEsgotoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

	
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirPerfilLigacaoEsgotoActionForm.setDescricao("");

			if (inserirPerfilLigacaoEsgotoActionForm.getDescricao() == null
					|| inserirPerfilLigacaoEsgotoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();

				filtroLigacaoEsgotoPerfil.setCampoOrderBy(FiltroLigacaoEsgotoPerfil.ID);

				colecaoPesquisa = fachada.pesquisar(filtroLigacaoEsgotoPerfil,
						LigacaoEsgotoPerfil.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(

					"atencao.pesquisa.nenhum_registro_tabela", null, "Perfil da Ligação de Esgoto");

				} else {

					sessao.setAttribute("colecaoLigacaoEsgotoPerfil", colecaoPesquisa);
				}
				// Coleção de Perfil da Ligação de Esgoto
				filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();

				filtroLigacaoEsgotoPerfil.setCampoOrderBy(FiltroLigacaoEsgotoPerfil.ID);

				Collection colecaoLigacaoEsgotoPerfil = fachada.pesquisar(filtroLigacaoEsgotoPerfil,
						LigacaoEsgotoPerfil.class.getName());

				sessao.setAttribute("colecaoLigacaoEsgotoPerfil", colecaoLigacaoEsgotoPerfil);
			}
		}

		return retorno;
	}
}
