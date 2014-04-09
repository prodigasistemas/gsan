package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
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
 * @date 25/08/08
 */
public class ExibirManterLigacaoEsgotoEsgotamentoAction extends GcomAction {
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
				.findForward("exibirManterLigacaoEsgotoEsgotamento");

		Collection colecaoLigacaoEsgotoEsgotamento = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = (FiltroLigacaoEsgotoEsgotamento) sessao
				.getAttribute("filtroLigacaoEsgotoEsgotamento");

		filtroLigacaoEsgotoEsgotamento.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.ID);	
		
		if (filtroLigacaoEsgotoEsgotamento!= null && !filtroLigacaoEsgotoEsgotamento.equals("")) {
			Map resultado = 
				controlarPaginacao(httpServletRequest, retorno,filtroLigacaoEsgotoEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
			
			colecaoLigacaoEsgotoEsgotamento= (Collection) resultado.get("colecaoRetorno");
			
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoLigacaoEsgotoEsgotamento != null
				&& !colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
			if (colecaoLigacaoEsgotoEsgotamento.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarLigacaoEsgotoEsgotamento");
					LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento = (LigacaoEsgotoEsgotamento) colecaoLigacaoEsgotoEsgotamento
							.iterator().next();
					sessao.setAttribute("ligacaoEsgotoEsgotamento", ligacaoEsgotoEsgotamento);
				} else {
					httpServletRequest.setAttribute("colecaoLigacaoEsgotoEsgotamento",
							colecaoLigacaoEsgotoEsgotamento);
				}
			} else {
				httpServletRequest.setAttribute("colecaoLigacaoEsgotoEsgotamento",
						colecaoLigacaoEsgotoEsgotamento);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
