package gcom.atualizacaocadastral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.logging.Logger;

import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.IImovelTipoOcupanteQuantidade;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.cadastro.imovel.Subcategoria;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarGerarLoteAtualizacaoCadastralActionHelper;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.util.CollectionUtil;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.IoUtil;

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
	public Collection<IImovel> obterImoveisParaAtualizar(Integer tipoOperacao, Integer idRota) throws ErroRepositorioException {

		Collection<IImovel> retorno = null;
		Session session = HibernateUtil.getSession();

		StringBuilder consulta = new StringBuilder();

		try {

			consulta.append(" select imovelRetorno ")
					.append(" from ImovelRetorno imovelRetorno ")
					.append(" left join fetch imovelRetorno.ramalLocalInstalacao ")
					.append(" where imovelRetorno.tipoOperacao = :tipoOperacao ")
					.append(" and imovelRetorno.idRota = :idRota ")
					.append(" and imovelRetorno.id in ")
						.append(" ( select imovelControle.imovelRetorno.id from ImovelControleAtualizacaoCadastral imovelControle ")
						.append(" where imovelControle.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO)
						.append(" and imovelControle.dataLiberacaoProcessamento <= :dataLiberacao")
						.append(" and imovelControle.dataProcessamento is null ) ");

			retorno = (Collection<IImovel>) session.createQuery(consulta.toString())
					.setInteger("tipoOperacao", tipoOperacao)
					.setInteger("idRota", idRota)
					.setDate("dataLiberacao", new Date())
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao obter imoveis para atualizar");
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


    @SuppressWarnings("unchecked")
	public Collection<IImovelTipoOcupanteQuantidade> obterImovelQuantidadesOcupantesParaAtualizar(Integer idImovel) throws ErroRepositorioException {
        Collection<IImovelTipoOcupanteQuantidade> retorno = null;
        Session session = HibernateUtil.getSession();

        try {
            String consulta = "from ImovelTipoOcupanteQuantidadeRetorno e where e.imovel.id = :idImovel " ;

            retorno = (Collection<IImovelTipoOcupanteQuantidade>) session.createQuery(consulta).setInteger("idImovel", idImovel).list();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar quantidades de ocupantes em retorno.");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }

	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		try {

			String consulta = "SELECT icac FROM ImovelControleAtualizacaoCadastral icac "
					+ "LEFT JOIN FETCH icac.imovel imovel "
					+ "LEFT JOIN FETCH icac.situacaoAtualizacaoCadastral situacao "
					+ "LEFT JOIN FETCH icac.cadastroOcorrencia cadastroOcorrencia "
					+ "WHERE icac.imovel.id = :idImovel ";

			return (ImovelControleAtualizacaoCadastral) session.createQuery(consulta).setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar controle de atualizacao cadastral");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer id, Integer tipoOperacao) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		try {

			String consulta = "SELECT icac FROM ImovelControleAtualizacaoCadastral icac "
					+ "LEFT JOIN FETCH icac.imovel imovel "
					+ "LEFT JOIN FETCH icac.situacaoAtualizacaoCadastral situacao "
					+ "LEFT JOIN FETCH icac.cadastroOcorrencia cadastroOcorrencia ";

			if (tipoOperacao.intValue() == AlteracaoTipo.INCLUSAO.intValue()) {
				consulta += "WHERE icac.id = :id ";
			} else {
				consulta += "WHERE icac.imovel.id = :id ";
			}

			return (ImovelControleAtualizacaoCadastral) session.createQuery(consulta).setInteger("id", id).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar controle de atualizacao cadastral");
		} finally {
			HibernateUtil.closeSession(session);
		}
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
	public Collection<IClienteFone> obterClienteFoneParaAtualizar(Integer idImovel, Integer idCliente) throws ErroRepositorioException {
		Collection<IClienteFone> retorno = null;
		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {
			consulta = "select clienteFoneRetorno "
					+ "from ClienteFoneRetorno clienteFoneRetorno, "
					+ " ClienteImovelRetorno clienteImovelRetorno"
					+ " inner join clienteFoneRetorno.cliente cliente "
					+ " where clienteFoneRetorno.idClienteRetorno = clienteImovelRetorno.idClienteRetorno "
					+ " and clienteImovelRetorno.imovel.id = :idImovel "
					+ " and clienteImovelRetorno.cliente.id = :idCliente ";

			retorno = (Collection<IClienteFone>) session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.setInteger("idCliente", idCliente)
					.list();
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
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel atualizacao cadastral");
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
			retorno = (Integer) session.createSQLQuery(consulta).addScalar("last_value", Hibernate.INTEGER).setMaxResults(1).uniqueResult();
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
		Collection<ImovelControleAtualizacaoCadastral> retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imovelControle "
							+ "from ImovelControleAtualizacaoCadastral imovelControle "
							+ "inner join imovelControle.imovelRetorno imovelRetorno "
							+ "where imovelRetorno.id in (:listaImoveisRetorno)";

			retorno = (Collection<ImovelControleAtualizacaoCadastral>) session.createQuery(consulta)
					.setParameterList("listaImoveisRetorno", getIdsImovelRetorno(listaImoveisRetorno))
					.list();

		} catch (HibernateException e) {
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public ImovelControleAtualizacaoCadastral obterImovelControlePorImovelRetorno(Integer idImovelRetorno) {
		ImovelControleAtualizacaoCadastral imovelControle = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imovelControle "
							+ "from ImovelControleAtualizacaoCadastral imovelControle "
							+ "inner join imovelControle.imovelRetorno imovelRetorno "							
							+ "where imovelRetorno.id = :idImovelRetorno)";

			imovelControle = (ImovelControleAtualizacaoCadastral)session.createQuery(consulta)
					.setInteger("idImovelRetorno", idImovelRetorno)
					.setMaxResults(1)
					.uniqueResult();
			
		} catch (HibernateException e) {
		} finally {
			HibernateUtil.closeSession(session);
		}
		return imovelControle;
	}

	public ImovelControleAtualizacaoCadastral obterImovelControle(Integer idImovel) {
		ImovelControleAtualizacaoCadastral imovelControle = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imovelControle "
							+ " from ImovelControleAtualizacaoCadastral imovelControle "
							+ " left join imovelControle.imovelRetorno imovelRetorno "
							+ " left join fetch imovelControle.cadastroOcorrencia cadastroOcorrencia "
							+ " where imovelControle.imovel.id = :idImovel ";
			
			imovelControle = (ImovelControleAtualizacaoCadastral)session.createQuery(consulta)
								.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();
			
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
				.append(" left join fetch sub.categoria categoria ")
				.append(" left join fetch sub.subcategoria subcategoria ")
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
	public Collection<ClienteImovelRetorno> obterClientesPorTipoOperacao(Integer idRota, Integer tipoOperacao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			StringBuilder subquery = new StringBuilder();
			subquery.append("SELECT imovelControle.imovel.id ")
					.append("FROM ImovelControleAtualizacaoCadastral imovelControle, Imovel imovel, ImovelRetorno imovelRetorno ")
					.append("WHERE imovel.id = imovelControle.imovel.id ")
					.append("AND imovelRetorno.id = imovelControle.imovelRetorno.id ")
					.append("AND imovelControle.situacaoAtualizacaoCadastral.id = :situacao ")
					.append("AND imovelRetorno.idRota = :idRota ")
					.append("AND imovelControle.dataLiberacaoProcessamento <= :dataLiberacao ")
					.append("AND imovelControle.dataProcessamento IS NULL") ;
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT clienteImovelRetorno ")
				 .append("FROM ClienteImovelRetorno clienteImovelRetorno ")
				 .append("INNER JOIN FETCH clienteImovelRetorno.cliente cliente ")
				 .append("INNER JOIN FETCH clienteImovelRetorno.clienteRelacaoTipo clienteRelacaoTipo ")
				 .append("WHERE clienteImovelRetorno.imovel.id in (" + subquery.toString() + ") ");
			
			if (tipoOperacao.intValue() == AlteracaoTipo.ALTERACAO) {
				query.append("AND clienteImovelRetorno.cliente.id > 0");
			} else if (tipoOperacao.intValue() == AlteracaoTipo.INCLUSAO) {
				query.append("AND clienteImovelRetorno.cliente.id = 0");
			}
					
			return (Collection<ClienteImovelRetorno>) session.createQuery(query.toString())
					.setInteger("situacao", SituacaoAtualizacaoCadastral.APROVADO)
					.setInteger("idRota", idRota)
					.setDate("dataLiberacao", new Date())
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao obter clientes para atualizar");
		} finally {
			HibernateUtil.closeSession(session);
		}
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
										.setInteger("idClienteRelacaoTipo", clienteImovelRetorno.getClienteRelacaoTipo().getId()).setMaxResults(1).uniqueResult();
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
			
			retorno = (IImovel)session.createQuery(consulta).setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();
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
					+ " inner join fetch clienteFoneRetorno.foneTipo foneTipo"
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
					+ " left join fetch clienteEnderecoRetorno.enderecoTipo enderecoTipo "
					+ " left join fetch clienteEnderecoRetorno.logradouroTipo logradouroTipo "
					+ " where clienteEnderecoRetorno.idClienteRetorno = :idClienteRetorno ";

			retorno = (Collection<IClienteEndereco>)session.createQuery(consulta).setInteger("idClienteRetorno", idClienteRetorno).list();
		}catch(HibernateException e) {
			e.printStackTrace();
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
						.setInteger("idClienteRelacaoTipo", idClienteRelacaoTipo).setMaxResults(1).uniqueResult();
			return retorno >0;

		} catch (HibernateException e) {
			logger.error("Erro ao pesquisar relacao existente de cliente imovel.", e);
			throw new ErroRepositorioException("Erro ao pesquisar relacao existente de cliente imovel.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<IClienteImovel> obterClientesParaExcluirRelacao(Integer idRota) throws ErroRepositorioException {

		Collection<IClienteImovel> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		StringBuilder subqueryImoveisAprovados = new StringBuilder();
		
		subqueryImoveisAprovados.append("select imovelControle.imovel.id ")
								.append(" from ImovelControleAtualizacaoCadastral imovelControle, Imovel imovel, ImovelRetorno imovelRetorno ")
								.append(" where imovelControle.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO)
								.append(" and imovel.id = imovelControle.imovel.id  ")
								.append(" and imovelRetorno.id = imovelControle.imovelRetorno.id ")
								.append(" and imovelControle.dataProcessamento is null ")
								.append(" and imovelRetorno.idRota = :idRota ")
								.append(" and imovelControle.dataLiberacaoProcessamento <= :dataLiberacao ")
								.append(" and imovelRetorno.tipoOperacao <> " + AlteracaoTipo.INCLUSAO);

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

			retorno = (Collection<IClienteImovel>) session.createQuery(consulta)
					.setInteger("idRota", idRota)
					.setDate("dataLiberacao", new Date())
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao obter clientes para excluir relacao");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void aprovarImovel(Integer idImovelRetorno, Date dataLiberacao) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		StringBuilder consulta = new StringBuilder();

		try {
			Date dataAprovacao = new Date();

			consulta.append("update ImovelControleAtualizacaoCadastral controle ")
					.append(" set controle.situacaoAtualizacaoCadastral.id = :situacaoAprovado ,")
					.append(" controle.dataAprovacao = :dataAprovacao, ")
					.append(" controle.dataLiberacaoProcessamento = :dataLiberacao ")
					.append(" where controle.imovelRetorno.id = :idImovel ");

			session.createQuery(consulta.toString())
						.setInteger("situacaoAprovado", SituacaoAtualizacaoCadastral.APROVADO)
						.setDate("dataAprovacao", dataAprovacao)
						.setDate("dataLiberacao", dataLiberacao)
						.setInteger("idImovel", idImovelRetorno).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao aprovar imoveis.");
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
			retorno = (Integer) session.createQuery(consulta).setInteger("idArquivo",  idArquivoAtualizacaoCadastral).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis aprovados para tela de an�lise.");
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
			
			retorno = (Integer) session.createQuery(consulta).setInteger("idArquivo",  idArquivoAtualizacaoCadastral).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis com anormalidade para tela de an�lise.");
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
					.setString("colunaAlteracao", colunaAlteracao).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis com altera��o de hidr�metro para tela de an�lise.");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosOriginaisFichaFiscalizacaoCadastral(List<Integer> listaIdImoveis) throws ErroRepositorioException {

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

	@SuppressWarnings("rawtypes")
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
					+ " 	   'N�O EST� EM RECADASTRAMENTO' as descSituacaoImovelRecadastramento, "
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

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public List<Integer> obterImoveisPorSituacao(Integer idArquivo, Integer idSituacao) throws ErroRepositorioException{
		List<Integer> retorno = null;
        Session session = HibernateUtil.getSession();

        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("select ic.imovel.id from ImovelControleAtualizacaoCadastral ic ")
            		.append("inner join ic.situacaoAtualizacaoCadastral situacao ")
            		.append("where situacao.id = :situacao ")
            		.append("and ic.imovel.id in (select idImovel from ImovelAtualizacaoCadastral where idArquivoTexto = :idArquivo ) ");


            retorno = (List<Integer>) session.createQuery(consulta.toString())
            		.setParameter("situacao", idSituacao)
            		.setParameter("idArquivo", idArquivo)
            		.list();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ImovelControleAtualizacaoCadastral> obterImoveisPorArquivoSituacaoLoteTrazendoInformativos(
			Integer idArquivo, List<Integer> situacoes, Date dataUltimaTransmissao, List<Integer> idsImoveis) throws ErroRepositorioException {
		
		List<ImovelControleAtualizacaoCadastral> retorno = null;
        Session session = HibernateUtil.getSession();

        try {
        	String consulta = montarQueryParaObterImoveisPorArquivoSituacaoLoteTrazendoInformativos(
        					idArquivo, situacoes, dataUltimaTransmissao, idsImoveis);
        	
            Query query = session.createSQLQuery(consulta)
            		.addEntity("imc", ImovelControleAtualizacaoCadastral.class)
            		.addJoin("atualizacao", "imc.imovelAtualizacaoCadastral")
            		.addScalar("informativo", Hibernate.INTEGER)
            		.addScalar("visitas", Hibernate.INTEGER)
            		.setParameterList("situacoes", situacoes)
            		.setInteger("idArquivo", idArquivo);
            
            if (CollectionUtil.naoEhVazia(idsImoveis)) {
            	query.setParameterList("ids", idsImoveis);
            } else if (dataUltimaTransmissao != null) {
        		query.setDate("dataUltimaTransmissao", dataUltimaTransmissao);
        	}
            
            List<Object[]> resultado = query.list();
            
			if (CollectionUtil.naoEhVazia(resultado)) {
				retorno = new ArrayList(resultado.size());
				
				for (Object[] array : resultado) {
					ImovelControleAtualizacaoCadastral controle = (ImovelControleAtualizacaoCadastral) array[2];
					controle.setImovelAtualizacaoCadastral((ImovelAtualizacaoCadastral) array[3]);
					controle.setQuantidadeVisitas((Integer) array[1]);
					controle.setInformativo((Integer) array[0]);
					
					if (dataUltimaTransmissao != null && controle.getQuantidadeVisitas().intValue() >= Visita.QUANTIDADE_MAXIMA_SEM_PRE_AGENDAMENTO) {
						controle.setInformativo(1);
					}
					
					retorno.add(controle);
				}
			}
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis da rota com imoveis informativos.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
	}
	
	private String montarQueryParaObterImoveisPorArquivoSituacaoLoteTrazendoInformativos(Integer idArquivo, List<Integer> situacoes, 
			Date dataUltimaTransmissao, List<Integer> idsImoveis) {
		StringBuilder consulta = new StringBuilder();
    	consulta.append("select {imc.*}, {atualizacao.*}, ");
    	consulta.append("       case when imc.siac_id in (:situacoes) ");
    	
    	if (CollectionUtil.naoEhVazia(idsImoveis)) {
    		consulta.append("   or imc.imov_id in (:ids) ");
    	} else if (dataUltimaTransmissao != null) {
    		consulta.append("   and ocorrencia.cocr_icvisita = 1 and imc.icac_tmretorno < :dataUltimaTransmissao ");
    	}
    	
    	consulta.append("       then 2 else 1 end as informativo, ");
    	consulta.append("       (select count(vist_id) from atualizacaocadastral.visita v where v.icac_id = imc.icac_id and v.vist_icexclusao = 'f') as visitas ");
    	consulta.append("from atualizacaocadastral.imovel_controle_atlz_cad imc ");
		consulta.append("left join cadastro.cadastro_ocorrencia ocorrencia on ocorrencia.cocr_id = imc.cocr_id ");
    	consulta.append("inner join cadastro.imovel imov on imov.imov_id = imc.imov_id ");
    	consulta.append("inner join cadastro.imovel_atlz_cadastral atualizacao on atualizacao.imov_id = imc.imov_id ");
    	consulta.append("where imc.imov_id in (select distinct imov_id from ( ");
    	consulta.append("select controle.imov_id as imov_id ");
    	consulta.append("from atualizacaocadastral.imovel_controle_atlz_cad controle ");
    	consulta.append("inner join cadastro.imovel_atlz_cadastral atlz on atlz.imov_id = controle.imov_id ");
    	consulta.append("inner join cadastro.arquivo_texto_atlz_cad arquivo on arquivo.txac_id = atlz.txac_id ");
    	consulta.append("inner join cadastro.imovel imovel on imovel.imov_id = controle.imov_id ");
    	consulta.append("inner join cadastro.quadra quadra on quadra.rota_id = arquivo.rota_id ");
    	consulta.append("where arquivo.txac_id = :idArquivo ");
    	
    	if (CollectionUtil.naoEhVazia(idsImoveis)) {
    		consulta.append("or controle.imov_id in (:ids) ");
    	}

    	consulta.append("order by quadra.qdra_id, imovel.imov_nnlote, imovel.imov_nnsublote) as imoveis) ");
    	return consulta.toString();
	}
	
	public TabelaColunaAtualizacaoCadastral obterTabelaColuna(
			TabelaColuna coluna, Integer idImovel, String complemento) throws ErroRepositorioException {
		TabelaColunaAtualizacaoCadastral retorno = null;
        Session session = HibernateUtil.getSession();
        
        StringBuilder sql = new StringBuilder();
        try {
        	sql.append("select colunaAtualizacao from TabelaColunaAtualizacaoCadastral colunaAtualizacao ")
               .append("inner join fetch colunaAtualizacao.tabelaAtualizacaoCadastral tabelaAtualizacaoCadastral ")
               .append("inner join tabelaAtualizacaoCadastral.tabela tabela ")
               .append("inner join colunaAtualizacao.tabelaColuna coluna ")
               .append("where tabela.id = :idTabela ")
               .append("and coluna.coluna like :nomeColuna ")
               .append("and tabelaAtualizacaoCadastral.codigoImovel = :idImovel ");
        	
        	if (StringUtils.isNotEmpty(complemento)) {
        		sql.append("and tabelaAtualizacaoCadastral.complemento = :complemento ");
        	}
        	
            Query query = session.createQuery(sql.toString())
            		.setInteger("idTabela", coluna.getTabela().getId())
            		.setString("nomeColuna", coluna.getDescricaoColuna())
            		.setInteger("idImovel", idImovel);
            
            if (StringUtils.isNotEmpty(complemento)) {
            	query.setString("complemento", complemento);
            }
            
            retorno = (TabelaColunaAtualizacaoCadastral) query.setMaxResults(1).uniqueResult();
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        
        return retorno;
	}
	
	public TabelaColunaAtualizacaoCadastral obterTabelaColuna(
			TabelaColuna coluna, Integer idImovel, String complemento, String complementoColuna) throws ErroRepositorioException {
		TabelaColunaAtualizacaoCadastral retorno = null;
        Session session = HibernateUtil.getSession();
        
        StringBuilder sql = new StringBuilder();
        try {
        	sql.append("select colunaAtualizacao from TabelaColunaAtualizacaoCadastral colunaAtualizacao ")
               .append("inner join fetch colunaAtualizacao.tabelaAtualizacaoCadastral tabelaAtualizacaoCadastral ")
               .append("inner join tabelaAtualizacaoCadastral.tabela tabela ")
               .append("inner join colunaAtualizacao.tabelaColuna coluna ")
               .append("where tabela.id = :idTabela ")
               .append("and coluna.coluna like :nomeColuna ")
               .append("and tabelaAtualizacaoCadastral.codigoImovel = :idImovel ");
        	
        	if (StringUtils.isNotEmpty(complemento)) {
        		sql.append("and tabelaAtualizacaoCadastral.complemento = :complemento ");
        	}
        	
        	if (StringUtils.isNotEmpty(complementoColuna)) {
        		sql.append("and colunaAtualizacao.complemento = :complementoColuna ");
        	}
        	
            Query query = session.createQuery(sql.toString())
            		.setInteger("idTabela", coluna.getTabela().getId())
            		.setString("nomeColuna", coluna.getDescricaoColuna())
            		.setInteger("idImovel", idImovel);
            
            if (StringUtils.isNotEmpty(complemento)) {
            	query.setString("complemento", complemento);
            }
            
            if (StringUtils.isNotEmpty(complementoColuna)) {
            	query.setString("complementoColuna", complementoColuna);
            }
            
            retorno = (TabelaColunaAtualizacaoCadastral) query.setMaxResults(1).uniqueResult();
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        
        return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<TabelaColunaAtualizacaoCadastral> obterTabelaColunas(TabelaColuna coluna, Integer idImovel) throws ErroRepositorioException {
		List<TabelaColunaAtualizacaoCadastral> retorno = null;
        Session session = HibernateUtil.getSession();
        
        StringBuilder sql = new StringBuilder();
        try {
        	sql.append("select colunaAtualizacao from TabelaColunaAtualizacaoCadastral colunaAtualizacao ")
               .append("inner join fetch colunaAtualizacao.tabelaAtualizacaoCadastral tabelaAtualizacaoCadastral ")
               .append("inner join tabelaAtualizacaoCadastral.tabela tabela ")
               .append("inner join colunaAtualizacao.tabelaColuna coluna ")
               .append("where tabela.id = :idTabela ")
               .append("and coluna.coluna like :nomeColuna ")
               .append("and tabelaAtualizacaoCadastral.codigoImovel = :idImovel ");
        	
            Query query = session.createQuery(sql.toString())
            		.setInteger("idTabela", coluna.getTabela().getId())
            		.setString("nomeColuna", coluna.getDescricaoColuna())
            		.setInteger("idImovel", idImovel);
            
            retorno = (List<TabelaColunaAtualizacaoCadastral>) query.list();
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        
        return retorno;
	}

	public void atualizarImovelRetorno(TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral, String campo, Integer tipoAlteracao) throws ErroRepositorioException {
        Session session = HibernateUtil.getSession();
        
        if (campo.equals("fiscalizado") || campo.equals("preaprovado")) {
        	try {
        		String update = this.montarUpdate(tabelaColunaAtualizacaoCadastral, campo, tipoAlteracao);
        		PreparedStatement st = null;
        		
        		try {	
        			Connection jdbcCon = session.connection();
        			
        			if (update != null) {
        				st = jdbcCon.prepareStatement(update);
        				
        				st.executeUpdate();
        			}
        		} catch (SQLException e) {
        			e.printStackTrace();
        			throw new ErroRepositorioException(e, "Erro no Hibernate");
        		} finally {
        			if (null != st)
        				try {
        					st.close();
        				} catch (SQLException e) {
        					throw new ErroRepositorioException(e, "Erro no Hibernate");
        				}
        			HibernateUtil.closeSession(session);
        		}
        		
        	} catch (HibernateException e) {
        		e.printStackTrace();
        		throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        	} finally {
        		HibernateUtil.closeSession(session);
        	}
        }

	}
	
	private String montarUpdate(TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral, String campo, Integer tipoAlteracao) throws ErroRepositorioException {
		Properties props = IoUtil.carregaPropriedades("tabela_retorno.properties");
		String update = new String();
		
		String tabelaDestino = props.getProperty(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getTabela().getNomeTabela());
		
		if (isColunaParaAtualizar(tabelaColunaAtualizacaoCadastral)) {
			if (isTabelaImovel(tabelaDestino))
				update = montarUpdateImovel(tabelaDestino, props, tabelaColunaAtualizacaoCadastral, campo);
			else if (isTabelaCliente(tabelaDestino))
				update = montarUpdateCliente(tabelaDestino, props, tabelaColunaAtualizacaoCadastral, campo);
			else if (isTabelaSubcategoria(tabelaDestino))
				update = montarUpdateSubcategoria(tabelaDestino, props, tabelaColunaAtualizacaoCadastral, campo, tipoAlteracao);
		}
		
		return update;
	}
	
	private boolean isTabelaSubcategoria(String nomeTabela) {
		return nomeTabela.equals("atualizacaocadastral.imovel_subcategoria_retorno");
	}
	
	private boolean isTabelaImovel(String nomeTabela) {
		return nomeTabela.equals("atualizacaocadastral.imovel_retorno") 
				|| nomeTabela.equals("atualizacaocadastral.imovel_ramo_atividade_retorno")
				|| nomeTabela.equals("atualizacaocadastral.imovel_tipo_ocupante_quantidade_retorno");
	}
	
	private boolean isTabelaCliente(String nomeTabela) {
		return nomeTabela.equals("atualizacaocadastral.cliente_retorno") 
				|| nomeTabela.equals("atualizacaocadastral.cliente_fone_retorno")
				|| nomeTabela.equals("atualizacaocadastral.cliente_endereco_retorno");
	}
	
	private String  oberColunaCpfCnpjAtualizacao(String valor) {
		return valor != null && valor.length() == 11 ? "clir_nncpf" : "clir_nncnpj"; 
	}
	
	private String montarUpdateCliente(String tabelaDestino, Properties props, TabelaColunaAtualizacaoCadastral tabelaColuna, String campo) {
		StringBuilder update = new StringBuilder();
		
		String colunaDestino = obterColunaDestinoCliente(props, tabelaColuna, campo);
		tabelaDestino = obterTabelaAtualizacaoCliente(tabelaDestino, tabelaColuna);
		
		String valor = obterValorParaAtualizarRetorno(colunaDestino, tabelaColuna.obterValorParaAtualizar(campo));

		if (valor != null && !isChaveEstrangeira(colunaDestino))
			update.append("update ").append(tabelaDestino).append(" set ").append(colunaDestino).append(" = ").append(valor)
				  .append(" where clir_id in ( select clir_id from atualizacaocadastral.cliente_imovel_retorno ")
								.append(" where imov_id = ").append(tabelaColuna.getTabelaAtualizacaoCadastral().getCodigoImovel())
								.append(" and clie_id =   ").append(tabelaColuna.getTabelaAtualizacaoCadastral().getCodigoCliente()).append(")");

		return update.toString();
	}

	private String obterColunaDestinoCliente(Properties props, TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral, String campo) {
		String colunaDestino;
		System.out.println(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna());
		if (tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clac_nncpfcnpj")) {
			colunaDestino = oberColunaCpfCnpjAtualizacao(tabelaColunaAtualizacaoCadastral.obterValorParaAtualizar(campo));
		} else {
			colunaDestino = props.getProperty(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna()); 
		}
		
		if (tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("crtp_id") 
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("psex_id") 
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("unfe_id")
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("cltp_id") 
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clie_id"))
			colunaDestino = tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna();
		return colunaDestino;
	}

	private boolean isColunaParaAtualizar(TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral) {
		return !tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clac_dsufsiglaoerg")
				&& !tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clac_dslogradourotipo")
				&& !tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("ratv_id")
				&& !tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("cocr_id")
				&& !tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("imac_nnlote")
				&& !tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("imac_dslogradourotipo")
				&& !tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("cfac_nnfoneramal");
	}

	private String obterTabelaAtualizacaoCliente(String tabelaDestino, TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral) {
		if (tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clac_dscomplementoendereco")
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clac_nnimovel")
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clac_nmmunicipio")
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clac_nmbairro")
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clac_dslogradouro")
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clac_cdcep")
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("lgtp_id"))
			tabelaDestino = "atualizacaocadastral.cliente_endereco_retorno";
		else if (tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("crtp_id"))
			tabelaDestino = "atualizacaocadastral.cliente_imovel_retorno";
		else if (tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clfr_cdddd")
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("clfr_nnfone")
				|| tabelaColunaAtualizacaoCadastral.getTabelaColuna().getColuna().equals("fnet_id"))
			tabelaDestino = "atualizacaocadastral.cliente_fone_retorno";
		return tabelaDestino;
	}
	
	private String montarUpdateImovel(String tabelaDestino, Properties props, TabelaColunaAtualizacaoCadastral tabelaColuna, String campo) {
		StringBuilder update = new StringBuilder();

		String colunaDestino = props.getProperty(tabelaColuna.getTabelaColuna().getColuna());
		String valor = obterValorParaAtualizarRetorno(colunaDestino, tabelaColuna.obterValorParaAtualizar(campo));
		
		update.append("update ").append(tabelaDestino)
			  .append(" set ").append(colunaDestino).append(" = ").append(valor)
			  .append(" where imov_id = ").append(tabelaColuna.getTabelaAtualizacaoCadastral().getCodigoImovel());
		
		return update.toString();
	}
	
	private String montarUpdateSubcategoria(String tabelaDestino, Properties props, TabelaColunaAtualizacaoCadastral tabelaColuna, String campo, Integer tipoAlteracao) throws ErroRepositorioException {
		StringBuilder sql = new StringBuilder();

		String valor = tabelaColuna.obterValorParaAtualizar(campo);
		
		Integer qtdEconomia = (valor == null ? 0 : new Integer(valor) );
		Integer idImovelRetorno = null; 
		
		if (existeSubcategoriaRetorno(tabelaColuna) ) {
			if (qtdEconomia > 0) {
				sql.append("update atualizacaocadastral.imovel_subcategoria_retorno ").append(" set isre_qteconomia = ").append(qtdEconomia);
			} else {
				sql.append("delete from atualizacaocadastral.imovel_subcategoria_retorno ");
			}
			sql.append(" where imov_id = ").append(tabelaColuna.getTabelaAtualizacaoCadastral().getCodigoImovel())
			.append(" and scat_id = ").append(Subcategoria.obterIdSubcategoria(tabelaColuna.getTabelaAtualizacaoCadastral().getComplemento()));
		} else if (qtdEconomia > 0) {
			
			if (tipoAlteracao != null && tipoAlteracao.intValue() == AlteracaoTipo.INCLUSAO.intValue()) {
				ImovelControleAtualizacaoCadastral controle = (ImovelControleAtualizacaoCadastral) pesquisarImovelControleAtualizacao(tabelaColuna.getTabelaAtualizacaoCadastral().getCodigoImovel(), tipoAlteracao);
				idImovelRetorno = controle.getImovelRetorno().getId();
			}else {
				ImovelRetorno retorno = (ImovelRetorno) pesquisarImovelRetorno(tabelaColuna.getTabelaAtualizacaoCadastral().getCodigoImovel());
				idImovelRetorno = retorno.getId();
			}
			sql.append("insert into atualizacaocadastral.imovel_subcategoria_retorno values ")
			.append("(nextval(\'atualizacaocadastral.sequence_imovel_subcategoria_retorno\'), ")
			.append(tabelaColuna.getTabelaAtualizacaoCadastral().getCodigoImovel()).append(", ")
			.append(Subcategoria.obterIdSubcategoria(tabelaColuna.getTabelaAtualizacaoCadastral().getComplemento())).append(", ")
			.append(qtdEconomia).append(", ")
			.append(idImovelRetorno).append(", ")
			.append("now())");
		}
			
		return sql.toString();
	}
	
	public boolean existeSubcategoriaRetorno(TabelaColunaAtualizacaoCadastral tabelaColuna) throws ErroRepositorioException {
		return existeSubcategoriaRetorno(tabelaColuna.getTabelaAtualizacaoCadastral().getCodigoImovel(), 
				Subcategoria.obterIdSubcategoria(tabelaColuna.getTabelaAtualizacaoCadastral().getComplemento()));
	}
	
	public boolean existeSubcategoriaRetorno(Integer idImovel, Integer idSubcategoria) throws ErroRepositorioException {
		ImovelSubcategoriaRetorno retorno = null;
		Session session = HibernateUtil.getSession();

		StringBuilder consulta = new StringBuilder();
		try {

			consulta.append("from ImovelSubcategoriaRetorno imovelSubcategoria ")
					.append(" inner join fetch imovelSubcategoria.subcategoria subcategoria ")
					.append(" inner join fetch subcategoria.categoria categoria ")
					.append(" where imovelSubcategoria.imovel.id = :idImovel ")
					.append(" and imovelSubcategoria.subcategoria.id = :subcategoria ");

			retorno = (ImovelSubcategoriaRetorno) session.createQuery(consulta.toString())
						.setInteger("idImovel", idImovel)
						.setInteger("subcategoria", idSubcategoria).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel subcategoria.");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno != null;
	}
	
	private String obterValorParaAtualizarRetorno(String nomeColuna, String valor) {
		String tipoDado = this.obterTipoDadoColuna(nomeColuna.trim());
		if (tipoDado == null) return valor;

		if (valor != null && !valor.isEmpty()) {
			if (tipoDado.contains("character"))
				return String.format("\'%s\'", valor);
			else if (tipoDado.equals("numeric"))
				return valor.replace(".","").replace(",",".");
			return valor;
		}

		if (valor == null || valor.isEmpty()) {
			if (isChaveEstrangeira(nomeColuna)) 
				return null;
			if (isTipoNumericoNoBancoDeDados(tipoDado))
				return "0";
			else 
				return "\'\'";
		}
		return valor;
	}
	
	private boolean isTipoNumericoNoBancoDeDados(String tipoDado) {
		return tipoDado.equals("bigint") || tipoDado.equals("smallint") || 
				tipoDado.equals("integer")  || tipoDado.equals("numeric");
	}
	
	private boolean isChaveEstrangeira(String nomeColuna) {
		return nomeColuna.endsWith("_id");
	}
	
	
	public void atualizarImovelParaSituacaoEmCampoPorArquivo(Integer idArquivo) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ImovelControleAtualizacaoCadastral controle ")
			.append("SET controle.situacaoAtualizacaoCadastral.id = :situacaoNova ")
			.append("WHERE controle.imovel.id IN (SELECT imovel.id FROM ImovelAtualizacaoCadastral imovel WHERE imovel.idArquivoTexto = :idArquivo) ")
			.append("AND controle.situacaoAtualizacaoCadastral.id = :situacaoAtual ");
			
			session.createQuery(sql.toString())
			.setInteger("idArquivo", idArquivo)
			.setInteger("situacaoNova", SituacaoAtualizacaoCadastral.EM_CAMPO)
			.setInteger("situacaoAtual", SituacaoAtualizacaoCadastral.DISPONIVEL)
			.executeUpdate();
			
			sql = new StringBuilder();
			sql.append("UPDATE ImovelAtualizacaoCadastral imovel ")
			.append("SET imovel.idSituacaoAtualizacaoCadastral = :situacaoNova ")
			.append("WHERE imovel.idArquivoTexto = :idArquivo ")
			.append("AND imovel.idSituacaoAtualizacaoCadastral = :situacaoAtual ");
			
			session.createQuery(sql.toString())
			.setInteger("idArquivo", idArquivo)
			.setInteger("situacaoNova", SituacaoAtualizacaoCadastral.EM_CAMPO)
			.setInteger("situacaoAtual", SituacaoAtualizacaoCadastral.DISPONIVEL)
			.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro atualizar situacao de imovel controle atualizacao cadastral por arquivo");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public boolean possuiInformacoesFiscalizacao(ImovelControleAtualizacaoCadastral imovelControle) throws ErroRepositorioException {

        Session session = HibernateUtil.getSession();
        Integer qtd = new Integer(0);
        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("select count(*) from TabelaColunaAtualizacaoCadastral colunaAtualizacao ")
            		.append("inner join colunaAtualizacao.tabelaAtualizacaoCadastral tabelaAtualizacaoCadastral ")
            		.append("where tabelaAtualizacaoCadastral.codigoImovel = :idImovel ")
            		.append("and  colunaAtualizacao.colunaValorFiscalizado is not null");
        	
            qtd = (Integer) session.createQuery(consulta.toString())
            		.setInteger("idImovel", imovelControle.getImovel().getId()).setMaxResults(1).uniqueResult();
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return qtd > 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<TabelaColunaAtualizacaoCadastral> obterColunasPreAprovadas(ImovelControleAtualizacaoCadastral imovelControle) throws ErroRepositorioException {
		List<TabelaColunaAtualizacaoCadastral> retorno = null;
        Session session = HibernateUtil.getSession();
        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("select colunaAtualizacao from TabelaColunaAtualizacaoCadastral colunaAtualizacao ")
            		.append("inner join fetch colunaAtualizacao.tabelaAtualizacaoCadastral tabelaAtualizacaoCadastral ")
            		.append("inner join fetch tabelaAtualizacaoCadastral.tabela tabela ")
            		.append("inner join fetch colunaAtualizacao.tabelaColuna coluna ")
            		.append("where tabelaAtualizacaoCadastral.codigoImovel = :idImovel ")
            		.append("and  colunaAtualizacao.colunaValorPreAprovado is not null");
        	
            retorno = (List<TabelaColunaAtualizacaoCadastral>) session.createQuery(consulta.toString())
            		.setInteger("idImovel", imovelControle.getImovel().getId()).list();
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
	}

	public TabelaAtualizacaoCadastral obterTabela(Tabela tabela, Integer idImovel, String complemento) throws ErroRepositorioException {
		TabelaAtualizacaoCadastral retorno = null;
        Session session = HibernateUtil.getSession();

        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("select tabelaAtualizacaoCadastral from TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral ")
            		.append("inner join tabelaAtualizacaoCadastral.tabela tabela ")
            		.append("where tabela.id = :idTabela ")
            		.append("and tabelaAtualizacaoCadastral.codigoImovel = :idImovel ");
        	
        	if (StringUtils.isNotEmpty(complemento))
        		consulta.append("and tabelaAtualizacaoCadastral.complemento = :complemento ");

            Query query = session.createQuery(consulta.toString())
            		.setInteger("idTabela", tabela.getId())
            		.setInteger("idImovel", idImovel);
            
            if (StringUtils.isNotEmpty(complemento))
            	query.setString("complemento", complemento);
            
            retorno = (TabelaAtualizacaoCadastral) query.setMaxResults(1).uniqueResult();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
	}
	
	private String obterTipoDadoColuna(String nomeColuna) {
		String retorno = null;
        Session session = HibernateUtil.getSession();
        
        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("SELECT distinct data_type FROM information_schema.columns where column_name = :nomeColuna ");
        	
            retorno = (String) session.createSQLQuery(consulta.toString())
            		.addScalar("data_type", Hibernate.STRING)
            		.setString("nomeColuna", nomeColuna).setMaxResults(1).uniqueResult();
            
        } catch (HibernateException e) {
            logger.error("Erro ao pesquisar tipos ocupantes.", e);
        } finally {
            HibernateUtil.closeSession(session);
        }
        
        return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> obterImoveisARevisitar(Integer idArquivo, Date dataUltimaTransmissao) throws ErroRepositorioException{
		List<Integer> retorno = null;
        Session session = HibernateUtil.getSession();

        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("select ic.imovel.id from ImovelControleAtualizacaoCadastral ic ")
            		.append("inner join ic.situacaoAtualizacaoCadastral situacao ")
            		.append("inner join ic.cadastroOcorrencia ocorrencia ")
            		.append("where situacao.id in (:situacoes) ")
            		.append("and ocorrencia.indicadorVisita = :indicadorVisita ")
            		.append("and ic.imovel.id in (select idImovel from ImovelAtualizacaoCadastral where idArquivoTexto = :idArquivo ) ")
            		.append("and ic.dataRetorno < :dataUltimaTransmissao ");

            retorno = (List<Integer>) session.createQuery(consulta.toString())
            		.setParameterList("situacoes", new Integer[] {SituacaoAtualizacaoCadastral.TRANSMITIDO,  SituacaoAtualizacaoCadastral.REVISITA})
            		.setInteger("idArquivo", idArquivo)
            		.setDate("dataUltimaTransmissao", dataUltimaTransmissao)
            		.setInteger("indicadorVisita", ConstantesSistema.SIM)
            		.list();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> obterImoveisPorSituacaoELote(Integer idArquivo, Integer idSituacao, Integer lote) throws ErroRepositorioException{
		List<Integer> retorno = null;
        Session session = HibernateUtil.getSession();

        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("select ic.imovel.id from ImovelControleAtualizacaoCadastral ic ")
            		.append("inner join ic.situacaoAtualizacaoCadastral situacao ")
            		.append("where 1 = 1 ")
            		.append("and ic.lote = :lote ");
        	
        	if (idArquivo != null) {
        		consulta.append("and ic.imovel.id in (select idImovel from ImovelAtualizacaoCadastral where idArquivoTexto = :idArquivo ) ");
        	}
        	
        	if (idSituacao != null) {
        		consulta.append("and situacao.id = :situacao ");
        	}
            
            Query query = session.createQuery(consulta.toString())
            		.setParameter("lote", lote);
            
            if (idArquivo != null) {
            	query.setParameter("idArquivo", idArquivo);
            }
            
            if (idSituacao != null) {
            	query.setInteger("situacao", idSituacao);
            }
            retorno = (List<Integer>) query.list();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis por situao e lote.");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
	}
	
	public Integer obterQuantidadeImoveisPorSituacaoELote(Integer idArquivo, Integer idSituacao, Integer lote) throws ErroRepositorioException{
		Integer retorno = null;
        Session session = HibernateUtil.getSession();

        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("select count(distinct ic.imovel.id) from ImovelControleAtualizacaoCadastral ic ")
            		.append("inner join ic.situacaoAtualizacaoCadastral situacao ")
            		.append("where 1 = 1 ")
            		.append("and ic.lote = :lote ");
        	
        	if (idArquivo != null) {
        		consulta.append("and ic.imovel.id in (select idImovel from ImovelAtualizacaoCadastral where idArquivoTexto = :idArquivo ) ");
        	}
        	
        	if (idSituacao != null) {
        		consulta.append("and situacao.id = :situacao ");
        	}
            
            Query query = session.createQuery(consulta.toString())
            		.setParameter("lote", lote);
            
            if (idArquivo != null) {
            	query.setParameter("idArquivo", idArquivo);
            }
            
            if (idSituacao != null) {
            	query.setInteger("situacao", idSituacao);
            }
            retorno = (Integer) query.uniqueResult();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar quantidade de imoveis por situao e lote.");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> obterImoveisPorIdArquivoESituacaoELoteParaSorteioComQuantidadeEAleatoriamente(
			Integer idArquivo, Integer idSituacao, Integer lote, Integer quantidade) throws ErroRepositorioException {
		List<Integer> retorno = null;
        Session session = HibernateUtil.getSession();

        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("select ic.imov_id as imov_id ")
            		.append("from atualizacaocadastral.imovel_controle_atlz_cad ic ")
            		.append("inner join cadastro.imovel_atlz_cadastral a on a.imov_id = ic.imov_id ")
            		.append("left join atualizacaocadastral.visita v on v.icac_id = ic.icac_id ")
            		.append("left join cadastro.cadastro_ocorrencia oc on oc.cocr_id = ic.cocr_id ")
            		.append("where 1 = 1 ")
            		.append("and oc.cocr_icvalidacao <> 2 ") // Com ocorrencia nao impeditiva
            		.append("and ic.siac_id = :situacao ")
            		.append("and ic.icac_lote = :lote ")
            		.append("and a.txac_id = :idArquivo ")
            		.append("group by ic.icac_id ")
            		.append("having count(v.icac_id) < 3 ") // Com menos de 3 visitas
            		.append("order by random() limit " + quantidade); // Aleatorios de acordo com a quantidade
        	
            Query query = session.createSQLQuery(consulta.toString())
            		.addScalar("imov_id", Hibernate.INTEGER)
            		.setParameter("situacao", idSituacao)
            		.setParameter("lote", lote)
            		.setParameter("idArquivo", idArquivo);
            
            retorno = (List<Integer>) query.list();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis para sorteio por lote e arquivo.");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> obterImoveisPorLote(Integer lote) throws ErroRepositorioException {
		List<Integer> retorno = null;
        Session session = HibernateUtil.getSession();

        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("select ic.imovel.id from ImovelControleAtualizacaoCadastral ic ")
            		.append("inner join ic.situacaoAtualizacaoCadastral situacao ")
            		.append("where 1 = 1 ")
            		.append("and ic.lote = :lote ");
        	
            Query query = session.createQuery(consulta.toString())
            		.setInteger("lote", lote);
            
            retorno = (List<Integer>) query.list();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis por lote.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> obterIdsArquivosPorLote(Integer lote) throws ErroRepositorioException {
		List<Integer> retorno = null;
		Session session = HibernateUtil.getSession();

        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append("select distinct a.txac_id as txac_id ")
        			.append("from atualizacaocadastral.imovel_controle_atlz_cad ic ")
            		.append("inner join cadastro.imovel_atlz_cadastral a on a.imov_id = ic.imov_id ")
            		.append("where 1 = 1 ")
            		.append("and ic.icac_lote = :lote ");
        	
            Query query = session.createSQLQuery(consulta.toString())
            		.addScalar("txac_id", Hibernate.INTEGER)
            		.setParameter("lote", lote);
            
            retorno = (List<Integer>) query.list();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar ids dos arquivos por lote.");
        } finally {
            HibernateUtil.closeSession(session);
        }
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<Visita> pesquisarVisitasPorImovelControle(ImovelControleAtualizacaoCadastral imovelControle) throws ErroRepositorioException {
		List<Visita> visitas = new ArrayList<Visita>();
		
		if (imovelControle == null || imovelControle.getId() == null)
			return visitas;

		Session session = HibernateUtil.getSession();
		try {
			visitas = (List<Visita>) session.createQuery("from Visita v where v.imovelControleAtualizacaoCadastral.id = :idImovelControle and v.indicadorExclusao = false ")
											.setInteger("idImovelControle", imovelControle.getId())
											.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar visitas do imovel controle: " + imovelControle.getId());
		} finally {
			HibernateUtil.closeSession(session);
		}
		return visitas;
	}
	
	public void atualizarSituacaoConjuntoImovelControle(Integer situacao, List<Integer> ids) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

        try {
        	StringBuilder sql = new StringBuilder();
        	sql.append("UPDATE ImovelControleAtualizacaoCadastral controle ")
               .append("SET controle.situacaoAtualizacaoCadastral.id = :situacao ")
               .append("WHERE controle.imovel.id IN (:ids) ");

            session.createQuery(sql.toString()).setInteger("situacao", situacao).setParameterList("ids", ids).executeUpdate();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao atualizar situacao de um conjunto de imovel controle.");
        } finally {
            HibernateUtil.closeSession(session);
        }
	}

	@SuppressWarnings("unchecked")
	public List<Visita> pesquisarVisitasPorImovelControleELatitudeELongitude(ImovelControleAtualizacaoCadastral imovelControle, String latitude, String longitude) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String sql = "from Visita v where v.imovelControleAtualizacaoCadastral.id = :idImovelControle and v.coordenadaX = :latitude and v.coordenadaY = :longitude and v.indicadorExclusao = false ";
			
			if (imovelControle == null) {
				return null;
			} else {
				return session.createQuery(sql).setInteger("idImovelControle", imovelControle.getId())
						.setString("latitude", latitude)
						.setString("longitude", longitude)
						.list();
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao atualizar situacao de um conjunto de imovel controle.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ImovelControleAtualizacaoCadastral> obterImoveisControlePorImovel(List<Integer> ids) throws ErroRepositorioException {
		List<ImovelControleAtualizacaoCadastral> retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String sql = "SELECT imovelControle FROM ImovelControleAtualizacaoCadastral imovelControle WHERE imovelControle.imovel.id IN (:ids)";
			retorno = (List<ImovelControleAtualizacaoCadastral>) session.createQuery(sql).setParameterList("ids", ids).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel controle por ids.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Integer obterIdImovelRetorno(Integer idImovel) throws ErroRepositorioException {
		Integer idImovelRetorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select i.id from ImovelRetorno i where i.idImovel = :idImovel";
			idImovelRetorno = (Integer) session.createQuery(consulta).setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao obter id do imovel retorno.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return idImovelRetorno;
	}
	
	@SuppressWarnings("unchecked")
	public boolean possuiClienteComCpfOuCnpjCadastrado(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			String sql = "SELECT clie_id FROM cadastro.cliente_atlz_cadastral WHERE imov_id = :idImovel AND clac_nncpfcnpj IS NOT NULL";
			List<Integer> retorno = (List<Integer>) session.createSQLQuery(sql)
					.addScalar("clie_id", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel).list();

			if (retorno != null && !retorno.isEmpty())
				return true;
			else
				return false;

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao verificar se cliente possui cpf ou cnpj por imovel.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean possuiClienteComCpfOuCnpjTransmitido(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT tcac_cnvalortransmitido ")
			   .append("FROM seguranca.tab_atlz_cadastral tatc ")
			   .append("INNER JOIN seguranca.tab_col_atlz_cadastral tcac ON tcac.tatc_id = tatc.tatc_id ")
			   .append("INNER JOIN seguranca.tabela_coluna tbco ON tbco.tbco_id = tcac.tbco_id ")
			   .append("WHERE tatc_cdimovel = :idImovel ") 
			   .append("AND tbco_nmcoluna = 'clac_nncpfcnpj' ")
			   .append("AND tcac_cnvalortransmitido is not null AND tcac_cnvalortransmitido <> '' ");
			
			List<Integer> retorno = (List<Integer>) session.createSQLQuery(sql.toString())
					.addScalar("tcac_cnvalortransmitido", Hibernate.STRING)
					.setInteger("idImovel", idImovel).list();

			if (retorno != null && !retorno.isEmpty())
				return true;
			else
				return false;

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao verificar se Cliente teve CPF ou CNPJ transmitido.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public void atualizarSubcategoriarAoFiscalizar(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			
			List<Integer> idsColunas = this.obterIdsColunasParaAtualizarDadosFiscalizacao(idImovel);
			if (idsColunas != null && !idsColunas.isEmpty()) {
				StringBuilder sql = new StringBuilder();
				sql.append(" update TabelaColunaAtualizacaoCadastral  coluna ")
				.append(" set coluna.colunaValorFiscalizado = coluna.colunaValorAnterior ")
				.append(" where coluna.id in (:colunas) ");
				
				session.createQuery(sql.toString()).setParameterList("colunas", idsColunas).executeUpdate();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao atualizar subcategorias - fiscalizacao.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}
	
	public void atualizarSubcategoriarAoPreAprovar(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			
			List<Integer> idsColunas = this.obterIdsColunasParaAtualizarDadosPreaprovados(idImovel);
			
			if (idsColunas != null && !idsColunas.isEmpty()) {
				StringBuilder sql = new StringBuilder();
				sql.append(" update TabelaColunaAtualizacaoCadastral  coluna ")
				.append(" set coluna.colunaValorPreAprovado = coluna.colunaValorAnterior ")
				.append(" where coluna.id in (:colunas) ");
				
				session.createQuery(sql.toString()).setParameterList("colunas", idsColunas).executeUpdate();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao atualizar subcategorias - fiscalizacao.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> obterIdsColunasParaAtualizarDadosFiscalizacao(Integer idImovel) throws ErroRepositorioException {
		List<Integer> retorno = null;
        Session session = HibernateUtil.getSession();
        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append(queryTabelaColuna(consulta))
            		.append("and   colunaAtualizacao.colunaValorFiscalizado is null ")
            		.append(" and tabelaAtualizacaoCadastral.tabela.id = :idTabelaSubcategoria ");
        	
            retorno = (List<Integer>) session.createQuery(consulta.toString())
            			.setInteger("idImovel",idImovel)
            			.setInteger("idTabelaSubcategoria",Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL).list();
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> obterIdsColunasParaAtualizarDadosPreaprovados(Integer idImovel) throws ErroRepositorioException {
		List<Integer> retorno = null;
        Session session = HibernateUtil.getSession();
        StringBuilder consulta = new StringBuilder();
        try {
        	consulta.append(queryTabelaColuna(consulta))
            		.append(" and   colunaAtualizacao.colunaValorPreAprovado is null ")
            		.append(" and tabela.id = :idTabelaSubcategoria ");
        	
            retorno = (List<Integer>) session.createQuery(consulta.toString())
            				.setInteger("idImovel",idImovel)
            				.setInteger("idTabelaSubcategoria",Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL).list();
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
	}

	private StringBuilder queryTabelaColuna(StringBuilder consulta) {
		StringBuilder sql = new StringBuilder();
		return sql.append("select colunaAtualizacao.id from TabelaColunaAtualizacaoCadastral colunaAtualizacao ")
				.append("inner join fetch colunaAtualizacao.tabelaAtualizacaoCadastral tabelaAtualizacaoCadastral ")
				.append("inner join fetch tabelaAtualizacaoCadastral.tabela tabela ")
				.append("inner join fetch colunaAtualizacao.tabelaColuna coluna ")
				.append("where tabelaAtualizacaoCadastral.codigoImovel = :idImovel ");
	}
	
    @SuppressWarnings("unchecked")
    public List<ImovelControleAtualizacaoCadastral> obterIdsImovelControleGeracaoLote(FiltrarGerarLoteAtualizacaoCadastralActionHelper helper) throws ErroRepositorioException {
        List<ImovelControleAtualizacaoCadastral> retorno = new ArrayList<ImovelControleAtualizacaoCadastral>();
        Session session = HibernateUtil.getSession();
        
        try {

        	StringBuilder consulta = new StringBuilder();
        	consulta.append(" select controle from ArquivoTextoAtualizacaoCadastral arquivo,  ")
        			.append(" ImovelAtualizacaoCadastral imovelAtualizacao, ImovelControleAtualizacaoCadastral controle ")  
		        	.append(" inner join controle.imovel imovel ")
		        	.append(" inner join imovel.imovelPerfil imovelPerfil ")
		        	.append(" inner join arquivo.leiturista leiturista ")
		        	.append(" inner join controle.situacaoAtualizacaoCadastral situacao ")
		        	.append(" inner join controle.cadastroOcorrencia ocorrencia ")
		        	.append(" where arquivo.id = imovelAtualizacao.idArquivoTexto ")
		        	.append(" and controle.imovel.id = imovelAtualizacao.idImovel ")
		        	.append(" and situacao.id in (:emFiscalizacao, :preAprovado) ")
		        	.append(" and imovelAtualizacao.idLocalidade = :idLocalidade ")
		        	.append(" and imovelAtualizacao.codigoSetorComercial = :codigoSetor ")
        			.append(" and controle.lote is null ");
		      
			if (helper.getPeriodoInicial() != null && helper.getPeriodoFinal() != null) {
				consulta.append(" and controle.dataPreAprovacao between :periodoInicial AND :periodoFinal ");
			}		        	
        	
			if (helper.getGrandesConsumidores() != FiltrarGerarLoteAtualizacaoCadastralActionHelper.TODOS) {
        		consulta.append(" and imovelPerfil.indicadorGrandeConsumidor = " + helper.getGrandesConsumidores());
        	}
        	
        	if (helper.getIdLeiturista() > 0) {
        		consulta.append(" and leiturista.id = " + helper.getIdLeiturista());
        	}
        	
        	if (helper.getOcorrenciaCadastro() > 0) {
        		if (helper.getOcorrenciaCadastroSelecionada() == FiltrarGerarLoteAtualizacaoCadastralActionHelper.TODOS)
        			consulta.append(" AND ocorrencia.indicadorValidacao = " + helper.getOcorrenciaCadastro());
        		else
        			consulta.append(" AND ocorrencia.id = " + helper.getOcorrenciaCadastroSelecionada());
        	}
        	
        	Query query = session.createQuery(consulta.toString())
        			.setInteger("emFiscalizacao",SituacaoAtualizacaoCadastral.EM_FISCALIZACAO)
        			.setInteger("preAprovado",SituacaoAtualizacaoCadastral.PRE_APROVADO)
        			.setInteger("idLocalidade",helper.getIdLocalidadeInicial())
        			.setInteger("codigoSetor",helper.getCdSetorComercialInicial());
        	
        	if (helper.getPeriodoInicial() != null && helper.getPeriodoFinal() != null) {
        		query.setTimestamp("periodoInicial", helper.getPeriodoInicial());
				query.setTimestamp("periodoFinal", helper.getPeriodoFinal());
        	}
        	
        	retorno = (List<ImovelControleAtualizacaoCadastral>) query.list();
        	
        	StringBuilder consultaImoveisNovos = new StringBuilder();
        	consultaImoveisNovos.append(" select controle from ImovelControleAtualizacaoCadastral controle, ImovelRetorno retorno ")
        						.append(" inner join controle.situacaoAtualizacaoCadastral situacao ")
					        	.append(" where controle.imovelRetorno.id = retorno.id ")
					        	.append(" and situacao.id in (:emFiscalizacao, :preAprovado) ")
					        	.append(" and retorno.idLocalidade = :idLocalidade ")
					        	.append(" and retorno.codigoSetorComercial = :codigoSetor ")
					        	.append(" and retorno.tipoOperacao = :inclusao ")
					        	.append(" and controle.lote is null ");
        	
			if (helper.getPeriodoInicial() != null && helper.getPeriodoFinal() != null) {
				consultaImoveisNovos.append(" and controle.dataPreAprovacao between :periodoInicial AND :periodoFinal ");
			}	
        	
			query = session.createQuery(consultaImoveisNovos.toString())
					.setInteger("emFiscalizacao",SituacaoAtualizacaoCadastral.EM_FISCALIZACAO)
					.setInteger("preAprovado",SituacaoAtualizacaoCadastral.PRE_APROVADO)
					.setInteger("inclusao", AlteracaoTipo.INCLUSAO)
					.setInteger("idLocalidade",helper.getIdLocalidadeInicial())
					.setInteger("codigoSetor",helper.getCdSetorComercialInicial());
			
			if (helper.getPeriodoInicial() != null && helper.getPeriodoFinal() != null) {
				query.setTimestamp("periodoInicial", helper.getPeriodoInicial());
				query.setTimestamp("periodoFinal", helper.getPeriodoFinal());
			}
			
        	if (helper.getImoveisNovos() == ConstantesSistema.SIM.intValue()) {
                retorno = (List<ImovelControleAtualizacaoCadastral>) query.list();
            } else if (helper.getImoveisNovos() == FiltrarGerarLoteAtualizacaoCadastralActionHelper.TODOS) {
            	retorno.addAll((List<ImovelControleAtualizacaoCadastral>) query.list());
            }
        	
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar tipos ocupantes.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
    }
    
    public boolean isLoteExistente(Integer lote) throws ErroRepositorioException {
        boolean retorno = false;
        Session session = HibernateUtil.getSession();
        StringBuilder consulta = new StringBuilder();
        try {
            consulta.append(" select count(*) from ImovelControleAtualizacaoCadastral controle  ")
                    .append(" where controle.lote = :lote");
            
            Integer qtd = (Integer) session.createQuery(consulta.toString()).setInteger("lote",lote).setMaxResults(1).uniqueResult();
            
            if (qtd >0)
                retorno = true;
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao verificar se o lote j� existe.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
    }
    
    public Integer obterProximoLote() throws ErroRepositorioException {
        Integer retorno = 0;
        Session session = HibernateUtil.getSession();
        StringBuilder consulta = new StringBuilder();
        try {
            consulta.append(" select max(controle.lote) from ImovelControleAtualizacaoCadastral controle ");
            
            retorno = (Integer) session.createQuery(consulta.toString()).setMaxResults(1).uniqueResult() + 1;
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao verificar se o lote j� existe.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
    }
    
    @SuppressWarnings("unchecked")
	public List<Integer> obterImagensImoveisAprovados(Integer idRota) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			StringBuilder consultaImovelImagem = new StringBuilder();
			consultaImovelImagem.append("SELECT imagem.idImovel ")
								.append("FROM ImovelImagem imagem ")
								.append("WHERE imagem.ultimaAlteracao like :dataGeracao) ");
			
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT imovelControle.imovel.id as idImovel ")
			.append("FROM ImovelControleAtualizacaoCadastral imovelControle ")
			.append("INNER JOIN imovelControle.imovelRetorno imovelRetorno ")
			.append("WHERE imovelControle.situacaoAtualizacaoCadastral.id = :situacaoImovel ")
			.append("AND imovelRetorno.idRota = :idRota ")
			.append("AND imovelControle.dataGeracao like :dataGeracao ")
			.append("AND imovelControle.dataLiberacaoProcessamento like :dataLiberacao ")
			.append("AND imovelControle.imovel.id not in (" + consultaImovelImagem.toString() + ") ");

			return (List<Integer>) session.createQuery(consulta.toString())
					.setInteger("situacaoImovel", SituacaoAtualizacaoCadastral.ATUALIZADO)
					.setInteger("idRota", idRota)
					.setString("dataGeracao", "2018%")
					.setDate("dataLiberacao", new Date()).list();			
			
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao verificar se o lote j� existe.");
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

	public void reprovarImoveis(List<Integer> imoveisParaReprovar) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		StringBuilder consulta = new StringBuilder();

		try {

			consulta.append("update ImovelControleAtualizacaoCadastral controle ")
						.append(" set controle.situacaoAtualizacaoCadastral.id = :situacaoAtual,")
						.append(" controle.dataReprovacaoLote = :dataReprovacaoLote ")
						.append(" where controle.id in (:imoveisParaReprovar) ");


			session.createQuery(consulta.toString())
						.setInteger("situacaoAtual", SituacaoAtualizacaoCadastral.EM_CAMPO)
						.setDate("dataReprovacaoLote", new Date())
						.setParameterList("imoveisParaReprovar", imoveisParaReprovar)   
						.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao reprovar imoveis.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public ImovelRetorno pesquisarImovelRetornoPorIdRetorno(Integer idImovelRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		ImovelRetorno retorno = null;
		try {
			String consulta = "select imovelRetorno "
					+ " from ImovelRetorno imovelRetorno "
					+ " where imovelRetorno.id = :idImovelRetorno ";
			
			retorno = (ImovelRetorno)session.createQuery(consulta).setInteger("idImovelRetorno", idImovelRetorno).setMaxResults(1).uniqueResult();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao pesquisar imovel retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRetornoFichaFiscalizacaoCadastral(List<Integer> listaIdImoveis) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT distinct imac.imov_id as idImovel, "
				+ "loca.loca_nmlocalidade as nomeLocalidade, "
				+ "imac.imre_cdsetorcomercial as codigoSetor, "
				+ "imac.imre_nnquadra as numeroQuadra, "
				+ "0 as numeroLote, "
				+ "0 as numeroSublote, "
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
			    + "CASE WHEN fnet_id = 3 THEN cfac.cfac_nnfone END as celular, "
			    + "imac.imac_nnmedidorenergia as contratoEnergia, "
			    + " imac.ftab_id as fonteAbastecimento, "
			    + " imac.last_id as ligacaoAguaSituacao, "
			    + " imac.lest_id as ligacaoEsgotoSituacao, "
			    + " imac.imac_nnhidrometro as numeroHidrometro, "
			    + " capacidade.hicp_dshidrometrocapacidade as hidrometroCapacidade, "
			    + " imac.hipr_id as hidrometroProtecao, "
			    + " marca.himc_dshidrometromarca as hidrometroMarca, "
			    + " imac.imac_dsoutrasinformacoes as outrasInformacoes, "
			    + " imac.imre_areaconstruida as areaConstruida, "
			    + " imac.imac_nnpontosutilizacao as pontosUtilizacao, "
			    + " imac.imac_nnmorador as moradores "
			    + "FROM atualizacaocadastral.imovel_retorno imac "
			    + "INNER JOIN atualizacaocadastral.imovel_controle_atlz_cad icac ON icac.imov_id = imac.imov_id "
			    + "INNER JOIN cadastro.imovel imov ON imov.imov_id = imac.imov_id "
			    + "INNER JOIN cadastro.quadra qdra ON qdra.qdra_id = imov.qdra_id "
			    + "INNER JOIN cadastro.quadra_face face ON face.qdra_id = qdra.qdra_id "
			    + "INNER JOIN micromedicao.rota rota ON rota.rota_id = qdra.rota_id "
			    + "INNER JOIN cadastro.localidade loca ON loca.loca_id = imac.loca_id "
			    + "INNER JOIN cadastro.cliente_atlz_cadastral clac ON clac.imov_id = imac.imov_id "
			    + "LEFT JOIN cadastro.cliente_fone_atlz_cad cfac ON cfac.clac_id = clac.clac_id "
			    + " LEFT JOIN micromedicao.hidrometro_capacidade capacidade on capacidade.hicp_id = imac.hicp_id "
			    + " LEFT JOIN micromedicao.hidrometro_marca marca on marca.himc_id = imac.himc_id "
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
					.addScalar("contratoEnergia", Hibernate.STRING)
					.addScalar("fonteAbastecimento", Hibernate.INTEGER)
					.addScalar("ligacaoAguaSituacao", Hibernate.INTEGER)
					.addScalar("ligacaoEsgotoSituacao", Hibernate.INTEGER)
					.addScalar("numeroHidrometro", Hibernate.STRING)
					.addScalar("hidrometroCapacidade", Hibernate.STRING)
					.addScalar("hidrometroProtecao", Hibernate.INTEGER)
					.addScalar("hidrometroMarca", Hibernate.STRING)
					.addScalar("outrasInformacoes", Hibernate.STRING)
					.addScalar("areaConstruida", Hibernate.BIG_DECIMAL)
					.addScalar("pontosUtilizacao", Hibernate.INTEGER)	
					.addScalar("moradores", Hibernate.INTEGER)
					.setInteger("situacao", SituacaoAtualizacaoCadastral.ATUALIZADO)
					.setParameterList("listaIdImoveis", listaIdImoveis)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel para ficha de cadastro.");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<ImovelSubcategoriaRetorno> pesquisarDadosSubcategoriaRetornoFichaFiscalizacaoCadastral(Integer idImovel) throws ErroRepositorioException {

		List<ImovelSubcategoriaRetorno> retorno = null;
		Session session = HibernateUtil.getSession();
		StringBuilder consulta = new StringBuilder();

		try {
			consulta.append("SELECT subcategoria from ImovelSubcategoriaRetorno subcategoria ")
			    	.append(" inner join subcategoria.imovel imovel ")
			    	.append(" where imovel.id = :idImovel ");

			retorno = (List<ImovelSubcategoriaRetorno>) session.createQuery(consulta.toString())
					.setInteger("idImovel", idImovel).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar subcategorias de imovel para ficha de cadastro.");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Short pesquisarQuantidadeEconomiasOriginais(Integer idImovel, Integer idCategoria) throws ErroRepositorioException {

        Short retorno = null;

        Session session = HibernateUtil.getSession();
        StringBuilder consulta = new StringBuilder();

        try {
            consulta.append("select sum( imsc.quantidadeEconomias ) ") 
                    .append("from ImovelSubcategoriaAtualizacaoCadastral as imsc ") 
                    .append("where imsc.imovel.id = :imovelId and ") 
                    .append("  imsc.categoria.id = :categoriaId ");

            retorno = (Short)session.createQuery(consulta.toString())
                    .setInteger("imovelId", idImovel)
                    .setInteger("categoriaId", idCategoria)
                    .uniqueResult();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
	
	@SuppressWarnings("unchecked")
    public List<ImovelSubcategoriaAtualizacaoCadastral> obterSubcategoriasOriginais(Integer idImovel) throws ErroRepositorioException {
        List<ImovelSubcategoriaAtualizacaoCadastral> retorno = null;

        Session session = HibernateUtil.getSession();

        try {
            StringBuilder sql =  new StringBuilder();
            
            sql.append("SELECT original FROM ImovelSubcategoriaAtualizacaoCadastral original ")
                .append(" inner join fetch original.imovel imovel ")
                .append(" inner join fetch original.subcategoria subcategoria ")
                .append(" inner join fetch original.categoria categoria ")
                .append(" WHERE imovel.id = :id ");
            retorno = (List<ImovelSubcategoriaAtualizacaoCadastral>) session.createQuery(sql.toString()).setInteger("id", idImovel).list();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar imovel controle por ids.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
    }
	
	@SuppressWarnings("unchecked")
    public List<ImovelSubcategoriaRetorno> obterSubcategoriasRetorno(Integer idImovel) throws ErroRepositorioException {
        List<ImovelSubcategoriaRetorno> retorno = null;
        StringBuilder sql =  new StringBuilder();
        Session session = HibernateUtil.getSession();
        try {
            sql.append("SELECT retorno FROM ImovelSubcategoriaRetorno retorno ")
                .append(" inner join fetch retorno.imovel imovel ")
                .append(" inner join fetch retorno.subcategoria subcategoria ")
                .append(" inner join fetch subcategoria.categoria categoria ")
                .append(" WHERE imovel.id = :id ");
            retorno = (List<ImovelSubcategoriaRetorno>) session.createQuery(sql.toString()).setInteger("id", idImovel).list();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar imovel controle por ids.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;
    }

	@SuppressWarnings("unchecked")
	public List<Visita> obterVisitasPorCoordenadas(String latitude, String longitude) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			return session.createQuery("from Visita v where v.coordenadaX = :latitude and v.coordenadaY = :longitude and v.indicadorExclusao = false ")
					.setString("latitude", latitude)
					.setString("longitude", longitude)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao obter visita por coordenadas.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public void excluirVisitasDosImoveisControle(List<Integer> idsImoveisControle) throws ErroRepositorioException {
        Session session = HibernateUtil.getSession();
        try {
    		session.createQuery("UPDATE Visita v set v.indicadorExclusao = true WHERE v.imovelControleAtualizacaoCadastral.id in (:ids) ")
    			.setParameterList("ids", idsImoveisControle)
    			.executeUpdate();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar imovel controle por ids.");
        } finally {
            HibernateUtil.closeSession(session);
        }
	}
	
	public boolean obterIndicadorSeHouveAlteracaoNaSituacaoAguaEEsgotoEHidrometroDoImovelDuranteAtualizacaoCadastral(Integer idImovel)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder consulta = new StringBuilder("select exists( ");
			consulta.append("select imovel.imov_id, imovel.last_id, imovel.lest_id, hidrometro.hicp_id, instalacao_hist.hili_id, hidrometro.himc_id ");
			consulta.append("from cadastro.imovel imovel ");
			consulta.append("inner join atualizacaocadastral.imovel_controle_atlz_cad controle on controle.imov_id = imovel.imov_id ");
			consulta.append("left join atendimentopublico.ligacao_agua ligagua on ligagua.lagu_id = imovel.imov_id ");
			consulta.append("left join micromedicao.hidrometro_inst_hist instalacao_hist on ligagua.hidi_id = instalacao_hist.hidi_id ");
			consulta.append("left join micromedicao.hidrometro hidrometro on hidrometro.hidr_id = instalacao_hist.hidr_id ");
			consulta.append("where imovel.imov_id = :idImovel ");
			consulta.append("except ");
			consulta.append("select imovel.imov_id, imovel.last_id, imovel.lest_id, imovel.hicp_id, imovel.hili_id, imovel.himc_id ");
			consulta.append("from cadastro.imovel_atlz_cadastral imovel ");
			consulta.append("where imovel.imov_id = :idImovel) as retorno ");

			return (Boolean) session.createSQLQuery(consulta.toString())
					.addScalar("retorno", Hibernate.BOOLEAN)
					.setInteger("idImovel", idImovel)
					.uniqueResult();
		} catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao recuperar indicador de alteracao no imovel.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public boolean obterIndicadorSeHouveAlteracaoNasSubcategoriasEQuantidadesDeEconomiasDoImovelDuranteAtualizacaoCadastral(Integer idImovel)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder consulta = new StringBuilder("select exists( ");
			consulta.append("select imov_sub.imov_id, imov_sub.scat_id, imov_sub.imsb_qteconomia ");
			consulta.append("from cadastro.imovel_subcategoria imov_sub ");
			consulta.append("inner join atualizacaocadastral.imovel_controle_atlz_cad controle on controle.imov_id = imov_sub.imov_id ");
			consulta.append("where imov_sub.imov_id = :idImovel ");
			consulta.append("  except ");
			consulta.append("select subatlz.imov_id, subatlz.scat_id, subatlz.isac_qteconomia ");
			consulta.append("from cadastro.imovel_subcatg_atlz_cad subatlz ");
			consulta.append("where subatlz.imov_id = :idImovel) as retorno ");

			return (Boolean) session.createSQLQuery(consulta.toString())
					.addScalar("retorno", Hibernate.BOOLEAN)
					.setInteger("idImovel", idImovel)
					.uniqueResult();
		} catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao recuperar indicador de alteracao nas subcategorios ou quantiade de economias do imovel.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public boolean obterIndicadorSeHouveAlteracaoNosClientesDoImovelDuranteAtualizacaoCadastral(Integer idImovel)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder consulta = new StringBuilder("select exists( ");
			consulta.append("select imovel.imov_id, ");
			consulta.append("       cliente.clie_id, ");
			consulta.append("       cliente_imovel.crtp_id, ");
			consulta.append("       case ");
			consulta.append("           when cliente_tipo.cltp_icpessoafisicajuridica = 1 then ");
			consulta.append("               cliente.clie_nncpf ");
			consulta.append("           else ");
			consulta.append("               cliente.clie_nncnpj ");
			consulta.append("           end as nncpfcpnj ");
			consulta.append("from cadastro.imovel imovel ");
			consulta.append("         inner join atualizacaocadastral.imovel_controle_atlz_cad controle on imovel.imov_id = controle.imov_id ");
			consulta.append("         inner join cadastro.cliente_imovel cliente_imovel on imovel.imov_id = cliente_imovel.imov_id ");
			consulta.append("         inner join cadastro.cliente cliente on cliente_imovel.clie_id = cliente.clie_id ");
			consulta.append("         inner join cadastro.cliente_tipo cliente_tipo on cliente.cltp_id = cliente_tipo.cltp_id ");
			consulta.append("where 1 = 1 ");
			consulta.append("  and cliente_imovel.clim_dtrelacaofim is null ");
			consulta.append("  and imovel.imov_id = :idImovel ");
			consulta.append("except ");
			consulta.append("select imovel.imov_id, cliente_atlz.clie_id, cliente_atlz.crtp_id, cliente_atlz.clac_nncpfcnpj as nncpfcpnj ");
			consulta.append("from cadastro.imovel_atlz_cadastral imovel ");
			consulta.append("         inner join cadastro.cliente_atlz_cadastral cliente_atlz on imovel.imov_id = cliente_atlz.imov_id ");
			consulta.append("where 1 = 1 ");
			consulta.append("  and imovel.imov_id = :idImovel) as retorno ");

			return (Boolean) session.createSQLQuery(consulta.toString())
					.addScalar("retorno", Hibernate.BOOLEAN)
					.setInteger("idImovel", idImovel)
					.uniqueResult();
		} catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao recuperar indicador de alteracao nos clientes do imovel.");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public ClienteRetorno pesquisarClienteRetornoPorMatriculaClienteETipoRelacao(Integer matriculaCliente, Short tipoRelacao)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		ClienteRetorno retorno = null;
		try {
			StringBuilder consulta = new StringBuilder("select cr ")
					.append(" from ClienteRetorno cr ")
					.append(" join cr.cliente c ")
					.append(" join c.clienteImoveis ci ")
					.append(" join ci.clienteRelacaoTipo crtp ")
					.append(" where c.id = :matriculaCliente ")
					.append(" and ci.dataFimRelacao is null")
					.append(" and crtp.id = :tipoRelacao");

			retorno = (ClienteRetorno) session.createQuery(consulta.toString())
					.setInteger("matriculaCliente", matriculaCliente)
					.setInteger("tipoRelacao", tipoRelacao.intValue()).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro ao pesquisar cliente retorno");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Integer> pesquisarRotasComImoveisAprovados() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT rota_id ")
			   .append("FROM atualizacaocadastral.imovel_controle_atlz_cad ic ")
			   .append("INNER JOIN atualizacaocadastral.imovel_retorno ir ON ir.imre_id = ic.imre_id ")
			   .append("WHERE siac_id = :situacao ")
			   .append("GROUP BY rota_id ")
			   .append("ORDER BY rota_id;");
			
			return session.createSQLQuery(sql.toString())
					.addScalar("rota_id", Hibernate.INTEGER)
					.setInteger("situacao", SituacaoAtualizacaoCadastral.APROVADO)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro ao pesquisar rotas com imoveis aprovados");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
    
    @SuppressWarnings("unchecked")
	public Collection<Integer> pesquisarIdsArquivoTextoPorRotaTodasSituacoes(Integer idRota, String[] idsArquivos)
			throws ErroRepositorioException {

		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT txac.txac_id AS idArquivo " +
							  "FROM cadastro.arquivo_texto_atlz_cad txac " +
							  "WHERE txac.rota_id = :idRota " + 
							  "AND txac.txac_id in (:idsArquivos)";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idArquivo", Hibernate.INTEGER)
					.setInteger("idRota", idRota)
					.setParameterList("idsArquivos", idsArquivos)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ImovelControleAtualizacaoCadastral> obterImoveisPorArquivoTodasSituacoes(
    		Integer idArquivo, Date dataUltimaTransmissao) throws ErroRepositorioException {
    	
    	List<ImovelControleAtualizacaoCadastral> retorno = null;
        Session session = HibernateUtil.getSession();

        try {
        	StringBuilder consulta = new StringBuilder();
        	consulta.append("SELECT {controle.*}, ");
        	consulta.append("       {atualizacao.*}, ");
        	consulta.append("       {ocorrencia.*}, ");
        	consulta.append("       (SELECT count(vist_id) FROM atualizacaocadastral.visita v WHERE v.icac_id = controle.icac_id and v.vist_icexclusao = 'f') AS visitas ");
        	consulta.append("FROM atualizacaocadastral.imovel_controle_atlz_cad controle ");
        	consulta.append("LEFT JOIN cadastro.cadastro_ocorrencia ocorrencia ON ocorrencia.cocr_id = controle.cocr_id ");
        	consulta.append("INNER JOIN cadastro.imovel imov ON imov.imov_id = controle.imov_id ");
        	consulta.append("INNER JOIN cadastro.imovel_atlz_cadastral atualizacao ON atualizacao.imov_id = controle.imov_id ");
        	consulta.append("WHERE controle.imov_id IN (SELECT distinct imov_id FROM ( ");
        	consulta.append("SELECT controle.imov_id AS imov_id ");
        	consulta.append("FROM atualizacaocadastral.imovel_controle_atlz_cad controle ");
        	consulta.append("INNER JOIN cadastro.imovel_atlz_cadastral atlz ON atlz.imov_id = controle.imov_id ");
        	consulta.append("INNER JOIN cadastro.arquivo_texto_atlz_cad arquivo ON arquivo.txac_id = atlz.txac_id ");
        	consulta.append("INNER JOIN cadastro.imovel imovel ON imovel.imov_id = controle.imov_id ");
        	consulta.append("INNER JOIN cadastro.quadra quadra ON quadra.rota_id = arquivo.rota_id ");
        	consulta.append("WHERE arquivo.txac_id = :idArquivo ");
        	consulta.append("ORDER BY quadra.qdra_id, imovel.imov_nnlote, imovel.imov_nnsublote) AS imoveis) ");
        	
            Query query = session.createSQLQuery(consulta.toString())
            		.addEntity("controle", ImovelControleAtualizacaoCadastral.class)
            		.addJoin("atualizacao", "controle.imovelAtualizacaoCadastral")
            		.addJoin("ocorrencia", "controle.cadastroOcorrencia")
            		.addScalar("visitas", Hibernate.INTEGER)
            		.setInteger("idArquivo", idArquivo);
            
			List<Object[]> resultado = query.list();
            
			if (CollectionUtil.naoEhVazia(resultado)) {
				retorno = new ArrayList(resultado.size());
				
				for (Object[] array : resultado) {
					ImovelControleAtualizacaoCadastral controle = (ImovelControleAtualizacaoCadastral) array[1];
					controle.setImovelAtualizacaoCadastral((ImovelAtualizacaoCadastral) array[2]);
					controle.setCadastroOcorrencia((CadastroOcorrencia) array[3]);
					controle.setQuantidadeVisitas((Integer) array[0]);
					controle.verificarInformativoArquivoTodasSituacoes(dataUltimaTransmissao);
					
					retorno.add(controle);
				}
			}
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis da rota com todas as situa��es e informativos.");
        } finally {
            HibernateUtil.closeSession(session);
        }
        
        return retorno;
    }
}
