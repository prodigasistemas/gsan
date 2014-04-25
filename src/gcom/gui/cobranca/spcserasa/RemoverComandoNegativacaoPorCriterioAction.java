package gcom.gui.cobranca.spcserasa;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remove os comandos negativação selecionados na lista da funcionalidade 
 * [UC652]Manter Coamndo de Negativação por Critério
 * 
 * @author Ana Maria 
 */
public class RemoverComandoNegativacaoPorCriterioAction extends GcomAction {

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

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
        
        //HttpSession sessao = httpServletRequest.getSession(false);
        
        //Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        //------------ REGISTRAR TRANSAÇÃO ----------------
/*        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_CLIENTE_REMOVER);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);*/
        //------------ REGISTRAR TRANSAÇÃO ----------------


        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        Fachada fachada = Fachada.getInstancia();

        // mensagem de erro quando o usuário tenta excluir sem ter selecionado
        // nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException(
                    "atencao.registros.nao_selecionados");
        }

    	//------------ REGISTRAR TRANSAÇÃO ----------------
/*    	UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
    	colecaoUsuarios.add(usuarioAcaoUsuarioHelper);*/
        //------------ REGISTRAR TRANSAÇÃO ----------------

        fachada.removerComandoNegativacaoPorCriterio(ids);

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Comando(s) de Negativação Critério removido(s) com sucesso",
                    "Realizar outra Manutenção de Comando Negativação Critério",
                    "exibirFiltrarComandoNegativacaoPorCriterioAction.do?menu=sim");
        }

        return retorno;
    }
}
