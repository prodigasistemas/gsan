package gcom.gui.operacional;


import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import gcom.operacional.FiltroSistemaEsgoto;

import gcom.operacional.SistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;


/**			
 * @date 21/05/08
 * @author Arthur Carvalho
 */

public class ExibirFiltrarBaciaAction extends GcomAction {
	
	/*
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarBacia");

		Fachada fachada = Fachada.getInstancia();
		

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarBaciaActionForm filtrarBaciaActionForm = (FiltrarBaciaActionForm) actionForm;
		
		Collection colecaoSistemaEsgoto = new ArrayList();
		
	
		
	if (sessao.getAttribute("colecaoSistemaEsgoto") == null) {
		FiltroSistemaEsgoto filtroSistemaEsgoto= new FiltroSistemaEsgoto(FiltroSistemaEsgoto.DESCRICAO);
		filtroSistemaEsgoto.adicionarParametro(new ParametroSimples
				(FiltroSistemaEsgoto.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		
		colecaoSistemaEsgoto = fachada.pesquisar(
				filtroSistemaEsgoto, SistemaEsgoto.class.getName());
		
		sessao.setAttribute("colecaoSistemaEsgoto", colecaoSistemaEsgoto);
	}
		
		
		
		String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarBaciaActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarBaciaActionForm.getIndicadorAtualizar()==null){
			filtrarBaciaActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarBaciaActionForm.setId("");
        	filtrarBaciaActionForm.setDescricao("");
        	filtrarBaciaActionForm.setIndicadorUso("");
        	filtrarBaciaActionForm.setSistemaEsgoto("-1");
        	
        }
       return retorno;

	}

}
