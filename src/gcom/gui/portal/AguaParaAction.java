package gcom.gui.portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.portal.AguaParaActionForm;
import gcom.util.ControladorException;

public class AguaParaAction extends GcomAction{
	
	private AguaParaActionForm form;
	private HttpServletRequest request;
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ControladorException {
		ActionForward retorno = mapping.findForward("agua-para");
		this.form = (AguaParaActionForm) actionForm;
		HttpSession sessao = request.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		this.request = request;
		if(isResetar()) {
			resetar();
		}
		return retorno;
	}
	private void resetar() {
		form.setCpf("");
		form.setRg("");
		form.setIdImovel(null);
		form.setNome("");
		form.setTelefone("");
		form.setNis("");
		form.setArquivoBolsaFamiliaNis(null);
		form.setArquivoConta(null);
		form.setArquivoCpf(null);
		form.setArquivoRg(null);
	}
	private boolean isResetar() {
		String action = (String) request.getParameter("action");
		return action != null && action.equals("resetar");
	}
}
