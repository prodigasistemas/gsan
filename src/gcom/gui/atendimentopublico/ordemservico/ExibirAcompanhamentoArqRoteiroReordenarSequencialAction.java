package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1199] – Acompanhamento Arquivos Roteiro Exibir Reordenar Sequencial
 * 
 * @author Thúlio Araújo
 *
 * @date 31/08/2011
 */
public class ExibirAcompanhamentoArqRoteiroReordenarSequencialAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirReordenarSequencialOS");
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		String chaveOs = httpServletRequest.getParameter("chave");
		String chaveArquivo = httpServletRequest.getParameter("chaveArquivo");
		String dataProgramacao = httpServletRequest.getParameter("dataProgramacao");
		
		Collection<OSProgramacaoAcompanhamentoServico> colecaoOsProgramacaoAcompanhamentoServico = 
				Fachada.getInstancia().pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(Util.converteStringParaDate(dataProgramacao),
						Util.converterStringParaInteger(chaveArquivo));

		OrdemServico ordemServico = Fachada.getInstancia().recuperaOSPorId(new Integer(chaveOs));
		
		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico =  null;
		
		if (chaveArquivo != null){
			FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
			filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID, chaveArquivo));
			
			
			Collection<?> colecaoArquivoTxtAcompanhamentoServico = Fachada.getInstancia().pesquisar(
					filtroArquivoTextoAcompanhamentoServico,
				    ArquivoTextoAcompanhamentoServico.class.getName());
			
			arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) colecaoArquivoTxtAcompanhamentoServico
				    .iterator().next();
			
			if (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
				throw new ActionServletException("atencao.nao_possivel.reordenar_os.situacao_finalizado");
			}
		}
		
		if (ordemServico.getSituacao() != OrdemServico.SITUACAO_PENDENTE){
			throw new ActionServletException("atencao.situacao.diferente_pendente", "reordenar o sequencial da");
		}
		
		if (colecaoOsProgramacaoAcompanhamentoServico != null && !colecaoOsProgramacaoAcompanhamentoServico.isEmpty()) {
			Iterator<OSProgramacaoAcompanhamentoServico> itera = colecaoOsProgramacaoAcompanhamentoServico.iterator();
			while (itera.hasNext()) {
				
				OSProgramacaoAcompanhamentoServico osProgramacaoAcompServico = (OSProgramacaoAcompanhamentoServico) itera.next();
				
				if (osProgramacaoAcompServico.getOrdemServico().getId() == ordemServico.getId() ||
					osProgramacaoAcompServico.getOrdemServico().getId().equals(ordemServico.getId())){
					acompanharActionForm.setIdOrdemServico(chaveOs);
					acompanharActionForm.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
					acompanharActionForm.setSequencialProgramacao(Integer.toString(osProgramacaoAcompServico.getSequencialProgramacao()));
					acompanharActionForm.setChaveArquivo(chaveArquivo);
				}
			}
		}
		
		return retorno;
	}
}
