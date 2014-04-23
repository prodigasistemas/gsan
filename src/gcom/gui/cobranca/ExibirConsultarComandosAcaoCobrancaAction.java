package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de ação de cobrança 
 * [UC0325] Consultar Comandos de Ação de Conbrança
 * @author Rafael Santos
 * @since 08/05/2006
 */
public class ExibirConsultarComandosAcaoCobrancaAction  extends GcomAction{
	
	
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirConsultarComandosAcaoCobranca");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        ConsultarComandosAcaoCobrancaCronogramaActionForm consultarComandosAcaoCobrancaCronogramaActionForm = (ConsultarComandosAcaoCobrancaCronogramaActionForm) actionForm;
        
        if(consultarComandosAcaoCobrancaCronogramaActionForm.getTipoComando() == null){
        	consultarComandosAcaoCobrancaCronogramaActionForm.setTipoComando("Cronograma");
        }
        
		String carregando = httpServletRequest.getParameter("carregando");
		if(carregando != null && !carregando.equals("")){
			if (sessao.getAttribute("consultarComandosAcaoCobrancaCronogramaActionForm") != null) {
				
				ConsultarComandosAcaoCobrancaCronogramaActionForm consultarComandosAcaoCobrancaCronogramaActionFormRecarregar =
		        	(ConsultarComandosAcaoCobrancaCronogramaActionForm) sessao.getAttribute("consultarComandosAcaoCobrancaCronogramaActionForm");

					consultarComandosAcaoCobrancaCronogramaActionForm.setTipoComando(consultarComandosAcaoCobrancaCronogramaActionFormRecarregar.getTipoComando());
				
			}
			
		}
		
		sessao.setAttribute("consultarComandosAcaoCobrancaCronogramaActionForm",consultarComandosAcaoCobrancaCronogramaActionForm);
		
		
        return retorno;
    }

}
