package gcom.gui.micromedicao.hidrometro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroRetornoControleHidrometro;
import gcom.micromedicao.hidrometro.RetornoControleHidrometro;


/**
 *	MANTER RETORNO CONTROLE HIDROMETRO  * 
 * @author Wallace Thierre
 * Date: 03/08/2010
 * 
 */
public class ExibirManterRetornoControleHidrometroAction extends GcomAction{	
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
			HttpServletResponse httpServletResponse){
		
		// Seta o caminho do retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterRetornoControleHidrometro");		
		
		Collection colecaoRetornoControleHidrometro = new ArrayList();
		
		// Mudar isso quando for implementado o esquema de segurança
		HttpSession session = httpServletRequest.getSession(false);
		
		FiltroRetornoControleHidrometro filtroRetornoControleHidrometro = (FiltroRetornoControleHidrometro) 
				session.getAttribute("filtroRetornoControleHidrometro");
			
		if(filtroRetornoControleHidrometro != null && !filtroRetornoControleHidrometro.equals("")){
			
			filtroRetornoControleHidrometro.setCampoOrderBy
			(FiltroRetornoControleHidrometro.ID);
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno, 
					filtroRetornoControleHidrometro, RetornoControleHidrometro.class.getName());
			colecaoRetornoControleHidrometro = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");			
		}
		
		if(colecaoRetornoControleHidrometro!= null && !colecaoRetornoControleHidrometro.isEmpty()){
			
			if (colecaoRetornoControleHidrometro.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null 
							|| httpServletRequest.getParameter("page.offset").equals("1"))) {
				
				if(httpServletRequest.getParameter("indicadorAtualizar") != null){
					
					retorno = actionMapping.findForward("exibirAtualizarRetornoControleHidrometro");
					
					RetornoControleHidrometro retornoControleHidrometro = 
						(RetornoControleHidrometro) colecaoRetornoControleHidrometro.iterator().next();
					
					session.setAttribute("retornoControleHidrometro", retornoControleHidrometro);
					
				} else {
					
					httpServletRequest.setAttribute("colecaoRetornoControleHidrometro", colecaoRetornoControleHidrometro);
				}				
				
			} else {
				httpServletRequest.setAttribute("colecaoRetornoControleHidrometro", colecaoRetornoControleHidrometro);
			}
		}else {
			// Caso não haja nenhum resultado da pesquisa
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		
		}		
		
		return retorno;
	}
	
}
