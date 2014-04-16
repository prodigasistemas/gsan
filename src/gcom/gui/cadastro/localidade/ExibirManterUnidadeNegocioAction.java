package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

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
 * [UC0???]Manter Unidade Negocio
 * 
 * @author Rômulo Aurélio
 * @date 30/09/2008
 */

public class ExibirManterUnidadeNegocioAction extends GcomAction {
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
				.findForward("exibirManterUnidadeNegocio");

		Collection colecaoUnidadeNegocio = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroUnidadeNegocio filtroUnidadeNegocio = (FiltroUnidadeNegocio) sessao
				.getAttribute("filtroUnidadeNegocio");

		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.ID);

		if (filtroUnidadeNegocio != null && !filtroUnidadeNegocio.equals("")) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			colecaoUnidadeNegocio = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
			if (colecaoUnidadeNegocio.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("atualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarUnidadeNegocio");
					UnidadeNegocio unidadeNegocio = (UnidadeNegocio) colecaoUnidadeNegocio
							.iterator().next();
					sessao.setAttribute("unidadeNegocio", unidadeNegocio);
				} else {
					httpServletRequest.setAttribute("colecaoUnidadeNegocio",
							colecaoUnidadeNegocio);
				}
			} else {
				httpServletRequest.setAttribute("colecaoUnidadeNegocio",
						colecaoUnidadeNegocio);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
