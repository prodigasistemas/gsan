package gcom.gui.batch;

import gcom.batch.FiltroFuncionalidadeIniciada;
import gcom.batch.FuncionalidadeIniciada;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de exibição do erro ocorrido no batch
 * 
 * @author Rodrigo Silveira
 * @created 12/05/2008
 */
public class ExibirExcecaoFuncionalidadeIniciadaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirExcecao");
		
		String idFuncionalidadeIniciada = httpServletRequest.getParameter("idFuncionalidadeIniciada");

		FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
		filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.ID, idFuncionalidadeIniciada));
		
		FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroFuncionalidadeIniciada, FuncionalidadeIniciada.class.getName()));
		
		httpServletRequest.setAttribute("excecao", funcionalidadeIniciada.getDescricaoExcecao());
		
		return retorno;
	}

}
