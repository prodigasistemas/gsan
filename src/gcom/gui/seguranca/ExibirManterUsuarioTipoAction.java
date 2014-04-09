package gcom.gui.seguranca;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 * @date 26/08/08
 */
public class ExibirManterUsuarioTipoAction extends GcomAction {
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterUsuarioTipo");

		Collection colecaoUsuarioTipo = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroUsuarioTipo filtroUsuarioTipo = (FiltroUsuarioTipo) sessao
				.getAttribute("filtroUsuarioTipo");

		filtroUsuarioTipo.setCampoOrderBy(FiltroUsuarioTipo.ID);	
		
		if (filtroUsuarioTipo!= null && !filtroUsuarioTipo.equals("")) {
			Map resultado = 
				controlarPaginacao(httpServletRequest, retorno,filtroUsuarioTipo, UsuarioTipo.class.getName());
			
			colecaoUsuarioTipo = (Collection) resultado.get("colecaoRetorno");
			
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoUsuarioTipo != null
				&& !colecaoUsuarioTipo.isEmpty()) {
			if (colecaoUsuarioTipo.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarUsuarioTipo");
					UsuarioTipo usuarioTipo = (UsuarioTipo) colecaoUsuarioTipo
							.iterator().next();
					sessao.setAttribute("usuarioTipo", usuarioTipo);
				} else {
					httpServletRequest.setAttribute("colecaoUsuarioTipo",
							colecaoUsuarioTipo);
				}
			} else {
				httpServletRequest.setAttribute("colecaoUsuarioTipo",
						colecaoUsuarioTipo);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
