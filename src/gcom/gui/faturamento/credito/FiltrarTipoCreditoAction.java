package gcom.gui.faturamento.credito;


import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 05/08/2006
 */
public class FiltrarTipoCreditoAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servicço
	 * 
	 * [UC0437] Pesquisar Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 31/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterTipoCredito");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarTipoCreditoActionForm filtrarTipoCreditoActionForm = (FiltrarTipoCreditoActionForm) actionForm;

		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = filtrarTipoCreditoActionForm.getDescricao();

		String abreviatura = filtrarTipoCreditoActionForm.getAbreviatura();

		String tipoLancamentoContabil = filtrarTipoCreditoActionForm.getTipoLancamentoContabil();

		String indicadorgeracaoAutomaica = filtrarTipoCreditoActionForm
				.getIndicadorgeracaoAutomaica();

		String indicadorUso = filtrarTipoCreditoActionForm.getIndicativoUso();

		String valorLimiteCreditoInicial = filtrarTipoCreditoActionForm.getValorLimiteCreditoInicial();

		String valorLimiteCreditoFinal = filtrarTipoCreditoActionForm.getValorLimiteCreditoFinal();
		
		String tipoPesquisa = filtrarTipoCreditoActionForm.getTipoPesquisa();

		// Verifica se o campo Descrição da Anormalidade de Leitura foi informado
		
		// Descrição do Sistema de Esgoto
		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroCreditoTipo.adicionarParametro(new ComparacaoTextoCompleto(FiltroCreditoTipo.DESCRICAO, 
						descricao));
			} else {
				
				filtroCreditoTipo.adicionarParametro(new ComparacaoTexto(FiltroSistemaEsgoto.DESCRICAO,
						descricao));
			}
		}		
		
		// Verifica se o campo Descrição da Anormalidade de Leitura foi informado

		if (abreviatura != null
				&& !abreviatura.trim().equalsIgnoreCase(
						"")) {

			peloMenosUmParametroInformado = true;

			filtroCreditoTipo.adicionarParametro(new ComparacaoTexto(
					FiltroCreditoTipo.DESCRICAO_ABREVIADA, abreviatura));

		}

		// Verifica se o campo LancamentoItemContabil foi informado

		if (tipoLancamentoContabil != null
				&& !tipoLancamentoContabil.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroCreditoTipo.adicionarParametro(new ParametroSimples(
					FiltroCreditoTipo.LANCAMENTO_ITEM_CONTABIL,
					tipoLancamentoContabil));

		}
		
		// Verifica se o campo LancamentoItemContabil foi informado
		if (indicadorgeracaoAutomaica != null
				&& !indicadorgeracaoAutomaica.trim().equals("3") && !indicadorgeracaoAutomaica.trim().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroCreditoTipo.adicionarParametro(new ParametroSimples(
					FiltroCreditoTipo.INDICADOR_GERACAO_AUTOMATICO,
					indicadorgeracaoAutomaica));

		}
		
		// se o usuário informar o intervalo inicial do valor de limite
		if (valorLimiteCreditoInicial != null
				&& !valorLimiteCreditoInicial.trim().equalsIgnoreCase("")) {

			// se o usuário não informar o intervalo final do valor de
			// limite
			if (valorLimiteCreditoFinal == null
					|| valorLimiteCreditoFinal.trim().equalsIgnoreCase("")) {
				// o intervalo final do valor de limite vai receber o valor
				// do intervalo inicial
				valorLimiteCreditoFinal = valorLimiteCreditoInicial;

				// se o usuário informar o intervalo final do valor de
				// limite
			} else {
				// se o intervalo final do valor de limite for menor que o
				// inicial
				if ((Util
						.formatarMoedaRealparaBigDecimal(valorLimiteCreditoInicial))
						.doubleValue() > ((Util
						.formatarMoedaRealparaBigDecimal(valorLimiteCreditoFinal)))
						.doubleValue()) {
					// levanta a exceção para a próxima camada
					throw new ActionServletException("atencao.valorlimitefinal.menorque");
				}
			}

			// se o usuário não informar o intervalo inicial do valor de
			// limite
		} else {
			// seta o intervalo final do valor de limite para null
			valorLimiteCreditoFinal = null;
		}

		// se o intervalo final do valor de limite não estiver nulo ou em
		// branco
		if (valorLimiteCreditoFinal != null
				&& !valorLimiteCreditoFinal.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			peloMenosUmParametroInformado = true;
			// seta no filtro para retornar os tipos de débito entre os
			// valores informados
			filtroCreditoTipo
					.adicionarParametro(new MaiorQue(
							FiltroCreditoTipo.VALOR_LIMITE_CREDITO,
							Util
									.formatarMoedaRealparaBigDecimal(valorLimiteCreditoInicial),
							ParametroSimples.CONECTOR_AND));
			filtroCreditoTipo
					.adicionarParametro(new MenorQue(
							FiltroCreditoTipo.VALOR_LIMITE_CREDITO,
							Util
									.formatarMoedaRealparaBigDecimal(valorLimiteCreditoFinal)));
		}

		if (indicadorUso != null && !indicadorUso.trim().equals("3") && !indicadorUso.trim().equals("")) {

			peloMenosUmParametroInformado = true;

			filtroCreditoTipo.adicionarParametro(new ParametroSimples(
					FiltroCreditoTipo.INDICADOR_USO, indicadorUso));

		}


		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

		sessao.setAttribute("filtroTipoCredito",
				filtroCreditoTipo);

		return retorno;
	}

}
