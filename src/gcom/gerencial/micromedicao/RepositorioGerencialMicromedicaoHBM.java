package gcom.gerencial.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.operacional.GDistritoOperacional;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.gerencial.micromedicao.FiltrarRelatorioResumoDistritoOperacionalHelper;
import gcom.util.ErroRepositorioException;
import gcom.util.GeradorSqlRelatorio;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGerencialMicromedicaoHBM implements
		IRepositorioGerencialMicromedicao {

	protected static IRepositorioGerencialMicromedicao instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	protected RepositorioGerencialMicromedicaoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioGerencialMicromedicao getInstancia() {

		String dialect = HibernateUtil.getDialect();
		
		if (dialect.toUpperCase().contains("ORACLE")){
			if (instancia == null) {
				instancia = new RepositorioGerencialMicromedicaoHBM();
			}
		} else {
			if (instancia == null) {
				instancia = new RepositorioGerencialMicromedicaoPostgresHBM();
			}
		}

		return instancia;
	}

	/**
	 * 
	 * Método que consulta os ResumoAnormalidadeHelper
	 * 
	 * @author Flávio Cordeiro
	 * @date 17/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getResumoAnormalidadeHelper(String anoMes, int idLocalidade)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			/*
			 * String selectCount = "select count(resumoAnormalidade.id) from " + "
			 * gcom.micromedicao.ResumoAnormalidadeLeitura as resumoAnormalidade " + "
			 * where resumoAnormalidade.anoMesReferencia = " + anoMes;
			 * 
			 * Collection colecaoCount =
			 * session.createQuery(selectCount).list();
			 * 
			 * if (colecaoCount == null || colecaoCount.isEmpty()) { throw new
			 * ErroRepositorioException( "atencao.resumo_mes_ja_gerado"); }
			 *//**
				 * Se o tipo de ligacao for igual a Ligacao Agua o objeto vai
				 * ter a ligacaoAgua.id e nao vai ter nenhum dos valores q sejam
				 * ligados ou dependam da tabela imovel, já se a ligação for do
				 * tipo poço o unico atrivuto q estara nulo será ligacaoAgua.id
				 */

			String hql = " select distinct(imovel.id), "
					+ "	ligacaoAgua.id, "
					+ "	leituraAnormalidadeFaturada.id, "
					+ " 	gerenciaRegional.id, "
					+ "	localidade.id, "
					+ "	setorComercial.id, "
					+ " 	setorComercial.codigo, "
					+ "	quadra.id, "
					+ "	quadra.numeroQuadra, "
					+ " 	rota.id, "
					+ "	imovelPerfil.id, "
					+ "	ligacaoAguaSituacao.id, "
					+ "	ligacaoEsgotoSituacao.id, "
					+ "	case when (clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.RESPONSAVEL
					+ " and"
					+ " 	(clienteImoveis.dataFimRelacao is null))"
					+ " 	then esferaPoder.id else null end,  "
					+ "	medicaoTipo.id, "
					+ "	leituraAnormalidadeFaturada.descricao"
					+ " from "
					+ "	gcom.micromedicao.medicao.MedicaoHistorico as medicaoHistorico "
					+ "	left join medicaoHistorico.ligacaoAgua as ligacaoAgua "
					+ "	left join ligacaoAgua.imovel as imovel with ligacaoAgua.id = imovel.id "
					+ "	inner join medicaoHistorico.leituraAnormalidadeFaturamento as leituraAnormalidadeFaturada "
					+ "	inner join medicaoHistorico.medicaoTipo as medicaoTipo "
					+ "	left join imovel.localidade as localidade "
					+ "	left join localidade.gerenciaRegional as gerenciaRegional "
					+ "	left join imovel.setorComercial as setorComercial "
					+ "	left join imovel.quadra as quadra "
					+ "	left join quadra.rota as rota "
					+ "	left join imovel.ligacaoAguaSituacao as ligacaoAguaSituacao "
					+ "	left join imovel.ligacaoEsgotoSituacao as ligacaoEsgotoSituacao "
					+ "	left join imovel.imovelPerfil as imovelPerfil "
					+ "	left join imovel.clienteImoveis as clienteImoveis "
					+ "	left join clienteImoveis.clienteRelacaoTipo as clienteRelacaoTipo "
					+ "	left join clienteImoveis.cliente as cliente "
					+ "	left join cliente.clienteTipo as clienteTipo "
					+ "	left join clienteTipo.esferaPoder as esferaPoder "
					+ " where " + " localidade.id = :idLocalidade and "
					+ "	leituraAnormalidadeFaturada.id is not null  "
					+ "	and medicaoHistorico.anoMesReferencia = " + anoMes +
					// " and clienteRelacaoTipo.id = " +
					// ClienteRelacaoTipo.RESPONSAVEL +
					// " and clienteImoveis.dataFimRelacao = is null" +
					"";

			retorno = session.createQuery(hql).setInteger("idLocalidade",
					idLocalidade).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}

	public Object[] pesquisarImovelPorIdLigacaoAgua(Integer idLigacaoAgua)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = null;

		Object[] imovel = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			/**
			 * Se o tipo de ligacao for igual a Ligacao Agua o objeto vai ter a
			 * ligacaoAgua.id e nao vai ter nenhum dos valores q sejam ligados
			 * ou dependam da tabela imovel, já se a ligação for do tipo poço o
			 * unico atrivuto q estara nulo será ligacaoAgua.id
			 */

			String hql = " select distinct(imovel.id), "
					+ " 	gerenciaRegional.id, "
					+ "	localidade.id, "
					+ "	setorComercial.id, "
					+ " 	setorComercial.codigo, "
					+ "	quadra.id, "
					+ "	quadra.numeroQuadra, "
					+ " 	rota.id, "
					+ "	imovelPerfil.id, "
					+ "	case when (clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.RESPONSAVEL
					+ " and"
					+ " 	(clienteImoveis.dataFimRelacao is null))"
					+ " 	then esferaPoder.id else null end,"
					+ "	ligacaoAguaSituacao.id, "
					+ "	ligacaoEsgotoSituacao.id "
					+ " from "
					+ "	gcom.cadastro.imovel.Imovel as imovel "
					+ "	left join imovel.localidade as localidade "
					+ "	left join localidade.gerenciaRegional as gerenciaRegional "
					+ "	left join imovel.setorComercial as setorComercial "
					+ "	left join imovel.quadra as quadra "
					+ "	left join quadra.rota as rota "
					+ "	left join imovel.ligacaoAguaSituacao as ligacaoAguaSituacao "
					+ "	left join imovel.ligacaoEsgotoSituacao as ligacaoEsgotoSituacao "
					+ "	left join imovel.imovelPerfil as imovelPerfil "
					+ "	left join imovel.clienteImoveis as clienteImoveis "
					+ "	left join clienteImoveis.clienteRelacaoTipo as clienteRelacaoTipo "
					+ "	left join clienteImoveis.cliente as cliente "
					+ "	left join cliente.clienteTipo as clienteTipo "
					+ "	left join clienteTipo.esferaPoder as esferaPoder "
					+ " where " + "	imovel.id =" + idLigacaoAgua +
					// " and clienteRelacaoTipo.id = " +
					// ClienteRelacaoTipo.RESPONSAVEL +
					// " and clienteImoveis.dataFimRelacao = is null" +
					"";

			retorno = session.createQuery(hql).list();

			imovel = (Object[]) retorno.iterator().next();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return imovel;
	}

	/**
	 * 
	 * Método que consulta os ResumoAnormalidadeHelper
	 * 
	 * @author Flávio Cordeiro
	 * @date 17/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getResumoAnormalidadeConsumoHelper(String anoMes)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		String selectCount = "select count(resumoAnormalidade.id) from "
				+ "	gcom.micromedicao.consumo.ResumoAnormalidadeConsumo as resumoAnormalidade "
				+ "	where resumoAnormalidade.anoMesReferencia = " + anoMes;

		Collection colecaoCount = session.createQuery(selectCount).list();

		if (colecaoCount == null || colecaoCount.isEmpty()) {
			throw new ErroRepositorioException("atencao.resumo_mes_ja_gerado");
		}

		try {
			String hql = " select "
					+
					// " new " + ResumoAnormalidadeConsumo.class.getName() + " (
					// " +
					"	distinct(imovel.id), "
					+ "	gerenciaRegional.id,  "
					+ "	localidade.id, "
					+ "	setorComercial.id,  "
					+ "	rota.id, "
					+ "	quadra.id, "
					+ "	setorComercial.codigo, "
					+ "	quadra.numeroQuadra, "
					+ "	imovelPerfil.id, "
					+ "	ligacaoAguaSituacao.id, "
					+ "	ligacaoEsgotoSituacao.id, "
					+ "	case when (clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.RESPONSAVEL
					+ " and"
					+ " 	(clienteImoveis.dataFimRelacao is null))"
					+ " 	then esferaPoder.id else null end,  "
					+ "	consumoAnormalidade.id, "
					+ "	ligacaoTipo.id "
					+
					// " ) " +
					" from "
					+ "	gcom.micromedicao.consumo.ConsumoHistorico as consumoHistorico "
					+ "	left join consumoHistorico.imovel as imovel "
					+ "	inner join consumoHistorico.consumoAnormalidade as consumoAnormalidade "
					+ "	inner join consumoHistorico.ligacaoTipo as ligacaoTipo "
					+ "	left join imovel.localidade as localidade "
					+ "	left join localidade.gerenciaRegional as gerenciaRegional "
					+ "	left join imovel.setorComercial as setorComercial "
					+ "	left join imovel.quadra as quadra "
					+ "	left join quadra.rota as rota "
					+ "	left join imovel.ligacaoAguaSituacao as ligacaoAguaSituacao "
					+ "	left join imovel.ligacaoEsgotoSituacao as ligacaoEsgotoSituacao "
					+ "	left join imovel.imovelPerfil as imovelPerfil "
					+ "	left join imovel.clienteImoveis as clienteImoveis "
					+ "	left join clienteImoveis.clienteRelacaoTipo as clienteRelacaoTipo "
					+ "	left join clienteImoveis.cliente as cliente "
					+ "	left join cliente.clienteTipo as clienteTipo "
					+ "	left join clienteTipo.esferaPoder as esferaPoder "
					+ " where " + "	consumoAnormalidade.id is not null and "
					+ "	consumoHistorico.referenciaFaturamento = " + anoMes +
					// " and clienteRelacaoTipo.id = " +
					// ClienteRelacaoTipo.RESPONSAVEL +
					// " and clienteImoveis.dataFimRelacao = is null" +
					"";

			retorno = session.createQuery(hql).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}

	/**
	 * [UC0344] - Consultar Resumo de Anormalidade
	 * 
	 * @author Flávio Cordeiro
	 * @date 23/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoAnormalidadeAgua(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = new ArrayList();
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		// A query abaixo realiza uma consulta a tabela de
		// ResumoAnormalidadeLeitura
		try {
			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(
					GeradorSqlRelatorio.ANORMALIDADE_LEITURA,
					informarDadosGeracaoRelatorioConsultaHelper);
			String condicionais = this.criarCondicionaisResumos(
					informarDadosGeracaoRelatorioConsultaHelper, "real");
			String condicionalSqlAgua = " and " + condicionais
					+ " and re.medt_id = " + MedicaoTipo.LIGACAO_AGUA;
			String sqlGeradoAgua = "";

			sqlGeradoAgua = geradorSqlRelatorio.sqlNivelUm(geradorSqlRelatorio
					.getNomeCampoFixo(), geradorSqlRelatorio
					.getNomeTabelaFixo(), geradorSqlRelatorio
					.getNomeTabelaFixoTotal(), "'"
					+ informarDadosGeracaoRelatorioConsultaHelper
							.getDescricaoOpcaoTotalizacao() + "'", "",
					"and re.ltan_id = la.ltan_id", condicionalSqlAgua, true, false);

			// System.out.println(anormalidadePorQuadra);
			List listAgua = session.createSQLQuery(sqlGeradoAgua).addScalar(
					"descricao", Hibernate.STRING).addScalar("somatorio",
					Hibernate.INTEGER).list();

			retorno.addAll(listAgua);
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção com os resultados da pesquisa
		return retorno;

	}

	/**
	 * [UC0344] - Consultar Resumo de Anormalidade
	 * 
	 * @author Flávio Cordeiro
	 * @date 23/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoAnormalidadePoco(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = new ArrayList();
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		// A query abaixo realiza uma consulta a tabela de
		// ResumoAnormalidadeLeitura
		try {
			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(
					GeradorSqlRelatorio.ANORMALIDADE_LEITURA,
					informarDadosGeracaoRelatorioConsultaHelper);
			String condicionais = this.criarCondicionaisResumos(
					informarDadosGeracaoRelatorioConsultaHelper, "real");

			String condicionalSqlPoco = " and " + condicionais
					+ " and re.medt_id = " + MedicaoTipo.POCO;

			String sqlGeradoPoco = geradorSqlRelatorio.sqlNivelUm(
					geradorSqlRelatorio.getNomeCampoFixo(), geradorSqlRelatorio
							.getNomeTabelaFixo(), geradorSqlRelatorio
							.getNomeTabelaFixoTotal(), "'"
							+ informarDadosGeracaoRelatorioConsultaHelper
									.getDescricaoOpcaoTotalizacao() + "'", "",
					"and re.ltan_id = la.ltan_id", condicionalSqlPoco, true, false);

			// System.out.println(anormalidadePorQuadra);
			List listEsgoto = session.createSQLQuery(sqlGeradoPoco).addScalar(
					"descricao", Hibernate.STRING).addScalar("somatorio",
					Hibernate.INTEGER).list();

			retorno.addAll(listEsgoto);
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção com os resultados da pesquisa
		return retorno;

	}

	/**
	 * 
	 * [UC0344] Consultar Resumo de Anormalidade
	 * 
	 * Consulta na tabela resumoAnormalidadeConsumo os regitros conforme os
	 * parametros passados pelo [UC0304] Informar Dados para geração de
	 * Relatório ou Consulta
	 * 
	 * @author Flávio Cordeiro
	 * @date 30/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 */

	public List consultarResumoAnormalidadeConsumo(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = new ArrayList();
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		// A query abaixo realiza uma consulta a tabela de
		// ResumoAnormalidadeConsumo
		try {
			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(
					GeradorSqlRelatorio.ANORMALIDADE_CONSUMO,
					informarDadosGeracaoRelatorioConsultaHelper);
			String condicionais = this.criarCondicionaisResumos(
					informarDadosGeracaoRelatorioConsultaHelper, "reac");

			String condicionalSql = " and " + condicionais;

			String sqlGerado = geradorSqlRelatorio.sqlNivelUm(
					geradorSqlRelatorio.getNomeCampoFixo(), geradorSqlRelatorio
							.getNomeTabelaFixo(), geradorSqlRelatorio
							.getNomeTabelaFixoTotal(), "'"
							+ informarDadosGeracaoRelatorioConsultaHelper
									.getDescricaoOpcaoTotalizacao() + "'", "",
					"and re.csan_id = ca.csan_id", condicionalSql, true, false);

			// System.out.println(anormalidadePorQuadra);
			List listEsgoto = session.createSQLQuery(sqlGerado).addScalar(
					"descricao", Hibernate.STRING).addScalar("somatorio",
					Hibernate.INTEGER).list();

			retorno.addAll(listEsgoto);
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção com os resultados da pesquisa
		return retorno;

	}

	public String criarCondicionaisResumos(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper,
			String nomeColunaTabela) {

		String sql = " ";
		// boolean existeWhere = false;
		// A partir daqui sera montanda a parte dos condicionais da query
		// estas condicionais serão usadas se necessarias, o q determina seus
		// usos
		// são os parametros que veem carregados no objeto
		// InformarDadosGeracaoRelatorioConsultaHelper
		// que é recebido do caso de uso [UC0304] Informar Dados para Geração de
		// Relatorio ou COnsulta
		if (informarDadosGeracaoRelatorioConsultaHelper != null) {

			// Inicio Parametros simples
			if (informarDadosGeracaoRelatorioConsultaHelper
					.getAnoMesReferencia() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getAnoMesReferencia().toString().equals("")) {
				sql = sql
						+ "re."
						+ nomeColunaTabela
						+ "_amreferencia = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getAnoMesReferencia() + " and ";
				// existeWhere = true;
			}

			// if(informarDadosGeracaoRelatorioConsultaHelper.getFaturamentoGrupo()
			// != null
			// &&
			// informarDadosGeracaoRelatorioConsultaHelper.getFaturamentoGrupo().getId()
			// != null){
			// sql = sql + " re." + +"_id = "
			// +
			// informarDadosGeracaoRelatorioConsultaHelper.getFaturamentoGrupo().getId()
			// + " and ";
			// existeWhere = true;
			// }

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getGerenciaRegional() != null
					&& informarDadosGeracaoRelatorioConsultaHelper
							.getGerenciaRegional().getId() != null) {
				sql = sql
						+ " re.greg_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getGerenciaRegional().getId() + " and ";
				// existeWhere = true;
			}

			// if(informarDadosGeracaoRelatorioConsultaHelper.getEloPolo() !=
			// null
			// &&
			// informarDadosGeracaoRelatorioConsultaHelper.getEloPolo().getId()
			// != null){
			// sql = sql + " eloPolo.loca_id = "
			// +
			// informarDadosGeracaoRelatorioConsultaHelper.getEloPolo().getId()
			// + " and ";
			// existeWhere = true;
			// }

			if (informarDadosGeracaoRelatorioConsultaHelper.getLocalidade() != null
					&& informarDadosGeracaoRelatorioConsultaHelper
							.getLocalidade().getId() != null) {
				sql = sql
						+ " re.loca_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getLocalidade().getId() + " and ";
				// existeWhere = true;
			}

			if (informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial() != null
					&& informarDadosGeracaoRelatorioConsultaHelper
							.getSetorComercial().getId() != null) {
				sql = sql
						+ " re.stcm_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getSetorComercial().getId() + " and ";
				// existeWhere = true;
			}

			if (informarDadosGeracaoRelatorioConsultaHelper.getQuadra() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getQuadra()
							.getId() != null) {
				sql = sql
						+ " re.qdra_id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getQuadra().getId() + " and ";
				// existeWhere = true;
			}

			// Inicio de parametros por colecão
			// sera lida a colecao e montado um IN() a partis dos id extraidos
			// de cada objeto da colecao.
			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoImovelPerfil() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoImovelPerfil().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoImovelPerfil().iterator();
				ImovelPerfil imovelPerfil = null;

				sql = sql + " re.iper_id in (";
				while (iterator.hasNext()) {
					imovelPerfil = (ImovelPerfil) iterator.next();
					sql = sql + imovelPerfil.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
				// existeWhere = true;
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoLigacaoAguaSituacao() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoLigacaoAguaSituacao().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoLigacaoAguaSituacao().iterator();
				LigacaoAguaSituacao ligacaoAguaSituacao = null;

				sql = sql + " re.last_id in (";
				while (iterator.hasNext()) {
					ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
					sql = sql + ligacaoAguaSituacao.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
				// existeWhere = true;
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoLigacaoEsgotoSituacao() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoLigacaoEsgotoSituacao().iterator();
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

				sql = sql + " re.lest_id in (";
				while (iterator.hasNext()) {
					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator
							.next();
					sql = sql + ligacaoEsgotoSituacao.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
				// existeWhere = true;
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoCategoria() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoCategoria().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoCategoria().iterator();
				Categoria categoria = null;

				sql = sql + " re.catg_id in (";
				while (iterator.hasNext()) {
					categoria = (Categoria) iterator.next();
					sql = sql + categoria.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
				// existeWhere = true;
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoEsferaPoder() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoEsferaPoder().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoEsferaPoder().iterator();
				EsferaPoder esferaPoder = null;

				sql = sql + " re.epod_id in (";
				while (iterator.hasNext()) {
					esferaPoder = (EsferaPoder) iterator.next();
					sql = sql + esferaPoder.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
				// existeWhere = true;
			}
		}

		// if(existeWhere){
		// sql = " where " + sql;
		// }
		// retira o " and " q fica sobrando no final da query
		sql = Util.removerUltimosCaracteres(sql, 4);

		return sql;
	}

	/**
	 * Gera o resumo das instações de hidrômetro para o ano/mês de referência da
	 * arrecadação.
	 * 
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * Verificar existência de dados para o ano/mês de referência da arrecadação
	 * do Resumo das instalações de hidrômetros.
	 * 
	 * [FS0001 - Verificar existência de dados para o ano/mês de referência da
	 * arrecadação]
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaResumoInstalacaoHidrometroParaAnoMesReferenciaArrecadacao(
			Integer anoMesReferenciaFaturamento, Integer idSetorComercial)
			throws ErroRepositorioException {

		// flag indicando a existência de dados
		boolean retorno = false;

		// Cria a varável que vai armazenar a coleção de retorno da pesquisa
		Collection colecaoRetorno = null;

		// Cria uma instância da sessão
		Session session = HibernateUtil.getSessionGerencial();

		// Cria a variável que vai conter o hql
		String consulta = "";

		try {

			// Cria o hql de pesquisa
			consulta = "from UnResumoInstalacaoHidrometro reih "
					+ "where reih.referencia = :anoMesReferenciaFaturamento and reih.gerSetorComercial.id = :idSetorComercial";

			// Executa o hql
			colecaoRetorno = session.createQuery(consulta).setInteger(
					"anoMesReferenciaFaturamento", anoMesReferenciaFaturamento)
					.setInteger("idSetorComercial", idSetorComercial).list();

			/**
			 * Caso a coleção de retorno não esteja vazia, retorna a flag
			 * indicando que já existe daddos no resumo das instações de
			 * hidrometro para o ano/mês de referência da arrecadação atual.
			 */
			if (colecaoRetorno != null && !colecaoRetorno.isEmpty()) {
				retorno = true;
			}

		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * Pesquisa os dados do setor comercial.
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param idSetorComercial
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosSetorComercial(Integer idSetorComercial)
			throws ErroRepositorioException {

		Object[] dadosSetorComercial = null;

		Session session = HibernateUtil.getSessionGerencial();

		try {

			String hql = "select "
					+ "gerenciaRegional.id, "
					+ "unidadeNegocio.id, "
					+ "elo.id, "
					+ "localidade.id, "
					+ "0, "
					+ "0, "
					+ "0, "
					+ "setorComercial.codigo "
					+ "from "
					+ "GSetorComercial setorComercial "
					+ "left join setorComercial.gerLocalidade as localidade "
					+ "left join localidade.gerLocalidade as elo "
					+ "left join elo.gerUnidadeNegocio as unidadeNegocio "
					+ "left join unidadeNegocio.gerGerenciaRegional as gerenciaRegional "
					+ "where setorComercial.id = :idSetorComercial";

			dadosSetorComercial = (Object[]) session.createQuery(hql)
					.setInteger("idSetorComercial", idSetorComercial)
					.setMaxResults(1)
					.uniqueResult();

			
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			e.printStackTrace();
			
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return dadosSetorComercial;
	}

	/**
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * Pesquisa a coleção de ids de quadras para o setor comercial informado.
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param idSetorComercial
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosQuadrasPorSetorComercial(
			Integer idSetorComercial, Date dataInicial, Date dataFinal)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "select qdra_id as quadra, rota_id as rota from cadastro.quadra qdra "
					+ "where stcm_id = :idSetorComercial and "
					+ "qdra_id in (select distinct qdra_id from cadastro.imovel "
					+ "where imov_id in ( "
					+ "select distinct imov_id from micromedicao.hidrometro_inst_hist "
					+ "as hidi where (hidi_dtretiradahidrometro is null or hidi_dtretiradahidrometro between :dataInicial and :dataFinal) "
					+ "union "
					+ "select distinct lagu_id from micromedicao.hidrometro_inst_hist "
					+ "as hidi where (hidi_dtretiradahidrometro is null or hidi_dtretiradahidrometro between :dataInicial and :dataFinal )  "
					+ ")" + ")";

			retorno = session.createSQLQuery(consulta).addScalar("quadra",
					Hibernate.INTEGER).addScalar("rota", Hibernate.INTEGER)
					.setInteger("idSetorComercial", idSetorComercial).setDate(
							"dataInicial", dataInicial).setDate("dataFinal",
							dataFinal).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Gerar Resumo das Instalações de Hidrômetros Por Ano
	 * 
	 * Pesquisar dados do imóvel.
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param idSetorComercial
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosImovelResumoInstalacaoHidrometroPorQuadra(
			Integer idSetorComercial, Date dataInicio, Date dataFim)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";
		try {

			consulta = "select "
					+ "imovel.imov_id as imovid, "
					+ "imovelperfil.iper_id as iperid, "
					+ "ligacaoaguasituacao.last_id as lastid, "
					+ "ligacaoesgotosituacao.lest_id as lestid, "
					+ "ligacaoaguaperfil.lapf_id as lapfid, "
					+ "ligacaoesgoto.lepf_id as lepfid, "
					+ "quadra.qdra_id as idquadra, "
					+ "quadra.qdra_nnquadra as numeroQuadra, "
					+ "quadra.rota_id as rotaid, "
					+ "case when (hidrometro.lagu_id is not null and hidrometro.hidi_dtretiradahidrometro is null) then 1 else 2 end as ramal, "
					+ "case when (hidrometro.imov_id is not null and hidrometro.hidi_dtretiradahidrometro is null) then 1 else 2 end as poco, "
					+ "hidrometro.imov_id as hidrometroimov , "
					+ "hidrometro.lagu_id as hidrometrolagu , "
					+ "hidrometro.hidi_icinstalacaosubstituicao as indicador , "
					+ "to_char(hidrometro.hidi_dtinstalacaohidmtsistema,'YYYYMM') as anomesinstalacao, "
					+ "to_char(hidrometro.hidi_dtretiradahidrometro,'YYYYMM') as anomesretirada, "
					+ "imovel.hidi_id as idhidrimov, "
					+ "ligacaoagua.hidi_id as idhidrlagu, "
					+ "rota.rota_cdrota as codigoRota, "
					+"imovel.cstf_id as idconsumotarifa, "
					+ "hidro.himc_id as idmarcahidrometro, "
					+ "hidro.hitp_id as idtipohidrometro, "
					+ "hidro.hicp_id as idcapacidadehidrometro, "
					+ "hidro.hidm_id as iddiametrohidrometro, "
					+ "hidro.hicm_id as idclassehidrometro "
					+ "from "
					+ "cadastro.imovel imovel "
					+ "inner join cadastro.setor_comercial setor on imovel.stcm_id=setor.stcm_id "
					+ "inner join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id and quadra.stcm_id = :idSetorComercial "
					+ "inner join micromedicao.rota rota on rota.rota_id=quadra.rota_id and imovel.qdra_id=quadra.qdra_id and "
					+ " quadra.stcm_id = :idSetorComercial "
					+ "inner join cadastro.imovel_perfil imovelperfil on imovel.iper_id=imovelperfil.iper_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao ligacaoaguasituacao on imovel.last_id=ligacaoaguasituacao.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao ligacaoesgotosituacao on imovel.lest_id=ligacaoesgotosituacao.lest_id "
					+ "left outer join atendimentopublico.ligacao_agua ligacaoagua on imovel.imov_id=ligacaoagua.lagu_id "
					+ "left outer join atendimentopublico.ligacao_agua_perfil ligacaoaguaperfil on ligacaoagua.lapf_id=ligacaoaguaperfil.lapf_id "
					+ "left outer join atendimentopublico.ligacao_esgoto ligacaoesgoto on imovel.imov_id=ligacaoesgoto.lesg_id "
					+ "left outer join atendimentopublico.ligacao_esgoto_perfil ligacaoesgotoperfil on ligacaoesgoto.lepf_id=ligacaoesgotoperfil.lepf_id "
					+ "left join micromedicao.hidrometro_inst_hist hidrometro on (imovel.imov_id=hidrometro.imov_id or imovel.imov_id = hidrometro.lagu_id) "
					+ "inner join micromedicao.hidrometro hidro on hidro.hidr_id = hidrometro.hidr_id "
					+"where " 
					+ " setor.stcm_id=:idSetorComercial "
					/**TODO:COSANPA
					 * Data: 11/10/2011
					 * Autor: Adriana Muniz
					 * 
					 * Adicionado um filtro para considerar apenas imóveis ativos.
					 * */
					+ " and imovel.imov_icexclusao = 2 "
					+ " and imovel.imov_id in (select distinct(imov_id) from micromedicao.hidrometro_inst_hist "
					+ "where (hidi_dtinstalacaohidmtsistema <= :dataFim  or hidi_dtretiradahidrometro between :dataInicio and :dataFim) "
					+ "union	"
					+ "select distinct(lagu_id) from micromedicao.hidrometro_inst_hist "
					+ "where (hidi_dtinstalacaohidmtsistema <= :dataFim or hidi_dtretiradahidrometro between :dataInicio and :dataFim) "
					+ ")";

			retorno = session.createSQLQuery(consulta).addScalar("imovid",
					Hibernate.INTEGER).addScalar("iperid", Hibernate.INTEGER)
					.addScalar("lastid", Hibernate.INTEGER).addScalar("lestid",
							Hibernate.INTEGER).addScalar("lapfid",
							Hibernate.INTEGER).addScalar("lepfid",
							Hibernate.INTEGER).addScalar("idquadra",
							Hibernate.INTEGER).addScalar("numeroQuadra",
							Hibernate.INTEGER).addScalar("rotaid",
							Hibernate.INTEGER).addScalar("ramal",
							Hibernate.INTEGER).addScalar("poco",
							Hibernate.INTEGER)

					.addScalar("hidrometroimov", Hibernate.INTEGER).addScalar(
							"hidrometrolagu", Hibernate.INTEGER).addScalar(
							"indicador", Hibernate.SHORT).addScalar(
							"anomesinstalacao", Hibernate.STRING).addScalar(
							"anomesretirada", Hibernate.STRING).addScalar(
							"idhidrimov", Hibernate.INTEGER).addScalar(
							"idhidrlagu", Hibernate.INTEGER).addScalar(
							"codigoRota", Hibernate.SHORT)

					.addScalar("idconsumotarifa", Hibernate.INTEGER).addScalar(
							"idmarcahidrometro", Hibernate.INTEGER).addScalar(
							"idtipohidrometro", Hibernate.INTEGER).addScalar(
							"idcapacidadehidrometro", Hibernate.INTEGER)
					.addScalar("iddiametrohidrometro", Hibernate.INTEGER)
					.addScalar("idclassehidrometro", Hibernate.INTEGER)

					.setInteger("idSetorComercial", idSetorComercial).setDate(
							"dataInicio", dataInicio).setDate("dataFim",
							dataFim).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetros
	 * 
	 * Pesquisa os dados do cliente responsável do imóvel informado.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/05/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosClienteResponsavelPorImovel(Integer idImovel)
			throws ErroRepositorioException {

		Object[] retorno = null;

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			// Monta o hql
			consulta = "select epod.id, cltp.id "
					+ "from ClienteImovel clim "
					+ "inner join clim.imovel imov "
					+ "inner join clim.cliente clie "
					+ "inner join clie.clienteTipo cltp "
					+ "inner join cltp.esferaPoder epod "
					+ "inner join clim.clienteRelacaoTipo crtp "
					+ "where imov.id = :idImovel and crtp.id = :idTipoRelacao and clim.dataFimRelacao is null ";

			// Executa o hql
			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setInteger("idTipoRelacao",
					ClienteRelacaoTipo.RESPONSAVEL).uniqueResult();

			// Erro na consulta
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// Retorna a coleção pesquisada
		return retorno;
	}

	/**
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetros
	 * 
	 * Pesquisa os dados da instalação do hidrômetro no histórico para o imóvel
	 * informado.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/05/2007
	 * 
	 * @param idImovel
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosHidrometroInstalacaoHistoricoPorImovel(
			Integer idImovel, Date dataInicio, Date dataFim)
			throws ErroRepositorioException {

		Collection retorno = null;

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			// Monta o hql
			consulta = "select imov.id, lagu.id, "
					+ "concat(substring(hidi.dataImplantacaoSistema,1,4), substring(hidi.dataImplantacaoSistema,6,2)), "
					+ "concat(substring(hidi.dataRetirada,1,4), substring(hidi.dataRetirada,6,2)), "
					+ "hidi.indicadorInstalcaoSubstituicao, "
					+ "imov.hidrometroInstalacaoHistorico.id, "
					+ "lagu.hidrometroInstalacaoHistorico.id "
					+ "from HidrometroInstalacaoHistorico hidi "
					+ "left join hidi.imovel imov "
					+ "left join hidi.ligacaoAgua lagu "
					+ "where (imov.id = :idImovel or lagu.id = :idImovel) and "
					+ "((hidi.dataImplantacaoSistema between :dataInicio and :dataFim) or "
					+ "(hidi.dataRetirada between :dataInicio and :dataFim)) ";

			// Executa o hql
			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).setDate("dataInicio", dataInicio).setDate(
					"dataFim", dataFim).list();

			// Erro na consulta
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// Retorna a coleção pesquisada
		return retorno;
	}

	/**
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetros
	 * 
	 * Pesquisa os ids dos setores comercias dos imóveis que tem hidrometro
	 * instalado no histórico
	 * 
	 * @author Pedro Alexandre
	 * @date 08/05/2007
	 * 
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "select distinct stcm_id as setorComercial "
					+ "from cadastro.imovel "
					+ "where imov_id in (select distinct imov_id from micromedicao.hidrometro_inst_hist hidi "
					+ "where hidi.hidi_dtretiradahidrometro is null "
					+ "union "
					+ "select distinct lagu_id from micromedicao.hidrometro_inst_hist hidi "
					+ "where hidi.hidi_dtretiradahidrometro is null " + ") ";

			retorno = session.createSQLQuery(consulta).addScalar(
					"setorComercial", Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que retona uma lista de objeto xxxxx que representa o UC0551 -
	 * Gerar Resumo Leitura Anormalidade
	 * 
	 * @author Ivan Sérgio
	 * @date 23/04/2007, 28/05/2007, 21/06/2007, 27/07/2007, 08/08/2007,
	 *       05/12/2008, 17/02/2009
	 * @Alteracao: 28/05/2007 - Troca do parametro Localidade para Setor
	 *             Comercial;
	 * @Alteracao: 21/06/2007 - LTAN_ID pode ser Null; Duas quebras novas:
	 *             Empresa(EMPR_ID), Situacao Leitura(LTST_ID);
	 * @Alteracao: 27/07/2007 - Trocar o parametro AMREFERENCIAARRECADACAO para
	 *             AMREFERENCIAFATURAMENTO; Alteracao na forma de consulta para
	 *             o campo LAST_ID. Agora por CONTA ou IMOVEL;
	 * @Alteracao: 08/08/2007 - Alteracao na forma de consulta dos principais
	 *             campos. Agora por CONTA ou IMOVEL;
	 * @Alteracao: 05/12/2008 - CRC719 - Adicionado o campo
	 *             ltan_idleituraanormalidadeinformada
	 * @Alteracao: 17/02/2009 - Adicionado o leituraAtualInformada para corrigir
	 *             o erro da quantidade de leituras informadas;
	 * 
	 * @param setorComercial
	 * @param anoMesLeitura
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoLeituraAnormalidade(int setorComercial,
			int anoMesLeitura) throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

	    /**TODO:COSANPA
		 * Data: 13/10/2011
		 * Autor: Adriana Muniz
		 * 
		 * Adição de filtro para impedir que imóveis excluídos sejam retornados na consulta
		 * */
		try {
			String sql = " SELECT imovel.imov_id, "
					+
					// " localidade.greg_id, " +
					// " localidade.uneg_id, " +
					// " localidade.loca_cdelo, " +
					// " imovel.loca_id, " +
					// " quadra.stcm_id, " +
					// " quadra.rota_id, " +
					// " imovel.qdra_id, " +
					// " imovel.iper_id, " +
					// " imovel.last_id, " + Retirado por conta da alteracao do
					// dia 27/07/2007
					// " imovel.lest_id, " +
					"	0 as lapf_id, "
					+ "	ligacao_esgoto.lepf_id, "
					+ "	medicao_historico.medt_id, "
					+

					"	case when (medicao_historico.ltan_idleitanormfatmt is not null) then "
					+ "		medicao_historico.ltan_idleitanormfatmt "
					+ "	else "
					+ "		0 "
					+ "	end as ltan_idleituraanormalidadefatu, "
					+

					// " setor_comercial.stcm_cdsetorcomercial, " +
					// " quadra.qdra_nnquadra, " +
					"	rota.empr_id, "
					+ "	medicao_historico.ltst_idleiturasituacaoatual, "
					+

					"	case when (medicao_historico.ltan_idleitanorminformada is not null) then "
					+ "		medicao_historico.ltan_idleitanorminformada "
					+ "	else "
					+ "		0 "
					+ "	end as ltan_idleituraanormalidadeinfo, "
					+

					"	case when (medicao_historico.mdhi_nnleituraatualinformada is not null) then "
					+ "		1 "
					+ "	else "
					+ "		0 "
					+ "	end as quantidadeLeituraInformada, "
					+

					"	medicao_historico.mdhi_id "
					+ // Campo utilizado para evitar o group by do union
					"FROM micromedicao.medicao_historico medicao_historico "
					+ "	INNER JOIN cadastro.imovel imovel ON imovel.imov_id = medicao_historico.imov_id "
					+ "	INNER JOIN cadastro.localidade localidade ON localidade.loca_id = imovel.loca_id "
					+ "	INNER JOIN cadastro.quadra quadra ON quadra.qdra_id = imovel.qdra_id "
					+ "	INNER JOIN micromedicao.rota rota ON rota.rota_id = quadra.rota_id "
					+ "	INNER JOIN atendimentopublico.ligacao_esgoto ligacao_esgoto ON ligacao_esgoto.lesg_id = medicao_historico.imov_id "
					+ "	INNER JOIN cadastro.setor_comercial setor_comercial ON setor_comercial.stcm_id = imovel.stcm_id "
					+ "WHERE medicao_historico.mdhi_amleitura = "
					+ anoMesLeitura + " " + "   AND quadra.stcm_id = "
					+ setorComercial + " "
					+ "	AND medicao_historico.medt_id = "
					+ LigacaoTipo.LIGACAO_ESGOTO + " "
					+ " and imovel.imov_icexclusao = 2";

			String sqlSegundo = "SELECT imovel.imov_id, "
					+
					// " localidade.greg_id, " +
					// " localidade.uneg_id, " +
					// " localidade.loca_cdelo, " +
					// " imovel.loca_id, " +
					// " quadra.stcm_id, " +
					// " quadra.rota_id, " +
					// " imovel.qdra_id, " +
					// " imovel.iper_id, " +
					// " imovel.last_id, " + Retirado por conta da alteracao do
					// dia 27/07/2007
					// " imovel.lest_id, " +
					"	ligacao_agua.lapf_id, "
					+ "	0 AS lepf_id, "
					+ "	medicao_historico.medt_id, "
					+

					"	case when (medicao_historico.ltan_idleitanormfatmt is not null) then "
					+ "		medicao_historico.ltan_idleitanormfatmt "
					+ "	else "
					+ "		0 "
					+ "	end as ltan_idleituraanormalidadefatu, "
					+

					// " setor_comercial.stcm_cdsetorcomercial, " +
					// " quadra.qdra_nnquadra, " +
					"	rota.empr_id, "
					+ "	medicao_historico.ltst_idleiturasituacaoatual, "
					+

					"	case when (medicao_historico.ltan_idleitanorminformada is not null) then "
					+ "		medicao_historico.ltan_idleitanorminformada "
					+ "	else "
					+ "		0 "
					+ "	end as ltan_idleituraanormalidadeinfo, "
					+

					"	case when (medicao_historico.mdhi_nnleituraatualinformada is not null) then "
					+ "		1 "
					+ "	else "
					+ "		0 "
					+ "	end as quantidadeLeituraInformada, "
					+

					"	medicao_historico.mdhi_id "
					+ // Campo utilizado para evitar o group by do union
					"FROM micromedicao.medicao_historico medicao_historico "
					+ "	INNER JOIN cadastro.imovel imovel ON imovel.imov_id = medicao_historico.lagu_id "
					+ "	INNER JOIN cadastro.localidade localidade ON localidade.loca_id = imovel.loca_id "
					+ "	INNER JOIN cadastro.quadra quadra ON quadra.qdra_id = imovel.qdra_id "
					+ "	INNER JOIN micromedicao.rota rota ON rota.rota_id = quadra.rota_id "
					+ "	INNER JOIN atendimentopublico.ligacao_agua ligacao_agua ON ligacao_agua.lagu_id = medicao_historico.lagu_id "
					+ "	INNER JOIN cadastro.setor_comercial setor_comercial ON setor_comercial.stcm_id = imovel.stcm_id "
					+ "WHERE medicao_historico.mdhi_amleitura = "
					+ anoMesLeitura + " " + "	AND quadra.stcm_id = "
					+ setorComercial + " "
					+ "	AND medicao_historico.medt_id = "
					+ LigacaoTipo.LIGACAO_AGUA + " "
					+ " and imovel.imov_icexclusao = 2";

			sql = sql + " UNION " + sqlSegundo;

			// System.out.print("-------------------> " + sql);

			retorno = session.createSQLQuery(sql).addScalar("imov_id",
					Hibernate.INTEGER)
			// .addScalar("greg_id", Hibernate.INTEGER)
					// .addScalar("uneg_id", Hibernate.INTEGER)
					// .addScalar("loca_cdelo", Hibernate.INTEGER)
					// .addScalar("loca_id", Hibernate.INTEGER)
					// .addScalar("stcm_id", Hibernate.INTEGER)
					// .addScalar("rota_id", Hibernate.INTEGER)
					// .addScalar("qdra_id", Hibernate.INTEGER)
					// .addScalar("iper_id", Hibernate.INTEGER)
					// .addScalar("lest_id", Hibernate.INTEGER)
					.addScalar("lapf_id", Hibernate.INTEGER).addScalar(
							"lepf_id", Hibernate.INTEGER).addScalar("medt_id",
							Hibernate.INTEGER).addScalar(
							"ltan_idleituraanormalidadefatu",
							Hibernate.INTEGER)
					// .addScalar("stcm_cdsetorcomercial", Hibernate.INTEGER)
					// .addScalar("qdra_nnquadra", Hibernate.INTEGER)
					.addScalar("empr_id", Hibernate.INTEGER).addScalar(
							"ltst_idleiturasituacaoatual", Hibernate.INTEGER)
					.addScalar("ltan_idleituraanormalidadeinfo",
							Hibernate.INTEGER).addScalar(
							"quantidadeLeituraInformada", Hibernate.INTEGER)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [FS0003] - Verificar Existencia de conta para o mes de faturamento Metodo
	 * utilizado para auxiliar o [UC0551 - Gerar Resumo Leitura Anormalidade]
	 * para recuperar o valo da Situacao da Ligacao de Agua.
	 * 
	 * @author Ivan Sérgio
	 * @date 27/07/2007, 08/08/2007
	 * @alteracao - Receber os outros campos da
	 *            getImoveisResumoLeituraAnormalidade;
	 * 
	 * @throws ErroRepositorioException
	 * @return List
	 */
	public List pesquisarLeituraAnormalidadeComplementar(Integer imovel,
			Integer dataFaturamento) throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();
		String hql = "";

		try {
			hql = "select " + "	0 as tipo, " + "	conta.id, "
					+ "	conta.localidade.gerenciaRegional.id, "
					+ "	conta.localidade.unidadeNegocio.id, "
					+ "	conta.localidade.localidade.id, "
					+ "	conta.localidade.id, "
					+ "	conta.quadraConta.setorComercial.id, "
					+ "	conta.quadraConta.rota.id, "
					+ "	conta.quadraConta.id, " + "	conta.imovelPerfil.id, "
					+ "	conta.ligacaoAguaSituacao.id, "
					+ "	conta.ligacaoEsgotoSituacao.id, "
					+ "	conta.codigoSetorComercial, " + "	conta.quadra, "
					+ "	conta.consumoTarifa.id, "
					+ "	conta.quadraConta.rota.faturamentoGrupo.id " + "from "
					+ "	gcom.faturamento.conta.Conta conta " + "where "
					+ "	conta.imovel.id = :imovel and "
					+ "	conta.referencia = :anoMesReferenciaFaturamento and "
					+ "	(conta.debitoCreditoSituacaoAtual.id = 0 or "
					+ "	conta.debitoCreditoSituacaoAnterior.id = 0)";

			retorno = session.createQuery(hql).setInteger("imovel", imovel)
					.setInteger("anoMesReferenciaFaturamento", dataFaturamento)
					.list();

			// Caso nao exista conta para o ano/mes de faturamento
			// utilizar last_id de imovel
			if (retorno == null || retorno.isEmpty()) {
				hql = "select " + "	1 as tipo, " + "	imovel.id, "
						+ "	imovel.localidade.gerenciaRegional.id, "
						+ "	imovel.localidade.unidadeNegocio.id, "
						+ "	imovel.localidade.localidade.id, "
						+ "	imovel.localidade.id, "
						+ "	imovel.quadra.setorComercial.id, "
						+ "	imovel.quadra.rota.id, " + "	imovel.quadra.id, "
						+ "	imovel.imovelPerfil.id, "
						+ "	imovel.ligacaoAguaSituacao.id, "
						+ "	imovel.ligacaoEsgotoSituacao.id, "
						+ "	imovel.setorComercial.codigo, "
						+ "	imovel.quadra.numeroQuadra, "
						+ "	imovel.consumoTarifa.id, "
						+ "	imovel.quadra.rota.faturamentoGrupo.id " + "from "
						+ "	gcom.cadastro.imovel.Imovel imovel " + "where "
						+ "	imovel.id = :imovel";

				retorno = session.createQuery(hql).setInteger("imovel", imovel)
						.list();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id rota id guradra numero da quadra id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Thiago Tenório, Ivan Sérgio
	 * @date 25/04/2007, 08/08/2007
	 * @alteracao: Consultar por Situacao do Hidrometro diferente de Instalado;
	 *             Dois campos adicionados a quebra: Motivo Baixa e Classe
	 *             Metrologica;
	 * 
	 * @param idLocalidade
	 *            id da localida a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection getHidrometrosResumoHidrometro(Integer idMarca,
			int indice, int qtRegistros) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = "SELECT hidroMotivoBaixa.id, "
					+ "    hidroLocalArmazenagem.id, "
					+ "    hidroTipo.id, "
					+ // 4
					"    hidroSituacao.id, "
					+ // 8
					"    hidrometro.anoFabricacao, "
					+ // 5
					"    hidroMarca.id, "
					+ // 3
					"    hidroDiametro.id, "
					+ // 1
					"    hidroCapacidade.id, "
					+ // 2
					"    hidrometro.indicadorMacromedidor, "
					+ // 6
					"    count(hidrometro.id), "
					+ //
					"	 hidroMotivoBaixa.id, "
					+ "	 hidroClasseMetrologica.id "
					+ "  FROM "
					+ "   Hidrometro as hidrometro "
					+ "  left join hidrometro.hidrometroLocalArmazenagem as hidroLocalArmazenagem "
					+ "  inner join hidrometro.hidrometroTipo as hidroTipo "
					+ "  inner join hidrometro.hidrometroSituacao as hidroSituacao "
					+ "  inner join hidrometro.hidrometroMarca as hidroMarca "
					+ "  inner join hidrometro.hidrometroDiametro as hidroDiametro "
					+ "  inner join hidrometro.hidrometroCapacidade as hidroCapacidade "
					+ "  inner join hidrometro.hidrometroClasseMetrologica as hidroClasseMetrologica "
					+ "  left join hidrometro.hidrometroMotivoBaixa as hidroMotivoBaixa "
					+

					" WHERE "
					+
					// " hidroMotivoBaixa.id is null and hidroMarca.id =
					// :idMarca " +
					"  hidroSituacao.id <> 1 and hidroMarca.id = :idMarca "
					+

					" group by hidroMotivoBaixa.id, hidroLocalArmazenagem.id, hidroTipo.id, hidroSituacao.id, "
					+ " hidrometro.anoFabricacao, hidroMarca.id, hidroDiametro.id, hidroCapacidade.id, "
					+ " hidrometro.indicadorMacromedidor, hidroMotivoBaixa.id, hidroClasseMetrologica.id ";

			retorno = session.createQuery(hql).setInteger("idMarca", idMarca)
					.setFirstResult(indice).setMaxResults(qtRegistros).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Método que insere em batch uma lista de ResumoRegistroAtendimento
	 * 
	 * [UC0586] - Gerar Resumo Resgistro Atendimento
	 * 
	 * @author Thiago Tenório
	 * @date 30/04/2007
	 * 
	 * @param listaResumoRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoHidrometroBatch(
			List<UnResumoHidrometro> listaResumoHidrometro)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		if (listaResumoHidrometro != null && !listaResumoHidrometro.isEmpty()) {
			Iterator it = listaResumoHidrometro.iterator();
			int i = 1;
			try {
				while (it.hasNext()) {

					Object obj = it.next();

					session.save(obj);

					if (i % 50 == 0 || !it.hasNext()) {
						// 20, same as the JDBC batch size
						// flush a batch of inserts and release memory:
						session.flush();
						session.clear();
					}
					i++;
				}

				session.flush();
				session.clear();

			} finally {
				HibernateUtil.closeSession(session);
			}
		}
	}

	/**
	 * [UC0269] - Consultar Resumo dos Registro de Atendimentos
	 * 
	 * @author Thiago Tenório
	 * @date 25/04/2007
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoHidrometro(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(
					GeradorSqlRelatorio.RESUMO_HIDROMETRO,
					informarDadosGeracaoRelatorioConsultaHelper);

			String sql = geradorSqlRelatorio.sqlNivelUm(geradorSqlRelatorio
					.getNomeCampoFixo(), geradorSqlRelatorio
					.getNomeTabelaFixo(), geradorSqlRelatorio
					.getNomeTabelaFixoTotal(), "'"
					+ informarDadosGeracaoRelatorioConsultaHelper
							.getDescricaoOpcaoTotalizacao() + "'", "", "", "",
					false, false);

			// faz a pesquisa
			retorno = session
					.createSQLQuery(sql)
					.addScalar("estado", Hibernate.STRING)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("descricaoGerencia", Hibernate.STRING)
					.addScalar("idElo", Hibernate.INTEGER)
					.addScalar("descricaoElo", Hibernate.STRING)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("descricaoLocalidade", Hibernate.STRING)
					.addScalar("idSetorComercial", Hibernate.INTEGER)
					.addScalar("descricaoSetorComercial", Hibernate.STRING)
					.addScalar("idQuadra", Hibernate.INTEGER)
					.addScalar("descricaoQuadra", Hibernate.STRING)
					.addScalar("idLigacaoAguaSituacao", Hibernate.INTEGER)
					.addScalar("descricaoLigacaoAguaSituacao", Hibernate.STRING)
					.addScalar("idLigacaoEsgotoSituacao", Hibernate.INTEGER)
					.addScalar("descricaoLigacaoEsgotoSituacao",
							Hibernate.STRING).addScalar("idCategoria",
							Hibernate.INTEGER).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção com os resultados da pesquisa
		return retorno;
	}

	public Long maximoIdImovel() throws ErroRepositorioException {

		Long retorno = 0L;

		Session session = HibernateUtil.getSession();

		try {

			String hql = "select max(imovel.id) from Imovel imovel";
			retorno = ((Integer) session.createQuery(hql).uniqueResult())
					.longValue();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	// public List getHidrometrosResumoHidrometro(int hidrometroMotivoBaixa, int
	// anoMesArrecadacao) throws ErroRepositorioException {
	// // TODO Auto-generated method stub
	// return null;
	// }

	/**
	 * calcula a quantidade de hidrometro instalados atualmente ramal de água
	 * 
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetro
	 * 
	 * @author Pedro Alexandre
	 * @date 08/08/2007
	 * 
	 * @param idLigacaoAgua
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeHidrometroInstalacaoRamalAgua(
			Integer idLigacaoAgua) throws ErroRepositorioException {

		Collection retorno = null;

		Integer qtd = 0;

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			// Monta o hql
			consulta = "select hidi "
					+ "from HidrometroInstalacaoHistorico hidi "
					+ "left join hidi.ligacaoAgua lagu "
					+ "where lagu.id = :idLigacaoAgua and "
					+ "hidi.dataRetirada is null ";

			// Executa o hql
			retorno = session.createQuery(consulta).setInteger("idLigacaoAgua",
					idLigacaoAgua).list();

			if (retorno != null) {
				qtd = retorno.size();
			}
			// Erro na consulta
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// Retorna a coleção pesquisada
		return qtd;
	}

	/**
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_consumo_agua
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoConsumoAgua()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + " ch.percentualColeta "
			consulta = "SELECT max(resConsAgua.referencia) "
					+ " FROM "
					+ " gcom.gerencial.cadastro.UnResumoConsumoAgua resConsAgua ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
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
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_coleta_esgoto
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoColetaEsgoto()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + " ch.percentualColeta "
			consulta = "SELECT max(resColEsgoto.referencia) "
					+ " FROM "
					+ " gcom.gerencial.micromedicao.UnResumoColetaEsgoto resColEsgoto ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
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
	 * Seleciona o maior mês/ano de referência da tabela
	 * un_resumo_leitura_anormalidade
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoLeituraAnormalidade()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + " ch.percentualColeta "
			consulta = "SELECT max(resLeitAnorm.referencia) "
					+ " FROM "
					+ " gcom.gerencial.micromedicao.UnResumoLeituraAnormalidade resLeitAnorm ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
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
	 * Seleciona o maior mês/ano de referência da tabela
	 * un_resumo_instalacao_hidrometro
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoInstalacaoHidrometro()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + " ch.percentualColeta "
			consulta = "SELECT max(resInstHidr.referencia) "
					+ " FROM "
					+ " gcom.gerencial.micromedicao.UnResumoInstalacaoHidrometro resInstHidr ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
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
	 * Seleciona o maior mês/ano de referência da tabela
	 * un_resumo_indicador_desempenho_micromedicao
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoIndicadorDesempenhoMicromedicao()
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + " ch.percentualColeta "
			consulta = "SELECT max(resIndDesMicromedicao.anoMesReferencia) "
					+ " FROM "
					+ " gcom.gerencial.micromedicao.UnResumoIndicadorDesempenhoMicromedicao resIndDesMicromedicao ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
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
	 * Atualiza os dados na tabela un_resumo_indicador_desempenho_micromedicao
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa, Ivan Sérgio
	 * @date 12/03/2008, 26/08/2008
	 * @alteracao: Alterado o esquema de cadastro para micromedicao.
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	//OVERRIDE - Metodo sobrescrito na classe RepositorioGerencialMicromedicaoPostgresHBM
	public void atualizarDadosResumoIndicadorDesempenhoMicromedicao(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas)
			throws ErroRepositorioException {

		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {

			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO micromedicao.un_resi_des_mmd "
					+ " SELECT "
					+ Util.obterNextValSequence("micromedicao.seq_un_resi_des_mmd") + ", "
					+ " reca_amreferencia, to_number(reca_anoreferencia, 9999), "
					+ " reca_mesreferencia, greg_id, uneg_id, loca_id, loca_cdelo, "
					+ " stcm_id, qdra_id, rota_id, reca_cdsetorcomercial, reca_nnquadra, "
					+ " iper_id, last_id, lest_id, catg_id, scat_id, epod_id, cltp_id, "
					+ " lapf_id, lepf_id, reca_qtligacoes_ativas, reca_qtligacoes_com_hidrometro, "
					+ " reca_qtligacoes_com_medicao_real, reca_qtligacoes_com_hidrometro_e_medicao_estimada, "
					+ " reca_qteconomias_ativas, reca_qteconomias_com_hidrometro, reca_qteconomias_com_medicao_real, "
					+ " reca_qteconomias_com_hidrometro_e_medicao_estimada, "
					+ " reca_consumoagua_ativas, reca_consumoagua_com_hidrometro, reca_consumoagua_com_medicao_real, "
					+ " reca_consumoagua_com_hidrometro_e_medicao_estimada, "
					+ " reca_vofaturadoagua, reca_vofaturadoaguamedido, reca_vofaturadoaguanaomedido, "
					+ " rece_qtligacoes, rece_qteconomias, rece_voesgoto, "
					+ " rece_vofaturadoesgoto, rece_vofaturadoesgotomedido, rece_vofaturadoesgotonaomedido, "
					+ " relt_qtvisitas_realizadas, relt_qtleituras_efetuadas, relt_qtleituras_com_anormalidade_hidrometro, "
					+ " reih_qthidrometro_instalado_ramal, reih_qthidrometro_substituido_ramal, reih_qthidrometro_remanejado_ramal, "
					+ " reih_qthidrometro_retirado_ramal, reih_qthidrometrosatualinstaladosramal, "
					+ " reih_qthidrometro_instalado_poco, reih_qthidrometro_substituido_poco, reih_qthidrometro_remanejado_poco, "
					+ " reih_qthidrometro_retirado_poco, reih_qthidrometrosatualinstaladospoco, "
					+ " reih_qthidrometro_dadosatualizados,  sysdate "
					+ " FROM micromedicao.vw_un_resi_des_mmd ";

			if (anoMesReferenciaIndicador != null) {
				consulta = consulta + " WHERE reca_amreferencia > "
						+ anoMesReferenciaIndicador
						+ " and reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			} else {
				consulta = consulta + " WHERE reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			}

			//consulta += "\n limit 1";
			System.out.println("inicio Batch atualizarDadosResumoIndicadorDesempenhoMicromedicao:" + new Date());
			stmt.executeUpdate(consulta);
			System.out.println("fim Batch atualizarDadosResumoIndicadorDesempenhoMicromedicao:" + new Date());

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexões");
			}
		}

	}

	/**
	 * calcula a quantidade de hidrometro instalados atualmente no poço
	 * 
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetro
	 * 
	 * @author Pedro Alexandre
	 * @date 08/08/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeHidrometroInstalacaoPoco(Integer idImovel)
			throws ErroRepositorioException {

		Collection retorno = null;

		Integer qtd = 0;

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			// Monta o hql
			consulta = "select hidi "
					+ "from HidrometroInstalacaoHistorico hidi "
					+ "left join hidi.imovel imov "
					+ "where imov.id = :idImovel and "
					+ "hidi.dataRetirada is null ";

			// Executa o hql
			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).list();

			if (retorno != null) {
				qtd = retorno.size();
			}
			// Erro na consulta
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// Retorna a coleção pesquisada
		return qtd;
	}

	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Distrito Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Collection<Object[]>
	 * 
	 */
	public Collection<GDistritoOperacional> pesquisarRelatorioResumoDistritoOperacional(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSessionGerencial();

		String referencia = filtro.getMesAno();
		try {
			String consulta = "";
			Query query = null;

			consulta = "select distinct do "
					+ "from gcom.gerencial.cadastro.UnResumoConsumoAgua unReAg "
					+ "inner join unReAg.gerQuadra qdra "
					+ "inner join qdra.gerDistritoOperacional do "
					+ "where unReAg.referencia = :referencia ";

			if (filtro.getGerenciaRegional() != null
					&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
				consulta = consulta + "and unReAg.gerGerenciaRegional.id =  "
						+ filtro.getGerenciaRegional() + " ";
			}
			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				consulta = consulta + "and unReAg.gerUnidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio() + " ";
			}
			if (filtro.getDistritoOperacional() != null
					&& !filtro.getDistritoOperacional().equalsIgnoreCase("")) {
				consulta = consulta + "and do.id =  "
						+ filtro.getDistritoOperacional() + " ";
			}
			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {
				consulta = consulta + "and unReAg.gerLocalidade.id between "
						+ filtro.getLocalidadeInicial() + " and "
						+ filtro.getLocalidadeFinal() + " ";
			}
			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {
				consulta = consulta
						+ "and unReAg.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}

			query = session.createQuery(consulta).setParameter("referencia",
					referencia);

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no HIbernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0892]Pesquisa Total de registro do Relatorio Resumo Distrito
	 * Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 17/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarTotalRegistroRelatorioResumoDistritoOperacional(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSessionGerencial();

		try {
			String consulta = "";

			consulta = "select  count(*) "
					+ "from gcom.gerencial.cadastro.UnResumoConsumoAgua unReAg "
					+ "inner join unReAg.gerQuadra qdra "
					+ "inner join qdra.gerDistritoOperacional do "
					+ "where unReAg.referencia = :mesAno";

			if (filtro.getGerenciaRegional() != null
					&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
				consulta = consulta + " and unReAg.gerGerenciaRegional.id =  "
						+ filtro.getGerenciaRegional();
			}

			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				consulta = consulta + " and unReAg.gerUnidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio();
			}

			if (filtro.getDistritoOperacional() != null
					&& !filtro.getDistritoOperacional().equalsIgnoreCase("")) {
				consulta = consulta + " and do.id =  "
						+ filtro.getDistritoOperacional();
			}

			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {
				consulta = consulta + " and unReAg.gerLocalidade.id between '"
						+ filtro.getLocalidadeInicial() + "' and '"
						+ filtro.getLocalidadeFinal() + "' ";
			}

			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {
				consulta = consulta
						+ "and unReAg.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}

			Integer mesAno = new Integer(filtro.getMesAno());

			retorno = (Integer) session.createQuery(consulta).setParameter(
					"mesAno", mesAno).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no HIbernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioResumoZonaAbastecimento(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSessionGerencial();

		String referencia = filtro.getMesAno();
		try {
			String consulta = "";
			Query query = null;

			consulta = "SELECT gRota.id, " + // 0
					"gRota.codigoRota, " + // 1
					"localidade.id, " + // 2
					"localidade.nomelocalidade, " + // 3
					"setorComercial.codigo, " + // 4
					"gerenciaRegional.id, " + // 5
					"gerenciaRegional.nome, " + // 6
					"do.id, " + // 7
					"do.descricao, " + // 8
					"setorComercial.id " + // 9
					"FROM gcom.gerencial.cadastro.UnResumoConsumoAgua unReAg "
					+ "INNER JOIN unReAg.gerRota gRota "
					+ "INNER JOIN unReAg.gerQuadra qdra "
					+ "INNER JOIN unReAg.gerLocalidade localidade "
					+ "INNER JOIN unReAg.gerSetorComercial setorComercial "
					+ "INNER JOIN unReAg.gerUnidadeNegocio unidadeNegocio "
					+ "INNER JOIN unReAg.gerGerenciaRegional gerenciaRegional "
					+ "INNER JOIN qdra.gerDistritoOperacional do ";

			consulta = consulta + "WHERE unReAg.referencia = :mesAno ";

			if (filtro.getGerenciaRegional() != null
					&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
				consulta = consulta + "and gerenciaRegional.id =  "
						+ filtro.getGerenciaRegional() + " ";
			}
			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				consulta = consulta + "and unidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio() + " ";
			}
			if (filtro.getDistritoOperacional() != null
					&& !filtro.getDistritoOperacional().equalsIgnoreCase("")) {
				consulta = consulta + "and do.id =  "
						+ filtro.getDistritoOperacional() + " ";
			}
			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {
				consulta = consulta + "and localidade.id between "
						+ filtro.getLocalidadeInicial() + " and "
						+ filtro.getLocalidadeFinal() + " ";
			}
			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {
				consulta = consulta
						+ "and unReAg.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}
			consulta += "group by "+
					"gerenciaRegional.id, "+
					"gerenciaRegional.nome, "+ 
					"do.id, "+
					"do.descricao, "+
					"localidade.id, " +
					"localidade.nomelocalidade, "+
					"setorComercial.id, " +
					"setorComercial.codigo, "+
					"gRota.id,"+ 
					"gRota.codigoRota ";
			consulta += "order by "+ 
					"gerenciaRegional.id, " + 
					"do.id, "+
					"localidade.id, " + 
					"setorComercial.codigo, "+
				    "gRota.codigoRota";

			query = session.createQuery(consulta).setParameter("mesAno",
					referencia);

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no HIbernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Integer pesquisarTotalRelatorioResumoZonaAbastecimento(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSessionGerencial();

		String referencia = filtro.getMesAno();
		try {
			String consulta = "";
			Query query = null;

			consulta = "SELECT Count(*)"
					+ "FROM gcom.gerencial.micromedicao.GRota gRota"
					+ "INNER JOIN  gcom.gerencial.cadastro.UnResumoConsumoAgua unReAg";
			if (filtro.getDistritoOperacional() != null
					&& !filtro.getDistritoOperacional().equalsIgnoreCase("")) {
				consulta = consulta + " INNER JOIN unReAg.gerQuadra qdra "
						+ "INNER JOIN qdra.gerDistritoOperacional do";
			}
			consulta = consulta
					+ " WHERE gRota.id = unReAg.gerRota.id and unReAg.referencia = :mesAno ";

			if (filtro.getGerenciaRegional() != null
					&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
				consulta = consulta + "and unReAg.gerGerenciaRegional.id =  "
						+ filtro.getGerenciaRegional() + " ";
			}
			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				consulta = consulta + "and unReAg.gerUnidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio() + " ";
			}
			if (filtro.getDistritoOperacional() != null
					&& !filtro.getDistritoOperacional().equalsIgnoreCase("")) {
				consulta = consulta + "and do.id =  "
						+ filtro.getDistritoOperacional() + " ";
			}
			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {
				consulta = consulta + "and unReAg.gerLocalidade.id between "
						+ filtro.getLocalidadeInicial() + " and "
						+ filtro.getLocalidadeFinal() + " ";
			}
			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {
				consulta = consulta
						+ "and unReAg.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}

			query = session.createQuery(consulta).setParameter("mesAno",
					referencia);

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no HIbernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0892]Consulta quantidade de dados de Hidrometos por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 17/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarQuantidadeHidrometros(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro,
			int parametro, Integer id, String indicador, String referencia,
			String idDistrito, String idGerencia)
			throws ErroRepositorioException {

		Integer retorno = null;
		Query query = null;

		Session session = HibernateUtil.getSessionGerencial();

		try {
			String consulta = "select SUM(unReAg.quantidadeLigacoes)"
					+ "from gcom.gerencial.cadastro.UnResumoConsumoAgua unReAg "
					+ "inner join unReAg.gerQuadra qdra "
					+ " where unReAg.indicadorHidrometro = :parametro and unReAg.referencia = :mesAno";

			if (indicador.equals("distrito")) {
				consulta += " and qdra.gerDistritoOperacional.id = :id ";
				if (filtro.getGerenciaRegional() != null
						&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {

					consulta = consulta
							+ "and unReAg.gerGerenciaRegional.id =  "
							+ filtro.getGerenciaRegional() + " ";
				}
			} else if (indicador.equals("rota")) {

				consulta += " and unReAg.gerRota.id = :id "
						+ "and qdra.gerDistritoOperacional.id = :idDistrito "
						+ "and unReAg.gerGerenciaRegional.id = :idGerencia ";

			}

			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {

				consulta = consulta + "and unReAg.gerUnidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio() + " ";
			}

			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {

				consulta = consulta + "and unReAg.gerLocalidade.id between "
						+ filtro.getLocalidadeInicial() + " and "
						+ filtro.getLocalidadeFinal() + " ";
			}

			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {

				consulta = consulta
						+ "and unReAg.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}

			query = session.createQuery(consulta);

			query.setParameter("parametro", parametro);
			query.setParameter("id", id);
			query.setParameter("mesAno", referencia);
			if (indicador.equals("rota")) {
				query.setParameter("idDistrito", idDistrito);
				query.setParameter("idGerencia", idGerencia);

			}

			retorno = (Integer) query.uniqueResult();
			if(retorno == null ){ retorno = 0;}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC0892]Consulta Situação da Agua de Hidrometos por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 20/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarSituacaoAgua(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro,
			String parametro, Integer id, String indicador, String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException {
		Integer retorno = null;
		Query query = null;

		Session session = HibernateUtil.getSessionGerencial();
		try {
			String consulta = "select SUM(rele.quantidadeLigacoes)" +
					" from gcom.gerencial.cadastro.UnResumoLigacaoEconomia rele "
					+ "inner join rele.gerQuadra qdra "
					+ "where rele.gerLigacaoAguaSituacao = :parametro and rele.referencia = :mesAno";			
			if (indicador.equals("distrito")) {
				consulta += " and qdra.gerDistritoOperacional.id = :id ";
				if (filtro.getGerenciaRegional() != null
						&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
					consulta = consulta
							+ "and rele.gerGerenciaRegional.id =  "
							+ filtro.getGerenciaRegional() + " ";
				}
			} else if (indicador.equals("rota")) {
				consulta += " and rele.gerRota.id = :id "
						+ "and qdra.gerDistritoOperacional.id = :idDistrito "
						+ "and rele.gerGerenciaRegional.id = :idGerencia ";

			}

			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				consulta = consulta + "and rele.gerUnidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio() + " ";
			}
			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {
				consulta = consulta + "and rele.gerLocalidade.id between "
						+ filtro.getLocalidadeInicial() + " and "
						+ filtro.getLocalidadeFinal() + " ";
			}
			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {
				consulta = consulta
						+ "and rele.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}

			query = session.createQuery(consulta);
			query.setParameter("parametro", parametro);
			query.setParameter("id", id);
			query.setParameter("mesAno", referencia);
			if (indicador.equals("rota")) {
				query.setParameter("idDistrito", idDistrito);
				query.setParameter("idGerencia", idGerencia);

			}

			retorno = (Integer) query.uniqueResult();
			if(retorno == null ){ retorno = 0;}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC0892]Consulta Situação da Agua de Hidrometos por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 20/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarSituacaoAguaTotal(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro, Integer id,
			String indicador, String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException {
		Integer retorno = null;
		Query query = null;

		Session session = HibernateUtil.getSessionGerencial();
		try {
			String consulta = "select SUM(rele.quantidadeLigacoes) " +
					"from gcom.gerencial.cadastro.UnResumoLigacaoEconomia rele "
					+ "inner join rele.gerQuadra qdra "
					+ "where rele.gerLigacaoAguaSituacao in (3,5) and rele.referencia = :mesAno";
			if (indicador.equals("distrito")) {
				consulta += " and qdra.gerDistritoOperacional.id = :id ";
				if (filtro.getGerenciaRegional() != null
						&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
					consulta = consulta + "and rele.gerGerenciaRegional.id =  "
							+ filtro.getGerenciaRegional() + " ";
				}
			} else if (indicador.equals("rota")) {
				consulta += " and rele.gerRota.id = :id "
					+ "and qdra.gerDistritoOperacional.id = :idDistrito "
					+ "and rele.gerGerenciaRegional.id = :idGerencia ";
			}
			
			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				consulta = consulta + "and rele.gerUnidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio() + " ";
			}
			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {
				consulta = consulta + "and rele.gerLocalidade.id between "
						+ filtro.getLocalidadeInicial() + " and "
						+ filtro.getLocalidadeFinal() + " ";
			}
			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {
				consulta = consulta
						+ "and rele.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}

			query = session.createQuery(consulta);

			query.setParameter("id", id);
			query.setParameter("mesAno", referencia);
			if (indicador.equals("rota")) {
				query.setParameter("idDistrito", idDistrito);
				query.setParameter("idGerencia", idGerencia);

			}

			retorno = (Integer) query.uniqueResult();
			if(retorno == null ){ retorno = 0;}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC0892]Consulta Economias por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 20/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarEconomias(
			String tipoRelatorio,
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro, Integer id,
			String indicador, String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException {
		Integer retorno;

		Session session = HibernateUtil.getSessionGerencial();
		try {
			if(tipoRelatorio.equals("RelatorioResumoDistritoOperacional")){
				String consulta1 = "select SUM (rele.quantidadeEconomias) "
					    + "from gcom.gerencial.cadastro.UnResumoLigacaoEconomia rele "
					    + "inner join rele.gerQuadra qdra "
					    + "where rele.gerLigacaoAguaSituacao in (1,2,3,5) and rele.referencia = :mesAno";
			    if (indicador.equals("distrito")) {
			        consulta1 += " and qdra.gerDistritoOperacional.id = :id ";
			        if (filtro.getGerenciaRegional() != null
			        	    	&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
			            consulta1 = consulta1 + "and rele.gerGerenciaRegional.id =  "
							    + filtro.getGerenciaRegional() + " ";
				    }
			    } else if (indicador.equals("rota")) {
				    consulta1 += " and rele.gerRota.id = :id "
					    + "and qdra.gerDistritoOperacional.id = :idDistrito "
					    + "and rele.gerGerenciaRegional.id = :idGerencia ";
			    }
			    if (filtro.getUnidadeNegocio() != null
				  	    && !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				    consulta1 = consulta1 + "and rele.gerUnidadeNegocio.id =  "
						    + filtro.getUnidadeNegocio() + " ";
			    }
			    if (filtro.getLocalidadeInicial() != null
					    && !filtro.getLocalidadeInicial().equals("")) {
				    consulta1 = consulta1 + "and rele.gerLocalidade.id between "
						    + filtro.getLocalidadeInicial() + " and "
						    + filtro.getLocalidadeFinal() + " ";
			    }
			    if (filtro.getSetorComercialInicial() != null
					    && !filtro.getSetorComercialInicial().equals("")) {
				    consulta1 = consulta1
						    + "and rele.gerSetorComercial.codigo between "
						    + filtro.getCodigoSetorComercialInicial() + " and "
						    + filtro.getCodigoSetorComercialFinal() + " ";
			    }

			    Query q1 = session.createQuery(consulta1);
			    q1.setParameter("id", id);
			    q1.setParameter("mesAno", referencia);
			    if (indicador.equals("rota")) {
				    q1.setParameter("idDistrito", idDistrito);
				    q1.setParameter("idGerencia", idGerencia);
			    }
			
			    Integer soma = (Integer) q1.uniqueResult();			
			    if(soma==null){
				    soma=0;
			    }
			    retorno = soma;
			
			    
			}else if(tipoRelatorio.equals("RelatorioResumoZonaAbastecimento")){
							
			    String subConsulta = "select " 
				    + "gerRota gcom.gerencial.cadastro.UnResumoLigacaoEconomia rele " 
				    + "where rele.referencia = :mesAno ";

			    String consulta2 = "select SUM (rele.quantidadeEconomias) "
					    + "from gcom.gerencial.cadastro.UnResumoLigacaoEconomia rele "
					    + "inner join rele.gerQuadra qdra "
					    + "where rele.gerLigacaoAguaSituacao in (1,2,3,5) and rele.referencia = :mesAno";
			    if (indicador.equals("distrito")) {
				    consulta2 += " and qdra.gerDistritoOperacional.id = :id and rele.gerRota.id in (" + subConsulta + " ) ";
				    if (filtro.getGerenciaRegional() != null
						    && !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
					    consulta2 = consulta2 + "and rele.gerGerenciaRegional.id =  "
							    + filtro.getGerenciaRegional() + " ";
				    }
			    } else if (indicador.equals("rota")) {
				    consulta2 += " and rele.gerRota.id = :id "
					    + "and qdra.gerDistritoOperacional.id = :idDistrito "
					    + "and rele.gerGerenciaRegional.id = :idGerencia ";
			    }
			
			    if (filtro.getUnidadeNegocio() != null
					    && !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				    consulta2 = consulta2 + "and rele.gerUnidadeNegocio.id =  "
						    + filtro.getUnidadeNegocio() + " ";
			    }
			    if (filtro.getLocalidadeInicial() != null
					    && !filtro.getLocalidadeInicial().equals("")) {
				    consulta2 = consulta2 + "and rele.gerLocalidade.id between "
						    + filtro.getLocalidadeInicial() + " and "
						    + filtro.getLocalidadeFinal() + " ";
			    }
			    if (filtro.getSetorComercialInicial() != null
					    && !filtro.getSetorComercialInicial().equals("")) {
				    consulta2 = consulta2
						    + "and rele.gerSetorComercial.codigo between "
						    + filtro.getCodigoSetorComercialInicial() + " and "
						    + filtro.getCodigoSetorComercialFinal() + " ";
			    }

			    Query q2 = session.createQuery(consulta2);
			    q2.setParameter("id", id);
			    q2.setParameter("mesAno", referencia);
			 
			    if (indicador.equals("rota")) {
				    q2.setParameter("idDistrito", idDistrito);
				    q2.setParameter("idGerencia", idGerencia);
			    }
			
			    Integer soma = (Integer) q2.uniqueResult();
			    if(soma==null){
				    soma = 0;
			    }
			    retorno = soma;
			    
			}else{
				retorno = 0;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC0892]Consulta Volume Real Medido por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 20/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarVolumeRealMedido(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro, Integer id,
			String indicador, String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException {
		Integer retorno;
		Query query;

		Session session = HibernateUtil.getSessionGerencial();
		try {
			String consulta = "select SUM (unReAg.consumoAgua) "
					+ "from gcom.gerencial.cadastro.UnResumoConsumoAgua unReAg "
					+ "inner join unReAg.gerQuadra qdra "
					+ "where unReAg.gerConsumoTipo.id in (1,2) and unReAg.referencia = :mesAno";
			if (indicador.equals("distrito")) {
				consulta += " and qdra.gerDistritoOperacional.id = :id ";
				if (filtro.getGerenciaRegional() != null
						&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
					consulta = consulta + "and unReAg.gerGerenciaRegional.id =  "
							+ filtro.getGerenciaRegional() + " ";
				}
			} else if (indicador.equals("rota")) {
				consulta += " and unReAg.gerRota.id = :id "
					+ "and qdra.gerDistritoOperacional.id = :idDistrito "
					+ "and unReAg.gerGerenciaRegional.id = :idGerencia ";
			}
			
			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				consulta = consulta + "and unReAg.gerUnidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio() + " ";
			}
			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {
				consulta = consulta + "and unReAg.gerLocalidade.id between "
						+ filtro.getLocalidadeInicial() + " and "
						+ filtro.getLocalidadeFinal() + " ";
			}
			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {
				consulta = consulta
						+ "and unReAg.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}

			query = session.createQuery(consulta);

			query.setParameter("id", id);
			query.setParameter("mesAno", referencia);
			if (indicador.equals("rota")) {
				query.setParameter("idDistrito", idDistrito);
				query.setParameter("idGerencia", idGerencia);

			}

			retorno = (Integer) query.uniqueResult();
			if (retorno == null) {
				retorno = 0;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC0892]Consultar volumes faturados por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 20/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarVolumesFaturados(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro,
			String parametro, Integer id, String indicador, String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException {
		Integer retorno = null;
		Query query = null;
		Session session = HibernateUtil.getSessionGerencial();
		try {
			String consulta = "select SUM (unReAg.volumeConsumoFaturado) "
					+ "from gcom.gerencial.cadastro.UnResumoConsumoAgua unReAg "
					+ "inner join unReAg.gerQuadra qdra "
					+ "where unReAg.indicadorHidrometro = :parametro and unReAg.referencia = :mesAno";
			if (indicador.equals("distrito")) {
				consulta += " and qdra.gerDistritoOperacional.id = :id ";
				if (filtro.getGerenciaRegional() != null
						&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
					consulta = consulta + "and unReAg.gerGerenciaRegional.id =  "
							+ filtro.getGerenciaRegional() + " ";
				}
			} else if (indicador.equals("rota")) {
				consulta += " and unReAg.gerRota.id = :id "
					+ "and qdra.gerDistritoOperacional.id = :idDistrito "
					+ "and unReAg.gerGerenciaRegional.id = :idGerencia ";
			}
			
			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				consulta = consulta + "and unReAg.gerUnidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio() + " ";
			}
			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {
				consulta = consulta + "and unReAg.gerLocalidade.id between "
						+ filtro.getLocalidadeInicial() + " and "
						+ filtro.getLocalidadeFinal() + " ";
			}
			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {
				consulta = consulta
						+ "and unReAg.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}

			query = session.createQuery(consulta);

			query.setParameter("parametro", parametro);
			query.setParameter("id", id);
			query.setParameter("mesAno", referencia);
			if (indicador.equals("rota")) {
				query.setParameter("idDistrito", idDistrito);
				query.setParameter("idGerencia", idGerencia);

			}
			retorno = (Integer) query.uniqueResult();

			if (retorno == null) {
				retorno = 0;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0892]Consultar volumes faturados por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 20/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarVolumesFaturadosTotal(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro, Integer id,
			String indicador, String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException {
		Integer retorno = null;
		Query query = null;
		Session session = HibernateUtil.getSessionGerencial();
		try {
			String consulta = "select SUM (unReAg.volumeConsumoFaturado) "
					+ "from gcom.gerencial.cadastro.UnResumoConsumoAgua unReAg "
					+ "inner join unReAg.gerQuadra qdra "
					+ "where unReAg.indicadorHidrometro in (1,2) and unReAg.referencia =:mesAno";
			if (indicador.equals("distrito")) {
				consulta += " and qdra.gerDistritoOperacional.id = :id ";
				if (filtro.getGerenciaRegional() != null
						&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
					consulta = consulta + "and unReAg.gerGerenciaRegional.id =  "
							+ filtro.getGerenciaRegional() + " ";
				}
			} else if (indicador.equals("rota")) {
				consulta += " and unReAg.gerRota.id = :id "
					+ "and qdra.gerDistritoOperacional.id = :idDistrito "
					+ "and unReAg.gerGerenciaRegional.id = :idGerencia ";
			}
			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				consulta = consulta + "and unReAg.gerUnidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio() + " ";
			}
			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {
				consulta = consulta + "and unReAg.gerLocalidade.id between "
						+ filtro.getLocalidadeInicial() + " and "
						+ filtro.getLocalidadeFinal() + " ";
			}
			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {
				consulta = consulta
						+ "and unReAg.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}

			query = session.createQuery(consulta);

			query.setParameter("id", id);
			query.setParameter("mesAno", referencia);
			if (indicador.equals("rota")) {
				query.setParameter("idDistrito", idDistrito);
				query.setParameter("idGerencia", idGerencia);

			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public Integer pesquisarSituacaoFactivelPotencial(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro, Integer id,
			String indicador, String tipo, String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException {
		Integer retorno = null;
		Query query = null;
		Session session = HibernateUtil.getSessionGerencial();
		try {
			
			String subConsulta = "select " 
				+ "gerRota from gcom.gerencial.cadastro.UnResumoConsumoAgua unResumoConsumoAgua " 
				+ "where unResumoConsumoAgua.referencia = :mesAno ";
		

			String consulta = "SELECT SUM(rele.quantidadeLigacoes) "
					+ "FROM gcom.gerencial.cadastro.UnResumoLigacaoEconomia rele "
					+ "INNER JOIN rele.gerQuadra qdra "
					+ "WHERE rele.gerLigacaoAguaSituacao.id = :tipo and rele.referencia = :mesAno ";
			if (indicador.equals("distrito")) {
				consulta += " and qdra.gerDistritoOperacional.id = :id and rele.gerRota.id in (" + subConsulta + " ) ";
				if (filtro.getGerenciaRegional() != null
						&& !filtro.getGerenciaRegional().equalsIgnoreCase("")) {
					consulta = consulta + "and rele.gerGerenciaRegional.id =  "
							+ filtro.getGerenciaRegional() +" ";
				}
			} else if (indicador.equals("rota")) {
				consulta += " and rele.gerRota.id = :id "
					+ "and qdra.gerDistritoOperacional.id = :idDistrito "
					+ "and rele.gerGerenciaRegional.id = :idGerencia ";
			}

			if (filtro.getUnidadeNegocio() != null
					&& !filtro.getUnidadeNegocio().equalsIgnoreCase("")) {
				consulta = consulta + "and rele.gerUnidadeNegocio.id =  "
						+ filtro.getUnidadeNegocio() + " ";
			}
			if (filtro.getLocalidadeInicial() != null
					&& !filtro.getLocalidadeInicial().equals("")) {
				consulta = consulta + "and rele.gerLocalidade.id between "
						+ filtro.getLocalidadeInicial() + " and "
						+ filtro.getLocalidadeFinal() + " ";
			}
			if (filtro.getSetorComercialInicial() != null
					&& !filtro.getSetorComercialInicial().equals("")) {
				consulta = consulta
						+ "and rele.gerSetorComercial.codigo between "
						+ filtro.getCodigoSetorComercialInicial() + " and "
						+ filtro.getCodigoSetorComercialFinal() + " ";
			}

			query = session.createQuery(consulta);

			query.setParameter("id", id);
			query.setParameter("mesAno", referencia);
			query.setParameter("tipo", tipo);
			if (indicador.equals("rota")) {
				query.setParameter("idDistrito", idDistrito);
				query.setParameter("idGerencia", idGerencia);

			}

			retorno = (Integer) query.uniqueResult();
			
			if(retorno == null ){ retorno = 0;}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
//	public static void main(String[] args) {
//		String consulta = "INSERT INTO micromedicao.un_resumo_indicador_desempenho_micromedicao "
//			+ " SELECT "
//			+ " nextval('micromedicao.sequence_un_resumo_indicador_desempenho_micromedicao'), "
//			+ " reca_amreferencia, to_number(reca_anoreferencia, 9999), "
//			+ " reca_mesreferencia, greg_id, uneg_id, loca_id, loca_cdelo, "
//			+ " stcm_id, qdra_id, rota_id, reca_cdsetorcomercial, reca_nnquadra, "
//			+ " iper_id, last_id, lest_id, catg_id, scat_id, epod_id, cltp_id, "
//			+ " lapf_id, lepf_id, reca_qtligacoes_ativas, reca_qtligacoes_com_hidrometro, "
//			+ " reca_qtligacoes_com_medicao_real, reca_qtligacoes_com_hidrometro_e_medicao_estimada, "
//			+ " reca_qteconomias_ativas, reca_qteconomias_com_hidrometro, reca_qteconomias_com_medicao_real, "
//			+ " reca_qteconomias_com_hidrometro_e_medicao_estimada, "
//			+ " reca_consumoagua_ativas, reca_consumoagua_com_hidrometro, reca_consumoagua_com_medicao_real, "
//			+ " reca_consumoagua_com_hidrometro_e_medicao_estimada, "
//			+ " reca_vofaturadoagua, reca_vofaturadoaguamedido, reca_vofaturadoaguanaomedido, "
//			+ " rece_qtligacoes, rece_qteconomias, rece_voesgoto, "
//			+ " rece_vofaturadoesgoto, rece_vofaturadoesgotomedido, rece_vofaturadoesgotonaomedido, "
//			+ " relt_qtvisitas_realizadas, relt_qtleituras_efetuadas, relt_qtleituras_com_anormalidade_hidrometro, "
//			+ " reih_qthidrometro_instalado_ramal, reih_qthidrometro_substituido_ramal, reih_qthidrometro_remanejado_ramal, "
//			+ " reih_qthidrometro_retirado_ramal, reih_qthidrometrosatualinstaladosramal, "
//			+ " reih_qthidrometro_instalado_poco, reih_qthidrometro_substituido_poco, reih_qthidrometro_remanejado_poco, "
//			+ " reih_qthidrometro_retirado_poco, reih_qthidrometrosatualinstaladospoco, "
//			+ " reih_qthidrometro_dadosatualizados, now() "
//			+ " FROM micromedicao.vw_un_resumo_indicador_desempenho_micromedicao ";
//
//	if (true) {
//		consulta = consulta + " WHERE reca_amreferencia > 200903 and reca_amreferencia <= 4040";
//	} else {
//		consulta = consulta + " WHERE reca_amreferencia <= 4040";
//	}
//	
//	System.out.println(consulta);
//
//	}
	
	/**
	 * Seleciona o maior mês/ano de referência da tabela
	 * un_resumo_indicador_desempenho_micromedicao_ref_2010
	 * 
	 * @author Fernando Fontelles
	 * @date 17/05/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoIndicadorDesempenhoMicromedicaoPorAno()
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			consulta = "SELECT max(resIndDesMicromedicaoRef2010.anoMesReferencia) "
					+ " FROM "
					+ " gcom.gerencial.micromedicao.UnResumoIndicadorDesempenhoMicromedicaoRef2010 resIndDesMicromedicaoRef2010 ";
			
			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
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
	 * Atualiza os dados na tabela un_resumo_indicador_desempenho_micromedicao_ref_2010
	 * 
	 * @author Fernando Fontelles
	 * @date 17/05/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	//OVERRIDE - metodo sobrescrito na classe RepositorioGerencialMicromedicaoPostgresHBM
	public void atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas)
			throws ErroRepositorioException {

		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {

			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO micromedicao.un_resi_des_mmd_ref_2010 (" 
					+ " reca_id, "
					+ " reca_amreferencia, "
					+ " greg_id, "
					+ " uneg_id, "
					+ " loca_id, "
					+ " loca_cdelo, "
					+ " stcm_id, "
					+ " reca_cdsetorcomercial, "
					+ " iper_id, "
					+ " last_id, "
					+ " lest_id, "
					+ " catg_id, "
					+ " epod_id, "
					+ " cltp_id, "
					+ " lapf_id, "
					+ " lepf_id, "
					+ " reca_qtligacoes_ativas, "
					+ " reca_qtligacoes_com_hidrometro, "
					+ " reca_qtligacoes_com_medicao_real, "
					+ " reca_qtligacoes_com_hidrometro_e_medicao_estimada, "
					+ " reca_qteconomias_ativas, "
					+ " reca_qteconomias_com_hidrometro, "
					+ " reca_qteconomias_com_medicao_real, "
					+ " reca_qteconomias_com_hidrometro_e_medicao_estimada, "
					+ " reca_consumoagua_ativas, "
					+ " reca_consumoagua_com_hidrometro, "
					+ " reca_consumoagua_com_medicao_real, "
					+ " reca_consumoagua_com_hidrometro_e_medicao_estimada, "
					+ " reca_vofaturadoagua, "
					+ " reca_vofaturadoaguamedido, "
					+ " reca_vofaturadoaguanaomedido, "
					+ " rece_qtligacoes, "
					+ " rece_qteconomias, "
					+ " rece_voesgoto, "
					+ " rece_vofaturadoesgoto, "
					+ " rece_vofaturadoesgotomedido, "
					+ " rece_vofaturadoesgotonaomedido, "
					+ " relt_qtvisitas_realizadas, "
					+ " relt_qtleituras_efetuadas, "
					+ " relt_qtleituras_com_anormalidade_hidrometro, "
					+ " reih_qthidrometro_instalado_ramal, "
					+ " reih_qthidrometro_substituido_ramal, "
					+ " reih_qthidrometro_remanejado_ramal, "
					+ " reih_qthidrometro_retirado_ramal, "
					+ " reih_qthidrometrosatualinstaladosramal, "
					+ " reih_qthidrometro_instalado_poco, "
					+ " reih_qthidrometro_substituido_poco, "
					+ " reih_qthidrometro_remanejado_poco, "
					+ " reih_qthidrometro_retirado_poco, "
					+ " reih_qthidrometrosatualinstaladospoco, "
					+ " reih_qthidrometro_dadosatualizados, "
					+ " reca_tmultimaalteracao "
					+ " ) "
					+ " SELECT "
					+ Util.obterNextValSequence("micromedicao.seq_un_resi_des_mmd_ref_2007")+ ", "
					+ " reca_amreferencia, " 
					//+ " to_number(reca_anoreferencia, 9999), "
					//+ " reca_mesreferencia," 
					+ " greg_id, " 
					+ " uneg_id, " 
					+ " loca_id, " 
					+ " loca_cdelo, "
					+ " stcm_id, " 
					//+ " qdra_id, " 
					//+ " rota_id, " 
					+ " reca_cdsetorcomercial, " 
					//+ " reca_nnquadra, "
					+ " iper_id, " 
					+ " last_id, " 
					+ " lest_id, " 
					+ " catg_id, " 
					//+ " scat_id, " 
					+ " epod_id, " 
					+ " cltp_id, "
					+ " lapf_id, " 
					+ " lepf_id, " 
					+ " reca_qtligacoes_ativas, " 
					+ " reca_qtligacoes_com_hidrometro, "
					+ " reca_qtligacoes_com_medicao_real, " 
					+ " reca_qtligacoes_com_hidrometro_e_medicao_estimada, "
					+ " reca_qteconomias_ativas, " 
					+ " reca_qteconomias_com_hidrometro, " 
					+ " reca_qteconomias_com_medicao_real, "
					+ " reca_qteconomias_com_hidrometro_e_medicao_estimada, "
					+ " reca_consumoagua_ativas, " 
					+ " reca_consumoagua_com_hidrometro, " 
					+ " reca_consumoagua_com_medicao_real, "
					+ " reca_consumoagua_com_hidrometro_e_medicao_estimada, "
					+ " reca_vofaturadoagua, " 
					+ " reca_vofaturadoaguamedido, " 
					+ " reca_vofaturadoaguanaomedido, "
					+ " rece_qtligacoes, " 
					+ " rece_qteconomias, " 
					+ " rece_voesgoto, "
					+ " rece_vofaturadoesgoto, " 
					+ " rece_vofaturadoesgotomedido, " 
					+ " rece_vofaturadoesgotonaomedido, "
					+ " relt_qtvisitas_realizadas, " 
					+ " relt_qtleituras_efetuadas, " 
					+ " relt_qtleituras_com_anormalidade_hidrometro, "
					+ " reih_qthidrometro_instalado_ramal, " 
					+ " reih_qthidrometro_substituido_ramal, " 
					+ " reih_qthidrometro_remanejado_ramal, "
					+ " reih_qthidrometro_retirado_ramal, " 
					+ " reih_qthidrometrosatualinstaladosramal, "
					+ " reih_qthidrometro_instalado_poco, " 
					+ " reih_qthidrometro_substituido_poco, " 
					+ " reih_qthidrometro_remanejado_poco, "
					+ " reih_qthidrometro_retirado_poco, " 
					+ " reih_qthidrometrosatualinstaladospoco, "
					+ " reih_qthidrometro_dadosatualizados," 
					+ "  sysdate "
					+ " FROM micromedicao.vw_un_resi_des_mmd ";

			if (anoMesReferenciaIndicador != null) {
				consulta = consulta + " WHERE reca_amreferencia > "
						+ anoMesReferenciaIndicador
						+ " and reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			} else {
				consulta = consulta + " WHERE reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			}

			System.out.println("inicio Batch atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno:" + new Date());
			stmt.executeUpdate(consulta);
			System.out.println("fim Batch atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno:" + new Date());

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexões");
			}
		}

	}
	
	/**
	 * Gerar Resumo das Instalações de Hidrômetros Por Ano
	 * 
	 * Pesquisar dados do imóvel.
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param idSetorComercial
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosImovelResumoInstalacaoHidrometroPorAno(
			Integer idSetorComercial, Date dataInicio, Date dataFim)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";
		try {

			consulta = "select "
					+ "imovel.imov_id as imovid, "
					+ "imovelperfil.iper_id as iperid, "
					+ "ligacaoaguasituacao.last_id as lastid, "
					+ "ligacaoesgotosituacao.lest_id as lestid, "
					+ "ligacaoaguaperfil.lapf_id as lapfid, "
					+ "ligacaoesgoto.lepf_id as lepfid, "
//					+ "quadra.qdra_id as idquadra, "
//					+ "quadra.qdra_nnquadra as numeroQuadra, "
//					+ "quadra.rota_id as rotaid, "
					+ "case when (hidrometro.lagu_id is not null and hidrometro.hidi_dtretiradahidrometro is null) then 1 else 2 end as ramal, "
					+ "case when (hidrometro.imov_id is not null and hidrometro.hidi_dtretiradahidrometro is null) then 1 else 2 end as poco, "
					+ "hidrometro.imov_id as hidrometroimov , "
					+ "hidrometro.lagu_id as hidrometrolagu , "
					+ "hidrometro.hidi_icinstalacaosubstituicao as indicador , "
					+ "to_char(hidrometro.hidi_dtinstalacaohidmtsistema,'YYYYMM') as anomesinstalacao, "
					+ "to_char(hidrometro.hidi_dtretiradahidrometro,'YYYYMM') as anomesretirada, "
					+ "imovel.hidi_id as idhidrimov, "
					+ "ligacaoagua.hidi_id as idhidrlagu, "
//					+ "rota.rota_cdrota as codigoRota, "
					+"imovel.cstf_id as idconsumotarifa, "
					+ "hidro.himc_id as idmarcahidrometro, "
					+ "hidro.hitp_id as idtipohidrometro, "
					+ "hidro.hicp_id as idcapacidadehidrometro, "
					+ "hidro.hidm_id as iddiametrohidrometro, "
					+ "hidro.hicm_id as idclassehidrometro "
					+ "from "
					+ "cadastro.imovel imovel "
					+ "inner join cadastro.setor_comercial setor on imovel.stcm_id=setor.stcm_id "
					+ "inner join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id and quadra.stcm_id = :idSetorComercial "
//					+ "inner join micromedicao.rota rota on rota.rota_id=quadra.rota_id and imovel.qdra_id=quadra.qdra_id and "
//					+ " quadra.stcm_id = :idSetorComercial "
					+ "inner join cadastro.imovel_perfil imovelperfil on imovel.iper_id=imovelperfil.iper_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao ligacaoaguasituacao on imovel.last_id=ligacaoaguasituacao.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao ligacaoesgotosituacao on imovel.lest_id=ligacaoesgotosituacao.lest_id "
					+ "left outer join atendimentopublico.ligacao_agua ligacaoagua on imovel.imov_id=ligacaoagua.lagu_id "
					+ "left outer join atendimentopublico.ligacao_agua_perfil ligacaoaguaperfil on ligacaoagua.lapf_id=ligacaoaguaperfil.lapf_id "
					+ "left outer join atendimentopublico.ligacao_esgoto ligacaoesgoto on imovel.imov_id=ligacaoesgoto.lesg_id "
					+ "left outer join atendimentopublico.ligacao_esgoto_perfil ligacaoesgotoperfil on ligacaoesgoto.lepf_id=ligacaoesgotoperfil.lepf_id "
					+ "left join micromedicao.hidrometro_inst_hist hidrometro on (imovel.imov_id=hidrometro.imov_id or imovel.imov_id = hidrometro.lagu_id) "
					+ "inner join micromedicao.hidrometro hidro on hidro.hidr_id = hidrometro.hidr_id "
					+"where " 
					+ " setor.stcm_id=:idSetorComercial "
					+ " and imovel.imov_id in (select distinct(imov_id) from micromedicao.hidrometro_inst_hist "
					+ "where (hidi_dtinstalacaohidmtsistema <= :dataFim  or hidi_dtretiradahidrometro between :dataInicio and :dataFim) "
					+ "union	"
					+ "select distinct(lagu_id) from micromedicao.hidrometro_inst_hist "
					+ "where (hidi_dtinstalacaohidmtsistema <= :dataFim or hidi_dtretiradahidrometro between :dataInicio and :dataFim) "
					+ ")";

			retorno = session.createSQLQuery(consulta).addScalar("imovid",
					Hibernate.INTEGER).addScalar("iperid", Hibernate.INTEGER)
					.addScalar("lastid", Hibernate.INTEGER).addScalar("lestid",
							Hibernate.INTEGER).addScalar("lapfid",
							Hibernate.INTEGER).addScalar("lepfid",
//							Hibernate.INTEGER).addScalar("idquadra",
//							Hibernate.INTEGER).addScalar("numeroQuadra",
//							Hibernate.INTEGER).addScalar("rotaid",
							Hibernate.INTEGER).addScalar("ramal",
							Hibernate.INTEGER).addScalar("poco",
							Hibernate.INTEGER)

					.addScalar("hidrometroimov", Hibernate.INTEGER).addScalar(
							"hidrometrolagu", Hibernate.INTEGER).addScalar(
							"indicador", Hibernate.SHORT).addScalar(
							"anomesinstalacao", Hibernate.STRING).addScalar(
							"anomesretirada", Hibernate.STRING).addScalar(
							"idhidrimov", Hibernate.INTEGER).addScalar(
							"idhidrlagu", Hibernate.INTEGER)/*.addScalar(
							"codigoRota", Hibernate.SHORT)*/

					.addScalar("idconsumotarifa", Hibernate.INTEGER).addScalar(
							"idmarcahidrometro", Hibernate.INTEGER).addScalar(
							"idtipohidrometro", Hibernate.INTEGER).addScalar(
							"idcapacidadehidrometro", Hibernate.INTEGER)
					.addScalar("iddiametrohidrometro", Hibernate.INTEGER)
					.addScalar("idclassehidrometro", Hibernate.INTEGER)

					.setInteger("idSetorComercial", idSetorComercial).setDate(
							"dataInicio", dataInicio).setDate("dataFim",
							dataFim).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
}
