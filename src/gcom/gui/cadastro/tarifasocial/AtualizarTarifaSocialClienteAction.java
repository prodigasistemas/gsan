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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Rafael Corrêa
 */
public class AtualizarTarifaSocialClienteAction extends GcomAction {
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("voltar");
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String concluir = httpServletRequest.getParameter("concluir");
		
		if (concluir != null && !concluir.equals("")) {
			retorno = actionMapping
			.findForward("concluir");
		}
		
		boolean existeUsuario = false;
		boolean existeProprietario = false;
		
		Cliente clienteUsuario = null;
		Cliente clienteEconomiaUsuario = null;
		
		if (sessao.getAttribute("colecaoClienteImovel") != null) {
		
		Collection colecaoClienteImovel = (Collection) sessao.getAttribute("colecaoClienteImovel");
		
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
			Iterator colecaoClienteImovelIterator = colecaoClienteImovel
					.iterator();
			while (colecaoClienteImovelIterator.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovelIterator
						.next();

				if (clienteImovel.getClienteRelacaoTipo() != null) {
					if (clienteImovel.getClienteRelacaoTipo().getId()
							.toString().equals(
									ClienteRelacaoTipo.USUARIO.toString())) {
						existeUsuario = true;
						clienteUsuario = clienteImovel.getCliente();
					} else if (clienteImovel.getClienteRelacaoTipo().getId()
							.toString().equals(
									ClienteRelacaoTipo.PROPRIETARIO.toString())) {
						existeProprietario = true;
					}
				}
			}
		}
		
		} else if (sessao.getAttribute("colecaoClienteImovelEconomia") != null) {
			
			Collection colecaoClienteImovelEconomia = (Collection) sessao.getAttribute("colecaoClienteImovelEconomia");

			if (colecaoClienteImovelEconomia != null && !colecaoClienteImovelEconomia.isEmpty()) {
				Iterator colecaoClienteImovelEconomiaIterator = colecaoClienteImovelEconomia
						.iterator();
				while (colecaoClienteImovelEconomiaIterator.hasNext()) {
					ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) colecaoClienteImovelEconomiaIterator
							.next();
					
					if (clienteImovelEconomia.getClienteRelacaoTipo() != null) {
						if (clienteImovelEconomia.getClienteRelacaoTipo().getId()
								.toString().equals(
										ClienteRelacaoTipo.USUARIO.toString())) {
							existeUsuario = true;
							clienteEconomiaUsuario = clienteImovelEconomia.getCliente();
						} else if (clienteImovelEconomia.getClienteRelacaoTipo().getId()
								.toString().equals(
										ClienteRelacaoTipo.PROPRIETARIO.toString())) {
							existeProprietario = true;
						}
					}
				}
			}
			
		}
		
		if (!existeUsuario) {
			throw new ActionServletException("atencao.informe.cliente_usuario");
		}
		
		if (!existeProprietario) {
			throw new ActionServletException("atencao.informe.cliente_proprietario");
		}
		
		TarifaSocialHelper tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelperAtualizar");
		
		if (clienteUsuario != null) {
			ClienteImovel clienteImovelTarifaSocial = tarifaSocialHelperAtualizar.getClienteImovel();
			clienteImovelTarifaSocial.setCliente(clienteUsuario);
			tarifaSocialHelperAtualizar.setClienteImovel(clienteImovelTarifaSocial);
		} else if (clienteEconomiaUsuario != null) {
			ClienteImovelEconomia clienteImovelEconomiaTarifaSocial = tarifaSocialHelperAtualizar.getClienteImovelEconomia();
			clienteImovelEconomiaTarifaSocial.setCliente(clienteEconomiaUsuario);
			tarifaSocialHelperAtualizar.setClienteImovelEconomia(clienteImovelEconomiaTarifaSocial);
		}
		
		sessao.setAttribute("tarifaSocialHelperAtualizar", tarifaSocialHelperAtualizar);
		
		httpServletRequest.setAttribute("voltar", true);

		return retorno;

	}
	
}	