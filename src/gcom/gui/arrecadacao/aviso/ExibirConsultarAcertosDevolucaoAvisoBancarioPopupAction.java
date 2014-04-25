package gcom.gui.arrecadacao.aviso;

import gcom.arrecadacao.aviso.bean.AcertosAvisoBancarioHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição da tela de consultar 
 * a lista dos Acertos da Devolução do Aviso Bancario
 * 
 * @author Vivianne Sousa
 * @created 14/12/2006
 */
public class ExibirConsultarAcertosDevolucaoAvisoBancarioPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarAcertosDevolucaoAvisoBancarioPopup");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String idAvisoBancario = httpServletRequest.getParameter("idAvisoBancario");
		
		Collection<AcertosAvisoBancarioHelper> colecaoAcertosAvisoBancarioHelper = 
			fachada.pesquisarAcertosAvisoBancario(new Integer(idAvisoBancario),2);

		sessao.setAttribute("colecaoAcertosAvisoBancarioHelper",colecaoAcertosAvisoBancarioHelper);
		
		return retorno;

	}

}
