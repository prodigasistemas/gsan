package gcom.gui.seguranca;


import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




/**
 * 
 * @author Arthur Carvalho
 * @date 26/08/2008
 */
public class ExibirAtualizarUsuarioTipoAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
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

		ActionForward retorno = actionMapping
				.findForward("usuarioTipoAtualizar");

		AtualizarUsuarioTipoActionForm atualizarUsuarioTipoActionForm = (AtualizarUsuarioTipoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((UsuarioTipo) sessao.getAttribute("usuarioTipo")).getId().toString();
		}
		

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		UsuarioTipo usuarioTipo = new UsuarioTipo();
						
		if (id != null && !id.trim().equals("")) {

			FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
			filtroUsuarioTipo.adicionarParametro(
				new ParametroSimples(FiltroUsuarioTipo.ID, id));
			
			Collection colecaoUsuarioTipo = fachada.pesquisar(
					filtroUsuarioTipo, UsuarioTipo.class.getName());
			
			if (colecaoUsuarioTipo != null && !colecaoUsuarioTipo.isEmpty()) {
				usuarioTipo= (UsuarioTipo) Util.retonarObjetoDeColecao(colecaoUsuarioTipo);
			}

			if (id == null || id.trim().equals("")) {

				atualizarUsuarioTipoActionForm.setCodigo(usuarioTipo
						.getId().toString());

				atualizarUsuarioTipoActionForm
						.setDescricao(usuarioTipo.getDescricao());

				atualizarUsuarioTipoActionForm
						.setIndicadorUso(usuarioTipo.getIndicadorUso());
				
				atualizarUsuarioTipoActionForm
						.setIndicadorFuncionario(usuarioTipo.getIndicadorFuncionario());

			}
			
			atualizarUsuarioTipoActionForm.setCodigo(usuarioTipo.getId().toString());
			atualizarUsuarioTipoActionForm.setDescricao(usuarioTipo.getDescricao());
			atualizarUsuarioTipoActionForm.setIndicadorUso(usuarioTipo.getIndicadorUso());
			atualizarUsuarioTipoActionForm.setIndicadorFuncionario(usuarioTipo.getIndicadorFuncionario());

			
			sessao.setAttribute("atualizarUsuarioTipo", usuarioTipo);

			if (sessao.getAttribute("colecaoUsuarioTipo") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarUsuarioTipoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarUsuarioTipoAction.do");
			}

		}
		

		return retorno;
	}
}
