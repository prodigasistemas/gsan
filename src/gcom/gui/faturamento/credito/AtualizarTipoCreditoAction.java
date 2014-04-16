package gcom.gui.faturamento.credito;

import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarTipoCreditoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Usuario logado no sistema
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);


		AtualizarTipoCreditoActionForm atualizarTipoCreditoActionForm = (AtualizarTipoCreditoActionForm) actionForm;

		CreditoTipo creditoTipo = (CreditoTipo) sessao
				.getAttribute("creditoTipoAtualizar");

//		creditoTipo.setId(new Integer(atualizarTipoCreditoActionForm
//				.getCodigo()));
		creditoTipo.setDescricao(atualizarTipoCreditoActionForm.getDescricao());

		creditoTipo.setDescricaoAbreviada(atualizarTipoCreditoActionForm
				.getAbreviatura());
		creditoTipo.setIndicadorGeracaoAutomatica(new Short(
				atualizarTipoCreditoActionForm.getIndicadorgeracaoAutomaica()));

		creditoTipo.setIndicadorUso(new Integer(atualizarTipoCreditoActionForm
				.getIndicativoUso()));

		String valorLimiteCredito = atualizarTipoCreditoActionForm
				.getValorLimiteCredito();

		valorLimiteCredito = valorLimiteCredito.replace(".", "");
		valorLimiteCredito = valorLimiteCredito.replace(",", ".");
		creditoTipo.setValorLimite(new BigDecimal(valorLimiteCredito));

		// AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = null;

		if (atualizarTipoCreditoActionForm.getTipoLancamentoContabil() != null) {

			Integer idTipoLancamentoContabil = new Integer(
					atualizarTipoCreditoActionForm.getTipoLancamentoContabil());

			if (idTipoLancamentoContabil
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				creditoTipo.setLancamentoItemContabil(null);
			} else {
				FiltroLancamentoItemContabil filtroLancamentoItemContabil = new FiltroLancamentoItemContabil();
				filtroLancamentoItemContabil
						.adicionarParametro(new ParametroSimples(
								FiltroLancamentoItemContabil.ID,
								atualizarTipoCreditoActionForm
										.getTipoLancamentoContabil().toString()));
				Collection colecaoTipoLancamentoContabil = (Collection) fachada
						.pesquisar(filtroLancamentoItemContabil,
								LancamentoItemContabil.class.getName());

				// setando
				creditoTipo
						.setLancamentoItemContabil((LancamentoItemContabil) colecaoTipoLancamentoContabil
								.iterator().next());
			}
		}

		 fachada.atualizarTipoCredito(creditoTipo, usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Tipo de Crédito "
				+ creditoTipo.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Tipo de Crédito",
				"exibirFiltrarTipoCreditoAction.do?menu=sim");
		return retorno;
	}
}
