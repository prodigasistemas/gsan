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

import java.util.Collection;

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0386]FILTRAR TIPO PERFIL SERVICO
 * 
 * @author Kássia Albuquerque
 * @date 25/10/2006
 */

public class FiltrarTipoPerfilServicoAction extends GcomAction {

	public static final String INDICADOR_TODOS = "3";

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping
					.findForward("exibirManterTipoPerfilServico");
	
			// Mudar isso quando tiver esquema de segurança
			HttpSession sessao = httpServletRequest.getSession(false);
	
			Fachada fachada = Fachada.getInstancia();
	
			FiltrarTipoPerfilServicoActionForm form = (FiltrarTipoPerfilServicoActionForm) actionForm;
	
			// Recupera todos os campos da página para ser colocada no filtro
			// posteriormente
			String codigoPerfilServico = form.getCodigoPerfilServico();
			String descricaoPerfilServico = form.getDescricaoPerfilServico();
			String abreviaturaPerfilServico = form.getAbrevPerfilServico();
			String qtdeComponentesEquipes = form.getQtdComponentesEquipe();
			String idEquipamentoEspecial = form.getIdEquipamentoEspecial();
			String descricaoEquipamentoEspecial = form
					.getDescricaoEquipamentoEspecial();
			String indicadorVeiculoProprio = form.getIndicadorVeiculoProprio();
			String indicadorUso = form.getIndicadorUso();
	
			String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
	
			if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
				sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
			} else {
				sessao.removeAttribute("indicadorAtualizar");
			}
	
			boolean peloMenosUmParametroInformado = false;
	
			FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo(
					FiltroServicoPerfilTipo.DESCRICAO);
	
			filtroServicoPerfilTipo
					.adicionarCaminhoParaCarregamentoEntidade("equipamentosEspeciais");
	
			// Código
			if (codigoPerfilServico != null
					&& !codigoPerfilServico.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoPerfilTipo.ID, codigoPerfilServico));
	
			}
	
			// Descrição
			if (descricaoPerfilServico != null
					&& !descricaoPerfilServico.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ComparacaoTexto(
						FiltroServicoPerfilTipo.DESCRICAO, descricaoPerfilServico));
	
			}
	
			// Descrição Abreviada
			if (abreviaturaPerfilServico != null
					&& !abreviaturaPerfilServico.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ComparacaoTexto(
						FiltroServicoPerfilTipo.DESCRICAO_ABREVIADA,
						abreviaturaPerfilServico));
	
			}
	
			// Quantidade de Componentes
			if (qtdeComponentesEquipes != null
					&& !qtdeComponentesEquipes.trim().equalsIgnoreCase("")) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoPerfilTipo.COMPONENTES_EQUIPE,
						qtdeComponentesEquipes));
	
			}
	
			// Equipamento Especial
			if (idEquipamentoEspecial != null
					&& !idEquipamentoEspecial.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
	
				if (descricaoEquipamentoEspecial == null
						|| descricaoEquipamentoEspecial.equals("")) {
					FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();
	
					filtroTabelaAuxiliarAbreviada
							.adicionarParametro(new ParametroSimples(
									FiltroTabelaAuxiliarAbreviada.ID,
									idEquipamentoEspecial));
	
					Collection colecaoEquipamentosEspeciais = fachada.pesquisar(
							filtroTabelaAuxiliarAbreviada,
							EquipamentosEspeciais.class.getName());
	
					if (colecaoEquipamentosEspeciais == null
							|| colecaoEquipamentosEspeciais.isEmpty()) {
						throw new ActionServletException(
								"atencao.equipamento_especial_inexistente");
					}
				}
	
				filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoPerfilTipo.ID_EQUIPES_COMPONENTES,
						idEquipamentoEspecial));
	
			}
	
			// Indicador Veículo Próprio
			if (indicadorVeiculoProprio != null
					&& !indicadorVeiculoProprio
							.equals(FiltrarTipoPerfilServicoAction.INDICADOR_TODOS)) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoPerfilTipo.VEICULO_PROPIO,
						indicadorVeiculoProprio));
	
			}
	
			// Indicador de Uso
			if (indicadorUso != null
					&& !indicadorUso
							.equals(FiltrarTipoPerfilServicoAction.INDICADOR_TODOS)) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoPerfilTipo.INDICADOR_USO, indicadorUso));
	
			}
	
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException(
						"atencao.filtro.nenhum_parametro_informado");
			}
	
			// Manda o filtro pela sessao para o
			// ExibirManterTipoPerfilServicoAction
			sessao.setAttribute("filtroServicoPerfilTipo", filtroServicoPerfilTipo);
	
			return retorno;
	}
}
