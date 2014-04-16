package gcom.gui.batch;

import gcom.batch.ProcessoTipo;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que insere um ProcessoIniciado no sistema
 * 
 * @author Rodrigo Silveira
 * @created 24/07/2006
 */
public class InserirProcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		InserirProcessoActionForm inserirProcessoActionForm = (InserirProcessoActionForm) actionForm;

		int idProcessoTipo = Integer.parseInt(inserirProcessoActionForm
				.getIdProcessoTipo());

		if (idProcessoTipo == ProcessoTipo.MENSAL
				|| idProcessoTipo == ProcessoTipo.EVENTUAL) {
			retorno = actionMapping
					.findForward("inserirProcessoMensalEventual");
		}

		if (idProcessoTipo == ProcessoTipo.COBRANCA_COMANDADO) {
			retorno = actionMapping
					.findForward("inserirProcessoCobrancaComandado");
		}

		if (idProcessoTipo == ProcessoTipo.FATURAMENTO_COMANDADO) {
			retorno = actionMapping
					.findForward("inserirProcessoFaturamentoComandado");
		}

		return retorno;
	}
}
