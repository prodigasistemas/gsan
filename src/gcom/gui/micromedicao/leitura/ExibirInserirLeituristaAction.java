package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.leitura.InserirLeituristaActionForm;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0588]Inserir Leiturista
 * 
 * @author Thiago Tenório
 * @date 22/07/2007
 */

public class ExibirInserirLeituristaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirLeiturista");

		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirLeituristaActionForm form = (InserirLeituristaActionForm) actionForm;
		
		//Usuário
		if (form.getLoginUsuario() != null &&
			!form.getLoginUsuario().equals("")) {
			
			getUsuario(form, fachada,
					form.getLoginUsuario(), sessao);
		}

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {
			// -------------- bt DESFAZER ---------------

			// Limpando o formulario
			form.setIdFuncionario("");
			form.setDescricaoFuncionario("");
			form.setEmpresaID("");
			form.setIdCliente("");
			form.setDescricaoCliente("");
			form.setDdd("");
			form.setTelefone("");
			form.setNumeroImei("");

		}
		
		// Bloquear o campo Empresa quando a empresa do usuario nao seja a empresa do sistema
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		if (usuario.getEmpresa() == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Empresa do usuário logado.");
		}
		if (!usuario.getEmpresa().getDescricao().equals(sistemaParametro.getNomeAbreviadoEmpresa())) {
			sessao.setAttribute("bloquearEmpresa", true);
			form.setEmpresaID(usuario.getEmpresa().getId().toString());
		}	

		// Verificar Existência do Leiturista Responsável(Funcionario)

		if ((form.getIdFuncionario() != null && !form.getIdFuncionario()
				.equals(""))) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, form.getIdFuncionario()));

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) colecaoFuncionario
						.iterator().next();
				form.setDescricaoFuncionario(funcionario.getNome());

			} else {
				httpServletRequest.setAttribute("funcionarioEncontrado",
						"exception");
				form.setIdFuncionario("");
				form.setDescricaoFuncionario("FUNCIONARIO INEXISTENTE");
			}

		}
		
		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
		}

		// Verificar Existência do Leiturista Responsável(Cliente)

		if ((form.getIdCliente() != null && !form.getIdCliente().equals(""))) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form.getIdCliente()));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
				form.setDescricaoCliente(cliente.getNome());

			} else {
				httpServletRequest.setAttribute("clienteEncontrado",
						"exception");
				form.setIdCliente("");
				form.setDescricaoCliente("CLIENTE INEXISTENTE");
			}

		}

		// Empresa

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoPesquisa = null;

		// Retorna empresa
		colecaoPesquisa = fachada.pesquisar(filtroEmpresa, Empresa.class
				.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			// Nenhum registro na tabela localidade_porte foi encontrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhum_registro_tabela", null, "Empresa");
		} else {
			sessao.setAttribute("colecaoEmpresa", colecaoPesquisa);
		}

		// Constrói filtro para pesquisa da Empresa
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		sessao.setAttribute("colecaoEmpresa", fachada.pesquisar(filtroEmpresa,
				Empresa.class.getName(), "EMPRESA"));

		httpServletRequest.setAttribute("colecaoEmpresa", colecaoPesquisa);

		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			httpServletRequest.setAttribute("nomeCampo", "idCliente");
		}

		return retorno;
	}
	
	/**
	 * Recupera o Usuário
	 *
	 * @author Bruno Barros
	 * @date 11/12/2006
	 *
	 * @param inserirLeituristaActionForm
	 * @param fachada
	 * @param idUsuario
	 * @return Descrição da Unidade Filtrada
	 */
	private void getUsuario(InserirLeituristaActionForm inserirLeituristaActionForm, 
			Fachada fachada, String idUsuario, HttpSession sessao) {
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, idUsuario));		
		
		// Recupera Usuário
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			inserirLeituristaActionForm.setNomeUsuario(usuario.getNomeUsuario());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			inserirLeituristaActionForm.setLoginUsuario("");
			inserirLeituristaActionForm.setNomeUsuario("Usuário Inexistente");
		}
	}	
}
