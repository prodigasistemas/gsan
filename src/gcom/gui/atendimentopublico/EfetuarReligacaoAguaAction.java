package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

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
 * @date 07/07/2006
 */
public class EfetuarReligacaoAguaAction extends GcomAction {

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

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		EfetuarReligacaoAguaActionForm efetuarReligacaoAguaActionForm = (EfetuarReligacaoAguaActionForm) actionForm;

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		OrdemServico ordemServico = null;

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			veioEncerrarOS = Boolean.FALSE;
		}

		if (sessao.getAttribute("ordemServico") == null) {

			String idOrdemServico = efetuarReligacaoAguaActionForm
					.getIdOrdemServico();

			if (efetuarReligacaoAguaActionForm.getIdOrdemServico() != null) {
				idOrdemServico = efetuarReligacaoAguaActionForm
						.getIdOrdemServico();
			} else {
				idOrdemServico = (String) httpServletRequest
						.getAttribute("veioEncerrarOS");

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

		//ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");

		String idServicoMotivoNaoCobranca = efetuarReligacaoAguaActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = efetuarReligacaoAguaActionForm
				.getPercentualCobranca();
		String qtdParcelas = efetuarReligacaoAguaActionForm
				.getQuantidadeParcelas();

		if (ordemServico != null
				&& efetuarReligacaoAguaActionForm.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			BigDecimal valorAtual = new BigDecimal(0);
			if (efetuarReligacaoAguaActionForm.getValorDebito() != null) {
				String valorDebito = efetuarReligacaoAguaActionForm
						.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}

			if (idServicoMotivoNaoCobranca != null) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(
						idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (valorPercentual != null) {
				ordemServico
						.setPercentualCobranca(new BigDecimal(
								efetuarReligacaoAguaActionForm
										.getPercentualCobranca()));
			}
		}

		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		LigacaoAgua ligacaoAgua = new LigacaoAgua();

		// [FS0005] Verificar preenchimento dos Campos
		// validar Data Corte
		if (efetuarReligacaoAguaActionForm.getDataReligacao() != null
				&& !efetuarReligacaoAguaActionForm.getDataReligacao()
						.equals("")) {
			Date data = Util
					.converteStringParaDate(efetuarReligacaoAguaActionForm
							.getDataReligacao());
			ligacaoAgua.setId(imovel.getId());
			ligacaoAgua.setDataReligacao(data);

		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					" Data da Ligação");
		}

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		
		if (efetuarReligacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

			fachada.efetuarReligacaoAgua(integracaoComercialHelper);
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

		// fachada.efetuarReligacaoAgua(ordemServico,
		// efetuarReligacaoAguaActionForm.getVeioEncerrarOS());
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					"Religação de Água para o imóvel " + imovel.getId()
							+ " efetuada com sucesso",
					"Efetuar outra Religação de Água",
					"exibirEfetuarReligacaoAguaAction.do?menu=sim");
		}

		return retorno;

	}
}
