package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
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
 * [UC0789] Filtrar Situacao de Ligação de Esgoto
 * 
 * @author Bruno Barros
 *
 * @date 20/05/2008
 */
public class ExibirManterLigacaoEsgotoSituacaoAction extends GcomAction {
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
				.findForward("exibirManterLigacaoEsgotoSituacao");

		Collection colecaoLigacaoEsgotoSituacao = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = (FiltroLigacaoEsgotoSituacao) sessao
				.getAttribute("filtroLigacaoEsgotoSituacao");

		filtroLigacaoEsgotoSituacao.setCampoOrderBy( FiltroLigacaoEsgotoSituacao.DESCRICAO );

		if (filtroLigacaoEsgotoSituacao != null && !filtroLigacaoEsgotoSituacao.equals("")) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			colecaoLigacaoEsgotoSituacao = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoLigacaoEsgotoSituacao != null && !colecaoLigacaoEsgotoSituacao.isEmpty()) {
			if (colecaoLigacaoEsgotoSituacao.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarLigacaoEsgotoSituacao");
					LigacaoEsgotoSituacao LigacaoEsgotoSituacao = (LigacaoEsgotoSituacao) colecaoLigacaoEsgotoSituacao
							.iterator().next();
					sessao.setAttribute("LigacaoEsgotoSituacao", LigacaoEsgotoSituacao);
				} else {
					httpServletRequest.setAttribute("colecaoLigacaoEsgotoSituacao",
							colecaoLigacaoEsgotoSituacao);
				}
			} else {
				httpServletRequest.setAttribute("colecaoLigacaoEsgotoSituacao",
						colecaoLigacaoEsgotoSituacao);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
