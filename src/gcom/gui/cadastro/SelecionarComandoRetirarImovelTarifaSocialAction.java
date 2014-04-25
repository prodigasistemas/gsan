package gcom.gui.cadastro;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SelecionarComandoRetirarImovelTarifaSocialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("resultadoSelecionarComandoRetirarImovelTarifaSocialAction");

		SelecionarComandoRetirarImovelTarifaSocialActionForm form = (SelecionarComandoRetirarImovelTarifaSocialActionForm) actionForm;
		
		if(form.getIndicadorSituacao() == null || form.getIndicadorSituacao().equals("")){
			throw new ActionServletException("atencao.campo.informado", null, "Situação");
		}
		
		// Devolve o mapeamento de retorno
		return retorno;
	}
}
