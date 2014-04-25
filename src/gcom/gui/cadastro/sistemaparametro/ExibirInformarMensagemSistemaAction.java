package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC__?__] Informar Mensagem do Sistema
 * 
 * @author Kassia Albuquerque
 * @created 27/02/2007
 */


public class ExibirInformarMensagemSistemaAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("informarMensagemSistema");
			
			InformarMensagemSistemaActionForm informarMensagemSistemaActionForm = (InformarMensagemSistemaActionForm) actionForm;
	
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			// Pesquisa os parâmetros do sistema
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			informarMensagemSistemaActionForm.setMensagemSistema(sistemaParametro.getMensagemSistema());
			
			sessao.setAttribute("sistemaParametro", sistemaParametro);
			
			return retorno;
	}

}
