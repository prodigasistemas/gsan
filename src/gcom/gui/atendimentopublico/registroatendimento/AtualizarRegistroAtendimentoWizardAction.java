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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade gerar as abas que serão responsáveis pelo
 * processo de atualização de um registro de atendimento
 * 
 * @author Sávio Luiz
 * @date 24/07/2006
 */
public class AtualizarRegistroAtendimentoWizardAction extends WizardAction {

	/*
	 * ABA Nº 01 - DADOS GERAIS
	 */
	public ActionForward exibirAtualizarRegistroAtendimentoDadosGeraisAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ExibirAtualizarRegistroAtendimentoDadosGeraisAction()
				.execute(actionMapping, actionForm, httpServletRequest,
						httpServletResponse);
	}

	public ActionForward atualizarRegistroAtendimentoDadosGeraisAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		new AtualizarRegistroAtendimentoDadosGeraisAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);

		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}

	/*
	 * ABA Nº 02 - LOCAL OCORRÊNCIA
	 */
	public ActionForward exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ExibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction()
				.execute(actionMapping, actionForm, httpServletRequest,
						httpServletResponse);
	}

	public ActionForward atualizarRegistroAtendimentoDadosLocalOcorrenciaAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		retorno = new AtualizarRegistroAtendimentoDadosLocalOcorrenciaAction()
				.execute(actionMapping, actionForm, httpServletRequest,
						httpServletResponse);
		if (httpServletRequest.getAttribute("telaOpcaoConsultar") != null) {
			return retorno;
		}
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);

	}

	/*
	 * ABA Nº 03 - SOLICITANTE
	 */
	public ActionForward exibirAtualizarRegistroAtendimentoDadosSolicitanteAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ExibirAtualizarRegistroAtendimentoDadosSolicitanteAction()
				.execute(actionMapping, actionForm, httpServletRequest,
						httpServletResponse);
	}

	public ActionForward atualizarRegistroAtendimentoDadosSolicitanteAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		new AtualizarRegistroAtendimentoDadosSolicitanteAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);

		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}
	
	/*
	 * ABA Nº 04 - ANEXO
	 */
	public ActionForward exibirAtualizarRegistroAtendimentoAnexosAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ExibirAtualizarRegistroAtendimentoAnexosAction()
				.execute(actionMapping, actionForm, httpServletRequest,
						httpServletResponse);
	}

	public ActionForward atualizarRegistroAtendimentoAnexosAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		new AtualizarRegistroAtendimentoAnexosAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);

		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}

	/*
	 * CONCLUIR
	 */
	public ActionForward concluirAtualizarRegistroAtendimentoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ConcluirAtualizarRegistroAtendimentoAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}

	/*
	 * CANCELAR
	 */
	public ActionForward cancelarAtualizarRegistroAtendimentoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new CancelarAtualizarRegistroAtendimentoAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}

}
