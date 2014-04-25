package gcom.gui.cadastro.imovel;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Wizard do [UC0472] Consultar Imóvel
 * 
 * @author Rafael Santos
 * @since 07/09/2006 
 */
public class ConsultarImovelWizardAction extends WizardAction {

    // MÉTODOS DE EXIBIÇÃO
    // =======================================================


    /**
     * Metodo da 1° Aba - Dados Cadastrais do Imóvel
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirConsultarImovelDadosCadastraisAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirConsultarImovelDadosCadastraisAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    /**
     * Metodo da 2° Aba - Dados Complementares do Imovel
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirConsultarImovelDadosComplementaresAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirConsultarImovelDadosComplementaresAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

     
    /**
     * Metodo da 3° Aba - Dados de Ligações, consumo e medição
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirConsultarImovelDadosAnaliseMedicaoConsumoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirConsultarImovelDadosAnaliseMedicaoConsumoAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    
    /**
     * Metodo da 4° Aba - Histórico de  Faturamento
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirConsultarImovelHistoricoFaturamentoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirConsultarImovelHistoricoFaturamentoAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }
     
    /**
     * Metodo da 5° Aba - Débitos do Imóvel
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirConsultarImovelDebitosAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirConsultarImovelDebitosAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

     
    /**
     * Metodo da 6° Aba - Pagamento do Imóvel
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirConsultarImovelPagamentosAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirConsultarImovelPagamentosAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }
    
    /**
     * Metodo da 7° Aba - Devoluções do Imóvel
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirConsultarImovelDevolucoesAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirConsultarImovelDevolucoesAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }
 
    /**
     * Metodo da 8° Aba - Dopcumento de Cobrança e ordens de Serviçoi de Cobrança Emitidos para o Imóvel
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirConsultarImovelDocumentosCobrancaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirConsultarImovelDocumentosCobrancaAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }
     
    /**
     * Metodo da 9° Aba - Parcelamento efetuados para o imóvel
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirConsultarImovelParcelamentosDebitosAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirConsultarImovelParcelamentosDebitosAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }    
     
    /**
     * Metodo da 10° Aba - Registros de atendimento e ordens de serviços associados ao imóvel
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward exibirConsultarImovelRegistroAtendimentoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        return new ExibirConsultarImovelRegistroAtendimentoAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }        
    
    
}
