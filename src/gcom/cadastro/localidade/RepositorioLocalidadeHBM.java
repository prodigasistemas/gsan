package gcom.cadastro.localidade;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * 
 * Title: GCOM
 * 
 * Description: Repositório de Localdiade
 * 
 * Copyright: Copyright (c) 2005
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Pedro Alexandre
 * @created 13 de Janeiro de 2006
 * @version 1.0
 */

/**
 * @author Administrador
 *
 */
/**
 * @author Administrador
 *
 */
public class RepositorioLocalidadeHBM implements IRepositorioLocalidade {

	// cria uma variável da inteface do repositório de localidade
	private static IRepositorioLocalidade instancia;

	// construtor da classe
	private RepositorioLocalidadeHBM() {
	}

	// retorna uma instância da repositório
	public static IRepositorioLocalidade getInstancia() {
		// se não existe ainda a instância
		if (instancia == null) {
			// cria a instância do repositório
			instancia = new RepositorioLocalidadeHBM();
		}
		// retorna a instância do repositório
		return instancia;
	}

	/**
	 * Pesquisa uma coleção de localidades por gerência regional
	 * 
	 * @param idGerenciaRegional
	 *            Código da gerência regional solicitada
	 * @return Coleção de Localidades da Gerência Regional solicitada
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection<Localidade> pesquisarLocalidadePorGerenciaRegional(
			int idGerenciaRegional) throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada
		Collection retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select localidade "
					+ "from Localidade localidade "
					+ "inner join localidade.gerenciaRegional greg "
					+ "where greg = :idGerenciaRegional ";

			// pesquisa a coleção de acordo com o parâmetro informado
			retorno = session.createQuery(consulta).setInteger(
					"idGerenciaRegional", idGerenciaRegional).list();

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
	 * Obtém o id da localidade
	 * @author Sávio Luiz
	 * @date 08/03/2006
	 *
	 * @param idImovel
	 * @return Um integer que representa o id da localidade
	 * @throws ControladorException
	 */
	public Integer pesquisarIdLocalidade(Integer idImovel)throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT localidade.id " + "FROM Imovel imovel "
					+ "LEFT JOIN imovel.localidade localidade "
					+ "WHERE imovel.id = :idImovel ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**
	 * Obtém o id da localidade
	 * @author Sávio Luiz
	 * @date 08/03/2006
	 *
	 * @param idImovel
	 * @return Um integer que representa o id da localidade
	 * @throws ControladorException
	 */
	public Collection pesquisarTodosIdLocalidade()throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT loc.id " + "FROM Localidade loc where loc.indicadorUso = :icUso ";

