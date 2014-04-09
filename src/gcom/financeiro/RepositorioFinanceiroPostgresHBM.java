package gcom.financeiro;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;



/**
 * Repositorio para financeiro
 * 
 * @author Raphael Rossiter
 * @since 09/01/2006
 */
public class RepositorioFinanceiroPostgresHBM extends RepositorioFinanceiroHBM {

	public Collection<Object[]> pesquisarContasAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, "//0, 1
					+ " loca.loca_id as idLocalidade, "//2
					+ " (CASE WHEN conta.iper_id IS NOT NULL THEN conta.iper_id ELSE "
					+ ImovelPerfil.NORMAL.toString() + " END) as idImovelPerfil, "//3
					+ " (CASE WHEN cltp.epod_id IS NOT NULL THEN cltp.epod_id ELSE "
					+ EsferaPoder.PARTICULAR.toString() + " END) as idEsferaPoder, "//4
					
					+ " (CASE WHEN (select ctcg.catg_id from faturamento.conta_categoria ctcg "
					+ " where ctcg.cnta_id = conta.cnta_id "
					+ " order by ctcg.ctcg_qteconomia desc, ctcg.catg_id asc limit  1) IS NOT NULL "
					+ " THEN (select ctcg.catg_id from faturamento.conta_categoria ctcg "
					+ " where ctcg.cnta_id = conta.cnta_id "
					+ " order by ctcg.ctcg_qteconomia desc, ctcg.catg_id asc limit  1 ) ELSE "
					+ Categoria.RESIDENCIAL.toString() + " END) as idCategoria, "//5
					
					+ DocumentoTipo.CONTA.toString() + " as idDocumentoTipo, "//6
					+ " conta.cnta_dtvencimentoconta as dataVencimento, "//7
					
					//VALORES DE ÁGUA, ESGOTO, DÉBITO E CRÉDITO
					+ " count(conta.cnta_id) as quantidadeDocumentos, "//8
					+ " sum(coalesce(conta.cnta_vlagua,0)) as valorAguaCategoria, "//9
					+ " sum(coalesce(conta.cnta_vlesgoto,0)) as valorEsgotoCategoria, "//10
					+ " sum(coalesce(conta.cnta_vldebitos,0)) as valorDebitoCategoria, "//11
					+ " sum(coalesce(conta.cnta_vlcreditos,0)) as valorCreditoCategoria, "//12
					
					//VALOR DOS IMPOSTOS
					+ " sum(coalesce(conta.cnta_vlimpostos,0)) as valorImpostos, "//13
					+ " fdrc.fdrc_id AS idFaixa " //14
					
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.conta conta on loca.loca_id = conta.loca_id "
					+ " LEFT JOIN cadastro.cliente_conta clct on conta.cnta_id = clct.cnta_id and clct.crtp_id = "
					+ ClienteRelacaoTipo.RESPONSAVEL.toString()
					+ " LEFT JOIN cadastro.cliente resp on clct.clie_id = resp.clie_id "
					+ " LEFT JOIN cadastro.cliente_tipo cltp on resp.cltp_id = cltp.cltp_id "
					+ " LEFT JOIN financeiro.faixa_docs_a_receber fdrc ON (fdrc.fdrc_vlfaixainicial <= (coalesce(conta.cnta_vlagua,0) + coalesce(conta.cnta_vlesgoto,0) + coalesce(conta.cnta_vldebitos,0) - (coalesce(conta.cnta_vlcreditos,0) + coalesce(conta.cnta_vlimpostos,0))) "
					+ "   AND fdrc.fdrc_vlfaixafinal >= (coalesce(conta.cnta_vlagua,0) + coalesce(conta.cnta_vlesgoto,0) + coalesce(conta.cnta_vldebitos,0) - (coalesce(conta.cnta_vlcreditos,0) + coalesce(conta.cnta_vlimpostos,0)))) "
					
					+ " WHERE loca.loca_id = :idLocalidade "
					+ " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil and conta.dcst_idatual "
					+ " in ( :situacaoNormal, :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil and conta.dcst_idatual "
					+ " in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) "
					+ " and ( conta.dcst_idanterior is null "
					+ " or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior <> :situacaoIncluida ) ) ) ) "
					
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, (case when conta.iper_id is not null then conta.iper_id else "+ ImovelPerfil.NORMAL.toString() +"  end), (case when conta.iper_id is not null then conta.iper_id else  "+ ImovelPerfil.NORMAL.toString() +"   end), "
					+ " (case when cltp.epod_id is not null then cltp.epod_id else  "+ EsferaPoder.PARTICULAR.toString()+"   end), idCategoria, idDocumentoTipo, dataVencimento, idFaixa "
					
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idImovelPerfil, idImovelPerfil, "
					+ " idEsferaPoder, idCategoria, idDocumentoTipo, dataVencimento, idFaixa ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("idImovelPerfil", Hibernate.INTEGER)
					.addScalar("idEsferaPoder", Hibernate.INTEGER)
					.addScalar("idCategoria", Hibernate.INTEGER)
					.addScalar("idDocumentoTipo", Hibernate.INTEGER)
					.addScalar("dataVencimento", Hibernate.DATE)
					
