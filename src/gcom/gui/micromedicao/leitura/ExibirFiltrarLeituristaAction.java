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
import gcom.gui.micromedicao.leitura.FiltrarLeituristaActionForm;
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
 * Action que define o pré-processamento da página de pesquisa de leiturista
 * 
 * Alterado em 11/06/2008
 * 
 * @author Thiago Tenório e Thiago Nascimento
 * @created 22 de Julho de 2007
 * 
 */
public class ExibirFiltrarLeituristaAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarLeiturista");

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarLeituristaActionForm filtrarLeituristaActionForm = (FiltrarLeituristaActionForm) actionForm;

		filtrarLeituristaActionForm.setAtualizar("1");
		
		// Bloquear o campo Empresa quando a empresa do usuario nao seja a empresa do sistema
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		if (usuario.getEmpresa() == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Empresa do usuário logado.");
		}
		if (!usuario.getEmpresa().getDescricao().equals(sistemaParametro.getNomeAbreviadoEmpresa())) {
			sessao.setAttribute("bloquearEmpresa", true);
			filtrarLeituristaActionForm.setEmpresaID(usuario.getEmpresa().getId().toString());
		}

		if (httpServletRequest.getParameter("menu") != null) {
			filtrarLeituristaActionForm.setIdFuncionario("");
			filtrarLeituristaActionForm.setDescricaoFuncionario("");
			filtrarLeituristaActionForm.setIdCliente("");
			filtrarLeituristaActionForm.setDescricaoCliente("");
			filtrarLeituristaActionForm.setDdd("");
			filtrarLeituristaActionForm.setTelefone("");
			filtrarLeituristaActionForm.setIndicadorUso("");
			filtrarLeituristaActionForm.setIndicadorAtualizar("1");
			filtrarLeituristaActionForm.setLoginUsuario("");
			filtrarLeituristaActionForm.setNomeUsuario("");
		}
		
		//Empresa
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,Empresa.class.getName());
		
		if(colecaoEmpresa != null && !colecaoEmpresa.isEmpty()){
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}

		// Verificar Existência do Leiturista Responsável(Funcionario)

		if ((filtrarLeituristaActionForm.getIdFuncionario() != null && !filtrarLeituristaActionForm
				.getIdFuncionario().equals(""))) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, filtrarLeituristaActionForm
							.getIdFuncionario()));

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) colecaoFuncionario
						.iterator().next();
				filtrarLeituristaActionForm.setDescricaoFuncionario(funcionario
						.getNome());

			} else {
				httpServletRequest.setAttribute("funcionarioEncontrado",
						"exception");
				filtrarLeituristaActionForm.setIdFuncionario("");
				filtrarLeituristaActionForm
						.setDescricaoFuncionario("FUNCIONARIO INEXISTENTE");
			}

		}
		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
		}

		// Verificar Existência do Leiturista Responsável(Cliente)

		if ((filtrarLeituristaActionForm.getIdCliente() != null && !filtrarLeituristaActionForm
				.getIdCliente().equals(""))) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, filtrarLeituristaActionForm
							.getIdCliente()));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
				filtrarLeituristaActionForm.setDescricaoCliente(cliente
						.getNome());

			} else {
				httpServletRequest.setAttribute("clienteEncontrado",
						"exception");
				filtrarLeituristaActionForm.setIdCliente("");
				filtrarLeituristaActionForm
						.setDescricaoCliente("CLIENTE INEXISTENTE");
			}

		}

		filtrarLeituristaActionForm.setIndicadorAtualizar("1");

		if (filtrarLeituristaActionForm.getTipoPesquisa() == null
				|| filtrarLeituristaActionForm.getTipoPesquisa()
						.equalsIgnoreCase("")) {
			filtrarLeituristaActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
		}
		
		//Usuário
		if (filtrarLeituristaActionForm.getLoginUsuario() != null &&
			!filtrarLeituristaActionForm.getLoginUsuario().equals("")) {
			
			getUsuario(filtrarLeituristaActionForm, fachada,
					filtrarLeituristaActionForm.getLoginUsuario(), sessao);
		}
		

		// código para checar ou naum o Atualizar
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarLeituristaActionForm.setIndicadorUso("3");
			filtrarLeituristaActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
		}
		// se voltou da tela de Atualizar Leiturista
		if (sessao.getAttribute("voltar") != null
				&& sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if (sessao.getAttribute("tipoPesquisa") != null) {
				filtrarLeituristaActionForm.setTipoPesquisa(sessao
						.getAttribute("tipoPesquisa").toString());
			}
		}
		sessao.removeAttribute("voltar");
		sessao.removeAttribute("idRegistroAtualizacao");
		sessao.removeAttribute("tipoPesquisa");

		return retorno;
	}
	
	/**
	 * Recupera o Usuário
	 *
	 * @author Bruno Barros
	 * @date 11/12/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param idUsuario
	 * @return Descrição da Unidade Filtrada
	 */
	private void getUsuario(FiltrarLeituristaActionForm filtrarRegistroAtendimentoActionForm, 
			Fachada fachada, String idUsuario, HttpSession sessao) {
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, idUsuario));
		
		
		// Recupera Usuário
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			filtrarRegistroAtendimentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
			filtrarRegistroAtendimentoActionForm.setLoginUsuario(usuario.getLogin());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			filtrarRegistroAtendimentoActionForm.setLoginUsuario("");
			filtrarRegistroAtendimentoActionForm.setNomeUsuario("Usuário Inexistente");
		}
	}	
}
