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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vinicius Medeiros
 * @date 203/06/2008
 */
public class ExibirAtualizarConsumoAnormalidadeAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("consumoAnormalidadeAtualizar");

		AtualizarConsumoAnormalidadeActionForm atualizarConsumoAnormalidadeActionForm = (AtualizarConsumoAnormalidadeActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = httpServletRequest.getParameter("idRegistroAtualizacao");

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		ConsumoAnormalidade consumoAnormalidade = new ConsumoAnormalidade();

		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
			filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroConsumoAnormalidade.ID, id));
			Collection colecaoConsumoAnormalidade = fachada.pesquisar(
					filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());
			if (colecaoConsumoAnormalidade != null
					&& !colecaoConsumoAnormalidade.isEmpty()) {
				consumoAnormalidade = (ConsumoAnormalidade) Util
						.retonarObjetoDeColecao(colecaoConsumoAnormalidade);
			}

			if (id == null || id.trim().equals("")) {

				atualizarConsumoAnormalidadeActionForm.setId(consumoAnormalidade
						.getId().toString());

				atualizarConsumoAnormalidadeActionForm
						.setDescricao(consumoAnormalidade.getDescricao());

				atualizarConsumoAnormalidadeActionForm
						.setDescricaoAbreviada(consumoAnormalidade
								.getDescricaoAbreviada());
				atualizarConsumoAnormalidadeActionForm
					.setIndicadorRevisaoComPermissaoEspecial(
							consumoAnormalidade.getIndicadorRevisaoPermissaoEspecial().toString());

			}

			atualizarConsumoAnormalidadeActionForm.setId(id);

			atualizarConsumoAnormalidadeActionForm.setDescricao(consumoAnormalidade
					.getDescricao());

			atualizarConsumoAnormalidadeActionForm
					.setDescricaoAbreviada(consumoAnormalidade
							.getDescricaoAbreviada());

			atualizarConsumoAnormalidadeActionForm
			.setMensagemConta(consumoAnormalidade.getMensagemConta());
			
			atualizarConsumoAnormalidadeActionForm.setIndicadorUso(""
					+ consumoAnormalidade.getIndicadorUso());
			
			atualizarConsumoAnormalidadeActionForm
				.setIndicadorRevisaoComPermissaoEspecial(
					consumoAnormalidade.getIndicadorRevisaoPermissaoEspecial().toString());

			sessao.setAttribute("atualizarConsumoAnormalidade", consumoAnormalidade);

			if (sessao.getAttribute("colecaoConsumoAnormalidade") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarConsumoAnormalidadeAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarConsumoAnormalidadeAction.do");
			}

		}

		return retorno;
	}
}