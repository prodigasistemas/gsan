package gcom.gui.seguranca.acesso;

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
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.FiltroAlteracaoTipo;


/**
 * @author Vinícius de Melo Medeiros
 * @created 14/05/2008
 */
public class ExibirInserirAlteracaoTipoAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("inserirAlteracaoTipo");

		InserirAlteracaoTipoActionForm inserirAlteracaoTipoActionForm = (InserirAlteracaoTipoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirAlteracaoTipoActionForm.setDescricao("");
			inserirAlteracaoTipoActionForm.setDescricaoAbreviada("");
			
			if (inserirAlteracaoTipoActionForm.getDescricao() == null
					|| inserirAlteracaoTipoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;
				FiltroAlteracaoTipo filtroAlteracaoTipo = new FiltroAlteracaoTipo();

				filtroAlteracaoTipo.setCampoOrderBy(FiltroAlteracaoTipo.DESCRICAO);
				
				colecaoPesquisa = fachada.pesquisar(filtroAlteracaoTipo,
						AlteracaoTipo.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Tipo de Alteração");
				} else {
					sessao.setAttribute("colecaoAtividade", colecaoPesquisa);
				}

				// Coleção de Grupo de Faturamento
				FiltroAlteracaoTipo filtroAlteracaoTipo2 = new FiltroAlteracaoTipo();
				filtroAlteracaoTipo2.setCampoOrderBy(FiltroAlteracaoTipo.ID);

				Collection colecaoAlteracaoTipo2 = fachada.pesquisar(filtroAlteracaoTipo2,
						AlteracaoTipo.class.getName());
				sessao.setAttribute("colecaoAlteracaoTipo2", colecaoAlteracaoTipo2);

			}

		}
		return retorno;
	}
}
