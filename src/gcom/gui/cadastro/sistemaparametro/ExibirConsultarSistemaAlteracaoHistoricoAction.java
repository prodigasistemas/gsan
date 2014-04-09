package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.FiltroSistemaAlteracaoHistorico;
import gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *  [UC0502] CONSULTAR SISTEMA ALTERCAO HISTORICO
 * 
 * @author Kássia Albuquerque
 * @date 29/11/2006
 */

public class ExibirConsultarSistemaAlteracaoHistoricoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		
		ActionForward retorno = actionMapping.findForward("consultarSistemaAlteracaoHistorico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoSistemaAlteracaoHistorico = null;

		// Parte da verificação do filtro
		FiltroSistemaAlteracaoHistorico filtroSistemaAlteracaoHistorico = new FiltroSistemaAlteracaoHistorico();
		
		if (sessao.getAttribute("filtroSistemaAlteracaoHistorico") != null) {
			filtroSistemaAlteracaoHistorico = (FiltroSistemaAlteracaoHistorico) sessao.getAttribute("filtroSistemaAlteracaoHistorico");
			httpServletRequest.setAttribute("Filtrar","Filtrar");
			sessao.removeAttribute("filtroSistemaAlteracaoHistorico");
		}	
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("consultarSistemaAlteracaoHistorico"))) {
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroSistemaAlteracaoHistorico, SistemaAlteracaoHistorico.class.getName());
			colecaoSistemaAlteracaoHistorico = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			if (colecaoSistemaAlteracaoHistorico == null || colecaoSistemaAlteracaoHistorico.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
					}
				else{
					sessao.setAttribute("colecaoSistemaAlteracaoHistorico", colecaoSistemaAlteracaoHistorico);
				}
			}
		
		return retorno;
		
		
	} 
	
}
