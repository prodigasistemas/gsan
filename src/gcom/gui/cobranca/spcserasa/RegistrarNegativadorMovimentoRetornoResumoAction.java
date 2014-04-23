package gcom.gui.cobranca.spcserasa;

import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável para dar um onLoad no arquivo e importar os ceps.
 * 
 * @author Yara Taciane
 * @created 09/01/2008
 */
public class RegistrarNegativadorMovimentoRetornoResumoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("registrarNegativadorMovimentoRetornoResumo");
		
		RegistrarNegativadorMovimentoRetornoActionForm form = (RegistrarNegativadorMovimentoRetornoActionForm) actionForm;
				
		form.setDataProcessamento(Util.formatarData(new Date()));		
		
		form.setHoraProcessamento(Util.formatarHoraSemSegundos(new Date()));
	

		return retorno;
	}
}
