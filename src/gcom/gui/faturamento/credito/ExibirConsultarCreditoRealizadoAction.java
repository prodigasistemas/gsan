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
 * action respons�vel pela exibi��o da p�gina de consultar cr�ditos realizados
 * 
 * @author pedro alexandre
 * @created 04 de Janeiro de 2006
 */
public class ExibirConsultarCreditoRealizadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno e seta o mapeamento para a tela de
		// consultar cr�ditos realizados
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarCreditoRealizado");

		// cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");

		if (tipoConsulta.equalsIgnoreCase("conta")) {

			// recupera o c�digo da conta do request
			String idConta = httpServletRequest.getParameter("contaID");

			// se o c�digo n�o for nulo
			if (idConta != null && !idConta.equalsIgnoreCase("")) {
				// remove o objeto conta da sess�o
				sessao.removeAttribute("conta");

				// remove o objeto conta da sess�o
				sessao.removeAttribute("contaHistorico");

				// remove a cole��o de categorias
				sessao.removeAttribute("colecaoContaCategoria");

				// remove a cole��o de impostos deduzidos
				sessao.removeAttribute("colecaoContaImpostosDeduzidos");

				// remove a cole��o de cr�ditos realizados
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

			// se o c�digo da conta n�o for nulo
			if (idConta != null && !idConta.equalsIgnoreCase("")) {

				// cria o filtro da conta
				FiltroConta filtroConta = new FiltroConta();

				// carrega o im�vel
				filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel");

				// carrega a situa��o da conta
				filtroConta
						.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

				// carrega a situa��o da liga��o de �gua
				filtroConta
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

				// carrega a a situa��o de esgoto
				filtroConta
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

				// seta o c�digo da conta no filtro
				filtroConta.adicionarParametro(new ParametroSimples(
						FiltroConta.ID, new Integer(idConta)));

				// pesquisa a conta na base de dados
				Collection colecaoConta = fachada.pesquisar(filtroConta,
						Conta.class.getName());

				// se n�o encontrou nenhuma conta com o c�digo informado
				if (colecaoConta == null || colecaoConta.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.conta.inexistente");
				}

				// recupera o objeto conta da cole��o
				conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);

				// seta o objeto conta na sess�o
				sessao.setAttribute("conta", conta);
			}
			// se j� existe uma conta na sess�o
			else if (sessao.getAttribute("conta") != null) {
				// recupera a conta da sess�o
				conta = (Conta) sessao.getAttribute("conta");
			} else {
				// levanta o erro de conta inexistente
				throw new ActionServletException(
						"atencao.pesquisa.conta.inexistente");
			}
			// ====================================================================

			// cria a vari�vel de cole��o de cr�ditos realizados
			Collection colecaoContaCreditosRealizados;

			/*
			 * Creditos Realizados (Carregar cole��o)
			 * ======================================================================
			 */
			// se n�o existir nenhuma cole��o de cr�ditos realizados na sess�o
			if (sessao.getAttribute("colecaoContaCreditosRealizados") == null) {

				// cria o filtro de credito realizado
				FiltroCreditoRealizado filtroCreditoRealizado = new FiltroCreditoRealizado();

				// carrega o tipo de cr�dito
				filtroCreditoRealizado
						.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

				// seta o c�digo da conta no filtro
				filtroCreditoRealizado.adicionarParametro(new ParametroSimples(
						FiltroCreditoRealizado.CONTA_ID, conta.getId()));

				// pesquisa a cole��o de cr�ditos realizados
				colecaoContaCreditosRealizados = fachada.pesquisar(
						filtroCreditoRealizado, CreditoRealizado.class
								.getName());

				// seta a cole��o de cr�ditos realizados na sess�o
				sessao.setAttribute("colecaoContaCreditosRealizados",
						colecaoContaCreditosRealizados);
			}
			// ====================================================================

		} else if (tipoConsulta.equalsIgnoreCase("contaHistorico")) {

			String idContaHistorico = httpServletRequest
					.getParameter("contaID");

			// se o c�digo n�o for nulo
			if (idContaHistorico != null
					&& !idContaHistorico.equalsIgnoreCase("")) {

				// remove o objeto conta da sess�o
				sessao.removeAttribute("conta");

				// remove o objeto conta hist�rico da sess�o
				sessao.removeAttribute("contaHistorico");

				// remove a cole��o de categorias
				sessao.removeAttribute("colecaoContaCategoria");

				// remove a cole��o de impostos deduzidos
				sessao.removeAttribute("colecaoContaImpostosDeduzidos");

				// remove a cole��o de cr�ditos realizados
				sessao.removeAttribute("colecaoContaCreditosRealizados");
				
				sessao.removeAttribute("colecaoContaCreditosRealizadosHistorico");
			}

			/*
			 * Pesquisando a conta hist�rico a partir do id recebido
			 * =====================================================================
			 */

			// cria o objeto conta hist�rico
			ContaHistorico contaHistorico = null;

			// se o c�digo da conta hist�rico n�o for nulo
			if (idContaHistorico != null
					&& !idContaHistorico.equalsIgnoreCase("")) {

				// cria o filtro da conta hist�rico
				FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();

				// carrega o im�vel
				filtroContaHistorico
						.adicionarCaminhoParaCarregamentoEntidade("imovel");

				// carrega a situa��o da conta
				filtroContaHistorico
						.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

				// carrega a situa��o da liga��o de �gua
				filtroContaHistorico
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

				// carrega a situa��o de esgoto
				filtroContaHistorico
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

				// seta o c�digo da conta hist�rico no filtro
				filtroContaHistorico.adicionarParametro(new ParametroSimples(
						FiltroContaHistorico.ID, idContaHistorico));

				// pesquisa a conta hist�rico na base de dados
				Collection colecaoContaHistorico = fachada.pesquisar(
						filtroContaHistorico, ContaHistorico.class.getName());

				// se n�o encontrou nenhuma conta hist�rico com o c�digo
				// informado
				if (colecaoContaHistorico == null
						|| colecaoContaHistorico.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.conta.inexistente");
				}

				// recupera o objeto conta hist�rico da cole��o
				contaHistorico = (ContaHistorico) Util
						.retonarObjetoDeColecao(colecaoContaHistorico);

				// seta o objeto conta historico na sess�o
				sessao.setAttribute("contaHistorico", contaHistorico);

			}
			// se j� existe uma conta na sess�o
			else if (sessao.getAttribute("contaHistorico") != null) {
				// recupera a conta da sess�o
				contaHistorico = (ContaHistorico) sessao
						.getAttribute("contaHistorico");
			} else {
				// levanta o erro de conta inexistente
				throw new ActionServletException(
						"atencao.pesquisa.conta.inexistente");
			}
			// ====================================================================

			// cria a vari�vel de cole��o de cr�ditos realizados hist�rico
			Collection colecaoContaCreditosRealizadosHistorico;

			/*
			 * Creditos Realizados Hist�rico (Carregar cole��o)
			 * ======================================================================
			 */
			// se n�o existir nenhuma cole��o de cr�ditos realizados hist�rico
			// na sess�o
			if (sessao.getAttribute("colecaoContaCreditosRealizadosHistorico") == null) {

				// cria o filtro de credito realizado hist�rico
				FiltroCreditoRealizadoHistorico filtroCreditoRealizadoHistorico = new FiltroCreditoRealizadoHistorico();

				// carrega o tipo de cr�dito
				filtroCreditoRealizadoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

				// seta o c�digo da conta hist�rico no filtro
				filtroCreditoRealizadoHistorico
						.adicionarParametro(new ParametroSimples(
								FiltroCreditoRealizadoHistorico.CONTA_HISTORICO_ID,
								contaHistorico.getId()));

				// pesquisa a cole��o de cr�ditos realizados hist�rico
				colecaoContaCreditosRealizadosHistorico = fachada.pesquisar(
						filtroCreditoRealizadoHistorico,
						CreditoRealizadoHistorico.class.getName());

				// seta a cole��o de cr�ditos realizados hist�rico na sess�o
				sessao.setAttribute("colecaoContaCreditosRealizadosHistorico",
						colecaoContaCreditosRealizadosHistorico);
			}
			// ====================================================================

		}
		// envia uma flag que ser� verificado no cliente_resultado_pesquisa.jsp
		// para saber se ir� usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaConsultaCreditoRealizado") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaConsultaCreditoRealizado",
							httpServletRequest
									.getParameter("caminhoRetornoTelaConsultaCreditoRealizado"));
		}

		// retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}
