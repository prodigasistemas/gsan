package gcom.gui.faturamento.credito;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.parcelamento.FiltroParcelamentoItem;
import gcom.cobranca.parcelamento.ParcelamentoItem;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarHistorico;
import gcom.faturamento.credito.FiltroCreditoARealizar;
import gcom.faturamento.credito.FiltroCreditoARealizarHistorico;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o da tela de consultar cr�dito a realizar
 * 
 * @author Fernanda Paiva
 * @created 17 de Janeiro de 2006
 */
public class ExibirConsultarCreditoARealizarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno e seta o mapeamento para a tela de
		// consultar Cr�dito realizado
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarCreditoARealizar");

		// cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o c�digo da conta do request
		String idImovel = httpServletRequest.getParameter("imovelID");
		String idCredito = httpServletRequest.getParameter("creditoID");
		String idCreditoHistorico = httpServletRequest.getParameter("creditoHistoricoID");
		String idParcelamento = httpServletRequest.getParameter("parcelamentoID");

		// se o c�digo n�o for nulo
		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {
			// remove o objeto conta da sess�o
			sessao.removeAttribute("imovelConsultar");
			// remove a cole��o de cr�ditos a realizar
			sessao.removeAttribute("colecaoCreditoARealizar");
			sessao.removeAttribute("colecaoCreditoARealizarHistorico");
		}

		/*
		 * Pesquisando o cr�dito a partir do id imovel
		 * =====================================================================
		 */

		// cria o objeto imovel
		Imovel imovelConsultar = null;

		// se o c�digo da conta n�o for nulo
		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {

			// cria o filtro do imovel
			FiltroImovel filtroImovel = new FiltroImovel();

			// seta o c�digo do imovel no filtro
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			// pesquisa o imovel na base de dados
			Collection colecaoImovel = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());

			// se n�o encontrou nenhum imovel com o c�digo informado
			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				throw new ActionServletException("atencao.imovel.inexistente");
			}

			String enderecoFormatado = "";
			try {
				enderecoFormatado = fachada
						.pesquisarEnderecoFormatado(new Integer(idImovel));
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (ControladorException e) {
				
				e.printStackTrace();
			}

			httpServletRequest.setAttribute("enderecoFormatado",
					enderecoFormatado);

			// recupera o objeto imovel da cole��o
			imovelConsultar = (Imovel) Util
					.retonarObjetoDeColecao(colecaoImovel);

			// seta o objeto conta na sess�o
			sessao.setAttribute("imovelConsultar", imovelConsultar);
		}
		// se j� existe uma conta na sess�o
		else if (sessao.getAttribute("imovelConsultar") != null) {
			// recupera a conta da sess�o
			imovelConsultar = (Imovel) sessao.getAttribute("imovelConsultar");
		} else {
			// levanta o erro de conta inexistente
			throw new ActionServletException("atencao.imovel.inexistente");
		}
		// ====================================================================

		/*
		 * Cr�ditos A Realizar (Carregar cole��o)
		 * ======================================================================
		 */
		// se n�o existir a cole��o na sess�o
		if (idParcelamento != null && !idParcelamento.equals("")) {
			FiltroParcelamentoItem filtroParcelamentoItem = new FiltroParcelamentoItem();

			// seta o c�digo do imovel no filtro
			filtroParcelamentoItem.adicionarParametro(new ParametroSimples(
					FiltroParcelamentoItem.PARCELAMENTO, idParcelamento));

			filtroParcelamentoItem.adicionarParametro(new ParametroNaoNulo(
					FiltroParcelamentoItem.CREDITO_A_REALIZAR));

			// carrega o debitoACobrar
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.creditoTipo");
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.debitoCreditoSituacaoAtual");
			/*
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.geracaoCredito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.anoMesReferenciaCredito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.anoMesCobrancaCredito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.numeroPrestacaoRealizada");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.numeroPrestacaoCredito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.valorCredito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.valorTotal");
			 */
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar");
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.registroAtendimento");
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.ordemServico");
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.creditoOrigem");
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.usuario");

			// carrega o parcelamento
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
			
			// carrega o im�vel origem do cr�dito a realizar
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.origem.creditoARealizar.imovel");

			Collection colecaoParcelamentoItem = fachada.pesquisar(
					filtroParcelamentoItem, ParcelamentoItem.class.getName());

			if (colecaoParcelamentoItem == null
					|| colecaoParcelamentoItem.isEmpty()) {
				throw new ActionServletException(
						"atencao.faturamento.credito_a_realizar.inexistente");
			} else {
				// seta a cole��o de d�bitos cobrados na sess�o
				sessao.setAttribute("colecaoParcelamentoItem",
						colecaoParcelamentoItem);
			}
		} else if(idCreditoHistorico != null && !idCreditoHistorico.equals("")){
			// CREDITO A COBRAR HISTORICO
			
			//cria o filtro de cr�ditos a realizar historico
			FiltroCreditoARealizarHistorico filtroCreditoARealizarHistorico = new FiltroCreditoARealizarHistorico();

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("creditoOrigem");
			
			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
			// carrega o im�vel origem do cr�dito a realizar historico
			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("origem.creditoARealizarHistorico.imovel");

			if (idImovel != null) {
				filtroCreditoARealizarHistorico.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizarHistorico.IMOVEL_ID, idImovel));
			}

			// seta o c�digo do credito no filtro
			filtroCreditoARealizarHistorico.adicionarParametro(new ParametroSimples(
					FiltroCreditoARealizarHistorico.ID, idCreditoHistorico));

			// pesquisa a cole��o de cr�ditos a realizar historico
			Collection<CreditoARealizar> colecaoCreditoARealizarHistorico = fachada
					.pesquisar(filtroCreditoARealizarHistorico, CreditoARealizarHistorico.class.getName());

			if (colecaoCreditoARealizarHistorico == null || colecaoCreditoARealizarHistorico.isEmpty()) {
				throw new ActionServletException("atencao.faturamento.credito_a_realizar.inexistente");
			} else {
				// seta a cole��o de cr�ditos a realizar historico na sess�o
				sessao.setAttribute("colecaoCreditoARealizarHistorico", colecaoCreditoARealizarHistorico);
			}
			
			
		} else {
			// cria o filtro de cr�ditos a realizar
			FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();

			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");

			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("creditoOrigem");
			
			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
			// carrega o im�vel origem do cr�dito a realizar
			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("origem.creditoARealizar.imovel");

			if (idImovel != null) {
				filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizar.IMOVEL_ID, idImovel));
			}

			if (idCredito != null) {
				// seta o c�digo do credito no filtro
				filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizar.ID, idCredito));
			}

			// pesquisa a cole��o de cr�ditos a realizar
			Collection<CreditoARealizar> colecaoCreditoARealizar = fachada
					.pesquisar(filtroCreditoARealizar, CreditoARealizar.class
							.getName());

			if (colecaoCreditoARealizar == null
					|| colecaoCreditoARealizar.isEmpty()) {
				throw new ActionServletException(
						"atencao.faturamento.credito_a_realizar.inexistente");
			} else {
				// seta a cole��o de cr�ditos a realizar na sess�o
				sessao.setAttribute("colecaoCreditoARealizar",
						colecaoCreditoARealizar);
			}
		}
		// ====================================================================

		// envia uma flag que ser� verificado no cliente_resultado_pesquisa.jsp
		// para saber se ir� usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaConsultaCreditoARealizar") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaConsultaCreditoARealizar",
							httpServletRequest
									.getParameter("caminhoRetornoTelaConsultaCreditoARealizar"));
		}

		// retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}
