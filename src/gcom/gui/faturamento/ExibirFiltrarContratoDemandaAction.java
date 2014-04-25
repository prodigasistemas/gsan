package gcom.gui.faturamento;


import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0514]	Filtrar Contrato de Demanda
 * 
 * @author Rafael Corrêa
 * @date 27/06/2007
 */

public class ExibirFiltrarContratoDemandaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirFiltrarContratoDemanda");
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarContratoDemandaActionForm filtrarContratoDemandaActionForm = (FiltrarContratoDemandaActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (httpServletRequest.getParameter("menu") != null) {

			filtrarContratoDemandaActionForm.setAtualizar("1");
			httpServletRequest.setAttribute("nomeCampo", "numeroContrato");
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
			
			sessao.removeAttribute("contratoDemandaAtualizar");
		}
		
		String idImovel = filtrarContratoDemandaActionForm.getIdImovel();
		
		if (idImovel != null && !idImovel.trim().equals("")) {
			
			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(idImovel));
			
			if (inscricao != null && !inscricao.trim().equals("")) {
				filtrarContratoDemandaActionForm.setInscricaoImovel(inscricao);
				httpServletRequest.setAttribute("nomeCampo", "dataInicioContrato");
			} else {
				filtrarContratoDemandaActionForm.setInscricaoImovel("IMÓVEL INEXISTENTE");
				filtrarContratoDemandaActionForm.setIdImovel("");
				httpServletRequest.setAttribute("existeImovel", "exception");
			}
			
		}
		
		return retorno;
	}
}
