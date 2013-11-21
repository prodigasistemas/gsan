/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAcao;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1058] MANTER CONSUMO ANORMALIDADE ACAO
 * 
 * @author Rodrigo Cabral
 * @date 04/10/2010
 */

public class ExibirManterConsumoAnormalidadeAcaoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("exibirManterConsumoAnormalidadeAcao");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<ConsumoAnormalidadeAcao> colecaoConsumoAnormalidadeAcao = null;

		// Parte da verificação do filtro
        FiltroConsumoAnormalidadeAcao filtroConsumoAnormalidadeAcao = new FiltroConsumoAnormalidadeAcao();
        
		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroConsumoAnormalidadeAcao") != null) {
			filtroConsumoAnormalidadeAcao = (FiltroConsumoAnormalidadeAcao) sessao.getAttribute("filtroConsumoAnormalidadeAcao");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("exibirManterConsumoAnormalidadeAcao"))) {

			Map resultado = controlarPaginacao(
					httpServletRequest, retorno,	filtroConsumoAnormalidadeAcao, ConsumoAnormalidadeAcao.class.getName());
			colecaoConsumoAnormalidadeAcao = (Collection<ConsumoAnormalidadeAcao>) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// Nenhum registro encontrado				
			if (colecaoConsumoAnormalidadeAcao == null || colecaoConsumoAnormalidadeAcao.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			if (colecaoConsumoAnormalidadeAcao != null
					&& !colecaoConsumoAnormalidadeAcao.isEmpty()) {
				if (colecaoConsumoAnormalidadeAcao.size() == 1
						&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
								.getParameter("page.offset").equals("1"))) {
					if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
						retorno = actionMapping
								.findForward("exibirAtualizarConsumoAnormalidadeAcao");
						ConsumoAnormalidadeAcao consumoAnormalidadeAcao = (ConsumoAnormalidadeAcao) colecaoConsumoAnormalidadeAcao
								.iterator().next();
						sessao.setAttribute("consumoAnormalidadeAcao", consumoAnormalidadeAcao);
					} else {
						httpServletRequest.setAttribute("colecaoConsumoAnormalidadeAcao",
								colecaoConsumoAnormalidadeAcao);
					}
				} else {
					httpServletRequest.setAttribute("colecaoConsumoAnormalidadeAcao",
							colecaoConsumoAnormalidadeAcao);
				}
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
		}
		
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
	} 
	
}
