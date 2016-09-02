package gcom.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.CallbackException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.exception.GenericJDBCException;
import org.jboss.logging.Logger;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.interceptor.ObjetoTransacao;
import gcom.model.IHistorico;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.GeradorHQLCondicional;
import gcom.util.filtro.PersistenciaUtil;

public class RepositorioUtilHBM implements IRepositorioUtil {

	private Logger logger = Logger.getLogger(RepositorioUtilHBM.class);

	private static RepositorioUtilHBM instancia;

	protected RepositorioUtilHBM() {
	}

	public static RepositorioUtilHBM getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioUtilHBM();
		}

		return instancia;
	}
	
	public Object obterPorId(Class classe, Integer id) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery("select e from " + classe.getName() + " e where e.id = " + id);
		
			return query.uniqueResult(); 
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao obter " + classe.getName() + " pelo id");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@SuppressWarnings("rawtypes")
	public int registroMaximo(Class classe) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {

			Query query = session.createQuery("select count(*) from " + classe.getName());

			Integer retorno = (Integer) query.list().iterator().next();

			return retorno.intValue();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

    public Collection listar(Class classe) throws ErroRepositorioException {
        Session session = HibernateUtil.getSession();

        try {
            Query query = session.createQuery("select e from " + classe.getName() + " e");

            return (Collection) query.list();
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection limiteMaximoFiltroPesquisa(Filtro filtro, String pacoteNomeObjeto, int limite) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		Collection resultado = null;

		try {

			resultado = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional
					.gerarCondicionalQuery(filtro, "objeto", "from " + pacoteNomeObjeto + " as objeto", session).setMaxResults(limite).list()));

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return resultado;
	}

	@SuppressWarnings("rawtypes")
	public int valorMaximo(Class classe, String atributo, String parametro1, String parametro2) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery("select max( tabela." + atributo + ") from " + classe.getName() + " tabela" + " where tabela." + parametro1
					+ " = " + parametro2);

			Integer retorno = (Integer) query.list().iterator().next();

			if (retorno == null || retorno.toString().trim().equalsIgnoreCase("")) {
				retorno = new Integer(0);
			}
			return retorno.intValue();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@SuppressWarnings("rawtypes")
	public int valorMaximo(Class classe, String atributo) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery("select max(" + atributo + ") from " + classe.getName());

			Integer retorno = (Integer) query.list().iterator().next();

			return retorno.intValue();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	public SistemaParametro pesquisarParametrosDoSistema() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			return (SistemaParametro) session.createCriteria(SistemaParametro.class).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	public Object inserir(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object retorno = null;

		try {
			retorno = session.save(objeto);
			session.flush();

			return retorno;
		} catch (Exception e) {
			logger.error("Erro ao inserir objeto", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	public Object obterNextVal(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Object retorno = null;
		String consulta = null;

		try {
			String dialect = HibernateUtil.getDialect();
			// verifica se o objeto é do tipo Imovel
			if (objeto instanceof Imovel) {
				if (dialect.toUpperCase().contains("ORACLE")) {
					consulta = "select cadastro.seq_imovel.nextval as id from dual ";
				} else {
					consulta = "select nextval('cadastro.seq_imovel') as id ";
				}
				// verifica se o objeto é do tipo Cliente
			} else if (objeto instanceof Cliente) {
				if (dialect.toUpperCase().contains("ORACLE")) {
					consulta = "select cadastro.seq_cliente.nextval as id from dual ";
				} else {
					consulta = "select nextval('cadastro.seq_cliente') as id ";
				}
			}

			retorno = session.createSQLQuery(consulta).addScalar("id", Hibernate.INTEGER).uniqueResult();

			return retorno;
		} catch (GenericJDBCException ex) {
			throw new ErroRepositorioException(ex, "Erro no Hibernate");
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			Query query = GeradorHQLCondicional.gerarCondicionalQuery(filtro, "objeto", "from " + pacoteNomeObjeto + " as objeto", session);
			
			retorno = new ArrayList(new CopyOnWriteArraySet(query.list()));

			if (filtro.isInitializeLazy()) {
				inicializarPropriedadesLazies(retorno);
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection pesquisarGerencial(Filtro filtro, String pacoteNomeObjeto) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSessionGerencial();

		try {
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(filtro, "objeto",
					"from " + pacoteNomeObjeto + " as objeto", session).list()));

			if (filtro.isInitializeLazy()) {
				inicializarPropriedadesLazies(retorno);
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	@SuppressWarnings("rawtypes")
	private void inicializarPropriedadesLazies(Collection colecao) {
		for (Iterator iter = colecao.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof ObjetoTransacao) {
				ObjetoTransacao objTrans = (ObjetoTransacao) element;
				objTrans.initializeLazy();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection pesquisar(Collection ids, Filtro filtro, String pacoteNomeObjeto) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			retorno = new ArrayList(new CopyOnWriteArraySet(session.createQuery("from " + pacoteNomeObjeto + " where id IN (:ids)")
					.setParameterList("ids", ids).list()));

			if (!filtro.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil.processaObjetosParaCarregamento(filtro.getColecaoCaminhosParaCarregamentoEntidades(), retorno);
			}
			if (filtro.isInitializeLazy()) {
				inicializarPropriedadesLazies(retorno);
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	public void atualizar(Object objeto) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			session.update(objeto);
			session.flush();
		} catch (Exception e){
			throw new ErroRepositorioException(e, "Erro ao atualizar objeto");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@SuppressWarnings("rawtypes")
	public void remover(int id, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			Iterator iterator = session.createQuery("from " + pacoteNomeObjeto + " where id = :id").setInteger("id", id).iterate();

			if (!iterator.hasNext()) {
				throw new RemocaoRegistroNaoExistenteException();

			}

			while (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj instanceof ObjetoTransacao && operacaoEfetuada != null) {
					ObjetoTransacao objetoTransacao = (ObjetoTransacao) obj;
					objetoTransacao.setOperacaoEfetuada(operacaoEfetuada);
					Iterator it = acaoUsuarioHelper.iterator();
					while (it.hasNext()) {
						UsuarioAcaoUsuarioHelper helper = (UsuarioAcaoUsuarioHelper) it.next();
						objetoTransacao.adicionarUsuario(helper.getUsuario(), helper.getUsuarioAcao());
					}
				}
				iterator.remove();
			}
			session.flush();
		} catch (JDBCException e) {
			throw new RemocaoInvalidaException(e);

		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void remover(Object objeto) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			session.delete(objeto);
			session.flush();
		} catch (JDBCException e) {
			throw new RemocaoInvalidaException(e);
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Object inserirOuAtualizar(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object retorno = null;

		try {
			Object retornoMetodo = descobrirIdClasse(objeto);

			if (retornoMetodo == null) {
				retorno = session.save(objeto);
			} else {
				session.update(objeto);

			}
			session.flush();

			return retorno;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate: " + objeto.getClass().getName() + " falhou na inserção");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Object descobrirIdClasse(Object objeto) throws ErroRepositorioException {
		Object retornoMetodo = null;

		try {
			retornoMetodo = objeto.getClass().getMethod("getId", (Class[]) null).invoke(objeto, (Object[]) null);

		} catch (IllegalArgumentException e) {
			throw new ErroRepositorioException(e);
		} catch (SecurityException e) {
			throw new ErroRepositorioException(e);
		} catch (IllegalAccessException e) {
			throw new ErroRepositorioException(e);
		} catch (InvocationTargetException e) {
			throw new ErroRepositorioException(e);
		} catch (NoSuchMethodException e) {
			try {
				retornoMetodo = objeto.getClass().getMethod("getComp_id", (Class[]) null).invoke(objeto, (Object[]) null);
			} catch (IllegalArgumentException e1) {
				throw new ErroRepositorioException(e);
			} catch (SecurityException e1) {
				throw new ErroRepositorioException(e);
			} catch (IllegalAccessException e1) {
				throw new ErroRepositorioException(e);
			} catch (InvocationTargetException e1) {
				throw new ErroRepositorioException(e);
			} catch (NoSuchMethodException e1) {
				throw new ErroRepositorioException(e);
			}

		}
		return retornoMetodo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection pesquisar(Filtro filtro, int pageOffset, String pacoteNomeObjeto) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional
					.gerarCondicionalQuery(filtro, "objeto", "from " + pacoteNomeObjeto + " as objeto", session).setFirstResult(10 * pageOffset)
					.setMaxResults(10).list()));

			if (!filtro.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil.processaObjetosParaCarregamento(filtro.getColecaoCaminhosParaCarregamentoEntidades(), retorno);
			}
			if (filtro.isInitializeLazy()) {
				inicializarPropriedadesLazies(retorno);
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int totalRegistrosPesquisa(Filtro filtro, String pacoteNomeObjeto) throws ErroRepositorioException {
		int retorno = 0;
		Session session = HibernateUtil.getSession();

		try {

			List camposOrderBy = new ArrayList();

			Collection caminhosParaCarregamentoEntidades = new TreeSet();

			camposOrderBy = filtro.getCamposOrderBy();

			caminhosParaCarregamentoEntidades = filtro.getColecaoCaminhosParaCarregamentoEntidades();

			filtro.limparCamposOrderBy();
			filtro.limparColecaoCaminhosParaCarregamentoEntidades();

			retorno = (Integer) GeradorHQLCondicional.gerarCondicionalQuery(filtro, "objeto",
					"select count(distinct objeto.id) from " + pacoteNomeObjeto + " as objeto", session).uniqueResult();

			filtro.setCampoOrderBy((String[]) camposOrderBy.toArray(new String[camposOrderBy.size()]));

			filtro.setColecaoCaminhosParaCarregamentoEntidades(caminhosParaCarregamentoEntidades);

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	@SuppressWarnings("rawtypes")
	public void inserirBatch(List list) throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();

		if (list != null && !list.isEmpty()) {
			Iterator it = list.iterator();
			try {
				while (it.hasNext()) {
					Object obj = it.next();
					session.insert(obj);
				}
			} catch (HibernateException e) {
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<NacionalFeriado> pesquisarFeriadosNacionais() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			return session.createCriteria(NacionalFeriado.class).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public DbVersaoBase pesquisarDbVersaoBase() throws ErroRepositorioException {

		DbVersaoBase dbVersaoBase = null;

		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT dbvb " + " FROM DbVersaoBase dbvb " + " WHERE dbvb.versaoDataBase = " + " (SELECT MAX(dbvimp.versaoDataBase) "
					+ " FROM DbVersaoBase dbvimp)";

			dbVersaoBase = (DbVersaoBase) session.createQuery(consulta).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return dbVersaoBase;
	}

	public Object inserirComCommit(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object retorno = null;

		try {

			//session.beginTransaction();
			session.persist(objeto);
			retorno = session.save(objeto);
		//	session.getTransaction().commit();
			session.flush();
			session.clear();

			return retorno;
		} catch (GenericJDBCException ex) {
			ex.printStackTrace();
			throw new ErroRepositorioException(ex, "Erro no Hibernate");
		} catch (CallbackException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, e.getMessage());
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
}
