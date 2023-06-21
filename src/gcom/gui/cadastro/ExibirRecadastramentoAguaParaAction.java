package gcom.gui.cadastro;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import gcom.seguranca.acesso.usuario.Usuario;

public class ExibirRecadastramentoAguaParaAction extends GcomAction {
	
	@Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        HttpSession sessao = httpServletRequest.getSession(false);
        
        DadosRecadastramentoAguaParaActionForm form = (DadosRecadastramentoAguaParaActionForm) actionForm;

        ActionForward retorno = actionMapping.findForward("exibirRecadastramentoAguaPara");

        Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);       
        
        if(!getFachada().verificarPermissaoRecadastramentoAguaPara(usuarioLogado))
        	throw new ActionServletException("atencao.usuario_sem_permissao_recadastramento_bolsa_agua"); 
        
        form.setIdImovel("");
        form.setSituacao(0);

        return retorno;
    }

}
