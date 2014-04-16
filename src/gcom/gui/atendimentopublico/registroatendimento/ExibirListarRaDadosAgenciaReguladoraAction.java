package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRaDadosAgenciaReguladora;
import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora;
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
 * [UC0538] Filtrar RAs na Agencia Reguladora
 *
 * @author Kássia Albuquerque
 * @date 04/05/2007
 */

public class ExibirListarRaDadosAgenciaReguladoraAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
			ActionForward retorno = actionMapping.findForward("listarRaDadosAgenciaReguladora");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Collection colecaoRaDadosAgenciaReguladora = null;
	
	        FiltroRaDadosAgenciaReguladora filtroRaDadosAgenciaReguladora = null;	      
			
			if (sessao.getAttribute("filtroRaDadosAgenciaReguladora") != null) {
				filtroRaDadosAgenciaReguladora = (FiltroRaDadosAgenciaReguladora) sessao.getAttribute("filtroRaDadosAgenciaReguladora");
			}
			
	
			if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("listarRaDadosAgenciaReguladora"))) {
	
				Map resultado = controlarPaginacao(httpServletRequest, retorno,filtroRaDadosAgenciaReguladora, 
						RaDadosAgenciaReguladora.class.getName());
				colecaoRaDadosAgenciaReguladora = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
	
				// Nenhum registro encontrado	
				
				if (colecaoRaDadosAgenciaReguladora == null || colecaoRaDadosAgenciaReguladora.isEmpty()) {
					
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
				
				
				if (colecaoRaDadosAgenciaReguladora.size()== 1 ){

					retorno = actionMapping.findForward("exibirConsultarRaDadosAgenciaReguladora");
					
					RaDadosAgenciaReguladora raDadosAgenciaReguladora = (RaDadosAgenciaReguladora)
														colecaoRaDadosAgenciaReguladora.iterator().next();
					
					sessao.setAttribute("raDadosAgenciaReguladora", raDadosAgenciaReguladora);
					
				}else{
					sessao.setAttribute("colecaoRaDadosAgenciaReguladora", colecaoRaDadosAgenciaReguladora);
					
				}
	
			}
			
			
			return retorno;
		
		
	} 
	
}
