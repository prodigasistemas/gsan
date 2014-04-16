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
 * Classe responável pelo processamento inicial do cadastro de imóvel 
 *
 * @author Raphael Rossiter
 * @date 11/05/2009
 */
public class ExibirInserirImovelAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirInserirImovelAction");

        HttpSession sessao = httpServletRequest.getSession(false);

        //limpa a sessão
        sessao.removeAttribute("InserirImovelActionForm");
        sessao.removeAttribute("colecaoEnderecos");
        sessao.removeAttribute("imovelClientesNovos");
        sessao.removeAttribute("imoveisPrincipal");
        sessao.removeAttribute("colecaoImovelSubCategorias");

        //GERENCIADOR DE ABAS
        StatusWizard statusWizard = new StatusWizard(
        "inserirImovelWizardAction", "inserirImovelAction",
        "cancelarInserirImovelAction","exibirInserirImovelAction.do");
        
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "LocalidadeA.gif", "LocalidadeD.gif",
                        "exibirInserirImovelLocalidadeAction",
                        "inserirImovelLocalidadeAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "EnderecoA.gif", "EnderecoD.gif",
                        "exibirInserirImovelEnderecoAction",
                        "inserirImovelEnderecoAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        3, "ClienteA.gif", "ClienteD.gif",
                        "exibirInserirImovelClienteAction",
                        "inserirImovelClienteAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        4, "SubcategoriaA.gif", "SubcategoriaD.gif",
                        "exibirInserirImovelSubCategoriaAction",
                        "inserirImovelSubCategoriaAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        5, "CaracteristicaA.gif", "CaracteristicaD.gif",
                        "exibirInserirImovelCaracteristicasAction",
                        "inserirImovelCaracteristicasAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        6, "ConclusaoA.gif", "ConclusaoD.gif",
                        "exibirInserirImovelConclusaoAction",
                        "inserirImovelConclusaoAction"));

        sessao.setAttribute("statusWizard", statusWizard);
        
        return retorno;
    }

}
