package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de usuário
 * 
 * @author Vivianne Sousa
 * @created 24/02/2006
 */

public class UsuarioPesquisarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("usuarioPesquisar");
		PesquisarUsuarioActionForm pesquisarUsuarioActionForm = (PesquisarUsuarioActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// Inicializando Variaveis

		// Pesquisando usuarios tipo
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
		Collection<Usuario> collectionUsuarioTipo = fachada.pesquisar(
				filtroUsuarioTipo, UsuarioTipo.class.getName());
		httpServletRequest.setAttribute("collectionUsuarioTipo",
				collectionUsuarioTipo);
		// Fim de pesquisando usuarios tipo

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			pesquisarUsuarioActionForm.setTipoUsuario(null);
			pesquisarUsuarioActionForm.setNome("");
			pesquisarUsuarioActionForm.setMatriculaFuncionario("");
			pesquisarUsuarioActionForm.setNomeFuncionario("");
			pesquisarUsuarioActionForm.setLogin("");
			pesquisarUsuarioActionForm.setIdUnidadeOrganizacional("");
			pesquisarUsuarioActionForm.setNomeUnidadeOrganizacional("");
			sessao.removeAttribute("caminhoRetornoTelaPesquisa");

		}

		if (httpServletRequest.getParameter("mostrarLogin") != null) {
			sessao.setAttribute("mostrarLogin", "S");
		}

		// -------Parte que trata do código quando o usuário tecla enter
		String idDigitadoEnterFuncionario = pesquisarUsuarioActionForm
				.getMatriculaFuncionario();

		if (idDigitadoEnterFuncionario != null
				&& !idDigitadoEnterFuncionario.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterFuncionario) > 0) {
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idDigitadoEnterFuncionario));

			Collection<Funcionario> funcionarioEncontrado = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (funcionarioEncontrado != null
					&& !funcionarioEncontrado.isEmpty()) {
				// O funcionario foi encontrado
				pesquisarUsuarioActionForm.setMatriculaFuncionario(""
						+ ((Funcionario) ((List) funcionarioEncontrado).get(0))
								.getId());
				pesquisarUsuarioActionForm
						.setNomeFuncionario(((Funcionario) ((List) funcionarioEncontrado)
								.get(0)).getNome());
				httpServletRequest.setAttribute("idFuncionarioNaoEncontrado",
						"true");

			} else {

				pesquisarUsuarioActionForm.setMatriculaFuncionario("");
				httpServletRequest.setAttribute("idFuncionarioNaoEncontrado",
						"exception");
				pesquisarUsuarioActionForm
						.setNomeFuncionario("FUNCIONÁRIO INEXISTENTE");

			}

		}
		
		String idUnidadeOrganizacional = pesquisarUsuarioActionForm
				.getIdUnidadeOrganizacional();

		if (idUnidadeOrganizacional != null
				&& !idUnidadeOrganizacional.trim().equals("")
				&& Integer.parseInt(idUnidadeOrganizacional) > 0) {
			
			// Faz a consulta de Unidade Organizacional
			pesquisarUnidadeOrganizacional(httpServletRequest, retorno,
					pesquisarUsuarioActionForm);
		}

		// -------Fim de parte que trata do código quando o usuário tecla enter

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina usuario_pesquisar.jsp
			pesquisarUsuarioActionForm
					.setMatriculaFuncionario(httpServletRequest
							.getParameter("idCampoEnviarDados"));
			pesquisarUsuarioActionForm.setNomeFuncionario(httpServletRequest
					.getParameter("descricaoCampoEnviarDados"));
		}

		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaUsuario") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaUsuario",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaUsuario"));

		}
		sessao.removeAttribute("caminhoRetornoTelaPesquisaFuncionario");

		if (httpServletRequest.getParameter("limpaForm") != null) {
			pesquisarUsuarioActionForm.setMatriculaFuncionario("");
			pesquisarUsuarioActionForm.setNome("");
			pesquisarUsuarioActionForm.setNomeFuncionario("");
			pesquisarUsuarioActionForm.setLogin("");
			pesquisarUsuarioActionForm.setIdUnidadeOrganizacional("");
			pesquisarUsuarioActionForm.setNomeUnidadeOrganizacional("");
			pesquisarUsuarioActionForm.setTipoUsuario((new Integer(
					ConstantesSistema.NUMERO_NAO_INFORMADO)).toString());
			sessao.removeAttribute("bloquearUnidadeOrganizacional");
		}

		if (httpServletRequest.getParameter("idUnidadeOrganizacional") != null
				&& !httpServletRequest.getParameter("idUnidadeOrganizacional")
						.trim().equals("")) {

			sessao.setAttribute("bloquearUnidadeOrganizacional", true);

			pesquisarUsuarioActionForm
					.setIdUnidadeOrganizacional(httpServletRequest
							.getParameter("idUnidadeOrganizacional").trim());

			// Faz a consulta de Unidade Organizacional
			pesquisarUnidadeOrganizacional(httpServletRequest, retorno,
					pesquisarUsuarioActionForm);

		}
		
		String popup = (String) sessao.getAttribute("popup");
		if (popup != null && popup.equals("2")) {
			sessao.setAttribute("popup", popup);
		} else {
			sessao.removeAttribute("popup");
		}

		return retorno;

	}

	private void pesquisarUnidadeOrganizacional(
			HttpServletRequest httpServletRequest, ActionForward retorno,
			PesquisarUsuarioActionForm pesquisarUsuarioActionForm) {
		// Filtro para obter o local de armazenagem ativo de id informado
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID,
				new Integer(pesquisarUsuarioActionForm
						.getIdUnidadeOrganizacional())));
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoUnidadeOrganizacional = Fachada.getInstancia()
				.pesquisar(filtroUnidadeOrganizacional,
						UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoUnidadeOrganizacional != null
				&& !colecaoUnidadeOrganizacional.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);

			// Exibe o código e a descrição pesquisa na página
			pesquisarUsuarioActionForm
					.setIdUnidadeOrganizacional(unidadeOrganizacional.getId()
							.toString());
			pesquisarUsuarioActionForm
					.setNomeUnidadeOrganizacional(unidadeOrganizacional
							.getDescricao());

		} else {

			// Exibe mensagem de código inexiste e limpa o campo de código
			httpServletRequest.setAttribute("unidadeOrganizacionalInexistente",
					true);
			pesquisarUsuarioActionForm.setIdUnidadeOrganizacional("");
			pesquisarUsuarioActionForm
					.setNomeUnidadeOrganizacional("Unidade inexistente");

		}

	}
}
