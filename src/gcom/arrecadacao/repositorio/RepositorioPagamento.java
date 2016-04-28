package gcom.arrecadacao.repositorio;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import gcom.faturamento.debito.DebitoACobrar;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

public class RepositorioPagamento{
    
    private static RepositorioPagamento instance;
    
    private RepositorioPagamento() {}

    public static RepositorioPagamento getInstancia() {
        if (instance == null){
            instance = new RepositorioPagamento();
        }
        
        return instance;
    }
    
	public boolean debitoSemPagamento(Integer idDebito) throws ErroRepositorioException{
	    Session session = HibernateUtil.getSession();
	    
		StringBuilder sql = new StringBuilder();
		sql.append("select count(pg) from Pagamento pg")
		.append(" where pg.debitoACobrarGeral.id = :idDebito");
		
		int count = 0;
		
		try {
		    count = (Integer) session.createQuery(sql.toString())
		            .setParameter("idDebito", idDebito)
		            .uniqueResult();
            
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        } finally{
            session.close();
        }

		return (count == 0) ? true : false;
	}

	public boolean guiaPaga(Integer idGuia) throws ErroRepositorioException{
	    Session session = HibernateUtil.getSession();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(pg) from Pagamento pg")
		.append(" where pg.guiaPagamento.id = :idGuia");
		
		int count = 0;
		try {
		    
		    count = (Integer) session.createQuery(sql.toString())
		            .setParameter("idGuia", idGuia)
		            .uniqueResult();
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        }finally{
            session.close();
        }

		return count == 0 ? false: true;
	}
	
	public boolean contaPaga(Integer idConta) throws ErroRepositorioException{
	    Session session = HibernateUtil.getSession();
	    
		StringBuilder sql = new StringBuilder();
		sql.append("select count(pg) from Pagamento pg")
		.append(" where pg.contaGeral.id = :idConta");
		
		int count = 0;
		try {
		    count = (Integer) session.createQuery(sql.toString())
		            .setParameter("idConta", idConta)
		            .uniqueResult();
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        }finally{
            session.close();
        }
		
		return count == 0 ? false: true;
	}
	
	public boolean existeDebitoSemPagamento(Collection<DebitoACobrar> debitosCobrar) throws ErroRepositorioException{
		boolean existeDebitoSemPagamento = false;
		
		for (DebitoACobrar debitoCobrar : debitosCobrar) {
			if (debitoSemPagamento(debitoCobrar.getId())){
				existeDebitoSemPagamento = true;
				break;
			}
		}
		
		return existeDebitoSemPagamento;
	}

	public void apagarPagamentosDasConta(List<Integer> ids) throws ErroRepositorioException{
	    Session session = HibernateUtil.getSession();
	    
		String delete = "update arrecadacao.pagamento set cnta_id = null where cnta_id in (:ids)";
		
		try {
		    session.createSQLQuery(delete)
		    .setParameter("ids", ids)
		    .executeUpdate();
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        }finally{
            session.close();
        }
	}	
}
