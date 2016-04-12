package gcom.faturamento.repositorio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import gcom.faturamento.credito.CreditoARealizar;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

public class CreditoARealizarRepositorio {
    
    private static CreditoARealizarRepositorio instance = null;
    
    private CreditoARealizarRepositorio() {}
    
    public static CreditoARealizarRepositorio getInstance(){
        if (instance == null){
            instance = new CreditoARealizarRepositorio();
        }
        
        return instance;
    }

	@SuppressWarnings("unchecked")
    public Collection<CreditoARealizar> buscarCreditoRealizarPorImovel(Integer imovelId, Integer debitoCreditoSituacaoAtual, int anoMesFaturamento) throws ErroRepositorioException{
	    
	    Session session = HibernateUtil.getSession();
	    
		StringBuilder consulta = new StringBuilder();
		
		Collection<CreditoARealizar> retorno = new ArrayList<CreditoARealizar>();
		
		try {
		    consulta.append(" select crar ")
		    .append(" from CreditoARealizar as crar ")
		    .append(" inner join crar.imovel as imov ")
		    .append(" inner join crar.quadra ")
		    .append(" inner join crar.localidade ")
		    .append(" inner join crar.creditoTipo ")
		    .append(" inner join crar.lancamentoItemContabil ")
		    .append(" inner join crar.creditoOrigem ")
		    .append(" inner join crar.creditoARealizarGeral ")
		    .append(" left outer join crar.parcelamento parc ")
		    .append(" where  imov.id = :imovelId ")
		    .append("  and crar.debitoCreditoSituacaoAtual = :debitoCreditoSituacaoAtualId ")
		    .append("  and (crar.numeroPrestacaoRealizada < ")
		    .append("      (crar.numeroPrestacaoCredito - coalesce(crar.numeroParcelaBonus, 0)) ")
		    .append("      or crar.valorResidualMesAnterior > 0) ")
		    .append("  and (parc.id is null or crar.numeroPrestacaoRealizada > 0 or (parc.id is not null ")
		    .append("       and crar.numeroPrestacaoRealizada = 0 and parc.anoMesReferenciaFaturamento < :anoMesFaturamento) ) ");
		    
		    retorno = (Collection<CreditoARealizar>) session.createQuery(consulta.toString())
		            .setParameter("imovelId", imovelId)
		            .setParameter("debitoCreditoSituacaoAtualId", debitoCreditoSituacaoAtual)
		            .setParameter("anoMesFaturamento", anoMesFaturamento)
		            .list();
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        } finally{
		    session.close();
		}
		
		return retorno;
	}
	
	public void atualizarParcelas(Integer referencia, List<Integer> idsImoveis) throws ErroRepositorioException{
	    Session session = HibernateUtil.getSession();

		StringBuilder sql = new StringBuilder();
		sql.append("update faturamento.credito_a_realizar ")
		.append(" set crar_nnprestacaorealizadas = crar_nnprestacaorealizadas - 1, ")
		.append(" crar_tmultimaalteracao = :data ")
		.append(" where crar_amreferenciaprestacao >= :referencia")
		.append(" and crar_nnprestacaorealizadas > 0 ")
		.append(" and (crar_nnprestacaocredito > 1 or (crar_nnprestacaocredito = 1 and crar_vlresidualmesanterior = 0.00)) ")
		.append(" and imov_id in (:ids)");
		
		try {
		    session.createSQLQuery(sql.toString())
		    .setParameter("referencia", referencia)
		    .setParameter("data", new Timestamp(new Date().getTime()))
		    .setParameter("ids", idsImoveis)
		    .executeUpdate();
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        } finally{
            session.close();
        }
	}
	
	public void atualizarValorResidual(List<Integer> idsImoveis) throws ErroRepositorioException{
	    Session session = HibernateUtil.getSession();
	    
		StringBuilder sql = new StringBuilder();
		sql.append("update faturamento.credito_a_realizar set ")
		.append(" crar_vlresidualmesanterior = coalesce(crar_vlresidualconcedidomes, 0) , ")
		.append(" crar_vlresidualconcedidomes = null, ")
		.append(" crar_tmultimaalteracao = :data ")
		.append(" where crar_nnprestacaorealizadas > 0")
		.append(" and imov_id in (:ids)");
		
		try {
		    session.createSQLQuery(sql.toString())
		    .setParameter("data", new Timestamp(new Date().getTime()))
		    .setParameter("ids", idsImoveis)
		    .executeUpdate();
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        } finally{
            session.close();
        }
	}	
}