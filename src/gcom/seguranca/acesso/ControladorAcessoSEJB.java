package gcom.seguranca.acesso;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.relatorio.seguranca.FiltrarRelatorioAcessosUsuariosHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper;
import gcom.relatorio.seguranca.FiltrarRelatorioSolicitacaoAcessoHelper;
import gcom.relatorio.seguranca.RelatorioAcessosPorUsuariosHelper;
import gcom.relatorio.seguranca.RelatorioFuncionalidadeOperacoesPorGrupoHelper;
import gcom.relatorio.seguranca.RelatorioSolicitacaoAcessoHelper;
import gcom.seguranca.FiltroSegurancaParametro;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.acesso.usuario.ControladorUsuarioLocal;
import gcom.seguranca.acesso.usuario.ControladorUsuarioLocalHome;
import gcom.seguranca.acesso.usuario.FiltroAbrangenciaUsuario;
import gcom.seguranca.acesso.usuario.FiltroSituacaoUsuario;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioFavorito;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupoRestricao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSenhaHistorico;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.acesso.usuario.UsuarioFavorito;
import gcom.seguranca.acesso.usuario.UsuarioFavoritoPK;
import gcom.seguranca.acesso.usuario.UsuarioGrupoRestricao;
import gcom.seguranca.acesso.usuario.UsuarioSenhaHistorico;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.IRepositorioUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.GeradorHQLCondicional;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.PersistenciaUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Defini��o da l�gica de neg�cio do Session Bean de ControladorCliente
 * 
 * @author S�vio Luiz
 * @created 25 de Abril de 2005
 */
/**
 * Descri��o da classe
 * 
 * @author Administrador
 * @date 04/07/2006
 */
