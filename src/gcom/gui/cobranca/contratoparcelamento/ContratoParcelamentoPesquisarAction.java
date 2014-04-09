package gcom.gui.cobranca.contratoparcelamento;

import java.util.Collection;

import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de contrato parcelamento
 * 
 * @author Paulo Diniz
 * @created 24/05/2011
 */

public class ContratoParcelamentoPesquisarAction extends GcomAction {

	Fachada fachada = Fachada.getInstancia();
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("contratoParcelamentoPesquisar");
		PesquisarContratoParcelamentoActionForm pesquisarContratoParcelamentoActionForm = (PesquisarContratoParcelamentoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(httpServletRequest.getParameter("indicadorPesquisaApenasContEncerrados") != null){
			sessao.setAttribute("indicadorPesquisaApenasContEncerrados", httpServletRequest.getParameter("indicadorPesquisaApenasContEncerrados"));
		}
		
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			pesquisarContratoParcelamentoActionForm.setDataContrato("");
			pesquisarContratoParcelamentoActionForm.setIndicadorSituacao("");
			pesquisarContratoParcelamentoActionForm.setNumeroContrato("");
			pesquisarContratoParcelamentoActionForm.setLoginUsuario("");
			pesquisarContratoParcelamentoActionForm.setNomeUsuario("");
			pesquisarContratoParcelamentoActionForm.setAutocompleteCliente("");
			sessao.removeAttribute("caminhoRetornoTelaPesquisa");
			sessao.removeAttribute("usuarioResponsavel");
			sessao.removeAttribute("cliente");

		}

		if (httpServletRequest.getParameter("limparForm") != null) {
			pesquisarContratoParcelamentoActionForm.setDataContrato("");
			pesquisarContratoParcelamentoActionForm.setIndicadorSituacao("");
			pesquisarContratoParcelamentoActionForm.setNumeroContrato("");
			pesquisarContratoParcelamentoActionForm.setLoginUsuario("");
			pesquisarContratoParcelamentoActionForm.setNomeUsuario("");
			pesquisarContratoParcelamentoActionForm.setAutocompleteCliente("");
			sessao.removeAttribute("usuarioResponsavel");
			sessao.removeAttribute("cliente");
		}
		
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaUsuario") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaUsuario",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaUsuario"));

		}
		
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaCliente") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaCliente",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaCliente"));

		}
		if (httpServletRequest.getParameter("consulta") != null
				&& httpServletRequest.getParameter("consulta").toString().trim().equalsIgnoreCase("usuario")) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, 
					pesquisarContratoParcelamentoActionForm.getLoginUsuario()));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			// [FS0009] - Verificar existência do usuário
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				pesquisarContratoParcelamentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
				pesquisarContratoParcelamentoActionForm.setLoginUsuario(usuario.getLogin());

				sessao.setAttribute("usuarioResponsavel", usuario);
				sessao.setAttribute("usuarioEncontrado","true");
			} else {
				pesquisarContratoParcelamentoActionForm.setLoginUsuario("");
				pesquisarContratoParcelamentoActionForm.setNomeUsuario("Usuário Inexistente");

				sessao.setAttribute("usuarioResponsavel", null);
				sessao.removeAttribute("usuarioEncontrado");
			}
			sessao.setAttribute("etapa", "primeira");
			
		} 
		
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("") 
				&& httpServletRequest.getParameter("tipoConsulta").equals("usuario")) {
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, 
					httpServletRequest.getParameter("idCampoEnviarDados")));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);

				pesquisarContratoParcelamentoActionForm.setLoginUsuario(usuario.getLogin());
				pesquisarContratoParcelamentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
				
				sessao.setAttribute("usuarioResponsavel", usuario);
				sessao.setAttribute("usuarioEncontrado","true");
			} else {
				pesquisarContratoParcelamentoActionForm.setLoginUsuario("");
				pesquisarContratoParcelamentoActionForm.setNomeUsuario("Usuário Inexistente");

				sessao.setAttribute("usuarioResponsavel", null);
				sessao.removeAttribute("usuarioEncontrado");
			}
			sessao.removeAttribute("caminhoRetornoTelaPesquisaUsuario");
			
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("") 
				&& httpServletRequest.getParameter("tipoConsulta").equals("cliente")) {
			int id = Integer.parseInt(httpServletRequest.getParameter("idCampoEnviarDados"));
			Cliente cliente = fachada.consultarCliente(id);
			cliente.setId(id);
			sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
			sessao.setAttribute("cliente", cliente);
		}
		

		String popup = (String) sessao.getAttribute("popup");
		if (popup != null && popup.equals("2")) {
			sessao.setAttribute("popup", popup);
		} else {
			sessao.removeAttribute("popup");
		}

		return retorno;

	}

}
