package gcom.gui.faturamento.debito;import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/** *  * Descrição da classe *  *  *  * @author Rômulo Aurélio *  * @date 15/03/2007 *  */public class AtualizarTipoDebitoAction extends GcomAction {	/**	 * 	 * Este caso de uso permite alterar e remover um Tipo de Débito	 * 	 * 	 * 	 * [UC0530] Manter Tipo de Débito	 * 	 * 	 * 	 * 	 * 	 * @author Rômulo Aurélio	 * 	 * @date 15/03/2007	 * 	 * 	 * 	 * @param actionMapping	 * 	 * @param actionForm	 * 	 * @param httpServletRequest	 * 	 * @param httpServletResponse	 * 	 * @return	 * 	 */	public ActionForward execute(ActionMapping actionMapping,	ActionForm actionForm, HttpServletRequest httpServletRequest,	HttpServletResponse httpServletResponse) {		// Seta o retorno		ActionForward retorno = actionMapping.findForward("telaSucesso");		// Obtém a instância da fachada		Fachada fachada = Fachada.getInstancia();		HttpSession sessao = httpServletRequest.getSession(false);		AtualizarTipoDebitoActionForm form = (AtualizarTipoDebitoActionForm) actionForm;		Usuario usuarioLogado = (Usuario) sessao				.getAttribute(Usuario.USUARIO_LOGADO);		String id = (String) sessao.getAttribute("idTipoDebito");		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();		filtroDebitoTipo.adicionarParametro(new ParametroSimples(				FiltroDebitoTipo.ID, id));//		filtroDebitoTipo.adicionarParametro(new ParametroSimples(//				FiltroDebitoTipo.INDICADOR_USO,//				ConstantesSistema.INDICADOR_USO_ATIVO));		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,				DebitoTipo.class.getName());		DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator()				.next();		String descricao = form.getDescricao();		String descricaoAbreviada = form.getDescricaoAbreviada();		String idTipoFinanciamento = form.getFinanciamentoTipo();		String indicadorGeracaoDebitoAutomatica = form				.getIndicadorGeracaoDebitoAutomatica();		String indicadorGeracaoDebitoConta = form				.getIndicadorGeracaoDebitoConta();		String indicadorUso = form.getIndicadorUso();		String idLancamentoItemContabil = form.getLancamentoItemContabil();		String valorLimeteDebito = form.getValorLimiteDebito();
		String valorSugerido = form.getValorSugerido();				String indicadorDebitoCartaoCredito = form.getIndicadorDebitoCartaoCredito();				String indicadorJurosParCliente = form.getIndicadorJurosParCliente();		fachada.atualizarDebitoTipo(debitoTipo, id, descricao,				descricaoAbreviada, idTipoFinanciamento,				indicadorGeracaoDebitoAutomatica, indicadorGeracaoDebitoConta,				idLancamentoItemContabil, valorLimeteDebito, indicadorUso,				usuarioLogado,valorSugerido, indicadorDebitoCartaoCredito, indicadorJurosParCliente);		montarPaginaSucesso(httpServletRequest, "Tipo de Débito de código "				+ id + " atualizado com sucesso.",				"Realizar outra Manutenção Tipo de Débito",				"exibirFiltrarTipoDebitoAction.do?menu=sim");		return retorno;	}}
