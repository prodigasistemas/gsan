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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.ClienteFone;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RemoverClienteTelefoneAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = null;

		if (httpServletRequest.getParameter("tela").trim().equals("inserir")) {
			retorno = actionMapping.findForward("inserirClienteTelefone");
		} else if (httpServletRequest.getParameter("tela").trim().equals(
				"atualizar")) {
			retorno = actionMapping.findForward("atualizarClienteTelefone");
		}

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

		Collection colecaoClienteFone = (Collection) sessao
				.getAttribute("colecaoClienteFone");

		ClienteFone clienteFone = null;

		// Obtém os ids de remoção
		String[] ids = (String[]) clienteActionForm.get("idRegistrosRemocao");

		if (ids != null && ids.length != 0) {
			if (colecaoClienteFone != null && !colecaoClienteFone.isEmpty()) {

				Iterator iteratorColecaoClienteFone = colecaoClienteFone
						.iterator();

				while (iteratorColecaoClienteFone.hasNext()) {
					clienteFone = (ClienteFone) iteratorColecaoClienteFone
							.next();
					for (int i = 0; i < ids.length; i++) {
						if (clienteFone.getDddTelefone().equals(ids[i])) {
							// Verifica se o clienteFone removido era o
							// principal para adicionar o indicador de principal
							// para o primeiro da lista
							if (obterTimestampIdObjeto(clienteFone) == (((Long) clienteActionForm
									.get("indicadorTelefonePadrao"))
									.longValue())
									&& colecaoClienteFone.size() > 1) {

								Iterator iteratorTemp = colecaoClienteFone
										.iterator();
								// Pega o primeiro da lista
								ClienteFone clienteFonePrimeiroDaLista = (ClienteFone) iteratorTemp
										.next();

								// Verifica se o primeiro da lista é o mesmo que
								// será removido
								if (clienteFonePrimeiroDaLista
										.equals(clienteFone)) {
									// Seta como principal o segundo da lista
									clienteFonePrimeiroDaLista = (ClienteFone) iteratorTemp
											.next();
								}

								// Seta o indicador no form
								clienteActionForm
										.set(
												"indicadorTelefonePadrao",
												new Long(
														obterTimestampIdObjeto(clienteFonePrimeiroDaLista)));
							}
							iteratorColecaoClienteFone.remove();
						}
					}

				}

			}

			clienteActionForm.set("botaoClicado", null);

			// Se a coleção de telefones tiver apenas um item, então este item
			// tem que estar selecionado
			// como telefone principal
			Iterator iterator = colecaoClienteFone.iterator();

			if (colecaoClienteFone != null && colecaoClienteFone.size() == 1) {

				clienteFone = (ClienteFone) iterator.next();

				clienteActionForm.set("indicadorTelefonePadrao", new Long(
						obterTimestampIdObjeto(clienteFone)));

			}
		}
		return retorno;
	}
}
