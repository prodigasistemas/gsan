package gcom.gui.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.OpcoesParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * Permite efetuar o parcelamento dos débitos de um imóvel
 * 
 * [UC0214] Efetuar Parcelamento de Débitos
 *
 * @author Roberta Costa
 * @date 24/01/2006
 */
public class ExibirEfetuarParcelamentoDebitosProcesso4Action extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo4");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;

		// Verifica se entrou na aba de Negociação
        Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = (Collection<OpcoesParcelamentoHelper>)
			sessao.getAttribute("colecaoOpcoesParcelamento");
		if( colecaoOpcoesParcelamento == null || colecaoOpcoesParcelamento.isEmpty() ){
			throw new ActionServletException(
					"atencao.parametros.obrigatorios.nao.selecionados");
		}
		
		// Pega dados do formulário
		BigDecimal valorDebitoTotalAtualizado = Util
			.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm
				.get("valorDebitoTotalAtualizado"));

		// 5.1.4 Valor do desconto
		BigDecimal valorASerNegociado = new BigDecimal("0.00");
		BigDecimal valorTotalDescontos = Util
			.formatarMoedaRealparaBigDecimal((String)efetuarParcelamentoDebitosActionForm
					.get("valorTotalDescontos"));
		
		valorASerNegociado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		valorASerNegociado = valorDebitoTotalAtualizado.subtract(valorTotalDescontos);
			efetuarParcelamentoDebitosActionForm
				.set("valorNegociado", Util.formatarMoedaReal(valorASerNegociado));

		
		// 5.1.6 Condições da Negociação
		if( colecaoOpcoesParcelamento != null && ! colecaoOpcoesParcelamento.isEmpty() ){
			Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
			while(opcoesParcelamentoValores.hasNext()) {
				OpcoesParcelamentoHelper opcoesParcelamento = 
					(OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
				if( ((String)efetuarParcelamentoDebitosActionForm.get("indicadorQuantidadeParcelas"))
						.equals(opcoesParcelamento.getQuantidadePrestacao().toString()) ){
					efetuarParcelamentoDebitosActionForm
						.set("parcelaEscolhida", opcoesParcelamento.getQuantidadePrestacao().toString());

					efetuarParcelamentoDebitosActionForm
						.set("valorParcelaEscolhida",Util.formatarMoedaReal(opcoesParcelamento.getValorPrestacao()));

					BigDecimal valorASerParcelado = new BigDecimal("0.00");
					valorASerParcelado = opcoesParcelamento.getValorPrestacao()
						.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)
							.multiply(new BigDecimal(opcoesParcelamento.getQuantidadePrestacao())); 
					
					efetuarParcelamentoDebitosActionForm
						.set("valorASerParcelado",Util.formatarMoedaReal(valorASerParcelado));
					
					efetuarParcelamentoDebitosActionForm
						.set("taxaJurosEscolhida",Util.formatarMoedaReal(opcoesParcelamento.getTaxaJuros()));

				}
			}
		}
		
		/*
		 * Colocado por Raphael Rossiter em 25/08/2008 - Analista: Rosana Carvalho
		 * 
		 * O sistema verifica se o parcelamento é para ser incluído obrigatoriamente já confirmado
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getIndicadorParcelamentoConfirmado() == 
			ConstantesSistema.SIM.shortValue()){
			
			httpServletRequest.setAttribute("parcelamentoConfirmado", "OK");
		}

		return retorno;
	}
}
