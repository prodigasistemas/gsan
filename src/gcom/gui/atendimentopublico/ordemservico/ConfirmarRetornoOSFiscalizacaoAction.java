/**
 * 
 */
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 06/03/2007
 */
public class ConfirmarRetornoOSFiscalizacaoAction extends GcomAction {

	/**
	 * 
	 * [UC0448] Informar Retorno Ordem de Fiscalização
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

		ActionForward retorno = actionMapping
				.findForward("confirmarRetornoOSFiscalizacao");

		Fachada fachada = Fachada.getInstancia();

		String msgFinal = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		String idOS = (String) sessao.getAttribute("idOS");

		Integer numeroOS = new Integer(idOS);

		// Usuário logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		if (sessao.getAttribute("msgFinal") != null) {
			msgFinal = (String) sessao.getAttribute("msgFinal");
		}

		if (httpServletRequest.getParameter("confirmado") != null) {
			if (httpServletRequest.getParameter("confirmado").equalsIgnoreCase(
					"sim")) {
				Date dataAtual = new Date();
				String indicadorExecucaoSim = "2"; // AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM

				fachada
						.encerrarOSComExecucaoSemReferencia(
								numeroOS,
								dataAtual,
								usuario,
								indicadorExecucaoSim,
								dataAtual,
								"ORDEM DE SERVICO ENCERRADA ATRAVES DA FUNCIONALIDADE DE FISCALIZACAO.",
								ServicoTipo.INDICADOR_PAVIMENTO_NAO,
								null,
								null,
								null,
								null,
								null,
								ServicoTipo.INDICADOR_VISTORIA_SERVICO_TIPO_NAO,
								null,
								null,
								null,
								null);
				retorno = actionMapping.findForward("telaSucesso");

				/*
				 * retorno = actionMapping
				 * .findForward("confirmarRetornoOSFiscalizacao");
				 */

			} else {
				if (httpServletRequest.getParameter("confirmado")
						.equalsIgnoreCase("nao")) {
					retorno = actionMapping.findForward("telaSucesso");
					// montando página de sucesso

				}
			}
		}
		montarPaginaSucesso(httpServletRequest, msgFinal,
				"Informar outra fiscalização",
				"/exibirInformarRetornoOSFiscalizacaoAction.do?menu=sim");

		return retorno;
	}
}
