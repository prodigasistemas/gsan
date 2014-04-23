package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Cancela o Parcelamento dos Débitos
 * @author Roberta Costa
 * @since 06/03/2006
 */
public class CancelarEfetuarParcelamentoDebitosAction extends GcomAction {

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
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaPrincipal");

        //obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //limpa a sessão
        sessao.removeAttribute("colecaoContaValoresImovel");
        sessao.removeAttribute("valorTotalContaValoresImovel");
        sessao.removeAttribute("colecaoGuiaPagamentoValores");
        sessao.removeAttribute("valorAcrescimosImpontualidadeImovel");
        sessao.removeAttribute("colecaoDebitoACobrar");
        sessao.removeAttribute("colecaoCreditoARealizar");
        sessao.removeAttribute("valorDebitoTotalAtualizadoImovel");
        sessao.removeAttribute("calcula");
        sessao.removeAttribute("codigoImovelEscolhida");
        sessao.removeAttribute("dataParcelamentoEscolhida");
        sessao.removeAttribute("resolucaoDiretoriaEscolhida");
        sessao.removeAttribute("fimIntervaloParcelamentoEscolhida");
        sessao.removeAttribute("inicioIntervaloParcelamentoEscolhida");
        sessao.removeAttribute("indicadorContasRevisaoEscolhida");
        sessao.removeAttribute("indicadorGuiasPagamentoEscolhida");
        sessao.removeAttribute("indicadorAcrescimosImpotualidadeEscolhida");
        sessao.removeAttribute("indicadorDebitosACobrarEscolhida");
        sessao.removeAttribute("indicadorCreditoARealizarEscolhida");
        sessao.removeAttribute("colecaoContaValores");
        sessao.removeAttribute("colecaoOpcoesParcelamento");
        sessao.removeAttribute("statusWizard");

        return retorno;
    }
}
