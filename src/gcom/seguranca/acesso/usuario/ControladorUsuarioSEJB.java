package gcom.seguranca.acesso.usuario;

import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.ControladorAcessoLocal;
import gcom.seguranca.acesso.ControladorAcessoLocalHome;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.ErroRepositorioException;
import gcom.util.IRepositorioUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Definição da lógica de negócio do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public class ControladorUsuarioSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	private IRepositorioUtil repositorioUtil;

	private IRepositorioUsuario repositorioUsuario;

	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		repositorioUtil = RepositorioUtilHBM.getInstancia();
		repositorioUsuario = RepositorioUsuarioHBM.getInstancia();
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	
	private ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	/**
	 * Retorna o valor de controladorAcesso
	 * 
	 * @return O valor de controladorAcesso
	 */
	private ControladorAcessoLocal getControladorAcesso() {
		ControladorAcessoLocalHome localHome = null;
		ControladorAcessoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorAcessoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ACESSO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Inseri um usuario com seus grupos
	 * 
	 * [UC0230]Inserir Usuario
	 * 
	 * @author Thiago Toscano
	 * @date 19/05/2006
	 * 
	 * @param usuario
	 * @param idGrupo
	 *            grupos que o usuario faz parte
	 * @throws ControladorException
	 */
	public void inserirUsuario(Usuario usuario, Integer[] idGrupos, Usuario usuarioLogado, String idSolicitacaoAcesso)
			throws ControladorException {
		
		// Verifica se já existe um usuário com este login
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, usuario.getLogin()));
		
		Collection colecaoUsuarios = getControladorUtil().pesquisar(filtroUsuario, Usuario.class.getName());
		
		if (colecaoUsuarios != null && !colecaoUsuarios.isEmpty()) {
			throw new ControladorException("atencao.usuario.login.ja.existe", null, usuario.getLogin());
		}

		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

/*		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_USUARIO_INSERIR,
				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_USUARIO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);*/

		// recupera o sistema parametro
		SistemaParametro sistemaParametro = null;
		sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
		usuario.setNumeroAcessos(new Integer(0));
		usuario.setUltimoAcesso(null);
		String senhaGerada = null;
		if (sistemaParametro.getIndicadorSenhaForte().compareTo(ConstantesSistema.SIM.intValue())==0) {
			senhaGerada = Util.gerarSenhaForte(8);
		} else {
			senhaGerada = "gcom";
		}
		String senhaCriptografada = null;
		try {
			senhaCriptografada = Criptografia.encriptarSenha(senhaGerada);
		} catch (ErroCriptografiaException e1) {
			throw new ControladorException("erro.criptografia.senha");
		}
		usuario.setSenha(senhaCriptografada);
		usuario.setBloqueioAcesso(new Short((short) 0));
		usuario.setDataCadastroAcesso(new Date());
		usuario.setUltimaAlteracao(new Date());
		UsuarioSituacao usuarioSituacao = new UsuarioSituacao();
		usuarioSituacao.setId(UsuarioSituacao.PENDENTE_SENHA);
		usuario.setUsuarioSituacao(usuarioSituacao);
		Date dataNascimento = usuario.getDataNascimento();
		usuario.setDataNascimento(dataNascimento);
		usuario.setLembreteSenha(null);
		String cpf = usuario.getCpf();
		usuario.setCpf(cpf);
		usuario.setIndicadorExibeMensagem(new Short(
				(short) TarefaRelatorio.INDICADOR_EXIBE_MENSAGEM));
		usuario.setIndicadorTipoRelatorioPadrao(new Short(
				(short) TarefaRelatorio.TIPO_PDF));
		Date dataCadastramentoFinal = usuario.getDataCadastroFim();
		Date dataAtual = new Date();
		int numeroDiasExpiracaoAcesso = sistemaParametro
				.getNumeroDiasExpiracaoAcesso().intValue();

		int numeroDiasMSGExpiracao = sistemaParametro
				.getNumeroDiasMensagemExpiracao().intValue();

		Date dataAtualMaisDiasSistemasParametros = Util
				.adicionarNumeroDiasDeUmaData(dataAtual,
						numeroDiasExpiracaoAcesso);

		Date dataAtualMenosDiasMSGExpiracao = Util.subtrairNumeroDiasDeUmaData(
				dataAtualMaisDiasSistemasParametros, numeroDiasMSGExpiracao);
		// verifica a data de cadastramento final se é diferente de nulo
		if (dataCadastramentoFinal != null
				&& !dataCadastramentoFinal.equals("")) {
			// caso a data atual + dias sistemas parametros seja maior que a
			// data de cadastramento final do usuário
			if (dataAtualMaisDiasSistemasParametros
					.after(dataCadastramentoFinal)) {
				// seta o valor da data de cadastramento final do usuario no
				// usuario data expiração acesso
				usuario.setDataExpiracaoAcesso(dataCadastramentoFinal);
			} else {
				// seta o valor da a data atual + dias sistemas parametros no
				// usuario data expiração acesso
				usuario
						.setDataExpiracaoAcesso(dataAtualMaisDiasSistemasParametros);
			}

			// caso a data atual - dias mensagem expiracao sistemas parametros
			// seja maior que a
			// data de cadastramento final do usuário
			if (dataAtualMenosDiasMSGExpiracao.after(dataCadastramentoFinal)) {
				// seta o valor da data de cadastramento final do usuario no
				// usuario data expiração acesso
				usuario.setDataPrazoMensagemExpiracao(dataCadastramentoFinal);
			} else {
				// seta o valor da a data atual - dias mensagem expiracao
				// sistemas parametros no
				// usuario data expiração acesso
				usuario
						.setDataPrazoMensagemExpiracao(dataAtualMenosDiasMSGExpiracao);
			}
		} else {
			// seta o valor da a data atual + dias sistemas parametros no
			// usuario data expiração acesso
			usuario.setDataExpiracaoAcesso(dataAtualMaisDiasSistemasParametros);
			// seta o valor da a data atual - dias mensagem expiracao sistemas
			// parametros no
			// usuario data prazo mensagem expiração acesso
			usuario
					.setDataPrazoMensagemExpiracao(dataAtualMenosDiasMSGExpiracao);
		}
