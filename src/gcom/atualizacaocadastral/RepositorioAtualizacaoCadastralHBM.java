package gcom.atualizacaocadastral;

import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
	public Collection<IImovel> obterImoveisParaAtualizar(Integer tipoOperacao) throws ErroRepositorioException {
		
		Collection<IImovel> retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		
		try {
			
			consulta = " select imovelRetorno "
					+ " from ImovelRetorno imovelRetorno "
					+ " left join fetch imovelRetorno.ramalLocalInstalacao "
					+ " where imovelRetorno.tipoOperacao = :tipoOperacao "
					+ " and imovelRetorno.id in "
						+ " ( select imovelControle.imovelRetorno.id from ImovelControleAtualizacaoCadastral imovelControle "
						+ " where imovelControle.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO  
						+ " and imovelControle.dataProcessamento is null ) " ;
			
			retorno = (Collection<IImovel>) session.createQuery(consulta).
					setInteger("tipoOperacao",  tipoOperacao).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis.");
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
			
			consulta = "from ImovelSubcategoriaRetorno imovelSubcategoria"
					+ " where imovelSubcategoria.imovel.id = :idImovel " ;
			
			retorno = (Collection<IImovelSubcategoria>) session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel subcategoria.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		ImovelControleAtualizacaoCadastral retorno = null;
		try{
			String consulta = " SELECT e FROM ImovelControleAtualizacaoCadastral e "
							+ " LEFT JOIN FETCH e.situacaoAtualizacaoCadastral s "
							+ " WHERE e.imovel.id = :idImovel ";
			retorno = (ImovelControleAtualizacaoCadastral) session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.uniqueResult();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel controle atualizacao cadastral");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<IImovelRamoAtividade> obterImovelRamoAtividadeParaAtualizar(Integer idImovel) throws ErroRepositorioException { 
		
		Collection<IImovelRamoAtividade> retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		
		try {
			
			consulta = "from ImovelRamoAtividadeRetorno imovelRamoAtividade"
					+ " where imovelRamoAtividade.imovel.id = :idImovel " ;
			
			retorno = (Collection<IImovelRamoAtividade>) session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<IClienteFone> obterClienterFoneParaAtualizar(Integer idImovel) throws ErroRepositorioException {
		Collection<IClienteFone> retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		
		try {
			consulta = "select clienteFoneRetorno " 
					+ "from ClienteFoneRetorno clienteFoneRetorno, "
					+ " ClienteImovelRetorno clienteImovelRetorno" 
					+ " inner join clienteFoneRetorno.cliente cliente "
					+ " where clienteFoneRetorno.idClienteRetorno = clienteImovelRetorno.idClienteRetorno "
					+ " and clienteImovelRetorno.imovel.id = :idImovel ";
			
			retorno = (Collection<IClienteFone>) session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}


	public void apagarImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try{
			String consulta = " DELETE FROM ImovelRetorno imovelRetorno "
							+ " WHERE imovelRetorno.idImovel = :idImovel ";
			session.createQuery(consulta).setInteger("idImovel", idImovel).executeUpdate();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao excluir imovel retorno");
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
							+ " INNER JOIN imovelSubCatRetorno.imovel imovel"
							+ " WHERE imovel.id = :idImovel ";
			retorno = (List<ImovelSubcategoriaRetorno>) session.createQuery(consulta)
							.setInteger("idImovel", idImovel).list();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel subcategoria");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public void apagarImovelQuantidadesOcupantes(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try{
			String consulta = " DELETE ImovelTipoOcupanteQuantidadeRetorno e WHERE e.imovel.id = :idImovel ";
			session.createQuery(consulta).setInteger("idImovel", idImovel).executeUpdate();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao apagar quantidades de ocupantes");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public void apagarImovelRetornoRamoAtividadeRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
	    Session session = HibernateUtil.getSession();
	    try{
	        String consulta = " DELETE ImovelRamoAtividadeRetorno ramo "
	                + " WHERE ramo.imovel.id = :idImovel ";
	        session.createQuery(consulta).setInteger("idImovel", idImovel).executeUpdate();
	    }catch (HibernateException e) {
	        throw new ErroRepositorioException(e, "Erro ao apagar imovel retorno ramo atividade");
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
						 + " WHERE imovelSubcategoria.imovel.id = :idImovel";
				
				if(idSubcategoria != null){
					consulta = consulta + " AND imovelSubcategoria.subcategoria.id = "+idSubcategoria;
				}
				
				if(idCategoria != null){
					consulta = consulta + " AND imovelSubcategoria.categoria.id = "+idCategoria;
				}
			
				retorno = (Collection<ImovelSubcategoria>)session.createQuery(consulta).setInteger("idImovel",
						idImovel.intValue()).list();
			
			} catch (HibernateException e) {
				throw new ErroRepositorioException(e, "Erro ao pesquisar imovel subcategoria");
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
			throw new ErroRepositorioException(e, "Erro ap pesquisar imovel atualizacao cadastral");
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
			throw new ErroRepositorioException(e, "Erro ao pesquisar cliente imovel retorno");
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
			throw new ErroRepositorioException(e, "Erro ao liberar cadastro do imovel");
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
			throw new ErroRepositorioException(e, "Erro ao apagar cliente imovel retono");
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
			throw new ErroRepositorioException("Erro ao apagar cliente endereco retorno");
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
			throw new ErroRepositorioException("Erro ao apagar cliente fone retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void apagarClienteRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String query = " DELETE FROM ClienteRetorno ret where ret.id in (:idsClientesRetorno) ";
			session.createQuery(query).setParameterList("idsClientesRetorno", idsClientesRetorno).executeUpdate();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao apagar cliente retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Integer> pesquisarIdsClienteRetorno(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection<Integer> retorno = null;
		try {
			String consulta = "SELECT clieImovel.idClienteRetorno "
					+ " FROM ClienteImovelRetorno clieImovel "
					+ " WHERE clieImovel.imovel.id = :idImovel ";
			retorno = (Collection<Integer>)session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao pesquisar ids de cliente retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Integer recuperaValorSequenceImovelRetorno() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		try {
			String consulta = "select last_value from atualizacaocadastral.sequence_imovel_retorno ";
			retorno = (Integer) session.createSQLQuery(consulta).addScalar("last_value", Hibernate.INTEGER).uniqueResult();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao recuperar valor da sequece do imovel retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Integer> pesquisarImoveisPorSituacaoPeriodo(Integer situacao, Date dataInicial, Date dataFinal) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT i.imovel.id ")
			   .append("FROM ImovelControleAtualizacaoCadastral i ")
			   .append("WHERE i.situacaoAtualizacaoCadastral.id = :situacao ");
			
			if (situacao.equals(SituacaoAtualizacaoCadastral.DISPONIVEL)) {
				sql.append("AND date(i.dataGeracao) BETWEEN :dataInicial AND :dataFinal");
			} else if (situacao.equals(SituacaoAtualizacaoCadastral.TRANSMITIDO)) {
				sql.append("AND date(i.dataRetorno) BETWEEN :dataInicial AND :dataFinal");
			} else if (situacao.equals(SituacaoAtualizacaoCadastral.APROVADO)) {
				sql.append("AND date(i.dataAprovacao) BETWEEN :dataInicial AND :dataFinal");
			} else if (situacao.equals(SituacaoAtualizacaoCadastral.ATUALIZADO)) {
				sql.append("AND date(i.dataProcessamento) BETWEEN :dataInicial AND :dataFinal");
			}
			
			Query query = session.createQuery(sql.toString()).setInteger("situacao", situacao);
			
			if (situacao.equals(SituacaoAtualizacaoCadastral.DISPONIVEL) 
					|| situacao.equals(SituacaoAtualizacaoCadastral.TRANSMITIDO)
					|| situacao.equals(SituacaoAtualizacaoCadastral.APROVADO)
					|| situacao.equals(SituacaoAtualizacaoCadastral.ATUALIZADO)) {
				
				query.setDate("dataInicial", dataInicial).setDate("dataFinal", dataFinal);
			}
			
			return (Collection<Integer>) query.list();
		} catch(HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public void apagarImagemRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try{
			String consulta = " DELETE FROM ImagemRetorno imagemRetorno "
							+ " WHERE imagemRetorno.idImovel = :idImovel ";
			session.createQuery(consulta).setInteger("idImovel", idImovel).executeUpdate();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao excluir imagem retorno");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<ImovelControleAtualizacaoCadastral> obterImoveisControle(Collection<IImovel> listaImoveisRetorno) {
		Collection<ImovelControleAtualizacaoCadastral> listaImoveisControle = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select imovelControle "
							+ " from ImovelControleAtualizacaoCadastral imovelControle "
							+ " inner join imovelControle.imovelRetorno imovelRetorno "
							+ " where imovelRetorno.id in (:listaImoveisRetorno)";
			
			listaImoveisControle = (Collection<ImovelControleAtualizacaoCadastral>)session.createQuery(consulta)
										.setParameterList("listaImoveisRetorno", getIdsImovelRetorno(listaImoveisRetorno)).list();
			
		} catch (HibernateException e) {
		} finally {
			HibernateUtil.closeSession(session);
		}
		return listaImoveisControle;
	}
	
	public ImovelControleAtualizacaoCadastral obterImovelControlePorImovelRetorno(Integer idImovelRetorno) {
		ImovelControleAtualizacaoCadastral imovelControle = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select imovelControle "
							+ " from ImovelControleAtualizacaoCadastral imovelControle "
							+ " inner join imovelControle.imovelRetorno imovelRetorno "
							+ " where imovelRetorno.id in (:listaImoveisRetorno)";
			
			imovelControle = (ImovelControleAtualizacaoCadastral)session.createQuery(consulta)
								.setInteger("listaImoveisRetorno", idImovelRetorno).uniqueResult();
			
		} catch (HibernateException e) {
		} finally {
			HibernateUtil.closeSession(session);
		}
		return imovelControle;
	}
	
	public ImovelControleAtualizacaoCadastral obterImovelControle(Integer idImovelControle) {
		ImovelControleAtualizacaoCadastral imovelControle = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = "select imovelControle "
							+ " from ImovelControleAtualizacaoCadastral imovelControle "
							+ " left join imovelControle.imovelRetorno imovelRetorno "
							+ " where imovelControle.id = :idImovelControle";
			
			imovelControle = (ImovelControleAtualizacaoCadastral)session.createQuery(consulta)
								.setInteger("idImovelControle", idImovelControle).uniqueResult();
			
		} catch (HibernateException e) {
		} finally {
			HibernateUtil.closeSession(session);
		}
		return imovelControle;
	}
	
	private Collection<Integer> getIdsImovelRetorno(Collection<IImovel> listaImoveisRetorno) {
		Collection<Integer> listaIds = new ArrayList<Integer>();
		
		for (IImovel imovelRetorno : listaImoveisRetorno) {
			listaIds.add(imovelRetorno.getId());
		}
		
		return listaIds;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ImovelSubcategoriaAtualizacaoCadastral> pesquisarSubCategoriasAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException {
		Collection<ImovelSubcategoriaAtualizacaoCadastral> retorno = null;

		Session session = HibernateUtil.getSession();

		StringBuilder consulta = new StringBuilder();

		try {
			consulta.append("select sub ")
				.append(" from ImovelSubcategoriaAtualizacaoCadastral sub ")
				.append(" where sub.imovel.id = :idImovel");

			retorno = session.createQuery(consulta.toString())
					.setInteger("idImovel",	idImovel.intValue())
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}
	
	@SuppressWarnings("unchecked")
	public Collection<ClienteImovelRetorno> obterClientesParaAtualizar() throws ErroRepositorioException {
		
		Collection<ClienteImovelRetorno> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " select clienteImovelRetorno "
					+ "from ClienteImovelRetorno clienteImovelRetorno, Cliente cliente "
					+ " inner join fetch clienteImovelRetorno.clienteRelacaoTipo clienteRelacaoTipo "
					+ " where clienteImovelRetorno.cliente.id = cliente.id "
					+ " and clienteImovelRetorno.idImovelRetorno in "
						+ " ( select imovelControle.imovelRetorno.id from ImovelControleAtualizacaoCadastral imovelControle, Imovel imovel "
						+ " where imovelControle.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO  
						+ " and imovel.id = imovelControle.imovel.id  "
						+ " and imovelControle.dataProcessamento is null ) " ;
			
			retorno = (Collection<ClienteImovelRetorno>) session.createQuery(consulta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public ICliente pesquisarClienteRetorno(ClienteImovelRetorno clienteImovelRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		ICliente retorno = null;
		try {
			String consulta = "select clienteRetorno "
					+ " from ClienteRetorno clienteRetorno, "
					+ " ClienteImovelRetorno clienteImovelRetorno "
					+ " where clienteImovelRetorno.idClienteRetorno = clienteRetorno.id "
					+ " and clienteImovelRetorno.idClienteRetorno = :idClienteRetorno "
					+ " and clienteImovelRetorno.idImovelRetorno = :idImovel "
					+ " and clienteImovelRetorno.clienteRelacaoTipo.id = :idClienteRelacaoTipo ";
			
			retorno = (ICliente)session.createQuery(consulta)
										.setInteger("idClienteRetorno", clienteImovelRetorno.getIdClienteRetorno())
										.setInteger("idImovel", clienteImovelRetorno.getIdImovelRetorno())
										.setInteger("idClienteRelacaoTipo", clienteImovelRetorno.getClienteRelacaoTipo().getId()).uniqueResult();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao pesquisar cliente retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public IImovel pesquisarImovelRetorno(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		IImovel retorno = null;
		try {
			String consulta = "select imovelRetorno "
					+ " from ImovelRetorno imovelRetorno "
					+ " where imovelRetorno.idImovel = :idImovel ";
			
			retorno = (IImovel)session.createQuery(consulta).setInteger("idImovel", idImovel).uniqueResult();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao pesquisar imovel retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<IClienteFone> pesquisarClienteFoneRetorno(Integer idClienteRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection<IClienteFone> retorno = null;
		try {
			String consulta = "select clienteFoneRetorno "
					+ " from ClienteFoneRetorno clienteFoneRetorno "
					+ " where clienteFoneRetorno.idClienteRetorno = :idClienteRetorno ";
			
			retorno = (Collection<IClienteFone>)session.createQuery(consulta).setInteger("idClienteRetorno", idClienteRetorno).list();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao pesquisar cliente fone retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<IClienteEndereco> pesquisarClienteEnderecoRetorno(Integer idClienteRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection<IClienteEndereco> retorno = null;
		try {
			String consulta = "select clienteEnderecoRetorno "
					+ " from ClienteEnderecoRetorno clienteEnderecoRetorno "
					+ " where clienteEnderecoRetorno.idClienteRetorno = :idClienteRetorno ";
			
			retorno = (Collection<IClienteEndereco>)session.createQuery(consulta).setInteger("idClienteRetorno", idClienteRetorno).list();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao pesquisar cliente endereco retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ClienteImovelRetorno> obterClienteImoveisDoImovel(Integer idImovelRetorno) throws ErroRepositorioException {
		
		Collection<ClienteImovelRetorno> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " select clienteImovelRetorno "
					+ "from ClienteImovelRetorno clienteImovelRetorno "
					+ " inner join fetch clienteImovelRetorno.clienteRelacaoTipo clienteRelacaoTipo "
					+ " where clienteImovelRetorno.idImovelRetorno = :idImovelRetorno ";
			
			retorno = (Collection<ClienteImovelRetorno>) session.createQuery(consulta).
					setInteger("idImovelRetorno",  idImovelRetorno).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ClienteImovelRetorno> obterClientesNovaRelacao() throws ErroRepositorioException {
		
		Collection<ClienteImovelRetorno> retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String consulta = " select clienteImovelRetorno "
					+ "from ClienteImovelRetorno clienteImovelRetorno, Cliente cliente "
					+ " inner join fetch clienteImovelRetorno.clienteRelacaoTipo clienteRelacaoTipo "
					+ " where clienteImovelRetorno.cliente.id = cliente.id "
					+ " and clienteImovelRetorno.idImovelRetorno in "
						+ " ( select imovelControle.imovelRetorno.id from ImovelControleAtualizacaoCadastral imovelControle, Imovel imovel "
						+ " where imovelControle.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO  
						+ " and imovel.id = imovelControle.imovel.id  "
						+ " and imovelControle.dataProcessamento is null ) " ;
			
			retorno = (Collection<ClienteImovelRetorno>) session.createQuery(consulta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public boolean existeRelacaoClienteImovel(Integer idImovel, Integer idCliente, Integer idClienteRelacaoTipo) throws ErroRepositorioException {
		Integer retorno = 0;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " select count(clienteImovel) "
							+ " from ClienteImovel clienteImovel " 
							+ " where clienteImovel.imovel.id = :idImovel "
							+ " and clienteImovel.cliente.id = :idCliente " 
							+ " and clienteImovel.clienteRelacaoTipo = :idClienteRelacaoTipo " 
							+ " and clienteImovel.dataFimRelacao is null " ;
			
			retorno = (Integer) session.createQuery(consulta)
						.setInteger("idImovel", idImovel)
						.setInteger("idCliente",idCliente)
						.setInteger("idClienteRelacaoTipo", idClienteRelacaoTipo).uniqueResult();
			
			return retorno >0;
			
		} catch (HibernateException e) {
			logger.error("Erro ao pesquisar relacao existente de cliente imovel.", e);
			throw new ErroRepositorioException("Erro ao pesquisar relacao existente de cliente imovel.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ClienteImovelRetorno> obterClientesParaIncluir() throws ErroRepositorioException {
		
		Collection<ClienteImovelRetorno> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " select clienteImovelRetorno "
					+ "from ClienteImovelRetorno clienteImovelRetorno "
					+ " inner join fetch clienteImovelRetorno.clienteRelacaoTipo clienteRelacaoTipo "
					+ " where clienteImovelRetorno.cliente.id = " + ConstantesSistema.ZERO
					+ " and clienteImovelRetorno.imovel.id in "
						+ " ( select imovelControle.imovelRetorno.idImovel from ImovelControleAtualizacaoCadastral imovelControle, Imovel imovel "
						+ " where imovelControle.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO  
						+ " and imovel.id = imovelControle.imovel.id  "
						+ " and imovelControle.dataProcessamento is null ) " ;
			
			
			retorno = (Collection<ClienteImovelRetorno>) session.createQuery(consulta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<IClienteImovel> obterClientesParaExcluirRelacao() throws ErroRepositorioException {
		
		Collection<IClienteImovel> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		String subqueryImoveisAprovados = "select imovelControle.imovel.id "
						+ " from ImovelControleAtualizacaoCadastral imovelControle, Imovel imovel, ImovelRetorno imovelRetorno "
						+ " where imovelControle.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO  
						+ " and imovel.id = imovelControle.imovel.id  "
						+ " and imovelRetorno.id = imovelControle.imovelRetorno.id "
						+ " and imovelControle.dataProcessamento is null "
						+ " and imovelRetorno.tipoOperacao <> " + AlteracaoTipo.INCLUSAO;
		
		String subqueryClientesImovelRetorno = " select clienteImovelRetorno.cliente.id "
					+ "from ClienteImovelRetorno clienteImovelRetorno, Cliente cliente "
					+ " where clienteImovelRetorno.cliente.id = cliente.id "
					+ " and clienteImovelRetorno.imovel.id in "
					+ " ( " + subqueryImoveisAprovados + " ) ";
		try {
			consulta = " select clienteImovel " 
						+ " from ClienteImovel clienteImovel "
						+ " inner join fetch clienteImovel.imovel imovel "
						+ " inner join fetch clienteImovel.cliente cliente "
						+ " inner join fetch clienteImovel.clienteRelacaoTipo clienteRelacaoTipo "
						+ " where clienteImovel.imovel.id in ("+ subqueryImoveisAprovados +")"
						+ " and clienteImovel.dataFimRelacao is null "
						+ " and clienteImovel.cliente.id not in ("+ subqueryClientesImovelRetorno +")";
			
			retorno = (Collection<IClienteImovel>) session.createQuery(consulta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public void aprovarImoveis(Collection<IImovel> listaImoveis) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try {
			Date dataAprovacao = new Date();
			
			consulta = "update ImovelControleAtualizacaoCadastral controle "
						+ " set controle.situacaoAtualizacaoCadastral.id = :situacaoAprovado ,"
						+ " controle.dataAprovacao = :dataAprovacao "  
						+ " where controle.imovelRetorno.id in (:listaImoveis) ";
			
			session.createQuery(consulta)
						.setInteger("situacaoAprovado", SituacaoAtualizacaoCadastral.APROVADO)
						.setDate("dataAprovacao", dataAprovacao)
						.setParameterList("listaImoveis", getIdsImovelRetorno(listaImoveis))   .executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao aprovar imóveis.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public Integer obterquantidadeImoveisAprovadosArquivo(Integer idArquivoAtualizacaoCadastral) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		
		try {
			
			consulta = " select count(imovelRetorno) "
					+ "from ImovelRetorno imovelRetorno, "
					+ " ArquivoTextoAtualizacaoCadastral arquivo, "
					+ " ImovelControleAtualizacaoCadastral imovelControle "
					+ " where imovelRetorno.idRota = arquivo.rota.id "
					+ " and imovelControle.imovelRetorno.id = imovelRetorno.id "
					+ " and imovelControle.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO  
					+ " and arquivo.id = :idArquivo " ;
			
			retorno = (Integer) session.createQuery(consulta).setInteger("idArquivo",  idArquivoAtualizacaoCadastral).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis aprovados para tela de análise.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public Integer obterquantidadeImoveisComAnormalidadeArquivo(Integer idArquivoAtualizacaoCadastral) throws ErroRepositorioException{
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		
		try {
			
			consulta = " select count(imovelRetorno) "
					+ "from ImovelRetorno imovelRetorno, "
					+ " ArquivoTextoAtualizacaoCadastral arquivo, "
					+ " ImovelControleAtualizacaoCadastral imovelControle, "
					+ " CadastroOcorrencia cadastroOcorrencia"
					+ " where imovelRetorno.idRota = arquivo.rota.id "
					+ " and imovelControle.imovelRetorno.id = imovelRetorno.id "
					+ " and imovelControle.cadastroOcorrencia.id = cadastroOcorrencia.id "
					+ " and cadastroOcorrencia.indicadorValidacao = " + ConstantesSistema.NAO
					+ " and arquivo.id = :idArquivo " ;
			
			retorno = (Integer) session.createQuery(consulta).setInteger("idArquivo",  idArquivoAtualizacaoCadastral).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis com anormalidade para tela de análise.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public Integer obterquantidadeImoveisComAlteracaoFaturamentoArquivo(Integer idArquivoAtualizacaoCadastral, String colunaAlteracao) throws ErroRepositorioException{
		
		Integer retorno = new Integer(0);
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		
		try {
			
			consulta = " select count(imovelRetorno) "
					+ "from ImovelRetorno imovelRetorno, "
					+ " ArquivoTextoAtualizacaoCadastral arquivo, "
					+ " ImovelControleAtualizacaoCadastral imovelControle, "
					+ " TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral, "
					+ " TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral, "
					+ " TabelaColuna tabelaColuna "
					+ " where imovelRetorno.idRota = arquivo.rota.id "
					+ " and imovelControle.imovelRetorno.id = imovelRetorno.id "
					+ " and tabelaAtualizacaoCadastral.codigoImovel = imovelRetorno.idImovel "
					+ " and tabelaColunaAtualizacaoCadastral.tabelaAtualizacaoCadastral.id = tabelaAtualizacaoCadastral.id "
					+ " and tabelaColunaAtualizacaoCadastral.tabelaColuna.id = tabelaColuna.id"
					+ " and tabelaColuna.nomeAbreviado like :colunaAlteracao "  
					+ " and arquivo.id = :idArquivo " ;
			
			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idArquivo",  idArquivoAtualizacaoCadastral)
					.setString("colunaAlteracao", colunaAlteracao).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis com alteração de hidrômetro para tela de análise.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	public Collection pesquisarDadosFichaFiscalizacaoCadastral(List<Integer> listaIdImoveis) throws ErroRepositorioException {
		
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			consulta = "SELECT imac.imov_id as idImovel, "
				+ "loca.loca_nmlocalidade as nomeLocalidade, "
				+ "imac.imac_cdsetorcomercial as codigoSetor, "
				+ "imac.imac_nnquadra as numeroQuadra, " 
				+ "imac.imac_nnlote as numeroLote, "
				+ "imac.imac_nnsublote as numeroSublote, "        
				+ "imac.imac_dslogradouro as descricaoLogradouroImovel, "       
				+ "imac.logr_id as idLogradouroImovel, "
				+ "imac.imac_nnimovel as numeroImovel, " 
				+ "imac.imac_dscomplementoendereco as complementoEnderecoImovel, "
				+ "imac.imac_nmbairro as bairroImovel, "
				+ "imac.imac_cdcep as cepImovel, "
				+ "rota.rota_cdrota as codigoRota, "
				+ "face.qdfa_nnfacequadra as numeroFace, "
				+ "imac.imac_nmmunicipio as nomeMunicipioImovel, "
				+ "imac.muni_id as idMunicipioImovel, "
			    + "clac.clie_id as idCliente, "
			    + "clac.clac_nmcliente as nomeCliente, "
			    + "clac.clac_nncpfcnpj as cpfCnpj, "
			    + "clac.clac_nnrg as rg, "
			    + "clac.clac_dsufsiglaoerg as uf, "
			    + "clac.psex_id as sexo, "
			    + "clac.clac_dslogradouro as descricaoLogradouroCliente, "
			    + "clac.clac_nnimovel as numeroImovelCliente, "
			    + "clac.edtp_id as enderecoTipoCliente, "
			    + "clac.clac_nmmunicipio as nomeMunicipioCliente, "
			    + "clac.clac_dscomplementoendereco as complementoEnderecoCliente, "
			    + "clac.clac_nmbairro as bairroCliente, "
			    + "clac.clac_cdcep as cepCliente, "
			    + "clac.clac_dsemail as emailCliente, "
			    + "cfac_cdddd as ddd, "
			    + "CASE WHEN fnet_id IN (1,2,4) THEN cfac.cfac_nnfone END as telefone, "
			    + "CASE WHEN fnet_id = 3 THEN cfac.cfac_nnfone END as celular "
			    + "FROM cadastro.imovel_atlz_cadastral imac "
			    + "INNER JOIN atualizacaocadastral.imovel_controle_atlz_cad icac ON icac.imov_id = imac.imov_id "
			    + "INNER JOIN cadastro.imovel imov ON imov.imov_id = imac.imov_id "
			    + "INNER JOIN cadastro.quadra qdra ON qdra.qdra_id = imov.qdra_id "
			    + "INNER JOIN cadastro.quadra_face face ON face.qdra_id = qdra.qdra_id "
			    + "INNER JOIN micromedicao.rota rota ON rota.rota_id = qdra.rota_id "
			    + "INNER JOIN cadastro.localidade loca ON loca.loca_id = imac.loca_id "
			    + "INNER JOIN cadastro.cliente_atlz_cadastral clac ON clac.imov_id = imac.imov_id "
			    + "LEFT JOIN cadastro.cliente_fone_atlz_cad cfac ON cfac.clac_id = clac.clac_id "
			    + "WHERE icac.siac_id = :situacao "
			    + "AND imac.imov_id IN (:listaIdImoveis)";
			
			retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("nomeLocalidade", Hibernate.STRING)
					.addScalar("codigoSetor", Hibernate.INTEGER)
					.addScalar("numeroQuadra", Hibernate.INTEGER)
					.addScalar("numeroLote", Hibernate.INTEGER)
					.addScalar("numeroSublote", Hibernate.INTEGER)
					.addScalar("descricaoLogradouroImovel", Hibernate.STRING)
					.addScalar("idLogradouroImovel", Hibernate.INTEGER)
					.addScalar("numeroImovel", Hibernate.STRING)
					.addScalar("complementoEnderecoImovel", Hibernate.STRING)
					.addScalar("bairroImovel", Hibernate.STRING)
					.addScalar("cepImovel", Hibernate.INTEGER)
					.addScalar("codigoRota", Hibernate.INTEGER)
					.addScalar("numeroFace", Hibernate.INTEGER)
					.addScalar("nomeMunicipioImovel", Hibernate.STRING)
					.addScalar("idMunicipioImovel", Hibernate.INTEGER)
					.addScalar("idCliente", Hibernate.INTEGER)
					.addScalar("nomeCliente", Hibernate.STRING)
					.addScalar("cpfCnpj", Hibernate.STRING)
					.addScalar("rg", Hibernate.STRING)
					.addScalar("uf", Hibernate.STRING)
					.addScalar("sexo", Hibernate.INTEGER)
					.addScalar("descricaoLogradouroCliente", Hibernate.STRING)
					.addScalar("numeroImovelCliente", Hibernate.STRING)
					.addScalar("enderecoTipoCliente", Hibernate.INTEGER)
					.addScalar("nomeMunicipioCliente", Hibernate.STRING)
					.addScalar("complementoEnderecoCliente", Hibernate.STRING)
					.addScalar("bairroCliente", Hibernate.STRING)
					.addScalar("cepCliente", Hibernate.INTEGER)
					.addScalar("emailCliente", Hibernate.STRING)
					.addScalar("ddd", Hibernate.STRING)
					.addScalar("telefone", Hibernate.STRING)
					.addScalar("celular", Hibernate.STRING)
					.setInteger("situacao", SituacaoAtualizacaoCadastral.EM_FISCALIZACAO)
					.setParameterList("listaIdImoveis", listaIdImoveis)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis para fiscalizacao.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public Collection pesquisarDadosImoveisPorRotaAtualizacaoCadastral(String idLocalidade, String cdSetorComercial, String cdRota)
			throws ErroRepositorioException {
		
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		
		try {
			String consultaImovelControle = ""
					+ " SELECT icac.imov_id "
					+ " FROM atualizacaocadastral.imovel_controle_atlz_cad icac "
					+ " INNER JOIN cadastro.imovel i ON i.imov_id = icac.imov_id "
	  	            + " INNER JOIN cadastro.localidade l ON l.loca_id = i.loca_id "
	  	            + " INNER JOIN cadastro.setor_comercial st ON st.stcm_id = i.stcm_id " 
	  	            + " INNER JOIN cadastro.quadra q ON q.qdra_id = i.qdra_id " 
	  	            + " INNER JOIN micromedicao.rota r ON r.rota_id = q.rota_id " 
	  	            + " WHERE i.loca_id = :idLocalidade AND st.stcm_cdsetorcomercial = :cdSetorComercial AND r.rota_cdRota = :cdRota ";
			
			String consultaImoveisForaDoRecadastramento = ""
					+ " SELECT i.imov_id as idImovel, "
					+ " 	   i.loca_id as idLocalidade, "
					+ " 	   l.loca_nmlocalidade as nomeLocalidade, "
					+ " 	   st.stcm_cdsetorcomercial as codigoSetorComercial, "
					+ " 	   q.qdra_nnquadra as numQuadra, "
					+ " 	   i.imov_nnlote as numLote, "
					+ " 	   i.imov_nnsublote as numSubLote, "
					+ " 	   la.last_dsligacaoaguasituacao as descSituacaoLigacaoAgua, "
					+ " 	   'NÃO ESTÁ EM RECADASTRAMENTO' as descSituacaoImovelRecadastramento, "
					+ " 	   0 as tipoOperacao, "
					+ " 	   0 as idImovelRetorno "
					+ " FROM cadastro.imovel i "
					+ " INNER JOIN atendimentopublico.ligacao_agua_situacao la ON i.last_id = la.last_id " 
					+ " INNER JOIN cadastro.localidade l ON i.loca_id = l.loca_id "
					+ " INNER JOIN cadastro.setor_comercial st ON st.stcm_id = i.stcm_id " 
					+ " INNER JOIN cadastro.quadra q ON q.qdra_id = i.qdra_id "
					+ " INNER JOIN micromedicao.rota r ON r.rota_id = q.rota_id " 
					+ " WHERE i.loca_id = :idLocalidade AND st.stcm_cdsetorcomercial = :cdSetorComercial AND r.rota_cdRota = :cdRota "
					+ " AND imov_icexclusao = :indicadorExclusao "
					+ " AND imov_id NOT IN (" + consultaImovelControle + ")";
			
			
			String consultaImovelControleComRetorno = ""
					+ " SELECT icac.imov_id "
					+ " FROM atualizacaocadastral.imovel_controle_atlz_cad icac "
					+ " INNER JOIN cadastro.imovel i ON i.imov_id = icac.imov_id "
					+ " INNER JOIN atualizacaocadastral.imovel_retorno ir ON ir.imov_id = icac.imov_id "
	  	            + " INNER JOIN cadastro.localidade l ON l.loca_id = i.loca_id "
	  	            + " INNER JOIN cadastro.setor_comercial st ON st.stcm_id = i.stcm_id " 
	  	            + " INNER JOIN cadastro.quadra q ON q.qdra_id = i.qdra_id " 
	  	            + " INNER JOIN micromedicao.rota r ON r.rota_id = q.rota_id " 
	  	            + " WHERE i.loca_id = :idLocalidade AND st.stcm_cdsetorcomercial = :cdSetorComercial AND r.rota_cdRota = :cdRota ";
			
			String consultaImoveisRecadastramento = ""
					+ "SELECT i.imov_id as idImovel, "
					+ "       i.loca_id as idLocalidade, "
					+ "       l.loca_nmlocalidade as nomeLocalidade, "
					+ "       st.stcm_cdsetorcomercial as codigoSetorComercial, "
					+ "       q.qdra_nnquadra as numQuadra, "
					+ "       i.imov_nnlote as numLote, "
					+ "       i.imov_nnsublote as numSubLote, "
					+ "       la.last_dsligacaoaguasituacao as descSituacaoLigacaoAgua, "
					+ "       sac.siac_dssituacao as descSituacaoImovelRecadastramento, "
					+ "       0 as tipoOperacao, "
					+ "       0 as idImovelRetorno "
					+ " FROM cadastro.imovel i "
					+ " INNER JOIN atualizacaocadastral.imovel_controle_atlz_cad icac ON icac.imov_id = i.imov_id "
					+ " INNER JOIN cadastro.situacao_atlz_cadastral sac on icac.siac_id = sac.siac_id "
					+ " INNER JOIN atendimentopublico.ligacao_agua_situacao la ON i.last_id = la.last_id " 
					+ " INNER JOIN cadastro.localidade l ON i.loca_id = l.loca_id "
					+ " INNER JOIN cadastro.setor_comercial st ON st.stcm_id = i.stcm_id " 
					+ " INNER JOIN cadastro.quadra q ON q.qdra_id = i.qdra_id "
					+ " INNER JOIN micromedicao.rota r ON r.rota_id = q.rota_id " 
					+ " WHERE i.loca_id = :idLocalidade AND st.stcm_cdsetorcomercial = :cdSetorComercial AND r.rota_cdRota = :cdRota "
					+ " AND imov_icexclusao = :indicadorExclusao "
					+ " AND i.imov_id NOT IN ("+ consultaImovelControleComRetorno + ")";

			String consultarImoveisRetorno = ""
					+ "SELECT ir.imov_id as idImovel, "
					+ "       ir.loca_id as idLocalidade, "
					+ "       l.loca_nmlocalidade as nomeLocalidade, "
					+ "       imre_cdsetorcomercial as codigoSetorComercial, "
					+ "       imre_nnquadra as numQuadra, "
					+ "       i.imov_nnlote as numLote, "
					+ "       i.imov_nnsublote as numSubLote, "
					+ "       la.last_dsligacaoaguasituacao as descSituacaoLigacaoAgua, "
					+ "       sac.siac_dssituacao as descSituacaoImovelRecadastramento, "
					+ "       ir.imac_tipooperacao as tipoOperacao, "
					+ "       ir.imre_id as idImovelRetorno "
					+ " FROM atualizacaocadastral.imovel_retorno ir " 
					+ " INNER JOIN cadastro.imovel i on i.imov_id = ir.imov_id "
					+ " INNER JOIN atendimentopublico.ligacao_agua_situacao la on la.last_id = ir.last_id "
					+ " INNER JOIN atualizacaocadastral.imovel_controle_atlz_cad icac on icac.imov_id = ir.imov_id "
					+ " INNER JOIN cadastro.situacao_atlz_cadastral sac on sac.siac_id = icac.siac_id "
					+ " INNER JOIN micromedicao.rota r on r.rota_id = ir.rota_id "
					+ " INNER JOIN cadastro.localidade l on l.loca_id = ir.loca_id "
					+ " WHERE ir.loca_id = :idLocalidade AND ir.imre_cdsetorcomercial = :cdSetorComercial AND r.rota_cdRota = :cdRota ";
			
			String consultaPrincipal = ""
					+ " SELECT idImovel, "
					+ "        idLocalidade, "
					+ "        nomeLocalidade, "
					+ "        codigoSetorComercial, "
					+ "        numQuadra, "
					+ "        numLote, "
					+ "        numSubLote, "
					+ "        descSituacaoLigacaoAgua, "
					+ "        descSituacaoImovelRecadastramento, "
					+ "        tipoOperacao, "
					+ "        idImovelRetorno "
					+ " FROM ("
					+ consultaImoveisForaDoRecadastramento
					+ " UNION "
					+ consultaImoveisRecadastramento
					+ " UNION "
					+ consultarImoveisRetorno
					+ ") as imoveis "
					+ " ORDER BY idLocalidade, codigoSetorComercial, numQuadra, numLote, numSubLote";
			
			retorno = (Collection) session.createSQLQuery(consultaPrincipal)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("nomeLocalidade", Hibernate.STRING)
					.addScalar("codigoSetorComercial", Hibernate.INTEGER)
					.addScalar("numQuadra", Hibernate.INTEGER)
					.addScalar("numLote", Hibernate.INTEGER)
					.addScalar("numSubLote", Hibernate.INTEGER)
					.addScalar("descSituacaoLigacaoAgua", Hibernate.STRING)
					.addScalar("descSituacaoImovelRecadastramento", Hibernate.STRING)
					.addScalar("tipoOperacao", Hibernate.INTEGER)
					.addScalar("idImovelRetorno", Hibernate.INTEGER)
					.setInteger("idLocalidade", Integer.parseInt(idLocalidade))
					.setInteger("cdSetorComercial", Integer.parseInt(cdSetorComercial))
					.setInteger("cdRota", Integer.parseInt(cdRota))
					.setShort("indicadorExclusao", ConstantesSistema.NAO)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ImovelSubcategoriaRetorno> pesquisarSubcategoriasImovelRetorno(Integer idImovelRetorno) throws ErroRepositorioException { 
		Collection<ImovelSubcategoriaRetorno> retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		try {
			
			consulta = "from ImovelSubcategoriaRetorno imovelSubcategoria "
					+ " inner join fetch imovelSubcategoria.subcategoria subcategoria "
					+ " inner join fetch subcategoria.categoria categoria "
					+ " where imovelSubcategoria.idImovelRetorno = :idImovelRetorno " ;
			
			retorno = (Collection<ImovelSubcategoriaRetorno>) session.createQuery(consulta).setInteger("idImovelRetorno", idImovelRetorno).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel subcategoria.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> pesquisarOcupantesAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException{
        Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> retorno = null;
        Session session = HibernateUtil.getSession();
        
        StringBuilder consulta = new StringBuilder();
        try {
            
            consulta.append("from ImovelTipoOcupanteQuantidadeAtualizacaoCadastral e ")
                .append(" inner join fetch e.tipoOcupante")
                .append(" where e.imovel.id = :idImovel ") ;
            
            retorno = (Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral>) session.createQuery(consulta.toString()).setInteger("idImovel", idImovel).list();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        
        return retorno;
	}
}
