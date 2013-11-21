package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroMensagemAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.MensagemAcompanhamentoServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * [UC12008] Cadastrar Mensagens de Acompanhamento de Serviços
 * 
 * @author Magno Gouveia
 * @since 11/08/2011
 */
public class CadastrarMensagemAcompanhamentoServicoPopupAction extends GcomAction {

	public static final String TXAC_ID_PARAMETER = "txac_id";

	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {
		
		Integer MAX_LENGTH = 60;

		ActionForward retorno = actionMapping.findForward("telaSucessoPopup");

		CadastrarMensagemAcompanhamentoServicoPopupActionForm form = (CadastrarMensagemAcompanhamentoServicoPopupActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
		filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID,
																						httpServletRequest.getParameter(TXAC_ID_PARAMETER)));
		filtroArquivoTextoAcompanhamentoServico.adicionarCaminhoParaCarregamentoEntidade("equipe");
		filtroArquivoTextoAcompanhamentoServico.adicionarCaminhoParaCarregamentoEntidade("situacaoTransmissaoLeitura");
		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroArquivoTextoAcompanhamentoServico, ArquivoTextoAcompanhamentoServico.class.getName()));

		// FS0001 - Validar Situação do Arquivo
		if (!arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.EM_CAMPO)) {
			throw new ActionServletException("atencao.situacao_diferente_em_campo");
		}

		// FS0002 - Validar Mensagem
		if (form.getMensagem().trim().equals("")) {
			throw new ActionServletException("atencao.mensagem_invalida");
		}

		//Verifica se existe uma mensagem que nao foi recebida ainda pela equipe
		FiltroMensagemAcompanhamentoServico filtroMensagemAcompanhamentoServico = new FiltroMensagemAcompanhamentoServico();
		filtroMensagemAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroMensagemAcompanhamentoServico.ARQUIVO_TEXTO_ACOMPANHAMENTO_SERVICO_ID,
				arquivoTextoAcompanhamentoServico.getId()));
		
		Collection colecaoMensagem = fachada.pesquisar(filtroMensagemAcompanhamentoServico,MensagemAcompanhamentoServico.class.getName());
		
		if (colecaoMensagem != null && !colecaoMensagem.equals(null)){
			Iterator<MensagemAcompanhamentoServico> itera = colecaoMensagem.iterator();
			while (itera.hasNext()) {
				MensagemAcompanhamentoServico mensagemAcompanhamentoServico = (MensagemAcompanhamentoServico) itera.next();
				if (mensagemAcompanhamentoServico.getIndicadorSituacao() == ConstantesSistema.NAO.intValue() &&
						mensagemAcompanhamentoServico.getIndicadorSituacao().equals(ConstantesSistema.NAO.intValue())){
					throw new ActionServletException("atencao.nao_possivel.cadastrar_mensagem");
				}
			}
		}
		
		// SB0001 - Cadastrar Mensagem
		MensagemAcompanhamentoServico mensagem = new MensagemAcompanhamentoServico();

		String msg = form.getMensagem().trim();
		if (msg.length() < MAX_LENGTH) {
			MAX_LENGTH = msg.length();
		}

		mensagem.setDescricaoMensagem(msg.substring(0, MAX_LENGTH));
		mensagem.setUsuario(usuarioLogado);
		mensagem.setArquivoTextoAcompanhamentoServico(arquivoTextoAcompanhamentoServico);
		mensagem.setIndicadorSituacao(MensagemAcompanhamentoServico.INDICADOR_SITUACAO_CADASTRADA);
		mensagem.setUltimaAlteracao(new Date());

		fachada.inserir(mensagem);

		sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirCadastrarMensagemAcompanhamentoServicoAction.do"
				+ CadastrarMensagemAcompanhamentoServicoPopupAction.adicionarParametroIdArquivo(httpServletRequest));

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Mensagem para equipe "
				+ arquivoTextoAcompanhamentoServico.getEquipe().getNome() + " cadastrada com sucesso.", "Retorna para cadastramento de mensagens", "exibirCadastrarMensagemAcompanhamentoServicoAction.do"
				+ CadastrarMensagemAcompanhamentoServicoPopupAction.adicionarParametroIdArquivo(httpServletRequest));

		form.setMensagem("");

		return retorno;
	}

	public static String adicionarParametroIdArquivo(HttpServletRequest httpServletRequest) {
		return "?" + TXAC_ID_PARAMETER + "=" + httpServletRequest.getParameter(TXAC_ID_PARAMETER);
	}
}
