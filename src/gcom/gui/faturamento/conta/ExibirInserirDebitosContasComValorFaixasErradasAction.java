package gcom.gui.faturamento.conta;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Inserir Débitos para as contas impressas via Impressão Simultânea de Contas que sairam com o valor da conta errada (Alguns grupos com tarifa proporcional
 *  que não estava levando em consideração a quantidade de economias)
 *
 * @author Sávio Luiz
 * @date 12/01/2011
 */
public class ExibirInserirDebitosContasComValorFaixasErradasAction extends GcomAction {

	 public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	    	ActionForward retorno = actionMapping.findForward("exibirInserirDebitosContas");       


	       	        
	        
	        return retorno;
	 }
	 
}