			retorno = session.createQuery(consulta).setShort("icUso",ConstantesSistema.INDICADOR_USO_ATIVO).list();
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}

	 /**
	 * Método que retorna o maior id da Localidade
	 * 
	 * @author Vivianne Sousa
	 * @date 12/07/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */   
    
    public int pesquisarMaximoIdLocalidade()
	throws ErroRepositorioException {
    	int retorno = 0;
    	Object maxIdLocalidade;
    	
    	Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT max(l.id) "
					+ "FROM Localidade l ";

			maxIdLocalidade = session.createQuery(consulta)
					.setMaxResults(1).uniqueResult();
	
			if (maxIdLocalidade != null){
				retorno = (Integer)maxIdLocalidade;	
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
    	
    	return retorno;
    }

	/**
	 * Pesquisa uma localidade pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Localidade
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoLocalidadeRelatorio(
			Integer idLocalidade) throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada
				
		Object[] retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select loc.loca_id as id, " +
				"loc.loca_nmlocalidade as descricao "
					+ "from cadastro.localidade loc "
					+ "where loc.loca_id = " + idLocalidade.toString();

			// pesquisa a coleção de acordo com o parâmetro informado
			Collection colecaoLocalidades = session.createSQLQuery(consulta)
			.addScalar("id", Hibernate.INTEGER)
			.addScalar("descricao", Hibernate.STRING).list();
			
			retorno = Util.retonarObjetoDeColecaoArray(colecaoLocalidades);

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
	
	public Integer verificarExistenciaLocalidade(
			Integer idLocalidade) throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada
				
		Integer retorno = null;
		
		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select id "+
							  "from Localidade loc "+
							  "where loc.id =" + idLocalidade.toString();

			// pesquisa a coleção de acordo com o parâmetro informado
			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();
			
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
	 * Atualiza logradouroBairro de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairroGerenciaRegional(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta = "UPDATE gcom.cadastro.localidade.GerenciaRegional SET "
					+ "lgbr_id = :idLogradouroBairroNovo, greg_tmultimaalteracao = :ultimaAlteracao " 
					+ "WHERE lgbr_id = :idLogradouroBairroAntigo ";

			session.createQuery(consulta).setInteger(
					"idLogradouroBairroNovo", logradouroBairroNovo.getId())
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("idLogradouroBairroAntigo", logradouroBairroAntigo.getId())
					.executeUpdate();
			

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * Atualiza logradouroCep de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCepGerenciaRegional(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta = "UPDATE gcom.cadastro.localidade.GerenciaRegional SET "
					+ "lgcp_id = :idLogradouroCepNovo, greg_tmultimaalteracao = :ultimaAlteracao " 
					+ "WHERE lgcp_id = :idLogradouroCepAntigo ";

			session.createQuery(consulta).setInteger(
					"idLogradouroCepNovo", logradouroCepNovo.getId())
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("idLogradouroCepAntigo", logradouroCepAntigo.getId())
					.executeUpdate();
			

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta = "UPDATE gcom.cadastro.localidade.Localidade SET "
					+ "lgbr_id = :idLogradouroBairroNovo, loca_tmultimaalteracao = :ultimaAlteracao " 
					+ "WHERE lgbr_id = :idLogradouroBairroAntigo ";

			session.createQuery(consulta).setInteger(
					"idLogradouroBairroNovo", logradouroBairroNovo.getId())
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("idLogradouroBairroAntigo", logradouroBairroAntigo.getId())
					.executeUpdate();
			

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * Atualiza logradouroCep de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta = "UPDATE gcom.cadastro.localidade.Localidade SET "
					+ "lgcp_id = :idLogradouroCepNovo, loca_tmultimaalteracao = :ultimaAlteracao " 
					+ "WHERE lgcp_id = :idLogradouroCepAntigo ";

			session.createQuery(consulta).setInteger(
					"idLogradouroCepNovo", logradouroCepNovo.getId())
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("idLogradouroCepAntigo", logradouroCepAntigo.getId())
					.executeUpdate();
			

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
    
	/**
	 * Obtém Elo Pólo
	 * @author Ana Maria
	 * @date 10/12/2007
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarEloPolo() throws ErroRepositorioException {
		
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select loca "
					  +"from Localidade loca " 
					  +"where loca.id in "
					  +"(select codElo.localidade.id from Localidade codElo) "
					  +"order by loca.id";

			retorno = session.createQuery(consulta).list();
			
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	
	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores
	 * 
	 * @author Raphael Rossiter
	 * @date 09/01/2008
	 *
	 * @param idConta
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorConta(Integer idConta)throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT localidade.id " + "FROM Conta cnta "
					+ "LEFT JOIN cnta.localidade localidade "
					+ "WHERE cnta.id = :idConta ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idConta", idConta.intValue()).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores
	 * 
	 * @author Raphael Rossiter
	 * @date 09/01/2008
	 *
	 * @param idConta
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorContaHistorico(Integer idContaHistorico)throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT localidade.id " + "FROM ContaHistorico cnta "
					+ "LEFT JOIN cnta.localidade localidade "
					+ "WHERE cnta.id = :idConta ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idConta", idContaHistorico.intValue()).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 11/01/2008
	 *
	 * @param idGuiaPagamento
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorGuiaPagamento(Integer idGuiaPagamento)throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT localidade.id " + "FROM GuiaPagamento gpag "
					+ "LEFT JOIN gpag.localidade localidade "
					+ "WHERE gpag.id = :idGuiaPagamento ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idGuiaPagamento", idGuiaPagamento.intValue()).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**
	 * 
	 * Pesquisar as localidades do município
	 * 
	 * @author Sávio Luiz
	 * @date 25/02/2008
	 * 
	 */

	public Collection pesquisarLocalidadesMunicipio(Integer idMunicipio) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select distinct loc.id "
					  +"from SetorComercial setor "
					  +"inner join setor.localidade loc "
					  +"inner join setor.municipio mun "
					  +"where mun.id = :idMunicipio";
			
			retorno = session.createQuery(consulta).setInteger(
					"idMunicipio", idMunicipio).list();

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
	 * Pesquisa os ids das localidades que possuem imóveis.
	 *
	 * @author Pedro Alexandre
	 * @date 07/07/2008
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesImoveis()throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT distinct(imov.loca_id) as idLocalidade " + 
						"FROM cadastro.imovel imov " ; 
						

			retorno = session.createSQLQuery(consulta)
			.addScalar("idLocalidade",Hibernate.INTEGER)
			.list();
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 26/11/2008
	 *
	 * @param idDebitoACobrar
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorDebitoACobrar(Integer idDebitoACobrar)throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT localidade.id " + "FROM DebitoACobrar dbac "
					+ "LEFT JOIN dbac.localidade localidade "
					+ "WHERE dbac.id = :idDebitoACobrar ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idDebitoACobrar", idDebitoACobrar.intValue()).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 11/01/2008
	 *
	 * @param idGuiaPagamento
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorGuiaPagamentoHistorico(Integer idGuiaPagamentoHistorico)throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT localidade.id " + "FROM GuiaPagamentoHistorico gpag "
					+ "LEFT JOIN gpag.localidade localidade "
					+ "WHERE gpag.id = :idGuiaPagamento ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idGuiaPagamento", idGuiaPagamentoHistorico.intValue()).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @date 26/11/2008
	 *
	 * @param idDebitoACobrar
	 * @return Um integer que representa o id da localidade
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalidadePorDebitoACobrarHistorico(Integer idDebitoACobrarHistorico)throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT localidade.id " + "FROM DebitoACobrarHistorico dbac "
					+ "LEFT JOIN dbac.localidade localidade "
					+ "WHERE dbac.id = :idDebitoACobrar ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idDebitoACobrar", idDebitoACobrarHistorico.intValue()).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**
	 * Pesquisa os ids de Todas as localidaes.
	 *
	 * @author Hugo Leonardo
	 * @date 08/07/2010
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarIdsLocalidades() throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT loc.id FROM Localidade loc ";

			retorno = session.createQuery(consulta).list();
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}

}
