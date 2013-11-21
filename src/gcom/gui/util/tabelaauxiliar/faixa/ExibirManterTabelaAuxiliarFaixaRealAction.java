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
package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaReal;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaReal;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 *
 */
public class ExibirManterTabelaAuxiliarFaixaRealAction extends GcomAction {
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
				.findForward("manterTabelaAuxiliarFaixaReal");

		// Obtém a instancia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Cria a coleção de tabelas auxiliares
		Collection tabelasAuxiliaresFaixaReais = null;

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém o nome da tela passado no get do request
		String tela = (String) httpServletRequest.getParameter("tela");

		// Declaração de objetos e tipos primitivos
		String titulo = null;
		TabelaAuxiliarFaixaReal tabelaAuxiliarFaixaReal = null;
		String pacoteNomeObjeto = (String) sessao
				.getAttribute("pacoteNomeObjeto");
		String funcionalidadeTabelaAuxiliarFaixaRealManter = null;
		int tamMaxCampoDescricao = 40;
		int tamMaxCampoDescricaoAbreviada = 3;
		if (sessao.getAttribute("tabela") != null) {
			tabelaAuxiliarFaixaReal = (TabelaAuxiliarFaixaReal) sessao
					.getAttribute("tabela");
		}
		// sessao.getAttribute("dados", dados);
		titulo = (String) sessao.getAttribute("titulo");

		// Verifica se o exibir manter foi chamado da tela de filtro
		if (httpServletRequest.getAttribute("tela") != null) {
			tela = (String) sessao.getAttribute("tela");
		}

		// Parte da verificação do filtro
		FiltroTabelaAuxiliarFaixaReal filtroTabelaAuxiliarFaixaReal = null;

		// Verifica se o filtro foi informado pela página de filtragem da tabela
		// auxiliar abreviada
		if (httpServletRequest.getAttribute("filtroTabelaAuxiliarFaixaReal") != null) {
			filtroTabelaAuxiliarFaixaReal = (FiltroTabelaAuxiliarFaixaReal) httpServletRequest
					.getAttribute("filtroTabelaAuxiliarFaixaReal");
			sessao.setAttribute("filtroTabelaAuxiliarFaixaReal",
					filtroTabelaAuxiliarFaixaReal);

		} else {
			// Caso o exibirManterTabelaAuxiliar não tenha passado por algum
			// esquema de filtro,
			// a quantidade de registros é verificada para avaliar a necessidade
			// de filtragem
			if (sessao.getAttribute("filtroTabelaAuxiliarFaixaReal") != null) {
				filtroTabelaAuxiliarFaixaReal = (FiltroTabelaAuxiliarFaixaReal) sessao
						.getAttribute("filtroTabelaAuxiliarFaixaReal");
			} else {
				filtroTabelaAuxiliarFaixaReal = new FiltroTabelaAuxiliarFaixaReal();
			}
			if (fachada.registroMaximo(tabelaAuxiliarFaixaReal.getClass()) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
				// Se o limite de registros foi atingido, a página de filtragem
				// é chamada
				retorno = actionMapping
						.findForward("filtroTabelaAuxiliarFaixaReal");
				sessao.setAttribute("tela", tela);
			}
		}

		// A pesquisa de tabelas auxiliares só será feita se o forward estiver
		// direcionado
		// para a página de manterTabelaAuxiliar
		if (retorno.getName().equalsIgnoreCase("manterTabelaAuxiliarFaixaReal")) {
			// Seta a ordenação desejada do filtro
			filtroTabelaAuxiliarFaixaReal
					.setCampoOrderBy(FiltroTabelaAuxiliarFaixaReal.VOLUME_MENOR_FAIXA);
			// Pesquisa de tabelas auxiliares

			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTabelaAuxiliarFaixaReal, pacoteNomeObjeto);
			tabelasAuxiliaresFaixaReais = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			if (tabelasAuxiliaresFaixaReais != null
					&& !tabelasAuxiliaresFaixaReais.isEmpty()) {

				// Verifica se a coleção contém apenas um objeto, se está
				// retornando
				// da paginação (devido ao esquema de paginação de 10 em 10 faz
				// uma
				// nova busca), evitando, assim, que caso haja 11 elementos no
				// retorno da pesquisa e o usuário selecione o link para ir para
				// a
				// segunda página ele não vá para tela de atualizar.

				if (tabelasAuxiliaresFaixaReais.size() == 1
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
								.findForward("atualizarTabelaAuxiliarFaixaReal");
						TabelaAuxiliarFaixaReal tabelaAuxiliarFaixaRealAux = (TabelaAuxiliarFaixaReal) tabelasAuxiliaresFaixaReais
								.iterator().next();
						httpServletRequest.setAttribute("id",
								tabelaAuxiliarFaixaRealAux.getId().toString());

					} else {
						sessao.setAttribute("tabelasAuxiliaresFaixaReais",
								tabelasAuxiliaresFaixaReais);
					}
				} else {
					sessao.setAttribute("tabelasAuxiliaresFaixaReais",
							tabelasAuxiliaresFaixaReais);
				}
			} else {
				// Nenhuma funcionalidade cadastrada
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
		}

		// Envia os objetos na sessão
		sessao.setAttribute("titulo", titulo);
		sessao.setAttribute("funcionalidadeTabelaAuxiliarAbreviadaManter",
				funcionalidadeTabelaAuxiliarFaixaRealManter);
		sessao.setAttribute("tamMaxCampoDescricao", new Integer(
				tamMaxCampoDescricao));
		sessao.setAttribute("tamMaxCampoDescricaoAbreviada", new Integer(
				tamMaxCampoDescricaoAbreviada));
		
		
		//seta o parametro tela a parte de acesso a funcionalidade ou operação
		httpServletRequest.setAttribute("tela",tela);
		//sessao.setAttribute("descricao", descricao);
		//sessao.setAttribute("descricaoAbreviada", descricaoAbreviada);

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
