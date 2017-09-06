package gcom.cadastro.cliente;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.localidade.Localidade;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.filtro.GeradorHQLCondicional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RepositorioClienteEnderecoHBM implements
		IRepositorioClienteEndereco {

	private static IRepositorioClienteEndereco instancia;

	public RepositorioClienteEnderecoHBM() {
	}

	public static IRepositorioClienteEndereco getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioClienteEnderecoHBM();
		}
		return instancia;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection pesquisarClienteEndereco(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroClienteEndereco,
							"clienteEndereco",
							"select distinct clienteEndereco.cliente.id,clienteEndereco.cliente.nome,clienteEndereco.cliente.clienteTipo.descricao,clienteEndereco.cliente.cpf,clienteEndereco.cliente.cnpj, clienteEndereco.cliente.indicadorUso "
									+ "from gcom.cadastro.cliente.ClienteEndereco as clienteEndereco "
									+ "left join clienteEndereco.cliente.clienteTipo "
									+ "left join clienteEndereco.logradouroCep ",
							session).list()));
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<Cliente> pesquisarClienteEnderecoClienteImovel(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ErroRepositorioException {

		Collection<Cliente> retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			retorno = new ArrayList(new CopyOnWriteArraySet<Cliente>(GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroClienteEndereco,
							"clienteEndereco",
							"select distinct clienteEndereco.cliente from gcom.cadastro.cliente.ClienteEndereco as clienteEndereco "
									+ "inner join clienteEndereco.cliente.clienteImoveis clienteImovel",
							session).list()));

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarEnderecosClienteAbreviado(Integer idCliente)
		throws ErroRepositorioException {
		
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select logradouro.nome," + // 0
					" logradouroTipo.descricaoAbreviada," + // 1
					" logradouroTitulo.descricaoAbreviada," + // 2
					" bairro.id," + // 3
					" bairro.nome," + // 4
					" municipio.id," + // 5
					" municipio.nome," + // 6
					" unidadeFederacao.id," + // 7
					" unidadeFederacao.sigla," + // 8
					" enderecoReferencia.descricaoAbreviada," + // 9
					" cep.cepId," + // 10
					" cep.logradouro," + // 11
					" cep.descricaoTipoLogradouro," + // 12
					" cep.bairro," + // 13
					" cep.municipio," + // 14
					" cep.sigla, " + // 15
					" cep.codigo, " + // 16
					" clienteEndereco.numero," + // 17
					" clienteEndereco.complemento," + // 18
					" logradouro.id," + // 19
					" logradouroCep.id," + // 20
					" logradouroBairro.id," + // 21
					" logradouroTipo.descricao," + // 22
					" logradouroTitulo.descricao," + // 23
					" enderecoReferencia.descricao, "  + // 24
					" clienteEndereco.indicadorEnderecoCorrespondencia, " + // 25
					" clienteEndereco.id, " + // 26
					" clienteEndereco.ultimaAlteracao, " // 27
					+ // 28
					" perimetroInicial.id, "
					+ // 29
					" perimetroInicial.nome, " 
					+ // 30
					" logradouroTipoPerimetroInicial.descricaoAbreviada, " 
					+ // 31
					" logradouroTituloPerimetroInicial.descricaoAbreviada, "
					+ // 32
					" perimetroFinal.id, " 
					+ // 33
					" perimetroFinal.nome, " 
					+ // 34
					" logradouroTipoPerimetroFinal.descricaoAbreviada, " 
					+ // 35
					" logradouroTituloPerimetroFinal.descricaoAbreviada " 
					+ "from ClienteEndereco clienteEndereco "
					+ "left join clienteEndereco.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join clienteEndereco.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join bairro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "left join clienteEndereco.enderecoReferencia enderecoReferencia "
					+ "left join clienteEndereco.perimetroInicial perimetroInicial "
					+ "left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial "
					+ "left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial "
					+ "left join clienteEndereco.perimetroFinal perimetroFinal "
					+ "left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal "
					+ "left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal "
					+ "inner join clienteEndereco.cliente cliente "
					+ "where cliente.id = :idCliente";
					
			retorno = session.createQuery(consulta).setInteger("idCliente",
					idCliente.intValue()).list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta = "UPDATE gcom.cadastro.cliente.ClienteEndereco SET "
					+ "lgbr_id = :idLogradouroBairroNovo, cled_tmultimaalteracao = :ultimaAlteracao " 
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
	
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta = "UPDATE gcom.cadastro.cliente.ClienteEndereco SET "
					+ "lgcp_id = :idLogradouroCepNovo, cled_tmultimaalteracao = :ultimaAlteracao " 
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
	
	public Localidade pesquisarLocalidadeCliente(Integer idCliente) throws ErroRepositorioException {
		Integer idLocalidade = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			StringBuilder consulta = new StringBuilder();
			consulta.append(" select localidade.loca_id ")
					.append(" from cadastro.cliente_endereco clienteEndereco ")
					.append(" inner join cadastro.cliente cliente on cliente.clie_id = clienteEndereco.clie_id ")
					.append(" left join cadastro.logradouro_bairro logradouroBairro on logradouroBairro.lgbr_id = clienteEndereco.lgbr_id ")
					.append(" left join cadastro.bairro bairro on bairro.bair_id = logradouroBairro.bair_id ")
					.append(" left join cadastro.municipio municipio on municipio.muni_id = bairro.muni_id ")
					.append(" inner join cadastro.localidade localidade on localidade.muni_idprincipal = municipio.muni_id ")
					.append(" where cliente.clie_id = :idCliente ")
					.append(" and clienteEndereco.cled_icenderecocorrespondencia = indicadorSim ") 
					.append(" order by localidade.loca_id  ")
					.append(" limit 1 ");


			idLocalidade = (Integer)session.createSQLQuery(consulta.toString())
					.addScalar("id", Hibernate.INTEGER)
					.setInteger("idCliente", idCliente.intValue())
					.setShort("indicadorSim", ConstantesSistema.SIM)
					.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return new Localidade(idLocalidade);
	}
}
