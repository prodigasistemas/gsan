package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAcompanharRoteiroProgramacaoOrdemServicoAlocaEquipeAction extends GcomAction {
	
	private Set<String> colecaoEquipesDaOrdemServico = null;
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("alocaOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		colecaoEquipesDaOrdemServico = new HashSet<String>();
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
			
		String chaveOs = httpServletRequest.getParameter("chave");
		
		OrdemServico ordemServico = 
			Fachada.getInstancia().recuperaOSPorId(new Integer(chaveOs));
		
		if(ordemServico.getSituacao() == OrdemServico.SITUACAO_ENCERRADO.shortValue()){
			throw new ActionServletException("atencao.ordem_servico_encerrada_para_alocar");			
		}
		
		Date dataRoteiro = 
			Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());		
		
		// Precisa pegar a unidade do usuario do login que esta na sessao
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();
		
		Integer idEquipePrincipal = 
			this.retornaEquipePrincipalParaOs(dataRoteiro,idUnidadeLotacao,new Integer(chaveOs));
		
		Collection<Equipe> colecaoEquipe = this.pesquisarEquipe(idUnidadeLotacao);

		acompanharActionForm.setIdOrdemServico(chaveOs);
		acompanharActionForm.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
		acompanharActionForm.setIdEquipePrincipal(""+idEquipePrincipal);
		acompanharActionForm.setEquipes(colecaoEquipe);
		
		
		String[] array = colecaoEquipesDaOrdemServico.toArray(new String[colecaoEquipesDaOrdemServico.size()]);
		acompanharActionForm.setEquipeSelecionada(array);
		acompanharActionForm.setEquipeSelecionadaAtual(array);
		
		
		return retorno;
	}
	
	/**
	 * Retorna a equipe principal da colecao da ordem de Servico Programacao 
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 * 
	 * @param data do roteiro,unidadeLotacao,
	 */
	private Integer retornaEquipePrincipalParaOs(Date dataRoteiro,Integer idUnidadeLotacao,Integer idOs){

		Integer idPrincipal = null;
		
		Collection<OrdemServicoProgramacao> colecaoOrdemServicoProgramacao = 
			Fachada.getInstancia().recuperaOSProgramacaoPorDataRoteiroUnidade(dataRoteiro,idUnidadeLotacao);
		
		if(colecaoOrdemServicoProgramacao != null && !colecaoOrdemServicoProgramacao.isEmpty()){
			
			Iterator<OrdemServicoProgramacao> itera = colecaoOrdemServicoProgramacao.iterator();
			
			while (itera.hasNext()) {
				
				OrdemServicoProgramacao ordemServicoProgramacao = itera.next();
				OrdemServico os = ordemServicoProgramacao.getOrdemServico();
				
				if(idOs.intValue() == os.getId().intValue() && 
					ordemServicoProgramacao.getIndicadorAtivo() == ConstantesSistema.SIM.shortValue()){
					
					if(ordemServicoProgramacao.getIndicadorEquipePrincipal() == ConstantesSistema.SIM.shortValue()){
						idPrincipal = ordemServicoProgramacao.getEquipe().getId();
					}
					
					colecaoEquipesDaOrdemServico.add(ordemServicoProgramacao.getEquipe().getId().toString());
				}
				
				
			}
		}
		return idPrincipal;
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
	private Collection<Equipe> pesquisarEquipe(Integer idUnidadeLotacao){

		Collection<Equipe> retorno = new ArrayList<Equipe>();
		
		FiltroEquipe filtroEquipe = new FiltroEquipe();

		filtroEquipe.adicionarParametro(new ParametroSimples(
			FiltroEquipe.ID_UNIDADE_ORGANIZACIONAL,idUnidadeLotacao));

		filtroEquipe.adicionarParametro(new ParametroSimples(
				FiltroEquipe.INDICADOR_USO,ConstantesSistema.SIM));

		filtroEquipe.setCampoOrderBy(FiltroEquipe.NOME);

		retorno = Fachada.getInstancia().pesquisar(filtroEquipe, Equipe.class.getName());
		
		return retorno;

	}
	
	
}
