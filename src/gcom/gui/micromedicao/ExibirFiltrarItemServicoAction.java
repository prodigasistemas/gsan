package gcom.gui.micromedicao;

import gcom.gui.GcomAction;
import gcom.micromedicao.FiltrarItemServicoActionForm;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Rodrigo Cabral
 * @date 04/08/10
 */



public class ExibirFiltrarItemServicoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("filtrarItemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarItemServicoActionForm form = (FiltrarItemServicoActionForm) actionForm;
		
		//	Código para checar ou não o ATUALIZAR e Passar o valor do Indicador de Uso como "TODOS"
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}
		
		return retorno;
	}
	
}



