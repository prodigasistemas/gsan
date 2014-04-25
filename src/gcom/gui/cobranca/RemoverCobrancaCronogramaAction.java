package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remove as categorias selecionadas na lista da funcionalidade Manter Categoria
 * 
 * @author Roberta Costa
 * @created 02 de Janeiro de 2006
 */
public class RemoverCobrancaCronogramaAction extends GcomAction {
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;

        // Obtém os ids de remoção
        String[] ids = cobrancaActionForm.getIdRegistrosRemocao();

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a sessão
        //HttpSession sessao = httpServletRequest.getSession(false);

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //mensagem de erro quando o usuário tenta excluir sem ter selecionado nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException(
                    "atencao.registros.nao_selecionados");
        }
        
        Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
        
        fachada.removerCobrancaCronograma(ids, usuarioLogado);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
                    ids.length + " Cronograma(s) de Cobrança(s) removido(s) com sucesso",
                    "Realizar outra manutenção de Cronograma de Cobrança",
                    "exibirFiltrarCobrancaCronogramaAction.do?menu=sim");
        }

        return retorno;
    }
}
