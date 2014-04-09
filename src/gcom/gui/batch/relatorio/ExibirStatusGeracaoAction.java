package gcom.gui.batch.relatorio;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de status dos relatórios batch
 * 
 * @author Rodrigo Silveira
 * @created 29/09/2006
 */
public class ExibirStatusGeracaoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("statusGeracao");

		Fachada fachada = Fachada.getInstancia();

		// Pesquisar todos as funcionalidades iniciadas que representam os
		// relatórios batch do sistema
		httpServletRequest.setAttribute("colecaoRelatoriosDados", fachada
				.pesquisarRelatoriosBatchSistema());
		
		httpServletRequest.setAttribute("dataCorrente", new Date());

		return retorno;
	}
}
