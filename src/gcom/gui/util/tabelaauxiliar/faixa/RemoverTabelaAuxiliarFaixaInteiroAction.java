package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 *
 */
public class RemoverTabelaAuxiliarFaixaInteiroAction extends GcomAction {

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

        //Obtém o action form
        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Cria os objetos
        String pacoteNomeObjeto = null;

        // Obtém os ids de remoção
        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //mensagem de erro quando o usuário tenta excluir sem ter selecionado
        // nenhum
        //registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException("erro.registros.nao_selecionados");
        }

        //Pega o path do pacote do objeto mais o tipo
        pacoteNomeObjeto = (String) sessao.getAttribute("pacoteNomeObjeto");
        //Remove o objeto
        fachada.removerTabelaAuxiliar(ids, pacoteNomeObjeto,null, null);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(
                    httpServletRequest,
                    ((String) sessao.getAttribute("titulo"))
                            + " removido(a)(s) com sucesso",
                    "Realizar outra manutenção de "
                            + ((String) sessao.getAttribute("titulo")),
                    ((String) sessao
                            .getAttribute("funcionalidadeTabelaAuxiliarFaixaInteiroFiltrar")));
        }

        //Remove os objetos da sessão
        sessao.removeAttribute("funcionalidadeTabelaAuxiliarFaixaInteiroFiltrar");
        sessao.removeAttribute("titulo");
        sessao.removeAttribute("tabelaAuxiliarFaixaInteiro");
        sessao.removeAttribute("tamMaxCampoDescricao");
        sessao.removeAttribute("tamMaxCampoDescricaoAbreviada");
        sessao.removeAttribute("descricao");
        sessao.removeAttribute("descricaoAbreviada");

        //devolve o mapeamento de retorno
        return retorno;
    }
}
