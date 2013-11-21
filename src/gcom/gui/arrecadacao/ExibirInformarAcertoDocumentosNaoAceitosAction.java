package gcom.gui.arrecadacao;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por configurar todo o processo de informar acerto documentos não aceitos
 * [UC1214] Informar Acerto Documentos Não Aceitos
 * 
 * @author 	Mariana Victor
 * @created	18/08/2011
 */
public class ExibirInformarAcertoDocumentosNaoAceitosAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        //Localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping.findForward("informarAcertoDocumentosNaoAceitos");


        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Limpa a sessão
        sessao.removeAttribute("colecaoFormaArrecadacao");
        sessao.removeAttribute("PagamentoActionForm");
        sessao.removeAttribute("colecaoDocumentoTipo");
        sessao.removeAttribute("colecaoPagamentos");
        sessao.removeAttribute("colecaoInserirPagamentoViaCanetaHelper");
        

        //Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard(
                "informarAcertoDocumentosNaoAceitosWizardAction", "informarAcertoDocumentosNaoAceitosAction",
                "cancelarInformarAcertoDocumentosNaoAceitosAction","exibirInformarAcertoDocumentosNaoAceitosAction.do");
        
        //monta a primeira aba do processo
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "abaPagamentoA.gif", "abaPagamentoB.gif",
                        "exibirInformarAcertoDocumentosNaoAceitosPagamentoAction",
                        "informarAcertoDocumentosNaoAceitosPagamentoAction"));
        
        //monta a segunda aba do processo,se for leitura do código de barra por caneta
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "abaDebitoA.gif", "abaDebitoB.gif",
                        "exibirInformarAcertoDocumentosNaoAceitosDebitosAction",
                        "informarAcertoDocumentosNaoAceitosDebitosAction"));
        
        
        //manda o statusWizard para a sessão
        sessao.setAttribute("statusWizard", statusWizard);

        //retorna o mapeamento contido na variável retorno
        return retorno;
    }

}