					.addScalar("quantidadeDocumentos", Hibernate.INTEGER)
					.addScalar("valorAguaCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("valorEsgotoCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("valorDebitoCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("valorCreditoCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("valorImpostos", Hibernate.BIG_DECIMAL)
					.addScalar("idFaixa", Hibernate.INTEGER)
					
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferenciaContabil", anoMesReferenciaContabil)
					
					.setInteger("situacaoNormal",DebitoCreditoSituacao.NORMAL)
					.setInteger("situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada", DebitoCreditoSituacao.RETIFICADA)
					
					.setInteger("situacaoCancelada", DebitoCreditoSituacao.CANCELADA)
					.setInteger("situacaoCanceladaPorRetificacao", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("situacaoDebitoPrescrito", DebitoCreditoSituacao.DEBITO_PRESCRITO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Collection<Object[]> pesquisarGuiasPagamentoAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, "//0, 1
					+ " loca.loca_id as idLocalidade, "//2
					+ " (CASE WHEN imov.iper_id IS NOT NULL THEN imov.iper_id ELSE "
					+ ImovelPerfil.NORMAL.toString() + " END) as idImovelPerfil, "//3
					+ " (CASE WHEN cltp.epod_id IS NOT NULL THEN cltp.epod_id ELSE "
					+ EsferaPoder.PARTICULAR.toString() + " END) as idEsferaPoder, "//4
					
					+ " (CASE WHEN (select gpcg.catg_id from faturamento.guia_pagamento_categoria gpcg "
					+ " where gpcg.gpag_id = gpag.gpag_id "
					+ " order by gpcg.gpcg_qteconomia desc, gpcg.catg_id asc limit  1 ) IS NOT NULL "
					+ " THEN (select gpcg.catg_id from faturamento.guia_pagamento_categoria gpcg "
					+ " where gpcg.gpag_id = gpag.gpag_id "
					+ " order by gpcg.gpcg_qteconomia desc, gpcg.catg_id asc limit  1) ELSE "
					+ Categoria.RESIDENCIAL.toString() + " END) as idCategoria, "//5
					
					+ DocumentoTipo.GUIA_PAGAMENTO.toString() + " as idDocumentoTipo, "//6
					+ " gpag.gpag_dtvencimento as dataVencimento, "//7
					
					+ " count(gpag.gpag_id) as quantidadeDocumentos, "//8
					+ " sum(coalesce(gpag.gpag_vldebito,0)) as valorCategoria, "//9
					+ " fdrc.fdrc_id AS idFaixa " //10
					
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.guia_pagamento gpag on loca.loca_id = gpag.loca_id "
					+ " LEFT JOIN cadastro.imovel imov on imov.imov_id = gpag.imov_id "
					+ " LEFT JOIN cadastro.cliente_guia_pagamento clgp on gpag.gpag_id = clgp.gpag_id and clgp.crtp_id = "
					+ ClienteRelacaoTipo.RESPONSAVEL.toString()
					+ " LEFT JOIN cadastro.cliente resp on clgp.clie_id = resp.clie_id "
					+ " LEFT JOIN cadastro.cliente_tipo cltp on resp.cltp_id = cltp.cltp_id "
					+ " LEFT JOIN financeiro.faixa_docs_a_receber fdrc ON fdrc.fdrc_vlfaixainicial <= coalesce(gpag.gpag_vldebito,0) "
					+ "  AND fdrc.fdrc_vlfaixafinal >= coalesce(gpag.gpag_vldebito,0)"
					
					+ " WHERE loca.loca_id = :idLocalidade "
					+ " and ( ( gpag.gpag_amreferenciacontabil <= :anoMesReferenciaContabil and gpag.dcst_idatual "
					+ " in ( :situacaoNormal, :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( gpag.gpag_amreferenciacontabil > :anoMesReferenciaContabil and gpag.dcst_idatual "
					+ " in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) "
					+ " and gpag.dcst_idanterior is null ) ) "
					
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, (case when imov.iper_id is not null then imov.iper_id else  "+ ImovelPerfil.NORMAL.toString() +"   end), (case when imov.iper_id is not null then imov.iper_id else  "+ ImovelPerfil.NORMAL.toString() +"   end), "
					+ " (case when cltp.epod_id is not null then cltp.epod_id else  "+ EsferaPoder.PARTICULAR.toString()+"   end), idCategoria, idDocumentoTipo, dataVencimento, idFaixa "
					
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idImovelPerfil, idImovelPerfil, "
					+ " idEsferaPoder, idCategoria, idDocumentoTipo, dataVencimento, idFaixa ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("idImovelPerfil", Hibernate.INTEGER)
					.addScalar("idEsferaPoder", Hibernate.INTEGER)
					.addScalar("idCategoria", Hibernate.INTEGER)
					.addScalar("idDocumentoTipo", Hibernate.INTEGER)
					.addScalar("dataVencimento", Hibernate.DATE)
					
					.addScalar("quantidadeDocumentos", Hibernate.INTEGER)
					.addScalar("valorCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("idFaixa", Hibernate.INTEGER)
					
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferenciaContabil", anoMesReferenciaContabil)
					
					.setInteger("situacaoNormal",DebitoCreditoSituacao.NORMAL)
					.setInteger("situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada", DebitoCreditoSituacao.RETIFICADA)
					
					.setInteger("situacaoCancelada", DebitoCreditoSituacao.CANCELADA)
					.setInteger("situacaoCanceladaPorRetificacao", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("situacaoDebitoPrescrito", DebitoCreditoSituacao.DEBITO_PRESCRITO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Collection<Object[]> pesquisarDebitosACobrarAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, "//0, 1
					+ " loca.loca_id as idLocalidade, "//2
					+ " (CASE WHEN imov.iper_id IS NOT NULL THEN imov.iper_id ELSE "
					+ ImovelPerfil.NORMAL.toString() + " END) as idImovelPerfil, "//3
					+ " (CASE WHEN cltp.epod_id IS NOT NULL THEN cltp.epod_id ELSE "
					+ EsferaPoder.PARTICULAR.toString() + " END) as idEsferaPoder, "//4
					
					+ " (CASE WHEN (select dbcg.catg_id from faturamento.deb_a_cobrar_catg dbcg "
					+ " where dbcg.dbac_id = dbac.dbac_id "
					+ " order by dbcg.dbcg_qteconomia desc, dbcg.catg_id asc limit  1) IS NOT NULL "
					+ " THEN (select dbcg.catg_id from faturamento.deb_a_cobrar_catg dbcg "
					+ " where dbcg.dbac_id = dbac.dbac_id "
					+ " order by dbcg.dbcg_qteconomia desc, dbcg.catg_id asc limit  1) ELSE "
					+ Categoria.RESIDENCIAL.toString() + " END) as idCategoria, "//5
					
					+ DocumentoTipo.DEBITO_A_COBRAR.toString() + " as idDocumentoTipo, "//6
					
					+ " count(dbac.dbac_id) as quantidadeDocumentos, "//7
					+ " sum(( coalesce(dbac.dbac_vldebito,0) - " +
					" (trunc(( coalesce(dbac.dbac_vldebito,0) /dbac.dbac_nnprestacaodebito ),2) " +
					" * dbac.dbac_nnprestacaocobradas))) as valorCategoria, "//8
					+ " fdrc.fdrc_id AS idFaixa " //9
					
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.debito_a_cobrar dbac on loca.loca_id = dbac.loca_id "
					+ " INNER JOIN cadastro.imovel imov on imov.imov_id = dbac.imov_id "
					+ " LEFT JOIN cadastro.cliente_imovel clim on imov.imov_id = clim.imov_id and clim.crtp_id = "
					+ ClienteRelacaoTipo.RESPONSAVEL.toString() + " and clim.clim_dtrelacaofim is null "
					+ " LEFT JOIN cadastro.cliente resp on clim.clie_id = resp.clie_id "
					+ " LEFT JOIN cadastro.cliente_tipo cltp on resp.cltp_id = cltp.cltp_id "
					+ " LEFT JOIN financeiro.faixa_docs_a_receber fdrc ON (fdrc.fdrc_vlfaixainicial <= (coalesce(dbac.dbac_vldebito,0) - (trunc(( coalesce(dbac.dbac_vldebito,0) /dbac.dbac_nnprestacaodebito ),2) " +
					" * dbac.dbac_nnprestacaocobradas)) "
					+ "   AND fdrc.fdrc_vlfaixafinal >= (coalesce(dbac.dbac_vldebito,0) - (trunc(( coalesce(dbac.dbac_vldebito,0) /dbac.dbac_nnprestacaodebito ),2) " +
					" * dbac.dbac_nnprestacaocobradas))) "	
					
					+ " WHERE loca.loca_id = :idLocalidade "
					+ " and ( ( dbac.dbac_amreferenciacontabil <= :anoMesReferenciaContabil and dbac.dcst_idatual "
					+ " in ( :situacaoNormal, :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( dbac.dbac_amreferenciacontabil > :anoMesReferenciaContabil and dbac.dcst_idatual "
					+ " in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) "
					+ " and dbac.dcst_idanterior is null ) ) "
					
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, (case when imov.iper_id is not null then imov.iper_id else  "+ ImovelPerfil.NORMAL.toString() +"   end), (case when imov.iper_id is not null then imov.iper_id else  "+ ImovelPerfil.NORMAL.toString() +"   end), "
					+ " (case when cltp.epod_id is not null then cltp.epod_id else  "+ EsferaPoder.PARTICULAR.toString()+"   end), idCategoria, idDocumentoTipo, idFaixa  "
					
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idImovelPerfil, idImovelPerfil, "
					+ " idEsferaPoder, idCategoria, idDocumentoTipo, idFaixa  ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("idImovelPerfil", Hibernate.INTEGER)
					.addScalar("idEsferaPoder", Hibernate.INTEGER)
					.addScalar("idCategoria", Hibernate.INTEGER)
					.addScalar("idDocumentoTipo", Hibernate.INTEGER)
					
					.addScalar("quantidadeDocumentos", Hibernate.INTEGER)
					.addScalar("valorCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("idFaixa", Hibernate.INTEGER)
					
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferenciaContabil", anoMesReferenciaContabil)
					
					.setInteger("situacaoNormal",DebitoCreditoSituacao.NORMAL)
					.setInteger("situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada", DebitoCreditoSituacao.RETIFICADA)
					
					.setInteger("situacaoCancelada", DebitoCreditoSituacao.CANCELADA)
					.setInteger("situacaoCanceladaPorRetificacao", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("situacaoDebitoPrescrito", DebitoCreditoSituacao.DEBITO_PRESCRITO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Collection<Object[]> pesquisarCreditosARealizarAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, "//0, 1
					+ " loca.loca_id as idLocalidade, "//2
					+ " (CASE WHEN imov.iper_id IS NOT NULL THEN imov.iper_id ELSE "
					+ ImovelPerfil.NORMAL.toString() + " END) as idImovelPerfil, "//3
					+ " (CASE WHEN cltp.epod_id IS NOT NULL THEN cltp.epod_id ELSE "
					+ EsferaPoder.PARTICULAR.toString() + " END) as idEsferaPoder, "//4
					
					+ " (CASE WHEN (select cacg.catg_id from faturamento.cred_a_realiz_catg cacg "
					+ " where cacg.crar_id = crar.crar_id "
					+ " order by cacg.cacg_qteconomia desc, cacg.catg_id asc limit  1 ) IS NOT NULL "
					+ " THEN (select cacg.catg_id from faturamento.cred_a_realiz_catg cacg "
					+ " where cacg.crar_id = crar.crar_id "
					+ " order by cacg.cacg_qteconomia desc, cacg.catg_id asc limit  1) ELSE "
					+ Categoria.RESIDENCIAL.toString() + " END) as idCategoria, "//5
					
					+ DocumentoTipo.CREDITO_A_REALIZAR.toString() + " as idDocumentoTipo, "//6
					
					+ " count(crar.crar_id) as quantidadeDocumentos, "//7 
					+ " sum( ( ( coalesce(crar.crar_vlcredito,0) - trunc((coalesce(crar.crar_vlcredito,0) /  crar.crar_nnprestacaocredito),2) " 
					+ " * crar.crar_nnprestacaorealizadas ) + coalesce(crar.crar_vlresidualmesanterior,0) )) as valorCategoria, "//8
					+ " fdrc.fdrc_id AS idFaixa " //9
					
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.credito_a_realizar crar on loca.loca_id = crar.loca_id "
					+ " INNER JOIN cadastro.imovel imov on imov.imov_id = crar.imov_id "
					+ " LEFT JOIN cadastro.cliente_imovel clim on imov.imov_id = clim.imov_id and clim.crtp_id = "
					+ ClienteRelacaoTipo.RESPONSAVEL.toString() + " and clim.clim_dtrelacaofim is null "
					+ " LEFT JOIN cadastro.cliente resp on clim.clie_id = resp.clie_id "
					+ " LEFT JOIN cadastro.cliente_tipo cltp on resp.cltp_id = cltp.cltp_id "
					+ " LEFT JOIN financeiro.faixa_docs_a_receber fdrc ON (fdrc.fdrc_vlfaixainicial <= ( ( coalesce(crar.crar_vlcredito,0) - trunc((coalesce(crar.crar_vlcredito,0) /  crar.crar_nnprestacaocredito),2) " 
					+ " * crar.crar_nnprestacaorealizadas ) + coalesce(crar.crar_vlresidualmesanterior,0) ) "
					+ "   AND fdrc.fdrc_vlfaixafinal >= ( ( coalesce(crar.crar_vlcredito,0) - trunc((coalesce(crar.crar_vlcredito,0) /  crar.crar_nnprestacaocredito),2) " 
					+ " * crar.crar_nnprestacaorealizadas ) + coalesce(crar.crar_vlresidualmesanterior,0) )) "
					
					+ " WHERE loca.loca_id = :idLocalidade "
					+ " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil and crar.dcst_idatual "
					+ " in ( :situacaoNormal, :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( crar.crar_amreferenciacontabil > :anoMesReferenciaContabil and crar.dcst_idatual "
					+ " in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) "
					+ " and crar.dcst_idanterior is null ) ) "
					
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, (case when imov.iper_id is not null then imov.iper_id else  "+ ImovelPerfil.NORMAL.toString() +"   end), (case when imov.iper_id is not null then imov.iper_id else  "+ ImovelPerfil.NORMAL.toString() +"   end), "
					+ " (case when cltp.epod_id is not null then cltp.epod_id else  "+ EsferaPoder.PARTICULAR.toString()+"   end), idCategoria, idDocumentoTipo, idFaixa "
					
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idImovelPerfil, idImovelPerfil, "
					+ " idEsferaPoder, idCategoria, idDocumentoTipo, idFaixa ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("idImovelPerfil", Hibernate.INTEGER)
					.addScalar("idEsferaPoder", Hibernate.INTEGER)
					.addScalar("idCategoria", Hibernate.INTEGER)
					.addScalar("idDocumentoTipo", Hibernate.INTEGER)
					
					.addScalar("quantidadeDocumentos", Hibernate.INTEGER)
					.addScalar("valorCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("idFaixa", Hibernate.INTEGER)
					
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferenciaContabil", anoMesReferenciaContabil)
					
					.setInteger("situacaoNormal",DebitoCreditoSituacao.NORMAL)
					.setInteger("situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada", DebitoCreditoSituacao.RETIFICADA)
					
					.setInteger("situacaoCancelada", DebitoCreditoSituacao.CANCELADA)
					.setInteger("situacaoCanceladaPorRetificacao", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("situacaoDebitoPrescrito", DebitoCreditoSituacao.DEBITO_PRESCRITO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
//	*******************************Caern	
	/**
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Arthur Carvalho
	 * @date 15/12/2010
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	@Override
	public Collection pesquisarGerarIntegracaoContabilidadeCaern(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException{
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select  lcor.numeroCartao ,"//0
					   + " lcor.codigoTipo ,"//1
					   + " lcor.numeroFolha ,"//2
					   + " cntc.indicadorLinha ,"//3
					   + " cntc.prefixoContabil ,"//4
					   + " cntc.numeroConta ,"//5
					   + " cntc.numeroDigito ,"//6
					   + " cntc.numeroTerceiros ,"//7
					   + " lcor.codigoReferencia ,"//8
					   + " sum(lcti.valorLancamento) ,"//9
					   + " lcti.indicadorDebitoCredito ,"//10
					   + " lcor.numeroCartao2 ,"//11
					   + " lcor.numeroVersao ,"//12
					   + " CASE WHEN cntc.indicadorCentroCusto = 1 THEN loca.codigoCentroCusto" 
					   + " ELSE null END as col_13_0_, "//13
					   + " cntc.indicadorCentroCusto "//14
					   + " from LancamentoContabilItem lcti " // lançamento contabil item
					   + " left join lcti.lancamentoContabil lcnb" //lançamento contábil
					   + " left join lcnb.localidade loca" //localidade
					   + " left join lcnb.lancamentoOrigem lcor" //lançamento origem
					   + " left join lcti.contaContabil cntc" //conta contabil
					   + " where lcnb.anoMes= :anoMes and lcor.id= :idLancamentoOrigem"
					   + " group by lcti.indicadorDebitoCredito,"
					   + " cntc.numeroConta, cntc.numeroDigito, cntc.numeroTerceiros ,"
					   + " lcor.numeroCartao, lcor.codigoTipo,"
					   + " lcor.numeroFolha, cntc.indicadorLinha, cntc.prefixoContabil ,"
					   + " lcor.codigoReferencia, "
					   + " lcor.numeroCartao2, lcor.numeroVersao, col_13_0_, "
					   + " cntc.indicadorCentroCusto";
			
			retorno = session.createQuery(consulta)
					.setInteger("anoMes",new Integer(anoMes))
					.setInteger("idLancamentoOrigem",new Integer(idLancamentoOrigem))
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
	 * Atualiza com o valor de referência de baixa contábil
	 * das contas baixadas contabilmente no ano/mês de referência 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre, Arthur Carvalho
	 * @date 22/11/2006, 14/06/2007 , 06/12-2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param colecaoIdsContas
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaAnoMesReferenciaContabilDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra ,
			Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();

		PreparedStatement st = null;
	
		try {
			// declara o tipo de conexao
			Connection jdbcCon = session.connection();
		
			
	update = 	"UPDATE faturamento.conta as cnta "
			+	"SET cnta_amreferenciabaixacontabil = ? "
			+	"where cnta.loca_id = ? "
			+   "and cnta.qdra_id = ? "
			+	"and cnta.dcst_idatual in ( ? , ? , ? ) "
			+	"and cnta.cnta_amreferenciaconta < ? "
			+	"and cnta.cnta_amreferenciabaixacontabil is null "
			+	"and exists "    
			+	"(select ctcg.cnta_id "   
			+	"from faturamento.conta_categoria as ctcg "
			+	"inner join cadastro.imovel_cobranca_situacao as icbs on (cnta.imov_id = icbs.imov_id and icbs.iscb_dtretiradacobranca is null) "
			+	"inner join financeiro.param_deved_duvid_item as pdd on  (     (pdd.cbst_id is null or icbs.cbst_id = pdd.cbst_id) "
			+   "and (pdd.pded_id = ?) " 
			+   "and abs((( extract( year from to_date( substring( ? , 1, 4) || substring( ? , 5, 2)  || '-01-', 'YYYY-MM-DD' ) ) - extract( year from to_date( cnta.cnta_dtvencimentoconta, 'YYYY-MM-DD' ) ) ) * 12 ) + "
			+	"( extract( month from to_date(  substring( ? , 1, 4) || substring( ? , 5, 2)  || '-01-', 'YYYY-MM-DD' )  ) ) - extract( month from to_date( cnta.cnta_dtvencimentoconta, 'YYYY-MM-DD' ) ) ) >= pdd.pdit_nnmeses "
	        +   "and    (cnta.cnta_vlagua + cnta.cnta_vlesgoto + cnta.cnta_vldebitos - cnta.cnta_vlcreditos - cnta.cnta_vlimpostos ) <= pdd.pdit_vlvalorlimite "
	        +   " ) "
			+   "where    (cnta.cnta_id = ctcg.cnta_id and ctcg.catg_id in(1,2,3)) "
			+   ") ";

			
			
			st = jdbcCon.prepareStatement(update);
			
			st.setInt(1, anoMesReferenciaContabil);
			st.setInt(2, idLocalidade);
			st.setInt(3, idQuadra);
			st.setInt(4, DebitoCreditoSituacao.NORMAL);
			st.setInt(5, DebitoCreditoSituacao.INCLUIDA);
			st.setInt(6, DebitoCreditoSituacao.RETIFICADA);
			st.setInt(7, anoMesReferenciaContabil);				
			st.setInt(8, idParametrosDevedoresDuvidosos );
			st.setInt(9, anoMesReferenciaContabil );
			st.setInt(10, anoMesReferenciaContabil );
			st.setInt(11, anoMesReferenciaContabil );
			st.setInt(12, anoMesReferenciaContabil );
		
			// executa o update
			st.executeUpdate();
			session.flush();
			
			
			
			
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}


	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorTotalContasDevedoresDuvidosos( int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, String anoMesString, 
			Integer idParametrosDevedoresDuvidosos)
			throws ErroRepositorioException {

		BigDecimal retorno = BigDecimal.ZERO;
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta;
		
		try {

			consulta = "select sum(cnta.cnta_vlagua + cnta.cnta_vlesgoto + cnta.cnta_vldebitos - cnta.cnta_vlcreditos - cnta.cnta_vlimpostos ) as valorContas " 
				+ " from faturamento.conta_categoria as ctcg"
				+ " inner join faturamento.conta as cnta on cnta.cnta_id = ctcg.cnta_id" 
				+ " inner join cadastro.imovel as imov on imov.imov_id = cnta.imov_id "
				+ " where"
				+ " cnta.loca_id = :idLocalidade "
				+ " and cnta.qdra_id = :idQuadra "
				+ " and cnta.cnta_amreferenciabaixacontabil = :anoMesReferencia " 
				+ " and exists ( select ctcg.cnta_id "   
				+      "from faturamento.conta_categoria as ctcg "
				+      "inner join cadastro.imovel_cobranca_situacao as icbs on (cnta.imov_id = icbs.imov_id and icbs.iscb_dtretiradacobranca is null) "
				+      "inner join financeiro.param_deved_duvid_item as pdd on  ( (pdd.cbst_id is null or icbs.cbst_id = pdd.cbst_id) "
				+      "and (pdd.pded_id = :idParametrosDevedoresDuvidosos) " 
				+      "and abs((( extract( year from to_date( substring(:anoMesReferencia , 1, 4) || substring(:anoMesReferencia , 5, 2)  || '-01-', 'YYYY-MM-DD' ) ) - extract( year from to_date( cnta.cnta_dtvencimentoconta, 'YYYY-MM-DD' ) ) ) * 12 ) + "
				+	   "( extract( month from to_date(  substring(:anoMesReferencia , 1, 4) || substring(:anoMesReferencia , 5, 2)  || '-01-', 'YYYY-MM-DD' )  ) ) - extract( month from to_date( cnta.cnta_dtvencimentoconta, 'YYYY-MM-DD' ) ) ) >= pdd.pdit_nnmeses "
		        +      "and    (cnta.cnta_vlagua + cnta.cnta_vlesgoto + cnta.cnta_vldebitos - cnta.cnta_vlcreditos - cnta.cnta_vlimpostos ) <= pdd.pdit_vlvalorlimite "
		        +      " ) "
				+      "where (cnta.cnta_id = ctcg.cnta_id and ctcg.catg_id in(1,2,3) ) "
				+      " ) ";
			
			
				retorno = (BigDecimal) session.createSQLQuery(consulta)
						.addScalar("valorContas", Hibernate.BIG_DECIMAL)
						.setInteger("idLocalidade", idLocalidade)
						.setInteger("anoMesReferencia", anoMesReferenciaContabil)
						.setInteger("idParametrosDevedoresDuvidosos", idParametrosDevedoresDuvidosos)
						.setInteger("idQuadra", idQuadra)
						.uniqueResult();
						
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = AGUA
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos) throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = "select sum(ctcg.ctcg_vlagua) as valorAgua, "
		    + "            categoria.catg_id as categoria " 
			+ " from faturamento.conta_categoria as ctcg"
			+ " inner join faturamento.conta as cnta on cnta.cnta_id = ctcg.cnta_id" 
			+ " inner join cadastro.categoria as categoria on categoria.catg_id = ctcg.catg_id"
			+ " inner join cadastro.imovel as imov on imov.imov_id = cnta.imov_id "
			+ " where"
			+ " cnta.loca_id = :idLocalidade "
			+ " and cnta.qdra_id = :idQuadra "
			+ " and cnta.cnta_amreferenciabaixacontabil = :anoMesReferencia "
			+ " group by categoria.catg_id ";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorAgua",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = ESGOTO
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException{
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = "select sum(ctcg.ctcg_vlesgoto) as valorEsgoto, "
		    + "            categoria.catg_id as categoria " 
			+ " from faturamento.conta_categoria as ctcg"
			+ " inner join faturamento.conta as cnta on cnta.cnta_id = ctcg.cnta_id" 
			+ " inner join cadastro.categoria as categoria on categoria.catg_id = ctcg.catg_id"
			+ " inner join cadastro.imovel as imov on imov.imov_id = cnta.imov_id "
			+ " where"
			+ " cnta.loca_id = :idLocalidade "
			+ " and cnta.qdra_id = :idQuadra "
			+ " and cnta.cnta_amreferenciabaixacontabil = :anoMesReferencia " 
			+ " group by categoria.catg_id ";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorEsgoto",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idQuadra", idQuadra)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
			
		
		return retorno;
	}
	
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (agua)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = " select   sum(dccg.dccg_vlcategoria) as valorCategoria," 
		         + " dccg.catg_id as categoria"
				 + " from  faturamento.conta as cnta " 
				 + " inner join faturamento.debito_cobrado as dbcb on dbcb.cnta_id = cnta.cnta_id"
				 + " inner join faturamento.debito_cobrado_categoria as dccg on dccg.dbcb_id = dbcb.dbcb_id"
				 + " where " 
				 + " cnta.loca_id = :idLocalidade"
				 + " and cnta.qdra_id = :idQuadra"
				 + " and cnta.cnta_amreferenciabaixacontabil = :anoMesReferencia " 
				 + " and dbcb.fntp_id = :parcelamentoAgua "		

				 + " group by dccg.catg_id";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelamentoAgua" , FinanciamentoTipo.PARCELAMENTO_AGUA)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
		
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (esgoto)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos )  throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = " select   sum(dccg.dccg_vlcategoria) as valorCategoria," 
		         + " 		  dccg.catg_id as categoria"
				 + " from faturamento.conta as cnta " 
				 + "  inner join faturamento.debito_cobrado as dbcb on dbcb.cnta_id = cnta.cnta_id"
				 + "  inner join faturamento.debito_cobrado_categoria as dccg on dccg.dbcb_id = dbcb.dbcb_id"
				 + " where " 
				 + "  cnta.loca_id = :idLocalidade"
				 + "  and cnta.qdra_id = :idQuadra"
				 + "  and cnta.cnta_amreferenciabaixacontabil = :anoMesReferencia " 
				 + "  and dbcb.fntp_id = :parcelamentoEsgoto"	

				 + " group by dccg.catg_id";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelamentoEsgoto" , FinanciamentoTipo.PARCELAMENTO_ESGOTO)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (grupo contabil)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta= " select   sum(dccg.dccg_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao, "//1
				+ " 		  lict.lict_id as financiamentoTipo, "//2
				+ " 		  dccg.catg_id as categoria "//3
				+ " from faturamento.conta as cnta "
				+ "  inner join faturamento.debito_cobrado as dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ "  inner join faturamento.debito_cobrado_categoria as dccg on dccg.dbcb_id = dbcb.dbcb_id"
				+ "  inner join financeiro.lancamento_item_contabil as lict on dbcb.lict_id = lict.lict_id"
				+ " where "
				+ "  cnta.loca_id = :idLocalidade"
				+ "  and cnta.qdra_id = :idQuadra"
				+ "  and cnta.cnta_amreferenciabaixacontabil = :anoMesReferencia  "
				+ "  and dbcb.fntp_id in( :parcelamentoServico , :entradaParcelamento) "		
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , dccg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelamentoServico", FinanciamentoTipo.PARCELAMENTO_SERVICO)
					.setInteger("entradaParcelamento", FinanciamentoTipo.ENTRADA_PARCELAMENTO)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (juros)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos )  throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta= " select   sum(dccg.dccg_vlcategoria) as valorCategoria,"
				+ "          dccg.catg_id as categoria"
				+ " from faturamento.conta as cnta "
				+ "  inner join faturamento.debito_cobrado as dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ "  inner join faturamento.debito_cobrado_categoria as dccg on dccg.dbcb_id = dbcb.dbcb_id"
				+ " where "
				+ "  cnta.loca_id = :idLocalidade"
				+ "  and cnta.qdra_id = :idQuadra"
				+ "  and cnta.cnta_amreferenciabaixacontabil = :anoMesReferencia  "
				+ "  and dbcb.fntp_id = :jurosParcelamento  "
				+ "  group by dccg.catg_id ";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("jurosParcelamento" , FinanciamentoTipo.JUROS_PARCELAMENTO)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = FINANCIAMENTOS COBRADOS 
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta= " select   sum(dccg.dccg_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao,"//1
				+ " 		  lict.lict_id as financiamentoTipo,"//2
				+ " 		  dccg.catg_id as categoria"//3
				+ " from faturamento.conta as cnta  "
				+ "  inner join faturamento.debito_cobrado as dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ "  inner join faturamento.debito_cobrado_categoria as dccg on dccg.dbcb_id = dbcb.dbcb_id"
				+ "  inner join financeiro.lancamento_item_contabil as lict on dbcb.lict_id = lict.lict_id"
				+ " where "
				+ "  cnta.loca_id = :idLocalidade"
				+ "  and cnta.qdra_id = :idQuadra"
				+ "  and cnta.cnta_amreferenciabaixacontabil = :anoMesReferencia "
				+ "  and dbcb.fntp_id  in ( :servicoNormal, :arrastoAgua, :arrastoEsgoto, :arrastoServico) "
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , dccg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("servicoNormal" , FinanciamentoTipo.SERVICO_NORMAL)
					.setInteger("arrastoAgua" , FinanciamentoTipo.ARRASTO_AGUA)
					.setInteger("arrastoEsgoto" , FinanciamentoTipo.ARRASTO_ESGOTO)
					.setInteger("arrastoServico" , FinanciamentoTipo.ARRASTO_SERVICO)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta= " select   sum(crcg.crcg_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao,"//1
				+ " 		  lict.lict_id as financiamentoTipo,"//2
				+ " 		  crcg.catg_id as categoria"//3
				+ " from faturamento.cred_realizado_catg as crcg"
				+ "  inner join faturamento.credito_realizado as crrz on crrz.crrz_id = crcg.crrz_id"
				+ "  inner join faturamento.conta as cnta on cnta.cnta_id = crrz.cnta_id"
				+ "  inner join financeiro.lancamento_item_contabil as lict on crrz.lict_id = lict.lict_id"
				+ "  inner join financeiro.lancamento_item as lcit on lict.lcit_id = lcit.lcit_id" 								
				+ " where "
				+ "  cnta.loca_id = :idLocalidade"
				+ "  and cnta.qdra_id = :idQuadra"
				+ "  and cnta.cnta_amreferenciabaixacontabil = :anoMesReferencia "	
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , crcg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idQuadra", idQuadra)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
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
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Arthur Carvalho
	 * @date 16/09/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorBaixadoParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil, BigDecimal valorTotalValoresBaixados) throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();

		PreparedStatement st = null;
	
		try {
			// declara o tipo de conexao
			Connection jdbcCon = session.connection();
		
			update = " update financeiro.param_deved_duvid "
					+ "	set pded_vlbaixado = " + valorTotalValoresBaixados + "  + (select p.pded_vlbaixado from financeiro.param_deved_duvid as p where p.pded_amreferenciacontabil = "+ anoMesReferenciaContabil+ " ) "	
					+ "WHERE pded_amreferenciacontabil = " + anoMesReferenciaContabil +" ";
		
			st = jdbcCon.prepareStatement(update);
			
			// executa o update
			st.executeUpdate();
			session.flush();
	
	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		
	}
}
