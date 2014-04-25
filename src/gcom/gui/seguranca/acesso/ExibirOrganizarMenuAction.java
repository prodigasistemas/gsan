package gcom.gui.seguranca.acesso;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.Modulo;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirOrganizarMenuAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirOrganizarMenu");
		
		FiltroModulo filtroModulo = 
			new FiltroModulo(FiltroModulo.NUMERO_ORDEM_MENU);
		
		Collection colecaoModulo = 
			this.getFachada().pesquisar(filtroModulo,Modulo.class.getName());
		
		this.getSessao(httpServletRequest).setAttribute("colecaoModulo",
				colecaoModulo);
		
		return retorno;
	}

}
