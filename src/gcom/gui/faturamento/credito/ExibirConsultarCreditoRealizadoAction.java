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
package gcom.gui.faturamento.credito;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoHistorico;
import gcom.faturamento.credito.FiltroCreditoRealizado;
import gcom.faturamento.credito.FiltroCreditoRealizadoHistorico;
import gcom.gui.ActionServletException;
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
 * action responsável pela exibição da página de consultar créditos realizados
 * 
 * @author pedro alexandre
 * @created 04 de Janeiro de 2006
 */
public class ExibirConsultarCreditoRealizadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno e seta o mapeamento para a tela de
		// consultar créditos realizados
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarCreditoRealizado");

		// cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");

		if (tipoConsulta.equalsIgnoreCase("conta")) {

			// recupera o código da conta do request
			String idConta = httpServletRequest.getParameter("contaID");

			// se o código não for nulo
			if (idConta != null && !idConta.equalsIgnoreCase("")) {
				// remove o objeto conta da sessão
				sessao.removeAttribute("conta");

				// remove o objeto conta da sessão
				sessao.removeAttribute("contaHistorico");

				// remove a coleção de categorias
				sessao.removeAttribute("colecaoContaCategoria");

				// remove a coleção de impostos deduzidos
				sessao.removeAttribute("colecaoContaImpostosDeduzidos");

				// remove a coleção de créditos realizados
				sessao.removeAttribute("colecaoContaCreditosRealizados");

				sessao
						.removeAttribute("colecaoContaCreditosRealizadosHistorico");
			}

			/*
			 * Pesquisando a conta a partir do id recebido
			 * =====================================================================
			 */

			// cria o objeto conta
			Conta conta = null;

			// se o código da conta não for nulo
			if (idConta != null && !idConta.equalsIgnoreCase("")) {

				// cria o filtro da conta
				FiltroConta filtroConta = new FiltroConta();

				// carrega o imóvel
				filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel");

				// carrega a situação da conta
				filtroConta
						.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

				// carrega a situação da ligação de água
				filtroConta
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

				// carrega a a situação de esgoto
				filtroConta
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

				// seta o código da conta no filtro
				filtroConta.adicionarParametro(new ParametroSimples(
						FiltroConta.ID, new Integer(idConta)));

				// pesquisa a conta na base de dados
				Collection colecaoConta = fachada.pesquisar(filtroConta,
						Conta.class.getName());

				// se não encontrou nenhuma conta com o código informado
				if (colecaoConta == null || colecaoConta.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.conta.inexistente");
				}

				// recupera o objeto conta da coleção
				conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);

				// seta o objeto conta na sessão
				sessao.setAttribute("conta", conta);
			}
			// se já existe uma conta na sessão
			else if (sessao.getAttribute("conta") != null) {
				// recupera a conta da sessão
				conta = (Conta) sessao.getAttribute("conta");
			} else {
				// levanta o erro de conta inexistente
				throw new ActionServletException(
						"atencao.pesquisa.conta.inexistente");
			}
			// ====================================================================

			// cria a variável de coleção de créditos realizados
			Collection colecaoContaCreditosRealizados;

			/*
			 * Creditos Realizados (Carregar coleção)
			 * ======================================================================
			 */
			// se não existir nenhuma coleção de créditos realizados na sessão
			if (sessao.getAttribute("colecaoContaCreditosRealizados") == null) {

				// cria o filtro de credito realizado
				FiltroCreditoRealizado filtroCreditoRealizado = new FiltroCreditoRealizado();

				// carrega o tipo de crédito
				filtroCreditoRealizado
						.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

				// seta o código da conta no filtro
				filtroCreditoRealizado.adicionarParametro(new ParametroSimples(
						FiltroCreditoRealizado.CONTA_ID, conta.getId()));

				// pesquisa a coleção de créditos realizados
				colecaoContaCreditosRealizados = fachada.pesquisar(
						filtroCreditoRealizado, CreditoRealizado.class
								.getName());

				// seta a coleção de créditos realizados na sessão
				sessao.setAttribute("colecaoContaCreditosRealizados",
						colecaoContaCreditosRealizados);
			}
			// ====================================================================

		} else if (tipoConsulta.equalsIgnoreCase("contaHistorico")) {

			String idContaHistorico = httpServletRequest
					.getParameter("contaID");

			// se o código não for nulo
			if (idContaHistorico != null
					&& !idContaHistorico.equalsIgnoreCase("")) {

				// remove o objeto conta da sessão
				sessao.removeAttribute("conta");

				// remove o objeto conta histórico da sessão
				sessao.removeAttribute("contaHistorico");

				// remove a coleção de categorias
				sessao.removeAttribute("colecaoContaCategoria");

				// remove a coleção de impostos deduzidos
				sessao.removeAttribute("colecaoContaImpostosDeduzidos");

				// remove a coleção de créditos realizados
				sessao.removeAttribute("colecaoContaCreditosRealizados");
			}

			/*
			 * Pesquisando a conta histórico a partir do id recebido
			 * =====================================================================
			 */

			// cria o objeto conta histórico
			ContaHistorico contaHistorico = null;

			// se o código da conta histórico não for nulo
			if (idContaHistorico != null
					&& !idContaHistorico.equalsIgnoreCase("")) {

				// cria o filtro da conta histórico
				FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();

				// carrega o imóvel
				filtroContaHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel");

				// carrega a situação da conta
				filtroContaHistorico
						.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

				// carrega a situação da ligação de água
				filtroContaHistorico
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

				// carrega a situação de esgoto
				filtroContaHistorico
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

				// seta o código da conta histórico no filtro
				filtroContaHistorico.adicionarParametro(new ParametroSimples(
						FiltroContaHistorico.ID, idContaHistorico));

				// pesquisa a conta histórico na base de dados
				Collection colecaoContaHistorico = fachada.pesquisar(
						filtroContaHistorico, ContaHistorico.class.getName());

				// se não encontrou nenhuma conta histórico com o código
				// informado
				if (colecaoContaHistorico == null
						|| colecaoContaHistorico.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.conta.inexistente");
				}

				// recupera o objeto conta histórico da coleção
				contaHistorico = (ContaHistorico) Util
						.retonarObjetoDeColecao(colecaoContaHistorico);

				// seta o objeto conta historico na sessão
				sessao.setAttribute("contaHistorico", contaHistorico);

			}
			// se já existe uma conta na sessão
			else if (sessao.getAttribute("contaHistorico") != null) {
				// recupera a conta da sessão
				contaHistorico = (ContaHistorico) sessao
						.getAttribute("contaHistorico");
			} else {
				// levanta o erro de conta inexistente
				throw new ActionServletException(
						"atencao.pesquisa.conta.inexistente");
			}
			// ====================================================================

			// cria a variável de coleção de créditos realizados histórico
			Collection colecaoContaCreditosRealizadosHistorico;

			/*
			 * Creditos Realizados Histórico (Carregar coleção)
			 * ======================================================================
			 */
			// se não existir nenhuma coleção de créditos realizados histórico
			// na sessão
			if (sessao.getAttribute("colecaoContaCreditosRealizadosHistorico") == null) {

				// cria o filtro de credito realizado histórico
				FiltroCreditoRealizadoHistorico filtroCreditoRealizadoHistorico = new FiltroCreditoRealizadoHistorico();

				// carrega o tipo de crédito
				filtroCreditoRealizadoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

				// seta o código da conta histórico no filtro
				filtroCreditoRealizadoHistorico
						.adicionarParametro(new ParametroSimples(
								FiltroCreditoRealizadoHistorico.CONTA_HISTORICO_ID,
								contaHistorico.getId()));

				// pesquisa a coleção de créditos realizados histórico
				colecaoContaCreditosRealizadosHistorico = fachada.pesquisar(
						filtroCreditoRealizadoHistorico,
						CreditoRealizadoHistorico.class.getName());

				// seta a coleção de créditos realizados histórico na sessão
				sessao.setAttribute("colecaoContaCreditosRealizadosHistorico",
						colecaoContaCreditosRealizadosHistorico);
			}
			// ====================================================================

		}
		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaConsultaCreditoRealizado") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaConsultaCreditoRealizado",
							httpServletRequest
									.getParameter("caminhoRetornoTelaConsultaCreditoRealizado"));
		}

		// retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
