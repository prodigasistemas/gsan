package gcom.gui.faturamento.consumotarifa;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirFiltrarConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarConsumoTarifa");

		FiltrarConsumoTarifaActionForm filtrarConsumoTarifaActionForm = (FiltrarConsumoTarifaActionForm) actionForm;
		
		if( filtrarConsumoTarifaActionForm.getAtualizarFiltro()== null){
			filtrarConsumoTarifaActionForm.setAtualizarFiltro("1");
			httpServletRequest.setAttribute("nomeCampo",
			"descTarifa");
		}
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("atualizar");
		
		if (sessao.getAttribute("idLigacaoAguaPerfil")!=null){
				sessao.removeAttribute("idLigacaoAguaPerfil");
		}
		
		return retorno;

	}

}
