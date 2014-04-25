package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarImovelCurvaAbcDebitosAction extends GcomAction {

	/**
	 * [UC0621] - Filtro para Curva ABC de Debitos
	 * @author: Ivan Sérgio
	 * @date: 30/07/2007
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("gerarCurvaAbcDebitos");
		
		ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm = (ImovelCurvaAbcDebitosActionForm) actionForm;

		/*
		 * [FS0005 - Verificar preenchimento dos campos]
		 * Campos obrigatórios: Referencia da cobranca (é validado antes);
		 * 						Valor minimo do Debito; 
		 */
		 
		if (imovelCurvaAbcDebitosActionForm.getValorMinimoDebito() == null) {
			retorno = actionMapping.findForward("filtrarImovelCurvaAbcDebitosDebito");
			
			httpServletRequest.setAttribute("msgValidacao", "Informe o Valor Mínimo do Débito.");
			
			FiltrarImovelCurvaAbcDebitosWizardAction filtrar = new FiltrarImovelCurvaAbcDebitosWizardAction();
			filtrar.exibirFiltrarImovelCurvaAbcDebitosDebito(actionMapping, actionForm,
					httpServletRequest, httpServletResponse);
		}
		
		return retorno;
	}
}
