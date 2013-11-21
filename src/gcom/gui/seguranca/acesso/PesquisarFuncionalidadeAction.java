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
package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.Funcionalidade;
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
 * [UC0282] Pesquisar Funcionalidade
 * 
 * @author Rômulo Aurélio
 * @date 10/05/2006
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */
public class PesquisarFuncionalidadeAction extends GcomAction {

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
				.findForward("exibirResultadoPesquisaFuncionalidadeAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarFuncionalidadeActionForm pesquisarFuncionalidadeActionForm = (PesquisarFuncionalidadeActionForm) actionForm;

		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.OPERACOES);
		
		boolean peloMenosUmParametroInformado = false;

		String codigo = pesquisarFuncionalidadeActionForm.getCodigo();
		String descricao = pesquisarFuncionalidadeActionForm.getDescricao();
		String idModulo = pesquisarFuncionalidadeActionForm.getModulo();
		String Pentrada = pesquisarFuncionalidadeActionForm.getIndicadorPontoEntrada();
		String indicadorRegistraTransacao = pesquisarFuncionalidadeActionForm.getIndicadorRegistraTransacao();

		
		String tipoPesquisa = pesquisarFuncionalidadeActionForm.getTipoPesquisa();
		// Verifica se o campo codigo foi informado

		if (codigo != null
				&& !codigo.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionalidade.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidade.ID, codigo));

		}

		// Verifica se o campo descricao foi informado

				
		
		if (descricao != null
				&& !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			 if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {

					filtroFuncionalidade
							.adicionarParametro(new ComparacaoTextoCompleto(
									FiltroFuncionalidade.DESCRICAO, descricao));
				} else {

					filtroFuncionalidade.adicionarParametro(new ComparacaoTexto(
							FiltroFuncionalidade.DESCRICAO, descricao));
				}

		}

		// Verifica se o campo modulo foi informado

		if (idModulo != null
				&& !idModulo.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroFuncionalidade.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidade.MODULO_ID, idModulo));
			

		}

		// Verifica se o campo Ponto de Entrada foi informado

		if (Pentrada != null
				&& !Pentrada.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroFuncionalidade.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA, Pentrada));

		}
		
		
		if (indicadorRegistraTransacao != null && 
			!indicadorRegistraTransacao.trim().equals("")) {

			filtroFuncionalidade.adicionarParametro(
				new ParametroSimples(
					FiltroFuncionalidade.OPERACOES_INDICADOR_REGISTRA_TRANSACAO, ConstantesSistema.SIM));
		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("modulo");
		
		Collection colecaoFuncionalidade = 
			(Collection) this.getFachada().pesquisar(
				filtroFuncionalidade, Funcionalidade.class.getName());

		if (colecaoFuncionalidade == null || colecaoFuncionalidade.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Funcionalidade");
		}

		sessao.setAttribute("colecaoFuncionalidade", colecaoFuncionalidade);
		
		return retorno;

	}

}
