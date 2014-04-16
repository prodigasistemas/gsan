package gcom.gui.seguranca;

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
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;

/**
 * @author Arthur Carvalho
 * @created 26 de agosto de 2008
 */
public class ExibirInserirUsuarioTipoAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("inserirUsuarioTipo");

		InserirUsuarioTipoActionForm inserirUsuarioTipoActionForm = (InserirUsuarioTipoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirUsuarioTipoActionForm.setIndicadorFuncionario(new Short("2"));
		}
		
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirUsuarioTipoActionForm.setDescricao("");

			if (inserirUsuarioTipoActionForm.getDescricao() == null
					|| inserirUsuarioTipoActionForm
							.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();

				filtroUsuarioTipo
						.setCampoOrderBy(FiltroUsuarioTipo.ID);

				colecaoPesquisa = fachada.pesquisar(
						filtroUsuarioTipo,
						UsuarioTipo.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Tipo de Usuário");
				} else {
					sessao.setAttribute("colecaoUsuarioTipo",
							colecaoPesquisa);
				}

				// Coleção de Tipo de Usuario
				filtroUsuarioTipo = new FiltroUsuarioTipo();
				
				filtroUsuarioTipo
						.setCampoOrderBy(FiltroUsuarioTipo.ID);

				Collection colecaoUsuarioTipo = fachada
						.pesquisar(filtroUsuarioTipo,
								UsuarioTipo.class.getName());
				sessao.setAttribute("colecaoUsuarioTipo",
						colecaoUsuarioTipo);

			}

		}
		return retorno;
	}
}
