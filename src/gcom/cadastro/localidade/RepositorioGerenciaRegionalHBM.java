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
package gcom.cadastro.localidade;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.util.Collection;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * 
 * Title: GCOM
 * 
 * Description: Repositório de Gerência Regional
 * 
 * Copyright: Copyright (c) 2005
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Pedro Alexandre
 * @created 13 de Janeiro de 2006
 * @version 1.0
 */
public class RepositorioGerenciaRegionalHBM implements
		IRepositorioGerenciaRegional {

	// cria uma variável da inteface do repositório de gerência regional
	private static IRepositorioGerenciaRegional instancia;

	// construtor da classe
	private RepositorioGerenciaRegionalHBM() {
	}

	// retorna uma instância do repositório
	public static IRepositorioGerenciaRegional getInstancia() {
		// se não existe ainda a instância
		if (instancia == null) {
			// cria a instância do repositório
			instancia = new RepositorioGerenciaRegionalHBM();
		}
		// retorna a instância do repositório
		return instancia;
	}

	/**
	 * Pesquisa uma coleção de gerências regionais
	 * 
	 * @return Coleção de Gerências Regionais
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection<GerenciaRegional> pesquisarGerenciaRegional()
			throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada
		Collection retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select gerenciaRegional "
					+ "from GerenciaRegional gerenciaRegional ";

			// pesquisa a coleção de acordo com o parâmetro informado
			retorno = session.createQuery(consulta).list();

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
	 * Pesquisa uma gerência regional pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Gerência Regional
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoGerenciaRegionalRelatorio(
			Integer idGerenciaRegional) throws ErroRepositorioException {
		// cria a variável que vai armazenar a coleção pesquisada
				
		Object[] retorno = null;

		// cria a sessão com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select gr.greg_id as id, " +
				"gr.greg_nmabreviado as nomeAbreviado "
					+ "from cadastro.gerencia_regional gr "
					+ "where gr.greg_id = " + idGerenciaRegional.toString();

			// pesquisa a coleção de acordo com o parâmetro informado
			Collection colecaoGerenciasRegionais = session.createSQLQuery(consulta)
			.addScalar("id", Hibernate.INTEGER)
			.addScalar("nomeAbreviado", Hibernate.STRING).list();
			
			retorno = Util.retonarObjetoDeColecaoArray(colecaoGerenciasRegionais);

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
     * Pesquisa o id da gerência regional para a qual a localidade pertence.
     * 
     * [UC0267] Encerrar Arrecadação do Mês
     * 
     * @author Pedro Alexandre
     * @date 05/01/2007
     * 
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Integer pesquisarIdGerenciaParaLocalidade(Integer idLocalidade) throws ErroRepositorioException {

        Integer retorno = null;

        Session session = HibernateUtil.getSession();

        String consulta = "";

        try {

            consulta = "select greg.id from Localidade loca " + "left join loca.gerenciaRegional greg " + "where loca.id = :idLocalidade";

            retorno = (Integer) session.createQuery(consulta).setInteger("idLocalidade", idLocalidade).uniqueResult();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }

}
