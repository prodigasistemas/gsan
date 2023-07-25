package gcom.gui.portal;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarCertidaoNegativaClienteAction extends GcomAction {

	private String cpfOuCnpjCliente;
	
	private HttpSession sessao;
	
	private static final String ATRIBUTO_CPF_OU_CNPJ = "cpfOuCnpjCliente";
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		this.sessao = request.getSession(false);
		
		this.cpfOuCnpjCliente = (String) sessao.getAttribute(ATRIBUTO_CPF_OU_CNPJ);
		
//		if (cpfOuCnpjCliente == null) {
//			return mapping.findForward("acessar-portal");
//		}
		return mapping.findForward("exibir");
	}
}
