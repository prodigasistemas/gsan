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
