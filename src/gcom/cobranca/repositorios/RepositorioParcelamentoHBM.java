package gcom.cobranca.repositorios;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.bean.CancelarParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class RepositorioParcelamentoHBM implements IRepositorioParcelamentoHBM {

	private static RepositorioParcelamentoHBM instancia;

	protected RepositorioParcelamentoHBM() {
	}

	public static RepositorioParcelamentoHBM getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioParcelamentoHBM();
		}
		return instancia;
	}
	
	public CancelarParcelamentoHelper pesquisarParcelamentoParaCancelar(Integer idParcelamento) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		CancelarParcelamentoHelper retorno = null;
		
		try {
			String complemento = "WHERE  p.parc_id = :idParcelamento GROUP BY p.parc_id, p.imov_id "; 
			StringBuilder consulta = new StringBuilder();
			consulta.append(montarRaizConsulta(complemento));
			
			Query query = criarQuery(session, consulta.toString());
			Object[] dados = (Object[]) query.setInteger("idParcelamento", idParcelamento).uniqueResult();
			
			if (dados != null && dados.length > 0){
				Parcelamento parcelamento = pesquisarPorId((Integer) dados[0]);
				Imovel imovel = pesquisarImovelPorId((Integer) dados[1]);
				retorno = new CancelarParcelamentoHelper(parcelamento, imovel, dados);
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
		
	@SuppressWarnings("unchecked")
	public List<CancelarParcelamentoHelper> pesquisarParcelamentosParaCancelar() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<CancelarParcelamentoHelper> helper = new ArrayList<CancelarParcelamentoHelper>();

		try {
			StringBuilder complemento = new StringBuilder();
			complemento.append("INNER JOIN cadastro.imovel i on i.imov_id = p.imov_id ")
					   .append("WHERE c.cnta_dtvencimentoconta < :dataAtual ")
					   .append("      AND c.dcst_idatual IN (:normal, :retificada, :incluida) ")
					   .append("      AND NOT EXISTS (SELECT cnta_id from arrecadacao.pagamento pg WHERE pg.cnta_id = c.cnta_id) ")
					   .append("      AND (i.imov_nnreparcelamento IS NULL OR i.imov_nnreparcelamento <= 0) ")
					   .append("GROUP BY p.parc_id, p.imov_id ")
					   .append("HAVING count(distinct c.cnta_id) >= :qtdContas");
			
			String consulta = montarRaizConsulta(complemento.toString());
			
			Query query = criarQuery(session, consulta.toString());
			List<Object[]> lista = query.setDate("dataAtual", new Date())
										.setInteger("normal", DebitoCreditoSituacao.NORMAL)
										.setInteger("retificada", DebitoCreditoSituacao.RETIFICADA)
										.setInteger("incluida", DebitoCreditoSituacao.INCLUIDA)
										.setInteger("qtdContas", ConstantesSistema.QTD_CONTAS_CANCELAMENTO_PARCELAMENTO)
										.list();

			for (Object[] dados : lista) {
				Parcelamento parcelamento = pesquisarPorId((Integer) dados[0]);
				Imovel imovel = pesquisarImovelPorId((Integer) dados[1]);
				helper.add(new CancelarParcelamentoHelper(parcelamento, imovel, dados));
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return helper;
	}
	
	private String montarRaizConsulta(String complemento) {
		StringBuilder select = new StringBuilder();
		select.append("SELECT p.parc_id as idParcelamento, ")
			  .append("       p.imov_id as idImovel, ")
			  .append("       p.parc_vlconta as valorContas, ")
			  .append("       p.parc_vlentrada as valorEntrada, ")
			  .append("       p.parc_vljurosmora + p.parc_vlmulta + p.parc_vlatualizacaomonetaria as valorAcrescimos, ")
			  .append("       p.parc_vldescontoacrescimos as valorDescontoAcrescimos, ")
			  .append("       p.parc_vldescontofaixa as valorDescontoFaixa, ")
			  .append("       p.parc_nnprestacoes as numeroPrestacoes, ")
			  .append("       count(distinct c.cnta_id) as numeroPrestacoesCobradas ")
			  .append("FROM cobranca.parcelamento p ");
		
		StringBuilder join = new StringBuilder();
		join.append("INNER JOIN faturamento.debito_a_cobrar dac on dac.parc_id = p.parc_id  ")
			.append("LEFT JOIN faturamento.debito_cobrado dc on dc.dbac_id = dac.dbac_id  ")
			.append("LEFT JOIN faturamento.conta c on c.cnta_id = dc.cnta_id ")
			.append("INNER JOIN faturamento.guia_pagamento guia on guia.parc_id = p.parc_id ");
		
		StringBuilder joinHistorico = new StringBuilder();
		joinHistorico.append("INNER JOIN faturamento.deb_a_cobrar_hist dac on dac.parc_id = p.parc_id  ")
					 .append("LEFT JOIN faturamento.debito_cobrado_historico dc on dc.dbac_id = dac.dbac_id  ")
					 .append("LEFT JOIN faturamento.conta_historico c on c.cnta_id = dc.cnta_id ")
					 .append("INNER JOIN faturamento.guia_pagamento_historico guia on guia.parc_id = p.parc_id ");
		
		StringBuilder consulta = new StringBuilder();
		consulta.append(select)
		        .append(join)
		        .append(complemento)
		        .append(" UNION ALL ")
		        .append(select)
		        .append(joinHistorico)
		        .append(complemento);
		
		return consulta.toString();
	}
	
	private Parcelamento pesquisarPorId(Integer id) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Parcelamento parcelamento = null;

		try {
			parcelamento = (Parcelamento) session.get(Parcelamento.class, id);
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return parcelamento;
	}
	
	private Imovel pesquisarImovelPorId(Integer id) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Imovel imovel = null;

		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT i FROM Imovel i ")
					.append("INNER JOIN FETCH i.quadra ")
					.append("INNER JOIN FETCH i.setorComercial ")
					.append("LEFT JOIN FETCH i.ligacaoEsgoto ")
					.append("WHERE i.id = :id");
			
			imovel = (Imovel) session.createQuery(consulta.toString()).setInteger("id", id).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return imovel;
	}
	
	private Query criarQuery(Session session, String consulta) {
		return session.createSQLQuery(consulta.toString())
					  .addScalar("idParcelamento", Hibernate.INTEGER)
					  .addScalar("idImovel", Hibernate.INTEGER)
					  .addScalar("valorContas", Hibernate.BIG_DECIMAL)
					  .addScalar("valorEntrada", Hibernate.BIG_DECIMAL)
					  .addScalar("valorAcrescimos", Hibernate.BIG_DECIMAL)
					  .addScalar("valorDescontoAcrescimos", Hibernate.BIG_DECIMAL)
					  .addScalar("valorDescontoFaixa", Hibernate.BIG_DECIMAL)
					  .addScalar("numeroPrestacoes", Hibernate.SHORT)
					  .addScalar("numeroPrestacoesCobradas", Hibernate.SHORT);
	}
}