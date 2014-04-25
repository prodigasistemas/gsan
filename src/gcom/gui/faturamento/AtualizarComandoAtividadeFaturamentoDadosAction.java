package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarComandoAtividadeFaturamentoDadosAction extends GcomAction {

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
        ActionForward retorno = actionMapping.findForward("");

        //Carrega a instancia da fachada
       // Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Instância do formulário que está sendo utilizado
        InserirComandoAtividadeFaturamentoActionForm inserirComandoAtividadeFaturamentoActionForm = (InserirComandoAtividadeFaturamentoActionForm) actionForm;

        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) sessao
                .getAttribute("faturamentoAtividadeCronograma");

        // Para auxiliar na formatação de uma data
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        // Data corrente para comparação
        // =============================
        String dataCorrente = null;
        Date dataCorrenteGrupo = null;

        if (faturamentoAtividadeCronograma.getFaturamentoAtividade().getId()
                .equals(FaturamentoAtividade.FATURAR_GRUPO)
                && sessao.getAttribute("dataCorrente") != null) {

            dataCorrente = (String) sessao.getAttribute("dataCorrente");
            if (dataCorrente != null && !dataCorrente.equalsIgnoreCase("")) {

                try {
                    dataCorrenteGrupo = formatoData.parse(dataCorrente);
                } catch (ParseException ex) {
                    dataCorrenteGrupo = null;
                }
            }
        }
        
        
        //Data de vecimento do grupo para comparação
        // =========================================
        String dataVencimentoGrupoBase = null;
      //  Date dataVencimentoGrupoBaseObjeto = null;

        if (faturamentoAtividadeCronograma.getFaturamentoAtividade().getId()
                .equals(FaturamentoAtividade.FATURAR_GRUPO)
                && sessao.getAttribute("exibirCampoVencimentoGrupo") != null) {

        	dataVencimentoGrupoBase = (String) sessao.getAttribute("exibirCampoVencimentoGrupo");
            if (dataVencimentoGrupoBase != null && !dataVencimentoGrupoBase.equalsIgnoreCase("")) {

              /*  try {
                	dataVencimentoGrupoBaseObjeto = formatoData.parse(dataVencimentoGrupoBase);
                } catch (ParseException ex) {
                	dataVencimentoGrupoBaseObjeto = null;
                }*/
            }
        }

        
        // Data de vecimento do grupo informado pelo usuário (JSP)
        // ========================================================
        String dataVencimentoGrupoJSP = inserirComandoAtividadeFaturamentoActionForm
                .getVencimentoGrupo();

        Date dataVencimentoGrupo = null;

        if (faturamentoAtividadeCronograma.getFaturamentoAtividade().getId()
                .equals(FaturamentoAtividade.FATURAR_GRUPO)
                && sessao.getAttribute("dataCorrente") != null) {

            if (dataVencimentoGrupoJSP != null
                    && !dataVencimentoGrupoJSP.equalsIgnoreCase("")) {

                try {
                    dataVencimentoGrupo = formatoData
                            .parse(dataVencimentoGrupoJSP);

                    String mesDataVencimentoGrupo = dataVencimentoGrupoJSP
                            .substring(3, 5);
                    String anoDataVencimentoGrupo = dataVencimentoGrupoJSP
                            .substring(6, 10);

                    String mesDataVencimentoGrupoBase = dataVencimentoGrupoBase.substring(3, 5);
                    String anoDataVencimentoGrupoBase = dataVencimentoGrupoBase.substring(6, 10);

                    if (dataCorrenteGrupo.after(dataVencimentoGrupo)) {
                        throw new ActionServletException(
                                "atencao.faturamento_data_vencimento_grupo_menor",
                                null, dataCorrente);
                    } else if ((!mesDataVencimentoGrupo
                            .equalsIgnoreCase(mesDataVencimentoGrupoBase))
                            || (!anoDataVencimentoGrupo
                                    .equalsIgnoreCase(anoDataVencimentoGrupoBase))) {
                        throw new ActionServletException(
                                "atencao.faturamento_data_vencimento_mes_ano_diferente");
                    }
                } catch (ParseException ex) {
                	throw new ActionServletException(
                    "atencao.data.invalida");
                }
            }
        }

        return retorno;
    }
}
