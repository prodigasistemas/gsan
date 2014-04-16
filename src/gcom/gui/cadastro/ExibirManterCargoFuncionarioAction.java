package gcom.gui.cadastro;

import gcom.cadastro.funcionario.FiltroFuncionarioCargo;
import gcom.cadastro.funcionario.FuncionarioCargo;
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
 * @date 11/08/08
 */
public class ExibirManterCargoFuncionarioAction extends GcomAction {
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
				.findForward("exibirManterCargoFuncionario");

		Collection colecaoFuncionarioCargo = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroFuncionarioCargo filtroFuncionarioCargo= (FiltroFuncionarioCargo) sessao
				.getAttribute("filtroFuncionarioCargo");

		filtroFuncionarioCargo.setCampoOrderBy(FiltroFuncionarioCargo.ID);	
		
		if (filtroFuncionarioCargo!= null && !filtroFuncionarioCargo.equals("")) {
			Map resultado = 
				controlarPaginacao(httpServletRequest, retorno,filtroFuncionarioCargo, FuncionarioCargo.class.getName());
			
			colecaoFuncionarioCargo = (Collection) resultado.get("colecaoRetorno");
			
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoFuncionarioCargo != null
				&& !colecaoFuncionarioCargo.isEmpty()) {
			if (colecaoFuncionarioCargo.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarCargoFuncionario");
					FuncionarioCargo funcionarioCargo = (FuncionarioCargo) colecaoFuncionarioCargo
							.iterator().next();
					sessao.setAttribute("funcionarioCargo", funcionarioCargo);
				} else {
					httpServletRequest.setAttribute("colecaoFuncionarioCargo",
							colecaoFuncionarioCargo);
				}
			} else {
				httpServletRequest.setAttribute("colecaoFuncionarioCargo",
						colecaoFuncionarioCargo);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
