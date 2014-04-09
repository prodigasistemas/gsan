package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoHelper;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAcompanharRoteiroProgramacaoOrdemServicoAlertaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("alertaOs");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
			
		String chaveEquipe = httpServletRequest.getParameter("chaveEquipe");
		Integer idOs = new Integer(httpServletRequest.getParameter("idOs"));
		
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");
		
		Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chaveEquipe);

		Iterator iter = colecaoHelper.iterator();
		while (iter.hasNext()) {
			
			OSProgramacaoHelper helper = (OSProgramacaoHelper) iter.next();
			
			OrdemServicoProgramacao osProgramacao = helper.getOrdemServicoProgramacao();
			OrdemServico ordemServico = osProgramacao.getOrdemServico();
			
			if(osProgramacao.getOrdemServico().getId().intValue() == idOs.intValue()){
				
				acompanharActionForm.setIdOrdemServico(""+idOs);
				acompanharActionForm.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
				acompanharActionForm.setAlertaEquipeServico(helper.getAlertaEquipeDeServicoPerfilTipo());
				httpServletRequest.setAttribute("colecaoAlertaEquipeLogradouro",
					helper.getColecaoAlertaEquipeDeLogradouro());
				
				break;
			}
		}

		return retorno;
	}
	
	
}
