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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

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
 * @date 07/11/2006
 */
public class FiltrarTipoSolicitacaoEspecificacaoAction extends GcomAction {
	/**
	 * [UC0400] Filtrar Tipo de Solicitação com Especificações
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa de Tipo de
	 * Solicitação com Especificações
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterTipoSolicitacaoEspecificacaoAction");

		FiltrarTipoSolicitacaoEspecificacaoActionForm filtrarTipoSolicitacaoEspecificacaoActionForm = (FiltrarTipoSolicitacaoEspecificacaoActionForm) actionForm;

		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();

		boolean peloMenosUmParametroInformado = false;

//		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;
//
//		Collection colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
//				.getAttribute("colecaoSolicitacaoTipoEspecificacao");
//		if (colecaoSolicitacaoTipoEspecificacao == null
//				|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
//			colecaoSolicitacaoTipoEspecificacao = new ArrayList();
//			// } else {
//			// solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao)
//			// colecaoSolicitacaoTipoEspecificacao
//			// .iterator().next();
//			// }
//			// if (solicitacaoTipoEspecificacao != null) {
//			//
//			// peloMenosUmParametroInformado = true;
//			//
//			// FiltroSolicitacaoTipoEspecificacao
//			// filtroSolicitacaoTipoEspecificacao = new
//			// FiltroSolicitacaoTipoEspecificacao();
//			//
//		}

		// Fachada fachada = Fachada.getInstancia();

		// SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
		// descrição da solicitação tipo
		if (filtrarTipoSolicitacaoEspecificacaoActionForm.getDescricao() != null
				&& !filtrarTipoSolicitacaoEspecificacaoActionForm
						.getDescricao().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroSolicitacaoTipo.DESCRICAO,
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getDescricao()));

		}
		// id do grupo de solicitação da descrição selecionada
		if (filtrarTipoSolicitacaoEspecificacaoActionForm
				.getIdgrupoTipoSolicitacao() != null
				&& !filtrarTipoSolicitacaoEspecificacaoActionForm
						.getIdgrupoTipoSolicitacao().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO_ID,
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getIdgrupoTipoSolicitacao()));

		}

		// indicativo de falta d'agua
		if (filtrarTipoSolicitacaoEspecificacaoActionForm
				.getIndicadorFaltaAgua() != null
				&& !filtrarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorFaltaAgua().equals("3")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_FALTA_AGUA,
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorFaltaAgua()));

		}
		// indicativo de tarifa solcial
		if (filtrarTipoSolicitacaoEspecificacaoActionForm
				.getIndicadorTarifaSocial() != null
				&& !filtrarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorTarifaSocial().equals("3")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_TARIFA_SOCIAL,
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorTarifaSocial()));

		}
		
		// indicativo de falta d'agua
		if (filtrarTipoSolicitacaoEspecificacaoActionForm.getIndicadorUso() != null
				&& !filtrarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorUso().equals("3")) {

			peloMenosUmParametroInformado = true;

			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_USO,
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorUso()));

		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroSolicitacaoTipo
				.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoGrupo");

		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterFuncionalidadeAction e nele verificar se irá para o
		// atualizar ou para o manter
		if (filtrarTipoSolicitacaoEspecificacaoActionForm.getAtualizar() != null
				&& filtrarTipoSolicitacaoEspecificacaoActionForm.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",
					filtrarTipoSolicitacaoEspecificacaoActionForm
							.getAtualizar());

		}

		// Manda o filtro pelo sessao para o
		// ExibirManterTipoSolicitacaoEspecificacaoAction

		sessao.setAttribute("filtroSolicitacaoTipo", filtroSolicitacaoTipo);

//		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
//				colecaoSolicitacaoTipoEspecificacao);

		httpServletRequest.setAttribute("filtroSolicitacaoTipo",
				filtroSolicitacaoTipo);

//		httpServletRequest.setAttribute("colecaoSolicitacaoTipoEspecificacao",
//				colecaoSolicitacaoTipoEspecificacao);

		return retorno;
	}
}
