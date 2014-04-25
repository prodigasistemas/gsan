package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class AtualizarTabelaAuxiliarFaixaAction extends GcomAction {
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

        //Pega o action form
        TabelaAuxiliarFaixaActionForm tabelaAuxiliarFaixaActionForm = (TabelaAuxiliarFaixaActionForm) actionForm;

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Recupera o ponto de coleta da sessão
        TabelaAuxiliarFaixa tabelaAuxiliarFaixa = (TabelaAuxiliarFaixa) sessao
                .getAttribute("tabelaAuxiliarFaixa");

        //Atualiza a tabela auxiliar com os dados inseridos pelo usuário
        //A data de última alteração não é alterada, pois será usada na
        //verificação de atualização
        tabelaAuxiliarFaixa.setFaixaInicial(tabelaAuxiliarFaixaActionForm
                .getFaixaInicial());
        tabelaAuxiliarFaixa.setFaixaFinal(tabelaAuxiliarFaixaActionForm
                .getFaixaFinal());

        //Atualiza os dados
        fachada.atualizarTabelaAuxiliar(tabelaAuxiliarFaixa);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

            montarPaginaSucesso(
                    httpServletRequest,
                    ((String) sessao.getAttribute("titulo"))
                            + " atualizada com sucesso",
                    "Realizar outra manutenção de "
                            + ((String) sessao.getAttribute("titulo")),
                    ((String) sessao
                            .getAttribute("funcionalidadeTabelaAuxiliarFaixaManter")));

        }

        //Remove os objetos da sessão
        sessao.removeAttribute("funcionalidadeTabelaAuxiliarFaixaManter");
        sessao.removeAttribute("titulo");
        sessao.removeAttribute("tabelaAuxiliarFaixa");
        sessao.removeAttribute("tamMaxCampoFaixaInicial");
        sessao.removeAttribute("tamMaxCampoFaixaFinal");

        //devolve o mapeamento de retorno
        return retorno;
    }

}
