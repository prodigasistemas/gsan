package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
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
 * [UC0325] Consultar Comandos de Ação de Cobrança - Resultado de Cronograma
 * @author Rafael Santos
 * @since 10/05/2006
 */
public class ExibirResultadoConsultarComandosAcaoCobrancaCronogramaAction  extends GcomAction{
	
	
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

        ActionForward retorno = actionMapping.findForward("exibirComandosAcaoCobrancaCronograma");
        
		//Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
        
		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma;
        if (sessao.getAttribute("filtroCobrancaAcaoAtividadeCronograma") != null){
        	filtroCobrancaAcaoAtividadeCronograma = (FiltroCobrancaAcaoAtividadeCronograma) sessao.getAttribute("filtroCobrancaAcaoAtividadeCronograma");
        	
    		Collection colecaoCobrancaAcaoAtividadeCronograma;
        	
            Map resultado = controlarPaginacao(httpServletRequest, retorno,
            		filtroCobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeCronograma.class.getName());
            colecaoCobrancaAcaoAtividadeCronograma = (Collection) resultado.get("colecaoRetorno");
           	retorno = (ActionForward) resultado.get("destinoActionForward");

      		if (colecaoCobrancaAcaoAtividadeCronograma == null
       				|| colecaoCobrancaAcaoAtividadeCronograma.isEmpty()) {
       			throw new ActionServletException("atencao.pesquisa.nenhumresultado",
       					null, "Comando Ação de Cobrança - Cronograma");
       		}
        	sessao.setAttribute("colecaoCobrancaAcaoAtividadeCronograma",colecaoCobrancaAcaoAtividadeCronograma);
        }
        
        return retorno;
    }

}
