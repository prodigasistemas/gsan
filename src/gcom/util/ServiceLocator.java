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

import gcom.fachada.Fachada;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.ejb.EJBLocalHome;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * Efetua a localização, instanciamento e cache de serviços
 * 
 * @author Administrador
 */

public class ServiceLocator {

	private static ServiceLocator instancia = null;

	private static Context contexto = null;

	private static Map mapaHomes = null;

	private static ConnectionFactory connectionFactoryJms = null;

	private static String empresa = null;

	static {

		try {

			Hashtable env = new Hashtable();

			env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");

			env.put("java.naming.factory.url.pkgs",
					"org.jboss.naming:org.jnp.interfaces");

			env.put(javax.naming.Context.URL_PKG_PREFIXES,
					"org.jboss.naming:org.jnp.interfaces");

			// env.put(javax.naming.Context.PROVIDER_URL, "localhost:1099");
			contexto = new InitialContext(env);

			mapaHomes = Collections.synchronizedMap(new HashMap());

			instancia = new ServiceLocator();

			connectionFactoryJms = (ConnectionFactory) contexto
					.lookup("java:JmsXA");

		} catch (Exception e) {
			e.printStackTrace();
			throw new SistemaException(e);
		}

	}

	/**
	 * Retorna a única instância do ServiceLocator
	 * 
	 * @return A instância do ServiceLocator
	 * @exception ServiceLocatorException
	 *                Exceção levantada caso ocorra algum erro de localização ou
	 *                instanciamento
	 */

	public static ServiceLocator getInstancia() throws ServiceLocatorException {

		return instancia;
	}

	/**
	 * Construtor da classe ServiceLocator
	 */

	private ServiceLocator() {
	}

	/**
	 * Retorna a interface Home de um Entreprise Java Bean
	 * 
	 * @param jndiName
	 *            O jndiName do EJB
	 * @param classe
	 *            O nome do .class da interface home
	 * @return A interface Home de um Entreprise Java Bean
	 * @exception ServiceLocatorException
	 *                Exceção levantada caso ocorra algum erro de localização ou
	 *                instanciamento
	 */

	public Object getHome(String jndiName, Class classe)
			throws ServiceLocatorException {

		Object home = mapaHomes.get(jndiName);

		if (home == null) {

			try {

				Object referencia = contexto.lookup(jndiName);

				home = PortableRemoteObject.narrow(referencia, classe);

				mapaHomes.put(jndiName, home);

			} catch (NamingException e) {

				throw new ServiceLocatorException(e, e.getMessage());
			}

		}

		return home;
	}

	/**
	 * will get the ejb Local home factory. clients need to cast to the type of
	 * EJBHome they desire
	 * 
	 * @param jndiHomeName
	 *            Descrição do parâmetro
	 * @return the Local EJB Home corresponding to the homeName
	 * @exception ServiceLocatorException
	 *                Descrição da exceção
	 */
	public EJBLocalHome getLocalHome(String jndiHomeName)
			throws ServiceLocatorException {

		try {

			return (EJBLocalHome) contexto.lookup(jndiHomeName);
		} catch (NamingException e) {

			throw new ServiceLocatorException(e, e.getMessage());
		}

	}

	public EJBLocalHome getLocalHomePorEmpresa(String jndiHomeName)
			throws ServiceLocatorException {

		try {
			if (empresa == null) {
				empresa = Fachada.getInstancia().pesquisarParametrosDoSistema()
						.getNomeControlador();
			}

			jndiHomeName = jndiHomeName.replace("GCOM", empresa);
			return (EJBLocalHome) contexto.lookup(jndiHomeName);
		} catch (NamingException e) {

			throw new ServiceLocatorException(e, e.getMessage());
		}

	}

	/**
	 * Retorna a instância do serviço de componente solicitado.
	 * 
	 * @param nome
	 *            Nome do serviço de componente necessário.
	 * @return Object Uma instância do serviço de componente solicitado
	 * @exception ServiceLocatorException
	 *                Exceção levantada caso ocorra algum erro de localização ou
	 *                instanciamento.
	 */

	public Object getComponente(String nome) throws ServiceLocatorException {
		return this.carregaClasse(nome);
	}

	/**
	 * Realiza a carga e instanciamento de uma classe
	 * 
	 * @param nome
	 *            O nome da classe a ser carregada
	 * @return Uma instância da classe solicitada
	 * @exception ServiceLocatorException
	 *                Exceção levantada caso ocorra algum erro de localização ou
	 *                instanciamento
	 */

	private Object carregaClasse(String nome) throws ServiceLocatorException {
		Object classe = null;
		Class classAuxiliar = null;
		Method metodo = null;

		try {
			classAuxiliar = Class.forName(nome);
			metodo = classAuxiliar.getMethod("getInstancia", (Class[]) null);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServiceLocatorException(e, "Classe não encontrada");
		} catch (NoSuchMethodException e) {

			try {
				metodo = classAuxiliar.getMethod("getInstance", (Class[]) null);

			} catch (NoSuchMethodException ex) {
				ex.printStackTrace();
				throw new ServiceLocatorException(ex);
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			classe = metodo.invoke(null, (Object[]) null);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new ServiceLocatorException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new ServiceLocatorException(e);
		}

		return classe;
	}

	public void enviarMensagemJms(int metodo, String queueMDB,
			Object[] parametros) throws ServiceLocatorException {

		Connection connection = null;
		Session session = null;

		try {
			// Direciona a fila para o controlador
			Queue destination = (Queue) contexto.lookup(queueMDB);

			connection = connectionFactoryJms.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session
					.createProducer(destination);
			ObjectMessage message = session.createObjectMessage();

			// Monta a mensagem
			message.setIntProperty("nomeMetodo", metodo);

			// Passa os parametros

			message.setObject(parametros);

			// Manda a mensagem
			messageProducer.send(message);

		} catch (NamingException e) {
			throw new ServiceLocatorException(e, e.getMessage());
		} catch (JMSException e) {
			throw new ServiceLocatorException(e, e.getMessage());
		} finally {
			try {

				session.close();
				connection.close();

			} catch (JMSException e) {
				throw new ServiceLocatorException(e, e.getMessage());
			}

		}

	}

	public void enviarMensagemJms(String queueMDB, Object[] parametros)
			throws ServiceLocatorException {

		Connection connection = null;
		Session session = null;

		try {
			// Direciona a fila para o controlador
			Queue destination = (Queue) contexto.lookup(queueMDB);

			connection = connectionFactoryJms.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session
					.createProducer(destination);
			ObjectMessage message = session.createObjectMessage();

			// Passa os parametros
			message.setObject(parametros);

			// Manda a mensagem
			messageProducer.send(message);

		} catch (NamingException e) {
			throw new ServiceLocatorException(e, e.getMessage());
		} catch (JMSException e) {
			throw new ServiceLocatorException(e, e.getMessage());
		} finally {
			try {

				session.close();
				connection.close();

			} catch (JMSException e) {
				throw new ServiceLocatorException(e, e.getMessage());
			}

		}

	}
	
	public static Object getResource(String jndiName) throws ServiceLocatorException {

		Object resource = null;

		try {

			resource = contexto.lookup(jndiName);
		
		} catch (NamingException e) {

			//throw new ServiceLocatorException(e, e.getMessage());
		}

		return resource;
	}
}
