package gcom.gui.arrecadacao.aviso;

import gcom.arrecadacao.aviso.bean.DeducoesHelper;
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
 * action responsável pela exibição da tela de consultar a lista das Deduçoes do Aviso Bancario
 * 
 * @author Vivianne Sousa
 * @created 13/12/2006
 */
public class ExibirConsultarDeducoesAvisoBancarioPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarDeducoesAvisoBancarioPopup");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String idAvisoBancario = httpServletRequest.getParameter("idAvisoBancario");
		
		Collection<DeducoesHelper> colecaoDeducoesHelper = fachada.pesquisarDeducoesAvisoBancario(new Integer (idAvisoBancario));

		sessao.setAttribute("colecaoDeducoesHelper",colecaoDeducoesHelper);
		
		return retorno;

	}

}
