package gcom.gui.cadastro.sistemaparametro;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

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
public class ExibirInformarParametrosSistemaArrecadacaoFinanceiroAction extends
		GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirInformarParametrosSistemaArrecadacaoFinanceiro");

		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		HttpSession sessao = this.getSessao(httpServletRequest);

		SistemaParametro sistemaParametro = 
			(SistemaParametro) sessao.getAttribute("sistemaParametro");

		// Verifica se os dados foram informados da tabela existem e joga
		// numa
		// colecao

		FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
		filtroContaBancaria.setCampoOrderBy(FiltroContaBancaria.ID);

		Collection<ContaBancaria> colecaoContaBancaria = 
			this.getFachada().pesquisar(filtroContaBancaria, ContaBancaria.class.getName());

		if (colecaoContaBancaria == null || colecaoContaBancaria.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,
				"Tabela Conta Bancaria");
		}

		httpServletRequest.setAttribute("colecaoContaBancaria",colecaoContaBancaria);

		// Verifica se ja entrou nesse action, caso nao coloca no form os dados
		// do objeto sistemaParametro

		Integer cont;

		if (sessao.getAttribute("ArrecadacaoFinanceiro") == null) {
			
			cont = 1;
			sessao.setAttribute("ArrecadacaoFinanceiro", cont);

			String anoMesArrecadacao = 
				Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao().toString());
			
			form.setMesAnoReferenciaArrecadacao("" + anoMesArrecadacao);

			if (sistemaParametro.getCodigoEmpresaFebraban() != null) {
				form.setCodigoEmpresaFebraban(sistemaParametro.getCodigoEmpresaFebraban().toString());
			}

			if (sistemaParametro.getNumeroLayoutFebraban() != null) {
				form.setNumeroLayOut(sistemaParametro.getNumeroLayoutFebraban().toString());
			}

			if (sistemaParametro.getContaBancaria() != null) {
				form.setIndentificadorContaDevolucao(sistemaParametro.getContaBancaria().getId().toString());
			}
			
			if(sistemaParametro.getIndicadorValorMovimentoArrecadador() != null){
				form.setIndicadorValorMovimentoArrecadador(String.valueOf(sistemaParametro.getIndicadorValorMovimentoArrecadador())) ; 
			}

			if (sistemaParametro.getPercentualFinanciamentoEntradaMinima() != null) {

				String valorAux = 
					Util.formatarMoedaReal(sistemaParametro.getPercentualFinanciamentoEntradaMinima());

				form.setPercentualEntradaMinima(valorAux);
			}

			if (sistemaParametro.getNumeroMaximoParcelasFinanciamento() != null) {
				form.setMaximoParcelas(sistemaParametro.getNumeroMaximoParcelasFinanciamento().toString());
			}

			if (sistemaParametro.getPercentualMaximoAbatimento() != null) {

				String valorAux = 
					Util.formatarMoedaReal(sistemaParametro.getPercentualMaximoAbatimento());

				form.setPercentualMaximoAbatimento(valorAux);
			}

			if (sistemaParametro.getPercentualTaxaJurosFinanciamento() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro
						.getPercentualTaxaJurosFinanciamento());

				form.setPercentualTaxaFinanciamento(valorAux);
			}

			if (sistemaParametro.getNumeroMaximoParcelaCredito() != null) {
				form.setNumeroMaximoParcelaCredito(sistemaParametro.getNumeroMaximoParcelaCredito().toString());
			}

			if (sistemaParametro.getPercentualMediaIndice() != null) {

				String valorAux = 
					Util.formatarMoedaReal(sistemaParametro.getPercentualMediaIndice());

				form.setPercentualCalculoIndice(valorAux);
			}
			
			if(sistemaParametro.getNumeroModuloDigitoVerificador()!=null
					&& !sistemaParametro.getNumeroModuloDigitoVerificador().equals("")){
				
				form.setNumeroModuloDigitoVerificador(sistemaParametro.getNumeroModuloDigitoVerificador().toString());
				
			}
			if(sistemaParametro.getNumeroMesesPesquisaImoveisRamaisSuprimidos()!=null){
				form.setNumeroMesesPesquisaImoveisRamaisSuprimidos(
						sistemaParametro.getNumeroMesesPesquisaImoveisRamaisSuprimidos().toString());
			}
			if(sistemaParametro.getNumeroAnoQuitacao()!=null){
				form.setNumeroAnoQuitacao(
						sistemaParametro.getNumeroAnoQuitacao().toString());
		  	}
			if(sistemaParametro.getIndicadorContaParcelada()!=null){
				form.setIndicadorContaParcelada(
						sistemaParametro.getIndicadorContaParcelada().toString());	
			}
			if(sistemaParametro.getIndicadorCobrancaJudical()!=null){
				form.setIndicadorCobrancaJudical(
						sistemaParametro.getIndicadorCobrancaJudical().toString());	
			}
			if(sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao()!=null){
				form.setNumeroMesesAnterioresParaDeclaracaoQuitacao(
						sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao().toString());	
			}
			if (sistemaParametro.getCdDadosDiarios() != null){
				form.setCdDadosDiarios(
						sistemaParametro.getCdDadosDiarios().toString());
			}
		}
		return retorno;
	}

}
