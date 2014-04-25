package gcom.gui.arrecadacao;

import gcom.arrecadacao.GuiaDevolucao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rafael Corrêa
 * @date 09/05/2006
 */
public class AtualizarGuiaDevolucaoAction extends GcomAction {

	/**
	 * Permite a Inclusao de Devolucoes
	 * 
	 * [UC0271] Inserir Devolucoes
	 * 
	 * @author Fernanda Paiva
	 * @date 10/03/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		AtualizarGuiaDevolucaoActionForm atualizarGuiaDevolucaoActionForm = (AtualizarGuiaDevolucaoActionForm) actionForm;

		String idGuiaDevolucao = atualizarGuiaDevolucaoActionForm
				.getIdGuiaDevolucao();
		String idRegistroAtendimento = atualizarGuiaDevolucaoActionForm
				.getIdRegistroAtendimento();
		String idOrdemServico = atualizarGuiaDevolucaoActionForm
				.getIdOrdemServico();
		String tipoDocumento = atualizarGuiaDevolucaoActionForm
				.getDocumentoTipoHidden();
		String idLocalidade = atualizarGuiaDevolucaoActionForm
				.getIdLocalidadeHidden();
		String referenciaConta = atualizarGuiaDevolucaoActionForm
				.getReferenciaConta();
		String idGuiaPagamento = atualizarGuiaDevolucaoActionForm
				.getIdGuiaPagamento();
		String idDebitoACobrar = atualizarGuiaDevolucaoActionForm
				.getIdDebitoACobrar();
		String idDebitoTipo = atualizarGuiaDevolucaoActionForm
				.getIdTipoDebitoHidden();
		String dataValidade = atualizarGuiaDevolucaoActionForm
				.getDataValidade();
		String valorDevolucao = atualizarGuiaDevolucaoActionForm
				.getValorDevolucao();

		GuiaDevolucao guiaDevolucao = (GuiaDevolucao) sessao
				.getAttribute("guiaDevolucaoAtualizar");

//		FiltroGuiaDevolucao filtroGuiaDevolucao = new FiltroGuiaDevolucao();
//		filtroGuiaDevolucao.adicionarParametro(new ParametroSimples(
//				FiltroGuiaDevolucao.ID, idGuiaDevolucao));
//
//		Collection colecaoGuiaDevolucao = fachada.pesquisar(
//				filtroGuiaDevolucao, GuiaDevolucao.class.getName());
//
//		if (colecaoGuiaDevolucao != null && !colecaoGuiaDevolucao.isEmpty()) {
//			guiaDevolucao = (GuiaDevolucao) colecaoGuiaDevolucao.iterator()
//					.next();
//		} else {
//			throw new ActionServletException("atencao.atualizacao.timestamp");
//		}

		RegistroAtendimento registroAtendimento = new RegistroAtendimento();

		DocumentoTipo documentoTipo = new DocumentoTipo();

		guiaDevolucao.setId(new Integer(idGuiaDevolucao));

		if (idRegistroAtendimento != null
				&& !idRegistroAtendimento.trim().equals("")) {
			registroAtendimento.setId(new Integer(idRegistroAtendimento));
			guiaDevolucao.setRegistroAtendimento(registroAtendimento);
		} else {
			throw new ActionServletException("atencao.Informe_entidade", null,
					"Registro de Atendimento");
		}

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(new Integer(idOrdemServico));
			guiaDevolucao.setOrdemServico(ordemServico);
		} else {
			guiaDevolucao.setOrdemServico(null);
		}

		if (tipoDocumento != null
				&& !tipoDocumento
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			documentoTipo.setId(new Integer(tipoDocumento));
			guiaDevolucao.setDocumentoTipo(documentoTipo);
		} else {
			throw new ActionServletException("atencao.Informe_entidade", null,
					"Tipo de Documento");
		}

		if (idLocalidade != null && !idLocalidade.equals("")) {
			Localidade localidade = new Localidade();
			localidade.setId(new Integer(idLocalidade));
			guiaDevolucao.setLocalidade(localidade);
		} else {
			guiaDevolucao.setLocalidade(null);
		}

		if (referenciaConta != null && !referenciaConta.trim().equals("")) {
			if (Util.validarAnoMes(referenciaConta)) {
				new ActionServletException(
						"atencao.adicionar_debito_ano_mes_referencia_invalido",
						null, referenciaConta);
			}
			Conta conta = new Conta();
			String referenciaContaFormatada = Util
					.formatarMesAnoParaAnoMesSemBarra(referenciaConta);
			conta.setReferencia(new Integer(referenciaContaFormatada)
					.intValue());
			guiaDevolucao.setConta(conta);
		} else {
			guiaDevolucao.setConta(null);
		}

		if (idGuiaPagamento != null && !idGuiaPagamento.trim().equals("")) {
			GuiaPagamento guiaPagamento = new GuiaPagamento();
			guiaPagamento.setId(new Integer(idGuiaPagamento));
			guiaDevolucao.setGuiaPagamento(guiaPagamento);
		} else {
			guiaDevolucao.setGuiaPagamento(null);
		}

		if (idDebitoACobrar != null && !idDebitoACobrar.trim().equals("")) {
			
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
			debitoACobrarGeral.setId(new Integer(idDebitoACobrar));
			
			guiaDevolucao.setDebitoACobrarGeral(debitoACobrarGeral);
		} else {
			guiaDevolucao.setDebitoACobrarGeral(null);
		}

		if (idDebitoTipo != null && !idDebitoTipo.trim().equals("")) {
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(new Integer(idDebitoTipo));
			guiaDevolucao.setDebitoTipo(debitoTipo);
		} else {
			guiaDevolucao.setDebitoTipo(null);
		}

		if (dataValidade != null && !dataValidade.trim().equals("")) {
			guiaDevolucao.setDataValidade(Util
					.converteStringParaDate(dataValidade));
		} else {
			throw new ActionServletException("atencao.Informe_entidade", null,
					"Data de Validade");
		}

		if (valorDevolucao != null && !valorDevolucao.trim().equals("")) {
			BigDecimal valorDevolucaoFormatado = new BigDecimal(valorDevolucao
					.replace(".", "").replace(",", "."));
			if (valorDevolucaoFormatado.equals(new BigDecimal("0.00"))
					|| valorDevolucaoFormatado.equals(new BigDecimal("0"))) {
				throw new ActionServletException("atencao.Informe_entidade",
						null, "Valor da Devolução");
			}
			guiaDevolucao.setValorDevolucao(valorDevolucaoFormatado);
		}

		fachada.atualizarGuiaDevolucao(guiaDevolucao,usuarioLogado);

		montarPaginaSucesso(httpServletRequest,
				"Guia de Devolução " + guiaDevolucao.getId().toString()
						+ " atualizada com sucesso.",
				"Realizar outra Manutenção de Guia de Devololução",
				"exibirFiltrarGuiaDevolucaoAction.do?menu=sim");

		return retorno;

	}

}
