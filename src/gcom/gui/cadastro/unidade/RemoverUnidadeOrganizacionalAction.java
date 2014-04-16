package gcom.gui.cadastro.unidade;

import java.util.ArrayList;
import java.util.Collection;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter rota
 * 
 * @author Ana Maria
 * @date 24/11/2006
 */
public class RemoverUnidadeOrganizacionalAction extends GcomAction {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        // ------------ REGISTRAR TRANSAÇÃO ----------------
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_UNIDADE_ORGANIZACIONAL_REMOVER);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
       
        //mensagem de erro quando o usuário tenta excluir sem ter selecionado
        // nenhum
        //registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException(
                    "atencao.registros.nao_selecionados");
        }
        
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        
//      ------------ REGISTRAR TRANSAÇÃO ----------------
    	UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
    	colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
        //------------ REGISTRAR TRANSAÇÃO ----------------

        fachada.remover(ids, UnidadeOrganizacional.class.getName(),operacaoEfetuada, colecaoUsuarios);
               

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length +
                    " Unidades Organizacionais removida(s) com sucesso.",
                    "Realizar outra Manutenção de Unidade Organizacional",
                    "exibirFiltrarUnidadeOrganizacionalAction.do?desfazer=S");
        }
        
        sessao.removeAttribute("UnidadeOrganizacionalActionForm");
       return retorno;
    }
    


}
 
