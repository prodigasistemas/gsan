package gcom.gui.atendimentopublico;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 17/01/2007
 */
public class ExibirEmitirSegundaViaContaInternetAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirEmitirSegundaViaContaInternetAction");
		httpServletRequest.setAttribute("nomeAction","/emitirSegundaViaContaInternetAction");

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("menu") != null) {
			sessao.setAttribute("veioMenu", "sim");
		}
		
		if (httpServletRequest.getParameter("ehEmail") != null) {
			sessao.setAttribute("ehEmail",true);
		}else{
			sessao.removeAttribute("ehEmail");
		}

		if (httpServletRequest.getParameter("acessoGeral") != null) {
			
			sessao.setAttribute("acessoGeral", "sim");
			retorno = 
				actionMapping.findForward("exibirEmitirSegundaViaContaInternetAcessoGeralAction");
			
			httpServletRequest.setAttribute("nomeAction","/emitirSegundaViaContaInternetAcessoGeralAction");
		}

		SistemaParametro sistemaParametro = 
			Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		sessao.setAttribute("sistemaParametro",sistemaParametro);

		//alteracao feita para a caern
		if (sistemaParametro != null && 
			sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAERN")) {
			
			sessao.setAttribute("caern", "caern");
		} else {
			sessao.removeAttribute("caern");
		}
			
		//Colocado por Raphael Rossiter em 22/10/2008 - Analista: Rosana Carvalho
		httpServletRequest.setAttribute("indicadorDocumentoValido", 
				sistemaParametro.getIndicadorDocumentoValido().toString());
		
		return retorno;

	}
}
