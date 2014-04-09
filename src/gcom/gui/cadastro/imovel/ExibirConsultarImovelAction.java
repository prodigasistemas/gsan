package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0472] Consultar Imovel
 * @author Rafael Santos
 * @since 07/09/2006
 *
 */
public class ExibirConsultarImovelAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        // localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping
                .findForward("exibirConsultarImovelAction");

        //obtém a instância da sessão
        HttpSession sessao = getSessao(httpServletRequest);

        // Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"consultarImovelWizardAction", "exibirConsultarImovelAction",
				"cancelarConsultarImovelAction", 
				"",	
				"exibirConsultarImovelAction.do");        
	        
        statusWizard
            .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
            1, "DadosCadastraisPrimeiraAbaA.gif", "DadosCadastraisPrimeiraAbaD.gif",
            "exibirConsultarImovelDadosCadastraisAction",
            ""));
        statusWizard
            .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
            2, "DadosAdicionaisIntervaloAbaA.gif", "DadosAdicionaisIntervaloAbaD.gif",
            "exibirConsultarImovelDadosComplementaresAction",
            ""));

        statusWizard
        	.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
    		3, "AnaliseLigacaoConsumoIntervaloAbaA.gif", "AnaliseLigacaoConsumoIntervaloAbaD.gif",
        	"exibirConsultarImovelDadosAnaliseMedicaoConsumoAction",
            ""));
        
        statusWizard
    	    .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
    		4, "HistoricoFaturamentoIntervaloAbaA.gif", "HistoricoFaturamentoIntervaloAbaD.gif",
    	    "exibirConsultarImovelHistoricoFaturamentoAction",
            ""));

        statusWizard
	    	.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	    	5, "DebitosImovelIntervaloAbaA.gif", "DebitosImovelIntervaloAbaD.gif",
	        "exibirConsultarImovelDebitosAction",
            ""));
        
        statusWizard
    	    .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
    	    6, "PagamentosImovelIntervaloAbaA.gif", "PagamentosImovelIntervaloAbaD.gif",
            "exibirConsultarImovelPagamentosAction",
            ""));

        statusWizard
	    	.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	    	7, "DevolucoesImovelIntervaloAbaA.gif", "DevolucoesImovelIntervaloAbaD.gif",
            "exibirConsultarImovelDevolucoesAction",
            ""));

        statusWizard
    		.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
    	    8, "DocumentosCobracaIntervaloAbaA.gif", "DocumentosCobrancaIntervaloAbaD.gif",
            "exibirConsultarImovelDocumentosCobrancaAction",
            ""));

        statusWizard
			.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			9, "ParcelamentoIntervaloAbaA.gif", "ParcelamentoIntervaloAbaD.gif",
            "exibirConsultarImovelParcelamentosDebitosAction",
            ""));
        
        statusWizard
			.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			10, "RegistroAtendimentoUltimaAbaA.gif", "RegistroAtendimentoUltimaAbaD.gif",
			"exibirConsultarImovelRegistroAtendimentoAction",
			""));
        
        //manda o statusWizard para a sessao
        sessao.setAttribute("statusWizard", statusWizard);

        return retorno;
    }

}
