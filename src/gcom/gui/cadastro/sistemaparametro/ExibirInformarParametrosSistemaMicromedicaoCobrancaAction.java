package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.util.ConstantesSistema;
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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 09/01/2007
 */
public class ExibirInformarParametrosSistemaMicromedicaoCobrancaAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirInformarParametrosSistemaMicromedicaoCobranca");

		HttpSession sessao = this.getSessao(httpServletRequest);

		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		SistemaParametro sistemaParametro = 
			(SistemaParametro) sessao.getAttribute("sistemaParametro");

		Collection colecaoHidrometroCapacidade = null;

		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		filtroHidrometroCapacidade.setCampoOrderBy(FiltroHidrometroCapacidade.ID);

		filtroHidrometroCapacidade.adicionarParametro(
			new ParametroSimples(FiltroHidrometroCapacidade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		colecaoHidrometroCapacidade = this.getFachada().pesquisar(
				filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());

		httpServletRequest.setAttribute("colecaoHidrometroCapacidade",colecaoHidrometroCapacidade);

		// Verifica se ja entrou nesse action, caso nao coloca no form os dados
		// do objeto sistemaParametro
		Integer cont;

		if (sessao.getAttribute("MicromedicaoCobranca") == null) {

			cont = 1;
			sessao.setAttribute("MicromedicaoCobranca", cont);

			if (sistemaParametro.getHidrometroCapacidade() != null) {
				form.setCodigoMenorCapacidade(sistemaParametro.getHidrometroCapacidade().getId().toString());
			}

			if (sistemaParametro.getIndicadorFaixaFalsa() != null) {
				form.setIndicadorGeracaoFaixaFalsa(sistemaParametro.getIndicadorFaixaFalsa().toString());
			}

			if (sistemaParametro.getIndicadorUsoFaixaFalsa() != null) {
				form.setIndicadorPercentualGeracaoFaixaFalsa(sistemaParametro.getIndicadorUsoFaixaFalsa().toString());
			}

			if (sistemaParametro.getPercentualFaixaFalsa() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getPercentualFaixaFalsa());

				form.setPercentualGeracaoFaixaFalsa(valorAux);
			}

			if (sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura() != null) {
				form.setIndicadorPercentualGeracaoFiscalizacaoLeitura(
					sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura().toString());
			}

			if (sistemaParametro.getIndicadorUsoFiscalizadorLeitura() != null) {
				form.setIndicadorGeracaoFiscalizacaoLeitura(
						sistemaParametro.getIndicadorUsoFiscalizadorLeitura().toString());
			}

			if (sistemaParametro.getPercentualFiscalizacaoLeitura() != null) {

				String valorAux = 
					Util.formatarMoedaReal(sistemaParametro.getPercentualFiscalizacaoLeitura());

				form.setPercentualGeracaoFiscalizacaoLeitura(valorAux);
			}

			if (sistemaParametro.getIncrementoMaximoConsumoRateio() != null) {

				form.setIncrementoMaximoConsumo(sistemaParametro.getIncrementoMaximoConsumoRateio().toString());
			}

			if (sistemaParametro.getDecrementoMaximoConsumoRateio() != null) {
				form.setDecrementoMaximoConsumo(sistemaParametro.getDecrementoMaximoConsumoRateio().toString());
			}
			
			if (sistemaParametro.getPercentualToleranciaRateio() != null) {

				String valorAux = Util.formataBigDecimal(sistemaParametro
						.getPercentualToleranciaRateio(), 1, false);

				form.setPercentualToleranciaRateioConsumo(valorAux);
			}
			
			if (sistemaParametro.getNumeroDiasVencimentoCobranca() != null) {
				form.setDiasVencimentoCobranca(sistemaParametro.getNumeroDiasVencimentoCobranca().toString());
			}

			if (sistemaParametro.getNumeroMaximoMesesSancoes() != null) {
				form.setNumeroMaximoMesesSancoes(sistemaParametro.getNumeroMaximoMesesSancoes().toString());
			}

			form.setValorSegundaVia(Util.formatarMoedaReal(sistemaParametro.getValorSegundaVia()));
			
			form.setIndicadorCobrarTaxaExtrato(""+sistemaParametro.getIndicadorCobrarTaxaExtrato());

			if (sistemaParametro.getCodigoPeriodicidadeNegativacao() != null) {
				form.setCodigoPeriodicidadeNegativacao(sistemaParametro.getCodigoPeriodicidadeNegativacao().toString());
			}

			form.setNumeroDiasCalculoAcrescimos(""+sistemaParametro.getNumeroDiasCalculoAcrescimos());
			
			form.setNumeroDiasValidadeExtrato(sistemaParametro.getNumeroDiasValidadeExtrato().toString());
			
			form.setIndicadorParcelamentoConfirmado(""+ sistemaParametro.getIndicadorParcelamentoConfirmado());
			
			form.setIndicadorTabelaPrice(""+ sistemaParametro.getIndicadorTabelaPrice());
			
			if (sistemaParametro.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo() != null){
				form.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(
						sistemaParametro.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo().toString());
			}
			
			if (sistemaParametro.getNumeroDiasValidadeExtratoPermissaoEspecial() != null) {
				form.setNumeroDiasValidadeExtratoPermissaoEspecial(
						sistemaParametro.getNumeroDiasValidadeExtratoPermissaoEspecial().toString());
			}

			form.setNumeroDiasVencimentoEntradaParcelamento(""+
					sistemaParametro.getNumeroDiasVencimentoEntradaParcelamento());
		
		}
		
		Collection colecaoResolucaoDiretoria = this.getFachada().pesquisarResolucaoDiretoriaMaiorDataVigenciaInicio();
		httpServletRequest.setAttribute("colecaoResolucaoDiretoria",colecaoResolucaoDiretoria);
		
		if (sistemaParametro.getResolucaoDiretoria() != null &&
			sistemaParametro.getResolucaoDiretoria().getId() != null) {
			form.setIdResolucaoDiretoria(sistemaParametro.getResolucaoDiretoria().getId().toString());
		}
		
		if (sistemaParametro.getIndicadorBloqueioContasContratoParcelDebitos() != null){
			form.setIndicadorBloqueioContasContratoParcelDebitos(sistemaParametro.getIndicadorBloqueioContasContratoParcelDebitos().toString());
		}
		
		if (sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() != null){
			form.setIndicadorBloqueioContasContratoParcelManterConta(sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta().toString());
		}
		
		if (sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito() != null){
			form.setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito().toString());
		}
		
		if(sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelDebito() != null){
		 	form.setIndicadorBloqueioDebitoACobrarContratoParcelDebito(sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelDebito().toString());
		}
				
		if (sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() != null){
			form.setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta().toString());
		}
		
		if(sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito() != null){
			form.setIndicadorBloqueioDebitoACobrarContratoParcelManterDebito(sistemaParametro.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito().toString());
		}
				
		if (sistemaParametro.getNumeroMaximoParcelasContratosParcelamento() != null){
			form.setNumeroMaximoParcelasContratosParcelamento(sistemaParametro.getNumeroMaximoParcelasContratosParcelamento().toString());
		}
	
		return retorno;
	}
}
