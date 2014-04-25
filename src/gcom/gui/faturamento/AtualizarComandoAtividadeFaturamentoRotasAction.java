package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarComandoAtividadeFaturamentoRotasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("");

        //Carrega a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) sessao
                .getAttribute("faturamentoAtividadeCronograma");

        // Verifica a existência de uma nova coleção para ser inserida
        if (sessao.getAttribute("colecaoRotasSelecionadasUsuario") != null) {

            Collection colecaoRotasSelecionadasUsuario = (Collection) sessao
                    .getAttribute("colecaoRotasSelecionadasUsuario");

            if (!colecaoRotasSelecionadasUsuario.isEmpty()) {

                // true = Rotas habilitadas
                Collection colecaoFaturamentoAtividadeCronogramaRotaHabilitadas = fachada
                	.verificarSituacaoAtividadeRota(
                			colecaoRotasSelecionadasUsuario,
                            faturamentoAtividadeCronograma.getFaturamentoAtividade(),
                            faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
                            	.getFaturamentoGrupo(),
                            true);

                //[FS0003] - Verificar seleção de pelo menos uma rota
                // habilitada
                if (colecaoFaturamentoAtividadeCronogramaRotaHabilitadas == null
                        || colecaoFaturamentoAtividadeCronogramaRotaHabilitadas
                                .isEmpty()) {
                    throw new ActionServletException(
                            "atencao.pesquisa.nenhuma.rota_habilitada_selecionada");
                }

            }
        }

        return retorno;
    }
}
