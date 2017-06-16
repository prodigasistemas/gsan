package gcom.cobranca.repositorios;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.repositorios.dto.CancelarParcelamentoDTO;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.ArrayList;
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
	
	public Parcelamento pesquisarPorId(Integer id) throws ErroRepositorioException {
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
	
	@SuppressWarnings("unchecked")
	public List<CancelarParcelamentoDTO> pesquisarParcelamentosParaCancelar() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<CancelarParcelamentoDTO> dtos = new ArrayList<CancelarParcelamentoDTO>();
		
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT parc_id as idParcelamento, ")
					.append("       c.imov_id as idImovel, ")
					.append("       parc_vldebitoatualizado as valorOriginal, ")
					.append("       parc_vlentrada as valorEntrada, ")
					.append("       parc_vlprestacao as valorPrestacao, ")
					.append("       count(distinct c.cnta_id) as numeroPrestacoesCobradas ")
					.append("FROM faturamento.conta c ")
					.append("INNER JOIN faturamento.debito_cobrado dc on dc.cnta_id = c.cnta_id ")
					.append("INNER JOIN faturamento.debito_a_cobrar dac on dac.dbac_id = dc.dbac_id ")
					.append("INNER JOIN cobranca.parcelamento p on p.parc_id = dac.parc_id ")
					.append("WHERE c.cnta_dtvencimentoconta < :dataAtual ")
					.append("      AND c.dcst_idatual IN (:normal, :retificada, :incluida) ")
					.append("      AND NOT EXISTS (SELECT cnta_id from arrecadacao.pagamento pg WHERE pg.cnta_id = c.cnta_id) ")
					.append("GROUP BY p.parc_id, c.imov_id ")
					.append("HAVING count(distinct c.cnta_id) >= :qtdContas");
			
			List<Object[]> lista = session.createSQLQuery(consulta.toString())
					.addScalar("idParcelamento", Hibernate.INTEGER)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("valorOriginal", Hibernate.BIG_DECIMAL)
					.addScalar("valorEntrada", Hibernate.BIG_DECIMAL)
					.addScalar("valorPrestacao", Hibernate.BIG_DECIMAL)
					.addScalar("numeroPrestacoesCobradas", Hibernate.INTEGER)
					.setDate("dataAtual", new Date())
					.setInteger("normal", DebitoCreditoSituacao.NORMAL)
					.setInteger("retificada", DebitoCreditoSituacao.RETIFICADA)
					.setInteger("incluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("qtdContas", ConstantesSistema.QTD_CONTAS_CANCELAMENTO_PARCELAMENTO)
					.list();
			
			for (Object[] dto : lista) {
				dtos.add(new CancelarParcelamentoDTO(dto));
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return dtos;
	}
}