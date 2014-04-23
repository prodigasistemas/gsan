package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * @author Rômulo Aurélio
 * @date 12/07/2006
 */
public class EfetuarRestabelecimentoLigacaoAguaAction extends GcomAction {

	/**
	 * [UC0357] Efetuar Religação de Água
	 * 
	 * Este caso de uso permite efetuar a religação de água, sendo chamada pela
	 * funcionalidade que encerra a execução da ordem de serviço ou chamada
	 * diretamente do menu.
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		EfetuarRestabelecimentoLigacaoAguaActionForm efetuarRestabelecimentoLigacaoAguaActionForm = (EfetuarRestabelecimentoLigacaoAguaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		String idServicoMotivoNaoCobranca = efetuarRestabelecimentoLigacaoAguaActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = efetuarRestabelecimentoLigacaoAguaActionForm
				.getPercentualCobranca();

		OrdemServico ordemServico = null;

//		 Usuario logado no sistema
		 Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");


		// [SB0001] - Atualizar Imóvel/Ligação de Água

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			veioEncerrarOS = Boolean.FALSE;
		}

		if (sessao.getAttribute("ordemServico") == null) {

			String idOrdemServico = efetuarRestabelecimentoLigacaoAguaActionForm
					.getIdOrdemServico();

			if (efetuarRestabelecimentoLigacaoAguaActionForm
					.getIdOrdemServico() != null) {
				idOrdemServico = efetuarRestabelecimentoLigacaoAguaActionForm
						.getIdOrdemServico();
			} else {
				idOrdemServico = (String) httpServletRequest
						.getAttribute("veioEncerrarOS");
				efetuarRestabelecimentoLigacaoAguaActionForm
						.setDataRestabelecimento((String) httpServletRequest
								.getAttribute("dataEncerramento"));

				sessao
						.setAttribute(
								"caminhoRetornoIntegracaoComercial",
								httpServletRequest
										.getAttribute("caminhoRetornoIntegracaoComercial"));
			}

			if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

				ordemServico = fachada.recuperaOSPorId(new Integer(
						idOrdemServico));

				if (ordemServico != null) {

					fachada.validarExibirRestabelecimentoLigacaoAgua(
							ordemServico, veioEncerrarOS);
				} else {
					throw new ActionServletException(
							"atencao.ordem_servico.inexistente");
				}
			}
		} else {
			ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
		}
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		// regitrando operacao
		// ordemServico.setOperacaoEfetuada(operacaoEfetuada);
		// ordemServico.adicionarUsuario(usuario,
		// UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		// registradorOperacao.registrarOperacao(ordemServico);
		// fachada.efetuarReligacaoAgua(ordemServico);

		if (ordemServico != null
				&& efetuarRestabelecimentoLigacaoAguaActionForm
						.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			BigDecimal valorAtual = new BigDecimal(0);
			if (efetuarRestabelecimentoLigacaoAguaActionForm.getValorDebito() != null) {
				String valorDebito = efetuarRestabelecimentoLigacaoAguaActionForm
						.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}

			if (idServicoMotivoNaoCobranca != null && !idServicoMotivoNaoCobranca.equals("-1")) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(
						idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			/*
			 * Alterado por Raphael Rossiter em 24/07/2007
			 * OBJETIVO: Inserir o débito a cobrar com o valor correto
			 */
			
			/*if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(Util.formatarMoedaRealparaBigDecimal(
						efetuarRestabelecimentoLigacaoAguaActionForm
								.getValorParcelas()));
			}*/
			
			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(valorPercentual));
			}
			
		}
		String qtdParcelas = efetuarRestabelecimentoLigacaoAguaActionForm
				.getQuantidadeParcelas();

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		if (efetuarRestabelecimentoLigacaoAguaActionForm
				.getDataRestabelecimento() != null
				&& !efetuarRestabelecimentoLigacaoAguaActionForm
						.getDataRestabelecimento().equals("")) {
			ordemServico
					.setDataEncerramento(Util
							.converteStringParaDate(efetuarRestabelecimentoLigacaoAguaActionForm
									.getDataRestabelecimento()));
		}

		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		
		if (efetuarRestabelecimentoLigacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

			fachada
					.efetuarRestabelecimentoLigacaoAgua(integracaoComercialHelper);
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

		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			// fachada.efetuarRestabelecimentoLigacaoAgua(ordemServico,
			// efetuarRestabelecimentoLigacaoAguaActionForm.getVeioEncerrarOS().toString());

			/*
			 * if(ordemServico.getServicoTipo().getDebitoTipo() != null &&
			 * (ordemServico.getServicoNaoCobrancaMotivo() == null ||
			 * ordemServico.getServicoNaoCobrancaMotivo().getId() ==
			 * ConstantesSistema.NUMERO_NAO_INFORMADO)){
			 * fachada.gerarDebitoOrdemServico(ordemServico.getId(),
			 * ordemServico.getServicoTipo().getDebitoTipo().getId(),
			 * Util.calcularValorDebitoComPorcentagem(ordemServico.getValorAtual(),
			 * efetuarRestabelecimentoLigacaoAguaActionForm.getPercentualCobranca()) ,
			 * new
			 * Integer(efetuarRestabelecimentoLigacaoAguaActionForm.getQuantidadeParcelas()).intValue()); }
			 */

			// [FS006] Verificar Sucesso da Transação
			montarPaginaSucesso(httpServletRequest,
					"Restabelecimento da Ligação de Água para o imóvel "
							+ imovel.getId() + " efetuada com sucesso",
					"Efetuar outro Restabelecimento da Ligação de Água",
					"exibirEfetuarRestabelecimentoLigacaoAguaAction.do?menu=sim");
		}

		return retorno;

	}
}
