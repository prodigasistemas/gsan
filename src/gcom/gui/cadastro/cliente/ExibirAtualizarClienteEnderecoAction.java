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

import gcom.cadastro.cliente.ClienteEndereco;
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
 * Action para exibir a página de endereço
 * 
 * @author Rodrigo
 */
public class ExibirAtualizarClienteEnderecoAction extends GcomAction {

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
		ActionForward retorno = actionMapping
				.findForward("atualizarClienteEndereco");

		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm clienteActionForm = (DynaValidatorForm) sessao
				.getAttribute("ClienteActionForm");

		// enderecoCorrespondenciaSelecao novo que pode ser setado no remover de
		// endereço
		// indica o novo enderecoCorrespondenciaSelecao
		Long enderecoCorrespondenciaSelecaoNovo = (Long) httpServletRequest
				.getAttribute("enderecoCorrespondenciaSelecao");

		if (enderecoCorrespondenciaSelecaoNovo != null) {
			clienteActionForm.set("enderecoCorrespondenciaSelecao",
					enderecoCorrespondenciaSelecaoNovo);
		}

		// Se a coleção de endereços tiver apenas um item, então este item tem
		// que estar selecionado
		// como endereço de correspondencia
		Collection colecaoEnderecos = (Collection) sessao
				.getAttribute("colecaoEnderecos");

		if (colecaoEnderecos != null && colecaoEnderecos.size() == 1) {

			Iterator iterator = colecaoEnderecos.iterator();

			ClienteEndereco clienteEndereco = (ClienteEndereco) iterator.next();

			clienteActionForm.set("enderecoCorrespondenciaSelecao", new Long(
					obterTimestampIdObjeto(clienteEndereco)));

		}
		
		//**********************************************************************
		// CRC2103
		// Autor: Ivan Sergio
		// Data: 02/07/2009
		// Verifica se a tela deve ser exibida como um PopUp
		//**********************************************************************
		if (httpServletRequest.getParameter("POPUP") != null) {
			if (httpServletRequest.getParameter("POPUP").equals("true")) {
				sessao.setAttribute("POPUP", "true");
			}else {
				sessao.setAttribute("POPUP", "false");
			}
		}else if (sessao.getAttribute("POPUP") == null) {
			sessao.setAttribute("POPUP", "false");
		}
		//**********************************************************************		

		return retorno;
	}
}
