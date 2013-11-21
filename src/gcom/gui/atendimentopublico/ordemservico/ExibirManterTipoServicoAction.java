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

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Administrador
 * @date 07/03/2006
 * 
 */
public class ExibirManterTipoServicoAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Administrador
	 * @date 07/03/2006
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
		

		ActionForward retorno = actionMapping.findForward("manterTipoServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoServicoTipo = null;

		// Parte da verificação do filtro
		FiltroTipoServico filtroTipoServico = null;

		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroTipoServico") != null) {
			filtroTipoServico = (FiltroTipoServico) sessao
					.getAttribute("filtroTipoServico");
			filtroTipoServico
			.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterTipoServico"))) {
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTipoServico, ServicoTipo.class.getName());
			colecaoServicoTipo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0002] Nenhum registro encontrado				
			if (colecaoServicoTipo == null || colecaoServicoTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)httpServletRequest.getParameter("indicadorAtualizar");
			
			if (colecaoServicoTipo.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarTipoServico");
				ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next();
				sessao.setAttribute("servicoTipo", servicoTipo);
				sessao.setAttribute("pesquisa", "S");
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoPerfilServicoAction.do");
				//chama ExibirAtualizarTipoPerfilServicoAction
			}else{
				sessao.setAttribute("colecaoServicoTipo", colecaoServicoTipo);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterServicoTipoPerfilAction.do");
				//chama ExibirManterTipoPerfilServicoAction
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");

		return retorno;
	}

}
