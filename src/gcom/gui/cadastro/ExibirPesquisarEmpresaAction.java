package gcom.gui.cadastro;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0777] Pesquisar Empresa
 * 
 * @see gcom.gui.cadastro.PesquisarEmpresaAction
 * @see gcom.gui.cadastro.PesquisarEmpresaActionForm
 * 
 * @author Victor Cisneiros
 * @date 19/05/2008
 */
public class ExibirPesquisarEmpresaAction extends GcomAction {
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("exibirPesquisarEmpresaAction");
	}

}
