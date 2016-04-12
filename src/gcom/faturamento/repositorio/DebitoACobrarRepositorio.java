package gcom.faturamento.repositorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

public class DebitoACobrarRepositorio{
    
    private static DebitoACobrarRepositorio instancia;
    
    private DebitoACobrarRepositorio() {}

    public static DebitoACobrarRepositorio getInstancia() {
        if (instancia == null){
            instancia = new DebitoACobrarRepositorio();
        }
        
        return instancia;
    }
    

	@SuppressWarnings("unchecked")
    public Collection<DebitoACobrar> debitosCobrarPorImovelComPendenciaESemRevisao(Integer idImovel) throws ErroRepositorioException{
	    Session session = HibernateUtil.getSession();
	    
		StringBuilder sql = new StringBuilder();
		sql.append("select dc from DebitoACobrar dc ")
		.append(" inner join fetch dc.debitoTipo dt ")
		.append(" left join dc.parcelamento parc ")
		.append(" where dc.imovel.id = :idImovel ")
		.append(" and dc.numeroPrestacaoCobradas < (dc.numeroPrestacaoDebito - coalesce(dc.numeroParcelaBonus, 0))")
		.append(" and dc.dataRevisao is null")
		.append(" and dc.contaMotivoRevisao is null")
		.append(" and dc.situacaoAtual = :situacao");
		
		Collection<DebitoACobrar> debitos = new ArrayList<DebitoACobrar>();
		
		try {
		    debitos = (Collection<DebitoACobrar>) session.createQuery(sql.toString())
		            .setParameter("idImovel", idImovel)
		            .setParameter("situacao", DebitoCreditoSituacao.NORMAL)
		            .list();
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        }finally{
            session.close();
        }
		
		return debitos;
	}
	
	public void reduzirParcelasCobradas(Integer referencia, Integer grupoFaturamento, List<Integer> idsImoveis) throws ErroRepositorioException{
	    Session session = HibernateUtil.getSession();
	    
	    try {
	        if (idsImoveis != null && idsImoveis.size() > 0){
	            StringBuilder sql = new StringBuilder();
	            sql.append(" update faturamento.debito_a_cobrar ")
	            .append(" set dbac_nnprestacaocobradas = dbac_nnprestacaocobradas - 1 ")
	            .append(" where dbac_amreferenciaprestacao >= :referencia ")
	            .append(" and dbac_nnprestacaocobradas > 0 ")
	            .append(" and imov_id in (:ids)");
	            
	            session.createSQLQuery(sql.toString())
	            .setParameter("referencia", referencia)
	            .setParameterList("ids", idsImoveis)
	            .executeUpdate();
	        }
            
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        }finally{
            session.close();
        }
	}

	public void atualizarDebitoCobrar(List<DebitoACobrar> debitos) throws ErroRepositorioException {
	    for (DebitoACobrar debitoACobrar : debitos) {
	        atualizarDebitoCobrar(debitoACobrar);
        }
	}
	
	public void atualizarDebitoCobrar(DebitoACobrar debitoCobrar) throws ErroRepositorioException {
	    Session session = HibernateUtil.getSession();

		StringBuffer sql = new StringBuffer();
		
		sql.append(" update DebitoACobrar dc set");
		sql.append(" dc.anoMesReferenciaPrestacao = :anoMesPrestacao, ");
		sql.append(" dc.numeroPrestacaoCobradas = :numeroPrestacao, ");
		sql.append(" dc.ultimaAlteracao = :dataAtual ");
		sql.append(" where dc.id = :idDebitoAcobrar");
		
		try {
		    session.createQuery(sql.toString())
		    .setParameter("numeroPrestacao", debitoCobrar.getNumeroPrestacaoCobradas())
		    .setParameter("idDebitoAcobrar", debitoCobrar.getId())
		    .setParameter("anoMesPrestacao", debitoCobrar.getAnoMesReferenciaPrestacao())
		    .setParameter("dataAtual", new Date())
		    .executeUpdate();
        } catch (Exception e) {
            throw new ErroRepositorioException(e);
        }finally{
            session.close();
        }
	}
}