public class ControladorAcessoSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	SessionContext sessionContext;
	private IRepositorioAcesso repositorioAcesso;
	
	protected IRepositorioUtil repositorioUtil = null;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @exception CreateException
	 *                Descri��o da exce��o
	 */
	public void ejbCreate() throws CreateException {
		repositorioAcesso = RepositorioAcessoHBM.getInstancia();
		
		repositorioUtil = RepositorioUtilHBM.getInstancia();

	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descri��o do m�todo>>
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

	/**
	 * 
	 * M�todo que consulta todas as TabelaColunas que estejam ligadas a uma
	 * Operacao
	 * 
	 * @author Thiago Toscano
	 * @date 23/03/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection getTabelaColunaPertencenteOperacao()
			throws ControladorException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
			filtroTabelaColuna
					.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaColuna.TABELA);

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroTabelaColuna,
							"tabelaColuna",
							" select tabelaColuna "
									+ " from TabelaColuna as tabelaColuna "
									+ PersistenciaUtil
											.processaObjetosParaCarregamentoJoinFetch(
													"tabelaColuna",
													filtroTabelaColuna
															.getColecaoCaminhosParaCarregamentoEntidades())
									+ " where tabelaColuna in (	select operacao.tabelaColuna "
									+ "							from Operacao as operacao "
									+ "							where operacao.tabelaColuna is not null)"
									+ "", session).list();

			// Carrega os objetos informados no filtro
			/*
			 * if (!filtroTabelaColuna
			 * .getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
			 * PersistenciaUtil.processaObjetosParaCarregamento(
			 * filtroTabelaColuna
			 * .getColecaoCaminhosParaCarregamentoEntidades(), retorno); }
			 */
			// erro no hibernate
		} catch (ErroRepositorioException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("Erro no Hibernate", e);
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("Erro no Hibernate", e);
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * M�todo que pesquisa todas as tabelas colunas que tem ligacao com operacao
	 * pela operacao tabela
	 * 
	 * @author thiago toscano
	 * @date 23/03/2006
	 * 
	 * @param idOperacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection getTabelaColunaDasOperacaoTabela(Integer idOperacao)
			throws ControladorException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			// FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
			// filtroTabelaColuna.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaColuna.TABELA);
			// filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.TABELA_COLUNA);

			FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroOperacaoTabela,
							"operacaoTabela",
							" select distinct operacaoTabela.tabela.tabelaColunas "
									+ " from OperacaoTabela as operacaoTabela "
									+ PersistenciaUtil
											.processaObjetosParaCarregamentoJoinFetch(
													"operacaoTabela",
													filtroOperacaoTabela
															.getColecaoCaminhosParaCarregamentoEntidades())
									+ " inner join operacaoTabela.operacao as operacao "
									+ " inner join operacaoTabela.tabela as tabela "
									+ " inner join tabela.tabelaColunas as tc "
									+ " where  operacaoTabela.operacao.id = "
									+ idOperacao + "", session).list();

			// Carrega os objetos informados no filtro
			/*
			 * if (!filtroOperacaoTabela
			 * .getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
			 * PersistenciaUtil.processaObjetosParaCarregamento(
			 * filtroOperacaoTabela
			 * .getColecaoCaminhosParaCarregamentoEntidades(), retorno); }
			 */
			// erro no hibernate
		} catch (ErroRepositorioException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("Erro no Hibernate", e);
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("Erro no Hibernate", e);
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
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
	 * Retorna a interface remota de ControladorParametro
	 * 
	 * @return A interface remota do controlador de par�metro
	 */
	private ControladorUsuarioLocal getControladorUsuario() {
		ControladorUsuarioLocalHome localHome = null;
		ControladorUsuarioLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUsuarioLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_USUARIO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
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
	 * [UC0280] Inserir Funcionalidade
	 * 
	 * Metodo que verifica os dados da tabela e inseri a funcionalidade
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 28/04/2006
	 * 
	 * @param funcionalidade
	 * @throws ControladorException
	 */

	public Integer inserirFuncionalidade(Funcionalidade funcionalidade,
			Collection colecaoFuncionalidadeDependencia)
			throws ControladorException {

		// Verifica se todos os campos obrigatorios foram preenchidos

		if ((funcionalidade.getDescricao() == null || funcionalidade
				.getDescricao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (funcionalidade.getDescricaoAbreviada() == null || funcionalidade
						.getDescricaoAbreviada().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (funcionalidade.getCaminhoMenu() == null || funcionalidade
						.getCaminhoMenu().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (funcionalidade.getCaminhoMenu() == null || funcionalidade
						.getCaminhoMenu().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			throw new ControladorException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Verifica se o campo Descri��o foi preenchido

		if (funcionalidade.getDescricao() == null
				|| funcionalidade.getDescricao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Descri��o");
		}

		// Verifica se o campo DescricaoAbreviada foi preenchido

		if (funcionalidade.getDescricaoAbreviada() == null
				|| funcionalidade.getDescricaoAbreviada().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Descri��o Abreviada");
		}

		// Verifica se o campo CaminhoMenu foi preenchido

		if (funcionalidade.getCaminhoMenu() == null
				|| funcionalidade.getCaminhoMenu().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Caminho Menu");
		}

		// Verifica se o campo CaminhoURL foi preenchido

		if (funcionalidade.getCaminhoUrl() == null
				|| funcionalidade.getCaminhoUrl().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Caminho URL");
		}

		// Verifica se o campo IndicadorPontoEntrada foi preenchido

		if (funcionalidade.getIndicadorPontoEntrada() == null
				|| funcionalidade.getIndicadorPontoEntrada().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Ponto de Entrada");
		}

		// Verifica se o campo Modulo foi preenchido

		if (funcionalidade.getModulo() == null
				|| funcionalidade.getModulo().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.naoinformado", null,
					"M�dulo");
		}

		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();

		filtroFuncionalidade.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidade.DESCRICAO, funcionalidade.getDescricao()));

		Collection colecaoFuncionalidade = getControladorUtil().pesquisar(
				filtroFuncionalidade, Funcionalidade.class.getName());

		if (colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()) {
			throw new ControladorException("atencao.descricao_ja_existente",
					null, "" + funcionalidade.getDescricao() + "");
		}

		funcionalidade.setUltimaAlteracao(new Date());

		Integer idFuncionalidade = (Integer) getControladorUtil().inserir(
				funcionalidade);

		if (colecaoFuncionalidadeDependencia != null
				&& !colecaoFuncionalidadeDependencia.isEmpty()) {
			Iterator iterator = colecaoFuncionalidadeDependencia.iterator();

			while (iterator.hasNext()) {

				FuncionalidadeDependencia funcionalidadeDependencia = new FuncionalidadeDependencia();
				Funcionalidade funcionalidadeInserir = (Funcionalidade) iterator
						.next();
				funcionalidadeDependencia
						.setFuncionalidade(funcionalidadeInserir);

				FuncionalidadeDependenciaPK funcionalidadeDependenciaPK = new FuncionalidadeDependenciaPK();
				funcionalidadeDependenciaPK
						.setFuncionalidadeDependenciaId(funcionalidadeDependencia
								.getFuncionalidade().getId());
				funcionalidadeDependenciaPK.setFuncionalidadeId(funcionalidade
						.getId());

				funcionalidadeDependencia
						.setComp_id(funcionalidadeDependenciaPK);

				this.getControladorUtil().inserir(funcionalidadeDependencia);

			}

		}

		return idFuncionalidade;

	}

	/**
	 * [UC0281] Manter Funcionalidade [SB0001] Atualizar Funcionalidade Metodo
	 * que atualiza a funcionalidade
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 17/05/2006
	 * 
	 * @param funcionalidade
	 * @throws ControladorException
	 */

	public void atualizarFuncionalidade(Funcionalidade funcionalidade,
			Collection colecaoFuncionalidadeDependencia)
			throws ControladorException {

		// Verifica se todos os campos obrigatorios foram preenchidos

		if ((funcionalidade.getDescricao() == null || funcionalidade
				.getDescricao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (funcionalidade.getDescricaoAbreviada() == null || funcionalidade
						.getDescricaoAbreviada().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (funcionalidade.getCaminhoMenu() == null || funcionalidade
						.getCaminhoMenu().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (funcionalidade.getCaminhoMenu() == null || funcionalidade
						.getCaminhoMenu().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			throw new ControladorException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Verifica se o campo Descri��o foi preenchido

		if (funcionalidade.getDescricao() == null
				|| funcionalidade.getDescricao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Descri��o");
		}

		// Verifica se o campo DescricaoAbreviada foi preenchido

		if (funcionalidade.getDescricaoAbreviada() == null
				|| funcionalidade.getDescricaoAbreviada().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Descri��o Abreviada");
		}

		// Verifica se o campo CaminhoMenu foi preenchido

		if (funcionalidade.getCaminhoMenu() == null
				|| funcionalidade.getCaminhoMenu().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Caminho Menu");
		}

		// Verifica se o campo CaminhoURL foi preenchido

		if (funcionalidade.getCaminhoUrl() == null
				|| funcionalidade.getCaminhoUrl().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Caminho URL");
		}

		// Verifica se o campo IndicadorPontoEntrada foi preenchido

		if (funcionalidade.getIndicadorPontoEntrada() == null
				|| funcionalidade.getIndicadorPontoEntrada().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Ponto de Entrada");
		}

		// [FS0003] - Atualiza��o realizada por outro usu�rio
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidade.ID, funcionalidade.getId()));

		Collection colecaoFuncionalidadeBase = getControladorUtil().pesquisar(
				filtroFuncionalidade, Funcionalidade.class.getName());

		if (colecaoFuncionalidadeBase == null
				|| colecaoFuncionalidadeBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		Funcionalidade funcionalidadeBase = (Funcionalidade) colecaoFuncionalidadeBase
				.iterator().next();

		if (funcionalidadeBase.getUltimaAlteracao().after(
				funcionalidade.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		filtroFuncionalidade.limparListaParametros();

		// Verifica se o campo Modulo foi preenchido

		if (funcionalidade.getModulo() == null
				|| funcionalidade.getModulo().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.naoinformado", null,
					"M�dulo");
		}

		String descFuncionalidadeNaBase = funcionalidadeBase.getDescricao();
		if (!funcionalidade.getDescricao().equalsIgnoreCase(
				descFuncionalidadeNaBase)) {
			filtroFuncionalidade.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidade.DESCRICAO, funcionalidade
							.getDescricao()));

			Collection colecaoFuncionalidade = getControladorUtil().pesquisar(
					filtroFuncionalidade, Funcionalidade.class.getName());

			if (colecaoFuncionalidade != null
					&& !colecaoFuncionalidade.isEmpty()) {
				throw new ControladorException(
						"atencao.descricao_ja_existente", null, ""
								+ funcionalidade.getDescricao() + "");
			}
		}

		funcionalidade.setUltimaAlteracao(new Date());

		getControladorUtil().atualizar(funcionalidade);

		FiltroFuncionalidadeDependencia filtroFuncionalidadeDependencia = new FiltroFuncionalidadeDependencia();
		filtroFuncionalidadeDependencia
				.adicionarParametro(new ParametroSimples(
						FiltroFuncionalidadeDependencia.FUNCIONALIDADE_ID,
						funcionalidade.getId()));

		Collection colecaoFuncionalidadeDependenciaBase = getControladorUtil()
				.pesquisar(filtroFuncionalidadeDependencia,
						FuncionalidadeDependencia.class.getName());

		if (colecaoFuncionalidadeDependenciaBase != null
				&& !colecaoFuncionalidadeDependenciaBase.isEmpty()) {
			Iterator colecaoFuncionalidadeDependenciaBaseIterator = colecaoFuncionalidadeDependenciaBase
					.iterator();

			while (colecaoFuncionalidadeDependenciaBaseIterator.hasNext()) {
				FuncionalidadeDependencia funcionalidadeDependenciaBase = (FuncionalidadeDependencia) colecaoFuncionalidadeDependenciaBaseIterator
						.next();
				getControladorUtil().remover(funcionalidadeDependenciaBase);
			}
		}

		if (colecaoFuncionalidadeDependencia != null
				&& !colecaoFuncionalidadeDependencia.isEmpty()) {
			Iterator colecaoFuncionalidadeDependenciaIterator = colecaoFuncionalidadeDependencia
					.iterator();

			while (colecaoFuncionalidadeDependenciaIterator.hasNext()) {
				FuncionalidadeDependencia funcionalidadeDependenciaTela = (FuncionalidadeDependencia) colecaoFuncionalidadeDependenciaIterator
						.next();

				FuncionalidadeDependencia funcionalidadeDependencia = new FuncionalidadeDependencia();

				funcionalidadeDependencia.setFuncionalidade(funcionalidade);
				funcionalidadeDependencia
						.setFuncionalidadeDependencia(funcionalidadeDependenciaTela
								.getFuncionalidadeDependencia());

				FuncionalidadeDependenciaPK funcionalidadeDependenciaPK = new FuncionalidadeDependenciaPK();
				funcionalidadeDependenciaPK
						.setFuncionalidadeDependenciaId(funcionalidadeDependencia
								.getFuncionalidadeDependencia().getId());
				funcionalidadeDependenciaPK.setFuncionalidadeId(funcionalidade
						.getId());
				funcionalidadeDependencia
						.setComp_id(funcionalidadeDependenciaPK);

				getControladorUtil().inserir(funcionalidadeDependencia);
			}
		}

	}


	/**
	 * Inseri um grupo na base de dados e suas permiss�es
	 * 
	 * [UC0278] Inserir Grupo
	 * 
	 * @author Pedro Alexandre
	 * @date 21/07/2006
	 * 
	 * @param grupo
	 * @param colecaoGrupoFuncionalidadeOperacao
	 * @throws ControladorException
	 */
	public void inserirGrupo(Grupo grupo,
			Collection colecaoGrupoFuncionalidadeOperacao, Usuario usuarioLogado)
			throws ControladorException {
		
		// Seta a data de �ltima altera��o do grupo
		grupo.setUltimaAlteracao(new Date());
		// ------------ REGISTRAR TRANSA��O ----------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		    Operacao.OPERACAO_INSERIR_GRUPO,
		    grupo.getId(), grupo.getId(),
		    new UsuarioAcaoUsuarioHelper(usuarioLogado,
		    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(grupo);

		// ------------ REGISTRAR TRANSA��O ----------------

		
		// Inseri o grupo no sistema
		getControladorUtil().inserir(grupo);

		/*
		 * Caso o usu�rio tenha informado alguma permiss�opara o grupo inseri as
		 * permiss�es do grupo na tabela GrupoFuncionalidadeOperacao
		 */
		if (colecaoGrupoFuncionalidadeOperacao != null
				&& !colecaoGrupoFuncionalidadeOperacao.isEmpty()) {
			// Cria o iterator das permiss�es
			Iterator iteratorGrupoFuncionalidadeOperacao = colecaoGrupoFuncionalidadeOperacao
					.iterator();

			// La�o para adicionar as todas as permiss�es informadas para o
			// grupo
			while (iteratorGrupoFuncionalidadeOperacao.hasNext()) {
				// Cria o objeto GrupoFuncionalidadeOperacao que vai representar
				// a permiss�o do grupo
				GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidadeOperacao
						.next();

				// Seta o grupo inserido na permiss�o
				grupoFuncionalidadeOperacao.setGrupo(grupo);

				// Cria a chave para a permiss�o do grupo
				GrupoFuncionalidadeOperacaoPK grupoFuncionalidadeOperacaoPK = new GrupoFuncionalidadeOperacaoPK();
				grupoFuncionalidadeOperacaoPK
						.setFuncionalidadeId(grupoFuncionalidadeOperacao
								.getFuncionalidade().getId());
				grupoFuncionalidadeOperacaoPK
						.setOperacaoId(grupoFuncionalidadeOperacao
								.getOperacao().getId());
				grupoFuncionalidadeOperacaoPK
						.setGrupoId(grupoFuncionalidadeOperacao.getGrupo()
								.getId());

				// Seta a chave na permiss�o
				grupoFuncionalidadeOperacao
						.setComp_id(grupoFuncionalidadeOperacaoPK);

				// Inseri a permiss�o do grupo no sistema
				getControladorUtil().inserir(grupoFuncionalidadeOperacao);
			}
		}
	}

	/**
	 * M�todo que atualiza um grupo e seus acessos
	 * 
	 * [UC0279] - Manter Grupo
	 * 
	 * @author Pedro Alexandre
	 * @date 03/07/2006
	 * 
	 * @param grupo
	 * @param grupoFuncionalidadeOperacao
	 */
	public void atualizarGrupo(Grupo grupo,
			Collection colecaoGrupoFuncionalidadeOperacao, Usuario usuarioLogado)
			throws ControladorException {

		
		
		/*
		 * Pesquisa o grupo na base de dados e verifica se o registro n�o foi
		 * atualizado por outro usu�rio durante essa transa��o
		 */
		FiltroGrupo filtroGrupo = new FiltroGrupo();
		filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID,
				grupo.getId()));
		Collection colecaoGrupo = getControladorUtil().pesquisar(filtroGrupo,
				Grupo.class.getSimpleName());
		if (colecaoGrupo != null && !colecaoGrupo.isEmpty()) {
			// Recupera o grupo na base de dados
			Grupo grupoNaBase = (Grupo) colecaoGrupo.iterator().next();

			// [FS0004] - Atualiza��o realizada por outro usu�rio
			if (grupoNaBase.getUltimaAlteracao().after(
					grupo.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
		}

		// ------------ REGISTRAR TRANSA��O ----------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		    Operacao.OPERACAO_ATUALIZAR_GRUPO,
		    grupo.getId(), grupo.getId(),
		    new UsuarioAcaoUsuarioHelper(usuarioLogado,
		    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(grupo);

		// ------------ REGISTRAR TRANSA��O ----------------
		
		/*
		 * Seta a data da ultima atualiza��o do grupo e atualiza os dados do
		 * grupo
		 */
		grupo.setUltimaAlteracao(new Date());
		getControladorUtil().atualizar(grupo);

		/*
		 * Cria o filtro para pesquisar as permiss�es j� cadastradas para o
		 * grupo
		 */
		FiltroGrupoFuncionalidadeOperacao filtroGrupoFuncionalidadeOperacao = new FiltroGrupoFuncionalidadeOperacao();
		filtroGrupoFuncionalidadeOperacao
				.adicionarParametro(new ParametroSimples(
						FiltroGrupoFuncionalidadeOperacao.GRUPO_ID, grupo
								.getId()));
		filtroGrupoFuncionalidadeOperacao.setConsultaSemLimites(true);
		Collection colecaoGrupoFuncionalidadeOperacaoCadastradas = getControladorUtil()
				.pesquisar(filtroGrupoFuncionalidadeOperacao,
						GrupoFuncionalidadeOperacao.class.getSimpleName());

		// Caso exista permiss�es cadastradas para o grupo que est� sendo
		// atualizado
		if (colecaoGrupoFuncionalidadeOperacaoCadastradas != null
				&& !colecaoGrupoFuncionalidadeOperacaoCadastradas.isEmpty() && colecaoGrupoFuncionalidadeOperacao != null) {

			// Cria o iterator das permiss�es cadastradas para o grupo
			Iterator iteratorGrupoFuncionalidadeOperacao = colecaoGrupoFuncionalidadeOperacaoCadastradas
					.iterator();

			// La�o para remover as permiss�es que foram retiradas pelo usu�rio
			// para o grupo
			while (iteratorGrupoFuncionalidadeOperacao.hasNext()) {

				// Recupera a permiss�o do grupo do iterator
				GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidadeOperacao
						.next();

				/*
				 * Caso a permiss�o n�o esteja contida na nova cole��o de
				 * permiss�es registradas pelo usu�rio para atualizar o grupo
				 * Remove as retri��es para essa opera��o e depois remove a
				 * permiss�o para a opera��o
				 */
				if (!colecaoGrupoFuncionalidadeOperacao
						.contains(grupoFuncionalidadeOperacao)) {

					/*
					 * Cria o filtro para pesquisar se existe alguma restri�a�
					 * para essa opera��o para algum usu�rio, Setando o c�digo
					 * do grupo, c�digo da funcionalidade e c�digo da opera��o
					 * 
					 */
					FiltroUsuarioGrupoRestricao filtroUsuarioGrupoRestricao = new FiltroUsuarioGrupoRestricao();
					filtroUsuarioGrupoRestricao
							.adicionarParametro(new ParametroSimples(
									FiltroUsuarioGrupoRestricao.GRUPO_ID,
									grupoFuncionalidadeOperacao.getGrupo()
											.getId()));
					filtroUsuarioGrupoRestricao
							.adicionarParametro(new ParametroSimples(
									FiltroUsuarioGrupoRestricao.FUNCIONALIDADE_ID,
									grupoFuncionalidadeOperacao
											.getFuncionalidade().getId()));
					filtroUsuarioGrupoRestricao
							.adicionarParametro(new ParametroSimples(
									FiltroUsuarioGrupoRestricao.OPERACAO_ID,
									grupoFuncionalidadeOperacao.getOperacao()
											.getId()));

					// Pesquisa as retri��es para a opera��o
					Collection<UsuarioGrupoRestricao> restricoes = getControladorUtil()
							.pesquisar(filtroUsuarioGrupoRestricao,
									UsuarioGrupoRestricao.class.getName());

					/*
					 * Caso exista restri��o para a opera��o remove as
					 * restri��es para depois remover a permiss�o para a
					 * opera��o
					 */
					if (restricoes != null && !restricoes.isEmpty()) {
						// La�o para remover todas as restri��es
						for (UsuarioGrupoRestricao usuarioGrupoRestricao : restricoes) {
							getControladorUtil().remover(usuarioGrupoRestricao);
						}
					}
					// Remove a permiss�o
					getControladorUtil().remover(grupoFuncionalidadeOperacao);
				}
			}
		}

		/*
		 * Caso o usu�rio tenha informado algum acesso para o grupo que est�
		 * sendo atualizado, inseri todos os acessos do grupo informados inserir
		 * na tabela grupo_funcionalidade_operacao
		 */
		if (colecaoGrupoFuncionalidadeOperacao != null
				&& !colecaoGrupoFuncionalidadeOperacao.isEmpty()) {

			// Cria o iterator para as permiss�es do grupo informadas pelo
			// usu�rio
			Iterator iterator = colecaoGrupoFuncionalidadeOperacao.iterator();
			while (iterator.hasNext()) {
				// Recupera a permiss�o do iterator
				GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iterator
						.next();
				grupoFuncionalidadeOperacao.setGrupo(grupo);

				// Cria a chave para a permiss�o
				GrupoFuncionalidadeOperacaoPK grupoFuncionalidadeOperacaoPK = new GrupoFuncionalidadeOperacaoPK();
				grupoFuncionalidadeOperacaoPK
						.setFuncionalidadeId(grupoFuncionalidadeOperacao
								.getFuncionalidade().getId());
				grupoFuncionalidadeOperacaoPK
						.setOperacaoId(grupoFuncionalidadeOperacao
								.getOperacao().getId());
				grupoFuncionalidadeOperacaoPK
						.setGrupoId(grupoFuncionalidadeOperacao.getGrupo()
								.getId());

				// Seta a chave composta na permiss�o
				grupoFuncionalidadeOperacao
						.setComp_id(grupoFuncionalidadeOperacaoPK);

				// Caso a permiss�o ainda n�o esteja cadastrada para o grupo
				// inseri a permiss�o para o grupo
				if (!colecaoGrupoFuncionalidadeOperacaoCadastradas
						.contains(grupoFuncionalidadeOperacao)) {
					getControladorUtil().inserir(grupoFuncionalidadeOperacao);
				}
			}
		}
	}

	/**
	 * Remove os grupos selecionados na tela de manter grupo e os
	 * relacionamentos existentes para o grupo(remove da tabela
	 * GrupoFuncionalidadeOperacao).
	 * 
	 * [UC0279] - Manter Grupo
	 * 
	 * @author Pedro Alexandre
	 * @date 29/06/2006
	 * 
	 * @param idsGrupos
	 * @throws ControladorException
	 */
	public void removerGrupo(String[] idsGrupos, Usuario usuarioLogado) throws ControladorException {
		// La�o para remover todos os grupos informados
		for (int i = 0; i < idsGrupos.length; i++) {
			// Verifica se o grupo existe realmente na base
			FiltroGrupo filtroGrupo = new FiltroGrupo();
			filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID,
					idsGrupos[i]));
			Collection colecaoGrupo = getControladorUtil().pesquisar(
					filtroGrupo, Grupo.class.getSimpleName());

			/*
			 * Caso a pesquisa tenha retornado o grupo remove todas as
			 * permiss�es existentes para o grupoe depois remove o grupo do
			 * sistema
			 */
			if (colecaoGrupo != null && !colecaoGrupo.isEmpty()) {
				// Recupera o grupo da cole��o
				Grupo grupo = (Grupo) colecaoGrupo.iterator().next();

				// Pesquisa todos os acessos do grupo
				FiltroGrupoFuncionalidadeOperacao filtroGrupoFuncionalidadeOperacao = new FiltroGrupoFuncionalidadeOperacao();
				filtroGrupoFuncionalidadeOperacao
						.adicionarParametro(new ParametroSimples(
								FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
								grupo.getId()));
				Collection colecaoGrupoFuncionalidadeOperacao = getControladorUtil()
						.pesquisar(
								filtroGrupoFuncionalidadeOperacao,
								GrupoFuncionalidadeOperacao.class
										.getSimpleName());

				// Caso exista acessos para o grupo(na tabela
				// GrupoFuncinalidadeOperacao) remove todos os
				// acessos do grupo antes de remover o grupo
				if (colecaoGrupoFuncionalidadeOperacao != null
						&& !colecaoGrupoFuncionalidadeOperacao.isEmpty()) {
					// Cria o iterator para remover todos os acessos do grupo
					Iterator iteratorGrupoFuncionalidadeOperacao = colecaoGrupoFuncionalidadeOperacao
							.iterator();

					// La�o para remover todos os acessos do grupo
					while (iteratorGrupoFuncionalidadeOperacao.hasNext()) {
						// Recupera o acesso do grupo da cole��o
						GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidadeOperacao
								.next();

						// Remove o acesso do grupo
						getControladorUtil().remover(
								grupoFuncionalidadeOperacao);
					}
				}
				
				
				//------------ REGISTRAR TRANSA��O ----------------
		        
			    RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_REMOVER_GRUPO,
					grupo.getId(), grupo.getId(),
				    new UsuarioAcaoUsuarioHelper(usuarioLogado,
				    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			        
			    Operacao operacao = new Operacao();
			    operacao.setId(Operacao.OPERACAO_REMOVER_GRUPO);
			
			    OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			    operacaoEfetuada.setOperacao(operacao);
			    registradorOperacao.registrarOperacao(grupo);
		    	//------------ REGISTRAR TRANSA��O ----------------
				
				// Remove o grupo selecionado
				getControladorUtil().remover(grupo);
			}
		}
	}

	/**
	 * Permite inserir uma ResolucaoDiretoria
	 * 
	 * [UC0217] Inserir Resolu��o de Diretoria
	 * 
	 * @author Rafael Corr�a
	 * @date 30/03/2006
	 * 
	 */
	public Integer inserirSituacaoUsuario(UsuarioSituacao usuarioSituacao)
			throws ControladorException {

		FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();
		filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(
				FiltroUsuarioSituacao.DESCRICAO, usuarioSituacao
						.getDescricaoUsuarioSituacao()));

		Collection colecaoResolucaoDiretoria = getControladorUtil().pesquisar(
				filtroUsuarioSituacao, UsuarioSituacao.class.getName());

		if (colecaoResolucaoDiretoria != null
				&& !colecaoResolucaoDiretoria.isEmpty()) {
			throw new ControladorException(
					"atencao.desc_ja_existente_situacao_usuario", null,
					usuarioSituacao.getDescricaoUsuarioSituacao());
		}

		usuarioSituacao.setUltimaAlteracao(new Date());
		Integer id = (Integer) getControladorUtil().inserir(usuarioSituacao);

		return id;
	}

	/**
	 * Inseri uma opera��o e seus relacionamentos com as tabelas se existir
	 * 
	 * [UC0284]Inserir Opera��o
	 * 
	 * @author Pedro Alexandre
	 * @date 08/05/2006
	 * 
	 * @param operacao
	 * @param colecaoOperacaoTabela
	 * @throws ControladorException
	 */
	public void inserirOperacao(Operacao operacao,
			Collection<Tabela> colecaoOperacaoTabela, Usuario usuarioLogado)
			throws ControladorException {

		// [FS0001 Verificar exist�ncia da descri��o]
		// Cria o filtro de opera��o para verificar se j� existe uma opera��o
		// cadastrada
		// com a descri��o informada
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao
				.adicionarParametro(new ParametroSimples(
						FiltroOperacao.DESCRICAO, operacao.getDescricao()
								.toUpperCase()));
		Collection colecaoOperacao = getControladorUtil().pesquisar(
				filtroOperacao, Operacao.class.getName());

		// Caso exista opera��o cadastrada com a opera��o informada
		// levanta a exce��o para o usu�rio
		if (colecaoOperacao != null && !colecaoOperacao.isEmpty()) {
			throw new ControladorException(
					"atencao.descricao.operacao.ja.existente", null, operacao
							.getDescricao());
		}

		// [FS0004 - Verificar exist�ncia da funcionalidade]
		// Cria o filtro de funcionalidade para verificar se existe a
		// funcionalidade informada
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidade.ID, operacao.getFuncionalidade().getId()));
		Collection colecaoFuncionalidade = getControladorUtil().pesquisar(
				filtroFuncionalidade, Funcionalidade.class.getName());

		// Caso a funcionalidade informada n�o esteja cadastrada no sistema
		// levanta uma exce��o para o cliente
		if (colecaoFuncionalidade == null || colecaoFuncionalidade.isEmpty()) {
			throw new ControladorException(
					"atencao.funcionalidade.inexistente", null, operacao
							.getDescricao());
		}

		// Cria a vari�vel que vai aramzenar o tipo da opera��o
		OperacaoTipo operacaoTipo = null;

		// Caso o tipo da opera��o tenha sido informada
		// pesquisa o tipo da opera��o no sistema
		// Caso contr�rio levanta uma exce��o indicando que o tipo da opera��o
		// n�o foi informada
		if (operacao.getOperacaoTipo() != null) {
			FiltroOperacaoTipo filtroOperacaoTipo = new FiltroOperacaoTipo();
			filtroOperacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTipo.ID, operacao.getOperacaoTipo().getId()));
			Collection colecaoOperacaoTipo = getControladorUtil().pesquisar(
					filtroOperacaoTipo, OperacaoTipo.class.getName());

			// Caso o tipo da opera��o informada n�o exista
			// levanta uma exce��o indicando que o tipo da opera��o n�o existe
			// Caso contr�rio recupera o tipo da opera��o da cole��o pesquisada
			if (colecaoOperacaoTipo == null || colecaoOperacaoTipo.isEmpty()) {
				throw new ControladorException(
						"atencao.operacao_tipo.inexistente", null, ""
								+ operacao.getOperacaoTipo().getId());
			}
			operacaoTipo = (OperacaoTipo) Util
					.retonarObjetoDeColecao(colecaoOperacaoTipo);
		} else {
			throw new ControladorException(
					"atencao.operacao_tipo.nao.informado", null);
		}

		// Caso o tipo da opera��o informada seja pesquisar
		// verifica o preenchimento do argumento de pesquisa
		if (operacaoTipo.getId().intValue() == OperacaoTipo.PESQUISAR) {

			// Caso o argumento de pesquisa n�o tenha sido informado
			// levanta uma exce��o indicando que o argumento de pesquisa n�o foi
			// informado
			if (operacao.getTabelaColuna() == null) {
				throw new ControladorException(
						"atencao.argumento_pesquisa.nao.informado", null);
			}
			// [FS0005 - Verificar exist�ncia do argumento de pesquisa]
			// Cria o filtro para pesqusiar o argumento de pesquisa
			// informado
			FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
			filtroTabelaColuna.adicionarParametro(new ParametroSimples(
					FiltroTabelaColuna.ID, operacao.getTabelaColuna()
							.getId()));

			// Pesquisa o argumento de pesquisa
			Collection colecaoTabelaColuna = getControladorUtil()
					.pesquisar(filtroTabelaColuna,
							TabelaColuna.class.getName());

			// Caso o argumento de pesquisa n�o esteja cadastrado
			// levanta uma exce��o indicando que o argumento de pesquisa n�o
			// existe
			// Caso contr�rio recupera o argumento de pesquisa da cole��o
			if (colecaoTabelaColuna == null
					|| colecaoTabelaColuna.isEmpty()) {
				throw new ControladorException(
						"atencao.argumento_pesquisa.inexistente", null);
			}
			// [FS0011 - Verificar argumento de pesquisa]
			TabelaColuna argumentoPesquisa = (TabelaColuna) Util
					.retonarObjetoDeColecao(colecaoTabelaColuna);

			// Caso o argumento de pesquisa informado n�o seja chave
			// prim�ria
			// levanta uma exce�a� indicando que o argumento de pesquisa
			// n�o � chave prim�ria da tabela
			if (argumentoPesquisa.getIndicadorPrimaryKey() == ConstantesSistema.NAO) {
				throw new ControladorException(
						"atencao.argumento_pesquisa.nao.chave.primaria",
						null);
			}

			// Cria o filtro para verificar se j� existe opera��o com o
			// argumento de pesquisa informado
			FiltroOperacao filtroOperacaoComArgumentoPesquisa = new FiltroOperacao();
			filtroOperacaoComArgumentoPesquisa
					.adicionarParametro(new ParametroSimples(
							FiltroOperacao.TABELA_COLUNA_ID,
							argumentoPesquisa.getId()));
			Collection colecaoOperacaoComArgumentoPesquisa = getControladorUtil()
					.pesquisar(filtroOperacaoComArgumentoPesquisa,
							Operacao.class.getName());

			// Caso j� existe opera��o com o argumento de pesquisa
			// informado
			// levanta uma exce��o indicando que j� existe uma opera��o
			// com o
			// argumento de pesquisa informado
			if (colecaoOperacaoComArgumentoPesquisa != null
					&& !colecaoOperacaoComArgumentoPesquisa.isEmpty()) {
				Operacao operacaoComArgumentoPesquisa = (Operacao) Util
						.retonarObjetoDeColecao(colecaoOperacaoComArgumentoPesquisa);
				throw new ControladorException(
						"atencao.argumento_pesquisa.ja.associado",
						null, operacaoComArgumentoPesquisa
								.getDescricao());
			}

		} else {
			// Caso o tipo de opera��o n�o seja "pesquisar"
			if (operacaoTipo.getIndicadorAtualiza() == ConstantesSistema.SIM) {

				//	Caso o usu�rio n�o tenha informado nenhuma tabela
				//	if (colecaoOperacaoTabela == null
				//		|| colecaoOperacaoTabela.isEmpty()) {
				//			throw new ControladorException(
				//				"atencao.tabela.nao.informada", null);
				//	}

				// Caso o usu�rio n�o tenha informado a opera��o de pesquisa
				// levanta uma exce��o
				// indicando que a opera��o de pesquisa n�o foi informada
				// Caso contr�rio verifica a exist�ncia da opera��o de pesquisa
				if (operacao.getIdOperacaoPesquisa() == null) {
					// throw new
					// ControladorException("atencao.operacao_pesquisa.nao.informado",
					// null);
				} else {
					// [FS0007 - Verificar exist�ncia da opera��o]
					// Cria o filtro para pesquisar a opera��o de pesquisa
					// informada
					FiltroOperacao filtroOperacaoPesquisa = new FiltroOperacao();
					filtroOperacaoPesquisa
							.adicionarParametro(new ParametroSimples(
									FiltroOperacao.ID, operacao
											.getIdOperacaoPesquisa().getId()));
					Collection colecaoOperacaoPesquisa = getControladorUtil()
							.pesquisar(filtroOperacaoPesquisa,
									Operacao.class.getName());

					// Caso a opera��o de pesquisa n�o esteja cadastrada
					// levanta uma exce��o indicando que a opera��o de pesquisa
					// n�o existe
					if (colecaoOperacaoPesquisa == null
							|| colecaoOperacaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.operacao_pesquisa.inexistente", null);
					}
				}
			}
		}

		// ------------ REGISTRAR TRANSA��O ----------------

		// RETIRAR ISSO DEPOIS
		// *******************************************************
		usuarioLogado = Usuario.USUARIO_TESTE;
		// ***************************************************************************

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_OPERACAO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacaoRegistrarTransacao = new Operacao();
		operacaoRegistrarTransacao.setId(Operacao.OPERACAO_OPERACAO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacaoRegistrarTransacao);

		operacao.setOperacaoEfetuada(operacaoEfetuada);
		operacao.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------

		// Inseri a opera��o no sistema e recupera o id gerado
		Integer idOperacao = (Integer) getControladorUtil().inserir(operacao);

		// Seta o id no objeto
		operacao.setId(idOperacao);

		// Caso exista a cole��o de tabela
		// Inseri todos os relacionamento entre a opera��o inserida e as tabelas
		// informadas
		if (colecaoOperacaoTabela != null) {
			for (Tabela tabela : colecaoOperacaoTabela) {
				OperacaoTabela operacaoTabela = new OperacaoTabela(
						new OperacaoTabelaPK(idOperacao, tabela.getId()));

				operacaoTabela.setOperacaoEfetuada(operacaoEfetuada);
				operacaoTabela.adicionarUsuario(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(operacaoTabela);

				this.getControladorUtil().inserir(operacaoTabela);
			}
		}
	}

	/**
	 * Permite inserir uma ResolucaoDiretoria
	 * 
	 * [UC0297] Inserir Abrang�ncia Usuario
	 * 
	 * @author Thiago Ten�rio
	 * @date 30/03/2006
	 * 
	 */
	public Integer inserirAbrangenciaUsuario(
			UsuarioAbrangencia usuarioAbrangencia) throws ControladorException {

		FiltroAbrangenciaUsuario filtroAbrangenciaUsuario = new FiltroAbrangenciaUsuario();
		filtroAbrangenciaUsuario.adicionarParametro(new ParametroSimples(
				FiltroAbrangenciaUsuario.DESCRICAO, usuarioAbrangencia
						.getDescricao()));

		filtroAbrangenciaUsuario.adicionarParametro(new ParametroSimples(
				FiltroAbrangenciaUsuario.DESCRICAO_ABREVIADA,
				usuarioAbrangencia.getDescricaoAbreviada()));

		if (usuarioAbrangencia.getUsuarioAbrangenciaSuperior() != null
				&& !usuarioAbrangencia.getUsuarioAbrangenciaSuperior().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			filtroAbrangenciaUsuario.adicionarParametro(new ParametroSimples(
					FiltroAbrangenciaUsuario.ABRANGENCIA_SUPERIOR,
					usuarioAbrangencia.getUsuarioAbrangenciaSuperior()));

		}

		filtroAbrangenciaUsuario.adicionarParametro(new ParametroSimples(
				FiltroAbrangenciaUsuario.INDICADOR_USO, usuarioAbrangencia
						.getIndicadorUso()));

		// Aqui
		Collection colecaoUsuarioAbrangencia = getControladorUtil().pesquisar(
				filtroAbrangenciaUsuario, UsuarioAbrangencia.class.getName());

		if (colecaoUsuarioAbrangencia != null
				&& !colecaoUsuarioAbrangencia.isEmpty()) {
			throw new ControladorException(
					"atencao.numero_resolucao_ja_existente");
		}

		usuarioAbrangencia.setUltimaAlteracao(new Date());
		Integer id = (Integer) getControladorUtil().inserir(usuarioAbrangencia);

		return id;
	}

	/**
	 * [UC0294] Manter Situa��o Usu�rio [] Atualizar Situa��o do Usuario Metodo
	 * que atualiza a Situa��o Usuario
	 * 
	 * 
	 * @author Thiago Ten�rio
	 * @date 25/05/2006
	 * 
	 * @param Situa��o
	 *            Usu�rio
	 * @throws ControladorException
	 */

	public void atualizarSituacaoUsuario(UsuarioSituacao usuarioSituacao,
			Collection colecaoUsuarioSituacao) throws ControladorException {

		// Verifica se todos os campos obrigatorios foram preenchidos

		if ((usuarioSituacao.getDescricaoUsuarioSituacao() == null || usuarioSituacao
				.getDescricaoUsuarioSituacao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (usuarioSituacao.getDescricaoAbreviada() == null || usuarioSituacao
						.getDescricaoAbreviada().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (usuarioSituacao.getId() == null || usuarioSituacao.getId()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (usuarioSituacao.getIndicadorUso() == null || usuarioSituacao
						.getIndicadorUso().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			throw new ControladorException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Verifica se o campo Descri��o foi preenchido

		if (usuarioSituacao.getDescricaoUsuarioSituacao() == null
				|| usuarioSituacao.getDescricaoUsuarioSituacao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Descri��o");
		}

		// Verifica se o campo DescricaoAbreviada foi preenchido

		if (usuarioSituacao.getDescricaoAbreviada() == null
				|| usuarioSituacao.getDescricaoAbreviada().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Descri��o Abreviada");
		}

		// Verifica se o campo IndicadorUso foi preenchido
		if (usuarioSituacao.getIndicadorUso() == null
				|| usuarioSituacao.getIndicadorUso().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Indicador de Uso");
		}

		// Verifica se o campo IndicadorUsoSistema preenchido

		if (usuarioSituacao.getIndicadorUsoSistema() == null
				|| usuarioSituacao.getIndicadorUsoSistema().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Indicador de Uso Exclusivo do Sistema");
		}

		// [FS0003] - Atualiza��o realizada por outro usu�rio
		FiltroSituacaoUsuario filtroSituacaoUsuario = new FiltroSituacaoUsuario();
		filtroSituacaoUsuario.adicionarParametro(new ParametroSimples(
				FiltroSituacaoUsuario.ID, usuarioSituacao.getId()));

		Collection colecaoUsuarioSituacaoBase = getControladorUtil().pesquisar(
				filtroSituacaoUsuario, UsuarioSituacao.class.getName());

		if (colecaoUsuarioSituacaoBase == null
				|| colecaoUsuarioSituacaoBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		UsuarioSituacao usuarioSituacaoBase = (UsuarioSituacao) colecaoUsuarioSituacaoBase
				.iterator().next();

		if (usuarioSituacaoBase.getUltimaAlteracao().after(
				usuarioSituacaoBase.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		usuarioSituacao.setUltimaAlteracao(new Date());

		getControladorUtil().atualizar(usuarioSituacao);

	}

	/**
	 * [UC0298] Manter Abrang�ncia Usu�rio [] Atualizar Abrang�ncia do Usuario
	 * Metodo que atualiza a Situa��o Usuario
	 * 
	 * 
	 * @author Thiago Ten�rio
	 * @date 25/05/2006
	 * 
	 * @param Abrang�ncia
	 *            Usu�rio
	 * @throws ControladorException
	 */

	public void atualizarAbrangenciaUsuario(
			UsuarioAbrangencia usuarioAbrangencia) throws ControladorException {

		// Verifica se todos os campos obrigatorios foram preenchidos

		if ((usuarioAbrangencia.getDescricao() == null || usuarioAbrangencia
				.getDescricao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (usuarioAbrangencia.getUsuarioAbrangenciaSuperior() == null || usuarioAbrangencia
						.getDescricaoAbreviada().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (usuarioAbrangencia.getDescricaoAbreviada() == null || usuarioAbrangencia
						.getDescricaoAbreviada().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (usuarioAbrangencia.getId() == null || usuarioAbrangencia
						.getId().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (usuarioAbrangencia.getIndicadorUso() == null || usuarioAbrangencia
						.getIndicadorUso().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			throw new ControladorException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Verifica se o campo Descri��o foi preenchido

		if (usuarioAbrangencia.getDescricao() == null
				|| usuarioAbrangencia.getDescricao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Descri��o");
		}

		// Verifica se o campo DescricaoAbreviada foi preenchido

		if (usuarioAbrangencia.getDescricaoAbreviada() == null
				|| usuarioAbrangencia.getDescricaoAbreviada().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Descri��o Abreviada");
		}

		// Verifica se o campo IndicadorUso foi preenchido
		if (usuarioAbrangencia.getIndicadorUso() == null
				|| usuarioAbrangencia.getIndicadorUso().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Indicador de Uso");
		}

		// Verifica se o campo AbrangenciaSuperior foi preenchido
		if (usuarioAbrangencia.getUsuarioAbrangenciaSuperior() == null
				|| usuarioAbrangencia.getUsuarioAbrangenciaSuperior().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Indicador de Uso");
		}

		// Verifica se o campo Codigo foi preenchido

		if (usuarioAbrangencia.getId() == null
				|| usuarioAbrangencia.getId().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Indicador de Uso Exclusivo do Sistema");
		}

		// [FS0003] - Atualiza��o realizada por outro usu�rio
		FiltroAbrangenciaUsuario filtroAbrangenciaUsuario = new FiltroAbrangenciaUsuario();
		filtroAbrangenciaUsuario.adicionarParametro(new ParametroSimples(
				FiltroAbrangenciaUsuario.ID, usuarioAbrangencia.getId()));

		Collection colecaoUsuarioAbrangenciaBase = getControladorUtil()
				.pesquisar(filtroAbrangenciaUsuario,
						UsuarioAbrangencia.class.getName());

		if (colecaoUsuarioAbrangenciaBase == null
				|| colecaoUsuarioAbrangenciaBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		UsuarioAbrangencia usuarioAbrangenciaBase = (UsuarioAbrangencia) colecaoUsuarioAbrangenciaBase
				.iterator().next();

		if (usuarioAbrangencia.getUltimaAlteracao().after(
				usuarioAbrangenciaBase.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		usuarioAbrangencia.setUltimaAlteracao(new Date());

		getControladorUtil().atualizar(usuarioAbrangencia);

	}

	/**
	 * Constroi um menu de acesso de acordo com as permiss�es que o usu�rio que
	 * est� logado no sistema conteme monta o link de retorno com o link
	 * informado.
	 * 
	 * [UC0277] - Construir menu de acesso
	 * 
	 * @author Pedro Alexandre
	 * @date 10/07/2006
	 * 
	 * @param usuarioLogado
	 * @param linkRetorno
	 * @return
	 * @throws ControladorException
	 */
	public String construirMenuAcesso(Usuario usuarioLogado, String linkRetorno,Integer idGrupo)
			throws ControladorException {

		Collection<Funcionalidade> colecaoFuncionalidadesPermitidas = new ArrayList();

		// Cria a colec�o que vai armazenar os m�dulos aos que o usu�rio tem
		// alguma funcionalidade cadastrada
		Collection modulos = new ArrayList();

		// Inicializa a vari�vel que vai definir os identicadores dos n�s
		// principais da arvore
		int contador = 0;

		// Vari�vel que vai armazenar o c�digo do n� principal temporariamente
		// para incluir as funcionalidades do m�dulo atual
		int temp;

		// Cria a primeira parte da arvore
		StringBuffer menu = new StringBuffer();
		menu.append("<link rel=\"StyleSheet\" href=\"/gsan/css/dtree.css\" type=\"text/css\" /><script type=\"text/javascript\" src=\"/gsan/javascript/dtree.js\"></script>\n");
		menu.append("<div class=\"dtree\">\n");
		menu.append("<script><!--\n p = new dTree('p');\n");
		menu.append("p.add(0,-1,'Funcionalidades');\n");

		Collection colecaoGruposUsuario = 
			getControladorUsuario().pesquisarGruposUsuario(usuarioLogado.getId());

		if (!usuarioLogado.getUsuarioTipo().getId().equals(UsuarioTipo.USUARIO_TIPO_ADMINISTRADOR)) {

			/*
			 * Pesquisa os grupos do usu�rio logado e seta esses grupos no
			 * filtro para a pesquisa das funcionalidade que os grupos tem
			 * acesso.
			 */
			Iterator iterator = colecaoGruposUsuario.iterator();
			FiltroGrupoFuncionalidadeOperacao filtroGrupoFuncionalidadeOperacao = 
				new FiltroGrupoFuncionalidadeOperacao();
			
			filtroGrupoFuncionalidadeOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade.modulo");
			filtroGrupoFuncionalidadeOperacao.adicionarParametro(
				new ParametroSimples(
					FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_INDICADOR_PONTO_ENTRADA,
					ConstantesSistema.SIM));
			filtroGrupoFuncionalidadeOperacao.setConsultaSemLimites(true);

			// Inseri os grupos do usu�rio no filtro
			while (iterator.hasNext()) {
				Grupo grupoUsuario = (Grupo) iterator.next();
				
				filtroGrupoFuncionalidadeOperacao.adicionarParametro(
					new ParametroSimples(
						FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
						grupoUsuario.getId(),
						FiltroParametro.CONECTOR_OR));
			}

			/*
			 * Pesquisa as funcionalidades as quais o usu�rio tem acesso atrav�s
			 * dos grupos a que o usu�rio pertence.
			 */
			Collection<GrupoFuncionalidadeOperacao> permissoes = 
				getControladorUtil().pesquisar(filtroGrupoFuncionalidadeOperacao,
					GrupoFuncionalidadeOperacao.class.getName());
			
			Iterator<GrupoFuncionalidadeOperacao> iteratorPermissoes = permissoes.iterator();

			/*
			 * Retira as funcionalidades repetidas cadastradas para o usu�rio
			 * que est� logado recupera tamb�m os m�dulos para ser gerada a
			 * arvore
			 */
			while (iteratorPermissoes.hasNext()) {
				
				// Recupera a funcionalidade
				GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = iteratorPermissoes.next();
				
				Funcionalidade funcionalidadePermitida = grupoFuncionalidadeOperacao.getFuncionalidade();

				// Caso a funcionalidade ainda n�o esteja na cole��o de
				// funcionalidades de acesso do usu�rio
				// adiciona a funcionalidade a cole��o
				if (!colecaoFuncionalidadesPermitidas.contains(funcionalidadePermitida)) {
					colecaoFuncionalidadesPermitidas.add(funcionalidadePermitida);
				}

				// Recupera o m�dulo da funcionalidade
				Modulo moduloPermitido = funcionalidadePermitida.getModulo();

				// Caso o modulo ainda n�o esteja na cole��o de modulos de
				// acesso do usu�rio
				// adiciona o modulo a cole��o
				if (!modulos.contains(moduloPermitido)) {
					modulos.add(moduloPermitido);
				}
			}
		} else {

			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
			filtroFuncionalidade.adicionarParametro(
				new ParametroSimples(
					FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA,
					ConstantesSistema.SIM));
			
			filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("modulo");
			filtroFuncionalidade.setConsultaSemLimites(true);

			Collection<Funcionalidade> permissoes = 
				getControladorUtil().pesquisar(filtroFuncionalidade,Funcionalidade.class.getName());
			Iterator<Funcionalidade> iteratorPermissoes = permissoes.iterator();

			while (iteratorPermissoes.hasNext()) {
				Funcionalidade funcionalidadePermitida = iteratorPermissoes.next();

				colecaoFuncionalidadesPermitidas.add(funcionalidadePermitida);

				// Recupera o m�dulo da funcionalidade
				Modulo moduloPermitido = funcionalidadePermitida.getModulo();

				// Caso o modulo ainda n�o esteja na cole��o de modulos de
				// acesso do usu�rio
				// adiciona o modulo a cole��o
				if (!modulos.contains(moduloPermitido)) {
					modulos.add(moduloPermitido);
				}
			}
		}

		/*
		 * Inicio do c�digo din�mico para cria a arvore de acesso
		 */

		// Cria o iterator dos modulos do usu�rio logado
		Iterator iteratorModulos = modulos.iterator();

		/*
		 * La�o para incluir todos os modulos na �rvore e as suas
		 * funcionalidades
		 */
		while (iteratorModulos.hasNext()) {

			// A vari�vel temp vai conter o valor do contador
			temp = ++contador;

			// Recupera o modulo do iterator
			Modulo modulo = (Modulo) iteratorModulos.next();

			// Inseri o n� do modulo na �rvore
			menu.append("p.add(" + temp + "," + "0" + ",'" + modulo.getDescricaoModulo() + "');\n");

			// Coloca a cole��o de funcionalidades permitidas para o usu�rio que
			// est� logado no iterator
			Iterator iteratorFuncionalidadesPermitidas = colecaoFuncionalidadesPermitidas.iterator();

			// La�o para incluir todas as funcionalidades na �rvore
			while (iteratorFuncionalidadesPermitidas.hasNext()) {

				// Incrementa o contador
				++contador;

				// Pega a funcionalidade do iterator
				Funcionalidade funcionalidade = (Funcionalidade) iteratorFuncionalidadesPermitidas.next();

				// Caso a funcionalidade perten�a ao modulo atual
				// inseri a funcionalidade na arvor� dentro do n� do m�dulo
				if (modulo.getId().equals(funcionalidade.getModulo().getId()) && 
					funcionalidade.getIndicadorPontoEntrada().equals(ConstantesSistema.SIM)) {

					FiltroGrupoFuncionalidadeOperacao filtroFuncionalidadeCadastradaParaUsuario = 
						new FiltroGrupoFuncionalidadeOperacao();
					
					filtroFuncionalidadeCadastradaParaUsuario.adicionarParametro(
						new ParametroSimples(
							FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_ID,
							funcionalidade.getId()));
					
					filtroFuncionalidadeCadastradaParaUsuario.setConsultaSemLimites(true);

					// Inseri os grupos do usu�rio no filtro
					if (idGrupo != null) {
						
						filtroFuncionalidadeCadastradaParaUsuario.adicionarParametro(
							new ParametroSimples(
								FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
								idGrupo));
					}

					Collection funcionlidadeCadastradaParaUsuario = 
						getControladorUtil().pesquisar(filtroFuncionalidadeCadastradaParaUsuario,
								GrupoFuncionalidadeOperacao.class.getName());

					if (funcionlidadeCadastradaParaUsuario == null || 
						funcionlidadeCadastradaParaUsuario.isEmpty()) {
						
						menu.append("p.add(" + contador + "," + temp + ",'"
								+ funcionalidade.getDescricao() + "','"
								+ linkRetorno + "&codigoFuncionalidade="
								+ funcionalidade.getId() + "');\n");
					} else {
						menu.append("p.add(" + contador + "," + temp + ",'"
								+ funcionalidade.getDescricao() + "','"
								+ linkRetorno + "&codigoFuncionalidade="
								+ funcionalidade.getId()
								+ "','','','','check.gif');\n");

					}
				}
			}
		}
		// Fim do c�digo din�mico

		/*
		 * Parte final da arvore de acesso
		 */
		menu.append("p.draw();\n//--></script>\n");
		menu.append("</div>");

		// Retorna o javascript que monta a arvore de acesso
		return menu.toString();
	}

	/**
	 * Met�do respons�vel por validar o login e senha do usu�rio, verificando se
	 * o usu�rio existe no sistema.
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Pedro Alexandre
	 * @date 04/07/2006
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws ControladorException
	 */
	public Usuario validarUsuario(String login, String senha)
			throws ControladorException {
		// Vari�vel que vai armazenar o usu�rio logado
		Usuario retorno = null;

		// Cria o filtro de usu�rio
		FiltroUsuario filtroUsuario = new FiltroUsuario();

		// Busca o usu�rio por senha e login
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, login));
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidadeElo");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidade");

		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("usuarioSituacao");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioTipo");
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional.unidadeTipo");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioAbrangencia");

		// filtroUsuario.adicionarParametro(new
		// ParametroSimples(FiltroUsuario.SENHA,senha));
		try {
			// Criptografa a senha para compar�-la no banco de dados
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.SENHA, Criptografia.encriptarSenha(senha)));

		} catch (ErroCriptografiaException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.criptografia.senha");
		}

		// Faz a pesquisa
		Collection usuarioEncontrado = getControladorUtil().pesquisar(
				filtroUsuario, Usuario.class.getName());

		// Caso tenha encontrad o usu�rio no sistema com o login e a senha
		// informados
		// retorna o usu�rio para o casode uso que chamou a fun��o
		if (!usuarioEncontrado.isEmpty()) {
			retorno = (Usuario) usuarioEncontrado.iterator().next();
		}

		// Retorna o usu�rio encontrado ou nulo se n�o encontrar
		return retorno;
	}

	/**
	 * Met�do respons�vel por registrar o acesso do usu�rio incrementando o n�
	 * de acessos e atualizando a data do ultimo acesso do usu�rio.
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Pedro Alexandre
	 * @date 04/07/2006
	 * 
	 * @param usuario
	 * @throws ControladorException
	 */
	public void registrarAcessoUsuario(Usuario usuario)
			throws ControladorException {
		// Seta o valor um para o n� de acessos
		int numeroAcesso = 1;

		// Caso n�o seja a primeira vez que o usu�rio tenha acessado o sistema
		// incrementa o n� de acesso + 1
		if (usuario.getNumeroAcessos() != null) {
			numeroAcesso = (usuario.getNumeroAcessos().intValue() + numeroAcesso);
		}

		// Atualiza o n� de acessos do usu�rio
		usuario.setNumeroAcessos(new Integer(numeroAcesso));

		// Atualiza a data do �ltimo acesso do usu�rio
		Date data = new Date();
		usuario.setUltimoAcesso(data);

		// Chama o met�do para atualizar o usu�rio
		try {
			this.repositorioAcesso.atualizarRegistrarAcessoUsuario(usuario);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Met�do respons�vel por criar a arvore do menu com todas as permiss�es do
	 * usu�rio de acordo com os grupos que o usu�rio pertence.
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Pedro Alexandre
	 * @date 04/07/2006
	 * 
	 * @param permissoesUsuario
	 * @return
	 * @throws ControladorException
	 */
	public FuncionalidadeCategoria pesquisarArvoreFuncionalidades(
			Collection permissoesUsuario) throws ControladorException {

		// Cria a cole��o que vai armazenar as funcionalidade permitidas para o
		// usu�rio acessar
		Collection funcionalidadesPermitidasAcesso = new ArrayList();

		// Obt�m a lista de todas as funcionalidades do sistema
		Collection funcionalidades = null;

		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(
			new ParametroSimples(
				FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA,
				ConstantesSistema.SIM));
		
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeCategoria");
		
		filtroFuncionalidade.setConsultaSemLimites(true);

		// Pesquisa todas as funcionalidades cadastradas no sistema
		funcionalidades = getControladorUtil().pesquisar(filtroFuncionalidade,
				Funcionalidade.class.getName());

		// Cria o iterator das funcionalidades cadastradas no sistema
		Iterator iteratorFuncionalidadesPermissoes = funcionalidades.iterator();

		// La�o para criar o menu com as funcionalidades permitidas para o
		// usu�rio
		while (iteratorFuncionalidadesPermissoes.hasNext()) {

			// Recupera a funcionalidade do iterator
			Funcionalidade funcionalidade = (Funcionalidade) iteratorFuncionalidadesPermissoes.next();

			// Usa a cole��o de permiss�es para eliminar as funcionalidades que
			// o usu�rio n�o
			// pode acessar
			Iterator iteratorPermissoes = permissoesUsuario.iterator();

			while (iteratorPermissoes.hasNext()) {
				GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorPermissoes.next();
				GrupoFuncionalidadeOperacaoPK chavePermissao = grupoFuncionalidadeOperacao.getComp_id();

				// Verifica se a funcionalidade tem o mesmo id da funcionalidade
				// e representa a mesma opera��o da permiss�o
				// para verificar se o usu�rio tem acesso
				if (funcionalidade.getId().equals(chavePermissao.getFuncionalidadeId())) {

					
					// A permiss�o foi encontrada para esta funcionalidade e a
					// mesma entra na lista das permitidas
					funcionalidadesPermitidasAcesso.add(funcionalidade);
				}
			}
		}
		

		// Primeira Funcionalidade da arvore
		FuncionalidadeCategoria arvorePastas = buscarArvorePastas(null);
		
		FuncionalidadeCategoria menu = new FuncionalidadeCategoria();
		menu.setNumeroOrdemMenu(0L);
		menu.setId(0);
		
		HashSet elementos = new HashSet();
		elementos.add(arvorePastas);
		
		menu.setElementos(elementos);
		
		adicionarFuncionalidadesPasta(menu, funcionalidadesPermitidasAcesso);
		
		
		
		return menu;
	}
	
	/**
	 * Met�do respons�vel montar os filhos a partir do pai informado na arvore
	 * recursivamente  
	 * 
	 * @author Rafael Pinto
	 * @date 12/09/2008
	 * 
	 * @param arvorePastas
	 * @param funcionalidadesPermitidasAcesso
	 * 
	 * @return void
	 * @throws ControladorException
	 */	
	private boolean adicionarFuncionalidadesPasta(FuncionalidadeCategoria arvorePastas, 
		Collection funcionalidadesPermitidasAcesso) {
		
		boolean removerNo = false;
		
		Iterator iteratorFuncionalidades = funcionalidadesPermitidasAcesso
				.iterator();

		while (iteratorFuncionalidades.hasNext()) {
			Funcionalidade funcionalidade = (Funcionalidade) iteratorFuncionalidades
					.next();

			if (arvorePastas.adicionarFuncionalidade(funcionalidade)) {
				iteratorFuncionalidades.remove();

			}

		}
		
		
		
		if (!arvorePastas.getElementos().isEmpty()) { 
			Iterator iteratorArvorePastas = arvorePastas.getElementos().iterator();
			while (iteratorArvorePastas.hasNext()) {
	
				Object objeto = iteratorArvorePastas.next();
	
				if (objeto instanceof FuncionalidadeCategoria) {
	
					FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) objeto;
	
					   
						if (adicionarFuncionalidadesPasta(funcionalidadeCategoria,
								funcionalidadesPermitidasAcesso)) {
							//remover a pasta
							iteratorArvorePastas.remove();
						}
				}
			}
		} else {
			
			removerNo = true;
			
		}
		
		if (arvorePastas.getElementos().isEmpty() ) {
			//remover a pasta
			removerNo = true;
			
		}
		
		return removerNo; 
		
	}

	/**
	 * Met�do respons�vel por buscar o PAI do menu na arvore
	 * 
	 * @author Rafael Pinto
	 * @date 12/09/2008
	 * 
	 * @return funcionalidadeCategoria
	 * @throws ControladorException
	 */
	private FuncionalidadeCategoria buscarArvorePastas(Integer modulo) throws ControladorException {
				
		FiltroFuncionalidadeCategoria filtroFuncionalidadeCategoria = 
			new FiltroFuncionalidadeCategoria();
		
		filtroFuncionalidadeCategoria.adicionarParametro(
				new ParametroNulo(FiltroFuncionalidadeCategoria.MENU_ITEM_SUPERIOR));
		
		FuncionalidadeCategoria arvore = 
			(FuncionalidadeCategoria)
				Util.retonarObjetoDeColecao(
					getControladorUtil().pesquisar(filtroFuncionalidadeCategoria, 
						FuncionalidadeCategoria.class.getName()));
		
		
		if(modulo != null){
			this.removerElementos(arvore.getElementos(),modulo);
		}
		
		return arvore;
	}

	/**
	 * Met�do respons�vel por remover as pastas 
	 * que n�o fazem parte do modulo
	 * 
	 * @author Rafael Pinto
	 * @date 12/09/2008
	 * 
	 * @param Set elementos
	 * @param Integer modulo
	 * 
	 * @return void
	 */
	private void removerElementos(Set elementos,Integer modulo){
		
		Iterator iteratorArvorePastas = elementos.iterator();
		
		while (iteratorArvorePastas.hasNext()) {
			
			Object objeto = iteratorArvorePastas.next();
			
			if (objeto instanceof FuncionalidadeCategoria) {
				
				FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) objeto;
				
				if(!modulo.equals(funcionalidadeCategoria.getModulo().getId())){
					iteratorArvorePastas.remove();
				}
			}
		}
	}
	
	
	public FuncionalidadeCategoria pesquisarArvoreFuncionalidades(Integer modulo) throws ControladorException {

		// Obt�m a lista de todas as funcionalidades do sistema
		Collection funcionalidades = null;

		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(
			new ParametroSimples(
				FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA,
				ConstantesSistema.SIM));

		filtroFuncionalidade.adicionarParametro(
			new ParametroSimples(
				FiltroFuncionalidade.MODULO_ID,
				modulo));
		
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeCategoria");
		filtroFuncionalidade.setConsultaSemLimites(true);

		// Pesquisa todas as funcionalidades cadastradas no sistema
		funcionalidades = 
			getControladorUtil().pesquisar(
				filtroFuncionalidade,
				Funcionalidade.class.getName());

		// Primeira Funcionalidade da arvore
		FuncionalidadeCategoria arvorePastas = buscarArvorePastas(modulo);
		
		FuncionalidadeCategoria menu = new FuncionalidadeCategoria();
		menu.setNumeroOrdemMenu(0L);
		menu.setId(0);
		HashSet elementos = new HashSet();
		elementos.add(arvorePastas);
		menu.setElementos(elementos);
		
		adicionarFuncionalidadesPasta(menu, funcionalidades);
		
		return menu;
	}

	public void efetuarAlteracaoSenha(Usuario usuarioLogado,
			String dataNascimentoString, String cpf, String lembreteSenha,
			String novaSenha, String confirmacaoNovaSenha)
			throws ControladorException {
		
		// [UC0288] - Validar Nova Senha
		this.validarNovaSenha(usuarioLogado, dataNascimentoString, cpf,
				lembreteSenha, novaSenha, confirmacaoNovaSenha);
		 
		/*
		 * Recupera os par�metros do sistema para recuperar o n� de dias da
		 * expira��o do acesso e o n� de dias de para a mensagem de expira��o
		 */
		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();
		Short numeroDiasExpiracaoAcesso = sistemaParametro
				.getNumeroDiasExpiracaoAcesso();
		Short numeroDiasMensagemExpiracao = sistemaParametro
				.getNumeroDiasMensagemExpiracao();

		// Cria e recupera as datas necess�rias para verificar se altera��o de
		// senha � permitida
		Date dataAtual = new Date();
		Date dataInicioCadastro = usuarioLogado.getDataCadastroInicio();
		Date dataFimCadastro = usuarioLogado.getDataCadastroFim();
		Date dataExpiracaoAcesso = null;
		Date dataPrazoMensagemExpiracao = null;

		/*
		 * Caso o n� de dias para expira��o de acesso for nulo atribui zero a
		 * ele, a mesma coisa para o n� de dias da mensagem de expira��o
		 */
		if (numeroDiasExpiracaoAcesso == null) {
			numeroDiasExpiracaoAcesso = 0;
		}
		if (numeroDiasMensagemExpiracao == null) {
			numeroDiasMensagemExpiracao = 0;
		}
		
		/*
		 * Pesquisa numero de dias para expira��o de senha
		 * do grupo ao qual o usuario pertence.
		 */
		Integer numeroDiasExpiracaoGrupo = 0;
		
		Collection colecaoGruposUsuario = this.getControladorUsuario().pesquisarGruposUsuario(usuarioLogado.getId());
		
		if(colecaoGruposUsuario!=null && !colecaoGruposUsuario.isEmpty()){
			
			for (Iterator iterator = colecaoGruposUsuario.iterator(); iterator.hasNext();) {
			
				Grupo grupo = (Grupo) iterator.next();
			
				if(grupo.getNumDiasExpiracaoSenha()!=null){
					numeroDiasExpiracaoGrupo = grupo.getNumDiasExpiracaoSenha();
				}
			
			}
		}
		
		/*
		 * Caso empresa controle dias de expira��o de senha por grupo
		 */
		if(sistemaParametro.getIndicadorControleExpiracaoSenhaPorGrupo()!=null
				&& sistemaParametro.getIndicadorControleExpiracaoSenhaPorGrupo().compareTo(new Integer('1'))==0){
			
			
			
			if (dataInicioCadastro != null && dataFimCadastro != null 
					&& (Util.adicionarNumeroDiasDeUmaData(dataAtual,
							numeroDiasExpiracaoGrupo)).after(dataFimCadastro)) {
				dataExpiracaoAcesso = dataFimCadastro;
			}else{
				dataExpiracaoAcesso = Util.adicionarNumeroDiasDeUmaData(dataAtual,numeroDiasExpiracaoGrupo);
			} 
			
			
		}else{
			/*
		 	* Caso contr�rio,
		 	* a data de inicio do cadastro esteja preenchida e a data atual
		 	* mais o n� de dias para expirar for maior que a data de fim do
		 	* cadastro a data de expira��o do acesso ser� a data fim do cadastro
		 	* Caso contr�rio a data de expira��o vai ser adata atual mais o n� de
		 	* dias de expira��o de acesso
		 	*/
			if (dataInicioCadastro != null && dataFimCadastro != null 
					&& (Util.adicionarNumeroDiasDeUmaData(dataAtual,
						numeroDiasExpiracaoAcesso)).after(dataFimCadastro)) {
				dataExpiracaoAcesso = dataFimCadastro;
			} else {
				dataExpiracaoAcesso = Util.adicionarNumeroDiasDeUmaData(dataAtual,
						numeroDiasExpiracaoAcesso);
			}
		}
		
		/*
		 * Caso empresa controle dias de expira��o de senha por grupo
		 */
		if(sistemaParametro.getIndicadorControleExpiracaoSenhaPorGrupo()!=null
				&& sistemaParametro.getIndicadorControleExpiracaoSenhaPorGrupo().compareTo(new Integer('1'))==0){
		
			
			if (dataInicioCadastro != null
					&& (Util.adicionarNumeroDiasDeUmaData(dataAtual,
							(numeroDiasExpiracaoGrupo - numeroDiasMensagemExpiracao)))
								.after(dataFimCadastro)) {
				
				dataPrazoMensagemExpiracao = Util.subtrairNumeroDiasDeUmaData(
						dataFimCadastro, numeroDiasMensagemExpiracao);
				
			} else {
				dataPrazoMensagemExpiracao = 
					Util.adicionarNumeroDiasDeUmaData(dataAtual,
							(numeroDiasExpiracaoGrupo - numeroDiasMensagemExpiracao));
			}
					
		}else{	
			/*
		 	* Caso a data de inicioa do cadastro esteja preenchida e a data atual
		 	* mas a diferen�a entre o n� de dias para expira��o e o n� de dias da
		 	* mensagem de expira��o for maior que a data fim do cadastro a data do
		 	* prazo para mensagem de expira��o vai ser a data fim do cadastro mais
		 	* o n� de dias mensagem de expira��o Caso contr�rio a data para o prazo
		 	* de expira��o da mensagem ser� a data atual mais a diferen�a entre o
		 	* n� de dias para expira��o e o n� de dias da mensagem de expira��o
		 	*/
			if (dataInicioCadastro != null
					&& (Util
							.adicionarNumeroDiasDeUmaData(
									dataAtual,
									(numeroDiasExpiracaoAcesso - numeroDiasMensagemExpiracao)))
								.after(dataFimCadastro)) {
				dataPrazoMensagemExpiracao = Util.subtrairNumeroDiasDeUmaData(
						dataFimCadastro, numeroDiasMensagemExpiracao);
			} else {
				dataPrazoMensagemExpiracao = Util.adicionarNumeroDiasDeUmaData(
						dataAtual,
						(numeroDiasExpiracaoAcesso - numeroDiasMensagemExpiracao));
			}
		}

		// Valida a data de nascimento digitada
		Date dataNascimento = null;
		SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataNascimento = dataFormato.parse(dataNascimentoString);
		} catch (ParseException ex) {
			throw new ControladorException("atencao.data.invalida", null,
					"Data de Nascimento");
		}
		
		
		// Cria a situa�do usu�rio e setaseu valor para senha ativa
		UsuarioSituacao usuarioSituacao = new UsuarioSituacao();
		usuarioSituacao.setId(UsuarioSituacao.ATIVO);
		
		
		/*
		 * Criptografa a nova senha gerada para ser usada pelo usu�rio
		 */
		try {
			novaSenha = Criptografia.encriptarSenha(novaSenha);
		} catch (ErroCriptografiaException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.criptografia.senha");
		}
		
		/*
		 * Caso a empresa controle o bloqueio de senhas anteriores, inserir registro
		 * em usuario senha historico.
		 * 
		 */
		if(sistemaParametro.getIndicadorControleBloqueioSenhaAnterior()!=null
				&& sistemaParametro.getIndicadorControleBloqueioSenhaAnterior().compareTo(new Integer(1))==0){
			
			//Pesquisa senhas antigas do usuario
			FiltroUsuarioSenhaHistorico 
				filtroUsuarioSenhaHistorico 
						= new FiltroUsuarioSenhaHistorico();
			
			filtroUsuarioSenhaHistorico
				.adicionarParametro(
						new ParametroSimples(
							FiltroUsuarioSenhaHistorico.USUARIO_ID,
							usuarioLogado.getId()));
			
			filtroUsuarioSenhaHistorico
				.setCampoOrderBy(FiltroUsuarioSenhaHistorico.ULTIMA_ALTERACAO);
			
			Collection<UsuarioSenhaHistorico> colecaoSenhasAntigasUsuario = 
				getControladorUtil().pesquisar(
						filtroUsuarioSenhaHistorico,
						UsuarioSenhaHistorico.class.getName());
			
			
			for (Iterator iterator = colecaoSenhasAntigasUsuario.iterator(); iterator
					.hasNext();) {
				UsuarioSenhaHistorico usuarioSenhaHistorico = (UsuarioSenhaHistorico) iterator.next();
				
				if(novaSenha.equals(usuarioSenhaHistorico.getSenha())){
					
				}
				
			}
			
			if(colecaoSenhasAntigasUsuario!=null){
				
				// O hist�rico dever� conter apenas as 3 ultimas senhas do usu�rio,
				// caso j� existe traz a mais antiga ser� apagada.
				if(colecaoSenhasAntigasUsuario.size()==3){
					
					UsuarioSenhaHistorico usuarioSenhaHistoricoMaisAntiga
						= colecaoSenhasAntigasUsuario.iterator().next();
			
					getControladorUtil().remover(usuarioSenhaHistoricoMaisAntiga);				
				}
				
				
				
			}
			
			UsuarioSenhaHistorico usuarioSenhaHistorico = new UsuarioSenhaHistorico();
			
			usuarioSenhaHistorico.setSenha(novaSenha);
			usuarioSenhaHistorico.setUsuario(usuarioLogado);
			usuarioSenhaHistorico.setUltimaAlteracao(dataAtual);
			
			this.getControladorUtil().inserir(usuarioSenhaHistorico);
			
		}	
		
		
		

		// Atualiza os dados do usu�rio
		usuarioLogado.setSenha(novaSenha);
		usuarioLogado.setDataExpiracaoAcesso(dataExpiracaoAcesso);
		usuarioLogado.setDataPrazoMensagemExpiracao(dataPrazoMensagemExpiracao);
		usuarioLogado.setDataNascimento(dataNascimento);
		usuarioLogado.setCpf(cpf);
		usuarioLogado.setLembreteSenha(lembreteSenha);
		usuarioLogado.setUltimaAlteracao(new Date());
		usuarioLogado.setUsuarioSituacao(usuarioSituacao);

//		// ------------ REGISTRAR TRANSA��O ----------------
//		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_EFETUAR_ALTERACAO_SENHA,
//				new UsuarioAcaoUsuarioHelper(usuarioLogado,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//
//		Operacao operacao = new Operacao();
//		operacao.setId(Operacao.OPERACAO_EFETUAR_ALTERACAO_SENHA);
//
//		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//		operacaoEfetuada.setOperacao(operacao);
//
//		usuarioLogado.setOperacaoEfetuada(operacaoEfetuada);
//		usuarioLogado.adicionarUsuario(usuarioLogado,
//				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//		registradorOperacao.registrarOperacao(usuarioLogado);
//		// ------------ REGISTRAR TRANSA��O ----------------
	
		// Seta a data de �ltima altera��o do grupo
		usuarioLogado.setUltimaAlteracao(new Date());
		// ------------ REGISTRAR TRANSA��O ----------------
		
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		    Operacao.OPERACAO_USUARIO_ALTERAR_SENHA, usuarioLogado.getId(),
		    usuarioLogado.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
		    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(usuarioLogado);

		// ------------ REGISTRAR TRANSA��O ----------------
    					
		// Atualiza os dados do usu�rio
		getControladorUtil().atualizar(usuarioLogado);
	}

	/**
	 * Met�do respons�vel por validar todos os dados informados pelo usu�rio
	 * para cadastrar uma nova senha para o usu�rio.
	 * 
	 * [UC0288] - Validar Nova Senha
	 * 
	 * @author Pedro Alexandre
	 * @date 13/07/2006
	 * 
	 * @param usuarioLogado
	 * @param dataNascimentoString
	 * @param cpf
	 * @param lembreteSenha
	 * @param novaSenha
	 * @param confirmacaoNovaSenha
	 * @throws ControladorException
	 */
	public void validarNovaSenha(Usuario usuarioLogado,
			String dataNascimentoString, String cpf, String lembreteSenha,
			String novaSenha, String confirmacaoNovaSenha)
			throws ControladorException {
		
		// Recupera Parametros do Sistema
		SistemaParametro sistemaParametro =getControladorUtil().pesquisarParametrosDoSistema();
		
		// Recupera o login do usu�rio logado
		String login = usuarioLogado.getLogin();

		// Recupera a data atual
		Date dataAtual = new Date();

		// [FS0003] - Validar Data
		Date dataNascimento = null;
		SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataNascimento = dataFormato.parse(dataNascimentoString);
		} catch (ParseException ex) {
			throw new ControladorException("atencao.data.invalida", null,
					"Data de Nascimento");
		}

		// [FS0004] - Verificar data maior ou igual a data corrente
		if (!dataNascimento.before(dataAtual)) {
			throw new ControladorException(
					"atencao.data_nascimento.anterior.dataatual", null, login,
					Util.formatarData(dataAtual));
		}

		// [FS0005] - Verificar data de nascimento do login
		Date dataNascimentoUsuarioLogado = usuarioLogado.getDataNascimento();
		if (dataNascimentoUsuarioLogado != null) {
			if (Util.compararData(dataNascimento,dataNascimentoUsuarioLogado) != 0) {
//				throw new ControladorException(
//						"atencao.data_nascimento.incorreta.login", null, login);
			}
		}

		// [FS0008] - Verificar CPF do login
		// Recupera o CPF do usu�rio que est� logado e verifica
		// se � o mesmo que foi informado n� p�gina
		// Caso o usu�rio n�o tenha cadastrado o cpf verifica se existe
		// um outro usu�rio j� com esse cpf informado
		String cpfUsuarioLogado = usuarioLogado.getCpf();
		if (cpfUsuarioLogado != null && !cpfUsuarioLogado.trim().equals("")) {
			if (!cpf.equals(cpfUsuarioLogado)) {
				throw new ControladorException("atencao.cpf.incorreto.login",
						null, login);
			}
		} else if (cpf != null) {
			FiltroUsuario filtroUsuarioComCpf = new FiltroUsuario();
			filtroUsuarioComCpf.adicionarParametro(new ParametroSimples(
					FiltroUsuario.CPF, cpf));
			Collection colecaoUsuariosComCpf = getControladorUtil().pesquisar(
					filtroUsuarioComCpf, Usuario.class.getName());

			// Caso exista um usu�rio cadastrado com o cpf informado
			if (colecaoUsuariosComCpf != null
					&& !colecaoUsuariosComCpf.isEmpty()) {
				throw new ControladorException("atencao.cpf.jainformado.login",
						null, login);
			}
		}

		// [FS0011] - Validar confirma��o da nova senha
		if (!novaSenha.equals(confirmacaoNovaSenha)) {
			throw new ControladorException(
					"atencao.confirmacao.novasenha.invalida");
		}
		
		// Fluxo 1.6.1
		if(sistemaParametro!=null 
				&& sistemaParametro.getIndicadorSenhaForte()!=null 
					&& sistemaParametro.getIndicadorSenhaForte().compareTo(new Integer(1))==0){
			// [FS0012] - Validar Senha Forte
			this.validarSenhaForte(novaSenha);
		}else{
			// [FS0010] - Validar Senha
			this.validarSenha(novaSenha);
		}
		
		// Fluxo 1.6.2
		if(sistemaParametro!=null 
				&& sistemaParametro.getIndicadorControleBloqueioSenhaAnterior()!=null 
					&& sistemaParametro.getIndicadorControleBloqueioSenhaAnterior().compareTo(new Integer(1))==0){
			this.validarBloqueiSenhasAnteriores(usuarioLogado, novaSenha);
			
		}
	}

	

	/**
	 * [UC0287] - Efetuar Login
	 * 
	 * Met�do respons�vel por enviar uma nova senha para o e-mail do usu�rio com
	 * situa��o pendente
	 * 
	 * [SB0002] - Lembrar senha
	 * 
	 * @author Pedro Alexandre
	 * @date 14/07/2006
	 * 
	 * @param login
	 * @param cpf
	 * @param dataNascimentoString
	 * @throws ControladorException
	 */
	public void lembrarSenha(String login, String cpf,
			String dataNascimentoString) throws ControladorException {
		// [FS0006] - Validar data
		Date dataNascimento = null;
		SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataNascimento = dataFormato.parse(dataNascimentoString);
		} catch (ParseException ex) {
			throw new ControladorException("atencao.data.invalida", null,
					"Data de Nascimento");
		}

		// [FS0007] Verificar data maior ou igual a data corrente
		Date dataAtual = new Date();
		if (!dataNascimento.before(dataAtual)) {
			throw new ControladorException(
					"atencao.data_nascimento.anterior.dataatual", null, login,
					Util.formatarData(dataAtual));
		}

		// Cria o filtro e pesquisa o usu�rio com o login informado
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(
				FiltroUsuario.LOGIN, login));
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("usuarioSituacao");
		Collection usuarios = this.getControladorUtil().pesquisar(
				filtroUsuario, Usuario.class.getName());

		// Recupera o usu�rio que est� solicitando o lembrete da senha
		Usuario usuarioLogado = (Usuario) usuarios.iterator().next();

		// [UC0008] - Verificar data de nascimento do login
		Date dataNascimentoUsuarioLogado = usuarioLogado.getDataNascimento();
		if (dataNascimentoUsuarioLogado != null && dataNascimento.compareTo(dataNascimentoUsuarioLogado) != 0) {
			throw new ControladorException(
					"atencao.data_nascimento.incorreta.login", null, login);
		}

		// [FS0012] - Verificar situa��o do usu�rio para lembrar senha
		UsuarioSituacao usuarioSituacao = usuarioLogado.getUsuarioSituacao();
		if (!usuarioSituacao.getId().equals(UsuarioSituacao.ATIVO)) {
			throw new ControladorException(
					"atencao.situacao_usuario.invalida.lembrar_senha", null,
					login, usuarioSituacao.getDescricaoUsuarioSituacao());
		}

		// Recupera o CPF do usu�rio que est� logado e verifica
		// se � o mesmo que foi informado n� p�gina
		String cpfUsuarioLogado = usuarioLogado.getCpf();
		if (cpfUsuarioLogado != null && !cpfUsuarioLogado.trim().equals("")) {
			// [FS0010] - Verificar CPF do login
			if (!cpf.equals(cpfUsuarioLogado)) {
				throw new ControladorException("atencao.cpf.incorreto.login",
						null, login);
			}
		}

		// Obt�m uma senha tempor�ria com 6 caracteres
		// essa senha ser� atribuida ao usu�rio e sua situa��o vai estar
		// pendente
		String novaSenha;
		String senhaCriptografada;
		try {
			novaSenha = Util.geradorSenha(6);
			senhaCriptografada = Criptografia.encriptarSenha(novaSenha);
		} catch (ErroCriptografiaException e1) {
			throw new ControladorException("erro.criptografia.senha");
		}

		// Atualiza os dados do usu�rio informando que a nova senha est�
		// pendente
		usuarioSituacao = new UsuarioSituacao();
		usuarioSituacao.setId(UsuarioSituacao.PENDENTE_SENHA);
		usuarioLogado.setUsuarioSituacao(usuarioSituacao);
		usuarioLogado.setSenha(senhaCriptografada);
		usuarioLogado.setUltimaAlteracao(new Date());
		getControladorUtil().atualizar(usuarioLogado);
		
		SistemaParametro sistemaParametro =getControladorUtil().pesquisarParametrosDoSistema(); 

		/*
		 * Trecho de c�digo respons�vel por enviar a nova senha do usu�rio para
		 * o email cadastrado
		 */
		String emailDestinatario = usuarioLogado.getDescricaoEmail();
		String emailRemetente = sistemaParametro.getDescricaoEmail();
		StringBuilder corpoEmail = new StringBuilder();
		corpoEmail.append("Login:" + login);
		corpoEmail.append(System.getProperty("line.separator"));
		corpoEmail.append("Senha:" + novaSenha);
		String assuntoEmail = "Solicita��o de senha";
		try {
			ServicosEmail.enviarMensagem(emailRemetente, emailDestinatario,
					assuntoEmail, corpoEmail.toString());
		} catch (ErroEmailException e) {
			throw new ControladorException("erro.email");
		}
	}

	/**
	 * [UC0288] - Validar nova senha
	 * 
	 * Met�do que verifica se a senha informada est� de acordo com o padr�o de
	 * seguran�a adotado.
	 * 
	 * [FS0011] - Validar senha
	 * 
	 * @author Pedro Alexandre
	 * @date 14/07/2006
	 * 
	 * @param senha
	 * @throws ControladorException
	 */
	private void validarSenha(String senha) throws ControladorException {

		if (senha.length() < 4) {
			throw new ControladorException("atencao.senha.invalida", null,
					"Senha deve ter pelo menos 4 caracteres.Informe outra.");
		}

		/*
		 * boolean senhaNoPadrao = true;
		 * 
		 * char[] senhaArray = senha.toCharArray();
		 * 
		 * for (int i = 0; i < senha.length(); i++) { char temp = senhaArray[i]; }
		 * 
		 * if(!senhaNoPadrao){ throw new
		 * ControladorException("atencao.senha.invalida", null,"Senha est� fora
		 * do padr�o de seguran�a adotado pela empresa.Informe outra."); }
		 */
	}

	/**
	 * Verifica se uma url solicitada para o servidor � uma funcionalidade ou
	 * uma opera��o
	 * 
	 * @author Pedro Alexandre
	 * @date 18/07/2006
	 * 
	 * @param url
	 * @return
	 * @throws ControladorException
	 */
	public String verificarTipoURL(String url) throws ControladorException {

		// Vari�vel que vai conter uma string indicando se a url � uma opera��o
		// ou uma funcionalidade
		String retorno = null;

		// Caso a url starte com "/"(barra) retira a barra da url
		if (url.startsWith("/")) {
			url = url.substring(1);
		}

		// Cria o filtro para pesquisar a funcionalidade na base de dados pela
		// url informada
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		// filtroFuncionalidade.adicionarParametro(new
		// ParametroSimples(FiltroFuncionalidade.CAMINHO_URL,url));
		filtroFuncionalidade.adicionarParametro(new ComparacaoTexto(
				FiltroFuncionalidade.CAMINHO_URL, url));

		// Pesquisa a funcionalidade com a url
		Collection colecaoFuncionalidade = getControladorUtil().pesquisar(
				filtroFuncionalidade, Funcionalidade.class.getName());

		/*
		 * Caso exista funcionalidade cadastrada para esta url indica que a url
		 * � uma funcionalidade. Caso contr�rio verifica se a url � uma
		 * opera��o.
		 */
		if (colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()) {
			retorno = "funcionalidade";
		} else {
			// Cria o filtro de opera��o para verificar se a url � uma opera��o
			FiltroOperacao filtroOperacao = new FiltroOperacao();
			// filtroOperacao.adicionarParametro(new
			// ParametroSimples(FiltroOperacao.CAMINHO_URL,url));
			filtroOperacao.adicionarParametro(new ComparacaoTexto(
					FiltroOperacao.CAMINHO_URL, url));

			// Pesquisa a opera��o com a url
			Collection colecaoOperacao = getControladorUtil().pesquisar(
					filtroOperacao, Operacao.class.getName());

			/*
			 * Caso exista opera��o cadastrada para esta url indica que a url �
			 * uma opera��o
			 */
			if (colecaoOperacao != null && !colecaoOperacao.isEmpty()) {
				retorno = "operacao";
			}
		}
		// Retorna uma string indicando se a url � uma funcionalidade ou
		// opera��o
		return retorno;
	}

	/**
	 * Met�do que verifica se o usu�rio tem permiss�o para acessar a
	 * funcionalidade que est� sendo requisitada (existe ocorr�ncia na tabela
	 * GrupoFuncionalidadeOperacao). Verifica se o(s) grupo(s) que o usu�rio
	 * pertence tem acesso a funcionalidade e se todas as opera��es desta
	 * funcionalidade n�o est�o com restri��es(existe ocorr�ncia na tabela
	 * UsuarioGrupoRestricao)
	 * 
	 * @author Pedro Alexandre
	 * @date 18/07/2006
	 * 
	 * @param usuarioLogado
	 * @param urlFuncionalidade
	 * @param colecaoGruposUsuario
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarAcessoPermitidoFuncionalidade(
			Usuario usuarioLogado, String urlFuncionalidade,
			Collection colecaoGruposUsuario,Integer idFuncionalidade) 
		throws ControladorException {
		
		// Cria a vari�vel que indica se o usu�rio tem ou n�o acesso a
		// funcionalidade
		boolean retorno = false;

		// Caso a url starte com "/"(barra) retira a barra da url
		if (urlFuncionalidade.startsWith("/")) {
			urlFuncionalidade = urlFuncionalidade.substring(1);
		}

		// Cria o filtro para pesquisar a funcionalidade com a url informada
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		
		if(idFuncionalidade != null){
			
			filtroFuncionalidade.adicionarParametro(
				new ParametroSimples(
						FiltroFuncionalidade.ID, idFuncionalidade));

		}else{
			filtroFuncionalidade.adicionarParametro(
				new ComparacaoTexto(
					FiltroFuncionalidade.CAMINHO_URL, urlFuncionalidade));
		}

		// Pesquisa a funcionalidade com a url informada
		Collection colecaoFuncionalidade = 
			getControladorUtil().pesquisar(
				filtroFuncionalidade, Funcionalidade.class.getName());

		// Vari�vel que vai armazenar a funcionalidade pesquisada
		Funcionalidade funcionalidade = null;

		// Caso a pesquisa retorne a funcionalidade
		if (colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()) {

			// Recupera a funcionalidade da cole��o
			funcionalidade = (Funcionalidade) colecaoFuncionalidade.iterator()
					.next();

			// Cria a vari�vel que vao armazenar as funcionalidades permitidas
			// para ser acessadas
			// com a opera�a� que est� sendo requisitada
			Collection<Funcionalidade> colecaoFuncionalidadesPermitidas = new ArrayList();
			colecaoFuncionalidadesPermitidas.add(funcionalidade);

			// Cria o filtro para verificar se existe permiss�o para acessar a
			// funcionalidade
			// existe ocorr�ncia na tabela GrupoFuncionalidadeOperacao
			FiltroGrupoFuncionalidadeOperacao filtroGrupoFuncionalidadeOperacao = new FiltroGrupoFuncionalidadeOperacao();

			// Cria o filtro para pesquisar se a funcionalidade � dependente de
			// alguma outra funcionalidade
			// para verificar se a opera�a� foi cadastrada para a funcionalidade
			// da propria opera��o
			// ou com uma funcionalidade principal com rela��o de depend�ncia
			// com a funcionalidade da propria opera��o
			FiltroFuncionalidadeDependencia filtroFuncionalidadeDependencia = new FiltroFuncionalidadeDependencia();
			filtroFuncionalidadeDependencia
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidadeDependencia.FUNCIONALIDADE_DEPENDENCIA,
							funcionalidade.getId()));
			Collection colecaoFuncionalidadePrincipal = getControladorUtil()
					.pesquisar(filtroFuncionalidadeDependencia,
							FuncionalidadeDependencia.class.getName());

			/*
			 * Caso a cole��o de funcionalidade principal n�o estiver vazia
			 * adiciona as funcionalidades no filtro para pesquisar as
			 * permiss�es
			 */
			if (colecaoFuncionalidadePrincipal != null
					&& !colecaoFuncionalidadePrincipal.isEmpty()) {
				Iterator iteratorFuncionalidadePrincipal = colecaoFuncionalidadePrincipal
						.iterator();

				// La�o para adicionar as funcionalidades principais a cole��o
				// de funcionalidades permitidas
				while (iteratorFuncionalidadePrincipal.hasNext()) {
					FuncionalidadeDependencia funcionalidadeDependencia = (FuncionalidadeDependencia) iteratorFuncionalidadePrincipal
							.next();
					colecaoFuncionalidadesPermitidas
							.add(funcionalidadeDependencia.getFuncionalidade());
				}
			}

			// Cria um contador para auxiliar na cria��o dos filtros
			int cont = 1;

			/*
			 * Caso a cole��o de funcionalidades permitidas n�o esteja vazia
			 * adicona os ids das funcionalidades permitidas no filtro para
			 * pesquisar as permiss�es
			 */
			if (colecaoFuncionalidadesPermitidas != null
					&& !colecaoFuncionalidadesPermitidas.isEmpty()) {
				Iterator<Funcionalidade> iteratorFuncionalidadesPeritidas = colecaoFuncionalidadesPermitidas
						.iterator();

				// La�o para adicionar os ids das funcionalidades permitidas no
				// filtro
				while (iteratorFuncionalidadesPeritidas.hasNext()) {
					// Recupera a funcionalidade do iterator
					Funcionalidade funcionalidadePermitida = iteratorFuncionalidadesPeritidas
							.next();

					/*
					 * Caso a cole��o possua uma �nica funcionalidade permitida
					 * para o usu�rio adiciona o id da cole��o no filtro sem
					 * nenhum conector Caso contr�rio verifica qual a posi��o do
					 * iterator para adicionar o id com o conector correto
					 */
					if (colecaoFuncionalidadesPermitidas.size() == 1) {
						filtroGrupoFuncionalidadeOperacao
								.adicionarParametro(new ParametroSimples(
										FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_ID,
										funcionalidadePermitida.getId()));
					} else {
						// Caso seja a primeira funcionalidade adiciona o id com
						// o conector "OR"
						// e informa quantas funcionalidades vai ter para inseri
						// os parenteses
						if (cont == 1) {
							filtroGrupoFuncionalidadeOperacao
									.adicionarParametro(new ParametroSimples(
											FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_ID,
											funcionalidadePermitida.getId(),
											FiltroParametro.CONECTOR_OR,
											colecaoFuncionalidadesPermitidas
													.size()));
							cont++;
						} else {
							/*
							 * Caso seja a �ltima funcionalidade da cole��o
							 * adiciona o id da funcionalidade sem conector Caso
							 * contr�rio adiciona o id da funcionalidade com o
							 * conector "OR"
							 */
							if (cont == colecaoFuncionalidadesPermitidas.size()) {
								filtroGrupoFuncionalidadeOperacao
										.adicionarParametro(new ParametroSimples(
												FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_ID,
												funcionalidadePermitida.getId()));
								cont++;
							} else {
								filtroGrupoFuncionalidadeOperacao
										.adicionarParametro(new ParametroSimples(
												FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_ID,
												funcionalidadePermitida.getId(),
												ParametroSimples.CONECTOR_OR));
								cont++;
							}
						}
					}
				}
			}

			// Inicializa o contador para auxiliar no filtro de grupos
			cont = 1;

			// Cria o iteratorpara a cole��o de grupos do usu�rio logado
			Iterator iteratorGruposUsuario = colecaoGruposUsuario.iterator();

			// La�o para adicionar os grupos do usu�rio no filtro
			while (iteratorGruposUsuario.hasNext()) {
				// Recupera o grupo do iterator
				Grupo grupoUsuario = (Grupo) iteratorGruposUsuario.next();

				/*
				 * Caso a cole��o possua um �nico grupo para o usu�rio adiciona
				 * o id do grupo no filtro sem nenhum conector Caso contr�rio
				 * verifica qual a posi��o do iterator para adicionar o id com o
				 * conector correto
				 */
				if (colecaoGruposUsuario.size() == 1) {
					filtroGrupoFuncionalidadeOperacao
							.adicionarParametro(new ParametroSimples(
									FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
									grupoUsuario.getId()));
				} else {
					// Caso seja o primeiro grupo adiciona o id com o conector
					// "OR"
					// e informa quantos grupos vai ter para inserir os
					// parenteses
					if (cont == 1) {
						filtroGrupoFuncionalidadeOperacao
								.adicionarParametro(new ParametroSimples(
										FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
										grupoUsuario.getId(),
										FiltroParametro.CONECTOR_OR,
										colecaoGruposUsuario.size()));
						cont++;
					} else {
						/*
						 * Caso seja o �ltimo grupo da cole��o adiciona o id do
						 * grupo sem conector Caso contr�rio adiciona o id
						 * dogrupo com o conector "OR"
						 */
						if (cont == colecaoGruposUsuario.size()) {
							filtroGrupoFuncionalidadeOperacao
									.adicionarParametro(new ParametroSimples(
											FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
											grupoUsuario.getId()));
							cont++;
						} else {
							filtroGrupoFuncionalidadeOperacao
									.adicionarParametro(new ParametroSimples(
											FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
											grupoUsuario.getId(),
											ParametroSimples.CONECTOR_OR));
							cont++;
						}
					}
				}
			}

			/*
			 * Cria o filtro para pesquisar as opera��oes da funcionalidade
			 * requisitada para verificar se o usu�rio tem acesso a alguma
			 * opera��o da funcionalidade
			 */
			FiltroOperacao filtroOperacao = new FiltroOperacao();
			filtroOperacao.adicionarParametro(new ParametroSimples(
					FiltroOperacao.FUNCIONALIDADE_ID, funcionalidade.getId()));
			Collection colecaoOperacoesFuncionalidade = getControladorUtil()
					.pesquisar(filtroOperacao, Operacao.class.getName());

			// Inicializa o contador para auxiliar no filtro de opera��es
			cont = 1;

			// Cria o iterator das opera��es
			Iterator iteratorOperacoesFuncionalidade = colecaoOperacoesFuncionalidade
					.iterator();

			// La�o para adicionar as opera��es da funcionalidade no filtro
			while (iteratorOperacoesFuncionalidade.hasNext()) {
				// Recupera a opera��o da funcionalidade
				Operacao operacaoFuncionalidade = (Operacao) iteratorOperacoesFuncionalidade
						.next();

				/*
				 * Caso a cole��o possua uma �nica opera��o para a
				 * funcionalidade adiciona o id da opera��o no filtro sem nenhum
				 * conector Caso contr�rio verifica qual a posi��o do iterator
				 * para adicionar o id com o conector correto
				 */
				if (colecaoOperacoesFuncionalidade.size() == 1) {
					filtroGrupoFuncionalidadeOperacao
							.adicionarParametro(new ParametroSimples(
									FiltroGrupoFuncionalidadeOperacao.OPERACAO_ID,
									operacaoFuncionalidade.getId()));
				} else {
					// Caso seja a primeira opera��o adiciona o id com o
					// conector "OR"
					// e informa quantas opera��es vai ter para inserir os
					// parenteses
					if (cont == 1) {
						filtroGrupoFuncionalidadeOperacao
								.adicionarParametro(new ParametroSimples(
										FiltroGrupoFuncionalidadeOperacao.OPERACAO_ID,
										operacaoFuncionalidade.getId(),
										FiltroParametro.CONECTOR_OR,
										colecaoOperacoesFuncionalidade.size()));
						cont++;
					} else {
						/*
						 * Caso seja a �ltima opera��o da cole��o adiciona o id
						 * da opera��o sem conector Caso contr�rio adiciona o id
						 * da opera��o com o conector "OR"
						 */
						if (cont == colecaoOperacoesFuncionalidade.size()) {
							filtroGrupoFuncionalidadeOperacao
									.adicionarParametro(new ParametroSimples(
											FiltroGrupoFuncionalidadeOperacao.OPERACAO_ID,
											operacaoFuncionalidade.getId()));
							cont++;
						} else {
							filtroGrupoFuncionalidadeOperacao
									.adicionarParametro(new ParametroSimples(
											FiltroGrupoFuncionalidadeOperacao.OPERACAO_ID,
											operacaoFuncionalidade.getId(),
											ParametroSimples.CONECTOR_OR));
							cont++;
						}
					}
				}
			}

			// Pesquisa se o usu�rio tem permiss�o para acessar a funcionalidade
			Collection permissoes = getControladorUtil().pesquisar(
					filtroGrupoFuncionalidadeOperacao,
					GrupoFuncionalidadeOperacao.class.getName());

			/*
			 * Caso o usu�rio tenha permiss�o para acessar a funcionalidade
			 * verifica se existe restri��o para todas as opera��es da
			 * funcionalidade
			 */
			if (permissoes != null && !permissoes.isEmpty()) {

				/*
				 * Cria o filtro para pesquisar todas as restri��es do usu�rio
				 * seta o c�digo do usu�rio logado e o c�digo da funcionalidade
				 * no filtro
				 */
				FiltroUsuarioGrupoRestricao filtroUsuarioGrupoRestricao = new FiltroUsuarioGrupoRestricao();
				filtroUsuarioGrupoRestricao
						.adicionarParametro(new ParametroSimples(
								FiltroUsuarioGrupoRestricao.USUARIO_ID,
								usuarioLogado.getId()));
				filtroUsuarioGrupoRestricao
						.adicionarParametro(new ParametroSimples(
								FiltroUsuarioGrupoRestricao.FUNCIONALIDADE_ID,
								funcionalidade.getId()));

				/*
				 * Bloco de c�digo para montar o filtro com todos os grupos do
				 * usu�rio logado
				 */
				Iterator iteratorGruposUsuarioPermissoes = colecaoGruposUsuario
						.iterator();
				cont = 1;
				// La�o para inserir todos os grupos no filtro
				while (iteratorGruposUsuarioPermissoes.hasNext()) {
					// Recupera o grupo do usu�rio
					Grupo grupoUsuario = (Grupo) iteratorGruposUsuarioPermissoes
							.next();
					/*
					 * Caso a cole��o de grupos de usu�rio tenha apenas um
					 * elemento adiciona o grupo no filtro sem o conector "OR"
					 * Caso contr�rio vai adicionar os grupos um a um com seus
					 * conectores
					 */
					if (colecaoGruposUsuario.size() == 1) {
						filtroUsuarioGrupoRestricao
								.adicionarParametro(new ParametroSimples(
										FiltroUsuarioGrupoRestricao.GRUPO_ID,
										grupoUsuario.getId()));
					} else {
						if (cont == 1) {
							filtroUsuarioGrupoRestricao
									.adicionarParametro(new ParametroSimples(
											FiltroUsuarioGrupoRestricao.GRUPO_ID,
											grupoUsuario.getId(),
											FiltroParametro.CONECTOR_OR,
											colecaoGruposUsuario.size()));
							cont++;
						} else {
							if (cont == colecaoGruposUsuario.size()) {
								filtroUsuarioGrupoRestricao
										.adicionarParametro(new ParametroSimples(
												FiltroUsuarioGrupoRestricao.GRUPO_ID,
												grupoUsuario.getId()));
								cont++;
							} else {
								filtroUsuarioGrupoRestricao
										.adicionarParametro(new ParametroSimples(
												FiltroUsuarioGrupoRestricao.GRUPO_ID,
												grupoUsuario.getId(),
												ParametroSimples.CONECTOR_OR));
								cont++;
							}
						}
					}
				}
				// Fim do bloco para adicionar os grupos no filtro

				// Inicializa ocontador para auxiliar no filtro das permiss�es
				cont = 1;

				// Cria o iterator das permiss�es
				Iterator iteratorPermissoes = permissoes.iterator();

				// La�o para inserir todas as opera��es no filtro
				while (iteratorPermissoes.hasNext()) {

					// Recupera a permiss�o do iterator
					GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorPermissoes
							.next();

					/*
					 * Caso a cole��o de permiss�es de usu�rio tenha apenas um
					 * elemento adiciona a opera��o no filtro sem o conector
					 * "OR" Caso contr�rio vai adicionar as opera��es uma a uma
					 * com seus conectores
					 */
					if (permissoes.size() == 1) {
						filtroUsuarioGrupoRestricao
								.adicionarParametro(new ParametroSimples(
										FiltroUsuarioGrupoRestricao.OPERACAO_ID,
										grupoFuncionalidadeOperacao
												.getComp_id().getOperacaoId()));
					} else {
						if (cont == 1) {
							filtroUsuarioGrupoRestricao
									.adicionarParametro(new ParametroSimples(
											FiltroUsuarioGrupoRestricao.OPERACAO_ID,
											grupoFuncionalidadeOperacao
													.getComp_id()
													.getOperacaoId(),
											FiltroParametro.CONECTOR_OR,
											permissoes.size()));
							cont++;
						} else {
							if (cont == colecaoGruposUsuario.size()) {
								filtroUsuarioGrupoRestricao
										.adicionarParametro(new ParametroSimples(
												FiltroUsuarioGrupoRestricao.OPERACAO_ID,
												grupoFuncionalidadeOperacao
														.getComp_id()
														.getOperacaoId()));
								cont++;
							} else {
								filtroUsuarioGrupoRestricao
										.adicionarParametro(new ParametroSimples(
												FiltroUsuarioGrupoRestricao.OPERACAO_ID,
												grupoFuncionalidadeOperacao
														.getComp_id()
														.getOperacaoId(),
												ParametroSimples.CONECTOR_OR));
								cont++;
							}
						}
					}
				}
				// Fim do bloco para adicionar as opera��es no filtro

				// Pesquisa as restri��es do usu�rio
				Collection restricoes = getControladorUtil().pesquisar(
						filtroUsuarioGrupoRestricao,
						UsuarioGrupoRestricao.class.getName());

				/*
				 * Caso o n� de restri��es for menor que o n� de permiss�es seta
				 * a flag para indicar que o usu�rio tem acesso a funcionalidade
				 */
				if (restricoes.size() < permissoes.size()) {
					retorno = true;

					/*
					 * Chama o met�do para registrar o acesso do usu�rio a
					 * funcionalidade na tabela UsuarioFavorito
					 */
					this.registrarFuncionalidadeAcessada(usuarioLogado,
							funcionalidade);
				}
			}
		}

		// Retorna uma flag indicando se o usu�rio tem acesso a funcionalidade
		return retorno;
	}

	/**
	 * Met�do que verifica se o usu�rio tem permiss�o para acessar a opera��o
	 * que est� sendo requisitada (existe ocorr�ncia na tabela
	 * GrupoFuncionalidadeOperacao). Verifica se o(s) grupo(s) que o usu�rio
	 * pertence tem acesso a opera��o e se a opera��o desta funcionalidade n�o
	 * est�o com restri��o(existe ocorr�ncia na tabela UsuarioGrupoRestricao)
	 * 
	 * @author Pedro Alexandre
	 * @date 18/07/2006
	 * 
	 * @param usuarioLogado
	 * @param urlOperacao
	 * @param colecaoGruposUsuario
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarAcessoPermitidoOperacao(Usuario usuarioLogado,
			String urlOperacao, Collection colecaoGruposUsuario)
			throws ControladorException {
		// Cria a flag que vai indicar se o usu�rio tem acesso para opera��o ou
		// n�o
		boolean retorno = false;

		// Caso a url inicia com barra retira a barra da url
		if (urlOperacao.startsWith("/")) {
			urlOperacao = urlOperacao.substring(1);
		}

		// Cria o filtro para pesquisar a opera��o da url informada
		// e carrega a funcionalidade da opera��o
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		// filtroOperacao.adicionarParametro(new
		// ParametroSimples(FiltroOperacao.CAMINHO_URL,urlOperacao));
		filtroOperacao.adicionarParametro(new ComparacaoTexto(
				FiltroOperacao.CAMINHO_URL, urlOperacao));
		filtroOperacao
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");

		// Pesquisa a opera��o no sistema com a url informada
		Collection colecaoOperacao = getControladorUtil().pesquisar(
				filtroOperacao, Operacao.class.getName());

		// Cria as vari�veis que v�o armazenar a funcionalidade e a opera��o
		Funcionalidade funcionalidadeOperacao = null;
		Operacao operacao = null;

		/*
		 * Caso a cole��o de opera��es n�o esteja vazia pesquisa as permiss�es
		 * do usu�rio e as restri��es
		 */
		if (colecaoOperacao != null && !colecaoOperacao.isEmpty()) {

			// Recupera a opera��o do iterator
			operacao = (Operacao) colecaoOperacao.iterator().next();

			// Recupera a funcionalidade da opera��o
			funcionalidadeOperacao = operacao.getFuncionalidade();

			// Cria a cole��o que vai armazenar as funcionalidades permitidas
			// para
			// cadastrar coma opera��o requerida
			Collection<Funcionalidade> colecaoFuncionalidadesPermitidas = new ArrayList();
			colecaoFuncionalidadesPermitidas.add(funcionalidadeOperacao);

			/*
			 * Cria o filtro de funcionalidades dep�ndencia para pesquisar se a
			 * funcionalidade da opera��o requerida pelo usu�rio tem alguma
			 * funcionalidade ligada a ela como principal
			 */
			FiltroFuncionalidadeDependencia filtroFuncionalidadeDependencia = new FiltroFuncionalidadeDependencia();
			filtroFuncionalidadeDependencia
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidadeDependencia.FUNCIONALIDADE_DEPENDENCIA,
							funcionalidadeOperacao.getId()));
			Collection colecaoFuncionalidadePrincipal = getControladorUtil()
					.pesquisar(filtroFuncionalidadeDependencia,
							FuncionalidadeDependencia.class.getName());

			/*
			 * Caso a cole��o de funcionalidades principais n�o esteja vazia
			 * adiciona as funcionalidades a cole��o de funcionalidades
			 * permitidas
			 */
			if (colecaoFuncionalidadePrincipal != null
					&& !colecaoFuncionalidadePrincipal.isEmpty()) {
				Iterator iteratorFuncionalidadePrincipal = colecaoFuncionalidadePrincipal
						.iterator();

				// La�o para adicionar a funcionalidade a cole��o de
				// funcionalidades permitidas
				while (iteratorFuncionalidadePrincipal.hasNext()) {
					FuncionalidadeDependencia funcionalidadeDependencia = (FuncionalidadeDependencia) iteratorFuncionalidadePrincipal
							.next();
					colecaoFuncionalidadesPermitidas
							.add(funcionalidadeDependencia.getFuncionalidade());
				}
			}

			// Cria o filtro para pesquisar as permiss�es do usu�rio para a
			// opera��o informada
			FiltroGrupoFuncionalidadeOperacao filtroGrupoFuncionalidadeOperacao = new FiltroGrupoFuncionalidadeOperacao();
			filtroGrupoFuncionalidadeOperacao
					.adicionarParametro(new ParametroSimples(
							FiltroGrupoFuncionalidadeOperacao.OPERACAO_ID,
							operacao.getId()));

			// Cria um contador para inserir os grupos do usu�rio no filtro
			int cont = 1;

			/*
			 * Caso a cole��o de funcionalidades permitidas n�o esteja vazia
			 * adicona os ids das funcionalidades permitidas no filtro para
			 * pesquisar as permiss�es para a opera��o
			 */
			if (colecaoFuncionalidadesPermitidas != null
					&& !colecaoFuncionalidadesPermitidas.isEmpty()) {

				// Cria o iterator das funcionalidades permitidas
				Iterator<Funcionalidade> iteratorFuncionalidadesPermitidas = colecaoFuncionalidadesPermitidas
						.iterator();

				// La�o para adicionar os ids das funcionalidades permitidas no
				// filtro
				while (iteratorFuncionalidadesPermitidas.hasNext()) {
					// Recupera a funcionalidade do iterator
					Funcionalidade funcionalidadePermitida = iteratorFuncionalidadesPermitidas
							.next();

					/*
					 * Caso a cole��o possua uma �nica funcionalidade permitida
					 * para o usu�rio adiciona o id da cole��o no filtro sem
					 * nenhum conector Caso contr�rio verifica qual a posi��o do
					 * iterator para adicionar o id com o conector correto
					 */
					if (colecaoFuncionalidadesPermitidas.size() == 1) {
						filtroGrupoFuncionalidadeOperacao
								.adicionarParametro(new ParametroSimples(
										FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_ID,
										funcionalidadePermitida.getId()));
					} else {
						// Caso seja a primeira funcionalidade adiciona o id com
						// o conector "OR"
						// e informa quantas funcionalidades vai ter para inseri
						// os parenteses
						if (cont == 1) {
							filtroGrupoFuncionalidadeOperacao
									.adicionarParametro(new ParametroSimples(
											FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_ID,
											funcionalidadePermitida.getId(),
											FiltroParametro.CONECTOR_OR,
											colecaoFuncionalidadesPermitidas
													.size()));
							cont++;
						} else {
							/*
							 * Caso seja a �ltima funcionalidade da cole��o
							 * adiciona o id da funcionalidade sem conector Caso
							 * contr�rio adiciona o id da funcionalidade com o
							 * conector "OR"
							 */
							if (cont == colecaoFuncionalidadesPermitidas.size()) {
								filtroGrupoFuncionalidadeOperacao
										.adicionarParametro(new ParametroSimples(
												FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_ID,
												funcionalidadePermitida.getId()));
								cont++;
							} else {
								filtroGrupoFuncionalidadeOperacao
										.adicionarParametro(new ParametroSimples(
												FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_ID,
												funcionalidadePermitida.getId(),
												ParametroSimples.CONECTOR_OR));
								cont++;
							}
						}
					}
				}
			}

			// Inicializa o contador para auxiliar com a cole��o de grupos
			cont = 1;

			// Cria oa iterator dos grupos do usu�rio
			Iterator iteratorGruposUsuario = colecaoGruposUsuario.iterator();

			// La�o para adicionar os ids dos grupos no filtro
			while (iteratorGruposUsuario.hasNext()) {
				// Recupera o grupo da cole��o
				Grupo grupoUsuario = (Grupo) iteratorGruposUsuario.next();

				/*
				 * Caso a cole��o de grupos do usu�rio n�o esteja vazia adicona
				 * os ids dos grupos do usu�rio no filtro para pesquisar as
				 * permiss�es para a opera��o
				 */
				if (colecaoGruposUsuario.size() == 1) {
					filtroGrupoFuncionalidadeOperacao
							.adicionarParametro(new ParametroSimples(
									FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
									grupoUsuario.getId()));
				} else {
					// Caso seja o primeiro grupo adiciona o id com o conector
					// "OR"
					// e informa quantos grupos vai ter para inserir os
					// parenteses
					if (cont == 1) {
						filtroGrupoFuncionalidadeOperacao
								.adicionarParametro(new ParametroSimples(
										FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
										grupoUsuario.getId(),
										FiltroParametro.CONECTOR_OR,
										colecaoGruposUsuario.size()));
						cont++;
					} else {
						/*
						 * Caso seja o �ltimo grupo da cole��o adiciona o id do
						 * grupo sem conector Caso contr�rio adiciona o id do
						 * grupo com o conector "OR"
						 */
						if (cont == colecaoGruposUsuario.size()) {
							filtroGrupoFuncionalidadeOperacao
									.adicionarParametro(new ParametroSimples(
											FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
											grupoUsuario.getId()));
							cont++;
						} else {
							filtroGrupoFuncionalidadeOperacao
									.adicionarParametro(new ParametroSimples(
											FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,
											grupoUsuario.getId(),
											ParametroSimples.CONECTOR_OR));
							cont++;
						}
					}
				}
			}

			// Pesquisa as permiss�es do usu�rio
			Collection permissoes = getControladorUtil().pesquisar(
					filtroGrupoFuncionalidadeOperacao,
					GrupoFuncionalidadeOperacao.class.getName());

			/*
			 * Caso exista permiss�es para o usu�rio acessar a opera��o pesquisa
			 * as restri��es da opera��o para o usu�rio
			 */
			if (permissoes != null && !permissoes.isEmpty()) {
				// Cria o filtro para pesquisar as restri��es do usu�rio
				// seta no filtro o c�digo do usu�rio da funcionalidade e da
				// opera��o
				FiltroUsuarioGrupoRestricao filtroUsuarioGrupoRestricao = new FiltroUsuarioGrupoRestricao();
				filtroUsuarioGrupoRestricao
						.adicionarParametro(new ParametroSimples(
								FiltroUsuarioGrupoRestricao.USUARIO_ID,
								usuarioLogado.getId()));
				filtroUsuarioGrupoRestricao
						.adicionarParametro(new ParametroSimples(
								FiltroUsuarioGrupoRestricao.OPERACAO_ID,
								operacao.getId()));

				// La�o para adicionar os ids dos grupos no filtro
				while (iteratorGruposUsuario.hasNext()) {
					// Recupera o grupo da cole��o
					Grupo grupoUsuario = (Grupo) iteratorGruposUsuario.next();

					/*
					 * Caso a cole��o de grupos do usu�rio n�o esteja vazia
					 * adicona os ids dos grupos do usu�rio no filtro para
					 * pesquisar as restri��es para a opera��o
					 */
					if (colecaoGruposUsuario.size() == 1) {
						filtroUsuarioGrupoRestricao
								.adicionarParametro(new ParametroSimples(
										FiltroUsuarioGrupoRestricao.GRUPO_ID,
										grupoUsuario.getId()));
					} else {
						// Caso seja o primeiro grupo adiciona o id com o
						// conector "OR"
						// e informa quantos grupos vai ter para inserir os
						// parenteses
						if (cont == 1) {
							filtroUsuarioGrupoRestricao
									.adicionarParametro(new ParametroSimples(
											FiltroUsuarioGrupoRestricao.GRUPO_ID,
											grupoUsuario.getId(),
											FiltroParametro.CONECTOR_OR,
											colecaoGruposUsuario.size()));
							cont++;
						} else {
							/*
							 * Caso seja o �ltimo grupo da cole��o adiciona o id
							 * do grupo sem conector Caso contr�rio adiciona o
							 * id do grupo com o conector "OR"
							 */
							if (cont == colecaoGruposUsuario.size()) {
								filtroUsuarioGrupoRestricao
										.adicionarParametro(new ParametroSimples(
												FiltroUsuarioGrupoRestricao.GRUPO_ID,
												grupoUsuario.getId()));
								cont++;
							} else {
								filtroUsuarioGrupoRestricao
										.adicionarParametro(new ParametroSimples(
												FiltroUsuarioGrupoRestricao.GRUPO_ID,
												grupoUsuario.getId(),
												ParametroSimples.CONECTOR_OR));
								cont++;
							}
						}
					}
				}

				// Inicializa o contador para auxiliar com a cole��o de
				// funcionalidades permitidas.
				cont = 1;

				// Caso a cole��o de funcionalidades permitidas n�o esteja
				// vazia.
				if (colecaoFuncionalidadesPermitidas != null
						&& !colecaoFuncionalidadesPermitidas.isEmpty()) {
					// Cria o iterator das funcionalidades permitidas.
					Iterator<Funcionalidade> iteratorFuncionalidadesPermitidas = colecaoFuncionalidadesPermitidas
							.iterator();

					// La�o para adicionar os ids das funcionalidades permitidas
					// no filtro.
					while (iteratorFuncionalidadesPermitidas.hasNext()) {
						// Recupera a funcionalidade do iterator
						Funcionalidade funcionalidadePermitida = iteratorFuncionalidadesPermitidas
								.next();

						/*
						 * Caso a cole��o possua uma �nica funcionalidade
						 * permitida para o usu�rio adiciona o id da cole��o no
						 * filtro sem nenhum conector. Caso contr�rio verifica
						 * qual a posi��o do iterator para adicionar o id com o
						 * conector correto para pesquisar as restri��es da
						 * opera��o.
						 */
						if (colecaoFuncionalidadesPermitidas.size() == 1) {
							filtroUsuarioGrupoRestricao
									.adicionarParametro(new ParametroSimples(
											FiltroUsuarioGrupoRestricao.FUNCIONALIDADE_ID,
											funcionalidadePermitida.getId()));
						} else {
							// Caso seja a primeira funcionalidade adiciona o id
							// com o conector "OR"
							// e informa quantas funcionalidades vai ter para
							// inserir os parenteses.
							if (cont == 1) {
								filtroUsuarioGrupoRestricao
										.adicionarParametro(new ParametroSimples(
												FiltroUsuarioGrupoRestricao.FUNCIONALIDADE_ID,
												funcionalidadePermitida.getId(),
												FiltroParametro.CONECTOR_OR,
												colecaoFuncionalidadesPermitidas
														.size()));
								cont++;
							} else {
								/*
								 * Caso seja a �ltima funcionalidade da cole��o
								 * adiciona o id da funcionalidade sem conector.
								 * Caso contr�rio adiciona o id do grupo com o
								 * conector "OR".
								 */
								if (cont == colecaoFuncionalidadesPermitidas
										.size()) {
									filtroUsuarioGrupoRestricao
											.adicionarParametro(new ParametroSimples(
													FiltroUsuarioGrupoRestricao.FUNCIONALIDADE_ID,
													funcionalidadePermitida
															.getId()));
									cont++;
								} else {
									filtroUsuarioGrupoRestricao
											.adicionarParametro(new ParametroSimples(
													FiltroUsuarioGrupoRestricao.FUNCIONALIDADE_ID,
													funcionalidadePermitida
															.getId(),
													ParametroSimples.CONECTOR_OR));
									cont++;
								}
							}
						}
					}
				}

				// Pesquisa as restri��es do usu�rio para a opera��o solicitada
				Collection restricoes = getControladorUtil().pesquisar(
						filtroUsuarioGrupoRestricao,
						UsuarioGrupoRestricao.class.getName());

				/*
				 * Caso o n� de restri��es for menor que o n� de permiss�es seta
				 * a flag para indicar que o usu�rio tem acesso a opera��o
				 */
				if (restricoes.size() < permissoes.size()) {
					retorno = true;
				}
			}
		}

		// Retorna uma flag indicando se o usu�rio tem acesso a opera��o
		return retorno;
	}

	/**
	 * [UC0285] - Manter Opera��o
	 * 
	 * Met�do respons�vel por atualizar uma opera��o no sistema e os
	 * relacionamentos entre a tabela e a opera��o
	 * 
	 * [SB0001] - Atualizar Opera��o
	 * 
	 * @author Pedro Alexandre
	 * @date 02/08/2006
	 * 
	 * @param operacao
	 * @param colecaoOperacaoTabela
	 * @throws ControladorException
	 */
	public void atualizarOperacao(Operacao operacao,
			Collection<OperacaoTabela> colecaoOperacaoTabela,
			Usuario usuarioLogado) throws ControladorException {

		/*
		 * [FS0009] - Verificar preenchimento dos campos
		 */
		if (operacao.getDescricao() == null
				|| operacao.getDescricao().trim().equals("")) {
			throw new ControladorException("atencao.naoinformado", null,
					"Descri��o");
		}

		if (operacao.getDescricaoAbreviada() == null
				|| operacao.getDescricaoAbreviada().trim().equals("")) {
			throw new ControladorException("atencao.naoinformado", null,
					"Descri��o Abreviada");
		}

		if (operacao.getCaminhoUrl() == null
				|| operacao.getCaminhoUrl().trim().equals("")) {
			throw new ControladorException("atencao.naoinformado", null,
					"Caminho URL");
		}

		if (operacao.getFuncionalidade() == null) {
			throw new ControladorException("atencao.naoinformado", null,
					"Funcionalidade");
		}

		if (operacao.getOperacaoTipo() == null) {
			throw new ControladorException("atencao.naoinformado", null,
					"Tipo de Opera��o");
		}

		/*
		 * [FS0010] - Atualiza��o realizada por outro usu�rio Pesquisa a
		 * opera��o no base de dados e verifica se a opera��o foi atualizada por
		 * outro usu�rio durante esta opera��ode remo��o
		 */
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarParametro(new ParametroSimples(
				FiltroOperacao.ID, operacao.getId()));
		Collection colecaoOperacaoBase = getControladorUtil().pesquisar(
				filtroOperacao, Operacao.class.getName());
		if (colecaoOperacaoBase == null || colecaoOperacaoBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// Recupera a opera��o na base de dados
		Operacao operacaoNaBase = (Operacao) colecaoOperacaoBase.iterator()
				.next();

		/*
		 * Caso a data de ultima altera��o da opera��o na base for posterior a
		 * opera��o que vai ser removida levanta uma exce��o para o usu�rio
		 * informando que a opera��o foi atualizada por outro usu�rio durante a
		 * tentativa de remo��o
		 */
		if (operacaoNaBase.getUltimaAlteracao().after(
				operacao.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		/*
		 * [FS0001] - Verificar exist�ncia da descri��o verifica se j� existe
		 * uma opera��o cadastrada com a descri��o informada na base de dados
		 */
		filtroOperacao.limparListaParametros();
		String descricaoOperacaoNaBase = operacaoNaBase.getDescricao();
		if (!operacao.getDescricao().equalsIgnoreCase(descricaoOperacaoNaBase)) {
			filtroOperacao.adicionarParametro(new ParametroSimples(
					FiltroOperacao.DESCRICAO, operacao.getDescricao()));
			Collection colecaoOperacaoComDescricao = getControladorUtil()
					.pesquisar(filtroOperacao, Operacao.class.getName());
			if (colecaoOperacaoComDescricao != null
					&& !colecaoOperacaoComDescricao.isEmpty()) {
				throw new ControladorException(
						"atencao.descricao_ja_existente", null, operacao
								.getDescricao()
								+ "");
			}
		}

		/*
		 * [FS0002] - Verificar exist�ncia da url verifica se j� existe uma
		 * opera��o cadastrada com a url informada
		 */
		filtroOperacao.limparListaParametros();
		String urlOperacaoNaBase = operacaoNaBase.getCaminhoUrl();
		if (!operacao.getCaminhoUrl().equalsIgnoreCase(urlOperacaoNaBase)) {
			filtroOperacao.adicionarParametro(new ParametroSimples(
					FiltroOperacao.CAMINHO_URL, operacao.getCaminhoUrl()));
			Collection colecaoOperacaoComURL = getControladorUtil().pesquisar(
					filtroOperacao, Operacao.class.getName());
			if (colecaoOperacaoComURL != null
					&& !colecaoOperacaoComURL.isEmpty()) {
				throw new ControladorException("atencao.url_ja_existente",
						null, operacao.getCaminhoUrl() + "");
			}
		}

		/*
		 * [FS0004 - Verificar exist�ncia da funcionalidade] Cria o filtro de
		 * funcionalidade para verificar se existe a funcionalidade informada
		 */
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidade.ID, operacao.getFuncionalidade().getId()));
		Collection colecaoFuncionalidade = getControladorUtil().pesquisar(
				filtroFuncionalidade, Funcionalidade.class.getName());

		// Caso a funcionalidade informada n�o esteja cadastrada no sistema
		// levanta uma exce��o para o cliente
		if (colecaoFuncionalidade == null || colecaoFuncionalidade.isEmpty()) {
			throw new ControladorException(
					"atencao.funcionalidade.inexistente", null, operacao
							.getDescricao());
		}

		// Cria a vari�vel que vai aramzenar o tipo da opera��o
		OperacaoTipo operacaoTipo = null;

		/*
		 * Caso o tipo da opera��o tenha sido informada pesquisa o tipo da
		 * opera��o no sistema Caso contr�rio levanta uma exce��o indicando que
		 * o tipo da opera��o n�o foi informada
		 */
		if (operacao.getOperacaoTipo() != null) {
			FiltroOperacaoTipo filtroOperacaoTipo = new FiltroOperacaoTipo();
			filtroOperacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTipo.ID, operacao.getOperacaoTipo().getId()));
			Collection colecaoOperacaoTipo = getControladorUtil().pesquisar(
					filtroOperacaoTipo, OperacaoTipo.class.getName());

			/*
			 * Caso o tipo da opera��o informada n�o exista levanta uma exce��o
			 * indicando que o tipo da opera��o n�o existe Caso contr�rio
			 * recupera o tipo da opera��o da cole��o pesquisada
			 */
			if (colecaoOperacaoTipo == null || colecaoOperacaoTipo.isEmpty()) {
				throw new ControladorException(
						"atencao.operacao_tipo.inexistente", null, ""
								+ operacao.getOperacaoTipo().getId());
			}
			operacaoTipo = (OperacaoTipo) Util
					.retonarObjetoDeColecao(colecaoOperacaoTipo);
		} else {
			throw new ControladorException(
					"atencao.operacao_tipo.nao.informado", null);
		}

		// Caso o tipo da opera��o informada seja pesquisar
		// verifica o preenchimento do argumento de pesquisa
		if (operacaoTipo.getId().intValue() == OperacaoTipo.PESQUISAR) {

			// Caso o argumento de pesquisa n�o tenha sido informado
			// levanta uma exce��o indicando que o argumento de pesquisa n�o foi
			// informado
			if (operacao.getTabelaColuna() == null) {
				throw new ControladorException(
						"atencao.argumento_pesquisa.nao.informado", null);
			}
			// [FS0005 - Verificar exist�ncia do argumento de pesquisa]
			// Cria o filtro para pesqusiar o argumento de pesquisa
			// informado
			FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
			filtroTabelaColuna.adicionarParametro(new ParametroSimples(
					FiltroTabelaColuna.ID, operacao.getTabelaColuna()
							.getId()));

			// Pesquisa o argumento de pesquisa
			Collection colecaoTabelaColuna = getControladorUtil()
					.pesquisar(filtroTabelaColuna,
							TabelaColuna.class.getName());

			/*
			 * Caso o argumento de pesquisa n�o esteja cadastrado levanta
			 * uma exce��o indicando que o argumento de pesquisa n�o existe
			 * Caso contr�rio recupera o argumento de pesquisa da cole��o
			 */
			if (colecaoTabelaColuna == null
					|| colecaoTabelaColuna.isEmpty()) {
				throw new ControladorException(
						"atencao.argumento_pesquisa.inexistente", null);
			}
			// [FS0013 - Verificar argumento de pesquisa]
			TabelaColuna argumentoPesquisa = (TabelaColuna) Util
					.retonarObjetoDeColecao(colecaoTabelaColuna);

			/*
			 * Caso o argumento de pesquisa informado n�o seja chave
			 * prim�ria levanta uma exce�a� indicando que o argumento de
			 * pesquisa n�o � chave prim�ria da tabela
			 */
			if (argumentoPesquisa.getIndicadorPrimaryKey() == ConstantesSistema.NAO) {
				throw new ControladorException(
						"atencao.argumento_pesquisa.nao.chave.primaria",
						null);
			}

			// Cria o filtro para verificar se j� existe opera��o com o
			// argumento de pesquisa informado
			FiltroOperacao filtroOperacaoComArgumentoPesquisa = new FiltroOperacao();
			filtroOperacaoComArgumentoPesquisa
					.adicionarParametro(new ParametroSimples(
							FiltroOperacao.TABELA_COLUNA_ID,
							argumentoPesquisa.getId()));
			Collection colecaoOperacaoComArgumentoPesquisa = getControladorUtil()
					.pesquisar(filtroOperacaoComArgumentoPesquisa,
							Operacao.class.getName());

			/*
			 * Caso j� existe opera��o com o argumento de pesquisa
			 * informado levanta uma exce��o indicando que j� existe uma
			 * opera��o com o argumento de pesquisa informado
			 */
			if (colecaoOperacaoComArgumentoPesquisa != null
					&& !colecaoOperacaoComArgumentoPesquisa.isEmpty()) {
				Operacao operacaoComArgumentoPesquisa = (Operacao) Util
						.retonarObjetoDeColecao(colecaoOperacaoComArgumentoPesquisa);
				throw new ControladorException(
						"atencao.argumento_pesquisa.ja.associado",
						null, operacaoComArgumentoPesquisa
								.getDescricao());
			}

		} else {
			// Caso o tipo de opera��o n�o seja "pesquisar"
			if (operacaoTipo.getIndicadorAtualiza() == ConstantesSistema.SIM) {

				/*
				 * Caso o usu�rio n�o tenha informado a opera��o de pesquisa
				 * levanta uma exce��o indicando que a opera��o de pesquisa n�o
				 * foi informada Caso contr�rio verifica a exist�ncia da
				 * opera��o de pesquisa
				 */
				if (operacao.getIdOperacaoPesquisa() == null) {
					// throw new
					// ControladorException("atencao.operacao_pesquisa.nao.informado",
					// null);
				} else {
					// [FS0007 - Verificar exist�ncia da opera��o]
					// Cria o filtro para pesquisar a opera��o de pesquisa
					// informada
					FiltroOperacao filtroOperacaoPesquisa = new FiltroOperacao();
					filtroOperacaoPesquisa
							.adicionarParametro(new ParametroSimples(
									FiltroOperacao.ID, operacao
											.getIdOperacaoPesquisa().getId()));
					Collection colecaoOperacaoPesquisa = getControladorUtil()
							.pesquisar(filtroOperacaoPesquisa,
									Operacao.class.getName());

					// Caso a opera��o de pesquisa n�o esteja cadastrada
					// levanta uma exce��o indicando que a opera��o de pesquisa
					// n�o existe
					if (colecaoOperacaoPesquisa == null
							|| colecaoOperacaoPesquisa.isEmpty()) {
						throw new ControladorException(
								"atencao.operacao_pesquisa.inexistente", null);
					}
				}
			}
		}

		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_OPERACAO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacaoRegistrarTransacao = new Operacao();
		operacaoRegistrarTransacao.setId(Operacao.OPERACAO_OPERACAO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacaoRegistrarTransacao);

		operacao.setOperacaoEfetuada(operacaoEfetuada);
		operacao.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------

		// Seta a data de ultima altera��o e atualiza a opera��o
		operacao.setUltimaAlteracao(new Date());
		getControladorUtil().atualizar(operacao);

		/*
		 * Cria o filtro para recuperar os relacionamentos entre a opera��o e a
		 * tabela
		 */
		FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
		filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
				FiltroOperacaoTabela.OPERACAO_ID, operacao.getId()));
		Collection<OperacaoTabela> colecaoOperacaoTabelaNaBase = getControladorUtil()
				.pesquisar(filtroOperacaoTabela, OperacaoTabela.class.getName());

		/*
		 * Caso exista tabela relacionadas com a opera��o remove os
		 * relacionamentos da base caso o relacionamento tenha sido removido
		 * pelo usu�rio ou remove da cole��o que dos novos relacionamentos
		 * marcados caso j� exista na base
		 */
		if (colecaoOperacaoTabelaNaBase != null
				&& !colecaoOperacaoTabelaNaBase.isEmpty() 
				&& colecaoOperacaoTabela!=null 
				&& !colecaoOperacaoTabela.isEmpty()) {
			// Cria o iterator do relacionamento entre tabela e opera��o
			Iterator<OperacaoTabela> iteratorOperacaoTabelaNaBase = colecaoOperacaoTabelaNaBase
					.iterator();

			/*
			 * La�o para remover os relacionamentos entre tabela e opera��o que
			 * foram removidos pelo usu�rio
			 */
			while (iteratorOperacaoTabelaNaBase.hasNext()) {
				// Recupera o relacionamento da base de dados do iterator
				OperacaoTabela operacaoTabelaNaBase = iteratorOperacaoTabelaNaBase
						.next();

				/*
				 * Caso a cole��o informada pelo usu�rio n�o tenha o
				 * relacionamento que est� na na base de dados remove o
				 * relacionamento da base de dados
				 */
				if (!colecaoOperacaoTabela.contains(operacaoTabelaNaBase)) {
					// ------------ REGISTRAR TRANSA��O ----------------
					operacaoTabelaNaBase.setOperacaoEfetuada(operacaoEfetuada);
					operacaoTabelaNaBase.adicionarUsuario(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
					registradorOperacao.registrarOperacao(operacaoTabelaNaBase);
					// ------------ REGISTRAR TRANSA��O ----------------

					// Remove o relacionamento entre tabela e opera��o
					getControladorUtil().remover(operacaoTabelaNaBase);
				} else {
					colecaoOperacaoTabela.remove(operacaoTabelaNaBase);
				}
			}
		}

		/*
		 * Caso a cole��o de relacionamento entre tabela e opera��o ainda tenha
		 * algum relacionamento que foi informado pelo usu�rio e que ainda n�o
		 * esteja cadastrado na base de dados inseri o relacionamento na base de
		 * dados
		 */
		if (colecaoOperacaoTabela != null && !colecaoOperacaoTabela.isEmpty()) {
			// Cria o iterator para inserir os relacionamentos entre tabela e
			// opera��o
			Iterator<OperacaoTabela> iteratorOperacaoTabelaInformado = colecaoOperacaoTabela
					.iterator();

			// La�o para inserir o relacionamento entre tabela e opera��o
			while (iteratorOperacaoTabelaInformado.hasNext()) {
				// Recupera o relacionamento informado pelo usu�rio
				OperacaoTabela operacaoTabelaInformado = iteratorOperacaoTabelaInformado
						.next();

				// ------------ REGISTRAR TRANSA��O ----------------
				operacaoTabelaInformado.setOperacaoEfetuada(operacaoEfetuada);
				operacaoTabelaInformado.adicionarUsuario(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(operacaoTabelaInformado);
				// ------------ REGISTRAR TRANSA��O ----------------

				// Inseri o relacionamento entre tabela e opera��o
				getControladorUtil().inserir(operacaoTabelaInformado);
			}
		}
	}

	/**
	 * [UC0285] - Manter Opera��o
	 * 
	 * Met�do respons�vel por remover uma opera��o no sistema e os
	 * relacionamentos entre a tabela e a opera��o
	 * 
	 * [SB0002] - Excluir Opera��o
	 * 
	 * @author Pedro Alexandre
	 * @date 02/08/2006
	 * 
	 * @param idsOperacao
	 * @throws ControladorException
	 */
	public void removerOperacao(String[] idsOperacao, Usuario usuarioLogado)
			throws ControladorException {

		// ------------ REGISTRAR TRANSA��O ----------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_OPERACAO_REMOVER,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacaoRegistrarTransacao = new Operacao();
		operacaoRegistrarTransacao.setId(Operacao.OPERACAO_OPERACAO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacaoRegistrarTransacao);
		// ------------ REGISTRAR TRANSA��O ----------------

		// La�o para remover todas as opera��es selecionadas
		for (int i = 0; i < idsOperacao.length; i++) {

			// Cria o filtro para pesquisar a opera��o que vai ser removida
			FiltroOperacao filtroOperacao = new FiltroOperacao();
			filtroOperacao.adicionarParametro(new ParametroSimples(
					FiltroOperacao.ID, idsOperacao[i]));
			Collection colecaoOperacao = getControladorUtil().pesquisar(
					filtroOperacao, Operacao.class.getSimpleName());

			/*
			 * Caso a pesquisa retorne a opera��o selecionada para remo��o
			 * recupera a opera��o da cole�a� de pesquisa para ser removida Caso
			 * contr�rio passa para a pr�xima opera��o no array
			 */
			if (colecaoOperacao != null && !colecaoOperacao.isEmpty()) {
				// Recupera a opera��o que vai ser removida
				Operacao operacao = (Operacao) colecaoOperacao.iterator()
						.next();

				// Cria o filtro para recuperar os relacionamentos entre a
				// opera��o e as tabelas da opera��o
				FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
				filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
						FiltroOperacaoTabela.OPERACAO_ID, operacao.getId()));
				Collection colecaoOperacaoTabela = getControladorUtil()
						.pesquisar(filtroOperacaoTabela,
								OperacaoTabela.class.getSimpleName());

				/*
				 * Caso exista relacionamentos entre opera��o e tabela
				 * cadastradas para a opera��o que vai ser removida, remove os
				 * relacionamentos antes de remover a opera��o
				 */
				if (colecaoOperacaoTabela != null
						&& !colecaoOperacaoTabela.isEmpty()) {

					// Coloca a cole��o de TabelaOPeracao no iterator
					Iterator<OperacaoTabela> iteratorOperacaoTabela = colecaoOperacaoTabela
							.iterator();

					// La�o para remover todos os relacionamentos TabelaOperacao
					while (iteratorOperacaoTabela.hasNext()) {
						// Recupera o relacionamento da cole��o
						OperacaoTabela operacaoTabela = iteratorOperacaoTabela
								.next();

						// ------------ REGISTRAR TRANSA��O ----------------
						operacaoTabela.setOperacaoEfetuada(operacaoEfetuada);
						operacaoTabela.adicionarUsuario(usuarioLogado,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
						registradorOperacao.registrarOperacao(operacaoTabela);
						// ------------ REGISTRAR TRANSA��O ----------------

						// Remove o relacionamento
						getControladorUtil().remover(operacaoTabela);
					}
				}

				// ------------ REGISTRAR TRANSA��O ----------------
				operacao.setOperacaoEfetuada(operacaoEfetuada);
				operacao.adicionarUsuario(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(operacao);
				// ------------ REGISTRAR TRANSA��O ----------------

				// Remove a opera��o selecionada
				getControladorUtil().remover(operacao);
			}
		}
	}

	/**
	 * Met�do respons�vel por registrar a funcionalidade que o usu�rio est�
	 * acessando no momento
	 * 
	 * @author Pedro Alexandre
	 * @date 08/08/2006
	 * 
	 * @param usuarioLogado
	 * @param funcionalidade
	 * @throws ControladorException
	 */
	private void registrarFuncionalidadeAcessada(Usuario usuarioLogado,
			Funcionalidade funcionalidade) throws ControladorException {

		/*
		 * Caso a funcionalidade seja ponto de entrada registra o acesso a
		 * funcionalidade pelo usu�rio (incluir na tabela UsuarioFavorito)
		 */
		if (funcionalidade.getIndicadorPontoEntrada().equals(
				ConstantesSistema.SIM)) {

			// Recupera o n� m�ximo de funcionalidades registradas para �ltimos
			// acessos
			SistemaParametro sistemaParametros = getControladorUtil()
					.pesquisarParametrosDoSistema();
			int numeroMaximoFavorito = sistemaParametros
					.getNumeroMaximoFavorito();

			// Cria o objeto UsuarioFavorito que representa a funcionalidade
			// acessada pelo usu�rio
			UsuarioFavorito usuarioFavorito = 
				new UsuarioFavorito(
					new UsuarioFavoritoPK(usuarioLogado.getId(), funcionalidade.getId()), 
					new Short("1"), 
					new Date(),
					funcionalidade, 
					usuarioLogado);

			// Cria o filtro para pesquisar os �ltimos acessos do usu�rio
			FiltroUsuarioFavorito filtroUsuarioFavoritoCadastrados = new FiltroUsuarioFavorito();
			filtroUsuarioFavoritoCadastrados.adicionarParametro(
				new ParametroSimples(
					FiltroUsuarioFavorito.USUARIO_ID, 
					usuarioLogado.getId()));
			
			filtroUsuarioFavoritoCadastrados.adicionarParametro(
				new ParametroSimples(
					FiltroUsuarioFavorito.INDICADOR_FAVORITO_ULTIMO_ACESSADO,
					ConstantesSistema.SIM));
			
			filtroUsuarioFavoritoCadastrados.setCampoOrderBy(FiltroUsuarioFavorito.ULTIMA_ALTERACAO);
			filtroUsuarioFavoritoCadastrados.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
			
			Collection<UsuarioFavorito> colecaoUsuarioFavoritosCadastrados = getControladorUtil()
					.pesquisar(filtroUsuarioFavoritoCadastrados,
							UsuarioFavorito.class.getName());

			/*
			 * Caso a cole��o de ultimos acessos do usu�rio esteja vazia
			 * registra o primeiro acesso do usu�rio no sistema
			 */
			if (colecaoUsuarioFavoritosCadastrados == null
					|| colecaoUsuarioFavoritosCadastrados.isEmpty()) {
				getControladorUtil().inserir(usuarioFavorito);
			} else {
				/*
				 * Caso j� exista acessos cadatrado para o usu�rio que est�
				 * logado e o n� de acessos registrados seja menor que o m�ximo
				 * permitido verifica se j� existe registro para a
				 * funcionalidade acessada se existir atualiza a data de �ltimo
				 * acesso se n�o existir inseri o registro na tabela
				 * UsuarioFavorito
				 */
				if (colecaoUsuarioFavoritosCadastrados.size() < numeroMaximoFavorito) {
					if (!colecaoUsuarioFavoritosCadastrados
							.contains(usuarioFavorito)) {
						getControladorUtil().inserir(usuarioFavorito);
					} else {
						getControladorUtil().atualizar(usuarioFavorito);
					}
				} else {
					/*
					 * Caso o n� de de acessos registrados seja igual ao n�
					 * m�ximo permitido verifica se a funcionalidade acessada j�
					 * est� registrada para este usu�rio Caso j� esteja
					 * registrada para o usu�rio atualiza apenas a data de
					 * �ltima altera��o Caso n�o esteja registrada remove o
					 * registro com menor data de �ltimo acesso e adiciona o
					 * novo acesso.
					 */
					if (colecaoUsuarioFavoritosCadastrados.size() == numeroMaximoFavorito) {
						/*
						 * Caso o acesso feito pelo usu�rio logado n�o esteja
						 * cadastrado remove o acesso com a data de ultimo
						 * acesso menor
						 */
						if (!colecaoUsuarioFavoritosCadastrados
								.contains(usuarioFavorito)) {

							// Recupera o acesso que foi acessado mais
							// antigamente para ser removido
							UsuarioFavorito usuarioFavoritoUltimoAcessado = colecaoUsuarioFavoritosCadastrados
									.iterator().next();
							getControladorUtil().remover(
									usuarioFavoritoUltimoAcessado);

							// Inseri o acesso mais recente a funcionalidade
							getControladorUtil().inserir(usuarioFavorito);
						} else {
							// Atualiza a data de ultimo acesso
							getControladorUtil().atualizar(usuarioFavorito);
						}
					} else {
						// Caso o n� de acessos for maior que o permitido
					}
				}
			}
		}
	}

	/**
	 * Met�do respons�vel por verificar se o usu�rio tem abrang�ncia sobre a
	 * opera��o e o n�vel de informa��o que est�o sendo informados.
	 * 
	 * [UC0XXX] Verificar Acesso Abrang�ncia
	 * 
	 * @author Pedro Alexandre
	 * @date 08/11/2006
	 * 
	 * @param abrangencia
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarAcessoAbrangencia(Abrangencia abrangencia)
			throws ControladorException {
		
		FiltroUsuario filtroUsuario = new FiltroUsuario();
        
        filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID,abrangencia.getUsuario().getId()));
        
        Collection usuarios = getControladorUtil().pesquisar(filtroUsuario,Usuario.class.getName());
        
        Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(usuarios);
		
        abrangencia.setUsuario(usuario);
		
		this.carregarAbrangencia(abrangencia);

		boolean retorno = true;
		

		UsuarioAbrangencia usuarioAbrangencia = abrangencia.getUsuario()
				.getUsuarioAbrangencia();

		// DADOS INFORMADOS PARA EXECUTAR A OPERA��O
		GerenciaRegional gerenciaRegionalInformada = abrangencia
				.getGerenciaRegional();
		UnidadeNegocio unidadeNegocioInformada = abrangencia
				.getUnidadeNegocio();
		Localidade eloPoloInformado = abrangencia.getEloPolo();
		Localidade localidadeInformada = abrangencia.getLocalidade();

		// ABRANGENCIA DO USUARIO
		GerenciaRegional gerenciaRegionalUsuario = abrangencia.getUsuario()
				.getGerenciaRegional();
		UnidadeNegocio unidadeNegocioUsuario = abrangencia.getUsuario().getUnidadeNegocio();
		Localidade eloPoloUsuario = abrangencia.getUsuario().getLocalidadeElo();
		Localidade localidadeUsuario = abrangencia.getUsuario().getLocalidade();

		Integer nivelAbrangencia = usuarioAbrangencia.getId();

		switch (nivelAbrangencia.intValue()) {
			case UsuarioAbrangencia.ESTADO_INT :
				retorno = true;
				break;

			case UsuarioAbrangencia.GERENCIA_REGIONAL_INT :

				if (gerenciaRegionalInformada != null) {
					if (gerenciaRegionalUsuario.getId().intValue() == gerenciaRegionalInformada
							.getId().intValue()) {
						retorno = true;
					} else {
						retorno = false;
					}
				} else {
					retorno = false;
				}

				break;

			case UsuarioAbrangencia.UNIDADE_NEGOCIO_INT :
				if (unidadeNegocioInformada != null && unidadeNegocioUsuario!=null) {
					if (unidadeNegocioUsuario.getId().intValue() == unidadeNegocioInformada
							.getId().intValue()) {
						retorno = true;
					} else {
						retorno = false;
					}
				} else {
					retorno = false;
				}

				break;

			case UsuarioAbrangencia.ELO_POLO_INT :
				if (eloPoloInformado != null) {
					if (eloPoloUsuario.getId().intValue() == eloPoloInformado
							.getId().intValue()) {
						retorno = true;
					} else {
						retorno = false;
					}
				} else {
					retorno = false;
				}
				break;

			case UsuarioAbrangencia.LOCALIDADE_INT :
				if (localidadeInformada != null) {
					if (localidadeUsuario.getId().intValue() == localidadeInformada
							.getId().intValue()) {
						retorno = true;
					} else {
						retorno = false;
					}
				} else {
					retorno = false;
				}
				break;

		}
		return retorno;
	}

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 13/11/2006
	 * 
	 * @param abrangencia
	 */
	private void carregarAbrangencia(Abrangencia abrangencia)
			throws ControladorException {

		GerenciaRegional gerenciaRegional = abrangencia.getGerenciaRegional();
		UnidadeNegocio unidadeNegocio = abrangencia.getUnidadeNegocio();
		Localidade eloPolo = abrangencia.getEloPolo();
		Localidade localidade = abrangencia.getLocalidade();
		Imovel imovel = abrangencia.getImovel();
		SetorComercial setorComercial = abrangencia.getSetorComercial();
		Quadra quadra = abrangencia.getQuadra();

		String consulta = null;

		try {
			if (gerenciaRegional != null) {
				// para ger�ncia n�o precisa carregar nada
			} else if (unidadeNegocio != null) {
				consulta = "from UnidadeNegocio as unidadeNegocio "
						+ "inner join fetch unidadeNegocio.gerenciaRegional gerenciaRegional "
						+ "where unidadeNegocio.id = " + unidadeNegocio.getId();

				UnidadeNegocio unidadeNegocioPesquisado = (UnidadeNegocio) this.repositorioAcesso
						.pesquisarObjetoAbrangencia(consulta);
				abrangencia.setGerenciaRegional(unidadeNegocioPesquisado
						.getGerenciaRegional());

			} else if (eloPolo != null) {
				consulta = "from Localidade as elo "
						+ "inner join fetch elo.unidadeNegocio unidadeNegocio "
						+ "inner join fetch unidadeNegocio.gerenciaRegional gerenciaRegional "
						+ "where elo.localidade.id = " + eloPolo.getId();

				Localidade eloPoloPesquisado = (Localidade) this.repositorioAcesso
						.pesquisarObjetoAbrangencia(consulta);
				abrangencia.setUnidadeNegocio(eloPoloPesquisado
						.getUnidadeNegocio());
				abrangencia.setGerenciaRegional(eloPoloPesquisado
						.getUnidadeNegocio().getGerenciaRegional());

			} else if (localidade != null) {
				
				consulta = "from Localidade as localidade "
						+ "inner join fetch localidade.localidade elo "
						+ "inner join fetch elo.unidadeNegocio unidadeNegocio "
						+ "inner join fetch unidadeNegocio.gerenciaRegional gerenciaRegional "
						+ "where localidade.id = " + localidade.getId();

				Localidade localidadePesquisada = 
					(Localidade) this.repositorioAcesso.pesquisarObjetoAbrangencia(consulta);
				
				abrangencia.setEloPolo(localidadePesquisada.getLocalidade());
				abrangencia.setUnidadeNegocio(localidadePesquisada.getLocalidade().getUnidadeNegocio());
				abrangencia.setGerenciaRegional(
					localidadePesquisada.getLocalidade().getUnidadeNegocio().getGerenciaRegional());

			} else if (imovel != null
					&& imovel.getId() != null) {
				consulta = "from Imovel as imovel "
						+ "inner join fetch imovel.localidade localidade "
						+ "inner join fetch localidade.localidade elo "
						+ "inner join fetch elo.unidadeNegocio unidadeNegocio "
						+ "inner join fetch unidadeNegocio.gerenciaRegional gerenciaRegional "
						+ "where imovel.id = " + imovel.getId();

				Imovel imovelPesquisado = (Imovel) this.repositorioAcesso
						.pesquisarObjetoAbrangencia(consulta);
				
				abrangencia.setLocalidade(imovelPesquisado.getLocalidade());
				abrangencia.setEloPolo(imovelPesquisado.getLocalidade().getLocalidade());
				abrangencia.setUnidadeNegocio(
					imovelPesquisado.getLocalidade().getLocalidade().getUnidadeNegocio());
				abrangencia.setGerenciaRegional(
					imovelPesquisado.getLocalidade().getLocalidade().getUnidadeNegocio().getGerenciaRegional());

			} else if (setorComercial != null) {
				
				consulta = "from SetorComercial as setorComercial "
						+ "inner join fetch setorComercial.localidade localidade "
						+ "inner join fetch localidade.localidade elo "
						+ "inner join fetch elo.unidadeNegocio unidadeNegocio "
						+ "inner join fetch unidadeNegocio.gerenciaRegional gerenciaRegional "
						+ "where setorComercial.id = " + setorComercial.getId();

				SetorComercial setorComercialPesquisado = 
					(SetorComercial) this.repositorioAcesso.pesquisarObjetoAbrangencia(consulta);
				
				abrangencia.setLocalidade(setorComercialPesquisado.getLocalidade());
				abrangencia.setEloPolo(setorComercialPesquisado.getLocalidade().getLocalidade());
				abrangencia.setUnidadeNegocio(
					setorComercialPesquisado.getLocalidade().getLocalidade().getUnidadeNegocio());
				abrangencia.setGerenciaRegional(
					setorComercialPesquisado.getLocalidade().getLocalidade().getUnidadeNegocio().getGerenciaRegional());

			} else if (quadra != null) {
				consulta = "from Quadra as quadra "
						+ "inner join fetch quadra.setorComercial setorComercial "
						+ "inner join fetch setorComercial.localidade localidade "
						+ "inner join fetch localidade.localidade elo "
						+ "inner join fetch elo.unidadeNegocio unidadeNegocio "
						+ "inner join fetch unidadeNegocio.gerenciaRegional gerenciaRegional "
						+ "where quadra.id = " + quadra.getId();

				Quadra quadraPesquisada = (Quadra) this.repositorioAcesso
						.pesquisarObjetoAbrangencia(consulta);
				
				abrangencia.setSetorComercial(quadraPesquisada.getSetorComercial());
				abrangencia.setLocalidade(quadraPesquisada.getSetorComercial().getLocalidade());
				abrangencia.setEloPolo(quadraPesquisada.getSetorComercial().getLocalidade().getLocalidade());
				
				abrangencia.setUnidadeNegocio(
					quadraPesquisada.getSetorComercial().getLocalidade().getLocalidade().getUnidadeNegocio());
				
				abrangencia.setGerenciaRegional(
					quadraPesquisada.getSetorComercial().getLocalidade().getLocalidade().getUnidadeNegocio().getGerenciaRegional());

			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro no Hibernate", e);
		}
	}
	
	/**
	 * Pesquisa os favoritos do usuario
	 * 
	 * @author: Rafael Pinto
	 * @date: 01/06/2009
	 */	
	public Collection pesquisarUsuarioFavorito(Integer idUsuario)
		throws ControladorException {
			
		try {
			return this.repositorioAcesso.pesquisarUsuarioFavorito(idUsuario);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC0407]-Filtrar Im�veis para Inserir ou Manter Conta
	 * [FS0011]-Verificar a abrang�ncia do c�digo do usu�rio
	 * 
	 * Verifica se existe localidade que esteja fora da abrang�ncia do usu�rio 
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2009
	 */
	public boolean existeLocalidadeForaDaAbrangenciaUsuario(FiltrarImovelInserirManterContaHelper filtro,
			Integer nivelAbrangencia,Usuario usuarioLogado)throws ControladorException {
		try {
			boolean retorno = false;
			Collection colecaoLoc =  this.repositorioAcesso.pesquisarLocalidadeForaDaAbrangenciaUsuario(filtro,nivelAbrangencia,usuarioLogado);
			
			if(colecaoLoc != null && !colecaoLoc.isEmpty()){
				 retorno = true;
			}
			return retorno;
			 
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	
	/**
	 * [UC0288] - Validar Senha Forte
	 * 
	 * Met�do que verifica se a senha informada est� de acordo com o padr�o de
	 * seguran�a adotado.
	 * 
	 * @author Hugo Amorim	
	 * @date 08/12/2009
	 * 
	 * @param senha
	 * @throws ControladorException
	 */
	public void validarSenhaForte(String senha) throws ControladorException {

		// Testa se senha possui letras minisculas, maiusculas e numeros
		Pattern pattern1 = Pattern.compile(".*[0-9].*");
		Pattern pattern2 = Pattern.compile(".*[a-zA-Z].*");


		// Executa testes de todas as express�es
		Matcher matcher1;
		Matcher matcher2;
		
		matcher1 = pattern1.matcher(senha);
		matcher2 = pattern2.matcher(senha);


		boolean testeTamanhoSenhaInvalido = senha.length() < 6
				&& senha.length() <= 8;

		// Testa se falhou em algum teste.
		if (!matcher1.matches() || !matcher2.matches() || testeTamanhoSenhaInvalido) {
			throw new ControladorException(
					"atencao.senha.invalida",
					null,
					"Senha forte deve ter no m�nimo 6 e no maximo 8 caracteres "
							+ "e deve conter letras e n�meros Informe outra senha.");
		}

		// Testa se senha tem seguencias de repetidos
		int count = 0;
		String letraAnterior = "";
		for (int i = 0; i < senha.toCharArray().length; i++) {
			if(count>=2){
				break;
			}
			String letra = String.valueOf(senha.toCharArray()[i]);

			if (letra.equals(letraAnterior)) {
				letraAnterior = letra;
				count++;
			} else {
				letraAnterior = letra;
				count = 0;
			}
		}

		if (count >= 2) {
			throw new ControladorException("atencao.senha.invalida", null,
					"Senha est� fora do padr�o de seguran�a adotado pela empresa. Informe outra.");
		}

	}
	
	/**
	 * [UC0288] - Validar Senha Forte
	 * 
	 * Met�do que verifica se a senha informada est� de acordo com o padr�o de
	 * seguran�a adotado.
	 * 
	 * @author Hugo Amorim	
	 * @date 08/12/2009
	 * 
	 * @param senha
	 * @throws ControladorException
	 */
	private void validarBloqueiSenhasAnteriores(Usuario usuarioLogado,
			String novaSenha) throws ControladorException {

		//Testa se senha j� foi usada pelo usuario anteriormente.
		Collection senhasAnteriores;
		try {
			
			String senhaCriptografada =
				Criptografia.encriptarSenha(novaSenha);
			
			
			senhasAnteriores = this.repositorioAcesso.pesquisarSenhasAnterioresUsuario(usuarioLogado);
		
		for (Iterator iterator = senhasAnteriores.iterator(); iterator
				.hasNext();) {
			String senhaAnterior = (String) iterator.next();
			
			if(senhaCriptografada.equalsIgnoreCase(senhaAnterior)){
				throw new ControladorException("atencao.senha.invalida", null,
						"Senha j� informada anteriormente para o usu�rio." +
						" Informe uma nova senha diferente das 3 anteriores." );
			}	
		}
		
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} catch (ErroCriptografiaException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1040] Gerar Relat�rio de Acessos por Usu�rio
	 * 
	 * @author Hugo Leonardo
	 * @date 13/07/2010
	 * 
	 * @param FiltrarRelatorioAcessosUsuariosHelper
	 * 
	 * @return Collection<RelatorioAcessosPorUsuariosHelper>
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcessosPorUsuario(
			FiltrarRelatorioAcessosUsuariosHelper helper)throws ControladorException {
		
		Collection colecaoRetorno = new ArrayList();
		try {
			
			Collection colecaoAcessosPorUsuario =  this.repositorioAcesso.pesquisarRelatorioAcessosPorUsuario(helper);
			
			Iterator iterator = colecaoAcessosPorUsuario.iterator();
			
			while (iterator.hasNext()) {
			
				RelatorioAcessosPorUsuariosHelper relatorioHelper = new RelatorioAcessosPorUsuariosHelper();
				
				Object[] objeto = (Object[]) iterator.next();
				
				
				// unidade Organizacional
				String unidadeOrganizacional = "";
				if ( objeto[0] != null ) {
					unidadeOrganizacional = objeto[0].toString();
					
					relatorioHelper.setUnidadeOrganizacional(unidadeOrganizacional);
				}
				
				// Tipo Usuario
				String usuarioTipo = "";
				if ( objeto[1] != null ) {
					usuarioTipo = objeto[1].toString();
					
					relatorioHelper.setUsuarioTipo( usuarioTipo);
				}
				
				// Usuario
				String usuario = "";
				if ( objeto[2] != null ) {
					usuario = objeto[2].toString();
					
					relatorioHelper.setUsuario( usuario);
				}
				
				// Situa��o Usu�rio
				String situacao = "";
				if ( objeto[3] != null ) {
					situacao = objeto[3].toString();
					
					relatorioHelper.setSituacaoUsuario( situacao);
				}
				
				// Grupo Acesso
				String grupoAcesso = "";
				if ( objeto[4] != null ) {
					grupoAcesso = objeto[4].toString();
					
					relatorioHelper.setGrupoAcesso( grupoAcesso);
				}
				
				// Funcionalidade
				String funcionalidade = "";
				if( objeto[5] != null){
					funcionalidade = objeto[5].toString();
					
					relatorioHelper.setFuncionalidade( funcionalidade);
				}
				
				// Operacao
				String operacao = "";
				if( objeto[6] != null){
					operacao = objeto[6].toString();
					
					relatorioHelper.setOperacao( operacao);
				}
				
				
				// Modulo
				String modulo = "";
				if(objeto[7] != null){
					
					modulo = objeto[7].toString();
					
					relatorioHelper.setModulo( modulo);
				}
				
				// Permissao Especial
				String permissao = "";
				if(objeto[8] != null){
					
					permissao = objeto[8].toString();
					
					relatorioHelper.setPermissaoEspecial( permissao);
				}
				
				colecaoRetorno.add(relatorioHelper);
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return colecaoRetorno;
	}
	
	/**
	 * [UC1040] Gerar Relat�rio de Acessos por Usu�rio
	 * 
	 * @author Hugo Leonardo
	 * @date 13/07/2010
	 * 
	 * @param FiltrarRelatorioAcessosUsuariosHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRelatorioAcessosPorUsuario(
			FiltrarRelatorioAcessosUsuariosHelper filtro) throws ControladorException{
		
		
		try {
			return this.repositorioAcesso.pesquisarTotalRelatorioAcessosPorUsuario(filtro);
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC1039] Gerar Relat�rio de Funcionalidades e Opera��es por Grupo
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper
	 * 
	 * @return Collection<RelatorioFuncionalidadeOperacoesPorGrupoHelper>
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioFuncionalidadeOperacoesPorGrupo(
			FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper helper)throws ControladorException {
		
		Collection colecaoRetorno = new ArrayList();
		try {
			
			Collection colecaoFuncionalidadeOperacoesPorGrupo =  repositorioAcesso.
				pesquisarRelatorioFuncionalidadeOperacoesPorGrupo(helper);
			
			Iterator iterator = colecaoFuncionalidadeOperacoesPorGrupo.iterator();
			
			while (iterator.hasNext()) {
			
				RelatorioFuncionalidadeOperacoesPorGrupoHelper relatorioHelper = new RelatorioFuncionalidadeOperacoesPorGrupoHelper();
				
				Object[] objeto = (Object[]) iterator.next();
				
				// Grupo
				String grupo = "";
				if ( objeto[0] != null ) {
					grupo = objeto[0].toString();
					
					relatorioHelper.setGrupo(grupo);
				}

				// Modulo
				String modulo = "";
				if(objeto[1] != null){
					modulo = objeto[1].toString();
					
					relatorioHelper.setModulo( modulo);
				}
				
				// Funcionalidade
				String funcionalidade = "";
				if( objeto[2] != null){
					funcionalidade = objeto[2].toString();
					
					relatorioHelper.setFuncionalidade( funcionalidade);
				}
				
				// Operacao
				String operacao = "";
				if( objeto[3] != null){
					operacao = objeto[3].toString();
					
					relatorioHelper.setOperacao( operacao);
				}
				
				colecaoRetorno.add(relatorioHelper);
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return colecaoRetorno;
	}
	
	/**
	 * [UC1039] Gerar Relat�rio de Funcionalidades e Opera��es por Grupo
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRelatorioFuncionalidadeOperacoesPorGrupo(
			FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper filtro) throws ControladorException{
		
		try {
			return this.repositorioAcesso.pesquisarTotalRelatorioFuncionalidadeOperacoesPorGrupo(filtro);
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Informa o n�mero total de registros do grupo, auxiliando o
	 * esquema de pagina��o
	 * 
	 * @author Hugo Leonardo
	 * @date 15/07/2010
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return n�mero de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exce��o do reposit�rio
	 */
	public Collection pesquisarGrupos(FiltroGrupo filtroGrupo, Integer numeroPagina)
			throws ControladorException {
		
		try {
			
			return repositorioAcesso.pesquisarGrupos(filtroGrupo, numeroPagina);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * [UC1047] Inserir Controle de Libera��o de Permiss�o Especial
	 * 
	 * Metodo que verifica os dados da tabela e insere um 
	 * Controle de Libera��o de Permiss�o Especial
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 23/07/2010
	 *  
	 * @param controleLiberacaoPermissaoEspecial
	 * @param usuarioLogado
	 * @throws ControladorException
	 */

	public Integer inserirControleLiberacaoPermissaoEspecial(ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecial, Usuario usuarioLogado)
			throws ControladorException {

		// Verifica se todos os campos obrigatorios foram preenchidos

		if ((controleLiberacaoPermissaoEspecial.getFuncionalidade() == null || 
			 controleLiberacaoPermissaoEspecial.getFuncionalidade().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (controleLiberacaoPermissaoEspecial.getPermissaoEspecial() == null || 
			 	    controleLiberacaoPermissaoEspecial.getPermissaoEspecial().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) ) {
			
			throw new ControladorException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Verifica se o campo Funcionalidade foi preenchido

		if (controleLiberacaoPermissaoEspecial.getFuncionalidade() == null
				|| controleLiberacaoPermissaoEspecial.getFuncionalidade().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Funcionalidade");
		}

		// Verifica se o campo Permiss�o Especial

		if (controleLiberacaoPermissaoEspecial.getPermissaoEspecial() == null
				|| controleLiberacaoPermissaoEspecial.getPermissaoEspecial().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Permiss�o Especial");
		}


		FiltroControleLiberacaoPermissaoEspecial filtroControleLiberacaoPermissaoEspecial = new FiltroControleLiberacaoPermissaoEspecial();

		filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ParametroSimples(
				FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE, controleLiberacaoPermissaoEspecial.getFuncionalidade()));
		
		filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ParametroSimples(
				FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL, controleLiberacaoPermissaoEspecial.getPermissaoEspecial()));		
		
		Collection colecaoControleLiberacaoPermissaoEspecial = getControladorUtil().pesquisar(
				filtroControleLiberacaoPermissaoEspecial, ControleLiberacaoPermissaoEspecial.class.getName());

		if (colecaoControleLiberacaoPermissaoEspecial != null && !colecaoControleLiberacaoPermissaoEspecial.isEmpty()) {
			throw new ControladorException("atencao.controle_liberacao_permissao_especial_ja_existe",
					null, "");
		}

		controleLiberacaoPermissaoEspecial.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		controleLiberacaoPermissaoEspecial.setUltimaAlteracao(new Date());
		
		
		//------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL,
				controleLiberacaoPermissaoEspecial.getId(),controleLiberacaoPermissaoEspecial.getId(),
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(controleLiberacaoPermissaoEspecial);
		
		//Fim Registrar Transa��o
		

		Integer idControleLiberacaoPermissaoEspecial = (Integer) getControladorUtil().inserir(controleLiberacaoPermissaoEspecial);
		
		return idControleLiberacaoPermissaoEspecial;

	}

	/**
	 * [UC1048] Manter Controle de Libera��o de Permiss�o Especial
	 * que atualiza o Controle de Libera��o de Permiss�o Especial
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 23/07/2010
	 * 
	 * @param controleLiberacaoPermissaoEspecial
	 * @param usuarioLogado
	 * @throws ControladorException
	 */

	public void manterControleLiberacaoPermissaoEspecial(ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecial, Usuario usuarioLogado)
			throws ControladorException {

		// Verifica se todos os campos obrigatorios foram preenchidos

		if ((controleLiberacaoPermissaoEspecial.getFuncionalidade() == null || 
			 controleLiberacaoPermissaoEspecial.getFuncionalidade().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (controleLiberacaoPermissaoEspecial.getPermissaoEspecial() == null || 
			 	    controleLiberacaoPermissaoEspecial.getPermissaoEspecial().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) 
			 	&& controleLiberacaoPermissaoEspecial.getIndicadorUso() == null) {
			
			throw new ControladorException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Verifica se o campo Funcionalidade foi preenchido

		if (controleLiberacaoPermissaoEspecial.getFuncionalidade() == null
				|| controleLiberacaoPermissaoEspecial.getFuncionalidade().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Funcionalidade");
		}

		// Verifica se o campo Permiss�o Especial

		if (controleLiberacaoPermissaoEspecial.getPermissaoEspecial() == null
				|| controleLiberacaoPermissaoEspecial.getPermissaoEspecial().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Permiss�o Especial");
		}
		
		// Verifica se o campo Permiss�o Especial

		if (controleLiberacaoPermissaoEspecial.getIndicadorUso() == null
				|| controleLiberacaoPermissaoEspecial.getIndicadorUso().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					"Indicador de Uso");
		}

		// [FS0003] - Atualiza��o realizada por outro usu�rio
		FiltroControleLiberacaoPermissaoEspecial filtroControleLiberacaoPermissaoEspecial = new FiltroControleLiberacaoPermissaoEspecial();
//		filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ParametroSimples(
//				FiltroControleLiberacaoPermissaoEspecial.ID, controleLiberacaoPermissaoEspecial.getId()));

		Collection colecaoControleLiberacaoPermissaoEspecialBase = getControladorUtil().pesquisar(
				filtroControleLiberacaoPermissaoEspecial, ControleLiberacaoPermissaoEspecial.class.getName());

		if (colecaoControleLiberacaoPermissaoEspecialBase == null
				|| colecaoControleLiberacaoPermissaoEspecialBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecialBase = (ControleLiberacaoPermissaoEspecial) colecaoControleLiberacaoPermissaoEspecialBase
				.iterator().next();

		if (controleLiberacaoPermissaoEspecialBase.getUltimaAlteracao().after(
				controleLiberacaoPermissaoEspecial.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		filtroControleLiberacaoPermissaoEspecial.limparListaParametros();
		
		
		filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ParametroSimples(
				FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE, controleLiberacaoPermissaoEspecial.getFuncionalidade()));
		
		filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ParametroSimples(
				FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL, controleLiberacaoPermissaoEspecial.getPermissaoEspecial()));
				
		Collection colecaoControleLiberacaoPermissaoEspecial = getControladorUtil().pesquisar(
				filtroControleLiberacaoPermissaoEspecial, ControleLiberacaoPermissaoEspecial.class.getName());
				

		if (colecaoControleLiberacaoPermissaoEspecial != null && !colecaoControleLiberacaoPermissaoEspecial.isEmpty()) {
			
			ControleLiberacaoPermissaoEspecial novoControleLiberacaoPermissaoEspecial = (ControleLiberacaoPermissaoEspecial) colecaoControleLiberacaoPermissaoEspecial.iterator().next();
			
			novoControleLiberacaoPermissaoEspecial.setIndicadorUso(controleLiberacaoPermissaoEspecial.getIndicadorUso());
			
			novoControleLiberacaoPermissaoEspecial.setUltimaAlteracao(new Date());
			
			
			//------------ REGISTRAR TRANSA��O ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_MANTER_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL,
					novoControleLiberacaoPermissaoEspecial.getId(),novoControleLiberacaoPermissaoEspecial.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			registradorOperacao.registrarOperacao(novoControleLiberacaoPermissaoEspecial);
			
			//Fim Registrar Transa��o

			getControladorUtil().atualizar(novoControleLiberacaoPermissaoEspecial);			
			
		}else{
			throw new ControladorException("atencao.controle_liberacao_permissao_especial_nao_existe", null, "");
		}
						
	}
	
	
	/**
	 * Pesquisa se existe algum controle com permiss�o especial ativa para a funcionalidade.
	 * 
	 * @author: Daniel Alves
	 * @date: 31/08/2010
	 * @return boolean
	 */	
	public boolean existeControlePermissaoEspecialFuncionalidade(Integer idFuncionalidade) throws ControladorException{
		
		try {
			return this.repositorioAcesso.existeControlePermissaoEspecialFuncionalidade(idFuncionalidade);
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Remover todos os Grupos Associados a Solicita��o de Acesso.
	 * [UC1093] - Manter Solicita��o de Acesso
	 * 
	 * @author Hugo Leonardo
	 * @date 18/11/2010
	 * 
	 * @param idsolicitacaoAcesso
	 * @return void
	 */
	public void removerGrupoDeSolicitacaoAcesso( Integer idsolicitacaoAcesso) throws ControladorException {
		
		try {
			
			// Grupos
			FiltroSolicitacaoAcessoGrupo filtroSolicitacaoAcessoGrupo = new FiltroSolicitacaoAcessoGrupo();
			
			filtroSolicitacaoAcessoGrupo.setConsultaSemLimites(true);
			filtroSolicitacaoAcessoGrupo.adicionarCaminhoParaCarregamentoEntidade(
					FiltroSolicitacaoAcessoGrupo.GRUPO);
			filtroSolicitacaoAcessoGrupo.adicionarParametro(new ParametroSimples( 
					FiltroSolicitacaoAcessoGrupo.SOLICITACAO_ACESSO_ID, idsolicitacaoAcesso) );
				
			Collection colecaoSolicitacaoAcessoGrupo = 
				this.getControladorUtil().pesquisar(
						filtroSolicitacaoAcessoGrupo, SolicitacaoAcessoGrupo.class.getName());
			
			if (colecaoSolicitacaoAcessoGrupo != null && !colecaoSolicitacaoAcessoGrupo.isEmpty()) {
				
				Iterator iterator = colecaoSolicitacaoAcessoGrupo.iterator();
				
				SolicitacaoAcessoGrupo solicitacaoAcessoGrupo = null;
				
				while (iterator.hasNext()) {
				
					solicitacaoAcessoGrupo = (SolicitacaoAcessoGrupo) iterator.next();
					
					this.repositorioUtil.remover(solicitacaoAcessoGrupo);
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * Atualizar Solicita��o de Acesso quando ela for Cadastrada.
	 * [UC1093] - Manter Solicita��o de Acesso
	 * 
	 * @author Hugo Leonardo
	 * @date 19/11/2010
	 * 
	 * @param idsolicitacaoAcesso
	 * @return void
	 */
	public void atualizarCadastroSolicitacaoAcesso( Integer idsolicitacaoAcesso) throws ControladorException {
		
		try {
			
			// SolicitacaoAcessoSituacao
			FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();
			
			filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
					FiltroSolicitacaoAcessoSituacao.CODIGO_SITUACAO, SolicitacaoAcessoSituacao.ATIVO));
			filtroSolicitacaoAcessoSituacao.adicionarParametro(new ParametroSimples( 
					FiltroSolicitacaoAcessoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
			
			Collection colecaoSolicitacaoAcessoSituacao = this.getControladorUtil().pesquisar(
						filtroSolicitacaoAcessoSituacao, SolicitacaoAcessoSituacao.class.getName());
			
			if (colecaoSolicitacaoAcessoSituacao != null && !colecaoSolicitacaoAcessoSituacao.isEmpty()) {
				
				Iterator iterator = colecaoSolicitacaoAcessoSituacao.iterator();
				SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = (SolicitacaoAcessoSituacao) iterator.next();
				
				// SolicitacaoAcesso
				FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();
				filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoAcesso.ID, idsolicitacaoAcesso) );
				Collection colecaoSolicitacaoAcesso = this.getControladorUtil().pesquisar(
						filtroSolicitacaoAcesso, SolicitacaoAcesso.class.getName());
				
				if (colecaoSolicitacaoAcesso != null && !colecaoSolicitacaoAcesso.isEmpty()) {
					Iterator iteratorSolAc = colecaoSolicitacaoAcesso.iterator();
					SolicitacaoAcesso solicitacaoAcesso = (SolicitacaoAcesso) iteratorSolAc.next();
					
					solicitacaoAcesso.setSolicitacaoAcessoSituacao(solicitacaoAcessoSituacao);
					solicitacaoAcesso.setDataCadastramento(new Date());
					solicitacaoAcesso.setUltimaAlteracao(new Date());
					
					this.repositorioUtil.atualizar(solicitacaoAcesso);
				}

			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1093] Gerar Relat�rio Solicita��o de Acesso
	 * 
	 * @author Hugo Leonardo
	 * @date 23/11/2010
	 * 
	 * @param FiltrarRelatorioSolicitacaoAcessoHelper
	 * 
	 * @return Collection<RelatorioSolicitacaoAcessoHelper>
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioSolicitacaoAcesso(
			FiltrarRelatorioSolicitacaoAcessoHelper helper)throws ControladorException {
		
		Collection colecaoRetorno = new ArrayList();
		try {
			
			Collection colecaoSolicitacaoAcesso =  this.repositorioAcesso.pesquisarRelatorioSolicitacaoAcesso(helper);
			
			Iterator iterator = colecaoSolicitacaoAcesso.iterator();
			
			while (iterator.hasNext()) {
			
				RelatorioSolicitacaoAcessoHelper relatorioHelper = new RelatorioSolicitacaoAcessoHelper();
				
				Object[] objeto = (Object[]) iterator.next();
				
				// unidade Organizacional
				String idUnidadeOrganizacional = "";
				if ( objeto[0] != null ) {
					idUnidadeOrganizacional = objeto[0].toString();
				}
				relatorioHelper.setIdLotacao(idUnidadeOrganizacional);
			
				String unidadeOrganizacional = "";
				if ( objeto[1] != null ) {
					unidadeOrganizacional = objeto[1].toString();
				}
				relatorioHelper.setNomeLotacao(unidadeOrganizacional);
				
				// Data Solicita��o
				String dataSolicitacao = "";
				if ( objeto[2] != null ) {
					dataSolicitacao = Util.formatarData( (Date) objeto[2]);
				}
				relatorioHelper.setDataSolicitacao( dataSolicitacao);
				
				// Funcion�rio Solicitante
				String idFuncionarioSolicitante = "";
				if(objeto[3] != null ){
					idFuncionarioSolicitante = objeto[3].toString();
				}
				relatorioHelper.setIdFuncionarioSolicitante( idFuncionarioSolicitante);
				
				String nomeFuncionarioSolicitante = "";
				if(objeto[4] != null ){
					nomeFuncionarioSolicitante = objeto[4].toString();
				}
				relatorioHelper.setNomeFuncionarioSolicitante( nomeFuncionarioSolicitante);
				
				// Funcion�rio Respons�vel
				String idFuncionarioResponsavel = "";
				if(objeto[5] != null ){
					idFuncionarioResponsavel = objeto[5].toString();
				}
				relatorioHelper.setIdFuncionarioSuperior( idFuncionarioResponsavel);
				
				String nomeFuncionarioResponsavel = "";
				if(objeto[6] != null ){
					nomeFuncionarioResponsavel = objeto[6].toString();
				}
				relatorioHelper.setNomeFuncionarioSuperior( nomeFuncionarioResponsavel);
				
				// Data Autoriza��o
				String dataAutorizacao = "";
				if ( objeto[7] != null ) {
					dataAutorizacao = Util.formatarData( (Date) objeto[7]);
				}
				relatorioHelper.setDataAutorizacao( dataAutorizacao);
				
				// Matr�cula
				String matricula = "";
				if ( objeto[8] != null ) {
					matricula = objeto[8].toString();
				}
				relatorioHelper.setMatricula( matricula);
				
				// CPF
				String cpf = "";
				if ( objeto[9] != null ) {
					cpf = objeto[9].toString();
				}
				relatorioHelper.setCpf( cpf);

				// Nome Usu�rio
				String nomeUsuario = "";
				if ( objeto[10] != null ) {
					nomeUsuario = objeto[10].toString();
				}
				relatorioHelper.setNomeUsuario( nomeUsuario);
				
				// Situa��o Usu�rio
				String situacao = "";
				if ( objeto[11] != null ) {
					situacao = objeto[11].toString();
				}
				relatorioHelper.setSituacaoAcesso( situacao);
				
				// Per�odo
				String periodoInicial = "";
				if ( objeto[12] != null ) {
					periodoInicial = Util.formatarData( (Date) objeto[12]);
				}
				relatorioHelper.setDataInicial( periodoInicial);
				
				String periodoFinal = "";
				if ( objeto[13] != null ) {
					periodoFinal = Util.formatarData( (Date) objeto[13]);
				}
				relatorioHelper.setDataFinal( periodoFinal);
				
				colecaoRetorno.add(relatorioHelper);
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return colecaoRetorno;
	}
	
	public String getSegurancaParametro(String parametro) throws ControladorException {
		FiltroSegurancaParametro filtroSegurancaParametro = new FiltroSegurancaParametro();
		filtroSegurancaParametro.adicionarParametro(new ParametroSimples(FiltroSegurancaParametro.NOME, parametro));

		Collection parametros = Fachada.getInstancia().pesquisar(filtroSegurancaParametro, SegurancaParametro.class.getName());

		 return ((SegurancaParametro) parametros.iterator().next()).getValor();
	}
}
