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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1046] Pesquisar Permissao Especial
 * 
 * @author Daniel Alves
 * @date 22/07/2010
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */
public class PesquisarPermissaoEspecialAction extends GcomAction {

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
				.findForward("exibirResultadoPesquisaPermissaoEspecialAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarPermissaoEspecialActionForm pesquisarPermissaoEspecialActionForm = (PesquisarPermissaoEspecialActionForm) actionForm;

		FiltroPermissaoEspecial filtroPermissaoEspecial = new FiltroPermissaoEspecial();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String codigo = pesquisarPermissaoEspecialActionForm.getCodigo();

		String descricao = pesquisarPermissaoEspecialActionForm.getDescricao();

		String codigoOperacao = pesquisarPermissaoEspecialActionForm.getCodigoOperacao();
		
		String tipoPesquisa = pesquisarPermissaoEspecialActionForm.getTipoPesquisa();
		// Verifica se o campo codigo foi informado

		if (codigo != null
				&& !codigo.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroPermissaoEspecial.ID, codigo));

		}

		// Verifica se o campo descricao foi informado

				
		
		if (descricao != null
				&& !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			
			 if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {

				    filtroPermissaoEspecial
							.adicionarParametro(new ComparacaoTextoCompleto(
									FiltroPermissaoEspecial.DESCRICAO, descricao));
				} else {

					filtroPermissaoEspecial.adicionarParametro(new ComparacaoTexto(
							FiltroPermissaoEspecial.DESCRICAO, descricao));
				}

		}

		// Verifica se o campo modulo foi informado

		if (codigoOperacao != null
				&& !codigoOperacao.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)
				&& !codigoOperacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroPermissaoEspecial.OPERACAO, codigoOperacao));			
		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroPermissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("operacao");
		
		Collection colecaoPermissaoEspecial = (Collection) fachada.pesquisar(
				filtroPermissaoEspecial, PermissaoEspecial.class.getName());

		if (colecaoPermissaoEspecial == null || colecaoPermissaoEspecial.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"PermissaoEspecial");
		}

		sessao.setAttribute("colecaoPermissaoEspecial", colecaoPermissaoEspecial);
		
		return retorno;

	}

}
