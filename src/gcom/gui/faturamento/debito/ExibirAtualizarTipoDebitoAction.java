package gcom.gui.faturamento.debito;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/** * Descrição da classe *  * @author Rômulo Aurélio * @date 14/03/2007 */
public class ExibirAtualizarTipoDebitoAction extends GcomAction {
	/**	 * Este caso de uso permite alterar e remover um Tipo de Débito	 * 	 * [UC0530] Manter Tipo de Débito	 * 	 * 	 * @author Rômulo Aurélio	 * @date 14/03/2007	 * 	 * @param actionMapping	 * @param actionForm	 * @param httpServletRequest	 * @param httpServletResponse	 * @return	 */
	public ActionForward execute(ActionMapping actionMapping,			ActionForm actionForm, HttpServletRequest httpServletRequest,			HttpServletResponse httpServletResponse) {
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping				.findForward("atualizarTipoDebito");
		AtualizarTipoDebitoActionForm form = (AtualizarTipoDebitoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		String id = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		/*		 * if (httpServletRequest.getParameter("idTipoDebito") != null &&		 * !httpServletRequest.getParameter("idTipoDebito").equals( "")) { }		 */
		// Carregando dados da tabela LancamentoItemContabil		FiltroLancamentoItemContabil filtroLancamentoItemContabil = new FiltroLancamentoItemContabil();
		filtroLancamentoItemContabil				.setCampoOrderBy(FiltroLancamentoItemContabil.DESCRICAO);
		filtroLancamentoItemContabil.adicionarParametro(new ParametroSimples(				FiltroLancamentoItemContabil.INDICADOR_USO,				ConstantesSistema.INDICADOR_USO_ATIVO));
		// Verifica se os dados foram informados da tabela existem e joga numa		// colecao
		Collection<LancamentoItemContabil> colecaoLancamentoItemContabil = fachada				.pesquisar(filtroLancamentoItemContabil,						LancamentoItemContabil.class.getName());
		if (colecaoLancamentoItemContabil == null				|| colecaoLancamentoItemContabil.isEmpty()) {
			throw new ActionServletException(					"atencao.entidade_sem_dados_para_selecao", null,					"Tabela Lancamento Item Contabil");
		}
		httpServletRequest.setAttribute("colecaoLancamentoItemContabil",				colecaoLancamentoItemContabil);
		// Carregando dados da tabela FinanciamentoTipo		FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
		filtroFinanciamentoTipo				.setCampoOrderBy(FiltroFinanciamentoTipo.DESCRICAO);
		filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(				FiltroFinanciamentoTipo.INDICADOR_USO,				ConstantesSistema.INDICADOR_USO_ATIVO));
		// Verifica se os dados foram informados da tabela existem e joga numa		// colecao
		Collection<FinanciamentoTipo> colecaoFinanciamentoTipo = fachada				.pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class						.getName());
		if (colecaoFinanciamentoTipo == null				|| colecaoFinanciamentoTipo.isEmpty()) {
			throw new ActionServletException(					"atencao.entidade_sem_dados_para_selecao", null,					"Tabela Financiamento Tipo");
		}
		httpServletRequest.setAttribute("colecaoFinanciamentoTipo",				colecaoFinanciamentoTipo);
		// Verifica se veio do filtrar ou do manter		if (httpServletRequest.getParameter("manter") != null) {			sessao.setAttribute("manter", true);				} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		// Verifica se a funcionalidade já está na sessão, em caso afirmativo		// significa que o usuário já entrou na tela e apenas selecionou algum		// item que deu um reload na tela e em caso negativo significa que ele		// está entrando pela primeira vez		if (sessao.getAttribute("colecaoTipoDebito") == null) {
			if (sessao.getAttribute("objetoDebitoTipo") != null) {
				DebitoTipo debitoTipo = (DebitoTipo) sessao						.getAttribute("objetoDebitoTipo");				sessao.setAttribute("idDebitoTipo", debitoTipo.getId()						.toString());
				form.setCodigo(debitoTipo.getId().toString());
				form.setDescricao(debitoTipo.getDescricao());								form.setDescricaoAbreviada(debitoTipo.getDescricaoAbreviada());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()						.getId().toString());
				form.setLancamentoItemContabil(debitoTipo						.getLancamentoItemContabil().getId().toString());
				form.setIndicadorGeracaoDebitoAutomatica(""						+ debitoTipo.getIndicadorGeracaoAutomatica());
				form.setIndicadorGeracaoDebitoConta(""						+ debitoTipo.getIndicadorGeracaoConta());
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());
				String valorAux = Util.formatarMoedaReal(debitoTipo						.getValorLimite());
				form.setValorLimiteDebito("" + valorAux);								String valorSug = Util.formatarMoedaReal(debitoTipo						.getValorSugerido());								form.setValorSugerido("" + valorSug);				form.setIdTipoDebito(debitoTipo.getId().toString());								form.setIndicadorDebitoCartaoCredito(""+debitoTipo.getIndicadorDebitoCartaoCredito());								form.setIndicadorJurosParCliente(""+debitoTipo.getIndicadorJurosParCliente());
				id = debitoTipo.getId().toString();				sessao.setAttribute("idTipoDebito", id);
			} else {
				String idTipoDebito = null;				if (httpServletRequest.getParameter("idDebitoTipo") == null						|| httpServletRequest.getParameter("idDebitoTipo")								.equals("")) {
					idTipoDebito = (String) sessao.getAttribute("idTipoDebito");
				} else {					idTipoDebito = (String) httpServletRequest							.getParameter("idDebitoTipo");
					sessao.setAttribute("idDebitoTipo", idTipoDebito);
				}
				sessao.setAttribute("idTipoDebito", idTipoDebito);
				id = idTipoDebito;
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
				filtroDebitoTipo						.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
				filtroDebitoTipo						.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(						FiltroDebitoTipo.ID, idTipoDebito));
				Collection<DebitoTipo> colecaoDebitoTipo = fachada.pesquisar(						filtroDebitoTipo, DebitoTipo.class.getName());
				if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {					throw new ActionServletException(							"atencao.atualizacao.timestamp");
				}
				httpServletRequest.setAttribute("colecaoDebitoTipo",						colecaoDebitoTipo);
				DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo						.iterator().next();
				form.setCodigo(debitoTipo.getId().toString());				form.setIdTipoDebito(debitoTipo.getId().toString());
				form.setDescricao(debitoTipo.getDescricao());				form.setDescricaoAbreviada(debitoTipo.getDescricaoAbreviada());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()						.getId().toString());
				form.setLancamentoItemContabil(debitoTipo						.getLancamentoItemContabil().getId().toString());
				form.setIndicadorGeracaoDebitoAutomatica(""						+ debitoTipo.getIndicadorGeracaoAutomatica());
				form.setIndicadorGeracaoDebitoConta(""						+ debitoTipo.getIndicadorGeracaoConta());
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());
				String valorAux = Util.formatarMoedaReal(debitoTipo						.getValorLimite());								String valorSug = Util.formatarMoedaReal(debitoTipo						.getValorSugerido());								form.setValorSugerido("" + valorSug);
				form.setValorLimiteDebito("" + valorAux);								form.setIndicadorDebitoCartaoCredito("" + debitoTipo.getIndicadorDebitoCartaoCredito());								form.setIndicadorJurosParCliente(debitoTipo.getIndicadorJurosParCliente().toString());
				form.setIdTipoDebito(debitoTipo.getId().toString());
			}
		}
		// -------------- bt DESFAZER ---------------		if (httpServletRequest.getParameter("desfazer") != null				&& httpServletRequest.getParameter("desfazer")						.equalsIgnoreCase("S")) {
			String idDebitoTipo = null;			if (httpServletRequest.getParameter("idDebitoTipo") == null					|| httpServletRequest.getParameter("idDebitoTipo").equals(							"")) {
				idDebitoTipo = (String) sessao.getAttribute("idDebitoTipo");
			} else {
				idDebitoTipo = (String) httpServletRequest						.getParameter("idDebitoTipo");
				sessao.setAttribute("idDebitoTipo", idDebitoTipo);
			}
			if (idDebitoTipo.equalsIgnoreCase("")) {
				idDebitoTipo = null;
			}
			if ((idDebitoTipo == null) && (id == null)) {
				DebitoTipo debitoTipo = (DebitoTipo) sessao						.getAttribute("objetoDebitoTipo");
				form.setCodigo(debitoTipo.getId().toString());
				form.setIdTipoDebito(debitoTipo.getId().toString());
				form.setDescricao(debitoTipo.getDescricao());
				form.setDescricaoAbreviada(debitoTipo.getDescricaoAbreviada());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()						.getId().toString());
				form.setLancamentoItemContabil(debitoTipo						.getLancamentoItemContabil().getId().toString());
				form.setIndicadorGeracaoDebitoAutomatica(""						+ debitoTipo.getIndicadorGeracaoAutomatica());
				form.setIndicadorGeracaoDebitoConta(""						+ debitoTipo.getIndicadorGeracaoConta());
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());				String valorAux = Util.formatarMoedaReal(debitoTipo						.getValorLimite());
				form.setValorLimiteDebito("" + valorAux);								String valorSug = Util.formatarMoedaReal(debitoTipo						.getValorSugerido());								form.setValorSugerido("" + valorSug);								form.setIndicadorDebitoCartaoCredito("" + debitoTipo.getIndicadorDebitoCartaoCredito());								form.setIndicadorJurosParCliente(""+debitoTipo.getIndicadorJurosParCliente());				form.setIdTipoDebito(debitoTipo.getId().toString());
				sessao.setAttribute("debitoTipoAtualizar", debitoTipo);
				sessao.removeAttribute("debitoTipo");
			}
			if ((idDebitoTipo == null) && (id != null)) {
				idDebitoTipo = id;
			}
			if (idDebitoTipo != null) {
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();				filtroDebitoTipo						.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
				filtroDebitoTipo						.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(						FiltroDebitoTipo.ID, idDebitoTipo));
				Collection<DebitoTipo> colecaoDebitoTipo = fachada.pesquisar(
						filtroDebitoTipo, DebitoTipo.class.getName());
				if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {
					throw new ActionServletException(							"atencao.atualizacao.timestamp");				}
				httpServletRequest.setAttribute("colecaoDebitoTipo",						colecaoDebitoTipo);
				DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo						.iterator().next();
				form.setCodigo(debitoTipo.getId().toString());				form.setIdTipoDebito(debitoTipo.getId().toString());				form.setDescricao(debitoTipo.getDescricao());
				form.setDescricaoAbreviada(debitoTipo.getDescricaoAbreviada());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()						.getId().toString());
				form.setLancamentoItemContabil(debitoTipo						.getLancamentoItemContabil().getId().toString());
				form.setIndicadorGeracaoDebitoAutomatica(""						+ debitoTipo.getIndicadorGeracaoAutomatica());				form.setIndicadorGeracaoDebitoConta(""						+ debitoTipo.getIndicadorGeracaoConta());
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());
				String valorAux = Util.formatarMoedaReal(debitoTipo						.getValorLimite());
				form.setValorLimiteDebito("" + valorAux);								String valorSug = Util.formatarMoedaReal(debitoTipo						.getValorSugerido());								form.setValorSugerido("" + valorSug);								form.setIndicadorDebitoCartaoCredito("" + debitoTipo.getIndicadorDebitoCartaoCredito());								form.setIndicadorJurosParCliente(""+debitoTipo.getIndicadorJurosParCliente());
				form.setIdTipoDebito(debitoTipo.getId().toString());
			}
		}
		// -------------- bt DESFAZER ---------------
		httpServletRequest.setAttribute("colecaoDebitoTipoTela", sessao				.getAttribute("colecaoDebitoTipoTela"));
		return retorno;
	}
}
