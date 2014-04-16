package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetificarContasPagasSemDebitoCreditoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        
        
        Fachada fachada = Fachada.getInstancia();
        
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    	
    	Collection colecaoContasRetificar = (Collection)sessao.getAttribute("colecaoContasPagas");
    	
    	// Verifica se existe contas para retificar
    	Collection qtdContasRetificadas = new ArrayList();
    	if(colecaoContasRetificar != null && !colecaoContasRetificar.equals("") && colecaoContasRetificar.size() > 0){
    		qtdContasRetificadas = fachada.retificarContasPagasSemDebitoCredito(colecaoContasRetificar,usuarioLogado);
    	}else{
    		throw new ActionServletException("atencao.senha.invalida",
					null,"Não existe contas para serem retificadas ");	
    	}
    	
    	 montarPaginaSucesso(httpServletRequest, qtdContasRetificadas.size()
                 + " Contas Retificadas com sucesso. ",
                 "Realizar outra Retificação de contas", "exibirRetificarContasPagasSemDebitoCreditoAction.do");
        
        
        return retorno;
	}
	
	
	
}
