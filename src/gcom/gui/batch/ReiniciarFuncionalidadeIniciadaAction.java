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
package gcom.gui.batch;

import gcom.batch.FiltroFuncionalidadeIniciada;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.ProcessoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remove os bairros selecionados na lista da funcionalidade Manter Bairro
 * 
 * @author Sávio Luiz
 * @created 4 de Julho de 2004
 */
public class ReiniciarFuncionalidadeIniciadaAction extends GcomAction {
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

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		// Obtém os ids de remoção
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarDadosProcessoIniciado");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum
		// registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		Integer idProcessoIniciado = (Integer) sessao
				.getAttribute("idProcessoIniciado");
		
		fachada.reiniciarFuncionalidadesIniciadas(ids, idProcessoIniciado);

		FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada(
				FiltroFuncionalidadeIniciada.SEQUENCIAL_EXECUCAO);
		filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidadeIniciada.PROCESSO_INICIADO_ID,
				idProcessoIniciado));

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processoSituacao");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processo");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeSituacao");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade.funcionalidade");

		Collection colecaoFuncionalidadeIniciada = fachada.pesquisar(
				filtroFuncionalidadeIniciada, FuncionalidadeIniciada.class
						.getName());

		if (colecaoFuncionalidadeIniciada == null
				|| colecaoFuncionalidadeIniciada.isEmpty()) {
			throw new ActionServletException(
					"atencao.processo_iniciado.funcionalidade_iniciada.inexistente");

		}

		httpServletRequest
				.setAttribute("mesAnoReferencia", Util
						.formatarAnoMesParaMesAno(fachada
								.pesquisarParametrosDoSistema()
								.getAnoMesFaturamento()));

		httpServletRequest.setAttribute("dataCorrente", new Date());

		httpServletRequest.setAttribute("processoIniciado",
				((FuncionalidadeIniciada) Util
						.retonarObjetoDeColecao(colecaoFuncionalidadeIniciada))
						.getProcessoIniciado());

		httpServletRequest.setAttribute("concluido", new Integer(
				ProcessoSituacao.CONCLUIDO));
		httpServletRequest.setAttribute("concluidoComErro", new Integer(
				ProcessoSituacao.CONCLUIDO_COM_ERRO));
		httpServletRequest.setAttribute("execucaoCancelada", new Integer(
				ProcessoSituacao.EXECUCAO_CANCELADA));

		httpServletRequest.setAttribute("colecaoFuncionalidadeIniciada",
				colecaoFuncionalidadeIniciada);

		return retorno;
	}

}
