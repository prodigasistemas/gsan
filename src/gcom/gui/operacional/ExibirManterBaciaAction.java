package gcom.gui.operacional;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.Bacia;
import gcom.operacional.FiltroBacia;

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
 * @author Arthur Carvalho
 * @date 22/05/08
 */
public class ExibirManterBaciaAction extends GcomAction {
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
				.findForward("exibirManterBacia");

		Collection colecaoBacia = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroBacia filtroBacia = (FiltroBacia) sessao
				.getAttribute("filtroBacia");

		
		filtroBacia.adicionarCaminhoParaCarregamentoEntidade(FiltroBacia.SISTEMA_ESGOTO);
		filtroBacia.setCampoOrderBy(FiltroBacia.ID);	
		
		if (filtroBacia != null && !filtroBacia.equals("")) {
			Map resultado = 
				controlarPaginacao(httpServletRequest, retorno,filtroBacia, Bacia.class.getName());
			
			colecaoBacia = (Collection) resultado.get("colecaoRetorno");
			
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoBacia != null
				&& !colecaoBacia.isEmpty()) {
			if (colecaoBacia.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarBacia");
					Bacia bacia = (Bacia) colecaoBacia
							.iterator().next();
					sessao.setAttribute("bacia", bacia);
				} else {
					httpServletRequest.setAttribute("colecaoBacia",
							colecaoBacia);
				}
			} else {
				httpServletRequest.setAttribute("colecaoBacia",
						colecaoBacia);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
