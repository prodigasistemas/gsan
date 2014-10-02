package gcom.cadastro.unidade;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class ControladorUnidadeSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;

	private IRepositorioUnidade repositorioUnidade = null;

	SessionContext sessionContext;

	public void ejbCreate() throws CreateException {
		repositorioUnidade = RepositorioUnidadeHBM.getInstancia();
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
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Obtém a unidade associada ao usuário que estiver efetuando o registro de
	 * atendimento (UNID_ID e UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL
	 * com UNID_ID=(UNID_ID da tabela USUARIO com USUR_NMLOGIN= Login do usuário
	 * que estiver efetuando o registro de atendimento) e UNID_ICABERTURARA=1)
	 * 
	 * @author Raphael Rossiter
	 * @date 25/07/2006
	 * 
	 * @param login
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeOrganizacionalAberturaRAAtivoUsuario(
			String loginUsuario) throws ControladorException {

		UnidadeOrganizacional retorno = null;

		try {

			retorno = repositorioUnidade
					.obterUnidadeOrganizacionalAberturaRAAtivoUsuario(loginUsuario);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;

	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a unidade de atendimento não tenha autorização para efetuar a
	 * abertura de registros de atendimento (UNID_ICABERTURARA com o valor
	 * correspondente a dois na tabela UNIDADE_ORGANIZACIONAL com UNID_ID=Id da
	 * Unidade de Atendimento), exibir a mensagem “A unidade <<UNID_NMUNIDADE
	 * da tabela UNIDADE_ORGANIZACIONAL>> não tem autorização para efetuar a
	 * abertura de registro de atendimento” e retornar para o passo
	 * correspondente no fluxo principal.
	 * 
	 * [FS0004] - Verificar existência da unidade de atendimento
	 * 
	 * [FS0033] - Verificar autorização da unidade de atendimento para abertura
	 * de registro de atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/07/2006
	 * 
	 * @param idUnidadeOrganizacional,
	 *            levantarExceptionUnidadeInexistente
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificarAutorizacaoUnidadeAberturaRA(
			Integer idUnidadeOrganizacional,
			boolean levantarExceptionUnidadeInexistente)
			throws ControladorException {

		UnidadeOrganizacional retorno = null;

		Collection colecaoUnidadeOrganizacional = null;

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional
				.adicionarCaminhoParaCarregamentoEntidade("meioSolicitacao");

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		colecaoUnidadeOrganizacional = this.getControladorUtil().pesquisar(
				filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		if (colecaoUnidadeOrganizacional != null
				&& !colecaoUnidadeOrganizacional.isEmpty()) {

			retorno = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);

			if (retorno.getIndicadorAberturaRa() == null
					|| retorno.getIndicadorAberturaRa().equals(
							ConstantesSistema.INDICADOR_USO_DESATIVO)) {

				throw new ControladorException(
						"atencao.unidade_organizacional_nao_autorizada_registro_atendimento",
						null, retorno.getDescricao());
			}
		} else if (levantarExceptionUnidadeInexistente) {

			throw new ControladorException("atencao.label_inexistente", null,
					"Unidade de Atendimento");
		}

		return retorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a unidade destino definida esteja vinculada a uma unidade
	 * centralizadora (UNID_IDCENTRALIZADORA com o valor diferente de nulo na
	 * tabela UNIDADE_ORGANIZACIONAL para UNID_ID=Id da unidade destino),
	 * definir a unidade destino a partir da unidade centralizadora (UNID_ID e
	 * UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e
	 * UNID_ID=UNID_IDCENTRALIZADORA).
	 * 
	 * [FS0018] - Verificar existência de unidade centralizadora
	 * 
	 * @author Raphael Rossiter
	 * @date 26/07/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificarExistenciaUnidadeCentralizadora(
			UnidadeOrganizacional unidadeOrganizacional)
			throws ControladorException {

		UnidadeOrganizacional retorno = null;

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional
				.adicionarCaminhoParaCarregamentoEntidade("unidadeCentralizadora");

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, unidadeOrganizacional.getId()));

		/*
		 * filtroUnidadeOrganizacional.adicionarParametro(new ParametroNaoNulo(
		 * FiltroUnidadeOrganizacional.ID_UNIDADE_CENTRALIZADORA));
		 */

		Collection colecaoUnidadeOrganizacional = this.getControladorUtil()
				.pesquisar(filtroUnidadeOrganizacional,
						UnidadeOrganizacional.class.getName());

		if (colecaoUnidadeOrganizacional != null
				&& !colecaoUnidadeOrganizacional.isEmpty()) {

			retorno = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);

			if (retorno.getUnidadeCentralizadora() != null) {

				if (retorno.getUnidadeCentralizadora().getIndicadorTramite() == ConstantesSistema.INDICADOR_USO_ATIVO
						.shortValue()) {
					retorno = retorno.getUnidadeCentralizadora();
				}

			}

		}

		return retorno;
	}

	/**
	 * [UC0373] Inserir Unidade Organizacional
	 * 
	 * Metodo inserção da unidade organizacional
	 * 
	 * [FS0001] - Validar Localidade [FS0002] - Validar Gerencia Regional
	 * [FS0003] - Verificar exitência da descrição [FS0004] - Verificar
	 * exitência da sigla [FS0005] - Validar Empresa [FS0006] - Validar Unidade
	 * Superior
	 * 
	 * @author Raphael Pinto
	 * @date 31/07/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return Object
	 * @throws ControladorException
	 */
	public Object inserirUnidadeOrganizacional(
			UnidadeOrganizacional unidadeOrganizacional, Usuario usuario)
			throws ControladorException {

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		Collection colecaoUnidade = null;

		// Caso 2 - [FS0001] - Validar Localidade
		if (unidadeOrganizacional.getLocalidade() != null) {

			String idLocalidade = ""
					+ unidadeOrganizacional.getLocalidade().getId();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID_LOCALIDADE,
							idLocalidade));

			// Pesquisa de acordo com os parâmetros informados no filtro
			colecaoUnidade = this.getControladorUtil().pesquisar(
					filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
				throw new ControladorException(
						"atencao.inserir_unidade_organizacional_localidade_ja_cadastradado",
						null, idLocalidade);
			}
		}

		// Caso 2 - [FS0002] - Validar Gerencia Regional
		if (unidadeOrganizacional.getGerenciaRegional() != null) {

			String idGerenciaRegional = ""
					+ unidadeOrganizacional.getGerenciaRegional().getId();

			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.GERENCIAL_REGIONAL,
							idGerenciaRegional));

			// Pesquisa de acordo com os parâmetros informados no filtro
			colecaoUnidade = this.getControladorUtil().pesquisar(
					filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
				throw new ControladorException(
						"atencao.inserir_unidade_organizacional_gerencia_ja_cadastradado",
						null, idGerenciaRegional);
			}
		}

		// Caso 2 - [FS0011] - Validar Unidade Negocio
		if (unidadeOrganizacional.getUnidadeNegocio() != null) {

			String idUnidadeNegocio = ""
					+ unidadeOrganizacional.getUnidadeNegocio().getId();

			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.UNIDADE_NEGOCIO,
							idUnidadeNegocio));

			// Pesquisa de acordo com os parâmetros informados no filtro
			colecaoUnidade = this.getControladorUtil().pesquisar(
					filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
				throw new ControladorException(
						"atencao.inserir_unidade_organizacional_unidade_negocio_ja_cadastradado",
						null, idUnidadeNegocio);
			}
		}

		// Caso 2 - [FS0003] - Verificar exitência da descrição
		String descricao = unidadeOrganizacional.getDescricao();

		filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.DESCRICAO, descricao));

		// Pesquisa de acordo com os parâmetros informados no filtro
		colecaoUnidade = this.getControladorUtil().pesquisar(
				filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
			throw new ControladorException(
					"atencao.inserir_unidade_organizacional_descricao_ja_existe",
					null);
		}

		// Caso 2 - [FS0004] - Verificar exitência da sigla
		if (unidadeOrganizacional.getSigla() != null
				&& !unidadeOrganizacional.getSigla().equals("")) {

			String sigla = unidadeOrganizacional.getSigla();

			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.SIGLA, sigla));

			// Pesquisa de acordo com os parâmetros informados no filtro
			colecaoUnidade = this.getControladorUtil().pesquisar(
					filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
				throw new ControladorException(
						"atencao.inserir_unidade_organizacional_sigla_ja_existe",
						null);
			}
		}

		// Caso 2 - [FS0005] - Validar Empresa
		String codigoUnidadeTipo = unidadeOrganizacional.getUnidadeTipo()
				.getCodigoTipo();
		if (codigoUnidadeTipo != null
				&& codigoUnidadeTipo
						.equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)) {
			Empresa empresa = unidadeOrganizacional.getEmpresa();

			if (empresa.getIndicadorEmpresaPrincipal().equals(
					ConstantesSistema.SIM)) {
				throw new ControladorException(
						"atencao.inserir_unidade_organizacional_terceirizada_associada",
						null);
			}
		}

		// Caso 2,3 - [FS0006] - Validar Unidade Superior
		Short nivelOrganizacional = unidadeOrganizacional.getUnidadeTipo()
				.getNivel();
		UnidadeOrganizacional unidadeSuperior = unidadeOrganizacional
				.getUnidadeSuperior();

		if (nivelOrganizacional != null) {

			if (unidadeSuperior != null
					&& unidadeSuperior.getUnidadeTipo().getNivel() != null) {

				Short nivelSuperior = unidadeSuperior.getUnidadeTipo()
						.getNivel();

				SistemaParametro sistemaParametro = Fachada.getInstancia()
						.pesquisarParametrosDoSistema();

				// Caso 2
				if (nivelSuperior.intValue() > nivelOrganizacional.intValue()) {

					if (sistemaParametro.getIndicadorVariaHierarquiaUnidade() != null
							&& sistemaParametro
									.getIndicadorVariaHierarquiaUnidade()
									.equals(ConstantesSistema.SIM)) {
						throw new ControladorException(
								"atencao.inserir_unidade_organizacional_nivel_hierarquico.deve_ser_menor",
								null, unidadeSuperior.getUnidadeTipo()
										.getDescricao());
					} else if (sistemaParametro
							.getIndicadorVariaHierarquiaUnidade() == null
							|| sistemaParametro
									.getIndicadorVariaHierarquiaUnidade()
									.equals(ConstantesSistema.NAO)) {
						throw new ControladorException(
								"atencao.inserir_unidade_organizacional_nivel_hierarquico.diferente_um",
								null, unidadeSuperior.getUnidadeTipo()
										.getDescricao());
					}
				}
				// Caso 3
				else if (nivelSuperior.intValue() < (nivelOrganizacional
						.intValue() -1)
						&& sistemaParametro
								.getIndicadorVariaHierarquiaUnidade() != 1
						&& (nivelOrganizacional.intValue() - nivelSuperior
								.intValue()) != 1) {
					throw new ControladorException(
							"atencao.inserir_unidade_organizacional_nivel_hierarquico",
							null);

				}
			}
		}

		// ------------ REGISTRAR TRANSAÇÃO UNIDADE
		// ORGANIZACIONAL----------------------------
		RegistradorOperacao registradorOperacaoUnidade = new RegistradorOperacao(
				Operacao.OPERACAO_UNIDADE_ORGANIZACIONAL_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacaoUnidade = new Operacao();
		operacaoUnidade.setId(Operacao.OPERACAO_UNIDADE_ORGANIZACIONAL_INSERIR);

		OperacaoEfetuada operacaoEfetuadaUnidade = new OperacaoEfetuada();
		operacaoEfetuadaUnidade.setOperacao(operacaoUnidade);

		unidadeOrganizacional.setOperacaoEfetuada(operacaoEfetuadaUnidade);
		unidadeOrganizacional.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacaoUnidade.registrarOperacao(unidadeOrganizacional);
		// ------------ REGISTRAR TRANSAÇÃO UNIDADE
		// ORGANIZACIONAL----------------------------

		return this.getControladorUtil().inserir(unidadeOrganizacional);
	}

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 05/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return Collection<UnidadeOrganizacional> unidades subordinadas
	 * @throws ErroRepositorioException
	 */
	public Collection<UnidadeOrganizacional> recuperarUnidadesSubordinadasPorUnidadeSuperior(
			UnidadeOrganizacional unidadeOrganizacional)
			throws ControladorException {
		Collection<UnidadeOrganizacional> colecaoUnidadeSubordinadas = null;
		try {
			colecaoUnidadeSubordinadas = repositorioUnidade
					.recuperarUnidadesSubordinadasPorUnidadeSuperior(unidadeOrganizacional);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoUnidadeSubordinadas;
	}

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento que estão na unidade atual informada
	 * (existe ocorrência na tabela REGISTRO_ATENDIMENTO com TRAMITE=Id da
	 * Unidade Atual e maior TRAM_TMTRAMITE)
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return RegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional recuperaUnidadeAtualPorRA(
			RegistroAtendimento registroAtendimento)
			throws ControladorException {
		UnidadeOrganizacional unidadeAtual = null;
		try {
			unidadeAtual = repositorioUnidade
					.recuperaUnidadeAtualPorRA(registroAtendimento);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return unidadeAtual;
	}

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * [FS007] Verificar existência de unidades subordinadas
	 * 
	 * @author Leonardo Regis
	 * @date 05/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @throws ControladorException
	 */
	public void verificarExistenciaUnidadesSubordinadas(
			UnidadeOrganizacional unidadeOrganizacional)
			throws ControladorException {
		int qtdeUnidadesSubordinadas = 0;
		try {
			qtdeUnidadesSubordinadas = repositorioUnidade
					.consultarTotalUnidadesSubordinadasPorUnidadeSuperior(unidadeOrganizacional);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		if (qtdeUnidadesSubordinadas == 0) {
			throw new ControladorException(
					"atencao.filtrar_ra_sem_unidades_subordinadas");
		}
	}

	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * Caso a unidade destino informada não possa receber registros de
	 * atendimento (UNID_ICTRAMITE=2 na tabela UNIDADE_ORGANIZACIONAL com
	 * UNID_ID=Id da unidade destino informada).
	 * 
	 * [FS0013] - Verificar possibilidade de encaminhamento para a unidade
	 * destino
	 * 
	 * @author Ana Maria
	 * @date 03/09/2006
	 * 
	 * @param idUnidadeOrganizacional
	 * @return void
	 * @throws ControladorException
	 */
	public void verificaPossibilidadeEncaminhamentoUnidadeDestino(
			Integer idUnidadeDestino) throws ControladorException {

		try {
			Short idTramiteUnidade = repositorioUnidade
					.verificaTramiteUnidade(idUnidadeDestino);
			if (idTramiteUnidade != null
					&& idTramiteUnidade == ConstantesSistema.INDICADOR_USO_DESATIVO
							.shortValue()) {

				throw new ControladorException(
						"atencao.unidade_destino_nao_possivel_encaminhamento");
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<UnidadeOrganizacional> pesquisarUnidadeOrganizacionalPorRA(
			Collection<Integer> idsRa) throws ControladorException {

		try {
			return repositorioUnidade
					.pesquisarUnidadeOrganizacionalPorRA(idsRa);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<UnidadeOrganizacional> pesquisarUnidadeOrganizacionalPorUnidade(
			Integer unidadeLotacao) throws ControladorException {

		try {
			return repositorioUnidade
					.pesquisarUnidadeOrganizacionalPorUnidade(unidadeLotacao);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Pesquisa a Unidade Organizacional do Usuário Logado
	 * 
	 * @author Rafael Corrêa
	 * @date 25/09/2006
	 * 
	 * @param id
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional pesquisarUnidadeUsuario(Integer idUsuario)
			throws ControladorException {
		try {
			return repositorioUnidade.pesquisarUnidadeUsuario(idUsuario);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0375] Manter Unidade Organizacional
	 * 
	 * @author Ana Maria
	 * @date 24/11/2006
	 * 
	 * @param unidadeOrganizacional
	 * @throws ControladorException
	 */
	public void atualizarUnidadeOrganizacional(
			UnidadeOrganizacional unidadeOrganizacional, Usuario usuario)
			throws ControladorException {

		String idUnidade = "" + unidadeOrganizacional.getId();

		validaAtualizacaoUnidadeOrganizacional(unidadeOrganizacional);

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		// Seta o filtro para buscar a unidade organizacional na base
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidade));

		// Procura unidade organizacional na base
		Collection unidadeAtualizadas = getControladorUtil().pesquisar(
				filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		UnidadeOrganizacional unidadeAtualizada = (UnidadeOrganizacional) Util
				.retonarObjetoDeColecao(unidadeAtualizadas);

		if (unidadeAtualizada == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_remocao_nao_existente");
		}

		// Procura a rota na base
		UnidadeOrganizacional unidadeNaBase = null;
		unidadeNaBase = (UnidadeOrganizacional) ((List) (this
				.getControladorUtil().pesquisar(filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName()))).get(0);

		// Verificar se a rota já foi atualizado por outro usuário
		// durante esta atualização
		if (unidadeNaBase.getUltimaAlteracao().after(
				unidadeOrganizacional.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// ------------ REGISTRAR TRANSAÇÃO UNIDADE
		// ORGANIZACIONAL----------------------------
		RegistradorOperacao registradorOperacaoUnidade = new RegistradorOperacao(
				Operacao.OPERACAO_UNIDADE_ORGANIZACIONAL_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacaoUnidade = new Operacao();
		operacaoUnidade
				.setId(Operacao.OPERACAO_UNIDADE_ORGANIZACIONAL_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuadaUnidade = new OperacaoEfetuada();
		operacaoEfetuadaUnidade.setOperacao(operacaoUnidade);

		unidadeOrganizacional.setOperacaoEfetuada(operacaoEfetuadaUnidade);
		unidadeOrganizacional.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacaoUnidade.registrarOperacao(unidadeOrganizacional);
		// ------------ REGISTRAR TRANSAÇÃO UNIDADE
		// ORGANIZACIONAL----------------------------

		// Atualiza a unidadeOrganizacional
		try {
			repositorioUnidade
					.atualizarUnidadeOrganizacional(unidadeOrganizacional);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	private void validaAtualizacaoUnidadeOrganizacional(
			UnidadeOrganizacional unidadeOrganizacional)
			throws ControladorException {
		Collection colecaoUnidade = null;
		// [FS0001] - Verificar exitência da descrição
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.DESCRICAO, unidadeOrganizacional
						.getDescricao()));

		// Pesquisa de acordo com os parâmetros informados no filtro
		colecaoUnidade = this.getControladorUtil().pesquisar(
				filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
			UnidadeOrganizacional unidadePesquisada = (UnidadeOrganizacional) ((List) colecaoUnidade)
					.get(0);

			if (!unidadeOrganizacional.getId()
					.equals(unidadePesquisada.getId())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.inserir_unidade_organizacional_descricao_ja_existe",
						null);
			}
		}

		// [FS0002] - Verificar exitência da sigla
		if (unidadeOrganizacional.getSigla() != null
				&& !unidadeOrganizacional.getSigla().equals("")) {

			String sigla = unidadeOrganizacional.getSigla();

			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.SIGLA, sigla));

			// Pesquisa de acordo com os parâmetros informados no filtro
			colecaoUnidade = this.getControladorUtil().pesquisar(
					filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
				UnidadeOrganizacional unidadePesquisada = (UnidadeOrganizacional) ((List) colecaoUnidade)
						.get(0);

				if (!unidadeOrganizacional.getId().equals(
						unidadePesquisada.getId())) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.inserir_unidade_organizacional_sigla_ja_existe",
							null);
				}
			}
		}

		// [FS0003] - Validar Empresa
		String codigoUnidadeTipo = unidadeOrganizacional.getUnidadeTipo()
				.getCodigoTipo();
		if (codigoUnidadeTipo != null
				&& codigoUnidadeTipo
						.equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)) {
			Empresa empresa = unidadeOrganizacional.getEmpresa();

			if (empresa.getIndicadorEmpresaPrincipal().equals(
					ConstantesSistema.SIM)) {
				throw new ControladorException(
						"atencao.inserir_unidade_organizacional_terceirizada_associada",
						null);
			}
		}

		// [FS0006] - Validar Unidade de Esgoto
		try {
			String descricaoDivisao = repositorioUnidade
					.verificarUnidadeEsgoto(unidadeOrganizacional.getId());

			if (descricaoDivisao != null
					&& unidadeOrganizacional.getIndicadorEsgoto() == ConstantesSistema.NAO) {
				throw new ControladorException("atencao.unidade.esgoto", null,
						descricaoDivisao);
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		// [FS0007] - Validar Unidade Aceita Tramitação
		try {
			String descricaoSolicitacaoTipoEspecificacao = repositorioUnidade
					.verificarUnidadeTramitacao(unidadeOrganizacional.getId());

			if (descricaoSolicitacaoTipoEspecificacao != null
					&& unidadeOrganizacional.getIndicadorTramite() == ConstantesSistema.NAO) {
				throw new ControladorException("atencao.unidade.tramitacao",
						null);
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (unidadeOrganizacional.getUnidadeSuperior() != null
				&& !unidadeOrganizacional.getUnidadeSuperior().equals("")) {

			// [FS0004] - Validar Unidade Superior
			Short nivelOrganizacional = unidadeOrganizacional.getUnidadeTipo()
					.getNivel();
			UnidadeOrganizacional unidadeSuperior = unidadeOrganizacional
					.getUnidadeSuperior();

			if (unidadeSuperior == null || unidadeSuperior.equals("")) {
				throw new ControladorException(
						"atencao.unidade.organizacional.inexistente", null);
			}

			if (nivelOrganizacional != null) {

				if (unidadeSuperior != null
						&& unidadeSuperior.getUnidadeTipo().getNivel() != null) {

					Short nivelSuperior = unidadeSuperior.getUnidadeTipo()
							.getNivel();

					SistemaParametro sistemaParametro = Fachada.getInstancia()
							.pesquisarParametrosDoSistema();

					// Caso 3
					if (nivelSuperior.intValue() > nivelOrganizacional
							.intValue()) {
						if (sistemaParametro
								.getIndicadorVariaHierarquiaUnidade() != null
								&& sistemaParametro
										.getIndicadorVariaHierarquiaUnidade()
										.equals(ConstantesSistema.SIM)) {
							throw new ControladorException(
									"atencao.inserir_unidade_organizacional_nivel_hierarquico.deve_ser_menor",
									null, unidadeSuperior.getUnidadeTipo()
											.getDescricao());
						} else if (sistemaParametro
								.getIndicadorVariaHierarquiaUnidade() == null
								|| sistemaParametro
										.getIndicadorVariaHierarquiaUnidade()
										.equals(ConstantesSistema.NAO)) {
							throw new ControladorException(
									"atencao.inserir_unidade_organizacional_nivel_hierarquico.diferente_um",
									null, unidadeSuperior.getUnidadeTipo()
											.getDescricao());
						}
						// Caso 4
					} else if (nivelSuperior.intValue() < (nivelOrganizacional
							.intValue() -1)
							&& sistemaParametro
									.getIndicadorVariaHierarquiaUnidade() != 1
							&& (nivelOrganizacional.intValue() - nivelSuperior
									.intValue()) != 1) {
						throw new ControladorException(
								"atencao.inserir_unidade_organizacional_nivel_hierarquico",
								null);
					}
				}
			}
		}
	}

	/**
	 * [UC0375] Manter Unidade Organizacional
	 * 
	 * @author Ana Maria
	 * @date 28/11/2006
	 * 
	 * @param unidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional pesquisarUnidadeOrganizacional(
			Integer idUnidadeOrganizacional) throws ControladorException {

		UnidadeOrganizacional retorno = null;
		try {
			retorno = repositorioUnidade
					.pesquisarUnidadeOrganizacional(idUnidadeOrganizacional);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * [UC0374] Filtrar Unidade Organizacional
	 * 
	 * Pesquisa as unidades organizacionais com os condicionais informados
	 * filtroUnidadeOrganizacional
	 * 
	 * @author Ana Maria
	 * @date 30/11/2006
	 * 
	 * @param filtro
	 * @return Collection
	 */
	public Collection pesquisarUnidadeOrganizacionalFiltro(
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional,
			Integer numeroPagina) throws ControladorException {

		Collection colecaoObject = new ArrayList();

		Collection colecaoUnidadeOrganizacional = new ArrayList();

		try {

			colecaoObject = repositorioUnidade
					.pesquisarUnidadeOrganizacionalFiltro(
							filtroUnidadeOrganizacional, numeroPagina);

			Iterator iteratorObject = colecaoObject.iterator();
			while (iteratorObject.hasNext()) {
				Object[] arrayObject = (Object[]) iteratorObject.next();
				if (arrayObject != null) {

					UnidadeOrganizacional unidade = new UnidadeOrganizacional();

					// id unidade organizacional
					if (arrayObject[0] != null) {
						unidade.setId((Integer) arrayObject[0]);
					}
					// instancia tipo unidade
					UnidadeTipo unidadeTipo = new UnidadeTipo();
					// id da tipo unidade
					if (arrayObject[1] != null) {
						unidadeTipo.setDescricao((String) arrayObject[1]);
						if (arrayObject[2] != null) {
							unidadeTipo.setNivel((Short) arrayObject[2]);
						}
						unidade.setUnidadeTipo(unidadeTipo);
					}

					unidade.setDescricao((String) arrayObject[3]);

					if (arrayObject[4] != null) {
						unidade.setIndicadorAberturaRa((Short) arrayObject[4]);
					}

					if (arrayObject[5] != null) {
						unidade.setIndicadorTramite((Short) arrayObject[5]);
					}

					colecaoUnidadeOrganizacional.add(unidade);
				}
			}
		} catch (ErroRepositorioException e) {
			
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return colecaoUnidadeOrganizacional;
	}

	public Integer pesquisarUnidadeOrganizacionalFiltroCount(
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional)
			throws ControladorException {
		try {
			return repositorioUnidade
					.pesquisarUnidadeOrganizacionalFiltroCount(filtroUnidadeOrganizacional);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Pesquisar unidade organizacional por localidade
	 * 
	 * @author Sávio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idLocalidade
	 * @return String
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalLocalidade(
			Integer idLocalidade) throws ControladorException {
		try {
			return repositorioUnidade
					.pesquisarUnidadeOrganizacionalLocalidade(idLocalidade);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobrança por Empresa
	 * 
	 * @author Mariana Victor
	 * @date 14/04/2011
	 */
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalPorImovel(
			Integer idImovel) throws ControladorException {

		try {
			return repositorioUnidade
					.pesquisarUnidadeOrganizacionalPorImovel(idImovel);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}
}
