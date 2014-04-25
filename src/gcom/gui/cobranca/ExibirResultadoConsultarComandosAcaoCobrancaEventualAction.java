package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
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
 * Permite consultar comandos de ação de cobrança 
 * [UC0325] Consultar Comandos de Ação de Cobrança - Resultado de Eventual
 * @author Rafael Santos
 * @since 10/05/2006
 */
public class ExibirResultadoConsultarComandosAcaoCobrancaEventualAction  extends GcomAction{
	
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirComandosAcaoCobrancaEventual");
        
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
        
		FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando;
        if (sessao.getAttribute("filtroCobrancaAcaoAtividadeComando") != null){
        	filtroCobrancaAcaoAtividadeComando = (FiltroCobrancaAcaoAtividadeComando) sessao.getAttribute("filtroCobrancaAcaoAtividadeComando");
        	
    		Collection colecaoCobrancaAcaoAtividadeComando;

    	     Map resultado = controlarPaginacao(httpServletRequest, retorno,
    	    		 filtroCobrancaAcaoAtividadeComando, CobrancaAcaoAtividadeComando.class.getName());
    	     
    	     colecaoCobrancaAcaoAtividadeComando = (Collection) resultado.get("colecaoRetorno");
    	     retorno = (ActionForward) resultado.get("destinoActionForward");
    		
    		
    		if (colecaoCobrancaAcaoAtividadeComando == null
    				|| colecaoCobrancaAcaoAtividadeComando.isEmpty()) {
    			
    			sessao.removeAttribute("filtroCobrancaAcaoAtividadeComando");
    			
    			throw new ActionServletException("atencao.pesquisa.nenhumresultado",
    					null, "Comando Ação de Cobrança - Eventual");
    		}
        	
        	sessao.setAttribute("colecaoCobrancaAcaoAtividadeEventual",colecaoCobrancaAcaoAtividadeComando);
        }
        
        return retorno;
    }

}
