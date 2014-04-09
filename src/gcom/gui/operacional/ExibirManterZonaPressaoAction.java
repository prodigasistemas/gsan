package gcom.gui.operacional;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroZonaPressao;
import gcom.operacional.ZonaPressao;

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
public class ExibirManterZonaPressaoAction extends GcomAction {
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterZonaPressao");

		Collection colecaoZonaPressao = new ArrayList();

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroZonaPressao filtroZonaPressao = (FiltroZonaPressao) sessao.getAttribute("filtroZonaPressao");
		filtroZonaPressao.adicionarCaminhoParaCarregamentoEntidade(FiltroZonaPressao.DISTRITO_OPERACIONAL);

		filtroZonaPressao.setCampoOrderBy(FiltroZonaPressao.ID,
										  FiltroZonaPressao.DESCRICAO,
										  FiltroZonaPressao.DESCRICAO_ABREVIADA);

		if (filtroZonaPressao != null && !filtroZonaPressao.equals("")) {
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroZonaPressao, ZonaPressao.class.getName());
			
			colecaoZonaPressao = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		
		}

		if (colecaoZonaPressao != null && !colecaoZonaPressao.isEmpty()) {
		
			if (colecaoZonaPressao.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null 
							|| httpServletRequest.getParameter("page.offset").equals("1"))) {
				
				// Caso o indicador ATUALIZAR esteja marcado, altera o caminho de retorno
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					
					retorno = actionMapping.findForward("exibirAtualizarZonaPressao");
					ZonaPressao zonaPressao = (ZonaPressao) colecaoZonaPressao.iterator().next();
					sessao.setAttribute("zonaPressao", zonaPressao);
				
				} else {
					
					httpServletRequest.setAttribute("colecaoZonaPressao",colecaoZonaPressao);
				
				}
			} else {
				
				httpServletRequest.setAttribute("colecaoZonaPressao",colecaoZonaPressao);
			
			}
		} else {
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
