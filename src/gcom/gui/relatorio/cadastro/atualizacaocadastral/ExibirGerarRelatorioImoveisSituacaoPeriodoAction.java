package gcom.gui.relatorio.cadastro.atualizacaocadastral;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioImoveisSituacaoPeriodoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioImoveisSituacaoPeriodo");
		
		RelatorioImoveisSituacaoPeriodoActionForm form = (RelatorioImoveisSituacaoPeriodoActionForm) actionForm;
		
		return retorno;
	}
}
