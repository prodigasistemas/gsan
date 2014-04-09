package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1019] - Filtrar Ordens de Repavimentacao em Processo de Aceite;
 *  
 * Realiza o filtro de Ordens de Repavimentação em Processo de Aceite de acordo com os parâmetros informados.
 * 
 * @author Hugo Leonardo
 * @created 14/05/2010
 */
public class FiltrarOrdemRepavimentacaoProcessoAceiteAction extends GcomAction {
	/**
	 * 
	 * @author Hugo Leonardo
	 * @date 14/05/2010
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

    	Fachada fachada = Fachada.getInstancia();
    	
        ActionForward retorno = actionMapping.findForward("retornarFiltroOrdemRepavimentacaoProcessoAceite");
        
        FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form = (FiltrarOrdemRepavimentacaoProcessoAceiteActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        
        String idUnidadeResponsavel = null;
		if ( form.getIdUnidadeResponsavel() != null && !form.getIdUnidadeResponsavel().equals("-1") ) {			
			
			idUnidadeResponsavel= form.getIdUnidadeResponsavel();
		} else {
			throw new ActionServletException("atencao.required", null,
					"Unidade Responsavel");
		}
     
		String situacaoAceite = null;
		if (!form.getSituacaoAceite().equals("-1") && form.getSituacaoAceite() != null) {			
			situacaoAceite= form.getSituacaoAceite();
		} else {
				throw new ActionServletException("atencao.required", null,
						"Situação Retorno");
		}
		
		String periodoAceiteServicoInicial = null;
		String periodoAceiteServicoFinal = null;
		
		String periodoRetornoServicoInicial = null;
		String periodoRetornoServicoFinal = null;
		
		if(form.getPeriodoAceiteServicoInicial() != null && !form.getPeriodoAceiteServicoInicial().equals("") 
				&& form.getPeriodoAceiteServicoFinal() != null && !form.getPeriodoAceiteServicoFinal().equals("") ){				
			periodoAceiteServicoInicial= form.getPeriodoAceiteServicoInicial();						
			periodoAceiteServicoFinal= form.getPeriodoAceiteServicoFinal();	
				
		}else if(form.getPeriodoRetornoServicoInicial()!= null && !form.getPeriodoRetornoServicoInicial().equals("") 
				&& form.getPeriodoRetornoServicoFinal() != null && !form.getPeriodoRetornoServicoFinal().equals("")){
			
			periodoRetornoServicoInicial= form.getPeriodoRetornoServicoInicial();		
			periodoRetornoServicoFinal= form.getPeriodoRetornoServicoFinal();		
		}
		
		Date pRetornoServicoInicial = null;
		Date pRetornoServicoFinal = null; 
	
		if(periodoAceiteServicoInicial == null && periodoAceiteServicoFinal == null){
			
			if(periodoRetornoServicoInicial != null && periodoRetornoServicoFinal != null){
	        	
	        	String inicio = Util.formatarData(periodoRetornoServicoInicial);
			    String fim = Util.formatarData(periodoRetornoServicoFinal);
			    
			    pRetornoServicoInicial = Util.converteStringParaDate(periodoRetornoServicoInicial);
			    pRetornoServicoFinal = Util.converteStringParaDate(periodoRetornoServicoFinal);
			    
			    //Se data inicio maior que data fim
	    		if(Util.compararData(pRetornoServicoInicial, pRetornoServicoFinal) == 1){
	    			 
	    			throw new ActionServletException(
	    					"atencao.data_inicial_maior_data_final", new Exception(),inicio,fim);
	    		}	
	    				
				Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
				
				if(Util.compararData(pRetornoServicoInicial, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null, dataAtual);					
				}	
				
				if(Util.compararData(pRetornoServicoFinal, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null, dataAtual);					
				}	
			}
		}
		
		Date pEncerramentoOsInicial = null;
		Date pEncerramentoOsFinal = null; 
	
		if(periodoRetornoServicoInicial == null && periodoRetornoServicoFinal == null){
			
			if(periodoAceiteServicoInicial != null && periodoAceiteServicoFinal != null){
	        	
	        	String inicio = Util.formatarData(periodoAceiteServicoInicial);
			    String fim = Util.formatarData(periodoAceiteServicoFinal);
			    
			    pEncerramentoOsInicial = Util.converteStringParaDate(periodoAceiteServicoInicial);
			    pEncerramentoOsFinal = Util.converteStringParaDate(periodoAceiteServicoFinal);
			    
			    //Se data inicio maior que data fim
	    		if(Util.compararData(pEncerramentoOsInicial, pEncerramentoOsFinal) == 1){
	    			 
	    			throw new ActionServletException(
	    					"atencao.data_inicial_maior_data_final", new Exception(),inicio,fim);
	    		}		
	    		
				Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
				
				if(Util.compararData(pEncerramentoOsInicial, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null, dataAtual);					
				}	
				
				if(Util.compararData(pEncerramentoOsFinal, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null, dataAtual);					
				}
	    		
			}else{
				pEncerramentoOsInicial = Util.formatarDataSemHora( new Date());
				pEncerramentoOsFinal =  Util.formatarDataSemHora( new Date()); 
			}
			
		}
		
		Integer idUnidadeOrganizacional = null;
		
		
		if(form.getIdUnidadeOrganizacional() != null && !form.getIdUnidadeOrganizacional().equals("")) {				
			idUnidadeOrganizacional = new Integer(form.getIdUnidadeOrganizacional());	
				
		}
		
		OrdemRepavimentacaoProcessoAceiteHelper ordemRepavimentacaoProcessoAceiteHelper = new OrdemRepavimentacaoProcessoAceiteHelper();
		
		ordemRepavimentacaoProcessoAceiteHelper.setIdUnidadeResponsavel(new Integer(idUnidadeResponsavel));
		
		if (situacaoAceite != null && situacaoAceite.equals("TODAS") ) {
			situacaoAceite = "4";
		}
		ordemRepavimentacaoProcessoAceiteHelper.setSituacaoAceite(new Integer(situacaoAceite));
		
		ordemRepavimentacaoProcessoAceiteHelper.setPeriodoAceiteServicoInicial(periodoAceiteServicoInicial);
		ordemRepavimentacaoProcessoAceiteHelper.setPeriodoAceiteServicoFinal(periodoAceiteServicoFinal);	
		
		ordemRepavimentacaoProcessoAceiteHelper.setPeriodoRetornoServicoInicial(periodoRetornoServicoInicial);
		ordemRepavimentacaoProcessoAceiteHelper.setPeriodoRetornoServicoFinal(periodoRetornoServicoFinal);		
		
		ordemRepavimentacaoProcessoAceiteHelper.setIdUnidadeOrganizacional(idUnidadeOrganizacional);
		
		Integer totalOrdemRepavimentacaoProcessoAceite = fachada.
			pesquisarOrdemRepavimentacaoProcessoAceiteCount(ordemRepavimentacaoProcessoAceiteHelper);
		
		if(totalOrdemRepavimentacaoProcessoAceite > 1000){
			throw new ActionServletException(
					"atencao.pesquisa.muitosregistros", null,"");	
		}
		
		sessao.setAttribute("osPavimentoAceiteHelper",ordemRepavimentacaoProcessoAceiteHelper);
		
       return retorno;
    }
   
}
 
