package gcom.gui.cadastro.imovel;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirImovelClienteAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
    	
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Collection clientes = (Collection) sessao.getAttribute("imovelClientesNovos");
		fachada.validarImovelAbaCliente(clientes, usuario);
		
		sessao.removeAttribute("gis");

        ActionForward retorno = actionMapping
                .findForward("gerenciadorProcesso");

        return retorno;
    }

}
