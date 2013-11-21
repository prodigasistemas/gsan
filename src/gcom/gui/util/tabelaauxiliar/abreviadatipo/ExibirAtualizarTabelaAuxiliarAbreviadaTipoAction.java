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
package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;
import gcom.util.tabelaauxiliar.abreviadatipo.FiltroTabelaAuxiliarAbreviadaTipo;
import gcom.util.tabelaauxiliar.abreviadatipo.TabelaAuxiliarAbreviadaTipo;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o
 * tipo SistemaAbastecimento>
 * 
 * @author rodrigo
 */
public class ExibirAtualizarTabelaAuxiliarAbreviadaTipoAction extends
		GcomAction {
	/**
	 * < <Descrição do método>>
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarTabelaAuxiliarAbreviadaTipo");

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		TabelaAuxiliarAbreviadaTipoActionForm tabelaAuxiliarAbreviadaTipoActionForm = (TabelaAuxiliarAbreviadaTipoActionForm) actionForm;

		// String com path do pacote mais o nome do objeto
		String pacoteNomeObjeto = (String) sessao
				.getAttribute("pacoteNomeObjeto");

		String id = null;

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		// Código da tabela auxiliar abreviada tipo a ser atualizada
		if (httpServletRequest.getAttribute("id") != null) {
			id = (String) httpServletRequest.getAttribute("id");
			sessao.setAttribute("id", id);
		} else {
			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				id = (String) httpServletRequest
						.getParameter("idRegistroAtualizacao");
				sessao.setAttribute("id", id);
			} else {
				id = (String) sessao.getAttribute("id");
			}
		}

		// Cria o filtro para atividade
		FiltroTabelaAuxiliarAbreviadaTipo filtroTabelaAuxiliarAbreviadaTipo = new FiltroTabelaAuxiliarAbreviadaTipo();

		if (httpServletRequest.getAttribute("desfazer") == null) {

			// Adiciona o parâmetro no filtro
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliar.ID, id));

			// Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresAbreviadasTipo = fachada
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarAbreviadaTipo,
							pacoteNomeObjeto);

			// Caso a coleção esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresAbreviadasTipo == null
					|| tabelasAuxiliaresAbreviadasTipo.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarAbreviadaTipo = tabelasAuxiliaresAbreviadasTipo
					.iterator();

			// A tabela auxiliar abreviada tipo que será atualizada
			TabelaAuxiliarAbreviadaTipo tabelaAuxiliarAbreviadaTipo = (TabelaAuxiliarAbreviadaTipo) iteratorTabelaAuxiliarAbreviadaTipo
					.next();

			if (tabelaAuxiliarAbreviadaTipo.getSistemaAbastecimento() != null) {
				tabelaAuxiliarAbreviadaTipoActionForm
						.setSistemaAbastecimento(tabelaAuxiliarAbreviadaTipo
								.getSistemaAbastecimento().getId().toString());

			}

			if (tabelaAuxiliarAbreviadaTipo == null) {

				if (id != null && !id.equals("")) {

					FiltroTabelaAuxiliarAbreviadaTipo filtroTabelaAuxiliarAbreviadaTipo2 = new FiltroTabelaAuxiliarAbreviadaTipo();

					filtroTabelaAuxiliarAbreviadaTipo2
							.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
					filtroTabelaAuxiliarAbreviadaTipo2
							.adicionarParametro(new ParametroSimples(
									FiltroDistritoOperacional.ID, id));

					Collection colecaoTabelaAuxiliarAbreviadaTipo = fachada
							.pesquisar(filtroTabelaAuxiliarAbreviadaTipo,
									TabelaAuxiliarAbreviadaTipo.class.getName());

					if (colecaoTabelaAuxiliarAbreviadaTipo != null
							&& !colecaoTabelaAuxiliarAbreviadaTipo.isEmpty()) {
						tabelaAuxiliarAbreviadaTipo = (TabelaAuxiliarAbreviadaTipo) Util
								.retonarObjetoDeColecao(colecaoTabelaAuxiliarAbreviadaTipo);
					}
				}
			}

			// Manda a tabela auxiliar abreviada tipo na sessão
			sessao.setAttribute("tabelaAuxiliarAbreviadaTipo",
					tabelaAuxiliarAbreviadaTipo);

			String tela = (String) sessao.getAttribute("tela");

			if (tela.equalsIgnoreCase("banco")) {

				if (tabelaAuxiliarAbreviadaTipo.getIndicadorUso().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO
						.intValue()) {

					sessao.setAttribute("indicadorUso", "sim");
				} else {
					sessao.setAttribute("indicadorUso", "nao");
				}

			}

			DadosTelaTabelaAuxiliarAbreviadaTipo dados = DadosTelaTabelaAuxiliarAbreviadaTipo
					.obterDadosTelaTabelaAuxiliar(tela);

			sessao.setAttribute(
					"funcionalidadeTabelaAuxiliarAbreviadaTipoFiltrar", dados
							.getFuncionalidadeTabelaAuxFiltrar());

			if (httpServletRequest.getAttribute("desfazer") != null) {

				// Cria o filtro para atividade
				filtroTabelaAuxiliarAbreviadaTipo.limparListaParametros();

				// Adiciona o parâmetro no filtro
				filtroTabelaAuxiliarAbreviadaTipo
						.adicionarParametro(new ParametroSimples(
								FiltroTabelaAuxiliar.ID, id));

				// Pesquisa a tabela auxiliar no sistema
				Collection tabelasAuxiliaresAbreviadasTipoBase = fachada
						.pesquisarTabelaAuxiliar(
								filtroTabelaAuxiliarAbreviadaTipo,
								pacoteNomeObjeto);

				// Caso a coleção esteja vazia, indica erro inesperado
				if (tabelasAuxiliaresAbreviadasTipoBase == null
						|| tabelasAuxiliaresAbreviadasTipoBase.isEmpty()) {
					throw new ActionServletException("erro.sistema");
				}

				Iterator iteratorTabelaAuxiliarAbreviadaTipoBase = tabelasAuxiliaresAbreviadasTipoBase
						.iterator();

				// A tabela auxiliar abreviada tipo que será atualizada
				TabelaAuxiliarAbreviadaTipo tabelaAuxiliarAbreviadaTipoBase = (TabelaAuxiliarAbreviadaTipo) iteratorTabelaAuxiliarAbreviadaTipoBase
						.next();

				// Manda a tabela auxiliar abreviada tipo na sessão
				sessao.setAttribute("tabelaAuxiliarAbreviadaTipo",
						tabelaAuxiliarAbreviadaTipoBase);
			}
		}

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
