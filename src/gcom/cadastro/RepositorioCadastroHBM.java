package gcom.cadastro;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.cliente.CadastroAguaPara;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelRamoAtividadeAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.cadastro.tarifasocial.TarifaSocialMotivoCarta;
import gcom.cobranca.CobrancaSituacao;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RotaAtualizacaoSeq;
import gcom.cadastro.cliente.CadastroAguaPara;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergiaHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

public class RepositorioCadastroHBM implements IRepositorioCadastro {
	
	private Logger logger = Logger.getLogger(RepositorioCadastroHBM.class);

	private static IRepositorioCadastro instancia;

	private RepositorioCadastroHBM() {
	}

	public static IRepositorioCadastro getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioCadastroHBM();
		}
		return instancia;
	}

	public Collection pesquisarFeriado(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio,
			Integer numeroPagina) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (tipoFeriado != 1) {

				consulta = " select 2 as tipoFeriado, mfer.mfer_id as id, mfer.mfer_dsferiado as descricao, "
						+ " muni.muni_nmmunicipio as descricaoMunicipio, mfer.mfer_dtferiado as data"
						+ " from cadastro.municipio_feriado mfer"
						+ " inner join cadastro.municipio muni on(mfer.muni_id = muni.muni_id)";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))
						|| (idMunicipio != null && !idMunicipio.equals(""))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(mfer.mfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";
					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "mfer.mfer_dtferiado between :dataInicio and :dataFim and ";
					}

					if (idMunicipio != null && !idMunicipio.equals("")) {
						consulta += "mfer.muni_id = " + idMunicipio + " and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			if (tipoFeriado == 3) {
				consulta += "union all";
			}

			if (tipoFeriado != 2) {
				consulta += " select 1 as tipoFeriado, nfer_id as id, nfer_dsferiado as descricao,"
						+ " '' as descricaoMunicipio, nfer_dtferiado as data"
						+ " from cadastro.nacional_feriado ";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(nfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";

					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "nfer_dtferiado between :dataInicio and :dataFim and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			consulta = consulta + "order by data";

			if (dataFeriadoInicio != null && !dataFeriadoInicio.equals("")) {
				retorno = session.createSQLQuery(consulta).addScalar(
						"tipoFeriado", Hibernate.SHORT).addScalar("id",
						Hibernate.INTEGER).addScalar("descricao",
						Hibernate.STRING).addScalar("descricaoMunicipio",
						Hibernate.STRING).addScalar("data", Hibernate.DATE)
						.setDate("dataInicio", dataFeriadoInicio).setDate(
								"dataFim", dataFeriadoFim).setFirstResult(
								10 * numeroPagina).setMaxResults(10).list();
			} else {
				retorno = session.createSQLQuery(consulta).addScalar(
						"tipoFeriado", Hibernate.SHORT).addScalar("id",
						Hibernate.INTEGER).addScalar("descricao",
						Hibernate.STRING).addScalar("descricaoMunicipio",
						Hibernate.STRING).addScalar("data", Hibernate.DATE)
						.setFirstResult(10 * numeroPagina).setMaxResults(10)
						.list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio)
			throws ErroRepositorioException {

		int retorno = 0;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (tipoFeriado != 1) {

				consulta = " select count(mfer.mfer_id) as id"
						+ " from cadastro.municipio_feriado mfer"
						+ " inner join cadastro.municipio muni on(mfer.muni_id = muni.muni_id)";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))
						|| (idMunicipio != null && !idMunicipio.equals(""))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(mfer.mfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";
					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "mfer.mfer_dtferiado between :dataInicio and :dataFim and ";
					}

					if (idMunicipio != null && !idMunicipio.equals("")) {
						consulta += "mfer.muni_id = " + idMunicipio + " and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			if (tipoFeriado == 3) {
				consulta += "union all";
			}

			if (tipoFeriado != 2) {
				consulta += " select count(nfer_id) as id"
						+ " from cadastro.nacional_feriado ";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(nfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";
					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "nfer_dtferiado between :dataInicio and :dataFim and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			Collection valores = null;
			if (dataFeriadoInicio != null && !dataFeriadoInicio.equals("")) {
				valores = (Collection) session.createSQLQuery(consulta)
						.addScalar("id", Hibernate.INTEGER).setDate(
								"dataInicio", dataFeriadoInicio).setDate(
								"dataFim", dataFeriadoFim).list();
			} else {
				valores = (Collection) session.createSQLQuery(consulta)
						.addScalar("id", Hibernate.INTEGER).list();
			}

			Integer valor = 0;
			Iterator iteratorValor = valores.iterator();
			while (iteratorValor.hasNext()) {
				valor = valor + (Integer) iteratorValor.next();
			}

			retorno = valor;

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void atualizarMensagemSistema(String mensagemSistema)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "update SistemaParametro sp "
				+ "set sp.mensagemSistema =:mensagemSistema, sp.ultimaAlteracao = :dataAtual ";

		try {

			session.createQuery(consulta).setString("mensagemSistema",
					mensagemSistema).setTimestamp("dataAtual", new Date())
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ErroRepositorioException {

		EnvioEmail retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select envioEmail " + "from EnvioEmail envioEmail "
					+ "where envioEmail.id = :idEnvioEmail";

			retorno = (EnvioEmail) session.createQuery(consulta).setInteger(
					"idEnvioEmail", idEnvioEmail).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
			throws ErroRepositorioException {

		DadosEnvioEmailHelper retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select new gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper(ipServidorSmtp, dsEmailResponsavel) "
					+ "from SistemaParametro sistemaParametro ";

			retorno = (DadosEnvioEmailHelper) session.createQuery(consulta)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Integer> pesquisarTodosIdsSetorComercial()
			throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "select stcm.id from SetorComercial stcm  ";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Object[] pesquisarSetorQuadra(Integer idLocalidade)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select stcm.id, qdra.id " + "from Quadra qdra "
					+ "inner join qdra.setorComercial stcm "
					+ "where stcm.localidade.id = :idLocalidade";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idLocalidade", idLocalidade).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarLogradouroBairro(Integer codigoLogradouro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select logB.id " + "from LogradouroBairro logB "
					+ "where logB.logradouro.id = :codigoLogradouro";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"codigoLogradouro", codigoLogradouro).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarLogradouroCep(Integer codigoLogradouro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select logC.id " + "from LogradouroCep logC "
					+ "where logC.logradouro.id = :codigoLogradouro";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"codigoLogradouro", codigoLogradouro).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void inserirClienteEndereco(Integer idCliente,
			String numeroImovelMenor, String numeroImovelMaior, Integer idCep,
			Integer idBairro, Integer idLograd, Integer idLogradBairro,
			Integer idLogradCep) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;
		String sequence = Util
				.obterNextValSequence("cadastro.seq_cliente_endereco");
		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into cadastro.cliente_endereco(cled_id, clie_id, edtp_id, "
					+ "edrf_id, cled_nnimovel, cled_dscomplementoendereco, "
					+ "cep_id, bair_id, cled_icenderecocorrespondencia, "
					+ "cled_tmultimaalteracao, logr_id, lgbr_id, lgcp_id) "
					+ "values ( "
					+ sequence
					+ ", "
					+ idCliente
					+ ", 1, 1, "
					+ numeroImovelMenor
					+ ", "
					+ numeroImovelMaior
					+ ", "
					+ idCep
					+ ", "
					+ idBairro
					+ ", 1, "
					+ Util.obterSQLDataAtual()
					+ " , "
					+ idLograd
					+ ", "
					+ idLogradBairro + ", " + idLogradCep + ")";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}

	public void inserirClienteImovel(Integer idCliente, Integer idImovel,
			String data) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();
			String sequence = Util
					.obterNextValSequence("cadastro.seq_cliente_imovel");
			insert = "insert into cadastro.cliente_imovel(clim_id, "
					+ "clie_id, imov_id, clim_dtrelacaoinicio, "
					+ "clim_tmultimaalteracao, "
					+ "crtp_id, clim_icnomeconta) " + "values ( " + sequence
					+ ", " + idCliente + ", " + idImovel + ", " + data + ", "
					+ Util.obterSQLDataAtual() + " , " + "2, " + "1)";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}

	public void inserirImovelSubcategoria(Integer idImovel,
			Integer idSubcategoria) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into cadastro.imovel_subcategoria(imov_id, scat_id, "
					+ "imsb_qteconomia, imsb_tmultimaalteracao) "
					+ "values ( "
					+ idImovel
					+ ", "
					+ idSubcategoria
					+ ", "
					+ "1, "
					+ Util.obterSQLDataAtual() + " )";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}

	public void inserirLigacaoAgua(Integer idImovel, String dataBD)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into atendimentopublico.ligacao_agua(lagu_id, lagu_dtimplantacao, lagu_dtligacaoagua, "
					+ "lagu_icemissaocortesupressao, lagd_id, lagm_id, lapf_id, lagu_tmultimaalteracao) "
					+ "values ( "
					+ idImovel
					+ ", "
					+ dataBD
					+ ", "
					+ dataBD
					+ ", 1, 2, 1, 1," + Util.obterSQLDataAtual() + " )";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}

	public Collection pesquisarCadastroRibeiraop()
			throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select crp " + "from CadastroRibeiraop crp "
					+ "where crp.imovel.id is null " + "order by crp.codigo";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void atualizarImovelRibeirao(Integer idImovel, Integer codigo)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizarValorExcedente;

		try {

			atualizarValorExcedente = "UPDATE CadastroRibeiraop "
					+ "SET imov_id = :idImovel " + "WHERE codigo = :codigo ";

			session.createQuery(atualizarValorExcedente).setInteger("idImovel",
					idImovel).setInteger("codigo", codigo).executeUpdate();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Collection pesquisarClientesSubordinados(Integer idCliente)

	throws ErroRepositorioException {

		Collection retorno = null;

		String consulta;

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select ci.id " + "from Cliente ci "
					+ "where ci.cliente.id = :idCliente";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idCliente", idCliente).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Collection pesquisarRelatorioAtualizacaoCadastral(
			Collection idLocalidades, Collection idSetores,
			Collection idQuadras, String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select imovel.id, "// 0
					+ " cliente.nome, "// 1
					+ " localidade.id, "// 2
					+ " localidade.descricao, "// 3
					+ " setorComercial.codigo, "// 4
					+ " setorComercial.descricao, "// 5
					+ " unidadeNegocio.nome,"// 6
					+ " imovel.quantidadeEconomias, "// 7
					+ " rota.codigo,"// 8
					+ " imovel.numeroSequencialRota, "// 9
					+ " imovel.indicadorExclusao,"// 10
					+ " unidadeNegocio.id "// 11
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota "
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " where relacaoTipo.id =:idRelacaoTipo ";

			if (idLocalidades != null && !idLocalidades.isEmpty()) {
				consulta = consulta + " and localidade.id in (";
				Iterator iterator = idLocalidades.iterator();
				while (iterator.hasNext()) {
					Localidade localidade = (Localidade) iterator.next();
					consulta = consulta + localidade.getId().toString() + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ")";
			}
			if (idSetores != null && !idSetores.isEmpty()) {
				consulta = consulta
						+ " and setorComercial.codigo in (:setores)";
				Iterator iterator = idSetores.iterator();
				while (iterator.hasNext()) {
					SetorComercial setorComercial = (SetorComercial) iterator
							.next();
					consulta = consulta + setorComercial.getId().toString()
							+ ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ")";
			}
			if (idQuadras != null && !idQuadras.isEmpty()) {
				consulta = consulta + " and quadra.numeroQuadra in (:quadras)";
				Iterator iterator = idQuadras.iterator();
				while (iterator.hasNext()) {
					Quadra quadra = (Quadra) iterator.next();
					consulta = consulta + quadra.getId().toString() + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ")";
			}
			if (rotaInicial != null && !rotaInicial.trim().equals("")
					&& rotaFinal != null && !rotaFinal.trim().equals("")) {
				consulta = consulta + " and (rota.codigo >= " + rotaInicial
						+ " and rota.codigo <= " + rotaFinal + ")";
			}

			if (sequencialRotaInicial != null
					&& !sequencialRotaInicial.trim().equals("")
					&& sequencialRotaFinal != null
					&& !sequencialRotaFinal.trim().equals("")) {
				consulta = consulta + " and (imovel.numeroSequencialRota >= "
						+ sequencialRotaInicial
						+ " and imovel.numeroSequencialRota <= "
						+ sequencialRotaFinal + ")";
			}

			retorno = session.createQuery(consulta).setInteger("idRelacaoTipo",
					ClienteRelacaoTipo.USUARIO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioImoveisSituacaoLigacaoAgua(
			FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoSituacaoLigacaoEsgoto = filtro
				.getSituacaoLigacaoEsgoto();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select imovel.id, " // 0
					+ " gerenciaRegional.id, " // 1
					+ " gerenciaRegional.nome, "// 2
					+ " unidadeNegocio.id," // 3
					+ " unidadeNegocio.nome," // 4
					+ " localidade.id, " // 5
					+ " localidade.descricao, " // 6
					+ " setorComercial.codigo, "// 7
					+ " setorComercial.descricao, "// 8
					+ " quadra.numeroQuadra, " // 9
					+ " cliente.nome, " // 10
					+ " ligacaoAguaSituacao.descricao, " // 11
					+ " ligacaoEsgotoSituacao.descricao, " // 12
					+ " rota.codigo," // 13
					+ " imovel.numeroSequencialRota, " // 14
					+ " imovel.lote, " // 15
					+ " imovel.subLote, " // 16
					+ " setorComercial.id, "// 17
					+ " rota.id " // 18
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua) "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoEsgoto != null
					&& !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
				consulta = consulta
						+ " and ligacaoEsgotoSituacao.id in (:situacaoEsgoto) ";
				parameters.put("situacaoEsgoto", colecaoSituacaoLigacaoEsgoto);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and (localidade.id >= "
						+ localidadeInicial + " and localidade.id <= "
						+ localidadeFinal + ")";
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and (setorComercial.codigo >= "
						+ setorComercialInicial
						+ " and setorComercial.codigo <= "
						+ setorComercialFinal + ")";
			}

			if (rotaInicial != null) {
				consulta = consulta + " and (rota.codigo >= " + rotaInicial
						+ " and rota.codigo <= " + rotaFinal + ")";
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta + " and (imovel.numeroSequencialRota >= "
						+ sequencialRotaInicial
						+ " and imovel.numeroSequencialRota <= "
						+ sequencialRotaFinal + ")";
			}

			consulta += " order by gerenciaRegional.id,unidadeNegocio.id,localidade.id,"
					+ "setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote,"
					+ "rota.codigo,imovel.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
			FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = 0;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoSituacaoLigacaoEsgoto = filtro
				.getSituacaoLigacaoEsgoto();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select count(*) " // 0
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua) "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoEsgoto != null
					&& !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
				consulta = consulta
						+ " and ligacaoEsgotoSituacao.id in (:situacaoEsgoto) ";
				parameters.put("situacaoEsgoto", colecaoSituacaoLigacaoEsgoto);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and (localidade.id >= "
						+ localidadeInicial + " and localidade.id <= "
						+ localidadeFinal + ")";
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and (setorComercial.codigo >= "
						+ setorComercialInicial
						+ " and setorComercial.codigo <= "
						+ setorComercialFinal + ")";
			}

			if (rotaInicial != null) {
				consulta = consulta + " and (rota.codigo >= " + rotaInicial
						+ " and rota.codigo <= " + rotaFinal + ")";
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta + " and (imovel.numeroSequencialRota >= "
						+ sequencialRotaInicial
						+ " and imovel.numeroSequencialRota <= "
						+ sequencialRotaFinal + ")";
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Short rotaInicial = filtro.getRotaInicial();
		Short rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Integer quantidadeFaturasAtrasoInicial = filtro
				.getQuantidadeFaturasAtrasoInicial();
		Integer quantidadeFaturasAtrasoFinal = filtro
				.getQuantidadeFaturasAtrasoFinal();

		Float valorFaturasAtrasoInicial = filtro.getValorFaturasAtrasoInicial();
		Float valorFaturasAtrasoFinal = filtro.getValorFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();

		Integer situacaoCobranca = filtro.getSituacaoCobranca();
		
		String hidrometro = filtro.getHidrometro();

		String categoria = "";
		if (colecaoCategorias != null) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		try {

			Map<String, Object> parameters = new HashMap<String, Object>();

			String consulta = " select \n"
					+ "   gr.id, \n"
					+ "   gr.nome, \n"
					+ "   un.id, \n"
					+ "   un.nome, \n"
					+ "   sc.codigo, \n"
					+ "   sc.descricao, \n"
					+ "   loc.id, \n"
					+ "   loc.descricao, \n"
					+ "   cli.nome, \n"
					+ "   las.descricaoAbreviado, \n"
					+ "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota, \n"
					+ "   imo.id, \n"
					+ "   les.id, \n"
					+ "   les.descricaoAbreviado, \n"
					+ "   qua.numeroQuadra, \n"
					+ "   min( c.referencia ) as referenciaMinima, \n"
					+ "   count(*) as quatidadeContas, \n"
					+ "   sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) as total, \n"
					+ "   max( c.referencia ) as referenciaMaxima, \n" 
					+ "   imo.lote, \n" 
					+ "   imo.subLote, \n" 
					+ "   cli.cpf, \n" 
					+ "   cli.cnpj \n" 
					+ " from \n" + "   Conta c, ClienteImovel ci  " + categoria
					+ " \n" + "   inner join c.imovel imo \n"
					+ "   inner join imo.localidade loc \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join loc.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "	  inner join imo.ligacaoAgua la \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join qua.rota rot \n"
					+ "   inner join ci.cliente cli \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}

			consulta += " where \n"
					+ "   imo.id = ci.imovel.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO
					+ " and \n"
					+ "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (localidadeInicial != null) {
				consulta += " and loc.id between :localidadeInicial and :localidadeFinal";

				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal ";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += "  and rot.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";

				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			if (hidrometro != null && !hidrometro.equals("0")) {
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";

				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by \n" + "   gr.id, \n" + "   gr.nome, \n"
					+ "   un.id, \n" + "   un.nome, \n" + "   sc.codigo, \n"
					+ "   sc.descricao, \n" + "   loc.id, \n"
					+ "   loc.descricao, \n" + "   cli.nome, \n"
					+ "   cli.cpf, \n" + "   cli.cnpj, \n"
					+ "   las.descricaoAbreviado, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota, \n" + "   imo.id, \n"
					+ "   les.id, \n" + "   les.descricaoAbreviado, \n"
					+ "   qua.numeroQuadra, \n" + "   imo.lote, \n"
					+ "   imo.subLote \n";

			if (valorFaturasAtrasoInicial != null
					|| quantidadeFaturasAtrasoInicial != null) {
				consulta += " having ";

				if (quantidadeFaturasAtrasoInicial != null) {
					consulta += "  count(*) between :quantidadeFaturasAtrasoInicial and :quantidadeFaturasAtrasoFinal";

					parameters.put("quantidadeFaturasAtrasoInicial",
							quantidadeFaturasAtrasoInicial);
					parameters.put("quantidadeFaturasAtrasoFinal",
							quantidadeFaturasAtrasoFinal);
				}

				if (valorFaturasAtrasoInicial != null) {
					if (quantidadeFaturasAtrasoInicial != null) {
						consulta += " and ";
					}

					consulta += " sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) between :valorFaturasAtrasoInicial and :valorFaturasAtrasoFinal";

					parameters.put("valorFaturasAtrasoInicial",
							valorFaturasAtrasoInicial);
					parameters.put("valorFaturasAtrasoFinal",
							valorFaturasAtrasoFinal);
				}
			}

			consulta += " order by \n" + "   gr.id, \n" + "   un.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   rot.codigo, \n" + "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();

		try {

			Integer referenciaImoveisFaturasAtrasoInicial = filtro
					.getReferenciaFaturasAtrasoInicial();
			Integer referenciaImoveisFaturasAtrasoFinal = filtro
					.getReferenciaFaturasAtrasoFinal();

			Integer quantidadeFaturasAtrasoInicial = filtro
					.getQuantidadeFaturasAtrasoInicial();
			Integer quantidadeFaturasAtrasoFinal = filtro
					.getQuantidadeFaturasAtrasoFinal();

			Float valorFaturasAtrasoInicial = filtro
					.getValorFaturasAtrasoInicial();
			Float valorFaturasAtrasoFinal = filtro.getValorFaturasAtrasoFinal();

			Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
					.getSituacaoLigacaoAgua();
			Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
			Collection<Integer> colecaoCategorias = filtro.getCategorias();
			Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();

			Integer situacaoCobranca = filtro.getSituacaoCobranca();
			
			String hidrometro = filtro.getHidrometro();

			Integer clienteSuperior = filtro.getClienteSuperior();
			Integer cliente = filtro.getCliente();
			Integer tipoRelacao = filtro.getTipoRelacao();
			Integer responsavel = filtro.getResponsavel();

			String clienteConta = "";
			if (cliente != null && responsavel != null
					&& !responsavel.equals(1)) {
				clienteConta = " , ClienteConta clienteConta ";
			}

			String categoria = "";
			if (!Util.isVazioOrNulo(colecaoCategorias)) {
				categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
			}

			String consulta = "";
			Map<String, Object> parameters = new HashMap<String, Object>();

			consulta = " select \n"
					+ "   cli.id, \n"
					+ "   cli.nome as  cliente, \n"
					+ "   gr.id, \n"
					+ "   loc.id, \n"
					+ "   sc.codigo, \n"
					+ "   qua.numeroQuadra, \n"
					+ "   las.descricaoAbreviado, \n"
					+ "   min( c.referencia ) as referenciaMinima, \n"
					+ "   count(*) as quatidadeContas, \n"
					+ "   sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) as totalSemEncargos, \n"
					+ "   rot.codigo, \n" 
					+ "   imo.numeroSequencialRota, \n" 
					+ "   imo.id, \n" 
					+ "   les.descricaoAbreviado, \n" 
					+ "   max( c.referencia ) as referenciaMaxima \n" 
					+ " from Cliente cli " + categoria + clienteConta + " \n"
					+ "   inner join cli.clienteImoveis ci \n"
					+ "   inner join ci.imovel imo \n"
					+ "	  inner join imo.ligacaoAgua la \n"	
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join imo.localidade loc \n"
					+ "   inner join qua.rota rot \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join imo.contas c \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}

			consulta += " where \n"
					+ "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (!clienteConta.trim().equals("")) {
				consulta += " and clienteConta.conta.id = c.id ";
			}

			if (clienteSuperior != null) {
				consulta += " and ci.clienteRelacaoTipo.id = "
						+ ClienteRelacaoTipo.RESPONSAVEL;
				consulta += " and ( cli.id = :clienteSuperior or cli.cliente.id = :clienteSuperior2 ) ";

				parameters.put("clienteSuperior", clienteSuperior);
				parameters.put("clienteSuperior2", clienteSuperior);
			}

			if (cliente != null && responsavel != null) {
				if (responsavel.equals(0)) {
					consulta += " and clienteConta.cliente.id = cli.id ";
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and clienteConta.clienteRelacaoTipo.id = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(1)) {
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ci.clienteRelacaoTipo.id = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(2)) {
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ( \n";
						consulta += " 	(clienteConta.cliente.id = cli.id  ";
						consulta += " 		and clienteConta.clienteRelacaoTipo.id = :tipoRelacao1 ) \n";
						consulta += " 	or  ";
						consulta += " 	( ci.clienteRelacaoTipo = :tipoRelacao2) ";
						consulta += " )";

						parameters.put("tipoRelacao1", tipoRelacao);
						parameters.put("tipoRelacao2", tipoRelacao);
					}
				}
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";

				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			if (hidrometro != null && !hidrometro.equals("0")) {
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by  cli.id,cli.nome,gr.id,loc.id,sc.codigo,"
					+ "qua.numeroQuadra,las.descricaoAbreviado,rot.codigo,"
					+ "imo.numeroSequencialRota,imo.id,les.descricaoAbreviado ";

			if (valorFaturasAtrasoInicial != null
					|| quantidadeFaturasAtrasoInicial != null) {
				consulta += " having ";

				if (quantidadeFaturasAtrasoInicial != null) {
					consulta += "  count(*) between :quantidadeFaturasAtrasoInicial and :quantidadeFaturasAtrasoFinal";

					parameters.put("quantidadeFaturasAtrasoInicial",
							quantidadeFaturasAtrasoInicial);
					parameters.put("quantidadeFaturasAtrasoFinal",
							quantidadeFaturasAtrasoFinal);
				}

				if (valorFaturasAtrasoInicial != null) {
					if (quantidadeFaturasAtrasoInicial != null) {
						consulta += " and ";
					}

					consulta += " sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) between :valorFaturasAtrasoInicial and :valorFaturasAtrasoFinal";

					parameters.put("valorFaturasAtrasoInicial",
							valorFaturasAtrasoInicial);
					parameters.put("valorFaturasAtrasoFinal",
							valorFaturasAtrasoFinal);
				}
			}

			consulta += " order by \n" + "   cli.id, \n" + "   gr.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   qua.numeroQuadra, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Short rotaInicial = filtro.getRotaInicial();
		Short rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer qtd1 = filtro.getQuantidadeFaturasAtrasoInicial();
		Integer qtd2 = filtro.getQuantidadeFaturasAtrasoFinal();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();

		Integer situacaoCobranca = filtro.getSituacaoCobranca();

		String categoria = "";
		if (colecaoCategorias != null) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		try {

			Map<String, Object> parameters = new HashMap<String, Object>();

			String consulta = " select "
					+ "   imo.id, "
					+ "   count(*) as quatidadeContas "
					+ " from "
					+ "   Conta c "
					+ "   inner join c.imovel imo "
					+ "   inner join imo.localidade loc "
					+ "   inner join loc.gerenciaRegional gr "
					+ "   inner join loc.unidadeNegocio un "
					+ "   inner join imo.setorComercial sc "
					+ "   inner join imo.ligacaoAguaSituacao las "
					+ "   inner join imo.ligacaoEsgotoSituacao les "
					+ "   inner join imo.quadra.rota rot, "
					+ "   ClienteImovel ci  "
					+ categoria
					+ "   inner join ci.cliente cli "
					+ "   inner join cli.clienteTipo cltp "
					+ " where "
					+ "   imo.id = ci.imovel.id and "
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO
					+ " and "
					+ "   ci.dataFimRelacao is null and "
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and "
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) ";

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (localidadeInicial != null) {
				consulta += " and loc.id between :localidadeInicial and :localidadeFinal";

				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal ";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += "  and rot.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (colecaoEsferasPoder != null) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";
				parameters.put("categorias", colecaoCategorias);
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by imo.id " +

			" having " + "   count(*) between :qtd1 and :qtd2 ";

			parameters.put("qtd1", qtd1);
			parameters.put("qtd2", qtd2);

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list().size();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer qtd1 = filtro.getQuantidadeFaturasAtrasoInicial();
		Integer qtd2 = filtro.getQuantidadeFaturasAtrasoFinal();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();

		Integer situacaoCobranca = filtro.getSituacaoCobranca();

		Integer clienteSuperior = filtro.getClienteSuperior();
		Integer cliente = filtro.getCliente();
		Integer tipoRelacao = filtro.getTipoRelacao();
		Integer responsavel = filtro.getResponsavel();

		String clienteConta = "";
		if (cliente != null && responsavel != null && !responsavel.equals(1)) {
			clienteConta = " , ClienteConta clienteConta ";
		}

		String categoria = "";
		if (colecaoCategorias != null) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		String consulta = "";
		Map<String, Object> parameters = new HashMap<String, Object>();

		try {
			consulta = " select " + "   imo.id, "
					+ "   count(*) as quatidadeContas " + " from "
					+ "   Cliente cli  " + categoria + clienteConta + " \n"
					+ "   inner join cli.clienteImoveis ci \n"
					+ "   inner join ci.imovel imo \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.contas c \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}

			consulta += " where "
					+ "   ci.dataFimRelacao is null and "
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and "
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) ";

			if (!clienteConta.trim().equals("")) {
				consulta += " and clienteConta.conta.id = c.id ";
			}

			if (clienteSuperior != null) {
				consulta += " and ci.clienteRelacaoTipo = "
						+ ClienteRelacaoTipo.RESPONSAVEL;
				consulta += " and ( cli.id = :clienteSuperior or cli.cliente.id = :clienteSuperior2) ";

				parameters.put("clienteSuperior", clienteSuperior);
				parameters.put("clienteSuperior2", clienteSuperior);
			}

			if (cliente != null && responsavel != null) {
				if (responsavel.equals(0)) {
					consulta += " and clienteConta.cliente.id = cli.id ";
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and clienteConta.clienteRelacaoTipo.id = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(1)) {
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ci.clienteRelacaoTipo = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(2)) {

					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ( \n";
						consulta += " 	(clienteConta.cliente.id = cli.id  ";
						consulta += " 		and clienteConta.clienteRelacaoTipo.id = :tipoRelacao1 ) \n";
						consulta += " 	or  ";
						consulta += " 	( ci.clienteRelacaoTipo = :tipoRelacao2) ";
						consulta += " )";

						parameters.put("tipoRelacao1", tipoRelacao);
						parameters.put("tipoRelacao2", tipoRelacao);
					}
				}
			}

			if (colecaoEsferasPoder != null) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";
				parameters.put("categorias", colecaoCategorias);
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by imo.id " +

			" having " + "   count(*) between :qtd1 and :qtd2 ";

			parameters.put("qtd1", qtd1);
			parameters.put("qtd2", qtd2);

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list().size();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer consumoMedioAguaInicial = filtro.getConsumoMedioAguaInicial();
		Integer consumoMedioAguaFinal = filtro.getConsumoMedioAguaFinal();

		Collection<Integer> colecaoPerfisImovel = filtro.getColecaoPerfisImovel();

		Integer consumoMedioEsgotoInicial = filtro
				.getConsumoMedioEsgotoInicial();
		Integer consumoMedioEsgotoFinal = filtro.getConsumoMedioEsgotoFinal();

		Integer indicadorMedicaoComHidrometro = filtro
				.getIndicadorMedicaoComHidrometro();
		
		Integer anoMesReferencia = filtro
				.getAnoMesReferencia();

		String consulta = "";

		try {
			consulta = "select greg.greg_id as gerencia, " + // 0
					"greg.greg_nmregional as nomeGerencia, " + // 1
					"uneg.uneg_id as unidadeNegocio, " + // 2
					"uneg.uneg_nmunidadenegocio as nomeUnidadeNegocio, " + // 3
					"loca.loca_id as localidade, " + // 4
					"loca.loca_nmlocalidade as nomeLocalidade," + // 5
					"stcm.stcm_cdsetorcomercial as codigoSetor, " + // 6
					"stcm.stcm_nmsetorcomercial as nomeSetor, " + // 7
					"clie.clie_nmcliente as nomeCliente, " + // 8
					"last.last_dsligacaoaguasituacao as ligacaoAguaSituacao, " + // 9
					"consumoAgua.cshi_nnconsumomedio as consumoAgua, " + // 10
					"rota.rota_cdrota as codigoRota, " + // 11
					"imov.imov_nnsequencialrota as sequencialRota, " + // 12
					"imov.imov_id as imovel, " + // 13
					"lest.lest_dsligacaoesgotosituacao as ligacaoEsgotoSituacao, "
					+ // 14
					"consumoEsgoto.cshi_nnconsumomedio as consumoEsgoto, " + // 15
					"imov.imov_nnlote as lote, " + // 16
					"imov.imov_nnsublote as subLote, " + // 17
					"qdra.qdra_nnquadra as numeroQuadra "; // 18

			consulta += "from cadastro.cliente_imovel clim "
					+

					"inner join cadastro.imovel imov on clim.imov_id=imov.imov_id "
					+ "inner join cadastro.localidade loca on imov.loca_id=loca.loca_id "
					+ "inner join cadastro.gerencia_regional greg on loca.greg_id=greg.greg_id "
					+ "inner join cadastro.unidade_negocio uneg on loca.uneg_id=uneg.uneg_id "
					+ "inner join cadastro.setor_comercial stcm on imov.stcm_id=stcm.stcm_id "
					+ "inner join cadastro.quadra qdra on imov.qdra_id=qdra.qdra_id "
					+ "inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao last on imov.last_id=last.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on imov.lest_id=lest.lest_id "
					+ "inner join atendimentopublico.ligacao_agua lagu on imov.imov_id=lagu.lagu_id "
					+

					"left outer join cadastro.logradouro_cep lgcp on imov.lgcp_id=lgcp.lgcp_id "
					+ "left outer join cadastro.logradouro logr on lgcp.logr_id=logr.logr_id "
					+ "left outer join cadastro.logradouro_bairro lgbr on imov.lgbr_id=lgbr.lgbr_id "
					+ "left outer join cadastro.bairro bair on lgbr.bair_id=bair.bair_id "
					+ "inner join cadastro.cliente clie on clim.clie_id=clie.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo crtp on clim.crtp_id=crtp.crtp_id "
					+

					"and (crtp.crtp_id = 2 ) "
					+

					"left join micromedicao.consumo_historico consumoAgua on imov.imov_id=consumoAgua.imov_id "
					+ "and (consumoAgua.lgti_id = 1 ) and (consumoAgua.cshi_amfaturamento = :anoMesReferencia ) "
					+

					"left join micromedicao.consumo_historico consumoEsgoto on imov.imov_id=consumoEsgoto.imov_id "
					+ "and (consumoEsgoto.lgti_id = 2 ) and (consumoEsgoto.cshi_amfaturamento = :anoMesReferencia ) ";

			consulta += "where clim.clim_dtrelacaofim is null ";

			if (unidadeNegocio != null) {
				consulta += " and uneg.uneg_id = " + unidadeNegocio.toString();
			}

			if (gerencia != null) {
				consulta += " and greg.greg_id = " + gerencia.toString();
			}

			if (localidadeInicial != null) {
				consulta += " and loca.loca_id between "
						+ localidadeInicial.toString() + " and "
						+ localidadeFinal.toString();
			}

			if (setorComercialInicial != null) {
				consulta += " and stcm.stcm_cdsetorcomercial between "
						+ setorComercialInicial.toString() + " and "
						+ setorComercialFinal.toString();
			}

			if (rotaInicial != null) {
				consulta += " and rota.rota_cdrota between "
						+ rotaInicial.toString() + " and "
						+ rotaFinal.toString();
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial.toString() + " and "
						+ sequencialRotaFinal;
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				String clausulaIn = "";
				Iterator perfis = colecaoPerfisImovel.iterator();

				while (perfis.hasNext()) {
					clausulaIn += String.valueOf(perfis.next());
					if (perfis.hasNext()) {
						clausulaIn += ",";
					}
				}

				consulta += " and imov.iper_id in ( " + clausulaIn + " )";
			}

			if (consumoMedioAguaInicial != null) {
				consulta += " and consumoAgua.cshi_nnconsumomedio between "
						+ consumoMedioAguaInicial.toString() + " and "
						+ consumoMedioAguaFinal;
			}

			if (consumoMedioEsgotoInicial != null) {
				consulta += " and consumoEsgoto.cshi_nnconsumomedio between "
						+ consumoMedioEsgotoInicial.toString() + " and "
						+ consumoMedioEsgotoFinal;
			}

			if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 1) {
				consulta += " and lagu.hidi_id is not null ";
			} else if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 2) {
				consulta += " and lagu.hidi_id is null ";
			}

			consulta += " order by greg.greg_id, uneg.uneg_id, loca.loca_id, stcm.stcm_cdsetorcomercial, "
					+ " rota.rota_cdrota, imov.imov_nnsequencialrota ";

			retorno = session.createSQLQuery(consulta).addScalar("gerencia",
					Hibernate.INTEGER).addScalar("nomeGerencia",
					Hibernate.STRING).addScalar("unidadeNegocio",
					Hibernate.INTEGER).addScalar("nomeUnidadeNegocio",
					Hibernate.STRING).addScalar("localidade", Hibernate.INTEGER)
						.addScalar("nomeLocalidade", Hibernate.STRING)
						.addScalar("codigoSetor", Hibernate.INTEGER)
						.addScalar("nomeSetor", Hibernate.STRING)
						.addScalar("nomeCliente", Hibernate.STRING)
						.addScalar("ligacaoAguaSituacao", Hibernate.STRING)
						.addScalar("consumoAgua", Hibernate.INTEGER)
						.addScalar("codigoRota", Hibernate.SHORT)
						.addScalar("sequencialRota", Hibernate.INTEGER)
						.addScalar("imovel", Hibernate.INTEGER)
						.addScalar("ligacaoEsgotoSituacao", Hibernate.STRING)
						.addScalar("consumoEsgoto", Hibernate.INTEGER)
						.addScalar( "lote", Hibernate.SHORT)
						.addScalar("subLote", Hibernate.SHORT)
						.addScalar("numeroQuadra", Hibernate.INTEGER)
						.setInteger("anoMesReferencia", anoMesReferencia.intValue())
						.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer consumoMedioAguaInicial = filtro.getConsumoMedioAguaInicial();
		Integer consumoMedioAguaFinal = filtro.getConsumoMedioAguaFinal();
		
		Collection<Integer> colecaoPerfisImovel = filtro.getColecaoPerfisImovel();

		Integer consumoMedioEsgotoInicial = filtro
				.getConsumoMedioEsgotoInicial();
		Integer consumoMedioEsgotoFinal = filtro.getConsumoMedioEsgotoFinal();

		Integer indicadorMedicaoComHidrometro = filtro
				.getIndicadorMedicaoComHidrometro();
		
		Integer anoMesReferencia = filtro.getAnoMesReferencia();

		String consulta = "";

		try {
			consulta = "select count(clim.clim_id) as clienteImo "; 

			consulta += "from cadastro.cliente_imovel clim "
					+ "inner join cadastro.imovel imov on clim.imov_id=imov.imov_id "
					+ "inner join cadastro.localidade loca on imov.loca_id=loca.loca_id "
					+ "inner join cadastro.gerencia_regional greg on loca.greg_id=greg.greg_id "
					+ "inner join cadastro.unidade_negocio uneg on loca.uneg_id=uneg.uneg_id "
					+ "inner join cadastro.setor_comercial stcm on imov.stcm_id=stcm.stcm_id "
					+ "inner join cadastro.quadra qdra on imov.qdra_id=qdra.qdra_id "
					+ "inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao last on imov.last_id=last.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on imov.lest_id=lest.lest_id "
					+ "inner join atendimentopublico.ligacao_agua lagu on imov.imov_id=lagu.lagu_id "
					+

					"left outer join cadastro.logradouro_cep lgcp on imov.lgcp_id=lgcp.lgcp_id "
					+ "left outer join cadastro.logradouro logr on lgcp.logr_id=logr.logr_id "
					+ "left outer join cadastro.logradouro_bairro lgbr on imov.lgbr_id=lgbr.lgbr_id "
					+ "left outer join cadastro.bairro bair on lgbr.bair_id=bair.bair_id "
					+ "inner join cadastro.cliente clie on clim.clie_id=clie.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo crtp on clim.crtp_id=crtp.crtp_id "
					+ "and (crtp.crtp_id = 2 ) "
					+"left join micromedicao.consumo_historico consumoAgua on imov.imov_id=consumoAgua.imov_id "
					+ "and (consumoAgua.lgti_id = 1 ) and (consumoAgua.cshi_amfaturamento = :anoMesReferencia ) "
					+ "left join micromedicao.consumo_historico consumoEsgoto on imov.imov_id=consumoEsgoto.imov_id "
					+ "and (consumoEsgoto.lgti_id = 2 ) and (consumoEsgoto.cshi_amfaturamento = :anoMesReferencia ) ";

			consulta += "where clim.clim_dtrelacaofim is null ";
			

			if (unidadeNegocio != null) {
				consulta += " and uneg.uneg_id = " + unidadeNegocio.toString();
			}

			if (gerencia != null) {
				consulta += " and greg.greg_id = " + gerencia.toString();
			}

			if (localidadeInicial != null) {
				consulta += " and loca.loca_id between "
						+ localidadeInicial.toString() + " and "
						+ localidadeFinal.toString();
			}

			if (setorComercialInicial != null) {
				consulta += " and stcm.stcm_cdsetorcomercial between "
						+ setorComercialInicial.toString() + " and "
						+ setorComercialFinal.toString();
			}

			if (rotaInicial != null) {
				consulta += " and rota.rota_cdrota between "
						+ rotaInicial.toString() + " and "
						+ rotaFinal.toString();
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial.toString() + " and "
						+ sequencialRotaFinal;
			}

			if (consumoMedioAguaInicial != null) {
				consulta += " and consumoAgua.cshi_nnconsumomedio between "
						+ consumoMedioAguaInicial.toString() + " and "
						+ consumoMedioAguaFinal;
			}
			
			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {

				Iterator perfis = colecaoPerfisImovel.iterator();

				String perfisImovel = "";
				
				while (perfis.hasNext()) {
					perfisImovel += perfis.next();
					if (perfis.hasNext()) {
						perfisImovel += ", ";
					}
				}
				
				consulta += " and imov.iper_id in ( " + perfisImovel + " )";
			}

			if (consumoMedioEsgotoInicial != null) {
				consulta += " and consumoEsgoto.cshi_nnconsumomedio between "
						+ consumoMedioEsgotoInicial.toString() + " and "
						+ consumoMedioEsgotoFinal;
			}

			if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 1) {
				consulta += " and lagu.hidi_id is not null ";
			} else if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 2) {
				consulta += " and lagu.hidi_id is null ";
			}

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"clienteImo", Hibernate.INTEGER).setInteger(
					"anoMesReferencia", anoMesReferencia.intValue())
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfil = filtro.getPerfilImovel();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select imovel.id, " // 0
					+ " gerenciaRegional.id, " // 1
					+ " gerenciaRegional.nome, "// 2
					+ " unidadeNegocio.id," // 3
					+ " unidadeNegocio.nome," // 4
					+ " localidade.id, " // 5
					+ " localidade.descricao, " // 6
					+ " setorComercial.codigo, "// 7
					+ " setorComercial.descricao, "// 8
					+ " quadra.numeroQuadra, " // 9
					+ " cliente.nome, " // 10
					+ " ligacaoAguaSituacao.descricao, " // 11
					+ " ligacaoEsgotoSituacao.descricao, " // 12
					+ " rota.codigo," // 13
					+ " imovel.numeroSequencialRota, " // 14
					+ " imovel.lote, " // 15
					+ " imovel.subLote, " // 16
					+ " setorComercial.id, "// 17
					+ " rota.id, " // 18
					+ " imovel.quantidadeEconomias " // 19
					+ " from ClienteImovel clienteImovel,"
					+ " ImovelSubcategoria imovelSubcateg"
					+ " left join imovelSubcateg.comp_id.subcategoria subcateg"
					+ " left join subcateg.categoria categ"
					+ " left join imovelSubcateg.comp_id.imovel imovelCateg"
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.imovelPerfil perfil "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where imovelCateg.id = clienteImovel.imovel.id "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAguaSituacao.id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta + " and categ.id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}

			if (colecaoPerfil != null && colecaoPerfil.size() > 0){
				consulta = consulta + " and perfil.id in (:colecaoPerfil) ";
				parameters.put("colecaoPerfil", colecaoPerfil);
			}
			
			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			consulta += " order by gerenciaRegional.id,unidadeNegocio.id,localidade.id,"
					+ "setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote,"
					+ "rota.codigo,imovel.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfilImovel = filtro.getPerfilImovel();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select count(*) "
					+ " from ClienteImovel clienteImovel,"
					+ " ImovelSubcategoria imovelSubcateg"
					+ " left join imovelSubcateg.comp_id.subcategoria subcateg"
					+ " left join subcateg.categoria categ"
					+ " left join imovelSubcateg.comp_id.imovel imovelCateg"
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join imovel.imovelPerfil perfil "
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where imovelCateg.id = clienteImovel.imovel.id "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAguaSituacao.id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta + " and categ.id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}
			
			if (colecaoPerfilImovel != null && colecaoPerfilImovel.size() > 0) {
				consulta = consulta + " and perfil.id in (:colecaoPerfilImovel) ";
				parameters.put("colecaoPerfilImovel", colecaoPerfilImovel);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select imovel.id, " // 0
					+ " gerenciaRegional.id, " // 1
					+ " gerenciaRegional.nome, "// 2
					+ " unidadeNegocio.id," // 3
					+ " unidadeNegocio.nome," // 4
					+ " localidade.id, " // 5
					+ " localidade.descricao, " // 6
					+ " setorComercial.codigo, "// 7
					+ " setorComercial.descricao, "// 8
					+ " quadra.numeroQuadra, " // 9
					+ " cliente.nome, " // 10
					+ " ligacaoAguaSituacao.descricao, " // 11
					+ " ligacaoEsgotoSituacao.descricao, " // 12
					+ " rota.codigo," // 13
					+ " imovel.numeroSequencialRota, " // 14
					+ " imovel.lote, " // 15
					+ " imovel.subLote, " // 16
					+ " setorComercial.id, "// 17
					+ " rota.id " // 18
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " inner join imovel.ligacaoAgua ligacaoAgua"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua1,:situacaoAgua2) "
					+ " and ligacaoAgua.hidrometroInstalacaoHistorico is null"
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null "
					+ " and imovel.indicadorExclusao <> :indicadorExclusao";

			parameters.put("situacaoAgua1", LigacaoAguaSituacao.LIGADO);
			parameters.put("situacaoAgua2", LigacaoAguaSituacao.CORTADO);
			parameters.put("indicadorExclusao", Imovel.IMOVEL_EXCLUIDO);

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			consulta += " order by gerenciaRegional.id,unidadeNegocio.id,localidade.id,"
					+ "setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote,"
					+ "rota.codigo,imovel.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select count(*) "
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua1,:situacaoAgua2) "
					+ " and imovel.hidrometroInstalacaoHistorico is null"
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null "
					+ " and imovel.indicadorExclusao <> :indicadorExclusao";

			parameters.put("situacaoAgua1", LigacaoAguaSituacao.LIGADO);
			parameters.put("situacaoAgua2", LigacaoAguaSituacao.CORTADO);
			parameters.put("indicadorExclusao", Imovel.IMOVEL_EXCLUIDO);

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();

		Integer referenciaDiaInicial = filtro.getReferenciaFaturasDiaInicial();
		Integer referenciaDiaFinal = filtro.getReferenciaFaturasDiaFinal();

		Integer referenciaAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "select "
					+ "imoveis_em_dia.imov_id as idImovel, " // 0
					+ "gerencia.greg_id as idGerencia, " // 1
					+ "gerencia.greg_nmregional as nomeGerencia, " // 2
					+ "unidade.uneg_id as idUnidade, " // 3
					+ "unidade.uneg_nmunidadenegocio as nomeUnidade, " // 4
					+ "local.loca_id as idLocalidade, " // 5
					+ "local.loca_nmlocalidade as nomeLocalidade, " // 6
					+ "setor.stcm_cdsetorcomercial as codigoSetor, "// 7
					+ "setor.stcm_nmsetorcomercial as nomeSetor, " // 8
					+ "qua.qdra_nnquadra as numeroQuadra, " // 9
					+ "cli.clie_nmcliente as nomeCliente, " // 10
					+ "ligacaoAgua.last_dsligacaoaguasituacao as situacaoAgua, " // 11
					+ "ligacaoEsgoto.lest_dsligacaoesgotosituacao as situacaoEsgoto, " // 12
					+ "rot.rota_cdrota as codigoRota, " // 13
					+ "imov.imov_nnsequencialrota as sequenciaRota, " // 14
					+ "imov.imov_nnlote as numeroLote, " // 15
					+ "imov.imov_nnsublote as numeroSubLote, " // 16
					+ "setor.stcm_id as idSetor, " // 17
					+ "rot.rota_id as idRota, " // 18
					+ "imov.imov_qteconomia as qtdEconomia "// 19
					+ "from "
					+ "( select i.imov_id from "
					+ "cadastro.imovel i "
					+ "where not exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaDiaInicial
					+ " and "
					+ referenciaDiaFinal
					+ " and "
					+ "contaAtual.imov_id = i.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "
					+ ") imoveis_em_dia "

					+ "inner join cadastro.imovel imov on imov.imov_id = imoveis_em_dia.imov_id "
					+ "inner join cadastro.cliente_imovel cliImovel on cliImovel.imov_id = imov.imov_id "
					+ "inner join cadastro.cliente cli on cli.clie_id = cliImovel.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo relacaoTipo on relacaoTipo.crtp_id = cliImovel.crtp_id "
					+ "inner join cadastro.localidade local on local.loca_id = imov.loca_id "
					+ "inner join cadastro.setor_comercial setor on setor.stcm_id = imov.stcm_id "
					+ "inner join cadastro.quadra qua on qua.qdra_id = imov.qdra_id "
					+ "inner join micromedicao.rota rot on rot.rota_id = qua.rota_id "
					+ "inner join cadastro.unidade_negocio unidade on unidade.uneg_id = local.uneg_id "
					+ "inner join cadastro.gerencia_regional gerencia on gerencia.greg_id = local.greg_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao ligacaoAgua on ligacaoAgua.last_id = imov.last_id "
					+ "left join atendimentopublico.ligacao_esgoto_situacao ligacaoEsgoto on ligacaoEsgoto.lest_id = imov.lest_id ";

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ "inner join cadastro.imovel_subcategoria imovelSubCat on imovelSubCat.imov_id = imov.imov_id "
						+ "inner join cadastro.subcategoria subCat on subCat.scat_id = imovelSubCat.scat_id ";
			}

			consulta = consulta
					+ "where exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaAtrasoInicial
					+ " and "
					+ referenciaAtrasoFinal
					+ " and "
					+ "contaAtual.imov_id = imoveis_em_dia.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "

					+ "and relacaoTipo.crtp_id = :idRelacaoTipo "
					+ "and cliImovel.clim_dtrelacaofim is null ";

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAgua.last_id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ " and subCat.catg_id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidade.uneg_id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta + " and gerencia.greg_id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and local.loca_id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta
						+ " and setor.stcm_cdsetorcomercial between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rot.rota_cdrota between "
						+ rotaInicial + " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			consulta += " order by idGerencia,idUnidade,idLocalidade,"
					+ "codigoSetor,numeroQuadra,numeroLote,numeroSubLote,"
					+ "codigoRota,sequenciaRota";

			query = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("nomeGerencia",
					Hibernate.STRING).addScalar("idUnidade", Hibernate.INTEGER)
					.addScalar("nomeUnidade", Hibernate.STRING).addScalar(
							"idLocalidade", Hibernate.INTEGER).addScalar(
							"nomeLocalidade", Hibernate.STRING).addScalar(
							"codigoSetor", Hibernate.INTEGER).addScalar(
							"nomeSetor", Hibernate.STRING).addScalar(
							"numeroQuadra", Hibernate.INTEGER).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar(
							"situacaoAgua", Hibernate.STRING).addScalar(
							"situacaoEsgoto", Hibernate.STRING).addScalar(
							"codigoRota", Hibernate.SHORT).addScalar(
							"sequenciaRota", Hibernate.INTEGER).addScalar(
							"numeroLote", Hibernate.SHORT).addScalar(
							"numeroSubLote", Hibernate.SHORT).addScalar(
							"idSetor", Hibernate.INTEGER).addScalar("idRota",
							Hibernate.INTEGER).addScalar("qtdEconomia",
							Hibernate.SHORT);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();

		Integer referenciaDiaInicial = filtro.getReferenciaFaturasDiaInicial();
		Integer referenciaDiaFinal = filtro.getReferenciaFaturasDiaFinal();

		Integer referenciaAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "select count(*) as quantidade "
					+ "from "
					+ "( select i.imov_id from "
					+ "cadastro.imovel i "
					+ "where exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaDiaInicial
					+ " and "
					+ referenciaDiaFinal
					+ " and "
					+ "contaAtual.imov_id = i.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "
					+ ") as imoveis_em_dia "

					+ "inner join cadastro.imovel imov on imov.imov_id = imoveis_em_dia.imov_id "
					+ "inner join cadastro.cliente_imovel cliImovel on cliImovel.imov_id = imov.imov_id "
					+ "inner join cadastro.cliente cli on cli.clie_id = cliImovel.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo relacaoTipo on relacaoTipo.crtp_id = cliImovel.crtp_id "
					+ "inner join cadastro.localidade local on local.loca_id = imov.loca_id "
					+ "inner join cadastro.setor_comercial setor on setor.stcm_id = imov.stcm_id "
					+ "inner join cadastro.quadra qua on qua.qdra_id = imov.qdra_id "
					+ "inner join micromedicao.rota rot on rot.rota_id = qua.rota_id "
					+ "inner join cadastro.unidade_negocio unidade on unidade.uneg_id = local.uneg_id "
					+ "inner join cadastro.gerencia_regional gerencia on gerencia.greg_id = local.greg_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao ligacaoAgua on ligacaoAgua.last_id = imov.last_id "
					+ "left join atendimentopublico.ligacao_esgoto_situacao ligacaoEsgoto on ligacaoEsgoto.lest_id = imov.lest_id ";

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ "inner join cadastro.imovel_subcategoria imovelSubCat on imovelSubCat.imov_id = imov.imov_id "
						+ "inner join cadastro.subcategoria subCat on subCat.scat_id = imovelSubCat.scat_id ";
			}

			consulta = consulta
					+ "where not exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaAtrasoInicial
					+ " and "
					+ referenciaAtrasoFinal
					+ " and "
					+ "contaAtual.imov_id = imoveis_em_dia.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "

					+ "and relacaoTipo.crtp_id = :idRelacaoTipo "
					+ "and cliImovel.clim_dtrelacaofim is null ";

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAgua.last_id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ " and subCat.catg_id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidade.uneg_id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta + " and gerencia.greg_id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and local.loca_id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta
						+ " and setor.stcm_cdsetorcomercial between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rot.rota_cdrota between "
						+ rotaInicial + " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			query = session.createSQLQuery(consulta).addScalar("quantidade",
					Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisInicial = filtro.getReferenciaInicial();
		Integer referenciaImoveisFinal = filtro.getReferenciaFinal();

		Collection<Integer> colecaoTiposConsumo = filtro.getTiposConsumo();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select \n" +
			// Gerencia Regional id - Indice 0
					"   gr.id, \n" +
					// Gerencia Regional nome - Indice 1
					"   gr.nome, \n" +
					// Unidade Neg�cio id - Indice 2
					"   un.id, \n" +
					// Unidade Neg�cio nome - Indice 3
					"   un.nome, \n" +
					// Localidade id - Indice 4
					"   loca.id, \n" +
					// Localidade descricao - Indice 5
					"   loca.descricao, \n" +
					// Setor Comercial codigo - Indice 6
					"   sc.codigo, \n" +
					// Setor Comercial descricao - Indice 7
					"   sc.descricao, \n" +
					// Imovel id - Indice 8
					"   imo.id, \n" +
					// Cliente nome- Indice 9
					"   cl.nome, \n" +
					// Ligacao Agua Situacao Descricao - Indice 10
					"   las.descricao, \n" +
					// Consumo Tipo descricao - Indice 11
					"   ct.descricao, \n" +
					// Rota codigo - Indice 12
					"   rt.codigo, \n" +
					// Numero do Sequencial da Rota - Indice 13
					"   imo.numeroSequencialRota, \n" +
					// Ligacao Esgoto Situacao - Indice 14
					"   les.descricao, \n" +
					// Numero da Quadra - Indice 15
					"   qua.numeroQuadra, \n" +
					// Lote - Indice 16
					"   imo.lote, \n" +
					// Sublote - Indice 17
					" 	imo.subLote, \n" +
					// Referencia - Indice 18
					"   ch.referenciaFaturamento \n" + " from \n"
					+ "   ConsumoHistorico ch \n"
					+ "   inner join ch.imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join ch.consumoTipo ct \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les, \n"
					+ "   ClienteImovel ci \n"
					+ "   inner join ci.cliente cl \n" + " where \n"
					+ "   ci.imovel.id = imo.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO + " and \n"
					+ "   ci.dataFimRelacao is null \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.codigo between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (referenciaImoveisInicial != null) {
				consulta += " and ch.referenciaFaturamento between :amInicial and :amFinal \n";

				parameters.put("amInicial", referenciaImoveisInicial);
				parameters.put("amFinal", referenciaImoveisFinal);
			}

			if (colecaoTiposConsumo != null && colecaoTiposConsumo.size() > 0) {
				consulta += " and ch.consumoTipo.id in (:tiposConsumo) \n";
				parameters.put("tiposConsumo", colecaoTiposConsumo);
			}

			consulta += " order by \n " + "   gr.id, \n " + "   un.id, \n "
					+ "   loca.id, \n " + "   sc.codigo, \n "
					+ "   rt.codigo, \n " + "   imo.numeroSequencialRota, \n "
					+ "   imo.id";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Imovel> pesquisarImovelArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper objeto)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = objeto.getLocalidadeInicial();
		Integer localidadeFinal = objeto.getLocalidadeFinal();

		Integer setorComercialInicial = objeto.getSetorComercialInicial();
		Integer setorComercialFinal = objeto.getSetorComercialFinal();

		Integer rotaInicial = objeto.getRotaInicial();
		Integer rotaFinal = objeto.getRotaFinal();

		Integer sequencialRotaInicial = objeto.getSequencialRotalInicial();
		Integer sequencialRotaFinal = objeto.getSequencialRotalFinal();

		Integer unidadeNegocio = objeto.getUnidadeNegocio();
		Integer gerencia = objeto.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select imo " + " from \n" + "   Imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   left join imo.ligacaoEsgoto le \n"
					+ "   left join le.ligacaoEsgotoPerfil le \n" + " where \n"
					+ "   imo.indicadorExclusao = 2 \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.codigo between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			consulta += " order by \n " + "   gr.id, \n " + "   un.id, \n "
					+ "   loca.id, \n " + "   sc.id, \n " + "   imo.id, \n "
					+ "   rt.codigo, \n " + "   imo.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<HidrometroInstalacaoHistorico> pesquisarImovelArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper objeto)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = objeto.getLocalidadeInicial();
		Integer localidadeFinal = objeto.getLocalidadeFinal();

		Integer setorComercialInicial = objeto.getSetorComercialInicial();
		Integer setorComercialFinal = objeto.getSetorComercialFinal();

		Integer rotaInicial = objeto.getRotaInicial();
		Integer rotaFinal = objeto.getRotaFinal();

		Integer sequencialRotaInicial = objeto.getSequencialRotalInicial();
		Integer sequencialRotaFinal = objeto.getSequencialRotalFinal();

		Integer unidadeNegocio = objeto.getUnidadeNegocio();
		Integer gerencia = objeto.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select iih " + " from \n"
					+ "   HidrometroInstalacaoHistorico iih , Imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   left join fetch iih.hidrometro hd \n"
					+ "   left join fetch iih.hidrometroLocalInstalacao hli \n"
					+ "   left join fetch hd.hidrometroCapacidade hc \n"
					+ " where \n" + "   imo.indicadorExclusao = 2 \n"
					+ "   and las.id in (3,5) and iih.dataRetirada is null \n"
					+ "   and iih.ligacaoAgua.id = imo.id \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.codigo between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			consulta += " order by \n " + "   gr.id, \n " + "   un.id, \n "
					+ "   loca.id, \n " + "   sc.id, \n " + "   imo.id, \n "
					+ "   rt.codigo, \n " + "   imo.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisInicial = filtro.getReferenciaInicial();
		Integer referenciaImoveisFinal = filtro.getReferenciaFinal();

		Collection<Integer> colecaoTiposConsumo = filtro.getTiposConsumo();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select \n" + "   count(*) \n" + " from \n"
					+ "   ConsumoHistorico ch \n"
					+ "   inner join ch.imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join ch.consumoTipo ct \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les, \n"
					+ "   ClienteImovel ci \n"
					+ "   inner join ci.cliente cl \n" + " where \n"
					+ "   ci.imovel.id = imo.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO + " and \n"
					+ "   ci.dataFimRelacao is null \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.id between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.id between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
			}

			if (referenciaImoveisInicial != null) {
				consulta += " and ch.referenciaFaturamento between :amInicial and :amFinal \n";

				parameters.put("amInicial", referenciaImoveisInicial);
				parameters.put("amFinal", referenciaImoveisFinal);
			}

			if (colecaoTiposConsumo != null && colecaoTiposConsumo.size() > 0) {
				consulta += " and ch.consumoTipo.id in (:tiposConsumo) \n";
				parameters.put("tiposConsumo", colecaoTiposConsumo);
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public Object[] pesquisarDadosRotaEntregaContaPorRota(Integer idRota)
			throws ErroRepositorioException {

		Object[] retorno = null;

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT local.id," + "setor.codigo," + "rot.codigo "
					+ "FROM Rota rot "
					+ "LEFT JOIN FETCH rot.setorComercial setor "
					+ "LEFT JOIN FETCH setor.localidade local "
					+ "WHERE rot.id = :idRota ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRota", idRota).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarSetorComercialPorQualidadeAgua(
			int tipoArgumento, BigDecimal indiceInicial,
			BigDecimal indiceFinal, Integer anoMesReferencia)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {

			consulta = "SELECT DISTINCT(loca.id), loca.descricao, stcm "
					+ "FROM QualidadeAgua qlag "
					+ "LEFT JOIN qlag.localidade loca "
					+ "LEFT JOIN qlag.setorComercial stcm "
					+ "WHERE qlag.anoMesReferencia = :anoMesReferencia ";

			switch (tipoArgumento) {

			case ConstantesSistema.TURBIDEZ:

				consulta += "AND qlag.numeroIndiceTurbidez BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.CLORO:

				consulta += "AND qlag.numeroCloroResidual BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.PH:

				consulta += "AND qlag.numeroIndicePh BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.COR:

				consulta += "AND qlag.numeroIndiceCor BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.FLUOR:

				consulta += "AND qlag.numeroIndiceFluor BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.FERRO:

				consulta += "AND qlag.numeroIndiceFerro BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.COLIFORMES_TOTAIS:

				consulta += "AND qlag.numeroIndiceColiformesTotais BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.COLIFORMES_FECAIS:

				consulta += "AND qlag.numeroIndiceColiformesFecais BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			default:

				consulta += "AND qlag.numeroNitrato BETWEEN :indiceInicial AND :indiceFinal ";
				break;
			}

			consulta += "ORDER BY loca.descricao, stcm.descricao";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setBigDecimal(
					"indiceInicial", indiceInicial).setBigDecimal(
					"indiceFinal", indiceFinal).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public ImovelAtualizacaoCadastral obterImovelGeracaoTabelasTemporarias(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		StringBuilder consulta;
		ImovelAtualizacaoCadastral retorno = null;

		Query query = null;

		try {

			consulta = new StringBuilder("SELECT distinct new gcom.cadastro.imovel.ImovelAtualizacaoCadastral(imov.id as idImovel, ")
			.append(" imov.localidade.id as idLocalidade, ") 
			.append(" stcm.codigo as codigoSetorComercial, ")
			.append(" qdra.numeroQuadra as numeroQuadra, ")
			.append(" imov.lote, ")
			.append(" imov.subLote, ")
			.append(" imov.numeroSequencialRota, ")
			.append(" imov.numeroMorador, ")
			.append(" coalesce(imov.logradouroCep.id, imov.logradouroBairro.id), ")
			.append(" cep.codigo as codigoCep, ")
			.append(" bairro.id as idBairro, ")
			.append(" bairro.nome as descricaoBairro, ")
			.append(" imov.enderecoReferencia.id as enderecoReferencia, ")
			.append(" imov.numeroImovel, ")
			.append(" imov.complementoEndereco, ")
			.append(" imov.areaConstruida, ")
			.append(" imov.ligacaoAguaSituacao.id, ")
			.append(" imov.volumeReservatorioInferior, ")
			.append(" imov.volumeReservatorioSuperior, ") 
			.append(" imov.volumePiscina, ")
			.append(" imov.indicadorJardim, ")
			.append(" imov.pavimentoCalcada.id, ")
			.append(" imov.pavimentoRua.id, ") 
			.append(" imov.fonteAbastecimento.id, ")
			.append(" imov.pocoTipo.id, ")
			.append(" imov.numeroPontosUtilizacao, ") 
			.append(" imov.ligacaoEsgotoSituacao.id, ")
			.append(" imov.imovelPerfil.id, ")
			.append(" imov.despejo.id, ")
			.append(" imov.coordenadaX, ")
			.append(" imov.coordenadaY, ") 
			.append(" imov.imovelPrincipal.id, ")
			.append(" imov.numeroIptu, ")
			.append(" imov.numeroCelpe, ")
			.append(" lagu.ramalLocalInstalacao.id, ")
			.append(" imov.classeSocial, ")
			.append(" imov.quantidadeAnimaisDomesticos, ")
			.append(" imov.volumeCisterna, ")
			.append(" imov.volumeCaixaDagua, ")
			.append(" imov.tipoUso, ")
			.append(" imov.acessoHidrometro, ")
			.append(" imov.quantidadeEconomiasSocial, ")
			.append(" imov.quantidadeEconomiasOutra,")
			.append(" imov.percentualAbastecimento")
			.append(" )")
			.append(" from Imovel imov ")
			.append(" inner join imov.setorComercial stcm ")
			.append(" inner join imov.quadra qdra ")
			.append(" left join  imov.logradouroCep lgcp ")
			.append(" left join  lgcp.cep cep ")
			.append(" left join  imov.logradouroBairro lgbr ")
			.append(" left join  lgbr.bairro bairro ")
			.append(" left join  imov.ligacaoAgua lagu ")
			.append(" where imov.id =:idImovel ");

			query = session.createQuery(consulta.toString())
					.setInteger("idImovel", idImovel);

			retorno = (ImovelAtualizacaoCadastral) query.uniqueResult();

		}catch (NonUniqueResultException e ){
		    retorno = null;
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public Collection obterImovelSubcategoriaAtualizacaoCadastral(
			Integer idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection retornoConsulta = null;
		Collection imovelSubcategorias = new ArrayList();

		try {

			consulta = " select scat.scat_id as idSubcategoria,"
					+ // 0
					" imsb_qteconomia as qteEconomia,"
					+ // 1
					" scat_dssubcategoria as descricaoSubcategoria,"
					+ // 2
					" catg.catg_id as idCategoria,"
					+ // 3
					" catg_dscategoria as descricaoCategoria"
					+ // 4
					" from cadastro.imovel_subcategoria isac"
					+ " inner join cadastro.subcategoria scat on (isac.scat_id = scat.scat_id)"
					+ " inner join cadastro.categoria  catg on (scat.catg_id = catg.catg_id)"
					+ " where imov_id = :idImovel";

			retornoConsulta = session.createSQLQuery(consulta).addScalar(
					"idSubcategoria", Hibernate.INTEGER).addScalar(
					"qteEconomia", Hibernate.SHORT).addScalar(
					"descricaoSubcategoria", Hibernate.STRING).addScalar(
					"idCategoria", Hibernate.INTEGER).addScalar(
					"descricaoCategoria", Hibernate.STRING).setInteger(
					"idImovel", idImovel).list();

			if (retornoConsulta.size() > 0) {
				Iterator imovelSubcategoriaIter = retornoConsulta.iterator();
				while (imovelSubcategoriaIter.hasNext()) {

					Object[] element = (Object[]) imovelSubcategoriaIter.next();

					ImovelSubcategoriaAtualizacaoCadastral imovSubAtual = new ImovelSubcategoriaAtualizacaoCadastral();

					imovSubAtual.setImovel(new Imovel(idImovel));

					imovSubAtual.setSubcategoria(new Subcategoria((Integer) element[0]));

					imovSubAtual.setQuantidadeEconomias((Short) element[1]);

					imovSubAtual.setDescricaoSubcategoria((String) element[2]);

					imovSubAtual.setCategoria(new Categoria((Integer) element[3]));

					imovSubAtual.setDescricaoCategoria((String) element[4]);

					imovelSubcategorias.add(imovSubAtual);
				}
			}

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return imovelSubcategorias;

	}

	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Short rotaInicial = filtro.getRotaInicial();
		Short rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();

		String hidrometro = filtro.getHidrometro();
		
		Integer situacaoCobranca = filtro.getSituacaoCobranca();

		String categoria = "";
		if (!Util.isVazioOrNulo(colecaoCategorias)) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		try {

			String consulta = "";
			Map<String, Object> parameters = new HashMap<String, Object>();

			consulta = " select \n"
					+
					// 0
					"   gr.id, \n"
					+
					// 1
					"   gr.nome, \n"
					+
					// 2
					"   un.id, \n"
					+
					// 3
					"   un.nome, \n"
					+
					// 4
					"   sc.codigo, \n"
					+
					// 5
					"   sc.descricao, \n"
					+
					// 6
					"   loc.id, \n"
					+
					// 7
					"   loc.descricao, \n"
					+
					// 8
					"   cli.nome, \n"
					+
					// 9
					"   las.descricaoAbreviado, \n"
					+
					// 10
					"   rot.codigo, \n"
					+
					// 11
					"   imo.numeroSequencialRota, \n"
					+
					// 12
					"   imo.id, \n"
					+
					// 13
					"   les.id, \n"
					+
					// 14
					"   les.descricaoAbreviado, \n"
					+
					// 15
					"   qua.numeroQuadra, \n"
					+
					// 16
					"   c.referencia as referenciaMinima, \n"
					+
					// 17
					"  sum( ( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) )) as valor, \n"
					+
					// 18
					"   imo.lote, \n" +
					// 19
					"   imo.subLote, \n" +
					// 20
					"   c.dataVencimentoConta, \n" +
					// 21
					"   cli.cpf, \n" +
					// 22
					"   cli.cnpj \n" +

					" from \n" + "   Conta c, ClienteImovel ci  " + categoria
					+ " \n" + "   inner join c.imovel imo \n"
					+ "   inner join imo.localidade loc \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join loc.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoAgua la \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join qua.rota rot \n"
					+ "   inner join ci.cliente cli \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}

			consulta += " where \n"
					+ "   imo.id = ci.imovel.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO
					+ " and \n"
					+ "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (localidadeInicial != null) {
				consulta += " and loc.id between :localidadeInicial and :localidadeFinal";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal ";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += "  and rot.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";
				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			//Consulta com Hidr�metro
			if(hidrometro != null && !hidrometro.equals("0")){
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca)";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and (c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal) ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by gr.id,gr.nome,un.id,un.nome,sc.codigo,sc.descricao,loc.id,loc.descricao,cli.nome,las.descricaoAbreviado,"
					+ " rot.codigo,imo.numeroSequencialRota,imo.id,les.id,les.descricaoAbreviado,qua.numeroQuadra,c.referencia,imo.lote,"
					+ " imo.subLote, c.dataVencimentoConta, c.dataVencimentoConta, cli.cpf, cli.cnpj ";

			consulta += " order by \n" + "   gr.id, \n" + "   un.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   rot.codigo, \n" + "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();

		try {

			Integer clienteSuperior = filtro.getClienteSuperior();
			Integer cliente = filtro.getCliente();
			Integer tipoRelacao = filtro.getTipoRelacao();
			Integer responsavel = filtro.getResponsavel();

			Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
					.getSituacaoLigacaoAgua();
			Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
			Collection<Integer> colecaoCategorias = filtro.getCategorias();
			Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();

			Integer situacaoCobranca = filtro.getSituacaoCobranca();
			
			String hidrometro = filtro.getHidrometro();

			Integer referenciaImoveisFaturasAtrasoInicial = filtro
					.getReferenciaFaturasAtrasoInicial();
			Integer referenciaImoveisFaturasAtrasoFinal = filtro
					.getReferenciaFaturasAtrasoFinal();

			String clienteConta = "";
			if (cliente != null && responsavel != null
					&& !responsavel.equals(1)) {
				clienteConta = " , ClienteConta clienteConta ";
			}

			String categoria = "";
			if (!Util.isVazioOrNulo(colecaoCategorias)) {
				categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
			}

			String consulta = "";

			Map<String, Object> parameters = new HashMap<String, Object>();

			consulta =

			" select \n"
					+
					// 1
					"   cli.id, \n"
					+
					// 2
					"   cli.nome, \n"
					+
					// 3
					"   gr.id, \n"
					+
					// 4
					"   loc.id, \n"
					+
					// 5
					"   sc.codigo, \n"
					+
					// 6
					"   qua.numeroQuadra, \n"
					+
					// 7
					"   rot.codigo, \n"
					+
					// 8
					"   imo.numeroSequencialRota, \n"
					+
					// 9
					"   imo.id, \n"
					+
					// 10
					"   las.descricaoAbreviado, \n"
					+
					// 11
					"   les.descricaoAbreviado, \n"
					+
					// 12
					"   c.id, \n"
					+
					// 13
					"   c.referencia , \n"
					+
					// 14
					"   c.dataVencimentoConta , \n"
					+
					// 15
					"   c.indicadorCobrancaMulta , \n"
					+
					// 16
					"   sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) as totalSemEncargos \n"
					+

					" from Cliente cli " + categoria + clienteConta + " \n"
					+ "   inner join cli.clienteImoveis ci \n"
					+ "   inner join ci.imovel imo \n"
					+ "   inner join imo.ligacaoAgua la \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join imo.localidade loc \n"
					+ "   inner join qua.rota rot \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join imo.contas c \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}

			consulta += " where \n"
					+ "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (!clienteConta.trim().equals("")) {
				consulta += " and clienteConta.conta.id = c.id ";
			}

			if (clienteSuperior != null) {
				consulta += " and ci.clienteRelacaoTipo = "
						+ ClienteRelacaoTipo.RESPONSAVEL;
				consulta += " and ( cli.id = :clienteSuperior or cli.cliente.id = :clienteSuperior2) \n";

				parameters.put("clienteSuperior", clienteSuperior);
				parameters.put("clienteSuperior2", clienteSuperior);
			}

			if (cliente != null && responsavel != null) {
				if (responsavel.equals(0)) {
					consulta += " and clienteConta.cliente.id = cli.id \n";
					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and clienteConta.clienteRelacaoTipo.id = :tipoRelacao \n";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(1)) {
					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ci.clienteRelacaoTipo = :tipoRelacao \n";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(2)) {
					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ( \n";
						consulta += " 	(clienteConta.cliente.id = cli.id  ";
						consulta += " 		and clienteConta.clienteRelacaoTipo.id = :tipoRelacao1 ) \n";
						consulta += " 	or  ";
						consulta += " 	( ci.clienteRelacaoTipo = :tipoRelacao2) ";
						consulta += " )";

						parameters.put("tipoRelacao1", tipoRelacao);
						parameters.put("tipoRelacao2", tipoRelacao);
					}
				}
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder in(:esferaPoder) \n";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) \n";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) \n";
				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			// Consulta com Hidr�metro
			if(hidrometro != null && !hidrometro.equals("0")){
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca) \n ";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			if (referenciaImoveisFaturasAtrasoInicial != null) {
				consulta += " and (c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal) ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by \n" + "   cli.id, \n" + "   cli.nome, \n"
					+ "   gr.id, \n" + "   loc.id, \n" + "   sc.codigo, \n"
					+ "   qua.numeroQuadra, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota, \n" + "   imo.id, \n"
					+ "   las.descricaoAbreviado, \n"
					+ "   les.descricaoAbreviado, \n" + "   imo.subLote, \n"
					+ "	c.id, \n" + "	c.referencia, \n"
					+ " 	c.dataVencimentoConta, \n"
					+ " 	c.indicadorCobrancaMulta \n";

			consulta += " order by \n" + "   cli.id, \n" + "   gr.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   qua.numeroQuadra, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	private Query criarQueryComParametros(String consulta,
			Map<String, Object> parameters, Session session) {

		Query query = session.createQuery(consulta);

		Set<String> set = parameters.keySet();
		Iterator<String> iterMap = set.iterator();
		while (iterMap.hasNext()) {
			String key = iterMap.next();
			if (parameters.get(key) instanceof Collection) {
				Collection<? extends Object> collection = (ArrayList<? extends Object>) parameters
						.get(key);
				query.setParameterList(key, collection);
			} else {
				query.setParameter(key, parameters.get(key));
			}
		}

		return query;
	}

	public Collection<NacionalFeriado> pesquisarFeriadosNacionais(
			String anoOrigemFeriado) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "from " + "  NacionalFeriado " + "where "
					+ "  to_char( data, 'yyyy' ) = :ano";

			retorno = session.createQuery(consulta).setString("ano",
					anoOrigemFeriado).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public Collection<MunicipioFeriado> pesquisarFeriadosMunicipais(
			String anoOrigemFeriado) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "from " + "  MunicipioFeriado " + "where "
					+ "  to_char( dataFeriado, 'yyyy' ) = :ano";

			retorno = session.createQuery(consulta).setString("ano",
					anoOrigemFeriado).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public void excluirFeriadosNacionais(String anoDestino)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "delete " + "from " + "  NacionalFeriado " + "where "
					+ "  to_char( data, 'yyyy' ) = :ano";

			session.createQuery(consulta).setString("ano", anoDestino)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void excluirFeriadosMunicipais(String anoDestino)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "delete " + "from " + "  MunicipioFeriado " + "where "
					+ "  to_char( dataFeriado, 'yyyy' ) = :ano";

			session.createQuery(consulta).setString("ano", anoDestino)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Collection pesquisarLocalidades() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Collection retorno = null;
		try {
			consulta = "select DISTINCT loca.id " + "from Localidade loca ";

			retorno = session.createQuery(consulta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Collection pesquisarTodosIdsDmc() throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "select distinct dm.dmc_id id, dmc_descr descricao from cadastro.dmc dm "
						+ "inner join cadastro.quadra_face qf on qf.dmc_id = dm.dmc_id  "
						+ "where qf.dmc_id is not null";
						
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING)
					.list();
		

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public List<ArquivoTextoAtualizacaoCadastral> pesquisarArquivoTextoAtualizacaoCadastro(String idEmpresa, String idLocalidade,
			String codigoSetorComercial, String idAgenteComercial, String idSituacaoTransmissao, String exibicao) throws ErroRepositorioException {

		List<ArquivoTextoAtualizacaoCadastral> retorno = new ArrayList<ArquivoTextoAtualizacaoCadastral>();
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "SELECT txac, "
							+ "(select count(distinct im.idImovel) from ImovelAtualizacaoCadastral im "
								+ " inner join im.imovelControleAtualizacaoCadastral ic "
								+ "	WHERE im.idArquivoTexto = txac.id "
								+ "	and ic.situacaoAtualizacaoCadastral.id >= " + SituacaoAtualizacaoCadastral.TRANSMITIDO + "), "
							+ "(select count(distinct im.idImovel) from ImovelAtualizacaoCadastral im "
								+ " inner join im.imovelControleAtualizacaoCadastral ic "
								+ "	WHERE im.idArquivoTexto = txac.id "
								+ "	and ic.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.EM_CAMPO + "), "
							+ "(select count(distinct im.idImovel) from ImovelAtualizacaoCadastral im "
								+ " inner join im.imovelControleAtualizacaoCadastral ic "
								+ " inner join ic.cadastroOcorrencia oc "
								+ "	WHERE im.idArquivoTexto = txac.id "
								+ " and oc.indicadorValidacao = 2 "
								+ " and 3 > (select count(v.id) from Visita v where v.imovelControleAtualizacaoCadastral = ic and v.indicadorExclusao = false ) "
								+ "	and ic.situacaoAtualizacaoCadastral.id IN (" + SituacaoAtualizacaoCadastral.EM_CAMPO
									+ ", " + SituacaoAtualizacaoCadastral.REVISITA + ")), "
							+ "(select count(distinct im.idImovel) from ImovelAtualizacaoCadastral im "
								+ " inner join im.imovelControleAtualizacaoCadastral ic "
								+ "	WHERE im.idArquivoTexto = txac.id "
								+ "	and ic.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.EM_REVISAO + "), "
							+ "(select count(distinct im.idImovel) from ImovelAtualizacaoCadastral im "
								+ " inner join im.imovelControleAtualizacaoCadastral ic "
								+ "	WHERE im.idArquivoTexto = txac.id "
								+ "	and ic.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.EM_FISCALIZACAO + "), "
							+ "(select count(distinct im.idImovel) from ImovelAtualizacaoCadastral im "
								+ " inner join im.imovelControleAtualizacaoCadastral ic "
								+ "	WHERE im.idArquivoTexto = txac.id "
								+ "	and ic.situacaoAtualizacaoCadastral.id >= " + SituacaoAtualizacaoCadastral.DISPONIVEL + ") "
							+ "FROM ArquivoTextoAtualizacaoCadastral txac "
							+ "INNER JOIN FETCH txac.localidade localidade "
							+ "INNER JOIN FETCH txac.rota rota "
							+ "INNER JOIN FETCH rota.setorComercial setorComercial "
							+ "INNER JOIN FETCH txac.situacaoTransmissaoLeitura situacao "
							+ "INNER JOIN FETCH txac.leiturista leiturista "
							+ "LEFT JOIN FETCH leiturista.cliente cliente "
							+ "LEFT JOIN FETCH leiturista.funcionario funcionario "
							+ "WHERE leiturista.empresa.id = " + idEmpresa;

			if (idLocalidade != null && !idLocalidade.equals("")) {
				consulta += " and txac.localidade.id = " + idLocalidade;
			}
			
			if (codigoSetorComercial != null && !codigoSetorComercial.equals("")) {
				consulta += " and txac.codigoSetorComercial = " + codigoSetorComercial;
			}

			if (idAgenteComercial != null && !idAgenteComercial.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				consulta += " and leiturista.id = " + idAgenteComercial;
			}

			if (idSituacaoTransmissao != null && !idSituacaoTransmissao.equals("")) {
				consulta += " and situacao.id = " + idSituacaoTransmissao;
			}
			
			if (exibicao.equals(ArquivoTextoAtualizacaoCadastral.EXIBIR_EM_REVISAO)) {
				consulta += " and txac.id IN (SELECT distinct imovel.idArquivoTexto FROM ImovelAtualizacaoCadastral imovel "
						+ " inner join imovel.imovelControleAtualizacaoCadastral ic2 "
						+ " WHERE ic2.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.EM_REVISAO + ")";
			}

			consulta += " order by localidade.id, setorComercial.codigo, rota.codigo, txac.id ";

			List<Object[]> resultado = session.createQuery(consulta).list();
			if (resultado != null && !resultado.isEmpty()) {
				for (Object[] array : resultado) {
					ArquivoTextoAtualizacaoCadastral arquivo = (ArquivoTextoAtualizacaoCadastral) array[0];
					arquivo.setQuantidadeImoveisTransmitidos((Integer) array[1]);
					arquivo.setQuantidadeEmCampo((Integer) array[2]);
					arquivo.setQuantidadeRevisita((Integer) array[3]);
					arquivo.setQuantidadeRevisao((Integer) array[4]);
					arquivo.setQuantidadeEmFiscalizacao((Integer) array[5]);
					arquivo.setQuantidadeDisponivel((Integer) array[6]);
					retorno.add(arquivo);
				}
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(Integer idArquivoTxt) throws ErroRepositorioException {
		ArquivoTextoAtualizacaoCadastral retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT txac FROM ArquivoTextoAtualizacaoCadastral txac " 
							+ "INNER JOIN FETCH txac.leiturista leit "
							+ "INNER JOIN FETCH txac.rota rota "
							+ "INNER JOIN FETCH rota.setorComercial setor "
							+ "INNER JOIN FETCH setor.localidade localidade "
							+ "INNER JOIN FETCH rota.faturamentoGrupo grupo "
							+ "WHERE txac.id = " + idArquivoTxt;

			retorno = (ArquivoTextoAtualizacaoCadastral) session.createQuery(consulta).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(
			String descricao)
			throws ErroRepositorioException {

		ArquivoTextoAtualizacaoCadastral retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " select txac"
					+ " from ArquivoTextoAtualizacaoCadastral txac"
					+ " inner join fetch txac.leiturista leit"
					+ " where txac.descricaoArquivo = :descricao";

			retorno = (ArquivoTextoAtualizacaoCadastral) session.createQuery(
					consulta).setString("descricao", descricao).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Collection<ArquivoTextoAtualizacaoCadastral> pesquisarArquivoTextoAtualizacaoCadastro(
			String[] idsArquivoTxt) throws ErroRepositorioException {

		Collection<ArquivoTextoAtualizacaoCadastral> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " select txac"// 2
					+ " from ArquivoTextoAtualizacaoCadastral txac"
					+ " inner join fetch txac.leiturista leit"
					+ " where txac.id in (:ids)";

			retorno = (Collection<ArquivoTextoAtualizacaoCadastral>) session.createQuery(consulta)
					.setParameterList("ids", idsArquivoTxt)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt, Integer idSituacaoTransmissao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		
		String consulta = " update ArquivoTextoAtualizacaoCadastral txac"
				+ " set txac.situacaoTransmissaoLeitura.id =:idSituacaoTransmissao,"
				+ " txac.ultimaAlteracao = :dataAtual" + " where txac.id = "
				+ idArquivoTxt;

		try {

			session.createQuery(consulta).setInteger("idSituacaoTransmissao",
					idSituacaoTransmissao)
					.setTimestamp("dataAtual", new Date()).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Collection<Integer> obterIdsImovelGeracaoTabelasTemporarias(
			Integer idRota,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Integer> retorno = null;

		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "SELECT distinct(imov.imov_id) as idImovel "
					+ "from cadastro.imovel imov "
					+ "LEFT JOIN cadastro.quadra qdra ON qdra.qdra_id = imov.qdra_id ";

			if (helper.getCliente() != null) {
				consulta += "LEFT JOIN cadastro.cliente_imovel clim ON clim.imov_id = imov.imov_id ";
			}

			if (helper.getLocalidadeInicial() != null
					|| helper.getElo() != null) {
				consulta += "LEFT JOIN cadastro.localidade local ON local.loca_id = imov.loca_id ";
			}

			if (helper.getCategoria() != null
					|| helper.getSubCategoria() != null) {
				consulta += "LEFT JOIN cadastro.imovel_subcategoria imsb ON imsb.imov_id = imov.imov_id "
					+ "LEFT JOIN cadastro.subcategoria scat ON scat.scat_id = imsb.scat_id ";
			}

			consulta += "where imov.imov_icexclusao <> 1 AND (imov.siac_id is null OR imov.siac_id = 0  OR imov.siac_id = 16) AND "
						+ " imov.last_id <> " + LigacaoAguaSituacao.POTENCIAL + " AND ";

			if (idRota != null) {
				consulta += "qdra.rota_id =:idRota AND ";
				parameters.put("idRota", idRota);
			}

			if (helper.getMatricula() != null) {
				consulta += "imov.imov_id = :matricula AND ";
				parameters.put("matricula", helper.getMatricula());
			}

			if (helper.getCliente() != null) {
				consulta += "clim.clie_id = :cliente AND ";
				parameters.put("cliente", helper.getCliente());
			}

			if (helper.getElo() != null) {
				consulta += "local.loca_cdelo = :elo AND ";
				parameters.put("elo", helper.getElo());
			}

			if (helper.getLocalidadeInicial() != null) {
				consulta += " local.loca_id between :localidadeInicial AND :localidadeFinal AND ";
				parameters.put("localidadeInicial", helper.getLocalidadeInicial());
				parameters.put("localidadeFinal", helper.getLocalidadeFinal());
			}

			if (helper.getSetorComercialInicial() != null) {
				consulta += " imov.stcm_id between :setorComercialInicial AND :setorComercialFinal AND ";
				parameters.put("setorComercialInicial", helper.getSetorComercialInicial());
				parameters.put("setorComercialFinal", helper.getSetorComercialFinal());

			}

			if (helper.getIdQuadraInicial() != null) {
				consulta += " imov.qdra_id between :quadraInicial AND :quadraFinal AND ";
				parameters.put("quadraInicial", helper.getIdQuadraInicial());
				parameters.put("quadraFinal", helper.getIdQuadraFinal());
			}

			if (helper.getRotaInicial() != null) {
				consulta += " qdra.rota_id between :rotaInicial AND :rotaFinal AND ";
				parameters.put("rotaInicial", helper.getRotaInicial());
				parameters.put("rotaFinal", helper.getRotaFinal());
			}

			if (helper.getRotaSequenciaInicial() != null) {
				consulta += " imov.imov_nnsequencialrota between :rotaSequenciaInicial AND :rotaSequenciaFinal AND ";
				parameters.put("rotaSequenciaInicial", helper.getRotaSequenciaInicial());
				parameters.put("rotaSequenciaFinal", helper.getRotaSequenciaFinal());
			}

			if (helper.getPerfilImovel() != null) {
				consulta += "imov.iper_id = :perfilImovel AND ";
				parameters.put("perfilImovel", helper.getPerfilImovel());
			}

			if (helper.getCategoria() != null) {
				consulta += "scat.catg_id = :categoria AND ";
				parameters.put("categoria", helper.getCategoria());
			}

			if (helper.getSubCategoria() != null) {
				consulta += "scat.scat_id = :subCategoria AND ";
				parameters.put("subCategoria", helper.getSubCategoria());
			}

			if (helper.getIdSituacaoLigacaoAgua() != null) {
				consulta += "imov.last_id = :idSituacaoLigacaoAgua AND ";
				parameters.put("idSituacaoLigacaoAgua", helper.getIdSituacaoLigacaoAgua());
			}

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			query = session.createSQLQuery(consulta).addScalar("idImovel", Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				query.setParameter(key, parameters.get(key));
			}

			retorno = (Collection<Integer>) query.list();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;
	}

	public Collection pesquisarImovelDebitoAtualizacaoCadastral(
			Collection colecaoIdsImovel) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " SELECT	distinct(debitos.idImovel) as idImovel"
					+ " FROM (SELECT imov.imov_id idImovel, 'CONTA' as tipoDebito, 	sum(CASE WHEN pagtoConta.pgmt_id is null and conta.cnta_id is not null THEN conta.cnta_vlagua +  "
					+ "       conta.cnta_vlesgoto + conta.cnta_vldebitos - conta.cnta_vlcreditos -coalesce(conta.cnta_vlimpostos, 0 ) ELSE 0.00 END) valorDebitos, "
					+ "       count(CASE WHEN pagtoConta.pgmt_id is null and conta.cnta_id is not null THEN conta.cnta_id END) qtdeDebitos 	 "
					+ " 	   FROM cadastro.imovel imov  "
					+ " 	   LEFT OUTER JOIN faturamento.conta conta on conta.imov_id = imov.imov_id and conta.dcst_idatual in (0, 1, 2)  "
					+ " 	   LEFT OUTER JOIN arrecadacao.pagamento pagtoConta on conta.cnta_id = pagtoConta.cnta_id  "
					+ "	   WHERE cnta_dtvencimentoconta < :dataAtual "
					+ "       and imov.imov_id in(:colecaoIdsImovel)  "
					+ " 	   GROUP BY distinct(debitos.idimovel), tipoDebito  "
					+ "       UNION  "
					+ " 	   SELECT imov.imov_id as idImovel, 'GUIA' as tipoDebito, sum(CASE WHEN pagtoGuia.pgmt_id is null and gpag.gpag_id is not null THEN gpag.gpag_vldebito ELSE 0.00 END) as valorDebitos, "
					+ "       count(CASE WHEN pagtoGuia.pgmt_id is null and gpag.gpag_id is not null THEN gpag.gpag_id END) as qtdeDebitos  "
					+ " 	   FROM cadastro.imovel imov  "
					+ " 	   LEFT OUTER JOIN faturamento.guia_pagamento gpag on gpag.imov_id = imov.imov_id  "
					+ " 	   LEFT OUTER JOIN arrecadacao.pagamento pagtoGuia on gpag.gpag_id = pagtoGuia.gpag_id "
					+ " 	   WHERE gpag_dtvencimento < :dataAtual  "
					+ " 	   and imov.imov_id in (:colecaoIdsImovel)  "
					+ " 	   GROUP BY 	(imov.imov_id), tipoDebito) debitos "
					+ " INNER JOIN cadastro.imovel imov on debitos.idImovel = imov.imov_id  "
					+ " WHERE debitos.valorDebitos is null or debitos.valorDebitos > 0 "
					+ " order by 1 ";

			retorno = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).setParameterList("colecaoIdsImovel",
					colecaoIdsImovel).setTimestamp("dataAtual", new Date())
					.list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer verificarClienteSelecionadoFuncionario(Integer idCliente)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Integer retorno = null;

		try {
			consulta = "select func.id "
					+ "from Funcionario func, Cliente clie "
					+ "where func.numeroCpf = clie.cpf "
					+ " and clie.id = :idCliente";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idCliente", idCliente).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(
			Integer idQuadra) throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = " SELECT qFace.numeroQuadraFace, bacia.descricao, qFace.indicadorRedeEsgoto, " // 0,
																										// 1, 2
					+ " qFace.indicadorRedeAgua, distritoOperacional.descricao, " // 3, 4
					+ " sistemaEsgoto.descricao " // 5
					+ " FROM QuadraFace qFace "
					+ " left join qFace.bacia bacia "
					+ " left join bacia.sistemaEsgoto sistemaEsgoto "
					+ " left join qFace.distritoOperacional distritoOperacional "
					+ " WHERE " + " qFace.quadra.id = :idQuadra";

			retorno = (Collection<Object[]>) session.createQuery(consulta)
					.setInteger("idQuadra", idQuadra).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Integer> pesquisarSetorComercialGeracaoTabelasTemporarias(
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Integer> retorno = null;

		Query query = null;

		try {

			consulta = "SELECT distinct (imov.stcm_id) as idSetor "
					+ "from cadastro.imovel imov ";

			if (helper.getCliente() != null) {
				consulta = consulta
						+ "LEFT JOIN cadastro.cliente_imovel clim ON clim.imov_id = imov.imov_id ";
			}

			if (helper.getLocalidadeInicial() != null
					|| helper.getElo() != null) {
				consulta = consulta
						+ "LEFT JOIN cadastro.localidade local ON local.loca_id = imov.loca_id ";
			}

			if (helper.getRotaInicial() != null) {
				consulta = consulta
						+ "LEFT JOIN cadastro.quadra qdra ON qdra.qdra_id = imov.qdra_id ";
			}

			if (helper.getCategoria() != null
					|| helper.getSubCategoria() != null) {
				consulta = consulta
						+ "LEFT JOIN cadastro.imovel_subcategoria imsb ON imsb.imov_id = imov.imov_id "
						+ "LEFT JOIN cadastro.subcategoria scat ON scat.scat_id = imsb.scat_id ";
			}

			consulta = consulta
					+ "where imov.imov_icexclusao <> 1 AND (imov.siac_id is null OR imov.siac_id = 0) ";

			if (helper.getMatricula() != null) {

				consulta = consulta + " AND imov.imov_id = "
						+ helper.getMatricula();
			}

			if (helper.getCliente() != null) {

				consulta = consulta + " AND clim.clie_id = "
						+ helper.getCliente();
			}

			if (helper.getElo() != null) {

				consulta = consulta + " AND local.loca_cdelo = "
						+ helper.getElo();
			}

			if (helper.getLocalidadeInicial() != null) {

				consulta = consulta + " AND local.loca_id between "
						+ helper.getLocalidadeInicial() + " AND "
						+ helper.getLocalidadeFinal();

			}

			if (helper.getSetorComercialInicial() != null) {

				consulta = consulta + " AND imov.stcm_id between "
						+ helper.getSetorComercialInicial() + " AND "
						+ helper.getSetorComercialFinal();

			}

			if (helper.getIdQuadraInicial() != null) {

				consulta = consulta + " AND imov.qdra_id between "
						+ helper.getIdQuadraInicial() + " AND "
						+ helper.getIdQuadraFinal();

			}

			if (helper.getRotaInicial() != null) {

				consulta = consulta + " AND qdra.rota_id between "
						+ helper.getRotaInicial() + " AND "
						+ helper.getRotaFinal();
			}

			if (helper.getRotaSequenciaInicial() != null) {

				consulta = consulta
						+ " AND imov.imov_nnsequencialrota between "
						+ helper.getRotaSequenciaInicial() + " AND "
						+ helper.getRotaSequenciaFinal();

			}

			if (helper.getPerfilImovel() != null) {

				consulta = consulta + " AND imov.iper_id = "
						+ helper.getPerfilImovel();
			}

			if (helper.getCategoria() != null) {

				consulta = consulta + " AND scat.catg_id = "
						+ helper.getCategoria();
			}

			if (helper.getSubCategoria() != null) {

				consulta = consulta + " AND scat.scat_id = "
						+ helper.getSubCategoria();
			}

			if (helper.getIdSituacaoLigacaoAgua() != null) {

				consulta = consulta + " AND imov.last_id = "
						+ helper.getIdSituacaoLigacaoAgua();
			}

			query = session.createSQLQuery(consulta).addScalar("idSetor",
					Hibernate.INTEGER);

			if (helper.getQuantidadeMaxima() == null) {
				retorno = (Collection<Integer>) query.list();
			} else {
				retorno = (Collection<Integer>) query.setMaxResults(
						helper.getQuantidadeMaxima()).list();
			}

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public EmpresaContratoCadastro pesquisarEmpresaContratoCadastro(
			Integer idEmpresa) throws ErroRepositorioException {

		EmpresaContratoCadastro retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select ecc " + "from EmpresaContratoCadastro ecc "
					+ "where ecc.empresa = :idEmpresa "
					+ "and ecc.dataFinalContrato >= :dataAtual "
					+ "and ecc.dataCancelamentoContrato is null "
					+ "order by ecc.dataInicioContrato  desc ";

			retorno = (EmpresaContratoCadastro) session.createQuery(consulta)
					.setInteger("idEmpresa", idEmpresa).setDate("dataAtual",
							new Date()).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Collection pesquisarOperacoesEfetuadasComImovelAssociado(
			Date dataInicio, Date dataFim, Integer idEmpresa)
			throws ErroRepositorioException {

		Collection retorno = null;
		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select "
					+ "opef.opef_cnargumento as argumento, "
					+ "tbla.tbla_id2 as id2, "
					+ "atrb.atrb_id as idAtributo, "
					+ "ecca.ecca_vlatualizacao as valorAtualizacao, "
					+ "atrb.atgr_id as idGrupo, "
					+ "atrb.atrb_nnordememissao as ordemEmissao, "
					+ "count(opef.opef_cnargumento) as qtdade "
					+ "from seguranca.OPERACAO_EFETUADA opef "
					+ "  inner join seguranca.OPERACAO oper on oper.oper_id=opef.oper_id "
					+ "  inner join seguranca.FUNCIONALIDADE_ATRIBUTO fnat on fnat.fncd_id=oper.fncd_id "
					+ "  inner join seguranca.ATRIBUTO atrb on fnat.atrb_id=atrb.atrb_id "
					+ "  inner join seguranca.ATRIBUTO_GRUPO atgr on atgr.atgr_id=atrb.atgr_id and atgr.atgr_icimovel=1 "
					+ "  inner join seguranca.USUARIO_ALTERACAO usat on usat.tref_id=opef.opef_id and usat.usac_id=1 "
					+ "  inner join seguranca.usuario usur on usur.usur_id=usat.usis_id and usur.empr_id= :idEmpresa "
					+ "  inner join seguranca.TABELA_LINHA_ALTERACAO tbla on tbla.tref_id=opef.opef_id "
					+ "  inner join seguranca.tab_linha_col_alteracao tlco on tlco.tbla_id=tbla.tbla_id "
					+ "  inner join seguranca.TABELA_COLUNA_ATRIBUTO tcat on tcat.tbco_id=tlco.tbco_id and tcat.atrb_id=fnat.atrb_id "
					+ "  inner join cadastro.empr_contrato_cad_atrib ecca on ecca.atrb_id = atrb.atrb_id "
					+ "where   "
					+ "   to_date(to_char(OPEF_TMULTIMAALTERACAO,'YYYY/MM/DD'),'YYYY/MM/DD') between :dataInicio and :dataFim "
					+ "group by  opef.opef_cnargumento, tcat.atrb_id, tbla.tbla_id2 , atrb.atrb_id , ecca.ecca_vlatualizacao , atrb.atgr_id , atrb.atrb_nnordememissao "
					+ "order by " + "   opef.OPEF_CNARGUMENTO, tcat.ATRB_ID ";

			retornoConsulta = session
					.createSQLQuery(consulta)
					.addScalar("argumento", Hibernate.INTEGER)
					.addScalar("id2", Hibernate.INTEGER)
					.addScalar("idAtributo", Hibernate.INTEGER)
					.addScalar("valorAtualizacao", Hibernate.BIG_DECIMAL)
					.addScalar("idGrupo", Hibernate.INTEGER)
					.addScalar("ordemEmissao", Hibernate.SHORT)
					.addScalar("qtdade", Hibernate.INTEGER)
					.setDate("dataInicio", Util.formatarDataInicial(dataInicio))
					.setDate("dataFim", Util.formatarDataFinal(dataFim))
					.setInteger("idEmpresa", idEmpresa).list();

			if (retornoConsulta.size() > 0) {
				Iterator operacoesEfetuadasIter = retornoConsulta.iterator();
				retorno = new ArrayList();
				AtributosBoletimChaveHelper atributosBoletimChaveHelper = null;
				OperacoesEfetuadasHelper helper = null;
				while (operacoesEfetuadasIter.hasNext()) {

					Object[] element = (Object[]) operacoesEfetuadasIter.next();

					helper = new OperacoesEfetuadasHelper();

					helper.setArgumento((Integer) element[0]);
					if (element[1] != null) {
						helper.setId2TabelaLinhaAlteracao((Integer) element[1]);
					} else {
						helper.setId2TabelaLinhaAlteracao(0);
					}

					helper.setValorAtualizacaoAtributo((BigDecimal) element[3]);

					atributosBoletimChaveHelper = new AtributosBoletimChaveHelper(
							(Integer) element[2], (Integer) element[4],
							(Short) element[5]);

					helper
							.setAtributosBoletimChaveHelper(atributosBoletimChaveHelper);

					retorno.add(helper);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarOperacoesEfetuadasSemImovelAssociado(
			Date dataInicio, Date dataFim, Integer idEmpresa)
			throws ErroRepositorioException {

		Collection retorno = null;
		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select "
					+ "opef.opef_cnargumento as argumento, "
					+ "tbla.tbla_id2 as id2, "
					+ "atrb.atrb_id as idAtributo, "
					+ "ecca.ecca_vlatualizacao as valorAtualizacao, "
					+ "atrb.atgr_id as idGrupo, "
					+ "atrb.atrb_nnordememissao as ordemEmissao, "
					+ "count(opef.opef_cnargumento) as qtdade "
					+ "from seguranca.OPERACAO_EFETUADA opef "
					+ "  inner join seguranca.OPERACAO oper on oper.oper_id=opef.oper_id "
					+ "  inner join seguranca.FUNCIONALIDADE_ATRIBUTO fnat on fnat.fncd_id=oper.fncd_id "
					+ "  inner join seguranca.ATRIBUTO atrb on fnat.atrb_id=atrb.atrb_id "
					+ "  inner join seguranca.ATRIBUTO_GRUPO atgr on atgr.atgr_id=atrb.atgr_id and atgr.atgr_id=opef.atgr_id "
					+
					// "on atgr.atgr_id=atrb.atgr_id and atgr.atgr_icimovel=2 "
					// +
					"  inner join seguranca.USUARIO_ALTERACAO usat on usat.tref_id=opef.opef_id and usat.usac_id=1 "
					+ "  inner join seguranca.usuario usur on usur.usur_id=usat.usis_id and usur.empr_id= :idEmpresa "
					+ "  inner join seguranca.TABELA_LINHA_ALTERACAO tbla on tbla.tref_id=opef.opef_id "
					+ "  inner join seguranca.tab_linha_col_alteracao tlco on tlco.tbla_id=tbla.tbla_id "
					+ "  inner join seguranca.TABELA_COLUNA_ATRIBUTO tcat on tcat.tbco_id=tlco.tbco_id and tcat.atrb_id=fnat.atrb_id "
					+ " inner join cadastro.empr_contrato_cad_atrib ecca on ecca.atrb_id = atrb.atrb_id "
					+ "where "
					+ "   to_date(to_char(OPEF_TMULTIMAALTERACAO,'YYYY/MM/DD'),'YYYY/MM/DD') between :dataInicio and :dataFim "
					+ "group by  opef.opef_cnargumento, tcat.atrb_id, tbla.tbla_id2 , atrb.atrb_id , ecca.ecca_vlatualizacao , atrb.atgr_id , atrb.atrb_nnordememissao "
					+ "order by " + "   tbla.TBLA_ID2, tcat.ATRB_ID ";

			retornoConsulta = session
					.createSQLQuery(consulta)
					.addScalar("argumento", Hibernate.INTEGER)
					.addScalar("id2", Hibernate.INTEGER)
					.addScalar("idAtributo", Hibernate.INTEGER)
					.addScalar("valorAtualizacao", Hibernate.BIG_DECIMAL)
					.addScalar("idGrupo", Hibernate.INTEGER)
					.addScalar("ordemEmissao", Hibernate.SHORT)
					.addScalar("qtdade", Hibernate.INTEGER)
					.setDate("dataInicio", Util.formatarDataInicial(dataInicio))
					.setDate("dataFim", Util.formatarDataFinal(dataFim))
					.setInteger("idEmpresa", idEmpresa).list();

			if (retornoConsulta.size() > 0) {
				Iterator operacoesEfetuadasIter = retornoConsulta.iterator();
				retorno = new ArrayList();
				AtributosBoletimChaveHelper atributosBoletimChaveHelper = null;
				OperacoesEfetuadasHelper helper = null;
				while (operacoesEfetuadasIter.hasNext()) {

					Object[] element = (Object[]) operacoesEfetuadasIter.next();

					helper = new OperacoesEfetuadasHelper();

					// 9.2 9.2. Neste caso, o Conte�do do Argumento deve
					// corresponder ao conte�do do segundo argumento (TBLA_ID2).
					helper.setArgumento((Integer) element[1]);
					// helper.setArgumento((Integer)element[0]);

					if (element[1] != null) {
						helper.setId2TabelaLinhaAlteracao((Integer) element[1]);
					} else {
						helper.setId2TabelaLinhaAlteracao(0);
					}

					helper.setValorAtualizacaoAtributo((BigDecimal) element[3]);

					atributosBoletimChaveHelper = new AtributosBoletimChaveHelper(
							(Integer) element[2], (Integer) element[4],
							(Short) element[5]);

					helper
							.setAtributosBoletimChaveHelper(atributosBoletimChaveHelper);

					retorno.add(helper);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarAtributosBoletim()
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select atributo "
					+ "from Atributo atributo "
					+ "inner join fetch atributo.atributoGrupo atributoGrupo "
					+ "order by atributoGrupo.id, atributo.numeroOrdemEmissao  ";

			retorno = (Collection) session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public BigDecimal pesquisarValorAtualizacaoAtributo(Integer idAtributo,
			Integer idEmpresaContratoCadastro) throws ErroRepositorioException {

		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select ecca.valorAtualizacaoAtributo "
					+ "from EmpresaContratoCadastroAtributo ecca "
					+ "where ecca.atributo.id = :idAtributo "
					+ "and ecca.empresaContratoCadastro.id = :idEmpresaContratoCadastro ";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idAtributo", idAtributo).setInteger(
					"idEmpresaContratoCadastro", idEmpresaContratoCadastro)
					.uniqueResult();

			if (retorno == null) {
				retorno = new BigDecimal("0.00");
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarDadosBoleto(int quantidadeInicio, Integer grupo,
			String nomeEmpresa) throws ErroRepositorioException {

		Collection retorno = null;
		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select distinct "
					+ "	im.loca_id AS idLocalidade,  "
					+ "	sc.stcm_cdsetorcomercial AS codigoSetor,  "
					+ "	qd.qdra_nnquadra AS numeroQuadra, "
					+ "	im.imov_nnlote AS lote,	 "
					+ "	im.imov_nnsublote AS sublote, "
					+ "	im.imov_id AS matricula,  "
					+ "	clie_nmcliente AS nomeCliente, "
					+ "	rota.ftgr_id AS grupo, "
					+ "   rota.empr_id AS empresa, "
					+ "   rota.rota_cdrota AS codigoRota, "
					+ "   im.imov_nnsequencialrota as sequencialRota "
					+

					"from   "
					+ "	cadastro.imovel		 		            im "
					+ "	INNER JOIN cadastro.cliente_imovel 	    clieImov   ON im.imov_id = clieImov.imov_id "
					+ "	INNER JOIN cadastro.cliente 		    cliente	   ON clieImov.clie_id = cliente.clie_id "
					+ "	INNER JOIN cadastro.setor_comercial     sc		   ON im.stcm_id = sc.stcm_id "
					+ "	INNER JOIN cadastro.quadra 		        qd		   ON im.qdra_id = qd.qdra_id "
					+ "	INNER JOIN micromedicao.rota		    rota	   ON qd.rota_id = rota.rota_id "
					+ "	INNER JOIN cadastro.imovel_subcategoria isc1 	   ON im.imov_id = isc1.imov_id "
					+ "	INNER JOIN cadastro.subcategoria        scat1 	   ON scat1.scat_id = isc1.scat_id "
					+

					"where  "
					+ "	(im.last_id in (:ligadoAgua, :ligadoAnaliseAgua ) or im.lest_id = :ligadoEsgoto)  ";

			// Caso seja CAERN, considera todos os municipios
			// Alterado por R�mulo Aur�lio / Analista: Rafael Pinto
			// Data: 25/11/2009
			if (!nomeEmpresa.equalsIgnoreCase(SistemaParametro.EMPRESA_CAERN)) {

				consulta = consulta
						+ "   and sc.muni_id in (005, 090, 105, 290, 345, 520, 680, 720, 760, 775, 790, 940, 1070, 1130, 1140, 1160, 1640, 960) ";
			}

			// Fim da alteracao
			consulta = consulta + "   and rota.ftgr_id = :grupo "
					+ "	and ClieImov.crtp_id = :clienteRelacaoTipo "
					+ "	and ClieImov.clim_dtrelacaofim is null "
					+ "	and scat1.catg_id <> :categoria  ";

			// Caso seja CAERN, Ordena tamb�m por codigo da rota e Sequencial da
			// Rota
			// Alterado por R�mulo Aur�lio / Analista: Rafael Pinto
			// Data: 25/11/2009
			if (!nomeEmpresa.equalsIgnoreCase(SistemaParametro.EMPRESA_CAERN)) {
				consulta = consulta
						+ "order by grupo, empresa, idLocalidade, codigoSetor, numeroQuadra, lote, sublote  ";
			} else {
				consulta = consulta + "order by idLocalidade, codigoSetor, "
						+ " codigoRota, sequencialRota, lote, sublote  ";
			}
			retornoConsulta = session.createSQLQuery(consulta).addScalar(
					"idLocalidade", Hibernate.INTEGER).addScalar("codigoSetor",
					Hibernate.INTEGER).addScalar("numeroQuadra",
					Hibernate.INTEGER).addScalar("lote", Hibernate.SHORT)
					.addScalar("sublote", Hibernate.SHORT).addScalar(
							"matricula", Hibernate.INTEGER).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar("grupo",
							Hibernate.INTEGER).addScalar("empresa",
							Hibernate.INTEGER).addScalar("codigoRota",
							Hibernate.SHORT).addScalar("sequencialRota",
							Hibernate.INTEGER).setInteger("ligadoAgua",
							LigacaoAguaSituacao.LIGADO).setInteger(
							"ligadoAnaliseAgua",
							LigacaoAguaSituacao.LIGADO_EM_ANALISE).setInteger(
							"ligadoEsgoto", LigacaoEsgotoSituacao.LIGADO)
					.setInteger("clienteRelacaoTipo",
							ClienteRelacaoTipo.USUARIO).setInteger("categoria",
							Categoria.PUBLICO).setInteger("grupo", grupo).

					setFirstResult(quantidadeInicio).setMaxResults(1000).list();

			if (retornoConsulta.size() > 0) {
				Iterator dadosBoletoIter = retornoConsulta.iterator();
				retorno = new ArrayList();
				DadosBoletoHelper helper = null;
				Imovel imovel = null;

				while (dadosBoletoIter.hasNext()) {

					Object[] element = (Object[]) dadosBoletoIter.next();
					helper = new DadosBoletoHelper();
					imovel = new Imovel();
					Localidade localidade = new Localidade();
					SetorComercial setorComercial = new SetorComercial();
					Quadra quadra = new Quadra();

					if (element[0] != null) {
						localidade.setId((Integer) element[0]);
						imovel.setLocalidade(localidade);
					}

					if (element[1] != null) {
						setorComercial.setCodigo((Integer) element[1]);
						imovel.setSetorComercial(setorComercial);
					}

					if (element[2] != null) {
						quadra.setNumeroQuadra((Integer) element[2]);
						imovel.setQuadra(quadra);
					}

					if (element[3] != null) {
						imovel.setLote((Short) element[3]);
					}

					if (element[4] != null) {
						imovel.setSubLote((Short) element[4]);
					}

					if (element[5] != null) {
						imovel.setId((Integer) element[5]);
					}

					helper.setImovel(imovel);

					if (element[6] != null) {
						helper.setNomeCliente((String) element[6]);
					}

					if (element[7] != null) {
						helper.setIdGrupoFaturamento((Integer) element[7]);
					}

					if (element[8] != null) {
						helper.setIdEmpresa((Integer) element[8]);
					}

					if (element[9] != null) {
						helper.setCodigoRota((Short) element[9]);
					}

					if (element[10] != null) {
						helper.setSequencialRota((Integer) element[10]);
					}

					retorno.add(helper);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public BigDecimal pesquisarValorLimiteDebitoTipo(Integer idDebitoTipo)
			throws ErroRepositorioException {

		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select dbtp.valorLimite " + "from DebitoTipo dbtp "
					+ "where dbtp.id = :idDebitoTipo ";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idDebitoTipo", idDebitoTipo).uniqueResult();

			if (retorno == null) {
				retorno = new BigDecimal("0.00");
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)
			throws ErroRepositorioException {

		UnidadeNegocio retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select unidadeNegocio " + "FROM Usuario usuario "
					+ "INNER JOIN usuario.unidadeNegocio unidadeNegocio "
					+ "WHERE usuario.id = :idUsuario ";

			retorno = (UnidadeNegocio) session.createQuery(consulta)
					.setInteger("idUsuario", idUsuario).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public List pesquisarImoveisExcluirDaTarifaSocial(Integer idSetor,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select i.id, " + "i.quantidadeEconomias, "
					+ "ch.consumoMedio " + "FROM ConsumoHistorico ch "
					+ "INNER JOIN ch.imovel i "
					+ "WHERE i.imovelPerfil = 4 and "
					+ "i.indicadorExclusao = 2 and "
					+ "ch.consumoMedio > 19 and " + "i.setorComercial = "
					+ idSetor + " and ch.ligacaoTipo = 1 and "
					+ "ch.referenciaFaturamento = " + anoMesFaturamento
					+ " GROUP BY i.id, i.quantidadeEconomias, ch.consumoMedio ";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void atualizarExcluirDaTarifaSocialTabelaDadoEconomia(String idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		// Update na tabela TarifaSocialDadoEconomia
		String consulta = " update TarifaSocialDadoEconomia tsde "
				+ " set tsde.tarifaSocialExclusaoMotivo = 37, "
				+ " tsde.dataExclusao =  " + Util.obterSQLDataAtual() + " , "
				+ " tsde.dataRevisao = null, "
				+ " tsde.tarifaSocialRevisaoMotivo = null, "
				+ " tsde.ultimaAlteracao =  " + Util.obterSQLDataAtual()
				+ " where tsde.imovel = " + idImovel;

		try {

			session.createQuery(consulta).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	public void atualizarExcluirDaTarifaSocialTabelaImovel(String idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		// update na tabela Imovel
		String consulta = " update Imovel imov "
				+ " set imov.imovelPerfil = 5, " + " imov.ultimaAlteracao = "
				+ Util.obterSQLDataAtual() + " where imov.id = " + idImovel;

		try {

			session.createQuery(consulta).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer consumoMedioAguaInicial = filtro.getConsumoMedioAguaInicial();
		Integer consumoMedioAguaFinal = filtro.getConsumoMedioAguaFinal();

		Integer consumoMedioEsgotoInicial = filtro
				.getConsumoMedioEsgotoInicial();
		Integer consumoMedioEsgotoFinal = filtro.getConsumoMedioEsgotoFinal();

		String consulta = "";

		try {
			consulta = "select count(distinct imov.imov_id) as cont "
					+

					"from cadastro.cliente_imovel clim "
					+

					"inner join cadastro.imovel imov on clim.imov_id=imov.imov_id "
					+ "inner join cadastro.localidade loca on imov.loca_id=loca.loca_id "
					+ "inner join cadastro.gerencia_regional greg on loca.greg_id=greg.greg_id "
					+ "inner join cadastro.unidade_negocio uneg on loca.uneg_id=uneg.uneg_id "
					+ "inner join cadastro.setor_comercial stcm on imov.stcm_id=stcm.stcm_id "
					+ "inner join cadastro.quadra qdra on imov.qdra_id=qdra.qdra_id "
					+ "inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao last on imov.last_id=last.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on imov.lest_id=lest.lest_id "
					+ "left outer join cadastro.logradouro_cep lgcp on imov.lgcp_id=lgcp.lgcp_id "
					+ "left outer join cadastro.logradouro logr on lgcp.logr_id=logr.logr_id "
					+ "left outer join cadastro.logradouro_bairro lgbr on imov.lgbr_id=lgbr.lgbr_id "
					+ "left outer join cadastro.bairro bair on lgbr.bair_id=bair.bair_id "
					+ "inner join cadastro.cliente clie on clim.clie_id=clie.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo crtp on clim.crtp_id=crtp.crtp_id "
					+

					// CLIENTE USU�RIO
					"and (crtp.crtp_id = 2 ) "
					+

					// AGUA
					"left join micromedicao.consumo_historico consumoAgua on imov.imov_id=consumoAgua.imov_id "
					+ "and (consumoAgua.lgti_id = 1 ) and (consumoAgua.cshi_amfaturamento = :anoMesFaturamento ) "
					+

					// ESGOTO
					"left join micromedicao.consumo_historico consumoEsgoto on imov.imov_id=consumoEsgoto.imov_id "
					+ "and (consumoEsgoto.lgti_id = 2 ) and (consumoEsgoto.cshi_amfaturamento = :anoMesFaturamento ) ";

			consulta += "where clim.clim_dtrelacaofim is null ";

			if (unidadeNegocio != null) {
				consulta += " and uneg.uneg_id = " + unidadeNegocio.toString();
			}

			if (gerencia != null) {
				consulta += " and greg.greg_id = " + gerencia.toString();
			}

			if (localidadeInicial != null) {
				consulta += " and loca.loca_id between "
						+ localidadeInicial.toString() + " and "
						+ localidadeFinal.toString();
			}

			if (setorComercialInicial != null) {
				consulta += " and stcm.stcm_cdsetorcomercial between "
						+ setorComercialInicial.toString() + " and "
						+ setorComercialFinal.toString();
			}

			if (rotaInicial != null) {
				consulta += " and rota.rota_cdrota between "
						+ rotaInicial.toString() + " and "
						+ rotaFinal.toString();
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial.toString() + " and "
						+ sequencialRotaFinal;
			}

			if (consumoMedioAguaInicial != null) {
				consulta += " and consumoAgua.cshi_nnconsumomedio between "
						+ consumoMedioAguaInicial.toString() + " and "
						+ consumoMedioAguaFinal;
			}

			if (consumoMedioEsgotoInicial != null) {
				consulta += " and consumoEsgoto.cshi_nnconsumomedio between "
						+ consumoMedioEsgotoInicial.toString() + " and "
						+ consumoMedioEsgotoFinal;
			}

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"cont", Hibernate.INTEGER).setInteger("anoMesFaturamento",
					anoMesFaturamento.intValue()).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount()
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select count(distinct imov_id) as cont " +

			"from cadastro.imovel_atlz_cadastral imovel ";

			consulta += " where  siac_id = " + ConstantesSistema.ZERO;

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"cont", Hibernate.INTEGER).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarQuantidadeImoveisTransmitidosAtualizacaoCadastral(Integer idArquivoTexto) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Integer qtd = null;

		try {
			String sql = "SELECT count(distinct imov_id) AS cont "
					   + "FROM cadastro.imovel_atlz_cadastral imovel "
					   + "WHERE siac_id >= :situacao "
					   + "AND txac_id = :idArquivoTexto";

			qtd = (Integer) session.createSQLQuery(sql)
					.addScalar("cont", Hibernate.INTEGER)
					.setInteger("situacao", SituacaoAtualizacaoCadastral.TRANSMITIDO)
					.setInteger("idArquivoTexto", idArquivoTexto)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return qtd;
	}
	
	public Collection<Integer> pesquisarIdsImoveisAtualizacaoCadastral(
			Integer idEmpresaLeiturista, Integer idRota)
	throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT imac.imov_id as idImovel "
				+ "FROM cadastro.imovel_atlz_cadastral AS imac "
				+ "INNER JOIN cadastro.imovel AS imov ON imov.imov_id = imac.imov_id "
				+ "INNER JOIN cadastro.setor_comercial AS stcm ON imov.stcm_id = stcm.stcm_id "
				+ "INNER JOIN cadastro.quadra AS qdra ON imov.qdra_id = qdra.qdra_id "
				+ "INNER JOIN micromedicao.rota AS rota ON rota.rota_id = qdra.rota_id "
				+ "WHERE imac.siac_id = :situacao "
				+ "AND imac.empr_id = :idEmpresaLeiturista "
				+ "AND rota.rota_id = :idRota "
				+ "ORDER BY imov.imov_icimovelcondominio, imov.loca_id, stcm.stcm_cdsetorcomercial, qdra.qdra_nnquadra, "
				+ "rota.rota_cdrota, imov.imov_nnlote, imov.imov_nnsublote ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idImovel", Hibernate.INTEGER)
					.setInteger("situacao", SituacaoAtualizacaoCadastral.DISPONIVEL)
					.setInteger("idEmpresaLeiturista", idEmpresaLeiturista)
					.setInteger("idRota", idRota)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Collection<Integer> pesquisarRotasAtualizacaoCadastral(
			Collection idsImoveis) throws ErroRepositorioException {

		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT distinct(rota.rota_id) as idRota "
				+ "FROM cadastro.imovel AS imov "
				+ "INNER JOIN cadastro.setor_comercial AS stcm ON imov.stcm_id = stcm.stcm_id "
				+ "INNER JOIN cadastro.quadra AS qdra ON imov.qdra_id = qdra.qdra_id "
				+ "INNER JOIN micromedicao.rota AS rota ON rota.rota_id = qdra.rota_id "
				+ "WHERE imov.imov_id IN (:idsImoveis) "
				+ "ORDER BY rota.rota_id";

			retorno = (Collection<Integer>) session.createSQLQuery(consulta)
					.addScalar("idRota", Hibernate.INTEGER)
					.setParameterList("idsImoveis", idsImoveis)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(
			int idArquivo) throws ErroRepositorioException {
		Collection<AtualizacaoCadastralSimplificadoCritica> retorno = new ArrayList<AtualizacaoCadastralSimplificadoCritica>();

		String consulta = "from AtualizacaoCadastralSimplificadoCritica critica"
				+ " join fetch critica.tipo tipo"
				+ " join fetch critica.linhas linha"
				+ " where linha.arquivo.id = :idArquivo"
				+ " order by tipo.descricao, critica.descricao";

		Session session = HibernateUtil.getSession();

		try {
			Query q = session.createQuery(consulta);
			q.setInteger("idArquivo", idArquivo);
			retorno = q.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		retorno = new HashSet<AtualizacaoCadastralSimplificadoCritica>(retorno);

		return retorno;
	}

	public BigDecimal pesquisarValorSugeridoDebitoTipo(Integer idDebitoTipo)
			throws ErroRepositorioException {

		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select dbtp.valorSugerido " + "from DebitoTipo dbtp "
					+ "where dbtp.id = :idDebitoTipo ";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idDebitoTipo", idDebitoTipo).uniqueResult();

			if (retorno == null) {
				retorno = new BigDecimal("0.00");
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(
			String idArquivoTxt, Integer idSituacaoTransmissao)
			throws ErroRepositorioException {

		ArquivoTextoAtualizacaoCadastral retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " select txac"// 2
					+ " from ArquivoTextoAtualizacaoCadastral txac"
					+ " inner join fetch txac.leiturista leit"
					+ " where txac.id = " + idArquivoTxt
					+ " and txac.situacaoTransmissaoLeitura.id ="
					+ idSituacaoTransmissao;

			retorno = (ArquivoTextoAtualizacaoCadastral) session.createQuery(
					consulta).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarImovelEmProgramaEspecial(
			Integer idPerfilProgramaEspecial, Rota rota, int numeroIndice,
			int quantidadeRegistros) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT "
					+ " imovelProgramaEspecial,"
					+ " imovel,"
					+ " quadra.id,"
					+ " rota.id,"
					+ " faturamentoGrupo.id,"
					+ " faturamentoGrupo.anoMesReferencia, "
					+ " imovelProgramaEspecial.id "
					+ "FROM ImovelProgramaEspecial imovelProgramaEspecial"
					+ " INNER JOIN FETCH imovelProgramaEspecial.imovel imovel"
					+ " INNER JOIN imovel.quadra quadra"
					+ " INNER JOIN quadra.rota rota"
					+ " INNER JOIN rota.faturamentoGrupo faturamentoGrupo"
					+ " WHERE imovelProgramaEspecial.imovelPerfil.id = :idPerfilProgramaEspecial "
					+ " AND (imovelProgramaEspecial.dataSuspensao IS NULL or imovelProgramaEspecial.formaSuspensao = :formaSuspensao)"
					+ " AND rota.id = :idRota "
					+ "ORDER BY imovelProgramaEspecial.id";

			retorno = session.createQuery(consulta).setInteger(
					"idPerfilProgramaEspecial", idPerfilProgramaEspecial)
					.setInteger("idRota", rota.getId()).setShort(
							"formaSuspensao",
							ImovelProgramaEspecial.FORMA_SUSPENSAO_OPERADOR)
					.setMaxResults(quantidadeRegistros).setFirstResult(
							numeroIndice).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select "
					+ "	imov.imov_id as idImovelEsp, "
					+ "	rgds.rdes_id as idRegDes, "
					+ "	rgds.rdes_nmregiaodesenvolvimento as nomeRegDes, "
					+ "	loca.loca_id as idLocalidade, "
					+ "	loca.loca_nmlocalidade as nomeLocalidade, "
					+ "	clie.clie_nmcliente as nomeCliente, "
					+ "	case when (lagu.hidi_id is null) then "
					+ "		'SEM HIDR.' "
					+ "	else "
					+ "		'COM HIDR.' "
					+ "	end as hidi, "
					+ "	( coalesce(cnta.cnta_nnconsumoagua, 0) + coalesce(cths.cnhi_nnconsumoagua, 0) ) as consumoAgua, "
					+ "	( ( ( coalesce(cnta.cnta_vlagua, 0) + coalesce(cnta.cnta_vlesgoto, 0) + coalesce(cnta.cnta_vldebitos, 0) ) "
					+ "	- ( coalesce(cnta.cnta_vlcreditos, 0) + coalesce(cnta.cnta_vlimpostos, 0) ) ) + "
					+ "	( ( coalesce(cths.cnhi_vlagua, 0) + coalesce(cths.cnhi_vlesgoto, 0) + coalesce(cths.cnhi_vldebitos, 0) ) "
					+ "	- ( coalesce(cths.cnhi_vlcreditos, 0) + coalesce(cths.cnhi_vlimpostos, 0) ) ) ) as valorConta "
					+ "from "
					+ "	faturamento.fatura fatu "
					+ "	inner join faturamento.fatura_item ftit on ftit.fatu_id = fatu.fatu_id "
					+ "	inner join cadastro.cliente clie on clie.clie_id = fatu.clie_id "
					+ "	left outer join faturamento.conta cnta on cnta.cnta_id = ftit.cnta_id "
					+ "	left outer join faturamento.conta_historico cths on cths.cnta_id = ftit.cnta_id "
					+ "	inner join cadastro.localidade loca on (loca.loca_id = cnta.loca_id or loca.loca_id = cths.loca_id) "
					+ "	inner join cadastro.imovel imov on (imov.imov_id = cnta.imov_id or imov.imov_id = cths.imov_id) "
					+ "	inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id "
					+ "	inner join cadastro.municipio muni on muni.muni_id = stcm.muni_id "
					+ "	inner join cadastro.regiao_desenvolvimento rgds on rgds.rdes_id = muni.rdes_id "
					+ "	inner join atendimentopublico.ligacao_agua lagu on lagu.lagu_id = imov.imov_id "
					+ "where "
					+ "	fatu.fatu_amreferencia = "
					+ helper.getMesAnoReferencia()
					+ " "
					+ "	and fatu.clie_id = (select clie_idprogramaespecial from cadastro.sistema_parametros) ";

			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equalsIgnoreCase("")) {
				consulta = consulta + "	and loca.loca_id = "
						+ helper.getIdLocalidade() + " ";
			}

			if (helper.getIdRegiaoDesenvolvimento() != null
					&& !helper.getIdRegiaoDesenvolvimento()
							.equalsIgnoreCase("")) {
				consulta = consulta + "	and muni.rdes_id = "
						+ helper.getIdRegiaoDesenvolvimento() + " ";
			}

			consulta = consulta
					+ "	order by idRegDes, idLocalidade, idImovelEsp";

			retorno = session.createSQLQuery(consulta).addScalar("idImovelEsp",
					Hibernate.INTEGER).addScalar("idRegDes", Hibernate.INTEGER)
					.addScalar("nomeRegDes", Hibernate.STRING).addScalar(
							"idLocalidade", Hibernate.INTEGER).addScalar(
							"nomeLocalidade", Hibernate.STRING).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar("hidi",
							Hibernate.STRING).addScalar("consumoAgua",
							Hibernate.INTEGER).addScalar("valorConta",
							Hibernate.BIG_DECIMAL).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ErroRepositorioException {

		Integer retorno = 0;
		String consulta = "";
		Collection colecao = null;

		Session session = HibernateUtil.getSession();

		try {
			consulta = "select "
					+ "	count(ftit.cnta_id) as qtd "
					+ "from "
					+ "	faturamento.fatura fatu "
					+ "	inner join faturamento.fatura_item ftit on ftit.fatu_id = fatu.fatu_id "
					+ "	inner join cadastro.cliente clie on clie.clie_id = fatu.clie_id "
					+ "	left outer join faturamento.conta cnta on cnta.cnta_id = ftit.cnta_id "
					+ "	left outer join faturamento.conta_historico cths on cths.cnta_id = ftit.cnta_id "
					+ "	inner join cadastro.localidade loca on (loca.loca_id = cnta.loca_id or loca.loca_id = cths.loca_id) "
					+ "	inner join cadastro.imovel imov on (imov.imov_id = cnta.imov_id or imov.imov_id = cths.imov_id) "
					+ "	inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id "
					+ "	inner join cadastro.municipio muni on muni.muni_id = stcm.muni_id "
					+ "where "
					+ "	fatu.fatu_amreferencia = "
					+ helper.getMesAnoReferencia()
					+ " "
					+ "	and fatu.clie_id = (select clie_idprogramaespecial from cadastro.sistema_parametros) ";

			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equalsIgnoreCase("")) {
				consulta += "	and loca.loca_id = " + helper.getIdLocalidade()
						+ " ";
			}

			if (helper.getIdRegiaoDesenvolvimento() != null
					&& !helper.getIdRegiaoDesenvolvimento()
							.equalsIgnoreCase("")) {
				consulta += "	and muni.rdes_id = "
						+ helper.getIdRegiaoDesenvolvimento() + " ";
			}

			colecao = (Collection) session.createSQLQuery(consulta).addScalar(
					"qtd", Hibernate.INTEGER).list();

			if (colecao != null && !colecao.isEmpty()) {
				Iterator iter = colecao.iterator();
				Integer element = (Integer) iter.next();
				retorno = (Integer) element;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select A.idRegDes as idReg, "
					+ "	A.nomeRegDes as nomeReg, "
					+ "	A.idLocalidade as idLoca, "
					+ "	A.nomeLocalidade as nomeLoca, "
					+ "	SUM(A.qtdContaSemHidr) as qtdContaSemHidr, "
					+ "	SUM(A.valorContasSemHidr) as valorContasSemHidr, "
					+ "	SUM(A.qtdContaComHidr) as qtdContaComHidr, "
					+ "	SUM(A.valorContasComHidr) as valorContasComHidr "
					+ "FROM ( "
					+ "	select "
					+ "		rgds.rdes_id idRegDes, "
					+ "		rgds.rdes_nmregiaodesenvolvimento as nomeRegDes, "
					+ "		loca.loca_id as idLocalidade, "
					+ "		loca.loca_nmlocalidade as nomeLocalidade, "
					+ "		case when (lagu.hidi_id is null) then 1 else 0 end as qtdContaSemHidr, "
					+ "		case when (lagu.hidi_id is null) then "
					+ "		( ( ( (coalesce(cnta.cnta_vlagua, 0) + coalesce(cnta.cnta_vlesgoto, 0) + "
					+ "		coalesce(cnta.cnta_vldebitos, 0)) - (coalesce(cnta.cnta_vlcreditos, 0) + "
					+ "		coalesce(cnta.cnta_vlimpostos, 0)) ) ) ) + "
					+ "		( ( ( (coalesce(cths.cnhi_vlagua, 0) + coalesce(cths.cnhi_vlesgoto, 0) + "
					+ "		coalesce(cths.cnhi_vldebitos, 0)) - (coalesce(cths.cnhi_vlcreditos, 0) + "
					+ "		coalesce(cths.cnhi_vlimpostos,0)) ) ) ) "
					+ "		else 0 end as valorContasSemHidr, "
					+ "		case when (lagu.hidi_id is not null) then 1 else 0 end as qtdContaComHidr, "
					+ "		case when (lagu.hidi_id is not null) then "
					+ "		( ( ( (coalesce(cnta.cnta_vlagua, 0) + coalesce(cnta.cnta_vlesgoto, 0) + "
					+ "		coalesce(cnta.cnta_vldebitos, 0)) - (coalesce(cnta.cnta_vlcreditos, 0) + "
					+ "		coalesce(cnta.cnta_vlimpostos, 0)) ) ) ) + "
					+ "		( ( ( (coalesce(cths.cnhi_vlagua, 0) + coalesce(cths.cnhi_vlesgoto, 0) + "
					+ "		coalesce(cths.cnhi_vldebitos, 0)) - (coalesce(cths.cnhi_vlcreditos, 0) + "
					+ "		coalesce(cths.cnhi_vlimpostos, 0)) ) ) ) "
					+ "		else 0 end as valorContasComHidr "
					+ "	from "
					+ "		faturamento.fatura fatu "
					+ "		inner join faturamento.fatura_item ftit on ftit.fatu_id = fatu.fatu_id "
					+ "		inner join cadastro.cliente clie on clie.clie_id = fatu.clie_id "
					+ "		left outer join faturamento.conta cnta on cnta.cnta_id = ftit.cnta_id "
					+ "		left outer join faturamento.conta_historico cths on cths.cnta_id = ftit.cnta_id "
					+ "		inner join cadastro.localidade loca on (loca.loca_id = cnta.loca_id or loca.loca_id = cths.loca_id) "
					+ "		inner join cadastro.imovel imov on (imov.imov_id = cnta.imov_id or imov.imov_id = cths.imov_id) "
					+ "		inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id "
					+ "		inner join cadastro.municipio muni on muni.muni_id = stcm.muni_id "
					+ "		inner join cadastro.regiao_desenvolvimento rgds on rgds.rdes_id = muni.rdes_id "
					+ "		left join atendimentopublico.ligacao_agua lagu on lagu.lagu_id = imov.imov_id "
					+ "	where "
					+ "		fatu.fatu_amreferencia = "
					+ helper.getMesAnoReferencia()
					+ " "
					+ "		and fatu.clie_id = (select clie_idprogramaespecial from cadastro.sistema_parametros) ";

			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equalsIgnoreCase("")) {
				consulta += "		and loca.loca_id = " + helper.getIdLocalidade()
						+ " ";
			}

			if (helper.getIdRegiaoDesenvolvimento() != null
					&& !helper.getIdRegiaoDesenvolvimento()
							.equalsIgnoreCase("")) {
				consulta += "		and muni.rdes_id = "
						+ helper.getIdRegiaoDesenvolvimento() + " ";
			}

			consulta += ") A "
					+ "group by "
					+ "	A.idRegDes, A.nomeRegDes, A.idLocalidade, A.nomeLocalidade "
					+ "order by " + "	idReg, idLoca";

			retorno = (Collection) session.createSQLQuery(consulta).addScalar(
					"idReg", Hibernate.INTEGER).addScalar("nomeReg",
					Hibernate.STRING).addScalar("idLoca", Hibernate.INTEGER)
					.addScalar("nomeLoca", Hibernate.STRING).addScalar(
							"qtdContaSemHidr", Hibernate.INTEGER).addScalar(
							"valorContasSemHidr", Hibernate.BIG_DECIMAL)
					.addScalar("qtdContaComHidr", Hibernate.INTEGER).addScalar(
							"valorContasComHidr", Hibernate.BIG_DECIMAL).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Integer verificarExistenciaParcelamentoImovel(Integer idImovel)
			throws ErroRepositorioException {

		int retorno = 0;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta += " select count (dc.imov_id) as cont "
				+ " from faturamento.debito_a_cobrar dc "
				+ " where dc.parc_id is not null "
				+ " and dc.dbac_nnprestacaocobradas <> dc.dbac_nnprestacaodebito "
				+ " and dc.imov_id = " + idImovel + " ";
		try {
			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"cont", Hibernate.INTEGER).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<RelatorioColetaMedidorEnergiaHelper> pesquisarRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial,
			String idLocalidadeFinal, String idSetorComercialInicial,
			String idSetorComercialFinal, String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal)
			throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		Query query = null;

		Map parameters = new HashMap();

		try {

			consulta += " select distinct faturamentoGrupo.id, " // 0
					+ " faturamentoGrupo.descricao, " // 1
					+ " imovel.localidade.id, " // 2
					+ " imovel.localidade.descricao, " // 3
					+ " rota.codigo, " // 4
					+ " cliente.nome, " // 5
					+ " imovel.id, " // 6
					+ " localidade.gerenciaRegional, " // 7
					+ " imovel.localidade, " // 8
					+ " setor.codigo, " // 9
					+ " quadra.numeroQuadra, " // 10
					+ " roteiro.numeroLoteImovel, " // 11
					+ " roteiro.numeroSubloteImovel " // 12
					+ " from MovimentoRoteiroEmpresa roteiro "
					+ " inner join roteiro.imovel imovel "
					+ " inner join imovel.clienteImoveis ci "
					+ " inner join ci.cliente cliente "
					+ " inner join imovel.quadra quadra "
					+ " inner join imovel.setorComercial setor "
					+ " inner join imovel.localidade localidade "
					+ " inner join quadra.rota rota "
					+ " inner join rota.faturamentoGrupo faturamentoGrupo "
					+ " where roteiro.anoMesMovimento = faturamentoGrupo.anoMesReferencia "
					+ " and imovel.ligacaoAguaSituacao in (:last) "
					+ " and imovel.ligacaoEsgotoSituacao in (:lest) "
					+ " and ci.dataFimRelacao is null "
					+ " and ci.clienteRelacaoTipo = :cliRelacaoTipo ";

			if (faturamentoGrupo != null && !faturamentoGrupo.equals("")) {

				consulta += " and faturamentoGrupo.id = :faturamentoGrupo ";

				parameters.put("faturamentoGrupo",
						new Integer(faturamentoGrupo));
			}

			if (idLocalidadeInicial != null && idLocalidadeFinal != null
					&& !idLocalidadeInicial.equals("")
					&& !idLocalidadeFinal.equals("")) {

				consulta += " and imovel.localidade.id between :idLocalidadeInicial and :idLocalidadeFinal ";

				parameters.put("idLocalidadeInicial", new Integer(
						idLocalidadeInicial));
				parameters.put("idLocalidadeFinal", new Integer(
						idLocalidadeFinal));

				// Setor Comercial
				if (idSetorComercialInicial != null
						&& idSetorComercialFinal != null
						&& !idSetorComercialInicial.equals("")
						&& !idSetorComercialFinal.equals("")) {

					consulta += " and setor.codigo between :idSetorComercialInicial and :idSetorComercialFinal ";

					parameters.put("idSetorComercialInicial", new Integer(
							idSetorComercialInicial));
					parameters.put("idSetorComercialFinal", new Integer(
							idSetorComercialFinal));
				}

			}

			if (rotaInicial != null && rotaFinal != null
					&& !rotaInicial.equals("") && !rotaFinal.equals("")) {

				consulta += " and rota.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", new Integer(rotaInicial));
				parameters.put("rotaFinal", new Integer(rotaFinal));

			}

			if (sequencialRotaInicial != null && sequencialRotaFinal != null
					&& !sequencialRotaInicial.equals("")
					&& !sequencialRotaFinal.equals("")) {

				consulta += " and imovel.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", new Integer(
						sequencialRotaInicial));
				parameters.put("sequencialRotaFinal", new Integer(
						sequencialRotaFinal));

			}

			consulta += " order by localidade.gerenciaRegional, imovel.localidade, setor.codigo, quadra.numeroQuadra, "
					+ " roteiro.numeroLoteImovel, roteiro.numeroSubloteImovel, imovel.id, faturamentoGrupo.id, "
					+ " faturamentoGrupo.descricao, imovel.localidade.id, imovel.localidade.descricao, "
					+ " rota.codigo, cliente.nome ";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			Collection colecaoLast = new ArrayList();
			colecaoLast.add(LigacaoAguaSituacao.LIGADO);
			colecaoLast.add(LigacaoAguaSituacao.LIGADO_EM_ANALISE);
			colecaoLast.add(LigacaoAguaSituacao.CORTADO);
			colecaoLast.add(LigacaoAguaSituacao.SUPRIMIDO);

			Collection colecaoLest = new ArrayList();
			colecaoLest.add(LigacaoEsgotoSituacao.POTENCIAL);
			colecaoLest.add(LigacaoEsgotoSituacao.LIGADO);
			colecaoLest.add(LigacaoEsgotoSituacao.LIG_FORA_DE_USO);
			colecaoLest.add(LigacaoEsgotoSituacao.TAMPONADO);

			retorno = query.setParameterList("last", colecaoLast)
					.setParameterList("lest", colecaoLest).setInteger(
							"cliRelacaoTipo", ClienteRelacaoTipo.USUARIO)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial,
			String idLocalidadeFinal, String idSetorComercialInicial,
			String idSetorComercialFinal, String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal)
			throws ErroRepositorioException {

		int retorno = 0;

		Session session = HibernateUtil.getSession();

		Query query = null;

		Map parameters = new HashMap();

		String consulta = "";

		try {

			consulta += " select count (roteiro.imovel.id) as cont "
					+ " from MovimentoRoteiroEmpresa roteiro "
					+ " inner join roteiro.imovel imovel "
					+ " inner join imovel.quadra quadra "
					+ " inner join imovel.setorComercial setor "
					+ " inner join quadra.rota rota "
					+ " inner join rota.faturamentoGrupo faturamentoGrupo "
					+ " inner join rota.empresa empresa "
					+ " where roteiro.anoMesMovimento = faturamentoGrupo.anoMesReferencia "
					+ " and imovel.ligacaoAguaSituacao in (:last) "
					+ " and imovel.ligacaoEsgotoSituacao in (:lest) ";

			if (faturamentoGrupo != null && !faturamentoGrupo.equals("")) {

				consulta += " and faturamentoGrupo.id = :faturamentoGrupo ";
				parameters.put("faturamentoGrupo",
						new Integer(faturamentoGrupo));
			}

			if (idLocalidadeInicial != null && idLocalidadeFinal != null
					&& !idLocalidadeInicial.equals("")
					&& !idLocalidadeFinal.equals("")) {

				consulta += " and imovel.localidade.id between :idLocalidadeInicial and :idLocalidadeFinal ";

				parameters.put("idLocalidadeInicial", new Integer(
						idLocalidadeInicial));
				parameters.put("idLocalidadeFinal", new Integer(
						idLocalidadeFinal));

				// Setor Comercial
				if (idSetorComercialInicial != null
						&& idSetorComercialFinal != null
						&& !idSetorComercialInicial.equals("")
						&& !idSetorComercialFinal.equals("")) {

					consulta += " and setor.codigo between :idSetorComercialInicial and :idSetorComercialFinal ";

					parameters.put("idSetorComercialInicial", new Integer(
							idSetorComercialInicial));
					parameters.put("idSetorComercialFinal", new Integer(
							idSetorComercialFinal));
				}

			}

			if (rotaInicial != null && rotaFinal != null
					&& !rotaInicial.equals("") && !rotaFinal.equals("")) {

				consulta += " and rota.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", new Integer(rotaInicial));
				parameters.put("rotaFinal", new Integer(rotaFinal));

			}

			if (sequencialRotaInicial != null && sequencialRotaFinal != null
					&& !sequencialRotaInicial.equals("")
					&& !sequencialRotaFinal.equals("")) {

				consulta += " and imovel.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", new Integer(
						sequencialRotaInicial));
				parameters.put("sequencialRotaFinal", new Integer(
						sequencialRotaFinal));
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			Collection colecaoLast = new ArrayList();
			colecaoLast.add(LigacaoAguaSituacao.LIGADO);
			colecaoLast.add(LigacaoAguaSituacao.LIGADO_EM_ANALISE);
			colecaoLast.add(LigacaoAguaSituacao.CORTADO);
			colecaoLast.add(LigacaoAguaSituacao.SUPRIMIDO);

			Collection colecaoLest = new ArrayList();
			colecaoLest.add(LigacaoEsgotoSituacao.POTENCIAL);
			colecaoLest.add(LigacaoEsgotoSituacao.LIGADO);
			colecaoLest.add(LigacaoEsgotoSituacao.LIG_FORA_DE_USO);
			colecaoLest.add(LigacaoEsgotoSituacao.TAMPONADO);

			retorno = (Integer) query.setParameterList("last", colecaoLast)
					.setParameterList("lest", colecaoLest).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Integer> pesquisarIdsImoveisDoSetorComercial(
			Integer idSetor, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException {
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " SELECT id " + " FROM Imovel"
					+ " WHERE setorComercial = :idSetor" + " ORDER BY id";

			retorno = (Collection<Integer>) session.createQuery(consulta)
					.setInteger("idSetor", idSetor).setFirstResult(
							quantidadeInicio).setMaxResults(quantidadeMaxima)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void atualizarCodigoDebitoAutomatico(Integer idImovel,
			Integer codigoDebitoAutomatico) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "UPDATE Imovel "
				+ "set codigoDebitoAutomatico =:codigoDebitoAutomatico where id = :idImovel ";

		try {

			session.createQuery(consulta).setInteger("codigoDebitoAutomatico",
					codigoDebitoAutomatico).setInteger("idImovel", idImovel)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}

	}

	public byte[] baixarNovaVersaoJad() throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		byte[] retorno = null;

		try {
			consulta = " select vemo_arquivojad as jad from cadastro.versao_mobile order by replace( vemo_nnversao, '.', '' ) desc";

			retorno = (byte[]) session.createSQLQuery(consulta).addScalar(
					"jad", Hibernate.BINARY).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		/*
		 * if ( retorno != null) {
		 * 
		 * StringBuilder sb = new StringBuilder();
		 * 
		 * try { sb = new StringBuilder( retorno.toString() ); } catch
		 * (IOException e) { throw new ErroRepositorioException( "Erro em
		 * Transformar Array de Byte em Object"); } catch
		 * (ClassNotFoundException e) { throw new ErroRepositorioException(
		 * "Erro em Transformar Array de Byte em Object"); } retorno =
		 * sb.toString().getBytes(); }
		 */

		return retorno;
	}

	public byte[] baixarNovaVersaoJar() throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		byte[] retorno = null;

		try {
			consulta = " select vemo_arquivojar as jar from cadastro.versao_mobile order by replace( vemo_nnversao, '.', '' ) desc";

			retorno = (byte[]) session.createSQLQuery(consulta).addScalar(
					"jar", Hibernate.BINARY).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel)
			throws ErroRepositorioException {

		boolean retorno = false;

		List resultado;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT  i.imov_id as idImovel, "
					+ "   cs.cbst_dscobrancasituacao as situacao"
					+ " FROM cadastro.imovel i"
					+ " INNER JOIN faturamento.conta c on c.imov_id = i.imov_id"
					+ " INNER JOIN cobranca.cobranca_situacao cs on cs.cmrv_id = c.cmrv_id"
					+ " WHERE cs.cbst_id = :cobrancaSituacao"
					+ " and i.imov_id = :idImovel";

			resultado = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("situacao", Hibernate.STRING)
					.setInteger("cobrancaSituacao",
							CobrancaSituacao.EM_COBRANCA_JUDICIAL).setInteger(
							"idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		if (resultado != null && resultado.size() > 0) {
			// Imovel esta em cobranca judicial
			retorno = true;

		} else {
			// imovel nao esta em cobranca judicial
			retorno = false;
		}

		return retorno;

	}

	public boolean verificarSituacaoImovelNegativacao(Integer idImovel)
			throws ErroRepositorioException {

		boolean retorno = false;

		List resultado;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT  i.imov_id as idImovel "
					+ " FROM cadastro.imovel i"
					+ " INNER JOIN cobranca.negatd_movimento_reg neg on neg.imov_id = i.imov_id"
					+ " WHERE (neg.nmrg_icaceito is null or neg.nmrg_icaceito = 1) "
					+ " and neg.nmrg_idreginclusao is null and neg.nmrg_cdexclusaotipo is null "
					+ " and i.imov_id = :idImovel";

			resultado = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).setInteger("idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		if (resultado != null && resultado.size() > 0) {
			// Imovel esta em negativacao
			retorno = true;

		} else {
			// imovel nao esta em negativacao
			retorno = false;
		}

		return retorno;

	}

	public Integer inserirCadastroEmailCliente(Integer idCliente,
			String nomeClienteAnterior, String cpfAnterior,
			String cnpjAnterior, String emailAnterior, String nomeSolicitante,
			String cpfSolicitante, String nomeClienteAtual,
			String cpfClienteAtual, String cnpjClienteAtual, String emailAtual)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Integer retorno;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();
			String sequence = Util
					.obterNextValSequence("cadastro.sequence_email_client_alterada");
			insert = "insert into cadastro.email_client_alterada( ecla_id, "
					+ " clie_id, " + " ecla_nmclienteanterior, "
					+ " ecla_nncpfanterior, " + " ecla_nncnpjanterior, "
					+ " ecla_dsemailanterior, " + " ecla_nmsolicitante, "
					+ " ecla_nncpfsolicitante, " + " ecla_nmclienteatual, "
					+ " ecla_nncpfatual, " + " ecla_nncnpjatual, "
					+ " ecla_dsemailatual, " + " ecla_tmconfirmacaoonline, "
					+ " ecla_tmsolicitacaoonline, "
					+ " ecla_tmultimaalteracao ) " + "values ( " + sequence
					+ ", " + idCliente + ", " + nomeClienteAnterior + ", "
					+ cpfAnterior + ", " + cnpjAnterior + ", " + emailAnterior
					+ ", " + nomeSolicitante + ", " + cpfSolicitante + ", "
					+ nomeClienteAtual + ", " + cpfClienteAtual + ", "
					+ cnpjClienteAtual + ", " + emailAtual + ", " + null + ", "
					+ Util.obterSQLDataAtual() + ", "
					+ Util.obterSQLDataAtual() + ")";

			retorno = (Integer) stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}

		return retorno;

	}

	public void atualizarSequenciaRotaImovel(RotaAtualizacaoSeq seq)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String delete = "update Imovel as imovel "
					+ "set imovel.numeroSequencialRota = :sequencial "
					+ "where imovel.id = :idImovel";

			session.createQuery(delete).setInteger("sequencial",
					seq.getSequencialRota() * 10).setInteger("idImovel",
					seq.getImovel().getId()).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
	}

	public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico(
			Integer idImovel) throws ErroRepositorioException {

		ClienteImovel resultado;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT  clienteImovel "
					+ " FROM ClienteImovel clienteImovel "
					+ " INNER JOIN clienteImovel.cliente cliente "
					+ " INNER JOIN clienteImovel.imovel imovel "
					+ " LEFT JOIN cliente.clienteTipo clienteTipo "
					+ " WHERE clienteImovel.clienteRelacaoTipo = :responsavel "
					+ " and clienteImovel.dataFimRelacao is NULL "
					+ " and clienteTipo.esferaPoder.id in ("
					+ EsferaPoder.ESTADUAL + "," + EsferaPoder.MUNICIPAL + ","
					+ EsferaPoder.FEDERAL + ") "
					+ " and clienteImovel.imovel.id = :idImovel";

			resultado = (ClienteImovel) session.createQuery(consulta)
					.setInteger("responsavel", ClienteRelacaoTipo.RESPONSAVEL)
					.setInteger("idImovel", idImovel).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return resultado;

	}

	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorUsuario(
			GerarRelatorioAlteracoesSistemaColunaHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = "SELECT us.unid_dsunidade as unidSuperior,"
					+ " uo.unid_dsunidade as unidOrganizacional,"
					+ " u.usur_nmusuario as usuario,"
					+ " ms.meso_dsmeiosolicitacao as meio,"
					+ " count(*) as contador"
					+ " FROM seguranca.tab_linha_col_alteracao tbco"
					+ " INNER JOIN seguranca.tabela_coluna tc on tc.tbco_id = tbco.tbco_id"
					+ " INNER JOIN seguranca.tabela_linha_alteracao tla on tla.tbla_id = tbco.tbla_id"
					+ " INNER JOIN seguranca.operacao_efetuada oe on oe.opef_id = tla.tref_id"
					+ " INNER JOIN seguranca.operacao o on o.oper_id = oe.oper_id"
					+ " INNER JOIN seguranca.usuario_alteracao ua on ua.tref_id = oe.opef_id"
					+ " INNER JOIN seguranca.usuario u on u.usur_id = ua.usis_id"
					+ " INNER JOIN cadastro.unidade_organizacional uo on uo.unid_id = u.unid_id"
					+ " LEFT JOIN cadastro.unidade_organizacional us on us.unid_id = uo.unid_idsuperior"
					+ " INNER JOIN atendimentopublico.meio_solicitacao ms on ms.meso_id = uo.meso_id"
					+ " WHERE tbco.tbco_id = :idColuna"
					+ " and to_date(to_char(tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoInicial and :periodoFinal"
					+ " and ";

			if (helper.getIdMeioSolicitacao() != null
					&& !helper.getIdMeioSolicitacao().equals("-1")
					&& !helper.getIdMeioSolicitacao().equals("0")) {
				consulta += " uo.meso_id = :meioSolicitacao and ";
				parameters.put("meioSolicitacao", Integer.parseInt(helper
						.getIdMeioSolicitacao()));
			}
			if (helper.getIdFuncionalidade() != null
					&& !helper.getIdFuncionalidade().equals("")) {
				consulta += " o.fncd_id = :idFuncionalidade and ";
				parameters.put("idFuncionalidade", Integer.parseInt(helper
						.getIdFuncionalidade()));
			}
			if (helper.getIdOperacao() != null
					&& !helper.getIdOperacao().equals("")) {
				consulta += " o.oper_id = :idOperacao and ";
				parameters.put("idOperacao", Integer.parseInt(helper
						.getIdOperacao()));
			}
			if (helper.getIdUnidadeSuperior() != null
					&& !helper.getIdUnidadeSuperior().equals("")) {
				consulta += " uo.unid_idsuperior = :idUnidadeSuperior and ";
				parameters.put("idUnidadeSuperior", Integer.parseInt(helper
						.getIdUnidadeSuperior()));
			}
			if (!Util.isVazioOrNulo(helper.getColecaoUnidadeOrganizacional())) {
				consulta += " uo.unid_id in ( :idsUnidadeOrganizacional ) and ";
				parameters.put("idsUnidadeOrganizacional", helper
						.getColecaoUnidadeOrganizacional());
			}
			if (helper.getIdUsuario() != null
					&& !helper.getIdUsuario().equals("")) {
				consulta += " u.usur_id = :idUsuario and ";
				parameters.put("idUsuario", Integer.parseInt(helper
						.getIdUsuario()));
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY us.unid_dsunidade,uo.unid_dsunidade,ms.meso_dsmeiosolicitacao,u.usur_nmusuario";
			consulta += " ORDER BY us.unid_dsunidade,uo.unid_dsunidade,ms.meso_dsmeiosolicitacao,u.usur_nmusuario";

			query = session.createSQLQuery(consulta).addScalar("unidSuperior",
					Hibernate.STRING).addScalar("unidOrganizacional",
					Hibernate.STRING).addScalar("usuario", Hibernate.STRING)
					.addScalar("meio", Hibernate.STRING).addScalar("contador",
							Hibernate.INTEGER).setInteger("idColuna",
							Integer.parseInt(helper.getIdColuna()))
					.setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorLocalidade(
			GerarRelatorioAlteracoesSistemaColunaHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT g.greg_nmregional as gerencia,\n "
					+ "  un.uneg_nmunidadenegocio as unidade,\n "
					+ "   l.loca_nmlocalidade as localidade,\n  "
					+ "   ms.meso_dsmeiosolicitacao as meio,\n "
					+ "  count(*) as contador \n"
					+ "  FROM seguranca.tab_linha_col_alteracao tbco \n"
					+ "   INNER JOIN seguranca.tabela_coluna tc on tc.tbco_id = tbco.tbco_id \n"
					+ "   INNER JOIN seguranca.tabela_linha_alteracao tla on tla.tbla_id = tbco.tbla_id \n"
					+ "   INNER JOIN seguranca.operacao_efetuada oe on oe.opef_id = tla.tref_id \n"
					+ "  INNER JOIN seguranca.operacao o on o.oper_id = oe.oper_id \n"
					+ "   INNER JOIN seguranca.usuario_alteracao ua on ua.tref_id = oe.opef_id \n"
					+ "   INNER JOIN seguranca.usuario u on u.usur_id = ua.usis_id \n"
					+ "   INNER JOIN cadastro.imovel ic on ic.imov_id = tla.tbla_id1 \n"
					+ "   LEFT JOIN cadastro.imovel i on i.imov_id = tla.tbla_id2 \n"
					+ "  INNER JOIN cadastro.localidade l on l.loca_id = ic.loca_id  \n"
					+ "   INNER JOIN cadastro.unidade_negocio un on un.uneg_id = l.uneg_id \n"
					+ "   INNER JOIN cadastro.gerencia_regional g on g.greg_id = un.greg_id \n"
					+ "   INNER JOIN cadastro.unidade_organizacional uo on uo.loca_id = l.loca_id \n"
					+ "   INNER JOIN atendimentopublico.meio_solicitacao ms on ms.meso_id = uo.meso_id \n"
					+ " WHERE tbco.tbco_id = :idColuna \n"
					+ " and to_date(to_char(tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoInicial and :periodoFinal \n"
					+ " and ";

			if (helper.getIdMeioSolicitacao() != null
					&& !helper.getIdMeioSolicitacao().equals("-1")
					&& !helper.getIdMeioSolicitacao().equals("0")) {
				consulta += " uo.meso_id = :meioSolicitacao and ";
				parameters.put("meioSolicitacao", Integer.parseInt(helper
						.getIdMeioSolicitacao()));
			}
			if (helper.getIdFuncionalidade() != null
					&& !helper.getIdFuncionalidade().equals("")) {
				consulta += " o.fncd_id = :idFuncionalidade and ";
				parameters.put("idFuncionalidade", Integer.parseInt(helper
						.getIdFuncionalidade()));
			}
			if (helper.getIdOperacao() != null
					&& !helper.getIdOperacao().equals("")) {
				consulta += " o.oper_id = :idOperacao and ";
				parameters.put("idOperacao", Integer.parseInt(helper
						.getIdOperacao()));
			}
			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("")) {
				consulta += " g.greg_id = :idGerenciaRegional and ";
				parameters.put("idGerenciaRegional", Integer.parseInt(helper
						.getIdGerenciaRegional()));
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("")) {
				consulta += " un.uneg_id = :idUnidadeNegocio and ";
				parameters.put("idUnidadeNegocio", Integer.parseInt(helper
						.getIdUnidadeNegocio()));
			}
			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equals("")) {
				consulta += " l.loca_id = :idLocalidade and ";
				parameters.put("idLocalidade", Integer.parseInt(helper
						.getIdLocalidade()));
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY g.greg_nmregional,un.uneg_nmunidadenegocio,l.loca_nmlocalidade,ms.meso_dsmeiosolicitacao \n";
			consulta += " ORDER BY g.greg_nmregional,un.uneg_nmunidadenegocio,l.loca_nmlocalidade,ms.meso_dsmeiosolicitacao";

			query = session.createSQLQuery(consulta).addScalar("gerencia",
					Hibernate.STRING).addScalar("unidade", Hibernate.STRING)
					.addScalar("localidade", Hibernate.STRING).addScalar(
							"meio", Hibernate.STRING).addScalar("contador",
							Hibernate.INTEGER).setInteger("idColuna",
							Integer.parseInt(helper.getIdColuna()))
					.setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public boolean verificarRelacaoColuna(Integer idColuna)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Integer retornoConsulta = null;
		boolean retorno = false;
		try {
			String consulta = "SELECT tbco_id as coluna"
					+ " FROM seguranca.tabela_coluna"
					+ " WHERE tabe_id = (SELECT tabe_id"
					+ " FROM seguranca.tabela_coluna WHERE tbco_id  = :idColuna)"
					+ " and (tbco_nmcoluna like 'clie_id' or tbco_nmcoluna like 'imov_id')";

			retornoConsulta = (Integer) session.createSQLQuery(consulta)
					.addScalar("coluna", Hibernate.INTEGER).setInteger(
							"idColuna", idColuna).setMaxResults(1)
					.uniqueResult();

			if (retornoConsulta != null) {
				retorno = true;
			}

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		try {
			consulta = " SELECT imovel.id,"
					+ " localidade.id, "
					+ " emailClienteAlterado.nomeClienteAnterior, "
					+ " emailClienteAlterado.nomeClienteAtual, "
					+ " emailClienteAlterado.cpfAnterior, "
					+ " emailClienteAlterado.cpfAtual, "
					+ " emailClienteAlterado.cnpjAnterior, "
					+ " emailClienteAlterado.cnpjAtual, "
					+ " emailClienteAlterado.emailAnterior, "
					+ " emailClienteAlterado.emailAtual, "
					+ " emailClienteAlterado.confirmacaoOnline, "
					+ " emailClienteAlterado.nomeSolicitante, "
					+ " emailClienteAlterado.cpfSolicitante, "
					+ " emailClienteAlterado.telefoneContato, "
					+ " unidadeNegocio.nome, "
					+ " gerenciaRegional.nome, "
					+ " emailClienteAlterado.confirmacaoOnline, "
					+ " emailClienteAlterado.idCliente.id "
					+ "FROM EmailClienteAlterado emailClienteAlterado "
					+ "INNER JOIN emailClienteAlterado.idCliente cliente "
					+ "INNER JOIN cliente.clienteImoveis clienteImovel "
					+ "INNER JOIN clienteImovel.imovel imovel "
					+ "INNER JOIN imovel.localidade localidade "
					+ "INNER JOIN localidade.unidadeNegocio  unidadeNegocio "
					+ "INNER JOIN unidadeNegocio.gerenciaRegional gerenciaRegional "
					+

					" WHERE emailClienteAlterado.confirmacaoOnline BETWEEN :periodoReferenciaInicial AND :periodoReferenciaFinal "
					+ " and clienteImovel.clienteRelacaoTipo.id = 2 and clienteImovel.dataFimRelacao is null "
					+ " and imovel.imovelContaEnvio = :idImovelContaEnvio ";

			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("-1")) {
				consulta += " and gerenciaRegional.id = "
						+ helper.getIdGerenciaRegional();
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("-1")) {
				consulta += " and unidadeNegocio.id = "
						+ helper.getIdUnidadeNegocio();
			}
			if (helper.getIdLocalidadeInicial() != null
					&& !helper.getIdLocalidadeInicial().equals("")
					&& helper.getIdLocalidadeFinal() != null
					&& !helper.getIdLocalidadeFinal().equals("")) {

				consulta += " and localidade.id BETWEEN "
						+ helper.getIdLocalidadeInicial() + " and "
						+ helper.getIdLocalidadeFinal();
			}

			consulta += " order by emailClienteAlterado.confirmacaoOnline,gerenciaRegional.id,unidadeNegocio.id,localidade.id ";

			query = session.createQuery(consulta).setTimestamp(
					"periodoReferenciaInicial",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaInicial()
							+ " 00:00:00")).setTimestamp(
					"periodoReferenciaFinal",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaFinal()
							+ " 23:59:59")).setInteger("idImovelContaEnvio",
					ImovelContaEnvio.ENVIAR_PARA_IMOVEL_E_PARA_EMAIL);

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		try {
			consulta = " SELECT SUM(CASE WHEN emailClienteAlterado.nomeClienteAtual != nvl(emailClienteAlterado.nomeClienteAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " SUM(CASE WHEN emailClienteAlterado.cpfAtual != nvl(emailClienteAlterado.cpfAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " SUM(CASE WHEN emailClienteAlterado.cnpjAtual != nvl(emailClienteAlterado.cnpjAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " SUM(CASE WHEN emailClienteAlterado.emailAtual != nvl(emailClienteAlterado.emailAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " COUNT(distinct cliente.id)"
					+ "FROM EmailClienteAlterado emailClienteAlterado "
					+ "INNER JOIN emailClienteAlterado.idCliente cliente "
					+ "INNER JOIN cliente.clienteImoveis clienteImovel "
					+ "INNER JOIN clienteImovel.imovel imovel "
					+ "INNER JOIN imovel.localidade localidade "
					+ "INNER JOIN localidade.unidadeNegocio  unidadeNegocio "
					+ "INNER JOIN unidadeNegocio.gerenciaRegional gerenciaRegional "
					+

					" WHERE emailClienteAlterado.confirmacaoOnline BETWEEN :periodoReferenciaInicial AND :periodoReferenciaFinal "
					+ " and clienteImovel.clienteRelacaoTipo = 2 and clienteImovel.dataFimRelacao is null ";

			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("-1")) {
				consulta += " and gerenciaRegional.id = "
						+ helper.getIdGerenciaRegional();
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("-1")) {
				consulta += " and unidadeNegocio.id = "
						+ helper.getIdUnidadeNegocio();
			}
			if (helper.getIdLocalidadeInicial() != null
					&& !helper.getIdLocalidadeInicial().equals("")
					&& helper.getIdLocalidadeFinal() != null
					&& !helper.getIdLocalidadeFinal().equals("")) {

				consulta += " and localidade.id BETWEEN "
						+ helper.getIdLocalidadeInicial() + " and "
						+ helper.getIdLocalidadeFinal();

			}

			// consulta+=" GROUP BY unidadeNegocio.nome,gerenciaRegional.nome";

			query = session.createQuery(consulta).setTimestamp(
					"periodoReferenciaInicial",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaInicial()
							+ " 00:00:00")).setTimestamp(
					"periodoReferenciaFinal",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaFinal()
							+ " 23:59:59"));

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer countRelatorioAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
			throws ErroRepositorioException {

		Integer retorno = new Integer(0);
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		// Date dataInicio =
		// Util.converteStringParaDate(helper.getPeriodoReferenciaInicial());
		// Date dataFim =
		// Util.converteStringParaDate(helper.getPeriodoReferenciaFinal());

		try {
			consulta = " SELECT count(*) "
					+ "FROM EmailClienteAlterado emailClienteAlterado "
					+ "INNER JOIN emailClienteAlterado.idCliente cliente "
					+ "INNER JOIN cliente.clienteImoveis clienteImovel "
					+ "INNER JOIN clienteImovel.imovel imovel "
					+ "INNER JOIN imovel.localidade localidade "
					+ "INNER JOIN localidade.unidadeNegocio  unidadeNegocio "
					+ "INNER JOIN unidadeNegocio.gerenciaRegional gerenciaRegional "
					+

					" WHERE emailClienteAlterado.confirmacaoOnline BETWEEN :periodoReferenciaInicial AND :periodoReferenciaFinal "
					+ " AND clienteImovel.clienteRelacaoTipo.id = 2 and clienteImovel.dataFimRelacao is null ";

			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("-1")) {
				consulta += " and gerenciaRegional.id = "
						+ helper.getIdGerenciaRegional();
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("-1")) {
				consulta += " and unidadeNegocio.id = "
						+ helper.getIdUnidadeNegocio();
			}
			if (helper.getIdLocalidadeInicial() != null
					&& !helper.getIdLocalidadeInicial().equals("")
					&& helper.getIdLocalidadeFinal() != null
					&& !helper.getIdLocalidadeFinal().equals("")) {

				consulta += " and localidade.id BETWEEN "
						+ helper.getIdLocalidadeInicial() + " and "
						+ helper.getIdLocalidadeFinal();

			}

			query = session.createQuery(consulta).setTimestamp(
					"periodoReferenciaInicial",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaInicial()
							+ " 00:00:00")).setTimestamp(
					"periodoReferenciaFinal",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaFinal()
							+ " 23:59:59"));

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
 	public Integer pesquisarIdRotaQuadra(Integer idQuadra) throws ErroRepositorioException {
 		Integer retorno = null;
 		
 		Session session = HibernateUtil.getSession();
 		String consulta = null;
 		
 		try{
 			consulta = " select q.rota.id " 
 					+ " from Quadra q " 
 					+ " where q.id = :idQuadra ";
 			
 			retorno = (Integer) session.createQuery(consulta)
 			.setInteger("idQuadra", idQuadra)
 			.setMaxResults(1).uniqueResult();
 			
 		}catch(HibernateException e) {
 			throw new ErroRepositorioException(e,"Erro no hibernate");
 		}finally {
 			HibernateUtil.closeSession(session);
 		}
 		return retorno;
 	}
 	
 	public Collection pesquisarEsferaPoder() throws ErroRepositorioException {
 		Collection retorno = null;
 		
 		Session session = HibernateUtil.getSession();
 		String consulta = null;
 		
 		try{
 			consulta = " select ep " 
 					+ " from EsferaPoder ep " 
 					+ " where ep.indicadorUso = :indicadorUso ";
 			
 			retorno = (Collection) session.createQuery(consulta)
 			.setInteger("indicadorUso", ConstantesSistema.SIM.intValue()).list();
 			
 		}catch(HibernateException e) {
 			throw new ErroRepositorioException(e,"Erro no hibernate");
 		}finally {
 			HibernateUtil.closeSession(session);
 		}
 		return retorno;
 	}

	public Collection pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select iia "
					+ " from ImovelInscricaoAlterada iia "
					+ " inner join fetch iia.imovel imov "
					+ " inner join fetch imov.clienteImoveis cliImo "
					+ " inner join fetch cliImo.cliente clie "
					+ " inner join iia.localidadeAtual locaAtual "
					+ " inner join fetch iia.setorComercialAtual setorAtual "
					+ " inner join fetch iia.quadraAtual quadraAtual "
					+ " inner join quadraAtual.rota rotaAtual "
					+ " inner join iia.localidadeAnterior locaAnterior "
					+ " inner join fetch iia.setorComercialAnterior setorAnterior "
					+ " inner join fetch iia.quadraAnterior quadraAnterior "
					+ " where 1=1 "
					+ " and cliImo.clienteRelacaoTipo = 2 "
					+ " and cliImo.dataFimRelacao is null ";
					

			parameters.put("dataInicial", relatorioHelper.getDataInicio());
			parameters.put("dataFinal", relatorioHelper.getDataFim());

			// Tipo da Consulta

			// Im�veis alterados com sucesso.
			if (relatorioHelper.getEscolhaRelatorio().intValue() == 1) {

				consulta += " and iia.indicadorAtualizado = 1 "
						  + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Im�veis sem altera��o devido a erro.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 2) {

				consulta += " and iia.indicadorErroAlteracao = 1 "
						  + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Im�vel pendente de altera��o.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 3) {

				consulta += " and iia.indicadorAtualizado = 2 and iia.indicadorErroAlteracao is null "
						  + " and iia.ultimaAlteracao between :dataInicial and :dataFinal ";
			}

			// Localidade
			if (relatorioHelper.getLocalidadeInicial() != null
					&& relatorioHelper.getLocalidadeFinal() != null) {

				parameters.put("localidadeInicial", relatorioHelper
						.getLocalidadeInicial());
				parameters.put("localidadeFinal", relatorioHelper
						.getLocalidadeFinal());

				consulta += " and iia.localidadeAtual between :localidadeInicial and :localidadeFinal ";
			} else if (relatorioHelper.getLocalidadeInicial() != null) {

				parameters.put("localidadeInicial", relatorioHelper
						.getLocalidadeInicial());

				consulta += " and iia.localidadeAtual =:localidadeInicial ";
			}

			// Setor Comercial
			if (relatorioHelper.getSetorComercialInicial() != null
					&& relatorioHelper.getSetorComercialFinal() != null) {

				parameters.put("setoComercialInicial", relatorioHelper
						.getSetorComercialInicial());
				parameters.put("setoComercialFinal", relatorioHelper
						.getSetorComercialFinal());

				consulta += " and iia.setorComercialAtual between :setoComercialInicial and :setoComercialFinal ";
			} else if (relatorioHelper.getSetorComercialInicial() != null) {

				parameters.put("setoComercialInicial", relatorioHelper
						.getSetorComercialInicial());

				consulta += " and iia.setorComercialAtual =:setoComercialInicial ";
			}

			// Quadra
			if (relatorioHelper.getQuadraInicial() != null
					&& relatorioHelper.getQuadraFinal() != null) {

				parameters.put("quadraInicial", relatorioHelper
						.getQuadraInicial());
				parameters.put("quadraFinal", relatorioHelper.getQuadraFinal());

				consulta += " and iia.quadraAtual between :quadraInicial and :quadraFinal ";
			} else if (relatorioHelper.getQuadraInicial() != null) {

				parameters.put("quadraInicial", relatorioHelper
						.getQuadraInicial());

				consulta += " and iia.quadraAtual =:quadraInicial ";
			}

			// Lote
			if (relatorioHelper.getLoteInicial() != null
					&& relatorioHelper.getLoteFinal() != null) {

				parameters.put("loteInicial", relatorioHelper.getLoteInicial());
				parameters.put("loteFinal", relatorioHelper.getLoteFinal());

				consulta += " and iia.loteAtual between :loteInicial and :loteFinal ";
			} else if (relatorioHelper.getLoteInicial() != null) {

				parameters.put("loteInicial", relatorioHelper.getLoteInicial());

				consulta += " and iia.loteAtual =:loteInicial ";
			}

			// Sub-Lote
			if (relatorioHelper.getSubLoteInicial() != null
					&& relatorioHelper.getSubLoteFinal() != null) {

				parameters.put("subLoteInicial", relatorioHelper
						.getSubLoteInicial());
				parameters.put("subLoteFinal", relatorioHelper
						.getSubLoteFinal());

				consulta += " and iia.subLoteAtual between :subLoteInicial and :subLoteFinal ";
			} else if (relatorioHelper.getSubLoteInicial() != null) {

				parameters.put("subLoteInicial", relatorioHelper
						.getSubLoteInicial());

				consulta += " and iia.subLoteAtual =:subLoteInicial ";
			}

			consulta += " order by locaAtual.id, setorAtual.codigo, "
					+ " rotaAtual.codigo, imov.numeroSequencialRota, quadraAtual.numeroQuadra, "
					+ " iia.loteAtual, iia.subLoteAtual ";

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper)
			throws ErroRepositorioException {

		Integer retorno = new Integer(0);

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " SELECT count(iia.id) "
					+ " from ImovelInscricaoAlterada iia "
					+ " inner join iia.imovel imov "
					+ " inner join imov.clienteImoveis cliImo "
					+ " inner join cliImo.cliente clie "
					+ " inner join iia.localidadeAtual locaAtual "
					+ " inner join iia.setorComercialAtual setorAtual "
					+ " inner join iia.quadraAtual quadraAtual "
					+ " inner join iia.localidadeAnterior locaAnterior "
					+ " inner join iia.setorComercialAnterior setorAnterior "
					+ " inner join iia.quadraAnterior quadraAnterior "
					+ " where 1=1 "
					+ " and cliImo.clienteRelacaoTipo = 2 "
					+ " and cliImo.dataFimRelacao is null ";

			parameters.put("dataInicial", relatorioHelper.getDataInicio());
			parameters.put("dataFinal", relatorioHelper.getDataFim());

			// Tipo da Consulta

			// Im�veis alterados com sucesso.
			if (relatorioHelper.getEscolhaRelatorio().intValue() == 1) {

				consulta += " and iia.indicadorAtualizado = 1 "
					      + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Im�veis sem altera��o devido a erro.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 2) {

				consulta += " and iia.indicadorErroAlteracao = 1 "
					      + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Im�vel pendente de altera��o.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 3) {

				consulta += " and iia.indicadorAtualizado = 2 and iia.indicadorErroAlteracao is null "
					      + " and iia.ultimaAlteracao between :dataInicial and :dataFinal ";
			}

			// Localidade
			if (relatorioHelper.getLocalidadeInicial() != null
					&& relatorioHelper.getLocalidadeFinal() != null) {

				parameters.put("localidadeInicial", relatorioHelper
						.getLocalidadeInicial());
				parameters.put("localidadeFinal", relatorioHelper
						.getLocalidadeFinal());

				consulta += " and iia.localidadeAtual between :localidadeInicial and :localidadeFinal ";
			} else if (relatorioHelper.getLocalidadeInicial() != null) {

				parameters.put("localidadeInicial", relatorioHelper
						.getLocalidadeInicial());

				consulta += " and iia.localidadeAtual =:localidadeInicial ";
			}

			// Setor Comercial
			if (relatorioHelper.getSetorComercialInicial() != null
					&& relatorioHelper.getSetorComercialFinal() != null) {

				parameters.put("setoComercialInicial", relatorioHelper
						.getSetorComercialInicial());
				parameters.put("setoComercialFinal", relatorioHelper
						.getSetorComercialFinal());

				consulta += " and iia.setorComercialAtual between :setoComercialInicial and :setoComercialFinal ";
			} else if (relatorioHelper.getSetorComercialInicial() != null) {

				parameters.put("setoComercialInicial", relatorioHelper
						.getSetorComercialInicial());

				consulta += " and iia.setorComercialAtual =:setoComercialInicial ";
			}

			// Quadra
			if (relatorioHelper.getQuadraInicial() != null
					&& relatorioHelper.getQuadraFinal() != null) {

				parameters.put("quadraInicial", relatorioHelper
						.getQuadraInicial());
				parameters.put("quadraFinal", relatorioHelper.getQuadraFinal());

				consulta += " and iia.quadraAtual between :quadraInicial and :quadraFinal ";
			} else if (relatorioHelper.getQuadraInicial() != null) {

				parameters.put("quadraInicial", relatorioHelper
						.getQuadraInicial());

				consulta += " and iia.quadraAtual =:quadraInicial ";
			}

			// Lote
			if (relatorioHelper.getLoteInicial() != null
					&& relatorioHelper.getLoteFinal() != null) {

				parameters.put("loteInicial", relatorioHelper.getLoteInicial());
				parameters.put("loteFinal", relatorioHelper.getLoteFinal());

				consulta += " and iia.loteAtual between :loteInicial and :loteFinal ";
			} else if (relatorioHelper.getLoteInicial() != null) {

				parameters.put("loteInicial", relatorioHelper.getLoteInicial());

				consulta += " and iia.loteAtual =:loteInicial ";
			}

			// Sub-Lote
			if (relatorioHelper.getSubLoteInicial() != null
					&& relatorioHelper.getSubLoteFinal() != null) {

				parameters.put("subLoteInicial", relatorioHelper
						.getSubLoteInicial());
				parameters.put("subLoteFinal", relatorioHelper
						.getSubLoteFinal());

				consulta += " and iia.subLoteAtual between :subLoteInicial and :subLoteFinal ";
			} else if (relatorioHelper.getSubLoteInicial() != null) {

				parameters.put("subLoteInicial", relatorioHelper
						.getSubLoteInicial());

				consulta += " and iia.subLoteAtual =:subLoteInicial ";
			}

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorUsuario(
			GerarRelatorioAlteracoesCpfCnpjHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT usur.usur_nmusuario AS nome, usur.usur_nmlogin AS login, "
					+ " unid.unid_dsunidade AS unidade, meso.meso_dsmeiosolicitacao AS meio, "
					+ " count(CASE WHEN tbca.tbco_id = 271 THEN tbca.tbca_id END) contadorCpf, "
					+ " count(CASE WHEN tbca.tbco_id = 275 THEN tbca.tbca_id END) contadorCnpj, "
					+ " count(*) AS contador "
					+ " FROM seguranca.tab_linha_col_alteracao tbca "
					+ " INNER JOIN seguranca.tabela_linha_alteracao tbla ON tbla.tbla_id = tbca.tbla_id "
					+ " INNER JOIN seguranca.usuario_alteracao usat ON usat.tref_id = tbla.tref_id "
					+ " INNER JOIN seguranca.usuario usur ON usur.usur_id = usat.usis_id "
					+ " INNER JOIN cadastro.unidade_organizacional unid ON usur.unid_id = unid.unid_id "
					+ " INNER JOIN atendimentopublico.meio_solicitacao meso ON unid.meso_id = meso.meso_id "
					+ " WHERE (tbca.tbco_id = 271 OR tbca.tbco_id = 275) "
					+ "  AND to_date(to_char(tbca.tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD')"
					+ "  between :periodoInicial and :periodoFinal" + " and ";

			if (helper.getIdUnidadeSuperior() != null
					&& !helper.getIdUnidadeSuperior().equals("")) {
				consulta += " unid.unid_idsuperior = :idUnidadeSuperior and ";
				parameters.put("idUnidadeSuperior", Integer.parseInt(helper
						.getIdUnidadeSuperior()));
			}
			if (!Util.isVazioOrNulo(helper.getColecaoUnidadeOrganizacional())) {
				consulta += " unid.unid_id in ( :idsUnidadeOrganizacional ) and ";
				parameters.put("idsUnidadeOrganizacional", helper
						.getColecaoUnidadeOrganizacional());
			}
			if (!Util.isVazioOrNulo(helper.getColecaoUsuario())) {
				consulta += " usur.usur_id in ( :idsUsuario ) and ";
				parameters.put("idsUsuario", helper.getColecaoUsuario());
			}
			if (Util.isCampoComboboxMultiploInformado(helper.getColecaoMeio())) {
				consulta += " meso.meso_id in ( ";
				String[] colecaoMeio = helper.getColecaoMeio();
				for (int i = 0; i < colecaoMeio.length; i++) {
					consulta = consulta + colecaoMeio[i] + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ") and ";
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY usur.usur_nmusuario, usur.usur_nmlogin, unid.unid_dsunidade, meso.meso_dsmeiosolicitacao";

			consulta += " ORDER BY usur.usur_nmusuario, usur.usur_nmlogin, unid.unid_dsunidade, meso.meso_dsmeiosolicitacao";

			query = session.createSQLQuery(consulta).addScalar("nome",
					Hibernate.STRING).addScalar("login", Hibernate.STRING)
					.addScalar("unidade", Hibernate.STRING).addScalar("meio",
							Hibernate.STRING).addScalar("contadorCpf",
							Hibernate.DOUBLE).addScalar("contadorCnpj",
							Hibernate.DOUBLE).addScalar("contador",
							Hibernate.DOUBLE).setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorLocalidade(
			GerarRelatorioAlteracoesCpfCnpjHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT greg.greg_nmregional AS gerenciaRegional, uneg.uneg_nmunidadenegocio AS unidadeNegocio, "
					+ " loca.loca_nmlocalidade AS localidade, "
					+ " count(CASE WHEN tbca.tbco_id = 271 THEN tbca.tbca_id END) contadorCpf, "
					+ " count(CASE WHEN tbca.tbco_id = 275 THEN tbca.tbca_id END) contadorCnpj, "
					+ " count(tbca.tbca_id) AS contador "
					+ " FROM seguranca.tab_linha_col_alteracao tbca "
					+ " INNER JOIN seguranca.tabela_linha_alteracao tbla ON tbla.tbla_id = tbca.tbla_id "
					+ " INNER JOIN seguranca.usuario_alteracao usat ON usat.tref_id = tbla.tref_id "
					+ " INNER JOIN seguranca.usuario usur ON  usur.usur_id = usat.usis_id "
					+ " INNER JOIN cadastro.unidade_organizacional unid ON usur.unid_id = unid.unid_id "
					+ " INNER JOIN cadastro.imovel imov ON imov.imov_id = tbla.tbla_id2 "
					+ " INNER JOIN cadastro.localidade loca ON loca.loca_id = imov.loca_id "
					+ " INNER JOIN cadastro.unidade_negocio uneg ON loca.uneg_id = uneg.uneg_id "
					+ " INNER JOIN cadastro.gerencia_regional greg ON greg.greg_id = loca.greg_id "
					+ " WHERE (tbca.tbco_id = 271 OR tbca.tbco_id = 275) "
					+ " AND to_date(to_char(tbca.tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD')"
					+ " between :periodoInicial and :periodoFinal" + " and ";

			if (helper.getOpcaoTotalizacao().equals("gerenciaRegional")
					&& helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("")) {
				consulta += " greg.greg_id = :idGerenciaRegional and ";
				parameters.put("idGerenciaRegional", Integer.parseInt(helper
						.getIdGerenciaRegional()));
			} else if (helper.getOpcaoTotalizacao().equals(
					"gerenciaRegionalLocalidade")
					&& helper.getIdGerenciaRegionalPorLocalidade() != null
					&& !helper.getIdGerenciaRegionalPorLocalidade().equals("")) {
				consulta += " greg.greg_id = :idGerenciaRegionalLocalidade and ";
				parameters.put("idGerenciaRegionalLocalidade", Integer
						.parseInt(helper.getIdGerenciaRegionalPorLocalidade()));
			} else if (helper.getOpcaoTotalizacao().equals("unidadeNegocio")
					&& helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("")) {
				consulta += " uneg.uneg_id = :idUnidadeNegocio and ";
				parameters.put("idUnidadeNegocio", Integer.parseInt(helper
						.getIdUnidadeNegocio()));
			} else if (helper.getOpcaoTotalizacao().equals("localidade")
					&& helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equals("")) {
				consulta += " loca.loca_id = :idLocalidade and ";
				parameters.put("idLocalidade", Integer.parseInt(helper
						.getIdLocalidade()));
			}
			if (Util.isCampoComboboxMultiploInformado(helper.getColecaoMeio())) {
				consulta += " unid.meso_id in ( ";
				String[] colecaoMeio = helper.getColecaoMeio();
				for (int i = 0; i < colecaoMeio.length; i++) {
					consulta = consulta + colecaoMeio[i] + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ") and ";
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY greg.greg_nmregional, uneg.uneg_nmunidadenegocio, loca.loca_nmlocalidade ";
			consulta += " ORDER BY greg.greg_nmregional, uneg.uneg_nmunidadenegocio, loca.loca_nmlocalidade ";

			query = session.createSQLQuery(consulta).addScalar(
					"gerenciaRegional", Hibernate.STRING).addScalar(
					"unidadeNegocio", Hibernate.STRING).addScalar("localidade",
					Hibernate.STRING)
					.addScalar("contadorCpf", Hibernate.DOUBLE).addScalar(
							"contadorCnpj", Hibernate.DOUBLE).addScalar(
							"contador", Hibernate.DOUBLE).setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorMeio(
			GerarRelatorioAlteracoesCpfCnpjHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT meso.meso_dsmeiosolicitacao AS meio, "
					+ " count(CASE WHEN tbca.tbco_id = 271 THEN tbca.tbca_id END) AS contadorCpf, "
					+ " count(CASE WHEN tbca.tbco_id = 275 THEN tbca.tbca_id END) AS contadorCnpj, "
					+ " count(tbca.tbca_id) AS contador "
					+ " FROM seguranca.tab_linha_col_alteracao tbca "
					+ " INNER JOIN seguranca.tabela_linha_alteracao tbla ON tbla.tbla_id = tbca.tbla_id "
					+ " INNER JOIN seguranca.usuario_alteracao usat ON usat.tref_id = tbla.tref_id "
					+ " INNER JOIN seguranca.usuario usur ON  usur.usur_id = usat.usis_id "
					+ " INNER JOIN cadastro.unidade_organizacional unid ON usur.unid_id = unid.unid_id "
					+ " INNER JOIN atendimentopublico.meio_solicitacao meso ON unid.meso_id = meso.meso_id "
					+ " WHERE (tbca.tbco_id = 271 OR tbca.tbco_id = 275) "
					+ "  AND to_date(to_char(tbca.tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD')"
					+ "  between :periodoInicial and :periodoFinal" + " and ";

			if (Util.isCampoComboboxMultiploInformado(helper.getColecaoMeio())) {
				consulta += " meso.meso_id in ( ";
				String[] colecaoMeio = helper.getColecaoMeio();
				for (int i = 0; i < colecaoMeio.length; i++) {
					consulta = consulta + colecaoMeio[i] + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ") and ";
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY meso.meso_dsmeiosolicitacao ";
			consulta += " ORDER BY meso.meso_dsmeiosolicitacao ";

			query = session.createSQLQuery(consulta).addScalar("meio",
					Hibernate.STRING)
					.addScalar("contadorCpf", Hibernate.DOUBLE).addScalar(
							"contadorCnpj", Hibernate.DOUBLE).addScalar(
							"contador", Hibernate.DOUBLE).setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisaImovelInscricaoAlterada(
			ImovelInscricaoAlteradaHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		Integer idLocalidade = helper.getIdLocalidade();
		Integer codigoSetorComercial = helper.getCodigoSetorComercial();
		String consulta = "";

		try {
			consulta = "SELECT count(imia.imov_id) as qtdImoveis, imia.qdra_idatual as idQuadra"
					+ " FROM cadastro.imovel_inscr_alterada imia "
					+ " INNER JOIN cadastro.setor_comercial stcm on stcm.stcm_id = imia.stcm_idatual "
					+ " WHERE imia.imia_icatualizado = :indicadorAtualizado "
					+ " AND imia.imia_icalteracaoexcluida = :alteracaoExcluida "
					+ " AND imia.imia_icerroalteracao is null "
					+ " AND imia.imia_icautorizado = :indicadorAutorizado "
					+ " AND imia.loca_idatual = :idLocalidade "
					+ " AND stcm.stcm_cdsetorcomercial = :codigoSetorComercial "
					+ " GROUP BY imia.qdra_idatual "
					+ " ORDER BY imia.qdra_idatual, qtdImoveis";

			retorno = session.createSQLQuery(consulta).addScalar("qtdImoveis",
					Hibernate.INTEGER).addScalar("idQuadra", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade).setInteger(
							"codigoSetorComercial", codigoSetorComercial)
					.setShort("indicadorAtualizado",
							ConstantesSistema.INDICADOR_USO_DESATIVO).setShort(
							"alteracaoExcluida",
							ConstantesSistema.INDICADOR_USO_DESATIVO).setShort(
							"indicadorAutorizado",
							ConstantesSistema.INDICADOR_USO_DESATIVO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.unidadeNegocio.gerenciaRegional.id = :idGerenciaRegional ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idGerenciaRegional", idGerenciaRegional).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarLocalidadesPorUnidadeNegocio(
			Integer idUnidadeNegocio) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.unidadeNegocio.id = :idUnidadeNegocio ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idUnidadeNegocio", idUnidadeNegocio).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarLocalidade() throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.indicadorUso = :indicadorUso ";

			retorno = (Collection) session.createQuery(consulta).setShort(
					"indicadorUso", ConstantesSistema.SIM).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public TarifaSocialMotivoCarta pesquisarTarifaSocialMotivoCarta(
			Integer idTarifaSocialMotivoCarta) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		TarifaSocialMotivoCarta retorno = null;

		try {

			String consulta = "SELECT tsmc "
					+ " FROM TarifaSocialMotivoCarta as tsmc "
					+ " WHERE tsmc.id = :idTarifaSocialMotivoCarta ";

			retorno = (TarifaSocialMotivoCarta) session.createQuery(consulta)
					.setInteger("idTarifaSocialMotivoCarta",
							idTarifaSocialMotivoCarta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarLocalidadesPorGerenciaEUnidade(
			Integer idGerenciaRegional, Integer idUnidadeNegocio)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.unidadeNegocio.gerenciaRegional.id = :idGerenciaRegional and "
					+ " loca.unidadeNegocio.id = :idUnidadeNegocio";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idGerenciaRegional", idGerenciaRegional).setInteger(
					"idUnidadeNegocio", idUnidadeNegocio).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> filtrarRelatorioAcessoSPC(
			FiltrarRelatorioAcessoSPCHelper filtro)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		StringBuilder sb = new StringBuilder();
		Query query = null;

		try {

			sb.append(" SELECT ");
			sb.append(" usu.unidadeOrganizacional.id, "); // 0
			sb.append(" usu.unidadeOrganizacional.descricao, "); // 1
			sb.append(" cdl.usuario.nomeUsuario, "); // 2
			sb.append(" cdl.cpfCliente, "); // 3
			sb.append(" cdl.cnpjCliente, "); // 4
			sb.append(" cdl.nomeCliente, "); // 5
			sb.append(" cdl.ultimaAlteracao "); // 6
			sb.append(" FROM gcom.seguranca.ConsultaCdl cdl ");
			sb.append(" INNER JOIN cdl.usuario usu ");
			sb.append(" INNER JOIN usu.unidadeOrganizacional uni ");
			sb.append(" WHERE ");

			String joinWhere = "";
			if (filtro.getLoginUsuarioResponsavel() != null
					&& !filtro.getLoginUsuarioResponsavel().equals("")) {
				joinWhere = " usu.login = :loginUsuario AND ";
			}
			if (filtro.getIdUnidadaOrganizacional() != null
					&& filtro.getIdUnidadaOrganizacional() > 0) {
				joinWhere += " uni.id = :idUnidade AND ";
			}
			if (filtro.getReferenciaInicial() != null
					&& filtro.getReferenciaFinal() != null) {
				joinWhere += " cdl.ultimaAlteracao BETWEEN :dataInicio and :dataFim AND ";
			}
			joinWhere = Util.removerUltimosCaracteres(joinWhere, 4);
			sb.append(joinWhere);
			sb.append(" GROUP BY ");
			sb.append(" usu.unidadeOrganizacional.id, ");
			sb.append(" usu.unidadeOrganizacional.descricao, ");
			sb.append(" cdl.ultimaAlteracao, ");
			sb.append(" cdl.cpfCliente, ");
			sb.append(" cdl.cnpjCliente, ");
			sb.append(" cdl.nomeCliente, ");
			sb.append(" cdl.usuario.nomeUsuario ");

			sb.append(" ORDER BY ");
			sb.append("usu.unidadeOrganizacional.id, ");
			sb.append("cdl.usuario.nomeUsuario ");

			query = session.createQuery(sb.toString());

			if (filtro.getLoginUsuarioResponsavel() != null
					&& !filtro.getLoginUsuarioResponsavel().equals("")) {
				query.setParameter("loginUsuario", filtro
						.getLoginUsuarioResponsavel());
			}
			if (filtro.getIdUnidadaOrganizacional() != null
					&& filtro.getIdUnidadaOrganizacional() > 0) {
				query.setParameter("idUnidade", filtro
						.getIdUnidadaOrganizacional());
			}
			if (filtro.getReferenciaInicial() != null
					&& filtro.getReferenciaFinal() != null) {
				query.setParameter("dataInicio", filtro.getReferenciaInicial());
				query.setParameter("dataFim", filtro.getReferenciaFinal());
			}
			retorno = (Collection<Object[]>) query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Collection obterCategorias() throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta = "";
		
		consulta = "SELECT cat.id, cat.descricao"
		         + " FROM Categoria cat";
		
		try{
			retorno = session.createQuery(consulta).list();
		}
		 catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}
		
		return retorno;
		
	}
	
	public Collection obterPerfisImoveis() throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta = "";
		
		consulta = "SELECT per.id,per.descricao"
			      +" FROM ImovelPerfil per"
			      +" WHERE per.indicadorUso = :indicador";
		
		try{
			retorno = session.createQuery(consulta)
							.setInteger("indicador",ConstantesSistema.INDICADOR_USO_ATIVO)
							.list();
		}
		 catch (HibernateException e) {
				throw new ErroRepositorioException(e, "Erro no Hibernate");
		}		
		return retorno;
	}

	public Boolean verificarIdentificacaoUsuario(Integer idUsuario) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT count(*) AS quantidade " //0
				+ "  FROM seguranca.usuario "
				+ "  WHERE usur_id = :idUsuario ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("idUsuario", idUsuario)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;
		
	}

	public Boolean verificarUsuarioEmpresaComandoCobranca(Integer idUsuario, Integer idComando) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT count(*) AS quantidade "
				+ " FROM cadastro.unidade_organizacional unid "
				+ "   INNER JOIN cobranca.cmd_empr_cobr_conta cecc ON cecc.empr_id = unid.empr_id "
				+ "   INNER JOIN seguranca.usuario usur ON unid.unid_id = usur.unid_id "
				+ " WHERE usur.usur_id = :idUsuario "
				+ "   AND cecc.cecc_id = :idComando ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("idUsuario", idUsuario)
				.setInteger("idComando", idComando)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;
		
	}

	public String pesquisarEmailEmpresa(Integer idEmpresa) throws ErroRepositorioException {

		String retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT empr_dsemail AS email "
				+ " FROM cadastro.empresa "
				+ " WHERE empr_id = :idEmpresa ";
			
			retorno = (String) session.createSQLQuery(consulta)
				.addScalar("email", Hibernate.STRING)
				.setInteger("idEmpresa", idEmpresa)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
	}
	
	public void atualizarGrauImportancia(Integer idLogradouro, Integer grauImportancia)
	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String update = "update Logradouro "
					+ "set ospc_id = :grauImportancia, logr_tmultimaalteracao = :dataAtual "
					+ "where logr_id = :idLogradouro";
		
			session.createQuery(update).setInteger("grauImportancia",
					grauImportancia).setInteger("idLogradouro",
							idLogradouro).setTimestamp("dataAtual", new Date()).
							executeUpdate();
		
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
	}
	
	public List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		List<HidrometroInstalacaoHistorico> retorno = null;
		
		try {
			
			Criteria crit = session.createCriteria(HidrometroInstalacaoHistorico.class);
			crit.setFetchMode("ligacaoAgua", FetchMode.JOIN);
			crit.add(Restrictions.eq("ligacaoAgua.id",idImovel.intValue()));
			retorno = (List<HidrometroInstalacaoHistorico>) crit.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

 	public Collection<CadastroOcorrencia> pesquisarOcorrenciasCadastro() throws ErroRepositorioException {
 		Collection retorno = null;
 		
 		Session session = HibernateUtil.getSession();
 		String consulta = null;
 		
 		try{
 			consulta = " select ocorrencia " 
 					+ " from CadastroOcorrencia ocorrencia " 
 					+ " where ocorrencia.indicadorUso = :indicadorUso ";
 			
 			retorno = (Collection) session.createQuery(consulta)
 			.setInteger("indicadorUso", ConstantesSistema.SIM.intValue()).list();
 			
 		}catch(HibernateException e) {
 			throw new ErroRepositorioException(e,"Erro no hibernate");
 		}finally {
 			HibernateUtil.closeSession(session);
 		}
 		return retorno;
 	}
 	
 	public Collection pesquisarRamosAtividade() throws ErroRepositorioException {
 		Collection retorno = null;
 		
 		Session session = HibernateUtil.getSession();
 		String consulta = null;
 		
 		try{
 			consulta = " select ramoAtividade " 
 					+ " from RamoAtividade ramoAtividade " 
 					+ " where ramoAtividade.indicadorUso = :indicadorUso ";
 			
 			retorno = (Collection) session.createQuery(consulta)
 			.setInteger("indicadorUso", ConstantesSistema.SIM.intValue()).list();
 			
 		}catch(HibernateException e) {
 			throw new ErroRepositorioException(e,"Erro no hibernate");
 		}finally {
 			HibernateUtil.closeSession(session);
 		}
 		return retorno;
 	}
 
 	public Collection pesquisarFonteAbastecimento() throws ErroRepositorioException {
 		Collection retorno = null;
 		
 		Session session = HibernateUtil.getSession();
 		String consulta = null;
 		
 		try{
 			consulta = " select fonteAbastecimento " 
 					+ " from FonteAbastecimento fonteAbastecimento " 
 					+ " where fonteAbastecimento.indicadorUso = :indicadorUso ";
 			
 			retorno = (Collection) session.createQuery(consulta)
 			.setInteger("indicadorUso", ConstantesSistema.SIM.intValue()).list();
 			
 		}catch(HibernateException e) {
 			throw new ErroRepositorioException(e,"Erro no hibernate");
 		}finally {
 			HibernateUtil.closeSession(session);
 		}
 		return retorno;
 	}

	public Collection obterImovelRamoAtividadeAtualizacaoCadastral(
			Integer idImovel) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta;
		Collection retornoConsulta = null;
		Collection imovelRamoAtividade = new ArrayList();

		try {
			consulta = " select " +
					   " ratv_id as idRamoAtividade "+// 0
					   " from cadastro.imovel_ramo_atividade ratv"+
					   " where imov_id = :idImovel";
			
			retornoConsulta = session.createSQLQuery(consulta)
					.addScalar("idRamoAtividade", Hibernate.INTEGER)
					.setInteger("idImovel",idImovel)
					.list();

			if (retornoConsulta.size() > 0) {
				Iterator imovelRamoAtividadeIter = retornoConsulta.iterator();
				while (imovelRamoAtividadeIter.hasNext()) {
	
					Integer element = (Integer) imovelRamoAtividadeIter.next();
					
					ImovelRamoAtividadeAtualizacaoCadastral imovRamoAtividadeAtual = new ImovelRamoAtividadeAtualizacaoCadastral(); 
					
					imovRamoAtividadeAtual.setImovel(new Imovel(idImovel));
					
					imovRamoAtividadeAtual.setRamoAtividade(new RamoAtividade(element));
					
					imovelRamoAtividade.add(imovRamoAtividadeAtual);
				}
			}
		} catch (HibernateException e) {
			logger.error("Erro ao obterImovelRamoAtividadeAtualizacaoCadastral", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return imovelRamoAtividade;
	}

	public boolean existeImovelRamoAtividadeAtualizacaoCadastral(Integer idImovel, Integer idRamoAtividade) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta;
		Collection retornoConsulta = null;
		Collection imovelRamoAtividade = new ArrayList();

		try {
			consulta = " select " +
					   " ratv_id as idRamoAtiv "+// 0
					   " from cadastro.imovel_ramo_ativ_atlz_cad ratv"+
					   " where imov_id = :idImovel AND" +
					   " ratv_id = :idRamoAtividade";
			
			retornoConsulta = session.createSQLQuery(consulta)
					.addScalar("idRamoAtiv", Hibernate.INTEGER)
					.setInteger("idImovel",idImovel)
					.setInteger("idRamoAtividade", idRamoAtividade)
					.list();

			if (retornoConsulta.size() > 0) {
				return true;
			}
		} catch (HibernateException e) {
			logger.error("Erro ao pesquisar ramo de atividade atualizacao cadastral", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return false;
	}
	
	public boolean existeRamoAtividade(Integer idRamoAtividade)	throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select ramo ")
			.append(" from RamoAtividade ramo ")
			.append(" where ramo.id = :idRamo");
			
			List retorno = session.createQuery(sql.toString())
					.setInteger("idRamo", idRamoAtividade)
					.list();

			if (retorno.size() > 0) {
				return true;
			}
		} catch (HibernateException e) {
			logger.error("Erro ao pesquisar ramo de atividade", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return false;
	}
	
	public boolean existePessoaSexo(Integer idSexo) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select sexo ")
			.append(" from PessoaSexo sexo ")
			.append(" where sexo.id = :idSexo");
			
			List retorno = session.createQuery(sql.toString())
					.setInteger("idSexo", idSexo)
					.list();

			if (retorno.size() > 0) {
				return true;
			}
		} catch (HibernateException e) {
			logger.error("Erro ao pesquisar sexo de pessoa", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return false;
	}
	
	public void liberarCadastroImovel(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update ImovelAtualizacaoCadastral tab ")
			.append(" set tab.idSituacaoAtualizacaoCadastral = :situacao ")
			.append(" where tab.idImovel = :idImovel");
			
			session.createQuery(sql.toString())
				.setInteger("idImovel", idImovel)
				.setInteger("situacao", SituacaoAtualizacaoCadastral.APROVADO)
				.executeUpdate();

		} catch (HibernateException e) {
			logger.error("Erro ao liberar cadastro do imovel", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	public Integer pesquisarIdSetorComercialPorCodigoELocalidade(Integer idLocalidade, Integer codigoSetor) throws ErroRepositorioException {
		Integer idSetorComercial = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select setor.id"
					+ " from SetorComercial setor "
					+ " where setor.localidade.id = :idLocalidade "
					+ " and setor.codigo = :codigoSetor ";
			
			idSetorComercial = (Integer) session.createQuery(consulta)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("codigoSetor", codigoSetor).uniqueResult();
			
		} catch (HibernateException e) {
			logger.error("Erro ao pesquisar id do setor pela localidade e codigo", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return idSetorComercial;
	}
	
	public Integer pesquisarIdQuadraPorNumeroQuadraEIdSetor(Integer idSetorComercial, Integer numeroQuadra) throws ErroRepositorioException {
		Integer idQuadra = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select quadra.id"
					+ " from Quadra quadra "
					+ " where quadra.setorComercial.id = :idSetorComercial "
					+ " and quadra.numeroQuadra = :numeroQuadra ";
			
			idQuadra = (Integer) session.createQuery(consulta)
					.setInteger("idSetorComercial", idSetorComercial)
					.setInteger("numeroQuadra", numeroQuadra).uniqueResult();
			
		} catch (HibernateException e) {
			logger.error("Erro ao pesquisar id do setor pela localidade e codigo", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return idQuadra;
	}
	
	public SituacaoAtualizacaoCadastral pesquisarSituacaoAtualizacaoCadastralPorId(Integer idSituacaoCadastral) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String consulta = " SELECT situacao " 
					+ " FROM SituacaoAtualizacaoCadastral situacao"
					+ " WHERE situacao.id = :idSituacaoCadastral ";
			return (SituacaoAtualizacaoCadastral)session.createQuery(consulta)
					.setInteger("idSituacaoCadastral", idSituacaoCadastral).uniqueResult();
		} catch(HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public String retornaIpServidorOperacional() throws ErroRepositorioException {
		String ip = "";
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select ipServidorModuloOperacional from SistemaParametro ";
			ip = (String) session.createQuery(consulta).uniqueResult();
		} catch (HibernateException e) {
			logger.error("Erro ao pesquisar ip do operacional", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return ip;
	}
	
	public String retornaIpServidorRelatorios() throws ErroRepositorioException {
		String ip = "";
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select parm_valor from operacao.parametro where parm_nmparametro = 'URL_RELATORIO' ";
			ip = (String) session.createSQLQuery(consulta).uniqueResult();
		} catch (HibernateException e) {
			logger.error("Erro ao pesquisar ip do servidor de relatorios", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return ip;
	}
	
	public Object[] pesquisarQtdeDebitosPreteritos(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Object[] retorno = null;
		try {
			String consulta = " SELECT COUNT(clct_id) as qtde,"
							+ " sum(c.cnta_vlagua + c.cnta_vlesgoto + c.cnta_vldebitos - c.cnta_vlcreditos - c.cnta_vlimpostos) as valorTotal "
							+ " FROM cadastro.cliente_conta cc "
							+ " INNER JOIN faturamento.conta c ON (c.cnta_id = cc.cnta_id) "
							+ " WHERE c.imov_id = :idImovel "
							+ " AND c.dcst_idatual in (:situacaoNormal, :situacaoRetificada, :situacaoIncluida, :situacaoParcelada) "
							+ " AND cc.clie_id <> (select ci.clie_id from cadastro.cliente_imovel ci where c.imov_id = ci.imov_id and ci.crtp_id = :idClienteRelacaoTipo and ci.clim_dtrelacaofim is null)"
							+ " AND cc.crtp_id = :idClienteRelacaoTipo ";
			
			Query query = session.createSQLQuery(consulta)
					.addScalar("qtde", Hibernate.INTEGER)
					.addScalar("valorTotal", Hibernate.BIG_DECIMAL)
					.setInteger("idImovel", idImovel)
					.setShort("idClienteRelacaoTipo", ClienteRelacaoTipo.USUARIO)
					.setInteger("situacaoNormal", DebitoCreditoSituacao.NORMAL)
					.setInteger("situacaoRetificada", DebitoCreditoSituacao.RETIFICADA)
					.setInteger("situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoParcelada", DebitoCreditoSituacao.PARCELADA);
			retorno = (Object[]) query.uniqueResult();
			
		} catch (HibernateException e) {
			logger.error("Erro ao pesquisar a quantidade de d�bitos pret�ritos", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> obterQuantidadesTiposOcupantesParaAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException {
        Session session = HibernateUtil.getSession();
        StringBuilder consulta;
        Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> retorno = null;

        Query query = null;

        try {

            consulta = new StringBuilder("SELECT distinct new gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral(qtd.quantidade, ")
            .append(" qtd.imovel, ") 
            .append(" qtd.tipoOcupante) ")
            .append(" from ImovelTipoOcupanteQuantidade qtd ")
            .append(" inner join qtd.imovel imov ")
            .append(" inner join qtd.tipoOcupante tipo ")
            .append(" where qtd.imovel.id =:idImovel ");

            query = session.createQuery(consulta.toString())
                    .setInteger("idImovel", idImovel);

            retorno = (Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral>) query.list();

        }catch (NonUniqueResultException e ){
            retorno = null;
        }catch (HibernateException e) {
            throw new ErroRepositorioException("Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
	
	public Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> recuperarTipoOcupantesParaAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException{
        Session session = HibernateUtil.getSession();
        StringBuilder consulta;
        Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> retorno = null;

        Query query = null;

        try {
            consulta = new StringBuilder("SELECT e ")
            .append(" from ImovelTipoOcupanteQuantidadeAtualizacaoCadastral e ")
            .append(" inner join fetch e.tipoOcupante tipo ")
            .append(" where e.imovel.id =:idImovel ");

            query = session.createQuery(consulta.toString())
                    .setInteger("idImovel", idImovel);

            retorno = (Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral>) query.list();
        }catch (NonUniqueResultException e ){
            retorno = null;
        }catch (HibernateException e) {
            throw new ErroRepositorioException("Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
	}
	
    public void removerQuantidadesOcupantesImovel(Integer idImovel) throws ErroRepositorioException { 
        Session session = HibernateUtil.getSession();
        try {
            String consulta = "delete from ImovelTipoOcupanteQuantidade where imovel.id = :idImovel " ;
            
            session.createQuery(consulta).setInteger("idImovel", idImovel).executeUpdate();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao remover ocupantes cadastrados no imovel.");
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
    
    public Date buscarUltimadataAlteracaoNoImovel(Integer idImovel) throws ErroRepositorioException {
    	Session session = HibernateUtil.getSession();
        StringBuilder consulta;
        Date retorno = null;

        Query query = null;

        try {
            consulta = new StringBuilder("select opef.opef_tmultimaalteracao as ultima_alteracao ")
	            .append(" from seguranca.operacao_efetuada opef ")
	            .append(" inner join seguranca.usuario_alteracao usalt on opef.opef_id=usalt.tref_id ")
	            .append(" inner join seguranca.usuario us on usalt.usis_id=us.usur_id ")
	            .append(" left outer join cadastro.unidade_negocio unneg on us.uneg_id=unneg.uneg_id ")
	            .append(" inner join seguranca.tabela_linha_alteracao tablinha on opef.opef_id=tablinha.tref_id ")
	            .append(" inner join seguranca.tab_linha_col_alteracao tablinha3 on tablinha.tbla_id=tablinha3.tbla_id ")
	            .append(" inner join seguranca.tabela_coluna tabcol4 on tablinha3.tbco_id=tabcol4.tbco_id ")
	            .append(" inner join seguranca.tabela tabela5_ on tabcol4.tabe_id=tabela5_.tabe_id ")
	            .append(" inner join seguranca.operacao oper on opef.oper_id=oper.oper_id ")
	            .append(" inner join seguranca.operacao_tipo optp on oper.optp_id = optp.optp_id ")
	            .append(" left outer join seguranca.tabela_coluna tabcol on oper.tbco_idargumento=tabcol.tbco_id ")
	            .append(" left outer join seguranca.tabela tab on tabcol.tabe_id=tab.tabe_id, seguranca.tabela_coluna tabcol2 ")
	            .append(" where oper.tbco_idargumento=tabcol2.tbco_id ")
	            .append(" and upper(optp.optp_dsoperacaotipo) not in ('CONSULTAR', 'FILTRAR', 'PESQUISAR', 'RELATORIO') ")
	            .append(" and tabcol2.tbco_nmcoluna='imov_id' ")
	            .append(" and opef.opef_cnargumento= :idImovel ")
	            .append(" order by opef.opef_tmultimaalteracao desc ")
	            .append(" limit 1 ");

            query = session.createSQLQuery(consulta.toString())
            		.addScalar("ultima_alteracao", Hibernate.TIMESTAMP)
                    .setInteger("idImovel", idImovel);

            retorno = (Date) query.uniqueResult();
        }catch (NonUniqueResultException e ){
            return null;
        }catch (HibernateException e) {
            throw new ErroRepositorioException("Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
    
    public Collection pesquisarRotaArquivoTextoAtualizacaoCadastroPorIdArquivo(
    		String[] idsArquivoTxt) throws ErroRepositorioException {

    	Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " SELECT atac.rota_id as idRota"// 2
					+ " FROM cadastro.arquivo_texto_atlz_cad AS atac"
					+ " where atac.txac_id in (:ids)";

			retorno = session.createSQLQuery(consulta)
					 .addScalar("idRota", Hibernate.INTEGER)
					 .setParameterList("ids", idsArquivoTxt)
					 .list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
    }

	public Integer obterFuncionarioPorImovelRetornoId(Integer idImovelRetorno) throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try {
			consulta = " SELECT f.func_id as idFuncionario from cadastro.funcionario f "
					+ " INNER JOIN micromedicao.leiturista l on l.func_id = f.func_id "
					+ " INNER JOIN cadastro.arquivo_texto_atlz_cad txac on txac.leit_id = l.leit_id "
					+ " INNER JOIN seguranca.tab_atlz_cadastral tatc on tatc.txac_id = txac.txac_id "
					+ " INNER JOIN atualizacaocadastral.imovel_controle_atlz_cad ctrl on ctrl.imov_id = tatc.tatc_cdimovel OR ctrl.icac_id = tatc.tatc_cdimovel "
					+ " WHERE ctrl.imre_id = " + idImovelRetorno;
			
			retorno = (Integer) session.createSQLQuery(consulta)
						.addScalar("idFuncionario", Hibernate.INTEGER)
						.setMaxResults(1)
						.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Boolean pesquisarCpfCadastroAguaPara(String cpf) throws ErroRepositorioException {
		Boolean retorno = true;
		Integer resultado = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = " SELECT caap.caap_id as id from cadastro.cadastro_agua_para caap "
					+ " WHERE caap.caap_nncpf = " + cpf;
			
			resultado = (Integer) session.createSQLQuery(consulta)
					    .addScalar("id", Hibernate.INTEGER)
						.setMaxResults(1)
						.uniqueResult();
			
			if(resultado!= null) {
				retorno = false;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Collection pesquisarRecadastramentoAguaParaSituacao(Integer situacao)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		StringBuilder consulta;
		try {			
			consulta = new StringBuilder(" SELECT cadastroAguaPara from CadastroAguaPara cadastroAguaPara ")
					.append(" WHERE ");
					if(situacao!=CadastroAguaPara.TODOS) {
						consulta.append("cadastroAguaPara.situacao = :situacao");
						retorno = session.createQuery(consulta.toString()).setInteger("situacao", situacao).list();
					} else {
						consulta.append("cadastroAguaPara.situacao in (" + CadastroAguaPara.ACEITO + ", "
								+ CadastroAguaPara.RECUSADO + ", " + CadastroAguaPara.PENDENTE + " ))");
						retorno = session.createQuery(consulta.toString()).list();
					}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Collection pesquisarRecadastramentoAguaParaMatricula(Integer matricula, Integer pageOffSet, Integer maxItemPage, Boolean flagTotalRegistros)
	        throws ErroRepositorioException {
	    Collection retorno = null;
	    Session session = HibernateUtil.getSession();
	    String consulta = "";
	    try {
	        consulta = " SELECT cadastroAguaPara from CadastroAguaPara cadastroAguaPara "
	                + " WHERE cadastroAguaPara.imovel.id = :matricula";
	        if(flagTotalRegistros) {
	        	retorno =   session.createQuery(consulta).setInteger("matricula", matricula).list();
	        }else {
		        retorno = session.createQuery(consulta)
		                .setInteger("matricula", matricula)
		                .setFirstResult((pageOffSet) * maxItemPage) 
		                .setMaxResults(maxItemPage) 
		                .list();
	        }
	    } catch (HibernateException e) {
	        throw new ErroRepositorioException(e, "Erro no Hibernate");
	    } finally {
	        HibernateUtil.closeSession(session);
	    }
	    return retorno;
	}

	
	public Collection pesquisarRecadastramentoAguaParaMatriculaSituacao(Integer matricula, Integer situacao)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		StringBuilder consulta;
		try {
			consulta = new StringBuilder(" SELECT cadastroAguaPara from CadastroAguaPara cadastroAguaPara ")
					.append("	WHERE cadastroAguaPara.imovel.id = :matricula and ");
			if (situacao != CadastroAguaPara.TODOS) {
				consulta.append("cadastroAguaPara.situacao = :situacao");
				retorno = session.createQuery(consulta.toString()).setInteger("matricula", matricula)
						.setInteger("situacao", situacao).list();
			} else {
				consulta.append("cadastroAguaPara.situacao in (" + CadastroAguaPara.ACEITO + ", "
						+ CadastroAguaPara.RECUSADO + ", " + CadastroAguaPara.PENDENTE + " ))");
				retorno = session.createQuery(consulta.toString()).setInteger("matricula", matricula).list();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Boolean pesquisarNisCadastroAguaPara(String nis) throws ErroRepositorioException {
		Boolean retorno = true;
		Integer resultado = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = " SELECT caap.caap_id as id from cadastro.cadastro_agua_para caap "
					+ " WHERE caap.caap_nnnis = " + nis;
			
			resultado = (Integer) session.createSQLQuery(consulta)
					    .addScalar("id", Hibernate.INTEGER)
						.setMaxResults(1)
						.uniqueResult();
			
			if(resultado!= null) {
				retorno = false;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Boolean pesquisarNisJaCadastradoInserirCliente(String nis) throws ErroRepositorioException {
		Boolean retorno = true;
		Integer resultado = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = " SELECT clie.clie_id as id from cadastro.cliente clie " + " WHERE clie.clie_nnnis = " + nis;
			resultado = (Integer) session.createSQLQuery(consulta).addScalar("id", Hibernate.INTEGER).setMaxResults(1).uniqueResult();
			
			if (resultado != null) {
					retorno = false;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	public CadastroAguaPara pesquisarRecadastramentoAguaParaPorCpf(String cpf) throws ErroRepositorioException {
		CadastroAguaPara resultado = null;
		Session session = HibernateUtil.getSession();
		StringBuilder consulta;
		try {
			consulta = new StringBuilder(" SELECT cadastroAguaPara from CadastroAguaPara cadastroAguaPara ")
					  .append("	WHERE cadastroAguaPara.cpf = :cpf ");
			
			resultado = (CadastroAguaPara) session.createQuery(consulta.toString()).setString("cpf", cpf).setMaxResults(1)
					.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return resultado;
	}
	
	public Boolean pesquisarNisJaCadastradoManterCliente(String nis, Integer idCliente)
			throws ErroRepositorioException {
		Boolean retorno = false;
		Collection<Integer> resultado = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = " SELECT clie.clie_id as id from cadastro.cliente clie " + " WHERE clie.clie_nnnis = " + nis;
			resultado = (Collection) session.createSQLQuery(consulta).addScalar("id", Hibernate.INTEGER).list();

			if (!resultado.isEmpty() && resultado != null) {
				for (Integer id : resultado) {
					if (id.equals(idCliente)) {
						retorno = true;
						break;
					}
				}
			} else {
				retorno = true;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
    
}
