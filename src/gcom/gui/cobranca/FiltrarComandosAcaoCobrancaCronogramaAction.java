package gcom.gui.cobranca;

import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de ação de cobrança 
 * [UC0326] Filtrar Comandos de Ação de Cobrança - Cronograma
 * @author Rafael Santos
 * @since 08/05/2006
 */
public class FiltrarComandosAcaoCobrancaCronogramaAction  extends GcomAction{
	
	
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

        ActionForward retorno = actionMapping.findForward("retornarComandosAcaoCobrancaCronograma");
        
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
        
        FiltrarComandosAcaoCobrancaCronogramaActionForm filtrarComandosAcaoCobrancaCronogramaActionForm = (FiltrarComandosAcaoCobrancaCronogramaActionForm)actionForm; 

        FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = 
        	fachada.construirFiltroCobrancaAcaoAtividadeCronograma(
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getPeriodoReferenciaCobrancaInicial(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getPeriodoReferenciaCobrancaFinal(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getGrupoCobranca(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getAcaoCobranca(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getAtividadeCobranca(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getPeriodoPrevisaoComandoInicial(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getPeriodoPrevisaoComandoFinal(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getPeriodoComandoInicial(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getPeriodoComandoFinal(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getPeriodoRealizacaoComandoInicial(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getPeriodoRealizacaoComandoFinal(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getIntervaloValorDocumentosInicial(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getIntervaloValorDocumentosFinal(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getIntervaloQuantidadeDocumentosInicial(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getIntervaloQuantidadeDocumentosFinal(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getIntervaloQuantidadeItensDocumentosInicial(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getIntervaloQuantidadeItensDocumentosFinal(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getSituacaoCronograma(),
        			filtrarComandosAcaoCobrancaCronogramaActionForm.getSituacaoComando());
       
        sessao.setAttribute("filtroCobrancaAcaoAtividadeCronograma",
        		filtroCobrancaAcaoAtividadeCronograma);
        
        sessao.setAttribute("filtrarComandosAcaoCobrancaCronogramaActionForm",filtrarComandosAcaoCobrancaCronogramaActionForm);
        
        return retorno;
    }

}
