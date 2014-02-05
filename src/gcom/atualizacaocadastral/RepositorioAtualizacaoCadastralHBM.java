package gcom.atualizacaocadastral;

import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RepositorioAtualizacaoCadastralHBM implements IRepositorioAtualizacaoCadastral {

	public static IRepositorioAtualizacaoCadastral instancia;
	
	public static IRepositorioAtualizacaoCadastral getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioAtualizacaoCadastralHBM();
		}
		return instancia;
	}

	@SuppressWarnings("unchecked")
	public Collection<IImovel> obterImoveisParaAtualizar() throws ErroRepositorioException {
		
		Collection<IImovel> retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		
		try {
			
			consulta = " from ImovelRetorno imovelRetorno"
					+ " where imovelRetorno.idImovel in "
						+ " ( select imovelCotrole.idImovel from ImovelControleAtualizacaoCadastral imovelControle "
						+ " where situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO  + ") " ;
			
			retorno = (Collection<IImovel>) session.createQuery(consulta);
		} catch (HibernateException e) {
			
		}
		
		return retorno;
	}
	
	public ImovelRetorno pesquisarImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try{
			String consulta = " SELECT imovelRetorno "
							+ " FROM ImovelRetorno imovelRetorno "
							+ " WHERE imovelRetorno.idImovel = :idImovel ";
			return (ImovelRetorno) session.createQuery(consulta)
							.setInteger("idImovel", idImovel).uniqueResult();
		}catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ImovelSubcategoriaRetorno> pesquisarImovelSubcategoriaRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<ImovelSubcategoriaRetorno> retorno = null;
		try{
			String consulta = " SELECT imovelSubCatRetorno "
							+ " FROM ImovelSubcategoriaRetorno imovelSubCatRetorno "
							+ " INNER JOIN imovelSubCatRetorno.comp_id.imovel imovel"
							+ " WHERE imovel.id = :idImovel ";
			retorno = (List<ImovelSubcategoriaRetorno>) session.createQuery(consulta)
							.setInteger("idImovel", idImovel).list();
		}catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public void apagarImovelRetornoRamoAtividadeRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try{
			String consulta = " DELETE ImovelRamoAtividadeRetorno ramo "
					+ " WHERE ramo.comp_id.imovel.id = :idImovel ";
			session.createQuery(consulta).setInteger("idImovel", idImovel).executeUpdate();
		}catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovel, Integer idSubcategoria,Integer idCategoria)
			throws ErroRepositorioException {
		
			Collection<ImovelSubcategoria> retorno = null;
			Session session = HibernateUtil.getSession();
			String consulta = null;
			
			try {
				consulta = " SELECT imovelSubcategoria" 
						 + " FROM ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoria" 
						 + " WHERE imovelSubcategoria.idImovel = :idImovel";
				
				if(idSubcategoria != null){
					consulta = consulta + " AND imovelSubcategoria.idSubcategoria = "+idSubcategoria;
				}
				
				if(idCategoria != null){
					consulta = consulta + " AND imovelSubcategoria.idCategoria = "+idCategoria;
				}
			
				retorno = (Collection<ImovelSubcategoria>)session.createQuery(consulta).setInteger("idImovel",
						idImovel.intValue()).list();
			
			} catch (HibernateException e) {
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
			
			return retorno;

	}
	
	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovel)
		throws ErroRepositorioException {
	
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT imov"
				     + " FROM ImovelAtualizacaoCadastral imov" 				    				    
				     + " WHERE imov.idImovel = :idImovel";
	
		imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral)session.createQuery(consulta)
										.setInteger("idImovel", idImovel)
										.setMaxResults(1).uniqueResult();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
	
		}
	
		return imovelAtualizacaoCadastral;
	}
	
	public void apagarClienteImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try{
			String consulta = " DELETE ClienteImovelRetorno ret "
					+ " WHERE ret.imovel.id = :idImovel ";
			session.createQuery(consulta).setInteger("idImovel", idImovel).executeUpdate();
		}catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}		
	}

	public void apagarClienteEnderecoRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String query = " DELETE FROM ClienteEnderecoRetorno clieImovel where clieImovel.idClienteRetorno in (:idClienteRetorno) ";
			session.createQuery(query).setParameterList("idsClientesRetorno", idsClientesRetorno).executeUpdate();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void apagarClienteFoneRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String query = " DELETE FROM ClienteFoneRetorno clieImovel where clieImovel.idClienteRetorno in (:idClienteRetorno) ";
			session.createQuery(query).setParameterList("idsClientesRetorno", idsClientesRetorno).executeUpdate();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void apagarClienteRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String query = " DELETE FROM ClienteRetorno clieImovel where clieImovel.idClienteRetorno in (:idsClientesRetorno) ";
			session.createQuery(query).setParameterList("idsClientesRetorno", idsClientesRetorno).executeUpdate();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Collection<Integer> pesquisarIdsClienteRetorno(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection<Integer> retorno = null;
		try {
			String consulta = "SELECT clieImovel.idClienteRetorno "
					+ " FROM ClienteImovelRetorno clieImovel "
					+ " WHERE clieImovel.imovel.id = :idImovel ";
			retorno = (Collection<Integer>)session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
}
