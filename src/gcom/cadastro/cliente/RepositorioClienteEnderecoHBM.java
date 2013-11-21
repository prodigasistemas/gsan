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
package gcom.cadastro.cliente;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.filtro.GeradorHQLCondicional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RepositorioClienteEnderecoHBM implements
		IRepositorioClienteEndereco {

	private static IRepositorioClienteEndereco instancia;

	/**
	 * Constructor for the RepositorioClienteTipoHBM object
	 */
	public RepositorioClienteEnderecoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioClienteEndereco getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioClienteEnderecoHBM();
		}

		return instancia;
	}

	/**
	 * Pesquisa uma coleção de cliente endereco com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteEndereco(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ErroRepositorioException {

		// cria a coleção de retorno
		Collection retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroClienteEndereco,
							"clienteEndereco",
							"select distinct clienteEndereco.cliente.id,clienteEndereco.cliente.nome,clienteEndereco.cliente.clienteTipo.descricao,clienteEndereco.cliente.cpf,clienteEndereco.cliente.cnpj, clienteEndereco.cliente.indicadorUso "
									+ "from gcom.cadastro.cliente.ClienteEndereco as clienteEndereco "
									+ "left join clienteEndereco.cliente.clienteTipo "
									+ "left join clienteEndereco.logradouroCep ",
							session).list()));
			// erro no hibernate
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
	 * Pesquisa ClienteEndereco percorrendo o ClienteImovel
	 * 
	 * @param filtroClienteEndereco
	 * @return
	 * @throws ErroRepositorioException
	 */
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
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
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

}