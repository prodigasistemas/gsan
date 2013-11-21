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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.atendimentopublico.ordemservico.FiltrarTipoPerfilServicoAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1103] - FILTRAR Tipo de Corte
 * 
 * @author Ivan Sergio
 * @date 03/12/2010
 */
public class FiltrarTipoCorteAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterTipoCorte");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarTipoCorteActionForm form = (FiltrarTipoCorteActionForm) actionForm;
		
		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente
		
		String idTipoCorte = form.getIdTipoCorte();
		String descricao = form.getDescricao();
		String indicadorUso = form.getIndicadorUso();
		String indicadorCorteAdministrativo = form.getIndicadorCorteAdministrativo();
		String tipoPesquisa = form.getTipoPesquisa();
		
		String indicadorAtualizar = httpServletRequest.getParameter("atualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}
		
		boolean peloMenosUmParametroInformado = false;

		FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo(FiltroCorteTipo.DESCRICAO);
		
		// Codigo
		if (idTipoCorte != null && !idTipoCorte.equalsIgnoreCase("")){
			peloMenosUmParametroInformado = true;
			filtroCorteTipo.adicionarParametro(new ParametroSimples(
					FiltroCorteTipo.ID, idTipoCorte));
		}
		
		// Descrição
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if(tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())){
				filtroCorteTipo.adicionarParametro( 
						new ComparacaoTextoCompleto(FiltroCorteTipo.DESCRICAO, descricao));
			} else {
				filtroCorteTipo.adicionarParametro( 
						new ComparacaoTexto(FiltroCorteTipo.DESCRICAO, descricao));
			}
		}
		
		// Indicador de Uso
		if (indicadorUso != null && !indicadorUso.equals(FiltrarTipoPerfilServicoAction.INDICADOR_TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroCorteTipo.adicionarParametro(new ParametroSimples(
					FiltroCorteTipo.INDICADOR_USO, indicadorUso));
		}
		
		// Indicador Corte Administrativo
		if (indicadorCorteAdministrativo != null && !indicadorCorteAdministrativo.equals(FiltrarTipoPerfilServicoAction.INDICADOR_TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroCorteTipo.adicionarParametro(new ParametroSimples(
					FiltroCorteTipo.INDICADOR_CORTE_ADMINISTRATIVO, indicadorCorteAdministrativo));
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		// Manda o filtro pela sessao
		sessao.setAttribute("filtroCorteTipo", filtroCorteTipo);
		
		return retorno;
	}
}
	
