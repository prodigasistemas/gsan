package gcom.gui.cadastro.dispositivomovel;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1015] Upload nova versão GSAN Dispositivo Móvel
 * 
 *   Este caso de uso permite realizar o envio dos arquivos referentes à 
 *   nova versão do GSAN Dispositivo Móvel.
 * 
 * @author Hugo Amorim
 * @since 11/05/2010
 *
 */
public class ExibirUploadVersaoDispositivoMovelAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirUploadVersaoDispositivoMovelAction");
		
		return retorno;
	}
}	
