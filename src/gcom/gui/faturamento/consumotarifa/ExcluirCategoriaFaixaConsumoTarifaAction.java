package gcom.gui.faturamento.consumotarifa;

import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.gui.GcomAction;

import java.util.Collection;
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
public class ExcluirCategoriaFaixaConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		if (httpServletRequest.getParameter("manter") == null){
			retorno = actionMapping
				.findForward("inserirCategoriaConsumoTarifa");
		} else{
			retorno = actionMapping
				.findForward("manterCategoriaConsumoTarifa");
		}

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessaoFaixa = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		String posicaoExcluir = httpServletRequest.getParameter("posicao");

		Collection colecaoFaixa = null;

		if (sessaoFaixa != null) {
			colecaoFaixa = (Collection) sessaoFaixa
					.getAttribute("colecaoFaixa");
			Iterator colecaoFaixaIterator = colecaoFaixa.iterator();

			while (colecaoFaixaIterator.hasNext()) {
				ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIterator
						.next();
				if (consumoTarifaFaixa.getUltimaAlteracao().getTime() == Long
						.parseLong(posicaoExcluir)) {
					colecaoFaixaIterator.remove();
				}
			}
		}

		return retorno;
	}
}
