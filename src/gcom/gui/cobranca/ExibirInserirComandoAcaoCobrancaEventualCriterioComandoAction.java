package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de Ação de Conbrança - Tipo de Comando Cronograma
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class ExibirInserirComandoAcaoCobrancaEventualCriterioComandoAction  extends GcomAction{
	
	
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
                .findForward("exibirInserirComandoAcaoCobrancaEventualCriterioComando");

        InserirComandoAcaoCobrancaEventualCriterioComandoActionForm inserirComandoAcaoCobrancaEventualCriterioComandoActionForm = (InserirComandoAcaoCobrancaEventualCriterioComandoActionForm) actionForm;        
        
        //Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
        String idCobrancaAtividade = httpServletRequest.getParameter("idCobrancaAtividade");
        String idCobrancaAcao = httpServletRequest.getParameter("idCobrancaAcao");
        
		String anoMesContaInicial = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoInicialConta(); 
		String anoMesContaFinal = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoFinalConta();
		
		String anoMesVencimentoInicial = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoVencimentoContaInicial();  
		String anoMesVencimentoFinal  = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoVencimentoContaFinal();

		
		String idComandoSelecionado = httpServletRequest.getParameter("idComandoSelecionado");
		if(idComandoSelecionado != null){
			sessao.setAttribute("idCobrancaCriterio",idComandoSelecionado);
		}		
		
		//[FS0012] - Verificar referência final menor que referência inicial
		if( (anoMesContaInicial != null && !anoMesContaInicial.equals("")) && (anoMesContaFinal != null && !anoMesContaFinal.equals("")) ){
			
			String anoInicial = anoMesContaInicial.substring(3,7); 
			String mesInicial = anoMesContaInicial.substring(0,2);
			
			String anoFinal = anoMesContaFinal.substring(3,7);
			String mesFinal = anoMesContaFinal.substring(0,2);
			
			boolean valida = Util.validarAnoMes(anoMesContaInicial);
			if(valida){
				throw new ActionServletException(
                "errors.invalid",null,"Período Inicial da Conta");			
			}
			
			valida = Util.validarAnoMes(anoMesContaFinal);
			if(valida){
				throw new ActionServletException(
                "errors.invalid",null,"Período Final da Conta");				
			}			
			
			Calendar periodoInicial = new GregorianCalendar();
			periodoInicial.set(Calendar.DATE,1);	
			periodoInicial.set(Calendar.MONTH,(new Integer(mesInicial).intValue()+1));
			periodoInicial.set(Calendar.YEAR,new Integer(anoInicial).intValue());
			
			Calendar periodoFinal = new GregorianCalendar();
			periodoFinal.set(Calendar.DATE,1);	
			periodoFinal.set(Calendar.MONTH,(new Integer(mesFinal).intValue()+1));
			periodoFinal.set(Calendar.YEAR,new Integer(anoFinal).intValue());
			
			if(periodoInicial.compareTo(periodoFinal) > 0){
	        	throw new ActionServletException(//Referência Final do Período  é anterior à Referência Inicial do Período
	                    "atencao.referencia_inicial.maior.referencia_final");				
			}
		}

		//[FS0014] - Verificar data final menos que data inicial 
		if( (anoMesVencimentoInicial != null && !anoMesVencimentoInicial.equals("")) && (anoMesVencimentoFinal != null && !anoMesVencimentoFinal.equals("")) ){
			
			String anoInicial = anoMesVencimentoInicial.substring(6,10); 
			String mesInicial = anoMesVencimentoInicial.substring(3,5);
			String diaInicial = anoMesVencimentoInicial.substring(0,2);
			
			String anoFinal = anoMesVencimentoFinal.substring(6,10);
			String mesFinal = anoMesVencimentoFinal.substring(3,5);
			String diaFinal = anoMesVencimentoInicial.substring(0,2);
			
			boolean valida = Util.validarDiaMesAno(anoMesVencimentoInicial);
			if(valida){
				throw new ActionServletException(
                "errors.invalid",null,"Período Inicial do Vencimento da Conta");				
			}
			valida = Util.validarDiaMesAno(anoMesVencimentoFinal);
			if(valida){
				throw new ActionServletException(
                "errors.invalid",null,"Período Final do Vencimento da Conta");				
			}			
			
			Calendar periodoInicial = new GregorianCalendar();
			periodoInicial.set(Calendar.DATE,new Integer(diaInicial).intValue());	
			periodoInicial.set(Calendar.MONTH,(new Integer(mesInicial).intValue()+1));
			periodoInicial.set(Calendar.YEAR,new Integer(anoInicial).intValue());
			
			Calendar periodoFinal = new GregorianCalendar();
			periodoFinal.set(Calendar.DATE,new Integer(diaFinal).intValue());	
			periodoFinal.set(Calendar.MONTH,(new Integer(mesFinal).intValue()+1));
			periodoFinal.set(Calendar.YEAR,new Integer(anoFinal).intValue());
			
			if(periodoInicial.compareTo(periodoFinal) > 0){
	        	throw new ActionServletException(//Data Final do Período é anterior  à Data Inicial do Período
	                    "atencao.data_inicial.maior.data_final");				
			}
		}
        
        //[SB0003] - Selecionar Cretério do Comando
        //pesquisar cobranca atividade
        if(idCobrancaAtividade != null && !idCobrancaAtividade.equals("")){
        	FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
        	filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.ID,idCobrancaAtividade));
        	
        	Collection colecaoCobrancaAtividade = fachada.pesquisar(filtroCobrancaAtividade,CobrancaAtividade.class.getName());

        	if(colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()){
        		
        		CobrancaAtividade cobrancaAtividade = (CobrancaAtividade)colecaoCobrancaAtividade.iterator().next();
        		if(cobrancaAtividade.getIndicadorExecucao().intValue() == 1){
        			httpServletRequest.setAttribute("habilitarExecutar","true");
        		}else{
        			httpServletRequest.setAttribute("habilitarExecutar","false");
        		}
        	}
        	
        }
        
        
        
        //pesquisar cobranca ação
        if(idCobrancaAcao != null && !idCobrancaAcao.equals("")){
        	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
        	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID,idCobrancaAcao));
        	
        	Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao,CobrancaAcao.class.getName());

        	if(colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()){
        		
        		CobrancaAcao cobrancaAcao = (CobrancaAcao)colecaoCobrancaAcao.iterator().next();
        		inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.setDescricaoAcaoCobranca(cobrancaAcao.getDescricaoCobrancaAcao());
        	}
        
        	FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
        	//filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID_COBRANCA_ACAO,idCobrancaAcao));
        	
        	Collection colecaoCriterioCobranca = null;
        	colecaoCriterioCobranca = fachada.pesquisar(filtroCobrancaCriterio,CobrancaCriterio.class.getName());
        	
        	if(colecaoCriterioCobranca == null || colecaoCriterioCobranca.isEmpty()){
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
						null, "Tabela Cobrança Critério");
        	}
        	
        	sessao.setAttribute("colecaoCriterioCobranca",colecaoCriterioCobranca);
        }
        
        //carregar criterios de cobranca
        
        if(sessao.getAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm") == null)
        		sessao.setAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm",
        				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm);
        return retorno;
    }

}
