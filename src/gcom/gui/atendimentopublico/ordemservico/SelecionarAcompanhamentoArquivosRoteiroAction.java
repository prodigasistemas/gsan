package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] – Acompanhar Arquivos de Roteiro
 * 
 * @author Thúlio Araújo
 *
 * @date 15/07/2011
 */
public class SelecionarAcompanhamentoArquivosRoteiroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAcompanhamentoArquivosRoteiro");
		
		HttpSession sessao = httpServletRequest.getSession(false);		

		// Precisa pegar a unidade do usuario do login que esta na sessao
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();
		
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanhamentoArquivosRoteiroActionForm = 
			(AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		String limparSessao = httpServletRequest.getParameter("limparSessao");

		String metodo = httpServletRequest.getParameter("metodo");
		if (Util.verificarNaoVazio(metodo) && metodo.equals("visualizarFotos")){
			Integer idOS = Integer.valueOf(httpServletRequest.getParameter("idOS"));
			
			ArrayList<OrdemServicoFoto> fotos = (ArrayList<OrdemServicoFoto>) Fachada.getInstancia().pesquisarFotosOrdemServico(idOS, true);
			if (!Util.isVazioOrNulo(fotos)){
				sessao.setAttribute("colecaoFotoOS", fotos);
				sessao.setAttribute("numeroFotos", fotos.size());
				sessao.setAttribute("idFoto", fotos.get(0).getId().intValue());
			} else {
				throw new ActionServletException("atencao.ordem.servico.nao.possui.foto");
			}
			retorno = actionMapping.findForward("fotos");
		} else {
			Collection<?> colecaoAcompanhamentoArquivosRoteiro = fachada.pesquisarAcompanhamentoArquivosRoteiro(
					acompanhamentoArquivosRoteiroActionForm.getDataProgramacao(),
					acompanhamentoArquivosRoteiroActionForm.getIdEmpresa(),
					acompanhamentoArquivosRoteiroActionForm.getIdSituacao(),
					idUnidadeLotacao);
			
			if (!colecaoAcompanhamentoArquivosRoteiro.isEmpty()){
				sessao.setAttribute("achou","1");
			} else {
				throw new ActionServletException("atencao.nao_existe_dados_filtro");
			}
			sessao.setAttribute("colecaoAcompanhamentoArquivosRoteiro", colecaoAcompanhamentoArquivosRoteiro);
		}
		
		Date dataInformada = Util.converteStringParaDate(acompanhamentoArquivosRoteiroActionForm.getDataProgramacao());
		Date dataAtual = Util.formatarDataSemHora(new Date());
		
		if (Util.compararData(dataInformada, dataAtual) != -1){
			httpServletRequest.setAttribute("exibirBotoes", true);
		}
		
		if (limparSessao != null && !limparSessao.equals("")){
			sessao.removeAttribute("dataRoteiroInformarSituacao");
			sessao.removeAttribute("chaveOsInformarSituacao");
			sessao.removeAttribute("chaveArquivoInformarSituacao");
		}
		
		String idOs = (String)sessao.getAttribute("chaveOsInformarSituacao");
		
		if (idOs != null && !idOs.equals("")){
			this.atualizarInformaSituacaoOrdemServico(sessao);
		}
		
		httpServletRequest.setAttribute("fecharPopup", "true");
		
		return retorno;
	}
	
	private void atualizarInformaSituacaoOrdemServico(HttpSession sessao){
		
		Fachada fachada = Fachada.getInstancia();
		
		Date dataRoteiro = (Date)sessao.getAttribute("dataRoteiroInformarSituacao");
		String idOs = (String) sessao.getAttribute("chaveOsInformarSituacao");
		String chaveArquivo = (String) sessao.getAttribute("chaveArquivoInformarSituacao");
		
		OrdemServico ordemServico = fachada.recuperaOSPorId(Util.converterStringParaInteger(idOs));
		
		Integer motivoNaoEncerramentoOs = null;
		
		fachada.atualizarOrdemProgramacaoAcompServicoInformarSituacao(Util.converterStringParaInteger(chaveArquivo),dataRoteiro,
				ordemServico.getId(), ordemServico.getSituacao(), motivoNaoEncerramentoOs);
		
		boolean naoInformaIndicadorAtivo = false;
		
		fachada.atualizarOrdemServicoProgramacaoSituacaoOs(ordemServico.getId(),
				dataRoteiro,ordemServico.getSituacao(),motivoNaoEncerramentoOs, naoInformaIndicadorAtivo);
		
		sessao.removeAttribute("dataRoteiroInformarSituacao");
		sessao.removeAttribute("chaveOsInformarSituacao");
		sessao.removeAttribute("chaveArquivoInformarSituacao");
	}
}
