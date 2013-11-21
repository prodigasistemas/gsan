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
import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 25 de Abril de 2005
 */
public class ExibirFiltrarTipoRetornoOrdemServicoReferidaAction extends GcomAction {
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
		
		FiltrarTipoRetornoOrdemServicoReferidaActionForm filtrarTipoRetornoOrdemServicoReferidaActionForm = (FiltrarTipoRetornoOrdemServicoReferidaActionForm) actionForm;
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("menu") != null) {

			filtrarTipoRetornoOrdemServicoReferidaActionForm.setAtualizar("1");
			filtrarTipoRetornoOrdemServicoReferidaActionForm.setDeferimento("");
			filtrarTipoRetornoOrdemServicoReferidaActionForm.setTrocaServico("");
			filtrarTipoRetornoOrdemServicoReferidaActionForm.setSituacao("");
			filtrarTipoRetornoOrdemServicoReferidaActionForm.setIndicadorUso("");
			sessao.setAttribute("atualizar", "1");

		} else {

			if (httpServletRequest.getParameter("paginacao") == null && sessao.getAttribute("filtrar") == null) {

				String atualizar = httpServletRequest.getParameter("atualizar");

				if (atualizar != null && !atualizar.equals("")) {
					sessao.setAttribute("atualizar", atualizar);
				} else {
					sessao.removeAttribute("atualizar");
				}

			}
		}
			
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarTipoRetornoOrdemServicoReferida");
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		sessao.removeAttribute("filtrar");

		// Parte que passa as coleções necessárias no jsp
		FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();
		filtroServicoTipoReferencia.setCampoOrderBy(FiltroOSReferidaRetornoTipo.ID);
		Collection colecaoServicoTipoReferencia = fachada.pesquisar(
				filtroServicoTipoReferencia, ServicoTipoReferencia.class.getName());

		if (colecaoServicoTipoReferencia != null && !colecaoServicoTipoReferencia.isEmpty()) {
			sessao.setAttribute("colecaoServicoTipoReferencia", colecaoServicoTipoReferencia);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Referência do Tipo de Serviço");
		}
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.ID);
		Collection colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(
				filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());

		if (colecaoAtendimentoMotivoEncerramento != null && !colecaoAtendimentoMotivoEncerramento.isEmpty()) {
			sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Motivo de Encerramento do Atendimento");
		}
		
		
		return retorno;



	}

}
