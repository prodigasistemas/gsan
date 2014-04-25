package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.Modulo;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarFuncionalidadeAction extends GcomAction {

	/**
	 * Este caso de uso efetua pesquisa de funcionalidade
	 * 
	 * [UC0283] Filtrar Funcionalidade
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/05/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("funcionalidadeFiltrar");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarFuncionalidadeActionForm filtrarfuncionalidadeActionForm = (FiltrarFuncionalidadeActionForm) actionForm;

		// Limpa o atributo se o usuário voltou o filtrar
		if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
			sessao.removeAttribute("colecaoFuncionalidadeTela");
		}

		filtrarfuncionalidadeActionForm.setAtualizar("1");

		FiltroModulo filtroModulo = new FiltroModulo();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<Modulo> colecaoModulo = fachada.pesquisar(filtroModulo,
				Modulo.class.getName());

		if (colecaoModulo == null || colecaoModulo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Modulo");
		}

		httpServletRequest.setAttribute("colecaoModulo", colecaoModulo);

		return retorno;
	}

}
