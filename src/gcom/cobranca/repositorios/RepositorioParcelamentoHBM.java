package gcom.cobranca.repositorios;

import gcom.cadastro.imovel.Imovel;
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
	
	public CancelarParcelamentoDTO pesquisarParcelamentoParaCancelar(Integer idParcelamento) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		CancelarParcelamentoDTO retorno = null;
		
		try {
			String complementoConsulta = "WHERE  p.parc_id = :idParcelamento GROUP BY p.parc_id, c.imov_id "; 
			StringBuilder consulta = new StringBuilder();
			consulta.append(montarRaizConsulta(complementoConsulta));
			
			Object[] dados = (Object[]) session.createSQLQuery(consulta.toString())
					.addScalar("idParcelamento", Hibernate.INTEGER)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("valorOriginal", Hibernate.BIG_DECIMAL)
					.addScalar("valorEntrada", Hibernate.BIG_DECIMAL)
					.addScalar("valorPrestacao", Hibernate.BIG_DECIMAL)
					.addScalar("numeroPrestacoesCobradas", Hibernate.INTEGER)
					.setInteger("idParcelamento", idParcelamento)
					.uniqueResult();
			
			if (dados != null && dados.length > 0){
				Parcelamento parcelamento = pesquisarPorId((Integer) dados[0]);
				Imovel imovel = pesquisarImovelPorId((Integer) dados[1]);
				retorno = new CancelarParcelamentoDTO(parcelamento, imovel, dados);
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
		
	@SuppressWarnings("unchecked")
	public List<CancelarParcelamentoDTO> pesquisarParcelamentosParaCancelar() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<CancelarParcelamentoDTO> dtos = new ArrayList<CancelarParcelamentoDTO>();

		try {
			StringBuilder complementoConsulta = new StringBuilder();
			complementoConsulta.append("INNER JOIN cadastro.imovel i on i.imov_id = p.imov_id ")
								.append("WHERE c.cnta_dtvencimentoconta < :dataAtual ")
								.append("      AND c.dcst_idatual IN (:normal, :retificada, :incluida) ")
								.append("      AND NOT EXISTS (SELECT cnta_id from arrecadacao.pagamento pg WHERE pg.cnta_id = c.cnta_id) ")
								.append("      AND (i.imov_nnreparcelamento IS NULL OR i.imov_nnreparcelamento <= 0) ")
								.append("      AND parc_amreferenciafaturamento = 201702 ")
								.append("GROUP BY p.parc_id, c.imov_id ")
								.append("HAVING count(distinct c.cnta_id) >= :qtdContas");
			
			String consulta = montarRaizConsulta(complementoConsulta.toString());

			List<Object[]> lista = session.createSQLQuery(consulta)
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
					.setInteger("qtdContas", ConstantesSistema.QTD_CONTAS_CANCELAMENTO_PARCELAMENTO).list();

			for (Object[] dados : lista) {
				Parcelamento parcelamento = pesquisarPorId((Integer) dados[0]);
				Imovel imovel = pesquisarImovelPorId((Integer) dados[1]);
				dtos.add(new CancelarParcelamentoDTO(parcelamento, imovel, dados));
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return dtos;
	}
	
	private String montarRaizConsulta(String complementoConsulta) {
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT p.parc_id as idParcelamento, ")
				.append("       c.imov_id as idImovel, ")
				.append("       p.parc_vldebitoatualizado as valorOriginal, ")
				.append("       p.parc_vlentrada as valorEntrada, ")
				.append("       p.parc_vlprestacao as valorPrestacao, ")
				.append("       count(distinct c.cnta_id) as numeroPrestacoesCobradas ")
				.append("FROM faturamento.conta c ")
				.append("INNER JOIN faturamento.debito_cobrado dc on dc.cnta_id = c.cnta_id ")
				.append("INNER JOIN faturamento.debito_a_cobrar dac on dac.dbac_id = dc.dbac_id ")
				.append("INNER JOIN cobranca.parcelamento p on p.parc_id = dac.parc_id ")
				.append(complementoConsulta);
		
		consulta.append(" UNION ALL ");
		
		consulta.append("SELECT p.parc_id as idParcelamento, ")
				.append("       c.imov_id as idImovel, ")
				.append("       p.parc_vldebitoatualizado as valorOriginal, ")
				.append("       p.parc_vlentrada as valorEntrada, ")
				.append("       p.parc_vlprestacao as valorPrestacao, ")
				.append("       count(distinct c.cnta_id) as numeroPrestacoesCobradas ")
				.append("FROM faturamento.conta_historico c ")
				.append("INNER JOIN faturamento.debito_cobrado_historico dc on dc.cnta_id = c.cnta_id ")
				.append("INNER JOIN faturamento.deb_a_cobrar_hist dac on dac.dbac_id = dc.dbac_id ")
				.append("INNER JOIN cobranca.parcelamento p on p.parc_id = dac.parc_id ")
				.append(complementoConsulta);
		
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
}