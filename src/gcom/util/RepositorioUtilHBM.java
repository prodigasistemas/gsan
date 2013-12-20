/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.util;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.GeradorHQLCondicional;
import gcom.util.filtro.PersistenciaUtil;

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

/**
 * Repositorio para as funções utilitárias do sistema
 * 
 * @author rodrigo
 */
public class RepositorioUtilHBM implements IRepositorioUtil {

	private static RepositorioUtilHBM instancia;

	/**
	 * Construtor da classe RepositorioAcessoHBM
	 */
	protected RepositorioUtilHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static RepositorioUtilHBM getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioUtilHBM();
		}

		return instancia;
	}

	/**
	 * Retorna a contagem máxima de registros de uma determinada classe no
	 * sistema
	 * 
	 * @param classe
	 *            Classe (.class) que será feita a contagem
	 * @return Número de registros
	 * @exception ErroRepositorioException
	 *                Erro no mecanismo hibernate
	 */

	public int registroMaximo(Class classe) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {

			Query query = session.createQuery("select count(*) from "
					+ classe.getName());

			Integer retorno = (Integer) query.list().iterator().next();

			return retorno.intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @param limite
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection limiteMaximoFiltroPesquisa(Filtro filtro,
			String pacoteNomeObjeto, int limite)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		Collection resultado = null;

		try {

			resultado = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(filtro,
					"objeto", "from " + pacoteNomeObjeto + " as objeto",
					session).setMaxResults(limite).list()));

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return resultado;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param classe
	 *            Descrição do parâmetro
	 * @param atributo
	 *            Descrição do parâmetro
	 * @param parametro1
	 *            Descrição do parâmetro
	 * @param parametro2
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public int valorMaximo(Class classe, String atributo, String parametro1,
			String parametro2) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery("select max( tabela." + atributo
					+ ") from " + classe.getName() + " tabela"
					+ " where tabela." + parametro1 + " = " + parametro2);

			Integer retorno = (Integer) query.list().iterator().next();

			if (retorno == null
					|| retorno.toString().trim().equalsIgnoreCase("")) {
				retorno = new Integer(0);
			}
			return retorno.intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param classe
	 *            Descrição do parâmetro
	 * @param atributo
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public int valorMaximo(Class classe, String atributo)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery("select max(" + atributo
					+ ") from " + classe.getName());

			Integer retorno = (Integer) query.list().iterator().next();

			return retorno.intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Retorna o único registro do SistemaParametros.
	 * 
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public SistemaParametro pesquisarParametrosDoSistema()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			return (SistemaParametro) session.createCriteria(
					SistemaParametro.class).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Object inserir(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object retorno = null;

		try {

			retorno = session.save(objeto);
			session.flush();

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

	/**
	 * Obtem o próximo valor do sequence do Banco do Imovel ou Cliente
	 * 
	 * @author Rafael Santos
	 * @since 17/11/2006
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Object obterNextVal(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Object retorno = null;
		String consulta = null;

		try {
			String dialect = HibernateUtil.getDialect();
			// verifica se o objeto é do tipo Imovel
			if (objeto instanceof Imovel) {
				if (dialect.toUpperCase().contains("ORACLE")){
					consulta = "select cadastro.seq_imovel.nextval as id from dual ";
				} else {
					consulta = "select nextval('cadastro.seq_imovel') as id ";
				}
				// verifica se o objeto é do tipo Cliente
			} else if (objeto instanceof Cliente) {
				if (dialect.toUpperCase().contains("ORACLE")){
					consulta = "select cadastro.seq_cliente.nextval as id from dual ";
				} else {
					consulta = "select nextval('cadastro.seq_cliente') as id ";
				}
			}

			retorno = session.createSQLQuery(consulta).addScalar("id",
					Hibernate.INTEGER).uniqueResult();

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

	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		Collection retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(filtro,
					"objeto", "from " + pacoteNomeObjeto + " as objeto",
					session).list()));

			// parametro usado para determinar se quer inicializar os atributos lazies
			if (filtro.isInitializeLazy()){
				inicializarPropriedadesLazies(retorno);
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}
	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarGerencial(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		Collection retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSessionGerencial();

		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(filtro,
					"objeto", "from " + pacoteNomeObjeto + " as objeto",
					session).list()));

			// parametro usado para determinar se quer inicializar os atributos lazies
			if (filtro.isInitializeLazy()){
				inicializarPropriedadesLazies(retorno);
			}
			
			 } catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}
	
	/**
	 * Inicializar as propriedades lazies chamando o método
	 * initializeLazy de cada objeto
	 * @param colecao
	 */
	private void inicializarPropriedadesLazies(Collection colecao){
		for (Iterator iter = colecao.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof ObjetoTransacao){
				ObjetoTransacao objTrans = (ObjetoTransacao) element;
				objTrans.initializeLazy();
			}			
		}
	}

	/**
	 * Pesquisa um conjunto de entidades através de um array de código
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisar(Collection ids, Filtro filtro,
			String pacoteNomeObjeto) throws ErroRepositorioException {
		// cria a coleção de retorno
		Collection retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(session.createQuery(
					"from " + pacoteNomeObjeto + " where id IN (:ids)")
					.setParameterList("ids", ids).list()));

			// Carrega os objetos informados no filtro
			if (!filtro.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil
						.processaObjetosParaCarregamento(filtro
								.getColecaoCaminhosParaCarregamentoEntidades(),
								retorno);
			}
			// parametro usado para determinar se quer inicializar os atributos lazies
			if (filtro.isInitializeLazy()){
				inicializarPropriedadesLazies(retorno);
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void atualizar(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			session.update(objeto);
			session.flush();

		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param id
	 *            Descrição do parâmetro
	 * @param pacoteNomeObjeto
	 *            Descrição do parâmetro
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void remover(int id, String pacoteNomeObjeto,
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ErroRepositorioException {

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			Iterator iterator = session.createQuery(
					"from " + pacoteNomeObjeto + " where id = :id").setInteger(
					"id", id).iterate();

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
						UsuarioAcaoUsuarioHelper helper = (UsuarioAcaoUsuarioHelper) it
								.next();
						objetoTransacao.adicionarUsuario(helper.getUsuario(),
								helper.getUsuarioAcao());
					}
				}
				iterator.remove();
			}
			session.flush();
			// restrições no sistema
		} catch (JDBCException e) {
			// e.printStackTrace();
			// levanta a exceção para a próxima camada
			throw new RemocaoInvalidaException(e);
			// erro no hibernate
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * 
	 * 
	 * @pram objeto Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void remover(Object objeto) throws ErroRepositorioException {
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			session.delete(objeto);
			session.flush();
			// restrições no sistema
		} catch (JDBCException e) {
			// e.printStackTrace();
			// levanta a exceção para a próxima camada
			throw new RemocaoInvalidaException(e);
			// erro no hibernate
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());

		} catch (HibernateException e) {
			e.printStackTrace();
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 */

	/**
	 * Insere ou atualiza um registro na base dependendo da chave
	 * 
	 * @param objeto
	 *            Referência do objeto a ser inserida
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Erro no mecanismo Hibernate
	 */
	public Object inserirOuAtualizar(Object objeto)
			throws ErroRepositorioException {

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
			throw new ErroRepositorioException("Erro no Hibernate: "
					+ objeto.getClass().getName() + " falhou na inserção");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Object descobrirIdClasse(Object objeto)
			throws ErroRepositorioException {
		Object retornoMetodo = null;

		try {
			retornoMetodo = objeto.getClass()
					.getMethod("getId", (Class[]) null).invoke(objeto,
							(Object[]) null);

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
				retornoMetodo = objeto.getClass().getMethod("getComp_id",
						(Class[]) null).invoke(objeto, (Object[]) null);
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

	/**
	 * Este método de pesquisa serve para localizar qualquer objeto no sistema.
	 * Ele aceita como parâmetro um offset que indica a página desejada no
	 * esquema de paginação. A paginação procura 10 registros de casa vez.
	 * 
	 * @author Rodrigo Silveira
	 * @date 30/03/2006
	 * 
	 * @param filtro
	 *            Filtro da pesquisa
	 * @param pageOffset
	 *            Indicador da página desejada do esquema de paginação
	 * @param pacoteNomeObjeto
	 *            Pacote do objeto
	 * @return Coleção dos resultados da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public Collection pesquisar(Filtro filtro, int pageOffset,
			String pacoteNomeObjeto) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(filtro,
					"objeto", "from " + pacoteNomeObjeto + " as objeto",
					session).setFirstResult(10 * pageOffset).setMaxResults(10)
					.list()));

			// Carrega os objetos informados no filtro
			if (!filtro.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil
						.processaObjetosParaCarregamento(filtro
								.getColecaoCaminhosParaCarregamentoEntidades(),
								retorno);
			}
			// parametro usado para determinar se quer inicializar os atributos lazies
			if (filtro.isInitializeLazy()){
				inicializarPropriedadesLazies(retorno);
			}
			
		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Informa o número total de registros de uma pesquisa, auxiliando o esquema
	 * de paginação
	 * 
	 * @author Rodrigo Silveira
	 * @date 30/03/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return Número de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exceção do repositório
	 */
	public int totalRegistrosPesquisa(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		int retorno = 0;
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			List camposOrderBy = new ArrayList();
			
			Collection caminhosParaCarregamentoEntidades = new TreeSet();

			camposOrderBy = filtro.getCamposOrderBy();
			
			caminhosParaCarregamentoEntidades = filtro.getColecaoCaminhosParaCarregamentoEntidades();

			filtro.limparCamposOrderBy();
			
			filtro.limparColecaoCaminhosParaCarregamentoEntidades();

			// pesquisa a coleção de atividades e atribui a variável "retorno"
			retorno = (Integer) GeradorHQLCondicional.gerarCondicionalQuery(
					filtro,
					"objeto",
					"select count(distinct objeto.id) from " + pacoteNomeObjeto
							+ " as objeto", session).uniqueResult();

			filtro.setCampoOrderBy((String[]) camposOrderBy
					.toArray(new String[camposOrderBy.size()]));
			
			filtro.setColecaoCaminhosParaCarregamentoEntidades(caminhosParaCarregamentoEntidades);
			

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;

	}

	/**
	 * Método que insere uma Lista em Batch
	 * 
	 * inserirBatch
	 * 
	 * @author Roberta Costa
	 * @date 17/05/2006
	 * 
	 * @param list
	 * @throws ErroRepositorioException
	 */
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
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}
	}

	/**
	 * Recupera a coleção de feriados nacionais
	 * 
	 * @author Pedro Alexandre
	 * @date 13/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<NacionalFeriado> pesquisarFeriadosNacionais()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			return session.createCriteria(NacionalFeriado.class).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * UC?? - ????????
	 * 
	 * @author Rômulo Aurélio Filho
	 * @date 25/01/2007
	 * @descricao O método retorna um objeto com a maior data de Implementacao
	 *            do Banco e sua ultima alteracao
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	
	
	public DbVersaoBase pesquisarDbVersaoBase() throws ErroRepositorioException {

		DbVersaoBase dbVersaoBase = null;

		Session session = HibernateUtil.getSession();
		
		String consulta = null;

		try {
			
			consulta = "SELECT dbvb " 
				+ " FROM DbVersaoBase dbvb " 
				+ " WHERE dbvb.versaoDataBase = " 
				+ " (SELECT MAX(dbvimp.versaoDataBase) " 
				+ " FROM DbVersaoBase dbvimp)";

			dbVersaoBase = (DbVersaoBase) session.createQuery(
					consulta).setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return dbVersaoBase;
	}
}
