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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
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
 * 
 * @author Vinicius Medeiros
 * @date 10/04/2008
 */
public class ExibirAtualizarArrecadacaoFormaAction extends GcomAction {

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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarArrecadacaoForma");

		AtualizarArrecadacaoFormaActionForm atualizarArrecadacaoFormaActionForm = (AtualizarArrecadacaoFormaActionForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String idArrecadacaoForma = httpServletRequest
			.getParameter("idRegistroAtualizacao");

		// Verifica se veio do Manter ou do Filtrar
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (idArrecadacaoForma == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				idArrecadacaoForma = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			} else {
				idArrecadacaoForma = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();

		
		if (idArrecadacaoForma != null && !idArrecadacaoForma.trim().equals("")
				&& Integer.parseInt(idArrecadacaoForma) > 0) {

			FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
			filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(
					FiltroArrecadacaoForma.CODIGO, idArrecadacaoForma));
			Collection colecaoArrecadacaoForma = fachada.pesquisar(
					filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
			if (colecaoArrecadacaoForma != null
					&& !colecaoArrecadacaoForma.isEmpty()) {
				arrecadacaoForma = (ArrecadacaoForma) Util
						.retonarObjetoDeColecao(colecaoArrecadacaoForma);
			}

			if (idArrecadacaoForma == null
					|| idArrecadacaoForma.trim().equals("")) {

				atualizarArrecadacaoFormaActionForm
						.setIdArrecadacaoForma(arrecadacaoForma.getId()
								.toString());
				atualizarArrecadacaoFormaActionForm
						.setDescricao(arrecadacaoForma.getDescricao());
				atualizarArrecadacaoFormaActionForm
						.setCodigoArrecadacaoForma(arrecadacaoForma
								.getCodigoArrecadacaoForma());

			}

			atualizarArrecadacaoFormaActionForm
					.setIdArrecadacaoForma(idArrecadacaoForma);

			atualizarArrecadacaoFormaActionForm.setDescricao(arrecadacaoForma
					.getDescricao());

			atualizarArrecadacaoFormaActionForm
					.setCodigoArrecadacaoForma(arrecadacaoForma
							.getCodigoArrecadacaoForma());

			sessao.setAttribute("arrecadacaoFormaAtualizar", arrecadacaoForma);

			if (sessao.getAttribute("colecaoArrecadacaoForma") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarArrecadacaoFormaAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarArrecadacaoFormaAction.do");
			}

		}

		return retorno;
	}
}