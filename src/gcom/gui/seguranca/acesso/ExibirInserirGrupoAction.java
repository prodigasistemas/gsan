package gcom.gui.seguranca.acesso;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável por montar todo o esquema do 
 * processo de inserir grupo de usuários.
 *
 * @author Pedro Alexandre
 * @date 14/06/2006
 */
public class ExibirInserirGrupoAction extends GcomAction {

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * [UC0278] - Inserir Grupo
     *
     * @author Pedro Alexandre
     * @date 14/06/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        //Localiza o action no objeto 
        ActionForward retorno = actionMapping.findForward("inserirGrupoDadosGerais");

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Limpa a sessão
        sessao.removeAttribute("GrupoActionForm");
        sessao.removeAttribute("grupo");
        

        //Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard(
                "inserirGrupoWizardAction", "inserirGrupoAction",
                "cancelarInserirGrupoAction","exibirInserirGrupoAction.do");
        
        //monta a primeira aba do processo
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "DadosGeraisPrimeiraAbaA.gif", "DadosGeraisPrimeiraAbaD.gif",
                        "exibirInserirGrupoDadosGeraisAction",
                        "inserirGrupoDadosGeraisAction"));
        
        //monta a segunda aba do processo,se for leitura do código de barra por caneta
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "AcessosGrupoUltimaAbaA.gif", "AcessosGrupoUltimaAbaD.gif",
                        "exibirInserirGrupoAcessosGrupoAction",
                        "inserirGrupoAcessosGrupoAction"));
        
        
        
        
        //manda o statusWizard para a sessão
        sessao.setAttribute("statusWizard", statusWizard);

        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
