package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;

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
public class ExibirManterConsumoAnormalidadeAction extends GcomAction {
	/**
	 * < <Descrição do método>>
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
				.findForward("exibirManterConsumoAnormalidade");

		Collection colecaoConsumoAnormalidade = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroConsumoAnormalidade filtroConsumoAnormalidade = (FiltroConsumoAnormalidade) sessao
				.getAttribute("filtroConsumoAnormalidade");

		filtroConsumoAnormalidade.setCampoOrderBy(FiltroConsumoAnormalidade.ID,
				FiltroConsumoAnormalidade.DESCRICAO,
				FiltroConsumoAnormalidade.DESCRICAO_ABREVIADA);

		if (filtroConsumoAnormalidade != null && !filtroConsumoAnormalidade.equals("")) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());
			colecaoConsumoAnormalidade = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoConsumoAnormalidade != null && !colecaoConsumoAnormalidade.isEmpty()) {
			if (colecaoConsumoAnormalidade.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarConsumoAnormalidade");
					ConsumoAnormalidade consumoAnormalidade = (ConsumoAnormalidade) colecaoConsumoAnormalidade
							.iterator().next();
					sessao.setAttribute("consumoAnormalidade", consumoAnormalidade);
				} else {
					httpServletRequest.setAttribute("colecaoConsumoAnormalidade",
							colecaoConsumoAnormalidade);
				}
			} else {
				httpServletRequest.setAttribute("colecaoConsumoAnormalidade",
						colecaoConsumoAnormalidade);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
