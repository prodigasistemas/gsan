package gcom.gui.util.tabelaauxiliar;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

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
public class RemoverTabelaAuxiliarAction extends GcomAction {

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
            reportarErros(httpServletRequest, "erro.registros.nao_selecionados");
            retorno = actionMapping.findForward("telaAtencao");
        }

        //Pega o path do pacote do objeto mais o tipo
        pacoteNomeObjeto = (String) sessao.getAttribute("pacoteNomeObjeto");

        //Remove o objeto
        fachada.removerTabelaAuxiliar(ids, pacoteNomeObjeto,null, null);

        String titulo = (String) sessao.getAttribute("titulo");
        
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(
                    httpServletRequest,
                    titulo+ " removido(a)(s) com sucesso",
                    "Realizar outra manutenção de "+ titulo,
                    ((String) sessao.getAttribute("funcionalidadeTabelaAuxiliarFiltrar")));
        }

        //Remove os objetos da sessão
        sessao.removeAttribute("funcionalidadeTabelaAuxiliarFiltrar");
        sessao.removeAttribute("titulo");
        sessao.removeAttribute("descricao");
        sessao.removeAttribute("tabelaAuxiliar");
        sessao.removeAttribute("tamanhoMaximoCampo");
        sessao.removeAttribute("pacoteNomeObjeto");
        sessao.removeAttribute("nomeSistema");
        sessao.removeAttribute("tabelasAuxiliares");
        sessao.removeAttribute("tela");
        sessao.removeAttribute("totalRegistros");

        //devolve o mapeamento de retorno
        return retorno;
    }
}
