package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o processamento da página de efetuar corte de ligação de
 * água
 * 
 * @author Leandro Cavalcanti
 * @date 12/07/2006
 */

public class EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm mudancaFaturamentoLigacaoAguaActionForm = (EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String ordemServicoId = mudancaFaturamentoLigacaoAguaActionForm
				.getIdOrdemServico();
		// String volumeMinimoFixado =
		// mudancaFaturamentoLigacaoAguaActionForm.getVolumeMinimoFixado();
		// String novaSituacaoEsgoto =
		// mudancaFaturamentoLigacaoAguaActionForm.getNovaSitLigacaoEsgoto();
		// String tipoServico =
		// mudancaFaturamentoLigacaoAguaActionForm.getTipoServico();
		String idServicoMotivoNaoCobranca = mudancaFaturamentoLigacaoAguaActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = mudancaFaturamentoLigacaoAguaActionForm
				.getPercentualCobranca();

		OrdemServico ordemServico = null;
		// Validar Ordem de Serviço
		if (ordemServicoId != null && !ordemServicoId.equals("")) {
			ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
			if (ordemServico == null) {
				throw new ActionServletException(
						"atencao.ordem_servico_inexistente", null,
						"ORDEM DE SERVIÇO INEXISTENTE");

			}
		}

		// Imovel imovel = null;
		// if (sessao.getAttribute("imovel") != null) {
		// imovel = (Imovel) sessao.getAttribute("imovel");
		// }
		/*---------------------  Início Dados do Corte da Ligação de Água  ------------------------*/

		// Validar Data da Mudança recebida do encerramento da Ordem de Serviço.
		// Date dataMudanca = null;
		// dataMudanca =
		// Util.converteStringParaDate(mudancaFaturamentoLigacaoAguaActionForm
		// .getDataMudanca());


		if (ordemServico != null
				&& mudancaFaturamentoLigacaoAguaActionForm.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			if (idServicoMotivoNaoCobranca != null) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(
						idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(
						mudancaFaturamentoLigacaoAguaActionForm
								.getPercentualCobranca()));
			}

			BigDecimal valorAtual = new BigDecimal(0);
			if (mudancaFaturamentoLigacaoAguaActionForm.getValorDebito() != null) {
				String valorDebito = mudancaFaturamentoLigacaoAguaActionForm
						.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}
		}

		String qtdParcelas = mudancaFaturamentoLigacaoAguaActionForm
				.getQuantidadeParcelas();

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		integracaoComercialHelper.setLigacaoEsgoto((LigacaoEsgoto) sessao
				.getAttribute("ligacaoEsgoto"));
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);
		integracaoComercialHelper.setUsuarioLogado(usuario);

		if (mudancaFaturamentoLigacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

			fachada
					.efetuarMudancaSituacaoFaturamentoLiagacaoEsgoto(integracaoComercialHelper);
		} else {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			sessao.setAttribute("integracaoComercialHelper",
					integracaoComercialHelper);

			if (sessao.getAttribute("semMenu") == null) {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoAction");
			} else {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}

		/*
		 * if(ordemServico.getServicoTipo().getDebitoTipo() != null &&
		 * (ordemServico.getServicoNaoCobrancaMotivo() == null ||
		 * ordemServico.getServicoNaoCobrancaMotivo().getId() ==
		 * ConstantesSistema.NUMERO_NAO_INFORMADO)){
		 * fachada.gerarDebitoOrdemServico(ordemServico.getId(),
		 * ordemServico.getServicoTipo().getDebitoTipo().getId(),
		 * Util.calcularValorDebitoComPorcentagem(valorAtual,
		 * mudancaFaturamentoLigacaoAguaActionForm.getPercentualCobranca()) ,
		 * new
		 * Integer(mudancaFaturamentoLigacaoAguaActionForm.getQuantidadeParcelas()).intValue()); }
		 */

		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			// Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest,
					"Mudança de Faturamento da Ligação de Esgoto"
							+ " efetuada com Sucesso", "",
					"exibirEfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction.do");
		}

		return retorno;
	}
}
