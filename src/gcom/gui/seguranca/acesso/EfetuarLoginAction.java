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
 * Esse action valida o usu�rio e coloca as informa��es na sess�o, todo o acesso
 * ter� que passar obrigatoriamente por aqui primeiro
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

		// Prepara o retorno da a��o para a tela principal
		ActionForward retorno = actionMapping.findForward("telaPrincipal");

		// Recupera o ActionForm
		EfetuarLoginActionForm loginActionForm = (EfetuarLoginActionForm) actionForm;
		
		String visualizacaoRAUrgencia = (String) httpServletRequest.getParameter("visualizacaoRAUrgencia");
		
		if(visualizacaoRAUrgencia == null || !visualizacaoRAUrgencia.equals("sim")){
						
		
			// Vari�vel que vai armazenar o usu�rio logado
			Usuario usuarioLogado = null;
	
			// Recupera o login e a senha do usu�rio
			String login = loginActionForm.getLogin();
			String senha = loginActionForm.getSenha();
	
			// [FS0003] - Verificar preenchimento do login
			if (login == null || login.trim().equals("")) {
				this.reportarErros(httpServletRequest, "atencao.login.invalido");
				retorno = actionMapping.findForward("telaLogin");
			} else {
	
				//Cria a vari�vel que vai armazenar a mensagem com a quantidade de 
				//dias que falta para expirar a validade da senha
				String mensagemExpiracao = "";
				
				// [FS0001] - Verificar exist�ncia do login
				if (!this.verificarExistenciaLogin(login)) {
					this.reportarErrosMensagem(httpServletRequest,"atencao.login.inexistente", login);
					retorno = actionMapping.findForward("telaLogin");
				} else {
	
					// Cria uma instancia da sess�o
					HttpSession sessao = httpServletRequest.getSession(true);
	
					// [FS0004] - Validar senha do login
					// Busca o usu�rio no sistema, o usu�rio ser� nulo se n�o
					// existir
					usuarioLogado = this.getFachada().validarUsuario(login, senha);
	
					// [FS0005] - Verificar n�mero de tentativas.
					Integer numeroTentativas = (Integer) sessao.getAttribute("numeroTentativas");
					Short numeroTentativasPermitidas = this.getSistemaParametro().getNumeroMaximoLoginFalho();
					if (numeroTentativas == null) {
						numeroTentativas = new Integer(0);
						sessao.setAttribute("numeroTentativas", numeroTentativas);
					}
	
					// Recupera o login do usu�rio da sess�o
					String loginUsuarioSessao = (String) sessao.getAttribute("loginUsuarioSessao");
	
					// Caso seja a primeira vez que o usu�rio esteja logando
					// joga o login do usu�rio na sess�o
					if (loginUsuarioSessao == null) {
						loginUsuarioSessao = login;
						sessao.setAttribute("loginUsuarioSessao",loginUsuarioSessao);
					}
	
					// Caso o usu�rio n�o esteja cadastrado, manda o erro para a
					// p�gina de login
					if (usuarioLogado == null) {
						this.reportarErros(httpServletRequest,"atencao.usuario.inexistente");
						retorno = actionMapping.findForward("telaLogin");
	
						/*
						 * Caso o login informado seja igual ao que est� na sess�o
						 * incrementa o n� de tentativas e joga esse n� na sess�o
						 * verifica se o n� de tentativas � maior que a permitida se
						 * for bloqueia a senha do usu�rio e indica o erro na p�gina
						 * de login
						 */
						if (loginUsuarioSessao.equals(login)) {
							numeroTentativas = numeroTentativas + 1;
							sessao.setAttribute("numeroTentativas",numeroTentativas);
	
							// [FS0005] - Verificar n�mero de tentativas de acesso
							if (numeroTentativas.intValue() > numeroTentativasPermitidas.intValue()) {
								this.bloquearSenha(login);
								this.reportarErros(httpServletRequest,"atencao.usuario.senha.bloqueada");
								retorno = actionMapping.findForward("telaLogin");
							}
						} else {
							//Zera o n� de tentativas de acesso e joga o login do usu�rio na sess�o
							numeroTentativas = 0;
							sessao.setAttribute("loginUsuarioSessao", login);
						}
	
					} else {
						// [FS0002] - Verificar situa��o do usu�rio
						if (!this.verificarSituacaoUsuario(usuarioLogado)) {
							if (usuarioLogado.getUsuarioSituacao().getId().equals(UsuarioSituacao.INATIVO)) {
								throw new ActionServletException("atencao.usuario_invalido", null, usuarioLogado.getLogin());
							} else {
								this.reportarErrosMensagem(	httpServletRequest,	"atencao.usuario.situacao.invalida",login+ " est� com situa��o correspondente a "+ usuarioLogado.getUsuarioSituacao().getDescricaoAbreviada());
								retorno = actionMapping.findForward("telaLogin");
							}
						}
	
						//[SB0005] Efetuar Controle de Altera��o de Senha
						boolean disponibilizarAlteracaoSenha = false;
						Date dataExpiracaoAcesso = usuarioLogado.getDataExpiracaoAcesso();
						UsuarioSituacao usuarioSituacao = usuarioLogado.getUsuarioSituacao();
						
						//Caso a data de expira��o de acesso esteja preenchida e seja menor 
						//que a data atual disponibiliza a tela de altera��o de senha
						if (dataExpiracaoAcesso != null) {
							if (dataExpiracaoAcesso.before(new Date())) {
								disponibilizarAlteracaoSenha = true;
							}
						}
	
						//Caso a situa��o da senha do usu�rio seja igual a "pendente"
						//disponibiliza a tela de altera��o de senha 
						if (usuarioSituacao.getId().equals(UsuarioSituacao.PENDENTE_SENHA)) {
							disponibilizarAlteracaoSenha = true;
						}
	
						//Caso a flag de disponibilizar altera��o de senha esteja "true"
						//seta o mapeamento para a tela de alterar senha
						if (disponibilizarAlteracaoSenha) {
							retorno = actionMapping.findForward("alterarSenha");
							// Adiciona o usu�rio logado na sess�o
							sessao.setAttribute("usuarioLogado", usuarioLogado);
						}else{
							//Caso a flag de disponibilizar altera��o da senha esteja setada para "false"
							//recupera a data da mensagem de expira��o 
							Date dataPrazoMensagemExpiracao = usuarioLogado.getDataPrazoMensagemExpiracao();
							
							//Caso a data de validade da mensagem de expira��o esteja preenchida
							//recupera o n� de dias que falta para expirar a semha do usu�rio
							//Caso contr�rio indica que a senha do usu�rio expira hoje
							if(dataPrazoMensagemExpiracao != null && Util.compararData(new Date(),dataPrazoMensagemExpiracao) != -1 ){
								if(dataExpiracaoAcesso.after(new Date())){
									Integer numeroDiasParaExpirar = Util.obterQuantidadeDiasEntreDuasDatas(new Date(),dataExpiracaoAcesso);
									mensagemExpiracao = "Sua senha expira dentro de "+numeroDiasParaExpirar+" dia(s).";
								}else{
									mensagemExpiracao = "Sua senha expira hoje.";
								}
							}
						}
	
						// Buscar as permiss�es do(s) grupo(s) do usu�rio
						FiltroGrupoFuncionalidadeOperacao filtroGrupoFuncionalidadeOperacao = new FiltroGrupoFuncionalidadeOperacao();
	                    filtroGrupoFuncionalidadeOperacao.setCampoOrderBy(FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_NUMERO_ORDEM_MENU);
						filtroGrupoFuncionalidadeOperacao.setConsultaSemLimites(true);
						
						// Pesquisa os grupos do usu�rio
						Collection colecaoGruposUsuario = this.getFachada().pesquisarGruposUsuario(usuarioLogado.getId());
	
						// Seta na sess�o os grupos aos que o usu�rio pertence
						sessao.setAttribute("colecaoGruposUsuario",colecaoGruposUsuario);
	
						// Cria o iterator dos grupos do usu�rio logado
						Iterator iterator = colecaoGruposUsuario.iterator();
	
						// La�o para adicionar todos os id's dos grupos no filtro
						// para pesquisar os acessos de todos os grupos do usu�rio
						// logado
						while (iterator.hasNext()) {
							Grupo grupoUsuario = (Grupo) iterator.next();
							filtroGrupoFuncionalidadeOperacao.adicionarParametro(new ParametroSimples(FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,grupoUsuario.getId(),FiltroParametro.CONECTOR_OR));
							
							// Verifica se existe alguma mensagem para o
							// grupo que o usuario logado pertence.
							if(grupoUsuario.getMensagem()!=null 
									&& !grupoUsuario.getMensagem().equals("")){						
								// Coloca a mensagem na sessao
								sessao.setAttribute("mensagemGrupo",grupoUsuario.getMensagem());							
							}	
						}
	
						/*
						 * Pesquisa todas as permiss�es do usu�rio
						 * pesquisa ocorr�ncia na tabela GrupoFuncionalidadeOperacao para os grupos
						 * aos quais o usu�rio logado pertence 
						 */
						Collection permissoes = this.getFachada().pesquisar(filtroGrupoFuncionalidadeOperacao,GrupoFuncionalidadeOperacao.class.getName());
	
						/*
						 * Pesquisa todas as restri��es do usu�rio
						 * pesquisa se existe ocorr�ncia na tabela UsuarioGrupoRestricao para o usu�rio 
						 * que est� logado.
						 */
						Collection restricoes = this.pesquisarRestricaoUsuario(usuarioLogado);
						
						//Caso exista restri��es para o usu�rio logado 
						//remove todas as funcionalidades que o usu�rio n�o tem acesso
						if(restricoes != null && !restricoes.isEmpty()){
							permissoes.removeAll(restricoes);
						}
						
						// Obt�m a �rvore de funcionalidades do sistema
						FuncionalidadeCategoria arvoreFuncionalidades = 
							this.getFachada().pesquisarArvoreFuncionalidades(permissoes);
						
						// Pega o ip do usuario logado.Esse ip sera usado no registrar transacao.
						// Para cada registrar transacao, sera gravado o ip na tabela usuario_acao.
						String ip = httpServletRequest.getRemoteAddr(); 
						usuarioLogado.setIpLogado(ip);
	
						// Coloca a �rvore de funcionalidades/permiss�es na sess�o
						// para
						// futuras consultas
						sessao.setAttribute("arvoreFuncionalidades",arvoreFuncionalidades);
						
						// Adiciona o usu�rio logado na sess�o
						sessao.setAttribute("usuarioLogado", usuarioLogado);
						
						// Cria uma inst�ncia do menu para gerar a arv�re do menu
						MenuGCOM menu = new MenuGCOM();
						String menuGerado = menu.gerarMenu(arvoreFuncionalidades, usuarioLogado);
	
						// Coloca o menu gerado na sess�o
						sessao.setAttribute("menuGCOM", menuGerado);
	
						// Seta o tempo m�ximo que o usu�rio tem para expirar sua
						// sess�o
						// caso nenhuma requisi��o seja feita em 1000(mil) segundos
						sessao.setMaxInactiveInterval(86400);
	
						/*
						 * Recupera a data do �ltimo acesso do usu�rio caso seja a
						 * primeira vez que o usu�rio acesse a aplica��o cria uma
						 * nova data e seta essa data na sess�o para a p�gina de
						 * informa��oes do usu�rio
						 */
						Date data = usuarioLogado.getUltimoAcesso();
						if (data == null) {
							data = new Date();
						}
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						String ultimaDataAcesso = formatter.format(data);
						sessao.setAttribute("dataUltimoAcesso", ultimaDataAcesso);
	
						// Cria a data atual e seta essa data na sess�o
						data = new Date();
	                    SimpleDateFormat formatterDataAtual = new SimpleDateFormat("dd/MM/yyyy");
						String dataAtual = formatterDataAtual.format(data);
						sessao.setAttribute("dataAtual", dataAtual);
	
						//Coloca na sess�o a mensagem informando quantos dias falta para 
						//a senha do usu�rio expirar
						sessao.setAttribute("mensagemExpiracao",mensagemExpiracao);
						
						/*
						 * Cria a lista de �ltimos acessos do usu�rio logado 
						 * e seta essa lista html na sess�o para ser recuperada
						 * na p�gina de informa��es do usu�rio 
						 */
						String ultimosAcessos = this.construirUltimosAcessos(usuarioLogado);
						sessao.setAttribute("ultimosAcessos",ultimosAcessos);
						
						// Registra o acesso do usu�rio no sistema
						this.getFachada().registrarAcessoUsuario(usuarioLogado);
						
						//Registra a Sessao para nao permitir acesso simultaneo no sistema
						SessaoHttpListener.registrarAcessoUsuario(sessao, this.getSistemaParametro());
					
					
						//C�digo para mostrar mensagem de "Alerta de RA Urgente", caso a unidade do usu�rio recebiba uma nova RA urgente.  
//						if(this.getFachada().verificarUsuarioRegistroAtendimentoUrgencia(usuarioLogado.getId()) > 0){
//							sessao.setAttribute("RAUrgencia", "true");
//						}else{	        
//							sessao.removeAttribute("RAUrgencia");
//						}
						
						Collection colecaoIndicadorReiteracao  = this.getFachada().
							verificarUsuarioRegistroAtendimentoUrgencia(usuarioLogado.getId());
						
						if(colecaoIndicadorReiteracao != null && !colecaoIndicadorReiteracao.isEmpty()){
							
							Iterator iterIndicadorReiteracao = colecaoIndicadorReiteracao.iterator();
							String msgUrgencia1 = "";
							String msgUrgencia2 = "";
							while (iterIndicadorReiteracao.hasNext()) {
								Short indicadorReiteracao = (Short) iterIndicadorReiteracao.next();
								if(indicadorReiteracao.equals(ConstantesSistema.NAO)){
									msgUrgencia1 = "Existem registros de atendimento em car�ter de urg�ncia";
								}
								if(indicadorReiteracao.equals(ConstantesSistema.SIM)){
									msgUrgencia2 = "Existe(m) registro(s) de atendimento reiterado";
								}
							}
							sessao.setAttribute("RAUrgencia", "true");
							if(!msgUrgencia1.equalsIgnoreCase("")){
								sessao.setAttribute("msgUrgencia1",msgUrgencia1);
							}else{
								sessao.removeAttribute("msgUrgencia1");
							}
							if(!msgUrgencia2.equalsIgnoreCase("")){
								sessao.setAttribute("msgUrgencia2",msgUrgencia2);
							}else{
								sessao.removeAttribute("msgUrgencia2");
							}
						}else{	        
							sessao.removeAttribute("RAUrgencia");
							sessao.removeAttribute("msgUrgencia1");
							sessao.removeAttribute("msgUrgencia2");
						}
						
									
					}					
					
				}
			}
		
		}else{
			Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
			
			//C�digo para remover a mensagem de "Alerta de RA Urgente" quando usuario pressionar OK
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
	 * Verifica se o login informado existe para algum usu�rio do sistema
	 * retorna true se existir caso contr�rio retorna false.
	 * 
	 * [UC0287] - Verificar exist�ncia do login
	 * 
	 * @author Pedro Alexandre
	 * @date 06/07/2006
	 * 
	 * @param login
	 * @return
	 */
	private boolean verificarExistenciaLogin(String login) {
		// Inicializa o retorno para falso(login n�o existe)
		boolean retorno = false;

		// Cria o filtro e pesquisa o usu�rio com o login informado
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, login));
		Collection usuarios = Fachada.getInstancia().pesquisar(filtroUsuario,
				Usuario.class.getName());

		// Caso exista o usu�rio com o login informado
		// seta o retorno para verdadeiro(login existe no sistema)
		if (usuarios != null && !usuarios.isEmpty()) {
			retorno = true;
		}
		// Retorna um indicador se o login informado existe ou n�o no sistema
		return retorno;
	}

	/**
	 * Met�do que verifica se a situa��o do usu�rio � diferente de ativo ou se �
	 * igual a senha pendente.Caso seja uma ou outra situa��o levanta uma
	 * exce��o para o usu�rio indicando que o usu�rio n�o pode se logar ao
	 * sistema.
	 * 
	 * [FS0002] - Verificar situa��o do usu�rio
	 * 
	 * @author Pedro Alexandre
	 * @date 06/07/2006
	 * 
	 * @param usuarioLogado
	 * @return
	 */
	private boolean verificarSituacaoUsuario(Usuario usuarioLogado) {
		boolean retorno = true;
		// Recupera a situa��o do usu�rio
		UsuarioSituacao usuarioSituacao = usuarioLogado.getUsuarioSituacao();

		/*
		 * Caso a situa��o do usu�rio n�o seja igual a ativo ou seja igual a
		 * pendente retorna uma flag indicando que o usu�rio n�o pode acessar o
		 * sistema
		 */
		if ((!usuarioSituacao.getId().equals(UsuarioSituacao.ATIVO))
				&& (!usuarioSituacao.getId().equals(
						UsuarioSituacao.PENDENTE_SENHA))) {
			retorno = false;
		}

		// Retorna uma flag indicando se a situ��o do usu�rio permite o acesso
		// ao sistema
		return retorno;
	}

	/**
	 * Bloqueia a senha do usu�rio depois de o n�meros de tentativas de acesso
	 * exceder o n�mero m�ximo de tentativas permitidas
	 * 
	 * [FS0005] - Verificar n�mero de tentativas de acesso
	 * 
	 * @author Pedro Alexandre
	 * @date 06/07/2006
	 * 
	 * @param usuarioLogado
	 */
	private void bloquearSenha(String login) {

		// Pesquisa o us�rio que vai ser bloqueada sua senha
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, login));
		Collection usuarios = Fachada.getInstancia().pesquisar(filtroUsuario,
				Usuario.class.getName());

		// Caso encontre o usu�rio com o login informado
		if (usuarios != null && !usuarios.isEmpty()) {
			// Recupera o usu�rio
			Usuario usuarioLogado = (Usuario) usuarios.iterator().next();

			// Atualiza a situa��o do usu�rio para bloqueada
			UsuarioSituacao usuarioSituacao = new UsuarioSituacao();
			usuarioSituacao.setId(UsuarioSituacao.SENHA_BLOQUEADA);

			// Recupera o n� de vezes que o usu�rio foi bloqueado
			Short bloqueioAcesso = usuarioLogado.getBloqueioAcesso();

			/*
			 * Caso o usu�rio nunca tenha sido bloqueado seta o n� de bloqueios
			 * para 1(um) caso contr�rio incrementa o valor do n� de bloqueio do
			 * usu�rio
			 */
			if (bloqueioAcesso == null) {
				usuarioLogado.setBloqueioAcesso(new Short("1"));
			} else {
				usuarioLogado.setBloqueioAcesso((new Integer(usuarioLogado
						.getBloqueioAcesso() + 1)).shortValue());
			}

			// Atualiza os dados do usu�rio
			usuarioLogado.setUsuarioSituacao(usuarioSituacao);
			usuarioLogado.setUltimaAlteracao(new Date());
			Fachada.getInstancia().atualizar(usuarioLogado);
		}
	}
	
	
	
	/**
	 * Pesquisa as retri��es que o usu�rio tem.
	 * pesquisa se existe ocorr�ncia na tabela UsuarioGrupoRestricao
	 *
	 * <Identificador e nome do fluxo secund�rio> 
	 *
	 * @author Pedro Alexandre
	 * @date 17/07/2006
	 *
	 * @param usuarioLogado
	 * @return
	 */
	private Collection<GrupoFuncionalidadeOperacao> pesquisarRestricaoUsuario(Usuario usuarioLogado){
		
		//Cria a cole��o que vai armazenar as restri��es do usu�rio
		Collection<GrupoFuncionalidadeOperacao> restricoes = new ArrayList();
		
		//Cria o filtro para pesquisar todas as restri��es do usu�rio no sistema
		FiltroUsuarioGrupoRestricao filtroUsuarioGrupoRestricao = new FiltroUsuarioGrupoRestricao();
        filtroUsuarioGrupoRestricao.adicionarParametro(new ParametroSimples(FiltroUsuarioGrupoRestricao.USUARIO_ID, usuarioLogado.getId()));
        filtroUsuarioGrupoRestricao.setConsultaSemLimites(true);
        
        Collection colecaoUsuarioRestricoes = Fachada.getInstancia().pesquisar(filtroUsuarioGrupoRestricao, UsuarioGrupoRestricao.class.getName());

        //Caso exista restri��o para o usu�rio
        //Existe ocorr�ncia para o id do usu�rio logado na tabela UsuarioGrupoRestricao
        if(colecaoUsuarioRestricoes != null && !colecaoUsuarioRestricoes.isEmpty()){
        	
        	//Cria o iterator das restri��es do usu�rio 
        	Iterator<UsuarioGrupoRestricao> iterator = colecaoUsuarioRestricoes.iterator();
        	
        	while(iterator.hasNext()){

        		//Recupera a restri��o do iterator
        		UsuarioGrupoRestricao usuarioGrupoRestricao = iterator.next();
        		
        		//Recupera qual a funcionalidade e sua opera��o a qual o usu�rio tem restri��o 
        		//para um determinado grupo
        		GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = new GrupoFuncionalidadeOperacao();
        		GrupoFuncionalidadeOperacaoPK grupoFuncionalidadeOperacaoPK = new GrupoFuncionalidadeOperacaoPK();
        		grupoFuncionalidadeOperacaoPK.setGrupoId(usuarioGrupoRestricao.getComp_id().getGrupoId());
        		grupoFuncionalidadeOperacaoPK.setFuncionalidadeId(usuarioGrupoRestricao.getComp_id().getFuncionalidadeId());
        		grupoFuncionalidadeOperacaoPK.setOperacaoId(usuarioGrupoRestricao.getComp_id().getOperacaoId());
        		grupoFuncionalidadeOperacao.setComp_id(grupoFuncionalidadeOperacaoPK);
        		
        		//Adiciona a opera��o a cole��o de restri��o
	        	restricoes.add(grupoFuncionalidadeOperacao);
        	}
        }
		
        //Retorna a cole��o de restri��es do sistema
		return restricoes;
	}
	
	/**
	 * Met�do respons�vel por criar um list com as funcionalidades �ltimas acessadas
	 * pelo usu�rio
	 *
	 * @author Pedro Alexandre
	 * @date 08/08/2006
	 *
	 * @param usuarioLogado
	 * @return
	 */
	private String construirUltimosAcessos(Usuario usuarioLogado){
		//Cria a vari�vel que vai armazenar a string contendo o html do list com 
		//os �ltimos acessos do usu�rio
		String retorno = null;
		StringBuilder ultimosAcessos = new StringBuilder();

		Collection<UsuarioFavorito> colecaoUltimosAcessos = new ArrayList();
		
		colecaoUltimosAcessos = 
			this.getFachada().pesquisarUsuarioFavorito(usuarioLogado.getId());		
		
		/*
		 * Caso a cole��o de �ltimos acessos n�o esteja vazia 
		 * cria a lista contendo os �ltimos acessos do usu�rio
		 * Caso contr�rio cria uma lista com nenhuma funcionalidade
		 * para �ltimo acessos
		 */
		if(colecaoUltimosAcessos != null && !colecaoUltimosAcessos.isEmpty()){
			
			/*
			 * Trecho para cria a parte inicial do list html
			 */
			ultimosAcessos.append("<select name=\"ultimoacesso\" id=\"ultimosacessos\" onchange=\"javascript:abrirUltimoAcesso();\" style=\"width:130px\">");
			ultimosAcessos.append(System.getProperty("line.separator"));
			ultimosAcessos.append("<option value=\"-1\">Ultimos Acessos</option>");
			ultimosAcessos.append(System.getProperty("line.separator"));
			ultimosAcessos.append("<option value=\"-1\">- - - - - - - - - - - - - - - -</option>");
			
            
			//La�o para adicionar as funcionalidades a lista de �ltimos acessos
			for(UsuarioFavorito usuarioFavorito : colecaoUltimosAcessos){
				Funcionalidade funcionalidade = usuarioFavorito.getFuncionalidade();
				ultimosAcessos.append("<option value=\""+funcionalidade.getCaminhoUrl()+"\">"+funcionalidade.getDescricao()+"</option>");
				ultimosAcessos.append(System.getProperty("line.separator"));
			}
			
			//Fecha o list html
			ultimosAcessos.append("</select>");
			retorno = ultimosAcessos.toString();
		}else{
			
			/*
			 * Cria o list html sem nenhuma funcionalidade dentro 
			 */
			ultimosAcessos.append("<select name=\"ultimoacesso\" id=\"ultimosacessos\" onchange=\"javascript:abrirUltimoAcesso();\" style=\"width:130px\">");
			ultimosAcessos.append(System.getProperty("line.separator"));
			ultimosAcessos.append("<option value=\"-1\"><font size=\"1\">Ultimos Acessos</font></option>");
			ultimosAcessos.append(System.getProperty("line.separator"));
			ultimosAcessos.append("<option value=\"-1\">- - - - - - - - - - - - - - - -</option>");
			ultimosAcessos.append("</select>");
			retorno = ultimosAcessos.toString();
		}

		//Retorna o html de uma lista contendo as funcionalidades �ltimas acessadas pelo usu�rio
		return retorno;
	}
	

}
