package gcom.cadastro.imovel;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RepositorioCategoriaHBM implements IRepositorioCategoria{

	private static IRepositorioCategoria instancia;

	private RepositorioCategoriaHBM() {
	}

	public static IRepositorioCategoria getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioCategoriaHBM();
		}
		return instancia;
	}

	@SuppressWarnings("unchecked")
	public Collection<Categoria> pesquisarCategoria() throws ErroRepositorioException {
		Collection<Categoria> retorno = null;
		
		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select categoria from Categoria categoria ";
			retorno = (Collection<Categoria>)session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Object pesquisarObterQuantidadeCategoria() throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT count(*) FROM gcom.cadastro.imovel.Categoria as catg ";
			retorno = session.createQuery(consulta).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Object pesquisarObterQuantidadeSubcategoria() throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT count(*) FROM gcom.cadastro.imovel.Subcategoria as scat ";
			retorno = session.createQuery(consulta).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Short pesquisarFatorEconomiasCategoria(Integer idCategoria) throws ErroRepositorioException {
		Short retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT catg.fatorEconomias FROM Categoria as catg WHERE catg.id = :idCategoria";
			retorno = (Short)session.createQuery(consulta).setInteger("idCategoria",idCategoria).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

}
