package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1103] - Manter Tipo de Corte
 * 
 * @author Ivan Sergio
 * @date 03/12/2010
 */
public class ExibirFiltrarTipoCorteAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Aponta o caminho de retorno do Action no struts
		ActionForward retorno = actionMapping.findForward("filtrarTipoCorte");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarTipoCorteActionForm form = (FiltrarTipoCorteActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("atualizar", "1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}	
		
		if (httpServletRequest.getParameter("menu") != null) {
			form.setIndicadorAtualizar("1");
			form.setIndicadorUso("");
			httpServletRequest.setAttribute("nomeCampo", "idTipoCorte");
			sessao.setAttribute("indicadorAtualizar", "1");
		} else {
			if (httpServletRequest.getParameter("paginacao") == null && sessao.getAttribute("filtrar") == null) {
				String atualizar = httpServletRequest.getParameter("atualizar");

				if (atualizar != null && !atualizar.equals("")) {
					sessao.setAttribute("atualizar", atualizar);
				} else {
					sessao.removeAttribute("atualizar");
				}
			}
		}
		
		if (sessao.getAttribute("filtrar") != null) {
			httpServletRequest.setAttribute("nomeCampo", "idTipoCorte");
		}
		
		sessao.removeAttribute("filtroEquipe");
		
		if (httpServletRequest.getParameter("menu") != null) {
			form.setIndicadorUso("1");
			form.setIndicadorCorteAdministrativo("1");
			httpServletRequest.setAttribute("nomeCampo", "idTipoCorte");
			sessao.setAttribute("atualizar", "1");
		} else {
			if (httpServletRequest.getParameter("paginacao") == null && sessao.getAttribute("filtrar") == null) {
				String atualizar = httpServletRequest.getParameter("atualizar");
				
				if (atualizar != null && !atualizar.equals("")) {
					sessao.setAttribute("atualizar", atualizar);
				} else {
					sessao.removeAttribute("atualizar");
				}
			}
		}
		
		return retorno;
	}
}
