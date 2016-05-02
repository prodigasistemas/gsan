package gcom.gui.faturamento;

import gcom.atendimentopublico.AgenciaReguladora;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarRelatorioAgenciaReguladoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAgenciaReguladora");
		HttpSession sessao = httpServletRequest.getSession(false);

		GerarRelatorioAgenciaReguladoraActionForm form = (GerarRelatorioAgenciaReguladoraActionForm) actionForm;

		String limparForm = httpServletRequest.getParameter("limparForm");
		Fachada fachada = Fachada.getInstancia();
		
		if (limparForm != null && !limparForm.equals("")) {
			form.setIdAgenciaReguladora(null);
			form.setDescricaoAgenciaReguladora(null);
			form.setMesAno(null);
		} else {
			List<AgenciaReguladora> agencias = fachada.obterAgenciasReguladorasAtivas();
			sessao.setAttribute("colecaoAgencias", agencias);
		}

		return retorno;
	}

}
