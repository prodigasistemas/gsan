package gcom.gui.faturamento.debito;

import gcom.cobranca.parcelamento.FiltroParcelamentoItem;
import gcom.cobranca.parcelamento.ParcelamentoItem;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoACobrarHistorico;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;
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
 * action respons�vel pela exibi��o da tela de consultar d�bito cobrado
 * 
 * @author Fernanda Paiva
 * @created 16 de Janeiro de 2006
 */
public class ExibirConsultarDebitoACobrarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno e seta o mapeamento para a tela de
		// consultar d�bito cobrado
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarDebitoACobrar");

		// cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o c�digo da conta do request
		String idImovel = httpServletRequest.getParameter("imovelID");

		String idDebito = httpServletRequest.getParameter("debitoID");
		String idDebitoHistorico = httpServletRequest.getParameter("debitoHistoricoID");

		String idParcelamento = httpServletRequest
				.getParameter("parcelamentoID");

		// se o c�digo n�o for nulo
//		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {
//			// remove a cole��o de d�bitos a cobrar
//			sessao.removeAttribute("colecaoDebitoACobrar");
//			sessao.removeAttribute("colecaoDebitoACobrarHistorico");
//		}

		/*
		 * Pesquisando o debito a partir do id imovel
		 * =====================================================================
		 */

		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {

			// cria o filtro do imovel
			String imovel = fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(idImovel));
			String enderecoFormatado = "";
			try {
				enderecoFormatado = fachada.pesquisarEnderecoFormatado(new Integer(idImovel));
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (ControladorException e) {
				
				e.printStackTrace();
			}

			httpServletRequest.setAttribute("idImovelConsultado", idImovel);
			httpServletRequest.setAttribute("enderecoFormatado", enderecoFormatado);

			// se n�o encontrou nenhum imovel com o c�digo informado
			if (imovel == null || imovel.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.imovel.inexistente");
			}

			// seta o objeto conta na sess�o
			httpServletRequest.setAttribute("imovelId", idImovel);
		}
		// ====================================================================

		// cria a cole��o de d�bitos a cobrar
		Collection colecaoDebitoACobrarConsultar;
		Collection colecaoDebitoACobrarHistoricoConsultar;

		/*
		 * D�bitos Cobrados (Carregar cole��o)
		 * ======================================================================
		 */
		// se n�o existir a cole��o na sess�o
		if (idParcelamento != null && !idParcelamento.equals("")) {
			FiltroParcelamentoItem filtroParcelamentoItem = new FiltroParcelamentoItem();

			// seta o c�digo do imovel no filtro
			filtroParcelamentoItem.adicionarParametro(new ParametroSimples(FiltroParcelamentoItem.PARCELAMENTO, idParcelamento));

			filtroParcelamentoItem.adicionarParametro(new ParametroNaoNulo(FiltroParcelamentoItem.DEBITO_A_COBRAR));

			// carrega o debitoACobrar
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoTipo");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual");
//			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar");
			/*
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.geracaoDebito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.anoMesReferenciaDebito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.anoMesCobrancaDebito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.numeroPrestacaoCobradas");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.numeroPrestacaoDebito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.valorDebito");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.valorTotal");
			 * filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.percentualTaxaJurosFinanciamento");
			 */
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.registroAtendimento");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.ordemServico");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.financiamentoTipo");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.cobrancaForma");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.usuario");

			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.debitoTipo");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.debitoCreditoSituacaoAtual");

			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.registroAtendimento");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.ordemServico");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.financiamentoTipo");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.cobrancaForma");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.usuario");
			// carrega o parcelamento
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
			
			// carrega o im�vel origem do d�bito a cobrar e debito a cobrar historico
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoACobrarGeralOrigem.debitoACobrar.imovel");
			filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.origem.debitoACobrarHistorico.imovel");

			Collection colecaoParcelamentoItem = fachada.pesquisar(filtroParcelamentoItem, ParcelamentoItem.class.getName());

			if (colecaoParcelamentoItem == null	|| colecaoParcelamentoItem.isEmpty()) {
				throw new ActionServletException("atencao.faturamento.debito_a_cobrar.inexistente");
			} else {
				// seta a cole��o de d�bitos cobrados na sess�o
				sessao.setAttribute("colecaoParcelamentoItem",	colecaoParcelamentoItem);
			}
			
		}else if(idDebitoHistorico != null && !idDebitoHistorico.equals("")){	
			//dados de DEBITO A COBRAR HISTORICO
			
			//cria o filtro de d�bito a cobrar
			FiltroDebitoACobrarHistorico filtroDebitoACobrarHistorico = new FiltroDebitoACobrarHistorico();

			// seta o c�digo do imovel no filtro
			filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.ID, idDebitoHistorico));
			
			if (idImovel != null) {
				filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.IMOVEL_ID, idImovel));
			}

			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
			
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");

			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");

			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
			
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
			// carrega o im�vel origem do d�bito a cobrar
			filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("origem.debitoACobrarHistorico.imovel");

			// pesquisa a cole��o de d�bitos cobrados
			colecaoDebitoACobrarHistoricoConsultar = fachada.pesquisar(filtroDebitoACobrarHistorico, DebitoACobrarHistorico.class.getName());
			if (colecaoDebitoACobrarHistoricoConsultar == null || colecaoDebitoACobrarHistoricoConsultar.isEmpty()) {
				throw new ActionServletException("atencao.faturamento.debito_a_cobrar.inexistente");
			} else {
				// seta a cole��o de d�bitos cobrados na sess�o
				sessao.setAttribute("colecaoDebitoACobrarHistoricoConsultar", colecaoDebitoACobrarHistoricoConsultar);
			}
			
		} else {
			//dados de DEBITO A COBRAR
			
			// cria o filtro de d�bito a cobrar
			FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();

			if (idDebito != null) {
				// seta o c�digo do imovel no filtro
				filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.ID, idDebito));
			}
			if (idImovel != null) {
				filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.IMOVEL_ID, idImovel));
			}

			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
			
			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");

			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");

			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
			
			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
			// carrega o im�vel origem do d�bito a cobrar
			filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeralOrigem.debitoACobrar.imovel");

			// pesquisa a cole��o de d�bitos cobrados
			colecaoDebitoACobrarConsultar = fachada.pesquisar(filtroDebitoACobrar, DebitoACobrar.class.getName());
			if (colecaoDebitoACobrarConsultar == null || colecaoDebitoACobrarConsultar.isEmpty()) {
				throw new ActionServletException("atencao.faturamento.debito_a_cobrar.inexistente");
			} else {
				// seta a cole��o de d�bitos cobrados na sess�o
				sessao.setAttribute("colecaoDebitoACobrarConsultar", colecaoDebitoACobrarConsultar);
			}
		}
		// ====================================================================

		// envia uma flag que ser� verificado no cliente_resultado_pesquisa.jsp
		// para saber se ir� usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaConsultaDebitoACobrar") != null) {
			sessao.setAttribute("caminhoRetornoTelaConsultaDebitoACobrar",
					httpServletRequest
							.getParameter("caminhoRetornoTelaConsultaDebitoACobrar"));
		}

		// retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}
