package gcom.gui.arrecadacao.pagamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por remover pagamentos do sistema 
 *
 * @author Pedro Alexandre
 * @date 21/03/2006
 */
public class RemoverPagamentoAction extends GcomAction {

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * <Breve descrição sobre o subfluxo>
     *
     * <Identificador e nome do subfluxo>	
     *
     * <Breve descrição sobre o fluxo secundário>
     *
     * <Identificador e nome do fluxo secundário> 
     *
     * @author Pedro Alexandre
     * @date 21/03/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Recupera o form de manter pagamentos
        ManterPagamentoActionForm manterPagamentoActionForm = (ManterPagamentoActionForm) actionForm;

        //Recupera do form oarray comos código de pagamentos selecionados para exclusão
        String[] idsPagamentos = manterPagamentoActionForm.getIdRegistrosRemocao();

        //Seta o mapeamento de retorno para a tela de sucesso
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //Caso o usuário não tenha selecionado nenhum pagamento pra exclusão
        //Levanta uma exceção para o usuário indicando que nenhum registro foi selecionado
        //Caso contrário chama o metódo de remover pagamentos da fachada
        if (idsPagamentos == null || idsPagamentos.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }else{
        	fachada.removerPagamentos(idsPagamentos,usuarioLogado);	
        }

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest, idsPagamentos.length +
                    " Pagamento(s) removido(s) com sucesso.",
                    "Realizar outra Manutenção de Pagamento",
                    "exibirFiltrarPagamentoAction.do?tela=manterPagamento&menu=sim");
        }

        //Retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
