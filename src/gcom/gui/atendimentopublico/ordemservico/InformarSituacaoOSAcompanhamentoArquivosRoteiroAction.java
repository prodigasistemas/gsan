package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] - Acompanhamento Arquivos Roteiro Informar Situação Ordem de Serviço
 * 
 * @author Thúlio Araújo
 *
 * @date 25/08/2011
 */
public class InformarSituacaoOSAcompanhamentoArquivosRoteiroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("informarSituacaoOS");
		
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

		this.informaSituacaoOrdemServico(acompanharActionForm,usuario,httpServletRequest);
		
		httpServletRequest.setAttribute("fecharPopup", "true");
		
		return retorno;
	}
	
	private void informaSituacaoOrdemServico(
		AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,Usuario usuario,HttpServletRequest httpServletRequest){
		
		Fachada fachada = Fachada.getInstancia();
		
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		
		Short novaSituacaoOs = new Short(acompanharActionForm.getSituacaoOrdemServico());

		Integer idOrdemServico = new Integer(acompanharActionForm.getIdOrdemServico());
		
		OrdemServico ordemServico = fachada.recuperaOSPorId(idOrdemServico);
		
		ordemServico.setSituacao(novaSituacaoOs);
		
		Integer motivoNaoEncerramentoOs = null;
		
		if(acompanharActionForm.getMotivoNaoEncerramento() != null && 
				!acompanharActionForm.getMotivoNaoEncerramento().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			motivoNaoEncerramentoOs = new Integer(acompanharActionForm.getMotivoNaoEncerramento());
		}
		
		fachada.atualizarOrdemProgramacaoAcompServicoInformarSituacao(Util.converterStringParaInteger(acompanharActionForm.getIdAcompanhamentoArquivosRoteiro()),
				dataRoteiro, idOrdemServico, novaSituacaoOs, motivoNaoEncerramentoOs);
		
		boolean naoInformaIndicadorAtivo = false;
		
		fachada.atualizarOrdemServicoProgramacaoSituacaoOs(idOrdemServico,
				dataRoteiro,novaSituacaoOs,motivoNaoEncerramentoOs, naoInformaIndicadorAtivo);
		
		fachada.atualizarOrdemServico(ordemServico,usuario);
	}
}
