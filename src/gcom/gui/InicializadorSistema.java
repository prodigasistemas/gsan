/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestao de Servicos de Saneamento
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
 * GSAN - Sistema Integrado de Gestao de Servicos de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araujo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl?udio de Andrade Lira
 * Denys Guimaraes Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabiola Gomes de Araujo
 * Flavio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Junior
 * Homero Sampaio Cavalcanti
 * Ivan Sergio da Silva Junior
 * Jose Edmar de Siqueira
 * Jose Thiago Ten?rio Lopes
 * Kassia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Marcio Roberto Batista da Silva
 * Maria de Fatima Sampaio Leite
 * Micaela Maria Coelho de Ara?jo
 * Nelson Mendonca de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Correa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araujo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Savio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa e software livre; voce pode redistribui-lo e/ou
 * modifica-lo sob os termos de Licenca Publica Geral GNU, conforme
 * publicada pela Free Software Foundation; versao 2 da
 * Licenca.

 * Este programa e distribuido na expectativa de ser util, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implicita de
 * COMERCIALIZACAO ou de ADEQUACAO A QUALQUER PROPOSITO EM
 * PARTICULAR. Consulte a Licenca Publica Geral GNU para obter mais
 * detalhes.
 * Voce deve ter recebido uma copia da Licenca Publica Geral GNU
 * junto com este programa; se nao, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package gcom.gui;


import gcom.util.HibernateUtil;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.TimeZone;

import javax.servlet.http.HttpServlet;

/**
 * Servlet Class
 * 
 * @web.servlet name="InicializadorSistema" 
 * 				display-name="Name for InicializadorSistema" 
 * 				description="Description for InicializadorSistema"
 * @web.servlet-mapping url-pattern="/InicializadorSistema"
 * @web.servlet-init-param name="A parameter" value="A value"
 */

public class InicializadorSistema extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public InicializadorSistema() {


		super();

		HibernateUtil.inicializarSessionFactory();

		TimeZone.setDefault(TimeZone.getTimeZone("America/Belem"));

		/*

		 * ATENCAO - NAO ALTERE ESTE ARQUIVO NO SVN!!!!!!!!!

		 */
		
	//	AgendadorTarefas.initAgendador();
		
		/*try {
			if (ServiceLocator.getResource("java:/BatchDS") != null) {
				AgendadorTarefas.initAgendador();
			}
		} catch (ServiceLocatorException e) {
			
			e.printStackTrace();
		}*/

	}
}



