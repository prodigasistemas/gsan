package gcom.gui.cadastro.cliente;

import java.util.Collection;
import java.util.List;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que inicializa a primeira página do processo de atualizar cliente
 * 
 * @author Rodrigo
 */
public class ExibirAtualizarClienteNomeTipoAction extends GcomAction {

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
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarClienteNomeTipo");
		
		// Pesquisa os Tipos de Clientes para a página
		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
		HttpSession session = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Fachada fachada = Fachada.getInstancia();
//		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;
		
		/** *************************************************************************************** */
		/**
		 * Recebe o form da inicialização do processo - Esta foi a solução
		 * encontrada para a *
		 */
		/**
		 * passagem do inserir direto para o atualizar pela consulta, fazendo as
		 * substituição *
		 */
		/**
		 * dos dados do form de mesmo nome corretamente, é preciso criar um form
		 * novo no exibir *
		 */
		/** action (neste caso ExibirAtualizarClienteAction) * */
		/** *************************************************************************************** */
		if (httpServletRequest.getAttribute("ClienteActionForm") != null) {
			session.setAttribute("ClienteActionForm", httpServletRequest
					.getAttribute("ClienteActionForm"));
		}
		
		DynaValidatorForm clienteActionForm = (DynaValidatorForm) session.getAttribute("ClienteActionForm");
		
		/** *************************************************************************************** */

		/** Verificar as permissão especial para alterar o nome do cliente * */
		boolean temPermissaoAlterarNomeCliente = fachada
				.verificarPermissaoAlterarNomeCliente(usuario);
		httpServletRequest.setAttribute("temPermissaoAlterarNomeCliente",
				temPermissaoAlterarNomeCliente);
		/** *************************************************************************************** */
				
		//Verificar se o usuário possui cnpj ou rg
		
		String clienteCnpj = (String) clienteActionForm.get("cnpj");
		String clienteCpf = (String) clienteActionForm.get("cpf"); 
			
		if (clienteCnpj != null && !clienteCnpj.equals("")  || clienteCpf != null && !clienteCpf.equals("")) {
			session.setAttribute("temCpfCnpj", "true");
		}
		/** *************************************************************************************** */
		
		/** Verificar as permissão especial habilitar acrescimos por impontualidade* */
		boolean temPermissaoAlterarAcrescimos = fachada
				.verificarPermissaoValAcrescimosImpontualidade(usuario);
		httpServletRequest.setAttribute("temPermissaoAlterarAcrescimos",
				temPermissaoAlterarAcrescimos);

		//		Verifica o indicador Cobranca de acrescimos
		
		/** *************************************************************************************** */
		

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		String descricao = "";

		String descricaoAbreviada = "";

		if (sistemaParametro.getIndicadorUsoNMCliReceitaFantasia().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO
				.intValue()) {

			session.setAttribute("indicadorNomeFantasia", true);

			descricao = "Nome na Receita Federal:";

			descricaoAbreviada = "Nome de Fantasia:";

		} else {

			descricao = "Nome: ";

			descricaoAbreviada = "Nome Abreviado: ";

			session.removeAttribute("indicadorNomeFantasia");

		}

		session.setAttribute("descricao", descricao);
		session.setAttribute("descricaoAbreviada", descricaoAbreviada);
		
		
		
		
		String indicadorPessoaFisicaJuridica = (String) clienteActionForm.get("indicadorPessoaFisicaJuridica"); 
		
		filtroClienteTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.INDICADOR_PESSOA_FISICA_JURIDICA, indicadorPessoaFisicaJuridica));
		filtroClienteTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

		httpServletRequest.setAttribute("colecaoTipoPessoa", Fachada
				.getInstancia().pesquisar(filtroClienteTipo,
						ClienteTipo.class.getName()));

		/**
		 * Verificar as permissão especial para Visualizar Dia de Vencimento em
		 * Cliente *
		 */
		boolean temPermissaoVisualizarDiaVencimentoContaCliente = fachada
				.verificarPermissaoVisualizarDiaVencimentoContaCliente(usuario);
		httpServletRequest.setAttribute(
				"temPermissaoVisualizarDiaVencimentoContaCliente",
				temPermissaoVisualizarDiaVencimentoContaCliente);
		/** *************************************************************************************** */
		
		//Verificar se o usuário possui permissão especial para negativar cliente
		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_MANTER_CLIENTE_SEM_NEGATIVACAO));
		
		Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
		
		session.removeAttribute("permissaoEspecial");
		
		if (colecaoUsuarioPermisao != null && !colecaoUsuarioPermisao.isEmpty()) {
			session.setAttribute("permissaoEspecial", "true");
		}
		
		
		//**********************************************************************
		// CRC2103
		// Autor: Ivan Sergio
		// Data: 02/07/2009
		// Verifica se a tela deve ser exibida como um PopUp
		//**********************************************************************
		if (httpServletRequest.getParameter("POPUP") != null) {
			if (httpServletRequest.getParameter("POPUP").equals("true")) {
				session.setAttribute("POPUP", "true");
			}else {
				session.setAttribute("POPUP", "false");
			}
		}else if (session.getAttribute("POPUP") == null) {
			session.setAttribute("POPUP", "false");
		}
		//**********************************************************************

		return retorno;
	}
}
