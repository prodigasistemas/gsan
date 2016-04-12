package gcom.faturamento.repositorio;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;

import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

public class FaturamentoSituacaoRepositorio {
    
    private static FaturamentoSituacaoRepositorio instance;
    
    private FaturamentoSituacaoRepositorio(){}
    
    public static FaturamentoSituacaoRepositorio getInstance(){
        if (instance == null){
            instance = new FaturamentoSituacaoRepositorio();
        }
        
        return instance;
    }
	
	public Collection<FaturamentoSituacaoHistorico> faturamentosHistoricoVigentesPorImovel(Integer imovelId) throws ErroRepositorioException{
		Collection<FaturamentoSituacaoHistorico> retorno = situacoesEspeciaisFaturamentoVigentes(imovelId);
		
		if(retorno == null || retorno.isEmpty()) {
			return new ArrayList<FaturamentoSituacaoHistorico>();
		} else {
			return retorno;
		}
	}
	
	@SuppressWarnings("unchecked")
    public Collection<FaturamentoSituacaoHistorico> situacoesEspeciaisFaturamentoVigentes(Integer idImovel) throws ErroRepositorioException{
	    Session session = HibernateUtil.getSession();

		StringBuilder sql = new StringBuilder();
		sql.append(" select t from FaturamentoSituacaoHistorico as t" )
		.append(" where t.imovel.id = :idImovel ")
		.append(" and t.anoMesFaturamentoRetirada is null" );
		
		Collection<FaturamentoSituacaoHistorico> retorno = new ArrayList<FaturamentoSituacaoHistorico>();
		
		try {
            retorno = (Collection<FaturamentoSituacaoHistorico>) session.createQuery(sql.toString())
                    .setParameter("idImovel", idImovel)
                    .list();
        } catch (Exception e) {
            throw new ErroRepositorioException(e); 
        }finally{
            session.close();
        }
		
		return retorno;
	}
}
