package gcom.gui.faturamento.consumotarifa;

import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirSubCategoriaFaixaConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("inserirSubCategoriaFaixaConsumoTarifa");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessaoFaixa = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		InserirSubCategoriaFaixaConsumoTarifaActionForm inserirSubCategoriaFaixaConsumoTarifaActionForm = (InserirSubCategoriaFaixaConsumoTarifaActionForm) actionForm;
		Collection colecaoFaixa = (Collection) sessaoFaixa.getAttribute("colecaoFaixa");

		String valorConsumoMinimo = (String) sessaoFaixa.getAttribute("consumoMinimo");
		String consumoM = inserirSubCategoriaFaixaConsumoTarifaActionForm
				.getLimiteSuperiorFaixa();
		if (consumoM.equalsIgnoreCase("999999")){
			retorno = actionMapping.findForward("inserirSubCategoriaConsumoTarifa");
		}
		
		Integer consumoMin = new Integer(
				(String) inserirSubCategoriaFaixaConsumoTarifaActionForm
						.getLimiteSuperiorFaixa());

		if (colecaoFaixa == null) {
			colecaoFaixa = new ArrayList();
		} else {
			if (!colecaoFaixa.isEmpty()) {
				Iterator colecaoFaixaIt = colecaoFaixa.iterator();
				while (colecaoFaixaIt.hasNext()) {
					ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIt
							.next();
					if (consumoMin.compareTo(consumoTarifaFaixa
							.getNumeroConsumoFaixaFim()) < 0) {
						throw new ActionServletException(
								"atencao.valor_consumoMinimo_menor2");
					}
				}

			}

		}

		ConsumoTarifaFaixa consumoTarifaFaixa = new ConsumoTarifaFaixa();

		Integer i = 0;
		Object[] teste = colecaoFaixa.toArray();
		if (teste.length > 0) {
			ConsumoTarifaFaixa consumoTarifaFaixa2 = (ConsumoTarifaFaixa) teste[teste.length - 1];
			if (colecaoFaixa != null) {
				i = consumoTarifaFaixa2.getNumeroConsumoFaixaFim();
			}
		}

		BigDecimal valorConsumoMinimoBigDecimal = new BigDecimal(valorConsumoMinimo);
		BigDecimal ConsumoMBigDecimal = new BigDecimal(consumoM);
		
		if (valorConsumoMinimoBigDecimal.compareTo(ConsumoMBigDecimal) >= 0 ) {
			throw new ActionServletException(
					"atencao.valor_consumoMinimo_menor");
		}

		consumoTarifaFaixa.setNumeroConsumoFaixaInicio(new Integer(i + 1));
		consumoTarifaFaixa.setNumeroConsumoFaixaFim(new Integer(
				inserirSubCategoriaFaixaConsumoTarifaActionForm
						.getLimiteSuperiorFaixa()));
		consumoTarifaFaixa
				.setValorConsumoTarifa(Util
						.formatarMoedaRealparaBigDecimal(inserirSubCategoriaFaixaConsumoTarifaActionForm
								.getValorM3Faixa()));
		consumoTarifaFaixa.setUltimaAlteracao(new Date());

		colecaoFaixa.add(consumoTarifaFaixa);

		sessaoFaixa.setAttribute("colecaoFaixa", colecaoFaixa);
		
		
		return retorno;

	}

}
