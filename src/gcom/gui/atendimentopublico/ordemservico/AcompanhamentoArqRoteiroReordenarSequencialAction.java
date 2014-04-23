package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] - Acompanhamento Arquivos Roteiro Reordenar Sequencial Ordem de Serviço
 * 
 * @author Thúlio Araújo
 *
 * @date 31/08/2011
 */
public class AcompanhamentoArqRoteiroReordenarSequencialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("reordenarSequencialOS");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
		acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		// Precisa pegar a unidade do usuario do login que esta na sessao
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataProgramacao());
		acompanharActionForm.setDataRoteiro(Util.formatarData(dataRoteiro));
		acompanharActionForm.setUnidadeLotacao(""+idUnidadeLotacao);

		this.reordenaSequencialNaProgramacaoNovaOrdem(acompanharActionForm,sessao);
		
		httpServletRequest.setAttribute("fecharPopup", "true");
		
		return retorno;
	}
	
	private void reordenaSequencialNaProgramacaoNovaOrdem(
			AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,HttpSession sessao){
		
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataProgramacao());
		
		short sequencialAtual 		= new Short(acompanharActionForm.getSequencialProgramacao());
		short sequencialInformado 	= new Short(acompanharActionForm.getSequencialProgramacaoPrevisto());
		String chaveArquivo = acompanharActionForm.getChaveArquivo();

		Collection<OSProgramacaoAcompanhamentoServico> colecaoOsProgramacaoAcompanhamentoServico = 
				Fachada.getInstancia().pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(dataRoteiro,
						Util.converterStringParaInteger(chaveArquivo));

		int valor = this.retornaUltimoSequencial(colecaoOsProgramacaoAcompanhamentoServico);
		
		if(sequencialInformado > valor){
			throw new ActionServletException("atencao.sequencial_programacao_maior_limite",null,""+valor);
		}
		
		Collection<Equipe> colecaoEquipe = Fachada.getInstancia().recuperaEquipeDaOSProgramacaoPorDataRoteiro(Util.converterStringParaInteger(acompanharActionForm.getIdOrdemServico()),
				dataRoteiro);
		Iterator<Equipe> itera = colecaoEquipe.iterator();
		Equipe equipe = (Equipe) itera.next();
		
		Fachada.getInstancia().reordenaSequencialOSProgramacaoAcompServico(dataRoteiro,sequencialInformado,equipe.getId(),sequencialAtual);
	}
		
	private int retornaUltimoSequencial(Collection<OSProgramacaoAcompanhamentoServico> colecaoOsProgramacaoAcompanhamentoServico){
		int valorSequencial = 0;
		if (colecaoOsProgramacaoAcompanhamentoServico != null && !colecaoOsProgramacaoAcompanhamentoServico.isEmpty()) {
			Iterator<OSProgramacaoAcompanhamentoServico> itera = colecaoOsProgramacaoAcompanhamentoServico.iterator();
			while (itera.hasNext()) {
				
				OSProgramacaoAcompanhamentoServico osProgramacaoAcompServico = (OSProgramacaoAcompanhamentoServico) itera.next();
				
				if(valorSequencial < osProgramacaoAcompServico.getSequencialProgramacao()){
					valorSequencial = osProgramacaoAcompServico.getSequencialProgramacao();
				}
			}
		}
		
		return valorSequencial;
	}
}
