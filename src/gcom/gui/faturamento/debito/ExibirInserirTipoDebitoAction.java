package gcom.gui.faturamento.debito;
import gcom.fachada.Fachada;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 09/03/2007
 */
public class ExibirInserirTipoDebitoAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclusão de um novo Tipo de Débito
	 * 
	 * [UC0529] Inserir Tipo de Débito
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/03/2007
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
		ActionForward retorno = actionMapping.findForward("inserirTipoDebito");
		Fachada fachada = Fachada.getInstancia();				InserirTipoDebitoActionForm form = (InserirTipoDebitoActionForm) actionForm;				form.setIndicadorJurosParCliente(ConstantesSistema.NAO_CONFIRMADA);
		// Carregando dados da tabela LancamentoItemContabil
		FiltroLancamentoItemContabil filtroLancamentoItemContabil = new FiltroLancamentoItemContabil();		filtroLancamentoItemContabil				.setCampoOrderBy(FiltroLancamentoItemContabil.DESCRICAO);		filtroLancamentoItemContabil.adicionarParametro(new ParametroSimples(				FiltroLancamentoItemContabil.INDICADOR_USO,				ConstantesSistema.INDICADOR_USO_ATIVO));
		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao
		Collection<LancamentoItemContabil> colecaoLancamentoItemContabil = fachada				.pesquisar(filtroLancamentoItemContabil,						LancamentoItemContabil.class.getName());
		if (colecaoLancamentoItemContabil == null				|| colecaoLancamentoItemContabil.isEmpty()) {
			throw new ActionServletException(										"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Lancamento Item Contabil");			
		}
		httpServletRequest.setAttribute("colecaoLancamentoItemContabil",
				colecaoLancamentoItemContabil);
		// Carregando dados da tabela FinanciamentoTipo
		FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();		filtroFinanciamentoTipo.setCampoOrderBy(FiltroFinanciamentoTipo.DESCRICAO);
		filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(				FiltroFinanciamentoTipo.INDICADOR_USO,				ConstantesSistema.INDICADOR_USO_ATIVO));
		// Verifica se os dados foram informados da tabela existem e joga numa		// colecao		Collection<FinanciamentoTipo> colecaoFinanciamentoTipo = fachada				.pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class						.getName());
		if (colecaoFinanciamentoTipo == null				|| colecaoFinanciamentoTipo.isEmpty()) {
			throw new ActionServletException(					"atencao.entidade_sem_dados_para_selecao", null,					"Tabela Financiamento Tipo");
		}
		httpServletRequest.setAttribute("colecaoFinanciamentoTipo",				colecaoFinanciamentoTipo);
		return retorno;
	}}
