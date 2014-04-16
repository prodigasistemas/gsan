package gcom.gui.cobranca;

import gcom.cobranca.CobrancaCriterioLinha;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para atualizar a linha do criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 05/06/2006
 */
public class AtualizarCriterioCobrancaLinhaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarCriterioCobrancaLinha");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) sessao
				.getAttribute("cobrancaCriteriolinha");

		// atualiza cobranca criterio linha para ser exibido na tela de
		// inserir ou atualizar
		// verifica se o valor maximo é menor que o mínimo
		BigDecimal valorDebitoMinimo = null;
		if (criterioCobrancaActionForm.getValorDebitoMinimo() != null
				&& !criterioCobrancaActionForm.getValorDebitoMinimo()
						.equals("")) {
			valorDebitoMinimo = Util
					.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
							.getValorDebitoMinimo());
		}
		BigDecimal valorDebitoMaximo = null;
		if (criterioCobrancaActionForm.getValorDebitoMaximo() != null
				&& !criterioCobrancaActionForm.getValorDebitoMaximo()
						.equals("")) {
			valorDebitoMaximo = Util
					.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
							.getValorDebitoMaximo());
		}
		if (valorDebitoMinimo != null && valorDebitoMaximo != null) {
			if (valorDebitoMinimo.compareTo(valorDebitoMaximo) == 1) {
				throw new ActionServletException(
						"atencao.valor.maximo.debito.menor.valor.minimo.debito");
			}
		}

		cobrancaCriterioLinha.setValorMinimoDebito(valorDebitoMinimo);
		cobrancaCriterioLinha.setValorMaximoDebito(valorDebitoMaximo);

		Short qtdContasMinima = null;
		if (criterioCobrancaActionForm.getQtdContasMinima() != null
				&& !criterioCobrancaActionForm.getQtdContasMinima().equals("")) {
			qtdContasMinima = new Short(criterioCobrancaActionForm
					.getQtdContasMinima());
		}
		Short qtdContasMaxima = null;
		if (criterioCobrancaActionForm.getQtdContasMaxima() != null
				&& !criterioCobrancaActionForm.getQtdContasMaxima().equals("")) {
			qtdContasMaxima = new Short(criterioCobrancaActionForm
					.getQtdContasMaxima());
		}
		if (qtdContasMinima != null && qtdContasMaxima != null) {
			if (qtdContasMinima > qtdContasMaxima) {
				throw new ActionServletException(
						"atencao.quantidade.maxima.contas.menor.quantidade.minima.contas");
			}
		}
		cobrancaCriterioLinha.setQuantidadeMinimaContas(qtdContasMinima);
		cobrancaCriterioLinha.setQuantidadeMaximaContas(qtdContasMaxima);

		if (criterioCobrancaActionForm.getVlMinimoDebitoCliente() != null
				&& !criterioCobrancaActionForm.getVlMinimoDebitoCliente()
						.equals("")) {
			cobrancaCriterioLinha.setValorMinimoDebitoDebitoAutomatico(Util
					.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
							.getVlMinimoDebitoCliente()));
		}
		if (criterioCobrancaActionForm.getQtdMinContasCliente() != null
				&& !criterioCobrancaActionForm.getQtdMinContasCliente().equals(
						"")) {
			cobrancaCriterioLinha
					.setQuantidadeMinimaContasDebitoAutomatico(new Short(
							criterioCobrancaActionForm.getQtdMinContasCliente()));
		}
		if (criterioCobrancaActionForm.getVlMinimoContasMes() != null
				&& !criterioCobrancaActionForm.getVlMinimoContasMes()
						.equals("")) {
			cobrancaCriterioLinha.setValorMinimoContaMes(Util
					.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
							.getVlMinimoContasMes()));
		}
		if (criterioCobrancaActionForm.getQuantidadeMinimaParcelasAtraso() != null
				&& !criterioCobrancaActionForm
						.getQuantidadeMinimaParcelasAtraso().equals("")) {
			cobrancaCriterioLinha
					.setQuantidadeMinimaContasParcelamento(new Short(
							criterioCobrancaActionForm
									.getQuantidadeMinimaParcelasAtraso()));

		} else {
			cobrancaCriterioLinha
					.setQuantidadeMinimaContasParcelamento(new Short("0"));
		}
		cobrancaCriterioLinha.setUltimaAlteracao(new Date());

		httpServletRequest.setAttribute("fechaPopup", "true");
		sessao.removeAttribute("cobrancaCriteriolinha");

		return retorno;
	}
}
