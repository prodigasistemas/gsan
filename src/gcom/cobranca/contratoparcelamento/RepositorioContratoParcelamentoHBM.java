package gcom.cobranca.contratoparcelamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


/**
 * @author Paulo Diniz
 * @created 18 de março de 2011
 */
public class RepositorioContratoParcelamentoHBM implements IRepositorioContratoParcelamento {

	private static IRepositorioContratoParcelamento instancia;

	/**
	 * Constructor for the RepositorioClienteTipoHBM object
	 */
	public RepositorioContratoParcelamentoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioContratoParcelamento getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioContratoParcelamentoHBM();
		}

		return instancia;
	}

	public ContratoParcelamentoRD pesquisarContratoParcelamentoRDPorNumero(String numero) throws ErroRepositorioException {
		//cria a coleção de retorno
		ContratoParcelamentoRD retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			Criteria crit = session.createCriteria(ContratoParcelamentoRD.class);
			crit.add(Restrictions.eq("numero", numero));
			retorno = (ContratoParcelamentoRD) crit.uniqueResult();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna o usuario pesquisado
		return retorno;
	}
	
	public ContratoParcelamentoRD pesquisarContratoParcelamentoRDPorID(Integer id) throws ErroRepositorioException {
		//cria a coleção de retorno
		ContratoParcelamentoRD retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			Criteria crit = session.createCriteria(ContratoParcelamentoRD.class);
			crit.add(Restrictions.eq("id", id));
			retorno = (ContratoParcelamentoRD) crit.uniqueResult();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna o usuario pesquisado
		return retorno;
	}
	
	/**
	 * Verifica Resolução de Diretoria associada a um Contrato Parcelamento não Encerrado
	 * 
	 * [UC1134]  Atualizar Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 */
	public boolean verificaResolucaoDiretoriaAssociadaContratoParcelamentoNaoEncerrado(Integer idRd)
		throws ErroRepositorioException{
		
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
	
		String consulta;
		Integer quantidadeContas = null;
	
		try {
	
			consulta = "SELECT COUNT(*) AS quantidade from COBRANCA.contrato_parcel cpar " +
					" where cpar.cprd_id = 1 and cpar.pcst_id = :idRd and cpar.pcst_id = " + ParcelamentoSituacao.NORMAL;
	
			quantidadeContas = (Integer) session.createSQLQuery(consulta)
			.addScalar("quantidade", Hibernate.INTEGER).setInteger("idRd", idRd)
			.setMaxResults(1).uniqueResult();
	
			if (quantidadeContas != null && quantidadeContas > 0) {
				retorno = true;
			}
			
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
	 * Verifica Conta Vinculada a um Contrato Parcelamento por Cliente Item
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaContaVinculadaAContratoParcel(Integer idConta, Integer idContrato) throws ErroRepositorioException{
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
	
		String consulta;
		Integer quantidadeContas = null;
	
		try {
	
			consulta = "SELECT COUNT(*) AS quantidade from COBRANCA.contrato_parcel_item item " +
						"inner join COBRANCA.contrato_parcel cpar on cpar.cpar_id = item.cpar_id " + 
						" where cpar.cpar_id = :idContrato and item.cnta_id = :idConta";
	
			quantidadeContas = (Integer) session.createSQLQuery(consulta)
			.addScalar("quantidade", Hibernate.INTEGER).setInteger("idConta", idConta)
			.setInteger("idContrato", idContrato)
			.setMaxResults(1).uniqueResult();
	
			if (quantidadeContas != null && quantidadeContas > 0) {
				retorno = true;
			}
			
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
	 * Consultar Contrato de Parcelamento por Cliente Com Pagamento jah efetuado
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaContratoParcelComPagamentoFeito(Integer idContrato) throws ErroRepositorioException{
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
	
		String consulta;
		Integer quantidadeContas = null;
	
		try {
	
			consulta = "SELECT COUNT(*) AS quantidade from cobranca.contrato_parcel_prest " +
					" where cppr_vlpagamento is not null and cppr_vlpagamento != 0 and cpar_id = :idContrato";
	
			quantidadeContas = (Integer) session.createSQLQuery(consulta)
			.addScalar("quantidade", Hibernate.INTEGER)
			.setInteger("idContrato", idContrato)
			.setMaxResults(1).uniqueResult();
	
			if (quantidadeContas != null && quantidadeContas > 0) {
				retorno = true;
			}
			
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
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento
	 * [FS0003] – Validar contrato
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Object[] pesquisarDadosContratoParcelamento(String numeroContrato) throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT cpar.cpar_id AS idContrato, " //0
					 + "   cpar.cpar_nncontrato AS numContrato, " //1
					 + "   cpar.cbfm_id AS idFormaPag, " //2
					 + "   cbfm.cbfm_dscobrancaforma AS formaPag, " //3
					 + "   cpar.cpar_vlparcelamentoacobrar as valorParcACobrar, " //4
					 + "   cpar.pcst_id AS idSituacaoParc, " //5
					 + "   pcst.pcst_dsparcelamentosituacao AS situacaoParc, " //6
					 + "   cpar.cpar_vlparcelado AS valorParc, " //7
					 + "   cpar.cpar_nnprestacoes AS numPrestacoes, " //8
					 + "   sum(cppr.cppr_vlpagamento) AS valorPg " //9
					 + " FROM cobranca.contrato_parcel cpar "
					 + "   INNER JOIN cobranca.parcelamento_situacao pcst ON pcst.pcst_id = cpar.pcst_id "
					 + "   INNER JOIN cobranca.cobranca_forma cbfm ON cbfm.cbfm_id = cpar.cbfm_id "
					 + "   INNER JOIN cobranca.contrato_parcel_prest cppr ON cppr.cpar_id = cpar.cpar_id "
					 + " WHERE (upper(cpar.cpar_nncontrato) like upper('"+numeroContrato+"'))"
					 + " GROUP BY cpar.cpar_id, cpar.cpar_nncontrato, cpar.cbfm_id, cbfm.cbfm_dscobrancaforma, "
					 + "   cpar.cpar_vlparcelamentoacobrar, cpar.pcst_id, pcst.pcst_dsparcelamentosituacao, "
					 + "   cpar.cpar_vlparcelado, cpar.cpar_nnprestacoes ";
			
			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("idContrato", Hibernate.INTEGER)
				.addScalar("numContrato", Hibernate.STRING)
				.addScalar("idFormaPag", Hibernate.INTEGER)
				.addScalar("formaPag", Hibernate.STRING)
				.addScalar("valorParcACobrar", Hibernate.BIG_DECIMAL)
				.addScalar("idSituacaoParc", Hibernate.INTEGER)
				.addScalar("situacaoParc", Hibernate.STRING)
				.addScalar("valorParc", Hibernate.BIG_DECIMAL)
				.addScalar("numPrestacoes", Hibernate.INTEGER)
				.addScalar("valorPg", Hibernate.BIG_DECIMAL)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento
	 * [FS0003] – Validar contrato
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Integer pesquisarDadosContratoParcelamentoNumeroParcelasPagas(Integer idContrato) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT MAX(cppr.cppr_nnprestacao) AS numParcPg "
					+ " from cobranca.contrato_parcel_prest cppr "
					+ " where cppr.cpar_id = :idContrato "
					+ " AND cppr.cppr_vlpagamento is not null ";

			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("numParcPg", Hibernate.INTEGER)
				.setInteger("idContrato", idContrato)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento por cliente
	 * [FS0005] – Verificar existência de contratos para o cliente
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Collection<Object[]> pesquisarDadosContratoParcelamentoPorCliente(Integer idCliente) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT cpar.cpar_id AS idContrato, " //0
					+ "   cpar.cpar_nncontrato AS numContrato, " //1
					+ "   cpar.cpar_vlparcelado AS valorParc, " //2
					+ "   cpar.cpar_nnprestacoes AS numPrestacoes, " //3
					+ "   sum(cppr.cppr_vlpagamento) AS valorPg, " //4
					+ "   max(cppr.cppr_nnprestacao) AS numParcPg " //5
					+ " FROM cobranca.contrato_parcel cpar "
					+ "   INNER JOIN cobranca.contrato_parcel_cliente cpcl ON cpcl.cpar_id = cpar.cpar_id "
					+ "   INNER JOIN cobranca.contrato_parcel_prest cppr ON cppr.cpar_id = cpar.cpar_id "
					+ " WHERE cpar.cpar_vlparcelamentoacobrar IS NOT NULL "
					+ "   AND cpar.cpar_vlparcelamentoacobrar > 0 "
					+ "   AND cpar.pcst_id = :pagamentoSituacao "
					+ "   AND cpar.cbfm_id = :cobrancaForma "
					+ "   AND cpcl.clie_id = :idCliente "
					+ "   AND cpcl.cpcl_idsuperior IS NULL "
					+ " GROUP BY cpar.cpar_id, cpar.cpar_nncontrato, cpar.cpar_vlparcelado, cpar.cpar_nnprestacoes "
					+ " ORDER BY cpar.cpar_nncontrato ";
			
			retorno = session.createSQLQuery(consulta)
				.addScalar("idContrato", Hibernate.INTEGER)
				.addScalar("numContrato", Hibernate.INTEGER)
				.addScalar("valorParc", Hibernate.BIG_DECIMAL)
				.addScalar("numPrestacoes", Hibernate.INTEGER)
				.addScalar("valorPg", Hibernate.BIG_DECIMAL)
				.addScalar("numParcPg", Hibernate.INTEGER)
				.setInteger("idCliente", idCliente)
				.setInteger("pagamentoSituacao", ParcelamentoSituacao.NORMAL)
				.setInteger("cobrancaForma", CobrancaForma.COBRANCA_POR_ICMS)
				.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}
/**
	 * Consultar Se Prestacao Ja Esta Paga
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaPrestacaoPaga(Integer idPrestacao) throws ErroRepositorioException{
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		String consulta;
		Integer quantidadeContas = null;
	
		try {
	
			consulta = "SELECT COUNT(*) AS quantidade from COBRANCA.contrato_parcel_pre_item cpit "+
			" inner join COBRANCA.contrato_parcel_prest cppr on cppr.cppr_id = cpit.cppr_id where cppr.cppr_id = :idPrestacao";
	
			quantidadeContas = (Integer) session.createSQLQuery(consulta)
			.addScalar("quantidade", Hibernate.INTEGER)
			.setInteger("idPrestacao", idPrestacao)
			.setMaxResults(1).uniqueResult();
	
			if (quantidadeContas != null && quantidadeContas > 0) {
				retorno = true;
			}
			
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
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento por cliente
	 * [SB0002] – Informar Dados do Pagamento
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public Object[] pesquisarDadosContratoParcelamentoPorClienteSelecionado(Integer idContrato) throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT cppr_nnprestacao AS numeroPrestacao, " //0
				+ "    cppr_vlprestacao AS valorPrestacao, " //1
				+ "    cppr_dtvencimento AS dataVencimento, " //2
				+ "    cppr_id AS idContrParcPrest " //3
				+ "  FROM cobranca.contrato_parcel_prest "
				+ "  WHERE cppr_vlpagamento IS NULL "
				+ "    AND cpar_id = :idContrato "
				+ "    and cppr_nnprestacao IN (SELECT MIN(cppr_nnprestacao) "
				+ "             FROM cobranca.contrato_parcel_prest "
				+ "             WHERE cppr_vlpagamento IS NULL "
				+ "             AND cpar_id = :idContrato) "
				+ "   GROUP BY cppr_nnprestacao, cppr_vlprestacao, cppr_dtvencimento, cppr_id ";

			
			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("numeroPrestacao", Hibernate.INTEGER)
				.addScalar("valorPrestacao", Hibernate.BIG_DECIMAL)
				.addScalar("dataVencimento", Hibernate.DATE)
				.addScalar("idContrParcPrest", Hibernate.INTEGER)
				.setInteger("idContrato", idContrato)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamento(Integer idContrato) throws ErroRepositorioException {
		
		ContratoParcelamento contratoParcelamento = null;
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT cpar_icparcelamentocomjuros AS indicadorParcComJuros, " //0
				+ "    cpar_vljurosparcelamento AS valorJurosParc, " //1
				+ "    cpar_icdebitocomacrescimo AS indicadorDebAcresc, " //2
				+ "    cpar_vlacrescimosimpont AS valorAcrecImpont, " //3
				+ "    cpar_nncontrato AS numeroContrato " //4
				+ "  FROM cobranca.contrato_parcel "
				+ "  WHERE cpar_id = :idContrato ";

			
			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("indicadorParcComJuros", Hibernate.SHORT)
				.addScalar("valorJurosParc", Hibernate.BIG_DECIMAL)
				.addScalar("indicadorDebAcresc", Hibernate.SHORT)
				.addScalar("valorAcrecImpont", Hibernate.BIG_DECIMAL)
				.addScalar("numeroContrato", Hibernate.STRING)
				.setInteger("idContrato", idContrato)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null) {
			contratoParcelamento = new ContratoParcelamento();
			
			contratoParcelamento.setId(idContrato);

			if (retorno[0] != null) {
				contratoParcelamento.setIndicadorParcelamentoJuros((Short) retorno[0]);
			}
			if (retorno[1] != null) {
				contratoParcelamento.setValorJurosParcelamento((BigDecimal) retorno[1]);
			}
			if (retorno[2] != null) {
				contratoParcelamento.setIndicadorDebitosAcrescimos((Short) retorno[2]);
			}
			if (retorno[3] != null) {
				contratoParcelamento.setValorTotalAcrescImpontualidade((BigDecimal) retorno[3]);
			}
			if (retorno[4] != null) {
				contratoParcelamento.setNumero((String) retorno[4]);
			}
		}
		
		return contratoParcelamento;
		
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0005] – Gerar Pagamento Guias Juros de Parcelamento e Guias de Acréscimos
	 * 
	 * Seleciona a guia de juros ou de acréscimos sobre contrato de parcelamento correspondente à parcela paga.
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public Object[] pesquisarGuiaDeJurosOuComAcrescimos(Integer idContrato, Integer debitoTipo, Integer idPrestacao) throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT gpag.gpag_id AS idGuia, " //0
				+ " cpit.cpit_vlitem AS valorItem, " //1
				+ " cpit.cpit_id AS idCpit, " //2
				+ " gpag.loca_id AS idLoca, " //3
				+ " gpag.imov_id AS idImov, " //4
				+ " gpag.clie_id AS idClie " //5
				+ " FROM cobranca.contrato_parcel_item cpit "
				+ "   INNER JOIN cobranca.contrato_parcel cpar ON cpit.cpar_id = cpar.cpar_id "
				+ "   INNER JOIN cobranca.contrato_parcel_prest cppr ON cppr.cpar_id = cpar.cpar_id "
				+ "   INNER JOIN faturamento.guia_pagamento gpag ON gpag.gpag_id = cpit.gpag_id "
				+ "     AND cppr.cppr_dtvencimento = gpag.gpag_dtvencimento "
				+ " WHERE cpit.cpar_id = :idContrato "
				+ "   AND cppr.cppr_id = :idPrestacao "
				+ "   AND cpit.dotp_id = :docTipoGuia "
				+ "   AND gpag.dbtp_id = :debTipo "
				+ "   AND gpag.dcst_idatual = :debCredSitNormal "
				+ "   AND NOT EXISTS (SELECT pgmt.gpag_id FROM arrecadacao.pagamento pgmt WHERE pgmt.gpag_id = gpag.gpag_id) "
				+ "   AND NOT EXISTS (SELECT pghi.gpag_id FROM ARRECADACAO.pagamento_historico pghi WHERE pghi.gpag_id = gpag.gpag_id) "
				+ " GROUP BY gpag.gpag_id, cpit.cpit_vlitem, cpit.cpit_id, gpag.loca_id, gpag.imov_id, gpag.clie_id ";

			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("idGuia", Hibernate.INTEGER)
				.addScalar("valorItem", Hibernate.BIG_DECIMAL)
				.addScalar("idCpit", Hibernate.INTEGER)
				.addScalar("idLoca", Hibernate.INTEGER)
				.addScalar("idImov", Hibernate.INTEGER)
				.addScalar("idClie", Hibernate.INTEGER)
				.setInteger("idContrato", idContrato)
				.setInteger("idPrestacao", idPrestacao)
				.setInteger("docTipoGuia", DocumentoTipo.GUIA_PAGAMENTO)
				.setInteger("debTipo", debitoTipo)
				.setInteger("debCredSitNormal", DebitoCreditoSituacao.NORMAL)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0007] – Gerar Pagamento
	 * 
	 * Calcula o valor já pago para o item
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public BigDecimal pesquisarValorPagoItem(Integer idContrato, Integer idItem) throws ErroRepositorioException {
		
		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT sum(cppi.cppi_vlpago) AS valorPago "
					+ " FROM cobranca.contrato_parcel_pre_item cppi "
					+ "   INNER JOIN cobranca.contrato_parcel_item cpit ON cpit.cpit_id = cppi.cpit_id "
					+ "   INNER JOIN cobranca.contrato_parcel cpar ON cpit.cpar_id = cpar.cpar_id "
					+ " WHERE cpit.cpar_id = :idContrato "
					+ " AND cpit.cpit_id = :idItem ";
			
			retorno = (BigDecimal) session.createSQLQuery(consulta)
				.addScalar("valorPago", Hibernate.BIG_DECIMAL)
				.setInteger("idContrato", idContrato)
				.setInteger("idItem", idItem)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0011] – Atualizar Item da Negativação
	 * 
	 * Verifica se a negativação está excluída
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Integer pesquisarCodigoExclusaoNegativacao(Integer idItemNegativacao) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT nmrg.nmrg_cdexclusaotipo AS codigoExclusao "
				+ " FROM cobranca.negatd_mov_reg_item nmri "
				+ "   INNER JOIN cobranca.negatd_movimento_reg nmrg ON nmri.nmrg_id = nmrg.nmrg_id "
				+ " WHERE nmri.nmri_id = :idItem ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("codigoExclusao", Hibernate.INTEGER)
				.setInteger("idItem", idItemNegativacao)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0005] – Gerar Pagamento Guias Juros de Parcelamento e Guias de Acréscimos
	 * 
	 * Seleciona a guia de juros ou de acréscimos sobre contrato de parcelamento correspondente à parcela paga.
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public Object[] pesquisarContratoParcelamentoPrestacao(Integer idContrato) throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT cppr.cppr_nnprestacao AS numeroPrestacao " //0
				+ " FROM cobranca.contrato_parcel cpar "
				+ "   INNER JOIN cobranca.contrato_parcel_prest cppr ON cppr.cpar_id = cpar.cpar_id "
				+ " WHERE cpit.cpar_id = :idContrato ";

			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("numeroPrestacao", Hibernate.INTEGER)
				.setInteger("idContrato", idContrato)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}


	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa itens de débito com valor pago a menor
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Object[] pesquisarItemDebitoValorPagoAMenor(Integer idContrato) throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT distinct cpit.cpit_id AS idItem, " //0
					 + " cpit.dotp_id AS docTipo, " //1
					 + " cpit_vlitem AS valorItem " //2
					 + " FROM cobranca.contrato_parcel_item cpit "
					 + "  INNER JOIN cobranca.contrato_parcel cpar ON cpit.cpar_id = cpar.cpar_id "
					 + " WHERE cpit.cpar_id = :idContrato "
					 + " AND cpit.cpit_vlitem <> "
					 + "  (SELECT SUM(cppi.cppi_vlpago) "
					 + "    FROM cobranca.contrato_parcel_pre_item cppi "
					 + "     INNER JOIN cobranca.contrato_parcel_prest cppr ON cppr.cppr_id = cppi.cppr_id "
					 + "    WHERE cppi.cpit_id = cpit.cpit_id "
					 + "     AND cppr.cpar_id = :idContrato) ";
			
			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("idItem", Hibernate.INTEGER)
				.addScalar("docTipo", Hibernate.INTEGER)
				.addScalar("valorItem", Hibernate.BIG_DECIMAL)
				.setInteger("idContrato", idContrato)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}


	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa a conta com valor pago a menor
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Object[] pesquisarContaValorPagoAMenor(Integer idItem) throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT cnta.cnta_id AS idConta, " //0
					+ " cnta.cnta_amreferenciaconta AS anoMesConta, " //1
					+ " cnta.loca_id AS idLoca, " //2
					+ " cnta.imov_id AS idImov " //3
					+ " FROM faturamento.conta cnta "
					+ "   INNER JOIN cobranca.contrato_parcel_item cpit ON cpit.cnta_id = cnta.cnta_id "
					+ " WHERE cnta.dcst_idatual in (:debCredSitNormal, :debCredSitRetificada, :debCredSitIncluida) "
					+ "   AND cnta.cmrv_id = :contaMotivoRevisao "
					+ "   AND cpit.cpit_id = :idItem "
					+ "   AND ((SELECT COALESCE(SUM(pgmt.pgmt_vlpagamento), 0) FROM arrecadacao.pagamento pgmt WHERE pgmt.cnta_id = cnta.cnta_id) "
					+ "       + (SELECT COALESCE(SUM(pghi.pghi_vlpagamento), 0) FROM arrecadacao.pagamento_historico pghi WHERE pghi.cnta_id = cnta.cnta_id)) "
					+ "       < cpit.cpit_vlitem ";
			
			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("idConta", Hibernate.INTEGER)
				.addScalar("anoMesConta", Hibernate.INTEGER)
				.addScalar("idLoca", Hibernate.INTEGER)
				.addScalar("idImov", Hibernate.INTEGER)
				.setInteger("idItem", idItem)
				.setInteger("debCredSitNormal", DebitoCreditoSituacao.NORMAL)
				.setInteger("debCredSitRetificada", DebitoCreditoSituacao.RETIFICADA)
				.setInteger("debCredSitIncluida", DebitoCreditoSituacao.INCLUIDA)
				.setInteger("contaMotivoRevisao", ContaMotivoRevisao.CONTA_EM_CONTRATO_PARCELAMENTO)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}


	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa o valor da menor prestação
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Object[] pesquisarValoMinimoPrestacoes(Integer idContrato) throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT cppr_vlprestacao AS valorPrestacao, "
				+ " cppr_id AS idPrestacao "
				+ " from cobranca.contrato_parcel_prest "
				+ " where cpar_id = :idContrato "
				+ " and cppr_nnprestacao in (SELECT MIN(cppr_nnprestacao) "
				+ "          FROM cobranca.contrato_parcel_prest "
				+ "          WHERE cppr_vlpagamento IS NULL "
				+ "            AND cpar_id = :idContrato) ";
			
			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("valorPrestacao", Hibernate.BIG_DECIMAL)
				.addScalar("idPrestacao", Hibernate.INTEGER)
				.setInteger("idContrato", idContrato)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa os itens de débitos do tipo conta para geração de pagamento
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Collection<Object[]> pesquisarItensDebitoConta(Integer idContrato) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT distinct cpit.cpit_id AS idItem, " //0
					+ " cpit.cpit_vlitem AS valorItem, " //1
					+ " cpit.cnta_id AS idConta, " //2
					+ " cnta.cnta_amreferenciaconta AS anoMesConta, " //3
					+ " cnta.loca_id AS idLoca, " //4
					+ " cnta.imov_id AS idImov " //5
					+ " FROM cobranca.contrato_parcel_item cpit "
					+ "   INNER JOIN faturamento.conta cnta ON cpit.cnta_id = cnta.cnta_id "
					+ " WHERE cpit.cpar_id = :idContrato "
					+ "   AND cpit.dotp_id = :documentoTipoConta "
					+ "   AND cnta.dcst_idatual in (:debCredSitNormal,:debCredSitRetificada,:debCredSitIncluida) "
					+ "   AND cnta.cmrv_id = :contaMotivoRevisao "
					+ "   AND NOT EXISTS (SELECT pgmt.cnta_id FROM arrecadacao.pagamento pgmt WHERE pgmt.cnta_id = cnta.cnta_id) "
					+ "   AND NOT EXISTS (SELECT pghi.cnta_id FROM arrecadacao.pagamento_historico pghi WHERE pghi.cnta_id = cnta.cnta_id) "
					+ " ORDER BY cnta.cnta_amreferenciaconta, cnta.imov_id ";
			
			retorno = session.createSQLQuery(consulta)
				.addScalar("idItem", Hibernate.INTEGER)
				.addScalar("valorItem", Hibernate.BIG_DECIMAL)
				.addScalar("idConta", Hibernate.INTEGER)
				.addScalar("anoMesConta", Hibernate.INTEGER)
				.addScalar("idLoca", Hibernate.INTEGER)
				.addScalar("idImov", Hibernate.INTEGER)
				.setInteger("idContrato", idContrato)
				.setInteger("debCredSitNormal", DebitoCreditoSituacao.NORMAL)
				.setInteger("debCredSitRetificada", DebitoCreditoSituacao.RETIFICADA)
				.setInteger("debCredSitIncluida", DebitoCreditoSituacao.INCLUIDA)
				.setInteger("contaMotivoRevisao", ContaMotivoRevisao.CONTA_EM_CONTRATO_PARCELAMENTO)
				.setInteger("documentoTipoConta", DocumentoTipo.CONTA)
				.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0003] – Efetuar Pagamento da Parcela do Contrato de Parcelamento Por Cliente
	 * 
	 * 1.8.1. Seleciona as guias de pagamento do contrato, atuais e sem pagamento 
	 * 
	 * @author Mariana Victor
	 * @data 08/06/2011
	 */
	public Collection<Object[]> pesquisarGuiasPagamentoContratoAtuaisSemPagamento(Integer idContrato) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT gpag.gpag_id AS idGuia, " //0
				+ "   gpag.clie_id AS idCliente, " //1
				+ "   gpag.imov_id AS idImovel, " //2
				+ "   gpag.gpag_amreferenciacontabil AS anoMesContabil, " //3
				+ "   gpag.fntp_id AS idFinanciamentoTipo, " //4
				+ "   gpag.gpag_dtemissao AS dataEmissao, " //5
				+ "   gpag.gpag_dtvencimento AS dataVencimento, " //6
				+ "   gpag.gpag_vldebito AS valorDebito, " //7
				+ "   gpag.gpag_iccobrancamulta AS indicadoCobrancaMulta, " //8
				+ "   gpag.gpag_icemitirobservacao AS indicadorEmitirObservacao, " //9
				+ "   gpag.loca_id AS localidade, " //10
				+ "   gpag.dbtp_id AS debitoTipo, " //11
				+ "   gpag.lict_id AS lancamentoItemContabil " //12
				+ " FROM cobranca.contrato_parcel_item cpit "
				+ "   INNER JOIN faturamento.guia_pagamento gpag ON gpag.gpag_id = cpit.gpag_id "
				+ " WHERE cpit.cpar_id = :idContrato "
				+ "   AND cpit.dotp_id = :documentoTipoGuia "
				+ "   AND cpit.cpit_id not in "
				+ "     (SELECT cppi.cpit_id "
				+ "       FROM cobranca.contrato_parcel_pre_item cppi "
				+ "         INNER JOIN cobranca.contrato_parcel_prest cppr ON cppr.cppr_id = cppi.cppr_id "
				+ "       WHERE cppr.cpar_id = :idContrato) ";

			retorno = session.createSQLQuery(consulta)
				.addScalar("idGuia", Hibernate.INTEGER)
				.addScalar("idCliente", Hibernate.INTEGER)
				.addScalar("idImovel", Hibernate.INTEGER)
				.addScalar("anoMesContabil", Hibernate.INTEGER)
				.addScalar("idFinanciamentoTipo", Hibernate.INTEGER)
				.addScalar("dataEmissao", Hibernate.DATE)
				.addScalar("dataVencimento", Hibernate.DATE)
				.addScalar("valorDebito", Hibernate.BIG_DECIMAL)
				.addScalar("indicadoCobrancaMulta", Hibernate.SHORT)
				.addScalar("indicadorEmitirObservacao", Hibernate.SHORT)
				.addScalar("localidade", Hibernate.INTEGER)
				.addScalar("debitoTipo", Hibernate.INTEGER)
				.addScalar("lancamentoItemContabil", Hibernate.INTEGER)
				.setInteger("idContrato", idContrato)
				.setInteger("documentoTipoGuia", DocumentoTipo.GUIA_PAGAMENTO)
				.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}
	

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Desvincula a guia de pagamento do contrato de parcelamento, 
	 * atualizando dados do contrato na tabela cobranca.CONTRATO_PARCEL_ITEM  
	 * 
	 * @author Mariana Victor
	 * @data 08/06/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarContratoParcelamentoItemDesvincularGuiaContrato(String[] idsGuias) throws ErroRepositorioException {
		
		String consulta;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "update gcom.cobranca.contratoparcelamento.ContratoParcelamentoItem "
				+ " set cpit_icitemcancelado = :indicadorItemCancelado, cpit_tmultimaalteracao = :ultimaAlteracao "
				+ " where gpag_id in (:idsGuias) ";
		
			session.createQuery(consulta).setInteger("indicadorItemCancelado",
					ConstantesSistema.SIM).setTimestamp("ultimaAlteracao", new Date())
					.setParameterList("idsGuias", idsGuias)
					.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}

	/**
	 * [UC1141] Emitir Contrato de Parcelamento por Cliente
	 * 
	 * [SB0003] – Emitir Dados do Parcelamento
	 * 
	 * Pesquisa se a parcela já foi paga 
	 * 
	 * @author Mariana Victor
	 * @data 13/06/2011
	 */
	public Boolean pesquisarParcelaPaga(Integer idPrestacao) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT count(*) AS quantidadeItem "
				+ " FROM cobranca.contrato_parcel_pre_item "
				+ " WHERE cppr_id = :idPrestacao ";

			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidadeItem", Hibernate.INTEGER)
				.setInteger("idPrestacao", idPrestacao)
				.setMaxResults(1).uniqueResult();
			
			if (retorno != null 
					&& retorno.compareTo(new Integer(0)) > 0) {
				return true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return false;
		
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta Os Clientes Vinculados a um ContratoParcelamento
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerClientesVinculadosAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException {
		
		Boolean retorno = true;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " DELETE FROM  gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente "
				+ " WHERE CPAR_ID = :idContratoParcelamento ";

			int removeu = (Integer) session.createQuery(consulta)
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.executeUpdate();
			
			if (removeu == 0) {
				retorno = false;
			}else{
				retorno = true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta Os Itens de Débitos Atuais do Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz, Mariana Victor
	 * @data 13/06/2011, 27/07/2011
	 */
	public Boolean removerItensDebitosVinculadosAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException {
		
		Boolean retorno = true;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = "DELETE FROM gcom.cobranca.contratoparcelamento.ContratoParcelamentoItem "
				+ " WHERE cpar_id = :idContratoParcelamento and DOTP_ID in (" 
				+ DocumentoTipo.CONTA + ", " + DocumentoTipo.DEBITO_A_COBRAR + ")";

			int removeu = (Integer) session.createQuery(consulta)
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.executeUpdate();
			
			if (removeu == 0) {
				retorno = false;
			}else{
				retorno = true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta as PrestacaoContratoParcelamento vinculadas ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerPrestacoesVinculadasAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException {
		
		Boolean retorno = true;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = "DELETE FROM gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento "
				+ " WHERE cpar_id = :idContratoParcelamento";

			int removeu = (Integer) session.createQuery(consulta)
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.executeUpdate();
			
			if (removeu == 0) {
				retorno = false;
			}else{
				retorno = true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta as Guias de Pagamento de 'Acrescimos por Impontualidade' vinculadas ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerGuiasDePagtoDeAcrescImpontualidadeVinculadasAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException {
		
		Boolean retorno = true;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " select cpit.id from " + ContratoParcelamentoItem.class.getName() +" cpit, "+GuiaPagamento.class.getName() +" gpag "+
			" where cpit.contrato.id = :idContratoParcelamento and cpit.documentoTipo.id = " +DocumentoTipo.GUIA_PAGAMENTO+" and gpag.id = cpit.guiaPagamentoGeral.guiaPagamento.id " +
			"and gpag.debitoTipo.id =  " + DebitoTipo.ACRESCIMOS_POR_IMPONTUALIDADE;
			Collection contratoItens = session.createQuery(consulta)
			.setInteger("idContratoParcelamento", idContratoParcelamento).list();
			
			int removeu = 1;
			if(contratoItens != null && !contratoItens.isEmpty()){
				consulta = " delete from " + ContratoParcelamentoItem.class.getName() +" where cpit.contrato.id in "+ contratoItens.toString().replaceAll("[", "(").replaceAll("]", ")");
				removeu = (Integer) session.createQuery(consulta).executeUpdate();
			}
			
			if (removeu == 0) {
				retorno = false;
			}else{
				retorno = true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta as Guias de Pagamento de 'Juros sobre Contrato' vinculadas ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerGuiasDePagtoJurosSobreContratoVinculadasAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException {
		
		Boolean retorno = true;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " select cpit.id from " + ContratoParcelamentoItem.class.getName() +" cpit, "+GuiaPagamento.class.getName() +" gpag "+
			" where cpit.contrato.id = :idContratoParcelamento and cpit.documentoTipo.id = " +DocumentoTipo.GUIA_PAGAMENTO+" and gpag.id = cpit.guiaPagamentoGeral.guiaPagamento.id " +
			"and gpag.debitoTipo.codigoConstante =  " + DebitoTipo.JUROS_SOBRE_CONTR_PARCELAMENTO;
			Collection contratoItens = session.createQuery(consulta)
			.setInteger("idContratoParcelamento", idContratoParcelamento).list();
			
			int removeu = 1;
			if(contratoItens != null && !contratoItens.isEmpty()){
				consulta = " delete from " + ContratoParcelamentoItem.class.getName() +" where cpit.contrato.id in "+ contratoItens.toString().replaceAll("[", "(").replaceAll("]", ")");
				removeu = (Integer) session.createQuery(consulta).executeUpdate();
			}
			
			if (removeu == 0) {
				retorno = false;
			}else{
				retorno = true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}

	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta o id da RD relacionada ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 29/06/2011
	 */
	public Integer pesquisarRDContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			
			consulta = " SELECT cprd_id AS idRD FROM cobranca.contrato_parcel "
				+ " WHERE cpar_id = :idContratoParcelamento";

			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("idRD", Hibernate.INTEGER)
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.setMaxResults(1).uniqueResult();
			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}

	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta os dados das contas vinculadas ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 02/07/2011
	 */
	public Collection<ContaValoresHelper> pesquisarDadosDasContasContratoParcelamento(Integer idContratoParcelamento) 
		throws ErroRepositorioException {
		
		Collection<Object[]> dados = null;
		Collection<ContaValoresHelper> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			
			consulta = " SELECT cnta.cnta_id AS idConta, cnta.cnta_amreferenciaconta AS anoMes, "
					 + "   cnta.cnta_dtvencimentoconta AS dataVencimento, "
					 + "   cnta.cnta_vlagua AS valorAgua, cnta.cnta_vlesgoto AS valorEsgoto, "
					 + "   cnta.cnta_vldebitos AS valorDebitos, cnta.cnta_vlcreditos AS valorCreditos, "
					 + "   cnta.cnta_vlimpostos AS valorImpostos, "
					 + "   cpit.cpit_vlacrescimosimpont AS valorAcrescimos, "
					 + "   dcst.dcst_dsdebitocreditosituacao AS dcstDescricao, "
					 + "   dcst.dcst_dsabreviado AS dcstDescricaoAbreviada, cnta.imov_id AS idImovel, "
					 + "   cnta.cnta_dtrevisao AS dataRevisao, cnta.cmrv_id AS idMotivoRevisao, "
					 + "   cnta_nnconsumoagua AS consumoAgua, cnta_nnconsumoesgoto AS consumoEsgoto "
					 + " FROM faturamento.conta cnta "
					 + "   INNER JOIN cobranca.contrato_parcel_item cpit ON cnta.cnta_id = cpit.cnta_id "
					 + "   INNER JOIN faturamento.debito_credito_situacao dcst ON dcst.dcst_id = cnta.dcst_idatual "
					 + " WHERE cpit.cpar_id = :idContratoParcelamento ";

			dados = (Collection<Object[]>) session.createSQLQuery(consulta)
				.addScalar("idConta", Hibernate.INTEGER)
				.addScalar("anoMes", Hibernate.INTEGER)
				.addScalar("dataVencimento", Hibernate.DATE)
				.addScalar("valorAgua", Hibernate.BIG_DECIMAL)
				.addScalar("valorEsgoto", Hibernate.BIG_DECIMAL)
				.addScalar("valorDebitos", Hibernate.BIG_DECIMAL)
				.addScalar("valorCreditos", Hibernate.BIG_DECIMAL)
				.addScalar("valorImpostos", Hibernate.BIG_DECIMAL)
				.addScalar("valorAcrescimos", Hibernate.BIG_DECIMAL)
				.addScalar("dcstDescricao", Hibernate.STRING)
				.addScalar("dcstDescricaoAbreviada", Hibernate.STRING)
				.addScalar("idImovel", Hibernate.INTEGER)
				.addScalar("dataRevisao", Hibernate.DATE)
				.addScalar("idMotivoRevisao", Hibernate.INTEGER)
				.addScalar("consumoAgua", Hibernate.INTEGER)
				.addScalar("consumoEsgoto", Hibernate.INTEGER)
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.list();
			
			
			if (dados != null && !dados.isEmpty()) {
				Iterator iterator = dados.iterator();
				
				while(iterator.hasNext()) {
					Object[] dadosConta = (Object[]) iterator.next();
					ContaValoresHelper helper = new ContaValoresHelper();
					Conta conta = new Conta();
					DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
					
					
					if (dadosConta[0] != null) {
						conta.setId((Integer) dadosConta[0]);
					}

					if (dadosConta[1] != null) {
						conta.setReferencia((Integer) dadosConta[1]);
					}

					if (dadosConta[2] != null) {
						conta.setDataVencimentoConta((Date) dadosConta[2]);
					}

					if (dadosConta[3] != null) {
						conta.setValorAgua((BigDecimal) dadosConta[3]);
					}

					if (dadosConta[4] != null) {
						conta.setValorEsgoto((BigDecimal) dadosConta[4]);
					}

					if (dadosConta[5] != null) {
						conta.setDebitos((BigDecimal) dadosConta[5]);
					}

					if (dadosConta[6] != null) {
						conta.setValorCreditos((BigDecimal) dadosConta[6]);
					}

					if (dadosConta[7] != null) {
						conta.setValorImposto((BigDecimal) dadosConta[7]);
					}

					if (dadosConta[8] != null) {
						helper.setValorJurosMora((BigDecimal) dadosConta[8]);
						helper.setValorAtualizacaoMonetaria(BigDecimal.ZERO);
						helper.setValorMulta(BigDecimal.ZERO);
					}
					
					if (dadosConta[9] != null) {
						debitoCreditoSituacao.setDescricaoAbreviada((String) dadosConta[9]);
					}
					
					if (dadosConta[10] != null) {
						debitoCreditoSituacao.setDescricaoAbreviada((String) dadosConta[10]);
					}
					
					if (dadosConta[11] != null) {
						Imovel imovel = new Imovel();
						imovel.setId((Integer) dadosConta[11]);
						conta.setImovel(imovel);
					}
					
					if (dadosConta[12] != null) {
						conta.setDataRevisao((Date) dadosConta[12]);
					}
					
					if (dadosConta[13] != null) {
						ContaMotivoRevisao contaMotivoRevisao = new ContaMotivoRevisao();
						contaMotivoRevisao.setId((Integer) dadosConta[13]);
						conta.setContaMotivoRevisao(contaMotivoRevisao);
					}
					
					if (dadosConta[14] != null) {
						conta.setConsumoAgua((Integer) dadosConta[14]);
					}
					
					if (dadosConta[15] != null) {
						conta.setConsumoEsgoto((Integer) dadosConta[15]);
					}
					
					conta.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
					helper.setConta(conta);
					helper.setRevisao(new Integer(1));
					
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
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * [FS0008] – Verificar existência do contrato anterior
	 * 
	 * @author Mariana Victor
	 * @data 08/07/2011
	 */
	public Boolean verificarExistenciaContratoAnterior(String numeroContratoAnterior) 
	throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " select count(distinct cpar_nncontrato) AS quantidade "
					 + " from cobranca.contrato_parcel "
					 + " where cpar_nncontrato = :numeroContratoAnterior " ;
					
			retorno = (Integer) session.createSQLQuery(consulta)
						.addScalar("quantidade", 
								Hibernate.INTEGER)
						.setString("numeroContratoAnterior",
								numeroContratoAnterior)
						.setMaxResults(1).uniqueResult();
			
			if(retorno != null && retorno > 0){
				return true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return false;
		
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * [FS0037] - Verificar situação do contrato anterior
	 * 
	 * @author Mariana Victor
	 * @data 08/07/2011
	 */
	public Boolean verificarSituacaoContratoAnterior(String numeroContratoAnterior) 
	throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " select pcst_id AS situacaoParcelamento "
					 + " from cobranca.contrato_parcel "
					 + " where cpar_nncontrato = :numeroContratoAnterior " ;
					
			retorno = (Integer) session.createSQLQuery(consulta)
						.addScalar("situacaoParcelamento", 
								Hibernate.INTEGER)
						.setString("numeroContratoAnterior",
								numeroContratoAnterior)
						.setMaxResults(1).uniqueResult();
			
			if (retorno != null 
					&& retorno.compareTo(ParcelamentoSituacao.NORMAL) == 0) {
				return true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return false;
		
	}


	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa o débito a cobrar com valor pago a menor
	 * 
	 * @author Mariana Victor
	 * @data 21/07/2011
	 */
	public Object[] pesquisarDebitoACobrarValorPagoAMenor(Integer idItem) throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT dbac.dbac_id AS idDebito, " //0
					+ " dbac.dbac_amreferenciadebito AS anoMesDebito, " //1
					+ " dbac.loca_id AS idLoca, " //2
					+ " dbac.imov_id AS idImov, " //3
					+ " dbac.dbtp_id AS idDebitoTipo " //4
					+ " FROM faturamento.debito_a_cobrar dbac "
					+ "   INNER JOIN cobranca.contrato_parcel_item cpit ON cpit.dbac_id = dbac.dbac_id "
					+ " WHERE dbac.dcst_idatual = :debCredSitNormal "
					+ "   AND dbac.cmrv_id = :contaMotivoRevisao " 
					+ "   AND cpit.cpit_id = :idItem "
					+ "   AND ((SELECT COALESCE(SUM(pgmt.pgmt_vlpagamento), 0) FROM arrecadacao.pagamento pgmt WHERE pgmt.dbac_id = dbac.dbac_id) "
					+ "       + (SELECT COALESCE(SUM(pghi.pghi_vlpagamento), 0) FROM arrecadacao.pagamento_historico pghi WHERE pghi.dbac_id = dbac.dbac_id)) "
					+ "       < cpit.cpit_vlitem ";
			
			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("idDebito", Hibernate.INTEGER)
				.addScalar("anoMesDebito", Hibernate.INTEGER)
				.addScalar("idLoca", Hibernate.INTEGER)
				.addScalar("idImov", Hibernate.INTEGER)
				.addScalar("idDebitoTipo", Hibernate.INTEGER)
				.setInteger("idItem", idItem)
				.setInteger("debCredSitNormal", DebitoCreditoSituacao.NORMAL)
				.setInteger("contaMotivoRevisao", ContaMotivoRevisao.DEBITO_A_COBRAR_EM_CONTRATO_PARCELAMENTO)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] – Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * Pesquisa os itens de débitos do tipo débito a cobrar para geração de pagamento
	 * 
	 * @author Mariana Victor
	 * @data 21/07/2011
	 */
	public Collection<Object[]> pesquisarItensDebitoACobrar(Integer idContrato) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT distinct cpit.cpit_id AS idItem, " //0
					+ " cpit.cpit_vlitem AS valorItem, " //1
					+ " cpit.dbac_id AS idDebito, " //2
					+ " dbac.dbac_amreferenciadebito AS anoMesDebito, " //3
					+ " dbac.loca_id AS idLoca, " //4
					+ " dbac.imov_id AS idImov, " //5
					+ " dbac.dbtp_id AS idDebitoTipo " //6
					+ " FROM cobranca.contrato_parcel_item cpit "
					+ "   INNER JOIN faturamento.debito_a_cobrar dbac ON cpit.dbac_id = dbac.dbac_id "
					+ " WHERE cpit.cpar_id = :idContrato "
					+ "   AND cpit.dotp_id = :documentoTipoDebitoACobrar "
					+ "   AND dbac.dcst_idatual = :debCredSitNormal "
					+ "   AND dbac.cmrv_id = :contaMotivoRevisao "
					+ "   AND NOT EXISTS (SELECT pgmt.cnta_id FROM arrecadacao.pagamento pgmt WHERE pgmt.dbac_id = dbac.dbac_id) "
					+ "   AND NOT EXISTS (SELECT pghi.cnta_id FROM arrecadacao.pagamento_historico pghi WHERE pghi.dbac_id = dbac.dbac_id) "
					+ " ORDER BY dbac.dbac_amreferenciadebito, dbac.imov_id ";
			
			retorno = session.createSQLQuery(consulta)
				.addScalar("idItem", Hibernate.INTEGER)
				.addScalar("valorItem", Hibernate.BIG_DECIMAL)
				.addScalar("idDebito", Hibernate.INTEGER)
				.addScalar("anoMesDebito", Hibernate.INTEGER)
				.addScalar("idLoca", Hibernate.INTEGER)
				.addScalar("idImov", Hibernate.INTEGER)
				.addScalar("idDebitoTipo", Hibernate.INTEGER)
				.setInteger("idContrato", idContrato)
				.setInteger("debCredSitNormal", DebitoCreditoSituacao.NORMAL)
				.setInteger("contaMotivoRevisao", ContaMotivoRevisao.DEBITO_A_COBRAR_EM_CONTRATO_PARCELAMENTO)
				.setInteger("documentoTipoDebitoACobrar", DocumentoTipo.DEBITO_A_COBRAR)
				.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}

	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta os dados dos débitos a cobrar vinculados ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 26/07/2011
	 */
	public Collection<DebitoACobrar> pesquisarDadosDosDebitosACobrarContratoParcelamento(Integer idContratoParcelamento) 
		throws ErroRepositorioException {
		
		Collection<Object[]> dados = null;
		Collection<DebitoACobrar> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			
			consulta = " SELECT dbac.dbac_id AS idDebitoACob, " //0
					 + " dbtp.dbtp_dsdebitotipo AS debitoTipo, " //1
					 + " dbac.dbac_amreferenciadebito AS anoMesReferencia, " //2
					 + " dbac.dbac_amcobrancadebito AS anoMesCobranca, " //3
					 + " dbac.dbac_nnprestacaocobradas AS numeroParcelasCobradas, " //4
					 + " dbac.dbac_nnprestacaodebito AS numeroParcelasDebitos, " //5
					 + " dbac.dbac_nnparcelabonus AS numeroParcelasBonus, " //6
					 + " dbac.dbac_vldebito as valorRestante, " //7
					 + " dbac.imov_id AS idImovel, " //8
					 + " dbac.cmrv_id AS idContaMotRev " //9
					 + " FROM faturamento.debito_a_cobrar dbac "
					 + "   INNER JOIN cobranca.contrato_parcel_item cpit ON dbac.dbac_id = cpit.dbac_id "
					 + "   INNER JOIN faturamento.debito_tipo dbtp ON dbtp.dbtp_id = dbac.dbtp_id "
					 + " WHERE cpit.cpar_id = :idContratoParcelamento AND cpit.cpit_icitemcancelado = :icItemCancelado ";

			dados = (Collection<Object[]>) session.createSQLQuery(consulta)
				.addScalar("idDebitoACob", Hibernate.INTEGER)
				.addScalar("debitoTipo", Hibernate.STRING)
				.addScalar("anoMesReferencia", Hibernate.INTEGER)
				.addScalar("anoMesCobranca", Hibernate.INTEGER)
				.addScalar("numeroParcelasCobradas", Hibernate.SHORT)
				.addScalar("numeroParcelasDebitos", Hibernate.SHORT)
				.addScalar("numeroParcelasBonus", Hibernate.SHORT)
				.addScalar("valorRestante", Hibernate.BIG_DECIMAL)
				.addScalar("idImovel", Hibernate.INTEGER)
				.addScalar("idContaMotRev", Hibernate.INTEGER)
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.setInteger("icItemCancelado", ConstantesSistema.NAO)
				.list();
			
			
			if (dados != null && !dados.isEmpty()) {
				Iterator iterator = dados.iterator();
				
				while(iterator.hasNext()) {
					Object[] dadosDebitos = (Object[]) iterator.next();
					DebitoACobrar debitoACobrar = new DebitoACobrar();
					
					if (dadosDebitos[0] != null) {
						debitoACobrar.setId((Integer) dadosDebitos[0]);
					}

					if (dadosDebitos[1] != null) {
						DebitoTipo debitoTipo = new DebitoTipo();
						debitoTipo.setDescricao((String) dadosDebitos[1]);
						debitoACobrar.setDebitoTipo(debitoTipo);
					}

					if (dadosDebitos[2] != null) {
						debitoACobrar.setAnoMesReferenciaDebito((Integer) dadosDebitos[2]);
					}

					if (dadosDebitos[3] != null) {
						debitoACobrar.setAnoMesCobrancaDebito((Integer) dadosDebitos[3]);
					}

					if (dadosDebitos[4] != null) {
						debitoACobrar.setNumeroPrestacaoCobradas((Short) dadosDebitos[4]);
					}

					if (dadosDebitos[5] != null) {
						debitoACobrar.setNumeroPrestacaoDebito((Short) dadosDebitos[5]);
					}

					if (dadosDebitos[6] != null) {
						debitoACobrar.setNumeroParcelaBonus((Short) dadosDebitos[6]);
					}

					if (dadosDebitos[7] != null) {
						debitoACobrar.setValorDebito((BigDecimal) dadosDebitos[7]);
					}
					
					if (dadosDebitos[8] != null) {
						Imovel imovel = new Imovel();
						imovel.setId((Integer) dadosDebitos[8]);
						debitoACobrar.setImovel(imovel);
					}
					
					if (dadosDebitos[9] != null) {
						ContaMotivoRevisao contaMotivoRevisao = new ContaMotivoRevisao();
						contaMotivoRevisao.setId((Integer) dadosDebitos[9]);
						debitoACobrar.setContaMotivoRevisao(contaMotivoRevisao);
					}

					retorno.add(debitoACobrar);
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
	 * [UC0184] Manter Débito A Cobrar
	 * 
	 * Verifica se o débito a cobrar está vinculado a um Contrato Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @date 28/07/2011
	 */
	public boolean verificaDebitoACobrarVinculadoContratoParcelamentoCliente(Integer idDebitoACobrar) 
		throws ErroRepositorioException {
		
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
	
		String consulta;
		Integer quantidadeDebitosACobrar = null;
	
		try {
	
			consulta = "SELECT COUNT(*) AS quantidade FROM cobranca.contrato_parcel_item item " +
						" INNER JOIN cobranca.contrato_parcel cpar ON cpar.cpar_id = item.cpar_id " + 
						" WHERE item.dbac_id = :idDebitoACobrar and cpar.pcst_id = " + ParcelamentoSituacao.NORMAL;
	
			quantidadeDebitosACobrar = (Integer) session.createSQLQuery(consulta)
							.addScalar("quantidade", Hibernate.INTEGER)
							.setInteger("idDebitoACobrar", idDebitoACobrar)
						.setMaxResults(1).uniqueResult();
	
			if (quantidadeDebitosACobrar != null && quantidadeDebitosACobrar > 0) {
				retorno = true;
			}
			
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
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * 1. O sistema obtém os dados do contrato de parcelamento por cliente
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public ContratoParcelamento obterDadosContratoParcelamento(String numeroContratoParcelamento) 
		throws ErroRepositorioException {
		
		Object[] dados = null;
		ContratoParcelamento contratoParcelamento = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			
			consulta = " SELECT cpar.cpar_id AS idContrato, " //0
					 + "   cpar.cpar_vlparcelamentoacobrar AS valorACobrar, " //1
					 + "   cbfm.cbfm_id AS idCobrancaForma, " //2
					 + "   cbfm.cbfm_dscobrancaforma AS descCobForma, " //3
					 + "   pcst.pcst_id AS idParcSituacao, " //4
					 + "   pcst.pcst_dsparcelamentosituacao AS descParcSituacao, " //5
					 + "   crtp.crtp_dsclienterelacaotipo AS descRelacTipo, " //6
					 + "   cpar.cpar_tmimplantacao AS dataImplantacao, " //7
					 + "   cpar.cpar_nnprestacoes AS numeroPrestacoes " //8
					 + " FROM cobranca.contrato_parcel cpar "
					 + "   LEFT JOIN cobranca.cobranca_forma cbfm ON cbfm.cbfm_id = cpar.cbfm_id "
					 + "   LEFT JOIN cobranca.parcelamento_situacao pcst ON pcst.pcst_id = cpar.pcst_id "
					 + "   LEFT JOIN cadastro.cliente_relacao_tipo crtp ON crtp.crtp_id = cpar.crtp_id "
					 + " WHERE cpar.cpar_nncontrato = :numeroContratoParcelamento ";

			dados = (Object[]) session.createSQLQuery(consulta)
				.addScalar("idContrato", Hibernate.INTEGER)
				.addScalar("valorACobrar", Hibernate.BIG_DECIMAL)
				.addScalar("idCobrancaForma", Hibernate.INTEGER)
				.addScalar("descCobForma", Hibernate.STRING)
				.addScalar("idParcSituacao", Hibernate.INTEGER)
				.addScalar("descParcSituacao", Hibernate.STRING)
				.addScalar("descRelacTipo", Hibernate.STRING)
				.addScalar("dataImplantacao", Hibernate.DATE)
				.addScalar("numeroPrestacoes", Hibernate.INTEGER)
				.setString("numeroContratoParcelamento", numeroContratoParcelamento)
				.setMaxResults(1).uniqueResult();
			
			if (dados != null) {
				contratoParcelamento = new ContratoParcelamento();
				
				if (dados[0] != null) {
					contratoParcelamento.setId((Integer) dados[0]);
				}
	
				if (dados[1] != null) {
					contratoParcelamento.setValorParcelamentoACobrar((BigDecimal) dados[1]);
				}
				
				if (dados[2] != null && dados[3] != null) {
					CobrancaForma cobrancaForma = new CobrancaForma();
					cobrancaForma.setId((Integer) dados[2]);
					cobrancaForma.setDescricao((String) dados[3]);
					contratoParcelamento.setCobrancaForma(cobrancaForma);
				}
				
				if (dados[4] != null && dados[5] != null) {
					ParcelamentoSituacao parcelamentoSituacao = new ParcelamentoSituacao();
					parcelamentoSituacao.setId((Integer) dados[4]);
					parcelamentoSituacao.setDescricao((String) dados[5]);
					contratoParcelamento.setParcelamentoSituacao(parcelamentoSituacao);
				}
				
				if (dados[6] != null) {
					ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
					clienteRelacaoTipo.setDescricao((String) dados[6]);
					contratoParcelamento.setRelacaoCliente(clienteRelacaoTipo);
				}
				
				if (dados[7] != null) {
					contratoParcelamento.setDataImplantacao((Date) dados[7]);
				}
				
				if (dados[8] != null) {
					contratoParcelamento.setNumeroPrestacoes((Integer) dados[8]);
				}
				
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return contratoParcelamento;
		
	}

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * 2.2.	Dados do Parcelamento 
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public Collection<PrestacaoContratoParcelamento> obterDadosPrestacoesContratoParcelamento(Integer idContratoParcelamento) 
		throws ErroRepositorioException {
		
		Collection<Object[]> dados = null;
		Collection<PrestacaoContratoParcelamento> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			
			consulta = " SELECT cppr.cppr_id AS idPrestacao, " //0
					 + "   cppr.cppr_nnprestacao AS numeroPrestacao, " //1
					 + "   cppr.cppr_dtvencimento AS dataVencimento, " //2
					 + "   cppr.cppr_vlprestacao AS valorPrestacao " //3
					 + " FROM cobranca.contrato_parcel_prest cppr "
					 + " WHERE cppr.cpar_id = :idContratoParcelamento "
					 + " ORDER BY cppr.cppr_nnprestacao ";

			dados = (Collection<Object[]>) session.createSQLQuery(consulta)
				.addScalar("idPrestacao", Hibernate.INTEGER)
				.addScalar("numeroPrestacao", Hibernate.INTEGER)
				.addScalar("dataVencimento", Hibernate.DATE)
				.addScalar("valorPrestacao", Hibernate.BIG_DECIMAL)
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.list();
			
			if (dados != null && !dados.isEmpty()) {
				Iterator iterator = dados.iterator();
				
				while(iterator.hasNext()) {
					Object[] dadosPrestacao = (Object[]) iterator.next();
					
					PrestacaoContratoParcelamento prestacaoContratoParcelamento = new PrestacaoContratoParcelamento();

					if (dadosPrestacao[0] != null) {
						prestacaoContratoParcelamento.setId((Integer) dadosPrestacao[0]);
					}
					
					if (dadosPrestacao[1] != null) {
						prestacaoContratoParcelamento.setNumero((Integer) dadosPrestacao[1]);
					}
					
					if (dadosPrestacao[2] != null) {
						prestacaoContratoParcelamento.setDataVencimento((Date) dadosPrestacao[2]);
					}
					
					if (dadosPrestacao[3] != null) {
						prestacaoContratoParcelamento.setValor((BigDecimal) dadosPrestacao[3]);
					}
					
					retorno.add(prestacaoContratoParcelamento);
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
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public CobrancaDocumentoItem obterDocumentoCobrancaPrestacao(Integer idPrestacao) 
		throws ErroRepositorioException {
		
		Object[] dados = null;
		CobrancaDocumentoItem retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			
			consulta = " SELECT cbdo.cbdo_id AS idDocumento, " //0
					 + "   cbdo.cbdo_nnsequenciadocumento AS seqDocumento, " //1
					 + "   clie.clie_id AS idCliente, " //2
					 + "   clie.clie_nmcliente AS nomeCliente, " //3
					 + "   cdit.cdit_id AS idItem " //4
					 + " FROM cobranca.cobranca_documento_item cdit "
					 + "   INNER JOIN cobranca.cobranca_documento cbdo on cbdo.cbdo_id = cdit.cbdo_id "
					 + "   INNER JOIN cadastro.cliente clie on cbdo.clie_id = clie.clie_id "
					 + " WHERE cdit.cppr_id = :idPrestacao ";

			dados = (Object[]) session.createSQLQuery(consulta)
				.addScalar("idDocumento", Hibernate.INTEGER)
				.addScalar("seqDocumento", Hibernate.INTEGER)
				.addScalar("idCliente", Hibernate.INTEGER)
				.addScalar("nomeCliente", Hibernate.STRING)
				.addScalar("idItem", Hibernate.INTEGER)
				.setInteger("idPrestacao", idPrestacao)
				.setMaxResults(1).uniqueResult();
			
			if (dados != null) {
				CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
				retorno = new CobrancaDocumentoItem();

				if (dados[0] != null) {
					cobrancaDocumento.setId((Integer) dados[0]);
				}
				
				if (dados[1] != null) {
					cobrancaDocumento.setNumeroSequenciaDocumento((Integer) dados[1]);
				}
				
				if (dados[2] != null && dados[3] != null) {
					Cliente cliente = new Cliente(); 
					cliente.setId((Integer) dados[2]);
					cliente.setNome((String) dados[3]);
					cobrancaDocumento.setCliente(cliente);
				}
				
				if (dados[4] != null) {
					retorno.setId((Integer) dados[4]);
				}
				
				retorno.setCobrancaDocumento(cobrancaDocumento);
				
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta o Documento de Cobrança vinculado ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 01/08/2011
	 */
	public Boolean removerDocumentoCobrancaVinculadoAContratoParcelamento(Integer idDocumentoCobranca) 
		throws ErroRepositorioException {
		
		Boolean retorno = true;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = "DELETE gcom.cobranca.CobrancaDocumento cbdo "
				+ "  where cbdo.id = :idDocumentoCobranca) ";

			int removeu = (Integer) session.createQuery(consulta)
				.setInteger("idDocumentoCobranca", idDocumentoCobranca)
				.executeUpdate();
			
			if (removeu == 0) {
				retorno = false;
			}else{
				retorno = true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta os Itens de Documento de Cobrança vinculados ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 01/08/2011
	 */
	public Boolean removerItemDocumentoCobrancaVinculadoAContratoParcelamento(Integer idItem) 
		throws ErroRepositorioException {
		
		Boolean retorno = true;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " DELETE gcom.cobranca.CobrancaDocumentoItem cdit "
				+ "  where cdit.id = :idItem ";

			int removeu = (Integer) session.createQuery(consulta)
				.setInteger("idItem", idItem)
				.executeUpdate();
			
			if (removeu == 0) {
				retorno = false;
			}else{
				retorno = true;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * 
	 * Retorna os dados do débito a cobrar caso exista algum pagamento para o mesmo.
	 * 
	 * @author Mariana Victor
	 * @data 03/08/2011
	 */
	public Object[] obterDadosDebitoACobrarPagoAMenor(Integer idDebitoACobrar) 
		throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT dbac.imov_id AS idImovel, "
				+ "   dbtp.dbtp_dsdebitotipo AS debitoTipo "
				+ " FROM faturamento.debito_a_cobrar dbac "
				+ "   INNER JOIN faturamento.debito_tipo dbtp ON dbtp.dbtp_id = dbac.dbtp_id "
				+ "   AND (EXISTS (SELECT pgmt.dbac_id FROM arrecadacao.pagamento pgmt WHERE pgmt.dbac_id = dbac.dbac_id) "
				+ "    OR EXISTS (SELECT pghi.dbac_id FROM arrecadacao.pagamento_historico pghi WHERE pghi.dbac_id = dbac.dbac_id)) "
				+ " WHERE dbac.dbac_id = :idDebitoACobrar ";

			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("idImovel", Hibernate.INTEGER)
				.addScalar("debitoTipo", Hibernate.STRING)
				.setInteger("idDebitoACobrar", idDebitoACobrar)
				.setMaxResults(1).uniqueResult();
	
			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 05/08/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamentoCompleto(Integer idContrato, String numeroContrato) 
		throws ErroRepositorioException {
		
		ContratoParcelamento contratoParcelamento = null;
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT cpar_icparcelamentocomjuros AS indicadorParcComJuros, " //0
				+ "    cpar_vljurosparcelamento AS valorJurosParc, " //1
				+ "    cpar_icdebitocomacrescimo AS indicadorDebAcresc, " //2
				+ "    cpar_vlacrescimosimpont AS valorAcrecImpont, " //3
				+ "    cpar_nncontrato AS numeroContrato, " //4
				+ "    cpar_dtcontrato AS dataContrato, " //5
				+ "    cpar_cdresponsavel AS codigoResponsavel, " //6
				+ "    cpar_amreferenciadebitoini AS anoMesInicial, " //7
				+ "    cpar_amreferenciadebitofim AS anoMesFim, " //8
				+ "    cpar_dtvencimentodebitoini AS dataInicial, " //9
				+ "    cpar_dtvencimentodebitofim AS dataFim, " //10
				+ "    cpar_dsobservacao AS observacao, " //11
				+ "    cpar_icinformarvalorparcela AS icInformValorParc, " //12
				+ "    cpar_dtvencimento AS dataVencimento, " //13
				+ "    cpar_nndiasentrevencimentoparc AS diasVencParc, " //14
				+ "    cpar_qtfaturasparceladas AS qtdFatParcelas, " //15
				+ "    cpar_vlconta AS valorConta, " //16
				+ "    cpar_vldebitoacobrar AS valorDebACobrar, " //17
				+ "    cpar_iccontasrevisao AS icContasRev, " //18
				+ "    cpar_icdebitoacobrar AS icDebitosACobrar, " //19
				+ "    cpar_iccreditoarealizar AS icCredARealizar, " //20
				+ "    cpar_nnprestacoes AS numeroPrestacoes, " //21
				+ "    cpar_txjuros AS taxaJuros, " //22
				+ "    cpar_vljurosmora AS valorJuros, " //23
				+ "    cpar_vlparcelado AS valorParcelado, " //24
				+ "    cpar_vldebitoatualizado AS valorDebAtu, " //25
				+ "    cpar_vlparcelamentoacobrar AS valorParcACobrar, " //26
				+ "    cpar_amreferenciafaturamento AS anoMesFaturamento, " //27
				+ "    cpar_tmimplantacao AS dataImplantacao, " //28
				+ "    cpar_tmcancelamento AS dataCancelamento, " //29
				+ "    cpar_icvlparcelainformadausur AS icInfValorParc, " //30
				+ "    pcst_id AS parcelamentoSituacao, " //31
				+ "    cpar_id AS idContrato " //32
				+ "  FROM cobranca.contrato_parcel ";
			if (idContrato != null) {
				consulta = consulta
					+ "  WHERE cpar_id = :idContrato ";
			} else if (numeroContrato != null && !numeroContrato.trim().equals("")) {
				consulta = consulta
					+ "  WHERE cpar_nncontrato = :numeroContrato ";
			}

			Query query = session.createSQLQuery(consulta)
				.addScalar("indicadorParcComJuros", Hibernate.SHORT)
				.addScalar("valorJurosParc", Hibernate.BIG_DECIMAL)
				.addScalar("indicadorDebAcresc", Hibernate.SHORT)
				.addScalar("valorAcrecImpont", Hibernate.BIG_DECIMAL)
				.addScalar("numeroContrato", Hibernate.STRING)
				.addScalar("dataContrato", Hibernate.DATE)
				.addScalar("codigoResponsavel", Hibernate.SHORT)
				.addScalar("anoMesInicial", Hibernate.INTEGER)
				.addScalar("anoMesFim", Hibernate.INTEGER)
				.addScalar("dataInicial", Hibernate.DATE)
				.addScalar("dataFim", Hibernate.DATE)
				.addScalar("observacao", Hibernate.STRING)
				.addScalar("icInformValorParc", Hibernate.SHORT)
				.addScalar("dataVencimento", Hibernate.DATE)
				.addScalar("diasVencParc", Hibernate.INTEGER)
				.addScalar("qtdFatParcelas", Hibernate.INTEGER)
				.addScalar("valorConta", Hibernate.BIG_DECIMAL)
				.addScalar("valorDebACobrar", Hibernate.BIG_DECIMAL)
				.addScalar("icContasRev", Hibernate.SHORT)
				.addScalar("icDebitosACobrar", Hibernate.SHORT)
				.addScalar("icCredARealizar", Hibernate.SHORT)
				.addScalar("numeroPrestacoes", Hibernate.INTEGER)
				.addScalar("taxaJuros", Hibernate.BIG_DECIMAL)
				.addScalar("valorJuros", Hibernate.BIG_DECIMAL)
				.addScalar("valorParcelado", Hibernate.BIG_DECIMAL)
				.addScalar("valorDebAtu", Hibernate.BIG_DECIMAL)
				.addScalar("valorParcACobrar", Hibernate.BIG_DECIMAL)
				.addScalar("anoMesFaturamento", Hibernate.INTEGER)
				.addScalar("dataImplantacao", Hibernate.DATE)
				.addScalar("dataCancelamento", Hibernate.DATE)
				.addScalar("icInfValorParc", Hibernate.SHORT)
				.addScalar("parcelamentoSituacao", Hibernate.INTEGER)
				.addScalar("idContrato", Hibernate.INTEGER);
			
			if (idContrato != null) {
				query.setInteger("idContrato", idContrato);
			} else if (numeroContrato != null && !numeroContrato.trim().equals("")) {
				query.setString("numeroContrato", numeroContrato);
			}
			
			retorno = (Object[]) query
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null) {
			contratoParcelamento = new ContratoParcelamento();
			
			if (retorno[0] != null) {
				contratoParcelamento.setIndicadorParcelamentoJuros((Short) retorno[0]);
			}
			if (retorno[1] != null) {
				contratoParcelamento.setValorJurosParcelamento((BigDecimal) retorno[1]);
			}
			if (retorno[2] != null) {
				contratoParcelamento.setIndicadorDebitosAcrescimos((Short) retorno[2]);
			}
			if (retorno[3] != null) {
				contratoParcelamento.setValorTotalAcrescImpontualidade((BigDecimal) retorno[3]);
			}
			if (retorno[4] != null) {
				contratoParcelamento.setNumero((String) retorno[4]);
			}
			if (retorno[5] != null) {
				contratoParcelamento.setDataContrato((Date) retorno[5]);
			}
			if (retorno[6] != null) {
				contratoParcelamento.setIndicadorResponsavel((Short) retorno[6]);
			}
			if (retorno[7] != null) {
				contratoParcelamento.setAnoMesDebitoInicio((Integer) retorno[7]);
			}
			if (retorno[8] != null) {
				contratoParcelamento.setAnoMesDebitoFinal((Integer) retorno[8]);
			}
			if (retorno[9] != null) {
				contratoParcelamento.setDataVencimentoInicio((Date) retorno[9]);
			}
			if (retorno[10] != null) {
				contratoParcelamento.setDataVencimentoFinal((Date) retorno[10]);
			}
			if (retorno[11] != null) {
				contratoParcelamento.setObservacao((String) retorno[11]);
			}
			if (retorno[12] != null) {
				contratoParcelamento.setIndicadorPermiteInformarValorParcel((Short) retorno[12]);
			}
			if (retorno[13] != null) {
				contratoParcelamento.setDataVencimentoPrimParcela((Date) retorno[13]);
			}
			if (retorno[14] != null) {
				contratoParcelamento.setNumeroDiasEntreVencimentoParcel((Integer) retorno[14]);
			}
			if (retorno[15] != null) {
				contratoParcelamento.setQtdFaturasParceladas((Integer) retorno[15]);
			}
			if (retorno[16] != null) {
				contratoParcelamento.setValorTotalConta((BigDecimal) retorno[16]);
			}
			if (retorno[17] != null) {
				contratoParcelamento.setValorTotalDebitosCobrar((BigDecimal) retorno[17]);
			}
			if (retorno[18] != null) {
				contratoParcelamento.setIndicadorContasRevisao((Short) retorno[18]);
			}
			if (retorno[19] != null) {
				contratoParcelamento.setIndicadorDebitoACobrar((Short) retorno[19]);
			}
			if (retorno[20] != null) {
				contratoParcelamento.setIndicadorCreditoARealizar((Short) retorno[20]);
			}
			if (retorno[21] != null) {
				contratoParcelamento.setNumeroPrestacoes((Integer) retorno[21]);
			}
			if (retorno[22] != null) {
				contratoParcelamento.setTaxaJuros((BigDecimal) retorno[22]);
			}
			if (retorno[23] != null) {
				contratoParcelamento.setValorJurosMora((BigDecimal) retorno[23]);
			}
			if (retorno[24] != null) {
				contratoParcelamento.setValorJurosParcelamento((BigDecimal) retorno[24]);
			}
			if (retorno[25] != null) {
				contratoParcelamento.setValorDebitoAtualizado((BigDecimal) retorno[25]);
			}
			if (retorno[26] != null) {
				contratoParcelamento.setValorParcelamentoACobrar((BigDecimal) retorno[26]);
			}
			if (retorno[27] != null) {
				contratoParcelamento.setAnoMesReferenciaFaturamento((Integer) retorno[27]);
			}
			if (retorno[28] != null) {
				contratoParcelamento.setDataImplantacao((Date) retorno[28]);
			}
			if (retorno[29] != null) {
				contratoParcelamento.setDataCancelamento((Date) retorno[29]);
			}
			if (retorno[30] != null) {
				contratoParcelamento.setIndicadorParcelaInformadaPeloUsuario((Short) retorno[30]);
			}
			if (retorno[31] != null) {
				ParcelamentoSituacao parcelamentoSituacao = new ParcelamentoSituacao();
				parcelamentoSituacao.setId((Integer) retorno[31]);
				contratoParcelamento.setParcelamentoSituacao(parcelamentoSituacao);
			}
			if (retorno[31] != null) {
				contratoParcelamento.setId((Integer) retorno[32]);
			}
		}
		
		return contratoParcelamento;
		
	}
	
	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/08/2011
	 */
	public Integer pesquisarIdClientecontrato(Integer idContratoParcelamento) 
		throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Object[]> dadosClienteContrato = null;
	
		try{	
			consulta = " SELECT cpcl_id AS idClienteContrato, "
				+ "    cpcl_icclientesuperior AS indicadorSuperior"
				+ " FROM cobranca.contrato_parcel_cliente  "
				+ " WHERE cpar_id = :idContratoParcelamento ";

			dadosClienteContrato = (Collection<Object[]>) session.createSQLQuery(consulta)
				.addScalar("idClienteContrato", Hibernate.INTEGER)
				.addScalar("indicadorSuperior", Hibernate.SHORT)
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.list();
	
			if (dadosClienteContrato != null && !dadosClienteContrato.isEmpty()) {
				
				Iterator iterator = dadosClienteContrato.iterator();
				
				while(iterator.hasNext()) {
					Object[] dados = (Object[]) iterator.next();
					
					if (dados[1] != null 
							&& ((Short) dados[1]).compareTo(ConstantesSistema.SIM) == 0) {
						retorno = (Integer) dados[0];
						
						break;
					}
				}
				
				if (retorno == null) {
					Object[] dados = dadosClienteContrato.iterator().next();
					
					retorno = (Integer) dados[0];
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
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/08/2011
	 */
	public ContratoParcelamentoCliente pesquisarClienteContrato(Integer idContratoParcelamento) 
		throws ErroRepositorioException {
		
		ContratoParcelamentoCliente retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Object[]> dadosClienteContrato = null;
	
		try{	
			consulta = " SELECT cpcl.cpcl_id AS idClienteContrato, "
				+ "    cpcl.cpcl_icclientesuperior AS indicadorSuperior,"
				+ "    clie.clie_nmcliente AS nome,"
				+ "    clie.clie_nncnpj AS cnpj, "
				+ "    clie.clie_id AS idCliente "
				+ " FROM cobranca.contrato_parcel_cliente cpcl "
				+ "    INNER JOIN cadastro.cliente clie ON clie.clie_id = cpcl.clie_id "
				+ " WHERE cpcl.cpar_id = :idContratoParcelamento ";

			dadosClienteContrato = (Collection<Object[]>) session.createSQLQuery(consulta)
				.addScalar("idClienteContrato", Hibernate.INTEGER)
				.addScalar("indicadorSuperior", Hibernate.SHORT)
				.addScalar("nome", Hibernate.STRING)
				.addScalar("cnpj", Hibernate.STRING)
				.addScalar("idCliente", Hibernate.INTEGER)
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.list();
	
			if (dadosClienteContrato != null && !dadosClienteContrato.isEmpty()) {
				
				Iterator iterator = dadosClienteContrato.iterator();
				
				while(iterator.hasNext()) {
					Object[] dados = (Object[]) iterator.next();
					
					if (dados[1] != null 
							&& ((Short) dados[1]).compareTo(ConstantesSistema.SIM) == 0) {
						
						retorno = new ContratoParcelamentoCliente();
						retorno.setId((Integer) dados[0]);
						
						Cliente cliente = new Cliente();
						cliente.setNome((String) dados[2]);
						cliente.setCnpj((String) dados[3]);
						cliente.setId((Integer) dados[4]);
						retorno.setCliente(cliente);
						
						break;
					}
				}
				
				if (retorno == null) {
					Object[] dados = dadosClienteContrato.iterator().next();
					
					retorno = new ContratoParcelamentoCliente();
					retorno.setId((Integer) dados[0]);
					
					Cliente cliente = new Cliente();
					cliente.setNome((String) dados[2]);
					cliente.setCnpj((String) dados[3]);
					cliente.setId((Integer) dados[4]);
					retorno.setCliente(cliente);
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
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 12/08/2011
	 */
	public ContratoParcelamentoCliente pesquisarClienteContrato(
			Integer idContratoParcelamento,
			Integer idCliente) 
		throws ErroRepositorioException {
		
		ContratoParcelamentoCliente retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Object[]> dadosClienteContrato = null;
	
		try{	
			consulta = " SELECT cpcl.cpcl_id AS idClienteContrato, "
				+ "    cpcl.cpcl_icclientesuperior AS indicadorSuperior,"
				+ "    clie.clie_nmcliente AS nome,"
				+ "    clie.clie_nncnpj AS cnpj, "
				+ "    clie.clie_id AS idCliente, "
				+ "    cltp.cltp_id AS idClienteTipo, "
				+ "    cltp.cltp_dsclientetipo AS clienteTipo "
				+ " FROM cobranca.contrato_parcel_cliente cpcl "
				+ "    INNER JOIN cadastro.cliente clie ON clie.clie_id = cpcl.clie_id "
				+ "    INNER JOIN cadastro.cliente_tipo cltp ON clie.cltp_id = cltp.cltp_id "
				+ " WHERE cpcl.cpar_id = :idContratoParcelamento "
				+ "    AND clie.clie_id = :idCliente ";
			
			dadosClienteContrato = (Collection<Object[]>) session.createSQLQuery(consulta)
				.addScalar("idClienteContrato", Hibernate.INTEGER) //0
				.addScalar("indicadorSuperior", Hibernate.SHORT) //1
				.addScalar("nome", Hibernate.STRING) //2
				.addScalar("cnpj", Hibernate.STRING) //3
				.addScalar("idCliente", Hibernate.INTEGER) //4
				.addScalar("idClienteTipo", Hibernate.INTEGER) //5
				.addScalar("clienteTipo", Hibernate.STRING) //6
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.setInteger("idCliente", idCliente)
				.list();
	
			if (dadosClienteContrato != null && !dadosClienteContrato.isEmpty()) {
				
				Iterator iterator = dadosClienteContrato.iterator();
				
				while(iterator.hasNext()) {
					Object[] dados = (Object[]) iterator.next();
					
					if (dados[1] != null 
							&& ((Short) dados[1]).compareTo(ConstantesSistema.SIM) == 0) {
						
						retorno = new ContratoParcelamentoCliente();
						retorno.setId((Integer) dados[0]);
						
						Cliente cliente = new Cliente();
						cliente.setNome((String) dados[2]);
						cliente.setCnpj((String) dados[3]);
						cliente.setId((Integer) dados[4]);
						
						ClienteTipo clienteTipo = new ClienteTipo();
						clienteTipo.setId((Integer) dados[5]);
						clienteTipo.setDescricao((String) dados[6]);
						
						cliente.setClienteTipo(clienteTipo);
						retorno.setCliente(cliente);
						
						break;
					}
				}
				
				if (retorno == null) {
					Object[] dados = dadosClienteContrato.iterator().next();
					
					retorno = new ContratoParcelamentoCliente();
					retorno.setId((Integer) dados[0]);
					
					Cliente cliente = new Cliente();
					cliente.setNome((String) dados[2]);
					cliente.setCnpj((String) dados[3]);
					cliente.setId((Integer) dados[4]);
					
					ClienteTipo clienteTipo = new ClienteTipo();
					clienteTipo.setId((Integer) dados[5]);
					clienteTipo.setDescricao((String) dados[6]);
					
					cliente.setClienteTipo(clienteTipo);
					retorno.setCliente(cliente);
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
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 15/08/2011
	 */
	public Collection<ContratoParcelamentoCliente> pesquisarClienteContrato(
			Integer idContratoParcelamento,
			Short indicadorClienteSuperior) 
		throws ErroRepositorioException {
		
		Collection<ContratoParcelamentoCliente> retorno = new ArrayList<ContratoParcelamentoCliente>();
		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Object[]> dadosClienteContrato = null;
	
		try{	
			consulta = " SELECT cpcl.cpcl_id AS idClienteContrato, "
				+ "    cpcl.cpcl_icclientesuperior AS indicadorSuperior,"
				+ "    clie.clie_nmcliente AS nome,"
				+ "    clie.clie_nncnpj AS cnpj, "
				+ "    clie.clie_id AS idCliente, "
				+ "    cltp.cltp_id AS idClienteTipo, "
				+ "    cltp.cltp_dsclientetipo AS clienteTipo "
				+ " FROM cobranca.contrato_parcel_cliente cpcl "
				+ "    INNER JOIN cadastro.cliente clie ON clie.clie_id = cpcl.clie_id "
				+ "    INNER JOIN cadastro.cliente_tipo cltp ON clie.cltp_id = cltp.cltp_id "
				+ " WHERE cpcl.cpar_id = :idContratoParcelamento ";
			
			if (indicadorClienteSuperior != null) {
				consulta = consulta 
					+ "    AND cpcl.cpcl_icclientesuperior = " + indicadorClienteSuperior;
			}

			dadosClienteContrato = (Collection<Object[]>) session.createSQLQuery(consulta)
				.addScalar("idClienteContrato", Hibernate.INTEGER) //0
				.addScalar("indicadorSuperior", Hibernate.SHORT) //1
				.addScalar("nome", Hibernate.STRING) //2
				.addScalar("cnpj", Hibernate.STRING) //3
				.addScalar("idCliente", Hibernate.INTEGER) //4
				.addScalar("idClienteTipo", Hibernate.INTEGER) //5
				.addScalar("clienteTipo", Hibernate.STRING) //6
				.setInteger("idContratoParcelamento", idContratoParcelamento)
				.list();
	
			if (dadosClienteContrato != null && !dadosClienteContrato.isEmpty()) {
				
				Iterator iterator = dadosClienteContrato.iterator();
				
				while(iterator.hasNext()) {
					Object[] dados = (Object[]) iterator.next();
					
					ContratoParcelamentoCliente contratoParcelamentoCliente = new ContratoParcelamentoCliente();
					contratoParcelamentoCliente.setId((Integer) dados[0]);
					
					Cliente cliente = new Cliente();
					cliente.setNome((String) dados[2]);
					cliente.setCnpj((String) dados[3]);
					cliente.setId((Integer) dados[4]);
					
					ClienteTipo clienteTipo = new ClienteTipo();
					clienteTipo.setId((Integer) dados[5]);
					clienteTipo.setDescricao((String) dados[6]);
					
					cliente.setClienteTipo(clienteTipo);
					contratoParcelamentoCliente.setCliente(cliente);

					retorno.add(contratoParcelamentoCliente);
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
	 * [UC1141] Emitir Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @created 29/08/2011
	 * 
	 * @param idContratoParcelamento
	 * @exception ErroRepositorioException
	 */
	public Collection<ContratoParcelamentoItem> pesquisarContratoParcelamentoItem(
			Integer idContratoParcelamento, Integer idDocumentoTipo) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection<ContratoParcelamentoItem> retorno = null;
		String consulta;

		try {
			consulta = " SELECT contratoParcelamentoItem FROM ContratoParcelamentoItem contratoParcelamentoItem "
					+ " left join fetch contratoParcelamentoItem.contaGeral contaGeral "
					+ " left join fetch contaGeral.conta conta "
					+ " left join fetch conta.imovel imovelConta "
					+ " left join fetch conta.debitoCreditoSituacaoAtual dcstConta "
					+ " left join fetch contratoParcelamentoItem.debitoACobrarGeral debitoACobrarGeral "
					+ " left join fetch debitoACobrarGeral.debitoACobrar debitoACobrar "
					+ " left join fetch debitoACobrar.imovel imovelDebACob "
					+ " left join fetch debitoACobrar.debitoTipo debitoTipo "
					+ " WHERE contratoParcelamentoItem.contrato.id = :idContratoParcelamento "
					+ "   AND contratoParcelamentoItem.documentoTipo.id = :idDocumentoTipo ";

			retorno = session.createQuery(consulta)
					.setInteger("idContratoParcelamento", idContratoParcelamento)
					.setInteger("idDocumentoTipo", idDocumentoTipo)
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
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 13/09/2011
	 */
	public CobrancaForma pesquisarFormaPagamentoRD(
			Integer idRD) 
		throws ErroRepositorioException {
		
		CobrancaForma retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		Object[] dados = null;
	
		try{	
			consulta = " SELECT cbfm.cbfm_id AS id, "
				+ "    cbfm.cbfm_dscobrancaforma AS descricao "
				+ " FROM cobranca.cobranca_forma cbfm "
				+ "    INNER JOIN cobranca.contrato_parcel_rd cprd ON cprd.cbfm_id = cbfm.cbfm_id "
				+ " WHERE cprd.cprd_id = :idRD ";
			
			dados = (Object[]) session.createSQLQuery(consulta)
				.addScalar("id", Hibernate.INTEGER) //0
				.addScalar("descricao", Hibernate.STRING) //1
				.setInteger("idRD", idRD)
				.setMaxResults(1).uniqueResult();
	
			if (dados != null 
					&& dados[0] != null 
					&& dados[1] != null) {
				
				retorno = new CobrancaForma();
				retorno.setId((Integer)dados[0]);
				retorno.setDescricao((String)dados[1]);
				
			}
			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}
	
}
