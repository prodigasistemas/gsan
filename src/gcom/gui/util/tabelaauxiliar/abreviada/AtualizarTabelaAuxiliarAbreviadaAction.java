package gcom.gui.util.tabelaauxiliar.abreviada;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

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
public class AtualizarTabelaAuxiliarAbreviadaAction extends GcomAction {
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
        TabelaAuxiliarAbreviadaActionForm tabelaAuxiliarAbreviadaActionForm = (TabelaAuxiliarAbreviadaActionForm) actionForm;

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Recupera o ponto de coleta da sessão
        TabelaAuxiliarAbreviada tabelaAuxiliarAbreviada = (TabelaAuxiliarAbreviada) sessao
                .getAttribute("tabelaAuxiliarAbreviada");

        //Atualiza a tabela auxiliar com os dados inseridos pelo usuário
        //A data de última alteração não é alterada, pois será usada na
        //verificação de atualização
        tabelaAuxiliarAbreviada.setDescricao(tabelaAuxiliarAbreviadaActionForm
                .getDescricao().toUpperCase());
        tabelaAuxiliarAbreviada.setDescricaoAbreviada(tabelaAuxiliarAbreviadaActionForm
                .getDescricaoAbreviada().toUpperCase());
        if(tabelaAuxiliarAbreviadaActionForm.getIndicadorUso()!=null){
        	tabelaAuxiliarAbreviada.setIndicadorUso(new Short(tabelaAuxiliarAbreviadaActionForm.getIndicadorUso()));
        }

        //Atualiza os dados
        fachada.atualizarTabelaAuxiliar(tabelaAuxiliarAbreviada);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

            montarPaginaSucesso(
                    httpServletRequest,
                    ((String) sessao.getAttribute("titulo")) + " " + tabelaAuxiliarAbreviada.getId().toString()
                            + " atualizado(a) com sucesso.",
                    "Realizar outra manutenção de "
                            + ((String) sessao.getAttribute("titulo")),
                    ((String) sessao
                            .getAttribute("funcionalidadeTabelaAuxiliarAbreviadaFiltrar")));

        }

        //Remove os objetos da sessão
        sessao.removeAttribute("funcionalidadeTabelaAuxiliarAbreviadaManter");
        sessao.removeAttribute("titulo");
        sessao.removeAttribute("tabelaAuxiliarAbreviada");
        sessao.removeAttribute("tamMaxCampoDescricao");
        sessao.removeAttribute("tamMaxCampoDescricaoAbreviada");
        sessao.removeAttribute("descricao");
        sessao.removeAttribute("descricaoAbreviada");

        //devolve o mapeamento de retorno
        return retorno;
    }

}
