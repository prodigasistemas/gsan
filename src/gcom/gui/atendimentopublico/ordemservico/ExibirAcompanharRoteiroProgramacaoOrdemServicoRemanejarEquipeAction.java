package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAcompanharRoteiroProgramacaoOrdemServicoRemanejarEquipeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("remanejarEquipe");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
			
		String chaveOs = httpServletRequest.getParameter("chave");
		
		OrdemServico ordemServico = fachada.recuperaOSPorId(new Integer(chaveOs));
		
		if(ordemServico.getSituacao() == OrdemServico.SITUACAO_ENCERRADO.shortValue()){
			throw new ActionServletException("atencao.ordem_servico_encerrada_para_alocar");			
		}
		
		// Precisa pegar a unidade do usuario do login que esta na sessao
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();

		Date dataRoteiro = 
			Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());		
		
		Collection<Equipe> colecao = 
			fachada.recuperaEquipeDaOSProgramacaoPorDataRoteiro(new Integer(chaveOs),dataRoteiro);
		
		if(colecao != null && colecao.size() > 1){
			throw new ActionServletException("atencao.ordem_servico_programada_varias_equipes");			
		}
		
		Equipe equipeAtual = (Equipe) Util.retonarObjetoDeColecao(colecao);
		
		Collection<Equipe> colecaoEquipe = this.pesquisarEquipes(idUnidadeLotacao,equipeAtual.getId());
		
		acompanharActionForm.setIdEquipeAtual(""+equipeAtual.getId());
		acompanharActionForm.setNomeEquipeAtual(equipeAtual.getNome());
		acompanharActionForm.setIdOrdemServico(chaveOs);
		acompanharActionForm.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
		acompanharActionForm.setEquipes(colecaoEquipe);
		
		
		return retorno;
	}
	
	/**
	 * Pesquisa todas as equipes que estão ligadas a unidade organizacional do usuario
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,id da Unidade Organizacional 
	 * @return Collection de Equipe
	 */
	private Collection<Equipe> pesquisarEquipes(Integer idUnidadeLotacao,Integer idEquipe){

		Collection<Equipe> retorno = new ArrayList();
		
		FiltroEquipe filtroEquipe = new FiltroEquipe();

		filtroEquipe.adicionarParametro(new ParametroSimples(
			FiltroEquipe.ID_UNIDADE_ORGANIZACIONAL,idUnidadeLotacao));

		filtroEquipe.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroEquipe.ID,idEquipe));
		
		filtroEquipe.adicionarParametro(new ParametroSimples(
				FiltroEquipe.INDICADOR_USO,ConstantesSistema.SIM));

		filtroEquipe.setCampoOrderBy(FiltroEquipe.NOME);

		retorno = Fachada.getInstancia().pesquisar(filtroEquipe, Equipe.class.getName());
		
		return retorno;

	}
	
	
}
