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
 * action responsável pela exibição da tela de consultar crédito a realizar
 * 
 * @author Fernanda Paiva
 * @created 17 de Janeiro de 2006
 */
public class ExibirConsultarCreditoARealizarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno e seta o mapeamento para a tela de
		// consultar Crédito realizado
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarCreditoARealizar");

		// cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o código da conta do request
		String idImovel = httpServletRequest.getParameter("imovelID");
		String idCredito = httpServletRequest.getParameter("creditoID");
		String idCreditoHistorico = httpServletRequest.getParameter("creditoHistoricoID");
		String idParcelamento = httpServletRequest.getParameter("parcelamentoID");

		// se o código não for nulo
		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {
			// remove o objeto conta da sessão
			sessao.removeAttribute("imovelConsultar");
			// remove a coleção de créditos a realizar
			sessao.removeAttribute("colecaoCreditoARealizar");
			sessao.removeAttribute("colecaoCreditoARealizarHistorico");
		}

		/*
		 * Pesquisando o crédito a partir do id imovel
		 * =====================================================================
		 */

		// cria o objeto imovel
		Imovel imovelConsultar = null;

		// se o código da conta não for nulo
		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {

			// cria o filtro do imovel
			FiltroImovel filtroImovel = new FiltroImovel();

			// seta o código do imovel no filtro
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			// pesquisa o imovel na base de dados
			Collection colecaoImovel = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());

			// se não encontrou nenhum imovel com o código informado
			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				throw new ActionServletException("atencao.imovel.inexistente");
			}

			String enderecoFormatado = "";
			try {
				enderecoFormatado = fachada
						.pesquisarEnderecoFormatado(new Integer(idImovel));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ControladorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			httpServletRequest.setAttribute("enderecoFormatado",
					enderecoFormatado);

			// recupera o objeto imovel da coleção
			imovelConsultar = (Imovel) Util
					.retonarObjetoDeColecao(colecaoImovel);

			// seta o objeto conta na sessão
			sessao.setAttribute("imovelConsultar", imovelConsultar);
		}
		// se já existe uma conta na sessão
		else if (sessao.getAttribute("imovelConsultar") != null) {
			// recupera a conta da sessão
			imovelConsultar = (Imovel) sessao.getAttribute("imovelConsultar");
		} else {
			// levanta o erro de conta inexistente
			throw new ActionServletException("atencao.imovel.inexistente");
		}
		// ====================================================================

		/*
		 * Créditos A Realizar (Carregar coleção)
		 * ======================================================================
		 */
		// se não existir a coleção na sessão
		if (idParcelamento != null && !idParcelamento.equals("")) {
			FiltroParcelamentoItem filtroParcelamentoItem = new FiltroParcelamentoItem();

			// seta o código do imovel no filtro
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
			
			// carrega o imóvel origem do crédito a realizar
			filtroParcelamentoItem
					.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarGeral.creditoARealizar.origem.creditoARealizar.imovel");

			Collection colecaoParcelamentoItem = fachada.pesquisar(
					filtroParcelamentoItem, ParcelamentoItem.class.getName());

			if (colecaoParcelamentoItem == null
					|| colecaoParcelamentoItem.isEmpty()) {
				throw new ActionServletException(
						"atencao.faturamento.credito_a_realizar.inexistente");
			} else {
				// seta a coleção de débitos cobrados na sessão
				sessao.setAttribute("colecaoParcelamentoItem",
						colecaoParcelamentoItem);
			}
		} else if(idCreditoHistorico != null && !idCreditoHistorico.equals("")){
			// CREDITO A COBRAR HISTORICO
			
			//cria o filtro de créditos a realizar historico
			FiltroCreditoARealizarHistorico filtroCreditoARealizarHistorico = new FiltroCreditoARealizarHistorico();

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");

			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("creditoOrigem");
			
			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
			// carrega o imóvel origem do crédito a realizar historico
			filtroCreditoARealizarHistorico.adicionarCaminhoParaCarregamentoEntidade("origem.creditoARealizarHistorico.imovel");

			if (idImovel != null) {
				filtroCreditoARealizarHistorico.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizarHistorico.IMOVEL_ID, idImovel));
			}

			// seta o código do credito no filtro
			filtroCreditoARealizarHistorico.adicionarParametro(new ParametroSimples(
					FiltroCreditoARealizarHistorico.ID, idCreditoHistorico));

			// pesquisa a coleção de créditos a realizar historico
			Collection<CreditoARealizar> colecaoCreditoARealizarHistorico = fachada
					.pesquisar(filtroCreditoARealizarHistorico, CreditoARealizarHistorico.class.getName());

			if (colecaoCreditoARealizarHistorico == null || colecaoCreditoARealizarHistorico.isEmpty()) {
				throw new ActionServletException("atencao.faturamento.credito_a_realizar.inexistente");
			} else {
				// seta a coleção de créditos a realizar historico na sessão
				sessao.setAttribute("colecaoCreditoARealizarHistorico", colecaoCreditoARealizarHistorico);
			}
			
			
		} else {
			// cria o filtro de créditos a realizar
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
			
			// carrega o imóvel origem do crédito a realizar
			filtroCreditoARealizar
					.adicionarCaminhoParaCarregamentoEntidade("origem.creditoARealizar.imovel");

			if (idImovel != null) {
				filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizar.IMOVEL_ID, idImovel));
			}

			if (idCredito != null) {
				// seta o código do credito no filtro
				filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizar.ID, idCredito));
			}

			// pesquisa a coleção de créditos a realizar
			Collection<CreditoARealizar> colecaoCreditoARealizar = fachada
					.pesquisar(filtroCreditoARealizar, CreditoARealizar.class
							.getName());

			if (colecaoCreditoARealizar == null
					|| colecaoCreditoARealizar.isEmpty()) {
				throw new ActionServletException(
						"atencao.faturamento.credito_a_realizar.inexistente");
			} else {
				// seta a coleção de créditos a realizar na sessão
				sessao.setAttribute("colecaoCreditoARealizar",
						colecaoCreditoARealizar);
			}
		}
		// ====================================================================

		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaConsultaCreditoARealizar") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaConsultaCreditoARealizar",
							httpServletRequest
									.getParameter("caminhoRetornoTelaConsultaCreditoARealizar"));
		}

		// retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
