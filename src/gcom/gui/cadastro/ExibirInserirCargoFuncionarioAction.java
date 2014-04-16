package gcom.gui.cadastro;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.FiltroFuncionarioCargo;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

/**
 * @author Arthur Carvalho
 * @created 11 de agosto de 2008
 */
public class ExibirInserirCargoFuncionarioAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("inserirCargoFuncionario");

		InserirCargoFuncionarioActionForm inserirCargoFuncionarioActionForm = (InserirCargoFuncionarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirCargoFuncionarioActionForm.setDescricao("");

			if (inserirCargoFuncionarioActionForm.getDescricao() == null
					|| inserirCargoFuncionarioActionForm
							.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroFuncionarioCargo filtroFuncionarioCargo = new FiltroFuncionarioCargo();

				filtroFuncionarioCargo
						.setCampoOrderBy(FiltroFuncionario.ID);

				colecaoPesquisa = fachada.pesquisar(
						filtroFuncionarioCargo,
						FuncionarioCargo.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Cargo do Funcionário");
				} else {
					sessao.setAttribute("colecaoFuncionarioCargo",
							colecaoPesquisa);
				}

				// Coleção do Cargo do Funcionário
				filtroFuncionarioCargo = new FiltroFuncionarioCargo();
				
				filtroFuncionarioCargo
						.setCampoOrderBy(FiltroFuncionarioCargo.ID);

				Collection colecaoFuncionarioCargo = fachada
						.pesquisar(filtroFuncionarioCargo,
								FuncionarioCargo.class.getName());
				sessao.setAttribute("colecaoFuncionarioCargo",
						colecaoFuncionarioCargo);

			}

		}
		return retorno;
	}
}
