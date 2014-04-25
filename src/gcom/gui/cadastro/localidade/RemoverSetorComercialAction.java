package gcom.gui.cadastro.localidade;

import java.util.ArrayList;
import java.util.Collection;

import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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

public class RemoverSetorComercialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        ResultadoPesquisaSetorComercialActionForm resultadoPesquisaSetorComercialActionForm = (ResultadoPesquisaSetorComercialActionForm) actionForm;
        
        //------------ REGISTRAR TRANSAÇÃO ----------------
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_SETOR_COMERCIAL_REMOVER);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------

        String[] selecaoSetorComercial = resultadoPesquisaSetorComercialActionForm
                .getSetorComercialID();

        System.out.print(selecaoSetorComercial.length);

        if (selecaoSetorComercial == null || selecaoSetorComercial.length < 1) {
            //Nenhum setor comercial foi escolhido
            throw new ActionServletException("atencao.pesquisa.nenhumresultado");
        } else {
        	
        	//------------ REGISTRAR TRANSAÇÃO ----------------
        	UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        	Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
        	colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
            //------------ REGISTRAR TRANSAÇÃO ----------------
            fachada.remover(selecaoSetorComercial, SetorComercial.class
                    .getName(),operacaoEfetuada, colecaoUsuarios);
        }

        montarPaginaSucesso(httpServletRequest,
                selecaoSetorComercial.length + " Setor(es) Comercial(is) removido(s) com sucesso.",
                "Realizar outra Manutenção de Setor Comercial",
                "exibirFiltrarSetorComercialAction.do");
        sessao.removeAttribute("InserirSetorComercialActionForm");
        //devolve o mapeamento de retorno
        return retorno;
    }

}
