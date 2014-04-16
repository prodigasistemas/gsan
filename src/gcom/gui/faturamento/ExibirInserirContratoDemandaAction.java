package gcom.gui.faturamento;


import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0521]	Inserir Contrato de Demanda
 * 
 * @author Eduardo Bianchi
 * @date 14/02/2007
 */

public class ExibirInserirContratoDemandaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("inserirContratoDemanda");
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirContratoDemandaActionForm inserirContratoDemandaActionForm = (InserirContratoDemandaActionForm) actionForm;
		
		String idImovel = inserirContratoDemandaActionForm.getIdImovel();
		
		if (idImovel != null && !idImovel.trim().equals("")) {
			
			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(idImovel));
			
			if (inscricao != null && !inscricao.trim().equals("")) {
				inserirContratoDemandaActionForm.setInscricaoImovel(inscricao);
				httpServletRequest.setAttribute("nomeCampo", "dataInicioContrato");
			} else {
				inserirContratoDemandaActionForm.setInscricaoImovel("IMÓVEL INEXISTENTE");
				inserirContratoDemandaActionForm.setIdImovel("");
				httpServletRequest.setAttribute("existeImovel", "exception");
			}
			
		}
		
		return retorno;
	}
}
