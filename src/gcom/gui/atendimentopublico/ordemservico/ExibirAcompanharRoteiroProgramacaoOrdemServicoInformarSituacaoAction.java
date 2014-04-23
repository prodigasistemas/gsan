package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOsProgramNaoEncerMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAcompanharRoteiroProgramacaoOrdemServicoInformarSituacaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("situacaoOs");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
			
		String chaveOs = httpServletRequest.getParameter("chave");
		String chaveEquipe = httpServletRequest.getParameter("chaveEquipe");
		
		OrdemServico ordemServico = fachada.recuperaOSPorId(new Integer(chaveOs));
		
		if(ordemServico.getSituacao() == OrdemServico.SITUACAO_ENCERRADO.shortValue()){
			throw new ActionServletException("atencao.ordem_servico_encerrada_para_alocar");
		}

		Date dataRoteiro = 
			Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());		
		
		fachada.verificaExitenciaProgramacaoAtivaParaDiasAnteriores(new Integer(chaveOs),dataRoteiro);
		
		acompanharActionForm.setIdOrdemServico(chaveOs);
		acompanharActionForm.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
		acompanharActionForm.setChaveEquipe(chaveEquipe);
		
		ObterDescricaoSituacaoOSHelper obter = fachada.obterDescricaoSituacaoOS(new Integer(chaveOs));
		acompanharActionForm.setSituacaoAtual(obter.getDescricaoSituacao());
		
		this.pesquisarOsProgramNaoEncerMotivo(httpServletRequest);

		return retorno;
	}
	
	/**
	 * Pesquisa todas as OsProgramNaoEncerMotivo
	 * 
	 * @author Rafael Pinto
	 * @date 25/08/2006
	 * @return Collection de OsProgramNaoEncerMotivo
	 */
	private Collection<OsProgramNaoEncerMotivo> pesquisarOsProgramNaoEncerMotivo(
			HttpServletRequest httpServletRequest){

		Collection<OsProgramNaoEncerMotivo> retorno = new ArrayList();
		
		FiltroOsProgramNaoEncerMotivo filtroOsProgramNaoEncerMotivo = new FiltroOsProgramNaoEncerMotivo();

		filtroOsProgramNaoEncerMotivo.setCampoOrderBy(FiltroOsProgramNaoEncerMotivo.DESCRICAO);

		retorno = 
			Fachada.getInstancia().pesquisar(filtroOsProgramNaoEncerMotivo, 
					OsProgramNaoEncerMotivo.class.getName());
		
		httpServletRequest.setAttribute("colecaoMotivoNaoEncerramento", retorno );
		
		return retorno;

	}
	
	
}
