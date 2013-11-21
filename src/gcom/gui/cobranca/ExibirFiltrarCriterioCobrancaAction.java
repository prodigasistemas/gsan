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
package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para filtrar o criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 17/04/2006
 */
public class ExibirFiltrarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarCriterioCobranca");

		CriterioCobrancaFiltrarActionForm criterioCobrancaFiltrarActionForm = (CriterioCobrancaFiltrarActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		//caso venha da tela atualizar sem ter passado pelo filtro
		//(no caso vim da tela de sucesso do inserir)
		if (sessao.getAttribute("indicadorAtualizar") == null) {
			sessao.setAttribute("indicadorAtualizar", "1");
		}

		String limpar = (String) httpServletRequest.getParameter("limpar");
		if (limpar != null && !limpar.equals("")) {
			criterioCobrancaFiltrarActionForm.setDescricaoCriterio("");
			criterioCobrancaFiltrarActionForm.setDataInicioVigencia("");
			criterioCobrancaFiltrarActionForm.setNumeroAnoContaAntiga("");
			criterioCobrancaFiltrarActionForm
					.setOpcaoAcaoImovelDebitoContasAntigas("");
			criterioCobrancaFiltrarActionForm
					.setOpcaoAcaoImovelDebitoMesConta("");
			criterioCobrancaFiltrarActionForm.setOpcaoAcaoImovelSit("");
			criterioCobrancaFiltrarActionForm.setOpcaoAcaoImovelSitEspecial("");
			criterioCobrancaFiltrarActionForm
					.setOpcaoAcaoInquilinoDebitoMesConta("");
			criterioCobrancaFiltrarActionForm.setOpcaoContasRevisao("");
			criterioCobrancaFiltrarActionForm.setIndicadorUso("3");
			sessao.setAttribute("indicadorAtualizar", "1");
			httpServletRequest.setAttribute("nomeCampo", "descricaoCriterio");

		}
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			criterioCobrancaFiltrarActionForm.setIndicadorUso("3");
			httpServletRequest.setAttribute("nomeCampo", "descricaoCriterio");
		}

		return retorno;
	}
}
