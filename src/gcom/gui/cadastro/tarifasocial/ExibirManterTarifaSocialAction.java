package gcom.gui.cadastro.tarifasocial;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirManterTarifaSocialAction extends GcomAction {

    /**
     * < <Descrição do método>> 0
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
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        // localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping
                .findForward("exibirManterTarifaSocial");

        //obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //limpa a sessão
        sessao.removeAttribute("colecaoImovelEconomia");
        sessao.removeAttribute("clienteImovel");
        sessao.removeAttribute("quantEconomias");
        sessao.removeAttribute("colecaoDadosTarifaSocial");
        sessao.removeAttribute("colecaoImovelSubcategoria");
        sessao.removeAttribute("ManterTarifaSocialActionForm");

        StatusWizard statusWizard = new StatusWizard(
                "manterTarifaSocialWizardAction", "manterTarifaSocialAction",
                "cancelarManterTarifaSocialAction", "exibirManterTarifaSocialAction.do");
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "TarifaSocialImovelA.gif",
                        "TarifaSocialImovelD.gif",
                        "exibirManterTarifaSocialImovelAction",
                        "manterTarifaSocialImovelAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "TarifaSocialTarifaA.gif",
                        "TarifaSocialTarifaD.gif",
                        "exibirManterTarifaSocialDadosEconomiaAction",
                        "manterTarifaSocialDadosEconomiaAction"));

        //manda o statusWizard para a sessao
        sessao.setAttribute("statusWizard", statusWizard);

        return retorno;
    }
}
