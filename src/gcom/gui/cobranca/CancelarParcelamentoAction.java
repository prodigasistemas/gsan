package gcom.gui.cobranca;

import gcom.cobranca.bean.CancelarParcelamentoHelper;
import gcom.cobranca.parcelamento.ParcelamentoMotivoCancelamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CancelarParcelamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		String codigoParcelamento = request.getParameter("codigoParcelamento");
		String codigoImovel = request.getParameter("codigoImovel");
		String motivo = request.getParameter("motivo");

		CancelarParcelamentoHelper helper = getFachada().pesquisarParcelamentoParaCancelar(Integer.valueOf(codigoParcelamento));
		
		if (helper != null) {
			helper.setMotivo(new ParcelamentoMotivoCancelamento(Integer.valueOf(motivo)));
			getFachada().cancelarParcelamento(helper, getUsuarioLogado(request), getFachada().pesquisarParametrosDoSistema());
		} else {
			new ActionServletException("atencao.cancelar_parcelamento");
		}
		
		montarPaginaSucesso(request, "Parcelamento de Débitos cancelado com sucesso.", 
				"Voltar", "exibirConsultarParcelamentoDebitoAction.do?codigoImovel=" + codigoImovel + "&codigoParcelamento=" + codigoParcelamento);
		
		return mapping.findForward("telaSucesso");
	}
}