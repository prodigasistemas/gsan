package gcom.gui.cobranca;

import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
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
 * @date 14/08/09
 */
public class ExibirManterCobrancaGrupoAction extends GcomAction {
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
				.findForward("exibirManterCobrancaGrupo");

		Collection colecaoCobrancaGrupo = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroCobrancaGrupo filtroCobrancaGrupo = (FiltroCobrancaGrupo) sessao
				.getAttribute("filtroCobrancaGrupo");

		
		
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.ID,
											FiltroCobrancaGrupo.ANO_MES_REFERENCIA);	
		
		if (filtroCobrancaGrupo!= null && !filtroCobrancaGrupo.equals("")) {
			Map resultado = 
				controlarPaginacao(httpServletRequest, retorno,filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			
			colecaoCobrancaGrupo= (Collection) resultado.get("colecaoRetorno");
			
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoCobrancaGrupo != null
				&& !colecaoCobrancaGrupo.isEmpty()) {
			if (colecaoCobrancaGrupo.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarCobrancaGrupo");
					CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) colecaoCobrancaGrupo
							.iterator().next();
					sessao.setAttribute("cobrancaGrupo", cobrancaGrupo);
				} else {
					httpServletRequest.setAttribute("colecaoCobrancaGrupo",
							colecaoCobrancaGrupo);
				}
			} else {
				httpServletRequest.setAttribute("colecaoCobrancaGrupo",
						colecaoCobrancaGrupo);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
