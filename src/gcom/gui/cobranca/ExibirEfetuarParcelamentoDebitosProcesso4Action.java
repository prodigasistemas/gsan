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
import org.jboss.logging.Logger;

public class ExibirEfetuarParcelamentoDebitosProcesso4Action extends GcomAction {

	private static Logger logger = Logger.getLogger(ExibirEfetuarParcelamentoDebitosProcesso4Action.class);
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = actionMapping.findForward("processo4");

		HttpSession sessao = request.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		DynaActionForm form = (DynaActionForm) actionForm;

		String codigoImovel = (String) form.get("matriculaImovel");
		logger.info("Parcelamento do imóvel " + codigoImovel);
		
		// Verifica se entrou na aba de Negociação
		Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = (Collection<OpcoesParcelamentoHelper>) sessao.getAttribute("colecaoOpcoesParcelamento");
		if (colecaoOpcoesParcelamento == null || colecaoOpcoesParcelamento.isEmpty()) {
			throw new ActionServletException("atencao.parametros.obrigatorios.nao.selecionados");
		}

		// Pega dados do formulário
		BigDecimal valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal((String) form.get("valorDebitoTotalAtualizado"));

		// 5.1.4 Valor do desconto
		BigDecimal valorASerNegociado = new BigDecimal("0.00");
		BigDecimal valorTotalDescontos = Util.formatarMoedaRealparaBigDecimal((String) form.get("valorTotalDescontos"));

		valorASerNegociado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		valorASerNegociado = valorDebitoTotalAtualizado.subtract(valorTotalDescontos);
		form.set("valorNegociado", Util.formatarMoedaReal(valorASerNegociado));

		// 5.1.6 Condições da Negociação
		if (colecaoOpcoesParcelamento != null && !colecaoOpcoesParcelamento.isEmpty()) {
			Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
			while (opcoesParcelamentoValores.hasNext()) {
				OpcoesParcelamentoHelper opcoesParcelamento = (OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
				if (((String) form.get("indicadorQuantidadeParcelas")).equals(opcoesParcelamento.getQuantidadePrestacao().toString())) {
					form.set("parcelaEscolhida", opcoesParcelamento.getQuantidadePrestacao().toString());

					form.set("valorParcelaEscolhida", Util.formatarMoedaReal(opcoesParcelamento.getValorPrestacao()));

					BigDecimal valorASerParcelado = new BigDecimal("0.00");
					valorASerParcelado = opcoesParcelamento.getValorPrestacao().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)
							.multiply(new BigDecimal(opcoesParcelamento.getQuantidadePrestacao()));

					form.set("valorASerParcelado", Util.formatarMoedaReal(valorASerParcelado));

					form.set("taxaJurosEscolhida", Util.formatarMoedaReal(opcoesParcelamento.getTaxaJuros()));

				}
			}
		}

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		if (sistemaParametro.getIndicadorParcelamentoConfirmado() == ConstantesSistema.SIM.shortValue()) {
			request.setAttribute("parcelamentoConfirmado", "OK");
		}

		return retorno;
	}
}