//		// registrar transação
//		usuario.setOperacaoEfetuada(operacaoEfetuada);
//		usuario.adicionarUsuario(Usuario.USUARIO_TESTE,
//				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//
//		registradorOperacao.registrarOperacao(usuario);
//		usuario.setUltimaAlteracao(new Date());
		
		/*
		 * [UC0107] Registrar Transação
		 * 
		 */
		//Alteração Flávio
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		    Operacao.OPERACAO_USUARIO_INSERIR,
		    usuario.getId(), usuario.getId(),
		    new UsuarioAcaoUsuarioHelper(usuarioLogado,
		    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(usuario);

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		/*
		 * Seta a data da ultima atualização do grupo e atualiza os dados do
		 * grupo
		 */
		usuario.setUltimaAlteracao(new Date());
		
		this.getControladorUtil().inserir(usuario);
		
		if(idSolicitacaoAcesso != null){
			this.getControladorAcesso().atualizarCadastroSolicitacaoAcesso(new Integer(idSolicitacaoAcesso));
		}

		/**
		 * Para todos os grupos selecionados cria o relacionamento
		 */
		if (idGrupos != null) {
			for (int i = 0; i < idGrupos.length; i++) {
				Integer idGrupo = idGrupos[i];

				// cria o grupo corrente
				Grupo grupo = new Grupo();
				grupo.setId(idGrupo);

				// cria a pk
				UsuarioGrupoPK pk = new UsuarioGrupoPK();
				pk.setGrupoId(grupo.getId());
				pk.setUsuarioId(usuario.getId());

				// cria o relacionamenteo do usuario com o grupo
				UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
				usuarioGrupo.setGrupo(grupo);
				usuarioGrupo.setUsuario(usuario);
				usuarioGrupo.setComp_id(pk);
				usuarioGrupo.setUltimaAlteracao(new Date());

//				// registrar transação
//				usuarioGrupo.setOperacaoEfetuada(operacaoEfetuada);
//				usuarioGrupo.adicionarUsuario(Usuario.USUARIO_TESTE,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//
//				registradorOperacao.registrarOperacao(usuarioGrupo);
				try {
					// salvando o usuarioGrupo
					repositorioUtil.inserir(usuarioGrupo);
				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", ex);
				}
			}
		}

		// Envia e-mail para o usuario informando usuario e senha
		if (usuario.getDescricaoEmail() != null
				&& !usuario.getDescricaoEmail().equals("")) {
			String mensagem = "Login:" + usuario.getLogin() + " \n"
					+ "Senha:" + senhaGerada;
			EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.INSERIR_USUARIO);

			try {
				ServicosEmail.enviarMensagem(envioEmail.getEmailRemetente(), usuario
						.getDescricaoEmail(), 
						envioEmail.getTituloMensagem(), mensagem);
			} catch (ErroEmailException e) {
				//throw new ControladorException("erro.envio.mensagem");
			}
		}

	}

	/**
	 * Atualiza um usuario com seus grupos
	 * 
	 * [UC0231]Atualizar Usuario
	 * 
	 * @author Sávio Luiz
	 * @date 07/07/2006
	 * 
	 * @param usuario
	 * @param idGrupo
	 *            grupos que o usuario faz parte
	 * @throws ControladorException
	 */
	public void atualizarUsuario(Usuario usuario, 
		Integer[] idGrupos, 
		String processo, 
		Usuario usuarioLogado) throws ControladorException {
		
		// Verifica se já existe um usuário com este login
		FiltroUsuario filtroUsuarioValidacaoLogin = new FiltroUsuario();
		filtroUsuarioValidacaoLogin.adicionarParametro(
			new ParametroSimples(FiltroUsuario.LOGIN, usuario.getLogin()));
		filtroUsuarioValidacaoLogin.adicionarParametro(
			new ParametroSimplesDiferenteDe(FiltroUsuario.ID, usuario.getId()));
		
		Collection colecaoUsuarios = 
			getControladorUtil().pesquisar(filtroUsuarioValidacaoLogin, Usuario.class.getName());
		
		if (colecaoUsuarios != null && !colecaoUsuarios.isEmpty()) {
			throw new ControladorException("atencao.usuario.login.ja.existe", null, usuario.getLogin());
		}
		
		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

//		RegistradorOperacao registradorOperacao = 
//			new RegistradorOperacao(
//				Operacao.OPERACAO_USUARIO_ATUALIZAR,
//				new UsuarioAcaoUsuarioHelper(
//					usuarioLogado,
//					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//
//		Operacao operacao = new Operacao();
//		operacao.setId(Operacao.OPERACAO_USUARIO_ATUALIZAR);
//
//		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//		operacaoEfetuada.setOperacao(operacao);

		// removendo os usuarios grupos
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(
			new ParametroSimples(FiltroUsuario.ID,
				usuario.getId()));
		
		Collection coll = 
			this.getControladorUtil().pesquisar(
				filtroUsuario,
				Usuario.class.getSimpleName());
		
		Usuario usuarioCadastrado = (Usuario) coll.iterator().next();

		if (usuarioCadastrado == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}
		
		usuario.setUltimaAlteracao(new Date());
		
		if (usuarioCadastrado.getUltimaAlteracao() != null && 
			usuario.getUltimaAlteracao() != null && 
			usuarioCadastrado.getUltimaAlteracao().after(usuario.getUltimaAlteracao())) {
			
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
		
		// recupera o sistema parametro
		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

		usuario.setDataCadastroAcesso(new Date());
		usuario.setUltimaAlteracao(new Date());

		Date dataCadastramentoFinal = usuario.getDataCadastroFim();
		Date dataAtual = new Date();
		
		int numeroDiasExpiracaoAcesso = 
			sistemaParametro.getNumeroDiasExpiracaoAcesso().intValue();

		int numeroDiasMSGExpiracao = 
			sistemaParametro.getNumeroDiasMensagemExpiracao().intValue();

		Date dataAtualMaisDiasSistemasParametros = 
			Util.adicionarNumeroDiasDeUmaData(dataAtual,numeroDiasExpiracaoAcesso);

		Date dataAtualMenosDiasMSGExpiracao = 
			Util.subtrairNumeroDiasDeUmaData(
				dataAtualMaisDiasSistemasParametros, 
				numeroDiasMSGExpiracao);
		
		// verifica a data de cadastramento final se é diferente de nulo
		if (dataCadastramentoFinal != null && !dataCadastramentoFinal.equals("")) {
			// caso a data atual + dias sistemas parametros seja maior que a
			// data de cadastramento final do usuário
			if (dataAtualMaisDiasSistemasParametros.after(dataCadastramentoFinal)) {
				// seta o valor da data de cadastramento final do usuario no
				// usuario data expiração acesso
				usuario.setDataExpiracaoAcesso(dataCadastramentoFinal);
			} else {
				// seta o valor da a data atual + dias sistemas parametros no
				// usuario data expiração acesso
				usuario.setDataExpiracaoAcesso(dataAtualMaisDiasSistemasParametros);
			}

			// caso a data atual - dias mensagem expiracao sistemas parametros
			// seja maior que a
			// data de cadastramento final do usuário
			if (dataAtualMenosDiasMSGExpiracao.after(dataCadastramentoFinal)) {
				// seta o valor da data de cadastramento final do usuario no
				// usuario data expiração acesso
				usuario.setDataPrazoMensagemExpiracao(dataCadastramentoFinal);
			} else {
				// seta o valor da a data atual - dias mensagem expiracao
				// sistemas parametros no
				// usuario data expiração acesso
				usuario.setDataPrazoMensagemExpiracao(dataAtualMenosDiasMSGExpiracao);
			}
		} else {
			// seta o valor da a data atual + dias sistemas parametros no
			// usuario data expiração acesso
			usuario.setDataExpiracaoAcesso(dataAtualMaisDiasSistemasParametros);
			// seta o valor da a data atual - dias mensagem expiracao sistemas
			// parametros no
			// usuario data prazo mensagem expiração acesso
			usuario.setDataPrazoMensagemExpiracao(dataAtualMenosDiasMSGExpiracao);
		}
		// registrar transação
//		usuario.setOperacaoEfetuada(operacaoEfetuada);
//		usuario.adicionarUsuario(usuarioLogado,
//			UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//
//		registradorOperacao.registrarOperacao(usuario);
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		    Operacao.OPERACAO_USUARIO_ATUALIZAR,
		    usuario.getId(), usuario.getId(),
		    new UsuarioAcaoUsuarioHelper(usuarioLogado,
		    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(usuario);

		// ------------ REGISTRAR TRANSAÇÃO ----------------

		
		this.getControladorUtil().atualizar(usuario);

		/*
		 * [UC0107] Registrar Transação
		 * 
		 */
		if(processo != null){
			if(processo.equalsIgnoreCase("2")){ // Caso o usuario tenha alterado os dados da segunda aba
	
//			registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_USUARIO_REMOVER,
//				new UsuarioAcaoUsuarioHelper(
//					usuarioLogado,
//					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	
//			operacao = new Operacao();
//			operacao.setId(Operacao.OPERACAO_USUARIO_ATUALIZAR);
//	
//			operacaoEfetuada = new OperacaoEfetuada();
//			operacaoEfetuada.setOperacao(operacao);
			
			// removendo os usuarios grupos
			FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();
			filtroUsuarioGrupo.adicionarParametro(
				new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_ID, 
					usuario.getId()));
			
			coll = 
				this.getControladorUtil().pesquisar(filtroUsuarioGrupo,
					UsuarioGrupo.class.getSimpleName());
	
			/*
			 * Alteracao realizada por Rômulo Aurélio 
			 * 
			 * Problema na hora de remover usuarios grupos que existam na tabela usuarioGrupoRestricao
			 * 
			 * Solicitado por Fatima
			 * 
			 * Data: 14/05/2006
			 */
			FiltroUsuarioGrupoRestricao filtroUsuarioGrupoRestricao = new FiltroUsuarioGrupoRestricao();
				
			filtroUsuarioGrupoRestricao.adicionarParametro(
				new ParametroSimples(
					FiltroUsuarioGrupoRestricao.USUARIO_ID,
					usuario.getId()));
			
			filtroUsuarioGrupoRestricao.adicionarCaminhoParaCarregamentoEntidade("usuarioGrupo.grupo");
			filtroUsuarioGrupoRestricao.adicionarCaminhoParaCarregamentoEntidade("usuarioGrupo.usuario");
			filtroUsuarioGrupoRestricao.adicionarCaminhoParaCarregamentoEntidade("grupoFuncionalidadeOperacao");
			
			Collection colecaoGrupoRestricao = 
				getControladorUtil().pesquisar(filtroUsuarioGrupoRestricao,
					UsuarioGrupoRestricao.class.getName());
			
			if(colecaoGrupoRestricao!=null && !colecaoGrupoRestricao.isEmpty()){
				
				Iterator colecaoGrupoRestricaoIterator = colecaoGrupoRestricao.iterator();
				
				while(colecaoGrupoRestricaoIterator.hasNext()){
					
					UsuarioGrupoRestricao usuarioGrupoRestricao = 
						(UsuarioGrupoRestricao) colecaoGrupoRestricaoIterator.next();
					
					getControladorUtil().remover(usuarioGrupoRestricao);
				}
			}
					
			if (coll != null && !coll.isEmpty()) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					
					UsuarioGrupo usuarioGrupo = (UsuarioGrupo) it.next();

					// registrar transação
//					usuarioGrupo.setOperacaoEfetuada(operacaoEfetuada);
//					usuarioGrupo.adicionarUsuario(
//						usuarioLogado,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//
//					registradorOperacao.registrarOperacao(usuarioGrupo);

					this.getControladorUtil().remover(usuarioGrupo);
				}
			}
		

		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

//		registradorOperacao = new RegistradorOperacao(
//			Operacao.OPERACAO_USUARIO_REMOVER,
//			new UsuarioAcaoUsuarioHelper(
//				usuarioLogado,
//				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//
//		operacao = new Operacao();
//		operacao.setId(Operacao.OPERACAO_USUARIO_INSERIR);
//
//		operacaoEfetuada = new OperacaoEfetuada();
//		operacaoEfetuada.setOperacao(operacao);

		/**
		 * Para todos os grupos selecionados cria o relacionamento
		 */
		
		Collection colecaoGrupos = new ArrayList();
		
		if (idGrupos != null) {
			
			for (int i = 0; i < idGrupos.length; i++) {
				
				Integer idGrupo = idGrupos[i];

				// cria o grupo corrente
				Grupo grupo = new Grupo();
				grupo.setId(idGrupo);
				
				colecaoGrupos.add(grupo.getId());

				// cria a pk
				UsuarioGrupoPK pk = new UsuarioGrupoPK();
				pk.setGrupoId(grupo.getId());
				pk.setUsuarioId(usuario.getId());

				// cria o relacionamenteo do usuario com o grupo
				UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
				usuarioGrupo.setGrupo(grupo);
				usuarioGrupo.setUsuario(usuario);
				usuarioGrupo.setComp_id(pk);
				usuarioGrupo.setUltimaAlteracao(new Date());
//				// registrar transação
//				usuarioGrupo.setOperacaoEfetuada(operacaoEfetuada);
//				usuarioGrupo.adicionarUsuario(usuarioLogado,
//					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//
//				registradorOperacao.registrarOperacao(usuarioGrupo);

				// salvando o usuarioGrupo
				this.getControladorUtil().inserir(usuarioGrupo);
			}
		}
		
		if (colecaoGrupoRestricao != null && !colecaoGrupoRestricao.isEmpty()) {
			 
			Iterator colecaoGrupoRestricaoIterator = colecaoGrupoRestricao.iterator();
			
			while (colecaoGrupoRestricaoIterator.hasNext()) {
				UsuarioGrupoRestricao usuarioGrupoRestricao = 
					(UsuarioGrupoRestricao) colecaoGrupoRestricaoIterator.next();
				
				if (colecaoGrupos.contains(usuarioGrupoRestricao.getUsuarioGrupo().getGrupo().getId())) {
					this.getControladorUtil().inserir(usuarioGrupoRestricao);
				}
					
			}
		}
		
		}
		}
		
		//Alterado por Vivianne Sousa  13/02/2007
		//solicitado por Leonardo Vieira
		if (usuario.getUsuarioTipo().getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO){
			Funcionario funcionario = usuario.getFuncionario();
			
			FiltroFuncionario  filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(
				new ParametroSimples(FiltroFuncionario.ID, funcionario.getId()));
			
			filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
			
			Collection colecaoFuncionario = 
				this.getControladorUtil().pesquisar(filtroFuncionario,
					Funcionario.class.getSimpleName());
			
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
				
				Funcionario f = (Funcionario) colecaoFuncionario.iterator().next();
				
				if (!f.getUnidadeOrganizacional().getId().equals(funcionario.getUnidadeOrganizacional().getId())){
					
//					funcionario.setOperacaoEfetuada(operacaoEfetuada);
//					funcionario.adicionarUsuario(usuarioLogado,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//					
					funcionario.setUltimaAlteracao(new Date());
					this.getControladorUtil().atualizar(funcionario);
				}
			}
		}	
		
	}

	/**
	 * Remove usuario(s)
	 * 
	 * [UC0231] Manter Usuario
	 * 
	 * @author Sávio Luiz
	 * @date 07/07/2006
	 * @param idsUsuario
	 * @param usuario
	 * @throws ControladorException
	 */
	public void removerUsuario(String[] idsUsuario, Usuario usuario, Usuario usuarioLogado)
			throws ControladorException {
		// removendo os usuarios grupos
		for (int i = 0; i < idsUsuario.length; i++) {
			
//			// ------------ REGISTRAR TRANSAÇÃO ----------------
//			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//					Operacao.OPERACAO_USUARIO_REMOVER,
//					new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
//							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//			Operacao operacao = new Operacao();
//			operacao.setId(Operacao.OPERACAO_USUARIO_REMOVER);
//
//			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//			operacaoEfetuada.setOperacao(operacao);

			// Parte da verificação do filtro
			FiltroUsuario filtroUsuario = new FiltroUsuario();

			// filtroUsuario.setCampoOrderBy(FiltroUsuario.NOME_USUARIO);
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.ID, idsUsuario[i]));
			filtroUsuario
					.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
			filtroUsuario
					.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional.unidadeTipo");
			filtroUsuario
					.adicionarCaminhoParaCarregamentoEntidade("funcionario.unidadeOrganizacional");
			Collection colecaoUsuario = this.getControladorUtil().pesquisar(
					filtroUsuario, Usuario.class.getName());

			Usuario usuarioParaRemover = (Usuario) Util
					.retonarObjetoDeColecao(colecaoUsuario);
			// [FS0008] - Verificar permissão para atualização

			UnidadeOrganizacional unidadeEmpresa = usuarioParaRemover
					.getUnidadeOrganizacional();
			if (unidadeEmpresa == null) {
				if (usuarioParaRemover.getFuncionario() != null
						&& !usuarioParaRemover.getFuncionario().equals("")) {
					unidadeEmpresa = usuarioParaRemover.getFuncionario()
							.getUnidadeOrganizacional();
				}
			}

			// caso o usuário que esteja efetuando a inserção não
			// seja
			// do grupo de administradores
			FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();

			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.GRUPO_ID, Grupo.ADMINISTRADOR));
			Collection colecaoUsuarioGrupo = this.getControladorUtil().pesquisar(
					filtroUsuarioGrupo, UsuarioGrupo.class.getName());
			if (colecaoUsuarioGrupo == null || colecaoUsuarioGrupo.isEmpty()) {
				// se a unidade de lotacao do usuario que estiver
				// efetuando seja diferente da unidade de
				// lotação informada
				if (usuario.getUnidadeOrganizacional() != null
						&& unidadeEmpresa != null
						&& usuario.getUnidadeOrganizacional().getId() != null
						&& !usuario.getUnidadeOrganizacional().getId().equals(
								unidadeEmpresa.getId())) {
					// recupera a unidade do usuário
					FiltroUnidadeOrganizacional filtroUnidadeEmpresaUsuario = new FiltroUnidadeOrganizacional();
					filtroUnidadeEmpresaUsuario
							.adicionarParametro(new ParametroSimples(
									FiltroUnidadeOrganizacional.ID, usuario
											.getUnidadeOrganizacional().getId()));
					filtroUnidadeEmpresaUsuario.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
					Collection colecaoUnidadeEmpresa = this.getControladorUtil()
							.pesquisar(filtroUnidadeEmpresaUsuario,
									UnidadeOrganizacional.class.getName());
					UnidadeOrganizacional unidadeEmpresaUsuario = new UnidadeOrganizacional();
					if (colecaoUnidadeEmpresa != null
							&& !colecaoUnidadeEmpresa.isEmpty()) {
						unidadeEmpresaUsuario = (UnidadeOrganizacional) Util
								.retonarObjetoDeColecao(colecaoUnidadeEmpresa);
					}
					// se o nivel da unidade de lotação do usuário
					// que
					// estiver efetuando a inserção seja maior ou
					// igual
					// ao nivel de unidade de lotação informada
					if (unidadeEmpresaUsuario != null) {
						if (unidadeEmpresaUsuario.getUnidadeTipo().getNivel()
								.intValue() >= unidadeEmpresa.getUnidadeTipo()
								.getNivel().intValue()) {
							throw new ControladorException(
									"atencao.usuario.sem.permissao.atualizacao",
									null, usuario.getLogin(), unidadeEmpresa
											.getDescricao());
						}
					}
					// ou a unidade superior da unidade de lotação
					// informada seja diferente da unidade de
					// lotação do usuário

					// enquanto o nivel superior da unidade de
					// lotação não esteja no mesmo nivel da unidade
					// de lotação do usuário
					boolean mesmoNivel = true;
					Integer idNivelUsuario = unidadeEmpresaUsuario
							.getUnidadeTipo().getNivel().intValue();
					UnidadeOrganizacional unidadeEmpresaSuperior = null;
					while (mesmoNivel) {
						Integer idUnidadeEmpresaSuperior = null;
						if (unidadeEmpresaSuperior == null) {
							idUnidadeEmpresaSuperior = unidadeEmpresa
									.getUnidadeSuperior().getId();
						} else {
							idUnidadeEmpresaSuperior = unidadeEmpresaSuperior
									.getUnidadeSuperior().getId();
						}
						// recupera a unidade do usuário
						FiltroUnidadeOrganizacional filtroUnidadeEmpresaSuperior = new FiltroUnidadeOrganizacional();
						filtroUnidadeEmpresaSuperior
								.adicionarParametro(new ParametroSimples(
										FiltroUnidadeOrganizacional.ID,
										idUnidadeEmpresaSuperior));
						Collection colecaoUnidadeEmpresaSuperior = 
							this.getControladorUtil().pesquisar(
										filtroUnidadeEmpresaSuperior,
										UnidadeOrganizacional.class.getName());
						if (colecaoUnidadeEmpresaSuperior != null
								&& !colecaoUnidadeEmpresaSuperior.isEmpty()) {
							unidadeEmpresaSuperior = (UnidadeOrganizacional) Util
									.retonarObjetoDeColecao(colecaoUnidadeEmpresaSuperior);
						}
						if (unidadeEmpresaSuperior != null) {
							// caso seja o mesmo nivel
							if (unidadeEmpresaSuperior.getUnidadeTipo()
									.getNivel().equals(idNivelUsuario)) {
								mesmoNivel = false;
								// caso o id da unidade empresa
								// informado for diferente do id da
								// unidade empresa do usuário no
								// mesmo nivel
								if (!unidadeEmpresaSuperior.getId().equals(
										unidadeEmpresaUsuario.getId())) {
									throw new ControladorException(
											"atencao.usuario.sem.permissao.atualizacao",
											null, usuario.getLogin(),
											unidadeEmpresa.getDescricao());
								}

							}
						}
					}

				}
			}
			// remove os usuarios grupos restrinções
			FiltroUsuarioGrupoRestricao filtroUsuarioGrupoRestricao = new FiltroUsuarioGrupoRestricao();
			filtroUsuarioGrupoRestricao
					.adicionarParametro(new ParametroSimples(
							FiltroUsuarioGrupoRestricao.USUARIO_ID,
							idsUsuario[i]));
			Collection colecaoUsuarioGrupoRestricao = this.getControladorUtil()
					.pesquisar(filtroUsuarioGrupoRestricao,
							UsuarioGrupoRestricao.class.getName());

			if (colecaoUsuarioGrupoRestricao != null
					&& !colecaoUsuarioGrupoRestricao.isEmpty()) {
				Iterator it = colecaoUsuarioGrupoRestricao.iterator();
				while (it.hasNext()) {
					UsuarioGrupoRestricao usuarioGrupoRestricao = (UsuarioGrupoRestricao) it
							.next();

//					// registrar transação
//					usuarioGrupoRestricao.setOperacaoEfetuada(operacaoEfetuada);
//					usuarioGrupoRestricao.adicionarUsuario(
//							Usuario.USUARIO_TESTE,
//							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//
//					registradorOperacao
//							.registrarOperacao(usuarioGrupoRestricao);

					this.getControladorUtil().remover(usuarioGrupoRestricao);
				}
			}

			// remove os usuarioGrupos
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial
					.adicionarParametro(new ParametroSimples(
							FiltroUsuarioPemissaoEspecial.USUARIO_ID,
							idsUsuario[i]));
			Collection colecaoUsuarioPermissaoEspecial = this
					.getControladorUtil().pesquisar(
							filtroUsuarioPemissaoEspecial,
							UsuarioPermissaoEspecial.class.getSimpleName());

			if (colecaoUsuarioPermissaoEspecial != null
					&& !colecaoUsuarioPermissaoEspecial.isEmpty()) {
				Iterator it = colecaoUsuarioPermissaoEspecial.iterator();
				while (it.hasNext()) {
					UsuarioPermissaoEspecial usuarioPermissaoEspecial = (UsuarioPermissaoEspecial) it
							.next();
//					// registrar transação
//					usuarioPermissaoEspecial
//							.setOperacaoEfetuada(operacaoEfetuada);
//					usuarioPermissaoEspecial.adicionarUsuario(
//							Usuario.USUARIO_TESTE,
//							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//
//					registradorOperacao
//							.registrarOperacao(usuarioPermissaoEspecial);

					this.getControladorUtil().remover(usuarioPermissaoEspecial);
				}
			}

			// remove os usuarios grupos
			FiltroUsuarioGrupo filtroUsuarioGrupoParaRemocao = new FiltroUsuarioGrupo();
			filtroUsuarioGrupoParaRemocao
					.adicionarParametro(new ParametroSimples(
							FiltroUsuarioGrupo.USUARIO_ID, idsUsuario[i]));
			Collection colecaoUsuarioGrupoParaRemocao = this
					.getControladorUtil().pesquisar(
							filtroUsuarioGrupoParaRemocao,
							UsuarioGrupo.class.getSimpleName());

			if (colecaoUsuarioGrupoParaRemocao != null
					&& !colecaoUsuarioGrupoParaRemocao.isEmpty()) {
				Iterator it = colecaoUsuarioGrupoParaRemocao.iterator();
				while (it.hasNext()) {
					UsuarioGrupo usuarioGrupo = (UsuarioGrupo) it.next();

					// registrar transação
//					usuarioGrupo.setOperacaoEfetuada(operacaoEfetuada);
//					usuarioGrupo.adicionarUsuario(Usuario.USUARIO_TESTE,
//							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//
//					registradorOperacao.registrarOperacao(usuarioGrupo);
					
					// ------------ REGISTRAR TRANSAÇÃO ----------------
					
					RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					    Operacao.OPERACAO_USUARIO_REMOVER, usuarioParaRemover.getId(),
					    usuarioParaRemover.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

					registradorOperacao.registrarOperacao(usuarioParaRemover);

					// ------------ REGISTRAR TRANSAÇÃO ----------------
					
					this.getControladorUtil().remover(usuarioGrupo);
				}
			}
			
			//remove os usuarios favoritos
			FiltroUsuarioFavorito filtroUsuarioFavoritoParaRemocao = new FiltroUsuarioFavorito();
			filtroUsuarioFavoritoParaRemocao.adicionarParametro( new ParametroSimples( 
					FiltroUsuarioFavorito.USUARIO_ID, idsUsuario[i] ));
			
			Collection colecaoUsuarioFavorito = this.getControladorUtil().pesquisar(filtroUsuarioFavoritoParaRemocao,
					UsuarioFavorito.class.getName());
			if(colecaoUsuarioFavorito != null && !colecaoUsuarioFavorito.isEmpty()) {
				Iterator iteUsuarioFavorito = colecaoUsuarioFavorito.iterator();
				while (iteUsuarioFavorito.hasNext()){
					UsuarioFavorito usuarioFavorito = (UsuarioFavorito) iteUsuarioFavorito.next();
//					// registrar transação
//					usuarioFavorito.setOperacaoEfetuada(operacaoEfetuada);
//					usuarioFavorito.adicionarUsuario(Usuario.USUARIO_TESTE,
//							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//
//					registradorOperacao.registrarOperacao(usuarioFavorito);
					this.getControladorUtil().remover(usuarioFavorito);
				}
			}
				
			//remove os usuario alteração
			FiltroUsuarioAlteracao filtroUsuarioAlteracaoParaRemocao = new FiltroUsuarioAlteracao();
			filtroUsuarioAlteracaoParaRemocao.adicionarParametro( new ParametroSimples(FiltroUsuarioAlteracao.USUARIO_ID,
					idsUsuario[i]));
			
			Collection colecaoUsuarioAlteracao = this.getControladorUtil().pesquisar(filtroUsuarioAlteracaoParaRemocao
					, UsuarioAlteracao.class.getName());
			if(colecaoUsuarioAlteracao != null && !colecaoUsuarioAlteracao.isEmpty()) {
				Iterator iteUsuarioAlteracao = colecaoUsuarioAlteracao.iterator();
				while ( iteUsuarioAlteracao.hasNext() ) {
					UsuarioAlteracao usuarioAlteracao = (UsuarioAlteracao) iteUsuarioAlteracao.next();
					
					//Registrar Transacao
					//usuarioAlteracao.setOperacaoEfetuada(operacaoEfetuada);
					this.getControladorUtil().remover(usuarioAlteracao);
				}
			}
			
			

		    //registrar transaçãofiltroUsuarioGrupoRestricao.adicionarCaminhoParaCarregamentoEntidade("grupo");
