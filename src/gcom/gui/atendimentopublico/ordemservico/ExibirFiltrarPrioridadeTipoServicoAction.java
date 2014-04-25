package gcom.gui.atendimentopublico.ordemservico;
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
public class ExibirFiltrarPrioridadeTipoServicoAction extends GcomAction {
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
		
		FiltrarPrioridadeTipoServicoActionForm filtrarPrioridadeTipoServicoActionForm = (FiltrarPrioridadeTipoServicoActionForm) actionForm;
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		//	Código para checar ou não o ATUALIZAR		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarPrioridadeTipoServicoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());			
		}
			
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarPrioridadeTipoServico");
		// Obtém a instância da Fachada
		//Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
			
			filtrarPrioridadeTipoServicoActionForm.setCodigo("");
			filtrarPrioridadeTipoServicoActionForm.setDescricao("");
			filtrarPrioridadeTipoServicoActionForm.setAbreviatura("");
			filtrarPrioridadeTipoServicoActionForm.setQtdHorasFim("");
			filtrarPrioridadeTipoServicoActionForm.setQtdHorasInicio("");

        }
		
		//	Se voltou da tela de Atualizar Sistema de Esgoto
		if (sessao.getAttribute("voltar") != null && sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if(sessao.getAttribute("tipoPesquisa") != null){
				filtrarPrioridadeTipoServicoActionForm.setTipoPesquisa(sessao.getAttribute("tipoPesquisa").toString());
			}
		}		
		
		return retorno;

	}

}
