package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Anderson Italo
 * @date 20/11/2009
 * Pre-Processamento do UC9999 Consultar Motivo da não Geração de Documento de Cobrança
 */
public class ExibirMotivoNaoGeracaoDocumentoTipoComandoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("motivoNaoGeracaoDocumentoTipoComando");
	
		return retorno;

	}

}
