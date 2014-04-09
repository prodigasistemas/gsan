package gcom.gui.cadastro;

import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FonteAbastecimento;
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
 * @date 14/08/08
 */
public class ExibirManterFonteAbastecimentoAction extends GcomAction {
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
				.findForward("exibirManterFonteAbastecimento");

		Collection colecaoFonteAbastecimento = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroFonteAbastecimento filtroFonteAbastecimento = (FiltroFonteAbastecimento) sessao
				.getAttribute("filtroFonteAbastecimento");

		filtroFonteAbastecimento.setCampoOrderBy(FiltroFonteAbastecimento.ID);	
		
		if (filtroFonteAbastecimento!= null && !filtroFonteAbastecimento.equals("")) {
			Map resultado = 
				controlarPaginacao(httpServletRequest, retorno,filtroFonteAbastecimento, FonteAbastecimento.class.getName());
			
			colecaoFonteAbastecimento = (Collection) resultado.get("colecaoRetorno");
			
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoFonteAbastecimento != null
				&& !colecaoFonteAbastecimento.isEmpty()) {
			if (colecaoFonteAbastecimento.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarFonteAbastecimento");
					FonteAbastecimento fonteAbastecimento = (FonteAbastecimento) colecaoFonteAbastecimento
							.iterator().next();
					sessao.setAttribute("fonteAbastecimento", fonteAbastecimento);
				} else {
					httpServletRequest.setAttribute("colecaoFonteAbastecimento",
							colecaoFonteAbastecimento);
				}
			} else {
				httpServletRequest.setAttribute("colecaoFonteAbastecimento",
						colecaoFonteAbastecimento);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
