package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroMensagemAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.MensagemAcompanhamentoServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC12008] Cadastrar Mensagens de Acompanhamento de Serviços Fluxo Principal:
 * Item 2
 * 
 * @author Magno Gouveia
 * @since 11/08/2011
 */
public class ExibirCadastrarMensagemAcompanhamentoServicoPopupAction extends GcomAction {

	public static final String LIMPAR_FORM_PARAMETER = "limpar";

	public static final String LIMPAR_FORM_PARAMETER_RESPONSE = "ok";

	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {

		ActionForward retorno = actionMapping.findForward("exibirCadastrarMensagemAcompanhamentoServico");

		Fachada fachada = Fachada.getInstancia();

		CadastrarMensagemAcompanhamentoServicoPopupActionForm form = (CadastrarMensagemAcompanhamentoServicoPopupActionForm) actionForm;

		/*
		 * limpa o formulário
		 */
		if (httpServletRequest.getParameter(LIMPAR_FORM_PARAMETER) != null
				&& httpServletRequest.getParameter(LIMPAR_FORM_PARAMETER).equals(LIMPAR_FORM_PARAMETER_RESPONSE)) {

			form.setIds(null);
			form.setMensagem(null);
		}

		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico = null;
		if (this.isValidParameterRequest(httpServletRequest, CadastrarMensagemAcompanhamentoServicoPopupAction.TXAC_ID_PARAMETER)) {

			FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
			filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID,
																							httpServletRequest.getParameter(CadastrarMensagemAcompanhamentoServicoPopupAction.TXAC_ID_PARAMETER)));
			filtroArquivoTextoAcompanhamentoServico.adicionarCaminhoParaCarregamentoEntidade("equipe");
			filtroArquivoTextoAcompanhamentoServico.adicionarCaminhoParaCarregamentoEntidade("situacaoTransmissaoLeitura");

			arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroArquivoTextoAcompanhamentoServico, ArquivoTextoAcompanhamentoServico.class.getName()));
		}

		if (arquivoTextoAcompanhamentoServico == null) {
			throw new ActionServletException(	"atencao.naocadastrado",
												null,
												"Arquivo de Acompanhamento de Roteiro");
		}

		if (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura() != null
				&& arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getDescricaoSituacao() != null) {
			httpServletRequest.setAttribute("situacaoTransmissaoLeitura", arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getDescricaoSituacao());
		} else {
			throw new ActionServletException(	"atencao.naocadastrado",
												null,
												"Situação Transmissão Leitura");
		}

		if (arquivoTextoAcompanhamentoServico.getEquipe() != null
				&& arquivoTextoAcompanhamentoServico.getEquipe().getNome() != null) {
			httpServletRequest.setAttribute("equipe", arquivoTextoAcompanhamentoServico.getEquipe().getNome());
		} else {
			throw new ActionServletException(	"atencao.naocadastrado",
												null,
												"Equipe");
		}

		FiltroMensagemAcompanhamentoServico filtro = new FiltroMensagemAcompanhamentoServico();
		filtro.adicionarParametro(new ParametroSimples(	FiltroMensagemAcompanhamentoServico.ARQUIVO_TEXTO_ACOMPANHAMENTO_SERVICO_ID,
														httpServletRequest.getParameter(CadastrarMensagemAcompanhamentoServicoPopupAction.TXAC_ID_PARAMETER)));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroMensagemAcompanhamentoServico.ARQUIVO_TEXTO_ACOMPANHAMENTO_SERVICO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroMensagemAcompanhamentoServico.USUARIO);

		Collection<MensagemAcompanhamentoServico> mensagens = fachada.pesquisar(filtro, MensagemAcompanhamentoServico.class.getName());

		if (!Util.isVazioOrNulo(mensagens)) {
			httpServletRequest.setAttribute("colecaoMensagens", mensagens);
		}

		return retorno;
	}

	/**
	 * Método responsável por verificar se o parâmetro foi enviado e se este é
	 * um inteiro, já que os parâmetros devem ser id
	 * 
	 * @param httpServletRequest
	 * @param paramName
	 * @return true caso o parametro recebido seja um inteiro válido
	 */
	private boolean isValidParameterRequest(HttpServletRequest httpServletRequest, String paramName) {
		boolean retorno = false;
		if (httpServletRequest.getParameter(paramName) != null
				&& httpServletRequest.getParameter(paramName).trim().length() != 0) {
			Integer paramValue = Util.converterStringParaInteger((String) httpServletRequest.getParameter(paramName));
			if (paramValue != null) {
				retorno = true;
			} else {
				throw new ActionServletException(	"atencao.invalido_zero",
													paramName);
			}
		} else {
			throw new ActionServletException(	"atencao.naoinformado",
												paramName);
		}

		return retorno;
	}
}
