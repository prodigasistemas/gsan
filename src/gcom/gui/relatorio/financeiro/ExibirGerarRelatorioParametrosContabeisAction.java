package gcom.gui.relatorio.financeiro;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Filtro do relatorio dos parâmetros contábeis 
 * [UC0824] Gerar Relatório dos Parâmetros Contábeis
 * 
 * @author Bruno Barros
 * @data 07/07/2008
 */
public class ExibirGerarRelatorioParametrosContabeisAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioParametrosContabeis");

		return retorno;
	}
	
}
