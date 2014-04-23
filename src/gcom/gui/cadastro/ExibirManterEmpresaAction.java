package gcom.gui.cadastro;


import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
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
 * @author Arthur Carvalho
 * 
 */
public class ExibirManterEmpresaAction extends GcomAction {
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
				.findForward("exibirManterEmpresa");


		Collection colecaoEmpresa = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroEmpresa filtroEmpresa = (FiltroEmpresa) sessao
				.getAttribute("filtroEmpresa");

		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);	

		
		if (filtroEmpresa != null
				&& !filtroEmpresa.equals("")) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroEmpresa, Empresa.class.getName());
			colecaoEmpresa = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoEmpresa != null
				&& !colecaoEmpresa.isEmpty()) {
			if (colecaoEmpresa.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarEmpresa");
					Empresa empresa = (Empresa) colecaoEmpresa
							.iterator().next();
					sessao.setAttribute("empresa", empresa);
				} else {
					httpServletRequest.setAttribute("colecaoEmpresa",
							colecaoEmpresa);
				}
			} else {
				httpServletRequest.setAttribute("colecaoEmpresa",
						colecaoEmpresa);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
