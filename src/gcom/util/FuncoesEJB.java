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

import java.sql.Connection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

/**
 * < <Descrição da Classe>>
 * 
 * @author
 */
public class FuncoesEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
    private SessionContext sessionContext;

    /**
     * < <Descrição do método>>
     */
    public void ejbCreate() {
    }

    /**
     * < <Descrição do método>>
     */
    public void ejbRemove() {
    }

    /**
     * < <Descrição do método>>
     */
    public void ejbActivate() {
    }

    /**
     * < <Descrição do método>>
     */
    public void ejbPassivate() {
    }

    /**
     * Seta o valor de sessionContext
     * 
     * @param sessionContext
     *            O novo valor de sessionContext
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * Retorna uma conexão
     * 
     * @param contextoJNDI
     *            Uma String representando o contexto jndi
     * @return Uma Conexão
     */
    public Connection alocarConexao(String contextoJNDI) {

        Connection conexao = null;

        try {
            InitialContext contexto = new InitialContext();
            DataSource dataSource = (DataSource) contexto.lookup(contextoJNDI);

            conexao = dataSource.getConnection();

        } catch (Exception e) {
        	sessionContext.setRollbackOnly();
            e.printStackTrace();
            throw new SistemaException(e, e.getMessage());
        }

        return conexao;
    }

    /**
     * Retorna uma transação
     * 
     * @return Uma Transação
     */
    public UserTransaction alocarTransacao() {

        UserTransaction ut = null;

        try {
            InitialContext contexto = new InitialContext();

            ut = (UserTransaction) contexto.lookup("java:comp/UserTransaction");

        } catch (Exception e) {
        	sessionContext.setRollbackOnly();
            e.printStackTrace();
            throw new SistemaException(e, e.getMessage());
        }

        return ut;
    }

}
