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
package gcom.cadastro.geografico;

import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.GeradorHQLCondicional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGeograficoHBM implements IRepositorioGeografico {

	private static IRepositorioGeografico instancia;

	/**
	 * Construtor da classe RepositorioAcessoHBM
	 */

	private RepositorioGeograficoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioGeografico getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioGeograficoHBM();
		}

		return instancia;
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
	 * < <Descrição do método>>
	 * 
	 * @param codigoSetorComercial
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public Collection pesquisarMunicipoPeloSetorComercial(
			String codigoSetorComercial, String idMunicipio)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {
			String query = "select municipio from gcom.cadastro.localidade.SetorComercial setor join setor.municipio  municipio where setor.municipio.id = :municipio and setor.codigo = :setor order by setor.municipio.nome";
			retorno = session.createQuery(query).setInteger("setor",
					Integer.parseInt(codigoSetorComercial)).setInteger(
					"municipio", Integer.parseInt(idMunicipio)).list();
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
	 * Método que retorna o maior código do bairro de um município
	 * 
	 * @author Rafael Corrêa
	 * @date 10/07/2006
	 * 
	 * @param idMunicipio
	 * @return
	 * @throws ControladorException
	 */

	public int pesquisarMaximoCodigoBairro(Integer idMunicipio)
			throws ErroRepositorioException {
		
		Integer retorno;
		int max = 0;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT max(b.codigo) " + "FROM Bairro b "
					+ "INNER JOIN b.municipio m "
					+ "WHERE m.id = :idMunicipio ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idMunicipio", idMunicipio).setMaxResults(1).uniqueResult();
			
			if(retorno != null){
				max = retorno.intValue();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return max;
	}

	/**
	 * Pesquisa um município pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Município
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoMunicipioRelatorio(Integer idMunicipio)
			throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada

		Object[] retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select m.muni_id as id, "
					+ "m.muni_nmmunicipio as nome "
					+ "from cadastro.municipio m " + "where m.muni_id = "
					+ idMunicipio.toString();

			// pesquisa a coleção de acordo com o parâmetro informado
			Collection colecaoMunicipios = session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER).addScalar("nome",
							Hibernate.STRING).list();

			retorno = Util.retonarObjetoDeColecaoArray(colecaoMunicipios);

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
	 * Pesquisa um bairro pelo código e pelo id do município
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Bairro
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoBairroRelatorio(Integer codigoBairro,
			Integer idMunicipio) throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada

		Object[] retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o SQL para consulta
			String consulta = "select b.bair_cdbairro as codigo, "
					+ "b.bair_nmbairro as nome "
					+ "from cadastro.municipio m, " + "cadastro.bairro b "
					+ "where b.muni_id = m.muni_id and" + " m.muni_id = "
					+ idMunicipio.toString() + " and b.bair_cdbairro = "
					+ codigoBairro.toString();

			// pesquisa a coleção de acordo com o parâmetro informado
			Collection colecaoBairros = session.createSQLQuery(consulta)
					.addScalar("codigo", Hibernate.INTEGER).addScalar("nome",
							Hibernate.STRING).list();

			retorno = Util.retonarObjetoDeColecaoArray(colecaoBairros);

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
	 * @author Vivianne Sousa
	 * @date 26/12/2006
	 * 
	 * @return colecao de BairroArea
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection pesquisarBairroArea(Integer idBairro)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {
			String query =  "select bairroArea " +
							"from BairroArea as bairroArea " +
							"left join fetch bairroArea.distritoOperacional " +
							"where bairroArea.bairro.id = :idBairro";
			
			retorno = session.createQuery(query).setInteger(
					"idBairro", idBairro).list();
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
	 * Remove todos os BairroArea de um determinado Bairro
	 * 
	 * @author Vivianne Sousa
	 * @date 27/12/200
	 * 
	 * @param idBairro
	 * @exception ErroRepositorioException
	 *              
	 */
	public void removerTodosBairroAreaPorBairro(Integer idBairro)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			Iterator iterator = session
					.createQuery(
							"from gcom.cadastro.geografico.BairroArea as bairroArea where bairroArea.bairro.id = :idBairro")
					.setInteger("idBairro", idBairro.intValue()).iterate();

			while (iterator.hasNext()) {
				iterator.next();
				iterator.remove();

			}

			session.flush();
		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	 /**
	 * Método que retorna o maior id do Município
	 * 
	 * @author Rafael Corrêa
	 * @date 24/07/2008
	 * 
	 * @return
	 * @throws ControladorException
	 */   
    
    public int pesquisarMaximoIdMunicipio()
	throws ErroRepositorioException {
    	int retorno = 0;
    	
    	Integer maxIdMunicipio;
    	
    	Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT max(m.id) "
					+ "FROM Municipio m ";

			maxIdMunicipio = (Integer) session.createQuery(consulta)
					.setMaxResults(1).uniqueResult();
	
			if (maxIdMunicipio != null){
				retorno = maxIdMunicipio;	
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
    	
    	return retorno;
    }
    /**
	 * Método que retorna o municipio do Imovel
	 * 
	 * @author Hugo Amorim
	 * @date 27/08/2009
	 * 
	 * @return Municipio
	 * @throws ControladorException
	 */  
    public Collection pesquisarMunicipioDoImovel(Integer idImovel) 
    	throws ErroRepositorioException {
    	Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {
			String query =  "select m "+
						" from gcom.cadastro.imovel.Imovel as i"+
						" inner join i.logradouroBairro as lb"+
						" inner join lb.bairro as b"+
						" inner join b.municipio as m"+
						" where i.id = :idImovel";

			
			retorno = session.createQuery(query).setInteger(
					"idImovel", idImovel).list();
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
	 * Método reponsável por retornar todos os municípios que possuem alguma
	 * associação com uma localidade (localidade.muni_idprincipal != null)
	 * 
	 * @author Diogo Peixoto
	 * @date 26/04/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */  
    public Collection pesquisarMunicipiosAssociadoLocalidade() throws ErroRepositorioException {
    	Session session = HibernateUtil.getSession();
		Collection retorno = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			
			sb.append("SELECT DISTINCT(muni) ");
			sb.append("FROM Localidade loc ");
			sb.append("INNER JOIN loc.municipio muni ");

			retorno = session.createQuery(sb.toString()).list();
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
