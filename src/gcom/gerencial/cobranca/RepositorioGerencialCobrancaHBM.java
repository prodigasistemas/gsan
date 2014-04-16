package gcom.gerencial.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.DocumentoEmissaoForma;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.ResumoCobrancaAcao;
import gcom.cobranca.ResumoCobrancaAcaoEventual;
import gcom.cobranca.ResumoCobrancaSituacaoEspecial;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaContasGerenciaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaContasGerencialPorAnoHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaCreditoARealizarGerenciaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaCreditoARealizarGerencialPorAnoHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaDebitosACobrarGerenciaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaDebitosACobrarGerencialPorAnoHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaGuiasPagamentoGerenciaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper;
import gcom.gerencial.faturamento.bean.ConsultarResumoSituacaoEspecialHelper;
import gcom.util.ErroRepositorioException;
import gcom.util.GeradorSqlRelatorio;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGerencialCobrancaHBM implements
		IRepositorioGerencialCobranca {

	protected static IRepositorioGerencialCobranca instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	protected RepositorioGerencialCobrancaHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioGerencialCobranca getInstancia() {

		String dialect = HibernateUtil.getDialect();
		
		if (dialect.toUpperCase().contains("ORACLE")){
			if (instancia == null) {
				instancia = new RepositorioGerencialCobrancaHBM();
			}
		} else {
			if (instancia == null) {
				instancia = new RepositorioGerencialCobrancaPostgresHBM();
			}
		}

		return instancia;
	}

	/**
	 * 
	 * Método que consulta os ResumoSituacaoEspecialCobrancaHelper
	 * 
	 * @author Thiago Toscano
	 * @date 15/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getResumoSituacaoEspecialCobrancaHelper(int idLocalidade)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"
			String hql = " select "
					+
					// " new " +
					// ResumoCobrancaSituacaoEspecialHelper.class.getName() + "
					// ( " +
					"	distinct (imovel.id), gerenciaRegional.id,  "
					+ "	localidade.id, setorComercial.id,  "
					+ "	rota.id, quadra.id, setorComercial.codigo, "
					+ "	quadra.numeroQuadra, imovelPerfil.id, ligacaoAguaSituacao.id, "
					+ "	ligacaoEsgotoSituacao.id, "
					+ "	case when (clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.RESPONSAVEL
					+ " and"
					+ " 	clienteImoveis.dataFimRelacao is null and esferaPoder.id is not null)"
					+ " 	then esferaPoder.id else 0 end,  "
					+ "	cobrancaSituacaoTipo.id, cobrancaSituacaoMotivo.id, "
					+ "	cobrancaSituacaoHistorico.anoMesCobrancaSituacaoInicio, "
					+ "	cobrancaSituacaoHistorico.anoMesCobrancaSituacaoFim "
					+
					// " ) " +
					" from "
					+ "	gcom.cobranca.CobrancaSituacaoHistorico cobrancaSituacaoHistorico "
					+ "	inner join cobrancaSituacaoHistorico.cobrancaSituacaoTipo cobrancaSituacaoTipo "
					+ "	inner join cobrancaSituacaoHistorico.cobrancaSituacaoMotivo cobrancaSituacaoMotivo "
					+ "	inner join cobrancaSituacaoHistorico.imovel imovel "
					+ "	inner join imovel.localidade localidade "
					+ "	inner join localidade.gerenciaRegional gerenciaRegional "
					+ "	inner join imovel.setorComercial setorComercial "
					+ "	inner join imovel.quadra quadra "
					+ "	inner join quadra.rota rota "
					+ "	inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "	inner join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ " 	inner join imovel.imovelPerfil imovelPerfil "
					+ " 	inner join imovel.clienteImoveis clienteImoveis "
					+ " 	left join clienteImoveis.clienteRelacaoTipo clienteRelacaoTipo "
					+ " 	left join clienteImoveis.cliente cliente "
					+ " 	left join cliente.clienteTipo clienteTipo "
					+ " 	left join clienteTipo.esferaPoder esferaPoder "
					+ " where "
					+ " localidade.id = :idLocalidade and "
					+ "	cobrancaSituacaoHistorico.anoMesCobrancaRetirada is null "
					+
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

	/**
	 * Método que insere o ResumoSituacaoEspecialCobranca em batch
	 * 
	 * @author Thiago Toscano
	 * @date 15/05/2006
	 * 
	 * @param listResumoFaturamentoSituacaoEspecialHelper
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoSituacaoEspecialCobranca(
			List<ResumoCobrancaSituacaoEspecial> list)
			throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();

		if (list != null && !list.isEmpty()) {
			Iterator it = list.iterator();
			//int i = 1;
			try {
				while (it.hasNext()) {

					Object obj = it.next();
					session.insert(obj);
					/*
					 * if (i % 50 == 0 || !it.hasNext()) { // 20, same as the
					 * JDBC batch size // flush a batch of inserts and release
					 * memory: session.flush(); // session.clear(); } i++;
					 */
				}
			} finally {
				HibernateUtil.closeSession(session);
			}
		}
	}

	/**
	 * Método que exclui todos os ResumoFaturamentoSituacaoEspecial
	 * 
	 * [CU0346] - Gerar Resumo de Situacao Especial de Cobrança
	 * 
	 * @author Thiago Toscano
	 * @date 15/05/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public void excluirTodosResumoCobrancaSituacaoEspecial(int idLocalidade)
			throws ErroRepositorioException {
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			session.createQuery(
					"delete gcom.cobranca.ResumoCobrancaSituacaoEspecial where loca_id="+idLocalidade).executeUpdate();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Este caso de uso gera o resumo da pendência
	 * 
	 * [UC0335] Gerar Resumo da Pendência
	 * 
	 * Gera a lista de conta da pendência das Contas
	 * 
	 * gerarResumoPendenciaContas
	 * 
	 * @author Roberta Costa
	 * @date 15/05/2006
	 * 
	 * @param sistemaParametro
	 * @return retorno
	 * @throws ErroRepositorioException
	 */
	public List getResumoPendenciaContas(SistemaParametro sistemaParametro,
			Integer idLocalidade, Integer idSetorComercial)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			/*
			 * String hql = "select conta.id from Conta conta " + "where
			 * conta.id not in (select clienteConta.conta.id from ClienteConta
			 * clienteConta) order by conta.id)";
			 */

			// 1. O sistema seleciona as contas pendentes (a partir da tabela
			// CONTA com
			// CNTA_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS) e
			// DCST_IDATUAL com o valor
			// correspondente a normal ou incluída ou retificada) e acumula a
			// quantidade de ligações,
			// a quantidade de documentos e o valor pendente, agrupando por
			// gerência regional, localidade,
			// setor comercial, código do setor comercial, rota, quadra, número
			// da quadra, perfil do imóvel,
			// situação da ligação de água, situação da ligação de esgoto,
			// principal categorai do imóvel,
			// esfera de poder do cliente responsável, indicador de existência
			// de hidromêtro,
			// tipo do documento, referência do documento, tipo de financiamento
			// e indicador de vencido
			// para inserção no Resumo da Pendência(tabela RESUMO_PENDENCIA)
			String hql = " select "
					+ "	distinct (imovel.id), conta.id, gerenciaRegional.id,  "
					+ "	localidade.id, setorComercial.id,  setorComercial.codigo, "
					+ "	rota.id, quadra.id, quadra.numeroQuadra, imovelPerfil.id, "
					+ "	ligacaoAguaSituacao.id, ligacaoEsgotoSituacao.id, "
					+ "	case when (clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.RESPONSAVEL
					+ " and"
					+ " 	(clienteImoveis.dataFimRelacao is null))"
					+ " 	then esferaPoder.id else null end,  "
					+ "	documentoTipo.id  "
					+ " from "
					+ "	ClienteConta clienteConta "
					+ "	inner join clienteConta.conta conta "
					+ "	inner join clienteConta.cliente cliente "
					+ "	inner join conta.imovel imovel "
					+ "	inner join imovel.localidade localidade "
					+ "	inner join localidade.gerenciaRegional gerenciaRegional "
					+ "	inner join imovel.setorComercial setorComercial "
					+ "	inner join imovel.quadra quadra "
					+ "	inner join quadra.rota rota "
					+ "	left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "	left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "	left join conta.documentoTipo documentoTipo "
					+ " 	left join imovel.imovelPerfil imovelPerfil "
					+ " 	left join imovel.clienteImoveis clienteImoveis "
					+ " 	left join clienteImoveis.clienteRelacaoTipo clienteRelacaoTipo "
					+ " 	left join cliente.clienteTipo clienteTipo "
					+ " 	left join clienteTipo.esferaPoder esferaPoder "
					+ " where " + "   (conta.referencia < "
					+ sistemaParametro.getAnoMesFaturamento() + ") and "
					+ "	( conta.debitoCreditoSituacaoAtual.id = "
					+ DebitoCreditoSituacao.NORMAL + " or "
					+ "     conta.debitoCreditoSituacaoAtual.id = "
					+ DebitoCreditoSituacao.INCLUIDA + " or "
					+ "     conta.debitoCreditoSituacaoAtual.id = "
					+ DebitoCreditoSituacao.RETIFICADA + "   ) "
					+ " and (localidade.id = :idLocalidade )"
					+ " and (setorComercial.id = :idSetorComercial)" +
					// " and localidade.id in(62,951,953)" +
					"";

			retorno = session.createQuery(hql).setInteger("idLocalidade",
					idLocalidade).setInteger("idSetorComercial",
					idSetorComercial).list();// .setFirstResult(inicioPesquisa).setMaxResults(1000)

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
	 * Este caso de uso gera o resumo da pendência
	 * 
	 * [UC0335] Gerar Resumo da Pendência
	 * 
	 * Gera a lista de guias de pagamento da pendência das Contas
	 * 
	 * getResumoPendenciaGuiasPagamento
	 * 
	 * @author Roberta Costa
	 * @date 17/05/2006
	 * 
	 * @param sistemaParametro
	 * @return retorno
	 * @throws ErroRepositorioException
	 */
	public List getResumoPendenciaGuiasPagamento(
			SistemaParametro sistemaParametro, Integer idLocalidade,
			Integer idSetorComercial) throws ErroRepositorioException {
		// cria a coleção de retorno
		List retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			// 1. O sistema seleciona as guias de pagamento pendentes (a partir
			// da tabela GUIA_PAGAMENTO com
			// GPAG_AMREFERENCIACONTABIL <= ( PARM_AMREFERENCIAFATURAMENTO da
			// tabela SISTEMA_PARAMETROS) e
			// DCST_IDATUAL com o valor correspondente a normal) e acumula a
			// quantidade de ligações,
			// a quantidade de economias e o valor pendente, agrupando por
			// gerência regional, localidade,
			// setor comercial, código do setor comercial, rota, quadra, número
			// da quadra, perfil do imóvel,
			// situação da ligação de água, situação da ligação de esgoto,
			// principal categorai do imóvel,
			// esfera de poder do cliente responsável, indicador de existência
			// de hidromêtro,
			// tipo do documento, referência do documento, tipo de financiamento
			// e indicador de vencido
			// para inserção no Resumo da Pendência(tabela RESUMO_PENDENCIA)
			String hql = " select "
					+ "	distinct (imovel.id), guiaPagamento.id, gerenciaRegional.id, localidade.id, "
					+ "	setorComercial.id, setorComercial.codigo, rota.id, quadra.id,  "
					+ "	quadra.numeroQuadra, imovelPerfil.id, ligacaoAguaSituacao.id, "
					+ "	ligacaoEsgotoSituacao.id, "
					+ "	case when (clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.RESPONSAVEL
					+ " and"
					+ " 	(clienteImoveis.dataFimRelacao is null))"
					+ " 	then esferaPoder.id else 0 end,  "
					+ "   imovel.id, documentoTipo.id, financiamentoTipo.id "
					+ " from "
					+ "	GuiaPagamento guiaPagamento"
					+ "	inner join guiaPagamento.cliente cliente "
					+ "	inner join guiaPagamento.imovel imovel "
					+ "	inner join imovel.localidade localidade "
					+ "	inner join localidade.gerenciaRegional gerenciaRegional "
					+ "	inner join imovel.setorComercial setorComercial "
					+ "	inner join imovel.quadra quadra "
					+ "	inner join quadra.rota rota "
					+ "	inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "	inner join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "	inner join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "   inner join guiaPagamento.documentoTipo documentoTipo "
					+ "   inner join guiaPagamento.financiamentoTipo financiamentoTipo "
					+ " 	left join imovel.imovelPerfil imovelPerfil "
					+ " 	left join imovel.clienteImoveis clienteImoveis "
					+ " 	left join clienteImoveis.clienteRelacaoTipo clienteRelacaoTipo "
					+ " 	left join cliente.clienteTipo clienteTipo "
					+ " 	left join clienteTipo.esferaPoder esferaPoder "
					+ " where "
					+ "   guiaPagamento.anoMesReferenciaContabil <= "
					+ sistemaParametro.getAnoMesFaturamento() + " and "
					+ "	guiaPagamento.debitoCreditoSituacaoAtual.id = "
					+ DebitoCreditoSituacao.NORMAL
					+ "   and (localidade.id = :idLocalidade)"
					+ " 	and (setorComercial.id = :idSetorComercial)" + "";
			retorno = session.createQuery(hql).setInteger("idLocalidade",
					idLocalidade).setInteger("idSetorComercial",
					idSetorComercial).list();

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
	 * Este caso de uso permite consultar o resumo da pendência, com a opção de
	 * impressão da consulta. Dependendo da opção de totalização sempre é gerado
	 * o relatório, sem a feração da consulta.
	 * 
	 * [UC0338] Consultar Resumo da Pendência
	 * 
	 * Gera a lista de pendências das Contas e Guias de Pagamento
	 * 
	 * consultarResumoPendencia
	 * 
	 * @author Roberta Costa
	 * @date 24/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoPendencia(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException {
		// Cria a coleção de retorno
		List retorno = null;

		// Obtém a sessão
		Session session = HibernateUtil.getSession();

		// A query abaixo realiza uma consulta a tabela de ResumoPendencia
		try {
			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(
					GeradorSqlRelatorio.PENDENCIA,
					informarDadosGeracaoRelatorioConsultaHelper);

			String sql = geradorSqlRelatorio.sqlNivelUmPendencia();

			// Faz a pesquisa
			retorno = session.createSQLQuery(sql).addScalar("estado",
					Hibernate.STRING).addScalar("tipoCategoria",
					Hibernate.STRING).addScalar("Categoria", Hibernate.STRING)
					.addScalar("tipoSituacaoAguaEsgoto", Hibernate.STRING)
					.addScalar("anoMesReferencia", Hibernate.INTEGER)
					.addScalar("somatorioLigacoes", Hibernate.INTEGER)
					.addScalar("somatorioDocumentos", Hibernate.INTEGER)
					.addScalar("somatorioDebitos", Hibernate.BIG_DECIMAL)
					.list();

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
	 * Este caso de uso permite consultar o resumo da pendência, com a opção de
	 * impressão da consulta. Dependendo da opção de totalização sempre é gerado
	 * o relatório, sem a feração da consulta.
	 * 
	 * [UC0338] Consultar Resumo da Pendência
	 * 
	 * Gera a lista de pendências das Contas e Guias de Pagamento
	 * 
	 * consultarResumoPendencia
	 * 
	 * @author Roberta Costa
	 * @date 24/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String criarCondicionaisResumoPendencia(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) {

		String sql = " ";

		// A partir daqui sera montanda a parte dos condicionais da query
		// estas condicionais serão usadas se necessarias, o q determina seus
		// usos
		// são os parametros que veem carregados no objeto
		// InformarDadosGeracaoRelatorioConsultaHelper
		// que é recebido do caso de uso [UC0304] Informar Dados para Geração de
		// Relatorio ou COnsulta
		if (informarDadosGeracaoRelatorioConsultaHelper != null) {
			sql = sql + " where" + " ( documentoTipo.id = "
					+ DocumentoTipo.CONTA + "   or documentoTipo.id = "
					+ DocumentoTipo.GUIA_PAGAMENTO + " ) and ";

			// Inicio Parametros simples
			if (informarDadosGeracaoRelatorioConsultaHelper
					.getAnoMesReferencia() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getAnoMesReferencia().toString().equals("")) {
				sql = sql
						+ " resumoPendencia.anoMesReferencia = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getAnoMesReferencia() + " and ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper
					.getGerenciaRegional() != null
					&& informarDadosGeracaoRelatorioConsultaHelper
							.getGerenciaRegional().getId() != null) {
				sql = sql
						+ " gerenciaRegional.id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getGerenciaRegional().getId() + " and ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper.getEloPolo() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getEloPolo()
							.getId() != null) {
				sql = sql
						+ " localidade.localidade.id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getEloPolo().getId() + " and ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper.getLocalidade() != null
					&& informarDadosGeracaoRelatorioConsultaHelper
							.getLocalidade().getId() != null) {
				sql = sql
						+ " localidade.id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getLocalidade().getId() + " and ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial() != null
					&& informarDadosGeracaoRelatorioConsultaHelper
							.getSetorComercial().getId() != null) {
				sql = sql
						+ " setorComercial.id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getSetorComercial().getId() + " and ";
			}

			if (informarDadosGeracaoRelatorioConsultaHelper.getQuadra() != null
					&& informarDadosGeracaoRelatorioConsultaHelper.getQuadra()
							.getId() != null) {
				sql = sql
						+ " quadra.id = "
						+ informarDadosGeracaoRelatorioConsultaHelper
								.getQuadra().getId() + " and ";
			}

			// Inicio de parametros por colecão
			// Sera lida a colecao e montado um IN() a partir dos id extraidos
			// de cada objeto da colecao.

			// [FS0002] Verificar retorno de perfis de imóvel
			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoImovelPerfil() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoImovelPerfil().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoImovelPerfil().iterator();
				ImovelPerfil imovelPerfil = null;

				sql = sql + " imovelPerfil.id in (";
				while (iterator.hasNext()) {
					imovelPerfil = (ImovelPerfil) iterator.next();
					sql = sql + imovelPerfil.getId();
					if (!iterator.hasNext()) {
						sql = sql + ",";
					}
				}
				sql = sql + ") and ";
			}

			// [FS0003] Verificar retorno de situações de ligação de água
			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoLigacaoAguaSituacao() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoLigacaoAguaSituacao().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoLigacaoAguaSituacao().iterator();
				LigacaoAguaSituacao ligacaoAguaSituacao = null;

				sql = sql + " ligacaoAguaSituacao.id in (";
				while (iterator.hasNext()) {
					ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
					sql = sql + ligacaoAguaSituacao.getId();
					if (!iterator.hasNext()) {
						sql = sql + ",";
					}
				}
				sql = sql + ") and ";
			}

			// [FS0004] Verificar retorno de situações de ligação de esgoto
			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoLigacaoEsgotoSituacao() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoLigacaoEsgotoSituacao().iterator();
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

				sql = sql + " ligacaoEsgotoSituacao.id in (";
				while (iterator.hasNext()) {
					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator
							.next();
					sql = sql + ligacaoEsgotoSituacao.getId();
					if (!iterator.hasNext()) {
						sql = sql + ",";
					}
				}
				sql = sql + ") and ";
			}

			// [FS0005] Verificar retorno de categorias
			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoCategoria() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoCategoria().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoCategoria().iterator();
				Categoria categoria = null;

				sql = sql + " categoria.id in (";
				while (iterator.hasNext()) {
					categoria = (Categoria) iterator.next();
					sql = sql + categoria.getId();
					if (!iterator.hasNext()) {
						sql = sql + ",";
					}
				}
				sql = sql + ") and ";
			}

			// [FS0006] Verificar retorno de esfera de poder
			if (informarDadosGeracaoRelatorioConsultaHelper
					.getColecaoEsferaPoder() != null
					&& !informarDadosGeracaoRelatorioConsultaHelper
							.getColecaoEsferaPoder().isEmpty()) {

				Iterator iterator = informarDadosGeracaoRelatorioConsultaHelper
						.getColecaoEsferaPoder().iterator();
				EsferaPoder esferaPoder = null;

				sql = sql + " esferaPoder.id in (";
				while (iterator.hasNext()) {
					esferaPoder = (EsferaPoder) iterator.next();
					sql = sql + esferaPoder.getId();
					if (!iterator.hasNext()) {
						sql = sql + ",";
					}
				}
				sql = sql + ") and ";
			}
		}

		// retira o " and " q fica sobrando no final da query
		sql = Util.removerUltimosCaracteres(sql, 4);

		// Abaixo codigo para ordenacao da consulta
		sql = sql + " order by " + "	gerenciaRegional.nomeAbreviado, "
				+ "	localidade.localidade.descricao, "
				+ "	setorComercial.descricao, " + "	quadra.numeroQuadra, "
				+ "	imovelPerfil.descricao, " + "	esferaPoder.descricao, "
				+ "   categoriaTipo.descricao," + "	categoria.descricao, "
				+ "	ligacaoAguaSituacao.descricao, "
				+ "	ligacaoEsgotoSituacao.descricao, "
				+ "	resumoPendencia.anoMesReferencia, "
				+ "	resumoPendencia.indicadorVencido " + "";

		return sql;
	}

	/**
	 * Este caso de uso permite consultar o resumo da pendência, com a opção de
	 * impressão da consulta. Dependendo da opção de totalização sempre é gerado
	 * o relatório, sem a feração da consulta.
	 * 
	 * [UC0338] Consultar Resumo da Pendência
	 * 
	 * Verifica se existe registros para o ano/mês refrência
	 * 
	 * verificarExistenciaAnoMesReferenciaResumo
	 * 
	 * @author Roberta Costa
	 * @date 24/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaAnoMesReferenciaResumo(
			Integer anoMesReferencia, String resumo)
			throws ErroRepositorioException {

		// Obtém a sessão
		Session session = HibernateUtil.getSession();

		Integer retorno = null;

		// A query abaixo realiza uma consulta a tabela de ResumoPendencia
		try {
			String sql =
			// "select count(*) from " + objetoResumo + "as resumo " +
			"select count(*) from " + resumo + " resumo "
					+ " where resumo.anoMesReferencia = " + anoMesReferencia
					+ "";
			// Faz a pesquisa
			retorno = (Integer) session.createQuery(sql).setMaxResults(1)
					.list().iterator().next();

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

	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaMotivoHelper(
			ConsultarResumoSituacaoEspecialHelper helper) throws ErroRepositorioException {
		// cria a coleção de retorno
		List<Object[]> retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			
			String consulta = " SELECT "
				+ " cobrancaMotivo.cbsm_id as idCobrancaMotivo, cobrancaMotivo.cbsm_dscobrancasituacaomotivo as descricaoCobrancaMotivo, "
				+ " MIN(rfse.rcse_amcobrancasituacaoinicio) as anoMesInicio, MAX(rfse.rcse_amcobrancasituacaofim) as anoMesFim, "
				+ " SUM(rfse.rcse_qtimoveis) as qtd "
				+ " FROM cobranca.resumo_cob_sit_especial rfse "
				+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
				+ " on gerenciaRegional.greg_id = rfse.greg_id "
				+ " INNER JOIN cadastro.localidade localidade "
				+ " on localidade.loca_id = rfse.loca_id "
				+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
				+ " on unidadeNegocio.uneg_id = localidade.uneg_id "
				+ " INNER JOIN cadastro.setor_comercial setorComercial "
				+ " on setorComercial.stcm_id = rfse.stcm_id "
				+ " INNER JOIN micromedicao.rota rota "
				+ " on rota.rota_id = rfse.rota_id "
				+ " INNER JOIN cobranca.cobranca_situacao_tipo cobrancaTipo "
				+ " on cobrancaTipo.cbsp_id = rfse.cbsp_id "
				+ " INNER JOIN cobranca.cobranca_situacao_motivo cobrancaMotivo "
				+ " on cobrancaMotivo.cbsm_id = rfse.cbsm_id ";

		consulta = consulta
				+ criarCondicionaisConsultarResumoCobrancaSituacaoEspecial(helper, true);

		consulta = consulta
				+ " GROUP BY cobrancaMotivo.cbsm_id, cobrancaMotivo.cbsm_dscobrancasituacaomotivo "
				+ " ORDER BY cobrancaMotivo.cbsm_id";

		retorno = session.createSQLQuery(consulta)
				.addScalar("idCobrancaMotivo", Hibernate.INTEGER)
				.addScalar("descricaoCobrancaMotivo", Hibernate.STRING)
				.addScalar("anoMesInicio", Hibernate.INTEGER)
				.addScalar("anoMesFim", Hibernate.INTEGER)
				.addScalar("qtd", Hibernate.INTEGER).list();
		
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

	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaSitTipoHelper(
			ConsultarResumoSituacaoEspecialHelper helper) throws ErroRepositorioException {
		// cria a coleção de retorno
		List<Object[]> retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			
			String consulta = " SELECT "
				+ " cobrancaTipo.cbsp_id as idCobrancaTipo, cobrancaTipo.cbsp_dscobrancasituacaotipo as descricaoCobrancaTipo, "
				+ " SUM(rfse.rcse_qtimoveis) as qtd "
				+ " FROM cobranca.resumo_cob_sit_especial rfse "
				+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
				+ " on gerenciaRegional.greg_id = rfse.greg_id "
				+ " INNER JOIN cadastro.localidade localidade "
				+ " on localidade.loca_id = rfse.loca_id "
				+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
				+ " on unidadeNegocio.uneg_id = localidade.uneg_id "
				+ " INNER JOIN cadastro.setor_comercial setorComercial "
				+ " on setorComercial.stcm_id = rfse.stcm_id "
				+ " INNER JOIN micromedicao.rota rota "
				+ " on rota.rota_id = rfse.rota_id "
				+ " INNER JOIN cobranca.cobranca_situacao_tipo cobrancaTipo "
				+ " on cobrancaTipo.cbsp_id = rfse.cbsp_id "
				+ " INNER JOIN cobranca.cobranca_situacao_motivo cobrancaMotivo "
				+ " on cobrancaMotivo.cbsm_id = rfse.cbsm_id ";

		consulta = consulta
				+ criarCondicionaisConsultarResumoCobrancaSituacaoEspecial(helper, true);

		consulta = consulta
				+ " GROUP BY cobrancaTipo.cbsp_id, cobrancaTipo.cbsp_dscobrancasituacaotipo "
				+ " ORDER BY cobrancaTipo.cbsp_id";

		retorno = session.createSQLQuery(consulta)
				.addScalar("idCobrancaTipo", Hibernate.INTEGER)
				.addScalar("descricaoCobrancaTipo", Hibernate.STRING)
				.addScalar("qtd", Hibernate.INTEGER).list();
		
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
	
	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper(
			ConsultarResumoSituacaoEspecialHelper helper) throws ErroRepositorioException {
		// cria a coleção de retorno
		List<Object[]> retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			
			String consulta = " SELECT "
				+ " setorComercial.stcm_cdsetorcomercial as codigoSetorComercial, setorComercial.stcm_nmsetorcomercial as descricaoSetorComercial, "
				+ " SUM(rfse.rcse_qtimoveis) as qtd "
				+ " FROM cobranca.resumo_cob_sit_especial rfse "
				+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
				+ " on gerenciaRegional.greg_id = rfse.greg_id "
				+ " INNER JOIN cadastro.localidade localidade "
				+ " on localidade.loca_id = rfse.loca_id "
				+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
				+ " on unidadeNegocio.uneg_id = localidade.uneg_id "
				+ " INNER JOIN cadastro.setor_comercial setorComercial "
				+ " on setorComercial.stcm_id = rfse.stcm_id "
				+ " INNER JOIN micromedicao.rota rota "
				+ " on rota.rota_id = rfse.rota_id "
				+ " INNER JOIN cobranca.cobranca_situacao_tipo cobrancaTipo "
				+ " on cobrancaTipo.cbsp_id = rfse.cbsp_id "
				+ " INNER JOIN cobranca.cobranca_situacao_motivo cobrancaMotivo "
				+ " on cobrancaMotivo.cbsm_id = rfse.cbsm_id ";

		consulta = consulta
				+ criarCondicionaisConsultarResumoCobrancaSituacaoEspecial(helper, true);

		consulta = consulta
				+ " GROUP BY setorComercial.stcm_cdsetorcomercial, setorComercial.stcm_nmsetorcomercial "
				+ " ORDER BY setorComercial.stcm_cdsetorcomercial";

		retorno = session.createSQLQuery(consulta)
				.addScalar("codigoSetorComercial", Hibernate.INTEGER)
				.addScalar("descricaoSetorComercial", Hibernate.STRING)
				.addScalar("qtd", Hibernate.INTEGER).list();
			
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

	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper(
			ConsultarResumoSituacaoEspecialHelper helper) throws ErroRepositorioException {
		// cria a coleção de retorno
		List<Object[]> retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			
			String consulta = " SELECT "
				+ " localidade.loca_id as idLocalidade, localidade.loca_nmlocalidade as nomeLocalidade, "
				+ " SUM(rfse.rcse_qtimoveis) as qtd "
				+ " FROM cobranca.resumo_cob_sit_especial rfse "
				+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
				+ " on gerenciaRegional.greg_id = rfse.greg_id "
				+ " INNER JOIN cadastro.localidade localidade "
				+ " on localidade.loca_id = rfse.loca_id "
				+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
				+ " on unidadeNegocio.uneg_id = localidade.uneg_id "
				+ " INNER JOIN cadastro.setor_comercial setorComercial "
				+ " on setorComercial.stcm_id = rfse.stcm_id "
				+ " INNER JOIN micromedicao.rota rota "
				+ " on rota.rota_id = rfse.rota_id "
				+ " INNER JOIN cobranca.cobranca_situacao_tipo cobrancaTipo "
				+ " on cobrancaTipo.cbsp_id = rfse.cbsp_id "
				+ " INNER JOIN cobranca.cobranca_situacao_motivo cobrancaMotivo "
				+ " on cobrancaMotivo.cbsm_id = rfse.cbsm_id ";

		consulta = consulta
				+ criarCondicionaisConsultarResumoCobrancaSituacaoEspecial(helper, true);

		consulta = consulta
				+ " GROUP BY localidade.loca_id, localidade.loca_nmlocalidade "
				+ " ORDER BY localidade.loca_id";

		retorno = session.createSQLQuery(consulta)
				.addScalar("idLocalidade", Hibernate.INTEGER)
				.addScalar("nomeLocalidade", Hibernate.STRING)
				.addScalar("qtd", Hibernate.INTEGER).list();
			
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
	
	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaUnidadeNegocioHelper(
			ConsultarResumoSituacaoEspecialHelper helper)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List<Object[]> retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			String consulta = " SELECT "
					+ " unidadeNegocio.uneg_id as idUnidadeNegocio, unidadeNegocio.uneg_nmabreviado as nomeAbreviadoUnidadeNegocio, "
					+ " unidadeNegocio.uneg_nmunidadenegocio as nomeUnidadeNegocio, SUM(rfse.rcse_qtimoveis) as qtd "
					+ " FROM cobranca.resumo_cob_sit_especial rfse "
					+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
					+ " on gerenciaRegional.greg_id = rfse.greg_id "
					+ " INNER JOIN cadastro.localidade localidade "
					+ " on localidade.loca_id = rfse.loca_id "
					+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
					+ " on unidadeNegocio.uneg_id = localidade.uneg_id "
					+ " INNER JOIN cadastro.setor_comercial setorComercial "
					+ " on setorComercial.stcm_id = rfse.stcm_id "
					+ " INNER JOIN micromedicao.rota rota "
					+ " on rota.rota_id = rfse.rota_id "
					+ " INNER JOIN cobranca.cobranca_situacao_tipo cobrancaTipo "
					+ " on cobrancaTipo.cbsp_id = rfse.cbsp_id "
					+ " INNER JOIN cobranca.cobranca_situacao_motivo cobrancaMotivo "
					+ " on cobrancaMotivo.cbsm_id = rfse.cbsm_id ";

			consulta = consulta
					+ criarCondicionaisConsultarResumoCobrancaSituacaoEspecial(helper, true);

			consulta = consulta
					+ " GROUP BY unidadeNegocio.uneg_id, unidadeNegocio.uneg_nmabreviado, unidadeNegocio.uneg_nmunidadenegocio "
					+ " ORDER BY unidadeNegocio.uneg_id";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("nomeAbreviadoUnidadeNegocio", Hibernate.STRING)
					.addScalar("nomeUnidadeNegocio", Hibernate.STRING)
					.addScalar("qtd", Hibernate.INTEGER).list();
			
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
			

	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaGerenciaRegionalHelper(
			ConsultarResumoSituacaoEspecialHelper helper)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		List<Object[]> retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			String consulta = " SELECT "
					+ " gerenciaRegional.greg_id as idGerenciaRegional, gerenciaRegional.greg_nmabreviado as nomeAbreviadoGerenciaRegional, "
					+ " gerenciaRegional.greg_nmregional as nomeGerenciaRegional, SUM(rfse.rcse_qtimoveis) as qtd "
					+ " FROM cobranca.resumo_cob_sit_especial rfse "
					+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
					+ " on gerenciaRegional.greg_id = rfse.greg_id "
					+ " INNER JOIN cadastro.localidade localidade "
					+ " on localidade.loca_id = rfse.loca_id "
					+ " INNER JOIN cadastro.setor_comercial setorComercial "
					+ " on setorComercial.stcm_id = rfse.stcm_id "
					+ " INNER JOIN micromedicao.rota rota "
					+ " on rota.rota_id = rfse.rota_id "
					+ " INNER JOIN cobranca.cobranca_situacao_tipo cobrancaTipo "
					+ " on cobrancaTipo.cbsp_id = rfse.cbsp_id "
					+ " INNER JOIN cobranca.cobranca_situacao_motivo cobrancaMotivo "
					+ " on cobrancaMotivo.cbsm_id = rfse.cbsm_id ";

			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().trim().equals("")) {
				consulta = consulta
						+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
						+ " on unidadeNegocio.uneg_id = localidade.uneg_id ";
			}

			consulta = consulta
					+ criarCondicionaisConsultarResumoCobrancaSituacaoEspecial(helper, true);

			consulta = consulta
					+ " GROUP BY gerenciaRegional.greg_id, gerenciaRegional.greg_nmabreviado, gerenciaRegional.greg_nmregional "
					+ " ORDER BY gerenciaRegional.greg_id";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idGerenciaRegional", Hibernate.INTEGER)
					.addScalar("nomeAbreviadoGerenciaRegional", Hibernate.STRING)
					.addScalar("nomeGerenciaRegional", Hibernate.STRING)
					.addScalar("qtd", Hibernate.INTEGER).list();

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
	
	private String criarCondicionaisConsultarResumoCobrancaSituacaoEspecial(ConsultarResumoSituacaoEspecialHelper helper, boolean pesquisaTipoMotivo) {
 		String retorno = " WHERE 1=1 ";
 		
 		if (helper.getIdGerenciaRegional() != null && !helper.getIdGerenciaRegional().trim().equals("")) {
 			retorno = retorno + " and gerenciaRegional.greg_id = " + helper.getIdGerenciaRegional();
 		}
 		
 		if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
 			retorno = retorno + " and unidadeNegocio.uneg_id = " + helper.getIdUnidadeNegocio();
 		}
 		
 		if (helper.getIdLocalidadeInicial() != null && !helper.getIdLocalidadeInicial().trim().equals("")) {
 			retorno = retorno + " and localidade.loca_id >= " + helper.getIdLocalidadeInicial();
 		}
 		
 		if (helper.getIdLocalidadeFinal() != null && !helper.getIdLocalidadeFinal().trim().equals("")) {
 			retorno = retorno + " and localidade.loca_id <= " + helper.getIdLocalidadeFinal();
 		}
 		
 		if (helper.getCodigoSetorComercialInicial() != null && !helper.getCodigoSetorComercialInicial().trim().equals("")) {
 			retorno = retorno + " and setorComercial.stcm_cdsetorcomercial >= " + helper.getCodigoSetorComercialInicial();
 		}
 		
 		if (helper.getCodigoSetorComercialFinal() != null && !helper.getCodigoSetorComercialFinal().trim().equals("")) {
 			retorno = retorno + " and setorComercial.stcm_cdsetorcomercial <= " + helper.getCodigoSetorComercialFinal();
 		}
 		
 		if (helper.getCodigoRotaInicial() != null && !helper.getCodigoRotaInicial().trim().equals("")) {
 			retorno = retorno + " and rota.rota_cdrota >= " + helper.getCodigoRotaInicial();
 		}
 		
 		if (helper.getCodigoRotaFinal() != null && !helper.getCodigoRotaFinal().trim().equals("")) {
 			retorno = retorno + " and rota.rota_cdrota <= " + helper.getCodigoRotaFinal();
 		}
	 
 		if (pesquisaTipoMotivo) {
 			if (helper.getSituacaoTipo() != null) {
		 
 				Integer[] idsSituacaoTipo = helper.getSituacaoTipo();
		 
 				String valoresIn = "";
 				for (int i = 0; i < idsSituacaoTipo.length; i++) {
 					if (!idsSituacaoTipo[i].equals("")) {
 						valoresIn = valoresIn + idsSituacaoTipo[i] + ",";
 					}
 				}
 				if (!valoresIn.equals("")) {
 			
 					retorno = retorno + " and cobrancaTipo.cbsp_id in (" + valoresIn;
 					retorno = Util.removerUltimosCaracteres(retorno, 1);
 					retorno = retorno + ") ";
 				}
 			}
 		
 			if (helper.getSituacaoMotivo() != null) {
 			 
 				Integer[] idsSituacaoMotivo = helper.getSituacaoMotivo();
		 
 				String valoresIn = "";
 				for (int i = 0; i < idsSituacaoMotivo.length; i++) {
 					if (!idsSituacaoMotivo[i].equals("")) {
 						valoresIn = valoresIn + idsSituacaoMotivo[i] + ",";
 					}
 				}
 				if (!valoresIn.equals("")) {
 			
 					retorno = retorno + " and cobrancaMotivo.cbsm_id in (" + valoresIn;
 					retorno = Util.removerUltimosCaracteres(retorno, 1);
 					retorno = retorno + ") ";
 				}
 			}
 		}
 		
 		return retorno;
	 
 	}

	public Collection<BigDecimal> pesquisarResumoCobrancaSituacaoEspecialConsultaFatEstimadoHelper(ConsultarResumoSituacaoEspecialHelper helper, 
			int anoMesReferencia) throws ErroRepositorioException {
		// cria a coleção de retorno
		List<BigDecimal> retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		  try {
			  
			  String consulta = " SELECT "
					+ " SUM(coalesce(rfsi.rfts_vlagua, 0) + coalesce(rfsi.rfts_vlesgoto, 0) + coalesce(rfsi.rfts_vldebitos, 0) - coalesce(rfsi.rfts_vlcreditos, 0)) as faturamentoEstimado "
					+ " FROM faturamento.resumo_fatur_simulacao rfsi "
					+ " INNER JOIN cadastro.gerencia_regional gerenciaRegional "
					+ " on gerenciaRegional.greg_id = rfsi.greg_id "
					+ " INNER JOIN cadastro.localidade localidade "
					+ " on localidade.loca_id = rfsi.loca_id "
					+ " INNER JOIN cadastro.setor_comercial setorComercial "
					+ " on setorComercial.stcm_id = rfsi.stcm_id "
					+ " INNER JOIN micromedicao.rota rota "
					+ " on rota.rota_id = rfsi.rota_id ";
		
			  if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
				  consulta = consulta
						+ " INNER JOIN cadastro.unidade_negocio unidadeNegocio "
						+ " on unidadeNegocio.greg_id = gerenciaRegional.greg_id ";
			  }
		
			  consulta = consulta + criarCondicionaisConsultarResumoCobrancaSituacaoEspecial(helper, false) + " and rfsi.rfts_amreferencia = :anoMesReferencia ";
		
			  retorno = session.createSQLQuery(consulta)
					.addScalar("faturamentoEstimado", Hibernate.BIG_DECIMAL)
					.setInteger("anoMesReferencia", anoMesReferencia).list();

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

	public Integer pesquisarResumoCobrancaSituacaoEspecialConsultaQtLigacoesHelper(ConsultarResumoSituacaoEspecialHelper helper,
			int anoMesReferencia)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		Integer retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSessionGerencial();
		
		try {
			  
			String consulta = " SELECT "
					+ " SUM(rle.rele_qtligacoes) as totalLigacoes "
					+ " FROM cadastro.un_res_lig_econ rle "
					+ " INNER JOIN cadastro.g_gerencia_regional gerenciaRegional "
					+ " on gerenciaRegional.greg_id = rle.greg_id "
					+ " INNER JOIN cadastro.g_localidade localidade "
					+ " on localidade.loca_id = rle.loca_id "
					+ " INNER JOIN cadastro.g_setor_comercial setorComercial "
					+ " on setorComercial.stcm_id = rle.stcm_id "
					+ " INNER JOIN micromedicao.g_rota rota "
					+ " on rota.rota_id = rle.rota_id ";
		
			if (helper.getIdUnidadeNegocio() != null && !helper.getIdUnidadeNegocio().trim().equals("")) {
				consulta = consulta
						+ " INNER JOIN cadastro.g_unidade_negocio unidadeNegocio "
						+ " on unidadeNegocio.greg_id = gerenciaRegional.greg_id ";
			}
		
			  consulta = consulta + criarCondicionaisConsultarResumoCobrancaSituacaoEspecial(helper, false) + " and rle.rele_amreferencia = :anoMesReferencia ";
		
			  retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("totalLigacoes", Hibernate.INTEGER)
					.setInteger("anoMesReferencia", anoMesReferencia).uniqueResult();

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
	 * Este caso de uso permite consultar o resumo da pendência, com a opção de
	 * impressão da consulta. Dependendo da opção de totalização sempre é gerado
	 * o relatório, sem a feração da consulta.
	 * 
	 * [UC0338] Consultar Resumo da Pendência
	 * 
	 * Gera a lista de pendências das Contas e Guias de Pagamento
	 * 
	 * consultarResumoPendencia
	 * 
	 * @author Roberta Costa
	 * @date 24/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoPendenciaEstadoPorLocalidade(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException {
		// Cria a coleção de retorno
		List retorno = null;

		// Obtém a sessão
		Session session = HibernateUtil.getSession();

		// A query abaixo realiza uma consulta a tabela de ResumoPendencia
		try {
			String sql =
			/**
			 * Primeiro bloco com as situações de Água e Esgoto SOMATORIO DOS
			 * TIPOS DE SITUAÇÃO DE ÁGUA E ESGOTO POR LOCALIDADE
			 */
			// POTENCIAL
			" select "
					+ "   'AA - ESTADO' label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, b.catg_dscategoria as label5, 'POTENCIAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e  "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 1 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, b.catg_dscategoria, 'POTENCIAL', a.rpen_amreferencia "
					+
					// FACTÍVEL
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, b.catg_dscategoria as label5, 'FACTÍVEL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e  "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 2 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, b.catg_dscategoria, 'FACTÍVEL', a.rpen_amreferencia "
					+
					// LIGADO DE ÁGUA
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, b.catg_dscategoria as label5, 'LIGADO DE ÁGUA' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e  "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, b.catg_dscategoria, 'LIGADO DE ÁGUA', a.rpen_amreferencia "
					+
					// CORTADO
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, b.catg_dscategoria as label5, 'CORTADO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e  "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, b.catg_dscategoria, 'CORTADO', a.rpen_amreferencia "
					+
					// LIGADO SÓ DE ESGOTO
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, b.catg_dscategoria as label5, 'LIGADO SÓ DE ESGOTO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e  "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, b.catg_dscategoria, 'LIGADO SÓ DE ESGOTO', a.rpen_amreferencia "
					+
					// ESGOTO FORA DE USO
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, b.catg_dscategoria as label5, 'ESGOTO FORA DE USO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e  "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, b.catg_dscategoria, 'ESGOTO FORA DE USO', a.rpen_amreferencia "
					+
					// ESGOTO TAMPONADO
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, b.catg_dscategoria as label5, 'ESGOTO TAMPONADO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e  "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 6 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, b.catg_dscategoria, 'ESGOTO TAMPONADO', a.rpen_amreferencia "
					+
					// SUPRIMIDO TOTAL
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, b.catg_dscategoria as label5, 'SUPRIMIDO TOTAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e  "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 6 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, b.catg_dscategoria, 'SUPRIMIDO TOTAL', a.rpen_amreferencia "
					+
					// SUPRIMIDO PARCIAL
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, b.catg_dscategoria as label5, 'SUPRIMIDO PARCIAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e  "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 7 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, b.catg_dscategoria, 'SUPRIMIDO PARCIAL', a.rpen_amreferencia "
					+
					// SUPRIMIDO A PEDIDO
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, b.catg_dscategoria as label5, 'SUPRIMIDO A PEDIDO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e  "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 8 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, b.catg_dscategoria, 'SUPRIMIDO A PEDIDO', a.rpen_amreferencia "
					+
					/**
					 * Segundo bloco com as situações de Água e Esgoto SOMATORIO
					 * DOS TIPOS DE SITUAÇÃO DE ÁGUA E ESGOTO POR LOCALIDADE
					 */
					// POTENCIAL
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, 'AA - TOTAL CATERGORIA' as label5, 'POTENCIAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 1 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, 'POTENCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, 'AA - TOTAL CATERGORIA' as label5, 'FACTÍVEL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 2 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, 'FACTÍVEL', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, 'AA - TOTAL CATERGORIA' as label5, 'LIGADO DE ÁGUA' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, 'LIGADO DE ÁGUA', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, 'AA - TOTAL CATERGORIA' as label5, 'CORTADO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, 'CORTADO', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, 'AA - TOTAL CATERGORIA' as label5, 'LIGADO SÓ DE ESGOTO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, 'LIGADO SÓ DE ESGOTO', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, 'AA - TOTAL CATERGORIA' as label5, 'ESGOTO FORA DE USO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, 'ESGOTO FORA DE USO', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, 'AA - TOTAL CATERGORIA' as label5, 'ESGOTO TAMPONADO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 6 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, 'ESGOTO TAMPONADO', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, 'AA - TOTAL CATERGORIA' as label5, 'SUPRIMIDO TOTAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id  "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 6 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, 'SUPRIMIDO TOTAL', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, 'AA - TOTAL CATERGORIA' as label5, 'SUPRIMIDO PARCIAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 7 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, 'SUPRIMIDO PARCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   c.loca_nmlocalidade as label4, 'AA - TOTAL CATERGORIA' as label5, 'SUPRIMIDO A PEDIDO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 8 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, c.loca_nmlocalidade, 'SUPRIMIDO A PEDIDO', a.rpen_amreferencia "
					+

					/**
					 * Terceiro bloco com as situações de Água e Esgoto
					 * SOMATORIO DOS TIPOS DE SITUAÇÃO DE ÁGUA E ESGOTO POR ELO
					 */
					" union  "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional  as label2, d.loca_nmlocalidade  as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5, 'POTENCIAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 1 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO' ,b.catg_dscategoria, 'POTENCIAL', a.rpen_amreferencia "
					+ " union  "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional  as label2, d.loca_nmlocalidade  as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5, 'FACTÍVEL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 2 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO' ,b.catg_dscategoria, 'FACTÍVEL', a.rpen_amreferencia "
					+ " union  "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional  as label2, d.loca_nmlocalidade  as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5, 'LIGADO DE ÁGUA' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO' ,b.catg_dscategoria, 'LIGADO DE ÁGUA', a.rpen_amreferencia "
					+ " union  "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional  as label2, d.loca_nmlocalidade  as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5, 'CORTADO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO' ,b.catg_dscategoria, 'CORTADO', a.rpen_amreferencia "
					+ " union  "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional  as label2, d.loca_nmlocalidade  as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5, 'LIGADO SÓ DE ESGOTO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO' ,b.catg_dscategoria, 'LIGADO SÓ DE ESGOTO', a.rpen_amreferencia "
					+ " union  "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional  as label2, d.loca_nmlocalidade  as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5, 'ESGOTO FORA DE USO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO' ,b.catg_dscategoria, 'ESGOTO FORA DE USO', a.rpen_amreferencia "
					+ " union  "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional  as label2, d.loca_nmlocalidade  as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5, 'ESGOTO TAMPONADO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 6 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO' ,b.catg_dscategoria, 'ESGOTO TAMPONADO', a.rpen_amreferencia "
					+ " union  "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional  as label2, d.loca_nmlocalidade  as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5, 'SUPRIMIDO TOTAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 6 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO' ,b.catg_dscategoria, 'SUPRIMIDO TOTAL', a.rpen_amreferencia "
					+ " union  "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional  as label2, d.loca_nmlocalidade  as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5, 'SUPRIMIDO PARCIAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 7 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO' ,b.catg_dscategoria, 'SUPRIMIDO PARCIAL', a.rpen_amreferencia "
					+ " union  "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional  as label2, d.loca_nmlocalidade  as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5, 'SUPRIMIDO A PEDIDO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 8 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO' ,b.catg_dscategoria, 'SUPRIMIDO A PEDIDO', a.rpen_amreferencia "
					+

					/**
					 * Quarto bloco com as situações de Água e Esgoto SOMATORIO
					 * DOS TIPOS DE SITUAÇÃO DE ÁGUA E ESGOTO POR ELO
					 */
					" union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA' as label5, 'POTENCIAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 1 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO', 'POTENCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA' as label5, 'FACTÍVEL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ " from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "      cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 2 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO', 'FACTÍVEL', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA' as label5, 'LIGADO DE ÁGUA' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "  from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO', 'LIGADO DE ÁGUA', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA' as label5, 'CORTADO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO', 'CORTADO', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA' as label5, 'LIGADO SÓ DE ESGOTO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO', 'LIGADO SÓ DE ESGOTO', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA' as label5, 'ESGOTO FORA DE USO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO', 'ESGOTO FORA DE USO', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA' as label5, 'ESGOTO TAMPONADO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 6 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO', 'ESGOTO TAMPONADO', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA' as label5, 'SUPRIMIDO TOTAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 6 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO', 'SUPRIMIDO TOTAL', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA' as label5, 'SUPRIMIDO PARCIAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 7 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO', 'SUPRIMIDO PARCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select  "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, d.loca_nmlocalidade as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA' as label5, 'SUPRIMIDO A PEDIDO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 8 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, d.loca_nmlocalidade, 'AA - TOTAL ELO', 'SUPRIMIDO A PEDIDO', a.rpen_amreferencia "
					+

					/**
					 * Quinto bloco com as situações de Água e Esgoto SOMATORIO
					 * DOS TIPOS DE SITUAÇÃO DE ÁGUA E ESGOTO POR GERÊNCIA
					 */
					" union "
					+ " select "
					+ "  'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "  'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'POTENCIAL' as label6, "
					+ "  a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "  sum(a.rpen_vldebito) as label10 "
					+ " from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 1 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'POTENCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "  'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "  'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'FACTÍVEL' as label6, "
					+ "  a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "  sum(a.rpen_vldebito) as label10 "
					+ " from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 2 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'FACTÍVEL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "  'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "  'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'LIGADO DE ÁGUA' as label6, "
					+ "  a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "  sum(a.rpen_vldebito) as label10 "
					+ " from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'LIGADO DE ÁGUA', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "  'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "  'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'CORTADO' as label6, "
					+ "  a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "  sum(a.rpen_vldebito) as label10 "
					+ " from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'CORTADO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "  'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "  'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'LIGADO SÓ DE ESGOTO' as label6, "
					+ "  a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "  sum(a.rpen_vldebito) as label10 "
					+ " from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'LIGADO SÓ DE ESGOTO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "  'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "  'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'ESGOTO FORA DE USO' as label6, "
					+ "  a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "  sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'ESGOTO FORA DE USO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "  'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "  'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'ESGOTO TAMPONADO' as label6, "
					+ "  a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "  sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 6 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'ESGOTO TAMPONADO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "  'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "  'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'SUPRIMIDO TOTAL' as label6, "
					+ "  a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "  sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id  "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 6 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'SUPRIMIDO TOTAL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "  'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "  'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'SUPRIMIDO PARCIAL' as label6, "
					+ "  a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "  sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 7 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'SUPRIMIDO PARCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "  'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "  'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'SUPRIMIDO A PEDIDO' as label6, "
					+ "  a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "  sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 8 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'SUPRIMIDO A PEDIDO', a.rpen_amreferencia "
					+

					/**
					 * Sexto bloco com as situações de Água e Esgoto SOMATORIO
					 * DOS TIPOS DE SITUAÇÃO DE ÁGUA E ESGOTO POR GERÊNCIA
					 */
					" union "
					+ " select "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA'  as label5,  'POTENCIAL'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 1 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', 'POTENCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA'  as label5,  'FACTÍVEL'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 2 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', 'FACTÍVEL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA'  as label5,  'LIGADO DE ÁGUA'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', 'LIGADO DE ÁGUA', a.rpen_amreferencia "
					+ " union  "
					+ " select "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA'  as label5,  'CORTADO'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', 'CORTADO', a.rpen_amreferencia "
					+ " union  "
					+ " select "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA'  as label5,  'LIGADO SÓ DE ESGOTO'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 3 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', 'LIGADO SÓ DE ESGOTO', a.rpen_amreferencia "
					+ " union  "
					+ " select "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA'  as label5,  'ESGOTO FORA DE USO'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 5 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', 'ESGOTO FORA DE USO', a.rpen_amreferencia "
					+ " union  "
					+ " select "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA'  as label5,  'ESGOTO TAMPONADO'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 6 "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', 'ESGOTO TAMPONADO', a.rpen_amreferencia "
					+ " union  "
					+ " select "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA'  as label5,  'SUPRIMIDO TOTAL'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 6 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', 'SUPRIMIDO TOTAL', a.rpen_amreferencia "
					+ " union  "
					+ " select "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA'  as label5,  'SUPRIMIDO PARCIAL'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "  from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id  "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 7 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', 'SUPRIMIDO PARCIAL', a.rpen_amreferencia "
					+ " union  "
					+ " select "
					+ "   'AA - ESTADO' as label1, e.greg_nmregional as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, 'AA - TOTAL CATEGORIA'  as label5,  'SUPRIMIDO A PEDIDO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 8 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', e.greg_nmregional, 'AA - TOTAL GER', 'AA - TOTAL ELO', 'SUPRIMIDO A PEDIDO', a.rpen_amreferencia "
					+

					/**
					 * Sétimo bloco com as situações de Água e Esgoto SOMATORIO
					 * DOS TIPOS DE SITUAÇÃO DE ÁGUA E ESGOTO POR ESTADO
					 */
					" union "
					+ " select "
					+ "   'AA - ESTADO' as label1, 'AA - TOTAL EST' as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'POTENCIAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 1 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'POTENCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, 'AA - TOTAL EST' as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'FACTÍVEL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "  from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 2 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'FACTÍVEL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, 'AA - TOTAL EST' as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'LIGAÇÃO DE ÁGUA' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 3 "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'LIGAÇÃO DE ÁGUA', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, 'AA - TOTAL EST' as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'CORTADO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 5 "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'CORTADO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, 'AA - TOTAL EST' as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'LIGADO SÓ DE ESGOTO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "  from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 3 "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'LIGADO SÓ DE ESGOTO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, 'AA - TOTAL EST' as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'ESGOTO FORA DE USO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 5 "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'ESGOTO FORA DE USO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, 'AA - TOTAL EST' as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'ESGOTO TAMPONADO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 6 "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'ESGOTO TAMPONADO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, 'AA - TOTAL EST' as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'SUPRIMIDO TOTAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 6 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'SUPRIMIDO TOTAL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, 'AA - TOTAL EST' as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'SUPRIMIDO PARCIAL' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 7 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'SUPRIMIDO PARCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO' as label1, 'AA - TOTAL EST' as label2, 'AA - TOTAL GER' as label3, "
					+ "   'AA - TOTAL ELO' as label4, b.catg_dscategoria as label5,  'SUPRIMIDO A PEDIDO' as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes) as label8, sum(a.rpen_qtdocumentos) as label9,"
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id  "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 8 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', b.catg_dscategoria, 'SUPRIMIDO A PEDIDO', a.rpen_amreferencia "
					+

					/**
					 * Oitavo bloco com as situações de Água e Esgoto SOMATORIO
					 * DOS TIPOS DE SITUAÇÃO DE ÁGUA E ESGOTO POR ESTADO
					 */
					" union "
					+ " select "
					+ "   'AA - ESTADO'  as label1, 'AA - TOTAL EST'  as label2, 'AA - TOTAL GER'  as label3, "
					+ "   'AA - TOTAL ELO'  as label4, 'AA - TOTAL CATEGORIA'  as label5,  'POTENCIAL'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes)  as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 1 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', 'POTENCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO'  as label1, 'AA - TOTAL EST'  as label2, 'AA - TOTAL GER'  as label3, "
					+ "   'AA - TOTAL ELO'  as label4, 'AA - TOTAL CATEGORIA'  as label5,  'FACTÍVEL'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes)  as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 2 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', 'FACTÍVEL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO'  as label1, 'AA - TOTAL EST'  as label2, 'AA - TOTAL GER'  as label3, "
					+ "   'AA - TOTAL ELO'  as label4, 'AA - TOTAL CATEGORIA'  as label5,  'LIGADO DE ÁGUA'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes)  as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 3 "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', 'LIGADO DE ÁGUA', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO'  as label1, 'AA - TOTAL EST'  as label2, 'AA - TOTAL GER'  as label3, "
					+ "   'AA - TOTAL ELO'  as label4, 'AA - TOTAL CATEGORIA'  as label5,  'CORTADO'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes)  as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 5 "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', 'CORTADO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO'  as label1, 'AA - TOTAL EST'  as label2, 'AA - TOTAL GER'  as label3, "
					+ "   'AA - TOTAL ELO'  as label4, 'AA - TOTAL CATEGORIA'  as label5,  'LIGADO SÓ DE ESGOTO'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes)  as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 3 "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', 'LIGADO SÓ DE ESGOTO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO'  as label1, 'AA - TOTAL EST'  as label2, 'AA - TOTAL GER'  as label3, "
					+ "   'AA - TOTAL ELO'  as label4, 'AA - TOTAL CATEGORIA'  as label5,  'ESGOTO FORA DE USO'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes)  as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "  from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 5 "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', 'ESGOTO FORA DE USO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO'  as label1, 'AA - TOTAL EST'  as label2, 'AA - TOTAL GER'  as label3, "
					+ "   'AA - TOTAL ELO'  as label4, 'AA - TOTAL CATEGORIA'  as label5,  'ESGOTO TAMPONADO'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes)  as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " 	and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id  "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id not in(3,5) "
					+ " and   a.lest_id = 6 "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', 'ESGOTO TAMPONADO', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO'  as label1, 'AA - TOTAL EST'  as label2, 'AA - TOTAL GER'  as label3, "
					+ "   'AA - TOTAL ELO'  as label4, 'AA - TOTAL CATEGORIA'  as label5,  'SUPRIMIDO TOTAL'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes)  as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 6 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', 'SUPRIMIDO TOTAL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO'  as label1, 'AA - TOTAL EST'  as label2, 'AA - TOTAL GER'  as label3, "
					+ "   'AA - TOTAL ELO'  as label4, 'AA - TOTAL CATEGORIA'  as label5,  'SUPRIMIDO PARCIAL'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes)  as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id "
					+ " and   a.last_id = 7 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', 'SUPRIMIDO PARCIAL', a.rpen_amreferencia "
					+ " union "
					+ " select "
					+ "   'AA - ESTADO'  as label1, 'AA - TOTAL EST'  as label2, 'AA - TOTAL GER'  as label3, "
					+ "   'AA - TOTAL ELO'  as label4, 'AA - TOTAL CATEGORIA'  as label5,  'SUPRIMIDO A PEDIDO'  as label6, "
					+ "   a.rpen_amreferencia as label7, sum(a.rpen_qtligacoes)  as label8, sum(a.rpen_qtdocumentos) as label9, "
					+ "   sum(a.rpen_vldebito) as label10 "
					+ "   from cobranca.resumo_pendencia a, cadastro.categoria b, cadastro.localidade c, cadastro.localidade d, "
					+ "        cadastro.gerencia_regional e "
					+ " where a.catg_id = b.catg_id  "
					+ " and   a.loca_id = c.loca_id "
					+ " and   c.loca_cdelo = d.loca_id "
					+ " and   a.greg_id = e.greg_id " + " and   a.last_id = 8 "
					+ " and   a.lest_id not in(3,5,6) "
					+ " group by 'AA - ESTADO', 'AA - TOTAL EST', 'AA - TOTAL GER', 'AA - TOTAL ELO', 'SUPRIMIDO A PEDIDO', a.rpen_amreferencia "
					+ " order by 1, 2, 3, 4, 5 " + " ";

			// A partir daqui sera montanda a parte dos condicionais da query
			// estas condicionais serão usadas se necessarias, o q determina
			// seus usos
			// são os parametros que veem carregados no objeto
			// InformarDadosGeracaoRelatorioConsultaHelper
			// que é recebido do caso de uso [UC0304] Informar Dados para
			// Geração de Relatorio ou COnsulta

			/* String condicionais = */this
					.criarCondicionaisResumoPendencia(informarDadosGeracaoRelatorioConsultaHelper);

			// sql = sql + condicionais;

			// Faz a pesquisa
			// retorno = session.createQuery(sql).list();

			retorno = session.createSQLQuery(sql).addScalar("label1",
					Hibernate.STRING).addScalar("label2", Hibernate.STRING)
					.addScalar("label3", Hibernate.STRING).addScalar("label4",
							Hibernate.STRING).addScalar("label5",
							Hibernate.STRING).addScalar("label6",
							Hibernate.STRING).addScalar("label7",
							Hibernate.INTEGER).addScalar("label8",
							Hibernate.INTEGER).addScalar("label9",
							Hibernate.INTEGER).addScalar("label10",
							Hibernate.BIG_DECIMAL).list();

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

	public String criarCondicionaisResumosHQL(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper) {

		String sql = " ";
		/*
		 * A partir daqui sera montanda a parte dos condicionais da query estas
		 * condicionais serão usadas se necessarias, o q determina seus usos são
		 * os parametros que veem carregados no objeto
		 * InformarDadosGeracaoRelatorioConsultaHelper que é recebido do caso de
		 * uso [UC0304] Informar Dados para Geração de Relatorio ou COnsulta
		 */
		if (informarDadosGeracaoResumoAcaoConsultaHelper != null) {

			// Inicio Parametros simples
			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getAnoMesReferencia() != null
					&& !informarDadosGeracaoResumoAcaoConsultaHelper
							.getAnoMesReferencia().toString().equals("")) {
				sql = sql
						+ " and re.anoMesReferencia = "
						+ informarDadosGeracaoResumoAcaoConsultaHelper
								.getAnoMesReferencia();
			}
			
			/*
			 * Colocado por Raphael Rossiter em 02/09/2010
			 * TIPO DE IMPRESSÃO
			 */
			if (informarDadosGeracaoResumoAcaoConsultaHelper.getTipoImpressao() != null) {
				
				if (informarDadosGeracaoResumoAcaoConsultaHelper.getTipoImpressao()
					.equals(InformarDadosGeracaoResumoAcaoConsultaHelper.ID_IMPRESSAO_TRADICIONAL)){
					
					sql = sql + " and (re.documentoEmissaoForma.id IS NULL OR re.documentoEmissaoForma.id <> "
					+ DocumentoEmissaoForma.IMPRESSAO_SIMULTANEA.toString() + ")";
				}
				else if (informarDadosGeracaoResumoAcaoConsultaHelper.getTipoImpressao()
						.equals(InformarDadosGeracaoResumoAcaoConsultaHelper.ID_IMPRESSAO_SIMULTANEA)){
					
					sql = sql + " and re.documentoEmissaoForma.id = "
					+ DocumentoEmissaoForma.IMPRESSAO_SIMULTANEA.toString();
				}
			}

			if (informarDadosGeracaoResumoAcaoConsultaHelper.getEloPolo() != null
					&& informarDadosGeracaoResumoAcaoConsultaHelper
							.getEloPolo().getId() != null) {
				sql = sql
						+ " and re.localidade.localidade.id = "
						+ informarDadosGeracaoResumoAcaoConsultaHelper
								.getEloPolo().getId();
			}

			if (informarDadosGeracaoResumoAcaoConsultaHelper.getLocalidade() != null
					&& informarDadosGeracaoResumoAcaoConsultaHelper
							.getLocalidade().getId() != null) {
				sql = sql
						+ " and re.localidade.id = "
						+ informarDadosGeracaoResumoAcaoConsultaHelper
								.getLocalidade().getId();
			}

			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getSetorComercial() != null
					&& informarDadosGeracaoResumoAcaoConsultaHelper
							.getSetorComercial().getId() != null) {
				sql = sql
						+ " and re.setorComercial.id = "
						+ informarDadosGeracaoResumoAcaoConsultaHelper
								.getSetorComercial().getId();
			}

			if (informarDadosGeracaoResumoAcaoConsultaHelper.getQuadra() != null
					&& informarDadosGeracaoResumoAcaoConsultaHelper.getQuadra()
							.getId() != null) {
				sql = sql
						+ " and re.quadra.id = "
						+ informarDadosGeracaoResumoAcaoConsultaHelper
								.getQuadra().getId();
			}

			// Inicio de parametros por colecão
			// sera lida a colecao e montado um IN() a partis dos id extraidos
			// de cada objeto da colecao.

			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getColecaoCobrancaGrupo() != null
					&& !informarDadosGeracaoResumoAcaoConsultaHelper
							.getColecaoCobrancaGrupo().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaHelper
						.getColecaoCobrancaGrupo().iterator();
				CobrancaGrupo cobrancaGrupo = null;

				sql = sql + " and re.cobrancaGrupo.id in (";
				while (iterator.hasNext()) {
					cobrancaGrupo = (CobrancaGrupo) iterator.next();
					sql = sql + cobrancaGrupo.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getColecaoGerenciaRegional() != null
					&& !informarDadosGeracaoResumoAcaoConsultaHelper
							.getColecaoGerenciaRegional().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaHelper
						.getColecaoGerenciaRegional().iterator();
				GerenciaRegional gerenciaRegional = null;

				sql = sql + " and re.gerenciaRegional.id in (";
				while (iterator.hasNext()) {
					gerenciaRegional = (GerenciaRegional) iterator.next();
					sql = sql + gerenciaRegional.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}
			
			
//--------------------------------------------------------------------------------
			
			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getColecaoUnidadeNegocio() != null
					&& !informarDadosGeracaoResumoAcaoConsultaHelper
							.getColecaoUnidadeNegocio().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaHelper
						.getColecaoUnidadeNegocio().iterator();
				UnidadeNegocio unidadeNegocio = null;

				sql = sql + " and re.unidadeNegocio.id in (";
				while (iterator.hasNext()) {
					unidadeNegocio = (UnidadeNegocio) iterator.next();
					sql = sql + unidadeNegocio.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}
			
			
			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getColecaoImovelPerfil() != null
					&& !informarDadosGeracaoResumoAcaoConsultaHelper
							.getColecaoImovelPerfil().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaHelper
						.getColecaoImovelPerfil().iterator();
				ImovelPerfil imovelPerfil = null;

				sql = sql + " and re.imovelPerfil.id in (";
				while (iterator.hasNext()) {
					imovelPerfil = (ImovelPerfil) iterator.next();
					sql = sql + imovelPerfil.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getColecaoLigacaoAguaSituacao() != null
					&& !informarDadosGeracaoResumoAcaoConsultaHelper
							.getColecaoLigacaoAguaSituacao().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaHelper
						.getColecaoLigacaoAguaSituacao().iterator();
				LigacaoAguaSituacao ligacaoAguaSituacao = null;

				sql = sql + " and re.ligacaoAguaSituacao.id in (";
				while (iterator.hasNext()) {
					ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
					sql = sql + ligacaoAguaSituacao.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getColecaoLigacaoEsgotoSituacao() != null
					&& !informarDadosGeracaoResumoAcaoConsultaHelper
							.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaHelper
						.getColecaoLigacaoEsgotoSituacao().iterator();
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

				sql = sql + " and re.ligacaoEsgotoSituacao.id in (";
				while (iterator.hasNext()) {
					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator
							.next();
					sql = sql + ligacaoEsgotoSituacao.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getColecaoCategoria() != null
					&& !informarDadosGeracaoResumoAcaoConsultaHelper
							.getColecaoCategoria().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaHelper
						.getColecaoCategoria().iterator();
				Categoria categoria = null;

				sql = sql + " and re.categoria.id in (";
				while (iterator.hasNext()) {
					categoria = (Categoria) iterator.next();
					sql = sql + categoria.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getColecaoEsferaPoder() != null
					&& !informarDadosGeracaoResumoAcaoConsultaHelper
							.getColecaoEsferaPoder().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaHelper
						.getColecaoEsferaPoder().iterator();
				EsferaPoder esferaPoder = null;

				sql = sql + " and re.esferaPoder.id in (";
				while (iterator.hasNext()) {
					esferaPoder = (EsferaPoder) iterator.next();
					sql = sql + esferaPoder.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}
			if (informarDadosGeracaoResumoAcaoConsultaHelper
					.getColecaoEmpresa() != null
					&& !informarDadosGeracaoResumoAcaoConsultaHelper
							.getColecaoEmpresa().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaHelper
						.getColecaoEmpresa().iterator();
				Empresa empresa = null;

				sql = sql + " and re.empresa.id in (";
				while (iterator.hasNext()) {
					empresa = (Empresa) iterator.next();
					sql = sql + empresa.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}
		}

		return sql;
	}

	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcao(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoHelper (cbac.id, cbac.descricaoCobrancaAcao, "
					+ " cbac.servicoTipo.id, min(re.realizacaoEmitir), max(re.realizacaoEncerrar)," 
					+ " sum(re.quantidadeDocumentos),sum(re.valorDocumentos), cbac.numeroDiasRemuneracaoTerceiro)"
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac"
					+ " where 1 = 1 "
					+ this
							.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper)
					+ " group by cbac.id, cbac.descricaoCobrancaAcao, cbac.servicoTipo.id, cbac.numeroDiasRemuneracaoTerceiro"
					+ " order by cbac.id";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 21/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer consultarCobrancaAcaoQuantidadeDocumentos(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			Integer idCobrancaAcao) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select sum(re.quantidadeDocumentos) "
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac"
					+ " where cbac.id = :idCobrancaAcao and re.indicadorDefinitivo = :indicadorDefinitivo "
					+ this
							.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper);

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).setInteger(
					"indicadorDefinitivo",
					ResumoCobrancaAcao.INIDCADOR_DEFINITIVO).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * Pesquisa as situações das ação de cobrança
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoSituacao(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			Integer idCobrancaAcao) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoSituacaoHelper(castu.id, castu.descricao, sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " where  cbac.id = :idCobrancaAcao"
					+ this
							.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper)
					+ " group by cbac.id, cbac.descricaoCobrancaAcao, castu.id, castu.descricao"
					+ " order by castu.id";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * Pesquisa as situações de débito da situação da ação
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoDebito(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoDebitoHelper (cdst.id, cdst.descricao, sum(re.quantidadeDocumentos),"
					+ " sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " left join re.cobrancaDebitoSituacao cdst"
					+ " where cbac.id = :idCobrancaAcao and castu.id = :idCobrancaAcaoSituacao"
					+ this
							.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper)
					+ " group by cdst.id, cdst.descricao"
					+ " order by cdst.id, cdst.descricao";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).setInteger(
					"idCobrancaAcaoSituacao", idCobrancaAcaoSituacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * Pesquisa as situações de débito da situação da ação de acordo com o
	 * indicador antesApos
	 * 
	 * @author Sávio Luiz
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoDebitoComIndicador(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao,
			short indicadorAntesApos, Integer idCobrancaAcaoDebito)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoDebitoHelper (cdst.id, cdst.descricao, sum(re.quantidadeDocumentos),"
					+ " sum(re.valorDocumentos), re.indicadorAntesApos)"
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " left join re.cobrancaDebitoSituacao cdst"
					+ " where cbac.id = :idCobrancaAcao and castu.id = :idCobrancaAcaoSituacao "

					+ " AND re.indicadorAntesApos = :indicadorAntesApos and cdst = :idCobrancaAcaoDebito "
					+ this
							.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper)
					+ " group by cdst.id, cdst.descricao, re.indicadorAntesApos"
					+ " order by cdst.id, cdst.descricao, re.indicadorAntesApos";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).setInteger(
					"idCobrancaAcaoSituacao", idCobrancaAcaoSituacao)
					.setInteger("idCobrancaAcaoDebito", idCobrancaAcaoDebito)
					.setShort("indicadorAntesApos", indicadorAntesApos).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoSituacaoPerfilImovel(
			int anoMesReferencia,
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoPerfilHelper(iper.id, iper.descricao,"
					+ " sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " inner join re.imovelPerfil iper"
					+ " where cbac.id = :idCobrancaAcao ";
			if (idCobrancaAcaoSituacao != null) {
				consulta = consulta + "and castu.id = "
						+ idCobrancaAcaoSituacao + " ";
			}

			consulta = consulta
					+ this
							.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper)
					+ " group by iper.id, iper.descricao" + " order by iper.id";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoDebitoPerfilImovel(
			int anoMesReferencia,
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idCobrancaAcaoDebito,
			Short idIndicador,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoPerfilHelper(iper.id, iper.descricao, "
					+ " sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac "
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " left join re.cobrancaDebitoSituacao cdst"
					+ " inner join re.imovelPerfil iper"
					+ " where cbac.id = :idCobrancaAcao "
					+ " and cdst = :idCobrancaAcaoDebito ";
			if (idIndicador != null) {
				consulta += "and  re.indicadorAntesApos = " + idIndicador + " ";
			} else {
				consulta += "and  re.indicadorAntesApos is null ";
			}
			if (idCobrancaAcaoSituacao != null) {
				consulta += " and castu.id = " + idCobrancaAcaoSituacao + " ";
			}
			consulta += this
					.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper);
			consulta += " group by iper.id, iper.descricao"
					+ " order by iper.id";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).setInteger(
					"idCobrancaAcaoDebito", idCobrancaAcaoDebito).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoSituacaoPerfilImovelIndicador(
			int anoMesReferencia,
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idPerfil,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoPerfilIndicadorHelper(iper.id, iper.descricao,"
					+ " re.indicadorLimite, sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " inner join re.imovelPerfil iper"
					+ " where cbac.id = :idCobrancaAcao "
					+ " and iper.id = :idPerfil ";
			if (idCobrancaAcaoSituacao != null) {
				consulta = consulta + " and castu.id = "
						+ idCobrancaAcaoSituacao + " ";
			}

			consulta = consulta
					+ this
							.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper)
					+ " group by iper.id, iper.descricao, re.indicadorLimite"
					+ " order by iper.id";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).setInteger("idPerfil",
					idPerfil).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoDebitoPerfilImovelIndicador(
			int anoMesReferencia,
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idCobrancaAcaoDebito,
			Integer idPerfil,
			Short idIndicador,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoPerfilIndicadorHelper(iper.id, iper.descricao, "
					+ " re.indicadorLimite, sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac "
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " left join re.cobrancaDebitoSituacao cdst"
					+ " inner join re.imovelPerfil iper"
					+ " where cbac.id = :idCobrancaAcao "
					+ " and cdst = :idCobrancaAcaoDebito "
					+ " and iper.id = :idPerfil ";
			if (idIndicador != null) {
				consulta += "and  re.indicadorAntesApos = " + idIndicador + " ";
			} else {
				consulta += "and  re.indicadorAntesApos is null ";
			}
			if (idCobrancaAcaoSituacao != null) {
				consulta += "and castu.id =" + idCobrancaAcaoSituacao + " ";
			}
			consulta += this
					.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper);
			consulta += " group by iper.id, iper.descricao, re.indicadorLimite"
					+ " order by iper.id";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).setInteger(
					"idCobrancaAcaoDebito", idCobrancaAcaoDebito).setInteger(
					"idPerfil", idPerfil).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * Popup de Motivo de Encerramento 
	 * 
	 * @author Francisco do Nascimento
	 * @date 13/06/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoMotivoEncerramento(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			boolean ehExecucao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper(moen.id, moen.descricao,"
					+ " sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " inner join re.motivoEncerramento moen"
					+ " where cbac.id = :idCobrancaAcao ";
			if (idCobrancaAcaoSituacao != null) {
				consulta = consulta + "and castu.id = "
						+ idCobrancaAcaoSituacao + " ";
			}

			consulta = consulta
					+ this
							.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper)
					+ " group by moen.id, moen.descricao" + " order by moen.id";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}	

	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança Eventual
	 * Popup de Motivo de Encerramento 
	 * 
	 * @author Francisco do Nascimento
	 * @date 19/06/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoMotivoEncerramentoEventual(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			boolean ehExecucao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper(moen.id, moen.descricao,"
					+ " sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " inner join re.motivoEncerramento moen"
					+ " where cbac.id = :idCobrancaAcao ";
			if (idCobrancaAcaoSituacao != null) {
				consulta = consulta + "and castu.id = "
						+ idCobrancaAcaoSituacao + " ";
			}

			consulta = consulta
					+ this
							.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper)
					+ " group by moen.id, moen.descricao" + " order by moen.id";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}	
	
	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * Popup de Retorno de Fiscalizacao 
	 * 
	 * @author Francisco do Nascimento
	 * @date 18/06/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoRetornoFiscalizacao(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper(fisu.id, " 
					+ " fisu.descricaoFiscalizacaoSituacao, "
					+ " sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " inner join re.fiscalizacaoSituacao fisu"
					+ " where cbac.id = :idCobrancaAcao ";
			if (idCobrancaAcaoSituacao != null) {
				consulta = consulta + "and castu.id = "
						+ idCobrancaAcaoSituacao + " ";
			}

			consulta = consulta
					+ this
							.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper)
					+ " group by fisu.id, fisu.descricaoFiscalizacaoSituacao " + " order by fisu.id";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0617] - Consultar Resumo das Ações de Cobrança Eventual
	 * Popup de Retorno de Fiscalizacao 
	 * 
	 * @author Francisco do Nascimento
	 * @date 19/06/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoRetornoFiscalizacaoEventual(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper(fisu.id, " 
					+ " fisu.descricaoFiscalizacaoSituacao, "
					+ " sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " inner join re.fiscalizacaoSituacao fisu"
					+ " where cbac.id = :idCobrancaAcao ";
			if (idCobrancaAcaoSituacao != null) {
				consulta = consulta + "and castu.id = "
						+ idCobrancaAcaoSituacao + " ";
			}

			consulta = consulta
					+ this
							.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper)
					+ " group by fisu.id, fisu.descricaoFiscalizacaoSituacao " + " order by fisu.id";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}	
	/**
	 * Exclui os dados Resumo de pendência por ano/mês e localidade
	 * 
	 * [UC0335] Gerar Resumo da Pendência
	 * 
	 * @author Ana Maria
	 * @date 30/01/2007
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void excluirResumoPendenciaPorAnoMesLocalidade(int anoMesReferencia,
			Integer idLocalidade) throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Cria a variável que vai conter o hql
		String consulta;

		try {

			// Constroi o hql para remover os dados diários da arrecadação
			// referentes ao ano/mês de arrecadação atual
			consulta = "delete ResumoPendencia rpen where rpen.anoMesReferencia = :anoMesReferencia and rpen.localidade.id = :idLocalidade";

			// Executa o hql
			session.createQuery(consulta).setInteger("anoMesReferencia",
					anoMesReferencia).setInteger("idLocalidade", idLocalidade)
					.executeUpdate();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 25/06/2007
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaResumoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException {

		// Obtém a sessão
		Session session = HibernateUtil.getSession();

		Integer retorno = null;

		try {
			String sql = "select count(*) from ResumoCobrancaAcaoEventual resumo ";
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				sql += "where resumo.tempoRealizacaoEmitir > :dataInicial and resumo.tempoRealizacaoEmitir < :dataFinal ";

				// Faz a pesquisa
				retorno = (Integer) session.createQuery(sql).setTimestamp(
						"dataInicial",
						informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getDataInicialEmissao()).setTimestamp(
						"dataFinal",
						informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getDataFinalEmissao()).setMaxResults(1).list()
						.iterator().next();
			} else {
				// Faz a pesquisa
				retorno = (Integer) session.createQuery(sql).setMaxResults(1)
						.list().iterator().next();
			}

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
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 25/06/2007
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoHelper (cbac.id, cbac.descricaoCobrancaAcao, "
					+ " cbac.servicoTipo.id, min(re.tempoRealizacaoEmitir), max(re.tempoRealizacaoEncerrar),sum(re.quantidadeDocumentos)," 
					+ " sum(re.valorDocumentos), cbac.numeroDiasRemuneracaoTerceiro) "
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac"
					+ " where 1 = 1 "
					+ this
							.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper);
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				consulta += " and re.tempoRealizacaoEmitir > :dataInicial and re.tempoRealizacaoEmitir < :dataFinal ";
			}
			consulta += " group by cbac.id, cbac.descricaoCobrancaAcao, cbac.servicoTipo.id, cbac.numeroDiasRemuneracaoTerceiro "
					+ " order by cbac.id";
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {

				retorno = session.createQuery(consulta).setTimestamp(
						"dataInicial",
						informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getDataInicialEmissao()).setTimestamp(
						"dataFinal",
						informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getDataFinalEmissao()).list();
			} else {
				retorno = session.createQuery(consulta).list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 25/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer consultarCobrancaAcaoEventualQuantidadeDocumentos(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			Integer idCobrancaAcao) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select sum(re.quantidadeDocumentos) "
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac"
					+ " where cbac.id = :idCobrancaAcao and re.indicadorDefinitivo = :indicadorDefinitivo "
					+ this
							.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper);
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				consulta += " and re.tempoRealizacaoEmitir > :dataInicial and re.tempoRealizacaoEmitir < :dataFinal ";
				retorno = (Integer) session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"indicadorDefinitivo",
						ResumoCobrancaAcaoEventual.INIDCADOR_DEFINITIVO)
						.setTimestamp(
								"dataInicial",
								informarDadosGeracaoResumoAcaoConsultaEventualHelper
										.getDataInicialEmissao()).setTimestamp(
								"dataFinal",
								informarDadosGeracaoResumoAcaoConsultaEventualHelper
										.getDataFinalEmissao()).uniqueResult();
			} else {
				retorno = (Integer) session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"indicadorDefinitivo",
						ResumoCobrancaAcaoEventual.INIDCADOR_DEFINITIVO)
						.uniqueResult();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoSituacaoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			Integer idCobrancaAcao) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoSituacaoHelper(castu.id, castu.descricao, sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " where  cbac.id = :idCobrancaAcao"
					+ this
							.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper);
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				consulta += " and re.tempoRealizacaoEmitir > :dataInicial and re.tempoRealizacaoEmitir < :dataFinal ";
			}
			consulta += " group by cbac.id, cbac.descricaoCobrancaAcao, castu.id, castu.descricao"
					+ " order by castu.id";
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setTimestamp(
						"dataInicial",
						informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getDataInicialEmissao()).setTimestamp(
						"dataFinal",
						informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getDataFinalEmissao()).list();
			} else {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoDebitoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoDebitoHelper (cdst.id, cdst.descricao, sum(re.quantidadeDocumentos),"
					+ " sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " left join re.cobrancaDebitoSituacao cdst"
					+ " where cbac.id = :idCobrancaAcao and castu.id = :idCobrancaAcaoSituacao"
					+ this
							.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper);
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				consulta += " and re.tempoRealizacaoEmitir > :dataInicial and re.tempoRealizacaoEmitir < :dataFinal ";
			}
			consulta += " group by cdst.id, cdst.descricao"
					+ " order by cdst.id, cdst.descricao";
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"idCobrancaAcaoSituacao", idCobrancaAcaoSituacao)
						.setTimestamp(
								"dataInicial",
								informarDadosGeracaoResumoAcaoConsultaEventualHelper
										.getDataInicialEmissao()).setTimestamp(
								"dataFinal",
								informarDadosGeracaoResumoAcaoConsultaEventualHelper
										.getDataFinalEmissao()).list();
			} else {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"idCobrancaAcaoSituacao", idCobrancaAcaoSituacao)
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
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventualSituacaoPerfilImovel(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoPerfilHelper(iper.id, iper.descricao,"
					+ " sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " inner join re.imovelPerfil iper"
					+ " where cbac.id = :idCobrancaAcao ";
			if (idCobrancaAcaoSituacao != null) {
				consulta = consulta + "and castu.id = "
						+ idCobrancaAcaoSituacao + " ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				consulta += " and re.tempoRealizacaoEmitir > :dataInicial and re.tempoRealizacaoEmitir < :dataFinal ";
			}

			consulta = consulta
					+ this
							.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper)
					+ " group by iper.id, iper.descricao" + " order by iper.id";
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setTimestamp(
						"dataInicial",
						informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getDataInicialEmissao()).setTimestamp(
						"dataFinal",
						informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getDataFinalEmissao()).list();
			} else {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventualSituacaoPerfilImovelIndicador(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idPerfil,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoPerfilIndicadorHelper(iper.id, iper.descricao,"
					+ " re.indicadorAcimaLimite, sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " inner join re.imovelPerfil iper"
					+ " where cbac.id = :idCobrancaAcao "
					+ " and iper.id = :idPerfil ";
			if (idCobrancaAcaoSituacao != null) {
				consulta = consulta + " and castu.id = "
						+ idCobrancaAcaoSituacao + " ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				consulta += " and re.tempoRealizacaoEmitir > :dataInicial and re.tempoRealizacaoEmitir < :dataFinal ";
			}

			consulta = consulta
					+ this
							.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper)
					+ " group by iper.id, iper.descricao, re.indicadorAcimaLimite"
					+ " order by iper.id";
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"idPerfil", idPerfil).setTimestamp(
						"dataInicial",
						informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getDataInicialEmissao()).setTimestamp(
						"dataFinal",
						informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getDataFinalEmissao()).list();
			} else {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"idPerfil", idPerfil).list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventualDebitoPerfilImovel(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idCobrancaAcaoDebito,
			Short idIndicador,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoPerfilHelper(iper.id, iper.descricao, "
					+ " sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac "
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " left join re.cobrancaDebitoSituacao cdst"
					+ " inner join re.imovelPerfil iper"
					+ " where cbac.id = :idCobrancaAcao "
					+ " and cdst = :idCobrancaAcaoDebito ";
			if (idIndicador != null) {
				consulta += "and  re.indicadorAntesApos = " + idIndicador + " ";
			} else {
				consulta += "and  re.indicadorAntesApos is null ";
			}
			if (idCobrancaAcaoSituacao != null) {
				consulta += " and castu.id = " + idCobrancaAcaoSituacao + " ";
			}
			consulta += this
					.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper);

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				consulta += " and re.tempoRealizacaoEmitir > :dataInicial and re.tempoRealizacaoEmitir < :dataFinal ";
			}

			consulta += " group by iper.id, iper.descricao"
					+ " order by iper.id";

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {

				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"idCobrancaAcaoDebito", idCobrancaAcaoDebito)
						.setTimestamp(
								"dataInicial",
								informarDadosGeracaoResumoAcaoConsultaEventualHelper
										.getDataInicialEmissao()).setTimestamp(
								"dataFinal",
								informarDadosGeracaoResumoAcaoConsultaEventualHelper
										.getDataFinalEmissao()).list();
			} else {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"idCobrancaAcaoDebito", idCobrancaAcaoDebito).list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventualDebitoPerfilImovelIndicador(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idCobrancaAcaoDebito,
			Integer idPerfil,
			Short idIndicador,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoPerfilIndicadorHelper(iper.id, iper.descricao, "
					+ " re.indicadorAcimaLimite, sum(re.quantidadeDocumentos), sum(re.valorDocumentos))"
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac "
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " left join re.cobrancaDebitoSituacao cdst"
					+ " inner join re.imovelPerfil iper"
					+ " where cbac.id = :idCobrancaAcao "
					+ " and cdst = :idCobrancaAcaoDebito "
					+ " and iper.id = :idPerfil ";
			if (idIndicador != null) {
				consulta += "and  re.indicadorAntesApos = " + idIndicador + " ";
			} else {
				consulta += "and  re.indicadorAntesApos is null ";
			}
			if (idCobrancaAcaoSituacao != null) {
				consulta += "and castu.id =" + idCobrancaAcaoSituacao + " ";
			}
			consulta += this
					.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper);

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				consulta += " and re.tempoRealizacaoEmitir > :dataInicial and re.tempoRealizacaoEmitir < :dataFinal ";
			}
			consulta += " group by iper.id, iper.descricao, re.indicadorAcimaLimite"
					+ " order by iper.id";

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"idCobrancaAcaoDebito", idCobrancaAcaoDebito)
						.setInteger("idPerfil", idPerfil).setTimestamp(
								"dataInicial",
								informarDadosGeracaoResumoAcaoConsultaEventualHelper
										.getDataInicialEmissao()).setTimestamp(
								"dataFinal",
								informarDadosGeracaoResumoAcaoConsultaEventualHelper
										.getDataFinalEmissao()).list();
			} else {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"idCobrancaAcaoDebito", idCobrancaAcaoDebito)
						.setInteger("idPerfil", idPerfil).list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventualDebitoComIndicador(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao,
			short indicadorAntesApos, Integer idCobrancaAcaoDebito)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.CobrancaAcaoDebitoHelper (cdst.id, cdst.descricao, sum(re.quantidadeDocumentos),"
					+ " sum(re.valorDocumentos), re.indicadorAntesApos)"
					+ " from ResumoCobrancaAcaoEventual re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " left join re.cobrancaDebitoSituacao cdst"
					+ " where cbac.id = :idCobrancaAcao and castu.id = :idCobrancaAcaoSituacao "

					+ " AND re.indicadorAntesApos = :indicadorAntesApos and cdst = :idCobrancaAcaoDebito "
					+ this
							.criarCondicionaisResumosEventuaisHQL(informarDadosGeracaoResumoAcaoConsultaEventualHelper);
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				consulta += " and re.tempoRealizacaoEmitir > :dataInicial and re.tempoRealizacaoEmitir < :dataFinal ";
			}
			consulta += " group by cdst.id, cdst.descricao, re.indicadorAntesApos"
					+ " order by cdst.id, cdst.descricao, re.indicadorAntesApos";

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getDataFinalEmissao() != null) {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"idCobrancaAcaoSituacao", idCobrancaAcaoSituacao)
						.setInteger("idCobrancaAcaoDebito",
								idCobrancaAcaoDebito).setShort(
								"indicadorAntesApos", indicadorAntesApos).setTimestamp(
										"dataInicial",
										informarDadosGeracaoResumoAcaoConsultaEventualHelper
												.getDataInicialEmissao()).setTimestamp(
										"dataFinal",
										informarDadosGeracaoResumoAcaoConsultaEventualHelper
												.getDataFinalEmissao())
						.list();
			} else {
				retorno = session.createQuery(consulta).setInteger(
						"idCobrancaAcao", idCobrancaAcao).setInteger(
						"idCobrancaAcaoSituacao", idCobrancaAcaoSituacao)
						.setInteger("idCobrancaAcaoDebito",
								idCobrancaAcaoDebito).setShort(
								"indicadorAntesApos", indicadorAntesApos)
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
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public String criarCondicionaisResumosEventuaisHQL(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper) {

		String sql = " ";
		/*
		 * A partir daqui sera montanda a parte dos condicionais da query estas
		 * condicionais serão usadas se necessarias, o q determina seus usos são
		 * os parametros que veem carregados no objeto
		 * InformarDadosGeracaoRelatorioConsultaHelper que é recebido do caso de
		 * uso [UC0304] Informar Dados para Geração de Relatorio ou COnsulta
		 */
		if (informarDadosGeracaoResumoAcaoConsultaEventualHelper != null) {

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper.getDataInicialEmissao() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper.getDataFinalEmissao() != null){
				
				sql = sql + " and re.tempoRealizacaoEmitir between to_date('" +
					Util.formatarDataComTracoAAAAMMDD(informarDadosGeracaoResumoAcaoConsultaEventualHelper.getDataInicialEmissao())
					+ "','YYYY-MM-DD') and to_date('" + 
					Util.formatarDataComTracoAAAAMMDD(informarDadosGeracaoResumoAcaoConsultaEventualHelper.getDataFinalEmissao())
					+ "','YYYY-MM-DD') ";				
			}
			
			// Inicio Parametros simples
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getIdCobrancaAcaoAtividadeComando() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getIdCobrancaAcaoAtividadeComando().toString()
							.equals("")) {
				sql = sql
						+ " and re.cobrancaAcaoAtividadeComando.id = "
						+ informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getIdCobrancaAcaoAtividadeComando();
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getEloPolo() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getEloPolo().getId() != null) {
				sql = sql
						+ " and re.localidade.localidade.id = "
						+ informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getEloPolo().getId();
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getLocalidade() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getLocalidade().getId() != null) {
				sql = sql
						+ " and re.localidade.id = "
						+ informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getLocalidade().getId();
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getSetorComercial() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getSetorComercial().getId() != null) {
				sql = sql
						+ " and re.setorComercial.id = "
						+ informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getSetorComercial().getId();
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getQuadra() != null
					&& informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getQuadra().getId() != null) {
				sql = sql
						+ " and re.quadra.id = "
						+ informarDadosGeracaoResumoAcaoConsultaEventualHelper
								.getQuadra().getId();
			}

			// Inicio de parametros por colecão
			// sera lida a colecao e montado um IN() a partis dos id extraidos
			// de cada objeto da colecao.

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getColecaoCobrancaGrupo() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getColecaoCobrancaGrupo().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaEventualHelper
						.getColecaoCobrancaGrupo().iterator();
				CobrancaGrupo cobrancaGrupo = null;

				sql = sql + " and re.cobrancaGrupo.id in (";
				while (iterator.hasNext()) {
					cobrancaGrupo = (CobrancaGrupo) iterator.next();
					sql = sql + cobrancaGrupo.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getColecaoGerenciaRegional() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getColecaoGerenciaRegional().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaEventualHelper
						.getColecaoGerenciaRegional().iterator();
				GerenciaRegional gerenciaRegional = null;

				sql = sql + " and re.gerenciaRegional.id in (";
				while (iterator.hasNext()) {
					gerenciaRegional = (GerenciaRegional) iterator.next();
					sql = sql + gerenciaRegional.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}
			
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getColecaoUnidadeNegocio() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getColecaoUnidadeNegocio().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaEventualHelper
						.getColecaoUnidadeNegocio().iterator();
				UnidadeNegocio unidadeNegocio = null;

				sql = sql + " and re.unidadeNegocio.id in (";
				while (iterator.hasNext()) {
					unidadeNegocio = (UnidadeNegocio) iterator.next();
					sql = sql + unidadeNegocio.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}
			
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getColecaoImovelPerfil() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getColecaoImovelPerfil().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaEventualHelper
						.getColecaoImovelPerfil().iterator();
				ImovelPerfil imovelPerfil = null;

				sql = sql + " and re.imovelPerfil.id in (";
				while (iterator.hasNext()) {
					imovelPerfil = (ImovelPerfil) iterator.next();
					sql = sql + imovelPerfil.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getColecaoLigacaoAguaSituacao() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getColecaoLigacaoAguaSituacao().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaEventualHelper
						.getColecaoLigacaoAguaSituacao().iterator();
				LigacaoAguaSituacao ligacaoAguaSituacao = null;

				sql = sql + " and re.ligacaoAguaSituacao.id in (";
				while (iterator.hasNext()) {
					ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
					sql = sql + ligacaoAguaSituacao.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getColecaoLigacaoEsgotoSituacao() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaEventualHelper
						.getColecaoLigacaoEsgotoSituacao().iterator();
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

				sql = sql + " and re.ligacaoEsgotoSituacao.id in (";
				while (iterator.hasNext()) {
					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator
							.next();
					sql = sql + ligacaoEsgotoSituacao.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getColecaoCategoria() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getColecaoCategoria().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaEventualHelper
						.getColecaoCategoria().iterator();
				Categoria categoria = null;

				sql = sql + " and re.categoria.id in (";
				while (iterator.hasNext()) {
					categoria = (Categoria) iterator.next();
					sql = sql + categoria.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}

			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getColecaoEsferaPoder() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getColecaoEsferaPoder().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaEventualHelper
						.getColecaoEsferaPoder().iterator();
				EsferaPoder esferaPoder = null;

				sql = sql + " and re.esferaPoder.id in (";
				while (iterator.hasNext()) {
					esferaPoder = (EsferaPoder) iterator.next();
					sql = sql + esferaPoder.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}
			if (informarDadosGeracaoResumoAcaoConsultaEventualHelper
					.getColecaoEmpresa() != null
					&& !informarDadosGeracaoResumoAcaoConsultaEventualHelper
							.getColecaoEmpresa().isEmpty()) {

				Iterator iterator = informarDadosGeracaoResumoAcaoConsultaEventualHelper
						.getColecaoEmpresa().iterator();
				Empresa empresa = null;

				sql = sql + " and re.empresa.id in (";
				while (iterator.hasNext()) {
					empresa = (Empresa) iterator.next();
					sql = sql + empresa.getId() + ",";
				}
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") ";
			}
		}

		return sql;
	}
	
	/**
	 * O sistema seleciona as contas pendentes ( a partir
	 * da tabela CONTA com CNTA_AMREFERENCIACONTA < 
	 * PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS
	 * e ( DCST_IDATUAL = 0 ou (DCST_IDATUAL = (1,2) e 
	 * CNTA_AMREFERENCIACONTABIL < PARM_AMREFENRECIAFATURAMENTO
	 * ou (DCST_IDATUAL = (3,4,5) e CNTA_AMREFERENCIACONTABIL >
	 * PARM_AMREFERENCIAFATURAMENTO 
	 * 
	 * @author Bruno Barrros
	 * @date 19/07/2007
	 * 
	 * @param idQuadra id da quadra a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getContasPendentes(int idQuadra)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select " +  
				"   loc.gerenciaRegional.id, " + // 0
				"   loc.unidadeNegocio.id, " + // 1
				"   loc.localidade.id,  " + // 2
				"   loc.id,  " + // 3
				"   setCom.id," + // 4
				"   qua.rota.id,  " + // 5
				"   qua.id,  " + // 6
				"   setCom.codigo, " + // 7 
				"   qua.numeroQuadra,  " + // 8
				"   imo.imovelPerfil.id,  " + // 9
				"   imo.ligacaoAguaSituacao.id, " + // 10
				"   imo.ligacaoEsgotoSituacao.id,  " + // 11
				"   case when (  " + // 12
				"     ligAgua.ligacaoAguaPerfil.id is null ) then " + 
				"     0 " + 
				"   else " + 
				"     ligAgua.ligacaoAguaPerfil.id " + 
				"   end, " + 
				"   case when ( " + // 13
				"     ligEsgoto.ligacaoEsgotoPerfil.id is null ) then " + 
				"     0 " + 
				"   else " + 
				"     ligEsgoto.ligacaoEsgotoPerfil.id " + 
				"   end, " + 
				"   case when ( " + // 14
				"       ( imo.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado) and " + 
				"         ligAgua.hidrometroInstalacaoHistorico is not null ) or " + 
				"       ( imo.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and " + 
				"         imo.hidrometroInstalacaoHistorico is not null ) ) then " + 
				"     1 " + 
				"   else " + 
				"     2 " + 
				"   end, " + 
				"   case when ( ligAgua.numeroConsumoMinimoAgua > 0 ) then " + // 15 
				"     1 " + 
				"   else " + 
				"     2 " + 
				"   end, " + 
				"   case when ( ligEsgoto.consumoMinimo > 0 ) then " + // 16
				"     1 " + 
				"   else " + 
				"     2 " + 
				"   end, " +
				"   con.referencia, " + // 17
				"   case when ( to_char(con.dataVencimentoConta, 'YYYYMM') < " + // 18 
				"   			   ( select " + 
			    "    			       anoMesArrecadacao " +
				"			     	 from " + 
				"                  	   SistemaParametro sp ) ) then " +
				"     1 " +
				"   else " +
				"     2 " +
				"   end, " +
				"   imo.id, " + // 19
				"   con.valorAgua," + // 20
				"   con.valorEsgoto," + // 21 
				"   con.debitos," + // 22
				"   con.valorCreditos," + // 23
				"   con.valorImposto," + // 24
				"	( select " + // 25
				" 	    anoMesArrecadacao " +
			    "     from " + 
				"       SistemaParametro sp ), " +
				"   imo.consumoTarifa.id, " + // 26
				"   ( " +
				"     select " +
				"       max( conTemp.referencia ) " +  // 27
				"     from " +
				"       Conta conTemp " +
				"     where " +
				"       conTemp.imovel.id = imo.id and " +
				"       conTemp.referencia < sp.anoMesFaturamento and " +
				"       ( " +
				"         conTemp.debitoCreditoSituacaoAtual.id = 0 or " +
				"         ( " + 
				"           conTemp.debitoCreditoSituacaoAtual.id in (1,2) and " +
				"           conTemp.referenciaContabil < sp.anoMesFaturamento" +
				"         ) or " +
				"         ( " +
				"           conTemp.debitoCreditoSituacaoAtual.id in (3,4,5) and " +
				"           conTemp.referenciaContabil >= sp.anoMesFaturamento" +
				"         ) " +
				"       ) " +
				"   ), " +
				"    rota.codigo " +//28
				"  from " +  
				"   gcom.faturamento.conta.Conta con " +
				"   inner join con.imovel as imo " +
				"   inner join con.localidade loc " +
				"   inner join con.quadraConta qua " +
				"	inner join qua.rota rota " +
				"   inner join qua.setorComercial setCom " +
				"   left join imo.ligacaoAgua ligAgua " + 
				"   left join imo.ligacaoEsgoto ligEsgoto, " +
				"   SistemaParametro sp " +
				"  where " + 
				"    con.referencia < sp.anoMesFaturamento and " +
				"    ( " +
				"      con.debitoCreditoSituacaoAtual.id = 0 or " +
				"      ( " + 
				"        con.debitoCreditoSituacaoAtual.id in (1,2) and " +
				"        con.referenciaContabil < sp.anoMesFaturamento " +
				"      ) or " +
				"      ( " +
				"        con.debitoCreditoSituacaoAtual.id in (3,4,5) and " +
				"        con.referenciaContabil >= sp.anoMesFaturamento " +
				"      ) " +
				"    ) and " +
				"    qua.id = :idQuadra" +
				"   order by " +
				"   imo.id, con.referencia";
			
			retorno = session.createQuery(hql)
				.setInteger("ligacaoAguaSituacaoLigado"  , LigacaoAguaSituacao.LIGADO)
				.setInteger("ligacaoAguaSituacaoCortado" , LigacaoAguaSituacao.CORTADO)
				.setInteger("ligacaoEsgotoSituacaoLigado", LigacaoEsgotoSituacao.LIGADO)
				.setInteger("idQuadra", idQuadra )
//                .setInteger("anoMesFaturamentoMenos1", anoMesFaturamentoMenos1 )
//				.setInteger("faixaInicialConta", faixaInicialConta)
//				.setInteger("faixaFinalConta", faixaFinalConta)
				.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Seleciona as faixas mínima e máxima para a pesquisa de contas pendentes 
	 * 
	 * @author Bruno Barros
	 * @date 03/09/2007
	 * 
	 * @param idLocalidade id da localidade a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] getFaixaContasPendentes( int idLocalidade, int anoMesReferenciaMenos1 ) throws ErroRepositorioException {
		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				" select " +
				"   min( con.id ), max( con.id ) " +
				" from " +
				"   Conta con, " +
				"   SistemaParametro sp" +
				" where " +
				"   con.localidade.id = :idLocalidade and " +
				"   con.referencia < :anoMesFaturamentoMenos1 and " +
				"   (  con.debitoCreditoSituacaoAtual = 0 or " +
				"      ( con.debitoCreditoSituacaoAtual in (1,2) and " +
				"        con.referenciaContabil < sp.anoMesFaturamento ) or " +
				"      ( con.debitoCreditoSituacaoAtual in (3,4,5) and " +
				"        con.referenciaContabil >= sp.anoMesFaturamento ) " +
				"   ) ";

			retorno = (Object[]) session.createQuery(hql).
				setInteger( "idLocalidade", idLocalidade ).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * O sistema seleciona as contas pendentes (a partir
	 * da tabela CONTA com CNTA_AMREFERENCIACONTA < PARM_AMREFERENCIAFATURAMENTO
	 * da tabela SISTEMA_PARAMENTOS e DCST_IDATUAL com valor correspondente a normal
	 * ou incluida ou retificada 
	 * 
	 * @author Bruno Barrros
	 * @date 01/08/2007
	 * 
	 * @param idLocalidade id da localidade a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getContasPendentesPorRegiao(int idSetor)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select " + 
				"  reg.id, " + // 1
				"  micr.id, " + // 2
				"  muni.id, " + // 3
				"  bai.id, " + // 4
				"  imo.imovelPerfil.id, " + // 5
				"  imo.ligacaoAguaSituacao.id, " + // 6
				"  imo.ligacaoEsgotoSituacao.id, " + // 7
				"  case when ( " + // 8
				"	( ligAguaSit.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado) and " +
				"	  ligAgua.hidrometroInstalacaoHistorico is not null ) or " + 
				"	( ligEsgSit.id = :ligacaoEsgotoSituacaoLigado and " + 
				"	  imo.hidrometroInstalacaoHistorico is not null ) ) then " + 
				"	 1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  case when ( ligAgua.numeroConsumoMinimoAgua > 0 ) then " + // 9
				"    1 " +
				"  else " + 
				"    2 " +
				"  end, " +
				"  case when ( ligEsgoto.consumoMinimo > 0 ) then " + // 10
				"    1 " + 
				"  else " + 
				"    2 " +  
				"  end, " +
				"  con.referencia, " + // 11
				"  case when ( to_char(con.dataVencimentoConta, 'YYYYMM') < " + // 12 
				"    ( select " +
				"        anoMesArrecadacao " +
				"      from " +
				"        SistemaParametro sp ) ) then " +
				"     1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  con.valorAgua, " + // 13
				"  con.valorEsgoto, " + // 14
				"  con.debitos, " + // 15
				"  con.valorCreditos, " + // 16
				"  con.valorImposto, " + // 17
				"  imo.id, " + // 18
				"from " +
				"  gcom.faturamento.conta.Conta con " +
				"  inner join con.imovel imo " +
				"  inner join imo.logradouroBairro logBa " +
				"  inner join logBa.bairro bai " +
				"  inner join bai.municipio muni " +
				"  inner join muni.microrregiao micr " +
				"  inner join micr.regiao reg " +
				"  inner join imo.ligacaoAguaSituacao ligAguaSit " +
				"  inner join imo.ligacaoEsgotoSituacao ligEsgSit " +
				"  left join imo.ligacaoAgua ligAgua " +
				"  left join imo.ligacaoEsgoto ligEsgoto " +
				"where " +
				"  referencia < (select " +
				"  				  anoMesFaturamento " +
				"  				from " +
				"  				  gcom.cadastro.sistemaparametro.SistemaParametro sp) and " +
				"  dcst_idatual in ( 0,1,2 ) and " +
				"  setCom.id = :idSetor";
			
			retorno = session.createQuery(hql)
				.setInteger("ligacaoAguaSituacaoLigado"  , LigacaoAguaSituacao.LIGADO)
				.setInteger("ligacaoAguaSituacaoCortado" , LigacaoAguaSituacao.CORTADO)
				.setInteger("ligacaoEsgotoSituacaoLigado", LigacaoEsgotoSituacao.LIGADO)
				.setInteger("idSetor"					 , idSetor ).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public void inserirPendenciaContasGerencia( Integer anoMesReferencia, ResumoPendenciaContasGerenciaHelper helper )
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSessionGerencial();
		String insert;
		
		Connection con = null;
		Statement stmt = null;
	
		try {
			
			con = session.connection();
			stmt = con.createStatement();
			
			insert = 
				"insert into " + 
				"  cobranca.un_resumo_pendencia ( " +
				"  rpen_id, " +  //1
				"  rpen_amreferencia, " + //2  
				"  greg_id, " + //3
				"  uneg_id, " +//4
				"  loca_cdelo, " +//5 
				"  loca_id, " +//6
				"  stcm_id, " +//7
				"  rota_id, " +//8
				"  qdra_id, " +//9
				"  rpen_cdsetorcomercial, " +//10 
				"  rpen_nnquadra, " +//11
				"  iper_id, " +  //12
				"  last_id, " +  //13				 
				"  lest_id, " +  //14
				"  catg_id, " +  //15
				"  scat_id, " +  //16
				"  epod_id, " +  //17
				"  cltp_id, " +  //18
				"  lapf_id, " +  //19
				"  lepf_id, " +	 //20			
				"  rpen_ichidrometro, " +//21
				"  rpen_icvofixadoagua, " +//22  
				"  rpen_icvofixadoesgoto, " + //23
				"  dotp_id, " + //24  
				"  rpen_amreferenciadocumento, " + //25  
				"  rpen_icvencido, " + //26
				"  rpen_qtligacoes, " +//27
				"  rpen_qtdocumentos, " +//28
				"  rpen_vlpendente_agua, " +//29
				"  rpen_vlpendente_esgoto, " +//30
				"  rpen_vlpendente_debitos, " +//31
				"  rpen_vlpendente_creditos, " +//32
				"  rpen_vlpendente_impostos, " +//33
				"  rpen_tmultimaalteracao, " + //34
				"  cstf_id, " + // 35
				"  fxvl_id, " + // 36
				"  rpen_cdrota)" + //37
				"values ( " +
				   Util.obterNextValSequence("cobranca.seq_un_resumo_pendencia") + ", "+ //1
				   anoMesReferencia + ", " +//2
				   helper.getIdGerenciaRegional() + ", " +//3
				   helper.getIdUnidadeNegocio() + ", " +//4
				   helper.getIdElo() + ", " +//5
				   helper.getIdLocalidade() + ", " +//6
				   helper.getIdSetorComercial() + ", " +//7
				   helper.getIdRota() + ", " +//8
				   helper.getIdQuadra() + ", " +//9
				   helper.getCodigoSetorComercial() + ", " +//10
				   helper.getNumeroQuadra() + ", " +//11
				   helper.getIdPerfilImovel() + ", " +//12
				   helper.getIdSituacaoLigacaoAgua() + ", " +//13
				   helper.getIdSituacaoLigacaoEsgoto() + ", " +//14
				   helper.getIdPrincipalCategoriaImovel() + ", " +//15
				   helper.getIdPrincipalSubCategoriaImovel() + ", " +//16
				   helper.getIdEsferaPoder() + ", " +//17
				   helper.getIdTipoClienteResponsavel() + ", " +//18
				   helper.getIdPerfilLigacaoAgua() + ", " +//19
				   helper.getIdPerfilLigacaoEsgoto() + ", " +//20
				   helper.getIdHidrometro() + ", " +//21
				   helper.getIdVolFixadoAgua() + ", " +//22
				   helper.getIdVolFixadoEsgoto() + ", " +//23
				   helper.getIdTipoDocumento() + ", " +//24
				   helper.getAnoMesReferenciaDocumento() + ", " +//25 
				   helper.getIdReferenciaVencimentoConta() + ", " +//26
				   helper.getQuantidadeLigacoes() + ", " +//27
				   helper.getQuantidadeDocumentos() + ", " +//28				   
				   helper.getValorPendenteAgua() + ", " +//29
				   helper.getValorPendenteEsgoto() + ", " +//30
				   helper.getValorPendenteDebito() + ", " +//31
				   helper.getValorPendenteCredito() + ", " +//32
				   helper.getValorPendenteImposto() + ", " +//33				   				   
	   			   Util.obterSQLDataAtual() + " ," + //34
	   			   helper.getIdTipoTarifaConsumo() + ", " + //35
	   			   helper.getIdFaixaValorTotalPendente() + ","+ // 36
	   			   null + ")"; //helper.getCdRota()
			
			stmt.executeUpdate( insert );
			

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);				
			
			try {
				stmt.close();
				con.close();								
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}
	
	/**
	 * @author Bruno Barrros
	 * @date 01/08/2007
	 * 
	 * @param idQuadra id da quadra a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getGuiasPagamentoGerencia(int idQuadra)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select " +
				"  loc.gerenciaRegional.id, " + // 1
				"  loc.unidadeNegocio.id, " + // 2
				"  loc.localidade.id, " + // 3
				"  loc.id, " + // 4
				"  setCom.id, " + // 5
				"  quaImo.rota.id, " + // 6
				"  quaImo.id, " + // 7
				"  setCom.codigo, " + // 8
				"  quaImo.numeroQuadra, " + // 9
				"  imo.imovelPerfil.id, " + // 10
				"  imo.ligacaoAguaSituacao.id, " + // 11
				"  imo.ligacaoEsgotoSituacao.id, " + // 12
				"  ligAgua.ligacaoAguaPerfil.id, " + // 13
				"  ligEsgoto.ligacaoEsgotoPerfil.id, " + // 14
				"  case when ( " + // 15
				"    ( imo.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado) and " +
				"      ligAgua.hidrometroInstalacaoHistorico is not null ) or " +
				"    ( imo.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and " +
				"      imo.hidrometroInstalacaoHistorico is not null ) ) then " +
				"    1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  case when ( ligAgua.numeroConsumoMinimoAgua > 0 ) then " + // 16
				"    1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  case when ( ligEsgoto.consumoMinimo > 0 ) then " + // 17
				"    1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  guia.anoMesReferenciaContabil, " + // 18
				"  guia.financiamentoTipo.id, " +   // 19
				"  case when ( to_char(guia.dataVencimento, 'YYYYMM') < " + // 20
			    "            ( select " +
				"    	         anoMesArrecadacao " +
				"  		       from " +
                "                SistemaParametro sp ) ) then " +
				"    1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  guia.valorDebito, " +  // 21
				"  imo.id, " +  // 22
				"  imo.consumoTarifa.id, " + // 23
				"  rota.codigo " +//24
				"from " +
				"  gcom.arrecadacao.pagamento.GuiaPagamento guia " +
				"  inner join guia.localidade as loc " +
				"  left join guia.imovel as imo " + 
				"  left join imo.quadra as quaImo " +
				"  left join quaImo.rota rota " +
				"  left join quaImo.setorComercial as setCom " +
				"  left join imo.ligacaoAgua as ligAgua " +
				"  left join imo.ligacaoEsgoto as ligEsgoto " +
				"where " +
				"  ( ( guia.anoMesReferenciaContabil < (select sp.anoMesFaturamento from " +
				"  								   	  gcom.cadastro.sistemaparametro.SistemaParametro sp) and guia.debitoCreditoSituacaoAtual in ( 0,1,2 ) ) " +
				" or( guia.anoMesReferenciaContabil >= (select sp.anoMesFaturamento from " +
				"  								   	  gcom.cadastro.sistemaparametro.SistemaParametro sp)" +
				" and guia.debitoCreditoSituacaoAtual in ( 3, 4, 5, 8 ) and guia.debitoCreditoSituacaoAnterior is null ) ) " +
				" and quaImo.id = :idQuadra" +
				"  order by " +
				"  imo.id, guia.id";
			
			retorno = session.createQuery(hql)
				.setInteger("ligacaoAguaSituacaoLigado"  , LigacaoAguaSituacao.LIGADO)
				.setInteger("ligacaoAguaSituacaoCortado" , LigacaoAguaSituacao.CORTADO)
				.setInteger("ligacaoEsgotoSituacaoLigado", LigacaoEsgotoSituacao.LIGADO)
				.setInteger("idQuadra"				     , idQuadra ).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public void inserirGuiasPagamentoGerencia( Integer anoMesReferencia, ResumoPendenciaGuiasPagamentoGerenciaHelper helper )
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSessionGerencial();
		String insert;
		
		Connection con = null;
		Statement stmt = null;
	
		try {
			
			con = session.connection();
			stmt = con.createStatement();
			
			insert = 
				"insert into " + 
				"  cobranca.un_resumo_pendencia ( " +
				"  rpen_id, " +  //1
				"  rpen_amreferencia, " + //2  
				"  greg_id, " + //3
				"  uneg_id, " +//4
				"  loca_cdelo, " +//5 
				"  loca_id, " +//6
				"  stcm_id, " +//7
				"  rota_id, " +//8
				"  qdra_id, " +//9
				"  rpen_cdsetorcomercial, " +//10 
				"  rpen_nnquadra, " +//11
				"  iper_id, " +  //12
				"  last_id, " +  //13				 
				"  lest_id, " +  //14
				"  catg_id, " +  //15
				"  scat_id, " +  //16
				"  epod_id, " +  //17
				"  cltp_id, " +  //18
				"  lapf_id, " +  //19
				"  lepf_id, " +	 //20			
				"  rpen_ichidrometro, " +//21
				"  rpen_icvofixadoagua, " +//22  
				"  rpen_icvofixadoesgoto, " + //23
				"  dotp_id, " + //24  
				"  rpen_amreferenciadocumento, " + //25  
				"  rpen_icvencido, " + //26
				"  rpen_qtligacoes, " +//27
				"  rpen_qtdocumentos, " +//28
				"  rpen_vlpendente_debitos, " +//31
				"  rpen_tmultimaalteracao," + //32
				"  cstf_id," +//33
				"  fxvl_id," +
				"  rpen_cdrota) " +//34
				"values ( " +
				   Util.obterNextValSequence("cobranca.seq_un_resumo_pendencia") + ", " +
				   anoMesReferencia + ", " +//2
				   helper.getIdGerenciaRegional() + ", " +//3
				   helper.getIdUnidadeNegocio() + ", " +//4
				   helper.getIdElo() + ", " +//5
				   helper.getIdLocalidade() + ", " +//6
				   helper.getIdSetorComercial() + ", " +//7
				   helper.getIdRota() + ", " +//8
				   helper.getIdQuadra() + ", " +//9
				   helper.getCodigoSetorComercial() + ", " +//10
				   helper.getNumeroQuadra() + ", " +//11
				   helper.getIdPerfilImovel() + ", " +//12
				   helper.getIdSituacaoLigacaoAgua() + ", " +//13
				   helper.getIdSituacaoLigacaoEsgoto() + ", " +//14
				   helper.getIdPrincipalCategoriaImovel() + ", " +//15
				   helper.getIdPrincipalSubCategoriaImovel() + ", " +//16
				   helper.getIdEsferaPoder() + ", " +//17
				   helper.getIdTipoClienteResponsavel() + ", " +//18
				   helper.getIdPerfilLigacaoAgua() + ", " +//19
				   helper.getIdPerfilLigacaoEsgoto() + ", " +//20
				   helper.getIdHidrometro() + ", " +//21
				   helper.getIdVolFixadoAgua() + ", " +//22
				   helper.getIdVolFixadoEsgoto() + ", " +//23
				   helper.getIdTipoDocumento() + ", " +//24
				   helper.getAnoMesReferenciaDocumento() + ", " +//25 
				   helper.getIdReferenciaVencimentoConta() + ", " +//26
				   helper.getQuantidadeLigacoes() + ", " +//27
				   helper.getQuantidadeDocumentos() + ", " +//28				   
				   helper.getValorPendenteDebito() + ", " +//31
				   Util.obterSQLDataAtual() + " , " + //32
	   			   "1, " +//33
	   			   "  0, " +
	   			   null +") "; //34 //helper.getCdRota() +
			
			stmt.executeUpdate( insert );
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);				
			
			try {
				stmt.close();
				con.close();								
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}
	
	/**
	 * @author Bruno Barrros
	 * @date 06/08/2007
	 * 
	 * @param idLocalidade id da localidade a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDebitosACobrarGerencia(int idQuadra)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select " +
				"  loc.gerenciaRegional.id, " + // 1
				"  loc.unidadeNegocio.id, " + // 2
				"  loc.localidade.id, " + // 3
				"  loc.id, " + // 4
				"  setCom.id, " + // 5
				"  qua.rota.id, " + // 6
				"  qua.id, " + // 7
				"  setCom.codigo, " + // 8
				"  qua.numeroQuadra, " + // 9
				"  imo.imovelPerfil.id, " + // 10
				"  imo.ligacaoAguaSituacao.id, " + // 11
				"  imo.ligacaoEsgotoSituacao.id, " + // 12
				"  case when ( " + // 13
				"    ligAgua.ligacaoAguaPerfil.id is null ) then " +   
				"    0 " +
				"  else " +   
				"    ligAgua.ligacaoAguaPerfil.id " +   
				"  end, " +   
				"  case when ( " + // 14
				"    ligEsgoto.ligacaoEsgotoPerfil.id is null ) then " +   
				"    0 " +   
				"  else " +   
				"    ligEsgoto.ligacaoEsgotoPerfil.id " +   
				"  end, " +    
				"  case when ( " + // 15
				"      ( imo.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado) and " +   
				"        ligAgua.hidrometroInstalacaoHistorico is not null ) or " +   
				"      ( imo.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and " +   
				"        imo.hidrometroInstalacaoHistorico is not null ) ) then " +   
				"    1 " +   
				"  else " +   
				"    2 " +   
				"  end, " +   
				"  case when ( ligAgua.numeroConsumoMinimoAgua > 0 ) then " + // 16
				"    1 " +   
				"  else " +   
				"    2 " +   
				"  end, " +   
				"  case when ( ligEsgoto.consumoMinimo > 0 ) then " + // 17
				"    1 " +   
				"  else " +   
				"    2 " +   
				"  end, " +
				"  dc.anoMesReferenciaContabil, " +//18
				"  finTipo.id, " +//19
                "  ( dc.valorDebito - (trunc(( dc.valorDebito / dc.numeroPrestacaoDebito ),2) * dc.numeroPrestacaoCobradas)), " + //20
//				"  ( dc.valorDebito - ( dc.valorDebito / dc.numeroPrestacaoDebito ) * dc.numeroPrestacaoCobradas ), " + // 20
				"  imo.id, " + // 21
				"  imo.consumoTarifa.id, " + // 22
				"  rota.codigo "+//23
				"from " +
				"  gcom.faturamento.debito.DebitoACobrar dc " +
				"  inner join dc.imovel as imo " +
				"  inner join dc.localidade loc " +
				"  inner join dc.quadra qua " +
				"  inner join qua.rota rota " +
				"  inner join qua.setorComercial setCom " +
				"  left join imo.ligacaoAgua ligAgua " +
				"  left join imo.ligacaoEsgoto ligEsgoto " +
				"  inner join dc.financiamentoTipo finTipo " +
				"where " +
				"  ( ( dc.anoMesReferenciaContabil < (select sp.anoMesFaturamento from " +
				"  			    gcom.cadastro.sistemaparametro.SistemaParametro sp ) and dc.debitoCreditoSituacaoAtual in (0,1,2) ) " +
				" or  ( dc.anoMesReferenciaContabil >= (select sp.anoMesFaturamento from " +
				"  			    gcom.cadastro.sistemaparametro.SistemaParametro sp ) and dc.debitoCreditoSituacaoAtual in ( 3, 4, 5, 8 ) " + 
				" and dc.debitoCreditoSituacaoAnterior is null ) ) " +
				" and qua.id = :idQuadra" +
				"  order by" +
				"  imo.id, dc.id";
			
			retorno = session.createQuery(hql)
				.setInteger("ligacaoAguaSituacaoLigado"  , LigacaoAguaSituacao.LIGADO)
				.setInteger("ligacaoAguaSituacaoCortado" , LigacaoAguaSituacao.CORTADO)
				.setInteger("ligacaoEsgotoSituacaoLigado", LigacaoEsgotoSituacao.LIGADO)
				.setInteger("idQuadra"				 	 , idQuadra ).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public void inserirPendendiciaDebitosACobrarGerencia( Integer anoMesReferencia, ResumoPendenciaDebitosACobrarGerenciaHelper helper )
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSessionGerencial();
		String insert;
		
		Connection con = null;
		Statement stmt = null;
	
		try {
			
			con = session.connection();
			stmt = con.createStatement();
			
			insert = 
				"insert into " + 
				"  cobranca.un_resumo_pendencia ( " +
				"  rpen_id, " +  //1
				"  rpen_amreferencia, " + //2  
				"  greg_id, " + //3
				"  uneg_id, " +//4
				"  loca_cdelo, " +//5 
				"  loca_id, " +//6
				"  stcm_id, " +//7
				"  rota_id, " +//8
				"  qdra_id, " +//9
				"  rpen_cdsetorcomercial, " +//10 
				"  rpen_nnquadra, " +//11
				"  iper_id, " +  //12
				"  last_id, " +  //13				 
				"  lest_id, " +  //14
				"  catg_id, " +  //15
				"  scat_id, " +  //16
				"  epod_id, " +  //17
				"  cltp_id, " +  //18
				"  lapf_id, " +  //19
				"  lepf_id, " +	 //20			
				"  rpen_ichidrometro, " +//21
				"  rpen_icvofixadoagua, " +//22  
				"  rpen_icvofixadoesgoto, " + //23
				"  dotp_id, " + //24  
				"  rpen_amreferenciadocumento, " + //25  
				"  fntp_id, " + //26
				"  rpen_qtligacoes, " +//27
				"  rpen_qtdocumentos, " +//28
				"  rpen_vlpendente_debitos, " +//29
				"  rpen_tmultimaalteracao, " +//30
				"  cstf_id, " + //31
				"  fxvl_id," +
				"  rpen_cdrota," + //32
                "  rpen_icvencido ) " + // 33
				"values ( " +
				   Util.obterNextValSequence("cobranca.seq_un_resumo_pendencia") + ", " +
				   anoMesReferencia + ", " +//2
				   helper.getIdGerenciaRegional() + ", " +//3
				   helper.getIdUnidadeNegocio() + ", " +//4
				   helper.getIdElo() + ", " +//5
				   helper.getIdLocalidade() + ", " +//6
				   helper.getIdSetorComercial() + ", " +//7
				   helper.getIdRota() + ", " +//8
				   helper.getIdQuadra() + ", " +//9
				   helper.getCodigoSetorComercial() + ", " +//10
				   helper.getNumeroQuadra() + ", " +//11
				   helper.getIdPerfilImovel() + ", " +//12
				   helper.getIdSituacaoLigacaoAgua() + ", " +//13
				   helper.getIdSituacaoLigacaoEsgoto() + ", " +//14
				   helper.getIdPrincipalCategoriaImovel() + ", " +//15
				   helper.getIdPrincipalSubCategoriaImovel() + ", " +//16
				   helper.getIdEsferaPoder() + ", " +//17
				   helper.getIdTipoClienteResponsavel() + ", " +//18
				   helper.getIdPerfilLigacaoAgua() + ", " +//19
				   helper.getIdPerfilLigacaoEsgoto() + ", " +//20
				   helper.getIdHidrometro() + ", " +//21
				   helper.getIdVolFixadoAgua() + ", " +//22
				   helper.getIdVolFixadoEsgoto() + ", " +//23
				   helper.getIdTipoDocumento() + ", " +//24
				   helper.getAnoMesReferenciaDocumento() + ", " +//25 
				   helper.getIdTipoFinanciamento() + ", " +//26
				   helper.getQuantidadeLigacoes() + ", " +//27
				   helper.getQuantidadeDocumentos() + ", " +//28				   
				   helper.getValorPendenteDebito() + ", " +//29
				   Util.obterSQLDataAtual() + " ," + //30
	   			   helper.getIdTipoTarifaConsumo() + ", " +//31
	   			   "  0, " +
	   			   null +", " +
                   "  2 )";
			
			stmt.executeUpdate( insert );
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);				
			
			try {
				stmt.close();
				con.close();								
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}
	
	/**
	 * @author Bruno Barrros
	 * @date 07/08/2007
	 * 
	 * @param idLocalidade id da localidade a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getCreditoARealizarGerencia(int idSetor)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select " + 
				"  loc.gerenciaRegional.id, " + // 0
				"  loc.unidadeNegocio.id, " +// 1
				"  loc.localidade.id, " +// 2
				"  loc.id, " +// 3
				"  setCom.id, " +// 4
				"  qua.rota.id, " +// 5
				"  qua.id, " +// 6
				"  setCom.codigo, " +// 7
				"  qua.numeroQuadra, " +// 8
				"  imo.imovelPerfil.id, " +// 9
				"  imo.ligacaoAguaSituacao.id, " +// 10
				"  imo.ligacaoEsgotoSituacao.id, " +// 11
				"  case when ( " +// 12
				"    ligAgua.ligacaoAguaPerfil.id is null ) then " +     
				"    0 " +  
				"  else " +     
				"    ligAgua.ligacaoAguaPerfil.id " +     
				"  end, " +     
				"  case when ( " + // 13

				"    ligEsgoto.ligacaoEsgotoPerfil.id is null ) then " +     
				"    0 " +     
				"  else " +     
				"    ligEsgoto.ligacaoEsgotoPerfil.id " +     
				"  end, " +      
				"  case when ( " + // 14
				"      ( imo.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado) and " +     
				"        ligAgua.hidrometroInstalacaoHistorico is not null ) or " +     
				"      ( imo.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and " +     
				"        imo.hidrometroInstalacaoHistorico is not null ) ) then " +     
				"    1 " +     
				"  else " +     
				"    2 " +     
				"  end, " +     
				"  case when ( ligAgua.numeroConsumoMinimoAgua > 0 ) then " + // 15
				"    1 " +     
				"  else " +     
				"    2 " +     
				"  end, " +     
				"  case when ( ligEsgoto.consumoMinimo > 0 ) then " + //16
				"    1 " +     
				"  else " +     
				"    2 " +     
				"  end, " +
				"  cr.anoMesReferenciaContabil, " +// 17
				"  cr.valorCredito, " +// 18
				"  imo.id, " +// 19
				"  imo.consumoTarifa.id, " + // 20
				"  rota.codigo, "+ //21
				" cr.numeroPrestacaoCredito, " + //22
				" cr.numeroPrestacaoRealizada, " + //23
				" cr.valorResidualMesAnterior " +//24
				
				"from " +
				"  gcom.faturamento.credito.CreditoARealizar cr " +
				"  inner join cr.imovel imo " +  
				"  inner join cr.localidade loc " +  
				"  inner join cr.quadra qua " + 
				"  inner join qua.rota rota " +
				"  inner join qua.setorComercial setCom " +  
				"  left join imo.ligacaoAgua ligAgua " +  
				"  left join imo.ligacaoEsgoto ligEsgoto   " +
				"where " +  
				" ( ( cr.anoMesReferenciaContabil < (select sp.anoMesFaturamento from gcom.cadastro.sistemaparametro.SistemaParametro sp ) " +
				" and cr.debitoCreditoSituacaoAtual in (0,1,2) ) " +
				" or ( cr.anoMesReferenciaContabil >= (select sp.anoMesFaturamento from gcom.cadastro.sistemaparametro.SistemaParametro sp ) " +
				" and cr.debitoCreditoSituacaoAtual in ( 3, 4, 5, 8 ) and cr.debitoCreditoSituacaoAnterior is null ) ) " +
				" and setCom.id = :idSetor" +
				"  order by" +
				"  imo.id, cr.id ";
			
			retorno = session.createQuery(hql)
				.setInteger("ligacaoAguaSituacaoLigado"  , LigacaoAguaSituacao.LIGADO)
				.setInteger("ligacaoAguaSituacaoCortado" , LigacaoAguaSituacao.CORTADO)
				.setInteger("ligacaoEsgotoSituacaoLigado", LigacaoEsgotoSituacao.LIGADO)
				.setInteger("idSetor"				 , idSetor ).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public void inserirPendendiciaCreditosARealizerGerencia( Integer anoMesReferencia, ResumoPendenciaCreditoARealizarGerenciaHelper helper )
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSessionGerencial();
		String insert;
		
		Connection con = null;
		Statement stmt = null;
	
		try {
			
			con = session.connection();
			stmt = con.createStatement();
			
			insert = 
				"insert into " + 
				"  cobranca.un_resumo_pendencia ( " +
				"  rpen_id, " +  //1
				"  rpen_amreferencia, " + //2  
				"  greg_id, " + //3
				"  uneg_id, " +//4
				"  loca_cdelo, " +//5 
				"  loca_id, " +//6
				"  stcm_id, " +//7
				"  rota_id, " +//8
				"  qdra_id, " +//9
				"  rpen_cdsetorcomercial, " +//10 
				"  rpen_nnquadra, " +//11
				"  iper_id, " +  //12
				"  last_id, " +  //13				 
				"  lest_id, " +  //14
				"  catg_id, " +  //15
				"  scat_id, " +  //16
				"  epod_id, " +  //17
				"  cltp_id, " +  //18
				"  lapf_id, " +  //19
				"  lepf_id, " +	 //20			
				"  rpen_ichidrometro, " +//21
				"  rpen_icvofixadoagua, " +//22  
				"  rpen_icvofixadoesgoto, " + //23
				"  dotp_id, " + //24  
				"  rpen_amreferenciadocumento, " + //25  
				"  rpen_qtligacoes, " +//26
				"  rpen_qtdocumentos, " +//27
				"  rpen_vlpendente_creditos, " +//28
				"  rpen_tmultimaalteracao," + //29
				"  cstf_id, " +//30
				"  fxvl_id," +
				"  rpen_cdrota, " +//31
                "  rpen_icvencido )" + // 32
				"values ( " +
				   Util.obterNextValSequence("cobranca.seq_un_resumo_pendencia") + ", " +
				   anoMesReferencia + ", " +//2
				   helper.getIdGerenciaRegional() + ", " +//3
				   helper.getIdUnidadeNegocio() + ", " +//4
				   helper.getIdElo() + ", " +//5
				   helper.getIdLocalidade() + ", " +//6
				   helper.getIdSetorComercial() + ", " +//7
				   helper.getIdRota() + ", " +//8
				   helper.getIdQuadra() + ", " +//9
				   helper.getCodigoSetorComercial() + ", " +//10
				   helper.getNumeroQuadra() + ", " +//11
				   helper.getIdPerfilImovel() + ", " +//12
				   helper.getIdSituacaoLigacaoAgua() + ", " +//13
				   helper.getIdSituacaoLigacaoEsgoto() + ", " +//14
				   helper.getIdPrincipalCategoriaImovel() + ", " +//15
				   helper.getIdPrincipalSubCategoriaImovel() + ", " +//16
				   helper.getIdEsferaPoder() + ", " +//17
				   helper.getIdTipoClienteResponsavel() + ", " +//18
				   helper.getIdPerfilLigacaoAgua() + ", " +//19
				   helper.getIdPerfilLigacaoEsgoto() + ", " +//20
				   helper.getIdHidrometro() + ", " +//21
				   helper.getIdVolFixadoAgua() + ", " +//22
				   helper.getIdVolFixadoEsgoto() + ", " +//23
				   helper.getIdTipoDocumento() + ", " +//24
				   helper.getAnoMesReferenciaDocumento() + ", " +//25 
				   helper.getQuantidadeLigacoes() + ", " +//26
				   helper.getQuantidadeDocumentos() + ", " +// 27				   
				   helper.getValorPendenteCredito() + ", " +//28
				   Util.obterSQLDataAtual() + " , " + //29
	   			   helper.getIdTipoTarifaConsumo() + ", " +//30
	   			   "  0," +
	   			   null +"," +
                   "  2 )";
			
			
			stmt.executeUpdate( insert );
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);				
			
			try {
				stmt.close();
				con.close();								
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}	
	
	/**
	 * @author Marcio Roberto
	 * @date 07/08/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal getPesquisaDebitoACobrar(int idParc)
			throws ErroRepositorioException {

		BigDecimal retorno = null;

		Session session = HibernateUtil.getSession();
		try {

			String hql = " select "
			+ "   sum(debitoacobrar.valorDebito) as valordebito "
			+ " from "
			+ "   gcom.faturamento.debito.DebitoACobrar debitoacobrar, "
			+ "   gcom.faturamento.conta.Conta as conta "
			+ "   inner join debitoacobrar.parcelamento as parc "			
			+ " where "
			+ "   debitoacobrar.imovel.id = conta.imovel.id and "
			+ "   parc.id = :idParc "
			+ "   and conta.debitos > 0 ";
			
			retorno = (BigDecimal) session.createQuery(hql)
			.setInteger("idParc", idParc).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}	
	

	/**
	 * @author Marcio Roberto
	 * @date 08/08/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal getPesquisaDebitoACobrarTipos(int idConta, int tipoLancamentoItemContabil)
			throws ErroRepositorioException {

		BigDecimal retorno = null;

		Session session = HibernateUtil.getSession();
		try {

			String hql = " select "
			+ "   sum(debitoacobrar.valorDebito) as valordebito "
			+ " from "
			+ "   gcom.faturamento.debito.DebitoACobrar debitoacobrar, "
			+ "   gcom.faturamento.conta.Conta as conta "
			+ "   inner join debitoacobrar.lancamentoItemContabil as lancamentoitemcontabil "
			+ " where "
			+ "   debitoacobrar.imovel.id = conta.imovel.id and "
			+ "   conta.id = :idConta "
			+ "   and conta.debitos > 0 "
			+ "   and debitoacobrar.lancamentoItemContabil.id = :idlancamentoItemContabil ";
			
			retorno = (BigDecimal) session.createQuery(hql)
			.setInteger("idConta", idConta).setInteger("idlancamentoItemContabil", tipoLancamentoItemContabil).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}	

	/**
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_pendencia
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobrança
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoPendencia()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + "  ch.percentualColeta "
			consulta = "SELECT max(resPend.anoMesReferencia) "
					+ " FROM "
					+ " gcom.gerencial.cobranca.UnResumoPendencia resPend ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1).uniqueResult();

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
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_parcelamento
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobrança
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoParcelamento()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + "  ch.percentualColeta "
			consulta = "SELECT max(resParc.anoMesReferencia) "
					+ " FROM "
					+ " gcom.gerencial.cobranca.UnResumoParcelamento resParc ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1).uniqueResult();

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
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_indicadores_cobranca
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobrança
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoIndicadoresCobranca()
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + "  ch.percentualColeta " 
			consulta = "SELECT max(resIndCob.anoMesReferencia) "
					+ " FROM "
					+ " UnResumoIndicadoresCobranca resIndCob ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1).uniqueResult();

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
	 * Atualiza os dados na tabela un_resumo_indicadores_cobranca
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobrança
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	//OVERRIDE - Metodo sobrescrito na classe RepositorioGerencialCobrancaPostgresHBM
	public void atualizarDadosResumoIndicadoresCobranca(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas) throws ErroRepositorioException {
		
		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {

			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO cobranca.un_res_ind_cob "
					+ " select " + Util.obterNextValSequence("cobranca.seq_un_res_ind_cob")+ ", "
					+ " rpen_amreferencia, rpen_anoreferencia, rpen_mesreferencia, "
					+ " greg_id, uneg_id, loca_id, loca_cdelo, stcm_id, qdra_id, rota_id, rpen_cdsetorcomercial, rpen_nnquadra, "
					+ " iper_id, last_id, lest_id, catg_id, scat_id, epod_id, cltp_id, lapf_id, lepf_id, "
					+ " rpen_qtcontaspendentesma, rpen_vlpendente_contama, rpen_qtligacoes, rpen_qtligacoesativas, "
					+ " rpen_qtdocumentos, rpen_qtcontaspendentes, rpen_vlpendente_total, rpen_vlpendente_conta, rpen_vlpendente_servicos, "
					+ " rpen_vlpendente_parcelamento, rele_qtligacoesativasagua, rele_qtligacoesinativasagua, "
					+ " rele_qtligacoessuprimidas, rele_qtligacoestotaisagua, "
					+ " rear_qtcontasrecebidas, rear_vlarrecadado, rear_vlarrecacadomesatevencimento, "
					+ " rear_vlarrecacadomesaposvencimento, rear_vlarrecacado2mes, rear_vlarrecacado3mes, rear_vlarrecacadoacumuladoate3mes, "
					+ " rear_vlarrecadado_parcelamento, refa_qtcontasfaturamentoliquido, refa_qtcontasfaturamentoliquidoma, refa_vlfaturamentoliquido, "
					+ " refa_vlfaturamentoliquidoma, repa_qtparcelamentos, repa_qtcontaseguias, repa_vlnegociado, repa_vlfinanciado, repa_vlparcelado, "
					+ " rlig_qtcortes, rlig_qtsupressoes, rlig_qtreligacoes, rlig_qtreestabelecimentos,  sysdate "
					+ " FROM cobranca.vw_un_res_ind_cob ";

			if (anoMesReferenciaIndicador != null) {
				consulta = consulta + " WHERE rpen_amreferencia > "
						+ anoMesReferenciaIndicador + " and rpen_amreferencia <= "
						+ anoMesReferenciaTabelas;
			} else {
				consulta = consulta + " WHERE rpen_amreferencia <= "
						+ anoMesReferenciaTabelas;
			}

//			consulta += "\n limit 1";
			stmt.executeUpdate(consulta);

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
	 * Esse método insere os dados gerados pelo resumo de pendencia 
	 * em uma outra tabela, a qual na leva em consideração a quadra como quebra
	 * Essa outra tabela foi criada devido a pouca performace da tabela
	 * com as quadras no mondrian
	 * 
	 * @param idQuadra - Código da Quadra a ser inserido
	 * @param anoMesReferencia - Ano e mes de referencia ser inserido
	 * @throws ErroRepositorioException - Qualquer erro...
	 * 
	 * @author Bruno Barros 
	 */
	public void inserirResumoPendenciaSemQuadra( Integer idQuadra, Integer anoMesReferencia )
		throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String insert;
		
		Connection con = null;
		Statement stmt = null;
	
		try {
			
			con = session.connection();
			stmt = con.createStatement();
			
			insert =				
				"INSERT INTO cobranca.un_res_pend_sqd ( " +
				"rpsq_amreferencia, " +
				"greg_id, " +
				"iper_id, " +
				"last_id, " +
				"loca_cdelo, " +
				"lest_id, " +
				"catg_id, " +
				"scat_id, " +
				"epod_id, " +
				"cltp_id, " +
				"lapf_id, " +
				"lepf_id, " +
				"rpsq_cdsetorcomercial, " +
				"uneg_id, " +
				"fntp_id, " +
				"loca_id, " +
				"stcm_id, " +
				"rpsq_icvofixadoagua, " +
				"rpsq_icvofixadoesgoto, " +
				"dotp_id, " +
				"rpsq_amreferenciadocumento, " +
				"rpsq_ichidrometro, " +
				"rpsq_icvencido, " +
				"rpsq_qtligacoes, " +
				"rpsq_qtdocumentos, " +
				"rpsq_vlpendente_agua, " +
				"rpsq_vlpendente_esgoto, " +
				"rpsq_vlpendente_debitos, " +
				"rpsq_vlpendente_creditos, " +
				"rpsq_vlpendente_impostos, " +
				"cstf_id, " +
				"fxvl_id, " +
				"rpmr_id " +
				") " +
				"select " +
				"rpen_amreferencia, " +
				"greg_id, " +
				"iper_id, " +
				"last_id, " +
				"loca_cdelo, " +
				"lest_id, " +
				"catg_id, " +
				"scat_id, " +
				"epod_id, " +
				"cltp_id, " +
				"lapf_id, " +
				"lepf_id, " +
				"rpen_cdsetorcomercial, " +
				"uneg_id, " +
				"fntp_id, " +
				"loca_id, " +
				"stcm_id, " +
				"rpen_icvofixadoagua, " +
				"rpen_icvofixadoesgoto, " +
				"dotp_id, " +
				"rpen_amreferenciadocumento, " +
				"rpen_ichidrometro, " +
				"rpen_icvencido, " +
				"sum(rpen_qtligacoes), " +
				"sum(rpen_qtdocumentos), " +
				"sum(rpen_vlpendente_agua), " +
				"sum(rpen_vlpendente_esgoto), " +
				"sum(rpen_vlpendente_debitos), " +
				"sum(rpen_vlpendente_creditos), " +
				"sum(rpen_vlpendente_impostos), " +
				"cstf_id, " +
				"fxvl_id, " +
				"0 " +
				"from " +
				"cobranca.un_resumo_pendencia " +
				"where " +
				"qdra_id = " + idQuadra + " and rpen_amreferencia = " + anoMesReferencia + " " +
				"group by " +
				"rpen_amreferencia, " +
				"greg_id, " +
				"iper_id, " +
				"last_id, " +
				"loca_cdelo, " +
				"lest_id, " +
				"catg_id, " +
				"scat_id, " +
				"epod_id, " +
				"cltp_id, " +
				"lapf_id, " +
				"lepf_id, " +
				"rpen_cdsetorcomercial, " +
				"uneg_id, " +
				"fntp_id, " +
				"loca_id, " +
				"stcm_id, " +
				"rpen_icvofixadoagua, " +
				"rpen_icvofixadoesgoto, " +
				"dotp_id, " +
				"rpen_amreferenciadocumento, " +
				"rpen_ichidrometro, " +
				"rpen_icvencido, " +
				"cstf_id, " +
				"fxvl_id " ;
				//+",,,,,,,,,,,,,,,,,,,,,,,, ";
			
			stmt.executeUpdate( insert );

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);				
			
			try {
				stmt.close();
				con.close();								
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}		
	}
	
	/**
	 * O sistema seleciona as contas pendentes ( a partir
	 * da tabela CONTA com CNTA_AMREFERENCIACONTA < 
	 * PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS
	 * e ( DCST_IDATUAL = 0 ou (DCST_IDATUAL = (1,2) e 
	 * CNTA_AMREFERENCIACONTABIL < PARM_AMREFENRECIAFATURAMENTO
	 * ou (DCST_IDATUAL = (3,4,5) e CNTA_AMREFERENCIACONTABIL >
	 * PARM_AMREFERENCIAFATURAMENTO 
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 25/03/2010
	 * 
	 * @param idSetor id do setor a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getContasPendentesPorAno(int idSetor/*, int quantidadeInicio, int quantidadeMaxima*/)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select " +  
				"   loc.gerenciaRegional.id, " + // 0
				"   loc.unidadeNegocio.id, " + // 1
				"   loc.localidade.id,  " + // 2
				"   loc.id,  " + // 3
				"   setCom.id," + // 4
				"   setCom.codigo, " + // 5
				"   imo.imovelPerfil.id,  " + // 6
				"   imo.ligacaoAguaSituacao.id, " + // 7
				"   imo.ligacaoEsgotoSituacao.id,  " + // 8
				"   case when ( " + // 9
				"       ( imo.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado) and " + 
				"         ligAgua.hidrometroInstalacaoHistorico is not null ) or " + 
				"       ( imo.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and " + 
				"         imo.hidrometroInstalacaoHistorico is not null ) ) then " + 
				"     1 " + 
				"   else " + 
				"     2 " + 
				"   end, " + 
				"   con.referencia, " + // 10
				"   case when ( to_char(con.dataVencimentoConta, 'YYYYMM') < " + // 11 
				"   			   ( select " + 
			    "    			       anoMesArrecadacao " +
				"			     	 from " + 
				"                  	   SistemaParametro sp ) ) then " +
				"     1 " +
				"   else " +
				"     2 " +
				"   end, " +
				"   imo.id, " + // 12
				"   con.valorAgua," + // 13
				"   con.valorEsgoto," + // 14
				"   con.debitos," + // 15
				"   con.valorCreditos," + // 16
				"   con.valorImposto," + // 17
				"	( select " + // 18
				" 	    anoMesArrecadacao " +
			    "     from " + 
				"       SistemaParametro sp ), " +
				"   ( " +
				"     select " +
				"       max( conTemp.referencia ) " +  // 19
				"     from " +
				"       Conta conTemp " +
				"     where " +
				"       conTemp.imovel.id = imo.id and " +
				"       conTemp.referencia < sp.anoMesFaturamento and " +
				"       ( " +
				"         conTemp.debitoCreditoSituacaoAtual.id = 0 or " +
				"         ( " + 
				"           conTemp.debitoCreditoSituacaoAtual.id in (1,2) and " +
				"           conTemp.referenciaContabil < sp.anoMesFaturamento" +
				"         ) or " +
				"         ( " +
				"           conTemp.debitoCreditoSituacaoAtual.id in (3,4,5) and " +
				"           conTemp.referenciaContabil >= sp.anoMesFaturamento" +
				"         ) " +
				"       ) " +
				"   ) " +
				"  from " +  
				"   gcom.faturamento.conta.Conta con " +
				"   inner join con.imovel as imo " +
				"   inner join con.localidade loc " +
				"   inner join imo.setorComercial setCom " +
				"   left join imo.ligacaoAgua ligAgua " + 
				"   left join imo.ligacaoEsgoto ligEsgoto, " +
				"   SistemaParametro sp " +
				"  where " + 
				"    con.referencia < sp.anoMesFaturamento and " +
				"    ( " +
				"      con.debitoCreditoSituacaoAtual.id = 0 or " +
				"      ( " + 
				"        con.debitoCreditoSituacaoAtual.id in (1,2) and " +
				"        con.referenciaContabil < sp.anoMesFaturamento " +
				"      ) or " +
				"      ( " +
				"        con.debitoCreditoSituacaoAtual.id in (3,4,5) and " +
				"        con.referenciaContabil >= sp.anoMesFaturamento " +
				"      ) " +
				"    ) and " +
				"    setCom.id = :idSetor" +
				"   order by " +
				"   imo.id, con.referencia";
			
			retorno = session.createQuery(hql)
				.setInteger("ligacaoAguaSituacaoLigado"  , LigacaoAguaSituacao.LIGADO)
				.setInteger("ligacaoAguaSituacaoCortado" , LigacaoAguaSituacao.CORTADO)
				.setInteger("ligacaoEsgotoSituacaoLigado", LigacaoEsgotoSituacao.LIGADO)
				.setInteger("idSetor", idSetor )
//				.setFirstResult(quantidadeInicio).setMaxResults(quantidadeMaxima)
				.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public void inserirPendenciaContasGerencialPorAno( Integer anoMesReferencia, 
			ResumoPendenciaContasGerencialPorAnoHelper helper )
	throws ErroRepositorioException{
	Session session = HibernateUtil.getSessionGerencial();
	String insert;
		
		Integer faixaValor = helper.getIdFaixaValorTotalPendente();
	
		Connection con = null;
		Statement stmt = null;
	
		try {
			
			con = session.connection();
			stmt = con.createStatement();
			
			insert = 
				"insert into " + 
				"  cobranca.un_res_pend_ref_2010 ( " +
				"  rpen_id, " +  //1
				"  rpen_amreferencia, " + //2  
				"  greg_id, " + //3
				"  uneg_id, " +//4
				"  loca_cdelo, " +//5 
				"  loca_id, " +//6
				"  stcm_id, " +//7
				"  rpen_cdsetorcomercial, " +//8 
				"  iper_id, " +  //9
				"  last_id, " +  //10				 
				"  lest_id, " +  //11
				"  catg_id, " +  //12
				"  scat_id, " +  //13
				"  epod_id, " +  //14		
				"  rpen_ichidrometro, " +//15
				"  dotp_id, " + //16  
				"  rpen_amreferenciadocumento, " + //17  
				"  rpen_icvencido, " + //18
				"  rpen_qtligacoes, " +//19
				"  rpen_qtdocumentos, " +//20
				"  rpen_vlpendente_agua, " +//21
				"  rpen_vlpendente_esgoto, " +//22
				"  rpen_vlpendente_debitos, " +//23
				"  rpen_vlpendente_creditos, " +//24
				"  rpen_vlpendente_impostos, " +//25
				"  rpen_tmultimaalteracao, " + //26
				"  fxvl_id )" + // 27
				"values ( " +
				   Util.obterNextValSequence("cobranca.seq_un_res_pend_ref_2007") + ", " +
				   anoMesReferencia + ", " +//2
				   helper.getIdGerenciaRegional() + ", " +//3
				   helper.getIdUnidadeNegocio() + ", " +//4
				   helper.getIdElo() + ", " +//5
				   helper.getIdLocalidade() + ", " +//6
				   helper.getIdSetorComercial() + ", " +//7
				   helper.getCodigoSetorComercial() + ", " +//8
				   helper.getIdPerfilImovel() + ", " +//9
				   helper.getIdSituacaoLigacaoAgua() + ", " +//10
				   helper.getIdSituacaoLigacaoEsgoto() + ", " +//11
				   helper.getIdPrincipalCategoriaImovel() + ", " +//12
				   helper.getIdPrincipalSubCategoriaImovel() + ", " +//13
				   helper.getIdEsferaPoder() + ", " +//14
				   helper.getIdHidrometro() + ", " +//15
				   helper.getIdTipoDocumento() + ", " +//16
				   helper.getAnoMesReferenciaDocumento() + ", " +//17 
				   helper.getIdReferenciaVencimentoConta() + ", " +//18
				   helper.getQuantidadeLigacoes() + ", " +//19
				   helper.getQuantidadeDocumentos() + ", " +//20				   
				   helper.getValorPendenteAgua() + ", " +//21
				   helper.getValorPendenteEsgoto() + ", " +//22
				   helper.getValorPendenteDebito() + ", " +//23
				   helper.getValorPendenteCredito() + ", " +//24
				   helper.getValorPendenteImposto() + ", " +//25				   				   
				   Util.obterSQLDataAtual() +" ," + //26
	   			   //helper.getIdFaixaValorTotalPendente().intValue() + //","+ // 27
	   			   faixaValor + //27   
	   			")";
			
			stmt.executeUpdate( insert );
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);				
			
			try {
				stmt.close();
				con.close();								
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}
	
	/**
	 * @author Fernando Fontelles Filho
	 * @date 25/03/2010
	 * 
	 * @param idSetor id do Setor Comercial a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getGuiasPagamentoGerenciaPorAno(int idSetor, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select " +
				"  loc.gerenciaRegional.id, " + // 0
				"  loc.unidadeNegocio.id, " + // 1
				"  loc.localidade.id, " + // 2
				"  loc.id, " + // 3
				"  setCom.id, " + // 4
				"  setCom.codigo, " + // 5
				"  imo.imovelPerfil.id, " + // 6
				"  imo.ligacaoAguaSituacao.id, " + // 7
				"  imo.ligacaoEsgotoSituacao.id, " + // 8
				"  case when ( " + // 9
				"    ( imo.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado) and " +
				"      ligAgua.hidrometroInstalacaoHistorico is not null ) or " +
				"    ( imo.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and " +
				"      imo.hidrometroInstalacaoHistorico is not null ) ) then " +
				"    1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  guia.anoMesReferenciaContabil, " + // 10
				"  guia.financiamentoTipo.id, " +   // 11
				"  case when ( to_char(guia.dataVencimento, 'YYYYMM') < " + // 12
			    "            ( select " +
				"    	         anoMesArrecadacao " +
				"  		       from " +
                "                SistemaParametro sp ) ) then " +
				"    1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  guia.valorDebito, " +  // 13
				"  imo.id " +  // 14
				"from " +
				"  gcom.arrecadacao.pagamento.GuiaPagamento guia " +
				"  inner join guia.localidade as loc " +
				"  left join guia.imovel as imo " + 
				"  left join imo.setorComercial as setCom " +
				"  left join imo.ligacaoAgua as ligAgua " +
				"  left join imo.ligacaoEsgoto as ligEsgoto " +
				"where " +
				"  guia.anoMesReferenciaContabil < (select " +
				"  								     sp.anoMesFaturamento" +
				"  								   from " +
				"  								   	  gcom.cadastro.sistemaparametro.SistemaParametro sp) and " +
				"  guia.debitoCreditoSituacaoAtual in ( 0,1,2 ) and" +
				"  setCom.id = :idSetor" +
				"  order by " +
				"  imo.id, guia.id";
			
			retorno = session.createQuery(hql)
				.setInteger("ligacaoAguaSituacaoLigado"  , LigacaoAguaSituacao.LIGADO)
				.setInteger("ligacaoAguaSituacaoCortado" , LigacaoAguaSituacao.CORTADO)
				.setInteger("ligacaoEsgotoSituacaoLigado", LigacaoEsgotoSituacao.LIGADO)
				.setInteger("idSetor"				     , idSetor )
				.setFirstResult(quantidadeInicio).setMaxResults(quantidadeMaxima)
				.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 26/03/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirGuiasPagamentoGerenciaPorAno( Integer anoMesReferencia, 
			ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper helper )
	throws ErroRepositorioException{
	Session session = HibernateUtil.getSessionGerencial();
	String insert;
	
		Connection con = null;
		Statement stmt = null;
	
		try {
			
			con = session.connection();
			stmt = con.createStatement();
			
			insert = 
				"insert into " + 
				"  cobranca.un_res_pend_ref_2010 ( " +
				"  rpen_id, " +  //1
				"  rpen_amreferencia, " + //2  
				"  greg_id, " + //3
				"  uneg_id, " +//4
				"  loca_cdelo, " +//5 
				"  loca_id, " +//6
				"  stcm_id, " +//7
				"  rpen_cdsetorcomercial, " +//10 
				"  iper_id, " +  //12
				"  last_id, " +  //13				 
				"  lest_id, " +  //14
				"  catg_id, " +  //15
				"  scat_id, " +  //16
				"  epod_id, " +  //17			
				"  rpen_ichidrometro, " +//21
				"  dotp_id, " + //24  
				"  rpen_amreferenciadocumento, " + //25  
				"  rpen_icvencido, " + //26
				"  rpen_qtligacoes, " +//27
				"  rpen_qtdocumentos, " +//28
				"  rpen_vlpendente_debitos, " +//31
				"  rpen_tmultimaalteracao," + //32
				"  fxvl_id, " +
				"  fntp_id) " +
				"values ( " +
				   Util.obterNextValSequence("cobranca.seq_un_res_pend_ref_2007") + ", " +
				   anoMesReferencia + ", " +//2
				   helper.getIdGerenciaRegional() + ", " +//3
				   helper.getIdUnidadeNegocio() + ", " +//4
				   helper.getIdElo() + ", " +//5
				   helper.getIdLocalidade() + ", " +//6
				   helper.getIdSetorComercial() + ", " +//7
				   helper.getCodigoSetorComercial() + ", " +//10
				   helper.getIdPerfilImovel() + ", " +//12
				   helper.getIdSituacaoLigacaoAgua() + ", " +//13
				   helper.getIdSituacaoLigacaoEsgoto() + ", " +//14
				   helper.getIdPrincipalCategoriaImovel() + ", " +//15
				   helper.getIdPrincipalSubCategoriaImovel() + ", " +//16
				   helper.getIdEsferaPoder() + ", " +//17
				   helper.getIdHidrometro() + ", " +//21
				   helper.getIdTipoDocumento() + ", " +//24
				   helper.getAnoMesReferenciaDocumento() + ", " +//25 
				   helper.getIdReferenciaVencimentoConta() + ", " +//26
				   helper.getQuantidadeLigacoes() + ", " +//27
				   helper.getQuantidadeDocumentos() + ", " +//28				   
				   helper.getValorPendenteDebito() + ", " +//31
				   Util.obterSQLDataAtual() +" , " + //32
	   			   "  0, " +
	   			   helper.getIdTipoFinanciamento() + ") "
	   			   ; //34
			
			stmt.executeUpdate( insert );
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);				
			
			try {
				stmt.close();
				con.close();								
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}
	
	/**
	 * @author Fernando Fontelles
	 * @date 26/03/2010
	 * 
	 * @param idSetor id do Setor Comercial a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDebitosACobrarGerenciaPorAno(int idSetor, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select " +
				"  loc.gerenciaRegional.id, " + // 1
				"  loc.unidadeNegocio.id, " + // 2
				"  loc.localidade.id, " + // 3
				"  loc.id, " + // 4
				"  setCom.id, " + // 5
				"  setCom.codigo, " + // 6
				"  imo.imovelPerfil.id, " + // 7
				"  imo.ligacaoAguaSituacao.id, " + // 8
				"  imo.ligacaoEsgotoSituacao.id, " + // 9
				"  case when ( " + // 10
				"      ( imo.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado) and " +   
				"        ligAgua.hidrometroInstalacaoHistorico is not null ) or " +   
				"      ( imo.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and " +   
				"        imo.hidrometroInstalacaoHistorico is not null ) ) then " +   
				"    1 " +   
				"  else " +   
				"    2 " +   
				"  end, " +   
				"  dc.anoMesReferenciaContabil, " +//11
				"  finTipo.id, " +//12
                "  ( dc.valorDebito - (trunc(( dc.valorDebito / dc.numeroPrestacaoDebito ),2) * dc.numeroPrestacaoCobradas)), " + //13
				"  imo.id " + // 14
				"from " +
				"  gcom.faturamento.debito.DebitoACobrar dc " +
				"  inner join dc.imovel as imo " +
				"  inner join dc.localidade loc " +
				"  inner join imo.setorComercial setCom " +
				"  left join imo.ligacaoAgua ligAgua " +
				"  left join imo.ligacaoEsgoto ligEsgoto " +
				"  inner join dc.financiamentoTipo finTipo " +
				"where " +
				"  dc.anoMesReferenciaContabil < (select " +
				"  			    				    sp.anoMesFaturamento" +
				"  			  					  from " +
				"  			    					gcom.cadastro.sistemaparametro.SistemaParametro sp ) and " +
				"  dc.debitoCreditoSituacaoAtual in (0,1,2) and " +
				"  setCom.id = :idSetor" +
				"  order by" +
				"  imo.id, dc.id";
			
			retorno = session.createQuery(hql)
				.setInteger("ligacaoAguaSituacaoLigado"  , LigacaoAguaSituacao.LIGADO)
				.setInteger("ligacaoAguaSituacaoCortado" , LigacaoAguaSituacao.CORTADO)
				.setInteger("ligacaoEsgotoSituacaoLigado", LigacaoEsgotoSituacao.LIGADO)
				.setInteger("idSetor", idSetor )
				.setFirstResult(quantidadeInicio).setMaxResults(quantidadeMaxima)
				.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 26/03/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirPendendiciaDebitosACobrarGerenciaPorAno( Integer anoMesReferencia, 
			ResumoPendenciaDebitosACobrarGerencialPorAnoHelper helper )
	throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSessionGerencial();
		String insert;
	
		Connection con = null;
		Statement stmt = null;
	
		try {
			
			con = session.connection();
			stmt = con.createStatement();
			
			insert = 
				"insert into " + 
				"  cobranca.un_res_pend_ref_2010 ( " +
				"  rpen_id, " +  //1
				"  rpen_amreferencia, " + //2  
				"  greg_id, " + //3
				"  uneg_id, " +//4
				"  loca_cdelo, " +//5 
				"  loca_id, " +//6
				"  stcm_id, " +//7
				"  rpen_cdsetorcomercial, " +//8
				"  iper_id, " +  //9
				"  last_id, " +  //10				 
				"  lest_id, " +  //11
				"  catg_id, " +  //12
				"  scat_id, " +  //13
				"  epod_id, " +  //14			
				"  rpen_ichidrometro, " +//15
				"  dotp_id, " + //16
				"  rpen_amreferenciadocumento, " + //17  
				"  fntp_id, " + //18
				"  rpen_qtligacoes, " +//19
				"  rpen_qtdocumentos, " +//20
				"  rpen_vlpendente_debitos, " +//21
				"  rpen_tmultimaalteracao, " +//22
				"  fxvl_id, " + //23
	            "  rpen_icvencido ) " + // 24
				"values ( " +
				   Util.obterNextValSequence("cobranca.seq_un_res_pend_ref_2007") + ", " +
				   anoMesReferencia + ", " +//2
				   helper.getIdGerenciaRegional() + ", " +//3
				   helper.getIdUnidadeNegocio() + ", " +//4
				   helper.getIdElo() + ", " +//5
				   helper.getIdLocalidade() + ", " +//6
				   helper.getIdSetorComercial() + ", " +//7
				   helper.getCodigoSetorComercial() + ", " +//8
				   helper.getIdPerfilImovel() + ", " +//9
				   helper.getIdSituacaoLigacaoAgua() + ", " +//10
				   helper.getIdSituacaoLigacaoEsgoto() + ", " +//11
				   helper.getIdPrincipalCategoriaImovel() + ", " +//12
				   helper.getIdPrincipalSubCategoriaImovel() + ", " +//13
				   helper.getIdEsferaPoder() + ", " +//14
				   helper.getIdHidrometro() + ", " +//15
				   helper.getIdTipoDocumento() + ", " +//16
				   helper.getAnoMesReferenciaDocumento() + ", " +//17 
				   helper.getIdTipoFinanciamento() + ", " +//18
				   helper.getQuantidadeLigacoes() + ", " +//19
				   helper.getQuantidadeDocumentos() + ", " +//20				   
				   helper.getValorPendenteDebito() + ", " +//21
				   Util.obterSQLDataAtual() + "  ," + //22
	   			   "  0, " + // 23
	               "  2 )"; //24
			
			stmt.executeUpdate( insert );
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);				
			
			try {
				stmt.close();
				con.close();								
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}
	
	/**
	 * @author Fernando Fontelles Filho
	 * @date 26/03/2010
	 * 
	 * @param idSetor id do Setor Comercial a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getCreditoARealizarGerenciaPorAno(int idSetor, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select " + 
				"  loc.gerenciaRegional.id, " + // 0
				"  loc.unidadeNegocio.id, " +// 1
				"  loc.localidade.id, " +// 2
				"  loc.id, " +// 3
				"  setCom.id, " +// 4
				"  setCom.codigo, " +// 5
				"  imo.imovelPerfil.id, " +// 6
				"  imo.ligacaoAguaSituacao.id, " +// 7
				"  imo.ligacaoEsgotoSituacao.id, " +// 8     
				"  case when ( " + // 9
				"      ( imo.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado) and " +     
				"        ligAgua.hidrometroInstalacaoHistorico is not null ) or " +     
				"      ( imo.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and " +     
				"        imo.hidrometroInstalacaoHistorico is not null ) ) then " +     
				"    1 " +     
				"  else " +     
				"    2 " +     
				"  end, " +  
				"  cr.anoMesReferenciaContabil, " +// 10
				"  ( ( cr.valorCredito - (cr.valorCredito / cr.numeroPrestacaoCredito) * cr.numeroPrestacaoRealizada ) + cr.valorResidualMesAnterior ), " +// 11
				"  imo.id " +// 12
				"from " +
				"  gcom.faturamento.credito.CreditoARealizar cr " +
				"  inner join cr.imovel imo " +  
				"  inner join cr.localidade loc " + 
				"  inner join imo.setorComercial setCom " +  
				"  left join imo.ligacaoAgua ligAgua " +  
				"  left join imo.ligacaoEsgoto ligEsgoto   " +
				"where " +  
				"  cr.anoMesReferenciaContabil < (select " +
			    "			       				    sp.anoMesFaturamento" +  
				"			  					  from   " +
				"			     					gcom.cadastro.sistemaparametro.SistemaParametro sp ) and " +  
				"  cr.debitoCreditoSituacaoAtual in (0,1,2) and " +  
				"  setCom.id = :idSetor" +
				"  order by" +
				"  imo.id, cr.id ";
			
			retorno = session.createQuery(hql)
				.setInteger("ligacaoAguaSituacaoLigado"  , LigacaoAguaSituacao.LIGADO)
				.setInteger("ligacaoAguaSituacaoCortado" , LigacaoAguaSituacao.CORTADO)
				.setInteger("ligacaoEsgotoSituacaoLigado", LigacaoEsgotoSituacao.LIGADO)
				.setInteger("idSetor", idSetor )
				.setFirstResult(quantidadeInicio).setMaxResults(quantidadeMaxima)
				.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 26/03/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirPendendiciaCreditosARealizerGerenciaPorAno( Integer anoMesReferencia, 
			ResumoPendenciaCreditoARealizarGerencialPorAnoHelper helper )
	throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSessionGerencial();
		String insert;
		
		Connection con = null;
		Statement stmt = null;
	
		try {
			
			con = session.connection();
			stmt = con.createStatement();
			
			insert = 
				"insert into " + 
				"  cobranca.un_res_pend_ref_2010 ( " +
				"  rpen_id, " +  //1
				"  rpen_amreferencia, " + //2  
				"  greg_id, " + //3
				"  uneg_id, " +//4
				"  loca_cdelo, " +//5 
				"  loca_id, " +//6
				"  stcm_id, " +//7
				"  rpen_cdsetorcomercial, " +//10 
				"  iper_id, " +  //12
				"  last_id, " +  //13				 
				"  lest_id, " +  //14
				"  catg_id, " +  //15
				"  scat_id, " +  //16
				"  epod_id, " +  //17			
				"  rpen_ichidrometro, " +//21
				"  dotp_id, " + //24  
				"  rpen_amreferenciadocumento, " + //25  
				"  rpen_qtligacoes, " +//26
				"  rpen_qtdocumentos, " +//27
				"  rpen_vlpendente_creditos, " +//28
				"  rpen_tmultimaalteracao," + //29
				"  fxvl_id," +
	            "  rpen_icvencido )" + // 32
				"values ( " +
				   Util.obterNextValSequence("cobranca.seq_un_res_pend_ref_2007") + ", " +
				   anoMesReferencia + ", " +//1
				   helper.getIdGerenciaRegional() + ", " +//2
				   helper.getIdUnidadeNegocio() + ", " +//3
				   helper.getIdElo() + ", " +//4
				   helper.getIdLocalidade() + ", " +//5
				   helper.getIdSetorComercial() + ", " +//6
				   helper.getCodigoSetorComercial() + ", " +//7
				   helper.getIdPerfilImovel() + ", " +//8
				   helper.getIdSituacaoLigacaoAgua() + ", " +//9
				   helper.getIdSituacaoLigacaoEsgoto() + ", " +//10
				   helper.getIdPrincipalCategoriaImovel() + ", " +//11
				   helper.getIdPrincipalSubCategoriaImovel() + ", " +//12
				   helper.getIdEsferaPoder() + ", " +//13
				   helper.getIdHidrometro() + ", " +//14
				   helper.getIdTipoDocumento() + ", " +//15
				   helper.getAnoMesReferenciaDocumento() + ", " +//16 
				   helper.getQuantidadeLigacoes() + ", " +//17
				   helper.getQuantidadeDocumentos() + ", " +// 18				   
				   helper.getValorPendenteCredito() + ", " +//19
				   Util.obterSQLDataAtual() + " , " + //20
	   			   "  0," +//21
	               "  2 )";//22
			
			
			stmt.executeUpdate( insert );
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);				
			
			try {
				stmt.close();
				con.close();								
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 06/05/2010
	 * 
	 * @param helper
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 * 
	 */
	public Integer atualizarPendenciaContasGerencialPorAno(ResumoPendenciaContasGerencialPorAnoHelper helper,
			Integer anoMesReferencia)
	throws ErroRepositorioException{
		
		Integer retorno = 0;
		
		Integer faixaValor = helper.getIdFaixaValorTotalPendente();
		
		Session session = HibernateUtil.getSessionGerencial();
		Connection con = null;
		PreparedStatement stmt = null;
		con = session.connection();

		try {
			
			String sql = "UPDATE cobranca.un_resumo_pendencia_ref_2010"
				+ " SET " 
				+ " rpen_qtligacoes = rpen_qtligacoes + ?, " //1
				+ " rpen_qtdocumentos = rpen_qtdocumentos + 1, "
				+ " rpen_vlpendente_agua = rpen_vlpendente_agua + ?, " //2
				+ " rpen_vlpendente_esgoto = rpen_vlpendente_esgoto + ?, " //3
				+ " rpen_vlpendente_creditos = rpen_vlpendente_creditos + ?, " //4
				+ " rpen_vlpendente_debitos = rpen_vlpendente_debitos + ?, " //5
				+ " rpen_vlpendente_impostos = rpen_vlpendente_impostos + ? " //6
				+ " WHERE "
				+ " rpen_amreferencia = ? AND " //7
				+ " rpen_amreferenciadocumento = ? AND " //8 
				+ " rpen_cdsetorcomercial = ? AND " //9
				+ " rpen_ichidrometro = ? AND " //10
				+ " rpen_icvencido = ? AND " //11
				+ " catg_id = ? AND " //12
				+ " scat_id = ? AND " //13
				+ " dotp_id = ? AND " //14	
				+ " fxvl_id = ? AND " //15
				+ " iper_id = ? AND " //16
				+ " epod_id = ? AND " //17
				+ " last_id = ? AND " //18
				+ " lest_id = ? AND " //19
				+ " greg_id = ? AND " //20
				+ " uneg_id = ? AND " //21
				+ " loca_id = ? AND " //22
				+ " loca_cdelo = ? AND " //23
				+ " stcm_id = ? " //24
				;
				
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, helper.getQuantidadeLigacoes());
			stmt.setBigDecimal(2, helper.getValorPendenteAgua());
			stmt.setBigDecimal(3,helper.getValorPendenteEsgoto());
			stmt.setBigDecimal(4,helper.getValorPendenteCredito());
			stmt.setBigDecimal(5,helper.getValorPendenteDebito());
			stmt.setBigDecimal(6, helper.getValorPendenteImposto());
			stmt.setInt(7, anoMesReferencia);
			stmt.setInt(8, helper.getAnoMesReferenciaDocumento());
			stmt.setInt(9, helper.getCodigoSetorComercial());
			stmt.setInt(10, helper.getIdHidrometro());
			stmt.setInt(11, helper.getIdReferenciaVencimentoConta());
			stmt.setInt(12, helper.getIdPrincipalCategoriaImovel());
			stmt.setInt(13, helper.getIdPrincipalSubCategoriaImovel());
			stmt.setInt(14, helper.getIdTipoDocumento());
			stmt.setInt(15, faixaValor);
			stmt.setInt(16, helper.getIdPerfilImovel());
			stmt.setInt(17, helper.getIdEsferaPoder());
			stmt.setInt(18, helper.getIdSituacaoLigacaoAgua());
			stmt.setInt(19, helper.getIdSituacaoLigacaoEsgoto());
			stmt.setInt(20, helper.getIdGerenciaRegional());
			stmt.setInt(21, helper.getIdUnidadeNegocio());
			stmt.setInt(22, helper.getIdLocalidade());
			stmt.setInt(23, helper.getIdElo());
			stmt.setInt(24, helper.getIdSetorComercial());
			
			retorno = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != stmt)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
				
			session.flush();	
			HibernateUtil.closeSession(session);
		}
		return retorno;
			
	}
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 07/05/2010
	 * 
	 * @param helper
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 * 
	 */
	public Integer atualizarGuiasPagamentoGerenciaPorAno
						(Integer anoMesReferencia,ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper helper)
	throws ErroRepositorioException{
		
		Integer retorno = 0;
		
		Session session = HibernateUtil.getSessionGerencial();
		Connection con = null;
		PreparedStatement stmt = null;
		con = session.connection();

		try {
			
			String sql = "UPDATE cobranca.un_resumo_pendencia_ref_2010"
				+ " SET " 
				+ " rpen_qtdocumentos = rpen_qtdocumentos + 1, "
				+ " rpen_vlpendente_debitos = rpen_vlpendente_debitos + ? " //1
				+ " WHERE "
				+ " rpen_amreferencia = ? AND " //2
				+ " rpen_amreferenciadocumento = ? AND " //3 
				+ " rpen_cdsetorcomercial = ? AND " //4 
				+ " rpen_ichidrometro = ? AND " //5 
				+ " rpen_icvencido = ? AND " //6 
				+ " catg_id = ? AND " //7 
				+ " scat_id = ? AND " //8 
				+ " dotp_id = ? AND " //9	
				+ " fntp_id = ? AND " //10 
				+ " iper_id = ? AND " //11 
				+ " epod_id = ? AND " //12 
				+ " last_id = ? AND " //13 
				+ " lest_id = ? AND " //14 
				+ " greg_id = ? AND " //15 
				+ " uneg_id = ? AND " //16 
				+ " loca_id = ? AND " //17 
				+ " loca_cdelo = ? AND " //18 
				+ " stcm_id = ? " //19 
				;
				
			
			stmt = con.prepareStatement(sql);
			stmt.setBigDecimal(1, helper.getValorPendenteDebito());
			stmt.setInt(2, anoMesReferencia);
			stmt.setInt(3, helper.getAnoMesReferenciaDocumento());
			stmt.setInt(4, helper.getCodigoSetorComercial());
			stmt.setInt(5, helper.getIdHidrometro());
			stmt.setInt(6, helper.getIdReferenciaVencimentoConta());
			stmt.setInt(7, helper.getIdPrincipalCategoriaImovel());
			stmt.setInt(8, helper.getIdPrincipalSubCategoriaImovel());
			stmt.setInt(9, helper.getIdTipoDocumento());
			stmt.setInt(10, helper.getIdTipoFinanciamento());
			stmt.setInt(11, helper.getIdPerfilImovel());
			stmt.setInt(12, helper.getIdEsferaPoder());
			stmt.setInt(13, helper.getIdSituacaoLigacaoAgua());
			stmt.setInt(14, helper.getIdSituacaoLigacaoEsgoto());
			stmt.setInt(15, helper.getIdGerenciaRegional());
			stmt.setInt(16, helper.getIdUnidadeNegocio());
			stmt.setInt(17, helper.getIdLocalidade());
			stmt.setInt(18, helper.getIdElo());
			stmt.setInt(19, helper.getIdSetorComercial());
			

			retorno = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != stmt)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			session.flush();
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 07/05/2010
	 * 
	 * @param helper
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 * 
	 */
	public Integer atualizarPendendiciaDebitosACobrarGerenciaPorAno
						(Integer anoMesReferencia,ResumoPendenciaDebitosACobrarGerencialPorAnoHelper helper)
	throws ErroRepositorioException{
		
		int retorno = 0;
		
		Session session = HibernateUtil.getSessionGerencial();
		Connection con = null;
		PreparedStatement stmt = null;
		con = session.connection();

		try {
			
			String sql = "UPDATE cobranca.un_resumo_pendencia_ref_2010"
				+ " SET " 
				+ " rpen_qtdocumentos = rpen_qtdocumentos + 1, "
				+ " rpen_vlpendente_debitos = rpen_vlpendente_debitos + ? " //1
				+ " WHERE "
				+ " rpen_amreferencia = ? AND " //2
				+ " rpen_amreferenciadocumento = ? AND " //3
				+ " rpen_cdsetorcomercial = ? AND " //4 
				+ " rpen_ichidrometro = ? AND " //5 
				+ " catg_id = ? AND " //6 
				+ " scat_id = ? AND " //7 
				+ " dotp_id = ? AND " //8 
				+ " fntp_id = ? AND " //9 
				+ " iper_id = ? AND " //10 
				+ " epod_id = ? AND " //11 
				+ " last_id = ? AND " //12 
				+ " lest_id = ? AND " //13 
				+ " greg_id = ? AND " //14 
				+ " uneg_id = ? AND " //15 
				+ " loca_id = ? AND " //16 
				+ " loca_cdelo = ? AND " //17 
				+ " stcm_id = ? " //18
				;
				
			stmt = con.prepareStatement(sql);
			stmt.setBigDecimal(1, helper.getValorPendenteDebito());
			stmt.setInt(2, anoMesReferencia);
			stmt.setInt(3, helper.getAnoMesReferenciaDocumento());
			stmt.setInt(4, helper.getCodigoSetorComercial());
			stmt.setInt(5, helper.getIdHidrometro());
			stmt.setInt(6, helper.getIdPrincipalCategoriaImovel());
			stmt.setInt(7, helper.getIdPrincipalSubCategoriaImovel());
			stmt.setInt(8, helper.getIdTipoDocumento());
			stmt.setInt(9, helper.getIdTipoFinanciamento());
			stmt.setInt(10, helper.getIdPerfilImovel());
			stmt.setInt(11, helper.getIdEsferaPoder());
			stmt.setInt(12, helper.getIdSituacaoLigacaoAgua());
			stmt.setInt(13, helper.getIdSituacaoLigacaoEsgoto());
			stmt.setInt(14, helper.getIdGerenciaRegional());
			stmt.setInt(15, helper.getIdUnidadeNegocio());
			stmt.setInt(16, helper.getIdLocalidade());
			stmt.setInt(17, helper.getIdElo());
			stmt.setInt(18, helper.getIdSetorComercial());
			
			retorno = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != stmt)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 07/05/2010
	 * 
	 * @param helper
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 * 
	 */
	public Integer atualizarPendendiciaCreditosARealizerGerenciaPorAno
						(Integer anoMesReferencia,ResumoPendenciaCreditoARealizarGerencialPorAnoHelper helper)
	throws ErroRepositorioException{
		
		int retorno = 0;
		
		Session session = HibernateUtil.getSessionGerencial();
		Connection con = null;
		PreparedStatement stmt = null;
		con = session.connection();

		try {
			
			String sql = "UPDATE cobranca.un_resumo_pendencia_ref_2010"
				+ " SET " 
				+ " rpen_qtdocumentos = rpen_qtdocumentos + 1, "
				+ " rpen_vlpendente_creditos = rpen_vlpendente_creditos + ? " //1
				+ " WHERE "
				+ " rpen_amreferencia = ? AND " //2
				+ " rpen_amreferenciadocumento = ? AND " //3 
				+ " rpen_cdsetorcomercial = ? AND " //4 
				+ " rpen_ichidrometro = ? AND " //5 
				+ " catg_id = ? AND " //6 
				+ " scat_id = ? AND " //7 
				+ " dotp_id = ? AND " //8 
				+ " iper_id = ? AND " //9 
				+ " epod_id = ? AND " //10 
				+ " last_id = ? AND " //11 
				+ " lest_id = ? AND " //12 
				+ " greg_id = ? AND " //13 
				+ " uneg_id = ? AND " //14 
				+ " loca_id = ? AND " //15 
				+ " loca_cdelo = ? AND " //16 
				+ " stcm_id = ? " //17 
				;
				
			stmt = con.prepareStatement(sql);
			stmt.setBigDecimal(1, helper.getValorPendenteCredito());
			stmt.setInt(2, anoMesReferencia);
			stmt.setInt(3, helper.getAnoMesReferenciaDocumento());
			stmt.setInt(4, helper.getCodigoSetorComercial());
			stmt.setInt(5, helper.getIdHidrometro());
			stmt.setInt(6, helper.getIdPrincipalCategoriaImovel());
			stmt.setInt(7, helper.getIdPrincipalSubCategoriaImovel());
			stmt.setInt(8, helper.getIdTipoDocumento());
			stmt.setInt(9, helper.getIdPerfilImovel());
			stmt.setInt(10, helper.getIdEsferaPoder());
			stmt.setInt(11, helper.getIdSituacaoLigacaoAgua());
			stmt.setInt(12, helper.getIdSituacaoLigacaoEsgoto());
			stmt.setInt(13, helper.getIdGerenciaRegional());
			stmt.setInt(14, helper.getIdUnidadeNegocio());
			stmt.setInt(15, helper.getIdLocalidade());
			stmt.setInt(16, helper.getIdElo());
			stmt.setInt(17, helper.getIdSetorComercial());
			
			retorno = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != stmt)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}
	
	/**
	 * 
	 */
	public List getGuiasPagamentoPorClienteGerencia(int idLocalidade)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select " +
				"  loc.gerenciaRegional.id, " + // 1
				"  loc.unidadeNegocio.id, " + // 2
				"  loc.localidade.id, " + // 3
				"  loc.id, " + // 4
				"  setCom.id, " + // 5
				"  quaImo.rota.id, " + // 6
				"  quaImo.id, " + // 7
				"  setCom.codigo, " + // 8
				"  quaImo.numeroQuadra, " + // 9
				"  imo.imovelPerfil.id, " + // 10
				"  imo.ligacaoAguaSituacao.id, " + // 11
				"  imo.ligacaoEsgotoSituacao.id, " + // 12
				"  ligAgua.ligacaoAguaPerfil.id, " + // 13
				"  ligEsgoto.ligacaoEsgotoPerfil.id, " + // 14
				"  case when ( " + // 15
				"    ( imo.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado) and " +
				"      ligAgua.hidrometroInstalacaoHistorico is not null ) or " +
				"    ( imo.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and " +
				"      imo.hidrometroInstalacaoHistorico is not null ) ) then " +
				"    1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  case when ( ligAgua.numeroConsumoMinimoAgua > 0 ) then " + // 16
				"    1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  case when ( ligEsgoto.consumoMinimo > 0 ) then " + // 17
				"    1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  guia.anoMesReferenciaContabil, " + // 18
				"  guia.financiamentoTipo.id, " +   // 19
				"  case when ( to_char(guia.dataVencimento, 'YYYYMM') < " + // 20
			    "            ( select " +
				"    	         anoMesArrecadacao " +
				"  		       from " +
                "                SistemaParametro sp ) ) then " +
				"    1 " +
				"  else " +
				"    2 " +
				"  end, " +
				"  guia.valorDebito, " +  // 21
				"  imo.id, " +  // 22
				"  imo.consumoTarifa.id, " + // 23
				"  rota.codigo " +//24
				"from " +
				"  gcom.arrecadacao.pagamento.GuiaPagamento guia " +
				"  inner join guia.localidade as loc " +
				"  left join guia.imovel as imo " + 
				"  left join imo.quadra as quaImo " +
				"  left join quaImo.rota rota " +
				"  left join quaImo.setorComercial as setCom " +
				"  left join imo.ligacaoAgua as ligAgua " +
				"  left join imo.ligacaoEsgoto as ligEsgoto " +
				"where " +
				"  ( ( guia.anoMesReferenciaContabil < ( select sp.anoMesFaturamento from gcom.cadastro.sistemaparametro.SistemaParametro sp ) " +
				" and guia.debitoCreditoSituacaoAtual in ( 0,1,2 ) ) " +
				" or( guia.anoMesReferenciaContabil >= (select sp.anoMesFaturamento from " +
				"  								   	  gcom.cadastro.sistemaparametro.SistemaParametro sp)" +
				" and guia.debitoCreditoSituacaoAtual in ( 3, 4, 5, 8 ) and guia.debitoCreditoSituacaoAnterior is null ) ) " +
				" and setCom.id is null and " +
				" loc.id = :idLocalidade " +
				"  order by " +
				"  imo.id, guia.id";
			
			retorno = session.createQuery(hql)
				.setInteger("ligacaoAguaSituacaoLigado"  , LigacaoAguaSituacao.LIGADO)
				.setInteger("ligacaoAguaSituacaoCortado" , LigacaoAguaSituacao.CORTADO)
				.setInteger("ligacaoEsgotoSituacaoLigado", LigacaoEsgotoSituacao.LIGADO)
				.setInteger("idLocalidade", new Integer(idLocalidade) ).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * Popup de Motivo de Encerramento 
	 * 
	 * @author Ivan Sergio
	 * @date 23/12/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoTipoCorte(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			boolean ehExecucao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select new gcom.gerencial.bean.ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper("
					+ "cotp.id, "
					+ "cotp.descricao, "
					+ "sum(re.quantidadeDocumentos), "
					+ "sum(re.valorDocumentos)) "
					+ " from ResumoCobrancaAcao re"
					+ " inner join re.cobrancaAcao cbac"
					+ " inner join re.cobrancaAcaoSituacao castu"
					+ " inner join re.corteTipo cotp"
					+ " where cbac.id = :idCobrancaAcao ";
			if (idCobrancaAcaoSituacao != null) {
				consulta = consulta + "and castu.id = "
						+ idCobrancaAcaoSituacao + " ";
			}

			consulta = consulta
					+ this
							.criarCondicionaisResumosHQL(informarDadosGeracaoResumoAcaoConsultaHelper)
					+ " group by cotp.id, cotp.descricao" + " order by cotp.id";

			retorno = session.createQuery(consulta).setInteger(
					"idCobrancaAcao", idCobrancaAcao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
}


