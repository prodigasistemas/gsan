package gcom.gui.cadastro.atualizacaocadastral;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiscalizarImovelAtualizacaoCadastralAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {
		
		ActionForward retorno =	actionMapping.findForward("filtrarAlteracaoAtualizacaoCadastral");
		
		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm form = 
			(ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        fachada.fiscalizarImovel(Integer.parseInt(form.getIdImovel()));
		
		return retorno;
	}
}
