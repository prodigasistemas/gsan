package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
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
public class ExibirManterFaturamentoGrupoAction extends GcomAction {
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
				.findForward("exibirManterFaturamentoGrupo");

		Collection colecaoFaturamentoGrupo = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = (FiltroFaturamentoGrupo) sessao
		.getAttribute("filtroFaturamentoGrupo");
		
		filtroFaturamentoGrupo.setCampoOrderBy(
				FiltroFaturamentoGrupo.ID,
				FiltroFaturamentoGrupo.DESCRICAO,
				FiltroFaturamentoGrupo.ANO_MES_REFERENCIA);
		
		if(filtroFaturamentoGrupo != null && !filtroFaturamentoGrupo.equals("")){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
			colecaoFaturamentoGrupo = (Collection) resultado
				.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}		
		
		if (colecaoFaturamentoGrupo != null && !colecaoFaturamentoGrupo.isEmpty()) {
			if (colecaoFaturamentoGrupo.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarFaturamentoGrupo");
					FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) colecaoFaturamentoGrupo
							.iterator().next();
					sessao.setAttribute("faturamentoGrupo",
							faturamentoGrupo);
				} else {
					httpServletRequest.setAttribute(
							"colecaoFaturamentoGrupo",
							colecaoFaturamentoGrupo);
				}
			} else {
				httpServletRequest.setAttribute("colecaoFaturamentoGrupo",
						colecaoFaturamentoGrupo);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
}}
