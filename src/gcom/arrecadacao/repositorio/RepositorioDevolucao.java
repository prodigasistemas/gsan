package gcom.arrecadacao.repositorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import gcom.arrecadacao.Devolucao;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

public class RepositorioDevolucao {
    
    private static RepositorioDevolucao instance;
    
    private RepositorioDevolucao(){}
    
    public static RepositorioDevolucao getInstance(){
        if (instance == null){
            instance = new RepositorioDevolucao();
        }
        
        return instance;
    }

	public boolean existeCreditoComDevolucao(Collection<CreditoARealizar> creditosRealizar) throws ErroRepositorioException{
		return !buscarDevolucaoPorCreditoRealizar(creditosRealizar).isEmpty();
	}
	
	@SuppressWarnings("unchecked")
    public Collection<Devolucao> buscarDevolucaoPorCreditoRealizar(Collection<CreditoARealizar> creditosARealizar) throws ErroRepositorioException{
	    
	    Collection<Devolucao> resultado = new ArrayList<Devolucao>();
	    
	    Session session = HibernateUtil.getSession();
	    
		List<Integer> ids = new ArrayList<Integer>();
		
		for (CreditoARealizar  credito : creditosARealizar) {
            ids.add(credito.getId());
        }
		
		try {
		    resultado = (Collection<Devolucao>) session.createQuery("select devolucao from Devolucao as devolucao inner join devolucao.creditoRealizar crar "
		            + "where crar.id in (:ids)")
		            .setParameterList("ids", ids)
		            .list();
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        }finally{
            session.close();
        }

		return resultado;
	}
}
