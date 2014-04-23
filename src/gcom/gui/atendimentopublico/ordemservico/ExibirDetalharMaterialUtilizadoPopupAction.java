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
 * Action que define o pré-processamento da página de exibir detalhar material utilizado
 * 
 * @author Leonardo Regis
 * @created 14/09/2006
 */
public class ExibirDetalharMaterialUtilizadoPopupAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("detalharMaterialUtilizadoPopup");
		Fachada fachada = Fachada.getInstancia();
		DetalharMaterialUtilizadoPopupActionForm detalharMaterialUtilizadoPopupActionForm = (DetalharMaterialUtilizadoPopupActionForm) actionForm;
		Integer idOS = new Integer(detalharMaterialUtilizadoPopupActionForm.getNumeroOS());
		Integer idAtividade = new Integer(detalharMaterialUtilizadoPopupActionForm.getAtividadeId());
		
		Collection<ObterDadosAtividadeOSHelper> colecaoObterDadosAtividadeOSHelper = fachada.obterDadosAtividadeOS(idOS, idAtividade, ObterDadosAtividadeOSHelper.INDICADOR_MATERIAL.intValue());
		if (colecaoObterDadosAtividadeOSHelper != null && !colecaoObterDadosAtividadeOSHelper.isEmpty()) {
				detalharMaterialUtilizadoPopupActionForm.setColecaoOSAtividade(colecaoObterDadosAtividadeOSHelper);
		}
		return retorno;
	}
}
