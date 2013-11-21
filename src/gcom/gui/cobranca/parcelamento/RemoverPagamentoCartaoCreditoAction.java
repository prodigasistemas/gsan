package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.bean.ParcelamentoCartaoCreditoHelper;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverPagamentoCartaoCreditoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping
				.findForward("removerPagamentoCartaoCreditoAction");

		Collection colecaoPagamentos = (Collection) sessao
				.getAttribute("colecaoTransacao");

		String tempoInclusao = httpServletRequest.getParameter("tempoInclusao");

		Iterator iteraColecaoPagamentos = colecaoPagamentos.iterator();

		while (iteraColecaoPagamentos.hasNext()) {

			ParcelamentoCartaoCreditoHelper helper = (ParcelamentoCartaoCreditoHelper) iteraColecaoPagamentos
					.next();

			if (helper.getTempoInclusao().equals(tempoInclusao)) {
				iteraColecaoPagamentos.remove();
				break;
			}
		}

		sessao.setAttribute("colecaoPagamentos", colecaoPagamentos);
		httpServletRequest.setAttribute("reload", true);

		return retorno;

	}

}
