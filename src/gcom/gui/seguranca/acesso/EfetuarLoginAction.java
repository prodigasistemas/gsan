package gcom.gui.seguranca.acesso;


import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.SessaoHttpListener;
import gcom.seguranca.acesso.FiltroGrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacaoPK;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupoRestricao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioFavorito;
import gcom.seguranca.acesso.usuario.UsuarioGrupoRestricao;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.Internacionalizador;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esse action valida o usuário e coloca as informações na sessão, todo o acesso
 * terá que passar obrigatoriamente por aqui primeiro
 * 
 * @author Pedro Alexandre
 * @date 05/07/2006
 */
public class EfetuarLoginAction extends GcomAction {

	/**
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Pedro Alexandre
	 * @date 04/07/2006
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

		// Prepara o retorno da ação para a tela principal
		ActionForward retorno = actionMapping.findForward("telaPrincipal");

		// Recupera o ActionForm
		EfetuarLoginActionForm loginActionForm = (EfetuarLoginActionForm) actionForm;
		
		String visualizacaoRAUrgencia = (String) httpServletRequest.getParameter("visualizacaoRAUrgencia");
		
		if(visualizacaoRAUrgencia == null || !visualizacaoRAUrgencia.equals("sim")){
						
		
			// Variável que vai armazenar o usuário logado
			Usuario usuarioLogado = null;
	
			// Recupera o login e a senha do usuário
			String login = loginActionForm.getLogin();
			String senha = loginActionForm.getSenha();
	
			// [FS0003] - Verificar preenchimento do login
			if (login == null || login.trim().equals("")) {
				this.reportarErros(httpServletRequest, "atencao.login.invalido");
				retorno = actionMapping.findForward("telaLogin");
			} else {
	
				//Cria a variável que vai armazenar a mensagem com a quantidade de 
				//dias que falta para expirar a validade da senha
				String mensagemExpiracao = "";
				
				// [FS0001] - Verificar existência do login
				if (!this.verificarExistenciaLogin(login)) {
					this.reportarErrosMensagem(httpServletRequest,"atencao.login.inexistente", login);
					retorno = actionMapping.findForward("telaLogin");
				} else {
	
					// Cria uma instancia da sessão
					HttpSession sessao = httpServletRequest.getSession(true);
	
					// [FS0004] - Validar senha do login
					// Busca o usuário no sistema, o usuário será nulo se não
					// existir
					usuarioLogado = this.getFachada().validarUsuario(login, senha);
	
					// [FS0005] - Verificar número de tentativas.
					Integer numeroTentativas = (Integer) sessao.getAttribute("numeroTentativas");
					Short numeroTentativasPermitidas = this.getSistemaParametro().getNumeroMaximoLoginFalho();
					if (numeroTentativas == null) {
						numeroTentativas = new Integer(0);
						sessao.setAttribute("numeroTentativas", numeroTentativas);
					}
	
					// Recupera o login do usuário da sessão
					String loginUsuarioSessao = (String) sessao.getAttribute("loginUsuarioSessao");
	
					// Caso seja a primeira vez que o usuário esteja logando
					// joga o login do usuário na sessão
					if (loginUsuarioSessao == null) {
						loginUsuarioSessao = login;
						sessao.setAttribute("loginUsuarioSessao",loginUsuarioSessao);
					}
	
					// Caso o usuário não esteja cadastrado, manda o erro para a
					// página de login
					if (usuarioLogado == null) {
						this.reportarErros(httpServletRequest,"atencao.usuario.inexistente");
						retorno = actionMapping.findForward("telaLogin");
	
						/*
						 * Caso o login informado seja igual ao que está na sessão
						 * incrementa o nº de tentativas e joga esse nº na sessão
						 * verifica se o nº de tentativas é maior que a permitida se
						 * for bloqueia a senha do usuário e indica o erro na página
						 * de login
						 */
						if (loginUsuarioSessao.equals(login)) {
							numeroTentativas = numeroTentativas + 1;
							sessao.setAttribute("numeroTentativas",numeroTentativas);
	
							// [FS0005] - Verificar número de tentativas de acesso
							if (numeroTentativas.intValue() > numeroTentativasPermitidas.intValue()) {
								this.bloquearSenha(login);
								this.reportarErros(httpServletRequest,"atencao.usuario.senha.bloqueada");
								retorno = actionMapping.findForward("telaLogin");
							}
						} else {
							//Zera o nº de tentativas de acesso e joga o login do usuário na sessão
							numeroTentativas = 0;
							sessao.setAttribute("loginUsuarioSessao", login);
						}
	
					} else {
						// [FS0002] - Verificar situação do usuário
						if (!this.verificarSituacaoUsuario(usuarioLogado)) {
							if (usuarioLogado.getUsuarioSituacao().getId().equals(UsuarioSituacao.INATIVO)) {
								throw new ActionServletException("atencao.usuario_invalido", null, usuarioLogado.getLogin());
							} else {
								this.reportarErrosMensagem(	httpServletRequest,	"atencao.usuario.situacao.invalida",login+ " está com situação correspondente a "+ usuarioLogado.getUsuarioSituacao().getDescricaoAbreviada());
								retorno = actionMapping.findForward("telaLogin");
							}
						}
	
						//[SB0005] Efetuar Controle de Alteração de Senha
						boolean disponibilizarAlteracaoSenha = false;
						Date dataExpiracaoAcesso = usuarioLogado.getDataExpiracaoAcesso();
						UsuarioSituacao usuarioSituacao = usuarioLogado.getUsuarioSituacao();
						
						//Caso a data de expiração de acesso esteja preenchida e seja menor 
						//que a data atual disponibiliza a tela de alteração de senha
						if (dataExpiracaoAcesso != null) {
							if (dataExpiracaoAcesso.before(new Date())) {
								disponibilizarAlteracaoSenha = true;
							}
						}
	
						//Caso a situação da senha do usuário seja igual a "pendente"
						//disponibiliza a tela de alteração de senha 
						if (usuarioSituacao.getId().equals(UsuarioSituacao.PENDENTE_SENHA)) {
							disponibilizarAlteracaoSenha = true;
						}
	
						//Caso a flag de disponibilizar alteração de senha esteja "true"
						//seta o mapeamento para a tela de alterar senha
						sessao.setAttribute("usuarioLogado", usuarioLogado);
						
						if (disponibilizarAlteracaoSenha) {
							retorno = actionMapping.findForward("alterarSenha");
						}
						
						Fachada.getInstancia().montarMenuUsuario(sessao, httpServletRequest.getRemoteAddr());
					}					
				}
			}
		
		}else{
			Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
			
			//Código para remover a mensagem de "Alerta de RA Urgente" quando usuario pressionar OK
			this.getFachada().atualizarUsuarioVisualizacaoRaUrgencia(null,null, usuarioLogado.getId(),null,1);			
						
			this.getSessao(httpServletRequest).setAttribute("RAUrgencia", "false");
			
		}
		
		
		verificarLocaleInternacionalizacao(httpServletRequest);
		
		return retorno;
	}
	
	
	private void verificarLocaleInternacionalizacao(HttpServletRequest httpServletRequest){
		Locale localeStruts = 
				(Locale)httpServletRequest.getSession(false).getAttribute(Globals.LOCALE_KEY);

		if(Internacionalizador.getLocale() == null || 
				!Internacionalizador.getLocale().equals(localeStruts)){
			
			Internacionalizador.setLocale(localeStruts);			
		}
	}

	/**
	 * Verifica se o login informado existe para algum usuário do sistema
	 * retorna true se existir caso contrário retorna false.
	 * 
	 * [UC0287] - Verificar existência do login
	 * 
	 * @author Pedro Alexandre
	 * @date 06/07/2006
	 * 
	 * @param login
	 * @return
	 */
	private boolean verificarExistenciaLogin(String login) {
		// Inicializa o retorno para falso(login não existe)
		boolean retorno = false;

		// Cria o filtro e pesquisa o usuário com o login informado
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, login));
		Collection usuarios = Fachada.getInstancia().pesquisar(filtroUsuario,
				Usuario.class.getName());

		// Caso exista o usuário com o login informado
		// seta o retorno para verdadeiro(login existe no sistema)
		if (usuarios != null && !usuarios.isEmpty()) {
			retorno = true;
		}
		// Retorna um indicador se o login informado existe ou não no sistema
		return retorno;
	}

	/**
	 * Metódo que verifica se a situação do usuário é diferente de ativo ou se é
	 * igual a senha pendente.Caso seja uma ou outra situação levanta uma
	 * exceção para o usuário indicando que o usuário não pode se logar ao
	 * sistema.
	 * 
	 * [FS0002] - Verificar situação do usuário
	 * 
	 * @author Pedro Alexandre
	 * @date 06/07/2006
	 * 
	 * @param usuarioLogado
	 * @return
	 */
	private boolean verificarSituacaoUsuario(Usuario usuarioLogado) {
		boolean retorno = true;
		// Recupera a situação do usuário
		UsuarioSituacao usuarioSituacao = usuarioLogado.getUsuarioSituacao();

		/*
		 * Caso a situação do usuário não seja igual a ativo ou seja igual a
		 * pendente retorna uma flag indicando que o usuário não pode acessar o
		 * sistema
		 */
		if ((!usuarioSituacao.getId().equals(UsuarioSituacao.ATIVO))
				&& (!usuarioSituacao.getId().equals(
						UsuarioSituacao.PENDENTE_SENHA))) {
			retorno = false;
		}

		// Retorna uma flag indicando se a situção do usuário permite o acesso
		// ao sistema
		return retorno;
	}

	/**
	 * Bloqueia a senha do usuário depois de o números de tentativas de acesso
	 * exceder o número máximo de tentativas permitidas
	 * 
	 * [FS0005] - Verificar número de tentativas de acesso
	 * 
	 * @author Pedro Alexandre
	 * @date 06/07/2006
	 * 
	 * @param usuarioLogado
	 */
	private void bloquearSenha(String login) {

		// Pesquisa o usário que vai ser bloqueada sua senha
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, login));
		Collection usuarios = Fachada.getInstancia().pesquisar(filtroUsuario,
				Usuario.class.getName());

		// Caso encontre o usuário com o login informado
		if (usuarios != null && !usuarios.isEmpty()) {
			// Recupera o usuário
			Usuario usuarioLogado = (Usuario) usuarios.iterator().next();

			// Atualiza a situação do usuário para bloqueada
			UsuarioSituacao usuarioSituacao = new UsuarioSituacao();
			usuarioSituacao.setId(UsuarioSituacao.SENHA_BLOQUEADA);

			// Recupera o nº de vezes que o usuário foi bloqueado
			Short bloqueioAcesso = usuarioLogado.getBloqueioAcesso();

			/*
			 * Caso o usuário nunca tenha sido bloqueado seta o nº de bloqueios
			 * para 1(um) caso contrário incrementa o valor do nº de bloqueio do
			 * usuário
			 */
			if (bloqueioAcesso == null) {
				usuarioLogado.setBloqueioAcesso(new Short("1"));
			} else {
				usuarioLogado.setBloqueioAcesso((new Integer(usuarioLogado
						.getBloqueioAcesso() + 1)).shortValue());
			}

			// Atualiza os dados do usuário
			usuarioLogado.setUsuarioSituacao(usuarioSituacao);
			usuarioLogado.setUltimaAlteracao(new Date());
			Fachada.getInstancia().atualizar(usuarioLogado);
		}
	}
}
