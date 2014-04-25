package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Sávio Luiz
 */
public class ExibirInserirUsuarioAction extends GcomAction {

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

        // localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping
                .findForward("inserirUsuario");

        //        GerenciadorPaginas gerenciadorPaginas = null;

        //obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        String idSolicitacaoAcesso = (String) httpServletRequest.getParameter("idRegistroCadastrar");
        
        if( idSolicitacaoAcesso != null ) {
        	httpServletRequest.setAttribute("idSolicitacaoAcesso", idSolicitacaoAcesso);
        }

        // obtem o gerenciador de paginas na sessão
        //        gerenciadorPaginas = (GerenciadorPaginas)
        // sessao.getAttribute("gerenciadorPaginas");

        //limpa a sessão
        sessao.removeAttribute("usuarioCadastrar");
        sessao.removeAttribute("grupo");
        sessao.removeAttribute("collEmpresa");
        sessao.removeAttribute("collUsuarioTipo");
        sessao.removeAttribute("collUsuarioAbrangencia");
        sessao.removeAttribute("collGrupo");
        sessao.removeAttribute("collGerenciaRegional");
        sessao.removeAttribute("collUnidadeNegocio");
        
        

        //Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard(
                "inserirUsuarioWizardAction", "concluirInserirUsuarioAction",
                "cancelarInserirUsuarioAction","exibirInserirUsuarioAction.do");
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "DadosGeraisPrimeiraAbaA.gif", "DadosGeraisPrimeiraAbaD.gif",
                        "exibirInserirUsuarioDadosGeraisAction",
                        "inserirUsuarioDadosGeraisAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "AcessosUsuarioUltimaAbaA.gif", "AcessosUsuarioUltimaAbaD.gif",
                        "exibirInserirUsuarioAcessosUsuarioAction",
                        "inserirUsuarioAcessosUsuarioAction"));
      

        //manda o statusWizard para a sessao
        sessao.setAttribute("statusWizard", statusWizard);

      

        return retorno;
    }
}
