package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarTipoRetornoOrdemServicoReferidaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarTipoRetornoOrdemServicoReferidaActionForm atualizarTipoRetornoOrdemServicoReferidaActionForm = (AtualizarTipoRetornoOrdemServicoReferidaActionForm) actionForm;

		OsReferidaRetornoTipo osReferidaRetornoTipo = (OsReferidaRetornoTipo) sessao
				.getAttribute("osReferidaRetornoTipoAtualizar");

		osReferidaRetornoTipo.setId(new Integer(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getCodigoTipoRetorno()));
		osReferidaRetornoTipo
				.setDescricao(atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getDescricao());
		osReferidaRetornoTipo
				.setDescricaoAbreviada(atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getAbreviatura());

		ServicoTipoReferencia servicoTipoReferencia = null;

		if (atualizarTipoRetornoOrdemServicoReferidaActionForm
				.getServicoTipoReferencia() != null
				&& !atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getServicoTipoReferencia().equals("")
				&& !atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getServicoTipoReferencia().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			servicoTipoReferencia = new ServicoTipoReferencia();
			servicoTipoReferencia.setId(new Integer(
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.getServicoTipoReferencia()));

		}

		osReferidaRetornoTipo.setServicoTipoReferencia(servicoTipoReferencia);
		osReferidaRetornoTipo.setIndicadorDeferimento(new Short(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getDeferimento()));
		osReferidaRetornoTipo.setIndicadorTrocaServico(new Short(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getTrocaServico()));
		if (atualizarTipoRetornoOrdemServicoReferidaActionForm.getSituacao() != null
				&& !atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getSituacao().equals("")) {
			osReferidaRetornoTipo.setSituacaoOsReferencia(new Short(
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.getSituacao()));
		} else {
			osReferidaRetornoTipo.setSituacaoOsReferencia(null);
		}

		osReferidaRetornoTipo.setIndicadorUso(new Short(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getIndicadorUso()));

		//AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = null;

		if (atualizarTipoRetornoOrdemServicoReferidaActionForm
				.getAtendimentoMotivoEncerramento() != null) {

			Integer idAtendimentoMotivoEncerramento = new Integer(
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.getAtendimentoMotivoEncerramento());

			if (idAtendimentoMotivoEncerramento
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				osReferidaRetornoTipo.setAtendimentoMotivoEncerramento(null);
			} else {
				FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
				filtroAtendimentoMotivoEncerramento
						.adicionarParametro(new ParametroSimples(
								FiltroAtendimentoMotivoEncerramento.ID,
								atualizarTipoRetornoOrdemServicoReferidaActionForm
										.getAtendimentoMotivoEncerramento()
										.toString()));
				Collection colecaoAtendimentoMotivoEncerramento = (Collection) fachada
						.pesquisar(filtroAtendimentoMotivoEncerramento,
								AtendimentoMotivoEncerramento.class.getName());

				// setando
				osReferidaRetornoTipo
						.setAtendimentoMotivoEncerramento((AtendimentoMotivoEncerramento) colecaoAtendimentoMotivoEncerramento
								.iterator().next());
			}
		}

		fachada.atualizarTipoRetornoOrdemServicoReferida(osReferidaRetornoTipo);

		montarPaginaSucesso(httpServletRequest, "Tipo Retorno da OS_Referida "
				+ osReferidaRetornoTipo.getId().toString()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Tipo Retorno da OS_Referida",
				"exibirFiltrarTipoRetornoOrdemServicoReferidaAction.do?menu=sim");
		return retorno;
	}
}
