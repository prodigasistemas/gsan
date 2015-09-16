package gcom.gui.relatorio.financeiro;

import gcom.arrecadacao.FiltroResumoArrecadacao;
import gcom.arrecadacao.ResumoArrecadacao;
import gcom.fachada.Fachada;
import gcom.financeiro.ContaAReceberContabil;
import gcom.financeiro.FiltroContaAReceberContabil;
import gcom.financeiro.FiltroResumoFaturamento;
import gcom.financeiro.ResumoFaturamento;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.financeiro.RelatorioParametrosContabeisArrecadacao;
import gcom.relatorio.financeiro.RelatorioParametrosContabeisContasAReceber;
import gcom.relatorio.financeiro.RelatorioParametrosContabeisFaturamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Geração do relatório [UC0824] Gerar Relatório dos Parâmetros Contábeis
 * 
 * @author Bruno Barros
 * @data 07/07/2008
 */

public class GerarRelatorioParametrosContabeisAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

        ActionForward retorno;
        GerarRelatorioParametrosContabeisActionForm form = (GerarRelatorioParametrosContabeisActionForm) actionForm;

        String referenciaContabil = form.getReferenciaContabil();
        
        // Verificar tipo de relatório
        if (form.getSelecaoRelatorio() == null || form.getSelecaoRelatorio().equalsIgnoreCase("")) {
          throw new ActionServletException("atencao.required", null, "Tipo de Relatório");
        }
        
        //[FS0001] - Validar Referencia Contabil
        String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio"); 
        String selecaoRelatorio = httpServletRequest.getParameter("selecaoRelatorio");    
        
        validarRefereciaContabil( form, selecaoRelatorio );        
        
		TarefaRelatorio relatorio;
        
		// Verificamos qual o tipo do relatorio a ser chamado se o de faturamento ou o de arrecadação ou de contas a receber
		if ( selecaoRelatorio.equals( GerarRelatorioParametrosContabeisActionForm.SELECAO_RELATORIO_FATURAMENTO ) ){
			relatorio = new RelatorioParametrosContabeisFaturamento( (Usuario)( httpServletRequest.getSession(false)).getAttribute("usuarioLogado") );
		} else if ( selecaoRelatorio.equals( GerarRelatorioParametrosContabeisActionForm.SELECAO_RELATORIO_CONTAS_A_RECEBER ) ){
			relatorio = new RelatorioParametrosContabeisContasAReceber( (Usuario)( httpServletRequest.getSession(false)).getAttribute("usuarioLogado") );
		} else {
			relatorio = new RelatorioParametrosContabeisArrecadacao( (Usuario)( httpServletRequest.getSession(false)).getAttribute("usuarioLogado") );
		}			
		
        relatorio.addParametro("referenciaContabil", referenciaContabil);
       
        
        if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoRelatorio", Integer.parseInt( tipoRelatorio ) );
        relatorio.addParametro("selecaoRelatorio",  selecaoRelatorio );
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;        
	}
	
	// FS00001 validar Referencia Contabil
	private void validarRefereciaContabil( GerarRelatorioParametrosContabeisActionForm form, String selecaoRelatorio ) throws ActionServletException{
		
		Fachada fachada = Fachada.getInstancia();
		
	
		// Verificamos se foi selecionado faturamento ou arrecadação ou contas a receber
		if ( form.getSelecaoRelatorio().equals( GerarRelatorioParametrosContabeisActionForm.SELECAO_RELATORIO_FATURAMENTO ) || selecaoRelatorio.equals(GerarRelatorioParametrosContabeisActionForm.SELECAO_RELATORIO_FATURAMENTO ) ){
            if ( form.getReferenciaContabil() != null && !form.getReferenciaContabil().equals( "" ) ) {
    			// Validamos se existe o ano mes de referencia na tabela de resumo de faturamento
    			FiltroResumoFaturamento filtro = new FiltroResumoFaturamento();
    			filtro.adicionarParametro( new ParametroSimples( FiltroResumoFaturamento.ANO_MES_REFERENCIA, Util.formatarMesAnoParaAnoMesSemBarra( form.getReferenciaContabil() ) ) );
    			Collection<ResumoFaturamento> colResumo = fachada.pesquisar( filtro, 1, ResumoFaturamento.class.getName() );
    			
    			if ( colResumo == null || colResumo.size() == 0 ){
    				throw new ActionServletException( "atencao.ano_mes_referencia_faturamento_inexistente", null, form.getReferenciaContabil() );
    			}
            }

		} else if ( form.getSelecaoRelatorio().equals( GerarRelatorioParametrosContabeisActionForm.SELECAO_RELATORIO_CONTAS_A_RECEBER) || selecaoRelatorio.equals(GerarRelatorioParametrosContabeisActionForm.SELECAO_RELATORIO_CONTAS_A_RECEBER ) ){
            if ( form.getReferenciaContabil() != null && !form.getReferenciaContabil().equals( "" ) ) {
        	     // Validamos se existe o ano mes de referencia na tabela de contas a receber
        		 FiltroContaAReceberContabil filtro = new FiltroContaAReceberContabil();
        		 filtro.adicionarParametro( new ParametroSimples( FiltroContaAReceberContabil.ANO_MES_REFERENCIA, Util.formatarMesAnoParaAnoMesSemBarra( form.getReferenciaContabil() ) ) );
        		 Collection<ContaAReceberContabil> colResumo = fachada.pesquisar( filtro, 1, ContaAReceberContabil.class.getName() );
        			
        		 if ( colResumo == null || colResumo.size() == 0 ){
        		 	throw new ActionServletException( "atencao.ano_mes_referencia_contas_a_receber_inexistente", null, form.getReferenciaContabil() );
        		 }
            }            
            
            
            
		} else {
			// Validamos se existe o ano mes de referencia na tabela de resumo de arrecadacao
            if ( form.getReferenciaContabil() != null && !form.getReferenciaContabil().equals( "" ) ) {
    			FiltroResumoArrecadacao filtro = new FiltroResumoArrecadacao();
    			filtro.adicionarParametro( new ParametroSimples( FiltroResumoArrecadacao.ANO_MES_REFERENCIA, Util.formatarMesAnoParaAnoMesSemBarra( form.getReferenciaContabil() ) ) );
    			Collection<ResumoArrecadacao> colResumo = fachada.pesquisar( filtro, 1, ResumoArrecadacao.class.getName() );
    			
    			if ( colResumo == null || colResumo.size() == 0 ){
    				throw new ActionServletException( "atencao.ano_mes_referencia_arrecadacao_inexistente", null, form.getReferenciaContabil() );
    			}
            }			
		}		
	}	
}
