package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelOutrosCriteriosCobrancaAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ImovelOutrosCriteriosActionForm form = (ImovelOutrosCriteriosActionForm) actionForm; 

		form.setIndicadorCodigoBarra("1");		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarImovelDadosCobranca");
	
		return retorno;
	}

}
