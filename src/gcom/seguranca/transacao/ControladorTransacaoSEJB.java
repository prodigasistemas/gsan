package gcom.seguranca.transacao;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.FiltroRamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoagua.FiltroSupressaoTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroSupressaoMotivo;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocal;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocalHome;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atualizacaocadastral.IRepositorioAtualizacaoCadastral;
import gcom.atualizacaocadastral.RepositorioAtualizacaoCadastralHBM;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.RepositorioCadastroHBM;
import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FiltroProfissao;
import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.EnderecoTipo;
import gcom.cadastro.endereco.FiltroEnderecoReferencia;
import gcom.cadastro.endereco.FiltroEnderecoTipo;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.Interceptador;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.seguranca.AtributoGrupo;
import gcom.seguranca.IRepositorioSeguranca;
import gcom.seguranca.RepositorioSegurancaHBM;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroOperacaoTabela;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.OperacaoOrdemExibicao;
import gcom.seguranca.acesso.OperacaoTabela;
import gcom.seguranca.acesso.OperacaoTabelaPK;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAlteracao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.acesso.usuario.UsuarioAlteracao;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.commons.lang.StringUtils;

/**
 * Definição da lógica de negócio do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public class ControladorTransacaoSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;

	private IRepositorioTransacao repositorioTransacao = null;
	private IRepositorioCadastro repositorioCadastro = null;
	private IRepositorioAtualizacaoCadastral repositorioAtualizacaoCadastral = null;
	private IRepositorioSeguranca repositorioSeguranca = null;

	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {

		repositorioTransacao            = RepositorioTransacaoHBM.getInstancia();
		repositorioCadastro             = RepositorioCadastroHBM.getInstancia();
		repositorioAtualizacaoCadastral = RepositorioAtualizacaoCadastralHBM.getInstancia();
		repositorioSeguranca            = RepositorioSegurancaHBM.getInstancia();
		
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
	 * Retorna o valor de controladorCliente
	 * 
	 * @return O valor de controladorImovel
	 */
	protected ControladorClienteLocal getControladorCliente() {

		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorClienteLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
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
	 * Retorna o valor de controladorImovel
	 * 
	 * @return O valor de controladorImovel
	 */
	private ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
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
	 * Retorna o valor de controladorRegistroAtendimento
	 * 
	 */
	private ControladorRegistroAtendimentoLocal getControladorRegistroAtendimento() {
		ControladorRegistroAtendimentoLocalHome localHome = null;
		ControladorRegistroAtendimentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorRegistroAtendimentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Método que consulta os usuario alteracao de uma determinada operacao com
	 * as restricoes passadas
	 * 
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param hotaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 * @author thiago toscano
	 * @date 17/02/2006
	 */
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(
			Integer idUsuarioAcao, Integer idOperacao, Integer idUsuario,
			Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal,
			Hashtable<String,String> argumentos, Integer id1, String unidadeNegocio)
			throws ControladorException {

		try {
			if (null == idUsuarioAcao && null == idOperacao
					&& null == idUsuario && null == dataInicial
					&& null == dataFinal && null == horaInicial
					&& null == horaFinal && null == argumentos
					&& null == id1) {
				throw new ControladorException(
						"atencao.filtro.nenhum_parametro_informado");
			}

			Collection coll = repositorioTransacao
					.pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(
							idUsuarioAcao, idOperacao, idUsuario, dataInicial,
							dataFinal, horaInicial, horaFinal, argumentos, id1, unidadeNegocio);

			/*
			 * Collection coll =
			 * repositorioTransacao.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(idUsuarioAcao,
			 * idOperacao, idUsuario, dataInicial, dataFinal, horaInicial,
			 * horaFinal, idTabela, idTabelaColuna,id1, numeroPagina);
			 */

			// para todas as operacoes efetuadas carregar a lista de
			// usuariosAlteracao
			if (coll != null && !coll.isEmpty()) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) it
							.next();
					Set set = new HashSet();
					operacaoEfetuada.setUsuarioAlteracoes(set);

					// criando o filtrtro para consultar os usuarios
					FiltroUsuarioAlteracao filtroUsuarioAlteracao = new FiltroUsuarioAlteracao();
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_ACAO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_TIPO);
					filtroUsuarioAlteracao
							.adicionarParametro(new ParametroSimples(
									FiltroUsuarioAlteracao.OPERACAO_EFETUADA_ID,
									operacaoEfetuada.getId()));

					Collection usuarioalteracoes = getControladorUtil()
							.pesquisar(filtroUsuarioAlteracao,
									UsuarioAlteracao.class.getSimpleName());
					// para todos os usuarios colocar na lista
					if (usuarioalteracoes != null
							&& !usuarioalteracoes.isEmpty()) {
						Iterator itt = usuarioalteracoes.iterator();
						while (itt.hasNext()) {
							set.add(itt.next());
						}
					}
				}
			}

			return coll;
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Método que consulta os usuario alteracao de uma determinada operacao com
	 * as restricoes passadas
	 * 
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param hotaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 * @author Romulo Aurelio
	 * @date 11/05/2007
	 */

	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(
			Integer idUsuarioAcao, String[] idOperacoes, String idUsuario,
			Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal,
			Hashtable<String,String> argumentos, Integer id1,
			Integer numeroPagina, String unidadeNegocio) throws ControladorException {

		try {
			if (null == idUsuarioAcao && null == idUsuario && null == dataInicial
					&& null == dataFinal && null == horaInicial
					&& null == horaFinal && null == argumentos
					&& null == id1) {
				throw new ControladorException(
						"atencao.filtro.nenhum_parametro_informado");
			}

			/*
			 * Collection coll =
			 * repositorioTransacao.pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
			 * idOperacao, idUsuario, dataInicial, dataFinal, horaInicial,
			 * horaFinal, idTabela, idTabelaColuna,id1);
			 */

			Collection coll = repositorioTransacao
					.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(
							idUsuarioAcao, idOperacoes, idUsuario, dataInicial,
							dataFinal, horaInicial, horaFinal, argumentos, id1, numeroPagina,unidadeNegocio);

			// para todas as operacoes efetuadas carregar a lista de
			// usuariosAlteracao
			if (coll != null && !coll.isEmpty()) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) it
							.next();
					Set set = new HashSet();
					operacaoEfetuada.setUsuarioAlteracoes(set);

					// criando o filtrtro para consultar os usuarios
					FiltroUsuarioAlteracao filtroUsuarioAlteracao = new FiltroUsuarioAlteracao();
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_ACAO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_TIPO);
					filtroUsuarioAlteracao
							.adicionarParametro(new ParametroSimples(
									FiltroUsuarioAlteracao.OPERACAO_EFETUADA_ID,
									operacaoEfetuada.getId()));

					Collection usuarioalteracoes = getControladorUtil()
							.pesquisar(filtroUsuarioAlteracao,
									UsuarioAlteracao.class.getSimpleName());
					// para todos os usuarios colocar na lista
					if (usuarioalteracoes != null
							&& !usuarioalteracoes.isEmpty()) {
						Iterator itt = usuarioalteracoes.iterator();
						while (itt.hasNext()) {
							set.add(itt.next());
						}
					}
				}
			}

			return coll;
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Método que consulta os usuario alteracao de uma determinada operacao com
	 * as restricoes passadas
	 * 
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param hotaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 * @author Romulo Aurelio
	 * @date 11/05/2007
	 */

	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(
			Integer idUsuarioAcao, String[] idOperacoes, String idUsuario,
			Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal,
			Hashtable<String,String> argumentos, Integer id1, String unidadeNegocio)
			throws ControladorException {

		try {
			if (null == idUsuarioAcao && null == idUsuario && null == dataInicial
					&& null == dataFinal && null == horaInicial
					&& null == horaFinal && null == argumentos
					&& null == id1) {
				throw new ControladorException(
						"atencao.filtro.nenhum_parametro_informado");
			}

			/*
			 * Collection coll =
			 * repositorioTransacao.pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
			 * idOperacao, idUsuario, dataInicial, dataFinal, horaInicial,
			 * horaFinal, idTabela, idTabelaColuna,id1);
			 */

			Collection coll = repositorioTransacao
					.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(
							idUsuarioAcao, idOperacoes, idUsuario, dataInicial,
							dataFinal, horaInicial, horaFinal, argumentos, id1, unidadeNegocio);

			// para todas as operacoes efetuadas carregar a lista de
			// usuariosAlteracao
			if (coll != null && !coll.isEmpty()) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) it
							.next();
					Set set = new HashSet();
					operacaoEfetuada.setUsuarioAlteracoes(set);

					// criando o filtrtro para consultar os usuarios
					FiltroUsuarioAlteracao filtroUsuarioAlteracao = new FiltroUsuarioAlteracao();
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_ACAO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_TIPO);
					filtroUsuarioAlteracao
							.adicionarParametro(new ParametroSimples(
									FiltroUsuarioAlteracao.OPERACAO_EFETUADA_ID,
									operacaoEfetuada.getId()));

					Collection usuarioalteracoes = getControladorUtil()
							.pesquisar(filtroUsuarioAlteracao,
									UsuarioAlteracao.class.getSimpleName());
					// para todos os usuarios colocar na lista
					if (usuarioalteracoes != null
							&& !usuarioalteracoes.isEmpty()) {
						Iterator itt = usuarioalteracoes.iterator();
						while (itt.hasNext()) {
							set.add(itt.next());
						}
					}
				}
			}

			return coll;
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Método que registra uma operacao ao sistema
	 * 
	 * @param operacaoEfetuada
	 * @param tabelaLinhaAlteracao
	 * @param tabelaLinhaColunaAlteracoes
	 * @throws ControladorException
	 */
	public void inserirOperacaoEfetuada(Collection usuariosAcaoUsuarioHelp,
			OperacaoEfetuada operacaoEfetuada,
			TabelaLinhaAlteracao tabelaLinhaAlteracao,
			Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes)
			throws ControladorException {

		// caso a operacaoEfetuada for nula
		// Nao ocorre pq o intercepto levanta execao caso nao tenha
		if (operacaoEfetuada == null) {
			operacaoEfetuada = new OperacaoEfetuada();
		}
		// caso a operacao da operacaoEfetuada for nula
		// Nao ocorre pq o interceptador levanta excecao caso nao tenha
		if (operacaoEfetuada.getOperacao() == null) {
			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_AVISO_BANCARIO_ATUALIZAR);
			operacaoEfetuada.setOperacao(operacao);
		}

		// caso nao tenha id é pq nao foi adicionado
		if (operacaoEfetuada.getId() == null) {
			operacaoEfetuada.setUltimaAlteracao(new Date(System
					.currentTimeMillis()));
			Integer idOperacaoEfetuada = (Integer) getControladorUtil().inserirOuAtualizar(operacaoEfetuada);
			operacaoEfetuada.setId(idOperacaoEfetuada);

			if (usuariosAcaoUsuarioHelp != null
					&& !usuariosAcaoUsuarioHelp.isEmpty()) {
				Iterator it = usuariosAcaoUsuarioHelp.iterator();
				while (it.hasNext()) {
					UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelp = (UsuarioAcaoUsuarioHelper) it
							.next();

					UsuarioAlteracao usuarioAteracao = new UsuarioAlteracao();
					usuarioAteracao.setOperacaoEfetuada(operacaoEfetuada);
					usuarioAteracao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
					usuarioAteracao.setUsuario(usuarioAcaoUsuarioHelp.getUsuario());
					usuarioAteracao.setUsuarioAcao(usuarioAcaoUsuarioHelp.getUsuarioAcao());
					usuarioAteracao.setEmpresa(usuarioAcaoUsuarioHelp.getUsuario().getEmpresa());
					usuarioAteracao.setIpAlteracao(usuarioAcaoUsuarioHelp.getUsuario().getIpLogado());

					getControladorUtil().inserir(usuarioAteracao);
				}
			}
			
			// Inserir tabela linha alteracao principal -> usada para conseguir recuperar dados do objeto principal
			
		} else if (operacaoEfetuada.getDadosAdicionais() != null){
			getControladorUtil().atualizar(operacaoEfetuada);
		}

		
		if (tabelaLinhaAlteracao != null) {
			
			tabelaLinhaAlteracao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
			tabelaLinhaAlteracao.setOperacaoEfetuada(operacaoEfetuada);
			
			getControladorUtil().inserir(tabelaLinhaAlteracao);

			FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
			filtroOperacaoTabela.adicionarParametro(
				new ParametroSimples(
					FiltroOperacaoTabela.TABELA_ID, tabelaLinhaAlteracao
							.getTabela().getId()));
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.OPERACAO_ID, operacaoEfetuada
							.getOperacao().getId()));
			
			Collection coll = getControladorUtil().pesquisar(
					filtroOperacaoTabela, OperacaoTabela.class.getSimpleName());
			
			if (coll == null || coll.isEmpty()) {

				OperacaoTabelaPK pk = new OperacaoTabelaPK();
				pk.setOperacaoId(operacaoEfetuada.getOperacao().getId());
				pk.setTabelaId(tabelaLinhaAlteracao.getTabela().getId());

				OperacaoTabela operacaoTabela = new OperacaoTabela();
				operacaoTabela.setComp_id(pk);
				operacaoTabela.setOperacao(operacaoEfetuada.getOperacao());
				operacaoTabela.setTabela(tabelaLinhaAlteracao.getTabela());
				getControladorUtil().inserir(operacaoTabela);
			}

			if (tabelaLinhaColunaAlteracoes != null
					&& !tabelaLinhaColunaAlteracoes.isEmpty()) {
				Iterator it = tabelaLinhaColunaAlteracoes.iterator();
				while (it.hasNext()) {
					TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = (TabelaLinhaColunaAlteracao) it
							.next();
					tabelaLinhaColunaAlteracao
							.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);
					tabelaLinhaColunaAlteracao.setUltimaAlteracao(new Date(
							System.currentTimeMillis()));
					getControladorUtil().inserir(tabelaLinhaColunaAlteracao);
				}
			}
		}
	}
	
	/**
	 * Método que registra uma operacao ao sistema sem utilizacao do Interceptador Hibernate
	 * 
	 * @param operacaoEfetuada
	 * @param tabelaLinhaAlteracao
	 * @param tabelaLinhaColunaAlteracoes
	 * @throws ControladorException
	 * @author Anderson Italo
	 * @date 02/06/2009
	 */
	public void inserirOperacaoEfetuadaBurlandoInterceptador(UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper,
			OperacaoEfetuada operacaoEfetuada,
			TabelaLinhaAlteracao tabelaLinhaAlteracao,
			Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes)
			throws ControladorException {

		// caso a operacaoEfetuada for nula
		// Nao ocorre pq o intercepto levanta execao caso nao tenha
		if (operacaoEfetuada == null) {
			operacaoEfetuada = new OperacaoEfetuada();
		}
		
		// caso nao tenha id é pq nao foi adicionado
		if (operacaoEfetuada.getId() == null) {
			operacaoEfetuada.setUltimaAlteracao(new Date(System
					.currentTimeMillis()));
			Integer idOperacaoEfetuada = (Integer) getControladorUtil().inserirOuAtualizar(operacaoEfetuada);
			operacaoEfetuada.setId(idOperacaoEfetuada);

			if (usuarioAcaoUsuarioHelper != null) {
				
					UsuarioAlteracao usuarioAteracao = new UsuarioAlteracao();
					usuarioAteracao.setOperacaoEfetuada(operacaoEfetuada);
					usuarioAteracao.setUltimaAlteracao(new Date(System
							.currentTimeMillis()));
					usuarioAteracao.setUsuario(usuarioAcaoUsuarioHelper
							.getUsuario());
					usuarioAteracao.setUsuarioAcao(usuarioAcaoUsuarioHelper
							.getUsuarioAcao());
					usuarioAteracao.setEmpresa(usuarioAcaoUsuarioHelper
							.getUsuario().getEmpresa());
					getControladorUtil().inserir(usuarioAteracao);
			}
		} 

		
		if (tabelaLinhaAlteracao != null) {
			tabelaLinhaAlteracao.setUltimaAlteracao(new Date(System
					.currentTimeMillis()));
			tabelaLinhaAlteracao.setOperacaoEfetuada(operacaoEfetuada);
			getControladorUtil().inserir(tabelaLinhaAlteracao);

			FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.TABELA_ID, tabelaLinhaAlteracao
							.getTabela().getId()));
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.OPERACAO_ID, operacaoEfetuada
							.getOperacao().getId()));
			Collection coll = getControladorUtil().pesquisar(
					filtroOperacaoTabela, OperacaoTabela.class.getSimpleName());
			if (coll == null || coll.isEmpty()) {

				OperacaoTabelaPK pk = new OperacaoTabelaPK();
				pk.setOperacaoId(operacaoEfetuada.getOperacao().getId());
				pk.setTabelaId(tabelaLinhaAlteracao.getTabela().getId());

				OperacaoTabela operacaoTabela = new OperacaoTabela();
				operacaoTabela.setComp_id(pk);
				operacaoTabela.setOperacao(operacaoEfetuada.getOperacao());
				operacaoTabela.setTabela(tabelaLinhaAlteracao.getTabela());
				getControladorUtil().inserir(operacaoTabela);
			}

			if (tabelaLinhaColunaAlteracoes != null
					&& !tabelaLinhaColunaAlteracoes.isEmpty()) {
				Iterator it = tabelaLinhaColunaAlteracoes.iterator();
				while (it.hasNext()) {
					TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = (TabelaLinhaColunaAlteracao) it
							.next();
					tabelaLinhaColunaAlteracao
							.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);
					tabelaLinhaColunaAlteracao.setUltimaAlteracao(new Date(
							System.currentTimeMillis()));
					if (tabelaLinhaAlteracao.getAlteracaoTipo().getId() == AlteracaoTipo.INCLUSAO && 
							tabelaLinhaColunaAlteracao.getConteudoColunaAtual() != null){
						getControladorUtil().inserir(tabelaLinhaColunaAlteracao);
						
					}else if (tabelaLinhaAlteracao.getAlteracaoTipo().getId() == AlteracaoTipo.EXCLUSAO && 
							tabelaLinhaColunaAlteracao.getConteudoColunaAnterior() != null){
							getControladorUtil().inserir(tabelaLinhaColunaAlteracao);
					}
				}
			}
		}
	}
	
	/**
	 * Metodo utilizado para efetuar o registro de transacao de um objeto helper
	 * @author Anderson Italo
	 * @date 08/06/2009
	 *
	 * @param usuario
	 * @param idTipoAlteracao
	 * @param objetoHelper
	 * @param operacaoEfetuada
	 * @param idTabela
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void processaRegistroOperacaoObjetohelper(UsuarioAcaoUsuarioHelper usuario, Integer idTipoAlteracao, 
												     ObjetoTransacao objetoHelper, OperacaoEfetuada operacaoEfetuada, Integer idTabela) {
		
		AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
		alteracaoTipo.setId(idTipoAlteracao);
		
		Tabela tabela = new Tabela();
		tabela.setId(idTabela);
		
		TabelaLinhaAlteracao tabelaLinhaAuteracao = new TabelaLinhaAlteracao();
		tabelaLinhaAuteracao.setAlteracaoTipo(alteracaoTipo);
		tabelaLinhaAuteracao.setTabela(tabela);
		
		
	    Collection<TabelaLinhaColunaAlteracao> colecaoTabelaLinhaColunaAlteracao = null;
		try {
			colecaoTabelaLinhaColunaAlteracao = pesquisarTabelaLinhaColunaAlteracoes(objetoHelper, 
														tabelaLinhaAuteracao, objetoHelper.retornarAtributosSelecionadosRegistro(), alteracaoTipo.getId());
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
	    
	    if (colecaoTabelaLinhaColunaAlteracao != null && !colecaoTabelaLinhaColunaAlteracao.isEmpty()){
	    	try {
				inserirOperacaoEfetuadaBurlandoInterceptador(usuario, operacaoEfetuada, tabelaLinhaAuteracao, colecaoTabelaLinhaColunaAlteracao);
			} catch (ControladorException e) {
				e.printStackTrace();
			}
	    }
	}
	
	/**
	 * @author Anderson Italo
	 * @date 08/06/2009
	 *
	 * @param objetoHelper
	 * @param tabelaLinhaAlteracao
	 * @param colecaoNomesAtributos
	 * @param idAlteracaoTipo
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Collection pesquisarTabelaLinhaColunaAlteracoes(ObjetoTransacao objetoHelper, 
			TabelaLinhaAlteracao tabelaLinhaAlteracao, String [] colecaoNomesAtributos, Integer idAlteracaoTipo) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
			Collection<TabelaLinhaColunaAlteracao> colecaoRetorno = new ArrayList<TabelaLinhaColunaAlteracao>();
		String nomeMetodo="";
		Method metodo = null;
		
		for (int i = 0; i < colecaoNomesAtributos.length; i++) {
			nomeMetodo = "get";
			Object[] args = {colecaoNomesAtributos[i]};
			Class[] tipos = {String.class};
			int idTabelaColuna = 0;
			
			Object retornoMetodo = null;
			
		    metodo = objetoHelper.getClass().getMethod(nomeMetodo, tipos);
		
		    retornoMetodo = metodo.invoke(objetoHelper, args);
			
			
			if (retornoMetodo!= null){
				try {
					Annotation annotations[] = objetoHelper.getClass().getDeclaredField(colecaoNomesAtributos[i]).getAnnotations();
					
					for (int j = 0; j < annotations.length; j++) {
						if (annotations[j] instanceof ControleAlteracao){
							ControleAlteracao controleAlteracao = (ControleAlteracao) annotations[j];
							idTabelaColuna = controleAlteracao.idTabelaColuna();
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
				
				TabelaColuna tabelaColuna = new TabelaColuna();
				tabelaColuna.setId(idTabelaColuna);
				
				TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = new TabelaLinhaColunaAlteracao();
				
				if (retornoMetodo instanceof ObjetoTransacao){
				    if (AlteracaoTipo.INCLUSAO == idAlteracaoTipo.intValue()){
				    	tabelaLinhaColunaAlteracao.setConteudoColunaAtual(Interceptador.consultarDescricao(retornoMetodo));
				    }else{
				    	tabelaLinhaColunaAlteracao.setConteudoColunaAnterior(Interceptador.consultarDescricao(retornoMetodo));
				    }
				}
				
			    
			    tabelaLinhaColunaAlteracao.setIndicadorAtualizada(new Integer(1).shortValue());
			    tabelaLinhaColunaAlteracao.setTabelaColuna(tabelaColuna);
			    tabelaLinhaColunaAlteracao.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);
			    
			    colecaoRetorno.add(tabelaLinhaColunaAlteracao);
			}
		   
		}
		
		return colecaoRetorno;
	}

	/**
	 * 
	 * Registrar Transacao - Inserir operacao efetuada
	 * @author anamaria
	 * @date 12/05/2009
	 *
	 * @param usuariosAcaoUsuarioHelp
	 * @param operacaoEfetuada
	 * @param tabelaAtualizacaoCadastral
	 * @param colecaoTabelaColunaAtualizacaoCadastral
	 * @throws ControladorException
	 */
	public void inserirOperacaoEfetuadaAtualizacaoCadastral(Collection usuariosAcaoUsuarioHelp,
			OperacaoEfetuada operacaoEfetuada,
			TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral,
			Collection<TabelaColunaAtualizacaoCadastral> colecaoTabelaColunaAtualizacaoCadastral)
			throws ControladorException {

		// caso a operacaoEfetuada for nula
		// Nao ocorre pq o intercepto levanta execao caso nao tenha
		if (operacaoEfetuada == null) {
			operacaoEfetuada = new OperacaoEfetuada();
		}
		
		// caso nao tenha id é pq nao foi adicionado
		if (operacaoEfetuada.getId() == null) {
			operacaoEfetuada.setUltimaAlteracao(new Date(System
					.currentTimeMillis()));
			Integer idOperacaoEfetuada = (Integer) getControladorUtil().inserirOuAtualizar(operacaoEfetuada);
			operacaoEfetuada.setId(idOperacaoEfetuada);

			if (usuariosAcaoUsuarioHelp != null
					&& !usuariosAcaoUsuarioHelp.isEmpty()) {
				Iterator it = usuariosAcaoUsuarioHelp.iterator();
				while (it.hasNext()) {
					UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelp = (UsuarioAcaoUsuarioHelper) it
							.next();

					UsuarioAlteracao usuarioAteracao = new UsuarioAlteracao();
					usuarioAteracao.setOperacaoEfetuada(operacaoEfetuada);
					usuarioAteracao.setUltimaAlteracao(new Date(System
							.currentTimeMillis()));
					usuarioAteracao.setUsuario(usuarioAcaoUsuarioHelp
							.getUsuario());
					usuarioAteracao.setUsuarioAcao(usuarioAcaoUsuarioHelp
							.getUsuarioAcao());
					usuarioAteracao.setEmpresa(usuarioAcaoUsuarioHelp
							.getUsuario().getEmpresa());
					getControladorUtil().inserir(usuarioAteracao);
				}
			}
			
			// Inserir tabela linha alteracao principal -> usada para conseguir recuperar dados do objeto principal
			
		} else if (operacaoEfetuada.getDadosAdicionais() != null){
			getControladorUtil().atualizar(operacaoEfetuada);
		}

		
		if (tabelaAtualizacaoCadastral != null) {
			tabelaAtualizacaoCadastral.setUltimaAlteracao(new Date(System
					.currentTimeMillis()));
			tabelaAtualizacaoCadastral.setOperacaoEfetuada(operacaoEfetuada);
			getControladorUtil().inserir(tabelaAtualizacaoCadastral);

			FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.TABELA_ID, tabelaAtualizacaoCadastral
							.getTabela().getId()));
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.OPERACAO_ID, operacaoEfetuada
							.getOperacao().getId()));
			Collection coll = getControladorUtil().pesquisar(
					filtroOperacaoTabela, OperacaoTabela.class.getSimpleName());
			if (coll == null || coll.isEmpty()) {

				OperacaoTabelaPK pk = new OperacaoTabelaPK();
				pk.setOperacaoId(operacaoEfetuada.getOperacao().getId());
				pk.setTabelaId(tabelaAtualizacaoCadastral.getTabela().getId());

				OperacaoTabela operacaoTabela = new OperacaoTabela();
				operacaoTabela.setComp_id(pk);
				operacaoTabela.setOperacao(operacaoEfetuada.getOperacao());
				operacaoTabela.setTabela(tabelaAtualizacaoCadastral.getTabela());
				getControladorUtil().inserir(operacaoTabela);
			}

			if (colecaoTabelaColunaAtualizacaoCadastral != null
					&& !colecaoTabelaColunaAtualizacaoCadastral.isEmpty()) {
				Iterator it = colecaoTabelaColunaAtualizacaoCadastral.iterator();
				while (it.hasNext()) {
					TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral= (TabelaColunaAtualizacaoCadastral) it
							.next();
					
					// Pesquisando o objeto de TabelaColuna
					TabelaColuna tabelaColuna = tabelaColunaAtualizacaoCadastral.getTabelaColuna();
					if (tabelaColuna != null && tabelaColuna.getColuna() != null) {
						FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
						filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.TABELA_ID, tabelaAtualizacaoCadastral
								.getTabela().getId()));
						filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.COLUNA, tabelaColuna.getColuna()));
						Collection collTabelaColuna = this.getControladorUtil().pesquisar(filtroTabelaColuna, TabelaColuna.class.getSimpleName());
						
						if (collTabelaColuna != null && !collTabelaColuna.isEmpty()) {
							tabelaColuna = (TabelaColuna) collTabelaColuna.iterator().next();
						}
						tabelaColunaAtualizacaoCadastral.setTabelaColuna(tabelaColuna);
					}
						
					tabelaColunaAtualizacaoCadastral
							.setTabelaAtualizacaoCadastral(tabelaAtualizacaoCadastral);
					tabelaColunaAtualizacaoCadastral.setUltimaAlteracao(new Date());
					getControladorUtil().inserir(tabelaColunaAtualizacaoCadastral);
				}
			}
		}
	}
	
	public Integer pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(
			Integer idUsuarioAcao,String[] idOperacoes, String idUsuario,
			Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal,
			Hashtable<String,String> argumentos, Integer id1, String unidadeNegocio)
			throws ControladorException {
		Integer retorno = null;
		try {
			retorno = (Integer) repositorioTransacao
					.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(
							idUsuarioAcao, idOperacoes, idUsuario, dataInicial,
							dataFinal, horaInicial, horaFinal, argumentos, id1, unidadeNegocio);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;

	}

	/**
	 * Registrar 
	 * @param objetoTransacao
	 * @throws ControladorException
	 */
	public void registrarTransacao(ObjetoTransacao objetoTransacao) 
		throws ControladorException {

		if (objetoTransacao.retornarAtributosSelecionadosRegistro() == null){
			sessionContext.setRollbackOnly();
			// Este método só deve ser chamado caso tenha sido definida 
			// a coleção de atributos selecionados para o registro da transacao
			throw new ControladorException("erro.sistema");			
		}
		
		Interceptador.getInstancia().verificarObjetoAlterado(objetoTransacao,
				objetoTransacao.retornarAtributosSelecionadosRegistro());
	}
	

	/**
	 * Registrar transacao para um conjunto de atributos especificos
	 * 
	 * @param objetoTransacao
	 * @throws ControladorException
	 * @author Francisco do Nascimento
	 * @date 11/08/09
	 */
	public void registrarTransacao(ObjetoTransacao objetoTransacao, String[] atributos) 
		throws ControladorException {

		if (atributos == null){
			sessionContext.setRollbackOnly();
			// Este método só deve ser chamado caso tenha sido definida 
			// a coleção de atributos selecionados para o registro da transacao
			throw new ControladorException("erro.sistema");			
		}
		
		Interceptador.getInstancia().verificarObjetoAlterado(objetoTransacao,
				atributos);	
	}
	
	public HashMap consultarResumoInformacoesOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada, int idItemAnalisado){

		
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroOperacao.ARGUMENTO_PESQUISA);
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroOperacao.ARGUMENTO_PESQUISA_TABELA);
		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID,
				operacaoEfetuada.getOperacao().getId()));
		
		Collection coll = Fachada.getInstancia().pesquisar(filtroOperacao,
				Operacao.class.getSimpleName());
		
		Operacao operacao = (Operacao) coll.iterator().next();
		// Consultando dados para o preenchimento das informações do item
		HashMap<String, Object> dados = new HashMap<String,Object>();

		try {
		
			// Tabela principal da operacao efetuada é considerada ser a tabela do argumento de pesquisa
			if (operacao.getArgumentoPesquisa() != null){
				String nomeTabela = operacao.getArgumentoPesquisa().getTabela().getNomeTabela();
				String nomeClasse = HibernateUtil.getClassName(nomeTabela);
				Object instancia = Class.forName(nomeClasse).newInstance();

				if (instancia instanceof ObjetoTransacao){ 
					ObjetoTransacao objTran = (ObjetoTransacao) instancia;
					objTran.set("id",Integer.class, idItemAnalisado);
					Filtro filtro = objTran.retornaFiltro();
					
					Collection objs = getControladorUtil().pesquisar(filtro, nomeClasse);
					
					if(objs.iterator().hasNext()){
						objTran = (ObjetoTransacao) objs.iterator().next();
						
						String[] atributos = objTran.retornarAtributosInformacoesOperacaoEfetuada();
						String[] labels = objTran.retornarLabelsInformacoesOperacaoEfetuada();
						
						for (int i = 0; i < atributos.length; i++) {
							dados.put(labels[i], objTran.get(atributos[i]));
						}					
					}
				}			
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ControladorException e) {
			e.printStackTrace();
		}	
		return dados;
	}
	
	public void ordenarTabelaLinhaColunaAlteracao(Collection linhas, int idOperacao) 
		throws ControladorException	{
		
		int[] idTCs = new int[linhas.size()];
		int i = 0;
		for (Iterator iter = linhas.iterator(); iter.hasNext();) {
			TabelaLinhaColunaAlteracao tlca = (TabelaLinhaColunaAlteracao) iter.next();
			idTCs[i++] = tlca.getTabelaColuna().getId();			
		}
		Collection ordem = null;
		try{
			ordem = repositorioTransacao.pesquisarOperacaoOrdemExibicao(idTCs, idOperacao);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		final Map mapOrdem = new HashMap();
		Iterator iterOrdem = ordem.iterator(); 
		while (iterOrdem.hasNext()) {
			OperacaoOrdemExibicao opOrdem = (OperacaoOrdemExibicao) iterOrdem.next();
			mapOrdem.put(opOrdem.getTabelaColuna().getId(), opOrdem.getNumeroOrdem()); // idTabcoluna e ordem
		}
		
		// Ordenador pelo Id da tabela para agrupar os itens atualizados
		class ComparadorTLCA implements Comparator {
						
		    public int compare(Object obj1, Object obj2){
		    	TabelaLinhaColunaAlteracao tlca1 = (TabelaLinhaColunaAlteracao) obj1;
		    	TabelaLinhaColunaAlteracao tlca2 = (TabelaLinhaColunaAlteracao) obj2;
		    	if (obj1 instanceof TabelaLinhaColunaAlteracao && obj2 instanceof TabelaLinhaColunaAlteracao){
		    		
		    		int idTab1 = tlca1.getTabelaLinhaAlteracao().getId();
		    		int idTab2 = tlca2.getTabelaLinhaAlteracao().getId();
		    		
		    		int dif = idTab2 - idTab1;
		    		if (dif == 0){
			            Object ordem =  mapOrdem.get(tlca1.getTabelaColuna().getId());
			            int i1 = 999;
			            if (ordem != null){
			            	i1 = ((Integer) ordem).intValue();
			            }		    			
			            ordem =  mapOrdem.get(tlca2.getTabelaColuna().getId());
			            int i2 = 999;
			            if (ordem != null){
			            	i2 = ((Integer) ordem).intValue();
			            }
			            
			            dif = i1 - i2;
		    		}

		            return dif;
		    	} else {
		    		return 0;
		    	}
		    }
		}
		
		Collections.sort((List)linhas, new ComparadorTLCA());

	}

	/**
	 * Pesquisa a quantidade de registros na tabela de Operação Efetuada
	 * para os argumentos passados.
	 * 		
	 * @author Yara Taciane
	 * @date 15/07/2008
	 *
	 * @param idOperacao
	 * @param argumentoValor
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOperacaoEfetuada(Integer idOperacao,
			Integer argumentoValor,Integer id2)throws ControladorException{
		Integer retorno = null;
		try {
			retorno =  repositorioTransacao
			.pesquisarOperacaoEfetuada(idOperacao,argumentoValor,id2);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/**
	 * 
	 * Pesquisa os registros na TabelaLinhaColunaAlteracao para o argumento passado.
	 * 
	 * @author Yara Taciane
	 * @date 15/07/2008
	 *
	 * @param idTabelaColuna
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTabelaLinhaColunaAlteracao(Integer idObjetoAlterado,
			Integer idTabelaColuna)throws ControladorException{
		Integer retorno = null;
		try {
			retorno =  repositorioTransacao
			.pesquisarTabelaLinhaColunaAlteracao(idObjetoAlterado, idTabelaColuna);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public Map<String, List<DadosTabelaAtualizacaoCadastralHelper>> consultarDadosTabelaColunaAtualizacaoCadastral(Long idRegistroAlterado, Integer idArquivo,
			Integer idImovel, Long idCliente, Integer idTipoAlteracao) throws Exception {

		Map<String, List<DadosTabelaAtualizacaoCadastralHelper>> retorno = new HashMap<String, List<DadosTabelaAtualizacaoCadastralHelper>>();
		DadosTabelaAtualizacaoCadastralHelper helper = null;

		List listaDados = repositorioTransacao.consultarDadosTabelaColunaAtualizacaoCadastral(idRegistroAlterado, idArquivo, idImovel, idCliente,
				idTipoAlteracao);

		if (listaDados.size() > 0) {
			Object obj = null;
			Object[] dados = null;

			for (int i = 0; i < listaDados.size(); i++) {
				obj = listaDados.get(i);
				if (obj instanceof Object[]) {
					dados = (Object[]) obj;
					helper = new DadosTabelaAtualizacaoCadastralHelper();

					helper.setIdTabelaAtualizacaoCadastral((Integer) dados[0]); 
					helper.setIdTabela((Integer) dados[1]); 
					helper.setDescricaoTabela((String) dados[2]); 
					if (dados[14] != null) {
						helper.setDescricaoTabela(helper.getDescricaoTabela() + " " + String.valueOf(dados[14]));
						helper.setComplemento(String.valueOf(dados[14]));
					}
					helper.setIdTabelaColuna((Integer) dados[3]); 
					helper.setDescricaoColuna((String) dados[4]); 
					helper.setIdTabelaColunaAtualizacaoCadastral((Integer) dados[5]); 
					if (dados[6] != null && !dados[6].equals("")) {
						String campoAnterior = getDescricaoCampoAtualizacaoCadastral((String) dados[6], (String) dados[12]);
						if (campoAnterior != null) {
							helper.setColunaValorAnterior(campoAnterior);
						} else {
							helper.setColunaValorAnterior((String) dados[6]);
						}
					}
					if (dados[7] != null && !dados[7].equals("")) {
						String campoAtual = getDescricaoCampoAtualizacaoCadastral((String) dados[7], (String) dados[12]);
						if (campoAtual != null) {
							helper.setColunaValorAtual(campoAtual); 
						} else {
							helper.setColunaValorAtual((String) dados[7]); 
						}
					}
					helper.setNomeColuna((String) dados[12]);
					helper.setIndicadorAutorizado((Short) dados[8]); 
					helper.setUltimaAtualizacao((Date) dados[9]); 
					helper.setIdAlteracaoTipo((Integer) dados[10]); 
					helper.setDescricaoAlteracaoTipo((String) dados[11]); 
					if (dados[13] != null) {
						helper.setDataValidacao((Date) dados[13]);
					}
					if (dados[15] != null) {
						helper.setNomeUsuario((String) dados[15]);
					}

					List<DadosTabelaAtualizacaoCadastralHelper> alteracoes = retorno.get(helper.getNomeColuna());
					if (alteracoes == null){
						alteracoes = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
						retorno.put(helper.getNomeColuna(), alteracoes);
					}
					alteracoes.add(helper);
				}
			}
		}

		return retorno;
	}
	
	private String getDescricaoCampoAtualizacaoCadastral(String campo, String coluna)
		throws ControladorException {
		String  retorno = null;
		if(coluna != null && !coluna.equals("") && StringUtils.isNotEmpty(campo)){
			if(coluna.equals("last_id")){
				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(pesquisa);
					retorno = ligacaoAguaSituacao.getDescricao();
				}
			}else if(coluna.equals("lest_id")){
				FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
				filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(pesquisa);
					retorno = ligacaoEsgotoSituacao.getDescricao();
				}				
			}else if(coluna.equals("ftab_id")){
				FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
				filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(FiltroFonteAbastecimento.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					FonteAbastecimento fonteAbastecimento = (FonteAbastecimento) Util.retonarObjetoDeColecao(pesquisa);
					retorno = fonteAbastecimento.getDescricao();				
				}					
			}else if(coluna.equals("crtp_id")){
				FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
				filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					ClienteRelacaoTipo clienteRelacaoTipo = (ClienteRelacaoTipo) Util.retonarObjetoDeColecao(pesquisa);
					retorno = clienteRelacaoTipo.getDescricao();				
				}					
			}else if(coluna.equals("cltp_id")){
				FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
				filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroClienteTipo, ClienteTipo.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					ClienteTipo clienteTipo = (ClienteTipo) Util.retonarObjetoDeColecao(pesquisa);
					retorno = clienteTipo.getDescricao();				
				}					
			}else if(coluna.equals("prof_id")){
				FiltroProfissao filtroProfissao = new FiltroProfissao();
				filtroProfissao.adicionarParametro(new ParametroSimples(FiltroProfissao.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroProfissao, Profissao.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					Profissao profissao = (Profissao) Util.retonarObjetoDeColecao(pesquisa);
					retorno = profissao.getDescricao();				
				}					
			}else if(coluna.equals("ratv_id")){
				FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
				filtroRamoAtividade.adicionarParametro(new ParametroSimples(FiltroRamoAtividade.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroRamoAtividade, RamoAtividade.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					RamoAtividade ramoAtividade = (RamoAtividade) Util.retonarObjetoDeColecao(pesquisa);
					retorno = ramoAtividade.getDescricao();				
				}					
			}else if(coluna.equals("edtp_id")){
				FiltroEnderecoTipo filtroEnderecoTipo = new FiltroEnderecoTipo();
				filtroEnderecoTipo.adicionarParametro(new ParametroSimples(FiltroEnderecoTipo.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroEnderecoTipo, EnderecoTipo.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					EnderecoTipo enderecoTipo = (EnderecoTipo) Util.retonarObjetoDeColecao(pesquisa);
					retorno = enderecoTipo.getDescricao();				
				}					
			}else if(coluna.equals("edrf_id")){
				FiltroEnderecoReferencia filtroEnderecoReferencia = new FiltroEnderecoReferencia();
				filtroEnderecoReferencia.adicionarParametro(new ParametroSimples(FiltroEnderecoReferencia.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroEnderecoReferencia, EnderecoReferencia.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					EnderecoReferencia enderecoReferencia = (EnderecoReferencia) Util.retonarObjetoDeColecao(pesquisa);
					retorno = enderecoReferencia.getDescricao();				
				}					
			}else if(coluna.equals("fnet_id")){
				FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
				filtroFoneTipo.adicionarParametro(new ParametroSimples(FiltroFoneTipo.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroFoneTipo, FoneTipo.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					FoneTipo foneTipo = (FoneTipo) Util.retonarObjetoDeColecao(pesquisa);
					retorno = foneTipo.getDescricao();				
				}					
			}else if(coluna.equals("cocr_id")){
				FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
				filtroCadastroOcorrencia.adicionarParametro(new ParametroSimples(FiltroCadastroOcorrencia.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					CadastroOcorrencia cadastroOcorrencia = (CadastroOcorrencia) Util.retonarObjetoDeColecao(pesquisa);
					retorno = cadastroOcorrencia.getDescricao();				
				}					
			} else if (coluna.equals("hicp_id")) {
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) Util.retonarObjetoDeColecao(pesquisa);
					retorno = hidrometroCapacidade.getDescricao();				
				}
			} else if (coluna.equals("himc_id")) {
				FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
				filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroHidrometroMarca, HidrometroMarca.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					HidrometroMarca hidrometroMarca = (HidrometroMarca) Util.retonarObjetoDeColecao(pesquisa);
					retorno = hidrometroMarca.getDescricao();				
				}
			} else if (coluna.equals("hili_id")) {
				FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();
				filtroHidrometroLocalInstalacao.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalInstalacao.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroHidrometroLocalInstalacao, HidrometroLocalInstalacao.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					HidrometroLocalInstalacao hidrometroLocalInstalacao = (HidrometroLocalInstalacao) Util.retonarObjetoDeColecao(pesquisa);
					retorno = hidrometroLocalInstalacao.getDescricao();				
				}
			} else if (coluna.equals("hipr_id")) {
				FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();
				filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(FiltroHidrometroProtecao.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroHidrometroProtecao, HidrometroProtecao.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					HidrometroProtecao hidrometroProtecao = (HidrometroProtecao) Util.retonarObjetoDeColecao(pesquisa);
					retorno = hidrometroProtecao.getDescricao();				
				}
			} else if (coluna.equals("rlin_id")) {
				FiltroRamalLocalInstalacao filtroRamalLocalInstalacao = new FiltroRamalLocalInstalacao();
				filtroRamalLocalInstalacao.adicionarParametro(new ParametroSimples(FiltroRamalLocalInstalacao.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroRamalLocalInstalacao, RamalLocalInstalacao.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					RamalLocalInstalacao ramalLocalInstalacao = (RamalLocalInstalacao) Util.retonarObjetoDeColecao(pesquisa);
					retorno = ramalLocalInstalacao.getDescricao();				
				}
			}
		}
		return retorno;
	}

	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> pesquisarMovimentoAtualizacaoCadastral(FiltrarAlteracaoAtualizacaoCadastralActionHelper helper)throws ControladorException {

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> retorno = null;
		
		try {
			retorno = repositorioTransacao.pesquisarMovimentoAtualizacaoCadastral(helper);
			
			Map<String, String> descricoes = new HashMap<String, String>();
			
			String nomeColuna = "";
			String valorCampo = "";
			for (ConsultarMovimentoAtualizacaoCadastralHelper item : retorno) {
				List<ColunaAtualizacaoCadastral> colunas = item.getColunasAtualizacao();
				
				for (ColunaAtualizacaoCadastral coluna : colunas) {
					nomeColuna = coluna.getNomeColuna();
					valorCampo = coluna.getValorAnterior();
					valorCampo = trocaValorColuna(descricoes, nomeColuna, valorCampo);
					coluna.setValorAnterior(valorCampo);

					valorCampo = coluna.getValorAtual();
					valorCampo = trocaValorColuna(descricoes, nomeColuna, valorCampo);
					coluna.setValorAtual(valorCampo);
				}
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	private String trocaValorColuna(Map<String, String> descricoes,
			String nomeColuna, String valorCampo) throws ControladorException {

		if (StringUtils.isNotEmpty(valorCampo)) {
			if (descricoes.get(nomeColuna + valorCampo) != null) {
				valorCampo = descricoes.get(nomeColuna + valorCampo);
			} else {
				String descricao = getDescricaoCampoAtualizacaoCadastral(valorCampo, nomeColuna);
				
				if (descricao != null) {
					valorCampo = descricao;
					descricoes.put(nomeColuna + valorCampo, valorCampo);
				}
			}
		}
		
		return valorCampo;
	}
	
	public void atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(
			Integer idImovel, String[] idsAtualizacaoCadastral,
			Short indicador, Usuario usuarioLogado) throws ControladorException {
		
		try {
			Integer tipoAlteracaoCadastral = null;
			
			for (int i = 0; i < idsAtualizacaoCadastral.length; i++) {
				
				Integer idAtualizacaoCadastral = new Integer(idsAtualizacaoCadastral[i]);
				
				this.repositorioTransacao.atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(
						idAtualizacaoCadastral, indicador, usuarioLogado);
				
				if(tipoAlteracaoCadastral == null) {
					TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral = 
						this.repositorioTransacao.pesquisarTabelaColunaAtualizacaoCadastral(idAtualizacaoCadastral);
					
					tipoAlteracaoCadastral = tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getAlteracaoTipo().getId();
				}
			}
			
			boolean existePendencia = repositorioTransacao.existeAlteracaoNaoAprovadaParaImovel(idImovel);
			
			if (!existePendencia){
				repositorioCadastro.liberarCadastroImovel(idImovel);
				repositorioAtualizacaoCadastral.liberarCadastroImovel(idImovel);
				repositorioSeguranca.autorizarAtualizacaoCadastral(idImovel);
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}		
	}
	
	public void atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(
			Integer idArgumento, Short indicador)throws ControladorException {
		try {
			this.repositorioTransacao.atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(idArgumento, indicador);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}		
	}
	
	public void pesquisarRegistroAutorizadoTabelaAtualizacaoCadastral(
			String idEmpresa, String idArquivo, String idLeiturista) throws ControladorException {
		
		try {
			List listaRegistros = repositorioTransacao.pesquisarRegistroAutorizadoTabelaAtualizacaoCadastral(idEmpresa, idArquivo, idLeiturista);
			
			if (listaRegistros.size() > 0) {
				Object obj = null;
				Object[] dados = null;
				
				for (int i = 0; i < listaRegistros.size(); i++) {
					obj = listaRegistros.get(i);
					if (obj instanceof Object[]) {
						dados = (Object[]) obj;
						if(dados[1].equals(Tabela.IMOVEL_ATUALIZACAO_CADASTRAL)){
							 
						}else if(dados[1].equals(Tabela.CLIENTE_ATUALIZACAO_CADASTRAL)){
							List listaColuna = repositorioTransacao.pesquisarRegistroAutorizadoTabelaColunaAtualizacaoCadastral((Integer) dados[2]);
							if(listaColuna.size() > 0){

								Iterator listaColunaIter = listaColuna.iterator();
								while (listaColunaIter.hasNext()) {
									Object[] coluna = (Object[]) listaColunaIter.next();
									if(coluna[0].equals("clac_nnimovel")){
										
									}
								}
							}
						}else if(dados[1].equals(Tabela.CLIENTE_FONE_ATUALIZACAO_CADASTRAL)){
							List listaColuna = repositorioTransacao.pesquisarRegistroAutorizadoTabelaColunaAtualizacaoCadastral((Integer) dados[2]);
							if(listaColuna.size() > 0){
								ClienteFone clienteFone = new ClienteFone();
								Iterator listaColunaIter = listaColuna.iterator();
								while (listaColunaIter.hasNext()) {
									Object[] coluna = (Object[]) listaColunaIter.next();
									
									if(dados[3].equals(AlteracaoTipo.ALTERACAO)){
										
										
									}else if(dados[3].equals(AlteracaoTipo.INCLUSAO)){
										Cliente cliente = new Cliente();
										cliente.setId((Integer)dados[0]);
										clienteFone.setCliente(cliente);
										if(coluna[0].equals("fnet_id")){
											FoneTipo foneTipo = new FoneTipo();
											foneTipo.setId((Integer)coluna[1]);
											clienteFone.setFoneTipo(foneTipo);	
										}
										if(coluna[0].equals("cfac_cdddd")){
											FoneTipo foneTipo = new FoneTipo();
											foneTipo.setId((Integer)coluna[1]);
											clienteFone.setFoneTipo(foneTipo);	
										}
										if(coluna[0].equals("cfac_nnfone")){
											FoneTipo foneTipo = new FoneTipo();
											foneTipo.setId((Integer)coluna[1]);
											clienteFone.setFoneTipo(foneTipo);	
										}
										if(coluna[0].equals("cfac_nnfoneramal")){
											FoneTipo foneTipo = new FoneTipo();
											foneTipo.setId((Integer)coluna[1]);
											clienteFone.setFoneTipo(foneTipo);	
										}
									}									
								}
							}
							
						}else if(dados[1].equals(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL)){
							
						}
					}
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}		
	}
	
	/**
	 * @author Genival barbosa
	 * @date 27/07/2009
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ControladorException
	 */
	public void atualizarIndicadorAutorizacaoAtualizacaoCadastral(
			Integer idAtualizacaoCadastral,
			Short indicador) throws ControladorException {
		try {
			this.repositorioTransacao.atualizarIndicadorAutorizacaoAtualizacaoCadastral(idAtualizacaoCadastral, indicador);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}		
	}
	
	/**
	 * CRC2103 - [FS0026] - Verificar existencia de operacao inserir/manter cliente pendente de atualizacao do imovel. 
	 *
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idImovel
	 * @param colecaoClientes
	 * @throws ControladorException
	 */
	public void verificarAtualizarOperacaoPendente(
			Integer idImovel,
			Collection colecaoClientes,
			Integer idUsuario) throws ControladorException {

		Integer idGrupoAtributo = null;
		
		try {
			if (colecaoClientes != null && !colecaoClientes.isEmpty()) {
				Iterator iColecaoCliente = colecaoClientes.iterator();
				while (iColecaoCliente.hasNext()) {
					ClienteImovel clienteImovel = (ClienteImovel) iColecaoCliente.next();
					Integer idOperacaoEfetuada = (Integer) repositorioTransacao.verificarOperacaoPendente(
							clienteImovel.getCliente().getId(),
							idUsuario);
					
					if (idOperacaoEfetuada != null) {
						if (clienteImovel.getClienteRelacaoTipo().getId().equals(new Integer(ClienteRelacaoTipo.PROPRIETARIO))) {
							idGrupoAtributo = AtributoGrupo.ATRIBUTOS_DO_PROPRIETARIO;
						}else if (clienteImovel.getClienteRelacaoTipo().getId().equals(new Integer(ClienteRelacaoTipo.USUARIO))) {
							idGrupoAtributo = AtributoGrupo.ATRIBUTOS_DO_USUARIO;
						}else {
							idGrupoAtributo = null;
						}
								
						repositorioTransacao.atualizarOperacaoEfetuadaPendente(idOperacaoEfetuada, idGrupoAtributo);
						repositorioTransacao.atualizarTabelaLinhaAlteracaoPendente(idOperacaoEfetuada, idImovel);
					}
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * @author Ana Maria
	 * @date 17/12/2009
	 *
	 * @param codigoImovel
	 * @param codigoCliente
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarClienteRelacaoTipoAtualizacaoCadastral(Integer codigoImovel, Integer codigoCliente) 
		throws ControladorException {
		try{
			this.repositorioTransacao.atualizarClienteRelacaoTipoAtualizacaoCadastral(codigoImovel, codigoCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 24/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean atualizaSituacaoLigacaoAguaImovel(Imovel imovel,Integer idCliente,
			Integer situacaoLigacaoAnterior,Integer situacaoLigacaoAtual,
			Usuario usuarioLogado) throws ControladorException{
		boolean alterouSituacaoLigacao = false;
		
		//caso a situação anterior seja igual Ligado
		if(situacaoLigacaoAnterior.equals(LigacaoAguaSituacao.LIGADO)){
			//caso a situação atual seja igual a Potencial ou Factivel
			if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.FACTIVEL)){
				alterouSituacaoLigacao = true;
			}else{
				//pesquisa a ligação de água
				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));
				Collection colecaoLigacaoAgua = getControladorUtil().pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
				LigacaoAgua ligacaoAgua = (LigacaoAgua) Util.retonarObjetoDeColecao(colecaoLigacaoAgua);
				//caso a ligação de água seja diferente de nulo
				if(ligacaoAgua != null){
					// se a situação da ligação atual for igual a cortado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.CORTADO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataCorte(new Date());
						FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
						filtroMotivoCorte.adicionarParametro(new ParametroSimples(FiltroMotivoCorte.ID, MotivoCorte.ATUALIZACAO_CADASTRAL));
						Collection colecaoMotivoCorte = getControladorUtil().pesquisar(filtroMotivoCorte, MotivoCorte.class.getName());
						MotivoCorte motivoCorte = (MotivoCorte) Util.retonarObjetoDeColecao(colecaoMotivoCorte);
						ligacaoAgua.setMotivoCorte(motivoCorte);
						FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();
						filtroCorteTipo.adicionarParametro(new ParametroSimples(FiltroCorteTipo.ID, CorteTipo.RAMAL_ID));
						Collection colecaoCorteTipo = getControladorUtil().pesquisar(filtroCorteTipo, CorteTipo.class.getName());
						CorteTipo corteTipo = (CorteTipo) Util.retonarObjetoDeColecao(colecaoCorteTipo);
						ligacaoAgua.setCorteTipo(corteTipo);
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
					
					// se a situação da ligação atual for igual a cortado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.SUPRIMIDO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataSupressao(new Date());
						FiltroSupressaoMotivo filtroSupressaoMotivo = new FiltroSupressaoMotivo();
						filtroSupressaoMotivo.adicionarParametro(new ParametroSimples(FiltroSupressaoMotivo.ID, SupressaoMotivo.ATUALIZACAO_CADASTRAL));
						Collection colecaoSupressaoMotivo = getControladorUtil().pesquisar(filtroSupressaoMotivo, SupressaoMotivo.class.getName());
						SupressaoMotivo supressaoMotivo = (SupressaoMotivo) Util.retonarObjetoDeColecao(colecaoSupressaoMotivo);
						ligacaoAgua.setSupressaoMotivo(supressaoMotivo);
						FiltroSupressaoTipo filtroSupressaoTipo = new FiltroSupressaoTipo();
						filtroSupressaoTipo.adicionarParametro(new ParametroSimples(FiltroSupressaoTipo.ID, SupressaoTipo.SUPRESSAO_TIPO_ID));
						Collection colecaoSupressaoTipo = getControladorUtil().pesquisar(filtroSupressaoTipo, SupressaoTipo.class.getName());
						SupressaoTipo supressaoTipo = (SupressaoTipo) Util.retonarObjetoDeColecao(colecaoSupressaoTipo);
						ligacaoAgua.setSupressaoTipo(supressaoTipo);
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
				}
			}
		}
		
		//caso a situação anterior seja igual Cortado
		if(situacaoLigacaoAnterior.equals(LigacaoAguaSituacao.CORTADO)){
			//caso a situação atual seja igual a Potencial ou Factivel
			if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.FACTIVEL)){
				alterouSituacaoLigacao = true;
			}else{
				//pesquisa a ligação de água
				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));
				Collection colecaoLigacaoAgua = getControladorUtil().pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
				LigacaoAgua ligacaoAgua = (LigacaoAgua) Util.retonarObjetoDeColecao(colecaoLigacaoAgua);
				//caso a ligação de água seja diferente de nulo
				if(ligacaoAgua != null){
					// se a situação da ligação atual for igual a Ligado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.LIGADO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataReligacao(new Date());
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
					
					// se a situação da ligação atual for igual a cortado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.SUPRIMIDO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataSupressao(new Date());
						FiltroSupressaoMotivo filtroSupressaoMotivo = new FiltroSupressaoMotivo();
						filtroSupressaoMotivo.adicionarParametro(new ParametroSimples(FiltroSupressaoMotivo.ID, SupressaoMotivo.ATUALIZACAO_CADASTRAL));
						Collection colecaoSupressaoMotivo = getControladorUtil().pesquisar(filtroSupressaoMotivo, SupressaoMotivo.class.getName());
						SupressaoMotivo supressaoMotivo = (SupressaoMotivo) Util.retonarObjetoDeColecao(colecaoSupressaoMotivo);
						ligacaoAgua.setSupressaoMotivo(supressaoMotivo);
						FiltroSupressaoTipo filtroSupressaoTipo = new FiltroSupressaoTipo();
						filtroSupressaoTipo.adicionarParametro(new ParametroSimples(FiltroSupressaoTipo.ID, SupressaoTipo.SUPRESSAO_TIPO_ID));
						Collection colecaoSupressaoTipo = getControladorUtil().pesquisar(filtroSupressaoTipo, SupressaoTipo.class.getName());
						SupressaoTipo supressaoTipo = (SupressaoTipo) Util.retonarObjetoDeColecao(colecaoSupressaoTipo);
						ligacaoAgua.setSupressaoTipo(supressaoTipo);
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
				}
			}
		}
		
		//caso a situação anterior seja igual Ligado
		if(situacaoLigacaoAnterior.equals(LigacaoAguaSituacao.SUPRIMIDO)){
			//caso a situação atual seja igual a Potencial ou Factivel
			if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.FACTIVEL)){
				alterouSituacaoLigacao = true;
			}else{
				//pesquisa a ligação de água
				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));
				Collection colecaoLigacaoAgua = getControladorUtil().pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
				LigacaoAgua ligacaoAgua = (LigacaoAgua) Util.retonarObjetoDeColecao(colecaoLigacaoAgua);
				//caso a ligação de água seja diferente de nulo
				if(ligacaoAgua != null){
					// se a situação da ligação atual for igual a Ligado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.LIGADO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataRestabelecimentoAgua(new Date());
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
					
					// se a situação da ligação atual for igual a cortado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.CORTADO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataCorte(new Date());
						FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
						filtroMotivoCorte.adicionarParametro(new ParametroSimples(FiltroMotivoCorte.ID, MotivoCorte.ATUALIZACAO_CADASTRAL));
						Collection colecaoMotivoCorte = getControladorUtil().pesquisar(filtroMotivoCorte, MotivoCorte.class.getName());
						MotivoCorte motivoCorte = (MotivoCorte) Util.retonarObjetoDeColecao(colecaoMotivoCorte);
						ligacaoAgua.setMotivoCorte(motivoCorte);
						FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();
						filtroCorteTipo.adicionarParametro(new ParametroSimples(FiltroCorteTipo.ID, CorteTipo.RAMAL_ID));
						Collection colecaoCorteTipo = getControladorUtil().pesquisar(filtroCorteTipo, CorteTipo.class.getName());
						CorteTipo corteTipo = (CorteTipo) Util.retonarObjetoDeColecao(colecaoCorteTipo);
						ligacaoAgua.setCorteTipo(corteTipo);
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
				}
			}
		}
		
		if(alterouSituacaoLigacao){
			//atualiza a situação da ligação de água do imóvel
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, situacaoLigacaoAtual));
			Collection colecaoLigacaoAguaSituacao = getControladorUtil().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
			imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			//cria o registro de atendimento para a situação da ligação alterada
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.CODIGO_CONSTANTE, SolicitacaoTipoEspecificacao.CODIGO_CONSTANTE_ATUALIZACAO_CADASTRAL));
			Collection colecaoSolicitacaoTipoEspecificacao = getControladorUtil().pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			if(colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()){
				SolicitacaoTipoEspecificacao SolicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
				getControladorRegistroAtendimento().inserirRASituacaoLigacaoImovel(MeioSolicitacao.ATUALIZACAO_CADASTRAL_CELULAR,
						SolicitacaoTipoEspecificacao.getId(), imovel.getId(), usuarioLogado.getUnidadeOrganizacional().getId(), usuarioLogado.getId(), idCliente);
			}	
		}else{
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.senha.invalida", null,"Alteração da Situção da Ligação de Água não permitida");
		}
		
		return alterouSituacaoLigacao;	
	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 24/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean atualizaSituacaoLigacaoEsgotoImovel(Imovel imovel,Integer idCliente,
			Integer situacaoLigacaoAnterior,Integer situacaoLigacaoAtual,
			Usuario usuarioLogado) throws ControladorException{
		boolean alterouSituacaoLigacao = false;
		
		//caso a situação anterior seja igual Ligado
		if(situacaoLigacaoAnterior.equals(LigacaoEsgotoSituacao.LIGADO)){
			//caso a situação atual seja igual a Potencial ou Factivel
			if(situacaoLigacaoAtual.equals(LigacaoEsgotoSituacao.FACTIVEL)){
				//atualiza a situação da ligação de água do imóvel
				FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
				filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, situacaoLigacaoAtual));
				Collection colecaoLigacaoEsgotoSituacao = getControladorUtil().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
				imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
				//cria o registro de atendimento para a situação da ligação alterada
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.CODIGO_CONSTANTE, SolicitacaoTipoEspecificacao.CODIGO_CONSTANTE_ATUALIZACAO_CADASTRAL));
				Collection colecaoSolicitacaoTipoEspecificacao = getControladorUtil().pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
				if(colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()){
					SolicitacaoTipoEspecificacao SolicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
					getControladorRegistroAtendimento().inserirRASituacaoLigacaoImovel(MeioSolicitacao.ATUALIZACAO_CADASTRAL_CELULAR,
							SolicitacaoTipoEspecificacao.getId(), imovel.getId(), usuarioLogado.getUnidadeOrganizacional().getId(), usuarioLogado.getId(), idCliente);
				}
				alterouSituacaoLigacao = true;
			}
			
		}
		
		if(!alterouSituacaoLigacao){
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.senha.invalida", null,"Alteração da Situção da Ligação de Água não permitida");
		}
		
		return alterouSituacaoLigacao;	
	}
}
