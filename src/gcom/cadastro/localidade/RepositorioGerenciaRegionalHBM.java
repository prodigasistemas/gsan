package gcom.cadastro.localidade;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.util.Collection;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * 
 * Title: GCOM
 * 
 * Description: Repositório de Gerência Regional
 * 
 * Copyright: Copyright (c) 2005
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Pedro Alexandre
 * @created 13 de Janeiro de 2006
 * @version 1.0
 */
public class RepositorioGerenciaRegionalHBM implements
		IRepositorioGerenciaRegional {

	// cria uma variável da inteface do repositório de gerência regional
	private static IRepositorioGerenciaRegional instancia;

	// construtor da classe
	private RepositorioGerenciaRegionalHBM() {
	}

	// retorna uma instância do repositório
	public static IRepositorioGerenciaRegional getInstancia() {
		// se não existe ainda a instância
		if (instancia == null) {
			// cria a instância do repositório
			instancia = new RepositorioGerenciaRegionalHBM();
		}
		// retorna a instância do repositório
		return instancia;
	}

	/**
	 * Pesquisa uma coleção de gerências regionais
	 * 
	 * @return Coleção de Gerências Regionais
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection<GerenciaRegional> pesquisarGerenciaRegional()
			throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada
		Collection retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select gerenciaRegional "
					+ "from GerenciaRegional gerenciaRegional ";

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
	 * Pesquisa uma gerência regional pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Gerência Regional
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoGerenciaRegionalRelatorio(
			Integer idGerenciaRegional) throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada
				
		Object[] retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select gr.greg_id as id, " +
				"gr.greg_nmabreviado as nomeAbreviado "
					+ "from cadastro.gerencia_regional gr "
					+ "where gr.greg_id = " + idGerenciaRegional.toString();

			// pesquisa a coleção de acordo com o parâmetro informado
			Collection colecaoGerenciasRegionais = session.createSQLQuery(consulta)
			.addScalar("id", Hibernate.INTEGER)
			.addScalar("nomeAbreviado", Hibernate.STRING).list();
			
			retorno = Util.retonarObjetoDeColecaoArray(colecaoGerenciasRegionais);

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
     * Pesquisa o id da gerência regional para a qual a localidade pertence.
     * 
     * [UC0267] Encerrar Arrecadação do Mês
     * 
     * @author Pedro Alexandre
     * @date 05/01/2007
     * 
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Integer pesquisarIdGerenciaParaLocalidade(Integer idLocalidade) throws ErroRepositorioException {

        Integer retorno = null;

        Session session = HibernateUtil.getSession();

        String consulta = "";

        try {

            consulta = "select greg.id from Localidade loca " + "left join loca.gerenciaRegional greg " + "where loca.id = :idLocalidade";

            retorno = (Integer) session.createQuery(consulta).setInteger("idLocalidade", idLocalidade).uniqueResult();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }

}
