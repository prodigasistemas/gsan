package gcom.faturamento.repositorio;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;

import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

public class FaturamentoSituacaoTipoRepositorio {
    
    private static FaturamentoSituacaoTipoRepositorio instance;
    
    private FaturamentoSituacaoTipoRepositorio(){}
    
    public static FaturamentoSituacaoTipoRepositorio getInstance(){
        if (instance == null){
            instance = new FaturamentoSituacaoTipoRepositorio();
        }
        
        return instance;
    }
    

	public FaturamentoSituacaoTipo situacaoTipoDoImovel(Integer idImovel) throws ErroRepositorioException{
	    Session session = HibernateUtil.getSession();
	    
		StringBuilder sql = new StringBuilder();
		sql.append(" select tipo ")
		   .append(" from Imovel imovel ")
		   .append(" inner join imovel.faturamentoSituacaoTipo tipo ")
		   .append(" where imovel.id = :idImovel ");

		try {
			return (FaturamentoSituacaoTipo) session.createQuery(sql.toString())
					.setParameter("idImovel", idImovel)
					.setMaxResults(1)
					.uniqueResult();
		} catch (NonUniqueResultException e) {
			return null;
		}catch (Exception e){
		    throw new ErroRepositorioException(e);
		}
	}
}
