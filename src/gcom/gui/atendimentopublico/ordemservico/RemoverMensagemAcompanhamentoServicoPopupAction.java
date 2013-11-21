package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.MensagemAcompanhamentoServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Magno Gouveia
 * @since 12/08/2011
 */
public class RemoverMensagemAcompanhamentoServicoPopupAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {

		ActionForward retorno = actionMapping.findForward("telaSucessoPopup");
		
		Fachada fachada = Fachada.getInstancia();

		CadastrarMensagemAcompanhamentoServicoPopupActionForm form = (CadastrarMensagemAcompanhamentoServicoPopupActionForm) actionForm;
		
		FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
		filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID, httpServletRequest.getParameter(CadastrarMensagemAcompanhamentoServicoPopupAction.TXAC_ID_PARAMETER)));
		filtroArquivoTextoAcompanhamentoServico.adicionarCaminhoParaCarregamentoEntidade("equipe");
		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroArquivoTextoAcompanhamentoServico, ArquivoTextoAcompanhamentoServico.class.getName()));
		
		String[] ids = form.getIds();

		// FS0003 - Verificar Seleção de Mensagem
		if (ids == null || ids.length == 0) {
			throw new ActionServletException("atencao.nenhuma_mensagem_selecionada");
		}
		
		// FS0004 - Verificar Situação das Mensagens
		/* 
		 * Esta validação foi realizada no ExibirCadastrarMensagemAcompanhamentoServicoAction,
		 * não permitindo a seleção de uma mensagem cuja situação seja 'RECEBIDA', 
		 * sendo desnecessário o tratamento novamente
		 */
		
		fachada.remover(ids, MensagemAcompanhamentoServico.class.getName(), null, null);

		montarPaginaSucesso(httpServletRequest, 
							"Mensagens para a equipe " + arquivoTextoAcompanhamentoServico.getEquipe().getNome() + " removidas com sucesso.",
							"Retorna para cadastramento de mensagens",
							"exibirCadastrarMensagemAcompanhamentoServicoAction.do" + CadastrarMensagemAcompanhamentoServicoPopupAction.adicionarParametroIdArquivo(httpServletRequest));

		return retorno;
	}
}