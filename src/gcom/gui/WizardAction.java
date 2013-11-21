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
package gcom.gui;

import gcom.gui.StatusWizard.StatusWizardItem;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class WizardAction extends DispatchAction {

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
	protected ActionForward redirecionadorWizard(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (httpServletRequest.getAttribute("confirmacao") != null
				&& ((String) httpServletRequest.getAttribute("confirmacao"))
						.trim().equalsIgnoreCase("true")) {
			return actionMapping.findForward("telaConfirmacao");
		} else {

			HttpSession sessao = httpServletRequest.getSession(false);
			StatusWizard statusWizard = (StatusWizard) sessao
					.getAttribute("statusWizard");
			String destino = httpServletRequest.getParameter("destino");
			String concluir = null;

			if (httpServletRequest.getParameter("concluir") != null
					&& !httpServletRequest.getParameter("concluir").equals("")) {
				concluir = httpServletRequest.getParameter("concluir");
			} else if (httpServletRequest.getAttribute("concluir") != null
					&& !httpServletRequest.getAttribute("concluir").equals("")) {
				concluir = (String) httpServletRequest.getAttribute("concluir");
			}

			String proximoCaminhoAction = null;

			if (concluir != null && concluir.trim().equalsIgnoreCase("true")) {
				// se o action for o de concluir validar o form
				ActionErrors errors = actionForm.validate(actionMapping,
						httpServletRequest);

				if (errors != null && !errors.isEmpty()) {
					saveErrors(httpServletRequest, errors);
					return actionMapping.findForward("telaErrosApresentacao");

				}

				proximoCaminhoAction = statusWizard.getCaminhoActionConclusao();
			} else {

				StatusWizardItem statusWizardItem = statusWizard
						.retornarItemWizard(Integer.parseInt(destino));

				proximoCaminhoAction = statusWizardItem
						.getCaminhoActionInicial();
			}

			try {
				return ((ActionForward) getClass().getMethod(
						proximoCaminhoAction,
						new Class[] { ActionMapping.class, ActionForm.class,
								HttpServletRequest.class,
								HttpServletResponse.class }).invoke(
						this,
						new Object[] { actionMapping, actionForm,
								httpServletRequest, httpServletResponse }));
			} catch (SecurityException ex) {
				throw new ActionServletException("erro.sistema", ex);
			} catch (NoSuchMethodException ex) {
				throw new ActionServletException("erro.sistema", ex);
			} catch (InvocationTargetException ex) {
				// caso o método execute jogue ActionServletException ou
				// ControladorException
				throw ((RuntimeException) ex.getCause());
			} catch (IllegalArgumentException ex) {
				throw new ActionServletException("erro.sistema", ex);
			} catch (IllegalAccessException ex) {
				throw new ActionServletException("erro.sistema", ex);
			}
		}
	}

}
