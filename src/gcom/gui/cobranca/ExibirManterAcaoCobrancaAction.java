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

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário as ações de cobrança
 * cadastradas e disponíveis para atualização ou remoção
 * 
 * @author Raphael Rossiter
 * @date 22/10/2007
 */
public class ExibirManterAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("manterAcaoCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		if (sessao.getAttribute("filtroCobrancaCriterio") != null) {

			FiltroCobrancaAcao filtroCobrancaAcao = (FiltroCobrancaAcao) sessao
					.getAttribute("filtroCobrancaCriterio");

			filtroCobrancaAcao
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
			filtroCobrancaAcao
					.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

			// Componente de Paginação
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroCobrancaAcao, CobrancaAcao.class.getName());

			Collection colecaoCobrancaAcao = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0001] Verificar existência de dados
			if (colecaoCobrancaAcao == null || colecaoCobrancaAcao.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}

			String identificadorAtualizar = (String) sessao
					.getAttribute("indicadorAtualizar");

			if (colecaoCobrancaAcao.size() == 1
					&& identificadorAtualizar != null) {
				// TELA ATUALIZAR
				retorno = actionMapping.findForward("atualizarAcaoCobranca");
				CobrancaAcao cobrancaAcao = (CobrancaAcao) colecaoCobrancaAcao
						.iterator().next();
				sessao.setAttribute("cobrancaAcao", cobrancaAcao);
			} else {
				sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);
			}
		}

		return retorno;
	}

}
