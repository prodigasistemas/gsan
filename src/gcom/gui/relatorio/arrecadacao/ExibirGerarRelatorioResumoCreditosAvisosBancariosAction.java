package gcom.gui.relatorio.arrecadacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;

public class ExibirGerarRelatorioResumoCreditosAvisosBancariosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward retorno = mapping.findForward("exibirGerarRelatorioResumoCreditosAvisosBancarios");

		return retorno;
	}

}
