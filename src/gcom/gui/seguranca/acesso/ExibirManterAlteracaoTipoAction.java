package gcom.gui.seguranca.acesso;


import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.FiltroAlteracaoTipo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Vinícius Medeiros
 * 
 */
public class ExibirManterAlteracaoTipoAction extends GcomAction {
	/**
	 * Exibir Manter Tipo de Alteracao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterAlteracaoTipo");

		Collection colecaoAlteracaoTipo = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroAlteracaoTipo filtroAlteracaoTipo = (FiltroAlteracaoTipo) sessao
				.getAttribute("filtroAlteracaoTipo");

		filtroAlteracaoTipo.setCampoOrderBy(FiltroAlteracaoTipo.ID,
				FiltroAlteracaoTipo.DESCRICAO,
				FiltroAlteracaoTipo.DESCRICAOABREVIADA);

		if (filtroAlteracaoTipo != null && !filtroAlteracaoTipo.equals("")) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroAlteracaoTipo, AlteracaoTipo.class.getName());
			colecaoAlteracaoTipo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoAlteracaoTipo != null && !colecaoAlteracaoTipo.isEmpty()) {
			if (colecaoAlteracaoTipo.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarAlteracaoTipo");
					AlteracaoTipo alteracaoTipo = (AlteracaoTipo) colecaoAlteracaoTipo
							.iterator().next();
					sessao.setAttribute("alteracaoTipo", alteracaoTipo);
				} else {
					httpServletRequest.setAttribute("colecaoAlteracaoTipo",
							colecaoAlteracaoTipo);
				}
			} else {
				httpServletRequest.setAttribute("colecaoAlteracaoTipo",
						colecaoAlteracaoTipo);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
