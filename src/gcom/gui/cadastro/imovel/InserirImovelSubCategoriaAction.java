package gcom.gui.cadastro.imovel;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;

public class InserirImovelSubCategoriaAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("gerenciadorProcesso");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        
        Fachada fachada = Fachada.getInstancia();
        
        sessao.removeAttribute("gis");

        Collection subCategorias = (Collection) sessao
                .getAttribute("colecaoImovelSubCategorias");

        fachada.validarAbaInserirImovelSubcategoria(subCategorias, 
        PermissaoEspecial.INSERIR_IMOVEL_PARA_ORGAO_PUBLICO, usuario);

        return retorno;
    }
}
