package gcom.gui.cadastro.atualizacaocadastral;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarDadosImovelAtualizacaoCadastralAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
    	ActionForward retorno =	actionMapping.findForward("filtrarAlteracaoAtualizacaoCadastral");
        
		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm form = (ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
        HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		if (!form.getIdRegistrosAutorizados().equals("")) {
			String registrosAutorizados = form.getIdRegistrosAutorizados();
			
			String[] listaIdRegistrosSim = registrosAutorizados.split(",");
			if(listaIdRegistrosSim != null && !listaIdRegistrosSim.equals("")){
				fachada.atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(Integer.valueOf(form.getIdImovel()), listaIdRegistrosSim, ConstantesSistema.SIM, usuario);
			}
		}
		
		httpServletRequest.setAttribute("reload", true);
		
        return retorno;
    }
}
