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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

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
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class InserirClienteTelefoneAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;
		
		Collection colecaoFones = (Collection) sessao
				.getAttribute("colecaoClienteFone");
		
		clienteActionForm.set("botaoAdicionar", "");
		clienteActionForm.set("botaoClicado", "");
		clienteActionForm.set("ddd", "");
		clienteActionForm.set("telefone", "");
		clienteActionForm.set("idTipoTelefone", "");
		clienteActionForm.set("idMunicipio", "");
		clienteActionForm.set("ramal", "");
		clienteActionForm.set("contato", "" );
		clienteActionForm.set("descricaoMunicipio", "");


		if (colecaoFones != null && !colecaoFones.isEmpty()) {

			// Radio que indica qual o telefone principal
			Long indicadorTelefonePadrao = (Long) clienteActionForm
					.get("indicadorTelefonePadrao");

			// Se o telefone padrão for escolhido então o objeto deve ser
			// alterado
			if (indicadorTelefonePadrao != null
					&& indicadorTelefonePadrao.longValue() > 0) {
				Iterator iterator = colecaoFones.iterator();

				// Varre a colecão para descobrir o objeto que tem o telefone
				// principal
				while (iterator.hasNext()) {
					ClienteFone clienteFone = (ClienteFone) iterator.next();

					if (obterTimestampIdObjeto(clienteFone) == indicadorTelefonePadrao
							.longValue()) {
						// Indica que o objeto possui o telefone principal
						clienteFone
								.setIndicadorTelefonePadrao(ConstantesSistema.INDICADOR_TELEFONE_PRINCIPAL);
					} else {
						// Indica que o objeto não possui o telefone principal
						clienteFone
								.setIndicadorTelefonePadrao(ConstantesSistema.INDICADOR_NAO_TELEFONE_PRINCIPAL);
					}
				}

			} else {
				// Nenhum telefone foi indicado como principal
				// Mostra o erro
				throw new ActionServletException(
						"atencao.telefone_principal.nao_selecionado");
			}

		}
		return retorno;
	}
}
