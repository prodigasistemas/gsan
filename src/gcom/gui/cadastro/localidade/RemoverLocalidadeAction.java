package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverLocalidadeAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
   	
    	
        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);

        FiltrarLocalidadeActionForm filtrarLocalidadeActionForm = (FiltrarLocalidadeActionForm) actionForm;
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        
        
        //------------ REGISTRAR TRANSAÇÃO ----------------
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_LOCALIDADE_REMOVER);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------

        String[] selecaoLocalidade = filtrarLocalidadeActionForm
                .getLocalidadeSelectID();

        if (selecaoLocalidade == null || selecaoLocalidade.length < 1) {
            //Nenhum registro selecionado para remoção.
            throw new ActionServletException("atencao.registros.nao_selecionados");
        } else {
            //------------ REGISTRAR TRANSAÇÃO ----------------
        	UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        	Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
        	colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
        	
            //------------ REGISTRAR TRANSAÇÃO ----------------  
        	
        	//Remove Localidade(s) selecionada(s)
            fachada.remover(selecaoLocalidade, Localidade.class.getName(),operacaoEfetuada, colecaoUsuarios);
        }

        montarPaginaSucesso(httpServletRequest,
        		selecaoLocalidade.length +
				" Localidade(s) removida(s) com sucesso.",
				"Realizar outra manutenção de Localidade",
				"exibirFiltrarLocalidadeAction.do?desfazer=S");
        sessao.removeAttribute("colecaoGerenciaRegional");

        //devolve o mapeamento de retorno
        return retorno;
    }

}
