package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverComandoAtividadeFaturamentoAction extends GcomAction {

    /**
     * 
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Carrega a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //instância o formulário que está sendo utilizado
        FiltrarComandoAtividadeFaturamentoActionForm filtrarComandoAtividadeFaturamentoActionForm = (FiltrarComandoAtividadeFaturamentoActionForm) actionForm;

        String[] faturamentoAtividadeCronogramaSelected = filtrarComandoAtividadeFaturamentoActionForm
                .getFaturamentoAtividadeCronogramaID();

        fachada
              .removerComandoAtividadeFaturamento(faturamentoAtividadeCronogramaSelected);

        montarPaginaSucesso(httpServletRequest,
                "Comando de Atividade de Faturamento removido com sucesso.",
                "Manter outro Comando de Atividade de Faturamento",
                "filtrarComandoAtividadeFaturamentoAction.do");

        return retorno;
    }

}
