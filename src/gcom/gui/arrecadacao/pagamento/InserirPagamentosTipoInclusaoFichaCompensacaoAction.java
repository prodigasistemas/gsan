package gcom.gui.arrecadacao.pagamento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.GcomAction;

public class InserirPagamentosTipoInclusaoFichaCompensacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o retorno para nulo pois o retrono vai ser definido pelo wizard 
		ActionForward retorno = null;
		
		return retorno;
	}
}
