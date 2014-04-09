package gcom.gui.micromedicao.hidrometro;


import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0139]	FILTRAR MARCA HIDROMETRO
 * 
 * @author Bruno Barros
 * @date 12/03/2007
 */
 
public class ExibirFiltrarHidrometroMarcaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		ActionForward retorno = actionMapping.findForward("exibirFiltrarHidrometroMarca");	
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarHidrometroMarcaActionForm form = (FiltrarHidrometroMarcaActionForm) actionForm;		
	
		//	Código para checar ou não o ATUALIZAR	
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}
		
		//	Se voltou da tela de Atualizar Sistema de Esgoto
		if (sessao.getAttribute("voltar") != null && sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if(sessao.getAttribute("tipoPesquisa") != null){
				form.setTipoPesquisa(sessao.getAttribute("tipoPesquisa").toString());
			}
		}
		
		sessao.removeAttribute("voltar");
		sessao.removeAttribute("tipoPesquisa");
		
		return retorno;
	}
}
