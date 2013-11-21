package gcom.gui.cobranca.contratoparcelamento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Form que Inseri uma ContratoParcelamentoPorCliente
 * 
 * @author Paulo Otávio
 * @date 04/04/2011
 */
public class InformarValorParcelaContratoAction extends GcomAction {
	
	/**
	 * Este caso de uso permite a inclusão de uma resolução de diretoria
	 * 
	 * [UC1136] Inserir Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obtém a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		String method = httpServletRequest.getParameter("method");
		ActionForward retorno = actionMapping.findForward("exibir");
		
		if(method != null && !method.equals("")){
			
			if(method.equals("inserirParcela")){
								
			}else if(method.equals("removerParcela")){
				
			}
			
		}
		
		return retorno;
	}
	
}
