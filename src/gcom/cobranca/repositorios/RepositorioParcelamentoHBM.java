package gcom.cobranca.repositorios;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> pesquisarParcelamentosParaCancelar() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<Object[]> retorno = null;
		
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT distinct parc_id as idParcelamento, sum(valorPrestacao) as saldoDevedor ")
					.append("FROM ( ")
					.append("SELECT distinct p.parc_id, c.cnta_id, sum(dc.dbcb_vlprestacao) as valorPrestacao ")
					.append("FROM faturamento.conta c ")
					.append("INNER JOIN faturamento.debito_cobrado dc on dc.cnta_id = c.cnta_id ")
					.append("INNER JOIN faturamento.debito_a_cobrar dac on dac.dbac_id = dc.dbac_id ")
					.append("INNER JOIN cobranca.parcelamento p on p.parc_id = dac.parc_id ")
					.append("WHERE c.cnta_dtvencimentoconta < :dataAtual ")
					.append("      and c.cnta_id NOT IN (SELECT cnta_id FROM arrecadacao.pagamento pg WHERE pg.cnta_id = c.cnta_id) ")
					.append("GROUP BY p.parc_id, c.cnta_id HAVING count(c.cnta_id) >= :qtdContas ")
					.append(") as parcelamentos ")
					.append("GROUP BY parc_id ");
			
			retorno = session.createSQLQuery(consulta.toString())
					.addScalar("idParcelamento", Hibernate.INTEGER)
					.addScalar("saldoDevedor", Hibernate.BIG_DECIMAL)
					.setDate("dataAtual", new Date())
					.setInteger("qtdContas", 3)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
}
