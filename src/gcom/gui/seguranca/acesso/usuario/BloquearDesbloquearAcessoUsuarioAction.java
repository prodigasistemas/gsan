package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 08/06/2006
 */
public class BloquearDesbloquearAcessoUsuarioAction extends GcomAction {

	/**
	 * Este caso de uso permite bloquear ou desbloquear o acesso do usuário ao
	 * sistema.
	 * 
	 * [UC0291] Bloquear/Desbloquear Acesso Usuário
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/06/2006
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		BloquearDesbloquearAcessoUsuarioActionForm bloquearDesbloquearAcessoUsuarioActionForm = (BloquearDesbloquearAcessoUsuarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Usuario usuario = new Usuario();

		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ACESSO_USUARIO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ACESSO_USUARIO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		usuario.setOperacaoEfetuada(operacaoEfetuada);
		usuario.adicionarUsuario(Usuario.USUARIO_TESTE,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

		// [UC0107] -Fim- Registrar Transação
		
		
		
		String login = bloquearDesbloquearAcessoUsuarioActionForm.getLogin();

		if (login != null && !login.equals("")) {

			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ComparacaoTexto(
					FiltroUsuario.LOGIN, login));

			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (colecaoUsuario == null || colecaoUsuario.isEmpty()) {
				throw new ActionServletException("atencao.login_nao_existente",
						null, "" + login + "");
			}
			Iterator colecaoUsuarioIterator = colecaoUsuario.iterator();
			
			while (colecaoUsuarioIterator.hasNext() ) {
				usuario = (Usuario) colecaoUsuarioIterator.next();
				
				if ( usuario.getLogin().equalsIgnoreCase(login) ){
					break;
				}
			}
			}

		String idSituacaoUsuario = bloquearDesbloquearAcessoUsuarioActionForm
				.getUsuarioSituacao();

		if (idSituacaoUsuario != null
				&& !idSituacaoUsuario.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();
			filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(
					FiltroUsuarioSituacao.ID, idSituacaoUsuario));

			Collection colecaoUsarioSituacao = fachada.pesquisar(
					filtroUsuarioSituacao, UsuarioSituacao.class.getName());

			UsuarioSituacao usuarioSituacao = (UsuarioSituacao) colecaoUsarioSituacao
					.iterator().next();

			if (usuario.getUsuarioSituacao().getId().equals(idSituacaoUsuario)) {
				throw new ActionServletException("atencao.login_nao_existente",
						null, "" + login + "");
			}
		
				if(usuario.getLogin().equalsIgnoreCase(login)) {
					usuario.setUsuarioSituacao(usuarioSituacao);
				}
		}

		registradorOperacao.registrarOperacao(usuario);
		
		fachada.bloquearDesbloquearUsuarioSituacao(usuario);

		

		// Monta a Pagina de sucesso

		montarPaginaSucesso(httpServletRequest, usuario.getUsuarioSituacao()
				.getDescricaoUsuarioSituacao()
				+ " " + "efetuada com sucesso.",
				"Bloquear/Desbloquear acesso de outro usuário",
				"exibirBloquearDesbloquearAcessoUsuarioAction.do?menu=sim");

		return retorno;
	}

}
