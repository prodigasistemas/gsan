package gcom.atualizacaocadastral;

import gcom.cadastro.RepositorioCadastroHBM;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jboss.logging.Logger;

public class RepositorioAtualizacaoCadastralHBM implements IRepositorioAtualizacaoCadastral {

	public static IRepositorioAtualizacaoCadastral instancia;
	
	private Logger logger = Logger.getLogger(RepositorioAtualizacaoCadastralHBM.class);
	
	
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
			
			consulta = "from ImovelRetorno imovelRetorno"
					+ " where imovelRetorno.idImovel in "
						+ " ( select imovelControle.imovel.id from ImovelControleAtualizacaoCadastral imovelControle "
						+ " where imovelControle.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO  + ") " ;
			
			retorno = (Collection<IImovel>) session.createQuery(consulta).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<IImovelSubcategoria> obterImovelSubcategoriaParaAtualizar(Integer idImovel) throws ErroRepositorioException { 
		
		Collection<IImovelSubcategoria> retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		
		try {
			
			consulta = "from ImovelSubcategoria imovelSubcategoria"
					+ " where imovelSubcategoria.comp_id.imovel.id = :idImovel " ;
			
			retorno = (Collection<IImovelSubcategoria>) session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
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
	
	@SuppressWarnings("unchecked")
	public Collection<ClienteImovelRetorno> pesquisarClienteImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		Collection<ClienteImovelRetorno> listaClienteImovel;
		try{
			String consulta = " select clienteImovel " 
					+ " from ClienteImovelRetorno clienteImovel "
					+ " WHERE clienteImovel.imovel.id = :idImovel ";
			listaClienteImovel = (Collection<ClienteImovelRetorno>) session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		}catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return listaClienteImovel;
	}
	
	public void liberarCadastroImovel(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update ImovelControleAtualizacaoCadastral tab ")
			.append(" set tab.situacaoAtualizacaoCadastral.id = :situacao ")
			.append(" , tab.dataAprovacao = :data")
			.append(" where tab.imovel.id = :idImovel");
			
			session.createQuery(sql.toString())
				.setInteger("situacao", SituacaoAtualizacaoCadastral.APROVADO)
				.setInteger("idImovel", idImovel)
				.setTimestamp("data", Calendar.getInstance().getTime())
				.executeUpdate();

		} catch (HibernateException e) {
			logger.error("Erro ao liberar cadastro do imovel", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void apagarClienteImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String query = "DELETE FROM ClienteImovelRetorno ret WHERE ret.imovel.id = :idImovel ";
			session.createQuery(query).setInteger("idImovel", idImovel).executeUpdate();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}

	public void apagarClienteEnderecoRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String query = " DELETE FROM ClienteEnderecoRetorno clieImovel where clieImovel.idClienteRetorno in (:idsClientesRetorno) ";
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
			String query = " DELETE FROM ClienteFoneRetorno clieImovel where clieImovel.idClienteRetorno in (:idsClientesRetorno) ";
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
