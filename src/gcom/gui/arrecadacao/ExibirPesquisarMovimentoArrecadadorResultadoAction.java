package gcom.gui.arrecadacao;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consultar Pagamento - Exibir 
 * 
 * @author TIAGO MORENO - 31/01/2006
 */
public class ExibirPesquisarMovimentoArrecadadorResultadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarMovimentoArrecadador");

		// Instacia a fachada
		//Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		//HttpSession sessao = httpServletRequest.getSession(false);
		
		return retorno;

	}

}
