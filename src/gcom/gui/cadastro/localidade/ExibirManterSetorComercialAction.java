package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterSetorComercialAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
//    	Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //PesquisarFiltrarSetorComercialActionForm pesquisarFiltrarSetorComercialActionForm = (PesquisarFiltrarSetorComercialActionForm) actionForm;
        
        ActionForward retorno = actionMapping.findForward("manterSetorComercial");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String atualizar = (String)sessao.getAttribute("indicadorAtualizar");


        FiltroSetorComercial filtroSetorComercial = (FiltroSetorComercial)sessao.getAttribute("filtroSetorComercial");
		
		//		Retorna Setor Comercial
        Collection colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                SetorComercial.class.getName());

        
        if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
	        if (atualizar != null && colecaoPesquisa.size() == 1){
	        	SetorComercial setorComercial = (SetorComercial) colecaoPesquisa.iterator().next();
	        	httpServletRequest.setAttribute("setorComercialID",
						setorComercial.getId());
	        	
	        	retorno = actionMapping
	            	.findForward("atualizarSetorComercial");
	        	
	        } else {
	        	retorno = actionMapping
                .findForward("manterSetorComercial");
	        	
	        	Collection setoresComercial = null;
	            //      Aciona o controle de paginação para que sejam pesquisados apenas
	    		// os registros que aparecem na página
	    		Map resultado = controlarPaginacao(httpServletRequest, retorno,
	    				filtroSetorComercial, SetorComercial.class.getName());
	    		setoresComercial = (Collection) resultado.get("colecaoRetorno");
	    		retorno = (ActionForward) resultado.get("destinoActionForward");

	    		if (setoresComercial == null || setoresComercial.isEmpty()) {
	    			// Nenhum cliente cadastrado
	    			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
	    		}
	    		// Manda o filtro pelo request para o ExibirManterClienteAction
	    		sessao.setAttribute("colecaoSetorComercial", setoresComercial);
	        }
        } else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
        if (httpServletRequest.getAttribute("voltar") == null){
			sessao.setAttribute("manter", "manter");
        }
        else
        {
    		sessao.removeAttribute("manter");
        }
        
        return retorno;
        
    }

}