//			usuarioParaRemover.setOperacaoEfetuada(operacaoEfetuada);
//			usuarioParaRemover.adicionarUsuario(Usuario.USUARIO_TESTE,
//					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
			    Operacao.OPERACAO_USUARIO_REMOVER, usuarioParaRemover.getId(),
			    usuarioParaRemover.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
			    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			registradorOperacao.registrarOperacao(usuarioParaRemover);

			// ------------ REGISTRAR TRANSAÇÃO ----------------
			
			this.getControladorUtil().remover(usuarioParaRemover);
			
		}
	}

	/**
	 * Método que atualiza o controle de acesso do usuário
	 * 
	 * [UC0231] - Manter Usuário
	 * 
	 * @author Sávio Luiz
	 * @date 14/07/2006
	 * 
	 * @param String[]
	 * @param grupoFuncionalidadeOperacao
	 */
	public void atualizarControleAcessoUsuario(
			String[] permissoesEspeciais,
			Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadesMap,
			Usuario usuarioAtualizar, Integer[] idsGrupos,
			String permissoesCheckBoxVazias, Usuario usuarioLogado) throws ControladorException {

		if (funcionalidadesMap != null && !funcionalidadesMap.isEmpty()) {
			/*
			 * Pesquisa todos os usuários grupos restrinções, e remove todos
			 * para ser inseridos os novos acesso(s) informados pelo usuário
			 */
			FiltroUsuarioGrupoRestricao filtroUsuarioGrupoRestricao = new FiltroUsuarioGrupoRestricao();
			filtroUsuarioGrupoRestricao
					.adicionarParametro(new ParametroSimples(
							FiltroUsuarioGrupoRestricao.USUARIO_ID,
							usuarioAtualizar.getId()));
			Collection colecaoUsuarioGrupoRestrincaoCadastradas = getControladorUtil()
					.pesquisar(filtroUsuarioGrupoRestricao,
							UsuarioGrupoRestricao.class.getName());
			if (colecaoUsuarioGrupoRestrincaoCadastradas != null
					&& !colecaoUsuarioGrupoRestrincaoCadastradas.isEmpty()) {
				Iterator iteratorUsuarioGrupoRestrincao = colecaoUsuarioGrupoRestrincaoCadastradas
						.iterator();
				while (iteratorUsuarioGrupoRestrincao.hasNext()) {
					UsuarioGrupoRestricao usuarioGrupoRestricao = (UsuarioGrupoRestricao) iteratorUsuarioGrupoRestrincao
							.next();
					/*
					 * // registrar transação
					 * usuarioGrupoRestricao.setOperacaoEfetuada(operacaoEfetuada);
					 * usuarioGrupoRestricao.adicionarUsuario(
					 * Usuario.USUARIO_TESTE,
					 * UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
					 * 
					 * registradorOperacao
					 * .registrarOperacao(usuarioGrupoRestricao);
					 */

					getControladorUtil().remover(usuarioGrupoRestricao);
				}
			}

			/*
			 * Caso o usuário tenha informado algum acesso para o grupo que está
			 * sendo atualizado, inseri na tabela usuario_grupo_restrinção
			 */

			Collection idsfuncionalidades = funcionalidadesMap.keySet();
			if (idsfuncionalidades != null && !idsfuncionalidades.isEmpty()) {
				Iterator iteratorFuncionalidades = idsfuncionalidades
						.iterator();
				// verifica se existe a funcionalidade escolhida na coleção de
				// funcionalidade
				while (iteratorFuncionalidades.hasNext()) {
					Integer idfuncionalidade = (Integer) iteratorFuncionalidades
							.next();
					Map<Integer, Collection<Operacao>> operacoesMap = funcionalidadesMap
							.get(idfuncionalidade);
					// para cada funcionalidade verifica se existe operações
					// desmarcadas(com indicador igual a 2).
					Collection colecaoOperacao = operacoesMap.get(2);
					if (colecaoOperacao != null && !colecaoOperacao.isEmpty()) {
						Iterator iteratorOperacao = colecaoOperacao.iterator();
						while (iteratorOperacao.hasNext()) {
							Operacao operacao = (Operacao) iteratorOperacao
									.next();

							Collection idsGruposNaBase = null;
							try {
								// pesquisando os ids que vão ser inseridos na
								// tabela de
								// usuarioGrupoRestrincao
								idsGruposNaBase = repositorioUsuario
										.pesquisarIdsGruposPelaFuncionalidadeGruposOperacao(
												idsGrupos, idfuncionalidade,
												operacao.getId());
							} catch (ErroRepositorioException ex) {
								sessionContext.setRollbackOnly();
								throw new ControladorException("erro.sistema",
										ex);
							}
							// recupera os ids dos grupos existente no grupo
							// funcionalidade operacao
							Iterator iteratoridsGrupos = idsGruposNaBase
									.iterator();
							while (iteratoridsGrupos.hasNext()) {
								Integer idGrupo = (Integer) iteratoridsGrupos
										.next();
								UsuarioGrupoRestricao usuarioGrupoRestricao = new UsuarioGrupoRestricao();
								UsuarioGrupoRestricaoPK usuarioGrupoRestricaoPK = new UsuarioGrupoRestricaoPK();

								usuarioGrupoRestricaoPK
										.setFuncionalidadeId(idfuncionalidade);
								usuarioGrupoRestricaoPK.setOperacaoId(operacao
										.getId());
								usuarioGrupoRestricaoPK.setGrupoId(idGrupo);
								usuarioGrupoRestricaoPK
										.setUsuarioId(usuarioAtualizar.getId());

								usuarioGrupoRestricao
										.setComp_id(usuarioGrupoRestricaoPK);
								usuarioGrupoRestricao
										.setUltimaAlteracao(new Date());
								/*
								 * // registrar transação usuarioGrupoRestricao
								 * .setOperacaoEfetuada(operacaoEfetuada);
								 * usuarioGrupoRestricao .adicionarUsuario(
								 * Usuario.USUARIO_TESTE,
								 * UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
								 * 
								 * registradorOperacao
								 * .registrarOperacao(usuarioGrupoRestricao);
								 */
								
								usuarioGrupoRestricao.setUltimaAlteracao(new Date());
								// ------------ REGISTRAR TRANSAÇÃO ----------------
								RegistradorOperacao registradorOperacao = new RegistradorOperacao(
								    Operacao.OPERACAO_USUARIO_CONTROLAR_PERMISSOES_ESPECIAIS, 
								    usuarioGrupoRestricao.getComp_id().getFuncionalidadeId(),
								    usuarioGrupoRestricao.getComp_id().getFuncionalidadeId(),
								    new UsuarioAcaoUsuarioHelper(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
								
								registradorOperacao.registrarOperacao(usuarioGrupoRestricao);

								// ------------ REGISTRAR TRANSAÇÃO ----------------
								
								getControladorUtil().inserir(
										usuarioGrupoRestricao);
							}
						}
					}

				}
			}
		}
		// caso tenha passado na funcionalidade de permissões especiais
		if (!(permissoesEspeciais == null && permissoesCheckBoxVazias == null)) {

			/*
			 * Pesquisa todos os usuários com permissão especial , e remove
			 * todos para ser inseridos os novos usuários com permissão especial
			 * informados pelo usuário
			 */
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial
					.adicionarParametro(new ParametroSimples(
							FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID,
							usuarioAtualizar.getId()));
			Collection colecaoUsuarioPermissaoEspecialCadastradas = getControladorUtil()
					.pesquisar(filtroUsuarioPemissaoEspecial,
							UsuarioPermissaoEspecial.class.getName());
			if (colecaoUsuarioPermissaoEspecialCadastradas != null
					&& !colecaoUsuarioPermissaoEspecialCadastradas.isEmpty()) {
				Iterator iteratorUsuarioPermissaoEspecial = colecaoUsuarioPermissaoEspecialCadastradas
						.iterator();
				while (iteratorUsuarioPermissaoEspecial.hasNext()) {
					UsuarioPermissaoEspecial usuarioPermissaoEspecial = (UsuarioPermissaoEspecial) iteratorUsuarioPermissaoEspecial
							.next();
					// registrar transação
					/*
					 * usuarioPermissaoEspecial.setOperacaoEfetuada(operacaoEfetuada);
					 * usuarioPermissaoEspecial.adicionarUsuario(
					 * Usuario.USUARIO_TESTE,
					 * UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
					 * 
					 * registradorOperacao.registrarOperacao(usuarioPermissaoEspecial);
					 */
					getControladorUtil().remover(usuarioPermissaoEspecial);
				}
			}

			/*
			 * Caso o usuário tenha informado algum usuario permissao especial
			 * que está sendo atualizado, inseri na tabela usuario permissao
			 * especial
			 */
			if (permissoesEspeciais != null && permissoesEspeciais.length != 0) {

				for (int i = 0; i < permissoesEspeciais.length; i++) {

					UsuarioPermissaoEspecial usuarioPermissaoEspecial = new UsuarioPermissaoEspecial();
					UsuarioPermissaoEspecialPK usuarioPermissaoEspecialPK = new UsuarioPermissaoEspecialPK();

					usuarioPermissaoEspecialPK
							.setPermissaoEspecialId(new Integer(
									permissoesEspeciais[i]));
					usuarioPermissaoEspecialPK.setUsuarioId(usuarioAtualizar
							.getId());

					usuarioPermissaoEspecial
							.setComp_id(usuarioPermissaoEspecialPK);
					usuarioPermissaoEspecial.setUltimaAlteracao(new Date());

					/**
					 * @author Flavio Ferreira
					 * @date 25/05/2010
					 * 
					 * Pesquisa feita para que seja exibido a permissao especial e o usuario na transação registrada.
					 * 
					 */
					
					FiltroPemissaoEspecial filtroPemissaoEspecial = new FiltroPemissaoEspecial();
					filtroPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroPemissaoEspecial.ID, 
							new Integer(permissoesEspeciais[i])));
					
					Collection colecao = getControladorUtil().pesquisar(filtroPemissaoEspecial, PermissaoEspecial.class.getName());
					PermissaoEspecial permissaoEspecial = (PermissaoEspecial) Util.retonarObjetoDeColecao(colecao);
					
					usuarioPermissaoEspecial.setPermissaoEspecial(permissaoEspecial);
					
					usuarioPermissaoEspecial.setUsuario(usuarioAtualizar);
//					FiltroUsuario filtroUsuario = new FiltroUsuario();
//					filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID,	new Integer(usuarioPermissaoEspecialPK.getUsuarioId()))); 
//					//adicionarParametro(new ParametroSimples(FiltroUsuario.ID,	new Integer(usuarioPermissaoEspecialPK)));
//					
//					Collection colecaoUsuario = getControladorUtil().pesquisar(filtroUsuario, UsuarioPermissaoEspecial.class.getName());
//					PermissaoEspecial permissaoEspecial = (PermissaoEspecial) Util.retonarObjetoDeColecao(colecaoUsuario);

					/**
					 * FIM
					 */
					
					/*
					 * // registrar transação
					 * usuarioPermissaoEspecial.setOperacaoEfetuada(operacaoEfetuada);
					 * usuarioPermissaoEspecial.adicionarUsuario(
					 * Usuario.USUARIO_TESTE,
					 * UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
					 * 
					 * registradorOperacao.registrarOperacao(usuarioPermissaoEspecial);
					 */
					
					// ------------ REGISTRAR TRANSAÇÃO ----------------
					
					RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					    Operacao.OPERACAO_USUARIO_CONTROLAR_PERMISSOES_ESPECIAIS, 
					    usuarioPermissaoEspecial.getComp_id().getPermissaoEspecialId(),
					    usuarioPermissaoEspecial.getComp_id().getPermissaoEspecialId(),
					    new UsuarioAcaoUsuarioHelper(usuarioLogado,
					    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

					registradorOperacao.registrarOperacao(usuarioPermissaoEspecial);

					// ------------ REGISTRAR TRANSAÇÃO ----------------
					getControladorUtil().inserir(usuarioPermissaoEspecial);
				}
			}
		}

	}

	/**
	 * [UC0291] Bloquear/Desbloquear Acesso Usuario
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/06/2006
	 * 
	 * @param usuario
	 * 
	 * @throws ControladorException
	 * @throws ErroEmailException
	 */

	public void bloquearDesbloquearUsuarioSituacao(Usuario usuario)
			throws ControladorException {
		// Verifica se o campo Login foi preenchido

		if (usuario.getLogin() == null
				|| usuario.getLogin().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Login");
		}
		// Verifica se o campo Usuario Situação foi preenchido
		if (usuario.getUsuarioSituacao() == null
				|| usuario.getUsuarioSituacao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Situação do Usuário");
		}

		FiltroUsuario filtroUsuario = new FiltroUsuario();

		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, usuario.getLogin()));

		Collection colecaousuario = getControladorUtil().pesquisar(
				filtroUsuario, Usuario.class.getName());

		UsuarioSituacao usuarioSituacao = new UsuarioSituacao();

		if (Util.isVazioOrNulo(colecaousuario)) {
			throw new ControladorException("atencao.login_nao_existente", null,
					"" + usuario.getLogin() + "");
		}
		//Usuario com situação pendente não deve ser atualizado
		Usuario usuarioBase = (Usuario) Util.retonarObjetoDeColecao(colecaousuario);
		if (usuarioBase.getUsuarioSituacao().getId() == 2) {
			throw new ControladorException("atencao.usuario_alteracao_senha",
					null);

		}

		// Caso a situcao do usuario selecionada corresponda ao valor "ATIVO",
		// id da situacao do usuario com o
		// valor correspondente a "SENHA PENDENTE" na tabela usuario_situacao
		// envia e-mail para o usuario informando login e senha gerada pelo
		// sistema
		if (usuario.getUsuarioSituacao().getId() == 1) {

			usuarioSituacao.setDescricaoUsuarioSituacao(usuario
					.getUsuarioSituacao().getDescricaoUsuarioSituacao());

			usuarioSituacao.setId(2);

			usuario.setUsuarioSituacao(usuarioSituacao);

			// Gera senha aleatoria

			String senha = Util.geradorSenha(10);

			usuario.setSenha(senha);
			
			EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.INSERIR_USUARIO);

			// Envia e-mail para o usuario informando usuario e senha

			try {
				ServicosEmail.enviarMensagem(envioEmail.getEmailRemetente(), usuario
						.getDescricaoEmail(), 
						envioEmail.getTituloMensagem(), "Usuário:" + usuario.getLogin()
								+ " \n " + "Senha:" + usuario.getSenha());
			} catch (ErroEmailException e) {

				e.printStackTrace();
			}
		}

		filtroUsuario.limparListaParametros();

		// [FS0003] Verificar nova situação de usuario
		// Verifica se situcaoUsuario atual é igual a nova

		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID,
				usuario.getId()));

		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.USUARIO_SITUACAO, usuario.getUsuarioSituacao()
						.getId().toString()));

		colecaousuario = getControladorUtil().pesquisar(filtroUsuario,
				Usuario.class.getName());

		if (colecaousuario != null && !colecaousuario.isEmpty()) {
			throw new ControladorException(
					"atencao.usuario_situcao_igual_principal", null, ""
							+ usuario.getUsuarioSituacao()
									.getDescricaoUsuarioSituacao() + "");

		}

		usuario.setUltimaAlteracao(new Date());

		getControladorUtil().atualizar(usuario);

	} // fim

	/**
	 * Método que consulta os grupos do usuário
	 * 
	 * @author Sávio Luiz
	 * @date 27/06/2006
	 */
	public Collection pesquisarGruposUsuario(Integer idUsuario)
			throws ControladorException {
		try {
			// salvando o usuarioGrupo
			return repositorioUsuario.pesquisarGruposUsuario(idUsuario);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Método que consulta os grupos do usuário da tabela grupoAcessos
	 * 
	 * @author Sávio Luiz
	 * @date 21/02/2007
	 */
	public Collection pesquisarGruposUsuarioAcesso(Collection colecaoUsuarioGrupos)throws ControladorException {
		try {
			// salvando o usuarioGrupo
			return repositorioUsuario.pesquisarGruposUsuarioAcesso(colecaoUsuarioGrupos);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método que consulta as abrangências dos usuário pelos os ids das
	 * abrangências superiores e com o id da abrangência diferente do id da
	 * abrangência do usuário que está inserindo(usuário logado)
	 * 
	 * @author Sávio Luiz
	 * @date 28/06/2006
	 */
	public Collection pesquisarUsuarioAbrangenciaPorSuperior(
			Collection colecaoUsuarioAbrangencia,
			Integer idUsuarioAbrangenciaLogado) throws ControladorException {
		try {
			// salvando o usuarioGrupo
			return repositorioUsuario.pesquisarUsuarioAbrangenciaPorSuperior(
					colecaoUsuarioAbrangencia, idUsuarioAbrangenciaLogado);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Informa o número total de registros de usuario grupo, auxiliando o
	 * esquema de paginação
	 * 
	 * @author Sávio Luiz
	 * @date 30/06/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return Número de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public int totalRegistrosPesquisaUsuarioGrupo(
			FiltroUsuarioGrupo filtroUsuarioGrupo) throws ControladorException {
		try {
			// salvando o usuarioGrupo
			return repositorioUsuario
					.totalRegistrosPesquisaUsuarioGrupo(filtroUsuarioGrupo);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Informa o número total de registros de usuario grupo, auxiliando o
	 * esquema de paginação
	 * 
	 * @author Sávio Luiz
	 * @date 30/06/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return Número de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public Collection pesquisarUsuariosDosGruposUsuarios(
			FiltroUsuarioGrupo filtroUsuarioGrupo, Integer numeroPagina)
			throws ControladorException {
		try {
			// salvando o usuarioGrupo
			return repositorioUsuario.pesquisarUsuariosDosGruposUsuarios(
					filtroUsuarioGrupo, numeroPagina);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método que consulta os grupos funcionários operações passando os ids dos
	 * grupos
	 * 
	 * @author Sávio Luiz
	 * @date 11/07/2006
	 */

	public Collection pesquisarGruposFuncionalidadeOperacoes(Integer[] idsGrupos)
			throws ControladorException {
		try {
			// salvando o usuarioGrupo
			return repositorioUsuario
					.pesquisarGruposFuncionalidadesOperacoes(idsGrupos);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método que consulta os grupos funcionários operações passando os ids dos
	 * grupos e o id da funcionalidade
	 * 
	 * @author Sávio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarGruposFuncionalidadesOperacoesPelaFuncionalidade(
			Integer[] idsGrupos, Integer idFuncionalidade)
			throws ControladorException {
		try {
			// salvando o usuarioGrupo
			return repositorioUsuario
					.pesquisarGruposFuncionalidadesOperacoesPelaFuncionalidade(
							idsGrupos, idFuncionalidade);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método que consulta os usuários restrinção passando os ids dos grupos , o
	 * id da funcionalidade e o id do usuário
	 * 
	 * @author Sávio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarUsuarioRestrincao(Integer[] idsGrupos,
			Integer idFuncionalidade, Integer idUsuario)
			throws ControladorException {
		try {
			// salvando o usuarioGrupo
			return repositorioUsuario.pesquisarUsuarioRestrincao(idsGrupos,
					idFuncionalidade, idUsuario);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método que consulta as funcionalidades da(s) funcionalidade(s)
	 * princpial(is)
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarFuncionanidadesDependencia(
			Collection idsFuncionalidades) throws ControladorException {
		try {
			// salvando o usuarioGrupo
			return repositorioUsuario
					.pesquisarFuncionanidadesDependencia(idsFuncionalidades);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método que consulta as operações da(s) funcionalidade(s)
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarOperacoes(Collection idsFuncionalidades)
			throws ControladorException {
		try {
			// salvando o usuarioGrupo
			return repositorioUsuario.pesquisarOperacoes(idsFuncionalidades);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método que consulta as operações da(s) funcionalidade(s) e das
	 * funcionalidades dependencia
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */
	public Collection recuperarOperacoesFuncionalidadesEDependentes(
			Integer idFuncionalidade) throws ControladorException {

		// cria uma coleção de funcionalidades principal e inseri o id da
		// funcionalidade na colleção
		Collection funcionalidadesPrincipal = new ArrayList();
		funcionalidadesPrincipal.add(idFuncionalidade);
		// cria uma coleção de funcionalidades dependencias que retornará as
		// funcionalidades dependencias da(s) funcionalidade(s) principal(is)
		Collection funcionalidadesDependencia = null;
		Collection idsFuncionalidadesParamOperacoes = new ArrayList();
		idsFuncionalidadesParamOperacoes.add(idFuncionalidade);
		// cria um boolean que vai verificar quando um determinado grupo(ou uma
		// só) de funcionalidade(s) não tem mais dependencias
		boolean terminou = false;
		// caso não tenha funcionalidades dependencias então sai do laço e
		// pesquisa as operações das funcionalidades
		while (!terminou) {
			funcionalidadesDependencia = pesquisarFuncionanidadesDependencia(funcionalidadesPrincipal);
			if (funcionalidadesDependencia != null
					&& !funcionalidadesDependencia.isEmpty()) {
				idsFuncionalidadesParamOperacoes
						.addAll(funcionalidadesDependencia);
				funcionalidadesPrincipal = new ArrayList();
				funcionalidadesPrincipal.addAll(funcionalidadesDependencia);
			} else {
				terminou = true;
			}
		}
		Collection operacoes = pesquisarOperacoes(idsFuncionalidadesParamOperacoes);
		return operacoes;
	}

	/**
	 * Método que marca e desmarca as permissões especiais do usuário.
	 * 
	 * @author Sávio Luiz
	 * @date 12/07/2006
	 */

	public String[] retornarPermissoesMarcadas(Collection permissoesEspeciais) {
		String[] permissaoEspecial = null;
		if (permissoesEspeciais != null && !permissoesEspeciais.isEmpty()) {
			// seta os campos de permissão especial no form para
			// aparecer no
			// jsp como checado
			Iterator iteratorPermissaoEspecial = permissoesEspeciais.iterator();
			int i = 0;
			permissaoEspecial = new String[permissoesEspeciais.size()];
			while (iteratorPermissaoEspecial.hasNext()) {
				PermissaoEspecial permissaoEspecialObject = (PermissaoEspecial) iteratorPermissaoEspecial
						.next();
				permissaoEspecial[i] = ""
						+ permissaoEspecialObject.getId();
				i = i + 1;
			}
		}
		return permissaoEspecial;
	}

	/**
	 * Retorna 2 coleções e um array ,com os valores que vão retornar
	 * marcados,uma com as permissões do usuário que ele possa marcar/desmarcar
	 * e a outra o usuário logado não vai poder marcar/desmarcar
	 * 
	 * [UC0231] - Manter Usuário [SB0010] - Selecionar Permissões Especiais
	 * (n°2)
	 * 
	 * @author Sávio Luiz
	 * @date 13/07/2006
	 */
	public Object[] pesquisarPermissoesEspeciaisUsuarioEUsuarioLogado(
			Usuario usuarioAtualizar, Usuario usuarioLogado)
			throws ControladorException {

		Collection colecaoPermissaoEspecial = new ArrayList();
		Collection colecaoPermissaoEspecialDesabilitado = new ArrayList();
		String[] idsPermissaoEspecialMarcado = null;

		Object[] object = new Object[3];
		try {
			// permissões especiais do usuário que está sendo atualizado
			Collection colecaoPermissaoEspecialUsuarioAtualizar = repositorioUsuario
					.pesquisarPermissaoEspecialUsuario(usuarioAtualizar.getId());
			// array com os ids das permissões que vai ser checado
			idsPermissaoEspecialMarcado = retornarPermissoesMarcadas(colecaoPermissaoEspecialUsuarioAtualizar);

			colecaoPermissaoEspecial
					.addAll(colecaoPermissaoEspecialUsuarioAtualizar);
			// pesquisa permissões especiais do usuário que está logado
			Collection colecaoPermissaoEspecialUsuarioLogado = repositorioUsuario
					.pesquisarPermissaoEspecialUsuario(usuarioLogado.getId());

			Collection colecaoPermissaoUsuarioLogadoAux = new ArrayList();
			colecaoPermissaoUsuarioLogadoAux
					.addAll(colecaoPermissaoEspecialUsuarioLogado);
			colecaoPermissaoUsuarioLogadoAux
					.removeAll(colecaoPermissaoEspecialUsuarioAtualizar);
			colecaoPermissaoEspecial.addAll(colecaoPermissaoUsuarioLogadoAux);

			colecaoPermissaoEspecialUsuarioAtualizar
					.removeAll(colecaoPermissaoEspecialUsuarioLogado);
			colecaoPermissaoEspecialDesabilitado
					.addAll(colecaoPermissaoEspecialUsuarioAtualizar);
			colecaoPermissaoEspecial
					.removeAll(colecaoPermissaoEspecialDesabilitado);

			/*
			 * // pesquisa as permissão especial dos usuários que não vão //
			 * aparecer marcados Collection
			 * colecaoPermissaoUsuarioLogadoSemMarcado = repositorioUsuario
			 * .pesquisarPermissaoEspecialUsuarioSemPermissoes(
			 * usuarioLogado.getId(), colecaoPermissaoEspecialUsuarioAtualizar); //
			 * adiciona todos os campos na coleção de permissões especiais
			 * colecaoPermissaoEspecial
			 * .addAll(colecaoPermissaoEspecialUsuarioAtualizar);
			 * colecaoPermissaoEspecial
			 * .addAll(colecaoPermissaoUsuarioLogadoSemMarcado); // pesquisa
			 * permissões especiais do usuário que está logado Collection
			 * colecaoPermissaoEspecialUsuarioLogado = repositorioUsuario
			 * .pesquisarPermissaoEspecialUsuario(usuarioLogado.getId());
			 * 
			 * Collection colecaoPermissaoUsuarioAtualizarSemMarcado =
			 * repositorioUsuario
			 * .pesquisarPermissaoEspecialUsuarioSemPermissoes(
			 * usuarioAtualizar.getId(), colecaoPermissaoEspecialUsuarioLogado);
			 */

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		object[0] = colecaoPermissaoEspecial;
		object[1] = colecaoPermissaoEspecialDesabilitado;
		object[2] = idsPermissaoEspecialMarcado;
		return object;
	}

	/**
	 * Retorna um map com o indicador dizendo se vai aparecer
	 * marcado(1),desmarcado(2) ou desabilitado(3) para cada operação da
	 * funcionalidade escolhida
	 * 
	 * [UC0231] - Manter Usuário [SB0008] - Selecionar Restrinções (n°2)
	 * 
	 * @author Sávio Luiz
	 * @date 17/07/2006
	 */
	public Map<Integer, Map<Integer, Collection<Operacao>>> organizarOperacoesComValor(
			Integer codigoFuncionalidade,
			Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadeMap,
			Integer[] idsGrupos, Usuario usuarioAtualizar)
			throws ControladorException {

		boolean existeFuncionalidade = false;

		if (funcionalidadeMap != null && !funcionalidadeMap.isEmpty()) {

			Collection idsfuncionalidades = funcionalidadeMap.keySet();
			Iterator iteratorFuncionalidades = idsfuncionalidades.iterator();
			// verifica se existe a funcionalidade escolhida na coleção de
			// funcionalidade
			while (iteratorFuncionalidades.hasNext()) {
				Integer idfuncionalidade = (Integer) iteratorFuncionalidades
						.next();
				if (idfuncionalidade.equals(new Integer(codigoFuncionalidade))) {
					existeFuncionalidade = true;
				}
			}
		}
		// caso não exista funcionalidade na coleção de
		// gruposFuncionalidades e a coleção de ids funcionalidades não
		// esteja vazio então pesquisa na base as operações que
		// o usuário tem acesso
		if (!existeFuncionalidade) {

			// Cria a funcionalidade para ser inserido no map
			Map<Integer, Collection<Operacao>> operacoesMap = new HashMap();

			// pesquisa as operações na tabela de
			// grupoFuncionalidadeOperacao
			Collection operacoesDaFuncionalidadeGrupo = pesquisarGruposFuncionalidadesOperacoesPelaFuncionalidade(
					idsGrupos, new Integer(codigoFuncionalidade));

			// pesquisa as operações na tabela de
			// UsuarioRestrinção
			Collection operacoesUsuarioComRestrincao = pesquisarUsuarioRestrincao(
					idsGrupos, new Integer(codigoFuncionalidade),
					usuarioAtualizar.getId());
			// remove todas as operações do usuário com
			// restrinção
			// da
			// coleção de operações funcionalidade
			operacoesDaFuncionalidadeGrupo
					.removeAll(operacoesUsuarioComRestrincao);

			if (operacoesDaFuncionalidadeGrupo != null
					&& !operacoesDaFuncionalidadeGrupo.isEmpty()) {
				// coloca as operações com o indicador de seleção igual a
				// 1(checkbox marcados)
				operacoesMap.put(1, operacoesDaFuncionalidadeGrupo);
			}

			if (operacoesUsuarioComRestrincao != null
					&& !operacoesUsuarioComRestrincao.isEmpty()) {
				// coloca as operações com o indicador de seleção igual a
				// 2(checkbox desmarcados)
				operacoesMap.put(2, operacoesUsuarioComRestrincao);
			}
			// recupera as operações da tabela grupo funcionalidade operação e
			// da tabela usuário restrinções pesquisadas anteriormente
			Collection colecaoOperacoesUniao = new ArrayList();

			colecaoOperacoesUniao.addAll(operacoesUsuarioComRestrincao);

			colecaoOperacoesUniao.addAll(operacoesDaFuncionalidadeGrupo);

			Collection colecaoOperacao = recuperarOperacoesFuncionalidadesEDependentes(new Integer(
					codigoFuncionalidade));
			if (colecaoOperacao != null && !colecaoOperacao.isEmpty()) {
				Collection operacoesDesabilitados = new ArrayList();
				Iterator iteratorOperacoes = colecaoOperacao.iterator();

				while (iteratorOperacoes.hasNext()) {
					Operacao operacao = (Operacao) iteratorOperacoes.next();
					// caso não exista na coleção de operações pesquisadas
					// operacoesUsuarioComRestrincao e
					// operacoesDaFuncionalidadeGrupo então é colocado no map
					// com o
					// indicador de seleção igual a 3(Desabilitado)
					if (!colecaoOperacoesUniao.contains(operacao)) {
						operacoesDesabilitados.add(operacao);
					}
				}
				// coloca as operações com o indicador de seleção igual a
				// 3(checkbox desabilitado)
				operacoesMap.put(3, operacoesDesabilitados);
			}
			// seta o map operacoesMap na map de funcionalidades
			funcionalidadeMap.put(new Integer(codigoFuncionalidade),
					operacoesMap);
		}

		return funcionalidadeMap;
	}

	/**
	 * Retorna um map com o indicador dizendo se vai aparecer
	 * marcado(1),desmarcado(2) ou desabilitado(3) para cada operação da
	 * funcionalidade escolhida e a coleção com as operações e funcionalidades
	 * que que foram desmarcados
	 * 
	 * [UC0231] - Manter Usuário [SB0008] - Selecionar Restrinções (n°2)
	 * 
	 * @author Sávio Luiz
	 * @date 17/07/2006
	 */
	public Map<Integer, Map<Integer, Collection<Operacao>>> recuperaFuncionalidadeOperacaoRestrincao(
			Integer codigoFuncionalidade, String[] idsOperacoes,
			Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadeMap)
			throws ControladorException {

		Map<Integer, Collection<Operacao>> operacoesMap = funcionalidadeMap
				.get(new Integer(codigoFuncionalidade));
		// recupera a coleção de operações que estão com o indicador de seleção
		// igual a 1(Marcados)
		Collection operacoesMarcadas = operacoesMap.get(1);
		Collection operacoesDesmarcadasMais = new ArrayList();
		if (operacoesMarcadas != null && !operacoesMarcadas.isEmpty()) {
			Iterator iteratorOperacoes = operacoesMarcadas.iterator();
			while (iteratorOperacoes.hasNext()) {
				// cria um boolean que verifica se cada operação que tinha o
				// indicador igual a 1(Marcada) continua marcada,caso contrário
				// remove da coleção de operações marcadas e inseri na coleção
				// de
				// operações não marcadas.
				boolean achou = false;
				Operacao operacao = (Operacao) iteratorOperacoes.next();
				if (idsOperacoes != null && !idsOperacoes.equals("")) {
					for (int i = 0; i < idsOperacoes.length; i++) {
						if (operacao.getId().equals(
								new Integer(idsOperacoes[i]))) {
							achou = true;
						}
					}
				}
				if (!achou) {
					operacoesDesmarcadasMais.add(operacao);
					iteratorOperacoes.remove();
				}
			}
		}
		// recupera a coleção de operações que estão com o indicador de seleção
		// igual a 2(Desmarcados)
		Collection operacoesDesmarcadas = operacoesMap.get(2);
		Collection operacoesMarcadasMais = new ArrayList();
		if (operacoesDesmarcadas != null && !operacoesDesmarcadas.isEmpty()) {
			Iterator iteratorOperacoesDesmarcadas = operacoesDesmarcadas
					.iterator();
			while (iteratorOperacoesDesmarcadas.hasNext()) {
				// cria um boolean que verifica se cada operação que tinha o
				// indicador igual a 2(Desmarcada) foi marcada,se sim
				// remove da coleção de operações desmarcadas e inseri na
				// coleção de
				// operações marcadas.
				boolean achou = false;
				Operacao operacao = (Operacao) iteratorOperacoesDesmarcadas
						.next();
				if (idsOperacoes != null && !idsOperacoes.equals("")) {
					for (int i = 0; i < idsOperacoes.length; i++) {
						if (operacao.getId().equals(
								new Integer(idsOperacoes[i]))) {
							achou = true;
						}
					}
				}
				if (achou) {
					operacoesMarcadasMais.add(operacao);
					iteratorOperacoesDesmarcadas.remove();
				}
			}
		}
		// adiciona as novas operações marcadas na coleção de operações
		// marcadas.
		if (operacoesMarcadas != null && !operacoesMarcadas.isEmpty()) {
			operacoesMarcadas.addAll(operacoesMarcadasMais);
		} else {
			if (operacoesMarcadasMais != null
					&& !operacoesMarcadasMais.isEmpty()) {

				operacoesMarcadas = new ArrayList();
				operacoesMarcadas.addAll(operacoesMarcadasMais);
				operacoesMap.put(1, operacoesMarcadas);
			}
		}
		// adiciona as novas operações desmarcadas na coleção de operações
		// desmarcadas.
		if (operacoesDesmarcadas != null && !operacoesDesmarcadas.isEmpty()) {
			operacoesDesmarcadas.addAll(operacoesDesmarcadasMais);

		} else {
			if (operacoesDesmarcadasMais != null
					&& !operacoesDesmarcadasMais.isEmpty()) {
				operacoesDesmarcadas = new ArrayList();
				operacoesDesmarcadas.addAll(operacoesDesmarcadasMais);
				operacoesMap.put(2, operacoesDesmarcadas);
			}
		}

		return funcionalidadeMap;

	}
	
	/**
	 * Método que consulta o nome do usuário de uma guia de devolução, passando
	 * por parâmetro o id da guia de devolucao
	 * 
	 * @author Daniel Alves
	 * @date 22/02/2010
	 */
	public String pesquisarUsuarioPorGuiaDevolucao(Integer idGuiaDevolucao)
			throws ControladorException {
		try {
			return repositorioUsuario
					.pesquisarUsuarioPorGuiaDevolucao(idGuiaDevolucao);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0204] Consultar Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 16/11/2010
	 */
	public Collection pesquisarUsuario(Integer idOperacao,
			Integer idImovel,String referenciaConta)throws ControladorException {
		try {
			return repositorioUsuario.pesquisarUsuario(idOperacao,idImovel,referenciaConta);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0146] Manter Conta
	 * [SB0012] – Determinar competência de retificação de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public Collection pesquisarGrupoUsuario(Integer idUsuario)throws ControladorException {
		try {
			return repositorioUsuario.pesquisarGrupoUsuario(idUsuario);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}	

	/**
	 * [UC0146] Manter Conta
	 * [SB0012] – Determinar competência de retificação de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public BigDecimal pesquisarMaiorCompetenciaRetificacaoGrupo()throws ControladorException {
		try {
			return repositorioUsuario.pesquisarMaiorCompetenciaRetificacaoGrupo();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}	
	
	/**
	 * [UC0230] Inserir Usuário
	 * [FS0020] Verificar existência de usuário batch
	 * [FS0021] Verificar usuário batch
	 * 
	 * @author Paulo Diniz
	 * @throws ControladorException 
	 * @date 03/03/2011
	 */
	public Usuario pesquisarUsuarioRotinaBatch() throws ControladorException{
		try {
			return repositorioUsuario.pesquisarUsuarioRotinaBatch();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}	
	
	public UsuarioDTO pesquisarUsuario(Integer idUsuario) throws ControladorException {
		try {
			return repositorioUsuario.pesquisarUsuario(idUsuario);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	/**
	 * [UC0230] Inserir Usuário
	 * [FS0022] Verificar existência de usuário internet
	 * [FS0023] Verificar usuário internet
	 * 
	 * @author Paulo Diniz
	 * @throws ControladorException 
	 * @date 03/03/2011
	 */
	public Usuario pesquisarUsuarioInternet() throws ControladorException{
		try {
			return repositorioUsuario.pesquisarUsuarioInternet();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}	
	
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * Filtra os Usuarios por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ControladorException 
	 */
	public Collection filtrarAutocompleteUsuario(String valor)throws ControladorException{
		
		try {
			return repositorioUsuario.filtrarAutocompleteUsuario(valor);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	

	
}
