package gcom.cadastro.cliente;

import gcom.cadastro.cliente.bean.PesquisarClienteResponsavelSuperiorHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;
import gcom.util.filtro.GeradorHQLCondicional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * O repositório faz a comunicação com a base de dados através do hibernate. O
 * cliente usa o repositório como fonte de dados.
 * 
 * @author Sávio Luiz
 */
public class RepositorioClienteHBM implements IRepositorioCliente {

	private static IRepositorioCliente instancia;

	/**
	 * Construtor da classe RepositorioCargaFuncionalidadesHBM
	 */
	private RepositorioClienteHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioCliente getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioClienteHBM();
		}

		return instancia;
	}

	/**
	 * pesquisa uma coleção de responsavel(is) de acordo com critérios
	 * existentes no filtro
	 * 
	 * @param filtroCliente
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarResponsavelSuperior(FiltroCliente filtroCliente)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		try {

			retorno = new ArrayList(new CopyOnWriteArraySet(
					GeradorHQLCondicional.gerarCondicionalQuery(filtroCliente,
							"cliente",
							"from gcom.cadastro.cliente.Cliente as cliente",
							session).list()));

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Remove todos os telefones de um determinado cliente
	 * 
	 * @param idCliente
	 *            Código do cliente que terá seus telefones apagados
	 * @exception ErroRepositorioException
	 *                Erro no BD
	 */
	public void removerTodosTelefonesPorCliente(Integer idCliente)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			Iterator iterator = session
					.createQuery(
							"from gcom.cadastro.cliente.ClienteFone where cliente = :cliente")
					.setInteger("cliente", idCliente.intValue()).iterate();

			while (iterator.hasNext()) {
				iterator.next();
				iterator.remove();

			}

			session.flush();
		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Remove todos os endereços de um determinado cliente
	 * 
	 * @param idCliente
	 *            Código do cliente que terá seus endereços apagados
	 * @exception ErroRepositorioException
	 *                Erro no BD
	 */
	public void removerTodosEnderecosPorCliente(Integer idCliente)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			Iterator iterator = session
					.createQuery(
							"from gcom.cadastro.cliente.ClienteEndereco where cliente = :cliente")
					.setInteger("cliente", idCliente.intValue()).iterate();

			while (iterator.hasNext()) {
				iterator.next();
				iterator.remove();

			}

			session.flush();
		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	public Collection<Cliente> pesquisarClienteDadosClienteEndereco(
			FiltroCliente filtroCliente, Integer numeroPagina)
			throws ErroRepositorioException {

		Collection<Cliente> retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			retorno = new ArrayList(
					new CopyOnWriteArraySet<Cliente>(
							GeradorHQLCondicional
									.gerarCondicionalQuery(
											filtroCliente,
											"cliente",
											"select distinct cliente from gcom.cadastro.cliente.Cliente as cliente "
													+ "left join cliente.clienteEnderecos clienteEndereco",
											// 						
											session).setFirstResult(
											10 * numeroPagina)
									.setMaxResults(10).list()));

			// Carrega os objetos informados no filtro
			/*
			 * if (!filtroCliente.getColecaoCaminhosParaCarregamentoEntidades()
			 * .isEmpty()) { PersistenciaUtil
			 * .processaObjetosParaCarregamento(filtroCliente
			 * .getColecaoCaminhosParaCarregamentoEntidades(), retorno); }
			 */
		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Cliente> pesquisarClienteDadosClienteEnderecoRelatorio(
			FiltroCliente filtroCliente) throws ErroRepositorioException {

		Collection<Cliente> retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			retorno = new ArrayList(
					new CopyOnWriteArraySet<Cliente>(
							GeradorHQLCondicional
									.gerarCondicionalQuery(
											filtroCliente,
											"cliente",
											"select distinct cliente from gcom.cadastro.cliente.Cliente as cliente "
													+ "left join cliente.clienteEnderecos clienteEndereco "
													+ "left join cliente.clienteImoveis clienteImovel",
											session).list()));

			// Carrega os objetos informados no filtro
			/*
			 * if (!filtroCliente.getColecaoCaminhosParaCarregamentoEntidades()
			 * .isEmpty()) { PersistenciaUtil
			 * .processaObjetosParaCarregamento(filtroCliente
			 * .getColecaoCaminhosParaCarregamentoEntidades(), retorno); }
			 */
		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * Retrona a quantidade de endereços que existem para o Cliente
	 * 
	 * pesquisarClienteDadosClienteEnderecoCount
	 * 
	 * @author Roberta Costa
	 * @date 29/06/2006
	 * 
	 * @param filtroCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarClienteDadosClienteEnderecoCount(
			FiltroCliente filtroCliente) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Integer retorno = null;

		try {
			List camposOrderBy = new ArrayList();

			camposOrderBy = filtroCliente.getCamposOrderBy();

			filtroCliente.limparCamposOrderBy();
			filtroCliente.limparColecaoCaminhosParaCarregamentoEntidades();
			
			Query query = GeradorHQLCondicional
							.gerarCondicionalQuery(
									filtroCliente,
									"cliente",
									"select count(distinct cliente) from gcom.cadastro.cliente.Cliente as cliente "
											+ "left join cliente.clienteEnderecos clienteEndereco "
											+ "left join cliente.clienteImoveis clienteImovel",
									session);

			retorno = (Integer) query.uniqueResult();

			filtroCliente.setCampoOrderBy((String[]) camposOrderBy
					.toArray(new String[camposOrderBy.size()]));
		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	// Filtrar Cliente Outros Critérios

	public Collection<Cliente> pesquisarClienteOutrosCriterios(
			FiltroCliente filtroCliente) throws ErroRepositorioException {

		Collection<Cliente> retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			retorno = new ArrayList(
					new CopyOnWriteArraySet<Cliente>(
							GeradorHQLCondicional
									.gerarCondicionalQuery(
											filtroCliente,
											"cliente",
											"select distinct cliente from gcom.cadastro.cliente.Cliente as cliente "
													+ "left join cliente.clienteEnderecos clienteEndereco "
													+ "left join cliente.clienteImoveis clienteImovel",
											session).list()));

			// Carrega os objetos informados no filtro
			/*
			 * if (!filtroCliente.getColecaoCaminhosParaCarregamentoEntidades()
			 * .isEmpty()) { PersistenciaUtil
			 * .processaObjetosParaCarregamento(filtroCliente
			 * .getColecaoCaminhosParaCarregamentoEntidades(), retorno); }
			 */
		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0242] - Registrar Movimento de Arrecadadores Author: Sávio Luiz Data:
	 * 01/02/2006
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */

	public Integer verificarExistenciaCliente(Integer idCliente)
			throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select cliente.id " + "from Cliente cliente "
					+ "where cliente.id = :idCliente";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idCliente", idCliente.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Pesquisa um cliente carregando os dados do relacionamento com ClienteTipo
	 * 
	 * [UC0321] Emitir Fatura de Cliente Responsável
	 * 
	 * @author Pedro Alexandre
	 * @date 02/05/2006
	 * 
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarCliente(Integer idCliente)
			throws ErroRepositorioException {

		// Cria uma sessão com ohibernate
		Session session = HibernateUtil.getSession();

		// Cria a variável que vai armazenar o cliente responsável
		Cliente cliente = new Cliente();
		String consulta = null;

		try {

			// Monta o HQL
			consulta = "select cliente from Cliente cliente "
					+ "INNER JOIN FETCH cliente.clienteTipo "
					+ "where cliente.id = :idCliente ";

			// Executa o HQL
			cliente = (Cliente) session.createQuery(consulta).setInteger(
					"idCliente", idCliente).uniqueResult();

			/*
			 * if (cliente != null){ // Carrega o relacionamento entre cliente e
			 * tipo Hibernate.initialize(cliente.getClienteTipo()); }
			 */

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// Retorna o cliente pesquisado
		return cliente;
	}

	/**
	 * Pesquisa um cliente pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Cliente
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoClienteRelatorio(Integer idCliente)
			throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada

		Object[] retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select c.clie_id as id, "
					+ "c.clie_nmcliente as nome " + "from cadastro.cliente c "
					+ "where c.clie_id = " + idCliente.toString();

			// pesquisa a coleção de acordo com o parâmetro informado
			Collection colecaoClientes = session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER).addScalar("nome",
							Hibernate.STRING).list();

			retorno = Util.retonarObjetoDeColecaoArray(colecaoClientes);

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção pesquisada
		return retorno;
	}

	/**
	 * Pesquisa as quantidades de imóveis e as quantidades de economias
	 * associadas a um cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 23/08/2007
	 * 
	 * @return Object[]
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarQtdeImoveisEEconomiasCliente(Integer idCliente)
			throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada

		Object[] retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "SELECT count(imov.imov_id) as qtdeImoveis, " 
					+ " sum(imov.imov_qteconomia) as qtdeEconomias "
					+ " FROM cadastro.imovel imov "
					+ " INNER JOIN cadastro.cliente_imovel clieImov "
					+ " on imov.imov_id = clieImov.imov_id "
					+ " where clieImov.clie_id = " + idCliente.toString();

			// pesquisa a coleção de acordo com o parâmetro informado
			Collection colecaoClientes = session.createSQLQuery(consulta)
					.addScalar("qtdeImoveis", Hibernate.INTEGER).addScalar(
							"qtdeEconomias", Hibernate.INTEGER).list();

			retorno = Util.retonarObjetoDeColecaoArray(colecaoClientes);

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção pesquisada
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public Collection<ClienteFone> pesquisarClienteFone(Integer idCliente)
			throws ErroRepositorioException {

		Collection<ClienteFone> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "from ClienteFone cfon " 
				    + "left join fetch cfon.cliente clie "
					+ "left join fetch cfon.foneTipo fnet "
					+ "where clie.id = :idCliente ";

			retorno = (Collection<ClienteFone>)session.createQuery(consulta).setInteger("idCliente",	idCliente.intValue()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}
	
	@SuppressWarnings("unchecked")
	public Collection<ClienteFone> pesquisarClienteFoneDoImovel(Integer idImovel) throws ErroRepositorioException {

		Collection<ClienteFone> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select cfon "
					+ "from ClienteFone cfon, " 
					+ " ClienteImovel clienteImovel "
					+ "left join fetch cfon.foneTipo fnet "
				    + "where clienteImovel.cliente.id = cfon.cliente.id "
					+ "and clienteImovel.imovel.id = :idImovel ";

			retorno = (Collection<ClienteFone>)session.createQuery(consulta).setInteger("idImovel",	idImovel.intValue()).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public Collection consultarCliente(Integer idCliente)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT cliente.nome," + // 0
					" cliente.nomeAbreviado, " + // 1
					" cliente.dataVencimento, " + // 2
					" clienteTipo.descricao," + // 3
					" clienteTipo.indicadorPessoaFisicaJuridica," + // 4
					" cliente.email, " + // 5
					" cliente.indicadorAcaoCobranca, " + // 6
					" cliente.cpf, " + // 7
					" cliente.rg, " + // 8
					" cliente.dataEmissaoRg, " + // 9
					" orgaoExpedidorRg.descricaoAbreviada, " + // 10
					" unidadeFederacao.sigla, " + // 11
					" cliente.dataNascimento, " + // 12
					" profissao.descricao, " + // 13
					" pessoaSexo.descricao, " + // 14
					" cliente.cnpj, " + // 15
					" ramoAtividade.descricao, " + // 16
					" clienteResponsavel.id, " + // 17
					" clienteResponsavel.nome, " +  // 18
					" clienteTipo.id " + //19
					"from Cliente cliente "
					+ "left join cliente.clienteTipo clienteTipo "
					+ "left join cliente.orgaoExpedidorRg orgaoExpedidorRg "
					+ "left join cliente.unidadeFederacao unidadeFederacao "
					+ "left join cliente.profissao profissao "
					+ "left join cliente.pessoaSexo pessoaSexo "
					+ "left join cliente.ramoAtividade ramoAtividade "
					+ "left join cliente.cliente clienteResponsavel "
					+ "where cliente.id = :idCliente ";

			retorno = session.createQuery(consulta).setInteger("idCliente",
					idCliente.intValue()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Pesquisa todos os endereços do cliente
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEnderecosCliente(Integer idCliente)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT enderecoTipo.descricao," + // 0
					" clienteEndereco.indicadorEnderecoCorrespondencia " + // 1
					"from ClienteEndereco clienteEndereco "
					+ "left join clienteEndereco.enderecoTipo enderecoTipo "
					+ "where clienteEndereco.cliente.id = :idCliente ";

			retorno = session.createQuery(consulta).setInteger("idCliente",
					idCliente.intValue()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0458] - Imprimir Ordem de Serviço
	 * 
	 * Pesquisa o telefone principal do Cliente para o Relatório de OS
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarClienteFonePrincipal(Integer idCliente)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT cf.ddd, cf.telefone, cf.ramal "
					+ "FROM ClienteFone cf " + "INNER JOIN cf.cliente cli "
					+ "WHERE cli.id = :idCliente AND "
					+ "cf.indicadorTelefonePadrao = "
					+ ClienteFone.INDICADOR_FONE_PADRAO;

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idCliente", idCliente).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Pesquisa os dados do cliente para a emissão do boletim
	 * 
	 * @author Rafael Corrêa
	 * @date 16/05/2007
	 * 
	 * @param idImovel,
	 *            clienteRelacaoTipo
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarClienteEmitirBoletimCadastro(Integer idImovel,
			Short clienteRelacaoTipo) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT clie.clie_id as id, clie.clie_nmcliente as nome, clie.cltp_id as tipo, " // 0,
					// 1, 2
					+ " clie.clie_nncpf as cpf, clie.clie_nncnpj as cnpj, clie.clie_nnrg as rg, " // 3,
					// 4, 5
					+ " clie.clie_dtrgemissao as dataEmissaoRg, oerg.oerg_dsabreviado as orgaoExpedidorRg, " // 6, 7
					+ " unfe.unfe_dsufsigla as uf, clie.clie_dtnascimento as dataNascimento, " // 8, 9
					+ " profissao.prof_dsprofissao as profissao, clie.psex_id as sexo, clie.clie_nnmae as nomeMae, " // 10,
					// 11,
					// 12
					+ " clie.clie_icuso as icUso, ce.edtp_id as tipoEndereco, ce.logr_id as idLogradouro, " // 13,
					// 14,
					// 15
					+ " cep.cep_cdcep as cep, bairro.bair_cdbairro as bairro, ce.edrf_id as idEnderecoReferencia, " // 16,
					// 17,
					// 18
					+ " ce.cled_nnimovel as numeroImovel, ce.cled_dscomplementoendereco as complemento, " // 19,
					// 20
					+ " cf.fnet_id as idFoneTipo, cf.cfon_cdddd as ddd, cf.cfon_nnfone as numeroTelefone, " // 21,
					// 22,
					// 23
					+ " cf.cfon_nnfoneramal as ramal, " // 24
					+ " bairro.muni_id as municipio " // 25
					+ " FROM cadastro.cliente_imovel ci "
					+ " INNER JOIN cadastro.cliente clie "
					+ " on clie.clie_id = ci.clie_id "
					+ " LEFT OUTER JOIN cadastro.profissao profissao "
					+ " on profissao.prof_id = clie.prof_id "
					+ " LEFT OUTER JOIN cadastro.orgao_expedidor_rg oerg "
					+ " on oerg.oerg_id = clie.oerg_id "
					+ " LEFT OUTER JOIN cadastro.unidade_federacao unfe "
					+ " on unfe.unfe_id = clie.unfe_id "
					+ " LEFT OUTER JOIN cadastro.cliente_endereco ce "
					+ " on ce.clie_id = clie.clie_id and ce.cled_icenderecocorrespondencia = "
					+ ClienteEndereco.INDICADOR_ENDERECO_CORRESPONDENCIA
					+ " LEFT OUTER JOIN cadastro.cep cep "
					+ " on cep.cep_id = ce.cep_id "
					+ " LEFT OUTER JOIN cadastro.bairro bairro "
					+ " on bairro.bair_id = ce.bair_id "
					+ " LEFT OUTER JOIN cadastro.cliente_fone cf "
					+ " on cf.clie_id = clie.clie_id "
					+ " WHERE ci.imov_id = :idImovel and ci.crtp_id = :clienteRelacaoTipo"
					+ " and clim_dtrelacaofim is null "
					+ "ORDER BY cf.cfon_icfonepadrao ";

			retorno = (Collection) session.createSQLQuery(consulta).addScalar(
					"id", Hibernate.INTEGER)
					.addScalar("nome", Hibernate.STRING).addScalar("tipo",
							Hibernate.INTEGER).addScalar("cpf",
							Hibernate.STRING).addScalar("cnpj",
							Hibernate.STRING).addScalar("rg", Hibernate.STRING)
					.addScalar("dataEmissaoRg", Hibernate.DATE).addScalar(
							"orgaoExpedidorRg", Hibernate.STRING).addScalar(
							"uf", Hibernate.STRING).addScalar("dataNascimento",
							Hibernate.DATE).addScalar("profissao",
							Hibernate.STRING).addScalar("sexo",
							Hibernate.INTEGER).addScalar("nomeMae",
							Hibernate.STRING).addScalar("icUso",
							Hibernate.SHORT).addScalar("tipoEndereco",
							Hibernate.INTEGER).addScalar("idLogradouro",
							Hibernate.INTEGER).addScalar("cep",
							Hibernate.INTEGER).addScalar("bairro",
							Hibernate.INTEGER).addScalar(
							"idEnderecoReferencia", Hibernate.INTEGER)
					.addScalar("numeroImovel", Hibernate.STRING).addScalar(
							"complemento", Hibernate.STRING).addScalar(
							"idFoneTipo", Hibernate.INTEGER).addScalar("ddd",
							Hibernate.STRING).addScalar("numeroTelefone",
							Hibernate.STRING).addScalar("ramal",
							Hibernate.STRING).addScalar("municipio",
							Hibernate.INTEGER).setInteger("idImovel", idImovel)
					.setShort("clienteRelacaoTipo", clienteRelacaoTipo)
					.setMaxResults(2).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Usado pelo Filtrar Cliente Filtra o Cliente usando os paramentos
	 * informados
	 * 
	 * @author Rafael Santos
	 * @date 27/11/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarCliente(String codigo, String cpf, String rg,
			String cnpj, String nome, String nomeMae, String cep,
			String idMunicipio, String idBairro, String idLogradouro,
			String indicadorUso, String tipoPesquisa,
			String tipoPesquisaNomeMae, String clienteTipo, String idEsferaPoder, Integer numeroPagina)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select distinct cliente.id," + // 0
					" cliente.nome," + // 1
					" cliente.rg," + // 2
					" cliente.cpf," + // 3
					" cliente.cnpj, " + // 4
					" clienteTipo.indicadorPessoaFisicaJuridica, " + // 5
					" orgaoExpedidorRg.descricao, " + // 6
					" unidadeFederacao.sigla, " + // 7
					" clienteTipo.descricao, " + // 8
					" cliente.indicadorUso " // 9

					+ "from Cliente cliente "
					+ "left join cliente.clienteTipo clienteTipo "
					+ "left join cliente.orgaoExpedidorRg orgaoExpedidorRg "
					+ "left join cliente.unidadeFederacao unidadeFederacao ";

			/*
			 * ## JOIN ##
			 */
			// join necessários
			// join facultativos
			
			// esfera poder
			if ((idEsferaPoder != null && !idEsferaPoder.equals("") && !idEsferaPoder
					.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				consulta = consulta
						+ " left join clienteTipo.esferaPoder esferaPoder ";
			}
			
			// cep
			if (cep != null
					&& !cep.equals("")
					&& !cep.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta
						+ " left join cliente.clienteEnderecos clienteEnderecos "
						+ " left join clienteEnderecos.logradouroCep logradouroCep "
						+ "left join logradouroCep.cep cep ";
			}
			// bairro
			if (idBairro != null
					&& !idBairro.equals("")
					&& !idBairro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta
						+ " left join cliente.clienteEnderecos clienteEnderecos "
						+ " left join clienteEnderecos.logradouroBairro logradouroBairro "
						+ " left join logradouroBairro.bairro bairro ";
			}

			// logradouro
			if (idLogradouro != null
					&& !idLogradouro.equals("")
					&& !idLogradouro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta
						+ " left join cliente.clienteEnderecos clienteEnderecos "
						+ " left join clienteEnderecos.logradouroCep logradouroCep "
						+ " left join logradouroCep.logradouro logradouro ";
			}

			// municipio
			if (idMunicipio != null
					&& !idMunicipio.equals("")
					&& !idMunicipio.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta
						+ " left join cliente.clienteEnderecos clienteEnderecos "
						+ " left join clienteEnderecos.logradouroBairro logradouroBairro "
						+ " left join logradouroBairro.bairro bairro "
						+ "left join bairro.municipio municipio ";
			}

			/*
			 * ## CONDIÇÕES ##
			 */
			consulta = consulta + " where   ";

			// codigo
			if (codigo != null
					&& !codigo.equals("")
					&& !codigo.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " cliente.id = :codigo  and  ";
			}
			// cpf
			if ((cpf != null && !cpf.equals("") && !cpf.trim()
					.equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				consulta = consulta + " cliente.cpf = :cpf  and  ";
			}
			// rg
			if ((rg != null && !rg.equals("") && !rg.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
							.toString()))) {
				consulta = consulta + " cliente.rg = :rg  and  ";
			}
			// cnpj
			if ((cnpj != null && !cnpj.equals("") && !cnpj.trim()
					.equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				consulta = consulta + " cliente.cnpj = :cnpj and  ";
			}
			// nome
			if (nome != null && !nome.equals("")) {
				if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					consulta = consulta + " upper(cliente.nome) like '%"
							+ nome.toUpperCase() + "%'  and  ";
				} else {
					consulta = consulta + " upper(cliente.nome) like '"
							+ nome.toUpperCase() + "%'  and  ";
				}
			}

			// nomeMae
			if (nomeMae != null && !nomeMae.equals("")) {
				if (tipoPesquisaNomeMae != null
						&& tipoPesquisaNomeMae
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					consulta = consulta + " upper(cliente.nomeMae) like '%"
							+ nomeMae.toUpperCase() + "%'  and  ";
				} else {
					consulta = consulta + " upper(cliente.nomeMae) like '"
							+ nomeMae.toUpperCase() + "%'  and  ";
				}
			}

			// municipio
			if (idMunicipio != null
					&& !idMunicipio.equals("")
					&& !idMunicipio.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " municipio.id = :idMunicipio  and  ";
			}
			
			// esfera poder
			if (idEsferaPoder != null
					&& !idEsferaPoder.equals("")
					&& !idEsferaPoder.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " esferaPoder.id = :idEsferaPoder and  ";
			}

			// cep
			if (cep != null
					&& !cep.equals("")
					&& !cep.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " cep.codigo = :cep  and  ";
			}

			// bairro
			if (idBairro != null
					&& !idBairro.equals("")
					&& !idBairro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " bairro.codigo = :idBairro  and  ";
			}

			// logradouro
			if (idLogradouro != null
					&& !idLogradouro.equals("")
					&& !idLogradouro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " logradouro.id = :idLogradouro  and  ";
			}

			// indicador de uso
			if ((indicadorUso != null && !indicadorUso.equals("") && !indicadorUso
					.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				consulta = consulta
						+ " cliente.indicadorUso = :indicadorUso  and  ";
			}

			// cliente Tipo
			if ((clienteTipo != null && !clienteTipo.equals("") && !clienteTipo
					.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				consulta = consulta + " clienteTipo.id = :clienteTipo  and  ";
			}

			consulta = consulta.substring(0, (consulta.length() - 5));

			consulta = consulta + " order by cliente.nome ";

			Query query = session.createQuery(consulta);

			// seta os valores na condição where

			// codigo
			if (codigo != null
					&& !codigo.equals("")
					&& !codigo.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("codigo", new Integer(codigo).intValue());
			}
			// cpf
			if ((cpf != null && !cpf.equals("") && !cpf.trim()
					.equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				query.setString("cpf", cpf);
			}
			// rg
			if ((rg != null && !rg.equals("") && !rg.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
							.toString()))) {
				query.setString("rg", rg);
			}
			// cnpj
			if ((cnpj != null && !cnpj.equals("") && !cnpj.trim()
					.equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				query.setString("cnpj", cnpj);
			}
			// nome
			// if (nome != null && !nome.equals("")) {
			// query.setString("nome",nome);
			// }

			// / nomeMae
			// if (nomeMae != null
			// && !nomeMae.equals("")) {
			// query.setString("nomeMae",nomeMae);
			// }

			// municipio
			if (idMunicipio != null
					&& !idMunicipio.equals("")
					&& !idMunicipio.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("idMunicipio", new Integer(idMunicipio)
						.intValue());
			}
			
			// esfera poder
			if (idEsferaPoder != null
					&& !idEsferaPoder.equals("")
					&& !idEsferaPoder.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("idEsferaPoder", new Integer(idEsferaPoder)
						.intValue());
			}

			// cep
			if (cep != null
					&& !cep.equals("")
					&& !cep.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("cep", new Integer(cep).intValue());
			}

			// bairro
			if (idBairro != null
					&& !idBairro.equals("")
					&& !idBairro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("idBairro", new Integer(idBairro).intValue());
			}

			// logradouro
			if (idLogradouro != null
					&& !idLogradouro.equals("")
					&& !idLogradouro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("idLogradouro", new Integer(idLogradouro)
						.intValue());
			}

			// indicador de uso
			if ((indicadorUso != null && !indicadorUso.equals("") && !indicadorUso
					.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				query.setShort("indicadorUso", new Short(indicadorUso)
						.shortValue());
			}

			// cliente Tipo
			if ((clienteTipo != null && !clienteTipo.equals("") && !clienteTipo
					.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				query.setInteger("clienteTipo", new Integer(clienteTipo)
						.shortValue());
			}

			retorno = query.setFirstResult(10 * numeroPagina).setMaxResults(10)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Usado pelo Filtrar Cliente Filtra a quantidade de Clientes usando os
	 * paramentos informados
	 * 
	 * @author Rafael Santos
	 * @date 27/11/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object filtrarQuantidadeCliente(String codigo, String cpf,
			String rg, String cnpj, String nome, String nomeMae, String cep,
			String idMunicipio, String idBairro, String idLogradouro,
			String indicadorUso, String tipoPesquisa,

			String tipoPesquisaNomeMae, String clienteTipo, String idEsferaPoder)
			throws ErroRepositorioException {

		Object retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select count(distinct cliente.id) "
					+ "from Cliente cliente "
					+ " left join cliente.clienteTipo clienteTipo ";

			/*
			 * ## JOIN ##
			 */
			// join necessários
			// join facultativos
			
			// esfera poder
			if ((idEsferaPoder != null && !idEsferaPoder.equals("") && !idEsferaPoder
					.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				consulta = consulta
						+ " left join clienteTipo.esferaPoder esferaPoder ";
			}
			
			// cep
			if (cep != null
					&& !cep.equals("")
					&& !cep.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta
						+ " left join cliente.clienteEnderecos clienteEnderecos "
						+ " left join clienteEnderecos.logradouroCep logradouroCep "
						+ "left join logradouroCep.cep cep ";
			}
			// bairro
			if (idBairro != null
					&& !idBairro.equals("")
					&& !idBairro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta
						+ " left join cliente.clienteEnderecos clienteEnderecos "
						+ " left join clienteEnderecos.logradouroBairro logradouroBairro "
						+ " left join logradouroBairro.bairro bairro ";
			}

			// logradouro
			if (idLogradouro != null
					&& !idLogradouro.equals("")
					&& !idLogradouro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta
						+ " left join cliente.clienteEnderecos clienteEnderecos "
						+ " left join clienteEnderecos.logradouroCep logradouroCep "
						+ " left join logradouroCep.logradouro logradouro ";
			}

			// municipio
			if (idMunicipio != null
					&& !idMunicipio.equals("")
					&& !idMunicipio.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta
						+ " left join cliente.clienteEnderecos clienteEnderecos "
						+ " left join clienteEnderecos.logradouroBairro logradouroBairro "
						+ " left join logradouroBairro.bairro bairro "
						+ "left join bairro.municipio municipio ";
			}

			/*
			 * ## CONDIÇÕES ##
			 */
			consulta = consulta + " where   ";

			// codigo
			if (codigo != null
					&& !codigo.equals("")
					&& !codigo.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " cliente.id = :codigo  and  ";
			}
			// cpf
			if ((cpf != null && !cpf.equals("") && !cpf.trim()
					.equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				consulta = consulta + " cliente.cpf = :cpf  and  ";
			}
			// rg
			if ((rg != null && !rg.equals("") && !rg.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
							.toString()))) {
				consulta = consulta + " cliente.rg = :rg  and  ";
			}
			// cnpj
			if ((cnpj != null && !cnpj.equals("") && !cnpj.trim()
					.equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				consulta = consulta + " cliente.cnpj = :cnpj and  ";
			}
			// nome
			if (nome != null && !nome.equals("")) {
				if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					consulta = consulta + " upper(cliente.nome) like '%"
							+ nome.toUpperCase() + "%'  and  ";
				} else {
					consulta = consulta + " upper(cliente.nome) like '"
							+ nome.toUpperCase() + "%'  and  ";
				}
			}

			// nomeMae
			if (nomeMae != null && !nomeMae.equals("")) {
				if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					consulta = consulta + " upper(cliente.nomeMae) like '%"
							+ nomeMae.toUpperCase() + "%'  and  ";
				} else {
					consulta = consulta + " upper(cliente.nomeMae) like '"
							+ nomeMae.toUpperCase() + "%'  and  ";
				}
			}

			// municipio
			if (idMunicipio != null
					&& !idMunicipio.equals("")
					&& !idMunicipio.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " municipio.id = :idMunicipio  and  ";
			}
			
			// esfera poder
			if (idEsferaPoder != null
					&& !idEsferaPoder.equals("")
					&& !idEsferaPoder.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " esferaPoder.id = :idEsferaPoder  and  ";
			}

			// cep
			if (cep != null
					&& !cep.equals("")
					&& !cep.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " cep.codigo = :cep  and  ";
			}

			// bairro
			if (idBairro != null
					&& !idBairro.equals("")
					&& !idBairro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " bairro.codigo = :idBairro  and  ";
			}

			// logradouro
			if (idLogradouro != null
					&& !idLogradouro.equals("")
					&& !idLogradouro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				consulta = consulta + " logradouro.id = :idLogradouro  and  ";
			}

			// indicador de uso
			if ((indicadorUso != null && !indicadorUso.equals("") && !indicadorUso
					.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				consulta = consulta
						+ " cliente.indicadorUso = :indicadorUso  and  ";
			}

			// cliente Tipo
			if ((clienteTipo != null && !clienteTipo.equals("") && !clienteTipo
					.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				consulta = consulta + " clienteTipo.id = :clienteTipo  and  ";
			}

			Query query = session.createQuery(consulta.substring(0, (consulta
					.length() - 5)));

			// seta os valores na condição where

			// codigo
			if (codigo != null
					&& !codigo.equals("")
					&& !codigo.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("codigo", new Integer(codigo).intValue());
			}
			// cpf
			if ((cpf != null && !cpf.equals("") && !cpf.trim()
					.equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				query.setString("cpf", cpf);
			}
			// rg
			if ((rg != null && !rg.equals("") && !rg.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
							.toString()))) {
				query.setString("rg", rg);
			}
			// cnpj
			if ((cnpj != null && !cnpj.equals("") && !cnpj.trim()
					.equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				query.setString("cnpj", cnpj);
			}
			// nome
			// if (nome != null && !nome.equals("")) {
			// query.setString("nome",nome);
			// }

			// / nomeMae
			// if (nomeMae != null
			// && !nomeMae.equals("")) {
			// query.setString("nomeMae",nomeMae);
			// }

			// municipio
			if (idMunicipio != null
					&& !idMunicipio.equals("")
					&& !idMunicipio.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("idMunicipio", new Integer(idMunicipio)
						.intValue());
			}
			
			// esfera poder
			if (idEsferaPoder != null
					&& !idEsferaPoder.equals("")
					&& !idEsferaPoder.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("idEsferaPoder", new Integer(idEsferaPoder)
						.intValue());
			}

			// cep
			if (cep != null
					&& !cep.equals("")
					&& !cep.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("cep", new Integer(cep).intValue());
			}

			// bairro
			if (idBairro != null
					&& !idBairro.equals("")
					&& !idBairro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("idBairro", new Integer(idBairro).intValue());
			}

			// logradouro
			if (idLogradouro != null
					&& !idLogradouro.equals("")
					&& !idLogradouro.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				query.setInteger("idLogradouro", new Integer(idLogradouro)
						.intValue());
			}

			// indicador de uso
			if ((indicadorUso != null && !indicadorUso.equals("") && !indicadorUso
					.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				query.setShort("indicadorUso", new Short(indicadorUso)
						.shortValue());
			}
			// cliente Tipo
			if ((clienteTipo != null && !clienteTipo.equals("") && !clienteTipo
					.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString()))) {
				query.setInteger("clienteTipo", new Integer(clienteTipo)
						.shortValue());
			}

			retorno = query.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0864] Gerar Certidão Negativa por Cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 25/09/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarClientesAssociadosResponsavel(Integer idCliente)
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		Collection<Integer> retorno = null;
		
		String consulta = null;
		
		try {

			consulta = "SELECT DISTINCT cliente.id "
					+ "FROM Cliente cliente "
					+ "INNER JOIN cliente.cliente clienteResponsavel "
					+ "WHERE clienteResponsavel.id = :idCliente and cliente.id <> :idCliente ";

			retorno = session.createQuery(consulta).setInteger("idCliente",
					idCliente).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 27/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterIdENomeCliente(String cpf)
			throws ErroRepositorioException {

		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select distinct cliente.id," // 0
					+ " cliente.nome " // 1
					+ "from Cliente cliente " + "where cliente.cpf = :cpf ";

			retorno = (Object[]) session.createQuery(consulta).setString("cpf",
					cpf).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarCPFCliente(String cpf, Integer idCliente)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String update = null;
		try {

			update = "update gcom.cadastro.cliente.Cliente "
					+ "set clie_nncpf = :cpf,clie_tmultimaalteracao = :ultimaAlteracao "
					+ "where clie_id = :idCliente";

			session.createQuery(update).setString("cpf", cpf).setInteger(
					"idCliente", idCliente).setTimestamp("ultimaAlteracao",
					new Date()).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosCliente(Integer idImovel, 
			Short idClienteRelacaoTipo) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta;
		Object[] retornoConsulta = null;

		try {

			consulta = " select cliente.clie_id as idCliente, " + //0
			   		   " clie_nmcliente as nomeCliente, " + //1
					   " cltp.cltp_id as idClienteTipo, " + //2
					   " clie_nncpf as cpf, " + //3
					   " clie_nncnpj as cnpj, " + //4
					   " clie_nnrg as rg, " + //5
					   " clie_dtrgemissao as dtRgEmissao, " + //6
					   " oerg_dsabreviado as orgaoExp, " + //7
					   " unfe_dsufsigla as unidFederativa, " + //8
					   " clie_dtnascimento as dtNascimento, " + //9
					   " prof_id as idProfissao, " + //10cltp_id
					   " psex_id as idSexo, " + //11
					   " clie_nnmae as nomeMae, " + //12
					   " clie_icuso as indUso, " + //13
					   " clie_dsemail as email, " + //14
					   " edtp_id as idEnderecoTipo, " + //15
					   " lgcp.logr_id as cepLogradouro, " + //16
					   " lgbr.logr_id as bairroLogradouro, "+ //17
					   " cep.cep_cdcep as codigoCep,"+ //18
					   " bairro.bair_id as idBairro,"+ //19
					   " bairro.bair_nmbairro as descricaoBairro,"+ //20
					   " edrf_id as idEnderecoReferencia, " + //21
					   " cled_nnimovel as numero, " + //22    
					   " cled_dscomplementoendereco as complemento, " + //23
					   " ratv_id as idRamoAtividade, " + //24
					   " cltp.cltp_icpessoafisicajuridica as indicadorPessoaFisicaJuridica" + //25
					   " from cadastro.cliente_imovel clienteImovel" +
					   " inner join cadastro.cliente cliente on cliente.clie_id = clienteImovel.clie_id" +
					   " inner join cadastro.cliente_tipo cltp on cliente.cltp_id = cltp.cltp_id" +
					   " left join cadastro.cliente_endereco clienteEndereco on(clienteEndereco.clie_id = cliente.clie_id)"+
					   " left join cadastro.logradouro_cep lgcp on(clienteEndereco.lgcp_id = lgcp.lgcp_id)"+
					   " left join cadastro.cep cep on (lgcp.cep_id = cep.cep_id)" +
					   " left join cadastro.logradouro_bairro lgbr on(clienteEndereco.lgbr_id = lgbr.lgbr_id)"+
					   " left join cadastro.bairro bairro on (lgbr.bair_id = bairro.bair_id)" + 
					   " left join cadastro.orgao_expedidor_rg oerg on(cliente.oerg_id = oerg.oerg_id)"+
					   " left join cadastro.unidade_federacao unfe on(cliente.unfe_id = unfe.unfe_id)"+
					   " where clienteImovel.imov_id = :idImovel " +
					   " and clienteImovel.crtp_id = :idClienteRelacaoTipo " +
					   " and clienteImovel.clim_dtrelacaofim is null" +
					   " and cled_icenderecocorrespondencia = 1"+
					   " order by clie_cdclienteresponsavel desc";
			
				
			retornoConsulta = (Object[])session.createSQLQuery(consulta)
					.addScalar("idCliente", Hibernate.INTEGER)
					.addScalar("nomeCliente", Hibernate.STRING)
					.addScalar("idClienteTipo", Hibernate.INTEGER)									
					.addScalar("cpf", Hibernate.STRING)					
					.addScalar("cnpj", Hibernate.STRING)										
					.addScalar("rg", Hibernate.STRING)					
					.addScalar("dtRgEmissao", Hibernate.DATE)
					.addScalar("orgaoExp", Hibernate.STRING)
					.addScalar("unidFederativa", Hibernate.STRING)
					.addScalar("dtNascimento", Hibernate.DATE)					
					.addScalar("idProfissao", Hibernate.INTEGER)
					.addScalar("idSexo", Hibernate.INTEGER)
					.addScalar("nomeMae", Hibernate.STRING)
					.addScalar("indUso", Hibernate.SHORT)
					.addScalar("email", Hibernate.STRING)
					.addScalar("idEnderecoTipo", Hibernate.INTEGER)
					.addScalar("cepLogradouro", Hibernate.INTEGER)
					.addScalar("bairroLogradouro", Hibernate.INTEGER)
					.addScalar("codigoCep", Hibernate.INTEGER)
					.addScalar("idBairro", Hibernate.INTEGER)
					.addScalar("descricaoBairro", Hibernate.STRING)	
					.addScalar("idEnderecoReferencia", Hibernate.INTEGER)
					.addScalar("numero", Hibernate.STRING)
					.addScalar("complemento", Hibernate.STRING)	
					.addScalar("idRamoAtividade", Hibernate.INTEGER)
					.addScalar("indicadorPessoaFisicaJuridica", Hibernate.SHORT)
					.setInteger("idImovel", idImovel)
					.setInteger("idClienteRelacaoTipo", idClienteRelacaoTipo)
					.setMaxResults(1).uniqueResult();



			
		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retornoConsulta;

		}
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 01/09/2008
	 * 
	 * @return Cliente Fone
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosClienteFone(Integer idCliente) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection clienteFones = new ArrayList();
		Collection retornoConsulta = null;

		try {

			consulta = " select clie_id as idCliente, " + //0
					   " cfon_id as idClienteFone, " + //1
					   " cfon_cdddd as ddd, " + //2
					   " cfon_nnfone as telefone, " + //3
					   " cfon_nnfoneramal as ramal, " + //4
					   " fnet_id as foneTipo, " + //5
					   " cfon_icfonepadrao as icFonePadrao " + //6
					   " from cadastro.cliente_fone" +
					   " where clie_id = :idCliente" ;
	
	
			retornoConsulta = session.createSQLQuery(consulta)
						.addScalar("idCliente", Hibernate.INTEGER)
						.addScalar("idClienteFone", Hibernate.INTEGER)
						.addScalar("ddd", Hibernate.STRING)
						.addScalar("telefone", Hibernate.STRING)
						.addScalar("ramal", Hibernate.STRING)
						.addScalar("foneTipo", Hibernate.INTEGER)
						.addScalar("icFonePadrao",Hibernate.SHORT)
						.setInteger("idCliente",idCliente)
						.list();
	
	
			if (retornoConsulta.size() > 0) {
				Iterator clienteFoneIter = retornoConsulta.iterator();
				while (clienteFoneIter.hasNext()) {
		
					Object[] element = (Object[]) clienteFoneIter.next();
			
					ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();
			
					clienteFone.setId((Integer) element[1]);
			
					if (element[2] != null){
						clienteFone.setDdd((String) element[2]);
					}
			
					if (element[3] != null){
						clienteFone.setTelefone((String) element[3]);
					}
			
					if (element[4] != null){
						clienteFone.setRamal((String) element[4]);
					}
			
					if (element[5] != null){
						clienteFone.setIdFoneTipo((Integer) element[5]);
					}
					
					if (element[6] != null){
						clienteFone.setIndicadorFonePadrao((Short) element[6]);
					}
			
					clienteFones.add(clienteFone);
				}
			}


		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {

		HibernateUtil.closeSession(session);

		}

		return clienteFones;

	}

	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 26/09/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaClienteAtualizacaoCadastral(Integer idCliente) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Integer retornoConsulta = null;

		try {

			consulta = "select clie_id as id " + 
					   "from cadastro.cliente_atlz_cadastral " + 
					   "where clie_id = :idCliente  " ;
				
			retornoConsulta = (Integer)session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.setInteger("idCliente",idCliente)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);

		}

		return retornoConsulta;

	}
	
	/**
	 * Pesquisa a quantidade de clientes responsáveis superiores com os condicionais informados
	 * 
	 * @author Rafael Corrêa
	 * @date 18/11/08
	 */
	public Integer pesquisarClienteResponsavelSuperiorParaPaginacaoCount(PesquisarClienteResponsavelSuperiorHelper helper) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Integer retorno = null;
		
		try {

			consulta = "SELECT COUNT (DISTINCT clienteSuperior) " 
				+ " FROM Cliente cliente "
				+ " INNER JOIN cliente.cliente clienteSuperior "
				+ " LEFT JOIN clienteSuperior.clienteTipo clienteSuperiorTipo ";
			
			consulta = consulta + criarCondicionaisPesquisarClienteResponsavelSuperior(helper);
				
			retorno = (Integer) session.createQuery(consulta).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);

		}
		
		return retorno;
		
	}
	
	/**
	 * Pesquisa os clientes responsáveis superiores com os condicionais informados
	 * 
	 * @author Rafael Corrêa
	 * @date 18/11/08
	 */
	public Collection<Cliente> pesquisarClienteResponsavelSuperiorParaPaginacao(PesquisarClienteResponsavelSuperiorHelper helper, Integer numeroPagina) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Collection<Cliente> retorno = null;
		
		try {

			consulta = "SELECT DISTINCT clienteSuperior " 
				+ " FROM Cliente cliente "
				+ " INNER JOIN cliente.cliente clienteSuperior "
				+ " LEFT JOIN clienteSuperior.clienteTipo clienteSuperiorTipo ";
			
			consulta = consulta + criarCondicionaisPesquisarClienteResponsavelSuperior(helper);
			
			consulta = consulta + " ORDER BY clienteSuperior.nome ";
				
			retorno = session.createQuery(consulta).setFirstResult(10 * numeroPagina).setMaxResults(10).list();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);

		}
		
		return retorno;
		
	}
	
	/**
	 * Cria os condicionais da pesquisa dos clientes reponsáveis superiores
	 * 
	 * @author Rafael Corrêa
	 * @date 18/11/08
	 */
	public String criarCondicionaisPesquisarClienteResponsavelSuperior(PesquisarClienteResponsavelSuperiorHelper helper) {

		String retorno = " WHERE ";
		
		if (helper.getNome() != null && !helper.getNome().trim().equals("")) {
			if (helper.getTipoPesquisa() != null
					&& helper.getTipoPesquisa()
					.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA)) {
				retorno = retorno + " clienteSuperior.nome like '%" + helper.getNome().trim().toUpperCase() + "%' and ";
			} else {
				retorno = retorno + " clienteSuperior.nome like '" + helper.getNome().trim().toUpperCase() + "%' and ";
			}
		}

		if (helper.getCnpj() != null && !helper.getCnpj().trim().equals("")) {
			retorno = retorno + "clienteSuperior.cnpj = " + helper.getCnpj().trim() + " and "; 
		}
		
		if (helper.getIdEsferaPoder() != null) {
			retorno = retorno + "clienteSuperiorTipo.esferaPoder.id = " + helper.getIdEsferaPoder() + " and ";
		}
		
		if (helper.getIndicadorUso() != null) {
			retorno = retorno + "clienteSuperior.indicadorUso = " + helper.getIndicadorUso() + " and ";
		}

		// retira o " and " q fica sobrando no final da query
		retorno = Util.removerUltimosCaracteres(retorno, 4);
		
		return retorno;
	}
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @param idCliente, idImovel
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Ana Maria
     * @date 15/05/2009
	 * @exception ErroRepositorioException
	 */
	public IClienteAtualizacaoCadastral pesquisarClienteAtualizacaoCadastral(Integer idCliente, Integer idImovel, Integer idClienteRelacaoTipo)
		throws ErroRepositorioException {
	
		IClienteAtualizacaoCadastral clienteAtualizacaoCadastral = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT clie"
				     + " FROM ClienteAtualizacaoCadastral clie" 				    				    
				     + " WHERE clie.idImovel = :idImovel "; 
			
			if(idClienteRelacaoTipo != null){
				consulta = consulta + " and clie.idClienteRelacaoTipo = "+ idClienteRelacaoTipo;
			}
			
			if(idCliente != null){
				consulta = consulta + " and clie.idCliente = "+ idCliente;
			}
	
			clienteAtualizacaoCadastral = (IClienteAtualizacaoCadastral)session.createQuery(consulta)
										.setInteger("idImovel", idImovel)
										.setMaxResults(1).uniqueResult();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
	
		}
	
		return clienteAtualizacaoCadastral;
	
	}
	
	/**
	 * 
	 * Pesquisar Cliente Fone Atualização Cadastral
	 *
	 * @author Ana Maria
	 * @date 24/10/2008
	 *
	 * @param idCliente
	 * @throws ErroRepositorioException 
	 */
	public Collection<ClienteFoneAtualizacaoCadastral> pesquisarClienteFoneAtualizacaoCadastral(Integer idCliente, Integer idMatricula, 
			Integer idTipoFone, Integer idClienteRelacaoTipo,String numeroFone)
		throws ErroRepositorioException {
	
		Collection<ClienteFoneAtualizacaoCadastral> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			
			consulta = " SELECT clienteFone" 
				 + " FROM ClienteFoneAtualizacaoCadastral clienteFone" 
				 + " WHERE clienteFone.idClienteAtualizacaoCadastral in(SELECT clienteAtualizacaoCadastral.id" 
				 + "												    FROM ClienteAtualizacaoCadastral clienteAtualizacaoCadastral" 
				 + "												    WHERE clienteAtualizacaoCadastral.idCliente =:idCliente ";
		
				 if(idClienteRelacaoTipo != null){
					 consulta = consulta + " AND clienteAtualizacaoCadastral.idClienteRelacaoTipo = "+ idClienteRelacaoTipo;
				 }
				 
		consulta = consulta + " AND clienteAtualizacaoCadastral.idImovel =:idMatricula) ";

		if(idTipoFone != null){
			consulta = consulta + " AND clienteFone.idFoneTipo = "+ idTipoFone;
		}
		
		if(numeroFone != null){
			 consulta = consulta + " AND clienteFone.telefone = "+ numeroFone;
		 }

		retorno = session.createQuery(consulta).setInteger("idCliente", idCliente.intValue())
				.setInteger("idMatricula", idMatricula).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	
	}
	
	
	/**
	 * 
	 * Atualiza telefone padrão
	 *
	 * @author Daniel Alves
	 * @date 06/09/2010
	 *
	 * @param idCliente
	 * @param idClienteFonePadrao  (novo telefone padrão do cliente).
	 * @throws ErroRepositorioException 
	 */
	public void atualizarTelefonePadrao(String idCliente, String idClienteFonePadrao)
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String consulta2 = null;
		try {
			//coloca todos os clienteFone com o indicadorTelefonePadrao = 2
			consulta = " UPDATE clienteFone" 
					 + " SET indicadorTelefonePadrao = 2" 
					 + " AND ultimaAlteracao = " + Util.obterSQLDataAtual() 
					 + " WHERE cliente.id = :idCliente ";
			
		    session.createQuery(consulta).setString("idCliente", idCliente).executeUpdate();
			
		  //coloca no clienteFone a ser atualizado o indicadorTelefonePadrao = 1
			consulta2 = " UPDATE clienteFone" 
			          + " SET indicadorTelefonePadrao = 1" 
			          + " WHERE cliente.id = :idCliente" 
			          + " AND id = :idClienteFonePadrao"; 	 
			
			session.createQuery(consulta2).setString("idCliente", idCliente).setString("idClienteFonePadrao", idClienteFonePadrao).executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0002]–Verifica Critério Recadastramento
	 * 
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClienteUsuarioDoImovel(Integer idImovel)
			throws ErroRepositorioException {

		// Cria uma sessão com ohibernate
		Session session = HibernateUtil.getSession();

		// Cria a variável que vai armazenar o cliente responsável
		Cliente cliente = new Cliente();
		String consulta = null;

		try {
			// Monta o HQL
			consulta = " select cliente from ClienteImovel clim " 
					+ " INNER JOIN clim.cliente cliente "
					+ " INNER JOIN clim.clienteRelacaoTipo crtp "
					+ " where clim.imovel.id = :idImovel " 
					+ " and crtp.id = :usuario " 
					+ " and clim.dataFimRelacao is null ";

			// Executa o HQL
			cliente = (Cliente) session.createQuery(consulta)
			.setInteger("idImovel", idImovel)
			.setInteger("usuario",ClienteRelacaoTipo.USUARIO)
			.uniqueResult();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// Retorna o cliente pesquisado
		return cliente;
	}
	
	/**
	 * 
	 * Filtra os Clientes por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ErroRepositorioException 
	 */
	public Collection filtrarAutocompleteCliente(String valor)throws ErroRepositorioException{
		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			consulta = " SELECT clie_id as id, clie_nmcliente as nome , clie_nncnpj as cnpj, cltp.cltp_icpessoafisicajuridica as ic from cadastro.cliente clie " +
					"inner join cadastro.cliente_tipo cltp on cltp.cltp_id = clie.cltp_id where ";
			
			valor = valor.trim();
			if(valor.contains("-")){
				valor = valor.split(" - ")[0].trim();
			}
			
			if(Util.validarStringNumerica(valor)){
				
				consulta += "clie_id like '%" + valor + "%'";
				consulta += " order by 1,2 ";
				retorno = session.createSQLQuery(consulta)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("cnpj", Hibernate.STRING)
				.addScalar("nome",Hibernate.STRING)
				.addScalar("ic", Hibernate.INTEGER).setMaxResults(200).list();
				
			}else{
				if(valor.length() > 3){
					consulta += "clie_nmcliente like '%" + valor.trim().toUpperCase() + "%'";
					consulta += " order by 1,2 ";
					retorno = session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("cnpj", Hibernate.STRING)
					.addScalar("nome",Hibernate.STRING)
					.addScalar("ic", Hibernate.INTEGER).setMaxResults(200).list();
				}
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * 
	 * Filtra os Clientes Responsavel por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ErroRepositorioException 
	 */
	public Collection filtrarAutocompleteClienteResponsavel(String valor, ClienteTipo tipo)throws ErroRepositorioException{
		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			consulta = " SELECT clie_id as id, clie_nmcliente as nome , clie_nncnpj as cnpj, cltp.cltp_icpessoafisicajuridica as ic from cadastro.cliente clie " +
					"inner join cadastro.cliente_tipo cltp on cltp.cltp_id = clie.cltp_id where cltp.cltp_icpessoafisicajuridica=" + ClienteTipo.INDICADOR_PESSOA_JURIDICA + " and ";
			
			valor = valor.trim();
			if(valor.contains("-")){
				valor = valor.split(" - ")[0].trim();
			}
			
			if(Util.validarStringNumerica(valor)){
				
				consulta += "clie_id like '%" + valor + "%'";
				consulta += " order by 1,2 ";
				retorno = session.createSQLQuery(consulta)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("cnpj", Hibernate.STRING)
				.addScalar("nome",Hibernate.STRING)
				.addScalar("ic", Hibernate.INTEGER).setMaxResults(200).list();
				
			}else{
				if(valor.length() > 3){
					consulta += "clie_nmcliente like '%" + valor.trim().toUpperCase() + "%'";
					consulta += " order by 1,2 ";
					retorno = session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("cnpj", Hibernate.STRING)
					.addScalar("nome",Hibernate.STRING)
					.addScalar("ic", Hibernate.INTEGER).setMaxResults(200).list();
				}
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Verifica Clientes Associados a um Cliente sem CNPJ ou ICPESSOAFISICAJURIDICA diferente de 2
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQtdClientesAssociadosResponsavelNaoJuridica(Integer idCliente)
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		
		String consulta = null;
		
		try {

			consulta = "SELECT count(*) as qtd FROM cadastro.cliente clie inner join cadastro.cliente_tipo cltp on cltp.cltp_id = clie.cltp_id " +
					"WHERE clie.clie_CDCLIENTERESPONSAVEL = " + idCliente.intValue() 
					+ " and clie.clie_id <> " + idCliente.intValue() + " " +
							"and (  cltp.CLTP_ICPESSOAFISICAJURIDICA <> 2 or clie.clie_nncnpj is null or clie.clie_nncnpj = '')";

			retorno = (Integer) session.createSQLQuery(consulta).addScalar("qtd", Hibernate.INTEGER).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Retorna Lista de Imóveis associados ao cliente
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisAssociadosCliente(Integer idCliente, Short relacaoTipo )
			throws ErroRepositorioException {
		
		Collection<ClienteImovel> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			
			Criteria crit = session.createCriteria(ClienteImovel.class);
			crit.add(Restrictions.eq("cliente.id",idCliente));
			crit.add(Restrictions.isNull("dataFimRelacao"));
			if(relacaoTipo != null){
				crit.add(Restrictions.eq("clienteRelacaoTipo.id",relacaoTipo.intValue()));
			}
			retorno = (Collection<ClienteImovel>) crit.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}

	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar os clientes a partir do imóvel e o tipo de relação com o cliente
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	
	public Collection obterClienteImovelporRelacaoTipo(Integer idImovel, Integer idRelacaoTipo) throws ErroRepositorioException {
		Collection<ClienteImovel> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			
			Criteria crit = session.createCriteria(ClienteImovel.class);
			crit.setFetchMode("cliente", FetchMode.JOIN);
			crit.add(Restrictions.eq("imovel.id",idImovel));
			crit.add(Restrictions.isNull("dataFimRelacao"));
			if(idRelacaoTipo != null && idRelacaoTipo.intValue() != -1){
				crit.add(Restrictions.eq("clienteRelacaoTipo.id",idRelacaoTipo.intValue()));
			}
			retorno = (Collection<ClienteImovel>) crit.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
		
	}
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos Através da Loja Virtual
	 * 
	 * Caso o CPF do cliente passado no parâmetro seja do cliente proprietário
	 * ou do cliente usuário do imóvel o método retorna o nome do cliente, caso
	 * contrário o método retorna null.
	 * 
	 * @author Diogo Peixoto
	 * @date 28/06/2011
	 * 
	 * @param CPFCliente
	 * @param Matricula
	 * 
	 * @throws ErroRepositorioException
	 * @return String
	 */
	public String validarCliente(String cpfCliente, Integer matricula) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		StringBuilder sb = new StringBuilder();
		String consulta = "";
		String nomeCliente = null; 
		
		try {
			sb.append("SELECT cli.clie_nmcliente AS nomeCliente ");
			sb.append("FROM cadastro.cliente cli ");
			sb.append("INNER JOIN cadastro.cliente_imovel imov ON imov.clie_id = cli.clie_id AND imov.imov_id = " + matricula + " AND clim_dtrelacaofim is NULL ");
			sb.append("INNER JOIN cadastro.cliente_relacao_tipo rela ON rela.crtp_id = imov.crtp_id AND (rela.crtp_id = 1 OR rela.crtp_id = 2) ");
			sb.append("WHERE cli.clie_nncpf = " + cpfCliente);
			
			consulta = sb.toString();
			SQLQuery sqlQuery = session.createSQLQuery(consulta);
			sqlQuery = sqlQuery.addScalar("nomeCliente",Hibernate.STRING);
			List<String> retorno = (List<String>) sqlQuery.setMaxResults(1).list();		
			//Caso a pesquisa recupere um cliente, retorna o primeiro elemento da lista (nome cliente).
			if(!retorno.isEmpty()){
				nomeCliente = retorno.get(0);
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return nomeCliente;
	}

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 05/08/2011
	 */
	public Cliente pesquisarDadosCliente(Integer idCliente) 
		throws ErroRepositorioException {
		
		Object[] dados = null;
		Cliente retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			
			consulta = " SELECT clie.clie_nmcliente AS nomeCliente, " //0
					 + "   clie.clie_nmabreviado AS nomeAbrevCliente, " //1
					 + "   clie.clie_nncpf AS cpfCliente, " //2
					 + "   clie.clie_nnrg AS rgCliente, " //3
					 + "   clie.clie_dtrgemissao AS dataRgEmissao, " //4
					 + "   clie.clie_ddvencimento AS dataVencimento, " //5
					 + "   clie.clie_nncnpj AS cnpjCliente, " //6
					 + "   clie.clie_dsemail AS emailCliente, " //7
					 + "   clie.clie_cdclienteresponsavel AS clienteResp, " //8
					 + "   cltp.cltp_id AS idClienteTipo, " //9 
					 + "   cltp.cltp_dsclientetipo AS descClienteTipo, " //10
					 + "   cltp.cltp_icuso AS indicadorUsoClienteTipo, " //11
					 + "   cltp.cltp_icpessoafisicajuridica AS indicadorPJ " //12
					 + " FROM cadastro.cliente clie "
					 + "   INNER JOIN cadastro.cliente_tipo cltp on cltp.cltp_id = clie.cltp_id "
					 + " WHERE clie.clie_id = :idCliente ";

			dados = (Object[]) session.createSQLQuery(consulta)
				.addScalar("nomeCliente", Hibernate.STRING)
				.addScalar("nomeAbrevCliente", Hibernate.STRING)
				.addScalar("cpfCliente", Hibernate.STRING)
				.addScalar("rgCliente", Hibernate.STRING)
				.addScalar("dataRgEmissao", Hibernate.DATE)
				.addScalar("dataVencimento", Hibernate.SHORT)
				.addScalar("cnpjCliente", Hibernate.STRING)
				.addScalar("emailCliente", Hibernate.STRING)
				.addScalar("clienteResp", Hibernate.INTEGER)
				.addScalar("idClienteTipo", Hibernate.INTEGER)
				.addScalar("descClienteTipo", Hibernate.STRING)
				.addScalar("indicadorUsoClienteTipo", Hibernate.SHORT)
				.addScalar("indicadorPJ", Hibernate.SHORT)
				.setInteger("idCliente", idCliente)
				.setMaxResults(1).uniqueResult();
			
			if (dados != null) {
				retorno = new Cliente();
				retorno.setId(idCliente);

				if (dados[0] != null && dados[0] != null) {
					retorno.setNome((String) dados[0]);
				}
				if (dados[1] != null && dados[1] != null) {
					retorno.setNomeAbreviado((String) dados[1]);
				}
				if (dados[2] != null && dados[2] != null) {
					retorno.setCpf((String) dados[2]);
				}
				if (dados[3] != null && dados[3] != null) {
					retorno.setRg((String) dados[3]);
				}
				if (dados[4] != null && dados[4] != null) {
					retorno.setDataEmissaoRg((Date) dados[4]);
				}
				if (dados[5] != null && dados[5] != null) {
					retorno.setDataVencimento((Short) dados[5]);
				}
				if (dados[6] != null && dados[6] != null) {
					retorno.setCnpj((String) dados[6]);
				}
				if (dados[7] != null && dados[7] != null) {
					retorno.setEmail((String) dados[7]);
				}
				if (dados[8] != null && dados[8] != null) {
					Cliente clienteResponsavel = new Cliente();
					clienteResponsavel.setId((Integer) dados[8]);
					retorno.setCliente(clienteResponsavel);
				}
				if (dados[9] != null && dados[9] != null) {
					ClienteTipo clienteTipo = new ClienteTipo();
					clienteTipo.setId((Integer) dados[9]);

					if (dados[10] != null && dados[10] != null) {
						clienteTipo.setDescricao((String) dados[10]);
					}
					if (dados[11] != null && dados[11] != null) {
						clienteTipo.setIndicadorUso((Short) dados[11]);
					}
					if (dados[12] != null && dados[12] != null) {
						clienteTipo.setIndicadorPessoaFisicaJuridica((Short) dados[12]);
					}
					
					retorno.setClienteTipo(clienteTipo);
				}
				
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}
	
	/**TODO: Cosanpa
	 * 
	 * Mantis 494
	 * 
	 * Pesquisando cliente tipo pelo id
	 * 
	 * @author Wellington Rocha*/
	public ClienteTipo pesquisarClienteTipo(Integer idClienteTipo)
			throws ErroRepositorioException {

		ClienteTipo retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "select cltp from ClienteTipo cltp "
				+ "where cltp.id = :idClienteTipo ";
	
		retorno = (ClienteTipo) session.createQuery(consulta)
				.setInteger("idClienteTipo", idClienteTipo)
				.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Collection<Cliente> pesquisarClientePorCpfCnpj(String cpfCnpj) throws ErroRepositorioException{
		Collection<Cliente> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select c from Cliente c where c.cpf = :cpfCnpj or c.cnpj = :cpfCnpj";
	
			retorno = (Collection<Cliente>) session.createQuery(consulta)
				.setString("cpfCnpj", cpfCnpj)
				.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
}
