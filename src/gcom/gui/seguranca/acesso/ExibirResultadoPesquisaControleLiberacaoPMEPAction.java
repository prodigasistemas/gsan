package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action exibir, da tela do resultado da pesquisa de 
 * Controle de Liberação de Permissão Especial
 * 
 * 
 * @author Daniel Alves
 * @date 13/08/2010
 */
public class ExibirResultadoPesquisaControleLiberacaoPMEPAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("resultadoPesquisaControleLiberacaoPMEP");
		
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ResultadoPesquisaControleLiberacaoPMEPActionForm form = (ResultadoPesquisaControleLiberacaoPMEPActionForm)actionForm;	
		
		Collection colecaoResultadoPesquisaControleLiberacaoPMEP = (Collection)sessao.getAttribute("colecaoResultadoPesquisaControleLiberacaoPMEP");
		
		//sessao.removeAttribute("filtroControleLiberacaoPermissaoEspecial");
		
		if(colecaoResultadoPesquisaControleLiberacaoPMEP != null && !colecaoResultadoPesquisaControleLiberacaoPMEP.isEmpty()){
			
			form.setControles(colecaoResultadoPesquisaControleLiberacaoPMEP);
			
		}else{	
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		

		return retorno;
	}
}
