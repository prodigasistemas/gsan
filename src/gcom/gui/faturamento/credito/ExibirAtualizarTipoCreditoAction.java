package gcom.gui.faturamento.credito;

import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 30/10/2006
 */
public class ExibirAtualizarTipoCreditoAction extends GcomAction {
	/**
	 * [UC0393] Atualizar Agência Bancária
	 * 
	 * Este caso de uso permite alterar um valor de Agência Bancária
	 * 
	 * @author Thiago Tenório
	 * @date 31/10/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarTipoCredito");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarTipoCreditoActionForm atualizarTipoCreditoActionForm = (AtualizarTipoCreditoActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {
			atualizarTipoCreditoActionForm.setTipoLancamentoContabil("");
		}
		Fachada fachada = Fachada.getInstancia();

		String id = null;

		String idTipoCredito = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
				&& !httpServletRequest.getParameter("idRegistroAtualizacao")
						.equals("")) {

			sessao.removeAttribute("objetoTipoCredito");
			sessao.removeAttribute("colecaoCreditoTipoTela");

		}

		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		// Verifica se o servicoCobrancaValor já está na sessão, em caso
		// afirmativo
		// significa que o usuário já entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// está entrando pela primeira vez

		if (sessao.getAttribute("colecaoCreditoTipoTela") == null) {

			if (sessao.getAttribute("objetoTipoCredito") != null) {

				CreditoTipo creditoTipo = (CreditoTipo) sessao
						.getAttribute("objetoTipoCredito");

				sessao.setAttribute("idTipoCredito", creditoTipo.getId()
						.toString());

				sessao.setAttribute("creditoTipo", creditoTipo);

				atualizarTipoCreditoActionForm.setCodigo(creditoTipo.getId()
						.toString());

				atualizarTipoCreditoActionForm.setDescricao(creditoTipo
						.getDescricao());

				atualizarTipoCreditoActionForm.setAbreviatura(creditoTipo
						.getDescricaoAbreviada());

				atualizarTipoCreditoActionForm
						.setTipoLancamentoContabil(creditoTipo
								.getLancamentoItemContabil().getId().toString());

				atualizarTipoCreditoActionForm.setIndicadorgeracaoAutomaica(""
						+ creditoTipo.getIndicadorGeracaoAutomatica());

				String valorAux = Util.formatarMoedaReal(creditoTipo
						.getValorLimite());
				atualizarTipoCreditoActionForm.setValorLimiteCredito(""
						+ valorAux);

				atualizarTipoCreditoActionForm.setIndicativoUso(""
						+ creditoTipo.getIndicadorUso());

				id = creditoTipo.getId().toString();

				sessao.setAttribute("creditoTipoAtualizar", creditoTipo);
				sessao.removeAttribute("objetoCreditoTipo");

			} else {

				CreditoTipo creditoTipo = null;

				idTipoCredito = null;

				if (httpServletRequest.getParameter("idRegistroAtualizacao") == null
						|| httpServletRequest.getParameter(
								"idRegistroAtualizacao").equals("")) {
					creditoTipo = (CreditoTipo) sessao
							.getAttribute("objetoCreditoTipo");
				} else {
					idTipoCredito = (String) httpServletRequest
							.getParameter("idRegistroAtualizacao");
					sessao.setAttribute("idRegistroAtualizacao", idTipoCredito);
				}

				if (idTipoCredito != null) {

					id = idTipoCredito;

					FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
					filtroCreditoTipo
							.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

					filtroCreditoTipo.adicionarParametro(new ParametroSimples(
							FiltroCreditoTipo.ID, idTipoCredito));

					Collection<CreditoTipo> colecaoCreditoTipo = fachada
							.pesquisar(filtroCreditoTipo, CreditoTipo.class
									.getName());

					if (colecaoCreditoTipo == null
							|| colecaoCreditoTipo.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					httpServletRequest.setAttribute("colecaoCreditoTipo",
							colecaoCreditoTipo);

					creditoTipo = (CreditoTipo) colecaoCreditoTipo.iterator()
							.next();

				}

				if (idTipoCredito == null) {
					if (sessao.getAttribute("idRegistroAtualizacao") != null) {
						idTipoCredito = (String) sessao
								.getAttribute("idRegistroAtualizacao");
					} else {
						creditoTipo = (CreditoTipo) sessao
								.getAttribute("creditoTipo");
						idTipoCredito = creditoTipo.getId().toString();
					}
				}

				// FiltroAgencia filtroAgencia = new FiltroAgencia();
				//
				// filtroAgencia
				// .adicionarParametro(new ParametroSimples(
				// FiltroAgencia.ID,
				// idAgencia));
				//
				// filtroAgencia
				// .adicionarCaminhoParaCarregamentoEntidade("banco");
				//
				// Collection colecaoAgencia = (Collection) fachada
				// .pesquisar(filtroAgencia,
				// Agencia.class.getName());
				//
				// agencia = (Agencia) colecaoAgencia
				// .iterator().next();

				// atualizarAgenciaBancariaActionForm.setCodigo(agencia.getCodigoAgencia()
				// .toString());
				
				atualizarTipoCreditoActionForm.setCodigo(creditoTipo.getId()
						.toString());

				atualizarTipoCreditoActionForm.setDescricao(creditoTipo
						.getDescricao());

				atualizarTipoCreditoActionForm.setAbreviatura(creditoTipo
						.getDescricaoAbreviada());

				atualizarTipoCreditoActionForm.setIndicadorgeracaoAutomaica(""
						+ creditoTipo.getIndicadorGeracaoAutomatica());

				atualizarTipoCreditoActionForm.setIndicativoUso(""
						+ creditoTipo.getIndicadorUso());

				String valorAux = Util.formatarMoedaReal(creditoTipo
						.getValorLimite());
				atualizarTipoCreditoActionForm.setValorLimiteCredito(""
						+ valorAux);

				if (creditoTipo.getLancamentoItemContabil() != null) {
					atualizarTipoCreditoActionForm
							.setTipoLancamentoContabil(creditoTipo
									.getLancamentoItemContabil().getId()
									.toString());
				} else {
					atualizarTipoCreditoActionForm
							.setTipoLancamentoContabil("");
				}

				// Collection colecaoEnderecos = null;
				//								
				// if (agencia.getEnderecoFormatado() != null) {
				// colecaoEnderecos = new ArrayList();
				// colecaoEnderecos.add(agencia.getEnderecoFormatado());
				// sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
				// }

				sessao.setAttribute("creditoTipoAtualizar", creditoTipo);

			}
		}

		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			sessao.removeAttribute("colecaoCreditoTipoTela");

			String creditoTipoID = null;

			if (sessao.getAttribute("idRegistroAtualizacao") != null
					&& !sessao.getAttribute("idRegistroAtualizacao").equals("")) {
				creditoTipoID = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			}

			if (creditoTipoID.equalsIgnoreCase("")) {
				creditoTipoID = null;
			}

			if ((creditoTipoID == null) && (id == null)) {

				CreditoTipo creditoTipo = (CreditoTipo) sessao
						.getAttribute("objetoCreditoTipo");

				atualizarTipoCreditoActionForm.setCodigo(creditoTipo.getId()
						.toString());

				atualizarTipoCreditoActionForm.setDescricao(creditoTipo
						.getDescricao());

				atualizarTipoCreditoActionForm.setAbreviatura(creditoTipo
						.getDescricaoAbreviada());

				atualizarTipoCreditoActionForm.setIndicadorgeracaoAutomaica(""
						+ creditoTipo.getIndicadorGeracaoAutomatica());

				atualizarTipoCreditoActionForm.setIndicativoUso(""
						+ creditoTipo.getIndicadorUso());

				String valorAux = Util.formatarMoedaReal(creditoTipo
						.getValorLimite());
				atualizarTipoCreditoActionForm.setValorLimiteCredito(""
						+ valorAux);

				atualizarTipoCreditoActionForm
						.setTipoLancamentoContabil(creditoTipo
								.getLancamentoItemContabil().getId().toString());

				sessao.setAttribute("creditoTipoAtualizar", creditoTipo);
				sessao.removeAttribute("creditoTipo");
			}

			if ((idTipoCredito == null) && (id != null)) {

				idTipoCredito = id;
			}

			if (idTipoCredito != null) {

				FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
				filtroCreditoTipo
						.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

				filtroCreditoTipo.adicionarParametro(new ParametroSimples(
						FiltroCreditoTipo.ID, idTipoCredito));

				Collection<CreditoTipo> colecaoCreditoTipo = fachada.pesquisar(
						filtroCreditoTipo, CreditoTipo.class.getName());

				if (colecaoCreditoTipo == null || colecaoCreditoTipo.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoCreditoTipo",
						colecaoCreditoTipo);

				CreditoTipo creditoTipo = (CreditoTipo) colecaoCreditoTipo
						.iterator().next();

				atualizarTipoCreditoActionForm.setCodigo(creditoTipo.getId()
						.toString());

				atualizarTipoCreditoActionForm.setDescricao(creditoTipo
						.getDescricao());

				atualizarTipoCreditoActionForm.setAbreviatura(creditoTipo
						.getDescricaoAbreviada());

				atualizarTipoCreditoActionForm.setIndicadorgeracaoAutomaica(""
						+ creditoTipo.getIndicadorGeracaoAutomatica());

				atualizarTipoCreditoActionForm.setIndicativoUso(""
						+ creditoTipo.getIndicadorUso());

				atualizarTipoCreditoActionForm
						.setTipoLancamentoContabil(creditoTipo
								.getLancamentoItemContabil().getId().toString());

				// Collection colecaoEnderecos = null;
				//				
				// if (agencia.getEnderecoFormatado() != null) {
				// colecaoEnderecos = new ArrayList();
				// colecaoEnderecos.add(agencia.getEnderecoFormatado());
				// sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
				// }

				httpServletRequest.setAttribute("idTipoCredito", idTipoCredito);
				sessao.setAttribute("tipoCreditoAtualizar", creditoTipo);

			}
		}
		// -------------- bt DESFAZER ---------------

		httpServletRequest.setAttribute("colecaoCreditoTipoTela", sessao
				.getAttribute("colecaoCreditoTipoValorTela"));

		return retorno;

	}

}
