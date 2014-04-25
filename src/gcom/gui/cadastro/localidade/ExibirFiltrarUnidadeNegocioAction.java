package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarUnidadeNegocioAction extends GcomAction {
	/**
	 * Este caso de uso efetua pesquisa de Unidade de Negocio
	 * 
	 * [UC0???] Filtrar Unidade de Negocio
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 30/09/2008
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
				.findForward("filtrarUnidadeNegocio");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarUnidadeNegocioActionForm form = (FiltrarUnidadeNegocioActionForm) actionForm;

		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
					.toString());
		}

		//if (form.getAtualizar() == null) {
			
			form.setAtualizar("1");
		//}

		String idCliente = form.getIdCliente();

		String idGerenciaRegional = form.getIdGerenciaRegional();

		if (idCliente != null && !idCliente.trim().equals("")) {
			// Pesquisa o cliente na base
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
				Cliente cliente = (Cliente) colecaoCliente.iterator().next();

				form.setIdCliente(cliente.getId().toString());

				form.setNomeCliente(cliente.getNome());
			} else {
				form.setIdCliente("");
				form.setNomeCliente("Cliente inexistente");

				httpServletRequest.setAttribute("corCliente", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}
		}

		if (idGerenciaRegional != null && !idGerenciaRegional.trim().equals("")) {
			// Pesquisa o cliente na base
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, idGerenciaRegional));

			Collection colecaoGerenciaRegional = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional != null
					&& !colecaoGerenciaRegional.isEmpty()) {

				GerenciaRegional gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional
						.iterator().next();

				form.setIdGerenciaRegional(gerenciaRegional.getId().toString());

				form.setNomeGerenciaRegional(gerenciaRegional.getNome());
			} else {
				form.setIdGerenciaRegional("");
				form.setNomeGerenciaRegional("Gerência Regional inexistente");

				httpServletRequest.setAttribute("corGerenciaRegional",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"idGerenciaRegional");
			}

		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			form.setId("");
			form.setNome("");
			form.setNomeAbreviado("");
			form.setIdCliente("");
			form.setNomeCliente("");
			form.setIdGerenciaRegional("");
			form.setNomeGerenciaRegional("");
			form.setIndicadorUso("");

		}
		
		if (form.getAtualizar() == null) {
			form.setAtualizar("1");
		}
		
		return retorno;

	}
}
