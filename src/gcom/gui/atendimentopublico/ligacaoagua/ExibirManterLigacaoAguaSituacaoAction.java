package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
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
 * @author Vinícius Medeiros
 * 
 */
public class ExibirManterLigacaoAguaSituacaoAction extends GcomAction {
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
				.findForward("exibirManterLigacaoAguaSituacao");

		Collection colecaoLigacaoAguaSituacao = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = (FiltroLigacaoAguaSituacao) sessao
				.getAttribute("filtroLigacaoAguaSituacao");

		filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.ID,
				FiltroLigacaoAguaSituacao.DESCRICAO,
				FiltroLigacaoAguaSituacao.CONSUMO_MINIMO,
				FiltroLigacaoAguaSituacao.INDICADOR_FATURAMENTO,
				FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE,
				FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO,
				FiltroLigacaoAguaSituacao.INDICADOR_AGUA_ATIVA,
				FiltroLigacaoAguaSituacao.INDICADOR_AGUA_CADASTRADA,
				FiltroLigacaoAguaSituacao.INDICADOR_AGUA_DESLIGADA,
				FiltroLigacaoAguaSituacao.INDICADOR_ANALISE_AGUA);

		if (filtroLigacaoAguaSituacao != null && !filtroLigacaoAguaSituacao.equals("")) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			colecaoLigacaoAguaSituacao = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoLigacaoAguaSituacao != null && !colecaoLigacaoAguaSituacao.isEmpty()) {
			if (colecaoLigacaoAguaSituacao.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarLigacaoAguaSituacao");
					LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) colecaoLigacaoAguaSituacao
							.iterator().next();
					sessao.setAttribute("ligacaoAguaSituacao", ligacaoAguaSituacao);
				} else {
					httpServletRequest.setAttribute("colecaoLigacaoAguaSituacao",
							colecaoLigacaoAguaSituacao);
				}
			} else {
				httpServletRequest.setAttribute("colecaoLigacaoAguaSituacao",
						colecaoLigacaoAguaSituacao);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
