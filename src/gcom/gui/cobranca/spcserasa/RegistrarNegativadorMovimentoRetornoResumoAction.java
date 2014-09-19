package gcom.gui.cobranca.spcserasa;

import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RegistrarNegativadorMovimentoRetornoResumoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("registrarNegativadorMovimentoRetornoResumo");
		RegistrarNegativadorMovimentoRetornoActionForm form = (RegistrarNegativadorMovimentoRetornoActionForm) actionForm;
		form.setDataProcessamento(Util.formatarData(new Date()));
		form.setHoraProcessamento(Util.formatarHoraSemSegundos(new Date()));

		return retorno;
	}
}
