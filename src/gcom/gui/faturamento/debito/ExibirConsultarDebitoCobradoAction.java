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
package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoHistorico;
import gcom.faturamento.debito.FiltroDebitoCobrado;
import gcom.faturamento.debito.FiltroDebitoCobradoHistorico;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.cadastro.imovel.RelatorioDebitoCobradoContaHelper;
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
 * action responsável pela exibição da tela de consultar débito cobrado
 * 
 * @author pedro alexandre
 * @created 04 de Janeiro de 2006
 */
public class ExibirConsultarDebitoCobradoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno e seta o mapeamento para a tela de
		// consultar débito cobrado
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarDebitoCobrado");

		// cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");
		
		RelatorioDebitoCobradoContaHelper relatorioDebitosDeUmaConta = new RelatorioDebitoCobradoContaHelper();

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

				// remove a coleção de débitos cobrados
				sessao.removeAttribute("colecaoContaDebitosCobrados");
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
				/*
				 * FiltroConta filtroConta = new FiltroConta();
				 * 
				 * //carrega o imóvel
				 * filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel");
				 * 
				 * //carrega a situação da conta
				 * filtroConta.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
				 * 
				 * //carrega a situação da ligação de água
				 * filtroConta.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
				 * 
				 * //carrega a a situação de esgoto
				 * filtroConta.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
				 * 
				 * //seta o código da conta no filtro
				 * filtroConta.adicionarParametro(new
				 * ParametroSimples(FiltroConta.ID, idConta));
				 * 
				 * //pesquisa a conta na base de dados Collection colecaoConta =
				 * fachada.pesquisar(filtroConta, Conta.class.getName());
				 */

				Collection colecaoConta = fachada.consultarConta(new Integer(
						idConta));
				// se não encontrou nenhuma conta com o código informado
				if (colecaoConta == null || colecaoConta.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.conta.inexistente");
				}
				// recupera o objeto conta da coleção
				conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);
				
				relatorioDebitosDeUmaConta.setIdImovel(conta.getImovel().getId()+"");
				relatorioDebitosDeUmaConta.setMesAnoConta(conta.getFormatarAnoMesParaMesAno());
				relatorioDebitosDeUmaConta.setSituacaoConta(conta.getDebitoCreditoSituacaoAtual().getDescricaoDebitoCreditoSituacao());
				relatorioDebitosDeUmaConta.setSituacaoLigacaoAgua(conta.getLigacaoAguaSituacao().getDescricao());
				relatorioDebitosDeUmaConta.setSituacaoLigacaoEsgoto(conta.getLigacaoEsgotoSituacao().getDescricao());
								// seta o objeto conta na sessão
				sessao.setAttribute("conta", conta);
			}
			// se já existe uma conta na sessão
			else if (sessao.getAttribute("conta") != null) {
				// recupera a conta da sessão
				conta = (Conta) sessao.getAttribute("conta");
				
				if(relatorioDebitosDeUmaConta.getIdImovel() == null){
					relatorioDebitosDeUmaConta.setIdImovel(conta.getImovel().getId()+"");
				}
			} else {
				// levanta o erro de conta inexistente
				throw new ActionServletException(
						"atencao.pesquisa.conta.inexistente");
			}
			// ====================================================================

			// cria a coleção de débitos cobrados
			Collection colecaoContaDebitosCobrados;

			/*
			 * Débitos Cobrados (Carregar coleção)
			 * ======================================================================
			 */

			// cria o filtro de débito cobrado
			FiltroDebitoCobrado filtroDebitoCobrado = new FiltroDebitoCobrado();

			// carrega o tipo de débito
			filtroDebitoCobrado
					.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

			// seta o código da conta no filtro
			filtroDebitoCobrado.adicionarParametro(new ParametroSimples(
					FiltroDebitoCobrado.CONTA_ID, conta.getId()));

			// pesquisa a coleção de débitos cobrados
			colecaoContaDebitosCobrados = fachada.pesquisar(
					filtroDebitoCobrado, DebitoCobrado.class.getName());
			
			

			// seta a coleção de débitos cobrados na sessão
			sessao.setAttribute("colecaoContaDebitosCobrados",
					colecaoContaDebitosCobrados);
			relatorioDebitosDeUmaConta.setColecaoDebitosCobrados(colecaoContaDebitosCobrados);
			sessao.removeAttribute("contaHistorico");
			sessao.removeAttribute("colecaoContaDebitosCobradosHistorico");

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

				// remove a coleção de débitos cobrados
				sessao.removeAttribute("colecaoContaDebitosCobrados");
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
				relatorioDebitosDeUmaConta.setIdImovel(contaHistorico.getImovel().getId()+"");
				relatorioDebitosDeUmaConta.setMesAnoConta(contaHistorico.getFormatarAnoMesParaMesAno());
				relatorioDebitosDeUmaConta.setSituacaoConta(contaHistorico.getDebitoCreditoSituacaoAtual().getDescricaoDebitoCreditoSituacao()+"");
				relatorioDebitosDeUmaConta.setSituacaoLigacaoAgua(contaHistorico.getLigacaoAguaSituacao().getDescricao());
				relatorioDebitosDeUmaConta.setSituacaoLigacaoEsgoto(contaHistorico.getLigacaoEsgotoSituacao().getDescricao());				
				sessao.removeAttribute("conta");
				sessao.removeAttribute("colecaoContaDebitosCobrados");

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

			// cria a coleção de débitos cobrados histórico
			Collection colecaoContaDebitosCobradosHistorico;

			/*
			 * Débitos Cobrados (Carregar coleção)
			 * ======================================================================
			 */

			// cria o filtro de débito cobrado
			FiltroDebitoCobradoHistorico filtroDebitoCobradoHistorico = new FiltroDebitoCobradoHistorico();

			// carrega o tipo de débito
			filtroDebitoCobradoHistorico
					.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

			// seta o código da conta histórico no filtro
			filtroDebitoCobradoHistorico
					.adicionarParametro(new ParametroSimples(
							FiltroDebitoCobradoHistorico.CONTA_HISTORICO_ID,
							contaHistorico.getId()));

			// pesquisa a coleção de débitos cobrados
			colecaoContaDebitosCobradosHistorico = fachada.pesquisar(
					filtroDebitoCobradoHistorico, DebitoCobradoHistorico.class
							.getName());

			// seta a coleção de débitos cobrados histórico na sessão
			sessao.setAttribute("colecaoContaDebitosCobradosHistorico",
					colecaoContaDebitosCobradosHistorico);
			
			relatorioDebitosDeUmaConta.setColecaoDebitosCobradosHistorico(colecaoContaDebitosCobradosHistorico);

		}
		
		sessao.setAttribute("relatorioHelper",relatorioDebitosDeUmaConta);
		
		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest.getParameter("caminhoRetornoTelaConsultaDebitos") != null) {
			sessao.setAttribute("caminhoRetornoTelaConsultaDebitos",
					httpServletRequest
							.getParameter("caminhoRetornoTelaConsultaDebitos"));
		}
		// retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
