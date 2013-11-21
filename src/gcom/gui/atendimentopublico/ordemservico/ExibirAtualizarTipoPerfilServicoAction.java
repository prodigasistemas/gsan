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

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [SB0001]Atualizar Tipo Perfil de Serviço
 *
 * @author Kássia Albuquerque
 * @date 31/10/2006
 */

public class ExibirAtualizarTipoPerfilServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarTipoPerfilServico");

		AtualizarTipoPerfilServicoActionForm atualizarTipoPerfilServicoActionForm = (AtualizarTipoPerfilServicoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		ServicoPerfilTipo servicoPerfilTipo = null;
		
		String idServico = null;
		
		String idEquipamentoEspecial = atualizarTipoPerfilServicoActionForm.getEquipamentosEspeciais();
		
		
		if (httpServletRequest.getParameter("idServico") != null) {
			//tela do manter
			idServico = (String) httpServletRequest.getParameter("idServico");
			sessao.setAttribute("idServico", idServico);
		} else if (sessao.getAttribute("idServico") != null) {
			//tela do filtrar
			idServico = (String) sessao.getAttribute("idServico");
		}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
			//link na tela de sucesso do inserir Perfil Serviço
			idServico = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoPerfilServicoAction.do?menu=sim");
		}
		
		if (idServico == null){
			
			if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
				servicoPerfilTipo = (ServicoPerfilTipo) sessao.getAttribute("idRegistroAtualizar");
			}else{
				idServico = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
			}
		}
		
		//-------Parte que trata do código quando o usuário tecla enter     
		if ((idEquipamentoEspecial != null && !idEquipamentoEspecial.equals(""))
				&& (httpServletRequest.getParameter("pesquisa") != null &&
				httpServletRequest.getParameter("pesquisa").equals("S"))) {

			FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

			filtroTabelaAuxiliarAbreviada
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviada.ID, atualizarTipoPerfilServicoActionForm
									.getEquipamentosEspeciais()));

			Collection colecaoEquipamentosEspeciais = fachada.pesquisar(
					filtroTabelaAuxiliarAbreviada, EquipamentosEspeciais.class
							.getName());

			if (colecaoEquipamentosEspeciais != null
					&& !colecaoEquipamentosEspeciais.isEmpty()) {
				
				EquipamentosEspeciais equipamentosEspeciais = (EquipamentosEspeciais) colecaoEquipamentosEspeciais
						.iterator().next();
				atualizarTipoPerfilServicoActionForm.setDescricaoEquipamentoEspecial(equipamentosEspeciais.getDescricao());
				
			} else {
				httpServletRequest.setAttribute("equipamentosEspecialEncontrado", "exception");
				atualizarTipoPerfilServicoActionForm.setEquipamentosEspeciais("");
				atualizarTipoPerfilServicoActionForm.setDescricaoEquipamentoEspecial("EQUIPAMENTO ESPECIAL INEXISTENTE");
			}

		}else{
			//------Inicio da parte que verifica se vem da página de
			// 		servico_perfil_tipo_manter.jsp
		
			
			if (servicoPerfilTipo == null){
			
				if (idServico != null && !idServico.equals("")) {
	
					FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
					
					filtroServicoPerfilTipo.adicionarCaminhoParaCarregamentoEntidade("equipamentosEspeciais");
	
					filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
							FiltroServicoPerfilTipo.ID, idServico));
	
					Collection colecaoServicoPerfilTipo = fachada.pesquisar(filtroServicoPerfilTipo, ServicoPerfilTipo.class
							.getName());
	
					if (colecaoServicoPerfilTipo != null && !colecaoServicoPerfilTipo.isEmpty()) {
						
						servicoPerfilTipo = (ServicoPerfilTipo) Util.retonarObjetoDeColecao(colecaoServicoPerfilTipo);
						
					}
				}
			}
			
			//  ------  O servico Perfil Tipo foi encontrado
			
			atualizarTipoPerfilServicoActionForm.setCodigoPerfilServico(""+servicoPerfilTipo.getId());

			atualizarTipoPerfilServicoActionForm.setDescricaoPerfil(servicoPerfilTipo.getDescricao());

			atualizarTipoPerfilServicoActionForm.setAbreviaturaPerfil(servicoPerfilTipo.getDescricaoAbreviada());

			atualizarTipoPerfilServicoActionForm.setQuantidadeComponente(""+servicoPerfilTipo.getComponentesEquipe());

			if(servicoPerfilTipo.getEquipamentosEspeciais()!= null && !servicoPerfilTipo.getEquipamentosEspeciais().equals("")){
				atualizarTipoPerfilServicoActionForm.setEquipamentosEspeciais(""+servicoPerfilTipo.getEquipamentosEspeciais().getId());
				atualizarTipoPerfilServicoActionForm.setDescricaoEquipamentoEspecial(servicoPerfilTipo.getEquipamentosEspeciais().getDescricao());
			}		
			
			atualizarTipoPerfilServicoActionForm.setVeiculoProprio(""+servicoPerfilTipo.getIndicadorVeiculoProprio());
			
			atualizarTipoPerfilServicoActionForm.setIndicadorUso(""+servicoPerfilTipo.getIndicadorUso());
			
			atualizarTipoPerfilServicoActionForm.setIdTipoPerfilServico(""+servicoPerfilTipo.getId());
			
			sessao.setAttribute("servicoPerfilTipo", servicoPerfilTipo);
			
			// ------ Fim da parte que verifica se vem da página de servico_perfil_tipo_manter.jsp
		}
			
			
			
			
			
			return retorno;
	}
					
		
}

