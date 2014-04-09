package gcom.gui.cobranca;

import java.util.Calendar;
import java.util.GregorianCalendar;

import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de ação de cobrança 
 * [UC0326] Filtrar Comandos de Ação de Cobrança - Eventual
 * @author Rafael Santos
 * @since 12/05/2006
 */
public class FiltrarComandosAcaoCobrancaEventualAction  extends GcomAction{
	
	
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

        ActionForward retorno = actionMapping.findForward("retornarComandosAcaoCobrancaEventual");
        
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
        
        FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm = (FiltrarComandosAcaoCobrancaEventualActionForm)actionForm; 

        if(sessao.getAttribute("filtroCobrancaAcaoAtividadeComando") == null){
        	
        
	        if(!filtrarComandosAcaoCobrancaEventualActionForm.getIndicadorCriterio().equals("Comando")){
	        	filtrarComandosAcaoCobrancaEventualActionForm.setCriterioCobranca("");
	        }
	        
			if(httpServletRequest.getParameter("grupoCobranca")== null){
				filtrarComandosAcaoCobrancaEventualActionForm.setGrupoCobranca(null);	
			}
			if(httpServletRequest.getParameter("gerenciaRegional")== null){
				filtrarComandosAcaoCobrancaEventualActionForm.setGerenciaRegional(null);
			}
			if(httpServletRequest.getParameter("unidadeNegocio") == null){
				filtrarComandosAcaoCobrancaEventualActionForm.setUnidadeNegocio(null);
			}
			if(httpServletRequest.getParameter("clienteRelacaoTipo") == null){
				filtrarComandosAcaoCobrancaEventualActionForm.setClienteRelacaoTipo(null);
			}
			
			//[FS0014 - Validar período de emissão];
			String dataInicial = filtrarComandosAcaoCobrancaEventualActionForm.getDataEmissaoInicio();
			String dataFinal = filtrarComandosAcaoCobrancaEventualActionForm.getDataEmissaoFim();
		
			if ((dataInicial.trim().length() == 10)
					&& (dataFinal.trim().length() == 10)) {
				
				Calendar calendarInicio = new GregorianCalendar();
				Calendar calendarFim = new GregorianCalendar();
	            
	            calendarInicio.setTime( Util.converteStringParaDate( dataInicial ) );
	            calendarFim.setTime( Util.converteStringParaDate( dataFinal ) );

				if (calendarFim.compareTo(calendarInicio) < 0) {
					throw new ActionServletException(
							"atencao.data_fim_menor_inicio");
				}
			}
	        
	        
	        FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = 
	        	fachada.construirFiltroCobrancaAcaoAtividadeEventual(
	        			filtrarComandosAcaoCobrancaEventualActionForm.getGrupoCobranca(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getAcaoCobranca(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getAtividadeCobranca(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoReferenciaContasInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoReferenciaContasFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoComandoInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoComandoFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoRealizacaoComandoInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoRealizacaoComandoFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoVencimentoContasInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoVencimentoContasFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloValorDocumentosInicial(),        			
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloValorDocumentosFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloQuantidadeDocumentosInicial(),        			
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloQuantidadeDocumentosFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloQuantidadeItensDocumentosInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloQuantidadeItensDocumentosFinal(),        			
	        			filtrarComandosAcaoCobrancaEventualActionForm.getSituacaoComando(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIndicadorCriterio(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getGerenciaRegional(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getLocalidadeOrigemID(),        			
	        			filtrarComandosAcaoCobrancaEventualActionForm.getLocalidadeDestinoID(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getSetorComercialOrigemCD(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getSetorComercialDestinoCD(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getRotaInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getRotaFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIdCliente(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getClienteRelacaoTipo(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getCriterioCobranca(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getUnidadeNegocio(), 
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIdCobrancaAcaoAtividadeComando(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getDataEmissaoInicio(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getDataEmissaoFim(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getConsumoMedioInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getConsumoMedioFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getTipoConsumo(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoInicialFiscalizacao(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoFinalFiscalizacao(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getSituacaoFiscalizacao(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getNumeroQuadraInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getNumeroQuadraFinal());
	       
	       
	        sessao.setAttribute("filtroCobrancaAcaoAtividadeComando",
	        		filtroCobrancaAcaoAtividadeComando);
	        
	        sessao.setAttribute("filtrarComandosAcaoCobrancaEventualActionForm",filtrarComandosAcaoCobrancaEventualActionForm);    
        }
        
        return retorno;
    }

}
