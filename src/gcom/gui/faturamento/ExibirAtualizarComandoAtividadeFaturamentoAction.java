package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoAtividadeCronograma;
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
public class ExibirAtualizarComandoAtividadeFaturamentoAction extends
        GcomAction {

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
                .findForward("exibirAtualizarComandoAtividadeFaturamento");

        //obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //limpa a sessão
        sessao.removeAttribute("dataCorrente");
        sessao.removeAttribute("exibirCampoVencimentoGrupo");
        if( sessao.getAttribute("faturamentoAtividadeCronograma") != null ){
        	FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma)
        		sessao.getAttribute("faturamentoAtividadeCronograma");
        	sessao.setAttribute("faturamentoGrupoId", faturamentoAtividadeCronograma
					.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getId());
        }    
        sessao.removeAttribute("faturamentoAtividadeCronograma");
        sessao.removeAttribute("colecaoFaturamentoAtividadeCronogramaRota");
        sessao.removeAttribute("colecaoRotasSelecionadas");
        sessao.removeAttribute("colecaoRotasSelecionadasUsuario");
        sessao.removeAttribute("colecaoFaturamentoAtividadeCronogramaRotaUniao");
        sessao.removeAttribute("PesquisarActionForm");
        sessao.removeAttribute("InserirComandoAtividadeFaturamentoActionForm");

        String faturamentoAtividadeCronogramaId = null;
        
        //sessao.removeAttribute("faturamentoAtividadeCronogramaIDSessao");
        if (httpServletRequest.getParameter("faturamentoAtividadeCronogramaID") != null) {
        	faturamentoAtividadeCronogramaId = (String) httpServletRequest
					.getParameter("faturamentoAtividadeCronogramaID");
        }	
        
        StatusWizard statusWizard = new StatusWizard(
                "atualizarComandoAtividadeFaturamentoWizardAction",
                "atualizarComandoAtividadeFaturamentoAction",
                "cancelarAtualizarComandoAtividadeFaturamentoAction",
                "filtrarComandoAtividadeFaturamentoAction.do?menu=sim",
                "",
                "exibirAtualizarComandoAtividadeFaturamentoAction.do?faturamentoAtividadeCronogramaID="+faturamentoAtividadeCronogramaId,
                faturamentoAtividadeCronogramaId);
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1,
                        "ComandoA.gif",
                        "ComandoD.gif",
                        "exibirAtualizarComandoAtividadeFaturamentoDadosAction",
                        "atualizarComandoAtividadeFaturamentoDadosAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2,
                        "RotasA.gif",
                        "RotasD.gif",
                        "exibirAtualizarComandoAtividadeFaturamentoRotasAction",
                        "atualizarComandoAtividadeFaturamentoRotasAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        3,
                        "ConclusaoA.gif",
                        "ConclusaoD.gif",
                        "exibirAtualizarComandoAtividadeFaturamentoDataVencimentoAction",
                        "atualizarComandoAtividadeFaturamentoDataVencimentoAction"));

        //manda o statusWizard para a sessao
        sessao.setAttribute("statusWizard", statusWizard);

        return retorno;
    }
}
