package gcom.gui.atendimentopublico.ordemservico;


import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeOSHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de exibir detalhar periodo execução equipe
 * 
 * @author Leonardo Regis
 * @created 14/09/2006
 */
public class ExibirDetalharPeriodoExecucaoEquipePopupAction extends GcomAction {
	/**
	 * Execute do Detalhar periodo execução equipe
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("detalharPeriodoExecucaoEquipePopup");
		Fachada fachada = Fachada.getInstancia();
		DetalharPeriodoExecucaoEquipePopupActionForm detalharPeriodoExecucaoEquipePopupActionForm = (DetalharPeriodoExecucaoEquipePopupActionForm) actionForm;
		Integer idOS = new Integer(detalharPeriodoExecucaoEquipePopupActionForm.getNumeroOS());
		Integer idAtividade = new Integer(detalharPeriodoExecucaoEquipePopupActionForm.getAtividadeId());
		
		Collection<ObterDadosAtividadeOSHelper> colecaoObterDadosAtividadeOSHelper = fachada.obterDadosAtividadeOS(idOS, idAtividade, ObterDadosAtividadeOSHelper.INDICADOR_PERIODO.intValue());
		if (colecaoObterDadosAtividadeOSHelper != null && !colecaoObterDadosAtividadeOSHelper.isEmpty()) {
				detalharPeriodoExecucaoEquipePopupActionForm.setColecaoOSAtividade(colecaoObterDadosAtividadeOSHelper);
		}
		return retorno;
	}
}
