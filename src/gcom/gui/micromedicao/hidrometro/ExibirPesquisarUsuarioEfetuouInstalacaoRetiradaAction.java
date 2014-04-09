package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pré-exibição do popup que mostro o usuário que fez a instalação ou a retirada de hidrometro
 * 
 * @author Sávio Luiz
 * Data:29/04/2007
 */
public class ExibirPesquisarUsuarioEfetuouInstalacaoRetiradaAction extends
		GcomAction {
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

		// Seta a ação de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisaUsuarioEfetuouInstalacaoRetirada");
		
		String idUsuario = httpServletRequest.getParameter("idUsuario");
		
		Fachada fachada = Fachada.getInstancia();
		
		if(idUsuario != null && !idUsuario.equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID,idUsuario));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario,Usuario.class.getName());
			Usuario usuario = (Usuario)Util.retonarObjetoDeColecao(colecaoUsuario);
			if(usuario != null && !usuario.equals("")){
			 httpServletRequest.setAttribute("loginUsuario",usuario.getLogin());
			 httpServletRequest.setAttribute("nomeUsuario",usuario.getNomeUsuario());
			}
		}else{
			httpServletRequest.setAttribute("loginUsuario","");
			httpServletRequest.setAttribute("nomeUsuario","");
		}
		
		httpServletRequest.setAttribute("tipo",httpServletRequest.getParameter("tipo"));
		
		
		

		return retorno;

	}

}
