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
package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * [UC0789] Filtrar Situacao de Ligação de Esgoto
 * 
 * @author Bruno Barros
 *
 * @date 20/05/2008
 */
public class ExibirAtualizarLigacaoEsgotoSituacaoAction extends GcomAction {

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
				.findForward("ligacaoEsgotoSituacaoAtualizar");

		AtualizarLigacaoEsgotoSituacaoActionForm atualizarLigacaoEsgotoSituacaoActionForm = (AtualizarLigacaoEsgotoSituacaoActionForm) actionForm;

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

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();

/*		String descricao = null;
		String descricaoAbreviada = null;
		String consumoMinimoFaturamento = null;
		String indicadorFaturamentoSituacao = null;
		String indicadorExistenciaRede = null;
		String indicadorExistenciaLigacao = null;*/
		
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoSituacao.ID, id));
			Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(
					filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			if (colecaoLigacaoEsgotoSituacao != null
					&& !colecaoLigacaoEsgotoSituacao.isEmpty()) {
				ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util
						.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
			}

			if (id == null || id.trim().equals("")) {

				atualizarLigacaoEsgotoSituacaoActionForm.setId(ligacaoEsgotoSituacao
						.getId().toString());

				atualizarLigacaoEsgotoSituacaoActionForm
						.setDescricao(ligacaoEsgotoSituacao.getDescricao());

				atualizarLigacaoEsgotoSituacaoActionForm
						.setDescricaoAbreviada(ligacaoEsgotoSituacao
								.getDescricaoAbreviado());

			}

			atualizarLigacaoEsgotoSituacaoActionForm.setId(id);

			atualizarLigacaoEsgotoSituacaoActionForm.setDescricao(ligacaoEsgotoSituacao
					.getDescricao());

			atualizarLigacaoEsgotoSituacaoActionForm
					.setDescricaoAbreviada(ligacaoEsgotoSituacao
							.getDescricaoAbreviado());
			
			atualizarLigacaoEsgotoSituacaoActionForm
			.setConsumoMinimoFaturamento(ligacaoEsgotoSituacao
					.getVolumeMinimoFaturamento().toString());

			atualizarLigacaoEsgotoSituacaoActionForm.setIndicadorUso(""
					+ ligacaoEsgotoSituacao.getIndicadorUso());

			atualizarLigacaoEsgotoSituacaoActionForm
			.setIndicadorExistenciaRede(""
					+ ligacaoEsgotoSituacao
							.getIndicadorExistenciaRede());
			
			atualizarLigacaoEsgotoSituacaoActionForm
			.setIndicadorFaturamentoSituacao(""
					+ ligacaoEsgotoSituacao
							.getIndicadorFaturamentoSituacao());
			
			atualizarLigacaoEsgotoSituacaoActionForm
					.setIndicadorExistenciaLigacao(""
							+ ligacaoEsgotoSituacao
									.getIndicadorExistenciaLigacao());

			sessao.setAttribute("atualizarLigacaoEsgotoSituacao", ligacaoEsgotoSituacao);

			if (sessao.getAttribute("colecaoLigacaoEsgotoSituacao") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarLigacaoEsgotoSituacaoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarLigacaoEsgotoSituacaoAction.do");
			}
		}

		return retorno;
	}
}