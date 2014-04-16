package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelCurvaAbcDebitosParametros extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarImovelCurvaAbcDebitosParametros");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm = (ImovelCurvaAbcDebitosActionForm) actionForm;
		
		if (imovelCurvaAbcDebitosActionForm.getIndicadorImovelMedicaoIndividualizada() != null) {
			httpServletRequest.setAttribute("indicadorImovelMedicaoIndividualizada",
					imovelCurvaAbcDebitosActionForm.getIndicadorImovelMedicaoIndividualizada());
		}
		
		if (imovelCurvaAbcDebitosActionForm.getIndicadorImovelParalizacaoFaturamentoCobranca() != null) {
			httpServletRequest.setAttribute("indicadorImovelParalizacaoFaturamentoCobranca",
					imovelCurvaAbcDebitosActionForm.getIndicadorImovelParalizacaoFaturamentoCobranca());
		}
		
		// Usado para fazer o controle de navegacao por conta da Aba Local 
		sessao.setAttribute("abaAtual", "PARAMETROS");
		
		if (imovelCurvaAbcDebitosActionForm.getClassificacao() != null) {
			httpServletRequest.setAttribute("classificacao", imovelCurvaAbcDebitosActionForm.getClassificacao());
		}
		
		return retorno;
	}
}
