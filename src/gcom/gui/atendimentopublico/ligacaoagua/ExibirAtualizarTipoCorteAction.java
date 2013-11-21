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
package gcom.gui.atendimentopublico.ligacaoagua;

import java.util.Collection;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ivan Sergio
 * @date 06/12/2010
 */

public class ExibirAtualizarTipoCorteAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("atualizarTipoCorte");
		
		AtualizarTipoCorteActionForm atualizarTipoCorteActionForm = (AtualizarTipoCorteActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		CorteTipo corteTipo = null;
		String idTipoCorte = null;
		
		if (httpServletRequest.getParameter("idTipoCorte") != null && 
				!httpServletRequest.getParameter("idTipoCorte").equalsIgnoreCase("")) {
			//tela do manter
			idTipoCorte = (String) httpServletRequest.getParameter("idTipoCorte");
			sessao.setAttribute("idTipoCorte", idTipoCorte);
		} else if (sessao.getAttribute("idTipoCorte") != null && !sessao.getAttribute("idTipoCorte").equals("")) {
			//tela do filtrar
			idTipoCorte = (String) sessao.getAttribute("idTipoCorte");
		}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
			//link na tela de sucesso do inserir material
			idTipoCorte = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoCorteAction.do?menu=sim");
		}
		
		if (idTipoCorte == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
				corteTipo = (CorteTipo) sessao.getAttribute("tipoCorteAtualizar");
			}else{
				idTipoCorte = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
			}
		}else if (sessao.getAttribute("tipoCorteAtualizar") != null && !sessao.getAttribute("tipoCorteAtualizar").equals("")) {
			corteTipo = (CorteTipo) sessao.getAttribute("tipoCorteAtualizar");
		}else {
			if (corteTipo == null) {
				if (idTipoCorte != null && !idTipoCorte.equals("")) {
					FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();
					filtroCorteTipo.adicionarParametro(new ParametroSimples(
							FiltroCorteTipo.ID, idTipoCorte));
	
					Collection colecaoTipoCorte = fachada.pesquisar(filtroCorteTipo, CorteTipo.class.getName());
	
					if (colecaoTipoCorte != null && !colecaoTipoCorte.isEmpty()) {
						corteTipo = (CorteTipo) Util.retonarObjetoDeColecao(colecaoTipoCorte);
					}
				}
			}
		}
		
		atualizarTipoCorteActionForm.setIdTipoCorte(""+corteTipo.getId());
		atualizarTipoCorteActionForm.setDescricao(corteTipo.getDescricao());
		atualizarTipoCorteActionForm.setIndicadorUso(""+corteTipo.getIndicadorUso());
		atualizarTipoCorteActionForm.setIndicadorCorteAdministrativo(""+corteTipo.getIndicadorCorteAdministrativo());

		sessao.setAttribute("corteTipo", corteTipo);
		
		return retorno;
	}
}