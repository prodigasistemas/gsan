package gcom.gui.cadastro.localidade;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 25 de Abril de 2005
 */
public class ExibirFiltrarGerenciaRegionalAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarGerenciaRegional");
		
		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarGerenciaRegionalActionForm filtrarGerenciaRegionalActionForm = (FiltrarGerenciaRegionalActionForm) actionForm;

		filtrarGerenciaRegionalActionForm.setAtualizar("1");
		
		if (httpServletRequest.getParameter("menu") != null) {
			filtrarGerenciaRegionalActionForm.setGerenciaRegionalID("");
			filtrarGerenciaRegionalActionForm.setGerenciaRegionalNome("");
			filtrarGerenciaRegionalActionForm.setGerenciaRegionalNomeAbre("");
			filtrarGerenciaRegionalActionForm.setIndicadorUso("");
		}
			
		filtrarGerenciaRegionalActionForm.setIndicadorAtualizar("1");
		
		if (filtrarGerenciaRegionalActionForm.getTipoPesquisa() == null
				|| filtrarGerenciaRegionalActionForm.getTipoPesquisa()
						.equalsIgnoreCase("")) {
			filtrarGerenciaRegionalActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
		}

		// código para checar ou naum o Atualizar
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarGerenciaRegionalActionForm.setIndicadorUso("3");
			filtrarGerenciaRegionalActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
		}
		// se voltou da tela de Atualizar Localidade
		if (sessao.getAttribute("voltar") != null
				&& sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if(sessao.getAttribute("tipoPesquisa") != null){
				filtrarGerenciaRegionalActionForm.setTipoPesquisa(sessao.getAttribute("tipoPesquisa").toString());
			}
		}
		sessao.removeAttribute("voltar");
		sessao.removeAttribute("idRegistroAtualizacao");
		sessao.removeAttribute("tipoPesquisa");
		
		return retorno;
	}
}
