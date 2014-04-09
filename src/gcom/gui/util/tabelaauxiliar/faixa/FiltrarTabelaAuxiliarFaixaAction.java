package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarTabelaAuxiliarFaixaAction extends Action {
    /**
     * < <Descrição do método>>
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("retornarFiltroTabelaAuxiliarFaixa");

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //Recupera os parâmetros do form
        String id = (String) pesquisarActionForm.get("id");
        String faixaInicial = (String) pesquisarActionForm.get("faixaInicial");
        String faixaFinal = (String) pesquisarActionForm.get("faixaFinal");

        //cria o filtro para Tabela Auxiliar Faixa
        FiltroTabelaAuxiliarFaixa filtroTabelaAuxiliarFaixa = new FiltroTabelaAuxiliarFaixa();

        //Insere os parâmetros informados no filtro
        if (id != null && !id.trim().equalsIgnoreCase("")) {
            filtroTabelaAuxiliarFaixa.adicionarParametro(new ParametroSimples(
                    FiltroTabelaAuxiliarFaixa.ID, id));
        }
        if (faixaInicial != null && !faixaFinal.trim().equalsIgnoreCase("")) {
            filtroTabelaAuxiliarFaixa.adicionarParametro(new ComparacaoTexto(
                    FiltroTabelaAuxiliarFaixa.FAIXAINICIAL, faixaInicial));
        }

        if (faixaFinal != null && !faixaFinal.trim().equalsIgnoreCase("")) {
            filtroTabelaAuxiliarFaixa.adicionarParametro(new ComparacaoTexto(
                    FiltroTabelaAuxiliarFaixa.FAIXAFINAL, faixaFinal));
        }

        //Manda o filtro pelo request para o ExibirManterTabelaAuxiliarFaixa
        httpServletRequest.setAttribute("filtroTabelaAuxiliarFaixa",
                filtroTabelaAuxiliarFaixa);

        //Devolve o mapeamento de retorno
        return retorno;
    }
}
