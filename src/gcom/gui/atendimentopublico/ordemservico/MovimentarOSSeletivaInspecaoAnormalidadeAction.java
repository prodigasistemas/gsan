package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MovimentarOSSeletivaInspecaoAnormalidadeAction extends GcomAction {
   
    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a tela de sucesso
    	ActionForward retorno = actionMapping.findForward("telaSucesso");

    	//Cria uma instância da sessão
    	HttpSession sessao = httpServletRequest.getSession(false);
		
        MovimentarOSSeletivaInspecaoAnormalidadeActionForm form = (MovimentarOSSeletivaInspecaoAnormalidadeActionForm) actionForm;

    	
    	if (httpServletRequest.getAttribute("tipoMovimentacao") != null
    			&& !httpServletRequest.getAttribute("tipoMovimentacao").equals("")) {
    		// Monta página de sucesso
        	montarPaginaSucesso(httpServletRequest,
        			"Ordem(ns) de Serviço " + httpServletRequest.getAttribute("tipoMovimentacao") + " com sucesso!",
        			"Voltar",
        			"exibirMovimentarOSSeletivaInspecaoAnormalidadeAction.do?comando=" + form.getIdComando());
        	
//    	} else if (httpServletRequest.getAttribute("gerarRelatorioEmitirDocumentoVisitaCobranca") != null
//    			&& !httpServletRequest.getAttribute("gerarRelatorioEmitirDocumentoVisitaCobranca").equals("")) {
//
//			return actionMapping.findForward("gerarRelatorioEmitirDocumentoVisitaCobranca");
			
    	} else {
	    	//Monta página de sucesso
	    	montarPaginaSucesso(httpServletRequest, "Ordem de Serviço movimentada com sucesso!",
	    			"Voltar",
	    			"exibirMovimentarOSSeletivaInspecaoAnormalidadeAction.do?comando=" + form.getIdComando());
    	}

    	//Limpa a sessão depois de inserir os dados
        sessao.removeAttribute("grupo");
        sessao.removeAttribute("grupoFuncionalidades");

        //Retorna o mapeamento contido na variável "retorno" 
        return retorno;
    }

}
