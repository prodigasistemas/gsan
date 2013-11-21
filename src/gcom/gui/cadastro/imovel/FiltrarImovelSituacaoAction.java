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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelSituacao;
import gcom.cadastro.imovel.ImovelSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0037] Filtrar Situação do Imóvel Tem o objetivo de filtrar as situacoes de
 * imovel existentes
 * 
 * @author Rômulo Aurélio
 * @date 31/03/2006
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */
public class FiltrarImovelSituacaoAction extends GcomAction {

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
				.findForward("exibirManterImovelSituacaoAction");

		// Recebe a sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarImovelSituacaoActionForm filtrarImovelSituacaoActionForm = (FiltrarImovelSituacaoActionForm) actionForm;

		FiltroImovelSituacao filtroImovelSituacao = new FiltroImovelSituacao();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		// Recebe os dados do ActionForm

		String idImovelSituacaoTipo = filtrarImovelSituacaoActionForm
				.getImovelSituacaoTipo();
		String idLigacaoAguaSituacao = filtrarImovelSituacaoActionForm
				.getLigacaoAguaSituacao();
		String idLigacaoEsgotoSituacao = filtrarImovelSituacaoActionForm
				.getLigacaoEsgotoSituacao();

		// [FS0002] Verificar preenchimento dos campos

		if (idImovelSituacaoTipo != null && !idImovelSituacaoTipo.equals("-1")) {
			peloMenosUmParametroInformado = true;

			filtroImovelSituacao.adicionarParametro(new ParametroSimples(
					FiltroImovelSituacao.SITUACAO_TIPO, idImovelSituacaoTipo));

		}
		if (idLigacaoAguaSituacao != null
				&& !idLigacaoAguaSituacao.equals("-1")) {
			peloMenosUmParametroInformado = true;
			filtroImovelSituacao.adicionarParametro(new ParametroSimples(
					FiltroImovelSituacao.SITUACAO_AGUA_ID,
					idLigacaoAguaSituacao));
		}
		if (idLigacaoEsgotoSituacao != null
				&& !idLigacaoEsgotoSituacao.equals("-1")) {
			peloMenosUmParametroInformado = true;
			filtroImovelSituacao.adicionarParametro(new ParametroSimples(
					FiltroImovelSituacao.SITUACAO_ESCGOTO_ID,
					idLigacaoEsgotoSituacao));

		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro

		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Coloca os dados do filtro na colecao

		filtroImovelSituacao
				.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
		filtroImovelSituacao
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		filtroImovelSituacao
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

		Collection colecaoImovelSituacao = (Collection) fachada.pesquisar(
				filtroImovelSituacao, ImovelSituacao.class.getName());

		/*if (colecaoImovelSituacao == null || colecaoImovelSituacao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"situação do imóvel");
		}*/

		if (colecaoImovelSituacao == null || colecaoImovelSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		
		// Coloca os dados da colecao na sessao

		sessao.setAttribute("colecaoImovelSituacao", colecaoImovelSituacao);

		// Manda o filtro pelo sessao para o
		// ExibirManterImovelSItuacaoAction

		sessao.setAttribute("filtroImovelSituacao", filtroImovelSituacao);

		httpServletRequest.setAttribute("filtroImovelSituacao",
				filtroImovelSituacao);

		return retorno;

	}
}