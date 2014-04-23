package gcom.gui.faturamento.credito;

import gcom.fachada.Fachada;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 25 de Abril de 2005
 */
public class ExibirFiltrarTipoCreditoAction extends GcomAction {
	/**
	 * Description of the Method
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

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarTipoCreditoActionForm filtrarTipoCreditoActionForm = (FiltrarTipoCreditoActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {
			filtrarTipoCreditoActionForm.setCodigo("");
			filtrarTipoCreditoActionForm.setDescricao("");
			filtrarTipoCreditoActionForm.setAbreviatura("");
			filtrarTipoCreditoActionForm.setValorLimiteCreditoInicial("");
			filtrarTipoCreditoActionForm.setValorLimiteCreditoFinal("");
			filtrarTipoCreditoActionForm.setTipoLancamentoContabil("");
			filtrarTipoCreditoActionForm.setIndicativoUso("3");
			filtrarTipoCreditoActionForm.setIndicadorgeracaoAutomaica("3");
			filtrarTipoCreditoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Parte que passa as coleções necessárias no jsp
		FiltroLancamentoItemContabil filtroLancamentoItemContabil = new FiltroLancamentoItemContabil();
		filtroLancamentoItemContabil.setCampoOrderBy(FiltroLancamentoItemContabil.ID);
		Collection colecaoTipoLancamentoContabil = fachada.pesquisar(
				filtroLancamentoItemContabil, LancamentoItemContabil.class.getName());

		if (colecaoTipoLancamentoContabil != null
				&& !colecaoTipoLancamentoContabil.isEmpty()) {
			sessao.setAttribute("colecaoTipoLancamentoContabil",
					colecaoTipoLancamentoContabil);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"CreditoTipo");
		}

		 filtrarTipoCreditoActionForm.setAtualizar("1");

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarTipoCredito");

		return retorno;

	}
}
