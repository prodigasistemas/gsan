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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 18/06/2007
 */
public class FiltrarClienteTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Cliente Tipo
	 * 
	 * [UC0???] Filtrar Cliente Tipo
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 18/06/2007
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterClienteTipo");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarClienteTipoActionForm filtrarClienteTipoActionForm = (FiltrarClienteTipoActionForm) actionForm;

		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = filtrarClienteTipoActionForm.getDescricao();

		String tipoPessoa = filtrarClienteTipoActionForm.getTipoPessoa();

		String esferaPoder = filtrarClienteTipoActionForm.getEsferaPoder();

		String tipoPesquisa = filtrarClienteTipoActionForm.getTipoPesquisa();
		
		String indicadorUso = filtrarClienteTipoActionForm.getIndicadorUso();
		
//		 Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pela sessão uma variável para o
		// ExibirManterEquipeAction e nele verificar se irá para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sessão
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		// Verifica se o campo Descrição do Cliente Tipo foi informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroClienteTipo
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroClienteTipo.DESCRICAO, descricao));
			} else {

				filtroClienteTipo.adicionarParametro(new ComparacaoTexto(
						FiltroClienteTipo.DESCRICAO, descricao));
			}

			filtroClienteTipo.adicionarParametro(new ComparacaoTexto(
					FiltroClienteTipo.DESCRICAO, descricao));

		}

		// Verifica se o campo Tipo Pessoa foi informado

		if (tipoPessoa != null
				&& !tipoPessoa.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroClienteTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteTipo.INDICADOR_PESSOA_FISICA_JURIDICA,
					tipoPessoa));

		}

		// Verifica se o campo Esfera Poder foi informado

		if (esferaPoder != null
				&& !esferaPoder.trim().equalsIgnoreCase(
						"")) {

			peloMenosUmParametroInformado = true;

			filtroClienteTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteTipo.ESFERA_PODER, esferaPoder));

		}
		
		// Verifica se o campo Indicador de Uso foi informado

		if (indicadorUso != null
				&& !indicadorUso.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroClienteTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteTipo.INDICADOR_USO,
					indicadorUso));

		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

		sessao.setAttribute("filtroClienteTipo", filtroClienteTipo);

		return retorno;
	}

}