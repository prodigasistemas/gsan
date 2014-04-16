package gcom.seguranca;

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
 * @author Rodrigo Cabral
 * @date 04/11/2010
 */

public class ExibirResultadoPesquisaConsultaCadastroCdlAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("exibirResultadoPesquisaConsultaCadastroCdlAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<ConsultaCdl> colecaoConsultaCdl = null;

		// Parte da verificação do filtro
        FiltroConsultaCadastroCdl filtroConsultaCadastroCdl = new FiltroConsultaCadastroCdl();
        
		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroConsultaCadastroCdl") != null) {
			filtroConsultaCadastroCdl = (FiltroConsultaCadastroCdl) 
				sessao.getAttribute("filtroConsultaCadastroCdl");
		}
		
		Map resultado = 
			controlarPaginacao( httpServletRequest, 
				retorno,	
				filtroConsultaCadastroCdl, 
				ConsultaCdl.class.getName());
		
		colecaoConsultaCdl = (Collection<ConsultaCdl>) resultado.get("colecaoRetorno");
		
		retorno = (ActionForward) resultado.get("destinoActionForward");

		//Nenhum registro encontrado				
		if (colecaoConsultaCdl == null || colecaoConsultaCdl.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado");
		}

		FiltrarConsultaCadastroCdlActionForm form =  new FiltrarConsultaCadastroCdlActionForm();
		
		httpServletRequest.setAttribute("colecaoConsultaCdl",colecaoConsultaCdl);
		httpServletRequest.setAttribute("periodoAcessoInicial",form.getPeriodoAcessoInicial());
		httpServletRequest.setAttribute("periodoAcessoFinal",form.getPeriodoAcessoFinal());
		
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
	}  
	
}
