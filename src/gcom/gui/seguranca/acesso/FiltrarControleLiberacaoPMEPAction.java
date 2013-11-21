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
package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial;
import gcom.seguranca.acesso.FiltroControleLiberacaoPermissaoEspecial;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0283] Filtrar Controle de Liberação de Permissão Especial
 * 
 * @author Daniel Alves
 * @date 13/08/2010
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */

public class FiltrarControleLiberacaoPMEPAction extends GcomAction {

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

		ActionForward retorno = actionMapping
				.findForward("exibirResultadoPesquisaControleLiberacaoPMEPAction");
		
		FiltrarControleLiberacaoPMEPActionForm filtrarControleLiberacaoPMEPActionForm = (FiltrarControleLiberacaoPMEPActionForm) actionForm;

		
		FiltroControleLiberacaoPermissaoEspecial filtroControleLiberacaoPermissaoEspecial = new FiltroControleLiberacaoPermissaoEspecial();		

		HttpSession sessao = httpServletRequest.getSession(false);
		
		boolean peloMenosUmParametroInformado = false;

		String codigoFuncionalidade = filtrarControleLiberacaoPMEPActionForm.getIdFuncionalidade();

		String codigoPermissaoEspecial = filtrarControleLiberacaoPMEPActionForm.getIdPermissaoEspecial();

		String indicadorUso = filtrarControleLiberacaoPMEPActionForm.getIndicadorUso();


		// Verifica se o campo codigoFuncionalidade foi informado

		if (codigoFuncionalidade != null && !codigoFuncionalidade.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE_ID, codigoFuncionalidade));			

		}

		// Verifica se o campo codigoPermissaoEspecial foi informado

		if (codigoPermissaoEspecial != null && !codigoPermissaoEspecial.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ComparacaoTexto(
					FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL_ID, codigoPermissaoEspecial));

		}

		// Verifica se o campo indicadorUso foi informado

		if (indicadorUso != null
				&& !indicadorUso.trim().equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroControleLiberacaoPermissaoEspecial.INDICADOR_USO, indicadorUso));

		}
		

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		
		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterFuncionalidadeAction e nele verificar se irá para o
		// atualizar ou para o manter
		if (filtrarControleLiberacaoPMEPActionForm.getAtualizar() != null
				&& filtrarControleLiberacaoPMEPActionForm.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",
					filtrarControleLiberacaoPMEPActionForm.getAtualizar());
			
			
		}
		
		/*filtroControleLiberacaoPermissaoEspecial = new FiltroControleLiberacaoPermissaoEspecial();
		
		
		if(filtroControleLiberacaoPermissaoEspecial == null){			
			filtroControleLiberacaoPermissaoEspecial = (FiltroControleLiberacaoPermissaoEspecial)httpServletRequest.getAttribute("filtroControleLiberacaoPermissaoEspecial");
		}*/
		
		filtroControleLiberacaoPermissaoEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE);
		
		filtroControleLiberacaoPermissaoEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL);
		
		
		Collection colecaoResultadoPesquisaControleLiberacaoPMEP = this.getFachada().pesquisar(filtroControleLiberacaoPermissaoEspecial, ControleLiberacaoPermissaoEspecial.class.getName());
		
		String indicadorAtualizar = httpServletRequest
			.getParameter("atualizar");
		
		if(colecaoResultadoPesquisaControleLiberacaoPMEP.size() == 1 && !indicadorAtualizar.equals("")){
						
			retorno = actionMapping.findForward("exibirManterControleLiberacaoPMEPAction");
			
		}
		
		//-----------------------------
		// Manda a colecao pela sessao para o
		// ExibirResultadoPesquisaAction

		sessao.setAttribute("colecaoResultadoPesquisaControleLiberacaoPMEP", colecaoResultadoPesquisaControleLiberacaoPMEP);
		
		return retorno;

	}

}
