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

public class EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = request.getSession(false);
		EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm form = (EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String ordemServicoId = form.getIdOrdemServico();
		String idServicoMotivoNaoCobranca = form.getMotivoNaoCobranca();
		String valorPercentual = form.getPercentualCobranca();

		OrdemServico ordemServico = null;
		if (ordemServicoId != null && !ordemServicoId.equals("")) {
			ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
			if (ordemServico == null) {
				throw new ActionServletException("atencao.ordem_servico_inexistente", null, "ORDEM DE SERVIÇO INEXISTENTE");

			}
		}

		if (ordemServico != null && form.getIdTipoDebito() != null) {
			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			if (idServicoMotivoNaoCobranca != null) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(form.getPercentualCobranca()));
			}

			BigDecimal valorAtual = new BigDecimal(0);
			if (form.getValorDebito() != null) {
				String valorDebito = form.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}
		}

		String qtdParcelas = form.getQuantidadeParcelas();

		IntegracaoComercialHelper helper = new IntegracaoComercialHelper();

		helper.setLigacaoEsgoto((LigacaoEsgoto) sessao.getAttribute("ligacaoEsgoto"));
		helper.setOrdemServico(ordemServico);
		helper.setQtdParcelas(qtdParcelas);
		helper.setUsuarioLogado(usuario);

		if (form.getVeioEncerrarOS().equalsIgnoreCase("FALSE")) {
			helper.setVeioEncerrarOS(Boolean.FALSE);
			fachada.efetuarMudancaSituacaoFaturamentoLiagacaoEsgoto(helper);
		} else {
			helper.setVeioEncerrarOS(Boolean.TRUE);
			sessao.setAttribute("integracaoComercialHelper", helper);

			if (sessao.getAttribute("semMenu") == null) {
				retorno = actionMapping.findForward("encerrarOrdemServicoAction");
			} else {
				retorno = actionMapping.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}

		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(request, "Mudança de Faturamento da Ligação de Esgoto" + " efetuada com Sucesso", "", "exibirEfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction.do");
		}

		return retorno;
	}
}
