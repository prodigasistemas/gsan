package gcom.cadastro.imovel;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * 
 * Title: GCOM
 * 
 * Description: Repositório de Categoria
 * 
 * Copyright: Copyright (c) 2005
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Pedro Alexandre
 * @created 13 de Janeiro de 2006
 * @version 1.0
 */
public class RepositorioCategoriaHBM implements IRepositorioCategoria{

	//cria uma variável da interface do repositório de categoria
	private static IRepositorioCategoria instancia;

	// construtor da classe
	private RepositorioCategoriaHBM() {
	}

	// retorna uma instância do repositório
	public static IRepositorioCategoria getInstancia() {
		// se não existe ainda a instância
		if (instancia == null) {
			// cria a instância do repositório
			instancia = new RepositorioCategoriaHBM();
		}
		// retorna a instância do repositório
		return instancia;
	}

	/**
	 * Pesquisa uma coleção de categorias
	 * 	 
	 * @return Coleção de Categorias 
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection<Categoria> pesquisarCategoria() throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada
		Collection retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select categoria "
					+ "from Categoria categoria ";

			// pesquisa a coleção de acordo com o parâmetro informado
			retorno = session.createQuery(consulta).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção pesquisada
		return retorno;
	}
	
	
	/**
	 * 
	 * @return Quantidade de categorias cadastradas no BD
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarObterQuantidadeCategoria()
			throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT count(*) "
					+ "FROM gcom.cadastro.imovel.Categoria as catg ";
			
			retorno = session.createQuery(consulta).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * 
	 * Autor: Raphael Rossiter
	 * Data: 18/04/2007
	 * 
	 * @return Quantidade de subcategorias
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarObterQuantidadeSubcategoria()
			throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT count(*) "
					+ "FROM gcom.cadastro.imovel.Subcategoria as scat ";
			
			retorno = session.createQuery(consulta).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * 
	 * Autor: Sávio Luiz
	 * Data: 07/05/2009
	 * 
	 * Pesquisa o Fator de Economia
	 * 
	 * 
	 */
	public Short pesquisarFatorEconomiasCategoria(Integer idCategoria)
			throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT catg.fatorEconomias "
					+ "FROM Categoria as catg "
					+ "WHERE catg.id = :idCategoria";
			
			retorno = (Short)session.createQuery(consulta)
			          .setInteger("idCategoria",idCategoria) 
			          .uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

}
