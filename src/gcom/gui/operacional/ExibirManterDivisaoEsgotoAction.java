package gcom.gui.operacional;


import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;

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
 * @date 10/06/08
 */
public class ExibirManterDivisaoEsgotoAction extends GcomAction {
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
				.findForward("exibirManterDivisaoEsgoto");

		Collection colecaoDivisaoEsgoto = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroDivisaoEsgoto filtroDivisaoEsgoto= (FiltroDivisaoEsgoto) sessao
				.getAttribute("filtroDivisaoEsgoto");
		filtroDivisaoEsgoto.adicionarCaminhoParaCarregamentoEntidade(FiltroDivisaoEsgoto.UNIDADE_ORGANIZACIONAL);
		
		filtroDivisaoEsgoto.setCampoOrderBy(FiltroDivisaoEsgoto.ID);	

		
		if (filtroDivisaoEsgoto != null
				&& !filtroDivisaoEsgoto.equals("")) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroDivisaoEsgoto, DivisaoEsgoto.class.getName());
			colecaoDivisaoEsgoto = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoDivisaoEsgoto != null
				&& !colecaoDivisaoEsgoto.isEmpty()) {
			if (colecaoDivisaoEsgoto.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarDivisaoEsgoto");
					DivisaoEsgoto divisaoEsgoto= (DivisaoEsgoto) colecaoDivisaoEsgoto
							.iterator().next();
					sessao.setAttribute("DivisaoEsgoto", divisaoEsgoto);
				} else {
					httpServletRequest.setAttribute("colecaoDivisaoEsgoto",
							colecaoDivisaoEsgoto);
				}
			} else {
				httpServletRequest.setAttribute("colecaoDivisaoEsgoto",
						colecaoDivisaoEsgoto);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
