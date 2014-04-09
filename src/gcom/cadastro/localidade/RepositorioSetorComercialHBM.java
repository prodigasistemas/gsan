package gcom.cadastro.localidade;

import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.util.Collection;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rossiter
 * @version 1.0
 */

public class RepositorioSetorComercialHBM implements IRepositorioSetorComercial {

    private static IRepositorioSetorComercial instancia;

    /**
     * Constructor for the RepositorioSetorComercialHBM object
     */
    public RepositorioSetorComercialHBM() {
    }

    /**
     * Retorna o valor de instancia
     * 
     * @return O valor de instancia
     */
    public static IRepositorioSetorComercial getInstancia() {

        if (instancia == null) {
            instancia = new RepositorioSetorComercialHBM();
        }

        return instancia;
    }

    /**
     * Pesquisa uma coleção de cliente imovel com uma query especifica
     * 
     * @param filtroClienteImovel
     *            parametros para a consulta
     * @return Description of the Return Value
     * @exception ErroRepositorioException
     *                Description of the Exception
     */

    public Collection pesquisarSetorComercial(int idLocalidade)
            throws ErroRepositorioException {

        //cria a coleção de retorno
        Collection retorno = null;
        //Query
        String consulta;
        //obtém a sessão
        Session session = HibernateUtil.getSession();

        try {
            //pesquisa a coleção de atividades e atribui a variável "retorno"
            consulta = "select new gcom.cadastro.localidade.SetorComercial(setorComercial.id, setorComercial.codigo,"
                    + "setorComercial.descricao) "
                    + "from gcom.cadastro.localidade.SetorComercial as setorComercial "
                    + "where setorComercial.localidade.id = "
                    + idLocalidade
                    + " and setorComercial.indicadorUso = 1";

            retorno = session.createQuery(consulta).list();

            //erro no hibernate
        } catch (HibernateException e) {
            //levanta a exceção para a próxima camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            //fecha a sessão
            HibernateUtil.closeSession(session);
        }
        //retorna a coleção de atividades pesquisada(s)
        return retorno;
    }
    
    /**
	 * Método que retorna o maior código do setor comercial de uma localidade
	 * 
	 * @author Rafael Corrêa
	 * @date 11/07/2006
	 * 
	 * @param idLocalidade
	 * @return
	 * @throws ControladorException
	 */   
    
    public int pesquisarMaximoCodigoSetorComercial(Integer idLocalidade)
	throws ErroRepositorioException {
    	int retorno = 0;
    	Object maxCodigoSetorComercial;
    	
    	Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT max(sc.codigo) "
					+ "FROM SetorComercial sc "
					+ "INNER JOIN sc.localidade l "
					+ "WHERE l.id = :idLocalidade ";
		
			maxCodigoSetorComercial = session.createQuery(consulta).setInteger(
					"idLocalidade", idLocalidade)
					.setMaxResults(1).uniqueResult();
			
			if (maxCodigoSetorComercial != null){
				retorno = (Integer)maxCodigoSetorComercial;	
			}
	
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
    	
    	return retorno;
    }
    
	/**
	 * Pesquisa um setor comercial pelo código e pelo id da localidade
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return SetorComercial
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoSetorComercialRelatorio(Integer codigoSetorComercial,
			Integer idLocalidade) throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada
				
		Object[] retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select sc.stcm_cdsetorcomercial as codigo, " +
				"sc.stcm_nmsetorcomercial as descricao "
					+ "from cadastro.localidade loc, "
					+ "cadastro.setor_comercial sc "
					+ "where sc.loca_id = loc.loca_id and"  
					+ " loc.loca_id = " + idLocalidade.toString() 
					+ " and sc.stcm_cdsetorcomercial = " + codigoSetorComercial.toString();

			// pesquisa a coleção de acordo com o parâmetro informado
			Collection colecaoSetoresComerciais = session.createSQLQuery(consulta)
			.addScalar("codigo", Hibernate.INTEGER)
			.addScalar("descricao", Hibernate.STRING).list();
			
			retorno = Util.retonarObjetoDeColecaoArray(colecaoSetoresComerciais);

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
	 * Pesquisar os ids do Setor comercial pela localidade
	 * 
	 * @author Ana Maria
	 * @date 07/02/2007
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorComercial(Integer idLocalidade)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = " select stcm.id"
					 + " from SetorComercial stcm"
					 + " where stcm.localidade.id =:idLocalidade";

			retorno = session.createQuery(consulta).setInteger(
					"idLocalidade", idLocalidade).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}	

    /**
     * Pesquisar os todos os ids de Setor comercial 
     * 
     * @author Vivianne Sousa
     * @date 14/05/2008
     * 
     * @return Collection<Integer>
     * @throws ErroRepositorioException
     */
    public Collection<Integer> pesquisarIdsCodigosSetorComercial()
            throws ErroRepositorioException {

        Collection retorno = null;

        Session session = HibernateUtil.getSession();

        String consulta = "";

        try {

            consulta = " select stcm.id"
                     + " from SetorComercial stcm";

            retorno = session.createQuery(consulta).list();
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }   

	/**
	 * [UC0928]-Manter Situação Especial de Faturamento
	 * [FS0003]-Verificar a existência do setor
	 *
	 * @author Marlon Patrick
	 * @date 11/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaSetorComercial(Integer idSetorComercial)throws ErroRepositorioException{
		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select count(setor.id) "

					+ "from SetorComercial setor "

					+ "where setor.id = :idSetor and (imovel.indicadorUso = :indicadorUso)";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"idSetor", idSetorComercial).setShort("indicadorUso",
					ConstantesSistema.INDICADOR_USO_ATIVO).setMaxResults(1).uniqueResult();

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
