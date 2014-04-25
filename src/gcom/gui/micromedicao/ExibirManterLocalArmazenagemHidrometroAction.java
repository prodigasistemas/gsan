package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;


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
 * @date 06/08/08
 */
public class ExibirManterLocalArmazenagemHidrometroAction extends GcomAction {
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
				.findForward("exibirManterLocalArmazenagemHidrometro");

		Collection colecaoHidrometroLocalArmazenagem = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem= (FiltroHidrometroLocalArmazenagem) sessao
				.getAttribute("filtroHidrometroLocalArmazenagem");

		filtroHidrometroLocalArmazenagem.setCampoOrderBy(FiltroHidrometroLocalArmazenagem.ID);	
		
		if (filtroHidrometroLocalArmazenagem!= null && !filtroHidrometroLocalArmazenagem.equals("")) {
			Map resultado = 
				controlarPaginacao(httpServletRequest, retorno,filtroHidrometroLocalArmazenagem, HidrometroLocalArmazenagem.class.getName());
			
			colecaoHidrometroLocalArmazenagem = (Collection) resultado.get("colecaoRetorno");
			
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoHidrometroLocalArmazenagem != null
				&& !colecaoHidrometroLocalArmazenagem.isEmpty()) {
			if (colecaoHidrometroLocalArmazenagem.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarLocalArmazenagemHidrometro");
					HidrometroLocalArmazenagem hidrometroLocalArmazenagem = (HidrometroLocalArmazenagem) colecaoHidrometroLocalArmazenagem
							.iterator().next();
					sessao.setAttribute("hidrometroLocalArmazenagem", hidrometroLocalArmazenagem);
				} else {
					httpServletRequest.setAttribute("colecaoHidrometroLocalArmazenagem",
							colecaoHidrometroLocalArmazenagem);
				}
			} else {
				httpServletRequest.setAttribute("colecaoHidrometroLocalArmazenagem",
						colecaoHidrometroLocalArmazenagem);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
