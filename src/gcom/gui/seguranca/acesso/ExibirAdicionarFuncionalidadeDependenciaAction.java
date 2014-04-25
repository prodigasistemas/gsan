package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.Funcionalidade;
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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 02/05/2006
 */

public class ExibirAdicionarFuncionalidadeDependenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		AdicionarFuncionalidadeDependenciaActionForm adicionarFuncionalidadeDependenciaActionForm = (AdicionarFuncionalidadeDependenciaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String parametro = null;

		String funcionalidadeID = null;
		
		if (httpServletRequest
				.getParameter("idFuncionalidade") == null || httpServletRequest
				.getParameter("idFuncionalidade").equals("")){
			funcionalidadeID = (String) sessao.getAttribute("idFuncionalidade");				
		} else {
			funcionalidadeID = (String) httpServletRequest
				.getParameter("idFuncionalidade");
			sessao.setAttribute("idFuncionalidade", funcionalidadeID);
		}
		
		adicionarFuncionalidadeDependenciaActionForm.setFuncionalidadeID(funcionalidadeID);

		//httpServletRequest.setAttribute("idFuncionalidade", funcionalidadeID);

		if (httpServletRequest.getParameter("funcionalidade") != null) {

			parametro = httpServletRequest.getParameter("funcionalidade");
			sessao.setAttribute("funcionalidade", parametro);
		}

		if (((String) sessao.getAttribute("funcionalidade"))
				.equalsIgnoreCase("inserir")) {
			retorno = actionMapping
					.findForward("inserirAdicionarFuncionalidadeDependenciaAction");
		} else {
			retorno = actionMapping
					.findForward("atualizarAdicionarFuncionalidadeDependenciaAction");
		}

		String idFuncionalidade = null;

		// Verifica se o tipo da consulta de arrecadador é de imovel
		// se for os parametros de enviarDadosParametros serão mandados para
		// a pagina arrecadador_pesuisar.jsp

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& httpServletRequest.getParameter("tipoConsulta").equals(
						"funcionalidade")) {

			idFuncionalidade = httpServletRequest
					.getParameter("idCampoEnviarDados");

			// pesquisarFuncionalidadeActionFormidFuncionalidade
			// .setInscricaoImovel(httpServletRequest
			// .getParameter("descricaoCampoEnviarDados"));

			// }
			// idFuncionalidade = (String) httpServletRequest
			// .getParameter("funcionalidadeID");
		} else {

			idFuncionalidade = adicionarFuncionalidadeDependenciaActionForm
					.getComp_id();
		}

		if (idFuncionalidade != null && !idFuncionalidade.equals("")) {

			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();

			filtroFuncionalidade.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidade.ID, idFuncionalidade));

			Collection<Funcionalidade> colecaoFuncionalidade = fachada
					.pesquisar(filtroFuncionalidade, Funcionalidade.class
							.getName());

			if (colecaoFuncionalidade != null
					&& !colecaoFuncionalidade.isEmpty()) {

				// a Funcionalidade foi encontrada
				Funcionalidade funcionalidade = (Funcionalidade) Util
						.retonarObjetoDeColecao(colecaoFuncionalidade);

				adicionarFuncionalidadeDependenciaActionForm.setComp_id(String
						.valueOf(funcionalidade.getId()));
				adicionarFuncionalidadeDependenciaActionForm
						.setDescricaoFuncionalidade(funcionalidade
								.getDescricao());

				httpServletRequest.setAttribute(
						"funcionalidadeDependenciaEncontrada", "true");

				sessao.setAttribute("colecaoFuncionalidade",
						colecaoFuncionalidade);

			} else {
				// a Funcionalidade não foi encontrada
				adicionarFuncionalidadeDependenciaActionForm.setComp_id("");

				httpServletRequest.setAttribute(
						"funcionalidadeDependenciaNaoEncontrada", "exception");

				adicionarFuncionalidadeDependenciaActionForm
						.setDescricaoFuncionalidade("FUNCIONALIDADE INEXISTENTE");
			}

		}

		// if (httpServletRequest.getParameter("reload") != null) {
		// httpServletRequest.setAttribute("reload", true);

		// }

		return retorno;

	}

}
