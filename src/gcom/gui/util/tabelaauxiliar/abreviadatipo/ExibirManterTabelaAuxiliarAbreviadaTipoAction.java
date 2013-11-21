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
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviadatipo.FiltroTabelaAuxiliarAbreviadaTipo;
import gcom.util.tabelaauxiliar.abreviadatipo.TabelaAuxiliarAbreviadaTipo;

import java.util.ArrayList;
import java.util.Collection;

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
 * @author Administrador
 * @created 11 de Fevereiro de 2005
 */
public class ExibirManterTabelaAuxiliarAbreviadaTipoAction extends GcomAction {
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
				.findForward("manterTabelaAuxiliarAbreviadaTipo");

		// Obtém a instancia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Cria a coleção de tabelas auxiliares
		Collection tabelasAuxiliaresAbreviadasTipo = new ArrayList();

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém o nome da tela passado no get do request
		String tela = httpServletRequest.getParameter("tela");

		// Declaração de objetos e tipos primitivos
		String titulo = null;
//		TabelaAuxiliarAbreviadaTipo tabelaAuxiliarAbreviadaTipo = null;
		int tamMaxCampoDescricao = 20;
		int tamMaxCampoDescricaoAbreviada = 6;
		String pacoteNomeObjeto = (String) sessao
				.getAttribute("pacoteNomeObjeto");

		String descricao = "descricao";
		String tituloTipo = null;

		if (sessao.getAttribute("titulo") != null) {
			titulo = (String) sessao.getAttribute("titulo");
		}

		// Verifica se o exibir manter foi chamado da tela de filtro
		if (tela != null) {
			tela = (String) sessao.getAttribute("tela");
		}

		// Parte da verificação do filtro
		FiltroTabelaAuxiliarAbreviadaTipo filtroTabelaAuxiliarAbreviadaTipo = null;

		DadosTelaTabelaAuxiliarAbreviadaTipo dados = DadosTelaTabelaAuxiliarAbreviadaTipo
				.obterDadosTelaTabelaAuxiliar(tela);

		if (dados.getNomeParametroFuncionalidade().equals("setorAbastecimento")) {

			Collection colecaoObject = new ArrayList();

			FiltroSistemaAbastecimento filtro = new FiltroSistemaAbastecimento();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroSistemaAbastecimento.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoObject = this.getFachada().pesquisar(filtro,
					SistemaAbastecimento.class.getName());

			sessao.setAttribute("colecaoObject", colecaoObject);
		}

		if (dados.getNomeParametroFuncionalidade().equals("zonaAbastecimento")) {

			Collection colecaoObject = new ArrayList();

			FiltroSistemaAbastecimento filtro = new FiltroSistemaAbastecimento();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroSistemaAbastecimento.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoObject = this.getFachada().pesquisar(filtro,
					SistemaAbastecimento.class.getName());

			sessao.setAttribute("colecaoObject", colecaoObject);
		}

		// Verifica se o filtro foi informado pela página de filtragem da tabela
		// auxiliar
		if (httpServletRequest
				.getAttribute("filtroTabelaAuxiliarAbreviadaTipo") != null) {
			filtroTabelaAuxiliarAbreviadaTipo = (FiltroTabelaAuxiliarAbreviadaTipo) httpServletRequest
					.getAttribute("filtroTabelaAuxiliarAbreviadaTipo");
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
		} else {
			// Caso o exibirManterTabelaAuxiliarAbreviadaTipo não tenha passado
			// por algum
			// esquema de filtro, a quantidade de registros é verificada para
			// avaliar a necessidade
			// de filtragem
			filtroTabelaAuxiliarAbreviadaTipo = new FiltroTabelaAuxiliarAbreviadaTipo();

			if (fachada.registroMaximo(TabelaAuxiliarAbreviadaTipo.class) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
				// Se o limite de registros foi atingido, a página de filtragem
				// é chamada
				retorno = actionMapping
						.findForward("filtrarTabelaAuxiliarAbreviadaTipo");
				sessao.setAttribute("tela", tela);
			}
		}

		// A pesquisa de tabelas auxiliares só será feita se o forward estiver
		// direcionado para a página de manterTabelaAuxiliarAbreviadaTipo
		if (retorno.getName().equalsIgnoreCase(
				"manterTabelaAuxiliarAbreviadaTipo")) {

			// Seta a ordenação desejada do filtro
			filtroTabelaAuxiliarAbreviadaTipo
					.setCampoOrderBy(FiltroTabelaAuxiliarAbreviadaTipo.ID);

			// Pesquisa de tabelas auxiliares
			tabelasAuxiliaresAbreviadasTipo = fachada.pesquisarTabelaAuxiliar(
					filtroTabelaAuxiliarAbreviadaTipo, pacoteNomeObjeto);

			if (tabelasAuxiliaresAbreviadasTipo == null
					|| tabelasAuxiliaresAbreviadasTipo.isEmpty()) {
				// Nenhum atividade cadastrado
				throw new ActionServletException("atencao.naocadastrado", null,
						titulo);
			}

	
			if (tabelasAuxiliaresAbreviadasTipo != null
					&& !tabelasAuxiliaresAbreviadasTipo.isEmpty()) {

				// Verifica se a coleção contém apenas um objeto, se está
				// retornando
				// da paginação (devido ao esquema de paginação de 10 em 10 faz
				// uma
				// nova busca), evitando, assim, que caso haja 11 elementos no
				// retorno da pesquisa e o usuário selecione o link para ir para
				// a
				// segunda página ele não vá para tela de atualizar.

				if (tabelasAuxiliaresAbreviadasTipo.size() == 1
						&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
								.getParameter("page.offset").equals("1"))) {

					// Verifica se o usuário marcou o checkbox de atualizar no
					// jsp
					// funcionalidade_filtrar. Caso todas as condições sejam
					// verdadeiras seta o retorno para o
					// ExibirAtualizarFuncionalidadeAction e em caso negativo
					// manda a coleção pelo request.

					if (httpServletRequest.getParameter("atualizar") != null) {
						retorno = actionMapping
								.findForward("atualizarTabelaAuxiliarAbreviadaTipo");
						TabelaAuxiliarAbreviadaTipo tabelaAuxiliarabreviadaTipo = (TabelaAuxiliarAbreviadaTipo) tabelasAuxiliaresAbreviadasTipo
								.iterator().next();
						httpServletRequest.setAttribute("id",
								tabelaAuxiliarabreviadaTipo.getId().toString());

					} else {
						sessao.setAttribute("tabelasAuxiliaresAbreviadasTipo",
								tabelasAuxiliaresAbreviadasTipo);
					}
				} else {
					sessao.setAttribute("tabelasAuxiliaresAbreviadasTipo",
							tabelasAuxiliaresAbreviadasTipo);
				}
			} else {
				// Nenhuma funcionalidade cadastrada
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
		}	
			

		// Envia o path do pacote na sessão
		sessao.setAttribute("pacoteNomeObjeto", pacoteNomeObjeto);
		
		// Envia os objetos na sessão
		sessao.setAttribute("titulo", titulo);
		sessao.setAttribute("totalRegistros", tabelasAuxiliaresAbreviadasTipo
				.size());
		sessao.setAttribute("descricao", descricao);
		sessao.setAttribute("tamMaxCampoDescricao", new Integer(
				tamMaxCampoDescricao));
		sessao.setAttribute("tamMaxCampoDescricaoAbreviada", new Integer(
				tamMaxCampoDescricaoAbreviada));
		sessao.setAttribute("tituloTipo", tituloTipo);

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
