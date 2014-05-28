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
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 09/03/2007
 */
public class ExibirInserirTipoDebitoAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclus�o de um novo Tipo de D�bito
	 * 
	 * [UC0529] Inserir Tipo de D�bito
	 * 
	 * 
	 * @author R�mulo Aur�lio
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
		Fachada fachada = Fachada.getInstancia();
		// Carregando dados da tabela LancamentoItemContabil
		FiltroLancamentoItemContabil filtroLancamentoItemContabil = new FiltroLancamentoItemContabil();
		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao
		Collection<LancamentoItemContabil> colecaoLancamentoItemContabil = fachada
		if (colecaoLancamentoItemContabil == null
			throw new ActionServletException(
					"Tabela Lancamento Item Contabil");
		}
		httpServletRequest.setAttribute("colecaoLancamentoItemContabil",
				colecaoLancamentoItemContabil);
		// Carregando dados da tabela FinanciamentoTipo
		FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
		filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(
		// Verifica se os dados foram informados da tabela existem e joga numa
		if (colecaoFinanciamentoTipo == null
			throw new ActionServletException(
		}
		httpServletRequest.setAttribute("colecaoFinanciamentoTipo",
		return retorno;
	}