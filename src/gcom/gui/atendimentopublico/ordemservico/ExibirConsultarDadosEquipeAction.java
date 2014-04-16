package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosEquipe;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarDadosEquipeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarDadosEquipe");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Form
		ConsultarDadosEquipeActionForm 
			consultarDadosEquipeActionForm = (ConsultarDadosEquipeActionForm) actionForm;
		
		if(httpServletRequest.getParameter("ehPopup") != null){
			sessao.setAttribute("ehPopup","true");	
		}
		
		Integer idEquipe = null;
		Equipe equipe = null;
		ObterDadosEquipe obterDadosEquipe = null;
		
		//Caso venha da tela de ordem_servico_roteiro_acompanhamento.jsp
		//O id não eh informada ,o id esta na sessao 
		if(httpServletRequest.getParameter("idEquipe") == null){

			String chave = httpServletRequest.getParameter("chave");
			
			HashMap mapEquipe = 
				(HashMap) sessao.getAttribute("mapEquipe");
			
			equipe = (Equipe) mapEquipe.get(chave);
			obterDadosEquipe = Fachada.getInstancia().obterDadosEquipe(equipe.getId());
		
		} else {
			
			idEquipe = new Integer(httpServletRequest.getParameter("idEquipe"));
			obterDadosEquipe = Fachada.getInstancia().obterDadosEquipe(idEquipe);
			equipe = obterDadosEquipe.getEquipe();
		}
		
		consultarDadosEquipeActionForm.setIdEquipe(""+equipe.getId());
		consultarDadosEquipeActionForm.setNomeEquipe(equipe.getNome());
		consultarDadosEquipeActionForm.setPlacaVeiculo(equipe.getPlacaVeiculo());
		
		//Para exibir a carga horária em horas e não em minutos - Raphael Rossiter em 13/02/2007
		consultarDadosEquipeActionForm.setCargaTrabalhoDia(""+ (equipe.getCargaTrabalho() / 60));
		
		consultarDadosEquipeActionForm.setCodigoDdd(equipe.getCodigoDdd().toString());
		consultarDadosEquipeActionForm.setNumeroTelefone(equipe.getNumeroTelefone().toString());
		consultarDadosEquipeActionForm.setNumeroImei(equipe.getNumeroImei().toString());
		
		consultarDadosEquipeActionForm.setUnidadeOrganizacionalId(""+equipe.getUnidadeOrganizacional().getId());
		consultarDadosEquipeActionForm.setUnidadeOrganizacionalDescricao(equipe.getUnidadeOrganizacional().getDescricao());
		consultarDadosEquipeActionForm.setTipoPerfilServicoId(""+equipe.getServicoPerfilTipo().getId());
		consultarDadosEquipeActionForm.setTipoPerfilServicoDescricao(equipe.getServicoPerfilTipo().getDescricao());
		consultarDadosEquipeActionForm.setEquipeComponentes(obterDadosEquipe.getColecaoEquipeComponentes());
		
		return retorno;
	}
	
}
